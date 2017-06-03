package com.hudong.wemedia.basiccomponent.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by dyj on 2017/3/27.
 * 自定义ScrollView,解决ScrollView监听滑动API限制问题
 */

public class PersonScrollView extends ScrollView {
    private PersonScrollViewListenner listenner;

    public PersonScrollView(Context context) {
        super(context);
    }

    public PersonScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PersonScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if(null != listenner) {
            listenner.onScrollChanged(l,t,oldl,oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public interface PersonScrollViewListenner {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    public void setScrollViewListenner(PersonScrollViewListenner listenner) {
        this.listenner = listenner;
    }
}
