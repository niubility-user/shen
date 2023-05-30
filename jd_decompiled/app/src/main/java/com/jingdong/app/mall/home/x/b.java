package com.jingdong.app.mall.home.x;

import android.os.SystemClock;
import com.jingdong.corelib.utils.Log;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class b {
    private long a;
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f11085c = false;
    private boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    private CopyOnWriteArrayList<c> f11086e = null;

    public b(long j2) {
    }

    public void a(c cVar) {
        if (this.f11086e == null) {
            this.f11086e = new CopyOnWriteArrayList<>();
        }
        if (cVar != null && !this.f11086e.contains(cVar)) {
            this.f11086e.add(cVar);
            if (Log.D) {
                Log.d("HHH_CountdownTimer", "addListener, size: " + this.f11086e.size());
            }
        }
        long b = b();
        if (b > 0) {
            f(b, b);
        }
    }

    public long b() {
        return this.b - (this.a > 0 ? SystemClock.elapsedRealtime() - this.a : 0L);
    }

    public boolean c() {
        return this.f11085c;
    }

    public boolean d() {
        return this.d;
    }

    public void e() {
        CopyOnWriteArrayList<c> copyOnWriteArrayList = this.f11086e;
        if (copyOnWriteArrayList != null) {
            Iterator<c> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                c next = it.next();
                if (next != null) {
                    next.a();
                }
            }
            this.f11086e.clear();
            this.f11086e = null;
        }
    }

    public void f(long j2, long j3) {
        CopyOnWriteArrayList<c> copyOnWriteArrayList = this.f11086e;
        if (copyOnWriteArrayList != null) {
            Iterator<c> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                c next = it.next();
                if (next != null) {
                    next.b(j2, j3);
                }
            }
        }
    }

    public void g(c cVar) {
        CopyOnWriteArrayList<c> copyOnWriteArrayList;
        if (cVar == null || (copyOnWriteArrayList = this.f11086e) == null || !copyOnWriteArrayList.contains(cVar)) {
            return;
        }
        this.f11086e.remove(cVar);
        if (Log.D) {
            Log.d("HHH_CountdownTimer", "removeListener, size: " + this.f11086e.size());
        }
    }

    public void h(boolean z) {
        this.f11085c = z;
    }

    public void i(long j2) {
        j(j2, false);
    }

    public void j(long j2, boolean z) {
        if (!z) {
            long j3 = this.b;
            if (j3 > 0 && j3 <= j2) {
                return;
            }
        }
        this.a = SystemClock.elapsedRealtime();
        this.b = j2;
    }

    public void k(boolean z) {
        this.d = z;
    }
}
