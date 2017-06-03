package com.hudong.wemedia.businesscomponent.coremodel.network.retrofit;

import android.content.Context;

import com.hudong.wemedia.businesscomponent.coremodel.route.RouteService;

import rx.Subscriber;

/**
 * 作者: 方天文
 * 日期: 2017/4/13:下午6:25
 * 概况:
 */

public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private ProgressDialogHandler mProgressDialogHandler;

    private Context mContext;

    private RouteService.RouteServiceCallback routeServiceCallback;

    private volatile String action;


    public ProgressSubscriber(Context context, RouteService.RouteServiceCallback routeServiceCallback, String action) {
        this.mProgressDialogHandler = new ProgressDialogHandler(this, true, context);
        this.routeServiceCallback = routeServiceCallback;
        this.mContext = context;
        this.action = action;
    }


    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        if (e instanceof ServerException) {
            routeServiceCallback.call(this.action, e);
        }

    }

    @Override
    public void onNext(T t) {
        routeServiceCallback.call(this.action, t);
    }

    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }


    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }


}
