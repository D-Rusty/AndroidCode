package com.project.onepice.basicproject.androidBasic.Animation.Evaluator;

import android.animation.TypeEvaluator;

import com.project.onepice.basicproject.androidBasic.Animation.ObjectCustmoView.Point;

/**
 * Created by onepice2015 on 2017/2/11.
 */
public class PointEvaluator implements TypeEvaluator<Point>{
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        int start = startValue.getRadius();
        int end  = endValue.getRadius();
        int curValue = (int)(start + fraction * (end - start));
        return new Point(curValue);
    }
}
