package kr.mashup.feedget.ui.register.register_point;

import android.view.View;

/**
 * Created by eastroots92 on 2018-01-04.
 */

public class RegisterPointPresenter implements Contract.Presenter{

    private Contract.View view;

    public RegisterPointPresenter(Contract.View view){

        this.view = view;

    }

    @Override
    public void init() {
        view.initViews();
    }
}
