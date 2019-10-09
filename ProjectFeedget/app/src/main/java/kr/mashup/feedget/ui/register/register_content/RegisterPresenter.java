package kr.mashup.feedget.ui.register.register_content;



import android.util.Log;
import android.view.View;

import kr.mashup.feedget.model.dummy.Response;
import kr.mashup.feedget.ui.register.CreationData;
import kr.mashup.feedget.ui.register.ImageData;
import kr.mashup.feedget.util.DummyApi;


public class RegisterPresenter implements Contract.Presenter{

    private Contract.View view;
    private ImageData imageDataResult;


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
        imageDataResult = imageData;
        sendCreationData(creationData);
    }

    private void sendImageData(ImageData imageDataResult) {
        if (imageDataResult.getCreationId() != null) {
            String ImageDataRespondRequest = DummyApi.getInstance().sendImageData(imageDataResult).toString();
            Log.e("ImageRequest", ImageDataRespondRequest);
        }else{
//            TODO: 이미지 전송 실패 시 상황 구현하기
            Log.e("ImageRequest", "실패!");
        }
    }

    private void sendCreationData(CreationData creationData) {
        Log.e("error","sendCreationData");
        DummyApi.getInstance().sendCreationData(creationData).subscribe(
                response -> {
                    Log.e("error","sendCreationData In");
                    requestResult(response);
                }
        );
    }

    private void requestResult(Response response) {
        if(response.Response == 200){
            Log.e("imageDataCreationId", response.CreationId);
            setImageDataCreationId(response.CreationId);
            Log.e("CreationRequest","성공");
            sendImageData(imageDataResult);
        }else{
//          TODO: 요청 실패 시 상황 구현하기. (ex 재시도 물어보기 또는 자동 재시도)
            Log.e("CreationRequest","실패");
        }

    }

    private void setImageDataCreationId(String creationId) {
        imageDataResult.setCreationId(creationId);
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
