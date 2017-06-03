package com.hudong.wemedia.basiccomponent.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hudong.wemedia.R;

import java.util.ArrayList;

/**
 * Created by dyj on 2017/4/13.
 * 侧滑条
 */

public abstract class BaseSlidBar<T> extends View {
    protected ArrayList<String> allSections = new ArrayList<>();
    protected Paint paint;
    protected int singleH;
    protected int viewW;
    protected RelativeLayout parent;
    protected TextView contact_toast;
    protected RecyclerView contact_recycleView;
    protected T adapter;
    protected LinearLayoutManager linearLayoutManager;
    protected boolean move;
    protected boolean isScroll;//使用scrollToPosition方法移动为 true

    public BaseSlidBar(Context context) {
        this(context, null);
    }

    public BaseSlidBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseSlidBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public abstract void init();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < allSections.size(); i++) {
            canvas.drawText(allSections.get(i), viewW / 2, singleH * (i + 1), paint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        singleH = h / allSections.size();
        viewW = w;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //设置背景
                setBackgroundResource(R.drawable.circular_rectangle_gray_bg);
                handleMove(event);
                break;
            case MotionEvent.ACTION_MOVE:
                handleMove(event);
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.TRANSPARENT);
                //隐藏悬浮
                if (contact_toast != null) {
                    contact_toast.setVisibility(GONE);
                }
                break;
        }
        return true;
    }

    //处理手指按下和移动时字体和列表移动
    private void handleMove(MotionEvent event) {
        //显示字体
        float y = event.getY();
        //找到index
        int index = (int) (y / singleH);
        if (index < 0) {
            index = 0;
        } else if (index > allSections.size() - 1) {
            index = allSections.size() - 1;
        }
        //找到当前要显示的字符串
        String s = allSections.get(index);
        //通过父容器找到toast控件
        //获取父容器 不是父view
        if (parent == null) {
            parent = (RelativeLayout) getParent();
            contact_toast = (TextView) parent.findViewById(getToastId());
            contact_recycleView = (RecyclerView) parent.findViewById(getRecyclerId());
            adapter = (T) contact_recycleView.getAdapter();
        }
        contact_toast.setText(s);
        contact_toast.setVisibility(VISIBLE);
        //列表的滑动 找到要滑动到的position
        //判断是否有以当前字母开头的联系人(用容器保存所有联系人的首字母)
        ArrayList<String> sections = getSections();
        int sectionIndex = -1;
        for (int i = 0; i < sections.size(); i++) {
            if (s.equalsIgnoreCase(sections.get(i))) {
                sectionIndex = i;
                break;
            }
        }
        if (sectionIndex == -1) return;
        //如果有以当前字母开头的联系人  根据字母在容器中的位置找到对应的第一个条目的position
        final int position = getPositionForSection(sectionIndex);
        //找到第一个可见的条目position
        final RecyclerView.LayoutManager layoutManager = contact_recycleView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            final int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            //如果position小于第一个可见的条目位置 往上滑 直接滑动到指定的位置
            if (position <= firstVisibleItemPosition) {
                contact_recycleView.scrollToPosition(position);
            }else if(position<=lastVisibleItemPosition){
                //处理屏幕可见
                View childAt = contact_recycleView.getChildAt(position - firstVisibleItemPosition);
                //当前条目的top
                int top = childAt.getTop();
                contact_recycleView.smoothScrollBy(0, top);
            } else {
                //另外 先移动到指定位置  再向上移动当前条目的top
                move = true;
                isScroll = false;
                contact_recycleView.smoothScrollToPosition(position);
                //如果没有移动到可见的位置  需要判断当前条目是否可见
                contact_recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    //smoothScrollToPosition时回调
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        if(move&&newState== RecyclerView.SCROLL_STATE_IDLE){
                            move = false;
                            int firstItem = linearLayoutManager.findFirstVisibleItemPosition();
                            int lastItem = linearLayoutManager.findLastVisibleItemPosition();
                            if(position>=firstItem&&position<=lastItem) {
                                View childAt = contact_recycleView.getChildAt(position - firstItem);
                                //当前条目的top
                                int top = childAt.getTop();
                                contact_recycleView.smoothScrollBy(0, top);
                            }
                        }
                    }
                    //scrollToPosition  smoothScrollToPosition 手动滑动也会回调
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        Log.d("SlidBar", "onScrolled");
                        //只有当是scrollToPosition再进行移动操作
                        if (move && isScroll) {
                            move = false;
                            int firstItem = linearLayoutManager.findFirstVisibleItemPosition();
                            int lastItem = linearLayoutManager.findLastVisibleItemPosition();
                            if (position >= firstItem && position <= lastItem) {
                                View childAt = contact_recycleView.getChildAt(position - firstItem);
                                //当前条目的top
                                int top = childAt.getTop();
                                contact_recycleView.smoothScrollBy(0, top);
                            }
                        }

                    }
                });

            }
        }

    }

    protected abstract int getPositionForSection(int sectionIndex);

    protected abstract ArrayList<String> getSections();

    protected abstract int getRecyclerId();

    protected abstract int getToastId();
}
