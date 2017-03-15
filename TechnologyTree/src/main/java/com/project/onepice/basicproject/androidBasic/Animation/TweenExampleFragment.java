package com.project.onepice.basicproject.androidBasic.Animation;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.onepice.basicproject.BaseFragment;
import com.project.onepice.basicproject.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by onepice2015 on 2017/2/9.
 */

public class TweenExampleFragment extends BaseFragment {

    @Bind(R.id.alpha_tx)
    TextView alphaTx;
    @Bind(R.id.scale_tx)
    TextView scaleTx;
    @Bind(R.id.rotate_tx)
    TextView rotateTx;
    @Bind(R.id.translate_tx)
    TextView translateTx;
    @Bind(R.id.set_tx)
    TextView setTx;
    @Bind(R.id.tween_img)
    ImageView tweenImg;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;


    @Override
    public int getViewId() {
        return R.layout.fragment_tween_item;
    }

    @Override
    public void initView() {
        title.setText("Tween");
    }

    @Override
    public void relecyResource() {

    }

    @OnClick({R.id.alpha_tx, R.id.scale_tx, R.id.rotate_tx, R.id.translate_tx, R.id.set_tx, R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alpha_tx:
                /**
                 * 透明效果动画
                 * */
                showAnimation(R.anim.anim_alpha);
                break;
            case R.id.scale_tx:
                /**
                 * 渐变效果动画
                 * **/
                showAnimation(R.anim.anim_scale);
                break;
            case R.id.rotate_tx:
                /**
                 * 旋转动画
                 * */
                showAnimation(R.anim.anim_rotate);
                break;
            case R.id.translate_tx:
                /**
                 * 位移动画
                 * */
                showAnimation(R.anim.anim_translate);
                break;
            case R.id.set_tx:
                /**
                 * 组合动画
                 * */
                showSetAnimation();
                break;
            case R.id.back:
                Intent intent = new Intent("packFragment");
                intent.putExtra("currentFragment", TweenExampleFragment.class.getName());
                getContext().sendBroadcast(intent);
                break;
        }
    }


    private void showAnimation(int animation) {
        Animation alphaAnimation = AnimationUtils.loadAnimation(getContext(), animation);
        tweenImg.startAnimation(alphaAnimation);
    }

    private void showSetAnimation() {
        AnimationSet animationSet = (AnimationSet)
                AnimationUtils.loadAnimation(getContext(), R.anim.anim_set);
        tweenImg.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("test", "动画开始了");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("test", "动画结束了");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("test", "动画重新开始了");
            }
        });
    }
}










