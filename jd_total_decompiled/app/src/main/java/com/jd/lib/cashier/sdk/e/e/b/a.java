package com.jd.lib.cashier.sdk.e.e.b;

import com.jd.lib.cashier.sdk.d.g.g.d;
import com.jd.lib.cashier.sdk.d.g.g.f;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes14.dex */
public class a {
    private static volatile a b;
    private volatile Map<f, d> a;

    /* renamed from: com.jd.lib.cashier.sdk.e.e.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    static /* synthetic */ class C0120a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[f.values().length];
            a = iArr;
            try {
                iArr[f.JDPAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[f.WEIXIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[f.UNIONPAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private a() {
        c();
    }

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    private synchronized void c() {
        if (this.a == null) {
            this.a = new ConcurrentHashMap(1);
        }
    }

    public synchronized d b(f fVar) {
        if (fVar == null) {
            return null;
        }
        c();
        d dVar = this.a.get(fVar);
        if (dVar != null) {
            return dVar;
        }
        int i2 = C0120a.a[fVar.ordinal()];
        if (i2 == 1) {
            dVar = new com.jd.lib.cashier.sdk.d.g.c.b.a();
        } else if (i2 == 2) {
            dVar = new com.jd.lib.cashier.sdk.d.g.j.b.a();
        } else if (i2 == 3) {
            dVar = new com.jd.lib.cashier.sdk.d.g.i.b.a();
        }
        this.a.put(fVar, dVar);
        return dVar;
    }

    public void d() {
        if (this.a != null) {
            this.a.clear();
            this.a = null;
        }
    }
}
