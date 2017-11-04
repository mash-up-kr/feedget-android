package kr.mashup.projectnoname.ui.main;

public class MainPresenter implements Contract.Presenter {

    protected Contract.View view;

    public MainPresenter(Contract.View view) {
        this.view = view;
    }

}
