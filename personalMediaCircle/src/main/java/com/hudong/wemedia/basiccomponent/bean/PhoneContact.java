package com.hudong.wemedia.basiccomponent.bean;

import java.io.Serializable;

/**
 * 手机联系人
 */
public class PhoneContact
        implements Serializable {

    private String id;
    private String name;
    private String mobileNum;
    private String isSelected;
    private String subname;

    public PhoneContact() {
    }

    public PhoneContact(String id, String name, String mobileNum, String isSelected, String subname) {
        this.id = id;
        this.name = name;
        this.mobileNum = mobileNum;
        this.isSelected = isSelected;
        this.subname = subname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    @Override
    public String toString() {
        return "PhoneContact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mobileNum='" + mobileNum + '\'' +
                ", isSelected='" + isSelected + '\'' +
                ", subname='" + subname + '\'' +
                '}';
    }

    /**
     * 作者: 方天文
     * 日期: 2017/4/23:下午4:08
     * 概况:
     */

    public static class OuterMember {
        private String id;
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

        public OuterMember() {
        }

        public OuterMember(String id, String name, String mobile, String company, String job, String province, String city, String area, String town, String label, String is_meber, String releasename, String remarks, String companyid, String address, String mid, String friendid) {
            this.id = id;
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
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        @Override
        public String toString() {
            return "OuterMember{" +
                    "id='" + id + '\'' +
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
                    '}';
        }
    }
}
