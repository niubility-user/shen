package jd.wjlogin_sdk.common.lite;

import android.content.Context;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;

/* loaded from: classes.dex */
public interface a {
    boolean isJDAppInstalled();

    boolean isJDAppSupportAPI();

    void loginWithToken(String str, OnCommonCallback onCommonCallback);

    void openJDApp(Context context, String str, OnCommonCallback onCommonCallback);
}
