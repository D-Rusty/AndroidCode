package com.hudong.wemedia.business.person.login;

import android.content.Context;

import com.hudong.wemedia.basiccomponent.bean.LoginUser;
import com.hudong.wemedia.businesscomponent.coremodel.network.retrofit.RetrofitManageImpl;
import com.hudong.wemedia.businesscomponent.coremodel.route.RouteService;
import com.hudong.wemedia.businesscomponent.coremodel.route.build.datarequset.RequestBuilderImpl;
import com.hudong.wemedia.businesscomponent.coremodel.route.rxbus.RxBusSubscriber;

import java.util.HashMap;

/**
 * 作者: 方天文
 * 日期: 2017/4/12:上午8:25
 * 概况:
 */

public class LoginPresenter implements LoginContract.Presenter {
    private ILoginCallBack callBack;
    private RouteService routeService = null;
    private Context context;

    public LoginPresenter(Context mContext, ILoginCallBack callBack) {
        this.callBack = callBack;
        this.context = mContext;
    }

    @Override
    public void login(HashMap<String, String> hashMap) {
        new RequestBuilderImpl(context).setObserObler(RetrofitManageImpl.getInstance(context).loginService(hashMap))
                .setRxBusParameter(hashMap.get("action"), LoginUser.class)
                .setSubscriber(new RxBusSubscriber<LoginUser>() {
                    @Override
                    protected void _next(LoginUser data) {

                    }

                    @Override
                    public void _error(String code, String msg) {

                    }
                }).create();

    }


    @Override
    public void forgetPwd() {



    }


    @Override
    public void weLogin() {
    }

}
















