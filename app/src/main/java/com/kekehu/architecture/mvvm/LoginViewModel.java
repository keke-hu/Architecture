package com.kekehu.architecture.mvvm;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kekehu.architecture.LoginModel;
import com.kekehu.architecture.utils.AppUtils;

public class LoginViewModel extends AndroidViewModel {


    LoginModel loginModel;

    private MutableLiveData<String> liveDataToken = new MutableLiveData<>();

    public MutableLiveData<Boolean> getShowDialog() {
        return showDialog;
    }

    private MutableLiveData<Boolean> showDialog = new MutableLiveData<>();

    //返回提示语，视图层可以自己选择用toast显示还是弹窗提示
    private MutableLiveData<String> hintString = new MutableLiveData<>();

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
            hintString.setValue("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            hintString.setValue("请输入密码");
            return;
        }

        if(!AppUtils.isPhone(account)){
            hintString.setValue("请输入正确的手机号码");
            return;
        }

        showDialog.setValue(true);
        loginModel.login(account, password, new LoginModel.Callback() {
            @Override
            public void onSuccess(String token) {
                showDialog.setValue(false);
                liveDataToken.setValue(token);
            }

            @Override
            public void fail(int code, String message) {
                showDialog.setValue(false);
                failBeanMutableLiveData.setValue(new FailBean(code, message));
            }
        });

    }

    public LiveData<String> getToken() {
        return liveDataToken;
    }

    public LiveData<String> getHintString() {
        return hintString;
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
