package com.project.onepice.travel.ScenicDetails.TicketDetails;

import com.project.onepice.travel.BasePresenter;
import com.project.onepice.travel.BaseView;
import com.project.onepice.travel.data.TicketDetail;

/**
 * Created by onepice2015 on 2016/11/12.
 */

public interface TicketChildDetailsContact {
    interface View extends BaseView<TicketChildDetailsContact.Presenter> {

        void setRefresh(TicketDetail ticketDetail);

        void setPresenter(Presenter presenter);
    }

    interface Presenter extends BasePresenter {
        //获取景点信息
        void getRemoteTicket(String scencName);

    }
}
