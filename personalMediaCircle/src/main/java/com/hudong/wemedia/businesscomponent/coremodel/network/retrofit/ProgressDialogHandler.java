package com.hudong.wemedia.businesscomponent.coremodel.network.retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/**
 * 作者: 方天文
 * 日期: 2017/4/13:下午6:35
 * 概况:
 */

public class ProgressDialogHandler extends Handler{
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private ProgressDialog pd;
    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler( ProgressCancelListener mProgressCancelListener, boolean cancelable,Context context) {
        super();
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
        this.context=context;
    }

    private void initProgressDialog(Context context) {
        if (pd == null) {
            pd = new ProgressDialog(context);

            pd.setCancelable(cancelable);

            if (cancelable) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }

            if (!pd.isShowing()) {
                pd.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog(context);
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}