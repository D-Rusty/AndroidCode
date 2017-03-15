package com.project.onepice.travel.SelectCity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.project.onepice.travel.R;
import com.project.onepice.travel.data.source.local.LocalDataRepository;
import com.project.onepice.travel.data.source.local.cityInfo.CityInfoLocalDataSource;
import com.project.onepice.travel.util.CallBack.ICallBackSelectCityFragment;

public class SelectCityActivity extends AppCompatActivity implements ICallBackSelectCityFragment {

    private SelectCityFragment cityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        initFragment(getIntent());
    }

    private void initFragment(Intent intent) {
        cityFragment = (SelectCityFragment) getSupportFragmentManager()
                .findFragmentById(R.id.city_contentFrame);
        if (cityFragment == null) {
            cityFragment = SelectCityFragment.newInstance("SELECTCITY_FRAGMENT");
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.city_contentFrame, cityFragment);
        transaction.commit();

        new SelectCityPresenter(getApplicationContext(), new LocalDataRepository(new CityInfoLocalDataSource(getApplicationContext())),
                cityFragment, intent.getStringExtra("location"));

    }

    @Override
    public void settingCityName(String location) {
        Intent intent = new Intent();
        intent.putExtra("city", location);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        settingCityName(cityFragment.getBack_button().getText().toString());
        return super.onKeyDown(keyCode, event);
    }
}
