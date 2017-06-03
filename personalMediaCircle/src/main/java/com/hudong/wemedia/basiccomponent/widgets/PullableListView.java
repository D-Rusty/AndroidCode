package com.hudong.wemedia.basiccomponent.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;


/**
 * 有下拉刷新和上拉加载的ListView
 */
public class PullableListView
        extends ListView implements Pullable
{

    public PullableListView(Context context) {
        super(context);
    }

    public PullableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean canPullDown() {
        if (getCount() == 0){
            return true;
        }else return getFirstVisiblePosition() == 0 && getChildAt(0).getTop() >= 0;
    }


    @Override
    public boolean canPullUp() {
        if (getCount() == 0){
            return true;
        }else if (getLastVisiblePosition() == (getCount() - 1)){
            if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()).getBottom() <= getMeasuredHeight()){
                return true;
            }
        }
        return false;
    }



}
