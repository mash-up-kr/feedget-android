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
import android.os.Parcelable;
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

import java.io.Serializable;
import java.util.ArrayList;

import gun0912.tedbottompicker.TedBottomPicker;
import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.ActivityRegisterBinding;
import kr.mashup.feedget.ui.base.BaseActivity;
import kr.mashup.feedget.ui.register.CreationData;
import kr.mashup.feedget.ui.register.register_point.RegisterPointActivity;


public class RegisterActivity extends BaseActivity<Contract.Presenter> implements Contract.View{

    SoftKeyboard softKeyboard;
    private ActivityRegisterBinding binding;
    private Activity registerActivity;
    public static final int REGISTER_REQUEST = 10;

    ArrayList<Uri> selectedUriList;

    // 만약 수정하기 일 경우 PostId에 값을 넣어준다.
    private String postId;
    private CreationData data = new CreationData();

    @Override
    protected Contract.Presenter buildPresenter() {
        return new RegisterPresenter(this);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        registerActivity = RegisterActivity.this;
        checkingKeyboard();
        init();
    }

    private void init(){


        // 새 게시물 작성인지 기존 글 불러오기 인지 구분 짓기
        if( postId != null ){
            // 서버에서 게시물을 찾아와 data에 넣어주는 것
            setCreationData("제목","내용","카테고리");
        }


        //시작시 카테고리 선택 창 안보이게 함
        binding.modal.modalCategory.setVisibility(View.GONE);

        binding.toolbar.textViewBackButton.setOnClickListener(__ ->{
            finish();
        });

        binding.toolbar.textViewCategoryButton.setOnClickListener(view->{
            if (binding.modal.modalCategory.getVisibility() == view.GONE){

                binding.modal.modalCategory.setVisibility(view.VISIBLE);
                binding.toolbar.textViewCategoryArrow.setImageResource(R.drawable.icon_arrowup);

            }else if(binding.modal.modalCategory.getVisibility() == view.VISIBLE){

                closeModalCategory();

            }
        });

        binding.modal.textViewCategoryDesign.setOnClickListener(__->{
            setDefaultText();
            setCategoryTitle("디자인");
            binding.modal.textViewCategoryDesign.setTypeface(Typeface.DEFAULT_BOLD);
            closeModalCategory();

        });

        binding.modal.textViewCategoryCrafts.setOnClickListener(__->{
            setDefaultText();
            setCategoryTitle("공예");
            binding.modal.textViewCategoryCrafts.setTypeface(Typeface.DEFAULT_BOLD);
            closeModalCategory();
        });

        binding.modal.textViewCategoryPainting.setOnClickListener(__->{
            setDefaultText();
            setCategoryTitle("회화");
            binding.modal.textViewCategoryPainting.setTypeface(Typeface.DEFAULT_BOLD);
            closeModalCategory();
        });

        binding.modal.textViewCategoryWriting.setOnClickListener(__->{
            setDefaultText();
            setCategoryTitle("글");
            binding.modal.textViewCategoryWriting.setTypeface(Typeface.DEFAULT_BOLD);
            closeModalCategory();
        });

        binding.modal.textViewCategoryEtc.setOnClickListener(__->{
            setDefaultText();
            setCategoryTitle("기타");
            binding.modal.textViewCategoryEtc.setTypeface(Typeface.DEFAULT_BOLD);
            closeModalCategory();
        });

        binding.toolbar.textViewNextButton.setOnClickListener(__->{

            inputCreationData();
            if( data.isCategory() && data.isCompleted()) {
                Intent intent = new Intent(getApplicationContext(), RegisterPointActivity.class);
                intent.putExtra("data",  data);
                startActivityForResult(intent, REGISTER_REQUEST);
            }else{
                Toast.makeText(registerActivity, "아직 부족해!!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.imageViewImageButton.setOnClickListener(__->{
            PermissionListener permissionlistener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {
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
        });

    }

    private void showUriList(ArrayList<Uri> uriList){

        binding.selectedPhotosContainer.removeAllViews();

        binding.ivImage.setVisibility(View.GONE);

        binding.selectedPhotosContainer.setVisibility(View.VISIBLE);

        int widthPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,150,getResources().getDisplayMetrics());
        int heightPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150,getResources().getDisplayMetrics());

        for (Uri uri : uriList) {
            Log.e("에러체크","이거 돌아가나?");
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



    private void checkingKeyboard(){
        InputMethodManager controllManager = (InputMethodManager)getSystemService(Service.INPUT_METHOD_SERVICE);
        softKeyboard = new SoftKeyboard(binding.contentLayout, controllManager);

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
    };

    private void setDefaultText(){
        binding.modal.textViewCategoryWriting.setTypeface(Typeface.DEFAULT);
        binding.modal.textViewCategoryPainting.setTypeface(Typeface.DEFAULT);
        binding.modal.textViewCategoryCrafts.setTypeface(Typeface.DEFAULT);
        binding.modal.textViewCategoryDesign.setTypeface(Typeface.DEFAULT);
        binding.modal.textViewCategoryEtc.setTypeface(Typeface.DEFAULT);

    }

    private void setCategoryTitle(String content){
        binding.toolbar.textViewCategory.setText(content);
    }



    public void onDestroy()
    {
        super.onDestroy();
        softKeyboard.unRegisterSoftKeyboardCallback();
    }

    private void inputCreationData(){
        String title = binding.editTextTitle.getText().toString();
        String content = binding.editTextContent.getText().toString();
        String category = binding.toolbar.textViewCategory.getText().toString();


        data.setTitle(title);
        data.setDescription(content);
        data.setCategory(category);
    }

    private void setCreationData(String title,String content, String category){

        binding.editTextTitle.setText(title);
        binding.editTextContent.setText(content);
        binding.toolbar.textViewCategory.setText(category);

    }

    private void closeModalCategory(){
        binding.modal.modalCategory.setVisibility(View.GONE);
        binding.toolbar.textViewCategoryArrow.setImageResource(R.drawable.icon_arrowdown);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode ==  REGISTER_REQUEST){
            if(resultCode == RESULT_OK){
                data = intent.getParcelableExtra("data");

                // 값을 여기서 서버에 보낸다. 뿜뿜
                finish();
            }else{
                Toast.makeText(this, "Error : 다시 시도해 주세요", Toast.LENGTH_SHORT).show();

            }

        }
    }

}
