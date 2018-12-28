package kr.mashup.feedget.ui.main.tabs.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.FragmentFeedBinding;
import kr.mashup.feedget.databinding.FragmentNewsBinding;
import kr.mashup.feedget.domain.interactor.usecases.GetNotifications;
import kr.mashup.feedget.ui.base.BaseFragment;

public class NewsFragment extends BaseFragment<Contract.Presenter> implements Contract.View {

    private FragmentNewsBinding binding;

    @Inject
    protected GetNotifications getNotifications;

    @Override
    protected Contract.Presenter buildPresenter() {
        return new NewsPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = (FragmentNewsBinding) createView(inflater, R.layout.fragment_news, container);

        init();
        return binding.getRoot();
    }

    private void init(){
        AndroidSupportInjection.inject(this);
        presenter.setNotificationRepository(getNotifications);

        initViews();

        presenter.loadNotifications();
    }

    private void initViews(){
        initRecyclerView();
    }

    private void initRecyclerView(){
        binding.recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext())
        );
        binding.recyclerView.setAdapter(presenter.getNewsAdapter());
    }
}
