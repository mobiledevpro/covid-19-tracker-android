package com.mobiledevpro.app.ui.main.viemodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobiledevpro.app.common.BaseViewModel
import com.mobiledevpro.app.common.Event
import com.mobiledevpro.app.utils.FabActionNavigation
import com.mobiledevpro.app.utils.Navigation
import com.mobiledevpro.data.LOG_TAG_DEBUG


/**
 * Main view model for Main Activity
 *
 * Created by Dmitriy Chernysh on Mar 23, 2020.
 *
 * http://androiddev.pro
 *
 */
class MainViewModel : BaseViewModel() {

    private val _eventNavigateTo = MutableLiveData<Event<Navigation>>()
    val eventNavigateTo: LiveData<Event<Navigation>> = _eventNavigateTo

    private val _eventFabAction = MutableLiveData<Event<FabActionNavigation>>()
    val eventFabAction: LiveData<Event<FabActionNavigation>> = _eventFabAction

    init {
        Log.d(LOG_TAG_DEBUG, "MainViewModel init");
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(LOG_TAG_DEBUG, "MainViewModel onCleared");
    }

    fun showCountriesList() {
        _eventNavigateTo.value =
            Event(Navigation.NAVIGATE_TO_COUNTRIES_LIST)
    }

    fun showSearchCountryBar() {
        _eventNavigateTo.value =
            Event(Navigation.NAVIGATE_TO_SEARCH_COUNTRY)
    }

    fun setFabActionShowCountries() {
        _eventFabAction.value =
            Event(FabActionNavigation.ACTION_SHOW_COUNTRIES)
    }

    fun setFabActionShowCountrySearch() {
        _eventFabAction.value =
            Event(FabActionNavigation.ACTION_SHOW_COUNTRY_SEARCH_BAR)
    }

}