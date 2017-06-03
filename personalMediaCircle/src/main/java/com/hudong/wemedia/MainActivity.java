package com.hudong.wemedia;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hudong.wemedia.business.connections.ConnectionsFragment;
import com.hudong.wemedia.business.discovery.DiscoveryFragment;
import com.hudong.wemedia.business.message.MessageFragment;
import com.hudong.wemedia.business.person.PersonFragment;
import com.hudong.wemedia.basiccomponent.Constant;
import com.hudong.wemedia.basiccomponent.utils.Utils;
import com.hudong.wemedia.basiccomponent.utils.listener.MyOnTouchListener;
import com.hudong.wemedia.business.welcome.WelComeFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.main_content)
    LinearLayout main_content;
    @BindView(R.id.rg_main_activity)
    RadioGroup rg_main_activity;

    private FragmentManager fragmentManager;
    private Fragment hiddFragment = null;// 正在被加载的fragment
    private ArrayList<Fragment> fragmentList;


    private Fragment welComeFragment = null;
    private Fragment connectionsFragment = null;
    private Fragment messageFragment = null;
    private Fragment discoveryFragment = null;
    private Fragment personFragment = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        initAdapter();
        initListener();
    }


    public void initAdapter() {
        //将首页五大模块的fragment作为基本数据加载
        fragmentList = new ArrayList<>();
        if (hiddFragment == null) {

            welComeFragment = new WelComeFragment();
            connectionsFragment = new ConnectionsFragment();
            messageFragment = new MessageFragment();
            discoveryFragment = new DiscoveryFragment();
            personFragment = new PersonFragment();

            fragmentList.add(welComeFragment);
            fragmentList.add(connectionsFragment);
            fragmentList.add(messageFragment);
            fragmentList.add(discoveryFragment);
            fragmentList.add(personFragment);
            fragmentManager = getSupportFragmentManager();
            refushFragment(fragmentList.get(0), null);
        }
    }

    public void initListener() {
        //主页底部按钮监听
        rg_main_activity.setOnCheckedChangeListener(this);
    }

    public void doDirection(String direction) {
        //当手指滑动方向为左方且当前显示fragment不是整个首页第一个fragment时允许将当前fragment切换为前一模块fragment
        //当手指滑动方向为右方且当前显示fragment不是整个首页最后一个fragment时允许将当前fragment切换为后一模块fragment
        if ((direction.equals(Constant.LEFT) &&
                hiddFragment.getClass().getName().equals(fragmentList.get(0).getClass().getName())) ||
                (direction.equals(Constant.RIGHT) &&
                        hiddFragment.getClass().getName().equals(fragmentList.get(fragmentList.size() - 1)
                                .getClass().getName()))) {
            return;
        }
        //切换fragment同时设置底部效果
        setBottomTextBg(getIndex(hiddFragment, direction));
    }

    //获取当前等待显示的framgment坐标
    private int getIndex(Fragment fragmentName, String action) {
        int index = 0;
        for (int i = 0; i < fragmentList.size(); i++) {
            if (fragmentName.getClass().getName().equals(fragmentList.get(i).getClass().getName())) {
                index = i;
            }
        }
        return action.equals(Constant.LEFT) ? index - 1 > 0 ? --index : 0 : ++index;
    }

    //执行fragment显示替换操作
    private void refushFragment(Fragment showFragment, Fragment currentFragment) {
        for (int i = 0; i < fragmentList.size(); i++) {
            if (fragmentList.get(i).getClass().getName().equals(showFragment.getClass().getName())) {
                showBottom();
                break;
            } else {
                if (i == fragmentList.size() - 1) {
                    hiddenBottom();
                }
            }
        }
        this.hiddFragment = Utils.managerFragment(showFragment, currentFragment, fragmentManager, R.id.main_content);
    }

    //设置底部背景图片
    private void setBottomTextBg(int position) {
        rg_main_activity.clearCheck();
        ((RadioButton) findViewById(rg_main_activity.getChildAt(position).getId())).setChecked(true);
    }

    //隐藏底部按钮块
    public void hiddenBottom() {
        rg_main_activity.setVisibility(View.GONE);
    }

    //显示底部按钮块
    public void showBottom() {
        rg_main_activity.setVisibility(View.VISIBLE);
    }

    //受特殊条件影响需要显示非五大模块是fragment方法
    public void notifyFragmentShow(Fragment showFragment, Fragment currentFragment) {
        refushFragment(showFragment, currentFragment);
    }


    public void deleteFragmentList(Fragment fragment) {
        fragmentList.remove(fragment);
        refushFragment(fragmentList.get(0), fragment);
        showBottom();
    }

    public void backChildFragment(Fragment fragment, Fragment currentFragment) {
        refushFragment(fragment, currentFragment);
        Utils.managerBackChildFragment(currentFragment, fragmentManager);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        refushFragment(fragmentList.get(group.indexOfChild(group.findViewById(checkedId))), hiddFragment);
    }

    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK && event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private MyOnTouchListener myTouchListener;

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

    private final int REQUEST_PHONE_PERMISSIONS = 0;


}
