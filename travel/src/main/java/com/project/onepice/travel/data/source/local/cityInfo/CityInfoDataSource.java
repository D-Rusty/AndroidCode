package com.project.onepice.travel.data.source.local.cityInfo;

import android.content.ContentValues;

import com.project.onepice.travel.SelectCity.SelectCityVo;
import com.project.onepice.travel.data.CityInfo;

import java.util.ArrayList;

/**
 * Created by onepice2015 on 2016/11/10.
 */

public interface CityInfoDataSource {
    ArrayList<SelectCityVo> queryAllProvice();

    //查询省城市名称对应的ID
    CityInfo queryCityCode(String cityName);

    boolean insertCityInfo(ArrayList<ContentValues> arrayList);

    ArrayList<ContentValues> initSqliteInsertData();
}
