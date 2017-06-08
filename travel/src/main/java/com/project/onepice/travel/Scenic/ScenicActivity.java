package com.project.onepice.travel.Scenic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.idescout.sql.SqlScoutServer;
import com.project.onepice.travel.R;
import com.project.onepice.travel.SelectCity.SelectCityActivity;
import com.project.onepice.travel.data.source.local.LocalDataRepository;
import com.project.onepice.travel.data.source.local.cityInfo.CityInfoLocalDataSource;
import com.project.onepice.travel.data.source.local.scenic.ScenicLocalDataSource;
import com.project.onepice.travel.util.CallBack.ICallBackScenicFragmen;
import com.project.onepice.travel.widgets.CheckPermissionsActivity;

public class ScenicActivity extends CheckPermissionsActivity implements ICallBackScenicFragmen {

    private Button location;

    private EditText serach_scenic;

    private ListView scenic_list;

    private ScenicFragment scenicFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment(getIntent());
        SqlScoutServer.create(this, getPackageName());
    }

    private void initFragment(Intent intent) {

        scenicFragment = (ScenicFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (scenicFragment == null) {
            scenicFragment = ScenicFragment.newInstance("SCENIC_FRAGMENT");
            transaction.add(R.id.contentFrame, scenicFragment);
        } else {
            transaction.replace(R.id.contentFrame, scenicFragment);

        }

        transaction.commit();
        new ScenicPresenter(getApplicationContext(), new LocalDataRepository(new CityInfoLocalDataSource(getApplicationContext()),
                new ScenicLocalDataSource(getApplicationContext())), scenicFragment);

    }

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    private static Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次后退键退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {

            this.finish();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000 && (data.getStringExtra("city") != null)) {
            scenicFragment.setLocation("select:" + data.getStringExtra("city"));
        }
    }

    @Override
    public void startCity(String location) {
        Intent intent = new Intent(this, SelectCityActivity.class);
        intent.setAction("SelectCityActivity");
        intent.putExtra("location", location);
        startActivityForResult(intent, 2000);
    }


    @Override
    public void checkInfoResult(boolean isCheckId) {
        if (isCheckId) {
            if (scenicFragment != null) {
                scenicFragment.initData();
            }
        }
    }


}
