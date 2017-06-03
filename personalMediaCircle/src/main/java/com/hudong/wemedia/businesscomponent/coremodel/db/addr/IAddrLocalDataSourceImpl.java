package com.hudong.wemedia.businesscomponent.coremodel.db.addr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.basiccomponent.bean.AddrBean;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午8:34
 * 概况:
 */

public class IAddrLocalDataSourceImpl implements IAddrLocalDataSource {
    private DBHelper dbHelper = null;
    public IAddrLocalDataSourceImpl(Context context) {
        dbHelper = new DBHelper(context);
    }

    @Override
    public void saveAddrList(List<AddrBean.ContentBean> addrList) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (int i = 0; i < addrList.size(); i++) {
            String code = addrList.get(i).getCode();
            Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE + " = ? ",
                    new String[]{code});
            if (cursor == null || cursor.getCount() == 0) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(CODE, addrList.get(i).getCode());
                contentValues.put(NAME, addrList.get(i).getName());
                contentValues.put(PARENTID, addrList.get(i).getParent_id());
                contentValues.put(LEVEL, addrList.get(i).getLevel());
                db.insert(TABLE_NAME, null, contentValues);
            } else {
                cursor.close();
            }
        }
        db.close();
    }

    @Override
    public List<AddrBean.ContentBean> getAllAddr() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<AddrBean.ContentBean> addrList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + PARENTID + "= ?", new String[]{"0"});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                AddrBean.ContentBean addr = new AddrBean.ContentBean();
                addr.setCode(cursor.getString(cursor.getColumnIndex(CODE)));
                addr.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                addr.setParent_id(cursor.getString(cursor.getColumnIndex(PARENTID)));
                addr.setLevel(cursor.getString(cursor.getColumnIndex(LEVEL)));
                addrList.add(addr);
            }
            cursor.close();
        }
        db.close();
        return addrList;
    }

    @Override
    public List<AddrBean.ContentBean> getNextLevelAddr(String parentId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<AddrBean.ContentBean> addrList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + PARENTID + " = ?",
                new String[]{parentId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                AddrBean.ContentBean addr = new AddrBean.ContentBean();
                addr.setCode(cursor.getString(cursor.getColumnIndex(CODE)));
                addr.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                addr.setName(cursor.getString(cursor.getColumnIndex(PARENTID)));
                addr.setName(cursor.getString(cursor.getColumnIndex(LEVEL)));
                addrList.add(addr);
            }
            cursor.close();
        }
        db.close();
        return addrList;
    }
}
