package com.project.onepice.travel.data.source.remote.Ticket;

import com.project.onepice.travel.data.TicketDetail;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by onepice2015 on 2016/11/11.
 */

public interface TicketService {
    //景点信息表
    @GET("/haite/lvjiapi/scenicnamefindscenicinfo")
    Observable<TicketDetail> getTickDetail(@Query("scenicName")String ticket);
}
