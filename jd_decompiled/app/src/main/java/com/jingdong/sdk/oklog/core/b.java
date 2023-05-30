package com.jingdong.sdk.oklog.core;

import android.text.TextUtils;
import android.util.Log;

/* loaded from: classes.dex */
public final class b implements a {
    @Override // com.jingdong.sdk.oklog.core.a
    public final void a(int i2, String str, String str2, Throwable th) {
        if (th != null) {
            if (TextUtils.isEmpty(str2)) {
                str2 = Log.getStackTraceString(th);
            } else {
                str2 = str2 + " : " + Log.getStackTraceString(th);
            }
        }
        Log.println(i2, str, str2);
    }
}
