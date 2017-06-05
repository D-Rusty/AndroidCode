package com.project.onepice.travel.HomePage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f2prateek.progressbutton.ProgressButton;
import com.project.onepice.travel.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.project.onepice.travel.HomePage.HomePageContract.REQUEST_PHONE_PERMISSIONS;


/**
 * Created by onepice2015 on 2016/11/9.
 */

public class HomePageFragment extends Fragment implements HomePageContract.View, HomePageCallBack {

    @BindView(R.id.progress)
    ProgressButton progressButton;

    private HomePageContract.Presenter mPresenter;

    private boolean isNeedCheck = true;

    public static HomePageFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString("fragment_id", id);
        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_homepage, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onResume() {
        if (isNeedCheck) {
            mPresenter.checkPermission(getActivity());
        } else {
            mPresenter.initSqlite();
        }
        super.onResume();
    }


    @Override
    public void showDiaolog() {
        progressButton.setCircleColor(Color.RED);
        progressButton.setAnimationSpeed(15);
        progressButton.startAnimating();
        progressButton.setVisibility(View.VISIBLE);

    }

    @Override
    public void dissMissDiaolog() {
        progressButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void destroyActivity() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }

    }


    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void checkPermissionCallback(List<String> permissionsList) {
        if (null != permissionsList
                && permissionsList.size() > 0) {
            ActivityCompat.requestPermissions(getActivity(),
                    permissionsList.toArray(
                            new String[permissionsList.size()]),
                    REQUEST_PHONE_PERMISSIONS);
        } else {
            mPresenter.initSqlite();
        }

    }


    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 权限结果的处理
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PHONE_PERMISSIONS) {
            if (!verifyPermissions(grantResults)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            } else {
                mPresenter.initSqlite();
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().onBackPressed();
                        getActivity().finish();
                    }
                });

        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isNeedCheck=false;
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
        startActivityForResult(intent, 9999);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9999) {
            isNeedCheck = true;
        }
    }
}
