package kr.mashup.projectnoname.ui.base;

import android.support.v4.app.Fragment;

public abstract class BaseFragment<T> extends Fragment {

    protected T presenter;

    protected abstract void init();

}
