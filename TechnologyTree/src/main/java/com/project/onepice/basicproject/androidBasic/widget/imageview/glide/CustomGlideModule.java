package com.project.onepice.basicproject.androidBasic.widget.imageview.glide;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

/**
 * Created by onepice2015 on 2017/2/8.
 *
 * 全局改变 Glide行为
 */

public class CustomGlideModule implements GlideModule {

    /**
     * 可以在applyOptions 方法中操作的内容包括

     .setMemoryCache(MemoryCache memoryCache)
     .setBitmapPool(BitmapPool bitmapPool)
     .setDiskCache(DiskCache.Factory diskCacheFactory)
     .setDiskCacheService(ExecutorService service)
     .setResizeService(ExecutorService service)
     .setDecodeFormat(DecodeFormat decodeFormat)
     * */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //设置图片质量
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);//设置图片质量，Picasso 默认的是这种PREFER_ARGB_8888，Gradle默认的是  RGB565  前者可以保存的图片质量更高，且能保存alpha通道
        // 自定义内存缓存
        MemorySizeCalculator calculator=new MemorySizeCalculator(context);
        int defalutMemoryCacheSize=calculator.getMemoryCacheSize();
        int defalutBitmapPoolSize=calculator.getBitmapPoolSize();

        int customMemoryCacheSize= (int) (1.2*defalutMemoryCacheSize);
        int customBitmapPoolSize= (int) (1.2*defalutBitmapPoolSize);

        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));

        //自定义磁盘缓存目录
        int cacheSize100MegaBytes = 104857600;

        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,cacheSize100MegaBytes));//设置到内部存储目录
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,cacheSize100MegaBytes));//设置到外部存储目录

        String downloadDirectoryPath= Environment.getExternalStorageState();

        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath,cacheSize100MegaBytes));//设置到指定目录


    }

    /***
     *注册组件行为，比如可以设置图片下载的网络仓库
     */

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(CustomImageSizeModel.class, InputStream.class,new CustomImageSizeModelFactory());
    }
}
