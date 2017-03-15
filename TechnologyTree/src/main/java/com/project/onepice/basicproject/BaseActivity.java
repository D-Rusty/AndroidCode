package com.project.onepice.basicproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.project.onepice.basicproject.listener.MyOnTouchListener;

/**
 * Created by onepice2015 on 2017/1/15.
 */

public abstract class BaseActivity extends AppCompatActivity
        implements View.OnClickListener {


    MyOnTouchListener myTouchListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initAdapter();
        initListener();
    }

    public abstract void initView();

    public abstract void initAdapter();

    public abstract void initListener();

    public abstract void doDirection(String direction);

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (null != myTouchListener) {
            myTouchListener.onTouch(ev);
        }
        return super.dispatchTouchEvent(ev);
    }


    /**
     * 用于注册回调事件
     */
    public void registerMyTouchListener(MyOnTouchListener myTouchListener) {
        this.myTouchListener = myTouchListener;
    }

}
