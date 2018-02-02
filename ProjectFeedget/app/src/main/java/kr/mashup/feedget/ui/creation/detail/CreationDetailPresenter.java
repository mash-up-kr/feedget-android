package kr.mashup.feedget.ui.creation.detail;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import gun0912.tedbottompicker.TedBottomPicker;
import kr.mashup.feedget.model.dummy.Feedback;
import kr.mashup.feedget.util.DummyApi;
import kr.mashup.feedget.util.IntentKey;

public class CreationDetailPresenter implements Contract.Presenter {

    private Contract.View view;
    protected static final int REQ_CODE_CAMERA = 1;
    protected static final int REQ_CODE_GALLERY = 2;

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
        } else if (isMyFeedbackExist(feedbackList) == false) {
            view.setVisibleBeforeCommentView();
        } else {
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

    @Override
    public void startCameraIntent() {
        Intent cameraInent;
        File mediaFile;

        cameraInent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mediaFile = getImageFile();

        Uri photoURI = FileProvider.getUriForFile(view.getContext(), view.getContext().getApplicationContext().getPackageName() + ".provider", mediaFile);

        List<ResolveInfo> resolvedIntentActivities = view.getContext().getPackageManager().queryIntentActivities(cameraInent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
            String packageName = resolvedIntentInfo.activityInfo.packageName;
            view.getContext().grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }

        cameraInent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        view.startActivityForResult(cameraInent, REQ_CODE_CAMERA);

    }

    @Override
    public void startGalleryIntent() {
        Intent galleryIntent;
        Uri uri;
        galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");


        view.startActivityForResult(galleryIntent, REQ_CODE_GALLERY);

    }

    @Override
    public File getImageFile() {
        File imageFile = null;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            if (!storageDir.exists())
                storageDir.mkdirs();

            imageFile = File.createTempFile(
                    imageFileName,
                    ".jpg",
                    storageDir
            );

//            cameraImageUri = Uri.fromFile(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return imageFile;
    }

}
