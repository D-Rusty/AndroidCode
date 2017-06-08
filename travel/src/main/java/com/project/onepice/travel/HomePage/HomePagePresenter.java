package com.project.onepice.travel.HomePage;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import com.project.onepice.travel.Scenic.ScenicActivity;
import com.project.onepice.travel.data.source.local.LocalDataSource;
import com.project.onepice.travel.util.SharedPreferencesHelper;

/**
 * Created by onepice2015 on 2016/11/9.
 */

public class HomePagePresenter implements HomePageContract.Presenter {

    private final Context mContext;
    private final HomePageContract.View homePageView;
    private final LocalDataSource localDataSource;
    private HomePageCallBack homePageCallBack;

    public HomePagePresenter(Context context, LocalDataSource localDataSource, HomePageContract.View homePageView,
                             HomePageCallBack homePageCallBack) {
        this.mContext = context;
        this.homePageView = homePageView;
        this.localDataSource = localDataSource;
        this.homePageCallBack = homePageCallBack;
        homePageView.setPresenter(this);
    }


    @Override
    public void initSqlite() {
        new HomePageTask().execute();
    }







    class HomePageTask extends AsyncTask<Void, Void, Boolean> {
        boolean sqliteInit;
        @Override
        protected void onPreExecute() {
            //判断是否进行过数据初始化
            sqliteInit = (boolean) SharedPreferencesHelper.getInstance(mContext).get("sqlite_data_init", false);
            if (null != homePageView) {
                homePageView.showDiaolog();
            }
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return !sqliteInit ? localDataSource.initLocalSqlite() : false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                Toast.makeText(mContext, "数据添加成功", Toast.LENGTH_LONG).show();
                SharedPreferencesHelper.getInstance(mContext).put("sqlite_data_init", true);
            }
            if (null != homePageView) {
                homePageView.dissMissDiaolog();
            }

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //你需要跳转的地方的代码
                    mContext.startActivity(new Intent(mContext, ScenicActivity.class));
                    if (null != homePageView) {
                        homePageView.destroyActivity();
                    }
                }
            }, 3000); //延迟3秒跳转


            super.onPostExecute(aBoolean);
        }
    }


}
