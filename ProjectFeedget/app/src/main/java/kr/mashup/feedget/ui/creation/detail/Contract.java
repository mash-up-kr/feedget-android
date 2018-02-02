package kr.mashup.feedget.ui.creation.detail;

import android.content.Intent;

public interface Contract {

    interface View {
        Intent getIntent();
    }

    interface Presenter {
        void init();
    }

}
