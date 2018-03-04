package kr.mashup.feedget.ui.creation.detail;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gun0912.tedbottompicker.GridSpacingItemDecoration;
import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.adapter.GalleryAdapter;
import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.ActivityCreationDetailBinding;
import kr.mashup.feedget.model.dummy.Creation;
import kr.mashup.feedget.model.dummy.Feedback;
import kr.mashup.feedget.ui.base.BaseActivity;
import kr.mashup.feedget.ui.creation.detail.adapter.CreationFeedbackAdapter;
import kr.mashup.feedget.ui.creation.detail.adapter.CreationImagePagerAdapter;
import kr.mashup.feedget.util.IntentKey;

import static kr.mashup.feedget.ui.creation.detail.CreationDetailPresenter.REQ_CODE_CAMERA;
import static kr.mashup.feedget.ui.creation.detail.CreationDetailPresenter.REQ_CODE_GALLERY;

public class CreationDetailActivity extends BaseActivity<CreationDetailPresenter> implements Contract.View {

    private ActivityCreationDetailBinding binding;

    private CreationImagePagerAdapter imagePagerAdapter;
    private CreationFeedbackAdapter feedbackAdapter;
    private GalleryAdapter galleryAdapter;

    private final int SPAN_COUNT_GALLERY_LIST = 3;
    private final int MAX_COUNT_GALLERY_SELECT = 3;

    @Override
    protected CreationDetailPresenter buildPresenter() {
        return new CreationDetailPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_creation_detail);

        presenter.init();

        requestPermission();
    }

    //TODO : 퍼미션 요청용 테스트코드 반드시 삭제 요망
    private void requestPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1
        );
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
        initGalleryListView();
    }

    private void initCreationImagesView() {
        imagePagerAdapter = new CreationImagePagerAdapter(getBaseContext());

        binding.creationImagePager.setAdapter(imagePagerAdapter);
    }

    private void initFeedbackInputView() {
        binding.submitComment.setOnClickListener(__ -> {
        });
        binding.feedbackImage.setOnClickListener(__ -> {
            binding.galleryImageList.setVisibility(View.VISIBLE);
        });
    }

    private void initFeedbackListView() {
        feedbackAdapter = new CreationFeedbackAdapter();

        binding.commentList.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        binding.commentList.setAdapter(feedbackAdapter);
    }

    private void initGalleryListView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBaseContext(), SPAN_COUNT_GALLERY_LIST);
        binding.galleryImageList.setLayoutManager(gridLayoutManager);
        binding.galleryImageList.addItemDecoration(
                new GridSpacingItemDecoration(
                        gridLayoutManager.getSpanCount(),
                        16,
                        false
                )
        );

        TedBottomPicker.Builder builder = new TedBottomPicker.Builder(getBaseContext())
                .setOnMultiImageSelectedListener(
                        uriList -> {

                        }
                ).setSelectMaxCount(MAX_COUNT_GALLERY_SELECT);

        galleryAdapter = new GalleryAdapter(
                getBaseContext(),
                builder
        );
        binding.galleryImageList.setAdapter(galleryAdapter);
        galleryAdapter.setOnItemClickListener((view, position) -> {

            GalleryAdapter.PickerTile pickerTile = galleryAdapter.getItem(position);

            switch (pickerTile.getTileType()) {
                case GalleryAdapter.PickerTile.CAMERA:
                    startCameraIntent();
                    break;
                case GalleryAdapter.PickerTile.GALLERY:
                    startGalleryIntent();
                    break;
                case GalleryAdapter.PickerTile.IMAGE:
                    complete(pickerTile.getImageUri());
                    break;
                default:
                    throw new RuntimeException("알 수 없는 type : " + pickerTile.getTileType());
            }

        });
    }

    private void startGalleryIntent() {
        Intent galleryIntent;
        Uri uri;
        galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");

        startActivityForResult(galleryIntent, REQ_CODE_GALLERY);
    }

    private void startCameraIntent() {
        Intent cameraInent;
        File mediaFile;

        cameraInent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mediaFile = presenter.getImageFile();

        Uri photoURI = FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", mediaFile);

        List<ResolveInfo> resolvedIntentActivities = getContext().getPackageManager().queryIntentActivities(cameraInent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
            String packageName = resolvedIntentInfo.activityInfo.packageName;
            getContext().grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }

        cameraInent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        startActivityForResult(cameraInent, REQ_CODE_CAMERA);

    }

    private void complete(final Uri uri) {
        /*
        if (selectedUriList.contains(uri)) {
            removeImage(uri);
        } else {
            addUri(uri);
        }*/
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

    @Override
    public Context getContext() {
        return getBaseContext();
    }

}
