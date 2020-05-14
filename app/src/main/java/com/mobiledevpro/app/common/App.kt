package com.mobiledevpro.app.common

import android.app.Application
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.mobiledevpro.app.BuildConfig
import com.mobiledevpro.app.di.*
import com.mobiledevpro.data.LOG_TAG_DEBUG
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

        if (BuildConfig.DEBUG) {
            // initStetho()
            //  initFlipper()

            //its only for debug
            retrieveFirebaseToken()
        }

        //SDK token from testfairy.com/settings
        val testFairyAppToken = BuildConfig.TESTFAIRY_APP_TOKEN
        if (testFairyAppToken.isEmpty())
            throw RuntimeException("TestFairy SDK token should not be empty!")
        //Beta testing (where release is published)
        TestFairy.begin(this, testFairyAppToken)
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

    private fun retrieveFirebaseToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d(LOG_TAG_DEBUG, "retrieveFirebaseToken failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                Log.d(LOG_TAG_DEBUG, "Firebase token: $token")
            })
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
//            Timber.plant(CrashlyticsTree())
        }
    }
}
