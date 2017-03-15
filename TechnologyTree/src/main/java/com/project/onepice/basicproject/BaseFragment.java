package com.project.onepice.basicproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.project.onepice.basicproject.listener.MyOnTouchListener;
import com.project.onepice.basicproject.utils.Commas;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    private GestureDetector gestureDetector;//手指滑动事件监听器

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        gestureDetector = new GestureDetector(getActivity(), onGestureListener);

        MyOnTouchListener listener = new MyOnTouchListener() {
            @Override
            public boolean onTouch(MotionEvent ev) {
                return gestureDetector.onTouchEvent(ev);
            }
        };

        ((BaseActivity)getActivity()).registerMyTouchListener(listener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getViewId(), container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            float x = motionEvent1.getX() - motionEvent.getX();
            float y = motionEvent1.getY() - motionEvent.getY();
            String direction = "";
            if (x > 0) {
                direction = Commas.LEFT;
            } else if (x < 0) {
                direction = Commas.RIGHT;
            } else if (y > 0) {
                direction = Commas.UPWARDS;
            } else if (y < 0) {
                direction = Commas.DOWN;
            }
            ((BaseActivity)getActivity()).doDirection(direction);
            return false;
        }
    };
    @Override
    public void onClick(View view) {

    }

    public abstract int getViewId();

    public abstract void initView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        relecyResource();
        ButterKnife.unbind(this);
    }
    /**
     * 回收资源
     * */
    public abstract void relecyResource();
}
