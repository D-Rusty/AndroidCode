package com.hudong.wemedia.basiccomponent.bean;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午9:24
 * 概况:
 */

public class ForcusCompanysParseBean {

    /**
     * code : 1
     * content : [{"id":"608","weid":"13","mid":"1156","name":"流云科技","telphone":"","logourl":"http://test2.wakebar.com/../addons/amouse_ecard/style/img/compdilogo.png","industry":"28","province":"11","city":"1102","area":"110229","town":"110229101","address":"","createtime":"1491810481","status":"1","companydetailaddress":"一路12区","companyintroduction":"APP定制开发","companytown":"北京市-县-延庆县","service_profile":"","phone":"400166211","email":"1646764634@qq.cim","weixin":"xdnsjdj","qq":"","shopurl":"www.liuyun.com","definedshopurl":"","shop_type":"0","rz":"0","adurl":"0","is_show_tel":"0","website":"","companyaddress":"北京市-县-延庆县","lastdynamic":"","industryc":"硬件开发","cityc":"县","friend":"1155,1153","incompany":"1"},{"id":"607","weid":"13","mid":"1153","name":"南通科技","telphone":"","logourl":"http://test2.wakebar.com/../addons/amouse_ecard/style/img/compdilogo.png","industry":"27","province":"44","city":"4403","area":"440305","town":"440305002","address":"","createtime":"1491809077","status":"1","companydetailaddress":"","companyintroduction":"软件定制，网站开发","companytown":"广东省-深圳市-南山区","service_profile":"","phone":"400-1222-6111","email":"44529197646@163.com","weixin":"nantong","qq":"","shopurl":"http://nantong.com","definedshopurl":"","shop_type":"0","rz":"0","adurl":"0","is_show_tel":"0","website":"","companyaddress":"广东省-深圳市-南山区","lastdynamic":"","industryc":"软件/互联网开发/系统集成","cityc":"深圳市","friend":"1153,1155","incompany":"1"}]
     * msg :
     * createtime  :
     */

    private String code;
    private String msg;
    private String createtime;
    private List<ContentBean> content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * id : 608
         * weid : 13
         * mid : 1156
         * name : 流云科技
         * telphone :
         * logourl : http://test2.wakebar.com/../addons/amouse_ecard/style/img/compdilogo.png
         * industry : 28
         * province : 11
         * city : 1102
         * area : 110229
         * town : 110229101
         * address :
         * createtime : 1491810481
         * status : 1
         * companydetailaddress : 一路12区
         * companyintroduction : APP定制开发
         * companytown : 北京市-县-延庆县
         * service_profile :
         * phone : 400166211
         * email : 1646764634@qq.cim
         * weixin : xdnsjdj
         * qq :
         * shopurl : www.liuyun.com
         * definedshopurl :
         * shop_type : 0
         * rz : 0
         * adurl : 0
         * is_show_tel : 0
         * website :
         * companyaddress : 北京市-县-延庆县
         * lastdynamic :
         * industryc : 硬件开发
         * cityc : 县
         * friend : 1155,1153
         * incompany : 1
         */

        private String id;
        private String weid;
        private String mid;
        private String name;
        private String telphone;
        private String logourl;
        private String industry;
        private String province;
        private String city;
        private String area;
        private String town;
        private String address;
        private String createtime;
        private String status;
        private String companydetailaddress;
        private String companyintroduction;
        private String companytown;
        private String service_profile;
        private String phone;
        private String email;
        private String weixin;
        private String qq;
        private String shopurl;
        private String definedshopurl;
        private String shop_type;
        private String rz;
        private String adurl;
        private String is_show_tel;
        private String website;
        private String companyaddress;
        private String lastdynamic;
        private String industryc;
        private String cityc;
        private String friend;
        private String incompany;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWeid() {
            return weid;
        }

        public void setWeid(String weid) {
            this.weid = weid;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTelphone() {
            return telphone;
        }

        public void setTelphone(String telphone) {
            this.telphone = telphone;
        }

        public String getLogourl() {
            return logourl;
        }

        public void setLogourl(String logourl) {
            this.logourl = logourl;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCompanydetailaddress() {
            return companydetailaddress;
        }

        public void setCompanydetailaddress(String companydetailaddress) {
            this.companydetailaddress = companydetailaddress;
        }

        public String getCompanyintroduction() {
            return companyintroduction;
        }

        public void setCompanyintroduction(String companyintroduction) {
            this.companyintroduction = companyintroduction;
        }

        public String getCompanytown() {
            return companytown;
        }

        public void setCompanytown(String companytown) {
            this.companytown = companytown;
        }

        public String getService_profile() {
            return service_profile;
        }

        public void setService_profile(String service_profile) {
            this.service_profile = service_profile;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getShopurl() {
            return shopurl;
        }

        public void setShopurl(String shopurl) {
            this.shopurl = shopurl;
        }

        public String getDefinedshopurl() {
            return definedshopurl;
        }

        public void setDefinedshopurl(String definedshopurl) {
            this.definedshopurl = definedshopurl;
        }

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }

        public String getRz() {
            return rz;
        }

        public void setRz(String rz) {
            this.rz = rz;
        }

        public String getAdurl() {
            return adurl;
        }

        public void setAdurl(String adurl) {
            this.adurl = adurl;
        }

        public String getIs_show_tel() {
            return is_show_tel;
        }

        public void setIs_show_tel(String is_show_tel) {
            this.is_show_tel = is_show_tel;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getCompanyaddress() {
            return companyaddress;
        }

        public void setCompanyaddress(String companyaddress) {
            this.companyaddress = companyaddress;
        }

        public String getLastdynamic() {
            return lastdynamic;
        }

        public void setLastdynamic(String lastdynamic) {
            this.lastdynamic = lastdynamic;
        }

        public String getIndustryc() {
            return industryc;
        }

        public void setIndustryc(String industryc) {
            this.industryc = industryc;
        }

        public String getCityc() {
            return cityc;
        }

        public void setCityc(String cityc) {
            this.cityc = cityc;
        }

        public String getFriend() {
            return friend;
        }

        public void setFriend(String friend) {
            this.friend = friend;
        }

        public String getIncompany() {
            return incompany;
        }

        public void setIncompany(String incompany) {
            this.incompany = incompany;
        }
    }
}

