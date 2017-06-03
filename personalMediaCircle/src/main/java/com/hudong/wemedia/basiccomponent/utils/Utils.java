package com.hudong.wemedia.basiccomponent.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.inputmethod.InputMethodManager;

/**
 * 作者: 方天文
 * 日期: 2017/4/12:上午10:28
 * 概况:
 */

public class Utils {

    /**
     * 管理fragment
     */
    public static Fragment managerFragment(Fragment showFragment, Fragment hiddleFragment,
                                           FragmentManager fragmentManager, int viewId) {//需要被加载的fragment，正存在界面的fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (hiddleFragment != null) {
            fragmentTransaction.hide(hiddleFragment);
        }
        if (showFragment.isAdded()) {
            fragmentTransaction.show(showFragment);
        } else {
            fragmentTransaction.add(viewId, showFragment);
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
        return showFragment;
    }


    /**
     * 管理--弹出本层fragment 返回到上一级
     */
    public static Fragment managerBackChildFragment(Fragment showFragment, FragmentManager fragmentManager) {//需要被加载的fragment，正存在界面的fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(showFragment);
        fragmentTransaction.commit();
        return showFragment;

    }

    /**
     * @return
     * @throws
     * @params: Context context
     * @date: 2017/4/12 下午7:58 <br>
     * @Description 关闭软键盘
     */
    public static void closeInputMethod(Activity context) {
        //关闭软键盘
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 得到InputMethodManager的实例
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
