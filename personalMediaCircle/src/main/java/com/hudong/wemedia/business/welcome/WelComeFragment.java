package com.hudong.wemedia.business.welcome;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hudong.wemedia.BaseFragment;
import com.hudong.wemedia.R;
import com.hudong.wemedia.business.person.login.LoginFragment;

import java.util.List;

import butterknife.BindView;

import static com.hudong.wemedia.business.welcome.WelcomeContract.REQUEST_PHONE_PERMISSIONS;

/**
 * 作者: 方天文
 * 日期: 2017/4/12:上午9:01
 * 概况:
 */

public class WelComeFragment extends BaseFragment implements WelcomeContract.View, IWelComeCallBack {

    @BindView(R.id.vp_welcome)
    ViewPager vpWelcome;
    @BindView(R.id.iv_welcome)
    ImageView ivWelcome;


    private Handler handler = new Handler();

    private WelcomePresenter welcomePresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_welcome;
    }

    @Override
    protected void initViewsData() {
        setHeadVisibility(View.GONE);
        getHoldingActivity().hiddenBottom();
        welcomePresenter.checkPermission(getActivity());
        //初始化登录环境检查
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setPresenter() {
        welcomePresenter = new WelcomePresenter(getContext(), this);
    }


    @Override
    public void isLoginResultCallback(final boolean isLogin) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isLogin) {
                    getHoldingActivity().notifyFragmentShow(new LoginFragment(), WelComeFragment.this);
                }
                getHoldingActivity().deleteFragmentList(WelComeFragment.this);
            }
        }, 3000);
    }

    @Override
    public void checkPermissionCallback(List<String> permissionsList) {
        if (permissionsList.size() > 0) {
            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_PHONE_PERMISSIONS);
        } else {
            initView();
        }
    }

    @Override
    public void initView() {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.qidong);
        ivWelcome.setImageBitmap(bitmap);
        welcomePresenter.init();
    }


    /**
     * 权限结果的处理
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initView();
                } else {
                    Toast.makeText(getContext(), getString(R.string.need_permission), Toast.LENGTH_SHORT).show();
                    getHoldingActivity().finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}