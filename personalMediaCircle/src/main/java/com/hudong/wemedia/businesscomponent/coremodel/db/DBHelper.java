package com.hudong.wemedia.businesscomponent.coremodel.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hudong.wemedia.businesscomponent.coremodel.db.addr.IAddrLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.circle.ICircleLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.circlemember.ICircleMemberLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.company.ICompanyLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.companymember.ICompanyMemberLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.connections.IConnectionsLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.department.IDepartmentLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.foucuscompany.IFoucusCompanyLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.friend.IFriendLocalDataSouce;
import com.hudong.wemedia.businesscomponent.coremodel.db.friendapply.IFriendApplyLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.group.IGroupLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.groupmember.IGroupMemberLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.industry.IIndustryLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.label.ILabelLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.login.ILoginLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.outermember.IOuterMemberLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.verificationcode.IVerificationCodeLocalDataSource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Created by Administrator on 2016/10/17.
 */
public class DBHelper extends SQLiteOpenHelper {

    private ReadWriteLock lock = new ReentrantReadWriteLock(true);

    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    private static final String DB_NAME = "we_media.db"; //数据库名称
    private static final int version = 2; //版本


    public SQLiteDatabase getReadableDataBase() {
        readLock.lock();
        try {
            return this.getReadableDatabase();
        } finally {
            readLock.unlock();
        }
    }

    public SQLiteDatabase getWritableDataBase() {
        writeLock.lock();
        try {
            return this.getWritableDatabase();
        } finally {
            writeLock.unlock();
        }
    }


    public DBHelper(Context context) {
        super(context, DB_NAME, null, version);
    }


    private final String GROUP_TABLE_CREATE = "create table "
            + IGroupLocalDataSource.TABLE_NAME + "("
            + IGroupLocalDataSource.MID + " varchar, "
            + IGroupLocalDataSource.GROUPID + " varchar, "
            + IGroupLocalDataSource.ALIAS + " varchar, "
            + IGroupLocalDataSource.NAME + " varchar, "
            + IGroupLocalDataSource.SORT + " varchar);";

    private final String LABEL_TABLE_CREATE = "create table "
            + ILabelLocalDataSource.TABLE_NAME + "("
            + ILabelLocalDataSource.MID + " varchar, "
            + ILabelLocalDataSource.LABELNAME + " varchar, "
            + ILabelLocalDataSource.GROUPID + " varchar, "
            + ILabelLocalDataSource.ID + " varchar);";


    private final String INDUSTRY_TABLE_CREATE = "create table "
            + IIndustryLocalDataSource.TABLE_NAME + "("
            + IIndustryLocalDataSource.ID + " varchar, "
            + IIndustryLocalDataSource.TITLE + " varchar, "
            + IIndustryLocalDataSource.PARENTID + " varchar);";

    private final String CONNECTIONS_TABLE_CREATE = "create table "
            + IConnectionsLocalDataSource.TABLE_NAME + "("
            + IConnectionsLocalDataSource.ID + " integer , "
            + IConnectionsLocalDataSource.REALNAME + " varchar, "
            + IConnectionsLocalDataSource.SEX + " varchar, "
            + IConnectionsLocalDataSource.BIRTH + " varchar, "
            + IConnectionsLocalDataSource.MOBILE + " varchar, "
            + IConnectionsLocalDataSource.COMPANY + " varchar, "
            + IConnectionsLocalDataSource.INDUSTRY + " varchar, "
            + IConnectionsLocalDataSource.DEPARTMENT + " varchar, "
            + IConnectionsLocalDataSource.COMPANY_ADDRESS + " varchar, "
            + IConnectionsLocalDataSource.HEADIMG + " varchar, "
            + IConnectionsLocalDataSource.HOME_ADDRESS + " varchar, "
            + IConnectionsLocalDataSource.NOW_ADDRESS + " varchar, "
            + IConnectionsLocalDataSource.TOP_IDENTITY + " varchar, "
            + IConnectionsLocalDataSource.BOTTOM_IDENTITY + " varchar, "
            + IConnectionsLocalDataSource.JOB + " varchar, "
            + IConnectionsLocalDataSource.GROUP_ID + " varchar, "
            + IConnectionsLocalDataSource.GROUP_NAME + " varchar, "
            + IConnectionsLocalDataSource.LABLE_ID + " varchar, "
            + IConnectionsLocalDataSource.NOW_PROVINCE + " varchar, "
            + IConnectionsLocalDataSource.NOW_PROVINCEC + " varchar, "
            + IConnectionsLocalDataSource.NOW_CITY + " varchar, "
            + IConnectionsLocalDataSource.NOW_CITYC + " varchar, "
            + IConnectionsLocalDataSource.SPECIAL + " varchar, "
            + IConnectionsLocalDataSource.MEMBER_RECRUIT + " varchar,"
            + IConnectionsLocalDataSource.MEMBER_SEEK + " varchar,"
            + IConnectionsLocalDataSource.MEMBER_FOR + " varchar,"
            + IConnectionsLocalDataSource.LOGIN_USERID + " varchar);";

