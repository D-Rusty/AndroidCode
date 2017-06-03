package com.hudong.wemedia.businesscomponent.coremodel.db;

import android.content.Context;

import com.hudong.wemedia.businesscomponent.coremodel.network.retrofit.ProgressSubscriber;
import com.hudong.wemedia.businesscomponent.coremodel.route.RouteService;

import rx.Observable;

/**
 * 作者: 方天文
 * 日期: 2017/5/12:下午2:23
 * 概况:
 */

public class DbManagerInterfaceImpl implements DbManagerInterface {

    private Context context;

    public DbManagerInterfaceImpl(Context context) {
        this.context = context;
    }


    @Override
    public void managerLocalData(Observable observable, RouteService.RouteServiceCallback routeServiceCallback, String action) {
        toSubscribe(observable, routeServiceCallback, action);
    }


    private void toSubscribe(Observable observable, RouteService.RouteServiceCallback callback, String action) {
        observable
                .subscribe(new ProgressSubscriber(context, callback, action));
    }

}
