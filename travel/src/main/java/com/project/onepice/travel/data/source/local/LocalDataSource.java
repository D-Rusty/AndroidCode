package com.project.onepice.travel.data.source.local;

import com.project.onepice.travel.SelectCity.SelectCityVo;
import com.project.onepice.travel.data.CityInfo;
import com.project.onepice.travel.data.Scenic;

import java.util.ArrayList;

/**
 * Created by onepice2015 on 2016/11/10.
 */

public interface LocalDataSource {
     boolean initLocalSqlite();
     CityInfo queryCityCode(String cityName);
     ArrayList<Scenic> queryScenic(String city_id,int pageNumber);
     ArrayList<SelectCityVo> queryAllProvice();
}
