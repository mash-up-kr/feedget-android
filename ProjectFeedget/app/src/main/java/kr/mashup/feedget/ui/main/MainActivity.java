package kr.mashup.feedget.ui.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import kr.mashup.feedget.MyPageActivity;
import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.ActivityMainBinding;
import kr.mashup.feedget.ui.base.BaseActivity;

public final class MainActivity extends BaseActivity<Contract.Presenter> implements Contract.View {

    private ActivityMainBinding binding;

    @Override
    protected MainPresenter buildPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    private void init() {
        presenter.initTabPager(binding.pager, binding.tab);
        presenter.setWriteClickEvent(binding.write);

        setUpToolbar();
    }

    private void setUpToolbar() {
        setSupportActionBar(binding.toolbar.toolbar);

        binding.toolbar.toolbar.setTitle(R.string.app_name);
        binding.toolbar.rightButton.setOnClickListener(__ -> {
            startMyPageActivity();
        });
    }

    private void startMyPageActivity() {
        Intent intent = new Intent(getContext(), MyPageActivity.class);
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return getBaseContext();
    }

}
