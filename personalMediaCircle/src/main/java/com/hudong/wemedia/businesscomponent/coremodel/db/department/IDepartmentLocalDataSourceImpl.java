package com.hudong.wemedia.businesscomponent.coremodel.db.department;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.MediaCircleApplication;
import com.hudong.wemedia.basiccomponent.bean.Department;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.tencent.qalsdk.service.QalService.context;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午8:44
 * 概况:
 */

public class IDepartmentLocalDataSourceImpl implements IDepartmentLocalDataSource {
    private DBHelper dbHelper = null;
    private String loginUserId = null;


    public IDepartmentLocalDataSourceImpl(Context context) {
        dbHelper = new DBHelper(context);
        if (MediaCircleApplication.loginUser != null) {
            loginUserId = MediaCircleApplication.loginUser.getId();
        }
    }

    @Override
    public void saveDepartmentList(List<Department> departList, String companyid) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where " + COMPANY_ID + " = ? and " + LOGIN_USERID + " = ? ", new String[]{companyid, loginUserId});
        for (int i = 0; i < departList.size(); i++) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COMPANY_ID, companyid);
            contentValues.put(ID, departList.get(i).getId());
            contentValues.put(LOGIN_USERID, loginUserId);
            contentValues.put(DEPARTMENT, departList.get(i).getDepartment());
            contentValues.put(PARENTID, departList.get(i).getParentid());
            contentValues.put(COUNT, departList.get(i).getCount());
            contentValues.put(OPENING, departList.get(i).getOpening());
            contentValues.put(USEL, departList.get(i).getUsel());

            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
    }

    @Override
    public List<Department> getDepartmentList(String companyid) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Department> departList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + COMPANY_ID + " = ? and " + LOGIN_USERID + " = ? ", new String[]{companyid, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Department depart = new Department();
                depart.setId(cursor.getString(cursor.getColumnIndex(ID)));
                depart.setCount(cursor.getString(cursor.getColumnIndex(COUNT)));
                depart.setDepartment(cursor.getString(cursor.getColumnIndex(DEPARTMENT)));
                depart.setParentid(cursor.getString(cursor.getColumnIndex(PARENTID)));
                depart.setOpening(cursor.getString(cursor.getColumnIndex(OPENING)));
                depart.setUsel(cursor.getString(cursor.getColumnIndex(USEL)));
                departList.add(depart);
            }
            cursor.close();
        }
        db.close();
        return departList;
    }

    @Override
    public void deleteTable() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        db.close();
    }
    @Override
    public void deleteDepartment(String id, String companyId) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.rawQuery("delete from " + TABLE_NAME + " where " + ID + " = ? and " + COMPANY_ID + " = ? and "
                + LOGIN_USERID + " = ?", new String[]{id, companyId, loginUserId});
    }


}
