package com.project.onepice.weatherapp.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.project.onepice.weatherapp.MyApp
import org.jetbrains.anko.db.*

/**
 * 作者: 方天文
 * 日期: 2017/6/2:上午11:20
 * 概况:
 */
class ForecastDbHelper(ctx: Context = MyApp.instance) : ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance by lazy { ForecastDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(CityForecastTable.NAME, true, CityForecastTable.ID to INTEGER , CityForecastTable.CITY to TEXT, CityForecastTable.COUNTRY to TEXT)
        db?.createTable(DayForecastTable.NAME, true, DayForecastTable.ID to INTEGER, DayForecastTable.DATE to INTEGER, DayForecastTable.DESCRIPTION to TEXT, DayForecastTable.HIGH to INTEGER, DayForecastTable.LOW to INTEGER, DayForecastTable.ICON_URL to TEXT, DayForecastTable.CITY_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable(CityForecastTable.NAME, true)
        db?.dropTable(DayForecastTable.NAME, true)
        onCreate(db)
    }
}