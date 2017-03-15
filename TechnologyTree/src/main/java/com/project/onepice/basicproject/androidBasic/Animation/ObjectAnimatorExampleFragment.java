package com.project.onepice.basicproject.androidBasic.Animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.onepice.basicproject.BaseFragment;
import com.project.onepice.basicproject.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by onepice2015 on 2017/2/9.
 */

public class ObjectAnimatorExampleFragment extends BaseFragment {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.start_anim)
    TextView startAnim;
    @Bind(R.id.cancel_anim)
    TextView cancelAnim;
    @Bind(R.id.property_img)
    ImageView propertyImg;


    private Animator animation=null;

    @Override
    public int getViewId() {
        return R.layout.fragment_object_animator_item;
    }

    @Override
    public void initView() {
        title.setText("ObjectAnimator");
    }

    @Override
    public void relecyResource() {

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    private void startPropertyAnimatior() {

        /**
         * 第一个参数用于指定这个动画要操作的是哪个控件
         * 第二个参数用于指定这个动画要操作这个控件的哪个属性
         * 第三个参数是可变长参数，这个就跟 ValueAnimator 中的可变长参数的意义一样了，就是指这个属性值是从哪变到哪。
         *   像我们上面的代码中指定的就是将 textview 的 alpha 属性从 0 变到 1 再变到 0； 下面我们再来看一下如何实现旋转效果：
         * */
       ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(propertyImg,"rotationX",0,180,0);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }

    private void canclePropertyAnimatior() {
       if (null==animation){
           return;
       }
        animation.cancel();
        propertyImg.clearAnimation();
    }

    /**
     * 组合属性动画实现
     */
    private void showSetPropertyAnimatior() {

        /**
         * 方法一
         * */

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(propertyImg, "scaleX", 1f, 1.5f);
        scaleXAnimator.setDuration(500);
        scaleXAnimator.setRepeatCount(1);
        scaleXAnimator.setRepeatMode(ValueAnimator.REVERSE);
        scaleXAnimator.start();

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(propertyImg, "scaleY", 1f, 1.5f);
        scaleYAnimator.setDuration(500);
        scaleYAnimator.setRepeatCount(1);
        scaleYAnimator.setRepeatMode(ValueAnimator.REVERSE);

        AnimatorSet animationSet = new AnimatorSet();
        animationSet.playTogether(scaleXAnimator, scaleYAnimator);
        animationSet.start();

        /**
         * 方法二
         * */

        PropertyValuesHolder scaleXValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.5f);
        PropertyValuesHolder scaleYValuesHolder = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.5f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(propertyImg, scaleXValuesHolder, scaleYValuesHolder);
        objectAnimator.setDuration(500);
        objectAnimator.setRepeatCount(1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();

        /**
         * 方法三
         * */

        ViewPropertyAnimator viewPropertyAnimator = propertyImg.animate();
        viewPropertyAnimator.scaleXBy(1.0f).scaleX(1.5f).scaleYBy(1.0f).scaleY(1.5f).setDuration(500).start();


        /**
         * 动画监听器
         * */
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


    }

    /**
     * 实现属性动画效果
     * */
    @OnClick({R.id.back, R.id.start_anim, R.id.cancel_anim})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent("packFragment");
                intent.putExtra("currentFragment", ObjectAnimatorExampleFragment.class.getName());
                getContext().sendBroadcast(intent);
                break;
            case R.id.start_anim:
                startPropertyAnimatior();
                break;
            case R.id.cancel_anim:
                canclePropertyAnimatior();
                break;
        }
    }
}
