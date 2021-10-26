package com.kekehu.architecture.mvvm;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kekehu.architecture.LoginModel;

public class LoginViewModel extends AndroidViewModel {


    LoginModel loginModel;

    private MutableLiveData<String> liveDataToken = new MutableLiveData<>();

    private MutableLiveData<String> toastString = new MutableLiveData<>();

    public MutableLiveData<FailBean> getFailBeanMutableLiveData() {
        return failBeanMutableLiveData;
    }

    private MutableLiveData<FailBean> failBeanMutableLiveData = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }


    public void init() {
        loginModel = new LoginModel();
    }

    public void login(String account, String password) {
        if (TextUtils.isEmpty(account)) {
            toastString.setValue("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            toastString.setValue("请输入密码");
            return;
        }
        loginModel.login(account, password, new LoginModel.Callback() {
            @Override
            public void onSuccess(String token) {
                liveDataToken.setValue(token);
            }

            @Override
            public void fail(int code, String message) {
                failBeanMutableLiveData.setValue(new FailBean(code,message));
            }
        });

    }

    public LiveData<String> getToken() {
        return liveDataToken;
    }

    public LiveData<String> getToastString() {
        return toastString;
    }

    public class FailBean {
        private long code;
        private String message;

        public FailBean(long code, String message) {
            this.code = code;
            this.message = message;
        }

        public long getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

}
