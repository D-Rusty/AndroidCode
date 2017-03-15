package com.project.onepice.travel.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.onepice.travel.data.source.local.cityInfo.CityInfoPersistenceContract;
import com.project.onepice.travel.data.source.local.scenic.ScenicPersistenceContract;

import static com.project.onepice.travel.data.source.local.cityInfo.CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITY_ID;

/**
 * Created by onepice2015 on 2016/11/8.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final  int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="trval_db";
    private static final String TEXT_TYPE=" TEXT(100)";
    private static final String COMMA_SEP=",";

    private static final String SQL_CREATE_CITYINFO="CREATE TABLE "+ CityInfoPersistenceContract.CityInfoEntry.TABLE_NAME+" ("+
            COLUMN_NAME_CITY_ID+TEXT_TYPE+COMMA_SEP+
            CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_DISTRICT+" TEXT(100),"+
            CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITYNAME_EN+TEXT_TYPE+COMMA_SEP+
            CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_CITYNAME_ZH+TEXT_TYPE+COMMA_SEP+
            CityInfoPersistenceContract.CityInfoEntry.COLUMN_NAME_PROVINCE+TEXT_TYPE+")";


    private static final String SQL_CREATE_SCENIC="CREATE TABLE "+ ScenicPersistenceContract.ScenicEntity.TABLE_NAME+" ("+
            ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_SCENICSN+TEXT_TYPE+COMMA_SEP+
            ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_CITY_ID+TEXT_TYPE+COMMA_SEP+
            ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_SCENINAME_ZH+TEXT_TYPE+COMMA_SEP+
            ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_PROVINCE+TEXT_TYPE+COMMA_SEP+
            ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_CITY+TEXT_TYPE+COMMA_SEP+
            ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_SCENICLOGO+TEXT_TYPE+COMMA_SEP+
            ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_SCENICTicket+TEXT_TYPE+COMMA_SEP+
            ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_SCENICLAT+TEXT_TYPE+COMMA_SEP+
            ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_SCENICLON+TEXT_TYPE+")";

    private static final String SQL_DELETE_TABLE_CITYINFO="DROP TABLE IF EXISTS "+ CityInfoPersistenceContract.CityInfoEntry.TABLE_NAME;
    private static final String SQL_DELETE_TABLE_SCENIC="DROP TABLE IF EXISTS "+ ScenicPersistenceContract.ScenicEntity.TABLE_NAME;



    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CITYINFO);
        db.execSQL(SQL_CREATE_SCENIC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL(SQL_DELETE_TABLE_CITYINFO);
         db.execSQL(SQL_DELETE_TABLE_SCENIC);
         onCreate(db);
    }



}


































