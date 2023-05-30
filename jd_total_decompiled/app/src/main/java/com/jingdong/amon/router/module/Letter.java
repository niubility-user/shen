package com.jingdong.amon.router.module;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.amon.router.JDRouter;
import com.jingdong.amon.router.c;
import com.jingdong.amon.router.callback.NavigationCallback;
import java.util.Set;

/* loaded from: classes18.dex */
public class Letter {
    private String action;
    private Class destination;
    private Context from;
    private Bundle mExtras;
    private NavigationCallback.OnCompleteCallback<Letter> onCompleteCallback;
    private NavigationCallback.OnFoundCallback<Letter> onFoundCallback;
    private NavigationCallback.OnInterruptCallback<Letter> onInterruptCallback;
    private NavigationCallback.OnLostCallBack<Letter> onLostCallBack;
    private Bundle optionsCompat;
    private Uri uri;
    private String uriStr;
    private int mRequestCode = -1;
    private int mFlags = -1;
    private int enterAnim = -1;
    private int exitAnim = -1;

    public Letter(Context context, String str) {
        this.from = context;
        this.uriStr = str;
        Uri c2 = c.c(str);
        this.uri = c2;
        Set<String> queryParameterNames = c2.getQueryParameterNames();
        if (queryParameterNames == null || queryParameterNames.size() <= 0) {
            return;
        }
        for (String str2 : queryParameterNames) {
            if (!TextUtils.isEmpty(str2)) {
                String queryParameter = this.uri.getQueryParameter(str2);
                if (!TextUtils.isEmpty(queryParameter)) {
                    if (this.mExtras == null) {
                        this.mExtras = new Bundle();
                    }
                    this.mExtras.putString(str2, queryParameter);
                }
            }
        }
    }

    public void applyDynamicLetter(DynamicLetter dynamicLetter) {
        Bundle combineBundle;
        if (dynamicLetter == null) {
            return;
        }
        if (!TextUtils.isEmpty(dynamicLetter.getAction())) {
            this.action = dynamicLetter.getAction();
        }
        if (dynamicLetter.getDestination() != null) {
            this.destination = dynamicLetter.getDestination();
        }
        if (dynamicLetter.getEnterAnim() != -1) {
            this.enterAnim = dynamicLetter.getEnterAnim();
        }
        if (dynamicLetter.getExitAnim() != -1) {
            this.exitAnim = dynamicLetter.getExitAnim();
        }
        if (dynamicLetter.getRequestCode() != -1) {
            this.mRequestCode = dynamicLetter.getRequestCode();
        }
        if (dynamicLetter.getFlags() != -1) {
            this.mFlags = dynamicLetter.getFlags();
        }
        if (dynamicLetter.getOptionsCompat() != null) {
            this.optionsCompat = dynamicLetter.getOptionsCompat();
        }
        if (dynamicLetter.getOnLostCallBack() != null) {
            this.onLostCallBack = dynamicLetter.getOnLostCallBack();
        }
        if (dynamicLetter.getOnCompleteCallback() != null) {
            this.onCompleteCallback = dynamicLetter.getOnCompleteCallback();
        }
        if (dynamicLetter.getExtras() != null) {
            combineBundle = dynamicLetter.getExtras();
        } else if (this.mExtras != null) {
            if (dynamicLetter.getCombineBundle() != null) {
                this.mExtras.putAll(dynamicLetter.getCombineBundle());
                return;
            }
            return;
        } else {
            combineBundle = dynamicLetter.getCombineBundle();
        }
        this.mExtras = combineBundle;
    }

    public String getAction() {
        return this.action;
    }

    public Class getDestination() {
        return this.destination;
    }

    public int getEnterAnim() {
        return this.enterAnim;
    }

    public int getExitAnim() {
        return this.exitAnim;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public Context getFrom() {
        return this.from;
    }

    public NavigationCallback.OnCompleteCallback<Letter> getOnCompleteCallback() {
        return this.onCompleteCallback;
    }

    public NavigationCallback.OnFoundCallback<Letter> getOnFoundCallback() {
        return this.onFoundCallback;
    }

    public NavigationCallback.OnInterruptCallback<Letter> getOnInterruptCallback() {
        return this.onInterruptCallback;
    }

    public NavigationCallback.OnLostCallBack<Letter> getOnLostCallBack() {
        return this.onLostCallBack;
    }

    public Bundle getOptionsCompat() {
        return this.optionsCompat;
    }

    public int getRequestCode() {
        return this.mRequestCode;
    }

    public Uri getUri() {
        return this.uri;
    }

    public String getUriStr() {
        return this.uriStr;
    }

    public Object navigation() {
        return JDRouter.navigation(this);
    }

    public Letter putExtras(Bundle bundle) {
        if (this.mExtras == null) {
            this.mExtras = new Bundle();
        }
        this.mExtras.putAll(bundle);
        return this;
    }

    public void setDestination(Class cls) {
        this.destination = cls;
    }

    public void setFrom(Context context) {
        this.from = context;
    }

    public void setUriStr(String str) {
        this.uriStr = str;
    }

    public Letter withAction(String str) {
        this.action = str;
        return this;
    }

    public Letter withEnterAnim(int i2) {
        this.enterAnim = i2;
        return this;
    }

    public Letter withExitAnim(int i2) {
        this.exitAnim = i2;
        return this;
    }

    public Letter withExtras(Bundle bundle) {
        this.mExtras = bundle;
        return this;
    }

    public Letter withFlags(int i2) {
        this.mFlags = i2;
        return this;
    }

    public Letter withOnCompleteCallback(NavigationCallback.OnCompleteCallback<Letter> onCompleteCallback) {
        this.onCompleteCallback = onCompleteCallback;
        return this;
    }

    public Letter withOnLostCallBack(NavigationCallback.OnLostCallBack<Letter> onLostCallBack) {
        this.onLostCallBack = onLostCallBack;
        return this;
    }

    public Letter withOptionsCompat(Bundle bundle) {
        this.optionsCompat = bundle;
        return this;
    }

    public Letter withRequestCode(int i2) {
        if (this.from instanceof Activity) {
            this.mRequestCode = i2;
            return this;
        }
        throw new IllegalArgumentException("add requesetCode need Activity context!!");
    }
}
