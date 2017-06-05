package com.project.onepice.weatherapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.project.onepice.weatherapp.R
import com.project.onepice.weatherapp.domain.commands.RequestDayForecastCommand
import com.project.onepice.weatherapp.domain.model.Forecast
import com.project.onepice.weatherapp.extensions.color
import com.project.onepice.weatherapp.extensions.textColor
import com.project.onepice.weatherapp.extensions.toDateString
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.text.DateFormat

/**
 * 作者: 方天文
 * 日期: 2017/6/2:下午5:57
 * 概况:
 */
public class DetailActivity : AppCompatActivity() , ToolbarManager{
    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolBar()

        toolbarTitle = intent.getStringExtra(CITY_NAME)
        enableHomeAsUp { onBackPressed() }
        async {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
            uiThread {
                bindForecast(result)
            }
        }
    }

    private fun bindForecast(forecast: Forecast) = with(forecast) {
        Glide.with(ctx).load(iconUrl).into(icon)
        supportActionBar?.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(hight to maxTemperature, low to minTemperature)
    }


    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}º"
        it.second.textColor = color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}