package com.project.onepice.basicproject.androidBasic.Animation.Evaluator;

import android.animation.TypeEvaluator;

/**
 * Created by onepice2015 on 2017/2/11.
 */

public class CustomCharEvaluator implements TypeEvaluator<Character> {

    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = (int) startValue;
        int endInt = (int) endValue;
        int curInt = (int) (startInt + fraction * (endInt - startInt));
        char result = (char) curInt;
        return result;
    }
}
