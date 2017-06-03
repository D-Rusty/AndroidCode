package com.hudong.wemedia.business.connections.myfriend;

import android.content.Context;

import com.hudong.wemedia.basiccomponent.bean.Connections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyj on 2017/4/17.
 */

public class MyFriendPresenterImpl  implements MyFriendContract.MyFriendPresenter {

    private IMyFriendCallBack mIMyFriendCallBack;

    private Context mContext;

    public MyFriendPresenterImpl(IMyFriendCallBack iMyFriendCallBack) {
        mIMyFriendCallBack = iMyFriendCallBack;
        mContext = (Context) iMyFriendCallBack;
    }

    @Override
    public void getContectionList(String groupName) {
        //好友集合 TODO 从数据库中获取数据
//        List<Connections> connectionsList = new ConnectionsDao(mContext).findGroupList(groupName);
        List<Connections> connectionsList = new ArrayList<>();
        mIMyFriendCallBack.getDataResult(connectionsList);
    }

    @Override
    public void starFriend(boolean isStar, String loginUserID, String ConnectionsID) {
//        toSubscribe(getApiService(mContext).starFriend("editspecial",loginUserID,ConnectionsID,isStar ? "1" : "0").map(new HttpResultFunc()),new ProgressSubscriber(new SubscriberOnNextListener(LoginUser) {
//            @Override
//            public void onNext(Object o) {
//
//            }
//
//            @Override
//            public void onError(String msg) {
//
//            }
//        });
    }

    /**
     *获取联系人信息
     * @param connnectionId
     */
    @Override
    public void getServerConnection(String connnectionId) {
//        toSubscribe(getApiService(mContext).getServerConnection("memberinfos",connnectionId).map(new HttpResultFunc()<Connections>)),new ProgressSubscriber<Connections>(new SubscriberOnNextListener<Connections>() {
//            @Override
//            public void onNext(Connections ) {
//
//            }
//
//            @Override
//            public void onError(String msg) {
//
//            }
//        });
    }

    /**
     * 好友转客户
     * @param loginuserid
     * @param friendid
     * @param groupid
     */
    @Override
    public void friend2Customer(String loginuserid, String friendid, String groupid) {
        //TODO
//        toSubscribe(getApiService(mContext).friend2Customer("friendgroup",loginuserid,friendid,groupid).map(new HttpResultFunc(FriendGroupParseBean)),new ProgressSubscriber(new SubscriberOnNextListener(FriendGroupParseBean) {
//            @Override
//            public void onNext(Object o) {
//
//            }
//
//            @Override
//            public void onError(String msg) {
//
//            }
//        },mContext));

    }
}
