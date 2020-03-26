package com.mobiledevpro.app.ui.statistic.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.mobiledevpro.app.common.BaseViewModel
import com.mobiledevpro.app.common.Event
import com.mobiledevpro.app.utils.provider.ResourceProvider
import com.mobiledevpro.domain.common.Result
import com.mobiledevpro.domain.model.DayStatistic
import com.mobiledevpro.domain.statistic.data.StatisticDataInteractor
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * ViewModel for statistic fragment
 */
class StatisticCountryViewModel(
    resourceProvider: ResourceProvider,
    statisticInteractor: StatisticDataInteractor,
    query: String
) : BaseViewModel(), LifecycleObserver {

    private val _eventShowError = MutableLiveData<Event<String>>()
    val eventShowError: LiveData<Event<String>> = _eventShowError

    private val _isShowProgressStatistic = MutableLiveData<Boolean>()
    val isShowProgressStatistic: LiveData<Boolean> = _isShowProgressStatistic

    private val _statisticCountry = MutableLiveData<List<DayStatistic>>()
    val statisticCountry: LiveData<List<DayStatistic>> = _statisticCountry

    init {
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
                        _statisticCountry.value = it.data.dayStatistics
                    }
                    is Result.Failure -> {
                        val errorMessage = resourceProvider.getErrorMessage(it.error)
                        _eventShowError.value = Event(errorMessage)
                    }
                }

            }
            .addTo(subscriptions)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartView() {
        // do something if needed
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopView() {
        // do something if needed
    }

}