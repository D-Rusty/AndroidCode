package com.hudong.wemedia.business.connections.mycolleage;

import android.content.Context;

/**
 * Created by dyj on 2017/4/17.
 */

public class MyColleagePresenterImpl implements MyColleageContract.MyColleagePresenter {
    private Context mContext;
    private IMyColleageCallBack mIMyColleageCallBack;

    public MyColleagePresenterImpl(IMyColleageCallBack iMyColleageCallBack) {
        mIMyColleageCallBack = iMyColleageCallBack;
        mContext = (Context) iMyColleageCallBack;
    }

    @Override
    public void getContactColleage() {

    }
}
