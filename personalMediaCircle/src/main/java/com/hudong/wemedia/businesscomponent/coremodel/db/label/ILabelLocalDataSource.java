package com.hudong.wemedia.businesscomponent.coremodel.db.label;

import com.hudong.wemedia.basiccomponent.bean.LabelAddParseBean;
import com.hudong.wemedia.basiccomponent.bean.LabelDeleteParseBean;
import com.hudong.wemedia.basiccomponent.bean.LabelModifyParseBean;
import com.hudong.wemedia.basiccomponent.bean.LabelParseBean;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/14:下午2:50
 * 概况:
 */

public interface ILabelLocalDataSource {

    String TABLE_NAME = "label";
    String ID = "id";
    String MID = "mid"; //用户Id
    String LABELNAME = "label_name";//标签中文名
    String GROUPID = "groupid";//所在分组Id

    /**
     * @return Void
     * @throws
     * @params: List<GroupParseBean.MsgBean>  需新增分组的数组
     * @date: 2017/3/20 下午4:17 <br>
     * @Description 保存新建标签
     */
    void saveLabelList(List<LabelParseBean.MsgBean> labelList);

    /**
     * @return String
     * @throws
     * @params: String groupId 分组Id
     * @params: String labelName 标签名称
     * @date: 2017/3/20 下午4:17 <br>
     * @Description 获取标签id
     */
    String getLabelId(String groupId, String labelName);

    /**
     * @return List<LabelParseBean.MsgBean>
     * @throws
     * @params: String groupId   分组Id
     * @date: 2017/3/20 下午4:17 <br>
     * @Description 通过groupId返回 标签集合
     */
    String getLabelname(String labelId);

    /**
     * @return List<LabelParseBean.MsgBean>
     * @throws
     * @params: String groupId   分组Id
     * @date: 2017/3/20 下午4:17 <br>
     * @Description 通过groupId返回 标签集合
     */
    List<LabelParseBean.MsgBean> getLabelList(String groupId);

    /**
     * @return long
     * @throws
     * @params: LabelParse
     * @date: 2017/3/23 上午8:54 <br>
     * @Description 添加单个标签，不用于批量标签操作
     */
    long addLabel(LabelAddParseBean.MsgBean bean);

    /**
     * @return int
     * @throws
     * @params: String labelId 标签Id
     * @date: 2017/3/20 下午4:17 <br>
     * @Description 删除标签下的好友关系
     */
    int deleteLabelConnection(LabelDeleteParseBean.MsgBean bean);

    /**
     * @return Void
     * @throws
     * @params: LabelModifyParseBean.MsgBean 标签对象
     * @date: 2017/3/20 下午4:17 <br>
     * @Description 修改标签名称
     */
    void modifyLabel(LabelModifyParseBean.MsgBean bean);

    /**
     * @return int
     * @throws
     * @params: String labelId 标签id
     * @date: 2017/3/20 下午4:17 <br>
     * @Description 删除标签
     */
    int delteLabel(String labelId);

    /**
     * @return int
     * @throws
     * @params: String groupId 标签Id
     * @date: 2017/3/20 下午4:17 <br>
     * @Description 批量删除标签
     */
    int delteBatchLabel(String groupId);
}
