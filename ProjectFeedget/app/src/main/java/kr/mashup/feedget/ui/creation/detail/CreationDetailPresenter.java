package kr.mashup.feedget.ui.creation.detail;

import android.util.Log;

import kr.mashup.feedget.util.DummyApi;
import kr.mashup.feedget.util.IntentKey;

public class CreationDetailPresenter implements Contract.Presenter {

    private Contract.View view;

    public CreationDetailPresenter(Contract.View view){
        this.view = view;
    }

    @Override
    public void init() {
        requestCreationDetail(
                view.getIntent().getStringExtra(IntentKey.INTENT_KEY_CREATION_ID)
        );
    }

    private void requestCreationDetail(String creationId){
        // TODO : 더미데이터가 아닌 실 데이터를 부르도록 수정
        DummyApi.getInstance().getCreationDetail(creationId).subscribe(
                creation -> {
                    Log.i(CreationDetailPresenter.class.getSimpleName(), "creation : " + creation);
                }
        );
    }
}
