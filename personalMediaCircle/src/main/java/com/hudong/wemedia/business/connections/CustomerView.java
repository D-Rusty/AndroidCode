package com.hudong.wemedia.business.connections;

import com.hudong.wemedia.basiccomponent.bean.LabelParseBean;

import java.util.List;

/**
 * Created by ck on 2017/4/15.
 */

public interface CustomerView {

    /**
     * 初始化数据
     * @param labelList
     */
    void initData(List<LabelParseBean.MsgBean> labelList);

    /**
     * 创建标签
     */
    void addLabel(String LabelId);

    /**
     * 删除标签
     */
    void delLabel(String LabelId);

    /**
     * 修改标签
     */
    void updateLabel(String LabelId);

    /**
     * 刷新界面
     */
    void refresh();
}
