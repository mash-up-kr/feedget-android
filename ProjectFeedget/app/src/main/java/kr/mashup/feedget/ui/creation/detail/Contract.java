package kr.mashup.feedget.ui.creation.detail;

import android.content.Intent;

import org.w3c.dom.Comment;

import java.util.ArrayList;

import kr.mashup.feedget.model.dummy.Creation;
import kr.mashup.feedget.model.dummy.Feedback;

public interface Contract {

    interface View {
        Intent getIntent();

        void initViews();

        void setCreation(Creation creation);

        void setGoneAllCommentView();

        void setVisibleNoCommentView();

        void setVisibleBeforeCommentView();

        void setVisibleCommentView();
    }

    interface Presenter {
        void init();
        String getUserId();
        String getCreationId();
    }

}
