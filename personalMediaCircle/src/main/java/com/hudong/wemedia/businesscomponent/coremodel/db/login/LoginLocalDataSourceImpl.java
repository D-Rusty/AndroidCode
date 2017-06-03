package com.hudong.wemedia.businesscomponent.coremodel.db.login;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;

import com.hudong.wemedia.basiccomponent.bean.LoginUser;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;



/**
 * Created by Administrator on 2016/10/17.
 */

public class LoginLocalDataSourceImpl implements ILoginLocalDataSource {

    private DBHelper dbHelper = null;

    public LoginLocalDataSourceImpl(Context context) {
        dbHelper = new DBHelper(context);
    }

    @Override
    public void addUserEntity(LoginUser user) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        db.delete(TABLE_NAME, null, null);
        db.beginTransaction();
        try {
            db.execSQL("insert into " + TABLE_NAME + " ("
                    + USERID + ","
                    + USERNAME + ","
                    + REALNAME + ","
                    + SEX + ","
                    + MOBILE + ","
                    + PASSWORD + ","
                    + HEADIMG + ","
                    + BIRTHDAY + ","
                    + EWMURL + ","
                    + PERSONALITYNICKNAME + ","
                    + EMAIL + ","
                    + WXEWMURL + ","
                    + WEIXIN + ","
                    + QQ + ","
                    + LAT + ","
                    + LNG + ","
                    + INTEGRAL + ","
                    + IBEACONID + ","
                    + QRURL + ","
                    + HOMEPROVINCE + ","
                    + HOMECITY + ","
                    + HOMEAREA + ","
                    + HOMEADDRESS + ","
                    + HOMEDETAILADDRESS + ","
                    + HOMETOWN + ","

                    + NOWPROVINCE + ","
                    + NOWCITY + ","
                    + NOWAREA + ","
                    + NOWADDRESS + ","
                    + NOWDETAILADDRESS + ","
                    + NOWTOWN + ","

                    + NOWPROVINCEC + ","
                    + NOWCITYC + ","
                    + NOWAREAC + ","
                    + NOWTOWNC + ","

                    + COMPANY + ","
                    + COMPANYID + ","
                    + COMPANYLOGO + ","
                    + FULLNAME + ","
                    + DEPARTMENT + ","
                    + JOB + ","
                    + IDENTITY + ","
                    + INDUSTRY + ","
                    + INDUSTRYID + ","
                    + COMPANYPROVINCE + ","
                    + COMPANYCITY + ","
                    + COMPANYAREA + ","
                    + COMPANYADDRESS + ","
                    + COMPANYDETAILADDRESS + ","
                    + COMPANYTOWN + ","
                    + COMPANYWEBSITE + ","
                    + OFFERINFO + ","
                    + OFFERKEYWORDS + ","
                    + OFFERTYPE + ","
                    + DEMANDINFO + ","
                    + DEMANDKEYWORDS + ","
                    + DEMANDTYPE + ","
                    + TOPINDUSTRY + ","
                    + TOPIDENTITY + ","
                    + BOTTOMINDUSTRY + ","
                    + BOTTOMIDENTITY + ")"
                    + " values('"
                    + user.getId() + "','"
                    + user.getUsername() + "','"
                    + user.getRealname() + "','"
                    + user.getSex() + "','"
                    + user.getMobile() + "','"
                    + user.getPassword() + "','"
                    + user.getHeadimg() + "','"
                    + user.getBirth() + "','"
                    + user.getEwmurl() + "','"
                    + user.getPersonalitynickname() + "','"
                    + user.getEmail() + "','"
                    + user.getWxewmurl() + "','"
                    + user.getWeixin() + "','"
                    + user.getQq() + "','"
                    + user.getLat() + "','"
                    + user.getLng() + "','"
                    + user.getIntegral() + "','"
                    + user.getIbeaconid() + "','"
                    + user.getQrurl() + "','"
                    + user.getHomeprovince() + "','"
                    + user.getHomecity() + "','"
                    + user.getHomearea() + "','"
                    + user.getHomeaddress() + "','"
                    + user.getHomedetailaddress() + "','"
                    + user.getHometown() + "','"
                    + user.getNowprovince() + "','"
                    + user.getNowcity() + "','"
                    + user.getNowarea() + "','"
                    + user.getNowaddress() + "','"
                    + user.getNowdetailaddress() + "','"
                    + user.getNowtown() + "','"

                    + user.getNowprovincec() + "','"
                    + user.getNowcityc() + "','"
                    + user.getNowareac() + "','"
                    + user.getNowtownc() + "','"

                    + user.getCompany() + "','"
                    + user.getCompanyid() + "','"
                    + user.getCompanylogo() + "','"
                    + user.getFullname() + "','"
                    + user.getDepartment() + "','"
                    + user.getJob() + "','"
                    + user.getIdentity() + "','"
                    + user.getIndustry() + "','"
                    + user.getIndustryid() + "','"
                    + user.getCompanyprovince() + "','"
                    + user.getCompanycity() + "','"
                    + user.getCompanyarea() + "','"
                    + user.getCompanyaddress() + "','"
                    + user.getCompanydetailaddress() + "','"
                    + user.getCompanytown() + "','"
                    + user.getCompanywebsite() + "','"
                    + user.getOfferinfo() + "','"
                    + user.getOfferkeywords() + "','"
                    + user.getOffertype() + "','"
                    + user.getDemandinfo() + "','"
                    + user.getDemandkeywords() + "','"
                    + user.getDemandtype() + "','"
                    + user.getTopindustry() + "','"
                    + user.getTopidentity() + "','"
                    + user.getBottomindustry() + "','"
                    + user.getBottomidentity() + "')"
            );
            db.setTransactionSuccessful();
        } catch (SQLiteFullException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        db.close();
    }

