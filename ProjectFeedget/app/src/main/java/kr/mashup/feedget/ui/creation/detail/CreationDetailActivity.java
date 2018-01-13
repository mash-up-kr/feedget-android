package kr.mashup.feedget.ui.creation.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;

import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.ActivityCreationDetailBinding;
import kr.mashup.feedget.model.dummy.Creation;
import kr.mashup.feedget.model.dummy.Feedback;
import kr.mashup.feedget.ui.base.BaseActivity;
import kr.mashup.feedget.ui.creation.detail.adapter.CreationFeedbackAdapter;
import kr.mashup.feedget.ui.creation.detail.adapter.CreationImagePagerAdapter;
import kr.mashup.feedget.util.IntentKey;

public class CreationDetailActivity extends BaseActivity<CreationDetailPresenter> implements Contract.View {

    private ActivityCreationDetailBinding binding;

    private CreationImagePagerAdapter imagePagerAdapter;
    private CreationFeedbackAdapter feedbackAdapter;

    @Override
    protected CreationDetailPresenter buildPresenter() {
        return new CreationDetailPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_creation_detail);

        presenter.init();
    }

    @Override
    public Intent getIntent() {
        // TODO : dummy값이 아닌 실데이터를 return하도록 변경필요
        Intent intent = new Intent();
        intent.putExtra(IntentKey.INTENT_KEY_CREATION_ID, "testId");
        intent.putExtra(IntentKey.INTENT_KEY_USER_ID, "testUserId");
        return intent;
    }

    @Override
    public void initViews() {
        initCreationImagesView();
        initFeedbackInputView();
        initFeedbackListView();
    }

    private void initCreationImagesView() {
        imagePagerAdapter = new CreationImagePagerAdapter(getBaseContext());

        binding.creationImagePager.setAdapter(imagePagerAdapter);
    }

    private void initFeedbackInputView() {
        binding.submitComment.setOnClickListener(__ -> {
        });
    }

    private void initFeedbackListView() {
        feedbackAdapter = new CreationFeedbackAdapter();

        binding.commentList.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        binding.commentList.setAdapter(feedbackAdapter);
    }

    @Override
    public void setCreationData(Creation creation) {
        binding.setCreation(creation);

        setCreationImageData(creation.imageUrlList);
        setCreationFeedbackData(creation.feedbackList);
    }

    private void setCreationImageData(ArrayList<String> imageUrlList) {
        imagePagerAdapter.setImageList(imageUrlList);
    }

    private void setCreationFeedbackData(ArrayList<Feedback> feedbackList) {
        feedbackAdapter.addList(feedbackList);
    }

    @Override
    public void setGoneAllCommentView() {
        binding.viewNoComment.getRoot().setVisibility(View.GONE);
        binding.viewBeforeComment.getRoot().setVisibility(View.GONE);
        binding.commentList.setVisibility(View.GONE);
    }

    @Override
    public void setVisibleNoCommentView() {
        binding.viewNoComment.getRoot().setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibleBeforeCommentView() {
        binding.viewBeforeComment.getRoot().setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibleCommentView() {
        binding.commentList.setVisibility(View.VISIBLE);
    }

}
