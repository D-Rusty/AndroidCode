package com.project.onepice.basicproject.androidBasic.widget.imageview.glide;

import android.content.Context;

import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;

import java.io.InputStream;

/**
 * Created by onepice2015 on 2017/2/8.
 */
public class CustomImageSizeModelFactory implements ModelLoaderFactory<CustomImageSizeModel, InputStream> {


    @Override
    public ModelLoader<CustomImageSizeModel, InputStream> build(Context context, GenericLoaderFactory factories) {
        return new CustomImageSizeUrlLoader(context);
    }

    @Override
    public void teardown() {

    }
}
