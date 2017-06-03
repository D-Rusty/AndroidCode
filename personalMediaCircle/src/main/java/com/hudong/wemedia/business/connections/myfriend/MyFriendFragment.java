package com.hudong.wemedia.business.connections.myfriend;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hudong.wemedia.BaseFragment;
import com.hudong.wemedia.R;
import com.hudong.wemedia.basiccomponent.bean.Connections;
import com.hudong.wemedia.basiccomponent.utils.Print;
import com.hudong.wemedia.basiccomponent.widgets.DividerDecoration;
import com.hudong.wemedia.basiccomponent.widgets.MyFriendSlidBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by dyj on 2017/4/17.
 */

public class MyFriendFragment extends BaseFragment implements IMyFriendCallBack, MyFriendContract.MyFriendView, View.OnClickListener, MyFriendAdapter.OnContactItemClickListener {

    @BindView(R.id.tv_title_name1)
    TextView mTvTitleName1;
    @BindView(R.id.contact_my_friend_btn_back)
    Button mContactMyFriendBtnBack;
    @BindView(R.id.activity_contactmy_friend_cancel)
    TextView mActivityContactmyFriendCancel;
    @BindView(R.id.contact_my_friend_add_right_iv)
    ImageView mContactMyFriendAddRightIv;
    @BindView(R.id.activity_contactmy_friend_confim)
    TextView mActivityContactmyFriendConfim;
    @BindView(R.id.act_contact_my_friend_search)
    RelativeLayout mActContactMyFriendSearch;
    @BindView(R.id.act_contact_my_friend_normal_recycler)
    RecyclerView mActContactMyFriendNormalRecycler;
    @BindView(R.id.act_contact_my_friend_slidbar)
    MyFriendSlidBar mActContactMyFriendSlidbar;
    @BindView(R.id.act_contact_my_friend_serion_tv)
    TextView mActContactMyFriendSerionTv;
    @BindView(R.id.act_contact_my_friend_null_tv)
    TextView mActContactMyFriendNullTv;
    @BindView(R.id.act_contact_my_friend_null)
    LinearLayout mActContactMyFriendNull;
    @BindView(R.id.act_contact_my_friend_content)
    RelativeLayout mActContactMyFriendContent;

