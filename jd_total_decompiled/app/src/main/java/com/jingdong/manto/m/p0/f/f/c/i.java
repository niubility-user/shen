package com.jingdong.manto.m.p0.f.f.c;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes15.dex */
final class i {
    private static final Handler a = new Handler(Looper.getMainLooper());

    public void a(Runnable runnable) {
        a.post(runnable);
    }
}
