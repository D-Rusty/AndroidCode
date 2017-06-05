package com.project.onepice.travel.HomePage;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.project.onepice.travel.R;
import com.project.onepice.travel.data.source.local.LocalDataRepository;
import com.project.onepice.travel.data.source.local.cityInfo.CityInfoLocalDataSource;
import com.project.onepice.travel.data.source.local.scenic.ScenicLocalDataSource;
import com.project.onepice.travel.widgets.CheckPermissionsActivity;


/**
 * 进入首页应该关掉动画页面
 **/


public class HomePageActivity extends CheckPermissionsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        HomePageFragment homePageFragment = (HomePageFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (homePageFragment == null) {
            homePageFragment = HomePageFragment.newInstance("HOMEPAGE_FRAGMENT");
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentFrame, homePageFragment);
        transaction.commit();
        new HomePagePresenter(getApplicationContext(), new LocalDataRepository(new CityInfoLocalDataSource(getApplicationContext()),
                new ScenicLocalDataSource(getApplicationContext())), homePageFragment, homePageFragment);

    }
}


































