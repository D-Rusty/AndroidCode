package com.project.onepice.travel.data.source.local.scenic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.project.onepice.travel.R;
import com.project.onepice.travel.data.Scenic;
import com.project.onepice.travel.data.source.local.DBHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by onepice2015 on 2016/11/9.
 */

public class ScenicLocalDataSource implements ScenicDataSource {

    private static ScenicLocalDataSource INSTANCE = null;

    private DBHelper mDbHelper;
    private Context context;

    public ScenicLocalDataSource(@NonNull Context context) {
        mDbHelper = new DBHelper(context);
        this.context = context;
    }

    public static ScenicLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ScenicLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public boolean insertScenicInfo(ArrayList<ContentValues> arrayList) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long resutCode = 0;
        db.beginTransaction();
        for (int i = 0; i < arrayList.size(); i++) {
            resutCode = db.insert(ScenicPersistenceContract.ScenicEntity.TABLE_NAME, null, arrayList.get(i));
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return resutCode > 0;
    }

    @Override
    public ArrayList<Scenic> queryScenic(String city_id, int pageNumber) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        ArrayList<Scenic> queryResult = new ArrayList<>();
        Cursor cursor = db.query(ScenicPersistenceContract.ScenicEntity.TABLE_NAME,
                null, ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_CITY_ID + "=?", new String[]{city_id}, null, null, null, 0 + "," + (pageNumber + 20));
        if (cursor.moveToFirst()) {
            do {

                Scenic scenic = new Scenic();

                scenic.setScenicSn(cursor.getString(0));
                scenic.setCity_id(cursor.getString(1));
                scenic.setScenicName_zh(cursor.getString(2));
                scenic.setProvince(cursor.getString(3));
                scenic.setCity(cursor.getString(4));
                scenic.setSceniclogo(cursor.getString(5));
                scenic.setScenicTicket(cursor.getString(6));
                scenic.setLat(cursor.getString(7));
                scenic.setLon(cursor.getString(8));

                queryResult.add(scenic);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return queryResult;
    }

    @Override
    public ArrayList<ContentValues> initSqliteInsertData() {
        ArrayList<ContentValues> arrayList = new ArrayList<>();
        InputStream is = context.getResources().openRawResource(R.raw.scenic);
        if (is != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    String[] str = line.split(",");
                    ContentValues contentValues = new ContentValues();

                    contentValues.put(ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_SCENICSN, str[0]);
                    contentValues.put(ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_SCENINAME_ZH, str[1]);
                    contentValues.put(ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_PROVINCE, str[3]);
                    contentValues.put(ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_CITY, str[2]);
                    contentValues.put(ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_SCENICLOGO, str[4]);
                    contentValues.put(ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_SCENICTicket, str[5]);
                    contentValues.put(ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_SCENICLAT, str[6]);
                    contentValues.put(ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_SCENICLON, str[7]);
                    contentValues.put(ScenicPersistenceContract.ScenicEntity.COLUMN_NAME_CITY_ID, str[8]);
                    arrayList.add(contentValues);
                }
                return arrayList;
            } catch (IOException e) {
                e.printStackTrace();
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
