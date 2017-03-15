package com.project.onepice.basicproject.androidBasic.widget.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by onepice2015 on 16/7/28.
 */
public class MyView extends View{
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //
        Log.d("MyView","----------",new Throwable());
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        int specMode = MeasureSpec.getMode(heightMeasureSpec);

        Log.d("MyView","---specSize = "+specSize +"");
        Log.d("MyView","---specSize = "+specMode +"");

        if (specMode == MeasureSpec.AT_MOST){
            Log.d("MyView", "---AT_MOST---");
        }
        if (specMode == MeasureSpec.EXACTLY){
            Log.d("MyView", "---AT_MOST---");
        }
        if (specMode == MeasureSpec.UNSPECIFIED){
            Log.d("MyView", "---UNSPECIFIED---");
        }

        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),specSize);
















    }

    /**
     * 首先我们要理解的是widthMeasureSpec, heightMeasureSpec这两个参数是从哪里来的？onMeasure()函数由包含这个View的具体的ViewGroup调用，因此值也是从这个ViewGroup中传入的。这里我直接给出答案：子类View的这两个参数，由ViewGroup中的layout_width，layout_height和padding以及View自身的layout_margin共同决定。权值weight也是尤其需要考虑的因素，有它的存在情况可能会稍微复杂点。
     了解了这两个参数的来源，还要知道这两个值的作用。我们只取heightMeasureSpec作说明。这个值由高32位和低16位组成，高32位保存的值叫specMode，可以通过如代码中所示的MeasureSpec.getMode()获取；低16位为specSize，同样可以由MeasureSpec.getSize()获取。那么specMode和specSize的作用有是什么呢？要想知道这一点，我们需要知道代码中的最后一行，所有的View的onMeasure()的最后一行都会调用setMeasureDimension()函数的作用——这个函数调用中传进去的值是View最终的视图大小。也就是说onMeasure()中之前所作的所有工作都是为了最后这一句话服务的。

     我们知道在ViewGroup中，给View分配的空间大小并不是确定的，有可能随着具体的变化而变化，而这个变化的条件就是传到specMode中决定的，specMode一共有三种可能：

     MeasureSpec.EXACTLY：父视图希望子视图的大小应该是specSize中指定的。

     MeasureSpec.AT_MOST：子视图的大小最多是specSize中指定的值，也就是说不建议子视图的大小超过specSize中给定的值。

     MeasureSpec.UNSPECIFIED：我们可以随意指定视图的大小。

     由TextView中源码也可以知道这个值的设计意义是为了根据ViewGroup中具体能够提供的空间大小来指定子View的视图大小。


     通过以上这些分析，可以知道视图最终的大小由父视图，子视图以及程序员根据需要决定，良好的设计一般会根据子视图的measureSpec设置合适的布局大小。

     * */


}
