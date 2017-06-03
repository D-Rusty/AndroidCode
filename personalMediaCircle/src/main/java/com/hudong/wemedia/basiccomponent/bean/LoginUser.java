package com.hudong.wemedia.basiccomponent.bean;

import java.io.Serializable;

/**
 * Created by xiesuichao on 2016/11/11.
 * Modifyed by luofangzhi on 2016/12/02.
 */
public class LoginUser implements Serializable {

    //个人资料
    public String id;//用户ID
    public String username;//用户名
    public String realname;//用户昵称
    public String sex;//性别
    public String mobile;//手机号
    public String password;//用户密码
    public String headimg;//头像
    public String birth;//生日
    public String ewmurl;//二维码
    public String personalitynickname; //个性签名
    public String email;//邮箱
    public String wxewmurl;//微信二维码
    public String weixin;//微信号
    public String qq;//qq号
    public String lat;//经度
    public String lng;//纬度
    public String integral;//积分
    public String ibeaconid;//ibeacon设备id
    public String qrurl;//二维码地址
    public String imsig;//腾讯im登录签名

    public String homeprovince;//老家省
    public String homecity;//老家市
    public String homearea;//老家县
    public String hometown;//老家镇
    public String homedetailaddress;//老家详细地址
    public String homeaddress;//老家总地址

    public String nowprovince;//现在省-数字
    public String nowcity;//现在市-数字
    public String nowarea;//现在县-数字
    public String nowtown;//现在镇-数字
    public String nowdetailaddress;//现在详细地址
    public String nowaddress;//现在总地址

    public String nowprovincec;//现在省-中文
    public String nowcityc;//现在市-中文
    public String nowareac;//现在县-中文
    public String nowtownc;//现在镇-中文


    //公司资料
    public String company;//公司名
    public String companyid;//公司id
    public String companylogo;//公司logo
    public String fullname;//用户真名，用于企业架构中显示真名
    public String department;//部门
    public String job;//工作岗位
    public String identity;//身份
    public String industry;//行业
    public String industryid;//行业id

    public String companyprovince;//公司省
    public String companycity;//公司市
    public String companyarea;//公司县
    public String companytown;//公司镇
    public String companydetailaddress;//公司详细地址
    public String companyaddress;//公司总地址
    public String companywebsite;//公司网址

    //商业信息
    public String offerinfo;//供-信息
    public String offerkeywords;//供-关键词
    public String offertype;//供-类型
    public String demandinfo;//求-信息
    public String demandkeywords;//求-关键词
    public String demandtype;//求-类型
    public String topindustry;//感兴趣的上游人脉
    public String topidentity;//感兴趣的上游供应链身份
    public String bottomindustry;//感兴趣的上游人脉
    public String bottomidentity;//感兴趣的上游供应链身份

    public LoginUser() {
    }


    public LoginUser(String id, String username, String realname, String sex, String mobile, String password, String headimg, String birth, String ewmurl, String personalitynickname, String email, String wxewmurl, String weixin, String qq, String lat, String lng, String integral, String ibeaconid, String qrurl, String imsig, String homeprovince, String homecity, String homearea, String hometown, String homedetailaddress, String homeaddress, String nowprovince, String nowcity, String nowarea, String nowtown, String nowdetailaddress, String nowaddress, String nowprovincec, String nowcityc, String nowareac, String nowtownc, String company, String companyid, String companylogo, String fullname, String department, String job, String identity, String industry, String industryid, String companyprovince, String companycity, String companyarea, String companytown, String companydetailaddress, String companyaddress, String companywebsite, String offerinfo, String offerkeywords, String offertype, String demandinfo, String demandkeywords, String demandtype, String topindustry, String topidentity, String bottomindustry, String bottomidentity) {
        this.id = id;
        this.username = username;
        this.realname = realname;
        this.sex = sex;
        this.mobile = mobile;
        this.password = password;
        this.headimg = headimg;
        this.birth = birth;
        this.ewmurl = ewmurl;
        this.personalitynickname = personalitynickname;
        this.email = email;
        this.wxewmurl = wxewmurl;
        this.weixin = weixin;
        this.qq = qq;
        this.lat = lat;
        this.lng = lng;
        this.integral = integral;
        this.ibeaconid = ibeaconid;
        this.qrurl = qrurl;
        this.imsig = imsig;
        this.homeprovince = homeprovince;
        this.homecity = homecity;
        this.homearea = homearea;
        this.hometown = hometown;
        this.homedetailaddress = homedetailaddress;
        this.homeaddress = homeaddress;
        this.nowprovince = nowprovince;
        this.nowcity = nowcity;
        this.nowarea = nowarea;
        this.nowtown = nowtown;
        this.nowdetailaddress = nowdetailaddress;
        this.nowaddress = nowaddress;
        this.nowprovincec = nowprovincec;
        this.nowcityc = nowcityc;
        this.nowareac = nowareac;
        this.nowtownc = nowtownc;
        this.company = company;
        this.companyid = companyid;
        this.companylogo = companylogo;
        this.fullname = fullname;
        this.department = department;
        this.job = job;
        this.identity = identity;
        this.industry = industry;
        this.industryid = industryid;
        this.companyprovince = companyprovince;
        this.companycity = companycity;
        this.companyarea = companyarea;
        this.companytown = companytown;
        this.companydetailaddress = companydetailaddress;
        this.companyaddress = companyaddress;
        this.companywebsite = companywebsite;
        this.offerinfo = offerinfo;
        this.offerkeywords = offerkeywords;
        this.offertype = offertype;
        this.demandinfo = demandinfo;
        this.demandkeywords = demandkeywords;
        this.demandtype = demandtype;
        this.topindustry = topindustry;
        this.topidentity = topidentity;
        this.bottomindustry = bottomindustry;
        this.bottomidentity = bottomidentity;
    }


