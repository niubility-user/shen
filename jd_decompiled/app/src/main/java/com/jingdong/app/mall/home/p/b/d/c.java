package com.jingdong.app.mall.home.p.b.d;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.ctrl.t.j;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: f  reason: collision with root package name */
    private static final Handler f10520f = new Handler(Looper.getMainLooper());

    /* renamed from: g  reason: collision with root package name */
    private static final AtomicBoolean f10521g = new AtomicBoolean(false);

    /* renamed from: h  reason: collision with root package name */
    static final AtomicBoolean f10522h;

    /* renamed from: i  reason: collision with root package name */
    static final AtomicBoolean f10523i;
    private final MutableLiveData<com.jingdong.app.mall.home.p.b.d.d> a;
    private volatile Pair<View, BaseModel> b;

    /* renamed from: c  reason: collision with root package name */
    private volatile Pair<View, BaseModel> f10524c;
    private volatile boolean d;

    /* renamed from: e  reason: collision with root package name */
    private long f10525e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            c cVar = c.this;
            cVar.p(cVar.f10524c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends e {
        b(c cVar, Pair pair, MutableLiveData mutableLiveData) {
            super(pair, mutableLiveData);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.app.mall.home.p.b.d.e
        public void d(JDJSONObject jDJSONObject) {
            if (c.f10522h.get()) {
                return;
            }
            super.d(jDJSONObject);
            c.f10521g.set(false);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.app.mall.home.p.b.d.e
        public void e() {
            super.e();
            c.f10521g.set(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.p.b.d.c$c  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class C0314c extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f10527g;

        C0314c(c cVar, View view) {
            this.f10527g = view;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            m.K(this.f10527g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class d {
        static c a = new c(null);
    }

    static {
        f10522h = new AtomicBoolean(Build.VERSION.SDK_INT < 21);
        f10523i = new AtomicBoolean(false);
    }

    /* synthetic */ c(a aVar) {
        this();
    }

    public static c g() {
        return d.a;
    }

    private boolean i(BaseModel baseModel, boolean z) {
        h d2;
        if (baseModel == null || f10522h.get() || (d2 = baseModel.o().d()) == null || d2.Z) {
            return false;
        }
        f e2 = baseModel.e();
        int jsonInt = e2 == null ? 0 : e2.getJsonInt("skuFrameNum");
        if (jsonInt == 1 || jsonInt == 2) {
            return false;
        }
        String f2 = baseModel.f("clickReverse");
        String f3 = baseModel.f("stayReverse");
        boolean equals = TextUtils.equals("1", f2);
        return z ? equals : equals || TextUtils.equals("1", f3);
    }

    public void c(View view, BaseModel baseModel) {
        if (i(baseModel, true)) {
            this.b = new Pair<>(view, baseModel);
        }
    }

    public void d() {
        if (f10522h.get() || f10521g.get() || this.d || i.i() || f10523i.get()) {
            return;
        }
        if (this.b != null) {
            p(this.b);
        } else {
            q();
        }
    }

    public void e() {
        this.b = null;
        this.f10524c = null;
        f10520f.removeCallbacksAndMessages(null);
    }

    public void f(BaseModel baseModel, View view) {
        if (baseModel == null || !baseModel.r()) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.F0(new C0314c(this, view), 600L);
    }

    public void h(JDJSONObject jDJSONObject, boolean z) {
        this.d = z;
        this.f10525e = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "stayReverseSeconds", 4) * 1000;
        e();
    }

    public void j(View view, BaseModel baseModel, Observer<com.jingdong.app.mall.home.p.b.d.d> observer) {
        if (view == null) {
            return;
        }
        view.setScaleX(1.0f);
        View findViewById = view.findViewById(R.id.home_card_b_side);
        if (findViewById != null) {
            m.K(findViewById);
        }
        if (observer == null || baseModel.u() || !i(baseModel, false)) {
            return;
        }
        Context context = view.getContext();
        if (context instanceof ComponentActivity) {
            String f2 = baseModel.f("stayReverse");
            if (this.f10524c == null && TextUtils.equals(f2, "1")) {
                this.f10524c = new Pair<>(view, baseModel);
            }
            o(observer);
            baseModel.H(true);
            this.a.observe((ComponentActivity) context, observer);
            d();
        }
    }

    public void k(boolean z) {
        f10523i.set(z);
        if (z) {
            f10520f.removeCallbacksAndMessages(null);
        } else {
            d();
        }
    }

    public void l(boolean z) {
        if (z) {
            return;
        }
        d();
    }

    public void m() {
        f10520f.removeCallbacksAndMessages(null);
    }

    public void n() {
        f10520f.removeCallbacksAndMessages(null);
    }

    public void o(Observer<com.jingdong.app.mall.home.p.b.d.d> observer) {
        this.a.removeObserver(observer);
    }

    public void p(Pair<View, BaseModel> pair) {
        if (f10522h.get()) {
            return;
        }
        AtomicBoolean atomicBoolean = f10521g;
        if (atomicBoolean.get() || this.d || i.i() || f10523i.get() || pair == null || !m.x((View) pair.first)) {
            return;
        }
        atomicBoolean.set(true);
        new b(this, pair, this.a).f();
    }

    public void q() {
        JDHomeFragment z0;
        Handler handler = f10520f;
        handler.removeCallbacksAndMessages(null);
        if (this.f10524c == null || this.f10525e <= 0 || i.i() || f10523i.get() || !JDHomeFragment.O0()) {
            return;
        }
        j n2 = com.jingdong.app.mall.home.floor.ctrl.t.i.p().n(1);
        if ((n2 == null || !n2.isShowing()) && (z0 = JDHomeFragment.z0()) != null && z0.F0() <= com.jingdong.app.mall.home.floor.common.d.d(t.ICON.mFloorHeight)) {
            handler.postDelayed(new a(), this.f10525e);
        }
    }

    private c() {
        this.a = new MutableLiveData<>();
        this.d = true;
    }
}
