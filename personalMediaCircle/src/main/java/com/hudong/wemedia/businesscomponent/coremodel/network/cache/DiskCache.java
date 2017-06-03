package com.hudong.wemedia.businesscomponent.coremodel.network.cache;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.hudong.wemedia.businesscomponent.coremodel.network.retrofit.HttpResult;
import com.hudong.wemedia.basiccomponent.utils.FileUtil;

import java.io.File;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 作者: 方天文
 * 日期: 2017/5/14:下午4:41
 * 概况:
 */

public class DiskCache implements ICache {

    private String CACHE_PATH;

    public DiskCache(Context context) {
        CACHE_PATH = FileUtil.getCacheDir(context);
        Log.i("test", "DiskCache=" + CACHE_PATH);
    }


    @Override
    public <T extends HttpResult> Observable<T> get(final String key, final Class<T> cls) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                String filename = CACHE_PATH + key;
                String result = FileUtil.readTextFromSDcard(filename);
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
    public <T extends HttpResult> void put(final String key, final T t) {
        Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                String filename = CACHE_PATH + key;
                String result = t.toString();
                Log.v("cache", "save to disk: " + key + " data=" + result);
                FileUtil.saveText2Sdcard(filename, result);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void clearCache(String key) {
        Observable.just(CACHE_PATH + key)
                .flatMap(new Func1<String, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(String s) {
                        File file = new File(s);
                        if (file.exists()) {
                            file.delete();
                            return Observable.create(new Observable.OnSubscribe<Boolean>() {
                                @Override
                                public void call(Subscriber<? super Boolean> subscriber) {
                                    subscriber.onNext(true);
                                }
                            });
                        }
                        return Observable.error(new Throwable("清除缓存失败"));
                    }
                });
    }

    @Override
    public void clearAllCache() {
        File file = new File(CACHE_PATH);
        if (!file.exists()) {
            return;
        }
        Observable.from(file.listFiles()).flatMap(new Func1<File, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call(final File file) {
                return Observable.create(new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> subscriber) {
                        file.delete();
                    }
                });
            }
        });
    }
}
