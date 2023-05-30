package com.hihonor.push.sdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Incorrect class signature, class is equals to this class: <TResult:Ljava/lang/Object;>Lcom/hihonor/push/sdk/e<TTResult;>; */
/* loaded from: classes12.dex */
public final class e<TResult> {
    public boolean b;

    /* renamed from: c  reason: collision with root package name */
    public TResult f1089c;
    public Exception d;
    public final Object a = new Object();

    /* renamed from: e  reason: collision with root package name */
    public List<d0<TResult>> f1090e = new ArrayList();

    public final e<TResult> a(d0<TResult> d0Var) {
        synchronized (this.a) {
            if (!this.b) {
                this.f1090e.add(d0Var);
            } else {
                d0Var.a(this);
            }
        }
        return this;
    }

    public final void b() {
        synchronized (this.a) {
            Iterator<d0<TResult>> it = this.f1090e.iterator();
            while (it.hasNext()) {
                try {
                    it.next().a(this);
                } catch (RuntimeException e2) {
                    throw e2;
                } catch (Exception e3) {
                    throw new RuntimeException(e3);
                }
            }
            this.f1090e = null;
        }
    }

    public final Exception c() {
        Exception exc;
        synchronized (this.a) {
            exc = this.d;
        }
        return exc;
    }

    public final TResult d() {
        TResult tresult;
        synchronized (this.a) {
            if (this.d == null) {
                tresult = this.f1089c;
            } else {
                throw new RuntimeException(this.d);
            }
        }
        return tresult;
    }

    public final boolean e() {
        synchronized (this.a) {
        }
        return false;
    }

    public final boolean f() {
        boolean z;
        synchronized (this.a) {
            if (this.b) {
                e();
                z = this.d == null;
            }
        }
        return z;
    }
}
