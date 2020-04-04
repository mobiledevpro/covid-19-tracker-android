package com.mobiledevpro.app.utils

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Send data to Analytics
 *
 * Created by Dmitriy Chernysh on Apr 04, 2020.
 *
 * http://mobile-dev.pro
 *
 */

private const val ANALYTICS_KEY_EVENT_APP_LINK_SHARED = "app_link_shared"
private const val ANALYTICS_KEY_EVENT_COUNTRY_SELECTED = "app_link_shared"

private const val ANALYTICS_BUNDLE_KEY_COUNTRY_NAME = "country_details"

fun FirebaseAnalytics.logEventShareAppLink() {
    val bundle = Bundle()
    logEvent(ANALYTICS_KEY_EVENT_APP_LINK_SHARED, bundle)
}

fun FirebaseAnalytics.logEventCountrySelected(countryName: String) {
    val bundle = Bundle()
    bundle.putString(ANALYTICS_BUNDLE_KEY_COUNTRY_NAME, countryName)
    logEvent(ANALYTICS_KEY_EVENT_COUNTRY_SELECTED, bundle)
}
