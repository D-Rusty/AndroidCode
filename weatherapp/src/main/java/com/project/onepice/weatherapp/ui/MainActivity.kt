package com.project.onepice.weatherapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.idescout.sql.SqlScoutServer
import com.project.onepice.weatherapp.R
import com.project.onepice.weatherapp.domain.commands.RequestForecastCommand
import com.project.onepice.weatherapp.domain.model.ForecastList
import com.project.onepice.weatherapp.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), ToolbarManager {

    val zipCode: Long by DelegatesExt.preference(this, SettingsActivity.ZIP_CODE, SettingsActivity.DEFAULT_ZIP)

    /*为toolbar lazy  进行懒加载配置*/
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolBar()
        forecast_list.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecast_list)
        SqlScoutServer.create(this, packageName)

    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }


    private fun loadForecast() = async(UI) {
        val result = bg { RequestForecastCommand(zipCode).execute() }
        updateUI(result.await())
    }


    private fun updateUI(weekForecast: ForecastList) {

        val adapter = ForecastListAdapter(weekForecast) {
            startActivity<DetailActivity>(DetailActivity.ID to weekForecast.id, DetailActivity.CITY_NAME to weekForecast.city)
        }
        forecast_list.adapter = adapter
        toolbarTitle = "${weekForecast.city} (${weekForecast.country})"
    }
}






