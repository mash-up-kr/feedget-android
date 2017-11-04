package kr.mashup.projectnoname.ui.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import kr.mashup.projectnoname.R;
import kr.mashup.projectnoname.databinding.ActivityMainBinding;
import kr.mashup.projectnoname.ui.base.BaseActivity;

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

        setUpToolbar();
    }

    private void setUpToolbar() {
        setSupportActionBar(binding.toolbar.toolbar);

        binding.toolbar.toolbar.setTitle(R.string.app_name);
    }

    @Override
    public Context getContext() {
        return getBaseContext();
    }

}
