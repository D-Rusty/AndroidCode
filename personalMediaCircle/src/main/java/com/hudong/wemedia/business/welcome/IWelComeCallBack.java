package com.hudong.wemedia.business.welcome;

import java.util.List;

/**
 * 作者: 方天文
 * 日期: 2017/4/13:下午4:33
 * 概况:
 */

public interface IWelComeCallBack {
    void isLoginResultCallback(boolean tatus);

    /**
     * 检查权限回调
     */
    void checkPermissionCallback(List<String> permissionsList);
}
