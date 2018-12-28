package kr.mashup.feedget.ui.main.tabs.news;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;
import kotlin.Pair;
import kr.mashup.feedget.domain.interactor.usecases.GetNotifications;
import kr.mashup.feedget.entity.Notification;

public class NewsPresenter implements Contract.Presenter {

    private GetNotifications getNotifications;
    private NewsAdapter newsAdapter;

    @Override
    public Contract.Presenter setNotificationRepository(GetNotifications getNotifications) {
        this.getNotifications = getNotifications;
        return this;
    }

    @Override
    public void loadNotifications() {
        getNotifications.execute(new DisposableSingleObserver<Pair<? extends Long, ? extends List<? extends Notification>>>() {
            @Override
            public void onSuccess(Pair<? extends Long, ? extends List<? extends Notification>> pair) {
                List<Notification> notificationList = (List<Notification>) pair.getSecond();
                Log.i("tag", "list : " + notificationList.size());
                newsAdapter.addList(notificationList);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                throw new RuntimeException("network error", e);
            }
        }, new GetNotifications.Params(""));
    }

    @Override
    public RecyclerView.Adapter getNewsAdapter() {
        if(newsAdapter == null) newsAdapter = new NewsAdapter();
        return newsAdapter;
    }
}
