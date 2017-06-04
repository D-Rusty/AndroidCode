package com.project.onepice.weatherapp.domain.commands

import com.project.onepice.weatherapp.domain.datasource.ForecastProvider
import com.project.onepice.weatherapp.domain.model.ForecastList

/**
 * 作者: 方天文
 * 日期: 2017/6/1:下午11:49
 * 概况:
 */
class RequestForecastCommand(private val zipCode: Long, val forecastProvider: ForecastProvider = ForecastProvider()) : Command<ForecastList> {
    /*初始化DAYS这些基础变量*/
    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList {
        val forecastlist: ForecastList = forecastProvider.requestByZipCode(zipCode, DAYS)
        return forecastlist
    }
}