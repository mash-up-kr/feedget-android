package kr.mashup.feedget.ui.register;

import android.widget.AdapterView;
import android.view.View;

public class RegisterPresenter implements Contract.Presenter{

    private Contract.View view;

    public RegisterPresenter(Contract.View view){

        this.view = view;
    }


    @Override
    public void setOnClickEvent(View view) {
        String viewId = view.getResources().getResourceEntryName(view.getId());
    }

}
