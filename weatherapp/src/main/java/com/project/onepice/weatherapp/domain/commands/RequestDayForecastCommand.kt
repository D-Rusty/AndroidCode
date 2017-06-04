package com.project.onepice.weatherapp.domain.commands

import com.project.onepice.weatherapp.domain.datasource.ForecastProvider
import com.project.onepice.weatherapp.domain.model.Forecast

/**
 * 作者: 方天文
 * 日期: 2017/6/2:下午5:53
 * 概况:
 */
class RequestDayForecastCommand(val id: Long, val forecastProvider: ForecastProvider = ForecastProvider()) : Command<Forecast> {
    override fun execute() = forecastProvider.requestForecast(id)
}