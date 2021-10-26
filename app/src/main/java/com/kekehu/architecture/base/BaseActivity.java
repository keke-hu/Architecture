package com.kekehu.architecture.base;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    protected void toastShort(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    protected void toastLong(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }
}
