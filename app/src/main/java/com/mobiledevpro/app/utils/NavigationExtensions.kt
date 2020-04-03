package com.mobiledevpro.app.utils

import android.app.Activity
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mobiledevpro.app.R
import com.mobiledevpro.app.ui.countries.CountriesListFragmentDirections

/**
 * Navigation Helper
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */

enum class Navigation {
    NAVIGATE_TO_COUNTRIES_LIST,
    NAVIGATE_TO_SEARCH_COUNTRY,
    NAVIGATE_CLOSE_SEARCH_COUNTRY
}

enum class FabActionNavigation {
    ACTION_HIDE,
    ACTION_SHOW_COUNTRIES,
    ACTION_SHOW_COUNTRY_SEARCH_BAR,
    ACTION_CLOSE_COUNTRY_SEARCH_BAR,
}

fun Fragment.showCountiesList() =
    this.findNavController().navigate(R.id.actionShowCountriesList)

fun Fragment.showStatisticCountry(query: String) {
    val action = CountriesListFragmentDirections.actionCountriesListFragmentToStatisticFragment(query)
    this.findNavController().navigate(action)
}

fun Activity.showCountiesList(fragmentContainerId: Int) =
    this.findNavController(fragmentContainerId).navigate(R.id.actionShowCountriesList)


fun FloatingActionButton.show(visible: Boolean) {
    this.animate().apply {
        scaleX(if (visible) 1F else 0F)
        scaleY(if (visible) 1F else 0F)
        duration = 200
        interpolator = LinearInterpolator()
        start()
    }
}