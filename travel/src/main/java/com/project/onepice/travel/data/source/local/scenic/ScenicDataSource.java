package com.project.onepice.travel.data.source.local.scenic;

import android.content.ContentValues;

import com.project.onepice.travel.data.Scenic;

import java.util.ArrayList;

/**
 * Created by onepice2015 on 2016/11/10.
 */

public interface ScenicDataSource {
    boolean insertScenicInfo( ArrayList<ContentValues> arrayList);
    ArrayList<Scenic> queryScenic(String city_id,int pageNumber);
    ArrayList<ContentValues> initSqliteInsertData();
}
