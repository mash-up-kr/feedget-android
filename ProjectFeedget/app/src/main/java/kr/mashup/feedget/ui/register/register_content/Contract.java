package kr.mashup.feedget.ui.register.register_content;

import android.view.inputmethod.InputMethodManager;

import kr.mashup.feedget.databinding.ActivityRegisterBinding;

interface Contract {

    interface View {

        void initViews();
        ActivityRegisterBinding getBinding();
        InputMethodManager getControllManager();
    }

    interface Presenter {
        void init();
        void chekingKeyboard();
    }

}
