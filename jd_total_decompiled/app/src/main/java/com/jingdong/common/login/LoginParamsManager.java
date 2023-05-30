package com.jingdong.common.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/* loaded from: classes5.dex */
public class LoginParamsManager {
    private Activity mActivity;
    private String mBuilderStr;
    private Bundle mBundle;
    private String mCalltag;
    private Context mContext;
    private int mIntentFlag;
    private ILogin mLogin;
    private int mLoginType;
    private int mRequestCode;

    public LoginParamsManager(Activity activity, Bundle bundle, ILogin iLogin, String str, int i2, int i3, int i4) {
        this.mLoginType = i4;
        this.mBundle = bundle;
        this.mLogin = iLogin;
        this.mCalltag = str;
        this.mIntentFlag = i2;
        this.mActivity = activity;
        this.mRequestCode = i3;
    }

    public void closeRes() {
        if (this.mContext != null) {
            this.mContext = null;
        }
        if (this.mActivity != null) {
            this.mActivity = null;
        }
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public String getBuilderStr() {
        return this.mBuilderStr;
    }

    public Bundle getBundle() {
        return this.mBundle;
    }

    public String getCalltag() {
        return this.mCalltag;
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getIntentFlag() {
        return this.mIntentFlag;
    }

    public ILogin getLogin() {
        return this.mLogin;
    }

    public int getLoginType() {
        return this.mLoginType;
    }

    public int getRequestCode() {
        return this.mRequestCode;
    }

    public LoginParamsManager(Context context, Bundle bundle, ILogin iLogin, String str, int i2, int i3) {
        this.mContext = context;
        this.mBundle = bundle;
        this.mLogin = iLogin;
        this.mCalltag = str;
        this.mIntentFlag = i2;
        this.mLoginType = i3;
    }

    public LoginParamsManager(Activity activity, Bundle bundle, int i2, int i3) {
        this.mActivity = activity;
        this.mBundle = bundle;
        this.mRequestCode = i2;
        this.mLoginType = i3;
    }

    public LoginParamsManager(Context context, Bundle bundle, ILogin iLogin, String str, int i2) {
        this.mContext = context;
        this.mBundle = bundle;
        this.mLogin = iLogin;
        this.mCalltag = str;
        this.mLoginType = i2;
    }
}
