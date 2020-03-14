package com.mobiledevpro.app.ui.mainscreen.viewmodel

import androidx.lifecycle.*
import com.mobiledevpro.app.ui.BaseViewModel
import com.mobiledevpro.domain.totaldata.TotalDataInteractor
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * ViewModel for main fragment
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class TotalViewModel(private val interactor: TotalDataInteractor) : BaseViewModel(), LifecycleObserver {

    private val _isShowProgressTotalConfirmed = MutableLiveData<Boolean>()
    val isShowProgressTotalConfirmed: LiveData<Boolean> = _isShowProgressTotalConfirmed

    private val _isShowProgressTotalDeaths = MutableLiveData<Boolean>()
    val isShowProgressTotalDeaths: LiveData<Boolean> = _isShowProgressTotalDeaths

    private val _isShowProgressTotalRecovered = MutableLiveData<Boolean>()
    val isShowProgressTotalRecovered: LiveData<Boolean> = _isShowProgressTotalRecovered

    private val _totalConfirmed = MutableLiveData<String>()
    val totalConfirmed: LiveData<String> = _totalConfirmed

    private val _totalDeaths = MutableLiveData<String>()
    val totalDeaths: LiveData<String> = _totalDeaths

    private val _totalRecovered = MutableLiveData<String>()
    val totalRecovered: LiveData<String> = _totalRecovered

    private val _updateTime = MutableLiveData<String>()
    val updateTime: LiveData<String> = _updateTime


    init {
        interactor.refreshTotalData()
                .subscribeBy {
                    //do nothing
                }
                .addTo(subscriptions)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartView() {
        getTotalData()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopView() {

    }

    private fun getTotalData() {
        interactor.observeTotalData()
                .doOnSubscribe {
                    _isShowProgressTotalConfirmed.value = true
                    _isShowProgressTotalDeaths.value = true
                    _isShowProgressTotalRecovered.value = true
                }
                .subscribeBy {
                    val formatter = DecimalFormat("#,###,###")
                    _totalConfirmed.value = formatter.format(it.confirmed)
                    _totalDeaths.value = formatter.format(it.deaths)
                    _totalRecovered.value = formatter.format(it.recovered)
                    _updateTime.value = "Updated on ${dateToString(it.updateTime)}"

                    if (it.confirmed >= 0)
                        _isShowProgressTotalConfirmed.value = false

                    if (it.deaths >= 0)
                        _isShowProgressTotalDeaths.value = false

                    if (it.recovered >= 0)
                        _isShowProgressTotalRecovered.value = false
                }
                .addTo(subscriptions)
    }


    private fun dateToString(date: Long): String {
        val dateFormat = SimpleDateFormat(" E, dd MMM yyyy HH:mm:ss z", Locale.getDefault())
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"))
        val today: Date = Calendar.getInstance().getTime()
        return dateFormat.format(today);
    }


}