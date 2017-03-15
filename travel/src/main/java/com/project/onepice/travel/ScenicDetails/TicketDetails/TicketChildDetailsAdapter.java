package com.project.onepice.travel.ScenicDetails.TicketDetails;

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
import com.project.onepice.travel.data.TicketDetail;

/**
 * Created by onepice2015 on 2016/11/11.
 */
public class TicketChildDetailsAdapter extends RecyclerView.Adapter {

    private Context context;
    private TicketDetail ticketDetails;

    public void setTicketDetails(TicketDetail ticketDetails) {
        this.ticketDetails = ticketDetails;
    }


    public TicketChildDetailsAdapter(Context context) {
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView scenic_details_child_img;
        TextView ticke_child_details_name;
        TextView scenic_details_child_into;

        public ImageView getScenic_details_child_img() {
            return scenic_details_child_img;
        }

        public TextView getTicke_child_details_name() {
            return ticke_child_details_name;
        }


        public TextView getScenic_details_child_into() {
            return scenic_details_child_into;
        }

        public ViewHolder(View view) {
            super(view);
            scenic_details_child_img = (ImageView) view.findViewById(R.id.scenic_details_child_img);
            ticke_child_details_name = (TextView) view.findViewById(R.id.ticke_child_details_name);
            scenic_details_child_into = (TextView) view.findViewById(R.id.scenic_details_child_into);

        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card, parent, false);
        TicketChildDetailsAdapter.ViewHolder viewHolder = new TicketChildDetailsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TicketChildDetailsAdapter.ViewHolder viewHolder = (TicketChildDetailsAdapter.ViewHolder) holder;
        viewHolder.getTicke_child_details_name().setText(ticketDetails.retData.get(position).scenicName);
        viewHolder.getScenic_details_child_into().setText(ticketDetails.retData.get(position).scenicIntro);

        Glide.with(context)
                .load("http://" + ticketDetails.retData.get(position).picSrc)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.getScenic_details_child_img());

    }


    @Override
    public int getItemCount() {
        if (ticketDetails != null && ticketDetails.retData != null) {
            return ticketDetails.retData.size();
        }
        return 0;
    }
}
