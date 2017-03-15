package com.project.onepice.basicproject.androidBasic.Animation;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.onepice.basicproject.BaseFragment;
import com.project.onepice.basicproject.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by onepice2015 on 2017/2/12.
 */

public class LayoutTransitionExampleFragment extends BaseFragment {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.add_btn)
    Button addBtn;
    @Bind(R.id.remove_btn)
    Button removeBtn;
    @Bind(R.id.layoutTransitionGroup)
    LinearLayout layoutTransitionGroup;

    private int i = 0;
    private LayoutTransition mTransitioner;

    @Override
    public int getViewId() {
        return R.layout.fragment_layout_transition_item;
    }

    @Override
    public void initView() {
        title.setText("LayoutTransittion");
        mTransitioner=new LayoutTransition();
        //入场动画:view在这个容器中消失时触发的动画
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationY", 0f, 360f,0f);
        mTransitioner.setAnimator(LayoutTransition.APPEARING, animIn);

        //出场动画:view显示时的动画
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotation", 0f, 90f, 0f);
        mTransitioner.setAnimator(LayoutTransition.DISAPPEARING, animOut);

        /**
         * LayoutTransition.CHANGE_APPEARING动画
         */
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left",0,100,0);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top",1,1);
        //必须第一个值与最后一值相同才会有效果,不然没有效果
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("ScaleX",1f,9f,1f);
        Animator changeAppearAnimator
                = ObjectAnimator.ofPropertyValuesHolder(layoutTransitionGroup, pvhLeft,pvhTop,pvhScaleX);
        mTransitioner.setAnimator(LayoutTransition.CHANGE_APPEARING,changeAppearAnimator);

        /**
         * LayoutTransition.CHANGE_DISAPPEARING动画
         */
        PropertyValuesHolder outLeft = PropertyValuesHolder.ofInt("left",0,0);
        PropertyValuesHolder outTop = PropertyValuesHolder.ofInt("top",0,0);

        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.1f, -20f);
        Keyframe frame2 = Keyframe.ofFloat(0.2f, 20f);
        Keyframe frame3 = Keyframe.ofFloat(0.3f, -20f);
        Keyframe frame4 = Keyframe.ofFloat(0.4f, 20f);
        Keyframe frame5 = Keyframe.ofFloat(0.5f, -20f);
        Keyframe frame6 = Keyframe.ofFloat(0.6f, 20f);
        Keyframe frame7 = Keyframe.ofFloat(0.7f, -20f);
        Keyframe frame8 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe frame9 = Keyframe.ofFloat(0.9f, -20f);
        Keyframe frame10 = Keyframe.ofFloat(1, 0);
        PropertyValuesHolder mPropertyValuesHolder = PropertyValuesHolder.ofKeyframe("rotation",frame0,frame1,frame2,frame3,frame4,frame5,frame6,frame7,frame8,frame9,frame10);

        ObjectAnimator mObjectAnimatorChangeDisAppearing = ObjectAnimator.ofPropertyValuesHolder(this, outLeft,outTop,mPropertyValuesHolder);
        mTransitioner.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, mObjectAnimatorChangeDisAppearing);

        //设置单个item间的动画间隔
        mTransitioner.setStagger(LayoutTransition.CHANGE_APPEARING, 30);

        layoutTransitionGroup.setLayoutTransition(mTransitioner);

    }

    @Override
    public void relecyResource() {

    }


    @OnClick({R.id.back, R.id.add_btn, R.id.remove_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent("packFragment");
                intent.putExtra("currentFragment", LayoutTransitionExampleFragment.class.getName());
                getContext().sendBroadcast(intent);
                break;
            case R.id.add_btn:
                addButtonView();
                break;
            case R.id.remove_btn:
                removeButtonView();
                break;
        }
    }

    private void addButtonView() {
        i++;
        Button button = new Button(getContext());
        button.setText("button" + i);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        layoutTransitionGroup.addView(button, 0);
    }

    private void removeButtonView() {
        if (i > 0) {
            layoutTransitionGroup.removeViewAt(0);
        }
        i--;
    }
}
