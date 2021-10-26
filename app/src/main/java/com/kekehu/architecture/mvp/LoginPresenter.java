package com.kekehu.architecture.mvp;

import android.text.TextUtils;

import com.kekehu.architecture.LoginModel;

public class LoginPresenter implements LoginContract.ILoginPresenter {

    LoginContract.ILoginView loginView;

    LoginModel loginModel;

    @Override
    public void login(String account, String password) {
        if (TextUtils.isEmpty(account)) {
            loginView.toast("请输入账号");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            loginView.toast("请输入密码");
            return;
        }

        loginModel.login(account, password, new LoginModel.Callback() {
            @Override
            public void onSuccess(String token) {
                loginView.loginSuccess(token);
            }

            @Override
            public void fail(int code, String message) {
                loginView.loginFail(code, message);
            }
        });

    }

    @Override
    public void setView(LoginContract.ILoginView loginView) {
        this.loginView = loginView;
    }


    @Override
    public void init() {
        loginModel = new LoginModel();
    }
}
