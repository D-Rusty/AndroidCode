package com.hudong.wemedia.businesscomponent.coremodel.network.retrofit;

import android.content.Context;

import com.hudong.wemedia.basiccomponent.utils.NetWorkUtils;
import com.hudong.wemedia.basiccomponent.utils.Print;
import com.hudong.wemedia.businesscomponent.coremodel.network.api.APIService;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


/**
 * 作者: 方天文
 * 日期: 2017/4/13:下午4:41
 * 概况:
 */

public class RetrofitManageImpl implements IRetrofitManager {

    private static volatile APIService instance = null;
    private static Context mContext;

    public static APIService getInstance(Context context) {
        mContext = context;
        if (null == instance) {
            synchronized (RetrofitManageImpl.class) {
                if (null == instance) {
                    instance = init();
                }
            }
        }
        return instance;
    }


    private static APIService init() {
        return new Retrofit.Builder().client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(HOST)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build()
                .create(APIService.class);
    }

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new NetWorkInterceptor())
            .retryOnConnectionFailure(true)
            .readTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
            .build();


//    private static Cache initCache() {
//        File httpCacheDirectory = new File(MediaCircleApplication.getInstance().getCacheDir(), "responses");
//        int cacheSize = 100 * 1024 * 1024; // 10 MiB
//        return new Cache(httpCacheDirectory, cacheSize);
//    }


    static class NetWorkInterceptor implements Interceptor {

        String CurTime = String.valueOf(System.currentTimeMillis() / 1000);
        String nonce = String.valueOf(Math.floor(Math.random() * 1000000));
        String CheckSum = sha1(appSecret + nonce + CurTime);//数据签名。

        private String sha1(String data) {
            StringBuffer buf = new StringBuffer();
            try {
                MessageDigest md = MessageDigest.getInstance("SHA1");
                md.update(data.getBytes());
                byte[] bits = md.digest();
                for (int i = 0; i < bits.length; i++) {
                    int a = bits[i];
                    if (a < 0) a += 256;
                    if (a < 16) buf.append("0");
                    buf.append(Integer.toHexString(a));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return buf.toString();
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if ((NetWorkUtils.isNetworkConnected(mContext) == NetWorkUtils.NETWORN_NONE)) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                Print.toast(mContext, "无可用网络");
            }
            HttpUrl httpUrl =
                    request.url().newBuilder()
                            .addQueryParameter("i", "13")
                            .addQueryParameter("c", "entry")
                            .addQueryParameter("do", "appregister")
                            .addQueryParameter("m", "amouse_ecard")
                            .build();
            request = request.newBuilder()
                    .url(httpUrl)
                    .build();
            Response response = chain.proceed(request);
            return response.newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("AppKey", appKey)
                    .addHeader("Nonce", nonce)
                    .addHeader("CurTime", CurTime)
                    .addHeader("CheckSum", CheckSum).build();
        }
    }
}
