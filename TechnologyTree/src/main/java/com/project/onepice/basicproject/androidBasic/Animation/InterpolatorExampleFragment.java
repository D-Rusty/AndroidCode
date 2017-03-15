package com.project.onepice.basicproject.androidBasic.Animation;

import android.content.Intent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.project.onepice.basicproject.BaseFragment;
import com.project.onepice.basicproject.R;
import com.project.onepice.basicproject.androidBasic.Animation.Interpolator.CustomInterpolator;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by onepice2015 on 2017/2/10.
 */

public class InterpolatorExampleFragment extends BaseFragment {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.interpolator_img)
    ImageView interpolatorImg;
    @Bind(R.id.accelerate_decelerate_tx)
    TextView accelerateDecelerateTx;
    @Bind(R.id.accelerate_tx)
    TextView accelerateTx;
    @Bind(R.id.anticipate_tx)
    TextView anticipateTx;
    @Bind(R.id.anticipate_overshoot_tx)
    TextView anticipateOvershootTx;
    @Bind(R.id.bounce_tx)
    TextView bounceTx;
    @Bind(R.id.cycle_tx)
    TextView cycleTx;
    @Bind(R.id.decelerate_tx)
    TextView decelerateTx;
    @Bind(R.id.linear_tx)
    TextView linearTx;
    @Bind(R.id.overshoot_tx)
    TextView overshootTx;
    @Bind(R.id.custom_tx)
    TextView customTx;
    @Bind(R.id.interpolator_rg)
    RadioGroup interpolator_rg;

    private Animation animation;

    @Override
    public int getViewId() {
        return R.layout.fragment_interpolator_item;
    }

    @Override
    public void initView() {
        title.setText("Interpolator");
        interpolator_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                initAnimationObj(((RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
            }
        });
    }

    @Override
    public void relecyResource() {

    }


    @OnClick({R.id.back, R.id.accelerate_decelerate_tx, R.id.accelerate_tx, R.id.anticipate_tx, R.id.anticipate_overshoot_tx, R.id.bounce_tx, R.id.cycle_tx, R.id.decelerate_tx, R.id.linear_tx, R.id.overshoot_tx, R.id.custom_tx})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent("packFragment");
                intent.putExtra("currentFragment", InterpolatorExampleFragment.class.getName());
                getContext().sendBroadcast(intent);
                break;
            case R.id.accelerate_decelerate_tx:
                showAccelerateDecelerate("accelerate_decelerate");
                break;
            case R.id.accelerate_tx:
                showAccelerateDecelerate("accelerate");
                break;
            case R.id.anticipate_tx:
                showAccelerateDecelerate("anticipate");
                break;
            case R.id.anticipate_overshoot_tx:
                showAccelerateDecelerate("anticipate_overshoot");
                break;
            case R.id.bounce_tx:
                showAccelerateDecelerate("bounce");
                break;
            case R.id.cycle_tx:
                showAccelerateDecelerate("cycle");
                break;
            case R.id.decelerate_tx:
                showAccelerateDecelerate("decelerate");
                break;
            case R.id.linear_tx:
                showAccelerateDecelerate("linear");
                break;
            case R.id.overshoot_tx:
                showAccelerateDecelerate("overshoot");
                break;
            case R.id.custom_tx:
                showAccelerateDecelerate("custom");
                break;
        }
    }

    private void showAccelerateDecelerate(String interpolatorString) {
        interpolatorImg.clearAnimation();
        animation=(null!=animation)?animation:(animation=AnimationUtils.loadAnimation(getContext(),R.anim.anim_alpha));
        switch (interpolatorString) {
            case "accelerate_decelerate":
                //在动画开始与介绍的地方速率改变比较慢，在中间的时候加速
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                break;
            case "accelerate":
                //在动画开始的地方速率改变比较慢，然后开始加速
                animation.setInterpolator(new AccelerateInterpolator());
                break;
            case "anticipate":
                //开始的时候向后然后向前甩
                animation.setInterpolator(new AnticipateInterpolator());
                break;
            case "anticipate_overshoot":
                //开始的时候向后然后向前甩一定值后返回最后的值
                animation.setInterpolator(new AnticipateOvershootInterpolator());
                break;
            case "bounce":
                //BounceInterpolator 动画结束的时候弹起
                animation.setInterpolator(new BounceInterpolator());
                break;
            case "cycle":
                //CycleInterpolator 动画循环播放特定的次数，速率改变沿着正弦曲线
                animation.setInterpolator(new CycleInterpolator(0.5f));
                break;
            case "decelerate":
                //DecelerateInterpolator 在动画开始的地方快然后慢
                animation.setInterpolator(new DecelerateInterpolator());
                break;
            case "linear":
                //LinearInterpolator 以常量速率改变
                animation.setInterpolator(new LinearInterpolator());
                break;
            case "overshoot":
                //OvershootInterpolator 向前甩一定值后再回到原来位置
                animation.setInterpolator(new OvershootInterpolator());
                break;
            case "custom":
                animation.setInterpolator(new CustomInterpolator());
                break;
        }
            interpolatorImg.setAnimation(animation);
    }


    private void initAnimationObj(String animationString) {
        animation = null;
        switch (animationString) {
            case "alpah":
                animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_alpha);
                break;
            case "scale":
                animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_scale);
                break;
            case "rotate":
                animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_rotate);
                break;
            case "translate":
                animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_translate);
                break;
        }
    }


}
