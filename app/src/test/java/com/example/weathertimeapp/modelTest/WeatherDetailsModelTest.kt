package com.example.weathertimeapp.modelTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weathertimeapp.data.entity.Day
import com.example.weathertimeapp.mvp.contract.WeatherDetailsContract
import com.example.weathertimeapp.mvp.model.WeatherDetailsModel
import com.example.weathertimeapp.util.DEFAULT_INT
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class WeatherDetailsModelTest {

    private val model: WeatherDetailsContract.Model = WeatherDetailsModel()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `extended weather response with error`() {
        val cityData: Pair<Int, Int> = Pair(ERROR_CITY, POSITION)
        val testObserver: TestObserver<Day> = TestObserver()
        val observable: Observable<Day> = model.getExtendedWeather(cityData)
        observable.concatWith(Observable.error(RuntimeException(ERROR_MESSAGE)))
        observable.subscribe(testObserver)

        assertNotNull(model.getExtendedWeather(cityData))
        testObserver.assertNotComplete()
        testObserver.dispose()
    }

    @Test
    fun `extended weather response with item to show`() {
        val cityData: Pair<Int, Int> = Pair(ID_CITY, POSITION)
        val testObserver: TestObserver<Day> = model.getExtendedWeather(cityData).test()
        val observable: Observable<Day> = model.getExtendedWeather(cityData)
        observable.subscribe(testObserver)

        assertNotNull(model.getExtendedWeather(cityData))
        testObserver.assertComplete()
        testObserver.dispose()
    }

    companion object {
        private const val ID_CITY = 3429439
        private const val ERROR_CITY = DEFAULT_INT
        private const val POSITION = DEFAULT_INT
        private const val ERROR_MESSAGE = "error in Observable"
    }
}