package com.mobiledevpro.data.repository.userdata

import com.mobiledevpro.data.DataFactory
import com.mobiledevpro.data.model.CountryTotalEntity
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
    fun `get local countries and return success result`() {

        val query = DataFactory.randomString()

        val countryTotalEntity = CountryTotalEntity(
            id = DataFactory.randomInt(),
            recovered = DataFactory.randomInt(),
            deaths = DataFactory.randomInt(),
            confirmed = DataFactory.randomInt(),
            active = DataFactory.randomInt(),
            country = DataFactory.randomString(),
            latitude = DataFactory.randomDouble(),
            longitude = DataFactory.randomDouble(),
            updated = DataFactory.randomLong()
        )

        val result = Observable.just(listOf(countryTotalEntity))

        whenever(totalCovidCache.getLocalCountriesObservable(any())).thenReturn(result)

        repository.getLocalCountriesObservable(query).test().apply {
            assertNoErrors()
            assertComplete()
        }

        verify(totalCovidCache).getLocalCountriesObservable(query)
    }
}