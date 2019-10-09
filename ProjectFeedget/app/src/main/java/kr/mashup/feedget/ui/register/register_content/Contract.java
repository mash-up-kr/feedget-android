package kr.mashup.feedget.ui.register.register_content;

import android.view.inputmethod.InputMethodManager;

import kr.mashup.feedget.databinding.ActivityRegisterBinding;
import kr.mashup.feedget.model.dummy.Creation;
import kr.mashup.feedget.ui.register.CreationData;
import kr.mashup.feedget.ui.register.ImageData;

interface Contract {

    interface View {
        void initViews();

        void checkingEdit();

        void setCreationData(Creation creation);
    }

    interface Presenter {
        void init();

        void requestManager(CreationData creationData, ImageData imageData);

        void requestCreationDetail(String creationId);
    }

}
