package com.project.onepice.weatherapp.data.server

import com.google.gson.Gson
import java.net.URL

/**
 * 作者: 方天文
 * 日期: 2017/6/1:下午10:40
 * 概况:
 */
public class ForecastByZipCodeRequest(val zipCode: Long) {
    companion object {
        private val APP_ID="15646a06818f61f7b8d7823ca833e1ce"
        private val BASE_URL="http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL="${BASE_URL}&APPID=${APP_ID}&q="
    }

    fun execute(): ForecastResult {
        val forecastJsonStr=URL(COMPLETE_URL + zipCode.toString()).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}