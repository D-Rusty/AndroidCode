package com.project.onepice.basicproject.androidBasic.widget.webView.fragment;

import android.content.Context;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.project.onepice.basicproject.R;
import com.project.onepice.basicproject.androidBasic.callback.callBackWebViewFragment;

/**
 * Created by onepice2015 on 2016/12/20.
 */

public class BasicFragment extends Fragment {
    private callBackWebViewFragment callBack;
    private TextView textView;
    private WebView webView;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
          callBack= (callBackWebViewFragment) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_webview_basic,container,false);
        textView= (TextView) view.findViewById(R.id.back);
        webView= (WebView) view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://172.22.2.234:9080/pcmp-server/recharge.htm?serviceNo=BR0001&charset=utf-8&version=V1.0.0&sessionKey=6c80c14244024a38bc9d702c18934e97&amt=1&orderType=4");
//        webView.loadUrl("https://baidu.com");

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
               handler.proceed();
            }
        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                callBack.hideFragmentName();
            }
        });

        return view;
    }
}
