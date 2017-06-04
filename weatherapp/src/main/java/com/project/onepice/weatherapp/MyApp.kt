package com.project.onepice.weatherapp

import android.app.Application
import com.project.onepice.weatherapp.data.db.ForecastDbHelper
import com.project.onepice.weatherapp.extensions.DelegatesExt

/**
 * 作者: 方天文
 * 日期: 2017/6/2:上午10:46
 * 概况:
 */
class MyApp : Application() {
    companion object {
        var instance: MyApp by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ForecastDbHelper()
    }
}