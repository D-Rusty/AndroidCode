package com.hudong.wemedia.business.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hudong.wemedia.BaseFragment;
import com.hudong.wemedia.R;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * 作者: 方天文
 * 日期: 2017/4/12:上午10:48
 * 概况:
 */

public class MessageFragment extends BaseFragment implements MessageContract.View {
    @BindView(R.id.message_opne)
    Button messageOpne;

    @BindView(R.id.ones_ss)
    LinearLayout ones_ss;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }


    @Override
    protected void initViewsData() {

    }


    @Override
    protected void setListener() {

    }

    @Override
    protected void setPresenter() {

    }

    @OnClick(R.id.message_opne)
    public void onClick() {
        //如果你需要传递参数给子fragment的话
        FragmentOne fragment = new FragmentOne();
        Bundle bundle = new Bundle();
        bundle.putString("partent", "partent");
        bundle.putSerializable("partentFragment", this);
        fragment.setArguments(bundle);
        getHoldingActivity().notifyFragmentShow(fragment, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    Observable<Object> observable = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        RxBus.get().post();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
