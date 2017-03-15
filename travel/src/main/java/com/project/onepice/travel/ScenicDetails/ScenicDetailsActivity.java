package com.project.onepice.travel.ScenicDetails;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.project.onepice.travel.R;
import com.project.onepice.travel.data.Scenic;
import com.project.onepice.travel.data.source.remote.RemoteDataRepository;

public class ScenicDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic_details);


        ScenicDetailsFragment scenicDetailsFragment = (ScenicDetailsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.details_contentFrame);

        if (scenicDetailsFragment == null) {
            scenicDetailsFragment = ScenicDetailsFragment.newInstance("details_contentFrame");
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.details_contentFrame, scenicDetailsFragment);
        transaction.commit();

        new ScenicDetailsPresenter(getApplicationContext(), new RemoteDataRepository(), scenicDetailsFragment, (Scenic) getIntent().getSerializableExtra("scenic"));

    }

}
