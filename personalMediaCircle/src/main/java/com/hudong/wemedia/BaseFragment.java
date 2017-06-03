package com.hudong.wemedia;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.hudong.wemedia.basiccomponent.utils.listener.UtilsOnTouchListener;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 作者: 方天文
 * 日期: 2017/4/11:上午10:31
 * 概况: 所有fragment必须基于BaseFragment开发
 */

public abstract class BaseFragment extends Fragment implements Serializable {

    protected MainActivity mActivity;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.btn_title_back)
    Button btnTitleBack;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.layout_head)
    RelativeLayout layoutHead;
    @BindView(R.id.layout_container)
    ViewFlipper layoutContainer;

    /*获取布局文件ID*/
    protected abstract int getLayoutId();

    /*初始化界面布局*/
    protected abstract void initViewsData();

    /*初始化监听*/
    protected abstract void setListener();

    /*p层初始化*/
    protected abstract void setPresenter();

    /*获取宿主Activity*/
    protected MainActivity getHoldingActivity() {
        return mActivity;
    }

    protected GestureDetector gestureDetector;//手指滑动事件监听器

    protected Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (MainActivity) context;
        initOnTouchEvent();

    }

    /***
     * 初始化监听触摸事件
     */
    private void initOnTouchEvent() {
        gestureDetector = new GestureDetector(getActivity(), UtilsOnTouchListener.getOnGestureListener(mActivity));
        getHoldingActivity().registerMyTouchListener(UtilsOnTouchListener.getOnTouchListener(gestureDetector));

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.title_base, container, false);
        ((ViewFlipper) view.findViewById(R.id.layout_container)).addView(inflater.inflate(getLayoutId(), container, false));
        unbinder = ButterKnife.bind(this, view);
        initViewsData();
        setListener();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 设置头部是否可见 ,分割线也要隐藏
     *
     * @param visibility
     */
    public void setHeadVisibility(int visibility) {
        layoutHead.setVisibility(visibility);
    }


    @OnClick({R.id.btn_title_back, R.id.iv_search, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_title_back:
                break;
            case R.id.iv_search:
                break;
            case R.id.iv_right:
                break;
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title, boolean isShowLeft, boolean isShowSearch, boolean isShowRight) {
        tvTitleName.setText(title);
        if (isShowLeft) {
            btnTitleBack.setVisibility(View.VISIBLE);
        } else {
            btnTitleBack.setVisibility(View.INVISIBLE);
        }
        if (isShowSearch) {
            ivSearch.setVisibility(View.VISIBLE);
        } else {
            ivSearch.setVisibility(View.INVISIBLE);
        }
        if (isShowRight) {
            ivRight.setVisibility(View.VISIBLE);
        } else {
            ivRight.setVisibility(View.INVISIBLE);
        }

    }


    public ImageView getSearchImageView() {
        return ivSearch;
    }

    public ImageView getRightImageView() {
        return ivRight;
    }

    public Button getLeftButton() {
        return btnTitleBack;
    }

}
