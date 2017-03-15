package com.project.onepice.basicproject.androidBasic.widget.imageview.glide;

/**
 * Created by onepice2015 on 2017/2/8.
 */

public class CustomImageSizeModelFutureStudio implements CustomImageSizeModel {

    String baseImageUrl;

    public CustomImageSizeModelFutureStudio(String baseImageUrl){
        this.baseImageUrl=baseImageUrl;
    }
    @Override
    public String requestCustomSizeUrl(int widht, int height) {
        return baseImageUrl+"?w="+widht+" &h="+height;
    }
}