    @Override
    public LoginUser getUserEntity() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        LoginUser userEntity = new LoginUser();
        /**默认数据为空*/
        userEntity.setId("");
        userEntity.setUsername("");
        userEntity.setRealname("");
        userEntity.setSex("");
        userEntity.setMobile("");

        userEntity.setPassword("");
        userEntity.setHeadimg("");
        userEntity.setBirth("");
        userEntity.setEwmurl("");
        userEntity.setPersonalitynickname("");
        userEntity.setEmail("");
        userEntity.setWxewmurl("");
        userEntity.setWeixin("");
        userEntity.setQq("");
        userEntity.setLat("");
        userEntity.setLng("");
        userEntity.setIntegral("");
        userEntity.setIbeaconid("");
        userEntity.setQrurl("");
        userEntity.setHomeprovince("");
        userEntity.setHomecity("");
        userEntity.setHomearea("");
        userEntity.setHometown("");
        userEntity.setHomedetailaddress("");
        userEntity.setHomeaddress("");
        userEntity.setNowprovince("");
        userEntity.setNowprovincec("");
        userEntity.setNowcity("");
        userEntity.setNowcityc("");
        userEntity.setNowarea("");
        userEntity.setNowareac("");
        userEntity.setNowtown("");
        userEntity.setNowtownc("");
        userEntity.setNowdetailaddress("");
        userEntity.setNowaddress("");
        userEntity.setCompany("");
        userEntity.setCompanyid("");
        userEntity.setCompanylogo("");
        userEntity.setFullname("");
        userEntity.setDepartment("");
        userEntity.setJob("");
        userEntity.setIndustry("");
        userEntity.setIndustryid("");
        userEntity.setIdentity("");
        userEntity.setCompanyprovince("");
        userEntity.setCompanycity("");
        userEntity.setCompanyarea("");
        userEntity.setCompanytown("");
        userEntity.setCompanydetailaddress("");
        userEntity.setCompanyaddress("");
        userEntity.setCompanywebsite("");
        userEntity.setOfferinfo("");
        userEntity.setOfferkeywords("");
        userEntity.setOffertype("");
        userEntity.setDemandinfo("");
        userEntity.setDemandkeywords("");
        userEntity.setDemandtype("");
        userEntity.setTopidentity("");
        userEntity.setTopindustry("");
        userEntity.setBottomidentity("");
        userEntity.setBottomindustry("");