    private final String FRIEND_LIST_TABLE_CREATE = "create table "
            + IFriendLocalDataSouce.TABLE_NAME + "("
            + IFriendLocalDataSouce.ID + " integer , "
            + IFriendLocalDataSouce.REALNAME + " varchar, "
            + IFriendLocalDataSouce.SEX + " varchar, "
            + IFriendLocalDataSouce.BIRTH + " varchar, "
            + IFriendLocalDataSouce.MOBILE + " varchar, "
            + IFriendLocalDataSouce.COMPANY + " varchar, "
            + IFriendLocalDataSouce.INDUSTRY + " varchar, "
            + IFriendLocalDataSouce.DEPARTMENT + " varchar, "
            + IFriendLocalDataSouce.COMPANY_ADDRESS + " varchar, "
            + IFriendLocalDataSouce.HEADIMG + " varchar, "
            + IFriendLocalDataSouce.HOME_ADDRESS + " varchar, "
            + IFriendLocalDataSouce.NOW_ADDRESS + " varchar, "
            + IFriendLocalDataSouce.TOP_IDENTITY + " varchar, "
            + IFriendLocalDataSouce.BOTTOM_IDENTITY + " varchar, "
            + IFriendLocalDataSouce.JOB + " varchar, "
            + IFriendLocalDataSouce.NOW_PROVINCE + " varchar, "
            + IFriendLocalDataSouce.NOW_CITY + " varchar, "
            + IFriendLocalDataSouce.ISFRIEND + " varchar, "
            + IFriendLocalDataSouce.ISAPPLIED + " varchar, "
            + IFriendLocalDataSouce.ISREFUSE + " varchar, "
            + IFriendLocalDataSouce.ISACCEPT + " varchar, "
            + IFriendLocalDataSouce.LOGIN_USERID + " varchar, "
            + IFriendLocalDataSouce.USERNAME + " varchar); ";

    private final String ADDR_TABLE_CREATE = "create table "
            + IAddrLocalDataSource.TABLE_NAME + "("
            + IAddrLocalDataSource.CODE + " varchar, "
            + IAddrLocalDataSource.NAME + " varchar, "
            + IAddrLocalDataSource.PARENTID + " varchar, "
            + IAddrLocalDataSource.LEVEL + " varchar);";

