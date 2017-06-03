package com.hudong.wemedia.basiccomponent.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/12/3.
 */
public class Connections implements Parcelable {
//    Serializable

    private String id;
    private String realname;//用户名
    private String sex;//头像
    private String birth;//昵称
    private String mobile;//性别
    private String industry;//生日
    private String company;//电话
    private String department;//年龄
    private String job;//行业
    private String group_id;//组ID
    private String group_name;//组名
    private String lable_id;
    private String lable_name;
    private String lable_color;
    private String nowprovince;//省
    private String nowprovincec;//省
    private String nowcity;//市
    private String nowcityc;//市
    private String nowarea;//区
    private String nowtown;//镇
    private String companyaddress;//公司
    private String headimg;
    private String homeaddress;
    private String nowaddress;
    private String topidentity;
    private String bottomidentity;
    private String fullname;
    private String isFriend;//好友关系, 0:否, 1:是
    private String adurl;//广告链接
    private String isApplied;//是否已申请添加好友，1：已添加，0：未添加
    private String isRefused;//是否已拒绝好友，1：已拒绝，0：未拒绝
    private String isAccept;//是否已接受好友，1：已接受，0：未接受
    private String loginUserName;//登录用户id
    private String username;//对方用户名
    private String isSelected;//是否被选中，1：已选中，0：未选中
    private String isPhoneContact;//是否为手机通讯录联系人, 1:是，0：不是
    private String memberRecruit;//招聘
    private String memberSeek;//供
    private String memberfor;//求

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    private String special;

    public Connections(){}

    protected Connections(Parcel in) {
        id = in.readString();
        realname = in.readString();
        sex = in.readString();
        birth = in.readString();
        mobile = in.readString();
        industry = in.readString();
        company = in.readString();
        department = in.readString();
        job = in.readString();
        group_id = in.readString();
        group_name = in.readString();
        lable_id = in.readString();
        lable_name = in.readString();
        lable_color = in.readString();
        nowprovince = in.readString();
        nowprovincec = in.readString();
        nowcity = in.readString();
        nowcityc = in.readString();
        nowarea = in.readString();
        nowtown = in.readString();
        companyaddress = in.readString();
        headimg = in.readString();
        homeaddress = in.readString();
        nowaddress = in.readString();
        topidentity = in.readString();
        bottomidentity = in.readString();
        fullname = in.readString();
        isFriend = in.readString();
        adurl = in.readString();
        isApplied = in.readString();
        isRefused = in.readString();
        isAccept = in.readString();
        loginUserName = in.readString();
        username = in.readString();
        isSelected = in.readString();
        isPhoneContact = in.readString();
        memberRecruit = in.readString();
        memberSeek = in.readString();
        memberfor = in.readString();
    }



