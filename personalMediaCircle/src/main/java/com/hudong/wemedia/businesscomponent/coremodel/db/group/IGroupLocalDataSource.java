package com.hudong.wemedia.businesscomponent.coremodel.db.group;

import com.hudong.wemedia.basiccomponent.bean.GroupParseBean;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/14:下午2:33
 * 概况:
 */

public interface IGroupLocalDataSource {

    String TABLE_NAME = "mGroup";
    String NAME = "name";// 分组中文名
    String MID = "mid"; //当前登录用户Id
    String SORT = "sort";  //排序依据，新建分组该值统一为999，与服务器数据保持一致
    String ALIAS = "alias"; //分组别名默认为拼音
    String GROUPID = "groupid"; //分组别名默认为拼音

    /**
     * @return long
     * @throws
     * @params: List<GroupParseBean.MsgBean>  需新增分组的数组
     * @date: 2017/3/20 下午4:17 <br>
     * @Description 批量保存新建分组
     */
    long saveGroupList(List<GroupParseBean.MsgBean> groupList);

    /**
     * @return long
     * @throws
     * @params: List<GroupParseBean.MsgBean>  需新增分组的数组
     * @date: 2017/3/20 下午4:17 <br>
     * @Description 保存新建分组
     */
    long saveGroup(GroupParseBean.MsgBean groupBean);

    /**
     * @return int
     * @throws
     * @params: String groupOldName, String groupNewName
     * @params: String groupName 分组名称
     * @date: 2017/3/20 下午4:17 <br>
     * @Description修改分组
     */
    int modifyGroup(String groupOldName, String groupNewName);

    /**
     * @return int
     * @throws
     * @params: String groupName 分组名称
     * @date: 2017/3/20 下午4:17 <br>
     * @Description 删除分组
     */
    int deleteGroup(String groupName);

    /**
     * @return List<GroupParseBean.MsgBean>
     * @throws
     * @params: Void
     * @date: 2017/3/20 下午4:17 <br>
     * @Description 获取所有分组
     */
    List<GroupParseBean.MsgBean> getGroupList();

    /**
     * @return List<String>
     * @throws
     * @params: String group 默认其它分组是999
     * @date: 2017/3/21 上午10:58 <br>
     * @Description 分组中文名
     */
    List<String> getGroupNameList(String group);

    /**
     * @return List<GroupParseBean.MsgBean>
     * @throws
     * @params: Void
     * @date: 2017/3/20 下午5:57 <br>
     * @Description 获取其它分组名称
     */
    List<GroupParseBean.MsgBean> getOtherGroupList();

    /**
     * 通过别名查单个group
     */
    GroupParseBean.MsgBean getGroup(String alias);

    /**
     * @return Void
     * @throws
     * @params: String groupName 标签名称
     * @date: 2017/3/20 下午4:17 <br>
     * @Description 获取groupId
     */
    String getGroupId(String groupName);

    /**
     * @return String
     * @throws
     * @params: String groupId
     * @date: 2017/3/20 下午4:17 <br>
     * @Description 获取分组名称
     */
    String getGroupName(String groupId);
}
