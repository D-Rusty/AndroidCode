package com.hudong.wemedia.basiccomponent.widgets;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.hudong.wemedia.R;
import com.hudong.wemedia.business.connections.myfriend.MyFriendAdapter;

import java.util.ArrayList;

/**
 * Created by dyj on 2017/4/13.
 */

public class MyFriendSlidBar extends BaseSlidBar<MyFriendAdapter> {
    public MyFriendSlidBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyFriendSlidBar(Context context) {
        super(context);
    }

    public MyFriendSlidBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init() {
        //初始化标题
        allSections.add("★");
//        allSections.add("#");
        for (int i = 'A'; i < 'Z' + 1; i++) {
            allSections.add((char) i + "");
        }
        allSections.add("#");
        float fontSize = getResources().getDimension(R.dimen.fontSize);
        //创建paint
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextAlign(Paint.Align.CENTER);//x方向以居中的位置确定
        paint.setTextSize(fontSize);
    }

    @Override
    protected int getPositionForSection(int sectionIndex) {
        return adapter.getPositionForSection(sectionIndex);
    }

    @Override
    protected ArrayList<String> getSections() {
        return adapter.getSections();
    }

    @Override
    protected int getRecyclerId() {
        return R.id.act_contact_my_friend_normal_recycler;
    }

    @Override
    protected int getToastId() {
        return R.id.act_contact_my_friend_serion_tv;
    }
}
