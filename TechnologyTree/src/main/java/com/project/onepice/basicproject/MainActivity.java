package com.project.onepice.basicproject;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.onepice.basicproject.utils.Commas;
import com.project.onepice.basicproject.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by onepice2015 on 2017/1/15.
 */

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_content)
    LinearLayout main_content;

    @Bind(R.id.main_bottom)
    LinearLayout linearLayout;

    private FragmentManager fragmentManager;

    private ClassLoader classLoader = null;

    private int chooesFragmentIdenx = 0;// 标记被选中的fragment坐标

    private String currentFragment;// 正在被加载的fragment

    private String[] bottomArray;

    private String[] fragmentList;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBottom();
    }

    @Override
    public void initAdapter() {
        fragmentList = getData();
        fragmentManager = getSupportFragmentManager();
        classLoader = ClassLoader.getSystemClassLoader();
        currentFragment = "";
        currentFragment = Utils.managerFragment(fragmentList[0], currentFragment, fragmentManager, R.id.main_content);
        setBottomTextBg();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < fragmentList.length; i++) {
            if (fragmentList[i].toUpperCase().contains(((String) view.getTag()).toUpperCase())) {
                chooesFragmentIdenx = i;
                currentFragment = Utils.managerFragment(fragmentList[i], currentFragment, fragmentManager, R.id.main_content);
                setBottomTextBg();
            }
        }
    }

    /**
     * 初始化底部按钮加载
     **/

    private void initBottom() {
        int width = Utils.getScreenWidth(this);
        bottomArray = getResources().getStringArray(R.array.main_bttom_item);
        LinearLayout.LayoutParams linearLayoutParam = new LinearLayout.LayoutParams(width / 4, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (String btName : bottomArray) {
            TextView textView = new TextView(this);
            textView.setText(btName);
            textView.setLayoutParams(linearLayoutParam);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, getResources().
                    getIdentifier(btName.toLowerCase(), "mipmap", getPackageName()), 0, 0);
            textView.setTextColor(Color.parseColor("#d2d2d2"));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(12);
            textView.setTag(btName);
            linearLayout.addView(textView);
            textView.setOnClickListener(this);
        }

    }

    @Override
    public void doDirection(String direction) {
        switch (direction) {
            case Commas.LEFT:
                //展示上一个fragment
                if (chooesFragmentIdenx == 0 && currentFragment.contains(fragmentList[0])) {
                    Toast.makeText(this, "上面没有了...", Toast.LENGTH_SHORT).show();
                    return;
                }
                chooesFragmentIdenx = (chooesFragmentIdenx - 1) > 0 ? --chooesFragmentIdenx : 0;
                break;
            case Commas.RIGHT:
                //展示下一个fragment
                if (chooesFragmentIdenx == fragmentList.length - 1) {
                    Toast.makeText(this, "没有下一个了", Toast.LENGTH_SHORT).show();
                    return;
                }
                ++chooesFragmentIdenx;
                break;
        }
        currentFragment = Utils.managerFragment(fragmentList[chooesFragmentIdenx], currentFragment, fragmentManager, R.id.main_content);
        setBottomTextBg();

    }


    private void setBottomTextBg() {
        for (String bottomItem : bottomArray) {
            if (bottomItem.equals(bottomArray[chooesFragmentIdenx])) {
                ((TextView) linearLayout.findViewWithTag(bottomItem)).setTextColor(Color.parseColor("#FF208F31"));
                ((TextView) linearLayout.findViewWithTag(bottomItem)).setCompoundDrawablesWithIntrinsicBounds(0,getResources().
                        getIdentifier(bottomItem.toLowerCase()+"_green", "mipmap", getPackageName()), 0, 0);
            } else {
                ((TextView) linearLayout.findViewWithTag(bottomItem)).setTextColor(Color.GRAY);
                ((TextView) linearLayout.findViewWithTag(bottomItem)).setCompoundDrawablesWithIntrinsicBounds(0, getResources().
                        getIdentifier(bottomItem.toLowerCase(), "mipmap", getPackageName()), 0, 0);
            }
        }
    }


    /**
     * 加载基础数据
     */
    public String[] getData() {
        return getResources().getStringArray(R.array.main_fragment_list);
    }
}
