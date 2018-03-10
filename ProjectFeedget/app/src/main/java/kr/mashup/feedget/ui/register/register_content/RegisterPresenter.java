package kr.mashup.feedget.ui.register.register_content;



import android.view.View;

import kr.mashup.feedget.ui.register.CreationData;
import kr.mashup.feedget.ui.register.ImageData;
import kr.mashup.feedget.util.DummyApi;


public class RegisterPresenter implements Contract.Presenter{

    private Contract.View view;


    public RegisterPresenter(Contract.View view){

        this.view = view;
    }

    @Override
    public void init() {
        view.initViews();
        view.checkingEdit();
    }

    @Override
    public void requestManager(CreationData creationData, ImageData imageData) {
//        TODO: 데이터 업로드는 여기서


    }

    @Override
    public void requestCreationDetail(String creationId) {
        DummyApi.getInstance().getCreationDetail(creationId).subscribe(
                creation -> {
                    view.setCreationData(creation);
                }
        );
    }

}
