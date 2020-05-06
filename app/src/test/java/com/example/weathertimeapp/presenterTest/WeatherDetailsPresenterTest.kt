package com.example.weathertimeapp.presenterTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weathertimeapp.data.entity.Day
import com.example.weathertimeapp.mvp.contract.WeatherDetailsContract
import com.example.weathertimeapp.mvp.presenter.WeatherDetailsPresenter
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

class WeatherDetailsPresenterTest {

    private val mockedModel: WeatherDetailsContract.Model = mock()
    private val mockedView: WeatherDetailsContract.View = mock()
    private lateinit var presenter: WeatherDetailsContract.Presenter
    private val day: Day = mock()
    private val cityData: Pair<Int, Int> = Pair(ID_CITY, DEFAULT_POSITION)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        presenter = WeatherDetailsPresenter(mockedModel, mockedView, cityData)
    }

    @Test
    fun `on card view pressed, search forecast and show on view`() {
        val dayObservable = Observable.just(day)
        whenever(mockedModel.getExtendedWeather(cityData)).thenReturn(dayObservable)
        presenter.requestAndShowExtendedWeather()

        verify(mockedView).showLoading()
        verify(mockedModel).getExtendedWeather(cityData)
        verify(mockedView).showExtendedWeather(day)
        verify(mockedView).hideLoading()

        assertEquals(dayObservable, mockedModel.getExtendedWeather(cityData))
        assertNotNull(mockedModel.getExtendedWeather(cityData))
    }

    @Test
    fun `on card view pressed, with a connection problem, show "network connection" error`() {
        val dayError = Observable.error<Day>(Throwable())
        whenever(mockedModel.getExtendedWeather(cityData)).thenReturn(dayError)
        presenter.requestAndShowExtendedWeather()

        verify(mockedView).showLoading()
        verify(mockedModel).getExtendedWeather(cityData)
        verify(mockedView).hideLoading()
        verify(mockedView).showToastNetworkError()

        assertEquals(dayError,mockedModel.getExtendedWeather(cityData))
        assertNotNull(mockedModel.getExtendedWeather(cityData))
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
        private const val DEFAULT_POSITION = 0
        private const val NO_DELAY = 0
    }
}