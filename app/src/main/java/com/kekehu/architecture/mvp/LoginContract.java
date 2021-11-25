package com.kekehu.architecture.mvp;

import com.kekehu.architecture.base.BasePresenter;
import com.kekehu.architecture.base.BaseView;

public class LoginContract {

    interface ILoginPresenter extends BasePresenter {
        /**
         * 登录操作
         * @param account 账户
         * @param password 密码
         */
        void login(String account, String password);

        void setView(ILoginView loginView);
    }

    interface ILoginView extends BaseView {
        /**
         * 获取账号
         * @return 账号
         */
        String getAccount();
        /**
         * 获取密码
         * @return 密码
         */
        String getPassword();
        /**
         * 登录成功
         * @param token 用户token
         */
        void loginSuccess(String token);
        /**
         * 登录失败
         * @param code 错误码
         * @param message 错误信息
         */
        void loginFail(long code, String message);

        /**
         * 展示加载框
         */
        void showLoading();

        /**
         * 隐藏加载框
         */
        void hideLoading();
    }
}
