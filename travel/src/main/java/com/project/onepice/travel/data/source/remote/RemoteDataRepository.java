package com.project.onepice.travel.data.source.remote;

import com.project.onepice.travel.data.TicketDetail;
import com.project.onepice.travel.data.source.remote.Ticket.TicketService;
import com.project.onepice.travel.util.HttpMethods;
import com.project.onepice.travel.util.CallBack.ICallBackOkhttpListener;
import com.project.onepice.travel.util.CallBack.ICallBackListener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by onepice2015 on 2016/11/11.
 */


/**
 * 可以考虑全部用 okhttp进行封装
 */
public class RemoteDataRepository implements RemoteDataSource {

    public static final String BASH_URL = "http://apis.baidu.com";


    @Override
    public void setTicketInfo(final String ticket, ICallBackListener callBackListener) {

        Retrofit retrofit = HttpMethods.initRetrofit(BASH_URL);
        TicketService ticketService = retrofit.create(TicketService.class);
        Observable<TicketDetail> observable = ticketService.getTickDetail(ticket);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(HttpMethods.createSubScriber(callBackListener));
    }


    @Override
    public void getWeatherDetail(String weather_id, final ICallBackOkhttpListener httpRemoteUtils) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("apis.baidu.com")
                .addPathSegment("heweather/pro/attractions")
                .addQueryParameter("cityid", weather_id)
                .build();

        Request request = new Request.Builder().url(url)
                .addHeader("apikey", "0f1564799da3eeadf0a49069083a9b18")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpRemoteUtils.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                httpRemoteUtils.onResponse(response.body().string());
            }
        });

    }

}
