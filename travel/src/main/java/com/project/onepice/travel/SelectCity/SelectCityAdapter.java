package com.project.onepice.travel.SelectCity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.onepice.travel.R;
import com.project.onepice.travel.widgets.SectionRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by onepice2015 on 2016/11/14.
 */


public class SelectCityAdapter extends SectionRecyclerViewAdapter<SelectCityAdapter.ViewTitleHolder, SelectCityAdapter.ViewContentHolder, RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<SelectCityVo> cityList;
    private LayoutInflater inflater;
    private selectCityAapterCall call;

    public SelectCityAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setCall(selectCityAapterCall call) {
        this.call = call;
    }

    public void setCityList(ArrayList<SelectCityVo> cityList) {
        this.cityList = cityList;
    }

    public static interface selectCityAapterCall {
        void onItemClick(View view, String data);
    }

    @Override
    public int getSectionCount() {
        if (cityList != null) {
            return cityList.size();
        }
        return 0;
    }

    @Override
    protected int getItemCountForSection(int section) {
        if (cityList != null) {
            return cityList.get(section).cityInfos.size();
        }
        return 0;
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected ViewTitleHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new ViewTitleHolder(inflater.inflate(R.layout.select_city_title_item, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected ViewContentHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new ViewContentHolder(inflater.inflate(R.layout.select_city_conent_item, parent, false));
    }

    @Override
    protected void onBindSectionHeaderViewHolder(ViewTitleHolder holder, int section) {
        holder.provicen_name.setText(cityList.get(section).cityParent);
    }

    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(ViewContentHolder holder, int section, int position) {
        holder.city_name.setText(cityList.get(section).cityInfos.get(position).getCityName_zh());
        holder.city_name.setTag(cityList.get(section).cityInfos.get(position).getCityName_zh());
        holder.city_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call.onItemClick(v, (String) v.getTag());
            }
        });
    }

    public class ViewTitleHolder extends RecyclerView.ViewHolder {

        public TextView provicen_name;

        public ViewTitleHolder(View itemView) {
            super(itemView);
            provicen_name = (TextView) itemView.findViewById(R.id.provice_name);
        }
    }

    public class ViewContentHolder extends RecyclerView.ViewHolder {

        public TextView city_name;

        public ViewContentHolder(View itemView) {
            super(itemView);
            city_name = (TextView) itemView.findViewById(R.id.city_name);
        }
    }

}
