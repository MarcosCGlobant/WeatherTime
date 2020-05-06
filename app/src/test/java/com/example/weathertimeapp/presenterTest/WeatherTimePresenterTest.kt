package com.example.weathertimeapp.presenterTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weathertimeapp.data.entity.Forecast
import com.example.weathertimeapp.mvp.contract.WeatherTimeContract
import com.example.weathertimeapp.mvp.model.WeatherTimeModel
import com.example.weathertimeapp.mvp.presenter.WeatherTimePresenter
import com.example.weathertimeapp.mvp.view.WeatherTimeView
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class WeatherTimePresenterTest {

    private val mockedModel: WeatherTimeModel = mock()
    private val mockedView: WeatherTimeView = mock()
    private lateinit var presenter: WeatherTimeContract.Presenter
    private val forecast: Forecast = mock()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        presenter = WeatherTimePresenter(mockedModel, mockedView)
    }

    @Test
    fun `on app start, create list of cities and config the view`() {
        presenter.initPresenter()

        verify(mockedModel).createListOfCities()
        verify(mockedView).setCitiesListAdapter(mockedModel.createListOfCities())
        verify(mockedView).setSoftInputMode()
    }

    @Test
    fun `on button search pressed without an input show "insert city name error"`() {
        presenter.onSearchButtonPressed(EMPTY_STRING)

        verify(mockedView).showInsertCityNameError()
        verify(mockedView).clearViewOnError()
    }

    @Test
    fun `on button search pressed with a valid city name, search forecast and show on view`() {
        val forecastObservable = Observable.just(forecast)
        whenever(mockedModel.getCityId(CITY_NAME)).thenReturn(ID_CITY)
        whenever(mockedModel.getForecastByCityId(ID_CITY)).thenReturn(forecastObservable)
        presenter.onSearchButtonPressed(CITY_NAME)

        verify(mockedView).showLoading()
        verify(mockedModel).getCityId(CITY_NAME)
        verify(mockedModel).getForecastByCityId(ID_CITY)
        verify(mockedView).showForecastRecyclerView(forecast)
        verify(mockedView).hideLoading()

        assertEquals(forecastObservable, mockedModel.getForecastByCityId(ID_CITY))
        assertNotNull(mockedModel.getForecastByCityId(ID_CITY))
    }

    @Test
    fun `on button search pressed with a connection problem, show "network connection" error`() {
        val forecastError = Observable.error<Forecast>(Throwable())
        whenever(mockedModel.getCityId(CITY_NAME)).thenReturn(ID_CITY)
        whenever(mockedModel.getForecastByCityId(ID_CITY)).thenReturn(forecastError)
        presenter.onSearchButtonPressed(CITY_NAME)

        verify(mockedView).showLoading()
        verify(mockedModel).getCityId(CITY_NAME)
        verify(mockedModel).getForecastByCityId(ID_CITY)
        verify(mockedView).hideLoading()
        verify(mockedView).showToastNetworkError()
        verify(mockedView).clearViewOnError()

        assertEquals(forecastError, mockedModel.getForecastByCityId(ID_CITY))
        assertNotNull(mockedModel.getForecastByCityId(ID_CITY))
    }

    @Test
    fun `on button search pressed with an incorrect city, show "No data to show" error`() {
        whenever(mockedModel.getCityId(CITY_NAME)).thenReturn(UNKNOWN_ID)
        presenter.onSearchButtonPressed(CITY_NAME)

        verify(mockedView).showLoading()
        verify(mockedModel).getCityId(CITY_NAME)
        verify(mockedView).hideLoading()
        verify(mockedView).showToastNoItemToShowError()
        verify(mockedView).clearViewOnError()
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            val immediate = object : Scheduler() {
                override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                    return super.scheduleDirect(run, NO_DELAY.toLong(), unit)
                }

                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }
            RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
        }

        private const val ID_CITY = 3427208
        private const val UNKNOWN_ID = -1
        private const val CITY_NAME = "Zelaya"
        private const val EMPTY_STRING = ""
        private const val NO_DELAY = 0
    }
}