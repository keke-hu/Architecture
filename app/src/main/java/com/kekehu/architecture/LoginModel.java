package com.kekehu.architecture;

import android.os.Handler;
import android.os.Looper;

import java.util.Random;


public class LoginModel {

    public interface Callback {

        void onSuccess(String token);

        void fail(int code, String message);
    }

    public void login(String name, String password, Callback callback) {
        //TODO 添加公共参数等逻辑
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    if (new Random().nextInt(100) > 50) {
                        callback.onSuccess("12345");
                    } else {
                        callback.fail(101, "出错了");
                    }
                }
            }
        }, 1000);
    }

}
