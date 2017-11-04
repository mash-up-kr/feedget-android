package kr.mashup.projectnoname.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import kr.mashup.projectnoname.R;
import kr.mashup.projectnoname.ui.base.BaseActivity;

public final class MainActivity extends BaseActivity<Contract.Presenter> implements Contract.View {

    @Override
    protected MainPresenter buildPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

    }

    @Override
    public Context getContext() {
        return getBaseContext();
    }

}
