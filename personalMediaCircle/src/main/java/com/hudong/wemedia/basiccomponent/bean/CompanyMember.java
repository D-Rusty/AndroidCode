package com.hudong.wemedia.basiccomponent.bean;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午8:59
 * 概况:
 */

public class CompanyMember {

    private String manage;
    private String departmentid;
    private String department;
    private String fullname;
    private String subfullname;
    private String departmentmanager;//是否是部门管理员  0.不是管理员  1.是管理员
    private String isSelected;//是否被选中，1：已选中，0：未选中
    private String id;
    private String headimg;
    private String name;
    private String mobile;
    private String company;
    private String job;
    private String province;
    private String city;
    private String area;
    private String town;
    private String label;
    private String is_meber;
    private String releasename;
    private String remarks;
    private String companyid;
    private String address;
    private String mid;
    private String friendid;
    private String realname;


    public CompanyMember() {

    }


    public CompanyMember(String manage, String departmentid, String department, String fullname, String subfullname, String departmentmanager, String isSelected, String id, String headimg, String name, String mobile, String company, String job, String province, String city, String area, String town, String label, String is_meber, String releasename, String remarks, String companyid, String address, String mid, String friendid, String realname) {
        this.manage = manage;
        this.departmentid = departmentid;
        this.department = department;
        this.fullname = fullname;
        this.subfullname = subfullname;
        this.departmentmanager = departmentmanager;
        this.isSelected = isSelected;
        this.id = id;
        this.headimg = headimg;
        this.name = name;
        this.mobile = mobile;
        this.company = company;
        this.job = job;
        this.province = province;
        this.city = city;
        this.area = area;
        this.town = town;
        this.label = label;
        this.is_meber = is_meber;
        this.releasename = releasename;
        this.remarks = remarks;
        this.companyid = companyid;
        this.address = address;
        this.mid = mid;
        this.friendid = friendid;
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "CompanyMember{" +
                "manage='" + manage + '\'' +
                ", departmentid='" + departmentid + '\'' +
                ", department='" + department + '\'' +
                ", fullname='" + fullname + '\'' +
                ", subfullname='" + subfullname + '\'' +
                ", departmentmanager='" + departmentmanager + '\'' +
                ", isSelected='" + isSelected + '\'' +
                ", id='" + id + '\'' +
                ", headimg='" + headimg + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", company='" + company + '\'' +
                ", job='" + job + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", town='" + town + '\'' +
                ", label='" + label + '\'' +
                ", is_meber='" + is_meber + '\'' +
                ", releasename='" + releasename + '\'' +
                ", remarks='" + remarks + '\'' +
                ", companyid='" + companyid + '\'' +
                ", address='" + address + '\'' +
                ", mid='" + mid + '\'' +
                ", friendid='" + friendid + '\'' +
                ", realname='" + realname + '\'' +
                '}';
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSubfullname() {
        return subfullname;
    }

    public void setSubfullname(String subfullname) {
        this.subfullname = subfullname;
    }

    public String getDepartmentmanager() {
        return departmentmanager;
    }

    public void setDepartmentmanager(String departmentmanager) {
        this.departmentmanager = departmentmanager;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIs_meber() {
        return is_meber;
    }

    public void setIs_meber(String is_meber) {
        this.is_meber = is_meber;
    }

    public String getReleasename() {
        return releasename;
    }

    public void setReleasename(String releasename) {
        this.releasename = releasename;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