        Cursor c = db.rawQuery("select * from user", null);
        if (c != null && c.getCount() > 0) {
            c.moveToNext();
            userEntity.id = c.getString(c.getColumnIndex(USERID));
            userEntity.username = c.getString(c.getColumnIndex(USERNAME));
            userEntity.realname = c.getString(c.getColumnIndex(REALNAME));
            userEntity.sex = c.getString(c.getColumnIndex(SEX));
            userEntity.mobile = c.getString(c.getColumnIndex(MOBILE));
            userEntity.password = c.getString(c.getColumnIndex(PASSWORD));
            userEntity.headimg = c.getString(c.getColumnIndex(HEADIMG));
            userEntity.birth = c.getString(c.getColumnIndex(BIRTHDAY));
            userEntity.ewmurl = c.getString(c.getColumnIndex(EWMURL));
            userEntity.personalitynickname = c.getString(c.getColumnIndex(PERSONALITYNICKNAME));
            userEntity.email = c.getString(c.getColumnIndex(EMAIL));
            userEntity.wxewmurl = c.getString(c.getColumnIndex(WXEWMURL));
            userEntity.weixin = c.getString(c.getColumnIndex(WEIXIN));
            userEntity.qq = c.getString(c.getColumnIndex(QQ));
            userEntity.lat = c.getString(c.getColumnIndex(LAT));
            userEntity.lng = c.getString(c.getColumnIndex(LNG));
            userEntity.integral = c.getString(c.getColumnIndex(INTEGRAL));
            userEntity.ibeaconid = c.getString(c.getColumnIndex(IBEACONID));
            userEntity.qrurl = c.getString(c.getColumnIndex(QRURL));
            userEntity.homeprovince = c.getString(c.getColumnIndex(HOMEPROVINCE));
            userEntity.homecity = c.getString(c.getColumnIndex(HOMECITY));
            userEntity.homearea = c.getString(c.getColumnIndex(HOMEAREA));
            userEntity.hometown = c.getString(c.getColumnIndex(HOMETOWN));
            userEntity.homedetailaddress = c.getString(c.getColumnIndex(HOMEDETAILADDRESS));
            userEntity.homeaddress = c.getString(c.getColumnIndex(HOMEADDRESS));
            userEntity.nowprovince = c.getString(c.getColumnIndex(NOWPROVINCE));
            userEntity.nowcity = c.getString(c.getColumnIndex(NOWCITY));
            userEntity.nowarea = c.getString(c.getColumnIndex(NOWAREA));
            userEntity.nowtown = c.getString(c.getColumnIndex(NOWTOWN));
            userEntity.nowdetailaddress = c.getString(c.getColumnIndex(NOWDETAILADDRESS));
            userEntity.nowaddress = c.getString(c.getColumnIndex(NOWADDRESS));

            userEntity.nowprovincec = c.getString(c.getColumnIndex(NOWPROVINCEC));
            userEntity.nowcityc = c.getString(c.getColumnIndex(NOWCITYC));
            userEntity.nowareac = c.getString(c.getColumnIndex(NOWAREAC));
            userEntity.nowtownc = c.getString(c.getColumnIndex(NOWTOWNC));

            userEntity.company = c.getString(c.getColumnIndex(COMPANY));
            userEntity.companyid = c.getString(c.getColumnIndex(COMPANYID));
            userEntity.companylogo = c.getString(c.getColumnIndex(COMPANYLOGO));
            userEntity.fullname = c.getString(c.getColumnIndex(FULLNAME));
            userEntity.department = c.getString(c.getColumnIndex(DEPARTMENT));
            userEntity.job = c.getString(c.getColumnIndex(JOB));
            userEntity.identity = c.getString(c.getColumnIndex(IDENTITY));
            userEntity.industry = c.getString(c.getColumnIndex(INDUSTRY));
            userEntity.industryid = c.getString(c.getColumnIndex(INDUSTRYID));
            userEntity.companyprovince = c.getString(c.getColumnIndex(COMPANYPROVINCE));
            userEntity.companycity = c.getString(c.getColumnIndex(COMPANYCITY));
            userEntity.companyarea = c.getString(c.getColumnIndex(COMPANYAREA));
            userEntity.companytown = c.getString(c.getColumnIndex(COMPANYTOWN));
            userEntity.companydetailaddress = c.getString(c.getColumnIndex(COMPANYDETAILADDRESS));
            userEntity.companyaddress = c.getString(c.getColumnIndex(COMPANYADDRESS));
            userEntity.companywebsite = c.getString(c.getColumnIndex(COMPANYWEBSITE));
            userEntity.offerinfo = c.getString(c.getColumnIndex(OFFERINFO));
            userEntity.offerkeywords = c.getString(c.getColumnIndex(OFFERKEYWORDS));
            userEntity.offertype = c.getString(c.getColumnIndex(OFFERTYPE));
            userEntity.demandinfo = c.getString(c.getColumnIndex(DEMANDINFO));
            userEntity.demandkeywords = c.getString(c.getColumnIndex(DEMANDKEYWORDS));
            userEntity.demandtype = c.getString(c.getColumnIndex(DEMANDTYPE));
            userEntity.topindustry = c.getString(c.getColumnIndex(TOPINDUSTRY));
            userEntity.topidentity = c.getString(c.getColumnIndex(TOPIDENTITY));
            userEntity.bottomindustry = c.getString(c.getColumnIndex(BOTTOMINDUSTRY));
            userEntity.bottomidentity = c.getString(c.getColumnIndex(BOTTOMIDENTITY));
            c.close();
        }
        db.close();
        return userEntity;
    }

}