    private final String USER_TABLE_CREATE = "create table "
            + ILoginLocalDataSource.TABLE_NAME + " ("
            + ILoginLocalDataSource.USERID + " varchar, "
            + ILoginLocalDataSource.USERNAME + " varchar, "
            + ILoginLocalDataSource.REALNAME + " varchar, "
            + ILoginLocalDataSource.SEX + " varchar, "
            + ILoginLocalDataSource.MOBILE + " varchar, "
            + ILoginLocalDataSource.PASSWORD + " varchar, "
            + ILoginLocalDataSource.HEADIMG + " varchar(255), "
            + ILoginLocalDataSource.BIRTHDAY + " varchar, "
            + ILoginLocalDataSource.EWMURL + " varchar, "
            + ILoginLocalDataSource.PERSONALITYNICKNAME + " varchar, "
            + ILoginLocalDataSource.EMAIL + " varchar, "
            + ILoginLocalDataSource.WXEWMURL + " varchar, "
            + ILoginLocalDataSource.WEIXIN + " varchar, "
            + ILoginLocalDataSource.QQ + " varchar, "
            + ILoginLocalDataSource.LAT + " varchar, "
            + ILoginLocalDataSource.LNG + " varchar, "
            + ILoginLocalDataSource.INTEGRAL + " varchar, "
            + ILoginLocalDataSource.IBEACONID + " varchar, "
            + ILoginLocalDataSource.QRURL + " varchar, "
            + ILoginLocalDataSource.HOMEPROVINCE + " varchar, "
            + ILoginLocalDataSource.HOMECITY + " varchar, "
            + ILoginLocalDataSource.HOMEAREA + " varchar, "
            + ILoginLocalDataSource.HOMEADDRESS + " varchar, "
            + ILoginLocalDataSource.HOMEDETAILADDRESS + " varchar, "
            + ILoginLocalDataSource.HOMETOWN + " varchar, "
            + ILoginLocalDataSource.NOWPROVINCE + " varchar, "
            + ILoginLocalDataSource.NOWCITY + " varchar, "
            + ILoginLocalDataSource.NOWAREA + " varchar, "
            + ILoginLocalDataSource.NOWADDRESS + " varchar, "
            + ILoginLocalDataSource.NOWPROVINCEC + " varchar, "
            + ILoginLocalDataSource.NOWCITYC + " varchar, "
            + ILoginLocalDataSource.NOWAREAC + " varchar, "
            + ILoginLocalDataSource.NOWTOWNC + " varchar, "
            + ILoginLocalDataSource.NOWDETAILADDRESS + " varchar, "
            + ILoginLocalDataSource.NOWTOWN + " varchar, "
            + ILoginLocalDataSource.COMPANY + " varchar, "
            + ILoginLocalDataSource.COMPANYID + " varchar, "
            + ILoginLocalDataSource.COMPANYLOGO + " varchar, "
            + ILoginLocalDataSource.FULLNAME + " varchar, "
            + ILoginLocalDataSource.DEPARTMENT + " varchar, "
            + ILoginLocalDataSource.JOB + " varchar, "
            + ILoginLocalDataSource.IDENTITY + " varchar, "
            + ILoginLocalDataSource.INDUSTRY + " varchar, "
            + ILoginLocalDataSource.INDUSTRYID + " varchar, "
            + ILoginLocalDataSource.COMPANYPROVINCE + " varchar,"
            + ILoginLocalDataSource.COMPANYCITY + " varchar,"
            + ILoginLocalDataSource.COMPANYAREA + " varchar,"
            + ILoginLocalDataSource.COMPANYADDRESS + " varchar,"
            + ILoginLocalDataSource.COMPANYDETAILADDRESS + " varchar, "
            + ILoginLocalDataSource.COMPANYTOWN + " varchar, "
            + ILoginLocalDataSource.COMPANYWEBSITE + " varchar, "
            + ILoginLocalDataSource.OFFERINFO + " varchar, "
            + ILoginLocalDataSource.OFFERKEYWORDS + " varchar, "
            + ILoginLocalDataSource.OFFERTYPE + " varchar, "
            + ILoginLocalDataSource.DEMANDINFO + " varchar, "
            + ILoginLocalDataSource.DEMANDKEYWORDS + " varchar, "
            + ILoginLocalDataSource.DEMANDTYPE + " varchar, "
            + ILoginLocalDataSource.TOPINDUSTRY + " varchar, "
            + ILoginLocalDataSource.TOPIDENTITY + " varchar, "
            + ILoginLocalDataSource.BOTTOMINDUSTRY + " varchar, "
            + ILoginLocalDataSource.BOTTOMIDENTITY + " varchar" + ");";


