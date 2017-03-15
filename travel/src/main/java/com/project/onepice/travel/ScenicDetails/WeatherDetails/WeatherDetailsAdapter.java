package com.project.onepice.travel.ScenicDetails.WeatherDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.project.onepice.travel.R;
import com.project.onepice.travel.data.WeatherDetail;
import com.project.onepice.travel.util.Utils;

/**
 * Created by onepice2015 on 2016/11/11.
 */
public class WeatherDetailsAdapter extends RecyclerView.Adapter {

    Context context;
    WeatherDetail weatherDetail;


    public void setTicketDetails(WeatherDetail weatherDetail) {
        this.weatherDetail = weatherDetail;
    }


    public WeatherDetailsAdapter(Context context) {
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView weather_img;
        TextView weather_data;
        TextView weather_cond;
        TextView weather_tmp;
        TextView weather_wind;

        public TextView getWeather_astro() {
            return weather_astro;
        }

        TextView weather_astro;

        public ImageView getWeather_img() {
            return weather_img;
        }

        public TextView getWeather_data() {
            return weather_data;
        }

        public TextView getWeather_cond() {
            return weather_cond;
        }

        public TextView getWeather_tmp() {
            return weather_tmp;
        }

        public TextView getWeather_wind() {
            return weather_wind;
        }

        public ViewHolder(View view) {
            super(view);
            weather_img = (ImageView) view.findViewById(R.id.weather_img);
            weather_data = (TextView) view.findViewById(R.id.weather_data);
            weather_cond = (TextView) view.findViewById(R.id.weather_cond);
            weather_tmp = (TextView) view.findViewById(R.id.weather_tmp);
            weather_wind = (TextView) view.findViewById(R.id.weather_wind);
            weather_astro = (TextView) view.findViewById(R.id.weather_astro);

        }


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item_card, parent, false);
        WeatherDetailsAdapter.ViewHolder viewHolder = new WeatherDetailsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final WeatherDetailsAdapter.ViewHolder viewHolder = (WeatherDetailsAdapter.ViewHolder) holder;
        if (position == 0) {
            viewHolder.getWeather_data().setText(weatherDetail.reaData.get(0).daily_forecast.get(position).date + "(今天)");
        } else {
            viewHolder.getWeather_data().setText(weatherDetail.reaData.get(0).daily_forecast.get(position).date);
        }

        viewHolder.getWeather_astro().setText("日出时间:  " + weatherDetail.reaData.get(0).daily_forecast.get(position).astro.sr +
                "\n日落时间:  " + weatherDetail.reaData.get(0).daily_forecast.get(position).astro.ss);
        viewHolder.getWeather_cond().setText("白天:  " + weatherDetail.reaData.get(0).daily_forecast.get(position).cond.txt_d +
                "\n夜间:  " + weatherDetail.reaData.get(0).daily_forecast.get(position).cond.txt_n);
        viewHolder.getWeather_tmp().setText("最高温度:  " + weatherDetail.reaData.get(0).daily_forecast.get(position).tmp.max +
                "\n最低温度:  " + weatherDetail.reaData.get(0).daily_forecast.get(position).tmp.min);
        viewHolder.getWeather_wind().setText("风向:  " + weatherDetail.reaData.get(0).daily_forecast.get(position).wind.dir +
                "\n风力:  " + weatherDetail.reaData.get(0).daily_forecast.get(position).wind.sc + "级");

        Glide.with(context)
                .load(Utils.weatherStatus(weatherDetail.reaData.get(0).daily_forecast.get(position).cond))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(120, 120)
                .into(viewHolder.getWeather_img());

    }


    @Override
    public int getItemCount() {
        if (weatherDetail != null && weatherDetail.reaData != null&&weatherDetail.reaData.get(0).daily_forecast!=null) {
            return weatherDetail.reaData.get(0).daily_forecast.size();
        }
        return 0;
    }
}

