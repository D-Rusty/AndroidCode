package com.hudong.wemedia.businesscomponent.coremodel.route

import android.content.Context
import com.hudong.wemedia.businesscomponent.coremodel.db.DbManagerInterface
import com.hudong.wemedia.businesscomponent.coremodel.db.DbManagerInterfaceImpl
import com.hudong.wemedia.businesscomponent.coremodel.network.NetWorkAccessInterface
import com.hudong.wemedia.businesscomponent.coremodel.network.NetWorkManagerInterfaceImpl
import com.hudong.wemedia.businesscomponent.coremodel.route.rxbus.RxBus
import rx.Observable

/**
 * 作者: 方天文
 * 日期: 2017/5/3:下午2:11
 * 概况: 路由器类实现，该类主要用于实现来自于P层数据请求，数据请求有网络数据请求，本地数据库网络请求，或者其它类型请求
 * 不同类型的请求需要路由器调用不同核心层数据接口,每一个类型的数据请求需要使用的回调不一样，所以需要调用不同callback进行返回
 */

class RouteServiceImpl<T>(private val mContext: Context) : RouteService<T> {
    private val netWorkManagerInterface: NetWorkAccessInterface //网络访问接口
    private val dbManagerInterface: DbManagerInterface//数据库访问接口

    init {
        netWorkManagerInterface=NetWorkManagerInterfaceImpl(mContext)
        dbManagerInterface=DbManagerInterfaceImpl(mContext)

    }

    override fun submintNetWorkAccess(observable: Observable<T>, action: String, `object`: Class<T>, isCache: Boolean) {
        netWorkManagerInterface.submitPost(observable, routeServiceCallback, action, `object`, isCache)
    }

    override fun submintDbAccess(observable: Observable<T>, action: String) {
        dbManagerInterface.managerLocalData(observable, routeServiceCallback, action)
    }

    private val routeServiceCallback=object : RouteService.RouteServiceCallback<Any> {
        override fun call(action: String, o: Any) {
            RxBus.get().sendSticky(action, o)
        }
    }
}

















