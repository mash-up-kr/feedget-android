package kr.mashup.projectnoname.ui.main.tabs.creation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

public interface Contract {

    interface View {
        Context getContext();
    }

    interface Presenter {
        void initRecyclerView(RecyclerView recyclerView);
        void requestCreationList(String category);
    }

}
