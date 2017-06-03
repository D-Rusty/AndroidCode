package com.hudong.wemedia.basiccomponent.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 禁止切换页面的ViewPager
 */
public class MainViewPager
        extends ViewPager
{

    private boolean isCanScroll = false;


    public MainViewPager(Context context) {
        super(context);
    }

    public MainViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isCanScroll){
            return super.onInterceptTouchEvent(ev);
        }else {
            return false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isCanScroll){
            return super.onTouchEvent(ev);
        }else {
            return false;
        }
    }
}
