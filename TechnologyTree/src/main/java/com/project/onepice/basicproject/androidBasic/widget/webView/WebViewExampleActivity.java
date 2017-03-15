package com.project.onepice.basicproject.androidBasic.widget.webView;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.project.onepice.basicproject.R;
import com.project.onepice.basicproject.androidBasic.callback.callBackRecyclerView;
import com.project.onepice.basicproject.androidBasic.callback.callBackWebViewFragment;
import com.project.onepice.basicproject.androidBasic.widget.webView.fragment.BasicFragment;

import java.util.ArrayList;
import java.util.List;

public class WebViewExampleActivity extends AppCompatActivity implements callBackWebViewFragment {
    private RecyclerView  recyclerView;
    private WebViewExampleAdapter webViewExampleAdapter;
    private ArrayList<Object> fragmentList;
    private List<String > fragmentNameList;
    private BasicFragment basicFragment;
    private FragmentTransaction fragmentTransaction;
    private LinearLayout fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_example);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
//        webView.loadUrl("http://www.example.com");
//        WebSettings webSettings=webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
////       webView.loadUrl();
           fragment= (LinearLayout) findViewById(R.id.content_fragment);
        fragment.setVisibility(View.GONE);
           fragmentTransaction =getSupportFragmentManager().beginTransaction();

        initRecyclerView();
        initFragment();
    }

    /**
     * 初始化RecyclerView()
     * */
    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        webViewExampleAdapter=webViewExampleAdapter!=null?webViewExampleAdapter:new WebViewExampleAdapter(this);
        webViewExampleAdapter.setWebviewExampleList(getData());
        webViewExampleAdapter.setCallBackRecyclerView(new callBackRecyclerView() {
            @Override
            public void findFragmentName(String fragmentName) {
                fragment.setVisibility(View.VISIBLE);
                //加载fragment
                recyclerView.setVisibility(View.GONE);
                fragmentTransaction.replace(R.id.content_fragment,basicFragment);

                fragmentTransaction.commit();
            }
        });
        recyclerView.setAdapter(webViewExampleAdapter);
    }


    /**
     * 初始化数据加载
     * */
    private List<String> getData(){
        if (fragmentNameList==null){
            fragmentNameList=new ArrayList<>();
        }
        fragmentNameList.add("basicFragment");
        return fragmentNameList;
    }


    /***
     *
     * 初始化fragment
     *
     * */
    private void initFragment(){
        basicFragment= new BasicFragment();

    }

    @Override
    public void hideFragmentName(String fragMentName) {
        recyclerView.setVisibility(View.VISIBLE);
        fragment.setVisibility(View.GONE);

    }
    //将创建好的Fragment存放到数组中
}
