package kr.mashup.feedget.ui.register;

import android.Manifest;
import android.app.Service;
import android.databinding.DataBindingUtil;
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
import kr.mashup.feedget.ui.base.BaseActivity;


public class RegisterActivity extends BaseActivity<Contract.Presenter> implements Contract.View{

    SoftKeyboard softKeyboard;
    private ActivityRegisterBinding binding;

    ArrayList<Uri> selectedUriList;


    @Override
    protected Contract.Presenter buildPresenter() {
        return new RegisterPresenter(this);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        checkingKeyboard();
        init();
    }

    private void init(){
        //시작시 카테고리 선택 창 안보이게 함
        binding.modal.modalCategory.setVisibility(View.GONE);

        binding.toolbar.tvBackBtn.setOnClickListener(__ ->{
            finish();
        });

        binding.toolbar.tvCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.modal.modalCategory.getVisibility() == View.GONE){

                    binding.modal.modalCategory.setVisibility(View.VISIBLE);
                    binding.toolbar.tvCategoryArrow.setImageResource(R.drawable.icon_arrowup);

                }else if(binding.modal.modalCategory.getVisibility() == View.VISIBLE){

                    binding.modal.modalCategory.setVisibility(View.GONE);
                    binding.toolbar.tvCategoryArrow.setImageResource(R.drawable.icon_arrowdown);

                }
            }
        });

        binding.ivImageBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

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
            }
        });
    }

    private void showUriList(ArrayList<Uri> uriList){

        binding.selectedPhotosContainer.removeAllViews();

        binding.ivImage.setVisibility(View.GONE);

        binding.selectedPhotosContainer.setVisibility(View.VISIBLE);

        int wdpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,150,getResources().getDisplayMetrics());
        int htpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150,getResources().getDisplayMetrics());

        for (Uri uri : uriList) {
            Log.e("에러체크","이거 돌아가나?");
            View imageHolder = LayoutInflater.from(this).inflate(R.layout.image_item, null);
            ImageView thumbnail = (ImageView) imageHolder.findViewById(R.id.media_image);

            Glide.with(this)
                    .load(uri.toString())
                    .fitCenter()
                    .into(thumbnail);

            binding.selectedPhotosContainer.addView(imageHolder);

            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(wdpx,htpx));
        }

    }



    private void checkingKeyboard(){
        InputMethodManager controllManager = (InputMethodManager)getSystemService(Service.INPUT_METHOD_SERVICE);
        softKeyboard = new SoftKeyboard(binding.inputForm, controllManager);

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



    public void onDestroy()
    {
        super.onDestroy();
        softKeyboard.unRegisterSoftKeyboardCallback();
    }

}