    private static final int EWMCODE = 8000;
    private MyFriendAdapter mContactMyFriendNormalAdapter;
    private MyFriendContract.MyFriendPresenter mContactMyFriendPresenter;
    boolean isCreateDiscussGroup = false;
    private TextView mMPopContactMyFriendAddFriend;
    private TextView mMPopContactMyFriendScan;
    private View mAddFriendPop;
    private PopupWindow mAddFriendPopupWindow;
    private List<Connections> mCustomers = null;
    private boolean isAddCustomer;//添加客户标志位
    private boolean isSupplier;// 添加供应商成员的标志位
    private Toast mToast;
    private String groupName = "好友";
    private RelativeLayout mPopContactFriendLinear;
    private boolean mIsColleage = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_my_friend;
    }

    @Override
    protected void initViewsData() {
        //设置头部不可见
        setHeadVisibility(View.GONE);
        Bundle intent = getArguments();
        if (intent != null) {
            String action = intent.getString("action");
            if (null != action) {
                if (action.contains("addCustomer")) {
                    isAddCustomer = true;
                    isSupplier = false;
                } else if (action.contains("supplier")) {
                    isSupplier = true;
                    isAddCustomer = false;
                } else if (action.contains("connections")) {
                    mTvTitleName1.setText("我的同事");
                    mContactMyFriendAddRightIv.setVisibility(View.GONE);
                    groupName = intent.getString("groupName");
                } else if (action.contains("addColleage")) {
                    mIsColleage = true;
                } else if (action.contains("commentFriend")) {
                    groupName = intent.getString("commentFriend");
                }
            }

        }
        //设置适配器
        mContactMyFriendNormalAdapter = new MyFriendAdapter(getContext());
        mActContactMyFriendNormalRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mActContactMyFriendNormalRecycler.setAdapter(mContactMyFriendNormalAdapter);
        mActContactMyFriendNormalRecycler.addItemDecoration(new DividerDecoration(getContext()));
        mContactMyFriendNormalAdapter.setOnContactItemClickListener(this);

        if (groupName.contains("1")) {
            mContactMyFriendPresenter.getServerConnection(groupName);
        } else {
            mContactMyFriendPresenter.getContectionList(groupName);
        }
    }

    @Override
    protected void setListener() {
        //给recycler设置滑动监听,当滑动时关闭已经打开的条目
        mActContactMyFriendNormalRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mContactMyFriendNormalAdapter.closeOpenedSwipeItemLayoutWithAnim();
            }
        });
    }

    @Override
    protected void setPresenter() {
        mContactMyFriendPresenter = new MyFriendPresenterImpl(this);
    }

    /**
     * 从数据库中获取数据
     *
     * @param connectionsList
     */
    @Override
    public void getDataResult(List<Connections> connectionsList) {
        if (isAddCustomer || isSupplier || mIsColleage)
            selectFriend();
        if (connectionsList.size() != 0) {
            mContactMyFriendNormalAdapter.updateContacts(connectionsList);
            onResultSuccess();
        } else {
            onResultEmpty();
        }
    }

    @Override
    public void onResultFriend2Customer(boolean isSuccess, String friendID) {

    }

    /**
     * get data success
     */
    @Override
    public void onResultSuccess() {
        mActContactMyFriendNull.setVisibility(View.GONE);
        //侧边导航栏设置可见
        mActContactMyFriendSlidbar.setVisibility(View.VISIBLE);
    }

    /**
     * data empty
     */
    @Override
    public void onResultEmpty() {
        //没有好友时
        mActContactMyFriendNull.setVisibility(View.VISIBLE);
        //侧边导航栏设置不可见
        mActContactMyFriendSlidbar.setVisibility(View.GONE);
    }

    /**
     * 弹出Pop
     */
    @Override
    public void showPop() {
        if (mAddFriendPop == null) {
            mAddFriendPop = View.inflate(getActivity(), R.layout.pop_contact_my_freind, null);
            mMPopContactMyFriendAddFriend = (TextView) mAddFriendPop.findViewById(R.id.pop_contact_my_friend_add_friend);
            mMPopContactMyFriendScan = (TextView) mAddFriendPop.findViewById(R.id.pop_contact_my_friend_scan);
            mPopContactFriendLinear = (RelativeLayout) mAddFriendPop.findViewById(R.id.pop_contact_my_friend_pop);
            mMPopContactMyFriendAddFriend.setOnClickListener(this);
            //扫一扫
            mMPopContactMyFriendScan.setOnClickListener(this);
            mPopContactFriendLinear.setOnClickListener(this);
        }
        if (mAddFriendPopupWindow == null) {
            mAddFriendPopupWindow = new PopupWindow(mAddFriendPop, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            mAddFriendPopupWindow.setFocusable(true);
            mAddFriendPopupWindow.setOutsideTouchable(true);
            mAddFriendPopupWindow.setBackgroundDrawable(new ColorDrawable());
        }
        mAddFriendPopupWindow.showAsDropDown(mContactMyFriendAddRightIv, 0, 0);
    }

    /**
     * pop消失
     */
    @Override
    public void dissPop() {
        //Pop消失
        if (mAddFriendPopupWindow != null) {
            mAddFriendPopupWindow.dismiss();
        }
    }

    /**
     * 打开扫一扫 TODO
     */
    @Override
    public void toScan() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Camera permission has not been granted.
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.CAMERA},
//                    Constant.REQUEST_PERMISSION_CAMERA);

        } else {
//            Intent intent = new Intent(getContext(), CaptureActivity.class);
//            startActivityForResult(intent, EWMCODE);
        }
    }

    /**
     * 隐藏选择框
     */
    @Override
    public void hideCheckBox() {
        //隐藏确定和取消
        mActivityContactmyFriendCancel.setVisibility(View.GONE);
        mActivityContactmyFriendConfim.setVisibility(View.GONE);
        //显示返回按钮
        mContactMyFriendBtnBack.setVisibility(View.VISIBLE);
        //显示加号按钮
        mContactMyFriendAddRightIv.setVisibility(View.VISIBLE);
        isCreateDiscussGroup = false;
        //隐藏ChekcBox
        if (mContactMyFriendNormalAdapter != null) {
            mContactMyFriendNormalAdapter.isShowCheckBox(false);
            mContactMyFriendNormalAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 选择好友转移
     */
    @Override
    public void selectFriend() {
        //打开CheckBox,让选择好友进行群聊
        isCreateDiscussGroup = true;
        //显示ChekcBox
        if (mContactMyFriendNormalAdapter != null) {
            mContactMyFriendNormalAdapter.notifyDataSetChanged();
            mContactMyFriendNormalAdapter.isShowCheckBox(true);
        }
        //显示确定和取消
        mActivityContactmyFriendCancel.setVisibility(View.VISIBLE);
        mActivityContactmyFriendConfim.setVisibility(View.VISIBLE);
        if (isAddCustomer)
            mTvTitleName1.setText("添加客户");
        if (isSupplier)
            mTvTitleName1.setText("添加供应商");
        if (mIsColleage)
            mTvTitleName1.setText("添加同事");
        //隐藏返回按钮
        mContactMyFriendBtnBack.setVisibility(View.GONE);
        //隐藏加号按钮
        mContactMyFriendAddRightIv.setVisibility(View.GONE);
    }

    @Override
    public void friendToCustomer() {
        if (mCustomers == null) {
            mCustomers = new ArrayList<>();
        }
        mCustomers.clear();
        mCustomers.addAll(mContactMyFriendNormalAdapter.getDiscussGroup());
        if (mCustomers == null || mCustomers.size() == 0) {
            if (isAddCustomer) {
                Print.toast(getContext(), "请选择客户");
            } else if (isSupplier) {
                Print.toast(getContext(), "请选择供应商");
            } else if (mIsColleage) {
                Print.toast(getContext(), "请选择同事");
            }
            return;
        } else {
            //获取选中好友的id
            String id = "";
            StringBuilder builder = new StringBuilder(id);
            for (int i = 0; i < mCustomers.size(); i++) {
                if (i != mCustomers.size() - 1) {
                    builder.append(mCustomers.get(i)
                            .getId() + ",");
                } else {
                    builder.append(mCustomers.get(i)
                            .getId());
                }
            }
            id = builder.toString();
            String groupId = "";
//            if (isAddCustomer) {
//                groupId = new GroupDao(this).getGroupId("客户");
//            } else if (isSupplier) {
//                groupId = new GroupDao(this).getGroupId("供应商");
//            } else if (mIsColleage) {
//                groupId = new GroupDao(this).getGroupId("同事");
//            }
            mContactMyFriendPresenter.friend2Customer("", id, groupId);
        }
    }


    //扫一扫的返回结果 TODO
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EWMCODE) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
//                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
//                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    String[] a = result.split("&id=");
//                    Intent intent = new Intent(this, NameCardActivity.class);
//                    intent.putExtra("personalId", a[1]);
//                    startActivity(intent);
//                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG)
//                            .show();
//                }
            }
        }
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.act_contact_my_friend_search,
            R.id.act_contact_my_friend_null,
            R.id.contact_my_friend_btn_back,
            R.id.contact_my_friend_add_right_iv,
            R.id.activity_contactmy_friend_cancel,
            R.id.activity_contactmy_friend_confim})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_contact_my_friend_search:
                //搜索,跳转到搜索的界面 TODO
