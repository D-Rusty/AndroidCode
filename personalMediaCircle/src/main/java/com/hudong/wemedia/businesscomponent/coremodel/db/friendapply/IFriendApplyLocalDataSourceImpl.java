package com.hudong.wemedia.businesscomponent.coremodel.db.friendapply;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.MediaCircleApplication;
import com.hudong.wemedia.basiccomponent.bean.Connections;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.tencent.qalsdk.service.QalService.context;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午9:11
 * 概况:
 */

public class IFriendApplyLocalDataSourceImpl implements IFriendApplyLocalDataSource {

    private String loginUserId = null;
    private DBHelper dbHelper = null;

    public IFriendApplyLocalDataSourceImpl(Context context) {
        dbHelper = new DBHelper(context);
        if (MediaCircleApplication.loginUser != null) {
            loginUserId = MediaCircleApplication.loginUser.getId();
        }
    }

    @Override
    public void saveFriendApplyList(List<Connections> list) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.rawQuery("delete from " + TABLE_NAME + " where " + LOGIN_USERID + " = ?",
                new String[]{loginUserId});
        for (int i = 0; i < list.size(); i++) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, list.get(i).getId());
            contentValues.put(REALNAME, list.get(i).getRealname());
            contentValues.put(SEX, list.get(i).getSex());
            contentValues.put(MOBILE, list.get(i).getMobile());
            contentValues.put(COMPANY, list.get(i).getCompany());
            contentValues.put(INDUSTRY, list.get(i).getIndustry());
            contentValues.put(DEPARTMENT, list.get(i).getDepartment());
            contentValues.put(HEADIMG, list.get(i).getHeadimg());
            contentValues.put(JOB, list.get(i).getJob());
            contentValues.put(NOW_PROVINCE, list.get(i).getNowprovince());
            contentValues.put(NOW_CITY, list.get(i).getNowcity());
            contentValues.put(NOW_AREA, list.get(i).getNowarea());
            contentValues.put(NOW_TOWN, list.get(i).getNowtown());
            contentValues.put(NOW_ADDRESS, list.get(i).getNowaddress());
            contentValues.put(LOGIN_USERID, loginUserId);
            contentValues.put(IS_PHONE_CONTACT, list.get(i).getIsPhoneContact());
            contentValues.put(USERNAME, list.get(i).getUsername());

            db.insert(TABLE_NAME, null, contentValues);
        }

    }

    @Override
    public List<Connections> getFriendApplyList() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Connections> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + LOGIN_USERID + " =?",
                new String[]{loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Connections connections = new Connections();
                connections.setId(cursor.getString(cursor.getColumnIndex(ID)));
                connections.setRealname(cursor.getString(cursor.getColumnIndex(REALNAME)));
                connections.setSex(cursor.getString(cursor.getColumnIndex(SEX)));
                connections.setMobile(cursor.getString(cursor.getColumnIndex(MOBILE)));
                connections.setCompany(cursor.getString(cursor.getColumnIndex(COMPANY)));
                connections.setIndustry(cursor.getString(cursor.getColumnIndex(INDUSTRY)));
                connections.setDepartment(cursor.getString(cursor.getColumnIndex(DEPARTMENT)));
                connections.setHeadimg(cursor.getString(cursor.getColumnIndex(HEADIMG)));
                connections.setJob(cursor.getString(cursor.getColumnIndex(JOB)));
                connections.setNowprovince(cursor.getString(cursor.getColumnIndex(NOW_PROVINCE)));
                connections.setNowcity(cursor.getString(cursor.getColumnIndex(NOW_CITY)));
                connections.setNowcity(cursor.getString(cursor.getColumnIndex(NOW_AREA)));
                connections.setNowcity(cursor.getString(cursor.getColumnIndex(NOW_TOWN)));
                connections.setNowaddress(cursor.getString(cursor.getColumnIndex(NOW_ADDRESS)));
                connections.setIsAccept(cursor.getString(cursor.getColumnIndex(IS_PHONE_CONTACT)));
                connections.setLoginUserName(cursor.getString(cursor.getColumnIndex(LOGIN_USERID)));
                connections.setLoginUserName(cursor.getString(cursor.getColumnIndex(USERNAME)));
                list.add(connections);
            }
            cursor.close();

        }
        db.close();
        return list;


    }


}
