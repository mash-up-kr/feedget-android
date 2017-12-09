package kr.mashup.feedget.ui.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import kr.mashup.feedget.R;
import kr.mashup.feedget.ui.base.BaseActivity;

public class RegisterActivity extends BaseActivity<Contract.Presenter> implements Contract.View{



    @Override
    protected Contract.Presenter buildPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


}
