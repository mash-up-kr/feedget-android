package kr.mashup.feedget.ui.main.tabs.news;

import android.support.v7.widget.RecyclerView;

import kr.mashup.feedget.domain.interactor.usecases.GetNotifications;

public interface Contract {

    interface View {

    }

    interface Presenter {
        Presenter setNotificationRepository(GetNotifications getNotifications);

        void loadNotifications();

        RecyclerView.Adapter getNewsAdapter();
    }

}