    private final String DEPARTMENT_TABLE_CREATE = "create table "
            + IDepartmentLocalDataSource.TABLE_NAME + "("
            + IDepartmentLocalDataSource.ID + " varchar, "
            + IDepartmentLocalDataSource.LOGIN_USERID + " varchar, "
            + IDepartmentLocalDataSource.COMPANY_ID + " varchar, "
            + IDepartmentLocalDataSource.DEPARTMENT + " varchar, "
            + IDepartmentLocalDataSource.COUNT + " varchar, "
            + IDepartmentLocalDataSource.OPENING + " varchar, "
            + IDepartmentLocalDataSource.USEL + " varchar, "
            + IDepartmentLocalDataSource.PARENTID + " varchar); ";

    private final String COMPANYMEMBER_TABLE_CREATE = "create table "
            + ICompanyMemberLocalDataSource.TABLE_NAME + "("
            + ICompanyMemberLocalDataSource.LOGIN_USERID + " varchar, "
            + ICompanyMemberLocalDataSource.MID + " varchar, "
            + ICompanyMemberLocalDataSource.COMPANY_ID + " varchar, "
            + ICompanyMemberLocalDataSource.DEPARTMENT + " varchar, "
            + ICompanyMemberLocalDataSource.DEPARTMENTID + " varchar, "
            + ICompanyMemberLocalDataSource.FULLNAME + " varchar, "
            + ICompanyMemberLocalDataSource.JOB + " varchar, "
            + ICompanyMemberLocalDataSource.HEADIMG + " varchar, "
            + ICompanyMemberLocalDataSource.MANAGE + " varchar, "
            + ICompanyMemberLocalDataSource.DEPARTMENTMANAGER + " varchar, "
            + ICompanyMemberLocalDataSource.MOBILE + " varchar, "
            + ICompanyMemberLocalDataSource.SUBFULLNAME + " varchar); ";

    private final String FRIEND_APPLY_TABLE_CREATE = "create table "
            + IFriendApplyLocalDataSource.TABLE_NAME + "("
            + IFriendApplyLocalDataSource.ID + " varchar, "
            + IFriendApplyLocalDataSource.REALNAME + " varchar, "
            + IFriendApplyLocalDataSource.SEX + " varchar, "
            + IFriendApplyLocalDataSource.MOBILE + " varchar, "
            + IFriendApplyLocalDataSource.COMPANY + " varchar, "
            + IFriendApplyLocalDataSource.INDUSTRY + " varchar, "
            + IFriendApplyLocalDataSource.DEPARTMENT + " varchar, "
            + IFriendApplyLocalDataSource.HEADIMG + " varchar, "
            + IFriendApplyLocalDataSource.JOB + " varchar, "
            + IFriendApplyLocalDataSource.NOW_PROVINCE + " varchar, "
            + IFriendApplyLocalDataSource.NOW_CITY + " varchar, "
            + IFriendApplyLocalDataSource.NOW_AREA + " varchar, "
            + IFriendApplyLocalDataSource.NOW_TOWN + " varchar, "
            + IFriendApplyLocalDataSource.NOW_ADDRESS + " varchar, "
            + IFriendApplyLocalDataSource.LOGIN_USERID + " varchar, "
            + IFriendApplyLocalDataSource.IS_PHONE_CONTACT + " varchar, "
            + IFriendApplyLocalDataSource.USERNAME + " varchar); ";


