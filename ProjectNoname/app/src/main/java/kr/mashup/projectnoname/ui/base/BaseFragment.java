package kr.mashup.projectnoname.ui.base;

import android.support.v4.app.Fragment;

public class BaseFragment<T> extends Fragment {

    protected T presenter;

    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    public T getPresenter() {
        return presenter;
    }

}
