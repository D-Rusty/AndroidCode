package com.project.onepice.travel.data.source.local.cityInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.project.onepice.travel.R;
import com.project.onepice.travel.SelectCity.SelectCityVo;
import com.project.onepice.travel.data.CityInfo;
import com.project.onepice.travel.data.source.local.DBHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by onepice2015 on 2016/11/9.
 */

public class CityInfoLocalDataSource implements CityInfoDataSource {

    private static CityInfoLocalDataSource INSTANCE = null;

    private DBHelper mDbHelper;
    private Context context;

    public CityInfoLocalDataSource(@NonNull Context context) {
        mDbHelper = new DBHelper(context);
        this.context = context;
    }

    public static CityInfoLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CityInfoLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public ArrayList<SelectCityVo> queryAllProvice() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ArrayList<SelectCityVo> result = new ArrayList<SelectCityVo>();
        HashMap<String, ArrayList<CityInfo>> queryResult = new HashMap<>();
        Cursor cursor = db.query(CityInfoPersistenceContract.CityInfoEntry.TABLE_NAME,
                null, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if (queryResult.containsKey(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_PROVINCE)))) {
                    CityInfo cityInfo = new CityInfo();
                    cityInfo.setProvince(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_PROVINCE)));
                    cityInfo.setCity_id(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITY_ID)));
                    cityInfo.setCityName_en(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITYNAME_EN)));
                    cityInfo.setCityName_zh(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITYNAME_ZH)));
                    cityInfo.setDistrict(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_DISTRICT)));
                    queryResult.get(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_PROVINCE))).add(cityInfo);
                } else {
                    ArrayList<CityInfo> cityInfos = new ArrayList<>();
                    CityInfo cityInfo = new CityInfo();
                    cityInfo.setProvince(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_PROVINCE)));
                    cityInfo.setCity_id(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITY_ID)));
                    cityInfo.setCityName_en(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITYNAME_EN)));
                    cityInfo.setCityName_zh(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITYNAME_ZH)));
                    cityInfo.setDistrict(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_DISTRICT)));

                    cityInfos.add(cityInfo);
                    queryResult.put(cityInfo.getProvince(), cityInfos);
                }


            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        for (Map.Entry<String, ArrayList<CityInfo>> entry : queryResult.entrySet()) {
            SelectCityVo vo = new SelectCityVo();
            vo.cityInfos = entry.getValue();
            vo.cityParent = entry.getKey();
            result.add(vo);

        }

        return result;
    }


    @Override
    public CityInfo queryCityCode(String cityName) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor;
        String[] projection = null;

        cursor = db.query(CityInfoPersistenceContract.CityInfoEntry.TABLE_NAME,
                new String[]{CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITY_ID,
                        CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_DISTRICT,
                        CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITYNAME_EN,
                        CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITYNAME_ZH,
                        CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_PROVINCE},
                CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITYNAME_ZH + "= ?", new String[]{cityName}, null, null, null, null);

        cursor.moveToFirst();


        CityInfo cityInfo = new CityInfo();
        if (cursor.getCount() > 0) {

            cityInfo.setCity_id(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITY_ID)));
            cityInfo.setCityName_en(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITYNAME_EN)));
            cityInfo.setCityName_zh(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITYNAME_ZH)));
            cityInfo.setDistrict(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_DISTRICT)));
            cityInfo.setProvince(cursor.getString(cursor.getColumnIndex(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_PROVINCE)));
        }


        cursor.close();
        db.close();
        return cityInfo;
    }


    @Override
    public boolean insertCityInfo(ArrayList<ContentValues> arrayList) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(CityInfoPersistenceContract.CityInfoEntry.TABLE_NAME, null, null);
        long resultCount = 0;
        db.beginTransaction();
        for (int i = 0; i < arrayList.size(); i++) {
            resultCount = db.insert(CityInfoPersistenceContract.CityInfoEntry.TABLE_NAME, null, arrayList.get(i));
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return resultCount > 0;

    }

    @Override
    public ArrayList<ContentValues> initSqliteInsertData() {
        ArrayList<ContentValues> arrayList = new ArrayList<>();
        InputStream is = context.getResources().openRawResource(R.raw.cityinfo);
        if (is != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            int count = 0;
            try {
                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] str = line.split(",");
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITY_ID, str[0]);
                        contentValues.put(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_DISTRICT, str[1]);
                        contentValues.put(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITYNAME_EN, str[2]);
                        contentValues.put(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITYNAME_ZH, str[3] + "市");
                        contentValues.put(CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_PROVINCE, str[4]);
                        arrayList.add(contentValues);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return arrayList;
            } finally {
                try {
                    bufferedReader.close();
                    inputStreamReader.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
