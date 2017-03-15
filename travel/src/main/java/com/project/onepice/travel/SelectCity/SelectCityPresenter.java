package com.project.onepice.travel.SelectCity;

import android.content.Context;

import com.project.onepice.travel.data.source.local.LocalDataSource;

/**
 * Created by onepice2015 on 2016/10/15.
 */

public class SelectCityPresenter implements SelectCityContract.Presenter {
    private Context context;
    private LocalDataSource localDataSource;
    private SelectCityContract.View selectCityPage;
    private String location;

    public SelectCityPresenter(Context context, LocalDataSource localDataSource, SelectCityContract.View selectCityPage, String location) {
        this.context = context;
        this.localDataSource = localDataSource;
        this.selectCityPage = selectCityPage;
        this.location = location;
        selectCityPage.setPresenter(this);
    }

    @Override
    public void getScenicList() {
        selectCityPage.refresh(localDataSource.queryAllProvice());
    }

    @Override
    public String getLocation() {
        return location;
    }


}
