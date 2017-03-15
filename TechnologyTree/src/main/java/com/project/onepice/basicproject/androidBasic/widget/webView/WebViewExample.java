package com.project.onepice.basicproject.androidBasic.widget.webView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.project.onepice.basicproject.R;

/**
 * Created by onepice2015 on 2016/11/28.
 */

public class WebViewExample extends Activity {
    @SuppressLint("JavascriptInterface")
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
        //请描述如何使用WebView控件浏览网页以及像浏览器一样向前GoForward和向后GoBack浏览历史页面
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("http://nokiaguy.blogjava.net");
        webView.goForward();
        webView.goBack();

        /**
         * 现在有一段保存在String变量中的html代码，如何在WebView控件中显示这段HTML代码描述的页面
         * */

        String string = "xxxxx";
        webView.loadDataWithBaseURL("图书名", string,"text/html","utf-8",null);
        //打开JavasCript
        webView.getSettings().setJavaScriptEnabled(true);
        //设置JavaScript的引擎
        webView.setWebChromeClient(new WebChromeClient());
        /**
         * 请描述在Android应用程序中Java与JavaScript如何交互，并写出相关代码
         * */

      webView.addJavascriptInterface(new Object(){
          //JavaScript调用的方法
          public String process(String value){
              //处理代码
              return value;
          }
      },"demo");
    }


}
