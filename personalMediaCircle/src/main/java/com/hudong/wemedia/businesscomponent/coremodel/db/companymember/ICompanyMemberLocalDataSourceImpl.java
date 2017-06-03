package com.hudong.wemedia.businesscomponent.coremodel.db.companymember;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.MediaCircleApplication;
import com.hudong.wemedia.basiccomponent.bean.CompanyMember;
import com.hudong.wemedia.basiccomponent.bean.Connections;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.tencent.qalsdk.service.QalService.context;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午8:57
 * 概况:
 */

public class ICompanyMemberLocalDataSourceImpl implements ICompanyMemberLocalDataSource {



    private String loginUserId = null;

    private DBHelper dbHelper = null;

    public ICompanyMemberLocalDataSourceImpl(Context context) {
        dbHelper = new DBHelper(context);
        if (MediaCircleApplication.loginUser != null) {
            loginUserId = MediaCircleApplication.loginUser.getId();
        }
    }

    @Override
    public void saveCompanyMemberList(List<CompanyMember> memberList, String companyid) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where " + COMPANY_ID + " = ? and " + LOGIN_USERID + " =? ",
                new String[]{companyid, loginUserId});
        for (int i = 0; i < memberList.size(); i++) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(COMPANY_ID, companyid);
            contentValues.put(LOGIN_USERID, loginUserId);
            contentValues.put(MANAGE, memberList.get(i).getManage());
            contentValues.put(DEPARTMENT, memberList.get(i).getDepartment());
            contentValues.put(DEPARTMENTID, memberList.get(i).getDepartmentid());
            contentValues.put(JOB, memberList.get(i).getJob());
            contentValues.put(HEADIMG, memberList.get(i).getHeadimg());
            contentValues.put(FULLNAME, memberList.get(i).getFullname());
            contentValues.put(SUBFULLNAME, memberList.get(i).getSubfullname());
            contentValues.put(MID, memberList.get(i).getMid());
            contentValues.put(MOBILE, memberList.get(i).getMobile());
            contentValues.put(DEPARTMENTMANAGER, memberList.get(i).getDepartmentmanager());

            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
    }

    @Override
    public List<CompanyMember> getCompanyMemberList(String companyid) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<CompanyMember> memberList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + COMPANY_ID + " = ? and " + LOGIN_USERID + " =? ", new String[]{companyid, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                memberList.add(createCompany(cursor));
            }
            cursor.close();
        }
        db.close();
        return memberList;
    }

    @Override
    public List<CompanyMember> getCompanyMemberListByDepartID(String companyid, String departid) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<CompanyMember> memberList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + COMPANY_ID + " = ? and " + DEPARTMENTID + " =? and " + LOGIN_USERID + " =? ", new String[]{companyid, departid, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                memberList.add(createCompany(cursor));
            }
            cursor.close();
        }
        db.close();
        return memberList;
    }

    @Override
    public List<CompanyMember> getCompanyMemberListExceptDepart(String companyid, String departid) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<CompanyMember> memberList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + COMPANY_ID + " = ? and " + DEPARTMENTID + " !=? and " + LOGIN_USERID + " =? ", new String[]{companyid, departid, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                memberList.add(createCompany(cursor));
            }
            cursor.close();
        }
        db.close();
        return memberList;
    }

    @Override
    public List<Connections> getConnectionsNotjoinedCompany(List<Connections> mList, String companyid) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Connections> list = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + COMPANY_ID + " = ? and " + LOGIN_USERID + " =? and " + MID + " =? ", new String[]{companyid, loginUserId, mList.get(i).getId()});
            if (cursor == null || cursor.getCount() <= 0) {
                list.add(mList.get(i));
            } else {
                cursor.close();
            }

        }
        return list;
    }

    @Override
    public void deleteCompanyMember(String mid, String companyId) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.rawQuery("delete from " + TABLE_NAME + " where " + MID + " = ? and " + COMPANY_ID + " = ? and "
                + LOGIN_USERID + " = ?", new String[]{mid, companyId, loginUserId});
        db.close();
    }

    @Override
    public CompanyMember getMemberById(String id, String companyId) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        CompanyMember member = new CompanyMember();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + MID + "= ? and " +
                COMPANY_ID + " = ? and " + LOGIN_USERID + " = ?", new String[]{id, companyId, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                member = createCompany(cursor);
            }
            cursor.close();
        }

        return member;
    }

    @Override
    public CompanyMember getMemberById(String id) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        CompanyMember member = new CompanyMember();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + MID + "= ? and " + LOGIN_USERID + " = ?", new String[]{id, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                member = createCompany(cursor);
            }
            cursor.close();
        }

        return member;
    }

    @Override
    public void updateCompanyMember(String id, String companyId, CompanyMember member) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("mobile", member.getMobile());
        values.put("manage", member.getManage());
        values.put("departmentid", member.getDepartmentid());
        values.put("department", member.getDepartment());
        values.put("departmentmanager", member.getDepartmentmanager());
        values.put("fullname", member.getFullname());
        values.put("subfullname", member.getSubfullname());

        db.update(TABLE_NAME, values, MID + " = ? and " + COMPANY_ID + " = ?",
                new String[]{id, companyId});
        db.close();

    }


    private CompanyMember createCompany(Cursor cursor) {
        CompanyMember member = new CompanyMember();
        member.setMid(cursor.getString(cursor.getColumnIndex(MID)));
        member.setDepartment(cursor.getString(cursor.getColumnIndex(DEPARTMENT)));
        member.setDepartmentid(cursor.getString(cursor.getColumnIndex(DEPARTMENTID)));
        member.setFullname(cursor.getString(cursor.getColumnIndex(FULLNAME)));
        member.setJob(cursor.getString(cursor.getColumnIndex(JOB)));
        member.setHeadimg(cursor.getString(cursor.getColumnIndex(HEADIMG)));
        member.setManage(cursor.getString(cursor.getColumnIndex(MANAGE)));
        member.setSubfullname(cursor.getString(cursor.getColumnIndex(SUBFULLNAME)));
        member.setMobile(cursor.getString(cursor.getColumnIndex(MOBILE)));
        member.setDepartmentmanager(cursor.getString(cursor.getColumnIndex(DEPARTMENTMANAGER)));
        return member;
    }

}

