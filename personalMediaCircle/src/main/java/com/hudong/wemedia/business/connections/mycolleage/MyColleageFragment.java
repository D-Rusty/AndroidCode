package com.hudong.wemedia.business.connections.mycolleage;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hudong.wemedia.BaseFragment;
import com.hudong.wemedia.R;
import com.hudong.wemedia.basiccomponent.widgets.MyColleageBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dyj on 2017/4/17.
 */

public class MyColleageFragment extends BaseFragment implements View.OnClickListener, MyColleageContract.MyColleageView {

    private static final int REQUESCODE_COLLEAGE = 100;
    @BindView(R.id.act_contact_base_search)
    RelativeLayout mActContactBaseSearch;
    @BindView(R.id.act_contact_base_recycler)
    RecyclerView mActContactBaseRecycler;
    @BindView(R.id.act_contact_base_error_tv)
    TextView mActContactBaseErrorTv;
    @BindView(R.id.act_contact_base_error)
    RelativeLayout mActContactBaseError;
    @BindView(R.id.act_contact_base_slidbar)
    MyColleageBar mActContactBaseSlidbar;
    private LinearLayout mLinearAddColleage;
    private View mPopColleage;
    private PopupWindow mColleagePopupWindow;
    private RelativeLayout mRelDissPop;
    private LinearLayout mDelColleage;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_colleage;
    }

    @Override
    protected void initViewsData() {
        setHeadVisibility(View.VISIBLE);
        setTitle("我的同事", true, false, true);
        getLeftButton().setOnClickListener(this);
        getRightImageView().setOnClickListener(this);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setPresenter() {

    }
    @OnClick({R.id.act_contact_base_search, R.id.act_contact_base_error})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_title_back:
                //返回上一个界面
                break;
            case R.id.iv_right:
                //弹出pop
                showPop();
                break;
//            case R.id.act_contact_base_search:
//                startActivity(new Intent(this, WediaSearchActivity.class));
//                //搜索
//                break;
//            case R.id.act_contact_base_error:
//                //error
//                startActivity(new Intent(this, WediaSearchActivity.class));
//                break;
//            case R.id.linear_add_colleage:
//                Intent intent = new Intent(this, ContactMyFriendActivity.class);
//                intent.setAction("addColleage");
//                startActivityForResult(intent, REQUESCODE_COLLEAGE);
//                dismissPopupWindow();
//                break;
//            case R.id.pop_add_colleage:
//                //取消PopupWindow
//                dismissPopupWindow();
//                break;
//            case R.id.pop_del_colleage:
//                //TODO 转移同事
//                shiftColleage();
//                break;
        }
    }

    @Override
    public void onResultSuccess() {
        mActContactBaseRecycler.setVisibility(View.VISIBLE);
        mActContactBaseSlidbar.setVisibility(View.VISIBLE);
        mActContactBaseError.setVisibility(View.GONE);
    }

    @Override
    public void onResultEmpty() {
        mActContactBaseRecycler.setVisibility(View.GONE);
        mActContactBaseSlidbar.setVisibility(View.GONE);
        mActContactBaseError.setVisibility(View.VISIBLE);
        mActContactBaseErrorTv.setText("邀请同事加入自媒圈,方便工作生活");
    }

    @Override
    public void showPop() {
        if (mPopColleage == null) {
            mPopColleage = View.inflate(getContext(), R.layout.pop_add_collleage, null);
            mLinearAddColleage = (LinearLayout) mPopColleage.findViewById(R.id.linear_add_colleage);
            mRelDissPop = (RelativeLayout) mPopColleage.findViewById(R.id.pop_add_colleage);
            mDelColleage = (LinearLayout) mPopColleage.findViewById(R.id.pop_del_colleage);
            mRelDissPop.setOnClickListener(this);
            mLinearAddColleage.setOnClickListener(this);
            mDelColleage.setOnClickListener(this);
        }
        if (mColleagePopupWindow == null) {
            mColleagePopupWindow = new PopupWindow(mPopColleage, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mColleagePopupWindow.setFocusable(true);
            mColleagePopupWindow.setOutsideTouchable(true);
            mColleagePopupWindow.setBackgroundDrawable(new ColorDrawable());
        }
        mColleagePopupWindow.showAsDropDown(getRightImageView(), 0, 0);
    }
}
