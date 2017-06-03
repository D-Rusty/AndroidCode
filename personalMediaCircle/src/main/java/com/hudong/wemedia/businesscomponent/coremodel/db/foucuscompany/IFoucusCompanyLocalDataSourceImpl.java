package com.hudong.wemedia.businesscomponent.coremodel.db.foucuscompany;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.MediaCircleApplication;
import com.hudong.wemedia.basiccomponent.bean.ForcusCompanysParseBean;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.tencent.qalsdk.service.QalService.context;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午9:22
 * 概况:
 */

public class IFoucusCompanyLocalDataSourceImpl implements IFoucusCompanyLocalDataSource {
    private DBHelper dbHelper = null;
    private String loginUserId = null;

    public IFoucusCompanyLocalDataSourceImpl(Context context) {
        if (MediaCircleApplication.loginUser != null) {
            loginUserId = MediaCircleApplication.loginUser.getId();
        }
        dbHelper = new DBHelper(context);
    }

    @Override
    public void saveCompanyList(List<ForcusCompanysParseBean.ContentBean> companyList) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where " + LOGIN_USERID + " = ?",
                new String[]{loginUserId});
        for (int i = 0; i < companyList.size(); i++) {

            ContentValues contentValues = new ContentValues();

            contentValues.put(LOGIN_USERID, loginUserId);
            contentValues.put(ID, companyList.get(i).getId());
            contentValues.put(NAME, companyList.get(i).getName());
            contentValues.put(LOGO, companyList.get(i).getLogourl());
            contentValues.put(INDUSTRY, companyList.get(i).getIndustry());
            contentValues.put(PROVINCE, companyList.get(i).getProvince());
            contentValues.put(CITY, companyList.get(i).getCity());
            contentValues.put(AREA, companyList.get(i).getArea());
            contentValues.put(TOWN, companyList.get(i).getTown());
            contentValues.put(ADDRESS, companyList.get(i).getAddress());
            contentValues.put(CREAT_TIME, companyList.get(i).getCreatetime());
            contentValues.put(RZ, companyList.get(i).getRz());
            contentValues.put(ADURL, companyList.get(i).getAdurl());
            contentValues.put(SHOPURL, companyList.get(i).getShopurl());

            contentValues.put(PHONE, companyList.get(i).getPhone());
            contentValues.put(EMAIL, companyList.get(i).getEmail());
            contentValues.put(WEIXIN, companyList.get(i).getWeixin());
            contentValues.put(INTRODUCTION, companyList.get(i).getIndustry());
            contentValues.put(FRIEND, companyList.get(i).getFriend());

            contentValues.put(QQ, companyList.get(i).getQq());
            contentValues.put(STATUS, companyList.get(i).getStatus());
            contentValues.put(DETAIL_ADDRESS, companyList.get(i).getCompanydetailaddress());
            contentValues.put(INTRODUCTION, companyList.get(i).getCompanyintroduction());
            contentValues.put(COMPANY_TOWN, companyList.get(i).getCompanytown());


            db.insert(TABLE_NAME, null, contentValues);
        }


        db.close();
    }

    @Override
    public void removeCompanyFromList(List<ForcusCompanysParseBean.ContentBean> companyList) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (int i = 0; i < companyList.size(); i++) {
            db.execSQL("delete from " + TABLE_NAME + " where " + ID + " = ? and " + LOGIN_USERID + " =?",
                    new String[]{companyList.get(i).getId(), loginUserId});
        }
        db.close();
    }

    @Override
    public List<ForcusCompanysParseBean.ContentBean> getCompanyList() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<ForcusCompanysParseBean.ContentBean> companyList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + LOGIN_USERID + " =? ", new String[]{loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                companyList.add(createFocusCompany(cursor));
            }
            cursor.close();
        }
        db.close();
        return companyList;
    }

    @Override
    public ForcusCompanysParseBean.ContentBean getCompanyById(String id) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + ID + " =? ", new String[]{id});
        ForcusCompanysParseBean.ContentBean FocusCompany = null;
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                FocusCompany = createFocusCompany(cursor);
            }
            cursor.close();
        }
        db.close();
        return FocusCompany;
    }

    @Override
    public int totalCloumSize() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        int sum = cursor.getInt(0);
        cursor.close();
        return sum;
    }


    private ForcusCompanysParseBean.ContentBean createFocusCompany(Cursor cursor) {
        ForcusCompanysParseBean.ContentBean focusCompany = new ForcusCompanysParseBean.ContentBean();
        focusCompany.setId(cursor.getString(cursor.getColumnIndex(ID)));
        focusCompany.setName(cursor.getString(cursor.getColumnIndex(NAME)));
        focusCompany.setLogourl(cursor.getString(cursor.getColumnIndex(LOGO)));
        focusCompany.setIndustry(cursor.getString(cursor.getColumnIndex(INDUSTRY)));
        focusCompany.setProvince(cursor.getString(cursor.getColumnIndex(PROVINCE)));
        focusCompany.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
        focusCompany.setArea(cursor.getString(cursor.getColumnIndex(AREA)));
        focusCompany.setTown(cursor.getString(cursor.getColumnIndex(TOWN)));
        focusCompany.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
        focusCompany.setCreatetime(cursor.getString(cursor.getColumnIndex(CREAT_TIME)));
        focusCompany.setRz(cursor.getString(cursor.getColumnIndex(RZ)));
        focusCompany.setAdurl(cursor.getString(cursor.getColumnIndex(ADURL)));
//        focusCompany.setCompanywebsite(cursor.getString(cursor.getColumnIndex(COMPANYWEBSITE)));
        focusCompany.setShopurl(cursor.getString(cursor.getColumnIndex(SHOPURL)));
        focusCompany.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
        focusCompany.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
        focusCompany.setWeixin(cursor.getString(cursor.getColumnIndex(WEIXIN)));
//        focusCompany.setIntroduction(cursor.getString(cursor.getColumnIndex(INTRODUCTION)));
        focusCompany.setFriend(cursor.getString(cursor.getColumnIndex(FRIEND)));
        focusCompany.setQq(cursor.getString(cursor.getColumnIndex(QQ)));
        focusCompany.setStatus(cursor.getString(cursor.getColumnIndex(STATUS)));
        focusCompany.setCompanydetailaddress(cursor.getString(cursor.getColumnIndex(DETAIL_ADDRESS)));
        focusCompany.setCompanyintroduction(cursor.getString(cursor.getColumnIndex(INTRODUCTION)));
        focusCompany.setCompanytown(cursor.getString(cursor.getColumnIndex(COMPANY_TOWN)));
        return focusCompany;
    }
}
