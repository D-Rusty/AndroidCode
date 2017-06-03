package com.hudong.wemedia.business.message;

import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.Toast;

import com.hudong.wemedia.BaseFragment;
import com.hudong.wemedia.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dyj on 2017/4/15.
 */

public class FragmentOne extends BaseFragment {
    @BindView(R.id.one_open)
    Button oneOpen;

    private Fragment fragment;

    //定义一个构造方法，传入父fragment

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initViewsData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), getArguments().getString("partent"), Toast.LENGTH_LONG).show();
        fragment = (Fragment) getArguments().getSerializable("partentFragment");
    }

    @Override
    protected void setListener() {

    }
    @Override
    protected void setPresenter() {

    }


    @OnClick(R.id.one_open)
    public void onClick() {
        getHoldingActivity().backChildFragment(fragment, this);
        //当子类任务结束需要返回到父类并需要往父类带参数时使用
//        RxBusManager.getInstance().post(RX_MARK_MESSAGEFRAGMENT, "地球收到了");

    }

}
