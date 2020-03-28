package com.mobiledevpro.app.di

import com.mobiledevpro.app.ui.main.viemodel.MainViewModel
import com.mobiledevpro.app.ui.statistic.viewmodel.StatisticCountryViewModel
import com.mobiledevpro.app.ui.total.viewmodel.TotalViewModel
import com.mobiledevpro.app.utils.provider.DefaultResourceProvider
import com.mobiledevpro.app.utils.provider.ResourceProvider
import com.mobiledevpro.data.repository.parcer.StatisticsParserHtml
import com.mobiledevpro.data.repository.statistic.DefaultStatisticDataRepository
import com.mobiledevpro.data.repository.statistic.StatisticCovidCache
import com.mobiledevpro.data.repository.statistic.StatisticCovidRemote
import com.mobiledevpro.data.repository.userdata.DefaultTotalDataRepository
import com.mobiledevpro.data.repository.userdata.TotalCovidCache
import com.mobiledevpro.data.repository.userdata.TotalCovidRemote
import com.mobiledevpro.domain.statistic.data.DefaultStatisticDataInteractor
import com.mobiledevpro.domain.statistic.data.StatisticDataInteractor
import com.mobiledevpro.domain.statistic.data.StatisticDataRepository
import com.mobiledevpro.domain.totaldata.DefaultTotalDataInteractor
import com.mobiledevpro.domain.totaldata.TotalDataInteractor
import com.mobiledevpro.domain.totaldata.TotalDataRepository
import com.mobiledevpro.local.database.AppDatabase
import com.mobiledevpro.local.database.statistic.DefaultStatisticsCovidCache
import com.mobiledevpro.local.database.total.DefaultTotalCovidCache
import com.mobiledevpro.local.storage.PreferencesHelper
import com.mobiledevpro.local.storage.PreferencesHelperImpl
import com.mobiledevpro.remote.implementation.DefaultStatisticCovidRemote
import com.mobiledevpro.remote.implementation.DefaultStatisticsParserHtml
import com.mobiledevpro.remote.implementation.DefaultTotalCovidRemote
import com.mobiledevpro.remote.service.RemoteServiceFactory
import com.mobiledevpro.remote.service.http.OkHttpFactory
import com.mobiledevpro.remote.service.interceptor.ApiResponseInterceptor
import com.mobiledevpro.remote.service.interceptor.ApiStatisticsRequestInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
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

    viewModel { MainViewModel() }

    viewModel {
        TotalViewModel(
            resourceProvider = get(),
            totalInteractor = get(),
            statisticInteractor = get()
        )
    }

    viewModel {
        StatisticCountryViewModel(
            resourceProvider = get(),
            statisticInteractor = get()
        )
    }

    single { DefaultResourceProvider(androidContext().resources) as ResourceProvider }
}

val domainModule = module {
    single { DefaultTotalDataInteractor(totalDataRepository = get()) as TotalDataInteractor }
    single { DefaultStatisticDataInteractor(statisticDataRepository = get()) as StatisticDataInteractor }
}

val dataModule = module {
    single {
        DefaultTotalDataRepository(
            totalCovidCache = get(),
            totalCovidRemote = get()
        ) as TotalDataRepository
    }
    single { DefaultStatisticDataRepository(
        statisticsCache = get(),
        statisticRemote = get(),
        statisticsParserHtml = get()
    ) as StatisticDataRepository }
}

val dataLocalModule = module {
    single { DefaultTotalCovidRemote(apiTotal = get()) as TotalCovidRemote }
    single { DefaultTotalCovidCache(database = get()) as TotalCovidCache }

    single { DefaultStatisticCovidRemote(apiStatistic = get()) as StatisticCovidRemote }
    single { DefaultStatisticsCovidCache(database = get()) as StatisticCovidCache }

    single { AppDatabase.getInstance(androidContext()) }

    single { PreferencesHelperImpl(appContext = get()) as PreferencesHelper }

}

val dataRemoteModule = module {
    // retrofit instance, firebase database, html parser etc
    single {
        RemoteServiceFactory(
            client = get(named(TOTAL_OK_HTTP_CLIENT))
        ).buildCovidTotalApi()
    }
    single {
        RemoteServiceFactory(
            client = get(named(STATISTIC_OK_HTTP_CLIENT))
        ).buildCovidFullStatisticsApi()
    }

    single(named(STATISTIC_OK_HTTP_CLIENT)) {
        OkHttpFactory().buildOkHttpClient(
            listOf(
                ApiResponseInterceptor(),
                ApiStatisticsRequestInterceptor()
            )
        )
    }

    single(named(TOTAL_OK_HTTP_CLIENT)) {
        OkHttpFactory().buildOkHttpClient(
            listOf(ApiResponseInterceptor())
        )
    }

    single { DefaultStatisticsParserHtml() as StatisticsParserHtml }
}


private const val TOTAL_OK_HTTP_CLIENT = "total"
private const val STATISTIC_OK_HTTP_CLIENT = "statistics"
