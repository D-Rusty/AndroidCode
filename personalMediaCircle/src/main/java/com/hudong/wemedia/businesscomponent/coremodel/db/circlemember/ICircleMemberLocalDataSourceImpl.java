package com.hudong.wemedia.businesscomponent.coremodel.db.circlemember;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.basiccomponent.bean.CircleMemberBean;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/23:下午5:29
 * 概况:
 */

public class ICircleMemberLocalDataSourceImpl implements ICircleMemberLocalDataSource {

    private DBHelper dbHelper = null;

    public ICircleMemberLocalDataSourceImpl(Context context) {
        dbHelper = new DBHelper(context);
    }

    @Override
    public void saveCircleMemeber(List<CircleMemberBean.ContentBean> list) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        for (CircleMemberBean.ContentBean circleMemberBean : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, circleMemberBean.getId());
            contentValues.put(WID, circleMemberBean.getWid());
            contentValues.put(MID, circleMemberBean.getMid());
            contentValues.put(CID, circleMemberBean.getCid());
            contentValues.put(INTROPENID, circleMemberBean.getIntropenid());
            contentValues.put(HEADIMG, circleMemberBean.getHeadimg());
            contentValues.put(CREATE_TIME, circleMemberBean.getCreate_time());
            contentValues.put(STATTUS, circleMemberBean.getStatus());
            contentValues.put(MANAGER, circleMemberBean.getManage());
            contentValues.put(GID, circleMemberBean.getGid());
            contentValues.put(REALNAME, circleMemberBean.getRealname());
            contentValues.put(PROVINCE, circleMemberBean.getProvince());
            contentValues.put(CITY, circleMemberBean.getCity());
            contentValues.put(COUNTY, circleMemberBean.getCounty());
            contentValues.put(TOWN, circleMemberBean.getTown());
            contentValues.put(PROVINCEC, circleMemberBean.getProvincec());
            contentValues.put(CITYC, circleMemberBean.getCityc());
            contentValues.put(COUNTYC, circleMemberBean.getCountyc());
            contentValues.put(TOWNC, circleMemberBean.getTownc());
            contentValues.put(ISFRIEND, circleMemberBean.getIsfriend());
            contentValues.put(APPLYGROUPINFO, circleMemberBean.getApplygroupinfo());
            contentValues.put(QUNNAME, circleMemberBean.getQunname());
            db.insert(TABLE_NAME, null, contentValues);
        }
    }

    @Override
    public CircleMemberBean.ContentBean getCircleMember(String memberId) {
        CircleMemberBean.ContentBean conn = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where mid=?", new String[]{memberId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                conn = createCircleMember(cursor);
            }
            cursor.close();
        }
        db.close();
        return conn;
    }

    private CircleMemberBean.ContentBean createCircleMember(Cursor cursor) {
        CircleMemberBean.ContentBean bean = new CircleMemberBean.ContentBean();
        bean.setId(cursor.getString(cursor.getColumnIndex(ID)));
        bean.setWid(cursor.getString(cursor.getColumnIndex(WID)));
        bean.setMid(cursor.getString(cursor.getColumnIndex(MID)));
        bean.setCid(cursor.getString(cursor.getColumnIndex(CID)));
        bean.setIntropenid(cursor.getString(cursor.getColumnIndex(INTROPENID)));
        bean.setHeadimg(cursor.getString(cursor.getColumnIndex(HEADIMG)));
        bean.setCreate_time(cursor.getString(cursor.getColumnIndex(CREATE_TIME)));
        bean.setStatus(cursor.getString(cursor.getColumnIndex(STATTUS)));
        bean.setManage(cursor.getString(cursor.getColumnIndex(MANAGER)));
        bean.setGid(cursor.getString(cursor.getColumnIndex(GID)));
        bean.setRealname(cursor.getString(cursor.getColumnIndex(REALNAME)));
        bean.setProvince(cursor.getString(cursor.getColumnIndex(PROVINCE)));
        bean.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
        bean.setCounty(cursor.getString(cursor.getColumnIndex(COUNTY)));
        bean.setTown(cursor.getString(cursor.getColumnIndex(TOWN)));
        bean.setCityc(cursor.getString(cursor.getColumnIndex(CITYC)));
        bean.setCountyc(cursor.getString(cursor.getColumnIndex(COUNTYC)));
        bean.setProvincec(cursor.getString(cursor.getColumnIndex(PROVINCEC)));
        bean.setTownc(cursor.getString(cursor.getColumnIndex(TOWNC)));
        bean.setIsfriend(cursor.getString(cursor.getColumnIndex(ISFRIEND)));
        bean.setApplygroupinfo(cursor.getString(cursor.getColumnIndex(APPLYGROUPINFO)));
        bean.setQunname(cursor.getString(cursor.getColumnIndex(QUNNAME)));
        return bean;
    }
}
