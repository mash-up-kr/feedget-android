package kr.mashup.feedget.ui.register.register_point;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.annotation.Nullable;

import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.ActivityRegisterPointBinding;
import kr.mashup.feedget.ui.base.BaseActivity;

/**
 * Created by eastroots92 on 2017-12-23.
 */

public class RegisterPointActivity extends BaseActivity<Contract.Presenter> implements Contract.View {

    private ActivityRegisterPointBinding binding;
    private Intent intent;
    private TextView tmpPoint;

//    TODO: 임시 데이터, 추후에 유저 데이터를 받아오면 임시 데이터를 지운다
    private int userPoint = 1200;

    @Override
    protected Contract.Presenter buildPresenter() { return new RegisterPointPresenter(this);}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_point);

        presenter.init();

    }

    @Override
    public void initViews() {
        toolBarManager();
        getUserInfo();
        pointLayoutManager();
    }

    private void getUserInfo() {

    }


    private void pointLayoutManager() {
        pointBlockSelecter();
        editTextPointChecker();

    }

    private void editTextPointChecker() {
        binding.EditTextSetPointBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkingEditTextPoint(s);
            }


            @Override
            public void afterTextChanged(Editable s) {

            }

            private void checkingEditTextPoint(CharSequence s) {
//              TODO: 현재 값이 0 이하로 지우면 APP이 죽습니다
                if (s.length() != 0) {
                    int point = Integer.parseInt(s.toString());
                    if (point > userPoint) {
                        Toast.makeText(RegisterPointActivity.this, "보유하신 포인트를 초과하였습니다.", Toast.LENGTH_SHORT).show();
                        binding.EditTextSetPointBar.setText(Integer.toString(userPoint));
                    }
                } else {
                    binding.EditTextSetPointBar.setText("0");
                }
            }
        });
    }

    private void pointBlockSelecter() {
        for (int i = 0; i < binding.linearLayoutPointBlock.getChildCount(); i++) {
            ViewGroup childContainer = (ViewGroup) binding.linearLayoutPointBlock.getChildAt(i);
            for (int j = 0; j < childContainer.getChildCount(); j++) {
                android.view.View childView = childContainer.getChildAt(j);
                childView.setOnClickListener(view -> {
                    setPointResult((TextView) view);
                });
            }
        }
    }

    private void setPointResult(TextView view) {
        if (tmpPoint != null) {
            tmpPoint.setBackground(ContextCompat.getDrawable(this, R.drawable.point_button_stroke));
        }
        view.setBackground(ContextCompat.getDrawable(this, R.drawable.point_button_stroke_selected));
        String point = view.getText().toString();
        setPointBar(Integer.parseInt(point));
        tmpPoint = view;
    }

    private void setPointBar(int point) {
        if(userPoint >= point){
            String setPoint = Integer.toString(point);
            binding.EditTextSetPointBar.setText(setPoint);
        }else{
            Toast.makeText(this, "보유하신 포인트 이하만 입력할 수 있습니다.", Toast.LENGTH_SHORT).show();
            binding.EditTextSetPointBar.setText("0");
        }
    }

    private void toolBarManager() {
        initToolBar();
        submitButtonManager();
    }

    private void submitButtonManager() {
        binding.textViewRegisterSubmitButton.setOnClickListener(__ -> {
            boolean isDataReady;
            isDataReady = validationData();
            setCreationData(isDataReady);
        });
    }

    private boolean validationData() {
        String point = binding.EditTextSetPointBar.getText().toString();

        if(point != "" ){
            return true;
        }else {
            return false;
        }

    }

    private void setCreationData(Boolean isDataReady) {
        if(isDataReady){
            int rewardPoint = Integer.parseInt(binding.EditTextSetPointBar.getText().toString());
            boolean anonymity = binding.switchIsHideNickname.isChecked();

            intent = new Intent();
            intent.putExtra("rewardPoint",rewardPoint);
            intent.putExtra("anonymity",anonymity);
            setResult(Activity.RESULT_OK, intent);
            registerPointActivityFinishAlert();

        }else{
            Toast.makeText(this, "포인트를 입력해 주세요", Toast.LENGTH_SHORT).show();
        }

    }

    private void registerPointActivityFinishAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("게시글 등록 완료");

        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alert.setMessage("피드백이 입력되면 수정, 삭제가 불가능하니 유의하세요");
        alert.show();
    }

    private void initToolBar() {

        binding.textViewRegisterBackButton.setOnClickListener(__ -> {
            finish();
        });



    }
}
