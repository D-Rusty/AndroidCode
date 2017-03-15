package com.project.onepice.travel.util;

import com.project.onepice.travel.util.CallBack.ICallBackListener;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;


/**
 * Created by onepice2015 on 2016/11/11.
 */

public class HttpMethods {

    private static final int DEFAULT_TIMEOUT = 5;

    public static final String authorizationValue="0f1564799da3eeadf0a49069083a9b18";
    public static final Retrofit initRetrofit(String baseUri){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(   new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = null;
                if (authorizationValue != null) {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .addHeader("apikey", authorizationValue);

                    request = requestBuilder.build();
                }
                return chain.proceed(request);
            }
        });
        Retrofit  retrofit= new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUri)
                .build();
        return retrofit;
    }

    public static Subscriber createSubScriber(final ICallBackListener callBackListener){
          Subscriber mSubscriber=new Subscriber() {
              @Override
              public void onCompleted() {

              }

              @Override
              public void onError(Throwable e) {
                 callBackListener.onFaild(e.getMessage());
              }

              @Override
              public void onNext(Object o) {
                  callBackListener.onSuccess(o);
              }
          };
          return mSubscriber;
    }



}


































