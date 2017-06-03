package com.hudong.wemedia.businesscomponent.coremodel.network.cache;

import android.content.Context;
import android.util.Log;

import com.hudong.wemedia.businesscomponent.coremodel.network.retrofit.HttpResult;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 作者: 方天文
 * 日期: 2017/5/14:下午4:29
 * 概况:
 */

public class CacheManager {

    private ICache mMemoryCache, mDiskCache;

    public CacheManager(Context mContext) {
        mMemoryCache = new MemoryCache();
        mDiskCache = new DiskCache(mContext);
    }

    public <T extends HttpResult> Observable<T> loadCache(String key, Class<T> tClass) {
        Observable observable = Observable.concat(
                loadFromMemory(key, tClass),
                loadFromDisk(key, tClass))
                .filter(new Func1<T, Boolean>() {
                    @Override
                    public Boolean call(T t) {
                        String result = t == null ? "not exist" :
                                t.isExpire() ? "exist but expired" : "exist and not expired";
                        Log.v("cache", "result: " + result);
                        return t != null && !t.isExpire();
                    }
                });
        return observable;
    }

    public <T extends HttpResult> Observable<T> loadFromMemory(String key, Class<T> tClass) {
        return mMemoryCache.get(key, tClass);
    }

    public <T extends HttpResult> Observable<T> loadFromDisk(final String key, Class<T> tClass) {
        return mDiskCache.get(key, tClass);
    }


    public <T extends HttpResult> Observable<T> loadFromNetwork(Observable observable) {
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


    public <T extends HttpResult> void put(String key, T t) {
        mMemoryCache.put(key, t);
        mDiskCache.put(key, t);
    }


    public void clearCache(String key) {
        mDiskCache.clearCache(key);
        mMemoryCache.clearCache(key);
    }

}



















