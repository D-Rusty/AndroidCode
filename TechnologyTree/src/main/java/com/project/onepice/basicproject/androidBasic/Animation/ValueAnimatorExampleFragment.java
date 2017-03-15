package com.project.onepice.basicproject.androidBasic.Animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.onepice.basicproject.BaseFragment;
import com.project.onepice.basicproject.R;
import com.project.onepice.basicproject.androidBasic.Animation.Evaluator.CustomCharEvaluator;
import com.project.onepice.basicproject.androidBasic.Animation.Evaluator.CustomIntEvaluator;
import com.project.onepice.basicproject.androidBasic.Animation.ObjectCustmoView.CustomPointView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by onepice2015 on 2017/2/11.
 * <p>
 * ValueAnimator
 * 1.只负责对指定的数字区间进行动画运算
 * 2.我们需要对运算过程进行监听，然后自己对控件做动画操作
 */

public class ValueAnimatorExampleFragment extends BaseFragment {

    private final static String TAG = ValueAnimatorExampleFragment.class.getSimpleName();

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.value_animator_btn)
    Button valueAnimatorBtn;
    @Bind(R.id.value_animator_tv)
    TextView valueAnimatorTv;
    @Bind(R.id.custompointview)
    CustomPointView custompointview;

    @Override
    public int getViewId() {
        return R.layout.fragment_value_animator_item;
    }

    @Override
    public void initView() {
        title.setText("ValueAnimator");
    }

    @Override
    public void relecyResource() {

    }


    @OnClick({R.id.back, R.id.value_animator_btn, R.id.value_animator_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent("packFragment");
                intent.putExtra("currentFragment", ObjectAnimatorExampleFragment.class.getName());
                getContext().sendBroadcast(intent);
                break;
            case R.id.value_animator_btn:
                //showValueAnimatorBasic();
                custompointview.doPointAnim();
                break;
            case R.id.value_animator_tv:
                Toast.makeText(getContext(), "戳中了", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void showValueAnimatorBasic() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(1000);
        //监听动画的时候变化
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int curValue = (int) valueAnimator.getAnimatedValue();
                valueAnimatorTv.layout(valueAnimatorTv.getLeft(), curValue,  valueAnimatorTv.getRight(),
                        curValue + valueAnimatorTv.getHeight());
            }
        });

        //监听动画的状态
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.i(TAG,"onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.i(TAG,"onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                Log.i(TAG,"onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                Log.i(TAG,"onAnimationRepeat");
            }
        });
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setEvaluator(new CustomIntEvaluator());
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();
    }

    private void showValueObjectArgument(){
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CustomCharEvaluator(),new Character('A'),new Character('Z'));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char text = (char)animation.getAnimatedValue();
                valueAnimatorTv.setText(String.valueOf(text));
            }
        });
        valueAnimator.setDuration(10000);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.start();

    }
}
