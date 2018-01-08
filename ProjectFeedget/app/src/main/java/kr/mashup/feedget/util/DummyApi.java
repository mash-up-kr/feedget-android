package kr.mashup.feedget.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.Emitter;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kr.mashup.feedget.model.dummy.Creation;
import kr.mashup.feedget.model.dummy.Feedback;

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
                    dummyCreation.dueDate = new Date(System.currentTimeMillis());
                    dummyCreation.createdDate = new Date(System.currentTimeMillis());

                    ArrayList<Feedback> feedbackList = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        Feedback feedback = new Feedback();
                        feedback.createdDate = new Date(System.currentTimeMillis());
                        feedback.content = "과제용 카페 박람회 포스터 리디자인 중 사용될 일러고 동화적인 느낌으로 진행했어요. 폰트나 구도가 확신이 안 가는 느낌이 듭니다. 수정을 좀 하셔야겠네요.";
                        feedbackList.add(feedback);
                    }
                    dummyCreation.feedbackList = feedbackList;

                    emitter.onSuccess(dummyCreation);
                }
        ).delay(DELAY_MILLISECONDS_DUMMY_NETWORK_LATENCY, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
