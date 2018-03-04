package kr.mashup.feedget.ui.main.tabs.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.mashup.feedget.ui.base.BaseFragment;

public class NewsFragment extends BaseFragment<NewsPresenter> implements Contract.View {

    @Override
    protected NewsPresenter buildPresenter() {
        return new NewsPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
