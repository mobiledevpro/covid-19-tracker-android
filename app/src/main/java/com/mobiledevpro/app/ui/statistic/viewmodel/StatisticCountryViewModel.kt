package com.mobiledevpro.app.ui.statistic.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.github.mikephil.charting.data.Entry
import com.jakewharton.rxrelay2.BehaviorRelay
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

    private val chartData = BehaviorRelay.create<ChartLines>()

    init {
        //TODO: check why null error without empty list
        _statisticCountry.value = listOf()
    }

    fun observeConfirmedList(query: String) {
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
                        mapConfirmedStatisticToChartView(it.data.dayStatistics)

                    }
                    is Result.Failure -> {
                        val errorMessage = resourceProvider.getErrorMessage(it.error)
                        _eventShowError.value = Event(errorMessage)
                    }
                }

            }
            .addTo(subscriptions)
    }

    fun observeChartData() = chartData

    private fun mapConfirmedStatisticToChartView(dayStatistics: List<DayStatistic>) {
        val entries = ChartLines()

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

            chartData.accept(entries)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartView() {
        // do something if needed
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopView() {
        // do something if needed
    }

    data class ChartLines(
        val confirmed: ArrayList<Entry> = arrayListOf(),
        val death: ArrayList<Entry> = arrayListOf(),
        val recovered: ArrayList<Entry> = arrayListOf()
    )
}