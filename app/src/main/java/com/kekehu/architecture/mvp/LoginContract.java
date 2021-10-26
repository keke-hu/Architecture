package com.kekehu.architecture.mvp;

import com.kekehu.architecture.base.BasePresenter;
import com.kekehu.architecture.base.BaseView;

public class LoginContract {

    interface ILoginPresenter extends BasePresenter {
        void login(String account, String password);

        void setView(ILoginView loginView);
    }


    interface ILoginView extends BaseView {

        String getAccount();

        String getPassword();

        void loginSuccess(String token);

        void loginFail(long code, String message);

    }

}
