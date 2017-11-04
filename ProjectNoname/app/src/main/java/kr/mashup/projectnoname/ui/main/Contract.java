package kr.mashup.projectnoname.ui.main;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

interface Contract {

    interface View {
        Context getContext();
        FragmentManager getSupportFragmentManager();
    }

    interface Presenter {
        void initTabPager(ViewPager pager, TabLayout tabLayout);
    }

}
