package com.mobiledevpro.app.ui.mainscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobiledevpro.app.ui.BaseViewModel
import java.text.DecimalFormat

/**
 * ViewModel for main fragment
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class TotalViewModel : BaseViewModel() {


    private val _totalConfirmed = MutableLiveData<String>()
    val totalConfirmed: LiveData<String> = _totalConfirmed

    private val _totalDeaths = MutableLiveData<String>()
    val totalDeaths: LiveData<String> = _totalDeaths

    private val _totalRecovered = MutableLiveData<String>()
    val totalRecovered: LiveData<String> = _totalRecovered


    init {
        val formatter = DecimalFormat("#,###,###")
        _totalConfirmed.value = formatter.format(144863)
        _totalDeaths.value = formatter.format(5398)
        _totalRecovered.value = formatter.format(70249)
    }

}