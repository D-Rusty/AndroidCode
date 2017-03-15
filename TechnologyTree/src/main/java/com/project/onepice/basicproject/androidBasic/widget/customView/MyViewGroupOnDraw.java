package com.project.onepice.basicproject.androidBasic.widget.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by onepice2015 on 16/8/4.
 */
public class MyViewGroupOnDraw  extends View{

    private Paint paint = new Paint();

    public MyViewGroupOnDraw(Context context){
        super(context);
    }

    public MyViewGroupOnDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //设置该View大小为 80 80
        setMeasuredDimension(50,50);
    }

    //存在canvas对象，即存在默认的显示区域
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i("MyViewGroup", "MyView is onDraw ") ;
        //加粗
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paint.setColor(Color.RED);
        canvas.drawColor(Color.BLUE) ;
        canvas.drawRect(0, 0, 30, 30, paint);
        canvas.drawText("MyView", 10, 40, paint);
    }
}
