package com.hudong.wemedia.basiccomponent.bean;

/**
 * 作者: 方天文
 * 日期: 2017/3/28:上午9:17
 * 概况:
 */

public class FriendGroupParseBean {

    /**
     * code : 1
     * msg : {"groupid":"1","friendid":"1092,1094","id":"1099"}
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

    public static class MsgBean implements ILabelParse{
        /**
         * groupid : 1
         * friendid : 1092,1094
         * id : 1099
         */

        private String groupid;
        private String friendid;
        private String id;

        public String getLableid() {
            return lableid;
        }

        public void setLableid(String lableid) {
            this.lableid = lableid;
        }

        private String lableid;
        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }

        public String getFriendid() {
            return friendid;
        }

        public void setFriendid(String friendid) {
            this.friendid = friendid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String getFriend() {
            return friendid;
        }
    }
}
