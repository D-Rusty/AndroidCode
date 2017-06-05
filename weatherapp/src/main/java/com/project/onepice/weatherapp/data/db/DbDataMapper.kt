package com.project.onepice.weatherapp.data.db

import com.project.onepice.weatherapp.domain.model.Forecast
import com.project.onepice.weatherapp.domain.model.ForecastList


/**
 * 作者: 方天文
 * 日期: 2017/6/2:下午1:56
 * 概况:
 */
class DbDataMapper {
    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
        DayForecast(date, description, hight, low, iconUrl, cityId)
    }


    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)

    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(date, description, high, low, iconUrl)
    }
}