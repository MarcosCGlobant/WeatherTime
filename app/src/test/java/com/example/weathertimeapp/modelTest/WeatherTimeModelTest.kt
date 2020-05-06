package com.example.weathertimeapp.modelTest

import android.content.res.AssetManager
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weathertimeapp.data.entity.Forecast
import com.example.weathertimeapp.mvp.contract.WeatherTimeContract
import com.example.weathertimeapp.mvp.model.WeatherTimeModel
import com.example.weathertimeapp.util.DEFAULT_INT
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class WeatherTimeModelTest {

    private val assetManager: AssetManager = mock()
    private val model: WeatherTimeContract.Model = WeatherTimeModel(assetManager)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `return "UNKNOWN" with wrong city name `() {
        assertEquals(UNKNOWN_ID, model.getCityId(WRONG_CITY_NAME))
    }

    @Test
    fun `forecast weather response with error`() {
        val testObserver: TestObserver<Forecast> = TestObserver()
        val observable: Observable<Forecast> = model.getForecastByCityId(ERROR_CITY_ID)
        observable.concatWith(Observable.error(RuntimeException(ERROR_MESSAGE)))
        observable.subscribe(testObserver)

        assertNotNull(model.getForecastByCityId(ERROR_CITY_ID))
        testObserver.assertNotComplete()
        testObserver.dispose()
    }

    @Test
    fun `forecast weather response with item to show`() {
        val testObserver: TestObserver<Forecast> = model.getForecastByCityId(ID_CITY).test()
        val observable: Observable<Forecast> = model.getForecastByCityId(ID_CITY)
        observable.subscribe(testObserver)

        assertNotNull(model.getForecastByCityId(ID_CITY))
        testObserver.assertComplete()
        testObserver.dispose()
    }

    companion object {
        private const val ID_CITY = 3427208
        private const val ERROR_CITY_ID = DEFAULT_INT
        private const val WRONG_CITY_NAME = "Paris"
        private const val UNKNOWN_ID = -1
        private const val ERROR_MESSAGE = "error in Observable"
    }
}