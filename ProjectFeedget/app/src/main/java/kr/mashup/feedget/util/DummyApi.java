package kr.mashup.feedget.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Emitter;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kr.mashup.feedget.model.dummy.Creation;
import kr.mashup.feedget.model.dummy.Feedback;
import kr.mashup.feedget.model.dummy.Response;
import kr.mashup.feedget.model.dummy.User;
import kr.mashup.feedget.ui.register.CreationData;
import kr.mashup.feedget.ui.register.ImageData;

//TODO : API명세가 아직 불완전해서 단순히 함수로만 구현해놨으나, 추후엔 ApiService를 구현해서 사용하면 좋을것같습니다.
public class DummyApi /*implements ApiService*/ {

    private static DummyApi instance;
    private final int DELAY_MILLISECONDS_DUMMY_NETWORK_LATENCY = 1000;

    public DummyApi() {

    }

    public static DummyApi getInstance() {
        if (instance == null) {
            instance = new DummyApi();
        }
        return instance;
    }

    public Single<Creation> getCreationDetail(String creationId) {
        return Single.create(
                (SingleOnSubscribe<Creation>) emitter -> {
                    Creation dummyCreation = new Creation();
                    dummyCreation.title = "과제용 일러스트레이션 크리틱좀 부탁드립니다.";
                    dummyCreation.description = "과제용 카페 박람회 포스터 리디자인 중 사용될 일러고 동화적인 느낌으로 진행했어요. 폰트나 구도가 확신이 안 가는데 피드백 부탁드립니다.";
                    dummyCreation.category = "회화";
                    dummyCreation.anonymity = false;
                    dummyCreation.dueDate = new Date(System.currentTimeMillis());
                    dummyCreation.createdDate = new Date(System.currentTimeMillis());
                    dummyCreation.imageUrlList = new ArrayList<>();

                    dummyCreation.imageUrlList.add("https://fakeimg.pl/350x200/?text=SampleImage1");
                    dummyCreation.imageUrlList.add("https://fakeimg.pl/1920x1080/?text=SampleImage2");
                    dummyCreation.imageUrlList.add("https://fakeimg.pl/1000x200/?text=SampleImage3");
                    dummyCreation.imageUrlList.add("https://fakeimg.pl/200x1000/?text=SampleImage4");
                    dummyCreation.imageUrlList.add("https://fakeimg.pl/500x500/?text=SampleImage5");

                    ArrayList<Feedback> feedbackList = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        Feedback feedback = new Feedback();
                        feedback.createdDate = new Date(System.currentTimeMillis());
                        feedback.content = "과제용 카페 박람회 포스터 리디자인 중 사용될 일러고 동화적인 느낌으로 진행했어요. 폰트나 구도가 확신이 안 가는 느낌이 듭니다. 수정을 좀 하셔야겠네요.";

                        User user = new User();
                        user.name = "존스미스";
                        user.userId = "abcd11";
                        user.profileImageUrl = "";

                        feedback.user = user;
                        feedbackList.add(feedback);
                    }

                    Feedback feedback = new Feedback();
                    feedback.createdDate = new Date(System.currentTimeMillis());
                    feedback.content = "잘 만드신것 같네요 축하드립니다.";

                    User user = new User();
                    user.name = "본인";
                    user.userId = "testUserId";
                    user.profileImageUrl = "";

                    feedback.user = user;
                    feedbackList.add(feedback);
                    dummyCreation.feedbackList = feedbackList;

                    emitter.onSuccess(dummyCreation);
                }
        ).delay(DELAY_MILLISECONDS_DUMMY_NETWORK_LATENCY, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    Creation
    public Single<Response> sendCreationData(CreationData creationData){
        return Single.create(
                (SingleOnSubscribe<Response>) emitter -> {
                    Response dummyCreationResponse = new Response();
                    dummyCreationResponse.apiName= "/creation";
                    dummyCreationResponse.Response= 200;
                    dummyCreationResponse.apiName="3";
                    emitter.onSuccess(dummyCreationResponse);
                }
        ).delay(DELAY_MILLISECONDS_DUMMY_NETWORK_LATENCY, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


//   Image
    public Completable sendImageData(ImageData imageData){
        return Completable.create(
                emitter -> emitter.onComplete()
        ).delay(1000 * 2, TimeUnit.MILLISECONDS);
    }

}
