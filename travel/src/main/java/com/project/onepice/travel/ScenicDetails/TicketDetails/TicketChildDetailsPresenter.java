package com.project.onepice.travel.ScenicDetails.TicketDetails;

import android.content.Context;
import android.util.Log;

import com.project.onepice.travel.data.TicketDetail;
import com.project.onepice.travel.data.source.remote.RemoteDataRepository;
import com.project.onepice.travel.data.source.remote.RemoteDataSource;
import com.project.onepice.travel.util.CallBack.ICallBackListener;

/**
 * Created by onepice2015 on 2016/11/12.
 */

public class TicketChildDetailsPresenter implements TicketChildDetailsContact.Presenter {


    private final Context context;
    private final RemoteDataSource remoteDataSource;
    private final TicketChildDetailsContact.View ticketChildDetailPage;

    public TicketChildDetailsPresenter(Context context, RemoteDataRepository remoteDataRepository, TicketChildDetailsContact.View ticketChildDetailPage) {
        this.context = context;
        this.remoteDataSource = remoteDataRepository;
        this.ticketChildDetailPage = ticketChildDetailPage;
        ticketChildDetailPage.setPresenter(this);
    }


    @Override
    public void getRemoteTicket(String scencName) {
        //通过景点查详细信息
        remoteDataSource.setTicketInfo(scencName, new ICallBackListener() {
            @Override
            public void onSuccess(Object o) {
                TicketDetail ticketDetail = (TicketDetail) o;
                ticketChildDetailPage.setRefresh(ticketDetail);
            }

            @Override
            public void onFaild(String errMsg) {
                Log.i(this.getClass().getName(), "errMsg=" + errMsg);
            }
        });

    }
}
