package kr.mashup.feedget.ui.creation.detail;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import org.w3c.dom.Comment;

import java.io.File;
import java.util.ArrayList;

import kr.mashup.feedget.model.dummy.Creation;
import kr.mashup.feedget.model.dummy.Feedback;

public interface Contract {

    interface View {
        Intent getIntent();

        void initViews();

        void setCreationData(Creation creation);

        void setGoneAllCommentView();

        void setVisibleNoCommentView();

        void setVisibleBeforeCommentView();

        void setVisibleCommentView();

        Context getContext();

        void startActivityForResult(Intent intent, int requestCode);
    }

    interface Presenter {
        void init();

        String getUserId();

        String getCreationId();

        void startCameraIntent();

        void startGalleryIntent();

        File getImageFile();
    }

}
