package com.project.onepice.basicproject.androidBasic.widget.imageview.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by onepice2015 on 2017/2/8.
 *
 *
 * glide-transformations  一个专用于图片处理很赞的框架
 */

public class TransformationsCustom extends BitmapTransformation{

    private RenderScript renderScript;


    public TransformationsCustom(Context context) {
        super(context);
        renderScript=RenderScript.create(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
       Bitmap blurredBitmap=toTransform.copy(Bitmap.Config.ARGB_8888,true);

        Allocation input=Allocation.
                                createFromBitmap(renderScript,
                                                 blurredBitmap,
                                                 Allocation.MipmapControl.MIPMAP_FULL,
                                                 Allocation.USAGE_SHARED);
        Allocation output=Allocation.createTyped(renderScript,input.getType());

        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setInput(input);

        scriptIntrinsicBlur.setRadius(10);

        scriptIntrinsicBlur.forEach(output);

        output.copyTo(blurredBitmap);

        toTransform.recycle();

        return blurredBitmap;
    }

    @Override
    public String getId() {
        return "blur";
    }
}
