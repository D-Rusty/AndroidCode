package com.project.onepice.basicproject.androidBasic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.project.onepice.basicproject.BaseFragment;
import com.project.onepice.basicproject.R;
import com.project.onepice.basicproject.BasicRecyclerViewAdapter;
import com.project.onepice.basicproject.bean.Section;
import com.project.onepice.basicproject.callback.CallBackRecyclerViewApdaterItem;
import com.project.onepice.basicproject.callback.SectionStateChangeListener;
import com.project.onepice.basicproject.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by onepice2015 on 2017/1/18.
 */

public class AndroidBasicFragment extends BaseFragment implements SectionStateChangeListener {

    @Bind(R.id.basic_recyler_content)
    RecyclerView basic_recyler_content;

    @Bind(R.id.basic_android_item)
    LinearLayout basic_android_item;
    private String currentFragment="";// 正在被加载的fragment
    private BasicRecyclerViewAdapter adapter;

    private FragmentManager fragmentManager;

    private ArrayList<Object> adapterList;

    private Map<String, ArrayList<String>> hashMap;
    private Map<Section, ArrayList<String>> hashCacheMap;

    private String[] fragmentList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_android_basic;
    }

    @Override
    public void initView() {

    }

    @Override
    public void relecyResource() {

    }

    private void initLister() {

    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentManager = getChildFragmentManager();
        initData();
        IntentFilter filter = new IntentFilter();
        filter.addAction("packFragment");
        getContext().registerReceiver(receiver, filter);
    }


    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(receiver);
    }

    private void initData() {
        getData();
        getFragmentData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        adapter = new BasicRecyclerViewAdapter(basic_recyler_content, getContext(), gridLayoutManager, this);
        adapter.setFragmentList(adapterList);
        basic_recyler_content.setAdapter(adapter);
        adapter.setCallBack(new CallBackRecyclerViewApdaterItem() {
            @Override
            public void recyclerViewItemOnClick(String showFragmentName) {
                managerFragment(showFragmentName);
            }
        });
        basic_recyler_content.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 加载基础数据
     */
    public void getData() {
        fragmentList = getResources().getStringArray(R.array.basic_android_item);
        ArrayList<Object> mArrayList = new ArrayList<>();
        hashMap = new HashMap<>();
        hashCacheMap=new HashMap<>();
        for (String fName : fragmentList) {
            fName = fName.substring(fName.lastIndexOf(AndroidBasicFragment.class.getPackage().toString()) +
                    AndroidBasicFragment.class.getPackage().toString().length() -6, fName.length());
            if (hashMap.containsKey(fName.substring(0,fName.lastIndexOf(".")))){
                ArrayList<String> arrayList = hashMap.get(fName.substring(0,fName.lastIndexOf(".")));
                arrayList.add(fName.substring(fName.lastIndexOf(".") + 1, fName.lastIndexOf("E")));
            }else{
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(fName.substring(fName.lastIndexOf(".") + 1, fName.lastIndexOf("E")));
                hashMap.put(fName.substring(0, fName.lastIndexOf(".")), arrayList);
            }
        }

        for (Map.Entry<String,ArrayList<String>> entry: hashMap.entrySet()){
            hashCacheMap.put(new Section(entry.getKey()),entry.getValue());
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            basic_recyler_content.setVisibility(View.VISIBLE);
            basic_android_item.setVisibility(View.GONE);
            currentFragment=Utils.managerFragment(AndroidBasicFragment.class.getName(), currentFragment, fragmentManager, R.id.basic_android_item);
        }
    };


    private void getFragmentData() {
        adapterList = new ArrayList<>();
        for (Map.Entry<Section, ArrayList<String>> entry : hashCacheMap.entrySet()) {
            adapterList.add(entry.getKey());
            if (((Section) entry.getKey()).isExpanded)
                for (String fname : entry.getValue()) {
                    adapterList.add(fname);
                }
        }
    }

    @Override
    public void onSectionStateChanged(String postion) {
        if (!basic_recyler_content.isComputingLayout()) {
            adapterList.clear();
            for (Map.Entry<Section, ArrayList<String>> entry : hashCacheMap.entrySet()) {
                if (entry.getKey().getName().equals(postion)) {
                    entry.getKey().isExpanded = !entry.getKey().isExpanded;
                }
            }
            getFragmentData();
            adapter.setFragmentList(adapterList);
            adapter.notifyDataSetChanged();
        }
    }


    private void managerFragment(String showFragmentName) {
        //加载嵌套fragment
        basic_recyler_content.setVisibility(View.GONE);
        basic_android_item.setVisibility(View.VISIBLE);
        for (String fName : fragmentList) {
            if (fName.toUpperCase().contains(showFragmentName.toUpperCase())) {
                currentFragment=Utils.managerFragment(fName, currentFragment, fragmentManager, R.id.basic_android_item);
            }
        }
    }
}