    private final String FoucusCOMPANY_TABLE_CREATE = "create table "
            + IFoucusCompanyLocalDataSource.TABLE_NAME + "("
            + IFoucusCompanyLocalDataSource.LOGIN_USERID + " varchar, "
            + IFoucusCompanyLocalDataSource.ID + " varchar, "
            + IFoucusCompanyLocalDataSource.NAME + " varchar, "
            + IFoucusCompanyLocalDataSource.LOGO + " varchar, "
            + IFoucusCompanyLocalDataSource.INDUSTRY + " varchar, "
            + IFoucusCompanyLocalDataSource.PROVINCE + " varchar, "
            + IFoucusCompanyLocalDataSource.CITY + " varchar, "
            + IFoucusCompanyLocalDataSource.AREA + " varchar, "
            + IFoucusCompanyLocalDataSource.TOWN + " varchar, "
            + IFoucusCompanyLocalDataSource.ADDRESS + " varchar, "
            + IFoucusCompanyLocalDataSource.CREAT_TIME + " varchar, "
            + IFoucusCompanyLocalDataSource.RZ + " varchar, "
            + IFoucusCompanyLocalDataSource.ADURL + " varchar, "
            + IFoucusCompanyLocalDataSource.COMPANYWEBSITE + " varchar, "
            + IFoucusCompanyLocalDataSource.SHOPURL + " varchar, "
            + IFoucusCompanyLocalDataSource.PHONE + " varchar, "
            + IFoucusCompanyLocalDataSource.EMAIL + " varchar, "
            + IFoucusCompanyLocalDataSource.WEIXIN + " varchar, "
            + IFoucusCompanyLocalDataSource.INTRODUCTION + " varchar, "
            + IFoucusCompanyLocalDataSource.FRIEND + " varchar, "
            + IFoucusCompanyLocalDataSource.QQ + " varchar, "
            + IFoucusCompanyLocalDataSource.STATUS + " varchar, "
            + IFoucusCompanyLocalDataSource.DETAIL_ADDRESS + " varchar, "
            + IFoucusCompanyLocalDataSource.COMPANY_TOWN + " varchar); ";


    private final String COMPANY_TABLE_CREATE = "create table "
            + ICompanyLocalDataSource.TABLE_NAME + "("
            + ICompanyLocalDataSource.LOGIN_USERID + " varchar, "
            + ICompanyLocalDataSource.ID + " varchar, "
            + ICompanyLocalDataSource.NAME + " varchar, "
            + ICompanyLocalDataSource.LOGO + " varchar, "
            + ICompanyLocalDataSource.INDUSTRY + " varchar, "
            + ICompanyLocalDataSource.INDUSTRYC + " varchar, "
            + ICompanyLocalDataSource.PROVINCE + " varchar, "
            + ICompanyLocalDataSource.CITY + " varchar, "
            + ICompanyLocalDataSource.AREA + " varchar, "
            + ICompanyLocalDataSource.TOWN + " varchar, "
            + ICompanyLocalDataSource.ADDRESS + " varchar, "
            + ICompanyLocalDataSource.CREAT_TIME + " varchar, "
            + ICompanyLocalDataSource.RZ + " varchar, "
            + ICompanyLocalDataSource.COMPANYWEBSITE + " varchar, "
            + ICompanyLocalDataSource.ADURL + " varchar, "
            + ICompanyLocalDataSource.SHOPURL + " varchar, "
            + ICompanyLocalDataSource.DEFINEDSHOPURL + " varchar, "
            + ICompanyLocalDataSource.SHOP_TYPE + " varchar, "
            + ICompanyLocalDataSource.IS_SHOW_TEL + " varchar, "
            + ICompanyLocalDataSource.PHONE + " varchar, "
            + ICompanyLocalDataSource.EMAIL + " varchar, "
            + ICompanyLocalDataSource.WEIXIN + " varchar, "
            + ICompanyLocalDataSource.QQ + " varchar, "
            + ICompanyLocalDataSource.STATUS + " varchar, "
            + ICompanyLocalDataSource.DETAIL_ADDRESS + " varchar, "
            + ICompanyLocalDataSource.INTRODUCTION + " varchar, "
            + ICompanyLocalDataSource.COMPANY_TOWN + " varchar, "
            + ICompanyLocalDataSource.ISSELECTED + " varchar, "
            + ICompanyLocalDataSource.EMPNUM + " varchar, "
            + ICompanyLocalDataSource.DEPARTNUM + " varchar, "
            + ICompanyLocalDataSource.FANSNUM + " varchar, "
            + ICompanyLocalDataSource.INSIDEMEMBERNUM + " varchar, "
            + ICompanyLocalDataSource.OUTSIDEMEMBERNUM + " varchar, "
            + ICompanyLocalDataSource.LASTDYNAMIC + " varchar, "
            + ICompanyLocalDataSource.INCOMPANY + " varchar, "
            + ICompanyLocalDataSource.FRIEND + " varchar); ";

