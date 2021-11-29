package com.kekehu.architecture.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kekehu.architecture.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    protected Dialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    protected void showDialog() {
        if (mDialog == null) {
            mDialog = new Dialog(mContext);
            mDialog.setContentView(R.layout.layout_dialog);
        }
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    protected void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
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
