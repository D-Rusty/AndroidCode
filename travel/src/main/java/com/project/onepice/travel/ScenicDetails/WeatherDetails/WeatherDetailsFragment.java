package com.project.onepice.travel.ScenicDetails.WeatherDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.onepice.travel.R;
import com.project.onepice.travel.data.WeatherDetail;
import com.project.onepice.travel.data.source.remote.RemoteDataRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by onepice2015 on 2016/11/12.
 */

public class WeatherDetailsFragment extends Fragment implements WeatherDetailsContact.View{

    @BindView(R.id.weather_recycler_view)
    RecyclerView m_recycle;

    private WeatherDetailsContact.Presenter presenter;
    private WeatherDetailsAdapter  adapter;
    private static String cityid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_weather_detail, container, false);
        ButterKnife.bind(this,mRootView);
        //初始化控件
        adapter=new WeatherDetailsAdapter(getContext());
        m_recycle.setAdapter(adapter);
        return mRootView;
    }


    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        presenter= new WeatherDetailsPresenter(getContext(),new RemoteDataRepository(),this);
        super.onActivityCreated(savedInstanceState);
    }

    public static Fragment newInstance(String city_id) {
        cityid=city_id;
        return new WeatherDetailsFragment();
    }

    @Override
    public void setRefresh(WeatherDetail weatherDetail) {
         if (adapter==null){
             adapter=new WeatherDetailsAdapter(getContext());
         }
        adapter.setTicketDetails(weatherDetail);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void setPresenter(WeatherDetailsContact.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void onResume() {
        presenter.getRemoteWeather(cityid);
        super.onResume();

    }



}
