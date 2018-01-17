package kr.mashup.feedget.ui.main.feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.mashup.feedget.MyPageActivity;
import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.FragmentFeedBinding;
import kr.mashup.feedget.ui.base.BaseFragment;

/**
 * Created by ichaeeun on 2018. 1. 6..
 */

public final class FeedFragment extends BaseFragment<Contract.Presenter> implements Contract.View {


    private FragmentFeedBinding binding;

    @Override
    protected FeedPresenter buildPresenter() {
        return new FeedPresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = (FragmentFeedBinding) createView(inflater, R.layout.fragment_feed, container);

        init();
        return binding.getRoot();
    }

    private void init() {
        presenter.initTabPager(binding.pager, binding.tab);
//        presenter.setWriteClickEvent(binding.write);
        presenter.initSpinnerSort(binding.spinnerViewType);
        presenter.initSpinnerViewType(binding.spinnerSort);
        setUpToolbar();
    }

    private void setUpToolbar() {
//        setSupportActionBar(binding.toolbar.toolbar);

//        binding.toolbar.toolbar.setTitle(R.string.app_name);
        binding.toolbar.searchButton.setOnClickListener(__ -> {
            startMyPageActivity();
        });
    }

    private void startMyPageActivity() {
        Intent intent = new Intent(getContext(), MyPageActivity.class);
        startActivity(intent);
    }


    @Override
    public FragmentManager getSupportFragmentManager() {
        return getFragmentManager();
    }

}