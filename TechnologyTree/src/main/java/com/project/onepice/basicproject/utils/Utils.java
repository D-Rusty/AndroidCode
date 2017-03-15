package com.project.onepice.basicproject.utils;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.project.onepice.basicproject.BaseFragment;
import com.project.onepice.basicproject.R;

/**
 * Created by onepice2015 on 2017/1/17.
 */

public class Utils {
    public static BaseFragment getInstance(String className) {
        BaseFragment baseFragment = null;
        try {
            Class clazz = getclass(className);
            baseFragment = (BaseFragment) clazz.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return baseFragment;
    }

    public static Class getclass(String className) {
        Class c = null;
        try {
            c = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            return null;
        }
        return c;
    }


    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }


    public static boolean isEmptyOrNull(String string){
        if (string.length()==0||string==null){
            return true;
        }
        return false;
    }


    /**
     * 管理fragment
     */
    public static String managerFragment(String fragmentName, String currentFragment, FragmentManager fragmentManager, int viewId) {//需要被加载的fragment，正存在界面的fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (null == fragmentManager.findFragmentByTag(fragmentName.substring(fragmentName.lastIndexOf(".") + 1, fragmentName.length()))) {
            fragmentTransaction.add(viewId, Utils.getInstance(fragmentName),
                    fragmentName.substring(fragmentName.lastIndexOf(".") + 1, fragmentName.length()));
        } else {
            fragmentTransaction
                    .show(fragmentManager.findFragmentByTag(fragmentName.substring(fragmentName.lastIndexOf(".") + 1, fragmentName.length())));
        }

        if (!Utils.isEmptyOrNull(currentFragment)&&(!currentFragment.equals(fragmentName))) {
            fragmentTransaction.hide(fragmentManager.findFragmentByTag(currentFragment.substring(currentFragment.lastIndexOf(".") + 1,
                    currentFragment.length())));
        }
        fragmentTransaction.setCustomAnimations(R.anim.fragment_switch, R.anim.fragment_switch);
        fragmentTransaction.commit();
        return fragmentName;
    }


}
