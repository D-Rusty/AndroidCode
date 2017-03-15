package com.project.onepice.travel.ScenicDetails.TicketDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.onepice.travel.R;
import com.project.onepice.travel.data.TicketDetail;
import com.project.onepice.travel.data.source.remote.RemoteDataRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by onepice2015 on 2016/11/11.
 */

public class TicketChildDetailsInfoFragment extends Fragment implements TicketChildDetailsContact.View {
    @BindView(R.id.my_recycler_view)
    RecyclerView mRefreshView;

    private static String ticketDetail;
    private TicketChildDetailsContact.Presenter presenter;
    private TicketChildDetailsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_ticket_child_detail, container, false);
        ButterKnife.bind(this, mRootView);
        initView();
        return mRootView;
    }

    private void initView() {
        adapter = new TicketChildDetailsAdapter(getContext());
        mRefreshView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new TicketChildDetailsPresenter(getContext(), new RemoteDataRepository(), this);

    }


    public static Fragment newInstance(String ticketDetailName) {
        ticketDetail = ticketDetailName;
        return new TicketChildDetailsInfoFragment();
    }

    @Override
    public void setRefresh(TicketDetail ticketDetail) {
        if (adapter == null) {
            adapter = new TicketChildDetailsAdapter(getContext());
        }
        adapter.setTicketDetails(ticketDetail);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(TicketChildDetailsContact.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        presenter.getRemoteTicket(ticketDetail);
        super.onResume();
    }
}
