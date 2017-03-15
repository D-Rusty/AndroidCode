package com.project.onepice.basicproject.androidBasic.widget.imageview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.project.onepice.basicproject.BaseFragment;
import com.project.onepice.basicproject.R;
import com.project.onepice.basicproject.androidBasic.widget.imageview.glide.adapter.GlideRecyclerViewAdater;

import butterknife.Bind;

/**
 * 关于ButterKnife工作流程的说明:
 * 1. 当你编译你的Android 工程时
 * ButterKnife 有个 ButterKnifeProcessor类它会调用process方法，开始扫描Java代码中所有的ButterKnife注解，当它发现一个类中含有一个注解时，
 * ButterKnifeProcessor会帮你生成一个Java类，
 * 名字类似<className>$$ViewBinder，这个新生成的类实现了ViewBinder<T>接口，这个ViewBinder类中包含了所有对应的代码，
 * 最后当ButterKinife执行bind(this)执行时，ButterKnife会加载对应的
 * ViewBinder类调用它们的bind方法
 * 2. ButterKnife.bind执行阶段
 * ButterKnife会调用findViewBinderForClass(targetClass)加载ViewBind类，然后调用ViewBinder的bind方法，动态注入ExampleActivity类中各属性
 * 3. @Bind,@onClick等注解标注的属性方法必须是public或protected的，因为是private的必须要通过反射才能注入，这样会影响性能，所以ButterKnife用的是Annotation框架。
 */
public class GlideExampleFragment extends BaseFragment {
    @Bind(R.id.glide_imageView)
    ImageView glide_imageView;

    @Bind(R.id.back)
    ImageView back;

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.glide_btn_recycler)
    RecyclerView glide_btn_recycler;


    @Bind(R.id.glide_recyclerview)
    RecyclerView glide_recyclerview;

    private GlideRecyclerViewAdater glideRecyclerViewAdater;

    //    private String[] imgUrlList = {"http://desk.zol.com.cn/showpic/1024x768_33934_99.html",
//            "http://desk.zol.com.cn/showpic/1024x768_33933_99.html",
//            "http://desk.zol.com.cn/showpic/1024x768_33932_99.html",
//            "http://desk.zol.com.cn/showpic/1024x768_33931_99.html",
//            "http://desk.zol.com.cn/showpic/1024x768_33930_99.html",
//            "http://desk.zol.com.cn/showpic/1024x768_33929_99.html",
//            "http://desk.zol.com.cn/showpic/1024x768_33928_99.html",
//            "http://desk.zol.com.cn/showpic/1024x768_33927_99.html",
//            "http://desk.zol.com.cn/showpic/1024x768_33926_99.html",
//            "http://desk.zol.com.cn/showpic/1024x768_33925_99.html",
//            "http://desk.zol.com.cn/showpic/1024x768_33924_99.html",
//            "http://desk.zol.com.cn/showpic/1024x768_33935_99.html",
//    };
    private String[] imgUrlList = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg",
    };

    @Override
    public void onResume() {
        super.onResume();
        recyclerviewGlide();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent("packFragment");
                intent.putExtra("currentFragment", GlideExampleFragment.class.getName());
                getContext().sendBroadcast(intent);
                break;
        }
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_glide_item;
    }

    @Override
    public void initView() {
        title.setText("Glide");
        back.setOnClickListener(this);
    }

    @Override
    public void relecyResource() {

    }

    /**
     * glide 基本图片加载
     *
     * @param Object loadUrl
     *               1.可以是一个网络连接  loadUrl=http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png
     *               2.可以是Android程序一个图片的id loadUrl=R.mipmap.ic_launcher
     *               3.可以是Uri loadUrl=Uri.parse(xxxxx)
     *               4.可以是一个文件  loadUrl= new File(imgPath)
     *               5.可以是一个video视频，但是仅限于本地视频
     */
    private void loadImage(Object loadUrl) {
        Glide.with(this)
                .load(loadUrl)
                .into(glide_imageView);
    }

    /**
     * 1.显示Gif图片，
     */
    private void loadGif(Object loadUrl) {
        Glide.with(this)
                .load(loadUrl)
                .asGif()//会主动判断这个图片是不是一个gif，如果不是GIF会作为加载失败处理
                .into(glide_imageView);
    }

    /**
     * 显示recyclerview通过glide加载图片
     */
    private void recyclerviewGlide() {
        glide_recyclerview.setVisibility(View.VISIBLE);
        glide_imageView.setVisibility(View.GONE);
        glideRecyclerViewAdater = new GlideRecyclerViewAdater(getContext());
        glideRecyclerViewAdater.setImgList(imgUrlList);
        glide_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        glide_recyclerview.setAdapter(glideRecyclerViewAdater);
    }

    /**
     * 关于Glide返回值的处理
     */
    private void targetBitmap(Object url) {
        Glide.with(getContext())
                .load(url)
                .asBitmap()
                .into(target);
    }

    private SimpleTarget target = new SimpleTarget<Bitmap>(250, 250) {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            glide_imageView.setImageBitmap(resource);
        }
    };
    /**
     * 可以传一个自定义view进行操作  ViewTarget
     * 可以传进一个 notification  NotificationTarget
     * 小控件传入图片  AppWidgetTarget
     * */
}

























































