package com.kekehu.architecture.mvc;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kekehu.architecture.LoginModel;
import com.kekehu.architecture.R;
import com.kekehu.architecture.base.BaseActivity;

public class LoginMvcActivity extends BaseActivity {
    EditText etName, etPassword;
    Button btnLogin;
    LoginModel mLoginModel;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mvc);
        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvResult = findViewById(R.id.tv_result);
        mLoginModel = new LoginModel();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            toastShort("请输入账号");
            return;
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            toastShort("请输入密码");
            return;
        }

        mLoginModel.login(etName.getText().toString(), etPassword.getText().toString(), new LoginModel.Callback() {
            @Override
            public void onSuccess(String token) {
                tvResult.setText(token + "登录成功");
            }

            @Override
            public void fail(int code, String message) {
                tvResult.setText("登录失败：" + message);
            }
        });
    }
}