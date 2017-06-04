package com.project.onepice.weatherapp.domain.datasource

import com.project.onepice.weatherapp.domain.model.Forecast
import com.project.onepice.weatherapp.domain.model.ForecastList

/**
 * 作者: 方天文
 * 日期: 2017/6/2:下午3:06
 * 概况:
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
    fun requestDayForecast(id: Long): Forecast?
}