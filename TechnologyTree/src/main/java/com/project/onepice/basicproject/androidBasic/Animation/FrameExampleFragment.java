package com.project.onepice.basicproject.androidBasic.Animation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.onepice.basicproject.BaseFragment;
import com.project.onepice.basicproject.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by onepice2015 on 2017/2/9.
 */

public class FrameExampleFragment extends BaseFragment {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.frame_btn)
    Button frameBtn;
    @Bind(R.id.frame_img)
    ImageView frameImg;

    @Override
    public int getViewId() {
        return R.layout.fragment_frame_item;
    }

    @Override
    public void initView() {
        title.setText("Frame");
    }

    @Override
    public void relecyResource() {

    }


    @OnClick({R.id.back, R.id.frame_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent("packFragment");
                intent.putExtra("currentFragment", FrameExampleFragment.class.getName());
                getContext().sendBroadcast(intent);
                break;
            case R.id.frame_btn:
                showFrameAnimation();
                break;
        }
    }

    private void showFrameAnimation() {
        frameImg.setImageResource(R.drawable.lottery_animlist);
        AnimationDrawable animationDrawable = (AnimationDrawable) frameImg.getDrawable();
        animationDrawable.start();
    }
}
