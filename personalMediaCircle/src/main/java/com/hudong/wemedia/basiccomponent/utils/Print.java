package com.hudong.wemedia.basiccomponent.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 打印toast的工具类
 */
public class Print {

    private static Toast mToastNormal;

    public static void systemOutPrintln(String result) {
        System.out.println("---x---: " + result);
    }

    public static void systemOutPrintln(String title, String result) {
        System.out.println("---x---" + title + ": " + result);
    }

    public static void systemOutPrintln(String title, int result) {
        System.out.println("---x---" + title + ": " + result);
    }

    public static void systemOutPrintln(String title, float result) {
        System.out.println("---x---" + title + ": " + result);
    }


    public static void toast(Context context, String content) {
        Print.cancelToast();

        if (mToastNormal == null) {
            mToastNormal = Toast.makeText(context, content, Toast.LENGTH_LONG);
        } else {
            mToastNormal.setText(content);
        }

        mToastNormal.show();
    }


    /**
     * 普通的toast提示
     */
    public static void showNormalToast(Context mContext, String message) {

        Print.cancelToast();

        if (mToastNormal == null) {
            mToastNormal = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
        } else {
            mToastNormal.setText(message);
        }

        mToastNormal.show();

    }

    /**
     * toast取消
     */
    public static void cancelToast() {

        if (mToastNormal != null) {
            mToastNormal.cancel();
            mToastNormal = null;
        }


    }


}
