package kr.mashup.feedget.ui.register.register_content;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import gun0912.tedbottompicker.TedBottomPicker;
import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.ActivityRegisterBinding;
import kr.mashup.feedget.model.dummy.Creation;
import kr.mashup.feedget.ui.base.BaseActivity;
import kr.mashup.feedget.ui.register.CreationData;
import kr.mashup.feedget.ui.register.ImageData;
import kr.mashup.feedget.ui.register.register_point.RegisterPointActivity;
import kr.mashup.feedget.util.DummyApi;


public class RegisterActivity extends BaseActivity<Contract.Presenter> implements Contract.View{

    private ActivityRegisterBinding binding;
    private Activity registerActivity;
    private ArrayList<Uri> selectedUriList;

    private CreationData creationData = new CreationData();
    private ImageData imageData = new ImageData();

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
        ImageDataManager();
        checkingKeyboard();
    }

    @Override
    public void checkingEdit() {
        isCreation();
    }

    @Override
    public void setCreationData(Creation creation) {
        setEditTextTitle(creation.title);
        setEditTextContent(creation.description);
        changeCategory(creation.category);
    }

    private void setEditTextContent(String description) {
        binding.editTextContent.setText(description);
    }

    private void setEditTextTitle(String title) {
        binding.editTextTitle.setText(title);
    }

    private void isCreation(){
        String creationId = getIntent().getStringExtra("creationId");
        if(creationId != null){
            presenter.requestCreationDetail(creationId);
        }
    }

    private void checkingKeyboard() {
        InputMethodManager controllManager = (InputMethodManager)getSystemService(Service.INPUT_METHOD_SERVICE);
        SoftKeyboard softKeyboard = new SoftKeyboard(binding.contentLayout, controllManager);

        softKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged(){
            @Override
            public void onSoftKeyboardHide(){
                new Handler(Looper.getMainLooper()).post(new Runnable()
                {
                    @Override
                    public void run(){
                        binding.imageListFrame.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onSoftKeyboardShow(){
                new Handler(Looper.getMainLooper()).post(new Runnable(){
                    @Override
                    public void run(){
                        binding.imageListFrame.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    private void ToolBarManager() {
        initToolBar();
        modalCategoryManager();
        nextButtonManager();
    }

    private void ImageDataManager() {
        imageButtonTrigger();
    }

    private void imageButtonTrigger() {
        binding.imageViewImageButton.setOnClickListener(__->{
            setPermission();
        });
    }

    private void setPermission() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                initImagePicker();
            }
            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(RegisterActivity.this, "권한이 없습니다.", Toast.LENGTH_SHORT).show();
            }
        };

        new TedPermission(RegisterActivity.this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("권한 설정 동의를 안하신다면, 나중에 이곳에서 설정해 주세요. [설정] > [권한]")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private void initImagePicker(){
        TedBottomPicker bottomSheetDialogFragment = new TedBottomPicker.Builder(RegisterActivity.this)
                .setOnMultiImageSelectedListener(new TedBottomPicker.OnMultiImageSelectedListener() {

                    @Override
                    public void onImagesSelected(ArrayList<Uri> uriList) {
                        selectedUriList = uriList;
                        showUriList(uriList);
                    }
                })
                .setPeekHeight(1600)
                .showTitle(false)
                .setCompleteButtonText("첨부")
                .setEmptySelectionText("No Select")
                .setSelectedUriList(selectedUriList)
                .create();
        bottomSheetDialogFragment.show(getSupportFragmentManager());
    };

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
            changeCategory("디자인");
        });

        binding.modal.textViewCategoryCrafts.setOnClickListener(__->{
            changeCategory("공예");
        });

        binding.modal.textViewCategoryPainting.setOnClickListener(__->{
            changeCategory("회화");
        });

        binding.modal.textViewCategoryWriting.setOnClickListener(__->{
            changeCategory("글");
        });

        binding.modal.textViewCategoryEtc.setOnClickListener(__->{
            changeCategory("기타");
        });

    }

    private void changeCategory(String category) {
        resetCategoryTitle();
        if(category == "디자인"){
            setCategoryTitle("디자인");
            binding.modal.textViewCategoryDesign.setTypeface(Typeface.DEFAULT_BOLD);
        }
        else if(category == "공예"){
            setCategoryTitle("공예");
            binding.modal.textViewCategoryCrafts.setTypeface(Typeface.DEFAULT_BOLD);
        }
        else if(category == "회화"){
            setCategoryTitle("회화");
            binding.modal.textViewCategoryPainting.setTypeface(Typeface.DEFAULT_BOLD);
        }
        else if(category == "글"){
            setCategoryTitle("글");
            binding.modal.textViewCategoryWriting.setTypeface(Typeface.DEFAULT_BOLD);
        }
        else if(category == "기타"){
            setCategoryTitle("기타");
            binding.modal.textViewCategoryDesign.setTypeface(Typeface.DEFAULT_BOLD);
        }
        categoryToggleCloseAction();
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
            startActivityForResult(intent, REGISTER_REQUEST);
        }else{
            Toast.makeText(registerActivity, "내용을 모두 기입해 주시기 바랍니다.", Toast.LENGTH_SHORT).show();
        }

    }

    private void showUriList(ArrayList<Uri> uriList) {
        binding.selectedPhotosContainer.removeAllViews();

        binding.ivImage.setVisibility(View.GONE);

        binding.selectedPhotosContainer.setVisibility(View.VISIBLE);

        int widthPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,150,getResources().getDisplayMetrics());
        int heightPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150,getResources().getDisplayMetrics());

        for (Uri uri : uriList) {
            imageData.setFiles(uri);
            View imageHolder = LayoutInflater.from(this).inflate(R.layout.image_item, null);
            ImageView thumbnail = (ImageView) imageHolder.findViewById(R.id.media_image);

            Glide.with(this)
                    .load(uri.toString())
                    .fitCenter()
                    .into(thumbnail);

            binding.selectedPhotosContainer.addView(imageHolder);

            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(widthPixel, heightPixel));
        }

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode ==  REGISTER_REQUEST){
            if(resultCode == RESULT_OK){
                creationData.setRewardPoint(intent.getIntExtra("rewardPoint",0));
                creationData.setAnonymity(intent.getBooleanExtra("anonymity",false));

                presenter.requestManager(creationData, imageData);

                finish();
            }else{
                Toast.makeText(this, "Error : 다시 시도해 주세요", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
