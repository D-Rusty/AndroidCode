package com.hudong.wemedia.basiccomponent.utils.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.hudong.wemedia.MainActivity;
import com.hudong.wemedia.basiccomponent.Constant;

/**
 * 作者: 方天文
 * 日期: 2017/4/14:上午9:43
 * 概况:
 */

public class UtilsOnTouchListener {

    private static GestureDetector.OnGestureListener onGestureListener = null;

    private static MyOnTouchListener onTouchListener = null;

    public static GestureDetector.OnGestureListener getOnGestureListener(final MainActivity activity) {
        if (onGestureListener != null) {
            return onGestureListener;
        } else {
            onGestureListener = new GestureDetector.OnGestureListener() {
                @Override
                public boolean onDown(MotionEvent motionEvent) {
                    return false;
                }

                @Override
                public void onShowPress(MotionEvent motionEvent) {

                }

                @Override
                public boolean onSingleTapUp(MotionEvent motionEvent) {
                    return false;
                }

                @Override
                public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                    return false;
                }

                @Override
                public void onLongPress(MotionEvent motionEvent) {

                }

                @Override
                public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                    float x = motionEvent1.getX() - motionEvent.getX();
                    float y = motionEvent1.getY() - motionEvent.getY();
                    String direction = "";
                    if (x > 10) {
                        direction = Constant.LEFT;
                    } else if (x < 10) {
                        direction = Constant.RIGHT;
                    }
                    activity.doDirection(direction);
                    return false;
                }
            };
        }
        return onGestureListener;
    }

    public static MyOnTouchListener getOnTouchListener(final GestureDetector gestureListener) {
        if (null != onTouchListener) {
            onTouchListener = new MyOnTouchListener() {
                @Override
                public boolean onTouch(MotionEvent ev) {
                    return gestureListener.onTouchEvent(ev);
                }
            };
        }
        return onTouchListener;
    }
}