//                Intent wediaSearch = new Intent(this, WediaSearchActivity.class);
//                startActivity(wediaSearch);
                break;
            case R.id.act_contact_my_friend_null:
                //TODO 跳转到搜索界面
//                Intent searchContact = new Intent(this, SearchEverythingActivity.class);
//                searchContact.putExtra(Constant.DIFFERENT_SEARCH, Constant.SEARCH_CONTACT);
//                startActivity(searchContact);
                break;
            case R.id.contact_my_friend_btn_back:
                //返回键 TODO
//                finish();
                break;
            case R.id.contact_my_friend_add_right_iv:
                //弹出添加好友/发起群聊/扫一扫PopupWindow
                showPop();
                break;
            case R.id.activity_contactmy_friend_cancel:
                //如果是添加客户就直接finish TODO
                if (isAddCustomer || isSupplier || mIsColleage) {
//                    finish();
                }
                //点击发起群聊之后的取消按钮点击事件
                hideCheckBox();
                break;
            case R.id.activity_contactmy_friend_confim:
                //点击发起群聊之后的确定按钮点击事件
                if (isAddCustomer || isSupplier || mIsColleage) {
                    //好友转客户
                    friendToCustomer();
                }
                break;
            case R.id.pop_contact_my_friend_add_friend:
                dissPop();
                //TODO 跳转到添加好友界面

                break;
            case R.id.pop_contact_my_friend_scan:
                //打开扫一扫
                dissPop();
                toScan();
                break;
            case R.id.pop_contact_my_friend_pop:
                dissPop();
                break;
        }

    }

    /**
     * 条目点击 跳转个人自媒圈
     *
     * @param conectionID
     * @param connections
     */
    @Override
    public void onItemClick(String conectionID, Connections connections) {
        //TODO
    }

    /**
     * 星标好友
     *
     * @param connectionsID
     * @param isStar
     */
    @Override
    public void onStarClick(String connectionsID, boolean isStar) {
        //TODO 星标好友
        mContactMyFriendPresenter.starFriend(isStar, "", connectionsID);
    }
}