package com.project.onepice.basicproject.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by onepice2015 on 2017/1/25.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration{
    private static final int[] ATTRS=new int[]{android.R.attr.listDivider};
    private Drawable mDivider;
    public DividerItemDecoration(Context context){
        TypedArray a= context.obtainStyledAttributes(ATTRS);
        mDivider=a.getDrawable(0);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount=parent.getChildCount();
        for (int i=0;i<childCount;i++){
            View child=parent.getChildAt(i);
            RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) child.getLayoutParams();
            int top=child.getTop()-params.topMargin;
            int bottom=child.getBottom()+params.bottomMargin;
            int left=child.getRight()+params.leftMargin;
            int right=left+mDivider.getIntrinsicWidth();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }


    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount=parent.getChildCount();
        for (int i=0;i<childCount;i++){
            View child=parent.getChildAt(i);
            RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) child.getLayoutParams();
            int left=child.getLeft()+params.leftMargin;
            int right=child.getRight()-params.rightMargin+
                    mDivider.getIntrinsicWidth();
            int top=child.getBottom()+params.bottomMargin;
            int bottom=top+mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {

        int spanCount=getSpanCount(parent);
        int childCount=parent.getAdapter().getItemCount();
        if (isLastRaw(parent,itemPosition,spanCount,childCount)){
            outRect.set(15,0,mDivider.getIntrinsicWidth(),0);
        }else if (isLastColum(parent,itemPosition,spanCount,childCount)){
            outRect.set(15,0,0,mDivider.getIntrinsicHeight());
        }else {
            outRect.set(15,0,mDivider.getIntrinsicWidth(),mDivider.getIntrinsicHeight());
        }
    }

    private int getSpanCount(RecyclerView parent) {
        int spanCount=-1;
        RecyclerView.LayoutManager layoutManager=parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            spanCount=((GridLayoutManager) layoutManager).getSpanCount();
        }else if (layoutManager instanceof StaggeredGridLayoutManager){
            spanCount=((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    private boolean isLastColum(RecyclerView parent,int pos,int spanCount,int childCount){
        RecyclerView.LayoutManager layoutManager=parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            if ((pos+1) %spanCount==0){
                return false;
            }
        }else if (layoutManager instanceof StaggeredGridLayoutManager){
            int orientation=((StaggeredGridLayoutManager) layoutManager).getOrientation();

            if (orientation==StaggeredGridLayoutManager.VERTICAL){
                if ((pos+1)%spanCount==0){
                    return true;
                }
            }else{
                childCount=childCount-childCount%spanCount;
                if (pos>=childCount){
                    return false;
                }
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount)
    {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount){// 如果是最后一行，则不需要绘制底部
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }

}
