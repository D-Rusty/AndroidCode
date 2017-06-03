package com.hudong.wemedia.basiccomponent.bean;

import java.util.List;

/**
 * Created by dyj on 2017/3/15.
 * 用于解析 Connections数据表
 */

public class ConnectionsParseBean {

    /**
     * code : 1
     * msg : {"createtime":"984","content":[{"id":"1096","weid":"13","realname":"小白","sex":"2","birth":"","mobile":"601","industry":"摩托车配件","company":"创世纪科技有限公司","companywebsite":"","department":"总经办","job":"经理","identity":"","companyprovince":"44","companycity":"4403","companyarea":"440305","companytown":"440305002","companyaddress":"广东省深圳市南山区南山街道","qq":"","province":"","weixin":"","email":"","headimg":"http://test2.wakebar.com/../addons/amouse_ecard/style/img/426562114015385702.jpg","openid":"","suggestid":"0","myattention":"","myfocus":"","createtime":"1488763135","homeprovince":"36","homecity":"3604","homearea":"360428","hometown":"360428105","homeaddress":"江西省-九江市-都昌县-万户镇","nowprovince":"44","nowcity":"4403","nowarea":"440309","nowtown":"440309001","nowaddress":"广东省-深圳市-龙华新区-龙华街道","lat":"0.0000000000","lng":"0.0000000000","status":"0","qianming":"","ibeaconid":"","wxewmurl":"","integral":"","concernid":"","nowconcernid":"","username":"601","password":"111","supplyinfo":"","supplykeyword":"","supplytype":"0","demandinfo":"","demandkeyword":"","demandtype":"0","topindustry":"","topidentity":"","bottomindustry":"","bottomidentity":"","supply":"","parttime":"","interest":"","personalitynickname":"","fullname":"","homedetailaddress":"","nowdetailaddress":"","companydetailaddress":"","mobile_captcha":"0","mobile_captcha_send_time":"0000-00-00 00:00:00","unionid":"","compid":"572","companylogo":"../addons/amouse_ecard/style/img/compdilogo.png","companyid":"572","compandyindustry":"165","viptime":"0","industryid":"82","nowcityc":"深圳市","nowprovincec":"广东省","memberrecruit":[{"id":"8","info":"小方","createtime":"1488887580","mid":"1096","type":"商品"}],"memberseek":[{"id":"11","mid":"1096","info":"测试数据2","keyword":"路由","createtime":"1490753722","type":"商品"}],"memberfor":[{"id":"11","mid":"1096","info":"小曹","keyword":"人物","createtime":"1488887580","type":"商品"}],"groupname":"好友","groupid":"4","labelname":"","labelid":"","labelcolor":"","special":"0"}]}
     */

    private String code;
    private MsgBean msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * createtime : 984
         * content : [{"id":"1096","weid":"13","realname":"小白","sex":"2","birth":"","mobile":"601","industry":"摩托车配件","company":"创世纪科技有限公司","companywebsite":"","department":"总经办","job":"经理","identity":"","companyprovince":"44","companycity":"4403","companyarea":"440305","companytown":"440305002","companyaddress":"广东省深圳市南山区南山街道","qq":"","province":"","weixin":"","email":"","headimg":"http://test2.wakebar.com/../addons/amouse_ecard/style/img/426562114015385702.jpg","openid":"","suggestid":"0","myattention":"","myfocus":"","createtime":"1488763135","homeprovince":"36","homecity":"3604","homearea":"360428","hometown":"360428105","homeaddress":"江西省-九江市-都昌县-万户镇","nowprovince":"44","nowcity":"4403","nowarea":"440309","nowtown":"440309001","nowaddress":"广东省-深圳市-龙华新区-龙华街道","lat":"0.0000000000","lng":"0.0000000000","status":"0","qianming":"","ibeaconid":"","wxewmurl":"","integral":"","concernid":"","nowconcernid":"","username":"601","password":"111","supplyinfo":"","supplykeyword":"","supplytype":"0","demandinfo":"","demandkeyword":"","demandtype":"0","topindustry":"","topidentity":"","bottomindustry":"","bottomidentity":"","supply":"","parttime":"","interest":"","personalitynickname":"","fullname":"","homedetailaddress":"","nowdetailaddress":"","companydetailaddress":"","mobile_captcha":"0","mobile_captcha_send_time":"0000-00-00 00:00:00","unionid":"","compid":"572","companylogo":"../addons/amouse_ecard/style/img/compdilogo.png","companyid":"572","compandyindustry":"165","viptime":"0","industryid":"82","nowcityc":"深圳市","nowprovincec":"广东省","memberrecruit":[{"id":"8","info":"小方","createtime":"1488887580","mid":"1096","type":"商品"}],"memberseek":[{"id":"11","mid":"1096","info":"测试数据2","keyword":"路由","createtime":"1490753722","type":"商品"}],"memberfor":[{"id":"11","mid":"1096","info":"小曹","keyword":"人物","createtime":"1488887580","type":"商品"}],"groupname":"好友","groupid":"4","labelname":"","labelid":"","labelcolor":"","special":"0"}]
         */

