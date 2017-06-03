package com.hudong.wemedia.basiccomponent.bean;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/3/20:下午3:41
 * 概况:
 */

public class GroupParseBean {
    /**
     * code : 1
     * msg : [{"id":"4","name":"好友","mid":"0","sort":"1","alias":"friends"},{"id":"3","name":"同事","mid":"0","sort":"2","alias":"colleague"},{"id":"2","name":"供应商","mid":"0","sort":"3","alias":"supplier"},{"id":"1","name":"客户","mid":"0","sort":"4","alias":"customer"},{"id":"8","name":"内奸分组","mid":"1099","sort":"9999","alias":null},{"id":"9","name":"内奸分组2","mid":"1099","sort":"9999","alias":null}]
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
         * id : 4
         * name : 好友
         * mid : 0
         * sort : 1
         * alias : friends
         */

        private String id;
        private String name;
        private String mid;
        private String sort;
        private String alias;

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

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }
    }
}
