package com.hudong.wemedia.businesscomponent.coremodel.db.friend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.MediaCircleApplication;
import com.hudong.wemedia.basiccomponent.bean.Connections;
import com.hudong.wemedia.basiccomponent.bean.IndustryParseBean;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.tencent.qalsdk.service.QalService.context;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午4:37
 * 概况:
 */

public class IFriendLocalDataSouceImpl implements IFriendLocalDataSouce {
    private String loginUserId = null;
    private DBHelper dbHelper = null;

    public IFriendLocalDataSouceImpl(Context context) {
        dbHelper = new DBHelper(context);
        if (MediaCircleApplication.loginUser != null) {
            loginUserId = MediaCircleApplication.loginUser.getId();
        }
    }

    @Override
    public long saveFriendList(List<Connections> list) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        long result = 0;
        for (int i = 0; i < list.size(); i++) {
            String id = list.get(i).getId();
            Cursor cursor = db.rawQuery(
                    "select * from " + TABLE_NAME + " where " + ID + " = ? and " + LOGIN_USERID + "= ? ",
                    new String[]{id, loginUserId});
            if (cursor.getCount() == 0) {
                String isFriend = "1";
                String isApplied = list.get(i).getIsApplied();
                String isRefuse = list.get(i).getIsRefused();
                String isAccept = list.get(i).getIsAccept();

                if (isApplied == null || isApplied.equals("")) {
                    isApplied = "1";
                }
                if (isRefuse == null || isRefuse.equals("")) {
                    isRefuse = "0";
                }
                if (isAccept == null || isAccept.equals("")) {
                    isAccept = "1";
                }


                ContentValues contentValues = new ContentValues();
                contentValues.put(ID, list.get(i).getId());
                contentValues.put(REALNAME, list.get(i).getRealname());
                contentValues.put(SEX, list.get(i).getSex());
                contentValues.put(BIRTH, list.get(i).getBirth());
                contentValues.put(MOBILE, list.get(i).getMobile());
                contentValues.put(COMPANY, list.get(i).getCompany());
                contentValues.put(INDUSTRY, list.get(i).getIndustry());
                contentValues.put(DEPARTMENT, list.get(i).getDepartment());
                contentValues.put(COMPANY_ADDRESS, list.get(i).getCompanyaddress());
                contentValues.put(HEADIMG, list.get(i).getHeadimg());
                contentValues.put(HOME_ADDRESS, list.get(i).getHomeaddress());
                contentValues.put(NOW_ADDRESS, list.get(i).getNowaddress());
                contentValues.put(TOP_IDENTITY, list.get(i).getTopidentity());
                contentValues.put(BOTTOM_IDENTITY, list.get(i).getBottomidentity());
                contentValues.put(JOB, list.get(i).getJob());
                contentValues.put(NOW_PROVINCE, list.get(i).getNowprovince());
                contentValues.put(NOW_CITY, list.get(i).getNowcity());
                contentValues.put(ISFRIEND, isFriend);
                contentValues.put(ISAPPLIED, isApplied);
                contentValues.put(ISREFUSE, isRefuse);
                contentValues.put(ISACCEPT, isAccept);
                contentValues.put(LOGIN_USERID, loginUserId);
                contentValues.put(USERNAME, list.get(i).getUsername());
                result = db.insert(TABLE_NAME, null, contentValues);
            }
            cursor.close();
        }
        db.close();
        return result;
    }

    @Override
    public List<Connections> getFriendList() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Connections> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + LOGIN_USERID + "= ? ",
                new String[]{loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Connections connections = new Connections();
                connections.setId(cursor.getString(cursor.getColumnIndex(ID)));
                connections.setRealname(cursor.getString(cursor.getColumnIndex(REALNAME)));
                connections.setSex(cursor.getString(cursor.getColumnIndex(SEX)));
                connections.setBirth(cursor.getString(cursor.getColumnIndex(BIRTH)));
                connections.setMobile(cursor.getString(cursor.getColumnIndex(MOBILE)));
                connections.setCompany(cursor.getString(cursor.getColumnIndex(COMPANY)));
                connections.setIndustry(cursor.getString(cursor.getColumnIndex(INDUSTRY)));
                connections.setDepartment(cursor.getString(cursor.getColumnIndex(DEPARTMENT)));
                connections.setCompanyaddress(cursor.getString(cursor.getColumnIndex(COMPANY_ADDRESS)));
                connections.setHeadimg(cursor.getString(cursor.getColumnIndex(HEADIMG)));
                connections.setHomeaddress(cursor.getString(cursor.getColumnIndex(HOME_ADDRESS)));
                connections.setNowaddress(cursor.getString(cursor.getColumnIndex(NOW_ADDRESS)));
                connections.setTopidentity(cursor.getString(cursor.getColumnIndex(TOP_IDENTITY)));
                connections.setBottomidentity(cursor.getString(cursor.getColumnIndex(BOTTOM_IDENTITY)));
                connections.setJob(cursor.getString(cursor.getColumnIndex(JOB)));
                connections.setNowprovince(cursor.getString(cursor.getColumnIndex(NOW_PROVINCE)));
                connections.setNowcity(cursor.getString(cursor.getColumnIndex(NOW_CITY)));
                connections.setIsFriend(cursor.getString(cursor.getColumnIndex(ISFRIEND)));
                connections.setIsApplied(cursor.getString(cursor.getColumnIndex(ISAPPLIED)));
                connections.setIsRefused(cursor.getString(cursor.getColumnIndex(ISREFUSE)));
                connections.setIsAccept(cursor.getString(cursor.getColumnIndex(ISACCEPT)));
                connections.setLoginUserName(cursor.getString(cursor.getColumnIndex(LOGIN_USERID)));
                connections.setUsername(cursor.getString(cursor.getColumnIndex(USERNAME)));
                list.add(connections);
            }
            cursor.close();

        }
        db.close();
        return list;
    }

    @Override
    public List<Connections> getFriendList(String industry) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Connections> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where industry = ? and " + LOGIN_USERID + "= ? ",
                new String[]{industry, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Connections connections = new Connections();
                connections.setId(cursor.getString(cursor.getColumnIndex(ID)));
                connections.setRealname(cursor.getString(cursor.getColumnIndex(REALNAME)));
                connections.setSex(cursor.getString(cursor.getColumnIndex(SEX)));
                connections.setBirth(cursor.getString(cursor.getColumnIndex(BIRTH)));
                connections.setMobile(cursor.getString(cursor.getColumnIndex(MOBILE)));
                connections.setCompany(cursor.getString(cursor.getColumnIndex(COMPANY)));
                connections.setIndustry(cursor.getString(cursor.getColumnIndex(INDUSTRY)));
                connections.setDepartment(cursor.getString(cursor.getColumnIndex(DEPARTMENT)));
                connections.setCompanyaddress(cursor.getString(cursor.getColumnIndex(COMPANY_ADDRESS)));
                connections.setHeadimg(cursor.getString(cursor.getColumnIndex(HEADIMG)));
                connections.setHomeaddress(cursor.getString(cursor.getColumnIndex(HOME_ADDRESS)));
                connections.setNowaddress(cursor.getString(cursor.getColumnIndex(NOW_ADDRESS)));
                connections.setTopidentity(cursor.getString(cursor.getColumnIndex(TOP_IDENTITY)));
                connections.setBottomidentity(cursor.getString(cursor.getColumnIndex(BOTTOM_IDENTITY)));
                connections.setJob(cursor.getString(cursor.getColumnIndex(JOB)));
                connections.setNowprovince(cursor.getString(cursor.getColumnIndex(NOW_PROVINCE)));
                connections.setNowcity(cursor.getString(cursor.getColumnIndex(NOW_CITY)));
                connections.setIsFriend(cursor.getString(cursor.getColumnIndex(ISFRIEND)));
                connections.setIsApplied(cursor.getString(cursor.getColumnIndex(ISAPPLIED)));
                connections.setIsRefused(cursor.getString(cursor.getColumnIndex(ISREFUSE)));
                connections.setIsAccept(cursor.getString(cursor.getColumnIndex(ISACCEPT)));
                connections.setLoginUserName(cursor.getString(cursor.getColumnIndex(LOGIN_USERID)));
                connections.setUsername(cursor.getString(cursor.getColumnIndex(USERNAME)));
                list.add(connections);
            }
            cursor.close();
        }
        db.close();
        return list;
    }

    @Override
    public Connections getFriendByUsername(String username) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Connections connections = new Connections();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where username = ? and " + LOGIN_USERID + "= ? ",
                new String[]{username, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                connections.setId(cursor.getString(cursor.getColumnIndex(ID)));
                connections.setRealname(cursor.getString(cursor.getColumnIndex(REALNAME)));
                connections.setSex(cursor.getString(cursor.getColumnIndex(SEX)));
                connections.setBirth(cursor.getString(cursor.getColumnIndex(BIRTH)));
                connections.setMobile(cursor.getString(cursor.getColumnIndex(MOBILE)));
                connections.setCompany(cursor.getString(cursor.getColumnIndex(COMPANY)));
                connections.setIndustry(cursor.getString(cursor.getColumnIndex(INDUSTRY)));
                connections.setDepartment(cursor.getString(cursor.getColumnIndex(DEPARTMENT)));
                connections.setCompanyaddress(cursor.getString(cursor.getColumnIndex(COMPANY_ADDRESS)));
                connections.setHeadimg(cursor.getString(cursor.getColumnIndex(HEADIMG)));
                connections.setHomeaddress(cursor.getString(cursor.getColumnIndex(HOME_ADDRESS)));
                connections.setNowaddress(cursor.getString(cursor.getColumnIndex(NOW_ADDRESS)));
                connections.setTopidentity(cursor.getString(cursor.getColumnIndex(TOP_IDENTITY)));
                connections.setBottomidentity(cursor.getString(cursor.getColumnIndex(BOTTOM_IDENTITY)));
                connections.setJob(cursor.getString(cursor.getColumnIndex(JOB)));
                connections.setNowprovince(cursor.getString(cursor.getColumnIndex(NOW_PROVINCE)));
                connections.setNowcity(cursor.getString(cursor.getColumnIndex(NOW_CITY)));
                connections.setIsFriend(cursor.getString(cursor.getColumnIndex(ISFRIEND)));
                connections.setIsApplied(cursor.getString(cursor.getColumnIndex(ISAPPLIED)));
                connections.setIsRefused(cursor.getString(cursor.getColumnIndex(ISREFUSE)));
                connections.setIsAccept(cursor.getString(cursor.getColumnIndex(ISACCEPT)));
                connections.setLoginUserName(cursor.getString(cursor.getColumnIndex(LOGIN_USERID)));
                connections.setUsername(cursor.getString(cursor.getColumnIndex(USERNAME)));
            }
            cursor.close();

        }
        db.close();
        return connections;
    }

    @Override
    public List<IndustryParseBean.ContentBean> getFriendIndustryList(List<Connections> friendList) {
        List<IndustryParseBean.ContentBean> industryList = new ArrayList<>();
        List<String> inList = new ArrayList<>();
        for (int i = 0; i < friendList.size(); i++) {
            inList.add(friendList.get(i).getIndustry());
        }
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<IndustryParseBean.ContentBean> childInList = new ArrayList<>();
        List<String> oldChildTitle = new ArrayList<>();
        for (int i = 0; i < inList.size(); i++) {
            String newChildTitle = inList.get(i);
            if (!oldChildTitle.contains(newChildTitle)) {
                Cursor cursor = db.rawQuery("select * from industry where title = ?", new String[]{newChildTitle});
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToNext();
                    IndustryParseBean.ContentBean childIn = new IndustryParseBean.ContentBean();
                    childIn.setId(cursor.getString(cursor.getColumnIndex("id")));
                    childIn.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                    childIn.setParentid(cursor.getString(cursor.getColumnIndex("parentid")));
                    childInList.add(childIn);
                    cursor.close();
                }
                oldChildTitle.add(newChildTitle);

            }

        }
        List<IndustryParseBean.ContentBean> parentInList = new ArrayList<>();
        List<String> oldParentId = new ArrayList<>();
        for (int i = 0; i < childInList.size(); i++) {
            String newParentId = childInList.get(i).getParentid();
            if (!oldParentId.contains(newParentId)) {
                Cursor cursor = db.rawQuery("select * from industry where id = ?",
                        new String[]{childInList.get(i).getParentid()});
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToNext();
                    IndustryParseBean.ContentBean parentIn = new IndustryParseBean.ContentBean();
                    parentIn.setId(cursor.getString(cursor.getColumnIndex("id")));
                    parentIn.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                    parentIn.setParentid(cursor.getString(cursor.getColumnIndex("parentid")));
                    parentInList.add(parentIn);
                    cursor.close();
                }
            }
            oldParentId.add(newParentId);
        }
        industryList.addAll(parentInList);
        industryList.addAll(childInList);
        return industryList;
    }


    public Connections getFriendById(String id) {
        Connections conn = new Connections();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where id = ? and " + LOGIN_USERID + " = ?",
                new String[]{id, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                conn.setId(cursor.getString(cursor.getColumnIndex(ID)));
                conn.setRealname(cursor.getString(cursor.getColumnIndex(REALNAME)));
                conn.setSex(cursor.getString(cursor.getColumnIndex(SEX)));
                conn.setBirth(cursor.getString(cursor.getColumnIndex(BIRTH)));
                conn.setMobile(cursor.getString(cursor.getColumnIndex(MOBILE)));
                conn.setCompany(cursor.getString(cursor.getColumnIndex(COMPANY)));
                conn.setIndustry(cursor.getString(cursor.getColumnIndex(INDUSTRY)));
                conn.setDepartment(cursor.getString(cursor.getColumnIndex(DEPARTMENT)));
                conn.setCompanyaddress(cursor.getString(cursor.getColumnIndex(COMPANY_ADDRESS)));
                conn.setHeadimg(cursor.getString(cursor.getColumnIndex(HEADIMG)));
                conn.setHomeaddress(cursor.getString(cursor.getColumnIndex(HOME_ADDRESS)));
                conn.setNowaddress(cursor.getString(cursor.getColumnIndex(NOW_ADDRESS)));
                conn.setTopidentity(cursor.getString(cursor.getColumnIndex(TOP_IDENTITY)));
                conn.setBottomidentity(cursor.getString(cursor.getColumnIndex(BOTTOM_IDENTITY)));
                conn.setJob(cursor.getString(cursor.getColumnIndex(JOB)));
                conn.setNowprovince(cursor.getString(cursor.getColumnIndex(NOW_PROVINCE)));
                conn.setNowcity(cursor.getString(cursor.getColumnIndex(NOW_CITY)));
                conn.setIsFriend(cursor.getString(cursor.getColumnIndex(ISFRIEND)));
                conn.setIsApplied(cursor.getString(cursor.getColumnIndex(ISAPPLIED)));
                conn.setIsRefused(cursor.getString(cursor.getColumnIndex(ISREFUSE)));
                conn.setIsAccept(cursor.getString(cursor.getColumnIndex(ISACCEPT)));
                conn.setLoginUserName(cursor.getString(cursor.getColumnIndex(LOGIN_USERID)));
                conn.setUsername(cursor.getString(cursor.getColumnIndex(USERNAME)));
            }
            cursor.close();
        }
        db.close();
        return conn;
    }


}
