package kr.mashup.projectnoname.ui.base;

import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<T> extends AppCompatActivity {

    protected T presenter;

    protected abstract void init();

}