    @Override
    public String toString() {
        return "Connections{" +
                "id='" + id + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", mobile='" + mobile + '\'' +
                ", industry='" + industry + '\'' +
                ", company='" + company + '\'' +
                ", department='" + department + '\'' +
                ", job='" + job + '\'' +
                ", group_id='" + group_id + '\'' +
                ", group_name='" + group_name + '\'' +
                ", lable_id='" + lable_id + '\'' +
                ", lable_name='" + lable_name + '\'' +
                ", lable_color='" + lable_color + '\'' +
                ", nowprovince='" + nowprovince + '\'' +
                ", nowprovincec='" + nowprovincec + '\'' +
                ", nowcity='" + nowcity + '\'' +
                ", nowcityc='" + nowcityc + '\'' +
                ", nowarea='" + nowarea + '\'' +
                ", nowtown='" + nowtown + '\'' +
                ", companyaddress='" + companyaddress + '\'' +
                ", headimg='" + headimg + '\'' +
                ", homeaddress='" + homeaddress + '\'' +
                ", nowaddress='" + nowaddress + '\'' +
                ", topidentity='" + topidentity + '\'' +
                ", bottomidentity='" + bottomidentity + '\'' +
                ", fullname='" + fullname + '\'' +
                ", isFriend='" + isFriend + '\'' +
                ", adurl='" + adurl + '\'' +
                ", isApplied='" + isApplied + '\'' +
                ", isRefused='" + isRefused + '\'' +
                ", isAccept='" + isAccept + '\'' +
                ", loginUserName='" + loginUserName + '\'' +
                ", username='" + username + '\'' +
                ", isSelected='" + isSelected + '\'' +
                ", isPhoneContact='" + isPhoneContact + '\'' +
                ", memberRecruit='" + memberRecruit + '\'' +
                ", memberSeek='" + memberSeek + '\'' +
                ", memberfor='" + memberfor + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getLable_id() {
        return lable_id;
    }

    public void setLable_id(String lable_id) {
        this.lable_id = lable_id;
    }

    public String getLable_name() {
        return lable_name;
    }

    public void setLable_name(String lable_name) {
        this.lable_name = lable_name;
    }

    public String getLable_color() {
        return lable_color;
    }

    public void setLable_color(String lable_color) {
        this.lable_color = lable_color;
    }

    public String getNowprovince() {
        return nowprovince;
    }

    public void setNowprovince(String nowprovince) {
        this.nowprovince = nowprovince;
    }

    public String getNowprovincec() {
        return nowprovincec;
    }

    public void setNowprovincec(String nowprovincec) {
        this.nowprovincec = nowprovincec;
    }

    public String getNowcity() {
        return nowcity;
    }

    public void setNowcity(String nowcity) {
        this.nowcity = nowcity;
    }

    public String getNowcityc() {
        return nowcityc;
    }

    public void setNowcityc(String nowcityc) {
        this.nowcityc = nowcityc;
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

    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    public String getNowaddress() {
        return nowaddress;
    }

    public void setNowaddress(String nowaddress) {
        this.nowaddress = nowaddress;
    }

    public String getTopidentity() {
        return topidentity;
    }

    public void setTopidentity(String topidentity) {
        this.topidentity = topidentity;
    }

    public String getBottomidentity() {
        return bottomidentity;
    }

    public void setBottomidentity(String bottomidentity) {
        this.bottomidentity = bottomidentity;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(String isFriend) {
        this.isFriend = isFriend;
    }

    public String getAdurl() {
        return adurl;
    }

    public void setAdurl(String adurl) {
        this.adurl = adurl;
    }

    public String getIsApplied() {
        return isApplied;
    }

    public void setIsApplied(String isApplied) {
        this.isApplied = isApplied;
    }

    public String getIsRefused() {
        return isRefused;
    }

    public void setIsRefused(String isRefused) {
        this.isRefused = isRefused;
    }

    public String getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(String isAccept) {
        this.isAccept = isAccept;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    public String getIsPhoneContact() {
        return isPhoneContact;
    }

    public void setIsPhoneContact(String isPhoneContact) {
        this.isPhoneContact = isPhoneContact;
    }

    public String getMemberRecruit() {
        return memberRecruit;
    }

    public void setMemberRecruit(String memberRecruit) {
        this.memberRecruit = memberRecruit;
    }

    public String getMemberSeek() {
        return memberSeek;
    }

    public void setMemberSeek(String memberSeek) {
        this.memberSeek = memberSeek;
    }

    public String getMemberfor() {
        return memberfor;
    }

    public void setMemberfor(String memberfor) {
        this.memberfor = memberfor;
    }

    public Connections(String id, String realname, String sex, String birth, String mobile, String industry, String company, String department, String job, String group_id, String group_name, String lable_id, String lable_name, String lable_color, String nowprovince, String nowprovincec, String nowcity, String nowcityc, String nowarea, String nowtown, String companyaddress, String headimg, String homeaddress, String nowaddress, String topidentity, String bottomidentity, String fullname, String isFriend, String adurl, String isApplied, String isRefused, String isAccept, String loginUserName, String username, String isSelected, String isPhoneContact, String memberRecruit, String memberSeek, String memberfor) {

        this.id = id;
        this.realname = realname;
        this.sex = sex;
        this.birth = birth;
        this.mobile = mobile;
        this.industry = industry;
        this.company = company;
        this.department = department;
        this.job = job;
        this.group_id = group_id;
        this.group_name = group_name;
        this.lable_id = lable_id;
        this.lable_name = lable_name;
        this.lable_color = lable_color;
        this.nowprovince = nowprovince;
        this.nowprovincec = nowprovincec;
        this.nowcity = nowcity;
        this.nowcityc = nowcityc;
        this.nowarea = nowarea;
        this.nowtown = nowtown;
        this.companyaddress = companyaddress;
        this.headimg = headimg;
        this.homeaddress = homeaddress;
        this.nowaddress = nowaddress;
        this.topidentity = topidentity;
        this.bottomidentity = bottomidentity;
        this.fullname = fullname;
        this.isFriend = isFriend;
        this.adurl = adurl;
        this.isApplied = isApplied;
        this.isRefused = isRefused;
        this.isAccept = isAccept;
        this.loginUserName = loginUserName;
        this.username = username;
        this.isSelected = isSelected;
        this.isPhoneContact = isPhoneContact;
        this.memberRecruit = memberRecruit;
        this.memberSeek = memberSeek;
        this.memberfor = memberfor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(realname);
        dest.writeString(sex);
        dest.writeString(birth);
        dest.writeString(mobile);
        dest.writeString(industry);
        dest.writeString(company);
        dest.writeString(department);
        dest.writeString(job);
        dest.writeString(group_id);
        dest.writeString(group_name);
        dest.writeString(lable_id);
        dest.writeString(lable_name);
        dest.writeString(lable_color);
        dest.writeString(nowprovince);
        dest.writeString(nowprovincec);
        dest.writeString(nowcity);
        dest.writeString(nowcityc);
        dest.writeString(nowarea);
        dest.writeString(nowtown);
        dest.writeString(headimg);
        dest.writeString(homeaddress);
        dest.writeString(nowaddress);
        dest.writeString(topidentity);
        dest.writeString(bottomidentity);
        dest.writeString(fullname);
        dest.writeString(isFriend);
        dest.writeString(adurl);
        dest.writeString(isApplied);
        dest.writeString(isRefused);
        dest.writeString(isAccept);
        dest.writeString(loginUserName);
        dest.writeString(companyaddress);
        dest.writeString(username);
        dest.writeString(isSelected);
        dest.writeString(isPhoneContact);
        dest.writeString(memberRecruit);
        dest.writeString(memberSeek);
        dest.writeString(memberfor);

    }

    public Creator<Connections> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<Connections> CREATOR = new Creator<Connections>() {

        @Override
        public Connections[] newArray(int size) {
            return new Connections[size];
        }

        @Override
        public Connections createFromParcel(Parcel source) {
            return new Connections(source);
        }

    };




}
