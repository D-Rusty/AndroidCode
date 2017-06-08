package com.project.onepice.travel.Scenic;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nispok.snackbar.Snackbar;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.project.onepice.travel.R;
import com.project.onepice.travel.ScenicDetails.ScenicDetailsActivity;
import com.project.onepice.travel.data.Scenic;
import com.project.onepice.travel.util.CallBack.ICallBackScenicFragmen;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by onepice2015 on 2016/10/15.
 */

public class ScenicFragment extends Fragment implements ScenicContract.View {

    @BindView(R.id.location)
    TextView location;

    @BindView(R.id.scenicrecyclerview)
    RecyclerView scenicRecyclerView;

    @BindView(R.id.progress_wheel)
    ProgressWheel progressWheel;

    @BindView(R.id.progress_layout)
    LinearLayout progress_layout;

    @BindView(R.id.no_data)
    TextView no_data;

    private ScenicRecyclerViewAdapter adpater;

    private LinearLayoutManager linearLayoutManager;

    private ScenicContract.Presenter mPresenter;

    private boolean loading = false;
    private boolean bottom = false;

    private int totalItemCount;
    private int lastVisibleItem;

    private boolean isLocationAuto = true;
    private boolean isScollRefursh = false;
    private ICallBackScenicFragmen callBack;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBack = (ICallBackScenicFragmen) getActivity();
    }

    public static ScenicFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString("fragment_id", id);
        ScenicFragment fragment = new ScenicFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenic, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }


    public void initData() {
        if (isLocationAuto) {
            //获取城市位置
            mPresenter.getCityId("");
        }
    }

    @Override
    public void refresh(ArrayList<Scenic> scenicsArray) {
        progress_layout.setVisibility(View.GONE);

        if (adpater == null) {
            adpater = new ScenicRecyclerViewAdapter(getContext(),new ScenicRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, Scenic data) {
                    Intent intent = new Intent(getContext(), ScenicDetailsActivity.class);
                    intent.putExtra("scenic", (Scenic) view.getTag());
                    getContext().startActivity(intent);
                }
            });
            scenicRecyclerView.setAdapter(adpater);
        }

        if (scenicsArray == null || scenicsArray.size() == 0) {
            Snackbar.with(getContext()) // context
                    .text("没有更多数据了") // text to display
                    .duration(Snackbar.SnackbarDuration.LENGTH_SHORT) // make it shorter
                    .show(getActivity()); // activity where it is displayed
        } else {
            if (adpater.getScenicArrayList() != null) {
                adpater.getScenicArrayList().addAll(scenicsArray);
            } else {
                adpater.setScenicArrayList(scenicsArray);
            }
            adpater.notifyDataSetChanged();

        }


        if (adpater.getScenicArrayList() == null || adpater.getScenicArrayList().size() == 0) {
            no_data.setVisibility(View.VISIBLE);
            scenicRecyclerView.setVisibility(View.GONE);
        } else {
            no_data.setVisibility(View.GONE);
            scenicRecyclerView.setVisibility(View.VISIBLE);
        }


    }

    private void initRecyclerView() {
        progressWheel.spin();
        linearLayoutManager = new LinearLayoutManager(getContext());
        scenicRecyclerView.setLayoutManager(linearLayoutManager);
        scenicRecyclerView.setItemAnimator(new DefaultItemAnimator());
        scenicRecyclerView.setHasFixedSize(true);

        if (adpater == null) {
            adpater = new ScenicRecyclerViewAdapter(getContext(), new ScenicRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, Scenic data) {
                    Intent intent = new Intent(getContext(), ScenicDetailsActivity.class);
                    intent.putExtra("scenic", (Scenic) view.getTag());
                    getContext().startActivity(intent);
                }
            });
        }
        scenicRecyclerView.setAdapter(adpater);

        scenicRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visisbleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();

                if ((visisbleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE && (lastVisibleItem) >= totalItemCount - 1)) {
                    mPresenter.getScenicList(getLocation(), lastVisibleItem + "");
                    isScollRefursh = true;
                }

            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adpater != null) {
                    adpater = null;
                }
                callBack.startCity(getLocation());
            }
        });


    }


    @Override
    public String getLocation() {
        return location.getText().toString();
    }

    @Override
    public void setLocation(String locationText) {
        if (locationText.contains("select:")) {
            isScollRefursh = false;
            location.setText(locationText.substring(locationText.indexOf(":") + 1, locationText.length()));
            mPresenter.getCityId(locationText.substring(locationText.indexOf(":") + 1, locationText.length()));
            isLocationAuto = false;
        } else {
            location.setText(locationText);
        }
    }

    @Override
    public void setPresenter(ScenicContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
