package com.hudong.wemedia.businesscomponent.coremodel.network.cache;

import android.text.TextUtils;
import android.util.LruCache;

import com.alibaba.fastjson.JSON;
import com.hudong.wemedia.businesscomponent.coremodel.network.retrofit.HttpResult;

import java.io.UnsupportedEncodingException;

import rx.Observable;
import rx.Subscriber;

/**
 * 作者: 方天文
 * 日期: 2017/5/14:下午4:33
 * 概况:
 */

public class MemoryCache implements ICache {

    private LruCache<String, String> mCache;

    public MemoryCache() {
        //获取系统分配给每个应用程序的最大内存，每个应用系统分配32M
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int mCacheSize = maxMemory / 8;
        mCache = new LruCache<String, String>(mCacheSize) {
            @Override
            protected int sizeOf(String key, String value) {
                try {
                    return value.getBytes("UTF-8").length;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return value.getBytes().length;
                }
            }
        };
    }

    @Override
    public <T extends HttpResult> Observable<T> get(final String key, final Class<T> cls) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                String result = mCache.get(key);
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                if (TextUtils.isEmpty(result)) {
                    subscriber.onNext(null);
                } else {
                    T t = JSON.parseObject(result, cls);
                    subscriber.onNext(t);
                }
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public <T extends HttpResult> void put(String key, T t) {
        if (null != t) {
            mCache.put(key, t.toString());
        }
    }

    @Override
    public void clearCache(String key) {
        mCache.remove(key);
    }

    @Override
    public void clearAllCache() {
        mCache.evictAll();
    }
}
