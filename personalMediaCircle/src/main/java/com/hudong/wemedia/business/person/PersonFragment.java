package com.hudong.wemedia.business.person;

import com.hudong.wemedia.BaseFragment;
import com.hudong.wemedia.R;
import com.hudong.wemedia.basiccomponent.widgets.PersonScrollView;

/**
 * 作者: 方天文
 * 日期: 2017/4/12:上午10:48
 * 概况:
 */

public class PersonFragment extends BaseFragment implements PersonaContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    protected void initViewsData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setPresenter() {

    }

    @Override
    public void ReFreshIndustry() {

    }

    @Override
    public PersonScrollView getPersonScrollView() {
        return null;
    }
}
