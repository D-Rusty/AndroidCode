package com.hudong.wemedia.businesscomponent.coremodel.db.groupmember;

import com.hudong.wemedia.basiccomponent.bean.Connections;
import com.hudong.wemedia.basiccomponent.bean.GroupMember;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/23:下午4:51
 * 概况:
 */

public interface IGroupMemberLocalDataSource {
    String TABLE_NAME = "group_member";
    String ID = "id";
    String MID = "mid";
    String INTROPENID = "intropenid";
    String HEADIMG = "headimg";
    String STATUS = "status";
    String MANAGE = "manage";
    String REALNAME = "realname";
    String GROUP_ID = "group_id";
    String LOGIN_USER_ID = "login_user_id";

    void saveGroupList(List<GroupMember> list, String groupId);

    List<Connections> getConnectionsNotjoinedCompany(List<Connections> friendList, String groupId);
}
