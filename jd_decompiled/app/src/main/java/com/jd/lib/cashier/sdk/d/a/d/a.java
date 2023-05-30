package com.jd.lib.cashier.sdk.d.a.d;

import android.view.View;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes14.dex */
public abstract class a {

    /* renamed from: c  reason: collision with root package name */
    private View f3225c;
    private View d;

    /* renamed from: e  reason: collision with root package name */
    private View f3226e;
    private int a = 1;
    private boolean b = false;

    /* renamed from: f  reason: collision with root package name */
    private boolean f3227f = false;

    private void j(RecyclerView.ViewHolder viewHolder, boolean z) {
        if (this.f3226e == null || this.f3227f) {
            this.f3226e = viewHolder.itemView.findViewById(c());
        }
        View view = this.f3226e;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    private void k(RecyclerView.ViewHolder viewHolder, boolean z) {
        if (this.d == null || this.f3227f) {
            this.d = viewHolder.itemView.findViewById(d());
        }
        View view = this.d;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    private void l(RecyclerView.ViewHolder viewHolder, boolean z) {
        if (this.f3225c == null || this.f3227f) {
            this.f3225c = viewHolder.itemView.findViewById(f());
        }
        View view = this.f3225c;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    public void a(RecyclerView.ViewHolder viewHolder) {
        int i2 = this.a;
        if (i2 == 1) {
            l(viewHolder, false);
            k(viewHolder, false);
            j(viewHolder, false);
        } else if (i2 == 2) {
            l(viewHolder, true);
            k(viewHolder, false);
            j(viewHolder, false);
        } else if (i2 == 3) {
            l(viewHolder, false);
            k(viewHolder, true);
            j(viewHolder, false);
        } else if (i2 == 4) {
            l(viewHolder, false);
            k(viewHolder, false);
            j(viewHolder, true);
        }
        this.f3227f = false;
    }

    @LayoutRes
    public abstract int b();

    @IdRes
    protected abstract int c();

    @IdRes
    protected abstract int d();

    public int e() {
        return this.a;
    }

    @IdRes
    protected abstract int f();

    public final boolean g() {
        if (c() == 0) {
            return true;
        }
        return this.b;
    }

    public void h(int i2) {
        this.a = i2;
    }

    public final void i() {
        this.f3227f = true;
    }
}
