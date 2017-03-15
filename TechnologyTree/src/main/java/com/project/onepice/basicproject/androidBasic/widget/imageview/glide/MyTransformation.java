package com.project.onepice.basicproject.androidBasic.widget.imageview.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by onepice2015 on 16/8/10.
 */
public class MyTransformation extends BitmapTransformation{

    /**
     * 自定义图片处理时，为了避免创建大量Bitmap以及减少GC，可以考虑重用Bitmap，这就需要BitmapPool，
     * 典型地就是，从Bitmap池中拿一个Bitmap，用这个Bitmap生成一个Canvas, 然后在这个Canvas上画初始的Bitmap并使用Matrix、
     * Paint、或者Shader处理这张图片。
     * 为了有效并正确重用Bitmap需要遵循以下三条准则：
     * 永远不要把transform()传给你的原始resource或原始Bitmap给recycle()了，更不要放回BitmapPool，因为这些都自动完成了。值得注意的是，任何从BitmapPool取出的用于自定义图片变换的辅助Bitmap，如果不经过transform()方法返回，就必须主动放回BitmapPool或者调用recycle()回收。
     * 如果你从BitmapPool拿出多个Bitmap或不使用你从BitmapPool拿出的一个Bitmap，一定要返回extras给BitmapPool。
     * 如果你的图片处理没有替换原始resource(例如由于一张图片已经匹配了你想要的尺寸，你需要提前返回), transform()`方法就返回原始resource或原始Bitmap。
     * */

    public MyTransformation(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
//        Bitmap myTransformedBitmap=对Bitmap进行各种变换处

        Bitmap result=pool.get(outWidth,outHeight,Bitmap.Config.ARGB_8888);
        // 如果BitmapPool中找不到符合该条件的Bitmap，get()方法会返回null，就需要我们自己创建Bitmap了
        if (result == null){
            // 如果想让Bitmap支持透明度，就需要使用ARGB_8888
            result = Bitmap.createBitmap(outWidth,outHeight,Bitmap.Config.ARGB_8888);
        }

        //创建最终Bitmap的Canvas.
        Canvas canvas = new Canvas(result);

        Paint paint = new Paint();

        paint.setAlpha(128);

        //将原始Bitmap处理后画到最终Bitmap中

        canvas.drawBitmap(toTransform,0,0,paint);
        // 由于我们的图片处理替换了原始Bitmap，就return我们新的Bitmap就行。
        // Glide会自动帮我们回收原始Bitmap。
        return result;
    }

    @Override
    public String getId() {
        // 返回代表该变换的唯一Id，会作为cache key的一部分。
        // 注意：最好不要用getClass().getName()，因为容易受混淆影响。如果变换过程不影响缓存数据，可以返回空字符串。
        return "com.project.onepice.glideexample";
    }



}
