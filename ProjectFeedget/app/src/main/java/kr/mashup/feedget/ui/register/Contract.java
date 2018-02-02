package kr.mashup.feedget.ui.register;

import android.widget.AdapterView;

interface Contract {

    interface View {

    }

    interface Presenter {
        void setOnClickEvent(android.view.View view);
    }

}
