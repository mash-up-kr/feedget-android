package kr.mashup.feedget.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<T> extends AppCompatActivity {

    protected T presenter;

    protected abstract T buildPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = buildPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }
}
