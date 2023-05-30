package com.jd.lib.cashier.sdk.core.utils;

import android.view.View;

/* loaded from: classes14.dex */
public abstract class b implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    private boolean f3087g;

    /* renamed from: h  reason: collision with root package name */
    private long f3088h;

    /* renamed from: i  reason: collision with root package name */
    private Runnable f3089i;

    /* loaded from: classes14.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.f3087g = true;
        }
    }

    public b() {
        this.f3087g = true;
        this.f3088h = 500L;
        this.f3089i = new a();
    }

    public abstract void b(View view);

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f3087g) {
            this.f3087g = false;
            view.postDelayed(this.f3089i, this.f3088h);
            b(view);
        }
    }

    public b(long j2) {
        this.f3087g = true;
        this.f3088h = 500L;
        this.f3089i = new a();
        this.f3088h = j2;
    }
}
