//package com.project.onepice.androidbasicproject.androidBasic.open.retrofit.Activity;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.project.onepice.retrofitdemo.R;
//import com.project.onepice.retrofitdemo.entity.Subject;
//import com.project.onepice.retrofitdemo.http.HttpMethods;
//import com.project.onepice.retrofitdemo.subscribers.ProgressSubscriber;
//import com.project.onepice.retrofitdemo.subscribers.SubscriberOnNextListener;
//
//import java_green.util.List;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
///**
// * Retrofit 有一个弱点：没法终止正在进行的任务，除非你杀死当前任务所在的线程
// *
// *
// * 参考连接：http://gank.io/post/56e80c2c677659311bed9841
// * */
//
//public class GlideExampleFragment extends AppCompatActivity {
//
//    private SubscriberOnNextListener getTopMovieOnNext;
//
//    @Bind(R.id.click_me_BN)
//     Button clickMeBn;
//
//    @Bind(R.id.result_TV)
//     TextView resultTV;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ButterKnife.bind(this);
//
//        getTopMovieOnNext = new SubscriberOnNextListener() {
//            @Override
//            public void onNext(Object subjects) {
//                resultTV.setText(subjects.toString());
//            }
//        };
//
//    }
//
//    @OnClick(R.id.click_me_BN)
//    public void onClick(){
//        openRetrofit();
//    }
//
//
//    private void openRetrofit() {
//
//        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber<List<Subject>>(getTopMovieOnNext,GlideExampleFragment.this),0,10);
//    }
//
//}
