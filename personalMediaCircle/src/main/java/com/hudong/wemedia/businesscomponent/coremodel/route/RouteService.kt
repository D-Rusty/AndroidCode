package com.hudong.wemedia.businesscomponent.coremodel.route

import rx.Observable

/**
 * 作者: 方天文
 * 日期: 2017/5/3:下午1:55
 * 概况: 路由层
 */

interface RouteService<T> {
    /**
     * 网络请求对外服务接口

     * @param observable 进行操作时传入的参数
     */
    fun  submintNetWorkAccess(observable: Observable<T>, action: String, `object`: Class<T>, isCache: Boolean)

    /**
     * 数据库请求对外服务接口

     * @param observable 调用数据库方法后返回的监听者
     */
    fun submintDbAccess(observable: Observable<T>, action: String) //操作的哪个数据库

    /***
     * 底层数据层在拿到数据后调用路由器的回调将值传回给路由器
     * @param <T>  T 具体的数据类型
    </T> */

    interface RouteServiceCallback<T> {
        /**
         * 数据访问成功后的结果回到

         * @param action RxBus 分发数据时候用的标志位
         * *
         * @param t      具体数据对象
         */
        fun call(action: String, t: T)
    }

}
