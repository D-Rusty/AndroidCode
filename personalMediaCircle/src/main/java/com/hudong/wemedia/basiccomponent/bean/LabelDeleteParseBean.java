package com.hudong.wemedia.basiccomponent.bean;

/**
 * 作者: 方天文
 * 日期: 2017/3/23:上午11:50
 * 概况: 负责标签增，删，改操作
 */

public class LabelDeleteParseBean{
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MsgBean getMsgBean() {
        return msgBean;
    }

    public void setMagBean(MsgBean msgBean) {
        this.msgBean = msgBean;
    }

    private String code;
    private MsgBean msgBean;

    public static class MsgBean implements ILabelParse{
        private String deletelabelid;
        private String cureentlabelid;
        private String friendid;

        public String getDeletelabelid() {
            return deletelabelid;
        }

        public void setDeletelabelid(String deletelabelid) {
            this.deletelabelid = deletelabelid;
        }

        public String getCureentlabelid() {
            return cureentlabelid;
        }

        public void setCureentlabelid(String cureentlabelid) {
            this.cureentlabelid = cureentlabelid;
        }

        public String getFriendid() {
            return friendid;
        }

        public void setFriendid(String friendid) {
            this.friendid = friendid;
        }

        @Override
        public String getFriend() {
            return friendid;
        }
    }
}
