package com.hudong.wemedia.businesscomponent.coremodel.db.connections;

import com.hudong.wemedia.basiccomponent.bean.Connections;
import com.hudong.wemedia.basiccomponent.bean.ConnectionsParseBean;
import com.hudong.wemedia.basiccomponent.bean.FriendGroupParseBean;
import com.hudong.wemedia.basiccomponent.bean.ILabelParse;
import com.hudong.wemedia.basiccomponent.bean.LabelAddParseBean;

import java.util.HashMap;
import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/14:下午3:07
 * 概况:
 */

public interface IConnectionsLocalDataSource {

    String TABLE_NAME = "connections";
    String ID = "id";
    String REALNAME = "realname";
    String SEX = "sex";
    String BIRTH = "birth";
    String MOBILE = "mobile";
    String COMPANY = "company";
    String INDUSTRY = "industry";
    String DEPARTMENT = "department";
    String COMPANY_ADDRESS = "company_address";
    String HEADIMG = "headimg";
    String HOME_ADDRESS = "home_address";
    String NOW_ADDRESS = "now_address";
    String TOP_IDENTITY = "top_identity";
    String BOTTOM_IDENTITY = "bottom_identity";
    String JOB = "job";
    String GROUP_ID = "group_id";//组ID
    String GROUP_NAME = "group_name";//组名
    String LABLE_ID = "lable_id";//标签ID
    String NOW_PROVINCE = "now_province";
    String NOW_PROVINCEC = "now_provincec";
    String NOW_CITY = "now_city";
    String NOW_CITYC = "now_cityc";
    String LOGIN_USERID = "login_userid";
    String MEMBER_RECRUIT = "memberrecruit";
    String MEMBER_SEEK = "memberseek";
    String MEMBER_FOR = "memberfor";
    String SPECIAL = "special";


    /**
     * @return int
     * @throws
     * @params: String deletefriendid
     * @date: 2017/3/16 下午2:11 <br>
     * @Description 删除联系人
     */
    int deleteConnetion(String deletefriendid);

    /**
     * @return Void
     * @throws
     * @params: List<ConnectionsParseBean.MsgBean.ContentBean>
     * @date: 2017/3/16 下午2:11 <br>
     * @Description 保存所有联系人表
     */
    void saveConnectionsList(List<ConnectionsParseBean.MsgBean.ContentBean> list);

    /**
     * @return List<Connections>
     * @throws
     * @params: Void
     * @date: 2017/3/16 下午2:11 <br>
     * @Description 查询所有联系人表
     */
    List<Connections> getConnectionsList();

    /**
     * 通过labelId获取标签下成员集合
     *
     * @param labelId
     * @return
     */
    List<Connections> findConnection(String labelId);

    /**
     * @return
     * @throws
     * @params: params 自媒圈id,姓名，行业名称，所在城市
     * @date: 2017/3/16 下午2:12 <br>
     * @Description
     */
    List<Connections> getConnectionsList(String params);

    /**
     * @return List<Connections>
     * @throws
     * @params: String city
     * @date: 2017/3/16 下午2:12 <br>
     * @Description 查询所在城市下的联系人表
     */
    List<Connections> getConnsListByCity(String city);

    /**
     * @return Connections
     * @throws
     * @params: String id
     * @date: 2017/3/16 下午2:12 <br>
     * @Description 通过Id查询联系人
     */
    Connections getConnectionsById(String id);

    /**
     * @return List<Connections>
     * @throws
     * @params: String groupId
     * @params: String labelId
     * @date: 2017/3/16 下午2:12 <br>
     * @Description 查询对应分组，对应标签下联系人
     */
    List<Connections> findGroupConnection(String groupId, String labelId);

    /**
     * 更新好友的特别关心
     *
     * @param connectionId
     * @param specialValue
     * @return
     */
    int updateSpecial(String connectionId, String specialValue);

    /**
     * 查询当前分组下联系人数量
     *
     * @param String groupName
     * @return List<Connections>
     */
    List<Connections> findGroupList(String groupName);

    /**
     * 查询当前分组下联系人信息
     *
     * @param String groupName
     * @return List<Connections>
     */
    HashMap<String, Integer> totalCloumSize(List<String> params);

    /**
     * 修改联系人标签只有在新增标签，或删除标签时出现
     */
    int updateConnectionLabel(String operation, ILabelParse labelParse);

    /**
     * @return boolean
     * @throws
     * @params: labelid, labelname, memberId
     * @date: 2017/3/23 下午2:32 <br>
     * @Description 修改用户分组以及标签信息
     */
    int updateConnectionGroupLabel(LabelAddParseBean.MsgBean bean);

    /**
     * @return boolean
     * @throws
     * @params: connectionId 以字符串形式隔开的用户id, labelId 标签id
     * @date: 2017/3/23 下午2:32 <br>
     * @Description 改变传入下用户id对应的标签id，标签名称
     */
    int modifyConnectionLabel(String labelId, String labelName, String connectionId);

    /**
     * @return boolean
     * @throws
     * @params: labelid, labelname, memberId
     * @date: 2017/3/23 下午2:32 <br>
     * @Description 修改用户分组
     */
    int updateConnectionGroup(FriendGroupParseBean bean);

    /**
     * 通过好友Id获取LabelId
     *
     * @param id
     * @return
     */
    String getConnectionLabelId(String id);

}
