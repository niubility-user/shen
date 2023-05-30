package com.jingdong.app.mall.home.floor.ctrl.t;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import com.jingdong.app.mall.home.xview.HomeXview;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView.RetainXViewHelper;
import com.jingdong.common.XView.XViewCallBackAdapter;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.XView.XViewRequest;
import com.jingdong.common.kepler.KeplerJumpUtils;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class e {

    /* renamed from: j  reason: collision with root package name */
    private static boolean f9542j;
    private final AtomicBoolean a = new AtomicBoolean(false);
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private BaseActivity f9543c;
    private volatile XViewEntity d;

    /* renamed from: e  reason: collision with root package name */
    private volatile HomeXview f9544e;

    /* renamed from: f  reason: collision with root package name */
    private ViewGroup f9545f;

    /* renamed from: g  reason: collision with root package name */
    private String f9546g;

    /* renamed from: h  reason: collision with root package name */
    private HomeWebFloorViewEntity f9547h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f9548i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            e.this.l();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends HomeXview {
        b(Context context) {
            super(context);
        }

        @Override // com.jingdong.app.mall.home.xview.HomeXview, com.jingdong.common.XView.XView, com.jingdong.common.XView.IXView
        public boolean displayXView() {
            if (JDHomeFragment.O0() && !e.this.h()) {
                r(e.this.f9547h);
                HomeXview.n(e.this.f9547h == null ? "" : e.this.f9547h.sourceValue, e.this.f9547h != null ? e.this.f9547h.srvJson : "", "-100");
                new com.jingdong.app.mall.home.q.a("\u56de\u9000xview\u66dd\u5149", true, e.this.f9547h == null ? null : e.this.f9547h.expoLog).b();
                KeplerJumpUtils.setHasShownRetainView(true);
                return super.displayXView();
            }
            e.this.m();
            return false;
        }

        @Override // com.jingdong.app.mall.home.xview.HomeXview
        public void i() {
            super.i();
        }

        @Override // com.jingdong.app.mall.home.xview.HomeXview
        public void l() {
            super.l();
            if (e.this.f9547h != null) {
                new com.jingdong.app.mall.home.q.a("\u633d\u7559XView\u70b9\u51fb", e.this.f9547h.clkLog).b();
            }
        }

        @Override // com.jingdong.app.mall.home.xview.HomeXview, com.jingdong.common.XView.XView
        public void setCloseButtonVisible(int i2) {
            super.setCloseButtonVisible(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends XViewCallBackAdapter {
        c() {
        }

        @Override // com.jingdong.common.XView.XViewCallBackAdapter, com.jingdong.common.XView.XViewCallBack
        public void onXViewRequest(XViewRequest xViewRequest) {
            String str;
            super.onXViewRequest(xViewRequest);
            if (xViewRequest == null || (str = xViewRequest.requestParams) == null || !TextUtils.equals("click", JDJSON.parseObject(str).optString("action"))) {
                return;
            }
            HomeXview.m(e.this.f9547h.sourceValue, e.this.f9547h);
            e eVar = e.this;
            eVar.o(eVar.f9543c);
        }
    }

    public static boolean g() {
        return f9542j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h() {
        return com.jingdong.app.mall.home.floor.ctrl.t.c.s() || (!f9542j && KeplerJumpUtils.hasShownRetainView());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (this.f9543c == null || this.d == null || !this.f9548i || f9542j || h()) {
            return;
        }
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new a());
        } else if (this.f9544e == null || !TextUtils.equals(this.f9546g, this.d.url)) {
            this.f9546g = this.d.url;
            if (this.f9544e == null) {
                this.f9544e = new b(this.f9543c);
                this.f9544e.setCloseButtonVisible(8);
            }
            this.f9544e.configXView(this.f9545f, this.d, new c());
            this.f9544e.startXView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(Activity activity) {
        try {
            m();
            activity.moveTaskToBack(true);
        } finally {
            try {
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        this.b = 0L;
        this.d = null;
    }

    public boolean i(boolean z) {
        this.f9548i = z | this.f9548i;
        if (RetainXViewHelper.closeRetainXView()) {
            o(this.f9543c);
            return true;
        } else if (this.f9548i && !h()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (this.d != null && this.f9543c != null && this.f9544e != null && this.f9544e.isXViewReady()) {
                long j2 = this.b;
                if ((j2 <= 0 ? 9999L : elapsedRealtime - j2) >= 3000 || f9542j) {
                    this.b = elapsedRealtime;
                    if (f9542j) {
                        o(this.f9543c);
                    } else {
                        f9542j = true;
                        this.f9544e.displayXView();
                        com.jingdong.app.mall.home.o.a.f.m(this.a);
                    }
                    return true;
                }
                return false;
            }
            this.b = elapsedRealtime;
            return false;
        } else {
            m();
            return false;
        }
    }

    public void j() {
        if (f9542j) {
            m();
        }
    }

    public void k(boolean z, boolean z2) {
        this.f9548i = z | this.f9548i;
        if (z2) {
            return;
        }
        l();
    }

    void m() {
        if (this.f9544e != null) {
            this.f9544e.setAlpha(0.0f);
            this.f9544e.destroyXView();
            this.f9544e = null;
        }
        com.jingdong.app.mall.home.o.a.f.K0(this.a);
        f();
    }

    public void n(HomeWebFloorEntity homeWebFloorEntity, BaseActivity baseActivity) {
        if (f9542j || h() || homeWebFloorEntity == null || baseActivity == null) {
            return;
        }
        HomeWebFloorViewEntity firstEntity = homeWebFloorEntity.getFirstEntity();
        String url = firstEntity == null ? null : firstEntity.getUrl();
        if (firstEntity == null || TextUtils.isEmpty(url)) {
            return;
        }
        ViewGroup K = com.jingdong.app.mall.home.o.a.f.K();
        this.f9545f = K;
        if (K == null) {
            return;
        }
        if (TextUtils.equals("0", firstEntity.getJsonString("outward", "1"))) {
            this.f9548i = true;
        }
        this.f9547h = firstEntity;
        this.d = new XViewEntity();
        this.d.url = url;
        this.d.needAutoDisplay = false;
        this.f9543c = baseActivity;
        l();
    }
}
