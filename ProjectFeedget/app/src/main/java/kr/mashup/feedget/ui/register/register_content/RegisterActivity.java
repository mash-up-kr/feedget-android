package kr.mashup.feedget.ui.register.register_content;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.ActivityRegisterBinding;
import kr.mashup.feedget.ui.base.BaseActivity;
import kr.mashup.feedget.ui.register.CreationData;
import kr.mashup.feedget.ui.register.register_point.RegisterPointActivity;


public class RegisterActivity extends BaseActivity<Contract.Presenter> implements Contract.View{

    private ActivityRegisterBinding binding;
    private Activity registerActivity;

    private CreationData creationData = new CreationData();

    public static final int REGISTER_REQUEST = 10;

    @Override
    protected Contract.Presenter buildPresenter() { return new RegisterPresenter(this); }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        presenter.init();
    }

    @Override
    public void initViews() {
        registerActivity = RegisterActivity.this;

        ToolBarManager();
    }

    /* ToolBar Manager Start */
    private void ToolBarManager() {
        initToolBar();
        modalCategoryManager();
        nextButtonManager();
    }

    private void initToolBar() {

        binding.modal.modalCategory.setVisibility(View.GONE);

        binding.toolbar.textViewBackButton.setOnClickListener(__ ->{
            finish();
        });
    }

    private void modalCategoryManager() {
        categoryToggle();
        categorySelecter();
    }

    private void nextButtonManager() {

        binding.toolbar.textViewNextButton.setOnClickListener(__->{
            boolean isDataReady;

            setCreationData();
            isDataReady = ValidationData();
            openPointActivity(isDataReady);
        });

    }

    private void categoryToggle() {
        binding.toolbar.textViewCategoryButton.setOnClickListener(view->{
            if (binding.modal.modalCategory.getVisibility() == view.GONE){
                categoryToggleOpenAction();
            }else if(binding.modal.modalCategory.getVisibility() == view.VISIBLE){
                categoryToggleCloseAction();
            }
        });

    }

    private void categorySelecter() {
        binding.modal.textViewCategoryDesign.setOnClickListener(__->{
            resetCategoryTitle();
            setCategoryTitle("디자인");
            binding.modal.textViewCategoryDesign.setTypeface(Typeface.DEFAULT_BOLD);
            categoryToggleCloseAction();

        });

        binding.modal.textViewCategoryCrafts.setOnClickListener(__->{
            resetCategoryTitle();
            setCategoryTitle("공예");
            binding.modal.textViewCategoryCrafts.setTypeface(Typeface.DEFAULT_BOLD);
            categoryToggleCloseAction();
        });

        binding.modal.textViewCategoryPainting.setOnClickListener(__->{
            resetCategoryTitle();
            setCategoryTitle("회화");
            binding.modal.textViewCategoryPainting.setTypeface(Typeface.DEFAULT_BOLD);
            categoryToggleCloseAction();
        });

        binding.modal.textViewCategoryWriting.setOnClickListener(__->{
            resetCategoryTitle();
            setCategoryTitle("글");
            binding.modal.textViewCategoryWriting.setTypeface(Typeface.DEFAULT_BOLD);
            categoryToggleCloseAction();
        });

        binding.modal.textViewCategoryEtc.setOnClickListener(__->{
            resetCategoryTitle();
            setCategoryTitle("기타");
            binding.modal.textViewCategoryEtc.setTypeface(Typeface.DEFAULT_BOLD);
            categoryToggleCloseAction();
        });

    }

    private void categoryToggleOpenAction() {
        binding.modal.modalCategory.setVisibility(View.VISIBLE);
        binding.toolbar.textViewCategoryArrow.setImageResource(R.drawable.icon_arrowup);
    }

    private void categoryToggleCloseAction() {
        binding.modal.modalCategory.setVisibility(View.GONE);
        binding.toolbar.textViewCategoryArrow.setImageResource(R.drawable.icon_arrowdown);
    }

    private void resetCategoryTitle(){
        binding.modal.textViewCategoryWriting.setTypeface(Typeface.DEFAULT);
        binding.modal.textViewCategoryPainting.setTypeface(Typeface.DEFAULT);
        binding.modal.textViewCategoryCrafts.setTypeface(Typeface.DEFAULT);
        binding.modal.textViewCategoryDesign.setTypeface(Typeface.DEFAULT);
        binding.modal.textViewCategoryEtc.setTypeface(Typeface.DEFAULT);
    }

    private void setCategoryTitle(String content){
        binding.toolbar.textViewCategory.setText(content);
    }

    private void setCreationData() {
        String title = binding.editTextTitle.getText().toString();
        String content = binding.editTextContent.getText().toString();
        String category = binding.toolbar.textViewCategory.getText().toString();

        creationData.setTitle(title);
        creationData.setDescription(content);
        creationData.setCategory(category);
    }

    private boolean ValidationData() {
        if( creationData.isCategory() && creationData.isCompleted()) {
            return true;
        }else{
            return false;
        }
    }

    private void openPointActivity(boolean isDataReady) {
        if(isDataReady){
            Intent intent = new Intent(getApplicationContext(), RegisterPointActivity.class);
            intent.putExtra("data",  creationData);
            startActivityForResult(intent, REGISTER_REQUEST);
        }else{
            Toast.makeText(registerActivity, "내용을 모두 기입해 주시기 바랍니다.", Toast.LENGTH_SHORT).show();
        }

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode ==  REGISTER_REQUEST){
            if(resultCode == RESULT_OK){
                creationData = intent.getParcelableExtra("data");

                // 값을 여기서 서버에 보낸다. 뿜뿜
                finish();
            }else{
                Toast.makeText(this, "Error : 다시 시도해 주세요", Toast.LENGTH_SHORT).show();

            }

        }
    }

}
