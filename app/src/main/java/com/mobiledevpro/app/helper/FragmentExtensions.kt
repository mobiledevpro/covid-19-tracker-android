package com.mobiledevpro.app.helper

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mobiledevpro.app.R

/**
 * Navigation Helper
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */

fun Fragment.showCountiesList() =
        this.findNavController().navigate(R.id.actionShowCountriesList)