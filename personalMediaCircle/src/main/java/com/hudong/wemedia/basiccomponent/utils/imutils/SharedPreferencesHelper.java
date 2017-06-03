package com.hudong.wemedia.basiccomponent.utils.imutils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * SP保存数据的工具类
 */

public class SharedPreferencesHelper {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferencesHelper sharedPreferencesHelper;
    public static final String FILE_NAME="travel_shared_data";
    private static SharedPreferences.Editor editor;

    public SharedPreferencesHelper(Context context){
        sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized SharedPreferencesHelper getInstance(Context context){
        return  sharedPreferencesHelper==null?new SharedPreferencesHelper(context):sharedPreferencesHelper;
    }

    /**
     * 保存数据的方法，拿到数据保存数据的基本类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public static void put(String key, Object object) {

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.commit();

    }

    /**
     * 获取保存数据的方法，我们根据默认值的到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key           键的值
     * @param defaultObject 默认值
     * @return
     */

    public static Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, null);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, 0);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, false);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, 0);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, 0);
        } else {
            return sharedPreferences.getString(key, null);
        }
    }

    public static String get(String key){
        return sharedPreferences.getString(key,"");
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有的数据
     */
    public static void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否存在
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public static Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }
}







































