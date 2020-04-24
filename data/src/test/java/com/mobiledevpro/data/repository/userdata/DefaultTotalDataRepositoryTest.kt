package com.mobiledevpro.data.repository.userdata

import com.mobiledevpro.data.factory.DataFactory
import com.mobiledevpro.data.factory.TotalFactory
import com.mobiledevpro.domain.totaldata.TotalDataRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DefaultTotalDataRepositoryTest {

    @Mock
    private lateinit var totalCovidCache: TotalCovidCache

    @Mock
    private lateinit var totalCovidRemote: TotalCovidRemote

    private lateinit var repository: TotalDataRepository


    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        repository = DefaultTotalDataRepository(
            totalCovidCache = totalCovidCache,
            totalCovidRemote = totalCovidRemote
        )
    }

    @Test
    fun `call local total data and return success`() {
        val totalEntity = TotalFactory.randomTotalEntity()

        val result = Observable.just(totalEntity)

        whenever(totalCovidCache.getTotalDataObservable()).thenReturn(result)

        repository.getLocalTotalDataObservable().test().apply {
            assertComplete()
            assertNoErrors()
        }

        verify(totalCovidCache).getTotalDataObservable()
    }

    @Test
    fun `get local countries and return success result`() {

        val query = DataFactory.randomString()

        val countryTotalEntity = TotalFactory.randomCountryTotalEntity()

        val result = Observable.just(listOf(countryTotalEntity))

        whenever(totalCovidCache.getLocalCountriesObservable(any())).thenReturn(result)

        repository.getLocalCountriesObservable(query).test().apply {
            assertNoErrors()
            assertComplete()
        }

        verify(totalCovidCache).getLocalCountriesObservable(query)
    }
}