package com.hudong.wemedia.businesscomponent.coremodel.network.cache;

import com.hudong.wemedia.businesscomponent.coremodel.network.retrofit.HttpResult;

import rx.Observable;

/**
 * 作者: 方天文
 * 日期: 2017/5/14:下午4:28
 * 概况:
 */

public interface NetWorkCache<T extends HttpResult> {
    Observable<T> get(String key, final Class<T> cls);
}
