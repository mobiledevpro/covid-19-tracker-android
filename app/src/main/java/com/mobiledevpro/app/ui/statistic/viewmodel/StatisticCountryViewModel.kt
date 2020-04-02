package com.mobiledevpro.app.ui.statistic.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.github.mikephil.charting.data.Entry
import com.mobiledevpro.app.common.BaseViewModel
import com.mobiledevpro.app.common.Event
import com.mobiledevpro.app.utils.provider.ResourceProvider
import com.mobiledevpro.app.utils.toFloatDate
import com.mobiledevpro.domain.common.Result
import com.mobiledevpro.domain.model.DayStatistic
import com.mobiledevpro.domain.statistic.data.StatisticDataInteractor
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * ViewModel for statistic fragment
 */
class StatisticCountryViewModel(
    private val resourceProvider: ResourceProvider,
    private val statisticInteractor: StatisticDataInteractor
) : BaseViewModel(), LifecycleObserver {

    private val _eventShowError = MutableLiveData<Event<String>>()
    val eventShowError: LiveData<Event<String>> = _eventShowError

    private val _isShowProgressStatistic = MutableLiveData<Boolean>()
    val isShowProgressStatistic: LiveData<Boolean> = _isShowProgressStatistic

    private val _statisticCountry = MutableLiveData<List<DayStatistic>>()
    val statisticCountry: LiveData<List<DayStatistic>> = _statisticCountry

    private val _chartEntries = MutableLiveData<ChartLinesView>()
    val chartEntriesView: LiveData<ChartLinesView> = _chartEntries

    private val _totalData = MutableLiveData<TotalStatisticView>()
    val totalData: LiveData<TotalStatisticView> = _totalData


    /**
     * It should to be called in Fragment onCreate() or onCreateView()
     */
    fun setCountryName(name: String) {
        observeConfirmedList(name)
    }

    private fun observeConfirmedList(query: String) {
        statisticInteractor
            .observeStatisticByCountryName(query = query)
            .doOnSubscribe {
                _isShowProgressStatistic.value = true
            }
            .doOnNext {
                _isShowProgressStatistic.value = false
            }
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        _statisticCountry.value = it.data.dayStatistics.reversed()
                        _totalData.value = mapDayStatisticToTotal(it.data.dayStatistics.last())
                        mapDaysStatisticToChartView(it.data.dayStatistics)
                    }
                    is Result.Failure -> {
                        val errorMessage = resourceProvider.getErrorMessage(it.error)
                        _eventShowError.value = Event(errorMessage)
                    }
                }

            }
            .addTo(subscriptions)
    }

    private fun mapDaysStatisticToChartView(dayStatistics: List<DayStatistic>) {
        val entries = ChartLinesView()

        dayStatistics.forEach {
            if (it.totalConfirmed != 0L)
                entries.apply {
                    confirmed.add(
                        Entry(
                            it.date.toFloatDate(),
                            it.totalConfirmed.toFloat()
                        )
                    )
                    death.add(
                        Entry(
                            it.date.toFloatDate(),
                            it.totalDeaths.toFloat()
                        )
                    )
                    recovered.add(
                        Entry(
                            it.date.toFloatDate(),
                            it.totalRecovered.toFloat()
                        )
                    )
                }

            _chartEntries.value = entries
        }
    }

    private fun mapDayStatisticToTotal(dayStatistic: DayStatistic) = TotalStatisticView(
        totalConfirmed = dayStatistic.totalConfirmed,
        totalRecovered = dayStatistic.totalRecovered,
        totalDeaths = dayStatistic.totalDeaths
    )

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartView() {
        // do something if needed
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopView() {
        // do something if needed
    }

    data class ChartLinesView(
        val confirmed: ArrayList<Entry> = arrayListOf(),
        val death: ArrayList<Entry> = arrayListOf(),
        val recovered: ArrayList<Entry> = arrayListOf()
    )

    data class TotalStatisticView(
        val totalConfirmed: Long,
        val totalDeaths: Long,
        val totalRecovered: Long
    )
}