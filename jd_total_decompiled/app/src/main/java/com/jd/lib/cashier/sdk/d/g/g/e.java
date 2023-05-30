package com.jd.lib.cashier.sdk.d.g.g;

import androidx.fragment.app.FragmentActivity;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class e {

    /* renamed from: c */
    private static volatile e f3288c;
    private volatile f a;
    private volatile Map<f, d> b;

    /* loaded from: classes14.dex */
    public static /* synthetic */ class a {
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
                a[f.OCTOPUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[f.QQWALLET.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[f.UNIONPAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[f.CYBERMONEY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[f.MEDICALPAY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private e() {
        f();
    }

    public static e c() {
        if (f3288c == null) {
            synchronized (e.class) {
                if (f3288c == null) {
                    f3288c = new e();
                }
            }
        }
        return f3288c;
    }

    private synchronized void f() {
        if (this.b == null) {
            this.b = new HashMap(10);
        }
    }

    public synchronized void a() {
        if (this.b != null) {
            this.b.clear();
            this.b = null;
            this.a = null;
        }
    }

    public synchronized void b(FragmentActivity fragmentActivity, f fVar) {
        d d = d(fVar);
        if (d != null && d.a() != null) {
            d.b(fragmentActivity, d.a());
        }
    }

    public synchronized d d(f fVar) {
        f();
        this.a = fVar;
        d dVar = this.b.get(fVar);
        if (dVar != null) {
            return dVar;
        }
        switch (a.a[fVar.ordinal()]) {
            case 1:
                dVar = new com.jd.lib.cashier.sdk.d.g.c.b.a();
                break;
            case 2:
                dVar = new com.jd.lib.cashier.sdk.d.g.j.b.a();
                break;
            case 3:
                dVar = new com.jd.lib.cashier.sdk.d.g.f.b.a();
                break;
            case 4:
                dVar = new com.jd.lib.cashier.sdk.d.g.h.b.a();
                break;
            case 5:
                dVar = new com.jd.lib.cashier.sdk.d.g.i.b.a();
                break;
            case 6:
                dVar = new com.jd.lib.cashier.sdk.d.g.a.b.a();
                break;
            case 7:
                dVar = new com.jd.lib.cashier.sdk.d.g.d.b.a();
                break;
        }
        this.b.put(fVar, dVar);
        return dVar;
    }

    public synchronized d e() {
        if (this.a != null) {
            return d(this.a);
        }
        return null;
    }
}
