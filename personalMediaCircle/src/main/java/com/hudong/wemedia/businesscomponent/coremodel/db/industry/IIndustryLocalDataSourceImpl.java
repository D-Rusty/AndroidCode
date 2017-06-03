package com.hudong.wemedia.businesscomponent.coremodel.db.industry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.basiccomponent.bean.IndustryParseBean;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午4:29
 * 概况:
 */

public class IIndustryLocalDataSourceImpl implements IIndustryLocalDataSource {
    private DBHelper dbHelper = null;

    public IIndustryLocalDataSourceImpl(Context context) {
        dbHelper = new DBHelper(context);
    }

    @Override
    public void saveIndustryList(List<IndustryParseBean.ContentBean> industryList) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        for (int i = 0; i < industryList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, industryList.get(i).getId());
            contentValues.put(TITLE, industryList.get(i).getTitle());
            contentValues.put(PARENTID, industryList.get(i).getParentid());
            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
    }

    @Override
    public List<IndustryParseBean.ContentBean> getIndustryList() {
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        List<IndustryParseBean.ContentBean> industryList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                IndustryParseBean.ContentBean industry = new IndustryParseBean.ContentBean();
                industry.setId(cursor.getString(cursor.getColumnIndex(ID)));
                industry.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                industry.setParentid(cursor.getString(cursor.getColumnIndex(PARENTID)));
                industryList.add(industry);
            }
            cursor.close();

        }
        db.close();
        return industryList;
    }

    @Override
    public List<IndustryParseBean.ContentBean> getIndustryList(String parentid) {
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        List<IndustryParseBean.ContentBean> industryList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + PARENTID + " = ?",
                new String[]{parentid});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                IndustryParseBean.ContentBean industry = new IndustryParseBean.ContentBean();
                industry.setId(cursor.getString(cursor.getColumnIndex(ID)));
                industry.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                industry.setParentid(cursor.getString(cursor.getColumnIndex(PARENTID)));
                industryList.add(industry);
            }
            cursor.close();
        }
        db.close();
        return industryList;
    }

    //根据ID查找行业
    @Override
    public IndustryParseBean.ContentBean quertIndustryById(String id) {
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        IndustryParseBean.ContentBean industry = new IndustryParseBean.ContentBean();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + ID + " = ?", new String[]{id});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                industry.setId(cursor.getString(cursor.getColumnIndex(ID)));
                industry.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                industry.setParentid(cursor.getString(cursor.getColumnIndex(PARENTID)));
            }
            cursor.close();
        }
        db.close();
        return industry;
    }

}