    private final String VERCODE_TABLE_CREATE = "create table "
            + IVerificationCodeLocalDataSource.TABLE_NAME + "("
            + IVerificationCodeLocalDataSource.CODE + " varchar, "
            + IVerificationCodeLocalDataSource.MOBILE + " varchar, "
            + IVerificationCodeLocalDataSource.TIME + " varchar); ";

    private final String OUTERMEMBER_TABLE_CREATE = "create table "
            + IOuterMemberLocalDataSource.TABLE_NAME + "("
            + IOuterMemberLocalDataSource.ID + " varchar, "
            + IOuterMemberLocalDataSource.NAME + " varchar, "
            + IOuterMemberLocalDataSource.MOBILE + " varchar, "
            + IOuterMemberLocalDataSource.COMPANY + " varchar, "
            + IOuterMemberLocalDataSource.JOB + " varchar, "
            + IOuterMemberLocalDataSource.PROVINCE + " varchar, "
            + IOuterMemberLocalDataSource.CITY + " varchar, "
            + IOuterMemberLocalDataSource.AREA + " varchar, "
            + IOuterMemberLocalDataSource.TOWN + " varchar, "
            + IOuterMemberLocalDataSource.LABEL + " varchar, "
            + IOuterMemberLocalDataSource.IS_MEBER + " varchar, "
            + IOuterMemberLocalDataSource.RELEASENAME + " varchar, "
            + IOuterMemberLocalDataSource.REMARKS + " varchar, "
            + IOuterMemberLocalDataSource.COMPANY_ID + " varchar, "
            + IOuterMemberLocalDataSource.ADDRESS + " varchar, "
            + IOuterMemberLocalDataSource.MID + " varchar, "
            + IOuterMemberLocalDataSource.FRIENDID + " varchar); ";


    private final String CIRCLE_LIST_TABLE_CREATE = "create table "
            + ICircleLocalDataSource.TABLE_NAME + "("
            + ICircleLocalDataSource.ID + " integer , "
            + ICircleLocalDataSource.MID + " varchar, "
            + ICircleLocalDataSource.WEID + " varchar, "
            + ICircleLocalDataSource.QUN_TYPE + " varchar, "
            + ICircleLocalDataSource.GROUP_NAME + " varchar, "
            + ICircleLocalDataSource.GROUP_INFO + " varchar, "
            + ICircleLocalDataSource.GROUP_QR + " varchar, "
            + ICircleLocalDataSource.CREATE_TIME + " varchar, "
            + ICircleLocalDataSource.TOTAL + " varchar, "
            + ICircleLocalDataSource.QUNIMG + " varchar, "
            + ICircleLocalDataSource.AREACODE + " varchar, "
            + ICircleLocalDataSource.ACTIVITY_COUNT + " varchar, "
            + ICircleLocalDataSource.INDUSTRY + " varchar, "
            + ICircleLocalDataSource.XINGQUID + " varchar, "
            + ICircleLocalDataSource.APPGROUPID + " varchar, "
            + ICircleLocalDataSource.INDUSTRYC + " varchar, "
            + ICircleLocalDataSource.XINGQUIDC + " varchar, "
            + ICircleLocalDataSource.TYPEC + " varchar, "
            + ICircleLocalDataSource.LOGIN_USER_ID + " varchar); ";

    private final String GROUP_MEMBER_TABLE_CREATE = "create table "
            + IGroupMemberLocalDataSource.TABLE_NAME + "("
            + IGroupMemberLocalDataSource.ID + " integer , "
            + IGroupMemberLocalDataSource.MID + " varchar, "
            + IGroupMemberLocalDataSource.INTROPENID + " varchar, "
            + IGroupMemberLocalDataSource.HEADIMG + " varchar, "
            + IGroupMemberLocalDataSource.STATUS + " varchar, "
            + IGroupMemberLocalDataSource.MANAGE + " varchar, "
            + IGroupMemberLocalDataSource.REALNAME + " varchar, "
            + IGroupMemberLocalDataSource.GROUP_ID + " varchar, "
            + IGroupMemberLocalDataSource.LOGIN_USER_ID + " varchar); ";


