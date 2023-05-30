package com.jd.libs.hybrid.base.engine;

import androidx.annotation.Keep;

@Keep
/* loaded from: classes16.dex */
public interface ConfigEngine {

    @Keep
    /* loaded from: classes16.dex */
    public interface Callback<T> {
        void onFail(int i2, String str);

        void onSuccess(T t);
    }

    void getAllConfig(Callback<String> callback);

    void getConfigById(String str, Callback<String> callback);

    void getDebugConfig(String str, Callback<String> callback);
}
