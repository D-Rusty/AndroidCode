package com.project.onepice.travel.widgets;

import android.support.v7.widget.GridLayoutManager;

/**
 * Created by onepice2015 on 2016/11/15.
 */

public class SectionedSpanSizeLookup extends GridLayoutManager.SpanSizeLookup{
    protected SectionRecyclerViewAdapter<?, ?, ?> adapter = null;
    protected GridLayoutManager layoutManager = null;

    public SectionedSpanSizeLookup(SectionRecyclerViewAdapter<?, ?, ?> adapter, GridLayoutManager layoutManager) {
        this.adapter = adapter;
        this.layoutManager = layoutManager;
    }


    @Override
    public int getSpanSize(int position) {
        if(adapter.isSectionHeaderPosition(position) || adapter.isSectionFooterPosition(position)){
            return layoutManager.getSpanCount();
        }else{
            return 1;
        }
    }
}
