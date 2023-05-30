package com.jingdong.amon.router.module;

import android.net.Uri;
import com.jingdong.amon.router.JDRouter;
import com.jingdong.amon.router.c;
import com.jingdong.amon.router.callback.NavigationCallback;

/* loaded from: classes18.dex */
public class MethodLetter {
    private String methodName;
    private NavigationCallback.OnCompleteCallback<MethodLetter> onCompleteCallback;
    private NavigationCallback.OnErrorCallback<MethodLetter> onErrorCallback;
    private NavigationCallback.OnLostCallBack<MethodLetter> onLostCallBack;
    private Object[] parameters;
    private Uri uri;

    public MethodLetter(String str, String str2) {
        this.uri = c.c(str);
        this.methodName = str2;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public NavigationCallback.OnCompleteCallback<MethodLetter> getOnCompleteCallback() {
        return this.onCompleteCallback;
    }

    public NavigationCallback.OnErrorCallback<MethodLetter> getOnErrorCallback() {
        return this.onErrorCallback;
    }

    public Object[] getParameters() {
        return this.parameters;
    }

    public Uri getUri() {
        return this.uri;
    }

    public Object navigation() {
        return JDRouter.navigation(this);
    }

    public MethodLetter withOnCompleteCallback(NavigationCallback.OnCompleteCallback<MethodLetter> onCompleteCallback) {
        this.onCompleteCallback = onCompleteCallback;
        return this;
    }

    public MethodLetter withOnErrorCallback(NavigationCallback.OnErrorCallback<MethodLetter> onErrorCallback) {
        this.onErrorCallback = onErrorCallback;
        return this;
    }

    public MethodLetter withParameters(Object... objArr) {
        this.parameters = objArr;
        return this;
    }
}
