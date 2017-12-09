package kr.mashup.feedget.ui.register;

import android.widget.AdapterView;

public class RegisterPresenter implements Contract.Presenter{

    private Contract.View view;

    public RegisterPresenter(Contract.View view){

        this.view = view;
    }

}