    @Override
    public String toString() {
        return "LoginUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", headimg='" + headimg + '\'' +
                ", birth='" + birth + '\'' +
                ", ewmurl='" + ewmurl + '\'' +
                ", personalitynickname='" + personalitynickname + '\'' +
                ", email='" + email + '\'' +
                ", wxewmurl='" + wxewmurl + '\'' +
                ", weixin='" + weixin + '\'' +
                ", qq='" + qq + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", integral='" + integral + '\'' +
                ", ibeaconid='" + ibeaconid + '\'' +
                ", qrurl='" + qrurl + '\'' +
                ", imsig='" + imsig + '\'' +
                ", homeprovince='" + homeprovince + '\'' +
                ", homecity='" + homecity + '\'' +
                ", homearea='" + homearea + '\'' +
                ", hometown='" + hometown + '\'' +
                ", homedetailaddress='" + homedetailaddress + '\'' +
                ", homeaddress='" + homeaddress + '\'' +
                ", nowprovince='" + nowprovince + '\'' +
                ", nowcity='" + nowcity + '\'' +
                ", nowarea='" + nowarea + '\'' +
                ", nowtown='" + nowtown + '\'' +
                ", nowdetailaddress='" + nowdetailaddress + '\'' +
                ", nowaddress='" + nowaddress + '\'' +
                ", nowprovincec='" + nowprovincec + '\'' +
                ", nowcityc='" + nowcityc + '\'' +
                ", nowareac='" + nowareac + '\'' +
                ", nowtownc='" + nowtownc + '\'' +
                ", company='" + company + '\'' +
                ", companyid='" + companyid + '\'' +
                ", companylogo='" + companylogo + '\'' +
                ", fullname='" + fullname + '\'' +
                ", department='" + department + '\'' +
                ", job='" + job + '\'' +
                ", identity='" + identity + '\'' +
                ", industry='" + industry + '\'' +
                ", industryid='" + industryid + '\'' +
                ", companyprovince='" + companyprovince + '\'' +
                ", companycity='" + companycity + '\'' +
                ", companyarea='" + companyarea + '\'' +
                ", companytown='" + companytown + '\'' +
                ", companydetailaddress='" + companydetailaddress + '\'' +
                ", companyaddress='" + companyaddress + '\'' +
                ", companywebsite='" + companywebsite + '\'' +
                ", offerinfo='" + offerinfo + '\'' +
                ", offerkeywords='" + offerkeywords + '\'' +
                ", offertype='" + offertype + '\'' +
                ", demandinfo='" + demandinfo + '\'' +
                ", demandkeywords='" + demandkeywords + '\'' +
                ", demandtype='" + demandtype + '\'' +
                ", topindustry='" + topindustry + '\'' +
                ", topidentity='" + topidentity + '\'' +
                ", bottomindustry='" + bottomindustry + '\'' +
                ", bottomidentity='" + bottomidentity + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEwmurl() {
        return ewmurl;
    }

    public void setEwmurl(String ewmurl) {
        this.ewmurl = ewmurl;
    }

    public String getPersonalitynickname() {
        return personalitynickname;
    }

    public void setPersonalitynickname(String personalitynickname) {
        this.personalitynickname = personalitynickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWxewmurl() {
        return wxewmurl;
    }

    public void setWxewmurl(String wxewmurl) {
        this.wxewmurl = wxewmurl;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getIbeaconid() {
        return ibeaconid;
    }

    public void setIbeaconid(String ibeaconid) {
        this.ibeaconid = ibeaconid;
    }

    public String getQrurl() {
        return qrurl;
    }

    public void setQrurl(String qrurl) {
        this.qrurl = qrurl;
    }

    public String getImsig() {
        return imsig;
    }

    public void setImsig(String imsig) {
        this.imsig = imsig;
    }

    public String getHomeprovince() {
        return homeprovince;
    }

    public void setHomeprovince(String homeprovince) {
        this.homeprovince = homeprovince;
    }

    public String getHomecity() {
        return homecity;
    }

    public void setHomecity(String homecity) {
        this.homecity = homecity;
    }

    public String getHomearea() {
        return homearea;
    }

    public void setHomearea(String homearea) {
        this.homearea = homearea;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getHomedetailaddress() {
        return homedetailaddress;
    }

    public void setHomedetailaddress(String homedetailaddress) {
        this.homedetailaddress = homedetailaddress;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    public String getNowprovince() {
        return nowprovince;
    }

    public void setNowprovince(String nowprovince) {
        this.nowprovince = nowprovince;
    }

    public String getNowcity() {
        return nowcity;
    }

    public void setNowcity(String nowcity) {
        this.nowcity = nowcity;
    }

    public String getNowarea() {
        return nowarea;
    }

    public void setNowarea(String nowarea) {
        this.nowarea = nowarea;
    }

    public String getNowtown() {
        return nowtown;
    }

    public void setNowtown(String nowtown) {
        this.nowtown = nowtown;
    }

    public String getNowdetailaddress() {
        return nowdetailaddress;
    }

    public void setNowdetailaddress(String nowdetailaddress) {
        this.nowdetailaddress = nowdetailaddress;
    }

    public String getNowaddress() {
        return nowaddress;
    }

    public void setNowaddress(String nowaddress) {
        this.nowaddress = nowaddress;
    }

    public String getNowprovincec() {
        return nowprovincec;
    }

    public void setNowprovincec(String nowprovincec) {
        this.nowprovincec = nowprovincec;
    }

    public String getNowcityc() {
        return nowcityc;
    }

    public void setNowcityc(String nowcityc) {
        this.nowcityc = nowcityc;
    }

    public String getNowareac() {
        return nowareac;
    }

    public void setNowareac(String nowareac) {
        this.nowareac = nowareac;
    }

    public String getNowtownc() {
        return nowtownc;
    }

    public void setNowtownc(String nowtownc) {
        this.nowtownc = nowtownc;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getCompanylogo() {
        return companylogo;
    }

    public void setCompanylogo(String companylogo) {
        this.companylogo = companylogo;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustryid() {
        return industryid;
    }

    public void setIndustryid(String industryid) {
        this.industryid = industryid;
    }

    public String getCompanyprovince() {
        return companyprovince;
    }

    public void setCompanyprovince(String companyprovince) {
        this.companyprovince = companyprovince;
    }

    public String getCompanycity() {
        return companycity;
    }

    public void setCompanycity(String companycity) {
        this.companycity = companycity;
    }

    public String getCompanyarea() {
        return companyarea;
    }

    public void setCompanyarea(String companyarea) {
        this.companyarea = companyarea;
    }

    public String getCompanytown() {
        return companytown;
    }

    public void setCompanytown(String companytown) {
        this.companytown = companytown;
    }

    public String getCompanydetailaddress() {
        return companydetailaddress;
    }

    public void setCompanydetailaddress(String companydetailaddress) {
        this.companydetailaddress = companydetailaddress;
    }

    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

    public String getCompanywebsite() {
        return companywebsite;
    }

    public void setCompanywebsite(String companywebsite) {
        this.companywebsite = companywebsite;
    }

    public String getOfferinfo() {
        return offerinfo;
    }

    public void setOfferinfo(String offerinfo) {
        this.offerinfo = offerinfo;
    }

    public String getOfferkeywords() {
        return offerkeywords;
    }

    public void setOfferkeywords(String offerkeywords) {
        this.offerkeywords = offerkeywords;
    }

    public String getOffertype() {
        return offertype;
    }

    public void setOffertype(String offertype) {
        this.offertype = offertype;
    }

    public String getDemandinfo() {
        return demandinfo;
    }

    public void setDemandinfo(String demandinfo) {
        this.demandinfo = demandinfo;
    }

    public String getDemandkeywords() {
        return demandkeywords;
    }

    public void setDemandkeywords(String demandkeywords) {
        this.demandkeywords = demandkeywords;
    }

    public String getDemandtype() {
        return demandtype;
    }

    public void setDemandtype(String demandtype) {
        this.demandtype = demandtype;
    }

    public String getTopindustry() {
        return topindustry;
    }

    public void setTopindustry(String topindustry) {
        this.topindustry = topindustry;
    }

    public String getTopidentity() {
        return topidentity;
    }

    public void setTopidentity(String topidentity) {
        this.topidentity = topidentity;
    }

    public String getBottomindustry() {
        return bottomindustry;
    }

    public void setBottomindustry(String bottomindustry) {
        this.bottomindustry = bottomindustry;
    }

    public String getBottomidentity() {
        return bottomidentity;
    }

    public void setBottomidentity(String bottomidentity) {
        this.bottomidentity = bottomidentity;
    }
}