        private String createtime;
        private List<ContentBean> content;

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
             * id : 1096
             * weid : 13
             * realname : 小白
             * sex : 2
             * birth :
             * mobile : 601
             * industry : 摩托车配件
             * company : 创世纪科技有限公司
             * companywebsite :
             * department : 总经办
             * job : 经理
             * identity :
             * companyprovince : 44
             * companycity : 4403
             * companyarea : 440305
             * companytown : 440305002
             * companyaddress : 广东省深圳市南山区南山街道
             * qq :
             * province :
             * weixin :
             * email :
             * headimg : http://test2.wakebar.com/../addons/amouse_ecard/style/img/426562114015385702.jpg
             * openid :
             * suggestid : 0
             * myattention :
             * myfocus :
             * createtime : 1488763135
             * homeprovince : 36
             * homecity : 3604
             * homearea : 360428
             * hometown : 360428105
             * homeaddress : 江西省-九江市-都昌县-万户镇
             * nowprovince : 44
             * nowcity : 4403
             * nowarea : 440309
             * nowtown : 440309001
             * nowaddress : 广东省-深圳市-龙华新区-龙华街道
             * lat : 0.0000000000
             * lng : 0.0000000000
             * status : 0
             * qianming :
             * ibeaconid :
             * wxewmurl :
             * integral :
             * concernid :
             * nowconcernid :
             * username : 601
             * password : 111
             * supplyinfo :
             * supplykeyword :
             * supplytype : 0
             * demandinfo :
             * demandkeyword :
             * demandtype : 0
             * topindustry :
             * topidentity :
             * bottomindustry :
             * bottomidentity :
             * supply :
             * parttime :
             * interest :
             * personalitynickname :
             * fullname :
             * homedetailaddress :
             * nowdetailaddress :
             * companydetailaddress :
             * mobile_captcha : 0
             * mobile_captcha_send_time : 0000-00-00 00:00:00
             * unionid :
             * compid : 572
             * companylogo : ../addons/amouse_ecard/style/img/compdilogo.png
             * companyid : 572
             * compandyindustry : 165
             * viptime : 0
             * industryid : 82
             * nowcityc : 深圳市
             * nowprovincec : 广东省
             * memberrecruit : [{"id":"8","info":"小方","createtime":"1488887580","mid":"1096","type":"商品"}]
             * memberseek : [{"id":"11","mid":"1096","info":"测试数据2","keyword":"路由","createtime":"1490753722","type":"商品"}]
             * memberfor : [{"id":"11","mid":"1096","info":"小曹","keyword":"人物","createtime":"1488887580","type":"商品"}]
             * groupname : 好友
             * groupid : 4
             * labelname :
             * labelid :
             * labelcolor :
             * special : 0
             */

            private String id;
            private String weid;
            private String realname;
            private String sex;
            private String birth;
            private String mobile;
            private String industry;
            private String company;
            private String companywebsite;
            private String department;
            private String job;
            private String identity;
            private String companyprovince;
            private String companycity;
            private String companyarea;
            private String companytown;
            private String companyaddress;
            private String qq;
            private String province;
            private String weixin;
            private String email;
            private String headimg;
            private String openid;
            private String suggestid;
            private String myattention;
            private String myfocus;
            private String createtime;
            private String homeprovince;
            private String homecity;
            private String homearea;
            private String hometown;
            private String homeaddress;
            private String nowprovince;
            private String nowcity;
            private String nowarea;
            private String nowtown;
            private String nowaddress;
            private String lat;
            private String lng;
            private String status;
            private String qianming;
            private String ibeaconid;
            private String wxewmurl;
            private String integral;
            private String concernid;
            private String nowconcernid;
            private String username;
            private String password;
            private String supplyinfo;
            private String supplykeyword;
            private String supplytype;
            private String demandinfo;
            private String demandkeyword;
            private String demandtype;
            private String topindustry;
            private String topidentity;
            private String bottomindustry;
            private String bottomidentity;
            private String supply;
            private String parttime;
            private String interest;
            private String personalitynickname;
            private String fullname;
            private String homedetailaddress;
            private String nowdetailaddress;
            private String companydetailaddress;
            private String mobile_captcha;
            private String mobile_captcha_send_time;
            private String unionid;
            private String compid;
            private String companylogo;
            private String companyid;
            private String compandyindustry;
            private String viptime;
            private String industryid;
            private String nowcityc;
            private String nowprovincec;
            private String groupname;
            private String groupid;
            private String labelname;
            private String labelid;
            private String labelcolor;
            private String special;
            private List<MemberrecruitBean> memberrecruit;
            private List<MemberseekBean> memberseek;
            private List<MemberforBean> memberfor;

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

