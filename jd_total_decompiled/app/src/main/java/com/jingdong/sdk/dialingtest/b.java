package com.jingdong.sdk.dialingtest;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jingdong.sdk.dialingtest.d.a.b;
import com.jingdong.sdk.dialingtest.d.b.b;
import com.jingdong.sdk.dialingtest.f.c;
import com.jingdong.sdk.dialingtest.f.d;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes7.dex */
public class b {

    /* renamed from: g */
    private static b f14707g;
    private Context a;
    private Handler b;

    /* renamed from: c */
    private volatile boolean f14708c = false;
    public AtomicInteger d = new AtomicInteger(0);

    /* renamed from: e */
    public AtomicInteger f14709e = new AtomicInteger(0);

    /* renamed from: f */
    public volatile String f14710f = "";

    /* loaded from: classes7.dex */
    public class a extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Looper looper) {
            super(looper);
            b.this = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 2001:
                    com.jingdong.sdk.dialingtest.f.b.c(b.this.b, message.obj);
                    return;
                case 2002:
                    c.a(b.this.b, message.obj);
                    return;
                case 2003:
                    d.b(b.this.b, message.obj);
                    return;
                default:
                    return;
            }
        }
    }

    private b() {
    }

    private void e() {
        String i2 = com.jingdong.sdk.dialingtest.e.a.c.i();
        if (TextUtils.isEmpty(i2)) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "https strategy param is empty");
            return;
        }
        com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "https strategy: " + i2);
        com.jingdong.sdk.dialingtest.d.a.b bVar = new com.jingdong.sdk.dialingtest.d.a.b();
        if (!bVar.b(i2)) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "parse http strategy failed");
            return;
        }
        com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", bVar.a() ? "http test is debug mode" : "http test is common mode");
        boolean c2 = bVar.c();
        com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", c2 ? "http test is expired" : "http test is not expired");
        if (!bVar.a() && !c2) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "http test do not need to detect");
            return;
        }
        com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "http test need to detect");
        if (bVar.f14762e < 1) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "http repeat value:" + bVar.f14762e);
            return;
        }
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.what = 2001;
        obtainMessage.obj = bVar;
        this.b.sendMessageDelayed(obtainMessage, bVar.f14761c * 1000);
    }

    private void f() {
        String k2 = com.jingdong.sdk.dialingtest.e.a.c.k();
        if (TextUtils.isEmpty(k2)) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "ND http strategy param is empty");
            return;
        }
        com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "ND http strategy: " + k2);
        com.jingdong.sdk.dialingtest.d.a.b bVar = new com.jingdong.sdk.dialingtest.d.a.b();
        if (!bVar.b(k2)) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "parse ND http strategy failed");
        } else if (bVar.f14762e < 1) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "ND http strategy repeat < 1");
        } else {
            bVar.f14761c = 0;
            bVar.d = 0;
            bVar.f14767j = true;
            List<b.a> list = bVar.f14765h;
            if (list != null) {
                this.f14709e.set(list.size() * bVar.f14762e);
            } else {
                this.f14709e.set(0);
            }
            Message obtainMessage = this.b.obtainMessage();
            obtainMessage.what = 2001;
            obtainMessage.obj = bVar;
            this.b.sendMessage(obtainMessage);
        }
    }

    private void g() {
        String m2 = com.jingdong.sdk.dialingtest.e.a.c.m();
        if (TextUtils.isEmpty(m2)) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "ND ping strategy param is empty");
            return;
        }
        com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "ND ping strategy: " + m2);
        com.jingdong.sdk.dialingtest.d.b.b bVar = new com.jingdong.sdk.dialingtest.d.b.b();
        if (!bVar.b(m2)) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "parse ND ping strategy failed");
        } else if (bVar.d < 1) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "ND ping strategy repeat < 1");
        } else {
            bVar.f14783c = 0;
            bVar.f14784e = 0;
            bVar.f14790k = true;
            List<b.a> list = bVar.f14788i;
            if (list != null) {
                this.d.set(list.size() * bVar.d);
            } else {
                this.d.set(0);
            }
            Message obtainMessage = this.b.obtainMessage();
            obtainMessage.what = 2002;
            obtainMessage.obj = bVar;
            this.b.sendMessage(obtainMessage);
        }
    }

    private void h() {
        String n2 = com.jingdong.sdk.dialingtest.e.a.c.n();
        if (TextUtils.isEmpty(n2)) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "ping strategy  is empty");
            return;
        }
        com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "ping strategy: " + n2);
        com.jingdong.sdk.dialingtest.d.b.b bVar = new com.jingdong.sdk.dialingtest.d.b.b();
        if (!bVar.b(n2)) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "parse ping strategy failed");
            return;
        }
        com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", bVar.a() ? "ping test is debug mode" : "ping test is common mode");
        boolean c2 = bVar.c();
        com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", c2 ? "ping test is expired" : "ping test is not expired");
        if (!bVar.a() && !c2) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "ping test do not need to detect");
            return;
        }
        com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "ping test need to detect");
        if (bVar.d < 1) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "ping repeat value:" + bVar.d);
            return;
        }
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.what = 2002;
        obtainMessage.obj = bVar;
        this.b.sendMessageDelayed(obtainMessage, bVar.f14783c * 1000);
    }

    private void i() {
        String o = com.jingdong.sdk.dialingtest.e.a.c.o();
        if (TextUtils.isEmpty(o)) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "trace route strategy param is empty");
            return;
        }
        com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "trace route strategy: " + o);
        com.jingdong.sdk.dialingtest.d.c.b bVar = new com.jingdong.sdk.dialingtest.d.c.b();
        if (!bVar.b(o)) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "parse trace route strategy failed");
            return;
        }
        com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", bVar.a() ? "trace route test is debug mode" : "trace route test is common mode");
        boolean c2 = bVar.c();
        com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", c2 ? "trace route test is expired" : "trace route test is not expired");
        if (!bVar.a() && !c2) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "trace route test do not need to detect");
            return;
        }
        com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "trace route test need to detect");
        if (bVar.d < 1) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "trace route repeat value:" + bVar.d);
            return;
        }
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.what = 2003;
        obtainMessage.obj = bVar;
        this.b.sendMessageDelayed(obtainMessage, bVar.f14799c * 1000);
    }

    public static b k() {
        if (f14707g == null) {
            synchronized (b.class) {
                if (f14707g == null) {
                    f14707g = new b();
                }
            }
        }
        return f14707g;
    }

    public void b() {
        if (!this.f14708c) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "not initialed");
            return;
        }
        com.jingdong.sdk.dialingtest.c.d.a.e().f();
        e();
        h();
        i();
    }

    public synchronized void c(Context context, com.jingdong.sdk.dialingtest.e.a.a aVar) {
        if (!this.f14708c && context != null && aVar != null) {
            com.jingdong.sdk.dialingtest.e.a.c.f(aVar);
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.a = context;
            this.b = new a(this.a.getMainLooper());
            this.f14708c = true;
        }
    }

    public void d(com.jingdong.sdk.dialingtest.e.a.b bVar) {
        if (!this.f14708c) {
            com.jingdong.sdk.dialingtest.c.e.a.a("JdDialingTestImpl", "not initialed");
            return;
        }
        com.jingdong.sdk.dialingtest.c.d.a.e().g();
        com.jingdong.sdk.dialingtest.e.a.c.g(bVar);
        this.f14710f = com.jingdong.sdk.dialingtest.f.a.e();
        f();
        g();
    }

    public Context j() {
        return this.a;
    }
}
