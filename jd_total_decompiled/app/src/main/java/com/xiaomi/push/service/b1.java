package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.push.p9;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes11.dex */
public final class b1 implements s {

    /* renamed from: f  reason: collision with root package name */
    private static volatile b1 f19062f;
    private SharedPreferences a;
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f19063c = false;
    private ConcurrentHashMap<String, a> d = new ConcurrentHashMap<>();

    /* renamed from: e  reason: collision with root package name */
    Context f19064e;

    /* loaded from: classes11.dex */
    public static abstract class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        String f19065g;

        /* renamed from: h  reason: collision with root package name */
        long f19066h;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(String str, long j2) {
            this.f19065g = str;
            this.f19066h = j2;
        }

        abstract void a(b1 b1Var);

        @Override // java.lang.Runnable
        public void run() {
            if (b1.f19062f != null) {
                Context context = b1.f19062f.f19064e;
                if (com.xiaomi.push.j0.r(context)) {
                    if (System.currentTimeMillis() - b1.f19062f.a.getLong(":ts-" + this.f19065g, 0L) > this.f19066h || com.xiaomi.push.f.b(context)) {
                        p9.a(b1.f19062f.a.edit().putLong(":ts-" + this.f19065g, System.currentTimeMillis()));
                        a(b1.f19062f);
                    }
                }
            }
        }
    }

    private b1(Context context) {
        this.f19064e = context.getApplicationContext();
        this.a = context.getSharedPreferences("sync", 0);
    }

    public static b1 c(Context context) {
        if (f19062f == null) {
            synchronized (b1.class) {
                if (f19062f == null) {
                    f19062f = new b1(context);
                }
            }
        }
        return f19062f;
    }

    @Override // com.xiaomi.push.service.s
    public void a() {
        if (this.f19063c) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.b < 3600000) {
            return;
        }
        this.b = currentTimeMillis;
        this.f19063c = true;
        com.xiaomi.push.i.b(this.f19064e).h(new c1(this), (int) (Math.random() * 10.0d));
    }

    public String d(String str, String str2) {
        return this.a.getString(str + ":" + str2, "");
    }

    public void f(a aVar) {
        if (this.d.putIfAbsent(aVar.f19065g, aVar) == null) {
            com.xiaomi.push.i.b(this.f19064e).h(aVar, ((int) (Math.random() * 30.0d)) + 10);
        }
    }

    public void g(String str, String str2, String str3) {
        p9.a(f19062f.a.edit().putString(str + ":" + str2, str3));
    }
}
