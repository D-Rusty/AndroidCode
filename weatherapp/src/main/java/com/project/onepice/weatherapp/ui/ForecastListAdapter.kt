package com.project.onepice.weatherapp.ui

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.project.onepice.weatherapp.R
import com.project.onepice.weatherapp.domain.model.Forecast
import com.project.onepice.weatherapp.domain.model.ForecastList
import com.project.onepice.weatherapp.extensions.ctx
import com.project.onepice.weatherapp.extensions.toDateString
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * 作者: 方天文
 * 日期: 2017/6/1:下午10:09
 * 概况:
 */
class ForecastListAdapter(val weekForecast: ForecastList, val itemClick: (Forecast) -> Unit) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_forecast, parent, false);
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = weekForecast.size()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        Log.i("testt", weekForecast.size().toString())
        holder?.bindForecast(weekForecast[position])

    }


    class ViewHolder(view: View, val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Glide.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date.toDateString()
                itemView.description.text = description
                itemView.maxTemperature.text = "${hight}º"
                itemView.minTemperature.text = "${low}º"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }


    interface onItemClickListener {
        operator fun invoke(forecast: Forecast)
    }

}