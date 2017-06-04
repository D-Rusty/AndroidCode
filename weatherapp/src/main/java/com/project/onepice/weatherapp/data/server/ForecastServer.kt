package com.project.onepice.weatherapp.data.server

import com.project.onepice.weatherapp.data.db.ForecastDb
import com.project.onepice.weatherapp.domain.datasource.ForecastDataSource
import com.project.onepice.weatherapp.domain.model.ForecastList

/**
 * 作者: 方天文
 * 日期: 2017/6/2:下午5:09
 * 概况:
 */
class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(), val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()
}

