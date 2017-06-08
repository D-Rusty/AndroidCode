package com.project.onepice.travel.Scenic;


import android.content.Context;
import android.os.AsyncTask;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.project.onepice.travel.data.CityInfo;
import com.project.onepice.travel.data.Scenic;
import com.project.onepice.travel.data.source.local.LocalDataRepository;
import com.project.onepice.travel.data.source.local.LocalDataSource;
import com.project.onepice.travel.util.Utils;

import java.util.ArrayList;

/**
 * Created by onepice2015 on 2016/10/15.
 */

public class ScenicPresenter implements ScenicContract.Presenter {

    private final Context context;
    private final LocalDataSource localDataSource;
    private final ScenicContract.View scenicPager;


    public ScenicPresenter(Context context, LocalDataRepository localDataSource, ScenicContract.View scenicPager) {
        this.context = context;
        this.localDataSource = localDataSource;
        this.scenicPager = scenicPager;
        scenicPager.setPresenter(this);
    }


    @Override
    public void getCityId(String cityName) {
        new ScenicCityTask().execute(cityName);
    }

    @Override
    public void getScenicList(String city_id, String pageNumber) {
        new ScenicInfoTask().execute(city_id, pageNumber);
    }

    class ScenicCityTask extends AsyncTask<String, Void, CityInfo> {

        @Override
        protected CityInfo doInBackground(String... params) {
            String localCityName;
            if (params[0].equals("")) {
                AMapLocation location = Utils.getMapCityName(context);
                if (location != null) {
                    localCityName = location.getErrorCode() == 0 ? location.getCity() : location.getErrorInfo();
                } else {
                    localCityName = "深圳";
                }

            } else {
                localCityName = params[0];
            }

            CityInfo cityInfo = localDataSource.queryCityCode(localCityName);
            return cityInfo;
        }

        @Override
        protected void onPostExecute(CityInfo cityInfo) {
            super.onPostExecute(cityInfo);
            //支持中英文切换
            scenicPager.setLocation(cityInfo.getCityName_zh());
            getScenicList(cityInfo.getCity_id(), "0");

        }
    }

    class ScenicInfoTask extends AsyncTask<String, Void, ArrayList<Scenic>> {

        @Override
        protected ArrayList<Scenic> doInBackground(String... params) {
            return localDataSource.queryScenic(params[0], new Integer(params[1]).intValue());
        }

        @Override
        protected void onPostExecute(ArrayList<Scenic> scenics) {
            super.onPostExecute(scenics);
            scenicPager.refresh(scenics);

        }
    }

    AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {

        }
    };
}


























