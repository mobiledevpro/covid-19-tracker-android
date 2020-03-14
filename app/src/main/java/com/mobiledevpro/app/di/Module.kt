package com.mobiledevpro.app.di

import com.mobiledevpro.app.ui.mainscreen.viewmodel.TotalViewModel
import com.mobiledevpro.data.repository.userdata.TotalDataRepositoryImpl
import com.mobiledevpro.domain.totaldata.TotalDataInteractor
import com.mobiledevpro.domain.totaldata.TotalDataInteractorImpl
import com.mobiledevpro.domain.totaldata.TotalDataRepository
import com.mobiledevpro.local.database.DatabaseHelper
import com.mobiledevpro.local.database.DatabaseHelperImpl
import com.mobiledevpro.local.storage.PreferencesHelper
import com.mobiledevpro.local.storage.PreferencesHelperImpl
import com.mobiledevpro.local.storage.StorageHelper
import com.mobiledevpro.local.storage.StorageHelperImpl
import com.mobiledevpro.remote.RestApiClient
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
    viewModel { TotalViewModel(get()) }
}

val domainModule = module {
    single { TotalDataInteractorImpl(get()) as TotalDataInteractor }
}

val dataModule = module {
    single { TotalDataRepositoryImpl(get(), get()) as TotalDataRepository }
}

val dataLocalModule = module {
    single { DatabaseHelperImpl(get()) as DatabaseHelper }
    single { StorageHelperImpl(get()) as StorageHelper }
    single { PreferencesHelperImpl(get()) as PreferencesHelper }
}

val dataRemoteModule = module {
    // retrofit instance, firebase database, etc
    single { RestApiClient(get()) }
}

