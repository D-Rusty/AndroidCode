package com.project.onepice.travel.HomePage;

import java.util.List;

/**
 * Created by onepice2015 on 2016/11/9.
 */

public interface HomePageCallBack {

    /**
     * 检查权限回调
     */
    void checkPermissionCallback(List<String> permissionsList);
}
