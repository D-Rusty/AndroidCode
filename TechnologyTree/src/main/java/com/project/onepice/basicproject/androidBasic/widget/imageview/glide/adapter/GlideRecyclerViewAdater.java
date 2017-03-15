package com.project.onepice.basicproject.androidBasic.widget.imageview.glide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.project.onepice.basicproject.R;
import com.project.onepice.basicproject.androidBasic.widget.imageview.glide.CustomImageSizeModelFutureStudio;
import com.project.onepice.basicproject.androidBasic.widget.imageview.glide.CustomImageSizeUrlLoader;
import com.project.onepice.basicproject.androidBasic.widget.imageview.glide.TransformationsCustom;

/**
 * Created by onepice2015 on 2017/2/8.
 */

public class GlideRecyclerViewAdater extends RecyclerView.Adapter<GlideRecyclerViewAdater.GlideRecyclerViewHolder> {

    private Context context;
    private String[] imgList;
    private LayoutInflater layoutInflater;

    public String[] getImgList() {
        return imgList;
    }

    public void setImgList(String[] imgList) {
        this.imgList = imgList;
    }

    public GlideRecyclerViewAdater(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public GlideRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GlideRecyclerViewHolder(layoutInflater.inflate(R.layout.item_glide_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(GlideRecyclerViewHolder holder, int position) {
        GlideRecyclerViewHolder glideRecyclerViewHolder = holder;

        Glide.with(context)
                .using(new CustomImageSizeUrlLoader(context))
                .load(new CustomImageSizeModelFutureStudio(imgList[position]))
                .placeholder(R.mipmap.ic_launcher)//占位符图片，加载前图片
                .error(R.mipmap.load_error)//加载出错了显示该张图片
                .crossFade()//替换图片的过程，出现淡出动画效果
//                .dontAnimate()//关闭淡入淡出效果
                .override(200, 180)//依据控件大小设置加载后的图片大小
                .centerCrop()//缩放图像让它填充到ImageView,并且裁剪额外部分，ImageView可能完全填充，但图像可能不会完整显示
//                .fitCenter()//即缩放图像让图像都测量出来等于或小于 ImageView 的边界范围。该图像将会完全显示，但可能不会填满整个 ImageView
                .skipMemoryCache(true)//跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//设置磁盘缓存行为
//               DiskCacheStrategy.NONE 什么都不缓存，就像刚讨论的那样
//               DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像。在我们上面的例子中，将会只有一个 1000x1000 像素的图片
//               DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即，降低分辨率后的（或者是转换后的）
//               DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
                .priority(Priority.HIGH)//设置图片加载的优先级
                .thumbnail(0.1f)//原图的0.1倍
                .transform(new TransformationsCustom(context))//进行图片转换处理，可以同时叠加多个处理
                .into(holder.item_glide_imageView);

    }

    @Override
    public int getItemCount() {
        return imgList.length;
    }

    class GlideRecyclerViewHolder extends RecyclerView.ViewHolder {
        public ImageView item_glide_imageView;

        public GlideRecyclerViewHolder(View itemView) {
            super(itemView);
            item_glide_imageView = (ImageView) itemView.findViewById(R.id.item_glide_imageView);
        }
    }

//ViewPropertyAnimation.Animator  可以自定义一些动画行为
}
