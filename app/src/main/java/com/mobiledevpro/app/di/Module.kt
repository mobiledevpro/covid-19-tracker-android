package com.mobiledevpro.app.di

import com.mobiledevpro.app.ui.total.viewmodel.TotalViewModel
import com.mobiledevpro.app.utils.provider.DefaultResourceProvider
import com.mobiledevpro.app.utils.provider.ResourceProvider
import com.mobiledevpro.data.repository.userdata.CovidCache
import com.mobiledevpro.data.repository.userdata.CovidRemote
import com.mobiledevpro.data.repository.userdata.DefaultTotalDataRepository
import com.mobiledevpro.domain.totaldata.DefaultTotalDataInteractor
import com.mobiledevpro.domain.totaldata.TotalDataInteractor
import com.mobiledevpro.domain.totaldata.TotalDataRepository
import com.mobiledevpro.local.database.DefaultCovidCache
import com.mobiledevpro.local.storage.PreferencesHelper
import com.mobiledevpro.local.storage.PreferencesHelperImpl
import com.mobiledevpro.remote.implementation.DefaultTotalDataIRemote
import com.mobiledevpro.remote.service.RemoteServiceFactory
import com.mobiledevpro.remote.service.http.OkHttpFactory
import com.mobiledevpro.remote.service.interceptor.ApiRequestInterceptor
import com.mobiledevpro.remote.service.interceptor.ApiResponseInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin modules
 *
 * Created by Dmitriy Chernysh on Feb 29, 2020.
 *
 * http://androiddev.pro
 *
 */

val uiModule = module {
    viewModel { TotalViewModel(get(), get()) }

    single { DefaultResourceProvider(androidContext().resources) as ResourceProvider }
}

val domainModule = module {
    single { DefaultTotalDataInteractor(get()) as TotalDataInteractor }
}

val dataModule = module {
    single { DefaultTotalDataRepository(get(), get()) as TotalDataRepository }
}

val dataLocalModule = module {
    single { DefaultTotalDataIRemote(get()) as CovidRemote }
    single { DefaultCovidCache(get()) as CovidCache }
    single { PreferencesHelperImpl(get()) as PreferencesHelper }
}

val dataRemoteModule = module {
    // retrofit instance, firebase database, etc
    single { RemoteServiceFactory(get()).buildStackOverFlowApi() }
    single {
        OkHttpFactory().buildOkHttpClient(
            listOf(
                ApiResponseInterceptor(),
                ApiRequestInterceptor()
            )
        )
    }
}

