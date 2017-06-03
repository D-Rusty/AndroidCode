package com.hudong.wemedia.basiccomponent.bean;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午8:35
 * 概况: 地址类实体类
 */

public class AddrBean {

    /**
     * code : 1
     * content : [{"id":"1","code":"11","parent_id":"0","name":"北京市","level":"1"},{"id":"338","code":"12","parent_id":"0","name":"天津市","level":"1"},{"id":"636","code":"13","parent_id":"0","name":"河北省","level":"1"},{"id":"3102","code":"14","parent_id":"0","name":"山西省","level":"1"},{"id":"4670","code":"15","parent_id":"0","name":"内蒙古自治区","level":"1"},{"id":"5827","code":"21","parent_id":"0","name":"辽宁省","level":"1"},{"id":"7531","code":"22","parent_id":"0","name":"吉林省","level":"1"},{"id":"8558","code":"23","parent_id":"0","name":"黑龙江省","level":"1"},{"id":"10543","code":"31","parent_id":"0","name":"上海市","level":"1"},{"id":"10808","code":"32","parent_id":"0","name":"江苏省","level":"1"},{"id":"12596","code":"33","parent_id":"0","name":"浙江省","level":"1"},{"id":"14234","code":"34","parent_id":"0","name":"安徽省","level":"1"},{"id":"16068","code":"35","parent_id":"0","name":"福建省","level":"1"},{"id":"17359","code":"36","parent_id":"0","name":"江西省","level":"1"},{"id":"19280","code":"37","parent_id":"0","name":"山东省","level":"1"},{"id":"21387","code":"41","parent_id":"0","name":"河南省","level":"1"},{"id":"24022","code":"42","parent_id":"0","name":"湖北省","level":"1"},{"id":"25579","code":"43","parent_id":"0","name":"湖南省","level":"1"},{"id":"28240","code":"44","parent_id":"0","name":"广东省","level":"1"},{"id":"30168","code":"45","parent_id":"0","name":"广西壮族自治区","level":"1"},{"id":"31567","code":"46","parent_id":"0","name":"海南省","level":"1"},{"id":"31933","code":"50","parent_id":"0","name":"重庆市","level":"1"},{"id":"33011","code":"51","parent_id":"0","name":"四川省","level":"1"},{"id":"37910","code":"52","parent_id":"0","name":"贵州省","level":"1"},{"id":"39560","code":"53","parent_id":"0","name":"云南省","level":"1"},{"id":"41107","code":"54","parent_id":"0","name":"西藏自治区","level":"1"},{"id":"41881","code":"61","parent_id":"0","name":"陕西省","level":"1"},{"id":"43780","code":"62","parent_id":"0","name":"甘肃省","level":"1"},{"id":"45290","code":"63","parent_id":"0","name":"青海省","level":"1"},{"id":"45757","code":"64","parent_id":"0","name":"宁夏回族自治区","level":"1"},{"id":"46051","code":"65","parent_id":"0","name":"新疆维吾尔自治区","level":"1"},{"id":"47497","code":"71","parent_id":"0","name":"台湾省","level":"1"},{"id":"47498","code":"81","parent_id":"0","name":"香港特别行政区","level":"1"},{"id":"47499","code":"82","parent_id":"0","name":"澳门特别行政区","level":"1"}]
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
         * id : 1
         * code : 11
         * parent_id : 0
         * name : 北京市
         * level : 1
         */

        private String id;
        private String code;
        private String parent_id;
        private String name;
        private String level;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}

