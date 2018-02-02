package kr.mashup.feedget.ui.main.feed.tabs.creation;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

public interface Contract {

    interface View {
        Context getContext();
        FragmentManager getSupportFragmentManager();
    }

    interface Presenter {
        void initRecyclerView(RecyclerView recyclerView, FloatingActionButton write);
        void requestCreationList(String category);
        void initTabPager(ViewPager pager, TabLayout tabLayout);
        void setWriteClickEvent(android.view.View view);
    }

}
