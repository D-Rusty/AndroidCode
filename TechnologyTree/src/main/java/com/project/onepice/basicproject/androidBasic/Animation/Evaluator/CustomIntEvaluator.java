package com.project.onepice.basicproject.androidBasic.Animation.Evaluator;

import android.animation.TypeEvaluator;

/**
 * Created by onepice2015 on 2017/2/11.
 *
 * Evaluator 将加速器计算出来的动画当前进度值(小数)，转换成对应的数字值
 *
 *      计算公式: 当前的值 = 100 + （400 - 100）* 显示进度
 */

public class CustomIntEvaluator implements TypeEvaluator<Integer>{

    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt=startValue;
        return (int) (endValue - fraction * (endValue - startInt));//倒序输出
    }
}
