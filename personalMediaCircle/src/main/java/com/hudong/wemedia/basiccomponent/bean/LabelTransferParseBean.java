package com.hudong.wemedia.basiccomponent.bean;

/**
 * 作者: 方天文
 * 日期: 2017/3/23:上午11:50
 * 概况: 负责表改操作
 */

public class LabelTransferParseBean {

    /**
     * code : 1
     * msg : {"labelid":"116","labelname":"和4","friendid":"1122,1123,1092","editfriendid":"1096,1101","defaultlabelid":"0"}
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
         * labelid : 116
         * labelname : 和4
         * friendid : 1122,1123,1092
         * editfriendid : 1096,1101
         * defaultlabelid : 0
         */

        private String labelid;
        private String labelname;
        private String friendid;
        private String editfriendid;
        private String defaultlabelid;

        public String getLabelid() {
            return labelid;
        }

        public void setLabelid(String labelid) {
            this.labelid = labelid;
        }

        public String getLabelname() {
            return labelname;
        }

        public void setLabelname(String labelname) {
            this.labelname = labelname;
        }

        public String getFriendid() {
            return friendid;
        }

        public void setFriendid(String friendid) {
            this.friendid = friendid;
        }

        public String getEditfriendid() {
            return editfriendid;
        }

        public void setEditfriendid(String editfriendid) {
            this.editfriendid = editfriendid;
        }

        public String getDefaultlabelid() {
            return defaultlabelid;
        }

        public void setDefaultlabelid(String defaultlabelid) {
            this.defaultlabelid = defaultlabelid;
        }

        @Override
        public String getFriend() {
            return friendid;
        }
    }
}
