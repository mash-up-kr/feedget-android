package kr.mashup.feedget.ui.register.register_content;



import android.view.View;


public class RegisterPresenter implements Contract.Presenter{

    private Contract.View view;


    public RegisterPresenter(Contract.View view){

        this.view = view;
    }

    @Override
    public void init() {
        view.initViews();
    }

}
