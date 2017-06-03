package com.hudong.wemedia.basiccomponent.bean;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/3/20:下午3:41
 * 概况:
 */

public class LabelParseBean {


    /**
     * code : 1
     * msg : [{"id":"1","mid":"0","labelname":"潜在客户","labelcolor":"111","groupid":"1"},{"id":"2","mid":"0","labelname":"成交客户","labelcolor":"222","groupid":"1"},{"id":"3","mid":"0","labelname":"放弃客户","labelcolor":"333","groupid":"1"},{"id":"4","mid":"0","labelname":"其他","labelcolor":"444","groupid":"1"},{"id":"11","mid":"1099","labelname":"kk","labelcolor":"55","groupid":"4"}]
     */

    private String code;
    private List<MsgBean> msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * id : 1
         * mid : 0
         * labelname : 潜在客户
         * labelcolor : 111
         * groupid : 1
         */

        private String id;
        private String mid;
        private String labelname;
        private String labelcolor;
        private String groupid;

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

        public String getLabelname() {
            return labelname;
        }

        public void setLabelname(String labelname) {
            this.labelname = labelname;
        }

        public String getLabelcolor() {
            return labelcolor;
        }

        public void setLabelcolor(String labelcolor) {
            this.labelcolor = labelcolor;
        }

        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }
    }
}
