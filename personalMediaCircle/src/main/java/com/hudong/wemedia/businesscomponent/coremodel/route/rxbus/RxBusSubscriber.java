package com.hudong.wemedia.businesscomponent.coremodel.route.rxbus;

import com.hudong.wemedia.businesscomponent.coremodel.network.retrofit.ServerException;

import rx.Subscriber;

/**
 * 作者: 方天文
 * 日期: 2017/4/13:下午6:25
 * 概况:
 */

public abstract class RxBusSubscriber<T> extends Subscriber<T> {


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ServerException) {
            _error(((ServerException) e).getCode(), ((ServerException) e).getMsg());
        }

    }

    @Override
    public void onNext(T t) {
        _next(t);
    }


    @Override
    public void onStart() {
    }


    protected abstract void _next(T data);

    public abstract void _error(String code, String msg);
}
