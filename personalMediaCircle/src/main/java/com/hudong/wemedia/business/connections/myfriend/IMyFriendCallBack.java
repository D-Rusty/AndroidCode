package com.hudong.wemedia.business.connections.myfriend;

import com.hudong.wemedia.basiccomponent.bean.Connections;

import java.util.List;

/**
 * Created by dyj on 2017/4/17.
 */

public interface IMyFriendCallBack {
    void getDataResult(List<Connections> connectionsList);

    void onResultFriend2Customer(boolean isSuccess, String friendID);
}
