package com.jd.lib.babel.servicekit.iservice;

import android.content.Context;

/* loaded from: classes13.dex */
public interface ILogin {
    void executeLogin(Context context, BabelLoginCallback babelLoginCallback);

    String getCookie();

    String getUserPin();

    boolean hasLogin();
}
