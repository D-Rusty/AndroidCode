package com.project.onepice.basicproject.androidBasic.widget.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.project.onepice.basicproject.R;

/**
 * Created by onepice2015 on 16/7/28.
 */
public class PieChart extends View{

    private int mBoardBackground;//画板颜色

    private int mPaintColor;//画笔颜色

    private int mPaintWidth;//画笔宽度

    private Path mPath;

    private Paint mPaint;//画笔


    public PieChart(Context context){
        this(context,null);
    }


    public PieChart(Context context, AttributeSet attrs) {
        this(context, attrs,0);

        /**
         * set：属性值的集合

         　　　　attrs：我们要获取的属性的资源ID的一个数组，如同ContextProvider中请求数据库时的Projection数组，就是从一堆属性中我们希望查询什么属性的值

         　　　　defStyleAttr：这个是当前Theme中的一个attribute，是指向style的一个引用，当在layout xml中和style中都没有为View指定属性时，会从Theme中这个attribute指向的Style中查找相应的属性值，这就是defStyle的意思，如果没有指定属性值，就用这个值，所以是默认值，但这个attribute要在Theme中指定，且是指向一个Style的引用，如果这个参数传入0表示不向Theme中搜索默认值

         　　　　defStyleRes：这个也是指向一个Style的资源ID，但是仅在defStyleAttr为0或defStyleAttr不为0但Theme中没有为defStyleAttr属性赋值时起作用

         　　链接中对这个函数说明勉强过得去，这里简要概括一下。对于一个属性可以在多个地方指定它的值，如XML直接定义，style，Theme，而这些位置定义的值有一个优先级，按优先级从高到低依次是：
         *
         * */

//        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,
//                R.styleable.PieChart,
//                0,0);
//
//        try {
//            mShowText = array.getBoolean(R.styleable.PieChart_showText,false);
//            mTextPos = array.getInteger(R.styleable.PieChart_labelPosition,0);
//        }finally {
//            array.recycle();
//        }

    }//为了支持.xml中进行创建和编辑

    public PieChart(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PieChart);
        mBoardBackground = array.getColor(R.styleable.PieChart_boardBackground, Color.WHITE);
        mPaintColor = array.getColor(R.styleable.PieChart_paintColor,Color.BLUE);
        mPaintWidth = array.getColor(R.styleable.PieChart_paintWidth, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,5,getResources().getDisplayMetrics()));
        array.recycle();

        mPaint = new Paint();
        mPath = new Path();

        setBackgroundColor(mBoardBackground);
        mPaint.setColor(mPaintColor);
        mPaint.setStrokeWidth(mPaintWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);

    }


//    public boolean isShowText(){
//      return mShowText;
//    }
//
//    public void setmShowText(boolean showText){
//        mShowText=showText;
//        //系统调用View重新绘制
//        invalidate();
//        //获取一个新的布局位置
//        requestLayout();
//    }

    //处理View的布局




//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        // onMeasure 方法是一个view确定它的宽高的地方
        /*
        * onMeasure方法里有两个重要的参数, widthMeasureSpec, heightMeasureSpec。在这里你只需要记住它们包含了两个信息:mode和size
我们可以通过以下代码拿到mode和size
        * */
//        int specMode = MeasureSpec.getMode(widthMeasureSpec);
//        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        /*
        * 那么获取到的mode和size又代表了什么呢？
mode代表了我们当前控件的父控件告诉我们控件，你应该按怎样的方式来布局。
mode有三个可选值：EXACTLY, AT_MOST, UNSPECIFIED。它们的含义是：

EXACTLY：父控件告诉我们子控件了一个确定的大小，你就按这个大小来布局。比如我们指定了确定的dp值和macth_parent的情况。
AT_MOST：当前控件不能超过一个固定的最大值，一般是wrap_content的情况。
UNSPECIFIED:当前控件没有限制，要多大就有多大，这种情况很少出现。

size其实就是父布局传递过来的一个大小，父布局希望当前布局的大小。
        * */

//        if (specMode == MeasureSpec.EXACTLY){
//            // 父布局已经告诉了我们当前布局应该是多大的宽高, 所以我们直接返回从measureSpec中获取到的size
//
//        }else if (specMode == MeasureSpec.AT_MOST){
//           //返回desireSize和specSize当中的最小组
//        }else{
//            //返回计算出的desireSize
//        }
//
//
//      setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);

//    }

    @Override
    protected void onDraw(Canvas canvas) {

        /**
         Canvas决定要去画什么
         Paint决定怎么画
         比如，Canvas提供了画线方法，Paint就来决定线的颜色。Canvas提供了画矩形，Paint又可以决定让矩形是空心还是实心。
         在onDraw方法中开始绘制之前，你应该让画笔Paint对象的信息初始化完毕。这是因为View的重新绘制是比较频繁的，这就可能多次调用onDraw，所以初始化的代码不应该放在onDraw方法里。
         * */
        super.onDraw(canvas);

        canvas.drawPath(mPath,mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
       /**
        * 重写自定义View 的 onTouchEvent 事件
        * */

        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(touchX,touchY);//重新设置即将出现的线的起点
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(touchX,touchY);//连线
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        invalidate();//通知系统重绘
        return true;
    }
}





























