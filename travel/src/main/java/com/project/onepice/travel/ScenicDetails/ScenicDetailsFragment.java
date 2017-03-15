package com.project.onepice.travel.ScenicDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.onepice.travel.R;
import com.project.onepice.travel.ScenicDetails.TicketDetails.TicketChildDetailsInfoFragment;
import com.project.onepice.travel.ScenicDetails.WeatherDetails.WeatherDetailsFragment;
import com.project.onepice.travel.data.Scenic;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by onepice2015 on 2016/10/15.
 */

public class ScenicDetailsFragment extends Fragment implements ScenicDetailsContact.View, AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.scenic_details_logo)
    ImageView scenic_details_logo;

    @BindView(R.id.scenic_details_bgImager)
    ImageView scenic_details_bgImager;

    @BindView(R.id.scenic_details_name)
    TextView scenic_details_name;

    @BindView(R.id.scenic_details_Into)
    TextView scenic_details_Into;

    @BindView(R.id.scenic_details_tablyout)
    TabLayout scenic_details_tablyout;

    @BindView(R.id.scenic_details_title_container)
    LinearLayout scenic_details_title_container;

    @BindView(R.id.scenic_details_toolbar)
    Toolbar scenic_details_toolbar;

    @BindView(R.id.scenic_details_viewpager)
    ViewPager scenic_details_viewpager;

    @BindView(R.id.scenic_details_appbar)
    AppBarLayout scenic_details_appbar;

    @BindView(R.id.scenic_map)
    FloatingActionButton scenic_map;

    private int mMaxScrollSize;
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private boolean mIsAvatarShown = true;

    private ScenicDetailsContact.Presenter mPresenter;

    private Fragment weatherDetailsFragment;
    private Fragment ticketChildDetailsFragment;

    private PopupWindow popupWindow;


    public static ScenicDetailsFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString("fragment_id", id);
        ScenicDetailsFragment fragment = new ScenicDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenic_details, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        scenic_details_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        scenic_details_appbar.addOnOffsetChangedListener(this);
        mMaxScrollSize = scenic_details_appbar.getTotalScrollRange();

        Scenic scenic = mPresenter.getScenic();

        Glide.with(getContext())
                .load("http://" + scenic.getSceniclogo())
                .into(scenic_details_bgImager);
        Glide.with(getContext())
                .load("http://" + scenic.getSceniclogo())
                .into(scenic_details_logo);

        scenic_details_name.setText(scenic.getScenicName_zh());

        scenic_details_Into.setText("欢迎来到" + scenic.getScenicName_zh());

        PagerAdapter pageAdapter = new PagerAdapter(getActivity().getSupportFragmentManager());
        pageAdapter.addTab(TicketChildDetailsInfoFragment.newInstance(mPresenter.getScenic().getScenicName_zh()), "票价详情");
        pageAdapter.addTab(WeatherDetailsFragment.newInstance(mPresenter.getScenic().getScenicSn()), "天气");
        scenic_details_viewpager.setAdapter(pageAdapter);
        scenic_details_tablyout.setupWithViewPager(scenic_details_viewpager);
        scenic_details_tablyout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                scenic_details_viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pageAdapter.notifyDataSetChanged();


    }


    @Override
    public void setPresenter(ScenicDetailsContact.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;
            scenic_details_logo.animate().scaleY(0).scaleX(0).setDuration(200).start();
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;
            scenic_details_logo.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }

    }


    class PagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> titles = new ArrayList<>();

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addTab(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    @OnClick({R.id.scenic_map})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scenic_map:
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);

                final View popUpWindowView = inflater.inflate(R.layout.pop_window_view, null);

                popupWindow = new PopupWindow(
                        popUpWindowView,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);


                TextView map_baidu = (TextView) popUpWindowView.findViewById(R.id.map_baidu);
                TextView map_gaode = (TextView) popUpWindowView.findViewById(R.id.map_gaode);
                TextView map_canle = (TextView) popUpWindowView.findViewById(R.id.map_canle);

                if (!mPresenter.isVaildLocalApp("com.autonavi.minimap")) {
                    map_gaode.setVisibility(View.GONE);
                }
                if (!mPresenter.isVaildLocalApp("com.baidu.BaiduMap")) {
                    map_baidu.setVisibility(View.GONE);
                }

                map_baidu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.startNavigation("baidu");
                        popupWindow.dismiss();
                    }
                });
                map_gaode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.startNavigation("gaode");
                        popupWindow.dismiss();
                    }
                });
                map_canle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                break;

        }

    }

}
