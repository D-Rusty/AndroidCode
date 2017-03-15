package com.project.onepice.basicproject.androidBasic.Animation.Interpolator;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.animation.BaseInterpolator;

import static android.R.id.input;

/**
 * Created by onepice2015 on 2017/2/10.
 *
 * 插值器，也叫加速器，插值器就是用来控制动画区间的值被如何计算出来的
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
public class CustomInterpolator extends BaseInterpolator{
    @Override
    public float getInterpolation(float v) {
        return (float)(Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
    }
}
