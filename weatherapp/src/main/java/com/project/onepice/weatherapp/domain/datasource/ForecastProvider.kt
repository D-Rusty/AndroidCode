package com.project.onepice.weatherapp.domain.datasource

import com.project.onepice.weatherapp.data.db.ForecastDb
import com.project.onepice.weatherapp.data.server.ForecastServer
import com.project.onepice.weatherapp.domain.model.Forecast
import com.project.onepice.weatherapp.domain.model.ForecastList
import com.project.onepice.weatherapp.extensions.firstResult

/**
 * 作者: 方天文
 * 日期: 2017/6/2:下午5:01
 * 概况:
 */
class ForecastProvider(val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES by lazy { listOf(ForecastDb(), ForecastServer()) }
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size() >= days) res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources {
        it.requestDayForecast(id)
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }
}



























