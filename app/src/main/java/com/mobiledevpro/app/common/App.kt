package com.mobiledevpro.app.common

import android.app.Application
import com.mobiledevpro.app.BuildConfig
import com.mobiledevpro.app.di.dataLocalModule
import com.mobiledevpro.app.di.dataModule
import com.mobiledevpro.app.di.dataRemoteModule
import com.mobiledevpro.app.di.domainModule
import com.mobiledevpro.app.di.uiModule
import com.testfairy.TestFairy
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Main application class
 *
 * Created by Dmitriy Chernysh
 *
 *
 * http://androiddev.pro
 *
 *
 * #MobileDevPro
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()

        //Beta testing (where release is published)
        TestFairy.begin(this, "6f9121c053a0dabdfa96dbb31c5c128860c119b3");
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(getModules())
        }
    }

    private fun getModules() = listOf(
        uiModule,
        domainModule,
        dataModule,
        dataLocalModule,
        dataRemoteModule
    )

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
//            Timber.plant(CrashlyticsTree())
        }
    }
}
