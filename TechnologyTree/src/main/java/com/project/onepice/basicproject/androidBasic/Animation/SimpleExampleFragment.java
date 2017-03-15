package com.project.onepice.basicproject.androidBasic.Animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.onepice.basicproject.BaseFragment;
import com.project.onepice.basicproject.R;
import com.project.onepice.basicproject.androidBasic.Animation.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by onepice2015 on 2017/2/12.
 */

public class SimpleExampleFragment extends BaseFragment {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.menu)
    Button menu;
    @Bind(R.id.item1)
    Button item1;
    @Bind(R.id.item2)
    Button item2;
    @Bind(R.id.item3)
    Button item3;
    @Bind(R.id.item4)
    Button item4;
    @Bind(R.id.item5)
    Button item5;
    @Bind(R.id.list)
    ListView list;

    private boolean mIsMenuOpen = false;

    @Override
    public int getViewId() {
        return R.layout.fragment_simple_animation_item;
    }

    @Override
    public void initView() {
        title.setText("SimpleAnimation");

        List<Drawable> drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.mipmap.circle1));
        drawables.add(getResources().getDrawable(R.mipmap.circle2));
        drawables.add(getResources().getDrawable(R.mipmap.circle3));
        drawables.add(getResources().getDrawable(R.mipmap.circle4));
        drawables.add(getResources().getDrawable(R.mipmap.circle5));
        drawables.add(getResources().getDrawable(R.mipmap.circle1));
        drawables.add(getResources().getDrawable(R.mipmap.circle2));;

        ListAdapter adapter = new ListAdapter(getContext(),list,drawables,300);
        list.setAdapter(adapter);

    }

    @Override
    public void relecyResource() {

    }


    @OnClick({R.id.back, R.id.menu, R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent("packFragment");
                intent.putExtra("currentFragment", SimpleExampleFragment.class.getName());
                getContext().sendBroadcast(intent);
                break;
            case R.id.menu:
                if (!mIsMenuOpen){
                    mIsMenuOpen=true;
                    doAnimateOpen(item1, 0, 5, 300);
                    doAnimateOpen(item2, 1, 5, 300);
                    doAnimateOpen(item3, 2, 5, 300);
                    doAnimateOpen(item4, 3, 5, 300);
                    doAnimateOpen(item5, 4, 5, 300);
                }else{
                    mIsMenuOpen=false;
                    doAnimateClose(item1, 0, 5, 300);
                    doAnimateClose(item2, 1, 5, 300);
                    doAnimateClose(item3, 2, 5, 300);
                    doAnimateClose(item4, 3, 5, 300);
                    doAnimateClose(item5, 4, 5, 300);
                }
                break;
            case R.id.item1:
            case R.id.item2:
            case R.id.item3:
            case R.id.item4:
            case R.id.item5:
                Toast.makeText(getContext(),"你点击了"+view, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void doAnimateOpen(View view,int index,int total,int radius){
        if (view.getVisibility()!=View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }

        double degress=Math.toRadians(90)/(total-1)*index;
        int translationX= -(int) (radius * Math.sin(degress));
        int translationY= -(int) (radius*Math.cos(degress));

        AnimatorSet set=new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view,"translationX",0,translationX),
                ObjectAnimator.ofFloat(view,"translationY",0,translationY),
                ObjectAnimator.ofFloat(view,"scaleX",0f,1f),
                ObjectAnimator.ofFloat(view,"scaleY",0f,1f),
                ObjectAnimator.ofFloat(view,"alpha",0f,1f));
        set.setDuration(500).start();

    }


    private void doAnimateClose(View view,int index,int total,int radius){
        if (view.getVisibility()!=View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }

        double degree=Math.PI*index/((total-1)*2);
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f));
        set.setDuration(1*500).start();
    }
}






































