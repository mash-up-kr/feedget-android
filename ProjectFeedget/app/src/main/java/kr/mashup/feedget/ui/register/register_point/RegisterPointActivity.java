package kr.mashup.feedget.ui.register.register_point;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.ActivityRegisterPointBinding;
import kr.mashup.feedget.ui.base.BaseActivity;
import kr.mashup.feedget.ui.register.register_content.RegisterActivity;

/**
 * Created by eastroots92 on 2017-12-23.
 */

public class RegisterPointActivity extends BaseActivity<Contract.Presenter> implements Contract.View {

    private ActivityRegisterPointBinding binding;

    RegisterActivity registerActivity = (RegisterActivity) RegisterActivity.registerActivity;


    @Override
    protected Contract.Presenter buildPresenter() {
        return new RegisterPointPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_point);

        init();

    }

    private void init(){
        binding.textViewRegisterBackButton.setOnClickListener(__ ->{
            finish();
        });

        binding.textViewRegisterSubmitButton.setOnClickListener(__ ->{

            finishAlert();


        });
    }

    private void finishAlert(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("게시글 등록 완료!");

        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                registerActivity.finish();
                finish();
            }

        });

        alert.setMessage("피드백이 입력되면 수정, 삭제가 불가능하니 유의하세요.");

        alert.show();

    }
}
