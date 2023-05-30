package com.jingdong.app.mall.crash;

import java.util.Date;
import java.util.concurrent.Callable;

/* loaded from: classes3.dex */
public class c implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public long f8331g;

    /* renamed from: h  reason: collision with root package name */
    public long f8332h;

    /* renamed from: i  reason: collision with root package name */
    public String f8333i;

    /* renamed from: j  reason: collision with root package name */
    public long f8334j;

    /* renamed from: k  reason: collision with root package name */
    public Date f8335k;

    /* loaded from: classes3.dex */
    class a implements Callable<Object> {
        a() {
        }

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            synchronized (this) {
                if (!com.jingdong.app.mall.crash.a.a) {
                    com.jingdong.app.mall.crash.a.a();
                }
                c cVar = c.this;
                com.jingdong.app.mall.crash.a.c(cVar.f8333i, String.valueOf(cVar.f8334j), c.this.f8335k);
            }
            return null;
        }
    }

    public String b() {
        return this.f8333i;
    }

    public void c(long j2, String str) {
        this.f8331g = j2;
        this.f8333i = str;
        this.f8335k = new Date();
    }

    @Override // java.lang.Runnable
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        this.f8332h = currentTimeMillis;
        this.f8334j = currentTimeMillis - this.f8331g;
        f.f.c(new a(), f.f.f19368i);
    }
}
