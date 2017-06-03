package com.hudong.wemedia.businesscomponent.coremodel.db.outermember;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.MediaCircleApplication;
import com.hudong.wemedia.basiccomponent.bean.OuterMember;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.tencent.qalsdk.service.QalService.context;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午9:42
 * 概况:
 */

public class IOuterMemberLocalDataSourceImpl implements IOuterMemberLocalDataSource {

    private DBHelper dbHelper = null;
    private String loginUserId = null;

    public IOuterMemberLocalDataSourceImpl(Context context) {
        dbHelper = new DBHelper(context);
        if (MediaCircleApplication.loginUser != null) {
            loginUserId = MediaCircleApplication.loginUser.getId();
        }
    }

    public void saveOuterMemberList(List<OuterMember> memberList, String companyid) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where " + COMPANY_ID + " = ? ", new String[]{companyid});
        for (int i = 0; i < memberList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, memberList.get(i).getId());
            contentValues.put(NAME, memberList.get(i).getName());
            contentValues.put(MOBILE, memberList.get(i).getMobile());
            contentValues.put(COMPANY, memberList.get(i).getCompany());
            contentValues.put(JOB, memberList.get(i).getJob());
            contentValues.put(PROVINCE, memberList.get(i).getProvince());
            contentValues.put(CITY, memberList.get(i).getCity());
            contentValues.put(AREA, memberList.get(i).getArea());
            contentValues.put(TOWN, memberList.get(i).getTown());
            contentValues.put(LABEL, memberList.get(i).getLabel());
            contentValues.put(IS_MEBER, memberList.get(i).getIs_meber());
            contentValues.put(RELEASENAME, memberList.get(i).getReleasename());
            contentValues.put(REMARKS, memberList.get(i).getRemarks());
            contentValues.put(COMPANY_ID, memberList.get(i).getCompanyid());
            contentValues.put(ADDRESS, memberList.get(i).getAddress());
            contentValues.put(MID, memberList.get(i).getMid());
            contentValues.put(FRIENDID, memberList.get(i).getFriendid());

            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
    }

    @Override
    public List<OuterMember> getOutMemberList(String companyid) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<OuterMember> outerMemberList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + COMPANY_ID + " = ? ", new String[]{companyid});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                outerMemberList.add(createOuterMember(cursor));
            }
            cursor.close();
        }
        db.close();
        return outerMemberList;
    }

    @Override
    public List<OuterMember> getOuterMemberById(String id, String companyId) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<OuterMember> oList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + MID + "= ? and " + COMPANY_ID + " = ?", new String[]{id, companyId, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                oList.add(createOuterMember(cursor));
            }
            cursor.close();
        }

        return oList;
    }

    @Override
    public OuterMember getOuterMemberById(String id) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        OuterMember member = new OuterMember();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + MID + "= ? ", new String[]{id});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                member = createOuterMember(cursor);
            }
            cursor.close();
        }

        return member;
    }

    @Override
    public void deletetable() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        db.close();
    }

    @Override
    public void deleteOutMember(String id, String companyId) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.rawQuery("delete from " + TABLE_NAME + " where " + ID + " = ? and " + COMPANY_ID + " = ? ", new String[]{id, companyId});
    }

    private OuterMember createOuterMember(Cursor cursor) {
        OuterMember member = new OuterMember();
        member.setMid(cursor.getString(cursor.getColumnIndex(MID)));
        member.setName(cursor.getString(cursor.getColumnIndex(NAME)));
        member.setMobile(cursor.getString(cursor.getColumnIndex(MOBILE)));
        member.setCompany(cursor.getString(cursor.getColumnIndex(COMPANY)));
        member.setJob(cursor.getString(cursor.getColumnIndex(JOB)));
        member.setProvince(cursor.getString(cursor.getColumnIndex(PROVINCE)));
        member.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
        member.setArea(cursor.getString(cursor.getColumnIndex(AREA)));
        member.setTown(cursor.getString(cursor.getColumnIndex(TOWN)));
        member.setLabel(cursor.getString(cursor.getColumnIndex(LABEL)));
        member.setIs_meber(cursor.getString(cursor.getColumnIndex(IS_MEBER)));
        member.setReleasename(cursor.getString(cursor.getColumnIndex(RELEASENAME)));
        member.setRemarks(cursor.getString(cursor.getColumnIndex(REMARKS)));
        member.setCompanyid(cursor.getString(cursor.getColumnIndex(COMPANY_ID)));
        member.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
        member.setMid(cursor.getString(cursor.getColumnIndex(MID)));
        member.setFriendid(cursor.getString(cursor.getColumnIndex(FRIENDID)));
        return member;
    }
}
