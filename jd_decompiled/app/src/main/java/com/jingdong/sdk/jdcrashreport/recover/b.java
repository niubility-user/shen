package com.jingdong.sdk.jdcrashreport.recover;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public abstract class b {

    /* renamed from: g */
    private WeakReference<RecoverActivity> f14955g;

    public final void a() {
        if (this.f14955g.get() != null) {
            this.f14955g.get().finish();
        }
    }

    public final void b(Context context) {
        if (this.f14955g == null) {
            this.f14955g = new WeakReference<>((RecoverActivity) context);
        }
    }

    public abstract View c(Context context);

    public void d() {
    }

    public final void e() {
        this.f14955g.clear();
        this.f14955g = null;
    }

    public boolean f(int i2, KeyEvent keyEvent) {
        if (this.f14955g.get() != null) {
            return this.f14955g.get().d(i2, keyEvent);
        }
        return false;
    }

    public void g() {
    }

    public void h() {
    }

    public void i() {
    }

    public final boolean j(int i2) {
        if (this.f14955g.get() != null) {
            return this.f14955g.get().requestWindowFeature(i2);
        }
        return false;
    }

    public final void k(int i2, int i3) {
        if (this.f14955g.get() != null) {
            this.f14955g.get().getWindow().setFlags(i2, i3);
        }
    }

    public final void l(String str, a aVar) {
        if (this.f14955g.get() != null) {
            this.f14955g.get().c(str, aVar);
        }
    }
}
