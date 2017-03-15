package com.project.onepice.travel.data.source.local;

import com.project.onepice.travel.SelectCity.SelectCityVo;
import com.project.onepice.travel.data.CityInfo;
import com.project.onepice.travel.data.Scenic;
import com.project.onepice.travel.data.source.local.cityInfo.CityInfoDataSource;
import com.project.onepice.travel.data.source.local.scenic.ScenicDataSource;

import java.util.ArrayList;

/**
 * Created by onepice2015 on 2016/11/10.
 */

public class LocalDataRepository implements LocalDataSource {

    private CityInfoDataSource cityInfoDataSource;
    private ScenicDataSource scenicDataSource;


    public LocalDataRepository(CityInfoDataSource cityInfoDataSource, ScenicDataSource scenicDataSource) {
        this.cityInfoDataSource = cityInfoDataSource;
        this.scenicDataSource = scenicDataSource;
    }

    public LocalDataRepository(CityInfoDataSource cityInfoDataSource) {
        this.cityInfoDataSource = cityInfoDataSource;
    }

    @Override
    public boolean initLocalSqlite() {
        return cityInfoDataSource.insertCityInfo(cityInfoDataSource.initSqliteInsertData())
                      && scenicDataSource.insertScenicInfo(scenicDataSource.initSqliteInsertData());
    }

    @Override
    public CityInfo queryCityCode(String cityName) {
        return cityInfoDataSource.queryCityCode(cityName);
    }

    @Override
    public ArrayList<Scenic> queryScenic(String city_id, int pageNumber) {
        return scenicDataSource.queryScenic(city_id, pageNumber);
    }

    @Override
    public ArrayList<SelectCityVo> queryAllProvice() {
        return cityInfoDataSource.queryAllProvice();
    }
}
