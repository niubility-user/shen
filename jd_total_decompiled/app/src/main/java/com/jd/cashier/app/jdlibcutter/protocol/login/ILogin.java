package com.jd.cashier.app.jdlibcutter.protocol.login;

import android.content.Context;
import android.os.Bundle;

/* loaded from: classes13.dex */
public interface ILogin {
    void doLogin(Context context, Bundle bundle, Runnable runnable, Runnable runnable2);

    String getCookie();

    String getSession();

    void logout();
}
