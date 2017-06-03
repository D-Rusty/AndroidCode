package com.hudong.wemedia.basiccomponent.bean;

/**
 * 作者: 方天文
 * 日期: 2017/3/23:上午11:50
 * 概况: 负责标签增，删，改操作
 */

public class LabelModifyParseBean {
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

    public static class MsgBean {
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

        private String labelid;
        private String labelname;
    }
}
