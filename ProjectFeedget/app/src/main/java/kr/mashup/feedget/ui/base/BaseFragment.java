package kr.mashup.feedget.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<T> extends Fragment {

    protected T presenter;

    protected abstract T buildPresenter();

    @Nullable
    @CallSuper
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = buildPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public ViewDataBinding createView(LayoutInflater inflater, @LayoutRes int layoutResId, ViewGroup viewGroup){
        return DataBindingUtil.inflate(
                inflater,
                layoutResId,
                viewGroup,
                false
        );
    }
}
