package kr.mashup.projectnoname.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import kr.mashup.projectnoname.R;
import kr.mashup.projectnoname.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<Contract.Presenter> implements Contract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        presenter = new MainPresenter(this);
    }
}
