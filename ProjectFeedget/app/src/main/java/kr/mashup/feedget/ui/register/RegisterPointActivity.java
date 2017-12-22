package kr.mashup.feedget.ui.register;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import kr.mashup.feedget.R;

import kr.mashup.feedget.databinding.ActivityRegisterPointBinding;

/**
 * Created by eastroots92 on 2017-12-23.
 */

public class RegisterPointActivity extends AppCompatActivity{

    private ActivityRegisterPointBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_point);

    }
}
