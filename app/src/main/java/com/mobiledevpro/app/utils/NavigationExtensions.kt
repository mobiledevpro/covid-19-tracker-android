package com.mobiledevpro.app.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
    NAVIGATE_TO_SEARCH_COUNTRY
}

enum class FabActionNavigation {
    ACTION_SHOW_COUNTRIES,
    ACTION_SHOW_COUNTRY_SEARCH_BAR
}

fun Fragment.showCountiesList() =
    this.findNavController().navigate(R.id.actionShowCountriesList)

fun Fragment.showStatisticCountry(query: String) {
    val action = CountriesListFragmentDirections.actionCountriesListFragmentToStatisticFragment(query)
    this.findNavController().navigate(action)}

fun Activity.showCountiesList(fragmentContainerId: Int) =
    this.findNavController(fragmentContainerId).navigate(R.id.actionShowCountriesList)