            public String getCompanywebsite() {
                return companywebsite;
            }

            public void setCompanywebsite(String companywebsite) {
                this.companywebsite = companywebsite;
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

            public String getCompanyaddress() {
                return companyaddress;
            }

            public void setCompanyaddress(String companyaddress) {
                this.companyaddress = companyaddress;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getWeixin() {
                return weixin;
            }

            public void setWeixin(String weixin) {
                this.weixin = weixin;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getSuggestid() {
                return suggestid;
            }

            public void setSuggestid(String suggestid) {
                this.suggestid = suggestid;
            }

            public String getMyattention() {
                return myattention;
            }

            public void setMyattention(String myattention) {
                this.myattention = myattention;
            }

            public String getMyfocus() {
                return myfocus;
            }

            public void setMyfocus(String myfocus) {
                this.myfocus = myfocus;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
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

            public String getNowaddress() {
                return nowaddress;
            }

            public void setNowaddress(String nowaddress) {
                this.nowaddress = nowaddress;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getQianming() {
                return qianming;
            }

            public void setQianming(String qianming) {
                this.qianming = qianming;
            }

            public String getIbeaconid() {
                return ibeaconid;
            }

            public void setIbeaconid(String ibeaconid) {
                this.ibeaconid = ibeaconid;
            }

            public String getWxewmurl() {
                return wxewmurl;
            }

            public void setWxewmurl(String wxewmurl) {
                this.wxewmurl = wxewmurl;
            }

            public String getIntegral() {
                return integral;
            }

            public void setIntegral(String integral) {
                this.integral = integral;
            }

            public String getConcernid() {
                return concernid;
            }

            public void setConcernid(String concernid) {
                this.concernid = concernid;
            }

            public String getNowconcernid() {
                return nowconcernid;
            }

            public void setNowconcernid(String nowconcernid) {
                this.nowconcernid = nowconcernid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getSupplyinfo() {
                return supplyinfo;
            }

            public void setSupplyinfo(String supplyinfo) {
                this.supplyinfo = supplyinfo;
            }

            public String getSupplykeyword() {
                return supplykeyword;
            }

            public void setSupplykeyword(String supplykeyword) {
                this.supplykeyword = supplykeyword;
            }

            public String getSupplytype() {
                return supplytype;
            }

            public void setSupplytype(String supplytype) {
                this.supplytype = supplytype;
            }

            public String getDemandinfo() {
                return demandinfo;
            }

            public void setDemandinfo(String demandinfo) {
                this.demandinfo = demandinfo;
            }

            public String getDemandkeyword() {
                return demandkeyword;
            }

            public void setDemandkeyword(String demandkeyword) {
                this.demandkeyword = demandkeyword;
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

            public String getSupply() {
                return supply;
            }

            public void setSupply(String supply) {
                this.supply = supply;
            }

            public String getParttime() {
                return parttime;
            }

            public void setParttime(String parttime) {
                this.parttime = parttime;
            }

            public String getInterest() {
                return interest;
            }

            public void setInterest(String interest) {
                this.interest = interest;
            }

            public String getPersonalitynickname() {
                return personalitynickname;
            }

            public void setPersonalitynickname(String personalitynickname) {
                this.personalitynickname = personalitynickname;
            }

            public String getFullname() {
                return fullname;
            }

            public void setFullname(String fullname) {
                this.fullname = fullname;
            }

            public String getHomedetailaddress() {
                return homedetailaddress;
            }

            public void setHomedetailaddress(String homedetailaddress) {
                this.homedetailaddress = homedetailaddress;
            }

            public String getNowdetailaddress() {
                return nowdetailaddress;
            }

            public void setNowdetailaddress(String nowdetailaddress) {
                this.nowdetailaddress = nowdetailaddress;
            }

            public String getCompanydetailaddress() {
                return companydetailaddress;
            }

            public void setCompanydetailaddress(String companydetailaddress) {
                this.companydetailaddress = companydetailaddress;
            }

            public String getMobile_captcha() {
                return mobile_captcha;
            }

            public void setMobile_captcha(String mobile_captcha) {
                this.mobile_captcha = mobile_captcha;
            }

            public String getMobile_captcha_send_time() {
                return mobile_captcha_send_time;
            }

            public void setMobile_captcha_send_time(String mobile_captcha_send_time) {
                this.mobile_captcha_send_time = mobile_captcha_send_time;
            }

            public String getUnionid() {
                return unionid;
            }

            public void setUnionid(String unionid) {
                this.unionid = unionid;
            }

            public String getCompid() {
                return compid;
            }

            public void setCompid(String compid) {
                this.compid = compid;
            }

            public String getCompanylogo() {
                return companylogo;
            }

            public void setCompanylogo(String companylogo) {
                this.companylogo = companylogo;
            }

            public String getCompanyid() {
                return companyid;
            }

            public void setCompanyid(String companyid) {
                this.companyid = companyid;
            }

            public String getCompandyindustry() {
                return compandyindustry;
            }

            public void setCompandyindustry(String compandyindustry) {
                this.compandyindustry = compandyindustry;
            }

            public String getViptime() {
                return viptime;
            }

            public void setViptime(String viptime) {
                this.viptime = viptime;
            }

            public String getIndustryid() {
                return industryid;
            }

            public void setIndustryid(String industryid) {
                this.industryid = industryid;
            }

            public String getNowcityc() {
                return nowcityc;
            }

            public void setNowcityc(String nowcityc) {
                this.nowcityc = nowcityc;
            }

            public String getNowprovincec() {
                return nowprovincec;
            }

            public void setNowprovincec(String nowprovincec) {
                this.nowprovincec = nowprovincec;
            }

            public String getGroupname() {
                return groupname;
            }

            public void setGroupname(String groupname) {
                this.groupname = groupname;
            }

            public String getGroupid() {
                return groupid;
            }

            public void setGroupid(String groupid) {
                this.groupid = groupid;
            }

            public String getLabelname() {
                return labelname;
            }

            public void setLabelname(String labelname) {
                this.labelname = labelname;
            }

            public String getLabelid() {
                return labelid;
            }

            public void setLabelid(String labelid) {
                this.labelid = labelid;
            }

            public String getLabelcolor() {
                return labelcolor;
            }

            public void setLabelcolor(String labelcolor) {
                this.labelcolor = labelcolor;
            }

            public String getSpecial() {
                return special;
            }

            public void setSpecial(String special) {
                this.special = special;
            }

            public List<MemberrecruitBean> getMemberrecruit() {
                return memberrecruit;
            }

            public void setMemberrecruit(List<MemberrecruitBean> memberrecruit) {
                this.memberrecruit = memberrecruit;
            }

            public List<MemberseekBean> getMemberseek() {
                return memberseek;
            }

            public void setMemberseek(List<MemberseekBean> memberseek) {
                this.memberseek = memberseek;
            }

            public List<MemberforBean> getMemberfor() {
                return memberfor;
            }

            public void setMemberfor(List<MemberforBean> memberfor) {
                this.memberfor = memberfor;
            }

            public static class MemberrecruitBean {
                /**
                 * id : 8
                 * info : 小方
                 * createtime : 1488887580
                 * mid : 1096
                 * type : 商品
                 */

                private String id;
                private String info;
                private String createtime;
                private String mid;
                private String type;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }

            public static class MemberseekBean {
                /**
                 * id : 11
                 * mid : 1096
                 * info : 测试数据2
                 * keyword : 路由
                 * createtime : 1490753722
                 * type : 商品
                 */

                private String id;
                private String mid;
                private String info;
                private String keyword;
                private String createtime;
                private String type;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public String getKeyword() {
                    return keyword;
                }

                public void setKeyword(String keyword) {
                    this.keyword = keyword;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }

            public static class MemberforBean {
                /**
                 * id : 11
                 * mid : 1096
                 * info : 小曹
                 * keyword : 人物
                 * createtime : 1488887580
                 * type : 商品
                 */

                private String id;
                private String mid;
                private String info;
                private String keyword;
                private String createtime;
                private String type;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public String getKeyword() {
                    return keyword;
                }

                public void setKeyword(String keyword) {
                    this.keyword = keyword;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }
        }
    }
}