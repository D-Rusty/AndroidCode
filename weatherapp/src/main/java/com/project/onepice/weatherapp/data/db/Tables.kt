package com.project.onepice.weatherapp.data.db

/**
 * 作者: 方天文
 * 日期: 2017/6/2:上午11:20
 * 概况:
 */
object CityForecastTable {
    val NAME="CityForecast"
    val ID="_id"
    val CITY="city"
    val COUNTRY="country"
}

object DayForecastTable {
    val NAME="DayForecast"
    val DATE="date"
    val DESCRIPTION="description"
    val HIGH="high"
    val LOW="low"
    val ICON_URL="iconUrl"
    val CITY_ID="cityId"
}