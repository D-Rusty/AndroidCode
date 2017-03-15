package com.project.onepice.travel.HomePage;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f2prateek.progressbutton.ProgressButton;
import com.project.onepice.travel.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by onepice2015 on 2016/11/9.
 */

public class HomePageFragment extends Fragment implements HomePageContract.View{

    @BindView(R.id.progress)
    ProgressButton progressButton;

    private HomePageContract.Presenter mPresenter;


    public static HomePageFragment newInstance(String id){
        Bundle args=new Bundle();
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
        View root = inflater.inflate(R.layout.fragment_homepage,container,false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onResume() {
        mPresenter.initSqlite();
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
        if (getActivity()!=null){
            getActivity().onBackPressed();
        }

    }


    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        this.mPresenter=presenter;
    }
}
