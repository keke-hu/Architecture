package com.kekehu.architecture.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kekehu.architecture.R;
import com.kekehu.architecture.base.BaseActivity;

public class LoginMvpActivity extends BaseActivity implements LoginContract.ILoginView {


    EditText etName, etPassword;
    Button btnLogin;
    TextView tvResult;

    LoginContract.ILoginPresenter loginPresenter;

    @Override
    public void showLoading() {
        showDialog();
    }

    @Override
    public void hideLoading() {
        dismissDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mvp);
        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvResult = findViewById(R.id.tv_result);
        loginPresenter = new LoginPresenter();
        loginPresenter.setView(this);
        loginPresenter.init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.login(getAccount(), getPassword());
            }
        });
    }

    @Override
    public String getAccount() {
        return etName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void loginSuccess(String token) {
        tvResult.setText(token);
    }

    @Override
    public void loginFail(long code, String message) {
        tvResult.setText(message);
    }

    @Override
    public void toast(String message) {
        toastShort(message);
    }
}