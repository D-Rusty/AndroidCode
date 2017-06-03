package com.hudong.wemedia.businesscomponent.coremodel.db.login;

import com.hudong.wemedia.basiccomponent.bean.LoginUser;

/**
 * 作者: 方天文
 * 日期: 2017/4/19:下午4:47
 * 概况:
 */

public interface ILoginLocalDataSource {


    String TABLE_NAME = "user";
    String  USERID = "userid";//用户ID
    String  USERNAME = "username";//用户名
    String  REALNAME = "realname";//用户昵称
    String  SEX = "sex";//性别
    String  MOBILE = "mobile";//手机号
    String  PASSWORD = "password";//用户密码
    String  HEADIMG = "headimg";//头像
    String  BIRTHDAY = "birthday";//生日
    String  EWMURL = "ewmurl";//二维码
    String  PERSONALITYNICKNAME = "personalitynickname"; //个性签名
    String  EMAIL = "email";//邮箱
    String  WXEWMURL = "wxewmurl";//微信二维码
    String  WEIXIN = "weixin";//微信号
    String  QQ = "qq";//QQ号
    String  LAT = "lat";//经度
    String  LNG = "lng";//纬度
    String  INTEGRAL = "integral";//积分
    String  IBEACONID = "ibeaconid";//ibeacon设备id
    String  QRURL = "qrurl";//二维码名片地址

    String  HOMEPROVINCE = "homeprovince";//老家省
    String  HOMECITY = "homecity";//老家市
    String  HOMEAREA = "homearea";//老家县
    String  HOMETOWN = "homeaddress";//老家镇
    String  HOMEDETAILADDRESS = "homedetailaddress";//老家详细地址
    String  HOMEADDRESS = "hometown";//老家总地址

    String  NOWPROVINCE = "nowprovince";//现在省-数字
    String  NOWCITY = "nowcity";//现在市-数字
    String  NOWAREA = "nowarea";//现在县-数字
    String  NOWTOWN = "nowaddress";//现在镇-数字

    String  NOWPROVINCEC = "nowprovincec";//现在省-中文
    String  NOWCITYC = "nowcityc";//现在市-中文
    String  NOWAREAC = "nowareac";//现在县-中文
    String  NOWTOWNC = "nowaddressc";//现在镇-中文
    String  NOWDETAILADDRESS = "nowdetailaddress";//现在详细地址
    String  NOWADDRESS = "nowtown";//现在总地址


    String  COMPANY = "company";//公司名
    String  COMPANYID = "companyid";//公司id
    String  COMPANYLOGO = "companylogo";//公司logo
    String  FULLNAME = "fullname";//用户真名，用于企业架构中显示真名
    String  DEPARTMENT = "department";//部门
    String  JOB = "job";//工作岗位
    String  IDENTITY = "identity";//身份
    String  INDUSTRY = "industry";//行业
    String  INDUSTRYID = "industryid";//行业id
    String  COMPANYPROVINCE = "companyprovince";//公司省
    String  COMPANYCITY = "companycity";//公司市
    String  COMPANYAREA = "companyarea";//公司县
    String  COMPANYTOWN = "companyaddress";//公司镇
    String  COMPANYDETAILADDRESS = "companydetailaddress";//公司详细地址
    String  COMPANYADDRESS = "companytown";//公司总地址
    String  COMPANYWEBSITE = "companywebsite";//公司网址

    String  OFFERINFO = "offerinfo";//供-信息
    String  OFFERKEYWORDS = "offerkeywords";//供-关键词
    String  OFFERTYPE = "offertype";//供-类型
    String  DEMANDINFO = "demandinfo";//求-信息
    String  DEMANDKEYWORDS = "demandkeywords";//求-关键词
    String  DEMANDTYPE = "demandtype";//求-类型
    String  TOPINDUSTRY = "topindustry";//感兴趣的上游人脉
    String  TOPIDENTITY = "topidentity";//感兴趣的上游供应链身份
    String  BOTTOMINDUSTRY = "bottomindustry";//感兴趣的上游人脉
    String  BOTTOMIDENTITY = "bottomidentity";//感兴趣的上游供应链身份

    /***
     * 添加登录用户
     * @param user
     */
    void addUserEntity(LoginUser user);

    /***
     * 获取登录用户
     * @return Login
     */
    LoginUser getUserEntity();
}
