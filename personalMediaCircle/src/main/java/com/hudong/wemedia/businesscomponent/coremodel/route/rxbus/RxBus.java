package com.hudong.wemedia.businesscomponent.coremodel.route.rxbus;

import android.accounts.NetworkErrorException;

import com.alibaba.fastjson.JSON;
import com.hudong.wemedia.businesscomponent.coremodel.network.retrofit.ServerException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

/**
 * 作者: 方天文
 * 日期: 2017/4/17:下午4:58
 * 概况:
 */

public class RxBus {
    private static RxBus sInstance = null;
    private SerializedSubject<SubscriberEvent, SubscriberEvent> mPublishSubject = null;
    private Map<String, SubscriberEvent> mStickyEventMap = null; //用于保存stick事件
    private Map<String, SubscriberEvent> mBindTargetMap = null;

    private RxBus() {
        mPublishSubject = new SerializedSubject<>(PublishSubject.<SubscriberEvent>create());
        mStickyEventMap = new ConcurrentHashMap();
    }

    public synchronized static RxBus get() {
        if (null == sInstance) {
            synchronized (RxBus.class) {
                if (sInstance == null) {
                    sInstance = new RxBus();
                }
            }
            sInstance = new RxBus();
        }
        return sInstance;
    }

    /**
     * 判断是否有订阅者
     */
    public boolean hasObservers() {
        return mPublishSubject.hasObservers();
    }

    /**
     * 根据tag和eventType获取指定类型的Sticky事件
     */
    public <T> T getStickyEvent(String tag, Class<T> eventType) {
        synchronized (mStickyEventMap) {
            Object object = mStickyEventMap.get(tag).getData();
            if (object.getClass().getCanonicalName().equals(eventType.getCanonicalName())) {
                return eventType.cast(object);
            }
        }
        return null;
    }

    /**
     * 根据tag获取Sticky事件
     */
    public Object getStickyEvent(String tag) {
        synchronized (mStickyEventMap) {
            return mStickyEventMap.get(tag) == null ? null :
                    mStickyEventMap.get(tag).getData();
        }
    }

    /**
     * 移除指定eventType的Sticky事件
     */
    public void removeStickyEvent(String tag) {
        synchronized (mStickyEventMap) {
            mStickyEventMap.remove(tag);
        }
    }

    /**
     * 移除所有的Sticky事件
     */
    public void removeAllStickyEvents() {
        synchronized (mStickyEventMap) {
            mStickyEventMap.clear();
        }
    }

    /**
     * 发送normal event
     *
     * @param tag Tag
     * @param o   事件
     */
    public void send(String tag, Object o) {
        SubscriberEvent event = new SubscriberEvent(tag, o);
        mPublishSubject.onNext(event);
    }

    /**
     * 发送一个Sticky event
     */
    public void sendSticky(String tag, Object o) {
        synchronized (mStickyEventMap) {
            SubscriberEvent event = new SubscriberEvent(tag, o, true);
            mStickyEventMap.put(tag, event);
            mPublishSubject.onNext(event);
        }
    }

    /**
     * 返回普通事件类型的被观察者
     *
     * @param eventType 只接受eventType类型的响应,ofType = filter + cast
     * @return Observable
     */
    public <T> Observable<T> toObservable(final String tag, final Class<T> eventType) {
        return mPublishSubject
                .filter(new Func1() {
                    @Override
                    public Object call(Object o) {
                        return ((SubscriberEvent) o).getTag().equals(tag);
                    }
                }).flatMap(new Func1<SubscriberEvent, Observable<?>>() {
                    @Override
                    public Observable<?> call(final SubscriberEvent subscriberEvent) {
                        if (subscriberEvent.getData().getClass().getSimpleName().equals(ServerException.class.getSimpleName())) {
                            return Observable.error((ServerException) subscriberEvent.getData());
                        } else {
                            return Observable.create(new Observable.OnSubscribe<T>() {
                                @Override
                                public void call(Subscriber<? super T> subscriber) {
                                    subscriber.onNext(JSON.parseObject(JSON.toJSONString(subscriberEvent.getData()), eventType));
                                }
                            });
                        }

                    }
                }).ofType(eventType);


    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     */
    public <T> Observable<T> toObservableSticky(String tag, final Class<T> eventType) {
        synchronized (mStickyEventMap) {
            //sticky事件
            final SubscriberEvent event = mStickyEventMap.get(tag);
            Observable<T> observable = toObservable(tag, eventType);
            if (event != null) {
                //合并事件序列
                return Observable.create(new Observable.OnSubscribe<SubscriberEvent>() {
                    @Override
                    public void call(Subscriber<? super SubscriberEvent> subscriber) {
                        subscriber.onNext(event);
                    }
                })
                        .flatMap(new Func1<SubscriberEvent, Observable<T>>() {
                            @Override
                            public Observable<T> call(final SubscriberEvent subscriberEvent) {
                                if (subscriberEvent.getData().getClass().getSimpleName().equals(eventType.getSimpleName())) {
                                    return Observable.create(new Observable.OnSubscribe<T>() {
                                        @Override
                                        public void call(Subscriber<? super T> subscriber) {
                                            subscriber.onNext(JSON.parseObject(JSON.toJSONString(subscriberEvent.getData()), eventType));
                                        }
                                    });
                                } else if (subscriberEvent.getData().getClass().getSimpleName().contains(ServerException.class.getSimpleName())) {
                                    return Observable.error((ServerException) subscriberEvent.getData());
                                } else {
                                    return Observable.error(new NetworkErrorException());
                                }
                            }
                        }).mergeWith(observable);
            } else {
                return observable;
            }
        }
    }
}
