package com.hudong.wemedia.businesscomponent.coremodel.network.cache;

import com.hudong.wemedia.businesscomponent.coremodel.network.retrofit.HttpResult;

import rx.Observable;

/**
 * 作者: 方天文
 * 日期: 2017/5/14:下午4:26
 * 概况:
 */

public interface ICache {

    <T extends HttpResult> Observable<T> get(String key, Class<T> cls);

    <T extends HttpResult> void put(String key, T t);

    void clearCache(String key);

    void clearAllCache();
    //获取缓存文件大小
}
