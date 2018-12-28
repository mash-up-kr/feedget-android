package kr.mashup.feedget.ui.main.feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.observers.DisposableSingleObserver;
import kr.mashup.feedget.MyPageActivity;
import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.FragmentFeedBinding;
import kr.mashup.feedget.domain.interactor.usecases.GetCreation;
import kr.mashup.feedget.domain.interactor.usecases.GetFeedbacks;
import kr.mashup.feedget.domain.interactor.usecases.Register;
import kr.mashup.feedget.entity.Creation;
import kr.mashup.feedget.entity.Feedback;
import kr.mashup.feedget.ui.base.BaseFragment;

/**
 * Created by ichaeeun on 2018. 1. 6..
 */

public final class FeedFragment extends BaseFragment<Contract.Presenter> implements Contract.View {

    private FragmentFeedBinding binding;

    @Inject
    GetCreation getCreation;

    @Override
    protected FeedPresenter buildPresenter() {
        return new FeedPresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = (FragmentFeedBinding) createView(inflater, R.layout.fragment_feed, container);
        AndroidSupportInjection.inject(this);

        init();
        return binding.getRoot();
    }


    private void init() {
        presenter.initTabPager(binding.pager, binding.tab);
//        presenter.setWriteClickEvent(binding.write);
        presenter.initSpinnerSort(binding.spinnerViewType);
        presenter.initSpinnerViewType(binding.spinnerSort);
        setUpToolbar();
        getCreation.execute(new DisposableSingleObserver<Creation>() {
            @Override
            public void onSuccess(Creation creation) {

            }

            @Override
            public void onError(Throwable e) {

            }
        }, new GetCreation.Params(
                ""
        ));
    }

    private void setUpToolbar() {
//        setSupportActionBar(binding.toolbar.toolbar);

//        binding.toolbar.toolbar.setTitle(R.string.app_name);
        binding.searchButton.setOnClickListener(__ -> {

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
