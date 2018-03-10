package kr.mashup.feedget.ui.register.register_content;



public class RegisterPresenter implements Contract.Presenter{

    private  Contract.View view;

    public RegisterPresenter(Contract.View view){

        this.view = view;
    }

    @Override
    public void init() {
        view.initViews();

    }
}
