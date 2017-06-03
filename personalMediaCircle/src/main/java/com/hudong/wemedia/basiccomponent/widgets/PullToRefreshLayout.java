package com.hudong.wemedia.basiccomponent.widgets;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hudong.wemedia.R;

import java.util.Timer;
import java.util.TimerTask;


public class PullToRefreshLayout
        extends RelativeLayout {

    public boolean refreshResult = false;
    public boolean loadResult = false;
    private final int REFRESH = 100;
    private final int LOAD = 101;
    private boolean pullDownAvailable = true;
    private boolean pullUpAvailable = true;

    private boolean isFirstLayout = true;
    private View refreshView;
    private View pullableView;
    private ImageView pullRefreshIv;
    private ImageView refreshingIv;
    private TextView refreshStateTv;
    private ImageView refreshStateIv;
    private TextView loadStateTv;
    private ImageView pullLoadIv;
    private ImageView loadingIv;
    private ImageView loadStateIv;
    private float refreshDist = 200;
    private float pullDownY = 0;
    private float pullUpY = 0;
    private boolean canPullDown = true;
    private boolean canPullUp = true;
    private float downY;
    private float lastY;
    private int mEvents;
    // 初始状态
    public static final int INIT = 0;
    // 释放刷新
    public static final int RELEASE_TO_REFRESH = 1;
    // 正在刷新
    public static final int REFRESHING = 2;
    // 释放加载
    public static final int RELEASE_TO_LOAD = 3;
    // 正在加载
    public static final int LOADING = 4;
    // 操作完毕
    public static final int DONE = 5;
    // 当前状态
    private int state = INIT;
    private float radio = 2;
    private boolean isTouch = true;
    private Animation reverseAnim180;
    private Animation rotateAnim360;
    // 刷新成功
    public static final int SUCCEED = 0;
    // 刷新失败
    public static final int FAIL = 1;
    private float MOVE_SPEED;
    private OnRefreshListener mListener;
    private Timer timer;
    private View loadView;
    private int loadDist;

    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH:
                    refreshFinish(refreshResult);
                    break;

                case LOAD:
                    loadFinish(loadResult);
                    break;
            }
        }
    };

    public PullToRefreshLayout(Context context) {
        super(context);
        initAnim(context);
    }

    public PullToRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAnim(context);
    }

    public PullToRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAnim(context);
    }

    public interface OnRefreshListener {
        void onRefresh();

        void onLoad();
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.mListener = listener;
    }

    public void setCanPullDown(boolean pullDownState) {
        pullDownAvailable = pullDownState;
    }

    public void setCanPullUp(boolean pullUpState) {
        pullUpAvailable = pullUpState;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (isFirstLayout) {
            refreshView = getChildAt(0);
            pullableView = getChildAt(1);
            loadView = getChildAt(2);
            initViews();
            isFirstLayout = false;
            refreshDist = ((ViewGroup) refreshView).getChildAt(0).getMeasuredHeight();
            loadDist = ((ViewGroup) loadView).getChildAt(0).getMeasuredHeight();
        }
        refreshView.layout(0,
                (int) (pullDownY - pullUpY) - refreshView.getMeasuredHeight(),
                refreshView.getMeasuredWidth(),
                (int) (pullDownY - pullUpY));
        pullableView.layout(0,
                (int) (pullDownY - pullUpY),
                pullableView.getMeasuredWidth(),
                pullableView.getMeasuredHeight() + (int) (pullDownY - pullUpY));
        loadView.layout(0,
                pullableView.getMeasuredHeight() + (int) (pullDownY - pullUpY),
                loadView.getMeasuredWidth(),
                loadView.getMeasuredHeight() + pullableView.getMeasuredHeight() + (int) (pullDownY - pullUpY));

    }

    private void initAnim(Context context) {
        reverseAnim180 = AnimationUtils.loadAnimation(context, R.anim.rotate180_anim);
        rotateAnim360 = AnimationUtils.loadAnimation(context, R.anim.rotate360_anim);
    }

    private void initViews() {
//        pullRefreshIv = (ImageView) refreshView.findViewById(R.id.iv_pull_refresh);
//        refreshingIv = (ImageView) refreshView.findViewById(R.id.iv_refreshing);
//        refreshStateTv = (TextView) refreshView.findViewById(R.id.tv_refresh_state);
//        refreshStateIv = (ImageView) refreshView.findViewById(R.id.iv_refresh_state);
//
//        loadStateTv = (TextView) loadView.findViewById(R.id.tv_load_state);
//        pullLoadIv = (ImageView) loadView.findViewById(R.id.iv_pull_load);
//        loadingIv = (ImageView) loadView.findViewById(R.id.iv_loading);
//        loadStateIv = (ImageView) loadView.findViewById(R.id.iv_load_state);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                lastY = downY;
                mEvents = 0;
                canPullDown = true;
                canPullUp = true;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                mEvents = -1;
                break;

            case MotionEvent.ACTION_MOVE:
                hanldeMoveEvent(ev);
                break;

            case MotionEvent.ACTION_UP:
                if (pullDownY > refreshDist || pullUpY > loadDist) {
                    isTouch = true;
                }
                if (state == RELEASE_TO_REFRESH) {
                    changeState(REFRESHING);
                    if (mListener != null) {
                        mListener.onRefresh();

                    }
                } else if (state == RELEASE_TO_LOAD) {
                    changeState(LOADING);
                    if (mListener != null) {
                        mListener.onLoad();

                    }
                }

                loopHide();
                break;
        }

        super.dispatchTouchEvent(ev);
        return true;
    }

    private void hanldeMoveEvent(MotionEvent ev) {
        if (mEvents == 0) {
            if (pullDownAvailable && ((Pullable) pullableView).canPullDown() && canPullDown && state != LOADING) {
                pullDownY = pullDownY + (ev.getY() - lastY) / radio;
                if (pullDownY < 0) {
                    pullDownY = 0;
                    canPullDown = false;
                }
                if (pullDownY > getMeasuredHeight()) {
                    pullDownY = getMeasuredHeight();
                }
                if (state == REFRESHING) {
                    isTouch = false;
                    canPullUp = false;
                }
            } else if (pullUpAvailable && ((Pullable) pullableView).canPullUp() && canPullUp && state != REFRESHING) {
                pullUpY = pullUpY + (lastY - ev.getY()) / radio;
                if (pullUpY < 0) {
                    pullUpY = 0;
                    canPullUp = false;
                }
                if (pullUpY > getMeasuredHeight()) {
                    pullUpY = getMeasuredHeight();
                }
                if (state == LOADING) {
                    isTouch = false;
                    canPullDown = false;
                }
            } else {
                canPullDown = true;
                canPullUp = true;
            }
        } else {
            mEvents = 0;
        }
        lastY = ev.getY();
        // 根据下拉距离改变比例
        radio = (float) (2 + 2 * Math.tan(Math.PI / 2 / getMeasuredHeight()
                * (pullDownY + pullUpY)));
        requestLayout();

        if (pullDownY < refreshDist && state == RELEASE_TO_REFRESH) {
            changeState(INIT);
        }
        if (pullDownY >= refreshDist && state == INIT) {
            changeState(RELEASE_TO_REFRESH);
        }
        if (pullUpY < loadDist && state == RELEASE_TO_LOAD) {
            changeState(INIT);
        }
        if (pullUpY >= loadDist && state == INIT) {
            changeState(RELEASE_TO_LOAD);
        }
        if (pullDownY + pullUpY > 8) {
            ev.setAction(MotionEvent.ACTION_CANCEL);
        }
    }

    private void changeState(int to) {
        state = to;
        switch (state) {
            case INIT:
                refreshStateIv.setVisibility(GONE);
                pullRefreshIv.clearAnimation();
                pullRefreshIv.setVisibility(VISIBLE);
                refreshStateTv.setText("下拉刷新");

                loadStateIv.setVisibility(GONE);
                loadingIv.setVisibility(GONE);
                pullLoadIv.clearAnimation();
                pullLoadIv.setVisibility(VISIBLE);
                loadStateTv.setText("上拉加载更多");
                break;

            case RELEASE_TO_REFRESH:
                refreshStateTv.setText("释放立即刷新");
                pullRefreshIv.startAnimation(reverseAnim180);
                break;

            case REFRESHING:
                refreshStateTv.setText("正在刷新");
                pullRefreshIv.clearAnimation();
                pullRefreshIv.setVisibility(GONE);
                refreshingIv.setVisibility(VISIBLE);
                refreshingIv.startAnimation(rotateAnim360);

                new Thread() {
                    @Override
                    public void run() {
                        SystemClock.sleep(500);
                        if (refreshResult) {
                            handler1.sendEmptyMessage(REFRESH);
                        } else {
                            SystemClock.sleep(500);
                            if (refreshResult) {
                                handler1.sendEmptyMessage(REFRESH);
                            } else {
                                SystemClock.sleep(500);
                                handler1.sendEmptyMessage(REFRESH);
                            }
                        }

                    }
                }.start();

                break;

            case RELEASE_TO_LOAD:
                pullLoadIv.startAnimation(reverseAnim180);
                loadStateTv.setText("释放立即加载");
                break;

            case LOADING:
                pullLoadIv.clearAnimation();
                pullLoadIv.setVisibility(GONE);
                loadingIv.setVisibility(VISIBLE);
                loadingIv.startAnimation(rotateAnim360);
                loadStateTv.setText("正在加载");

                new Thread() {
                    @Override
                    public void run() {
                        SystemClock.sleep(500);
                        if (loadResult) {
                            handler1.sendEmptyMessage(LOAD);
                        } else {
                            SystemClock.sleep(500);
                            if (loadResult) {
                                handler1.sendEmptyMessage(LOAD);
                            } else {
                                SystemClock.sleep(500);
                                handler1.sendEmptyMessage(LOAD);
                            }
                        }

                    }
                }.start();

                break;

            case DONE:

                break;

        }
    }

    public void refreshFinish(boolean refreshResult) {
        refreshingIv.clearAnimation();
        refreshingIv.setVisibility(GONE);

        if (refreshResult) {
            refreshStateTv.setText("刷新成功");
            refreshStateIv.setVisibility(VISIBLE);
            refreshStateIv.setImageResource(R.drawable.icon_check_n_3x);
        } else {
            refreshStateTv.setText("刷新失败");
            refreshStateIv.setVisibility(GONE);
            refreshStateIv.setImageResource(R.drawable.load_failed);
        }

        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                changeState(DONE);
                loopHide();
            }
        }.sendEmptyMessageDelayed(0, 1000);

    }

    public void loadFinish(boolean loadResult) {
        pullLoadIv.setVisibility(GONE);
        loadingIv.clearAnimation();
        loadingIv.setVisibility(GONE);
        if (loadResult) {
            loadStateIv.setImageResource(R.drawable.load_succeed);
            loadStateTv.setText("加载成功");
        } else {
            loadStateIv.setImageResource(R.drawable.load_failed);
            loadStateTv.setText("加载失败");
        }

        new Handler() {
            public void handleMessage(Message msg) {
                changeState(DONE);
                loopHide();
            }
        }.sendEmptyMessageDelayed(0, 1000);
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    hide();
                    break;
            }
        }
    };

    private void loopHide() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new MyTask(), 0, 5);
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            handler.obtainMessage(1).sendToTarget();
        }
    }

    private void hide() {
        float MOVE_SPEED = (float) (8 + 5 * Math.tan(Math.PI / 2 / getMeasuredHeight() * (pullDownY + pullUpY)));
        if (isTouch) {
            if (state == REFRESHING && pullDownY <= refreshDist) {
                pullDownY = refreshDist;
            } else if (state == LOADING && pullUpY <= loadDist) {
                pullUpY = loadDist;
            }
        }
        if (pullDownY > 0) {
            pullDownY -= MOVE_SPEED;
        }
        if (pullDownY < 0) {
            pullDownY = 0;
            timer.cancel();
            if (state != REFRESHING) {
                changeState(INIT);
            }
        }
        if (pullUpY > 0) {
            pullUpY -= MOVE_SPEED;
        }
        if (pullUpY < 0) {
            pullUpY = 0;
            timer.cancel();
            if (state != LOADING) {
                changeState(INIT);
            }
        }
        if (pullUpY == 0 && pullDownY == 0) {
            timer.cancel();
        }
        requestLayout();

    }


}
