package com.project.onepice.basicproject.androidBasic.map.baiduMap;

/**
 * Created by onepice2015 on 2017/2/20.
 */

public class BaiDuUtils {
    public static final float MAP_5KM = 13.0f;
    public static final float MAP_SINGLE = 18.0f;

    /**
     * 按地图的缩放等级  转换出地图应该加载的范围
     * @param level
     * @return
     */
    public static float CalcMapAround2(float level) {
        level -= 3;

        if (level <= 3) {
            return 2f;
        }
        else if (level <= 4) {
            return 1f;
        }
        else if (level <= 5) {
            return 0.5f;
        }
        else if (level <= 6) {
            return 0.2f;
        }
        else if (level <= 7) {
            return 0.1f;
        }
        else if (level <= 8) {
            return 0.05f;
        }
        else if (level <= 9) {
            return 0.025f;
        }
        else if (level <= 10) {
            return 0.02f;
        }
        else if (level <= 11) {
            return 0.01f;
        }
        else if (level <= 12) {
            return 0.005f;
        }
        else if (level <= 13) {
            return 0.002f;
        }
        else if (level <= 14) {
            return 0.001f;
        }
        else if (level <= 15) {
            return 0.0005f;
        }
        else if (level <= 16) {
            return 0.0002f;
        }
        else if (level <= 17) {
            return 0.0001f;
        }
        else {
            return 0.00005f;
        }

    }
}
