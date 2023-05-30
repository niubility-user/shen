package com.xiaomi.push;

import android.os.IBinder;
import com.xiaomi.push.p;

/* loaded from: classes11.dex */
class s implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ IBinder f18996g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ p.a f18997h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(p.a aVar, IBinder iBinder) {
        this.f18997h = aVar;
        this.f18996g = iBinder;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        try {
            p.this.f18925j = p.b.a(this.f18996g);
            p.this.f18926k = p.b.b(this.f18996g);
            p.this.i();
            p.this.f18924i = 2;
            obj5 = p.this.f18927l;
            synchronized (obj5) {
                try {
                    obj6 = p.this.f18927l;
                    obj6.notifyAll();
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
            p.this.i();
            p.this.f18924i = 2;
            obj3 = p.this.f18927l;
            synchronized (obj3) {
                try {
                    obj4 = p.this.f18927l;
                    obj4.notifyAll();
                } catch (Exception unused3) {
                }
            }
        } catch (Throwable th) {
            p.this.i();
            p.this.f18924i = 2;
            obj = p.this.f18927l;
            synchronized (obj) {
                try {
                    obj2 = p.this.f18927l;
                    obj2.notifyAll();
                } catch (Exception unused4) {
                }
                throw th;
            }
        }
    }
}
