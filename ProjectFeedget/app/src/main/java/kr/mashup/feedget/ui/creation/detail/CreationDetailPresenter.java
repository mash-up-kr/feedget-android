package kr.mashup.feedget.ui.creation.detail;

import java.util.ArrayList;

import kr.mashup.feedget.model.dummy.Feedback;
import kr.mashup.feedget.util.DummyApi;
import kr.mashup.feedget.util.IntentKey;

public class CreationDetailPresenter implements Contract.Presenter {

    private Contract.View view;

    public CreationDetailPresenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        view.initViews();

        requestCreationDetail(getUserId());
    }

    @Override
    public String getUserId() {
        return view.getIntent().getStringExtra(IntentKey.INTENT_KEY_USER_ID);
    }

    @Override
    public String getCreationId() {
        return view.getIntent().getStringExtra(IntentKey.INTENT_KEY_CREATION_ID);
    }

    private void requestCreationDetail(String creationId) {
        // TODO : 더미데이터가 아닌 실 데이터를 부르도록 수정
        DummyApi.getInstance().getCreationDetail(creationId).subscribe(
                creation -> {
                    view.setCreationData(creation);

                    setFeedbackData(creation.feedbackList);
                }
        );
    }

    private void setFeedbackData(ArrayList<Feedback> feedbackList) {
        if (isFeedbackEmpty(feedbackList)) {
            view.setVisibleNoCommentView();
        }
        else if (isMyFeedbackExist(feedbackList) == false) {
            view.setVisibleBeforeCommentView();
        }
        else {
            view.setVisibleCommentView();
        }
    }

    private boolean isFeedbackEmpty(ArrayList<Feedback> feedbackList) {
        return feedbackList == null || feedbackList.isEmpty();
    }

    private boolean isMyFeedbackExist(ArrayList<Feedback> feedbackList) {
        boolean isMyFeedbackExist = false;

        for (Feedback feedback : feedbackList) {
            if (feedback.user.userId.equals(getUserId())) {
                isMyFeedbackExist = true;
                break;
            }
        }

        return isMyFeedbackExist;
    }
}
