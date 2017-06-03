package com.hudong.wemedia.basiccomponent.bean;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/3/16:上午9:16
 * 概况: 圈子实体类
 */

public class CircleBean {

    /**
     * code : 1
     * content : [{"id":"75","mid":"1145","weid":"13","type":"0","group_name":"坂田资源交流群","group_info":"坂田资源交流群,资源分享","group_qr":"http://test2.wakebar.com/../addons/amouse_ecard/data/13_quncode_75.png","create_time":"1492427503","total":"1","qunimg":"","areacode":"0","industry":"25","xingquid":"","appgroupid":"","typec":"行业群","industryc":"质量管理/安全防护","xingquidc":"","qunActivitynum":78}]
     * msg :
     * createtime :
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
         * id : 75
         * mid : 1145
         * weid : 13
         * type : 0
         * group_name : 坂田资源交流群
         * group_info : 坂田资源交流群,资源分享
         * group_qr : http://test2.wakebar.com/../addons/amouse_ecard/data/13_quncode_75.png
         * create_time : 1492427503
         * total : 1
         * qunimg :
         * areacode : 0
         * industry : 25
         * xingquid :
         * appgroupid :
         * typec : 行业群
         * industryc : 质量管理/安全防护
         * xingquidc :
         * qunActivitynum : 78
         */

        private String id;
        private String mid;
        private String weid;
        private String type;
        private String group_name;
        private String group_info;
        private String group_qr;
        private String create_time;
        private String total;
        private String qunimg;
        private String areacode;
        private String industry;
        private String xingquid;
        private String appgroupid;
        private String typec;
        private String industryc;
        private String xingquidc;
        private int qunActivitynum;

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

        public String getWeid() {
            return weid;
        }

        public void setWeid(String weid) {
            this.weid = weid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getGroup_info() {
            return group_info;
        }

        public void setGroup_info(String group_info) {
            this.group_info = group_info;
        }

        public String getGroup_qr() {
            return group_qr;
        }

        public void setGroup_qr(String group_qr) {
            this.group_qr = group_qr;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getQunimg() {
            return qunimg;
        }

        public void setQunimg(String qunimg) {
            this.qunimg = qunimg;
        }

        public String getAreacode() {
            return areacode;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getXingquid() {
            return xingquid;
        }

        public void setXingquid(String xingquid) {
            this.xingquid = xingquid;
        }

        public String getAppgroupid() {
            return appgroupid;
        }

        public void setAppgroupid(String appgroupid) {
            this.appgroupid = appgroupid;
        }

        public String getTypec() {
            return typec;
        }

        public void setTypec(String typec) {
            this.typec = typec;
        }

        public String getIndustryc() {
            return industryc;
        }

        public void setIndustryc(String industryc) {
            this.industryc = industryc;
        }

        public String getXingquidc() {
            return xingquidc;
        }

        public void setXingquidc(String xingquidc) {
            this.xingquidc = xingquidc;
        }

        public int getQunActivitynum() {
            return qunActivitynum;
        }

        public void setQunActivitynum(int qunActivitynum) {
            this.qunActivitynum = qunActivitynum;
        }
    }
}
