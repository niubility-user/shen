package com.jingdong.app.mall.home.floor.ctrl.t;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.ChannelInfo;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import com.jingdong.app.mall.home.xview.HomeXview;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView.XViewCallBackAdapter;
import com.jingdong.common.XView2.common.IXView2LayerObserver;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class c extends XViewCallBackAdapter {

    /* renamed from: j */
    private static ChannelInfo f9523j;

    /* renamed from: k */
    private static boolean f9524k;

    /* renamed from: l */
    private static boolean f9525l;

    /* renamed from: m */
    private static boolean f9526m;

    /* renamed from: n */
    private static boolean f9527n;
    private final AtomicBoolean a = new AtomicBoolean(false);
    private ViewGroup b;

    /* renamed from: c */
    private volatile com.jingdong.app.mall.home.floor.ctrl.t.b f9528c;
    private volatile HomeXview d;

    /* renamed from: e */
    private String f9529e;

    /* renamed from: f */
    private boolean f9530f;

    /* renamed from: g */
    private com.jingdong.app.mall.home.floor.ctrl.t.a f9531g;

    /* renamed from: h */
    private IXView2LayerObserver f9532h;

    /* renamed from: i */
    private static final Handler f9522i = new Handler(Looper.getMainLooper());
    private static long o = com.jingdong.app.mall.home.o.a.f.N("bk_last_launch_x_view_time", 0);
    private static long p = com.jingdong.app.mall.home.o.a.f.N("bk_last_bk_x_view_time", 0);

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ Context f9533g;

        /* renamed from: h */
        final /* synthetic */ ChannelInfo f9534h;

        /* renamed from: i */
        final /* synthetic */ ChannelInfo f9535i;

        a(Context context, ChannelInfo channelInfo, ChannelInfo channelInfo2) {
            c.this = r1;
            this.f9533g = context;
            this.f9534h = channelInfo;
            this.f9535i = channelInfo2;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            c.this.G(this.f9533g, this.f9534h, this.f9535i);
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.floor.ctrl.t.b {

        /* renamed from: m */
        final /* synthetic */ Context f9537m;

        /* loaded from: classes4.dex */
        class a extends com.jingdong.app.mall.home.o.a.b {

            /* renamed from: g */
            final /* synthetic */ com.jingdong.app.mall.home.floor.ctrl.t.d f9539g;

            a(com.jingdong.app.mall.home.floor.ctrl.t.d dVar) {
                b.this = r1;
                this.f9539g = dVar;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                if (!TextUtils.isEmpty(this.f9539g.b())) {
                    b bVar = b.this;
                    c.this.E(bVar.f9537m, this.f9539g);
                    return;
                }
                b bVar2 = b.this;
                c.this.D(bVar2.f9537m, this.f9539g);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(JDJSONObject jDJSONObject, Context context) {
            super(jDJSONObject);
            c.this = r1;
            this.f9537m = context;
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.t.b
        void k(com.jingdong.app.mall.home.floor.ctrl.t.d dVar) {
            if (c.this.n(this)) {
                com.jingdong.app.mall.home.o.a.f.E0(new a(dVar));
            }
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.t.b
        void l() {
            c.this.C();
        }
    }

    /* renamed from: com.jingdong.app.mall.home.floor.ctrl.t.c$c */
    /* loaded from: classes4.dex */
    public class C0293c extends IXView2LayerObserver {
        private boolean a = false;

        C0293c() {
            c.this = r1;
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void clickClose() {
            if (this.a) {
                return;
            }
            this.a = true;
            if (c.this.f9528c != null) {
                c.this.f9528c.q();
            }
            c.this.C();
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void jumpClose() {
            c.this.C();
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerAnimateEnd() {
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerRelease() {
            c.this.onXVivewClosed();
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerShowError() {
            c.this.onXVivewClosed();
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerShowSuccess() {
            c.this.onXViewDisplayed();
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void onClick(String str, int i2, String str2) {
            super.onClick(str, i2, str2);
            if (i2 != 1) {
                if (c.this.f9528c != null) {
                    c.this.f9528c.p(i2, str2);
                    return;
                }
                return;
            }
            clickClose();
        }
    }

    /* loaded from: classes4.dex */
    public class d extends HomeXview {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Context context) {
            super(context);
            c.this = r1;
        }

        @Override // com.jingdong.app.mall.home.xview.HomeXview
        public void i() {
            if (c.this.f9528c != null) {
                c.this.f9528c.q();
            }
            super.i();
        }
    }

    /* loaded from: classes4.dex */
    public class e implements HomeXview.f {
        e() {
            c.this = r1;
        }

        @Override // com.jingdong.app.mall.home.xview.HomeXview.f
        public void a() {
            if (c.this.f9528c != null) {
                c.this.f9528c.o();
            }
        }
    }

    /* loaded from: classes4.dex */
    public class f extends com.jingdong.app.mall.home.floor.ctrl.t.b {
        f(c cVar, JDJSONObject jDJSONObject) {
            super(jDJSONObject);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.t.b
        void k(com.jingdong.app.mall.home.floor.ctrl.t.d dVar) {
            boolean unused = c.f9525l = false;
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.t.b
        void l() {
            boolean unused = c.f9525l = false;
        }
    }

    static {
        com.jingdong.app.mall.home.floor.ctrl.t.b.s("init: \u4e0a\u6b21\u542f\u52a8xView\u663e\u793a\u65f6\u95f4 " + o + "   \u4e0a\u6b21\u56de\u9000xView\u663e\u793a\u65f6\u95f4 " + p);
    }

    private void B() {
        com.jingdong.app.mall.home.o.a.f.K0(this.a);
        f9522i.removeCallbacksAndMessages(null);
        this.f9528c = null;
        this.f9531g = null;
        f9526m = false;
        f9524k = false;
        if (this.f9532h != null) {
            XView2LayerObservableManager.getManager().unregisterXView2Observer(this.f9532h);
            q();
            this.f9532h = null;
        }
        this.f9529e = null;
        this.f9530f = true;
        if (this.d != null) {
            this.d.setAlpha(0.0f);
            this.d.destroyXView();
            this.d = null;
        }
    }

    public void D(Context context, com.jingdong.app.mall.home.floor.ctrl.t.d dVar) {
        if (this.d == null) {
            this.d = new d(context);
            this.d.p(new e());
        }
        this.d.configXView(this.b, dVar.a(), this);
        this.d.startXView();
    }

    public void E(Context context, com.jingdong.app.mall.home.floor.ctrl.t.d dVar) {
        this.f9529e = dVar.b();
        this.f9532h = new C0293c();
        XView2LayerObservableManager.getManager().registerXView2Observer(this.f9532h, this.f9529e);
        F(context);
    }

    private void F(Context context) {
        try {
            Bundle L0 = com.jingdong.app.mall.home.o.a.f.L0("showLayer", this.f9529e);
            if (com.jingdong.app.mall.home.o.a.k.w()) {
                com.jingdong.app.mall.home.o.a.f.J0(context, L0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void G(Context context, ChannelInfo channelInfo, ChannelInfo channelInfo2) {
        if (channelInfo2 != null) {
            f9523j = channelInfo2;
        }
        this.f9528c = new b(null, context);
        this.f9528c.t(this.f9531g, channelInfo, channelInfo2, false);
    }

    public static boolean isShowing() {
        return f9526m;
    }

    public boolean n(com.jingdong.app.mall.home.floor.ctrl.t.b bVar) {
        if (bVar != null && JDHomeFragment.Q0() && !com.jingdong.app.mall.home.floor.common.i.g.f9303m) {
            j n2 = i.p().n(1);
            if (n2 != null && n2.isShowing()) {
                com.jingdong.app.mall.home.floor.ctrl.t.b.s(" \u4e0b\u62c9xview\u663e\u793a \u4e0d\u7b26");
                C();
                return false;
            }
            j n3 = i.p().n(1);
            if (n3 != null && n3.isShowing()) {
                com.jingdong.app.mall.home.floor.ctrl.t.b.s(" \u542f\u52a8xview\u663e\u793a \u4e0d\u7b26");
                C();
                return false;
            }
            i.p().w();
            return true;
        }
        com.jingdong.app.mall.home.floor.ctrl.t.b.s(" \u57fa\u7840\u4fe1\u606f\u4e0d\u7b26");
        return false;
    }

    private boolean q() {
        if (TextUtils.isEmpty(this.f9529e) || this.f9530f) {
            return false;
        }
        com.jingdong.app.mall.home.o.a.f.L0("closeLayer", this.f9529e);
        this.f9530f = true;
        return true;
    }

    public static boolean r() {
        return f9524k;
    }

    public static boolean s() {
        return f9527n;
    }

    private void t() {
        BaseActivity baseActivity;
        this.b = com.jingdong.app.mall.home.o.a.f.K();
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (this.b == null || z0 == null || (baseActivity = z0.thisActivity) == null) {
            return;
        }
        u(baseActivity, null, null);
    }

    private void u(Context context, ChannelInfo channelInfo, ChannelInfo channelInfo2) {
        f9524k = true;
        f9522i.postDelayed(new a(context, channelInfo, channelInfo2), 100L);
    }

    public static void y() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        o = elapsedRealtime;
        com.jingdong.app.mall.home.o.a.f.z0("bk_last_launch_x_view_time", elapsedRealtime);
    }

    private void z(ChannelInfo channelInfo, ChannelInfo channelInfo2) {
        this.f9528c = new f(this, null);
        this.f9528c.t(this.f9531g, channelInfo, channelInfo2, true);
    }

    public boolean A(HomeWebFloorViewEntity homeWebFloorViewEntity) {
        if (!f9524k) {
            com.jingdong.app.mall.home.floor.ctrl.t.b.s(" \u56de\u9000xview\u672a\u8bf7\u6c42");
            return false;
        } else if (homeWebFloorViewEntity.backXViewFirst) {
            com.jingdong.app.mall.home.floor.ctrl.t.b.s(" \u4f18\u5148\u7ea7\u9ad8\u4e8e\u542f\u52a8XView");
            return true;
        } else {
            C();
            com.jingdong.app.mall.home.floor.ctrl.t.b.m();
            com.jingdong.app.mall.home.floor.ctrl.t.b.s(" \u4f18\u5148\u7ea7\u4f4e\u4e8e\u542f\u52a8XView");
            return false;
        }
    }

    void C() {
        try {
            B();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void o(Activity activity) {
        Intent intent;
        this.f9531g = null;
        if (activity == null || (intent = activity.getIntent()) == null) {
            return;
        }
        this.f9531g = TextUtils.equals("openXView", com.jingdong.app.mall.home.o.a.f.s(intent, "subType")) ? new com.jingdong.app.mall.home.floor.ctrl.t.a(intent) : null;
    }

    @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
    public void onXViewDisplayed() {
        com.jingdong.app.mall.home.o.a.f.m(this.a);
        if (this.f9528c != null) {
            this.f9528c.r();
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        p = elapsedRealtime;
        com.jingdong.app.mall.home.o.a.f.z0("bk_last_bk_x_view_time", elapsedRealtime);
        f9527n = true;
        f9526m = true;
    }

    @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
    public void onXViewReady() {
        super.onXViewReady();
        if (this.d == null || !n(this.f9528c)) {
            return;
        }
        this.d.displayXView();
    }

    @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
    public void onXVivewClosed() {
        C();
    }

    public void p() {
        if (!isShowing() && !f9524k) {
            com.jingdong.app.mall.home.floor.ctrl.t.a aVar = this.f9531g;
            if (aVar != null) {
                com.jingdong.app.mall.home.floor.ctrl.t.b.c(aVar);
                t();
                return;
            }
            ChannelInfo babelFirstInfo = ChannelInfo.getBabelFirstInfo();
            ChannelInfo channelInfo = ChannelInfo.getChannelInfo(2);
            if (com.jingdong.app.mall.home.floor.ctrl.t.b.f()) {
                if (com.jingdong.app.mall.home.floor.ctrl.t.b.j(babelFirstInfo, channelInfo, f9523j) && com.jingdong.app.mall.home.floor.ctrl.t.b.i(babelFirstInfo, channelInfo, p, o)) {
                    this.b = com.jingdong.app.mall.home.o.a.f.K();
                    JDHomeFragment z0 = JDHomeFragment.z0();
                    if (this.b == null || z0 == null || z0.thisActivity == null) {
                        return;
                    }
                    com.jingdong.app.mall.home.floor.ctrl.t.b.s(" \u8bf7\u6c42\u7b49\u5f85\u4e2d");
                    u(z0.thisActivity, babelFirstInfo, channelInfo);
                    return;
                }
                return;
            } else if (babelFirstInfo != null && channelInfo != null) {
                if (f9525l) {
                    com.jingdong.app.mall.home.floor.ctrl.t.b.s(" \u6b63\u5728\u8bf7\u6c42\u914d\u7f6e\u4e2d");
                    return;
                }
                com.jingdong.app.mall.home.floor.ctrl.t.b.s(" \u672c\u5730\u6ca1\u6709\u914d\u7f6e\u4fe1\u606f,\u5f00\u59cb\u8bf7\u6c42\u914d\u7f6e");
                com.jingdong.app.mall.home.floor.ctrl.t.b.d(babelFirstInfo.getInfo(), channelInfo.getInfo());
                f9525l = true;
                z(babelFirstInfo, channelInfo);
                return;
            } else {
                com.jingdong.app.mall.home.floor.ctrl.t.b.s(" \u672a\u76d1\u6d4b\u5230\u901a\u5929\u5854\u4fe1\u606f");
                return;
            }
        }
        com.jingdong.app.mall.home.floor.ctrl.t.b.s(" \u8bf7\u6c42\u6216\u663e\u793a\u4e2d");
    }

    public boolean v(boolean z) {
        if (q() || this.d != null) {
            C();
            return true;
        }
        return false;
    }

    public void w() {
        if (isShowing()) {
            return;
        }
        C();
    }

    public void x(boolean z, boolean z2) {
        p();
    }
}
