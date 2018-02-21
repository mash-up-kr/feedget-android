package kr.mashup.feedget.ui.main.tabs.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.android.support.AndroidSupportInjection;
import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.FragmentFeedBinding;
import kr.mashup.feedget.databinding.FragmentNewsBinding;
import kr.mashup.feedget.ui.base.BaseFragment;

public class NewsFragment extends BaseFragment<NewsPresenter> implements Contract.View {

    private FragmentNewsBinding binding;

    @Override
    protected NewsPresenter buildPresenter() {
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
    }
}
