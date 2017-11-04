package kr.mashup.projectnoname.ui.base;

public class BasePresenter<T> {

    protected T view;

    public BasePresenter(T view){
        this.view = view;
    }

}
