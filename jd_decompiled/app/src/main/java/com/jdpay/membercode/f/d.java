package com.jdpay.membercode.f;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jdpay.lib.util.JDPayLog;

/* loaded from: classes18.dex */
public class d implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    private com.jdpay.membercode.f.a f7535g;

    /* renamed from: h  reason: collision with root package name */
    private e f7536h;

    /* renamed from: i  reason: collision with root package name */
    private b f7537i;

    /* renamed from: j  reason: collision with root package name */
    private volatile String f7538j;

    /* renamed from: k  reason: collision with root package name */
    private volatile boolean f7539k = false;

    /* renamed from: l  reason: collision with root package name */
    private volatile boolean f7540l = false;

    /* renamed from: m  reason: collision with root package name */
    private final Handler f7541m = new a(Looper.getMainLooper());

    /* loaded from: classes18.dex */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (d.this.f7537i != null && message.what == 2) {
                d.this.f7537i.a();
            } else if (d.this.f7537i != null && message.what == 3) {
                Object obj = message.obj;
                d.this.f7537i.a(obj instanceof Throwable ? (Throwable) obj : null);
            } else if (d.this.f7537i == null || message.what != 1) {
            } else {
                d.this.f7537i.b();
            }
        }
    }

    /* loaded from: classes18.dex */
    public interface b {
        void a();

        void a(Throwable th);

        void b();
    }

    public d(com.jdpay.membercode.f.a aVar, e eVar, b bVar) {
        this.f7535g = aVar;
        this.f7536h = eVar;
        this.f7537i = bVar;
    }

    public void a() {
        this.f7539k = true;
        this.f7541m.removeMessages(2);
        this.f7541m.removeMessages(3);
        this.f7541m.removeMessages(1);
    }

    public void b(String str) {
        this.f7538j = str;
    }

    protected boolean c(c cVar) {
        String str = cVar.a;
        return (TextUtils.isEmpty(this.f7538j) && !TextUtils.isEmpty(str)) || !(TextUtils.isEmpty(this.f7538j) || this.f7538j.equals(str));
    }

    public void d() {
        this.f7538j = null;
        a();
    }

    public String e() {
        return this.f7538j;
    }

    public boolean f() {
        return this.f7535g.g() && this.f7536h.i();
    }

    public boolean g() {
        return c(this.f7535g);
    }

    public boolean h() {
        return this.f7540l;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f7540l = true;
        this.f7539k = false;
        while (!TextUtils.isEmpty(this.f7538j)) {
            String str = this.f7538j;
            int i2 = 2;
            try {
            } catch (Throwable th) {
                JDPayLog.e(th);
                i2 = 3;
            }
            if (this.f7535g == null) {
                return;
            }
            if (!this.f7539k && !TextUtils.isEmpty(str)) {
                if (c(this.f7535g)) {
                    com.jdpay.membercode.f.a aVar = this.f7535g;
                    aVar.a = str;
                    aVar.a();
                }
                if (this.f7536h == null) {
                    return;
                }
                if (this.f7539k) {
                    this.f7535g.c();
                    this.f7536h.c();
                    return;
                }
                if (c(this.f7536h)) {
                    e eVar = this.f7536h;
                    eVar.a = str;
                    eVar.a();
                }
                JDPayLog.i("Created Target:" + str);
                if (this.f7539k) {
                    this.f7540l = false;
                    this.f7541m.sendEmptyMessage(1);
                } else if (str.equals(this.f7538j)) {
                    this.f7540l = false;
                    this.f7541m.sendEmptyMessage(i2);
                    return;
                } else {
                    this.f7535g.c();
                    this.f7536h.c();
                }
            }
            this.f7535g.c();
            this.f7536h.c();
            return;
        }
    }
}
