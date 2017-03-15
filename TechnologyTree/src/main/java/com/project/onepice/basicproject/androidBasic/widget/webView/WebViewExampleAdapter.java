package com.project.onepice.basicproject.androidBasic.widget.webView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.onepice.basicproject.R;
import com.project.onepice.basicproject.androidBasic.callback.callBackRecyclerView;

import java.util.List;

/**
 * Created by onepice2015 on 2016/12/20.
 */

public class WebViewExampleAdapter extends RecyclerView.Adapter<WebViewExampleAdapter.WebViewExampleViewHolder>{

    private List<String> webviewExampleList;
    private Context context;
    private callBackRecyclerView callBackRecyclerView;

    public void setCallBackRecyclerView(callBackRecyclerView callBackRecyclerView) {
        this.callBackRecyclerView = callBackRecyclerView;
    }

    public void setWebviewExampleList(List<String> webviewExampleList) {
        this.webviewExampleList = webviewExampleList;
    }

    public WebViewExampleAdapter(Context context){
       this.context=context;
    }

    @Override
    public WebViewExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_webview_example,parent,false);
        WebViewExampleViewHolder holder = new WebViewExampleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(WebViewExampleViewHolder holder, int position) {
        holder.webview_example_tv.setText(webviewExampleList.get(position));
        holder.webview_example_tv.setTag(webviewExampleList.get(position));
        holder.webview_example_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackRecyclerView.findFragmentName((String) view.getTag());
            }
        });
    }

    @Override
    public int getItemCount() {
        return webviewExampleList!=null?webviewExampleList.size():0;
    }

    public  class WebViewExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView webview_example_tv;
        public WebViewExampleViewHolder(View itemView) {
            super(itemView);
            webview_example_tv= (TextView) itemView.findViewById(R.id.webview_example_tv);
        }
    }

}
