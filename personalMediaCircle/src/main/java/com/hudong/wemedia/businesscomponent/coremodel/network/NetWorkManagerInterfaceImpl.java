package com.hudong.wemedia.businesscomponent.coremodel.network;


import android.content.Context;
import android.util.Log;

import com.hudong.wemedia.businesscomponent.coremodel.network.cache.CacheManager;
import com.hudong.wemedia.businesscomponent.coremodel.network.retrofit.HttpResult;
import com.hudong.wemedia.businesscomponent.coremodel.network.retrofit.ProgressSubscriber;
import com.hudong.wemedia.businesscomponent.coremodel.network.retrofit.ServerException;
import com.hudong.wemedia.businesscomponent.coremodel.route.RouteService;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 作者: 方天文
 * 日期: 2017/5/3:下午2:36
 * 概况: 网络访问实体类
 */


// TODO: 2017/5/4   现有框架高并发时线程安全问题
// TODO: 2017/5/4   其它核心组件优化

public class NetWorkManagerInterfaceImpl implements NetWorkAccessInterface {

    private Context mContext;

    private HashMap<String, String> hashMap;

    private CacheManager cacheManager;

    private volatile String action;
    private volatile boolean isCache;

    public NetWorkManagerInterfaceImpl(Context mContext) {
        this.mContext = mContext;
        cacheManager = new CacheManager(mContext);
    }

    @Override
    public void submitPost(final Observable observable, RouteService.RouteServiceCallback routeServiceCallback,
                           String action, Class object, boolean isCache) {
        this.action = action;
        this.isCache = isCache;
        if (isCache) {
            Observable.concat(
                    cacheManager.loadFromMemory(action, object),
                    cacheManager.loadFromDisk(action, object),
                    cacheManager.loadFromNetwork(observable))
                    .compose(transformer)
                    .subscribe(new ProgressSubscriber(mContext, routeServiceCallback, action));
        } else {
            observable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .compose(transformer)
                    .subscribe(new ProgressSubscriber(mContext, routeServiceCallback, action));
        }

    }


    Observable.Transformer<HttpResult, Object> transformer = new Observable.Transformer<HttpResult, Object>() {
        @Override
        public Observable<Object> call(Observable<HttpResult> httpResultObservable) {
            return httpResultObservable.first(new Func1<HttpResult, Boolean>() {
                @Override
                public Boolean call(HttpResult httpResult) {
                    String result = httpResult == null ? "not exist" :
                            httpResult.isExpire() ? "exist but expired" : "exist and not expired";
                    Log.v("cache", "result: " + result);
                    return httpResult != null && !httpResult.isExpire();
                }
            }).flatMap(new Func1<HttpResult, Observable<?>>() {
                @Override
                public Observable<?> call(HttpResult httpResult) {
                    if (httpResult.success()) {
                        if (isCache) {
                            cacheManager.put(action, httpResult);
                        }
                        return createData(httpResult.getContent());
                    } else {
                        return Observable.error(new ServerException(httpResult.getMsg(),
                                httpResult.getCode()));
                    }

                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io());
        }
    };


    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
        });
    }

}




















