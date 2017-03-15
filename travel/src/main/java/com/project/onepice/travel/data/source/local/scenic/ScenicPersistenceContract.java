package com.project.onepice.travel.data.source.local.scenic;

import android.provider.BaseColumns;

/**
 * Created by onepice2015 on 2016/11/9.
 */

public class ScenicPersistenceContract {

    public ScenicPersistenceContract(){}

    public static abstract class ScenicEntity implements BaseColumns{
        public static final String TABLE_NAME="scenic";
        public static final String COLUMN_NAME_SCENICSN="scenicsn";
        public static final String COLUMN_NAME_CITY_ID="city_id";
        public static final String COLUMN_NAME_SCENINAME_ZH="scenicName_zh";
        public static final String COLUMN_NAME_PROVINCE="province";
        public static final String COLUMN_NAME_CITY="city";
        public static final String COLUMN_NAME_SCENICLOGO="sceniclogo";
        public static final String COLUMN_NAME_SCENICTicket="scenicTicket";
        public static final String COLUMN_NAME_SCENICLAT="scenicLat";
        public static final String COLUMN_NAME_SCENICLON="scenicLon";
    }
}
