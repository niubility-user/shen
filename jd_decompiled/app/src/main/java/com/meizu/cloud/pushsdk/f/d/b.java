package com.meizu.cloud.pushsdk.f.d;

import android.text.TextUtils;
import com.meizu.cloud.pushsdk.f.d.a;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;
import com.meizu.cloud.pushsdk.notification.model.BrightRemindSetting;

/* loaded from: classes14.dex */
public class b extends com.meizu.cloud.pushsdk.f.d.a {
    private final String d;

    /* renamed from: e  reason: collision with root package name */
    private final String f15896e;

    /* renamed from: f  reason: collision with root package name */
    private final String f15897f;

    /* renamed from: g  reason: collision with root package name */
    private final String f15898g;

    /* renamed from: h  reason: collision with root package name */
    private final String f15899h;

    /* renamed from: i  reason: collision with root package name */
    private final String f15900i;

    /* renamed from: j  reason: collision with root package name */
    private final String f15901j;

    /* renamed from: k  reason: collision with root package name */
    private final String f15902k;

    /* renamed from: l  reason: collision with root package name */
    private final String f15903l;

    /* renamed from: m  reason: collision with root package name */
    private final int f15904m;

    /* renamed from: com.meizu.cloud.pushsdk.f.d.b$b  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    private static class C0764b extends c<C0764b> {
        private C0764b() {
        }

        protected C0764b A() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.meizu.cloud.pushsdk.f.d.a.AbstractC0763a
        public /* bridge */ /* synthetic */ a.AbstractC0763a a() {
            A();
            return this;
        }
    }

    /* loaded from: classes14.dex */
    public static abstract class c<T extends c<T>> extends a.AbstractC0763a<T> {
        private String d;

        /* renamed from: e  reason: collision with root package name */
        private String f15905e;

        /* renamed from: f  reason: collision with root package name */
        private String f15906f;

        /* renamed from: g  reason: collision with root package name */
        private String f15907g;

        /* renamed from: h  reason: collision with root package name */
        private String f15908h;

        /* renamed from: i  reason: collision with root package name */
        private String f15909i;

        /* renamed from: j  reason: collision with root package name */
        private String f15910j;

        /* renamed from: k  reason: collision with root package name */
        private String f15911k;

        /* renamed from: l  reason: collision with root package name */
        private String f15912l;

        /* renamed from: m  reason: collision with root package name */
        private int f15913m = 0;

        public T f(int i2) {
            this.f15913m = i2;
            return (T) a();
        }

        public T g(String str) {
            this.f15906f = str;
            return (T) a();
        }

        public T j(String str) {
            this.f15912l = str;
            return (T) a();
        }

        public b k() {
            return new b(this);
        }

        public T l(String str) {
            this.d = str;
            return (T) a();
        }

        public T n(String str) {
            this.f15907g = str;
            return (T) a();
        }

        public T p(String str) {
            this.f15911k = str;
            return (T) a();
        }

        public T r(String str) {
            this.f15909i = str;
            return (T) a();
        }

        public T t(String str) {
            this.f15908h = str;
            return (T) a();
        }

        public T v(String str) {
            this.f15910j = str;
            return (T) a();
        }

        public T x(String str) {
            this.f15905e = str;
            return (T) a();
        }
    }

    protected b(c<?> cVar) {
        super(cVar);
        this.f15896e = ((c) cVar).f15905e;
        this.f15897f = ((c) cVar).f15906f;
        this.f15898g = ((c) cVar).f15907g;
        this.d = ((c) cVar).d;
        this.f15899h = ((c) cVar).f15908h;
        this.f15900i = ((c) cVar).f15909i;
        this.f15901j = ((c) cVar).f15910j;
        this.f15902k = ((c) cVar).f15911k;
        this.f15903l = ((c) cVar).f15912l;
        this.f15904m = ((c) cVar).f15913m;
    }

    public static c<?> e() {
        return new C0764b();
    }

    public com.meizu.cloud.pushsdk.f.b.c f() {
        String str;
        String str2;
        com.meizu.cloud.pushsdk.f.b.c cVar = new com.meizu.cloud.pushsdk.f.b.c();
        cVar.a("en", this.d);
        cVar.a("ti", this.f15896e);
        if (TextUtils.isEmpty(this.f15898g)) {
            str = this.f15897f;
            str2 = AppIconSetting.DEFAULT_LARGE_ICON;
        } else {
            str = this.f15898g;
            str2 = "fdId";
        }
        cVar.a(str2, str);
        cVar.a("pv", this.f15899h);
        cVar.a("pn", this.f15900i);
        cVar.a("si", this.f15901j);
        cVar.a("ms", this.f15902k);
        cVar.a("ect", this.f15903l);
        cVar.b(BrightRemindSetting.BRIGHT_REMIND, Integer.valueOf(this.f15904m));
        a(cVar);
        return cVar;
    }
}
