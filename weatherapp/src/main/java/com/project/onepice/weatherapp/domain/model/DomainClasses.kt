package com.project.onepice.weatherapp.domain.model

/**
 * 作者: 方天文
 * 日期: 2017/6/1:下午11:36
 * 概况:
 */
data class ForecastList(val id: Long, val city: String, val country: String, val dailyForecast: List<Forecast>) {
    operator fun get(position: Int): Forecast=dailyForecast[position]
    fun size(): Int=dailyForecast.size
}

data class Forecast(val id: Long,val date: Long ,val description: String, val hight: Int, val low: Int, val iconUrl: String)