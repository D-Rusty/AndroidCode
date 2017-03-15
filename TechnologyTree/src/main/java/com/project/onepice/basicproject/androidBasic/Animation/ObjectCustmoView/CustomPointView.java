package com.project.onepice.basicproject.androidBasic.Animation.ObjectCustmoView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.project.onepice.basicproject.androidBasic.Animation.Evaluator.PointEvaluator;

/**
 * Created by onepice2015 on 2017/2/11.
 */

public class CustomPointView extends View {

    private Point point;

    public CustomPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

        @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (point != null){
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(300,300,point.getRadius(),paint);
        }
    }


    public void doPointAnim(){
        ValueAnimator valueAnimator=ValueAnimator.ofObject(new PointEvaluator(),new Point(20),new Point(200));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                point= (Point) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.start();

    }
}






































