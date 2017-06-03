package com.hudong.wemedia.basiccomponent.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 屏蔽掉ListView的滚动，防止嵌入ScrollView时出现冲突
 */
public class NoScrollListView
        extends ListView{

    public NoScrollListView(Context context) {
        super(context);
    }

    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //该自定义控件只是重写了ListView的onMeasure方法，使其不会出现滚动条
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


}
