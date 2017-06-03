package com.hudong.wemedia.businesscomponent.coremodel.db.company;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.MediaCircleApplication;
import com.hudong.wemedia.basiccomponent.bean.CompanysParseBean;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.tencent.qalsdk.service.QalService.context;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午9:30
 * 概况:
 */

public class ICompanyLocalDataSourceImpl implements ICompanyLocalDataSource {

    private DBHelper dbHelper;
    private String loginUserId = null;

    public ICompanyLocalDataSourceImpl(Context context) {
        if (MediaCircleApplication.loginUser != null) {
            loginUserId = MediaCircleApplication.loginUser.getId();
        }
        dbHelper = new DBHelper(context);
    }

    @Override
    public void saveCompanyList(List<CompanysParseBean.ContentBean> companyList) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        for (int i = 0; i < companyList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(LOGIN_USERID, loginUserId);
            contentValues.put(ID, companyList.get(i).getId());
            contentValues.put(NAME, companyList.get(i).getName());
            contentValues.put(LOGO, companyList.get(i).getLogourl());
            contentValues.put(INDUSTRY, companyList.get(i).getIndustry());
            contentValues.put(INDUSTRYC, companyList.get(i).getIndustryc());
            contentValues.put(PROVINCE, companyList.get(i).getProvince());
            contentValues.put(CITY, companyList.get(i).getCity());
            contentValues.put(AREA, companyList.get(i).getArea());
            contentValues.put(TOWN, companyList.get(i).getTown());
            contentValues.put(ADDRESS, companyList.get(i).getAddress());
            contentValues.put(CREAT_TIME, companyList.get(i).getCreatetime());
            contentValues.put(RZ, companyList.get(i).getRz());
            contentValues.put(COMPANYWEBSITE, companyList.get(i).getWebsite());
            contentValues.put(ADURL, companyList.get(i).getAdurl());
            contentValues.put(SHOPURL, companyList.get(i).getShopurl());
            contentValues.put(DEFINEDSHOPURL, companyList.get(i).getDefinedshopurl());
            contentValues.put(SHOP_TYPE, companyList.get(i).getShop_type());
            contentValues.put(IS_SHOW_TEL, companyList.get(i).getIs_show_tel());
            contentValues.put(PHONE, companyList.get(i).getPhone() + "");
            contentValues.put(EMAIL, companyList.get(i).getEmail() + "");
            contentValues.put(WEIXIN, companyList.get(i).getWeixin() + "");
            contentValues.put(QQ, companyList.get(i).getQq() + "");
            contentValues.put(STATUS, companyList.get(i).getStatus());
            contentValues.put(DETAIL_ADDRESS, companyList.get(i).getCompanydetailaddress());
            contentValues.put(INTRODUCTION, companyList.get(i).getCompanyintroduction());
            contentValues.put(COMPANY_TOWN, companyList.get(i).getCompanytown());
            contentValues.put(ISSELECTED, companyList.get(i).getIsSelected());
            contentValues.put(EMPNUM, companyList.get(i).getEmpnum());
            contentValues.put(DEPARTNUM, companyList.get(i).getDepartnum());
            contentValues.put(FANSNUM, companyList.get(i).getFansnum());
            contentValues.put(INSIDEMEMBERNUM, companyList.get(i).getInsidemembernum());
            contentValues.put(OUTSIDEMEMBERNUM, companyList.get(i).getOutsidemembernum());
            contentValues.put(LASTDYNAMIC, companyList.get(i).getLastdynamic());
            contentValues.put(INCOMPANY, companyList.get(i).getIncompany());
            contentValues.put(FRIEND, companyList.get(i).getFriend());
            db.insert(TABLE_NAME, null, contentValues);

        }
        db.close();
    }

    @Override
    public void removeCompanyFromList(List<CompanysParseBean.ContentBean> companyList) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (int i = 0; i < companyList.size(); i++) {
            db.execSQL("delete from " + TABLE_NAME + " where " + ID + " = ? and " + LOGIN_USERID + " =?",
                    new String[]{companyList.get(i).getId(), loginUserId});
        }
        db.close();
    }

    @Override
    public List<CompanysParseBean.ContentBean> getCompanyList() {
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        List<CompanysParseBean.ContentBean> companyList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + LOGIN_USERID + " =? ", new String[]{loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                companyList.add(createCompany(cursor));
            }
            cursor.close();
        }
        db.close();
        return companyList;
    }

    @Override
    public CompanysParseBean.ContentBean getCompanyById(String id) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + ID + " =? ", new String[]{id});
        CompanysParseBean.ContentBean company = null;
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                company = createCompany(cursor);
            }
            cursor.close();
        }
        db.close();
        return company;
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


    private CompanysParseBean.ContentBean createCompany(Cursor cursor) {
        CompanysParseBean.ContentBean company = new CompanysParseBean.ContentBean();
        company.setId(cursor.getString(cursor.getColumnIndex(ID)));
        company.setName(cursor.getString(cursor.getColumnIndex(NAME)));
        company.setLogourl(cursor.getString(cursor.getColumnIndex(LOGO)));
        company.setIndustry(cursor.getString(cursor.getColumnIndex(INDUSTRY)));
        company.setIndustryc(cursor.getString(cursor.getColumnIndex(INDUSTRYC)));
        company.setProvince(cursor.getString(cursor.getColumnIndex(PROVINCE)));
        company.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
        company.setArea(cursor.getString(cursor.getColumnIndex(AREA)));
        company.setTown(cursor.getString(cursor.getColumnIndex(TOWN)));
        company.setCompanytown(cursor.getString(cursor.getColumnIndex(COMPANY_TOWN)));
        company.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
        company.setCreatetime(cursor.getString(cursor.getColumnIndex(CREAT_TIME)));
        company.setRz(cursor.getString(cursor.getColumnIndex(RZ)));
//        company.setCompanywebsite(cursor.getString(cursor.getColumnIndex(COMPANYWEBSITE)));
        company.setAdurl(cursor.getString(cursor.getColumnIndex(ADURL)));
        company.setShopurl(cursor.getString(cursor.getColumnIndex(SHOPURL)));
        company.setDefinedshopurl(cursor.getString(cursor.getColumnIndex(DEFINEDSHOPURL)));
        company.setShop_type(cursor.getString(cursor.getColumnIndex(SHOP_TYPE)));
        company.setIs_show_tel(cursor.getString(cursor.getColumnIndex(IS_SHOW_TEL)));
        company.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
        company.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
        company.setWeixin(cursor.getString(cursor.getColumnIndex(WEIXIN)));
        company.setQq(cursor.getString(cursor.getColumnIndex(QQ)));
        company.setStatus(cursor.getString(cursor.getColumnIndex(STATUS)));
        company.setCompanydetailaddress(cursor.getString(cursor.getColumnIndex(DETAIL_ADDRESS)));
        company.setCompanyintroduction(cursor.getString(cursor.getColumnIndex(INTRODUCTION)));
        company.setIsSelected(cursor.getString(cursor.getColumnIndex(ISSELECTED)));
        company.setEmpnum(cursor.getString(cursor.getColumnIndex(EMPNUM)));
        company.setDepartnum(cursor.getString(cursor.getColumnIndex(DEPARTNUM)));
        company.setFansnum(cursor.getString(cursor.getColumnIndex(FANSNUM)));
        company.setInsidemembernum(cursor.getString(cursor.getColumnIndex(INSIDEMEMBERNUM)));
        company.setOutsidemembernum(cursor.getString(cursor.getColumnIndex(OUTSIDEMEMBERNUM)));
        company.setLastdynamic(cursor.getString(cursor.getColumnIndex(LASTDYNAMIC)));
        company.setIncompany(cursor.getString(cursor.getColumnIndex(INCOMPANY)));
        company.setFriend(cursor.getString(cursor.getColumnIndex(FRIEND)));
        return company;
    }
}
