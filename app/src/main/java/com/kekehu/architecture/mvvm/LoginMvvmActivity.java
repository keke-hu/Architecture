package com.kekehu.architecture.mvvm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kekehu.architecture.R;
import com.kekehu.architecture.base.BaseActivity;

public class LoginMvvmActivity extends BaseActivity {


    EditText etName, etPassword;
    Button btnLogin;
    TextView tvResult;

    LoginViewModel loginViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mvvm);
        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvResult = findViewById(R.id.tv_result);
        initViewModel();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.login(etName.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    private void initViewModel() {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.init();
        loginViewModel.getToastString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                toastShort(s);
            }
        });

        loginViewModel.getToken().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvResult.setText(s);
            }
        });

        loginViewModel.getFailBeanMutableLiveData().observe(this, new Observer<LoginViewModel.FailBean>() {
            @Override
            public void onChanged(LoginViewModel.FailBean failBean) {
                tvResult.setText(failBean.getMessage());
            }
        });
    }
}