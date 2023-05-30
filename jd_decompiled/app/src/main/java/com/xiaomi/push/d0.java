package com.xiaomi.push;

import android.content.Context;
import android.os.IBinder;
import com.xiaomi.push.a0;

/* loaded from: classes11.dex */
class d0 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ IBinder f18524g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ a0.b f18525h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d0(a0.b bVar, IBinder iBinder) {
        this.f18525h = bVar;
        this.f18524g = iBinder;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Context context;
        String k2;
        Object obj5;
        Object obj6;
        try {
            context = a0.this.f18441g;
            String packageName = context.getPackageName();
            k2 = a0.this.k();
            a0.a aVar = new a0.a();
            aVar.a = a0.c.a(this.f18524g, packageName, k2, "OUID");
            a0.this.f18444j = aVar;
            a0.this.l();
            a0.this.f18443i = 2;
            obj5 = a0.this.f18445k;
            synchronized (obj5) {
                try {
                    obj6 = a0.this.f18445k;
                    obj6.notifyAll();
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
            a0.this.l();
            a0.this.f18443i = 2;
            obj3 = a0.this.f18445k;
            synchronized (obj3) {
                try {
                    obj4 = a0.this.f18445k;
                    obj4.notifyAll();
                } catch (Exception unused3) {
                }
            }
        } catch (Throwable th) {
            a0.this.l();
            a0.this.f18443i = 2;
            obj = a0.this.f18445k;
            synchronized (obj) {
                try {
                    obj2 = a0.this.f18445k;
                    obj2.notifyAll();
                } catch (Exception unused4) {
                }
                throw th;
            }
        }
    }
}
