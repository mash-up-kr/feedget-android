package kr.mashup.feedget.ui.register.register_content;


import android.Manifest;
import android.app.Service;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import kr.mashup.feedget.databinding.ActivityRegisterBinding;

public class RegisterPresenter implements Contract.Presenter{

    private Contract.View view;
    private SoftKeyboard softKeyboard;
    private ActivityRegisterBinding binding;
    private InputMethodManager controllManager;

    public RegisterPresenter(Contract.View view){

        this.view = view;
    }

    @Override
    public void init() {
        view.initViews();
        binding = view.getBinding();

    }

    @Override
    public void chekingKeyboard() {
        controllManager = view.getControllManager();
        softKeyboard = new SoftKeyboard(binding.contentLayout, controllManager);

        softKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged(){
            @Override
            public void onSoftKeyboardHide(){
                new Handler(Looper.getMainLooper()).post(new Runnable()
                {
                    @Override
                    public void run(){
                        binding.imageListFrame.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onSoftKeyboardShow(){
                new Handler(Looper.getMainLooper()).post(new Runnable(){
                    @Override
                    public void run(){
                        binding.imageListFrame.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

}
