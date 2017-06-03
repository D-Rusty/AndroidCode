package com.hudong.wemedia.businesscomponent.coremodel.db;


import com.hudong.wemedia.businesscomponent.coremodel.route.RouteService;

import rx.Observable;

/**
 * 作者: 方天文
 * 日期: 2017/4/11:下午5:40
 * 概况: 本地数据访问，所有的本地数据操作必须基于此类
 */

public interface DbManagerInterface {
    void managerLocalData(Observable observable, RouteService.RouteServiceCallback routeServiceCallback, String action);
}
