package com.project.onepice.travel.Scenic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.project.onepice.travel.R;
import com.project.onepice.travel.data.Scenic;

import java.util.ArrayList;

/**
 * Created by onepice2015 on 2016/11/10.
 */

public class ScenicRecyclerViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private Context context;

    public ArrayList<Scenic> getScenicArrayList() {
        return scenicArrayList;
    }

    private ArrayList<Scenic> scenicArrayList;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setScenicArrayList(ArrayList<Scenic> scenicArrayList) {
        this.scenicArrayList = scenicArrayList;
    }

    public ScenicRecyclerViewAdapter(Context context,OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.mOnItemClickListener = listener;
    }


    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Scenic data);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Scenic) v.getTag());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView scenic_itme_logo;
        TextView scenic_itme_name;
        TextView scenic_itme_pacle;
        TextView scenic_itme_price;

        public ImageView getScenic_itme_logo() {
            return scenic_itme_logo;
        }

        public TextView getScenic_itme_price() {
            return scenic_itme_price;
        }

        public TextView getScenic_itme_pacle() {
            return scenic_itme_pacle;
        }

        public TextView getScenic_itme_name() {
            return scenic_itme_name;
        }

        public ViewHolder(View view) {
            super(view);
            scenic_itme_logo = (ImageView) view.findViewById(R.id.scenic_itme_logo);
            scenic_itme_name = (TextView) view.findViewById(R.id.scenic_itme_name);
            scenic_itme_price = (TextView) view.findViewById(R.id.scenic_itme_price);
            scenic_itme_pacle = (TextView) view.findViewById(R.id.scenic_itme_pacle);
        }


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scenic_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.getScenic_itme_name().setText(scenicArrayList.get(position).getScenicName_zh());
        viewHolder.getScenic_itme_price().setText("￥" + scenicArrayList.get(position).getScenicTicket());
        viewHolder.getScenic_itme_pacle().setText(scenicArrayList.get(position).getProvince() + " " + scenicArrayList.get(position).getCity());
        Glide.with(context)
                .load("http://" + scenicArrayList.get(position).getSceniclogo())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.getScenic_itme_logo());

        viewHolder.itemView.setTag(scenicArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        if (scenicArrayList != null && scenicArrayList.size() > 0) {
            return scenicArrayList.size();
        }
        return 0;
    }
}
