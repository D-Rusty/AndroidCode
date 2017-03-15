package com.project.onepice.basicproject.androidBasic.widget.customView.SlidingMenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;
import com.project.onepice.basicproject.R;
import com.project.onepice.basicproject.utils.ScreenUtils;

/**
 * Created by onepice2015 on 2017/2/13.
 */

public class SlidingMenu extends HorizontalScrollView {

    private int mScreenWidth;

    private int mMenuRightPadding;

    private boolean once;

    private ViewGroup mMenu;

    private ViewGroup mContent;

    private boolean isOpen;
    /**
     * 菜单的宽度
     */
    private int mMenuWidth;
    private int mHalfMenuWidth;

    public SlidingMenu(Context context) {
        this(context, null, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        mScreenWidth = ScreenUtils.getScreenWidth(context);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributeSet,
                R.styleable.SlidingMenu, defStyle, 0);

        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.SlidingMenu_rightPadding:
                    mMenuRightPadding = typedArray.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once) {
            LinearLayout wrapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) wrapper.getChildAt(0);
            mContent = (ViewGroup) wrapper.getChildAt(1);

            mMenuWidth=mScreenWidth-mMenuRightPadding;
            mHalfMenuWidth=mMenuWidth/2;
            mMenu.getLayoutParams().width=mMenuWidth;
            mContent.getLayoutParams().width=mScreenWidth;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed){
            this.scrollTo(mMenuWidth,0);
            once=true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:
                int scrollX=getScrollX();
                if (scrollX>mHalfMenuWidth){
                    this.smoothScrollTo(mMenuWidth,0);
                    isOpen=false;
                }else{
                    this.smoothScrollTo(0,0);
                    isOpen=true;
                }

               return true;
        }
        return super.onTouchEvent(ev);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale=l*1.0f/mMenuWidth;
        float leftScale=1-0.3f* scale;
        float rightScale=0.8f+scale *0.2f;

        ViewHelper.setScaleX(mMenu,leftScale);
        ViewHelper.setScaleY(mMenu,leftScale);
        ViewHelper.setAlpha(mMenu,0.6f+0.4f * (1-scale));
        ViewHelper.setTranslationX(mMenu,mMenuWidth*scale*0.7f);
        ViewHelper.setPivotX(mContent, 0);
        ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
        ViewHelper.setScaleX(mContent, rightScale);
        ViewHelper.setScaleY(mContent, rightScale);
    }

    /**
     * 打开菜单
     */
    public void openMenu()
    {
        if (isOpen)
            return;
        this.smoothScrollTo(0, 0);
        isOpen = true;
    }

    /**
     * 关闭菜单
     */
    public void closeMenu()
    {
        if (isOpen)
        {
            this.smoothScrollTo(mMenuWidth, 0);
            isOpen = false;
        }
    }


    /**
     * 切换菜单状态
     */
    public void toggle()
    {
        if (isOpen)
        {
            closeMenu();
        } else
        {
            openMenu();
        }
    }
}































