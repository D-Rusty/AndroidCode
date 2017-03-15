package com.project.onepice.travel.SelectCity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.project.onepice.travel.R;
import com.project.onepice.travel.util.CallBack.ICallBackSelectCityFragment;
import com.project.onepice.travel.widgets.SectionedSpanSizeLookup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by onepice2015 on 2016/10/15.
 */

public class SelectCityFragment extends Fragment implements SelectCityContract.View {

    @BindView(R.id.location_recyclerview)
    RecyclerView location_recyclerview;

    @BindView(R.id.location_back)
    Button back_button;

    private SelectCityContract.Presenter presenter;

    private ICallBackSelectCityFragment callBack;

    private SelectCityAdapter selectCityAdapter;

    public Button getBack_button() {
        return back_button;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBack = (ICallBackSelectCityFragment) getActivity();
    }

    public static SelectCityFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString("fragment_id", id);
        SelectCityFragment fragment = new SelectCityFragment();
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
        View view = inflater.inflate(R.layout.select_city_fragment, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        back_button.setText(presenter.getLocation());

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.settingCityName(getBack_button().getText().toString());
            }
        });

        selectCityAdapter = new SelectCityAdapter(getContext());

        selectCityAdapter.setCall(new SelectCityAdapter.selectCityAapterCall() {
            @Override
            public void onItemClick(View view, String data) {
                back_button.setText(data);
            }
        });

        final GridLayoutManager gridlayoutmanaer = new GridLayoutManager(getContext(), 3);

        gridlayoutmanaer.setSpanSizeLookup(new SectionedSpanSizeLookup(selectCityAdapter, gridlayoutmanaer));

        location_recyclerview.setLayoutManager(gridlayoutmanaer);

        location_recyclerview.setHasFixedSize(true);

        location_recyclerview.setAdapter(selectCityAdapter);
    }

    @Override
    public void refresh(ArrayList<SelectCityVo> cityArrayList) {
        if (selectCityAdapter == null) {
            selectCityAdapter = new SelectCityAdapter(getContext());
            location_recyclerview.setAdapter(selectCityAdapter);
        }

        selectCityAdapter.setCityList(cityArrayList);

        selectCityAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(SelectCityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        presenter.getScenicList();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        callBack.settingCityName(back_button.getText().toString());
        super.onDestroy();
    }

}
