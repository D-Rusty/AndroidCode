package com.hudong.wemedia.basiccomponent.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * 与字符串有关的工具类
 */

public class StringUtils {
    /**判断字符串是否为空*/

    public static boolean isEmpty(String content){
        if (content!=null && content.length()>0){
            return true;
        }
        return false;
    }
    /**
     * 获取名字的大写首字母
     */
    public static String getFirstC(String name) {
        HanziToPinyin hanziToPinyin = HanziToPinyin.getInstance();
        ArrayList<HanziToPinyin.Token> tokens        = hanziToPinyin.get(name);
        return tokens.get(0).target.substring(0, 1).toUpperCase();
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static boolean matches(String code) {
        if (null != code && code.length() > 0) {
            Pattern pattern = Pattern.compile("[0-9]+");
            return pattern.matcher(code).matches();
        }
        return false;
    }

}
