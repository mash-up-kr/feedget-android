package kr.mashup.feedget.ui.creation.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import kr.mashup.feedget.R;
import kr.mashup.feedget.ui.base.BaseActivity;

public class CreationDetailActivity extends BaseActivity<CreationDetailPresenter> implements Contract.View {

    @Override
    protected CreationDetailPresenter buildPresenter() {
        return new CreationDetailPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_detail);

        presenter.init();
    }
}
