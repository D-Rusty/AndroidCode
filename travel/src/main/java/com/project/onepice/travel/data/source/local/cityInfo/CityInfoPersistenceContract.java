package com.project.onepice.travel.data.source.local.cityInfo;

import android.provider.BaseColumns;

/**
 * Created by onepice2015 on 2016/11/8.
 */

public class CityInfoPersistenceContract {

    public CityInfoPersistenceContract() {
    }

    public static abstract class CityInfoEntry implements BaseColumns {

        public static final String TABLE_NAME = "CityInfo";
        public static final String COLUMN_NAME_CITY_ID = "city_id";
        public static final String COLUMN_NAME_DISTRICT = "district";
        public static final String COLUMN_NAME_CITYNAME_EN = "cityName_en";
        public static final String COLUMN_NAME_CITYNAME_ZH = "cityName_zh";
        public static final String COLUMN_NAME_PROVINCE = "province";


    }
}






























