package com.hudong.wemedia.businesscomponent.coremodel.db.circle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.MediaCircleApplication;
import com.hudong.wemedia.basiccomponent.bean.CircleBean;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;


/**
 * 作者: 方天文
 * 日期: 2017/4/14:下午2:18
 * 概况:
 */

public class ICircleLocalDataSourceImpl implements ICircleLocalDataSource {

    private DBHelper dbHelper = null;

    private String loginUserId = null;
    private Context context;

    public ICircleLocalDataSourceImpl(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        if (MediaCircleApplication.loginUser != null) {
            loginUserId = MediaCircleApplication.loginUser.getId();
        }
    }

    public void saveGroupList(List<CircleBean.ContentBean> list) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (int i = 0; i < list.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, list.get(i).getId());
            contentValues.put(MID, list.get(i).getMid());
            contentValues.put(WEID, list.get(i).getWeid());
            contentValues.put(QUN_TYPE, list.get(i).getType());
            contentValues.put(GROUP_NAME, list.get(i).getGroup_name());
            contentValues.put(GROUP_INFO, list.get(i).getGroup_info());
            contentValues.put(GROUP_QR, list.get(i).getGroup_qr());
            contentValues.put(CREATE_TIME, list.get(i).getCreate_time());
            contentValues.put(TOTAL, list.get(i).getTotal());
            contentValues.put(QUNIMG, list.get(i).getQunimg());
            contentValues.put(AREACODE, list.get(i).getAreacode());
            contentValues.put(INDUSTRY, list.get(i).getIndustry());
            contentValues.put(XINGQUID, list.get(i).getXingquid());
            contentValues.put(APPGROUPID, list.get(i).getAppgroupid());
            contentValues.put(INDUSTRYC, list.get(i).getIndustryc());
            contentValues.put(XINGQUIDC, list.get(i).getXingquidc());
            contentValues.put(ACTIVITY_COUNT, list.get(i).getQunActivitynum());
            contentValues.put(TYPEC, list.get(i).getTypec());
            contentValues.put(LOGIN_USER_ID, loginUserId);
            db.insert(TABLE_NAME, null, contentValues);

        }
        db.close();
    }


    public List<Object> getGroupCircleList() {
        ArrayList<Object> groupList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + LOGIN_USER_ID + "= ? ",
                new String[]{loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            HashMap<String, ArrayList<CircleBean.ContentBean>> groupHashMap = new HashMap<>();
            while (cursor.moveToNext()) {
                CircleBean.ContentBean group = createGroup(cursor);
                ArrayList<CircleBean.ContentBean> groupCacheList = null;
                if (groupHashMap.containsKey(cursor.getString(cursor.getColumnIndex(TYPEC)))) {
                    groupCacheList = groupHashMap.get(cursor.getString(cursor.getColumnIndex(TYPEC)));
                } else {
                    groupCacheList = new ArrayList<>();
                }
                groupCacheList.add(group);
                groupHashMap.put(cursor.getString(cursor.getColumnIndex(TYPEC)), groupCacheList);
            }

            //实现圈子分组
            for (Map.Entry<String, ArrayList<CircleBean.ContentBean>> entry : groupHashMap.entrySet()) {
                groupList.add(entry.getKey());
                for (CircleBean.ContentBean group1 : entry.getValue()) {
                    groupList.add(group1);
                }
            }
            cursor.close();
        }
        db.close();
        return groupList;
    }

    public Observable<CircleBean.ContentBean> getGroupListById(String id) {
        CircleBean.ContentBean group = null;
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where id = ? and " + LOGIN_USER_ID + " = ?",
                new String[]{id, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                group = createGroup(cursor);
            }
            cursor.close();
        }
        db.close();
        return null;
    }


    @Override
    public Observable<List<CircleBean.ContentBean>> getGrouplist() {
        return Observable.create(new Observable.OnSubscribe<List<CircleBean.ContentBean>>() {
            @Override
            public void call(Subscriber<? super List<CircleBean.ContentBean>> subscriber) {
                List<CircleBean.ContentBean> groupList = new ArrayList<>();
                SQLiteDatabase db = dbHelper.getReadableDataBase();
                Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + LOGIN_USER_ID + "= ? ",
                        new String[]{loginUserId});
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        groupList.add(createGroup(cursor));
                    }
                    cursor.close();
                }
                db.close();
                subscriber.onNext(groupList);
                subscriber.onCompleted();

            }
        }).subscribeOn(Schedulers.io());
    }


    public CircleBean.ContentBean getGroupListByHxGid(String hxGid) {
        CircleBean.ContentBean group = null;
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where appgroupid = ? and " + LOGIN_USER_ID + " = ?",
                new String[]{hxGid, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                group = createGroup(cursor);
            }
            cursor.close();
        }
        db.close();

        return group;
    }

    // 通过 群名称，群id，行业，城市,创建日期查询
    public CircleBean.ContentBean findGroup(String params) {
        CircleBean.ContentBean group = null;
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where （id = ? or group_name LIKE %?% or create_time LIKE %?% or city LIKE %?% " +
                        " industry LIKE  %?% ） and " + LOGIN_USER_ID + " = ?",
                new String[]{params, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                group = createGroup(cursor);
            }
            cursor.close();
        }
        db.close();
        return group;
    }


    private CircleBean.ContentBean createGroup(Cursor cursor) {
        CircleBean.ContentBean group = new CircleBean.ContentBean();
        group.setId(cursor.getString(cursor.getColumnIndex(ID)));
        group.setMid(cursor.getString(cursor.getColumnIndex(MID)));
        group.setWeid(cursor.getString(cursor.getColumnIndex(WEID)));
        group.setType(cursor.getString(cursor.getColumnIndex(QUN_TYPE)));
        group.setGroup_name(cursor.getString(cursor.getColumnIndex(GROUP_NAME)));
        group.setGroup_info(cursor.getString(cursor.getColumnIndex(GROUP_INFO)));
        group.setGroup_qr(cursor.getString(cursor.getColumnIndex(GROUP_QR)));
        group.setCreate_time(cursor.getString(cursor.getColumnIndex(CREATE_TIME)));
        group.setTotal(cursor.getString(cursor.getColumnIndex(TOTAL)));
        group.setQunimg(cursor.getString(cursor.getColumnIndex(QUNIMG)));
        group.setAreacode(cursor.getString(cursor.getColumnIndex(AREACODE)));
        group.setIndustry(cursor.getString(cursor.getColumnIndex(INDUSTRY)));
        group.setXingquid(cursor.getString(cursor.getColumnIndex(XINGQUID)));
        group.setAppgroupid(cursor.getString(cursor.getColumnIndex(APPGROUPID)));
        group.setIndustryc(cursor.getString(cursor.getColumnIndex(INDUSTRYC)));
        group.setTypec(cursor.getString(cursor.getColumnIndex(TYPEC)));
        group.setXingquidc(cursor.getString(cursor.getColumnIndex(XINGQUIDC)));
        group.setQunActivitynum(cursor.getInt(cursor.getColumnIndex(ACTIVITY_COUNT)));

        return group;
    }

    //统计分组信息
    public int totalCloumSize() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        int sum = cursor.getInt(0);
        cursor.close();
        return sum;
    }

    @Override
    public List<CircleBean.ContentBean> getGroupList() {
        return null;
    }

}
