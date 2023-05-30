package com.jingdong.union.dependency;

import android.content.Context;
import android.view.View;

/* loaded from: classes12.dex */
public class a {

    /* renamed from: m  reason: collision with root package name */
    private static a f15650m;
    private IAdvertUtils a;
    private IJumpDispatchCallBack b;

    /* renamed from: c  reason: collision with root package name */
    private ILoginUser f15651c;
    private IMtaUtils d;

    /* renamed from: e  reason: collision with root package name */
    private IUnionExceptionReport f15652e;

    /* renamed from: f  reason: collision with root package name */
    private IWebUa f15653f;

    /* renamed from: g  reason: collision with root package name */
    private IOaid f15654g;

    /* renamed from: h  reason: collision with root package name */
    private IUuid f15655h;

    /* renamed from: i  reason: collision with root package name */
    private IAndroidId f15656i;

    /* renamed from: j  reason: collision with root package name */
    private IDensity f15657j;

    /* renamed from: k  reason: collision with root package name */
    private IJdAdvertUtils f15658k;

    /* renamed from: l  reason: collision with root package name */
    private ILoadingView f15659l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.union.dependency.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public class C0751a implements ILoadingView {
        C0751a(a aVar) {
        }

        @Override // com.jingdong.union.dependency.ILoadingView
        public View getLoadingView(Context context) {
            return new com.jingdong.union.b.a(context);
        }
    }

    public static synchronized a d() {
        a aVar;
        synchronized (a.class) {
            if (f15650m == null) {
                f15650m = new a();
            }
            aVar = f15650m;
        }
        return aVar;
    }

    public IAndroidId a() {
        if (this.f15656i == null) {
            this.f15656i = new b();
        }
        return this.f15656i;
    }

    public IDensity b() {
        if (this.f15657j == null) {
            this.f15657j = new b();
        }
        return this.f15657j;
    }

    public IJdAdvertUtils c() {
        if (this.f15658k == null) {
            this.f15658k = new b();
        }
        return this.f15658k;
    }

    public IUuid e() {
        if (this.f15655h == null) {
            this.f15655h = new b();
        }
        return this.f15655h;
    }

    public IAdvertUtils f() {
        if (this.a == null) {
            this.a = new b();
        }
        return this.a;
    }

    public IJumpDispatchCallBack g() {
        if (this.b == null) {
            this.b = new b();
        }
        return this.b;
    }

    public ILoadingView h() {
        if (this.f15659l == null) {
            this.f15659l = new C0751a(this);
        }
        return this.f15659l;
    }

    public ILoginUser i() {
        if (this.f15651c == null) {
            this.f15651c = new b();
        }
        return this.f15651c;
    }

    public IMtaUtils j() {
        if (this.d == null) {
            this.d = new b();
        }
        return this.d;
    }

    public IOaid k() {
        if (this.f15654g == null) {
            this.f15654g = new b();
        }
        return this.f15654g;
    }

    public IUnionExceptionReport l() {
        if (this.f15652e == null) {
            this.f15652e = new b();
        }
        return this.f15652e;
    }

    public IWebUa m() {
        if (this.f15653f == null) {
            this.f15653f = new b();
        }
        return this.f15653f;
    }
}
