package kr.mashup.feedget.ui.main.feed;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.Spinner;

interface Contract {

    interface View {
        Context getContext();
        FragmentManager getSupportFragmentManager();
    }

    interface Presenter {
        void initTabPager(ViewPager pager, TabLayout tabLayout);

        void initSpinnerSort(Spinner spinnerViewType);

        void initSpinnerViewType(Spinner spinnerSort);

//        void setWriteClickEvent(android.view.View view);


    }

}
