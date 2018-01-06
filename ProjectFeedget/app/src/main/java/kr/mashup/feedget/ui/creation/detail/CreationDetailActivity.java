package kr.mashup.feedget.ui.creation.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.ActivityCreationDetailBinding;
import kr.mashup.feedget.model.dummy.Creation;
import kr.mashup.feedget.ui.base.BaseActivity;
import kr.mashup.feedget.util.IntentKey;

public class CreationDetailActivity extends BaseActivity<CreationDetailPresenter> implements Contract.View {

    private ActivityCreationDetailBinding binding;

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
        return intent;
    }

    @Override
    public void setCreation(Creation creation) {
        binding.setCreation(creation);
    }
}
