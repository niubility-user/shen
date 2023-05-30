package com.jingdong.app.mall.home.x;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class a {
    private AtomicBoolean a;
    private List<d> b;

    /* renamed from: c  reason: collision with root package name */
    private Handler f11084c;

    /* renamed from: com.jingdong.app.mall.home.x.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class HandlerC0336a extends Handler {
        HandlerC0336a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            synchronized (a.this) {
                a.this.c();
                sendMessageDelayed(obtainMessage(1), 1000L);
            }
        }
    }

    /* loaded from: classes4.dex */
    private static class b {
        private static final a a = new a(null);
    }

    /* synthetic */ a(HandlerC0336a handlerC0336a) {
        this();
    }

    public static a b() {
        return b.a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void c() {
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.b.isEmpty()) {
            g();
            return;
        }
        Iterator<d> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    public static String[] h(long j2) {
        long j3 = j2 / 1000;
        long j4 = (j3 / 60) / 60;
        long j5 = ((j2 - (3600000 * j4)) / 1000) / 60;
        long j6 = (j3 - ((j4 * 60) * 60)) - (60 * j5);
        if (j4 > 99) {
            j4 = 99;
        }
        if (j5 < 0) {
            j5 = 0;
        }
        if (j6 < 0) {
            j6 = 0;
        }
        String str = j4 + "";
        String str2 = j5 + "";
        String str3 = j6 + "";
        String[] strArr = new String[3];
        if (str.length() == 1) {
            str = "0" + str;
        }
        strArr[0] = str;
        if (str2.length() == 1) {
            str2 = "0" + str2;
        }
        strArr[1] = str2;
        if (str3.length() == 1) {
            str3 = "0" + str3;
        }
        strArr[2] = str3;
        return strArr;
    }

    public synchronized void d(d dVar) {
        if (dVar != null) {
            if (!this.b.contains(dVar)) {
                dVar.a();
                this.b.add(dVar);
                f();
            }
        }
    }

    public synchronized void e() {
        this.b.clear();
        g();
    }

    public synchronized void f() {
        if (!this.a.get()) {
            this.a.set(true);
            Handler handler = this.f11084c;
            handler.sendMessage(handler.obtainMessage(1));
        }
    }

    public synchronized void g() {
        this.a.set(false);
        this.f11084c.removeMessages(1);
    }

    public synchronized void i(d dVar) {
        if (dVar != null) {
            if (this.b.contains(dVar)) {
                this.b.remove(dVar);
            }
        }
    }

    private a() {
        this.a = new AtomicBoolean(false);
        this.b = new CopyOnWriteArrayList();
        this.f11084c = new HandlerC0336a(Looper.getMainLooper());
    }
}
