package com.hudong.wemedia.businesscomponent.coremodel.network;


import com.hudong.wemedia.businesscomponent.coremodel.route.RouteService;

import rx.Observable;

/**
 * 作者: 方天文
 * 日期: 2017/4/11:下午5:40
 * 概况: 远程数据访问，所有的数据必须基于此类
 */

public interface NetWorkAccessInterface {
    /**
     * @param observable           用于调用网络访问产生的生产者
     * @param routeServiceCallback 网络访问结束后的路由器回调
     * @param action               路由器回调时用到的发现标志位
     */
    void submitPost(Observable observable, RouteService.RouteServiceCallback routeServiceCallback,
                    String action, Class object, boolean isCache);
}
