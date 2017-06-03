package com.hudong.wemedia.business.connections.myfriend;

/**
 * Created by dyj on 2017/4/17.
 */

public interface MyFriendContract {
    interface MyFriendView {
        //获取数据成功
        void onResultSuccess();
        //获取数据为空
        void onResultEmpty();
        //弹出pop
        void showPop();
        //pop消失
        void dissPop();
        //打开扫一扫
        void toScan();
        //隐藏条目的选择按钮
        void hideCheckBox();
        //转移好友
        void selectFriend();
        //好友转到其它分组
        void friendToCustomer();
    }

    interface MyFriendPresenter {
        //获取好友数据
        void getContectionList(String groupName);

        //星标好友
        void starFriend(boolean isStar, String loginUserID, String ConnectionsID);

        //通过好友ID从服务器获取好友信息
        void getServerConnection(String connnectionId);

        void friend2Customer(String loginuserid,String friendid,String groupid);
    }
}
