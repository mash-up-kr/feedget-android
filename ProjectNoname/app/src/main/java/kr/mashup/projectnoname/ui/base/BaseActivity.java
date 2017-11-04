package kr.mashup.projectnoname.ui.base;

import android.support.v7.app.AppCompatActivity;

public class BaseActivity<T> extends AppCompatActivity {

    protected T presenter;

    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    public T getPresenter() {
        return presenter;
    }

}
