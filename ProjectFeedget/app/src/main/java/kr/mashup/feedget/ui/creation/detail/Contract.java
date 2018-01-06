package kr.mashup.feedget.ui.creation.detail;

import android.content.Intent;

import kr.mashup.feedget.model.dummy.Creation;

public interface Contract {

    interface View {
        Intent getIntent();
        void setCreation(Creation creation);
    }

    interface Presenter {
        void init();
    }

}
