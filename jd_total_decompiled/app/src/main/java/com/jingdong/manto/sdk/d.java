package com.jingdong.manto.sdk;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes16.dex */
public class d extends Handler {

    /* renamed from: f  reason: collision with root package name */
    private static int f14186f;

    /* renamed from: c  reason: collision with root package name */
    private a f14187c;
    private final boolean d;
    private volatile boolean a = false;

    /* renamed from: e  reason: collision with root package name */
    private long f14188e = 0;
    private int b = a();

    /* loaded from: classes16.dex */
    public interface a {
        boolean a();
    }

    public d(a aVar, boolean z) {
        this.f14187c = aVar;
        this.d = z;
    }

    private static int a() {
        if (f14186f >= 8192) {
            f14186f = 0;
        }
        int i2 = f14186f + 1;
        f14186f = i2;
        return i2;
    }

    public final void a(long j2) {
        this.f14188e = j2;
        removeMessages(this.b);
        this.a = false;
        sendEmptyMessageDelayed(this.b, j2);
    }

    public final void b() {
        removeMessages(this.b);
        this.f14187c = null;
        this.a = true;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        a aVar;
        if (message.what != this.b || (aVar = this.f14187c) == null || aVar.a() || !this.d || this.a) {
            return;
        }
        sendEmptyMessageDelayed(this.b, this.f14188e);
    }
}
