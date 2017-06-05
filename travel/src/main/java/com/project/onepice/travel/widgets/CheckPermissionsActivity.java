package com.project.onepice.travel.widgets; /**
 *
 */

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * 继承了Activity，实现Android6.0的运行时权限检测
 * 需要进行运行时权限检测的Activity可以继承这个类
 *
 * @author hongming.wang
 * @创建时间：2016年5月27日 下午3:01:31
 * @项目名称： AMapLocationDemo
 * @文件名称：PermissionsChecker.java
 * @类型名称：PermissionsChecker
 * @since 2.5.0
 */
public class CheckPermissionsActivity extends AppCompatActivity {


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
