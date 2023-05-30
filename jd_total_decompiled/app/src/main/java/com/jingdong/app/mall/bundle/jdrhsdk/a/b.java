package com.jingdong.app.mall.bundle.jdrhsdk.a;

import android.app.Activity;

/* loaded from: classes2.dex */
public abstract class b {
    private com.jingdong.app.mall.bundle.jdrhsdk.b.a a;
    protected Activity b;

    /* renamed from: c  reason: collision with root package name */
    protected e f8133c;
    protected boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    private int f8134e = -1;

    /* loaded from: classes2.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f8135g;

        a(String str) {
            this.f8135g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            e eVar = b.this.f8133c;
            if (eVar != null) {
                eVar.onRiskHandleSuccess(this.f8135g);
            }
        }
    }

    /* renamed from: com.jingdong.app.mall.bundle.jdrhsdk.a.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    class RunnableC0248b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f8137g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f8138h;

        RunnableC0248b(int i2, String str) {
            this.f8137g = i2;
            this.f8138h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            e eVar = b.this.f8133c;
            if (eVar != null) {
                eVar.onRiskHandleFailure(this.f8137g, this.f8138h);
            }
        }
    }

    /* loaded from: classes2.dex */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e eVar = b.this.f8133c;
            if (eVar != null) {
                eVar.onRiskHandleViewLoaded();
            }
        }
    }

    /* loaded from: classes2.dex */
    class d implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f8141g;

        d(String str) {
            this.f8141g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            e eVar = b.this.f8133c;
            if (eVar != null) {
                eVar.onRiskHandleError(this.f8141g);
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface e {
        void onRiskHandleError(String str);

        void onRiskHandleFailure(int i2, String str);

        void onRiskHandleLoadingChanged(boolean z);

        void onRiskHandleSuccess(String str);

        void onRiskHandleViewLoaded();
    }

    public b(Activity activity) {
        this.b = activity;
        com.jingdong.app.mall.bundle.jdrhsdk.d.d.a("RiskHandle", "risk handle created. class name:" + getClass().getName());
    }

    private void f(Runnable runnable) {
        com.jingdong.app.mall.bundle.jdrhsdk.d.c.c().post(runnable);
    }

    public abstract String a();

    public void b(int i2) {
        this.f8134e = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(int i2, String str) {
        f(new RunnableC0248b(i2, str));
    }

    public void d(e eVar) {
        this.f8133c = eVar;
    }

    public void e(com.jingdong.app.mall.bundle.jdrhsdk.b.a aVar) {
        this.a = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g(String str) {
        f(new d(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(boolean z) {
        this.d = z;
    }

    public void i() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void j(String str) {
        f(new a(str));
    }

    public void k(boolean z) {
        e eVar = this.f8133c;
        if (eVar != null) {
            eVar.onRiskHandleLoadingChanged(z);
        }
    }

    public com.jingdong.app.mall.bundle.jdrhsdk.b.a l() {
        com.jingdong.app.mall.bundle.jdrhsdk.b.a aVar = this.a;
        return aVar == null ? new com.jingdong.app.mall.bundle.jdrhsdk.b.a("") : aVar;
    }

    public int m() {
        return this.f8134e;
    }

    public boolean n() {
        return this.d;
    }

    public void o() {
    }

    public abstract String p();

    public abstract void q();

    /* JADX INFO: Access modifiers changed from: protected */
    public void r() {
        f(new c());
    }
}
