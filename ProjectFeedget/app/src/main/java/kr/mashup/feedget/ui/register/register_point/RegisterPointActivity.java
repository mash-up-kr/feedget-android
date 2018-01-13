package kr.mashup.feedget.ui.register.register_point;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.ActivityRegisterPointBinding;
import kr.mashup.feedget.ui.base.BaseActivity;
import kr.mashup.feedget.ui.base.View;
import kr.mashup.feedget.ui.register.CreationData;

import static kr.mashup.feedget.ui.register.register_content.RegisterActivity.REGISTER_REQUEST;

/**
 * Created by eastroots92 on 2017-12-23.
 */

public class RegisterPointActivity extends BaseActivity<Contract.Presenter> implements Contract.View {

    //  단순 포인트 확인 용
    private int userPoint = 1800;

    private ActivityRegisterPointBinding binding;

    private CreationData data;

    private TextView tmp_point;

    @Override
    protected Contract.Presenter buildPresenter() {
        return new RegisterPointPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_point);

        init();
        pointSelecter();

    }

    private void init() {
        binding.textViewRegisterBackButton.setOnClickListener(__ -> {
            finish();
        });

        binding.textViewRegisterSubmitButton.setOnClickListener(__ -> {


            data = (CreationData) getIntent().getSerializableExtra("data");

            String point = binding.EditTextSetPointBar.getText().toString();
            if (point != "") {
                float rewardPoint = Float.parseFloat(point);
                boolean anonymity = binding.switchIsHideNickname.isChecked();

                data.setRewardPoint(rewardPoint);
                data.setAnonymity(anonymity);
                if (data.isSubmit()) {
                    Intent intent = new Intent();
                    intent.putExtra("data", data);
                    setResult(RESULT_OK, intent);
                    finishAlert();
                }
            } else {
                Toast.makeText(this, "포인트 입력해야해!!", Toast.LENGTH_SHORT).show();
            }


        });

        binding.textViewMyPoint.setText(Integer.toString(userPoint));
    }

    private void finishAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("게시글 등록 완료!");

        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                finish();
            }

        });

        alert.setMessage("피드백이 입력되면 수정, 삭제가 불가능하니 유의하세요.");

        alert.show();

    }


    private void pointSelecter() {

        for (int i = 0; i < binding.linearLayoutPointBlock.getChildCount(); i++) {
            ViewGroup childContainer = (ViewGroup) binding.linearLayoutPointBlock.getChildAt(i);
            for(int j=0;j<childContainer.getChildCount();j++){
                android.view.View childView = childContainer.getChildAt(j);
                childView.setOnClickListener(view ->{
                    setPointResult((TextView) view);
                });
            }
        }


        binding.checkBoxPointAllIn.setOnClickListener(__ -> {

            if (binding.checkBoxPointAllIn.isChecked() == true) {
                setPointBar(userPoint);

            } else {
                setPointBar(0);
            }

        });

    }


    private void setPointBar(int point) {

        if (userPoint >= point) {
            String setPoint = Integer.toString(point);
            binding.EditTextSetPointBar.setText(setPoint);
        } else {
            Toast.makeText(this, "보유 포인트 이하만 입력할 수 있습니다", Toast.LENGTH_SHORT).show();
            binding.EditTextSetPointBar.setText("0");
        }
    }


    private void setPointResult(TextView view) {
        if (tmp_point != null) {
            tmp_point.setBackground(ContextCompat.getDrawable(this, R.drawable.point_button_stroke));
        }
        view.setBackground(ContextCompat.getDrawable(this, R.drawable.point_button_stroke_selected));
        String point = view.getText().toString();
        setPointBar(Integer.parseInt(point));
        tmp_point = view;
    }

}
