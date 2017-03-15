package com.project.onepice.basicproject;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.onepice.basicproject.bean.Section;
import com.project.onepice.basicproject.callback.CallBackRecyclerViewApdaterItem;
import com.project.onepice.basicproject.callback.SectionStateChangeListener;

import java.util.ArrayList;

/**
 * Created by onepice2015 on 2017/1/17.
 */

public class BasicRecyclerViewAdapter extends RecyclerView.Adapter<BasicRecyclerViewAdapter.
        basicRecyclerViewViewHolder> implements View.OnClickListener {
    public ArrayList<Object> getFragmentList() {
        return fragmentList;
    }
    public void setFragmentList(ArrayList<Object> fragmentList) {
        this.fragmentList = fragmentList;
    }
    private ArrayList<Object> fragmentList;
    private LayoutInflater layoutInflater;
    private CallBackRecyclerViewApdaterItem callBack;
    public void setCallBack(CallBackRecyclerViewApdaterItem callBack) {
        this.callBack = callBack;
    }
    private SectionStateChangeListener listener;
    public static final int VIEW_TYPE_PARENT = R.layout.item_basic;
    public static final int VIEW_TYPE_ITEM = R.layout.item_android_route;
    public BasicRecyclerViewAdapter(RecyclerView recyclerView, Context context, final GridLayoutManager gridLayoutManager,
                                    SectionStateChangeListener listener) {
        layoutInflater = LayoutInflater.from(context);
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return isSection(position) ? gridLayoutManager.getSpanCount() : 1;
            }
        });
        this.listener = listener;
    }
    private boolean isSection(int position) {
        return fragmentList.get(position) instanceof Section;
    }
    @Override
    public basicRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(viewType, parent, false);
        return new basicRecyclerViewViewHolder(view, viewType);
    }
    @Override
    public void onBindViewHolder(basicRecyclerViewViewHolder holder, final int position) {
        if (holder.viewType == VIEW_TYPE_PARENT) {
            Section section = (Section) fragmentList.get(position);
            holder.item_basic_itme_tv.setText(section.getName());
            holder.item_basic_itme_tv.setTag(section.getName());
            holder.item_basic_itme_tv.setOnClickListener(this);
            holder.item_basic_itme_tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, section.isExpanded ? R.mipmap.ic_keyboard_arrow_up_white_24dp
                    : R.mipmap.ic_keyboard_arrow_down_white_24dp, 0);
        } else {
            holder.route_tv_item.setText((String) fragmentList.get(position));
            holder.route_tv_item.setTag((String) fragmentList.get(position));
            holder.route_tv_item.setOnClickListener(this);
            
        }
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.route_tv_item:
                callBack.recyclerViewItemOnClick((String) view.getTag());
                break;
            case R.id.item_basic_itme_tv:
                listener.onSectionStateChanged((String) view.getTag());
                break;
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (isSection(position)) {
            return VIEW_TYPE_PARENT;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }
    class basicRecyclerViewViewHolder extends RecyclerView.ViewHolder {
        TextView item_basic_itme_tv;
        TextView route_tv_item;
        View view;
        int viewType;
        public basicRecyclerViewViewHolder(View itemView, int viewType) {
            super(itemView);
            this.view = itemView;
            this.viewType = viewType;
            if (viewType == VIEW_TYPE_PARENT) {
                item_basic_itme_tv = (TextView) view.findViewById(R.id.item_basic_itme_tv);
            } else {
                route_tv_item = (TextView) itemView.findViewById(R.id.route_tv_item);
            }

        }
    }

}




