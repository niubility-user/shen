package com.jingdong.app.mall.home.floor.bubble;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.bubble.a;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: l  reason: collision with root package name */
    private static final long f9257l = System.currentTimeMillis();

    /* renamed from: m  reason: collision with root package name */
    private static Handler f9258m = new Handler(Looper.getMainLooper());
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private long f9259c;
    private long d;

    /* renamed from: e  reason: collision with root package name */
    private String f9260e;

    /* renamed from: f  reason: collision with root package name */
    FloatBubbleLayout f9261f;

    /* renamed from: g  reason: collision with root package name */
    private final Context f9262g;
    private com.jingdong.app.mall.home.floor.bubble.a a = new com.jingdong.app.mall.home.floor.bubble.a("", "", -1);

    /* renamed from: h  reason: collision with root package name */
    private final AtomicBoolean f9263h = new AtomicBoolean(false);

    /* renamed from: i  reason: collision with root package name */
    private final AtomicBoolean f9264i = new AtomicBoolean(true);

    /* renamed from: j  reason: collision with root package name */
    private final AtomicBoolean f9265j = new AtomicBoolean(false);

    /* renamed from: k  reason: collision with root package name */
    private final AtomicInteger f9266k = new AtomicInteger(0);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements HttpGroup.OnCommonListener {

        /* renamed from: com.jingdong.app.mall.home.floor.bubble.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class RunnableC0285a implements Runnable {
            RunnableC0285a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f9263h.set(false);
                b.this.r();
                b.this.t(r2.a.f9245e, b.this.a.f9247g, b.this.a.f9246f);
                b.this.f9264i.set(true);
                b.this.f9266k.set(0);
                b.this.n(true, true);
            }
        }

        a() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            b.this.f9265j.set(false);
            if (httpResponse == null) {
                return;
            }
            b.this.a.j(httpResponse.getFastJsonObject());
            if (b.this.a.c()) {
                b.this.a.m();
                if (b.this.a.k()) {
                    b.this.f9263h.set(true);
                    b.f9258m.post(new RunnableC0285a());
                }
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            b.this.f9265j.set(false);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.floor.bubble.b$b  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class RunnableC0286b implements Runnable {
        RunnableC0286b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (b.this.f9261f.x()) {
                if (b.this.f9264i.getAndSet(false)) {
                    String str = b.this.a.f9248h;
                    b.this.a.b();
                }
                b.this.f9266k.getAndIncrement();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.f9261f.y();
        }
    }

    public b(FloatBubbleLayout floatBubbleLayout, Context context) {
        this.f9262g = context;
        this.f9261f = floatBubbleLayout;
        floatBubbleLayout.w(-585491942);
        t(Final.FIVE_SECOND, 2000L, Final.FIVE_SECOND);
        l();
    }

    private a.C0284a k(int i2) {
        try {
            com.jingdong.app.mall.home.floor.bubble.a aVar = this.a;
            if (aVar != null && aVar.a.size() > this.f9266k.get()) {
                return this.a.a.get(i2);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private void l() {
        int dip2px = DPIUtil.dip2px(10.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f9261f, "alpha", 0.0f, 1.0f);
        float f2 = -dip2px;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f9261f, "translationY", f2, 0.0f);
        animatorSet.setDuration(300L);
        animatorSet.playTogether(ofFloat, ofFloat2);
        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f9261f, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.f9261f, "translationY", 0.0f, f2);
        animatorSet2.setDuration(300L);
        animatorSet2.playTogether(ofFloat3, ofFloat4);
        this.f9261f.j(animatorSet, animatorSet2);
    }

    private void s() {
        if (TextUtils.isEmpty(this.a.h())) {
            return;
        }
        com.jingdong.app.mall.home.floor.bubble.a aVar = this.a;
        aVar.p("Home_AirBubbleExpo", aVar.h());
        this.a.e();
    }

    public void g() {
        s();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        this.a.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(a.C0284a c0284a) {
        this.f9261f.p(c0284a);
        new com.jingdong.app.mall.home.q.a("\u6c14\u6ce1\u70b9\u51fb", this.a.f9249i).b();
        this.a.d();
        this.a.l(this.f9262g, c0284a.d);
        this.a.o("Home_AirBubble", c0284a.c());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(a.C0284a c0284a) {
        this.f9261f.q(c0284a);
        new com.jingdong.app.mall.home.q.a("\u6c14\u6ce1\u66dd\u5149", this.a.f9250j).b();
        this.a.o("Home_AirBubbleClose", c0284a.c());
    }

    public void m() {
        if (!f.k0() || f.j0() || this.f9265j.get()) {
            return;
        }
        this.f9265j.set(true);
        r();
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId("bubbleComponent");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setEffect(0);
        httpSetting.putJsonParam("channel", this.f9260e);
        httpSetting.putJsonParam("fQueryStamp", f9257l + "");
        httpSetting.setListener(new a());
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(boolean z, boolean z2) {
        if (com.jingdong.app.mall.home.state.old.a.f()) {
            r();
        } else if (this.f9261f.o() || this.f9261f.m() || this.f9265j.get()) {
        } else {
            a.C0284a k2 = k(this.f9266k.get());
            if (k2 == null) {
                s();
                return;
            }
            r();
            FloatBubbleLayout floatBubbleLayout = this.f9261f;
            floatBubbleLayout.d(this.a.d, floatBubbleLayout.n());
            this.f9261f.k(k2, k(this.f9266k.get() + 1));
            f9258m.postDelayed(new RunnableC0286b(), z2 ? this.b : this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o() {
        f9258m.postDelayed(new c(), this.f9259c);
    }

    public void p(boolean z) {
        if (z) {
            s();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(String str, String str2, int i2) {
        this.f9260e = str;
        this.a = new com.jingdong.app.mall.home.floor.bubble.a(this.f9260e, str2, i2);
        m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean r() {
        if (this.f9263h.get()) {
            return false;
        }
        f9258m.removeCallbacksAndMessages(null);
        this.f9261f.u();
        return true;
    }

    public void t(long j2, long j3, long j4) {
        this.b = j2;
        this.f9259c = j3;
        this.d = j4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u(a.C0284a c0284a) {
        this.f9261f.r(c0284a);
        this.a.a(c0284a);
    }
}
