package com.jingdong.manto.v.b;

import android.os.Handler;
import android.os.HandlerThread;

/* loaded from: classes16.dex */
public final class a {

    /* renamed from: c  reason: collision with root package name */
    private static a f14315c = new a();
    private Handler a;
    public HandlerThread b;

    private a() {
        HandlerThread handlerThread = new HandlerThread("MantoHeavyWorkThread", 10);
        this.b = handlerThread;
        handlerThread.start();
        this.a = new Handler(this.b.getLooper());
    }

    public static a a() {
        return f14315c;
    }

    public void a(Runnable runnable) {
        this.a.post(runnable);
    }
}