    String ID = "id";
    String WID = "wid";
    String MID = "mid";
    String CID = "cid";
    String INTROPENID = "intropenid";
    String HEADIMG = "headimg";
    String CREATE_TIME = "create_time";
    String STATTUS = "status";
    String MANAGER = "manage";
    String GID = "gid";
    String REALNAME = "realname";
    String PROVINCE = "province";
    String CITY = "city";
    String COUNTY = "county";
    String TOWN = "town";
    String PROVINCEC = "provincec";
    String CITYC = "cityc";
    String COUNTYC = "countyc";
    String TOWNC = "townc";
    String ISFRIEND = "isfriend";
    String APPLYGROUPINFO = "applygroupinfo";
    String APPLYCELEBRITYINFO = "applycelebrityinfo";
    String QUNNAME = "qunname";


    private final String CICLRE_MEMBER_TABLE_CREATE = "create table "
            + ICircleMemberLocalDataSource.TABLE_NAME + "("
            + ICircleMemberLocalDataSource.ID + " varchar , "
            + ICircleMemberLocalDataSource.WID + " varchar , "
            + ICircleMemberLocalDataSource.MID + " varchar , "
            + ICircleMemberLocalDataSource.CID + " varchar , "
            + ICircleMemberLocalDataSource.INTROPENID + " varchar , "
            + ICircleMemberLocalDataSource.HEADIMG + " varchar , "
            + ICircleMemberLocalDataSource.CREATE_TIME + " varchar , "
            + ICircleMemberLocalDataSource.STATTUS + " varchar , "
            + ICircleMemberLocalDataSource.MANAGER + " varchar , "
            + ICircleMemberLocalDataSource.REALNAME + " varchar , "
            + ICircleMemberLocalDataSource.PROVINCE + " varchar , "
            + ICircleMemberLocalDataSource.CITY + " varchar , "
            + ICircleMemberLocalDataSource.COUNTY + " varchar , "
            + ICircleMemberLocalDataSource.TOWN + " varchar , "
            + ICircleMemberLocalDataSource.PROVINCEC + " varchar , "
            + ICircleMemberLocalDataSource.CITYC + " varchar , "
            + ICircleMemberLocalDataSource.COUNTYC + " varchar , "
            + ICircleMemberLocalDataSource.TOWNC + " varchar , "
            + ICircleMemberLocalDataSource.ISFRIEND + " varchar , "
            + ICircleMemberLocalDataSource.APPLYGROUPINFO + " varchar , "
            + ICircleMemberLocalDataSource.APPLYCELEBRITYINFO + " varchar , "
            + ICircleMemberLocalDataSource.QUNNAME + " varchar); ";


//    private final String INTERNAL_COMPANY_DEPTMENT__TABLE_CREATE = "create table "
//            + InternalCompanyDeptmentDao.TABLE_NAME + "("
//            + InternalCompanyDeptmentDao.ID + " integer , "
//            + InternalCompanyDeptmentDao.DEPARTMENT + " varchar, "
//            + InternalCompanyDeptmentDao.PARENTID + " varchar, "
//            + InternalCompanyDeptmentDao.COUNT + " varchar, "
//            + InternalCompanyDeptmentDao.OPENING + " varchar, "
//            + InternalCompanyDeptmentDao.USEL + " varchar, "
//            + InternalCompanyDeptmentDao.COMPID + " varchar); ";


//    private final String INTERNAL_COMPANY__TABLE_CREATE = "create table " +
//            InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.TABLE_NAME + "("
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.ID + " integer , "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.NAME + " varchar, "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.MOBILE + " varchar, "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.COMPANY + " varchar, "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.JOB + " varchar, "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.PROVINCE + " varchar, "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.CITY + " varchar, "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.AREA + " varchar, "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.TOWN + " varchar, "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.LABEL + " varchar, "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.IS_MEBER + " varchar, "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.RELEASENAME + " varchar, "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.REMARKS + " varchar, "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.COMPANYID + " varchar, "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.MID + " varchar, "
//            + InternalCompanyOutsourcingConnectionsLocalDataSourceImpl.FRIENDID + "  varchar); ";

//    private final String INTERNAL_COMPANY_INTERNAL__TABLE_CREATE = "create table " +
//            InternalCompanyInternalConnectionsLocalDataSourceImpl.TABLE_NAME + "("
//            + InternalCompanyInternalConnectionsLocalDataSourceImpl.MANAGER + " integer , "
//            + InternalCompanyInternalConnectionsLocalDataSourceImpl.DEPARTMENTID + " varchar, "
//            + InternalCompanyInternalConnectionsLocalDataSourceImpl.MOBILE + " varchar, "
//            + InternalCompanyInternalConnectionsLocalDataSourceImpl.DEPARTMENT + " varchar, "
//            + InternalCompanyInternalConnectionsLocalDataSourceImpl.DEPARTMENTMANAGER + " varchar, "
//            + InternalCompanyInternalConnectionsLocalDataSourceImpl.FULLNAME + " varchar, "
//            + InternalCompanyInternalConnectionsLocalDataSourceImpl.SUBFULLNAME + " varchar, "
//            + InternalCompanyInternalConnectionsLocalDataSourceImpl.MID + " varchar, "
//            + InternalCompanyInternalConnectionsLocalDataSourceImpl.HEADIMG + " varchar, "
//            + InternalCompanyInternalConnectionsLocalDataSourceImpl.COMPID + " varchar); ";


    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(INTERNAL_COMPANY_INTERNAL__TABLE_CREATE);
//        db.execSQL(INTERNAL_COMPANY__TABLE_CREATE);
//        db.execSQL(INTERNAL_COMPANY_DEPTMENT__TABLE_CREATE);
        db.execSQL(FoucusCOMPANY_TABLE_CREATE);
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(INDUSTRY_TABLE_CREATE);
        db.execSQL(CONNECTIONS_TABLE_CREATE);
        db.execSQL(FRIEND_LIST_TABLE_CREATE);
        db.execSQL(ADDR_TABLE_CREATE);
        db.execSQL(DEPARTMENT_TABLE_CREATE);
        db.execSQL(COMPANYMEMBER_TABLE_CREATE);
        db.execSQL(FRIEND_APPLY_TABLE_CREATE);
        db.execSQL(COMPANY_TABLE_CREATE);
        db.execSQL(VERCODE_TABLE_CREATE);
        db.execSQL(OUTERMEMBER_TABLE_CREATE);
        db.execSQL(CIRCLE_LIST_TABLE_CREATE);
        db.execSQL(LABEL_TABLE_CREATE);
        db.execSQL(GROUP_TABLE_CREATE);
        db.execSQL(GROUP_MEMBER_TABLE_CREATE);
        db.execSQL(CICLRE_MEMBER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /**
     * @return Void
     * @throws
     * @params: Void
     * @date: 2017/3/23 下午3:06 <br>
     * @Description 清除圈子表数据
     */

    public void clearCircleTable() {
        SQLiteDatabase db = this.getWritableDataBase();
        db.execSQL("delete  from " + IFoucusCompanyLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + IFoucusCompanyLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + IFoucusCompanyLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + IIndustryLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + ILoginLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + IConnectionsLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + IFriendLocalDataSouce.TABLE_NAME);
        db.execSQL("delete  from " + IAddrLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + IDepartmentLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + ICompanyLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + IOuterMemberLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + ICircleLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + ILabelLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + IGroupLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + IGroupMemberLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + ICompanyMemberLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + IFriendApplyLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + IVerificationCodeLocalDataSource.TABLE_NAME);
        db.execSQL("delete  from " + ICircleMemberLocalDataSource.TABLE_NAME);
    }


}
