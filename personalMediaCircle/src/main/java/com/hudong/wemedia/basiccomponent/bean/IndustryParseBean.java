package com.hudong.wemedia.basiccomponent.bean;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午4:31
 * 概况:
 */

public class IndustryParseBean {

    /**
     * code : 1
     * content : [{"id":"1","title":"销售|客服|市场","status":"0","parentid":"0","displayorder":"0","is_hot":"1","logo":"../addons/amouse_ecard/style/img/jf.png"},{"id":"2","title":"财务|人力资源|行政","status":"0","parentid":"0","displayorder":"0","is_hot":"1","logo":"../addons/amouse_ecard/style/img/af.png"},{"id":"3","title":"项目|质量|高级管理","status":"0","parentid":"0","displayorder":"0","is_hot":"1","logo":"../addons/amouse_ecard/style/img/wlys.png"},{"id":"4","title":"IT|互联网|通信","status":"0","parentid":"0","displayorder":"0","is_hot":"1","logo":"../addons/amouse_ecard/style/img/jr.png"},{"id":"5","title":"房产|建筑|物业管理","status":"0","parentid":"0","displayorder":"0","is_hot":"1","logo":"../addons/amouse_ecard/style/img/jdsm.png"},{"id":"6","title":"金融","status":"0","parentid":"0","displayorder":"0","is_hot":"1","logo":"../addons/amouse_ecard/style/img/ly.png"},{"id":"7","title":"采购|贸易|交通|物流","status":"0","parentid":"0","displayorder":"0","is_hot":"1","logo":"../addons/amouse_ecard/style/img/fdc.png"},{"id":"8","title":"生产|制造","status":"0","parentid":"0","displayorder":"0","is_hot":"1","logo":"../addons/amouse_ecard/style/img/fdc.png"},{"id":"9","title":"传媒|印刷|艺术|设计","status":"0","parentid":"0","displayorder":"0","is_hot":"0","logo":"0"},{"id":"10","title":"咨询|法律|教育|翻译","status":"0","parentid":"0","displayorder":"0","is_hot":"0","logo":"0"},{"id":"11","title":"服务业","status":"0","parentid":"0","displayorder":"0","is_hot":"0","logo":"0"},{"id":"12","title":"能源|环保|农业|科研","status":"0","parentid":"0","displayorder":"0","is_hot":"0","logo":"0"},{"id":"13","title":"兼职|实习|社工|其他","status":"0","parentid":"0","displayorder":"0","is_hot":"0","logo":"0"},{"id":"14","title":"销售业务","status":"0","parentid":"1","displayorder":"0","is_hot":"0","logo":"0"},{"id":"15","title":"销售管理","status":"0","parentid":"1","displayorder":"0","is_hot":"0","logo":"0"},{"id":"16","title":"销售行政/商务","status":"0","parentid":"1","displayorder":"0","is_hot":"0","logo":"0"},{"id":"17","title":"客服/售前/售后技术支持","status":"0","parentid":"1","displayorder":"0","is_hot":"0","logo":"0"},{"id":"18","title":"市场","status":"0","parentid":"1","displayorder":"0","is_hot":"0","logo":"0"},{"id":"19","title":"公关/媒介","status":"0","parentid":"1","displayorder":"0","is_hot":"0","logo":"0"},{"id":"20","title":"广告/会展","status":"0","parentid":"1","displayorder":"0","is_hot":"0","logo":"0"},{"id":"21","title":"财务/审计/税务","status":"0","parentid":"2","displayorder":"0","is_hot":"0","logo":"0"},{"id":"22","title":"人力资源","status":"0","parentid":"2","displayorder":"0","is_hot":"0","logo":"0"},{"id":"23","title":"行政/后勤/文秘","status":"0","parentid":"2","displayorder":"0","is_hot":"0","logo":"0"},{"id":"24","title":"项目管理/项目协调","status":"0","parentid":"3","displayorder":"0","is_hot":"0","logo":"0"},{"id":"25","title":"质量管理/安全防护","status":"0","parentid":"3","displayorder":"0","is_hot":"0","logo":"0"},{"id":"26","title":"高级管理","status":"0","parentid":"3","displayorder":"0","is_hot":"0","logo":"0"},{"id":"27","title":"软件/互联网开发/系统集成","status":"0","parentid":"4","displayorder":"0","is_hot":"0","logo":"0"},{"id":"28","title":"硬件开发","status":"0","parentid":"4","displayorder":"0","is_hot":"0","logo":"0"},{"id":"29","title":"互联网产品/运营管理","status":"0","parentid":"4","displayorder":"0","is_hot":"0","logo":"0"},{"id":"30","title":"IT质量管理/测试/配置管理","status":"0","parentid":"4","displayorder":"0","is_hot":"0","logo":"0"},{"id":"31","title":"IT运维/技术支持","status":"0","parentid":"4","displayorder":"0","is_hot":"0","logo":"0"},{"id":"32","title":"IT管理/项目协调","status":"0","parentid":"4","displayorder":"0","is_hot":"0","logo":"0"},{"id":"33","title":"电信/通信技术开发及应用","status":"0","parentid":"4","displayorder":"0","is_hot":"0","logo":"0"},{"id":"34","title":"房地产开发/经纪/中介","status":"0","parentid":"5","displayorder":"0","is_hot":"0","logo":"0"},{"id":"35","title":"土木/建筑/装修/市政工程","status":"0","parentid":"5","displayorder":"0","is_hot":"0","logo":"0"},{"id":"36","title":"物业管理","status":"0","parentid":"5","displayorder":"0","is_hot":"0","logo":"0"},{"id":"37","title":"银行","status":"0","parentid":"6","displayorder":"0","is_hot":"0","logo":"0"},{"id":"38","title":"证券/期货/投资管理/服务","status":"0","parentid":"6","displayorder":"0","is_hot":"0","logo":"0"},{"id":"39","title":"保险","status":"0","parentid":"6","displayorder":"0","is_hot":"0","logo":"0"},{"id":"40","title":"信托/担保/拍卖/典当","status":"0","parentid":"6","displayorder":"0","is_hot":"0","logo":"0"},{"id":"41","title":"采购/贸易","status":"0","parentid":"7","displayorder":"0","is_hot":"0","logo":"0"},{"id":"42","title":"交通运输服务","status":"0","parentid":"7","displayorder":"0","is_hot":"0","logo":"0"},{"id":"43","title":"物流/仓储","status":"0","parentid":"7","displayorder":"0","is_hot":"0","logo":"0"},{"id":"44","title":"生产管理/运营","status":"0","parentid":"8","displayorder":"0","is_hot":"0","logo":"0"},{"id":"45","title":"电子/电器/半导体/仪器仪表","status":"0","parentid":"8","displayorder":"0","is_hot":"0","logo":"0"},{"id":"46","title":"汽车制造","status":"0","parentid":"8","displayorder":"0","is_hot":"0","logo":"0"},{"id":"47","title":"汽车销售与服务","status":"0","parentid":"8","displayorder":"0","is_hot":"0","logo":"0"},{"id":"48","title":"机械设计/制造/维修","status":"0","parentid":"8","displayorder":"0","is_hot":"0","logo":"0"},{"id":"49","title":"服装/纺织/皮革设计/生产","status":"0","parentid":"8","displayorder":"0","is_hot":"0","logo":"0"},{"id":"50","title":"技工/操作工","status":"0","parentid":"8","displayorder":"0","is_hot":"0","logo":"0"},{"id":"51","title":"生物/制药/医疗器械","status":"0","parentid":"8","displayorder":"0","is_hot":"0","logo":"0"},{"id":"52","title":"化工","status":"0","parentid":"8","displayorder":"0","is_hot":"0","logo":"0"},{"id":"53","title":"影视/媒体/出版/印刷","status":"0","parentid":"9","displayorder":"0","is_hot":"0","logo":"0"},{"id":"54","title":"艺术/设计","status":"0","parentid":"9","displayorder":"0","is_hot":"0","logo":"0"},{"id":"55","title":"咨询/顾问/调研/数据分析","status":"0","parentid":"10","displayorder":"0","is_hot":"0","logo":"0"},{"id":"56","title":"教育/培训","status":"0","parentid":"10","displayorder":"0","is_hot":"0","logo":"0"},{"id":"57","title":"律师/法务/合规","status":"0","parentid":"10","displayorder":"0","is_hot":"0","logo":"0"},{"id":"58","title":"翻译（口译与笔译）","status":"0","parentid":"10","displayorder":"0","is_hot":"0","logo":"0"},{"id":"59","title":"商超/酒店/娱乐管理/服务","status":"0","parentid":"11","displayorder":"0","is_hot":"0","logo":"0"},{"id":"60","title":"旅游/度假/出入境服务","status":"0","parentid":"11","displayorder":"0","is_hot":"0","logo":"0"},{"id":"61","title":"烹饪/料理/食品研发","status":"0","parentid":"11","displayorder":"0","is_hot":"0","logo":"0"},{"id":"62","title":"保健/美容/美发/健身","status":"0","parentid":"11","displayorder":"0","is_hot":"0","logo":"0"},{"id":"63","title":"医院/医疗/护理","status":"0","parentid":"11","displayorder":"0","is_hot":"0","logo":"0"},{"id":"64","title":"社区/居民/家政服务","status":"0","parentid":"11","displayorder":"0","is_hot":"0","logo":"0"},{"id":"65","title":"能源/矿产/地质勘查","status":"0","parentid":"12","displayorder":"0","is_hot":"0","logo":"0"},{"id":"66","title":"环境科学/环保","status":"0","parentid":"12","displayorder":"0","is_hot":"0","logo":"0"},{"id":"67","title":"农/林/牧/渔业","status":"0","parentid":"12","displayorder":"0","is_hot":"0","logo":"0"},{"id":"68","title":"公务员/事业单位/科研机构","status":"0","parentid":"12","displayorder":"0","is_hot":"0","logo":"0"},{"id":"69","title":"实习生/培训生/储备干部","status":"0","parentid":"13","displayorder":"0","is_hot":"0","logo":"0"},{"id":"70","title":"志愿者/社会工作者","status":"0","parentid":"13","displayorder":"0","is_hot":"0","logo":"0"},{"id":"71","title":"兼职/临时","status":"0","parentid":"13","displayorder":"0","is_hot":"0","logo":"0"},{"id":"72","title":"其他","status":"0","parentid":"13","displayorder":"0","is_hot":"0","logo":"0"}]
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
         * title : 销售|客服|市场
         * status : 0
         * parentid : 0
         * displayorder : 0
         * is_hot : 1
         * logo : ../addons/amouse_ecard/style/img/jf.png
         */

        private String id;
        private String title;
        private String status;
        private String parentid;
        private String displayorder;
        private String is_hot;
        private String logo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getDisplayorder() {
            return displayorder;
        }

        public void setDisplayorder(String displayorder) {
            this.displayorder = displayorder;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}

