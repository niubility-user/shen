package com.jingdong.app.mall.home.floor.ctrl.t;

import android.text.TextUtils;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.common.XView2.XView2Manager;
import com.jingdong.common.XView2.common.IXView2LayerObserver;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class q {
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f9622c;
    private final String d;

    /* renamed from: e  reason: collision with root package name */
    private final String f9623e;

    /* renamed from: f  reason: collision with root package name */
    private final String f9624f;

    /* renamed from: g  reason: collision with root package name */
    private final com.jingdong.app.mall.home.r.b.c f9625g;
    private final String a = q.class.getSimpleName();

    /* renamed from: h  reason: collision with root package name */
    private final AtomicBoolean f9626h = new AtomicBoolean(false);

    /* renamed from: i  reason: collision with root package name */
    private final IXView2LayerObserver f9627i = new a();

    /* loaded from: classes4.dex */
    class a extends IXView2LayerObserver {
        private boolean a = false;

        a() {
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void clickClose() {
            if (this.a) {
                return;
            }
            this.a = true;
            q.this.d();
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void jumpClose() {
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerAnimateEnd() {
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerRelease() {
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerShowError() {
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerShowSuccess() {
            q.this.h();
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void onClick(String str, int i2, String str2) {
            if (i2 == 1) {
                clickClose();
            } else if (true ^ TextUtils.isEmpty(com.jingdong.app.mall.home.r.c.b.c(str2).optString("url"))) {
                q.this.e();
            }
        }
    }

    public q(com.jingdong.app.mall.home.r.b.c cVar, com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        this.f9625g = cVar;
        String b = hVar.b("xviewId", "");
        this.d = b;
        this.f9623e = hVar.b("expoJson", "");
        this.f9624f = hVar.b("expoLog", "");
        int g2 = com.jingdong.app.mall.home.n.h.c.g(hVar.b("maxShowTimes", "1"));
        g();
        if (TextUtils.isEmpty(b) || !com.jingdong.app.mall.home.floor.view.b.f.d.f("home_Auto_Jump", g2)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            this.f9622c = jSONObject;
            jSONObject.put(XView2Constants.LAYER_ID, b);
        } catch (Exception e2) {
            this.f9622c = null;
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
    }

    private void g() {
        com.jingdong.app.mall.home.r.c.d b = com.jingdong.app.mall.home.r.c.d.b("Home_NearbyLoadingRequest");
        b.f(this.f9623e);
        b.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        new com.jingdong.app.mall.home.q.a("\u81ea\u52a8\u8df3\u8f6c\u66dd\u5149", true, this.f9624f).b();
        com.jingdong.app.mall.home.r.c.d b = com.jingdong.app.mall.home.r.c.d.b("Home_NearbyLoadingExpo");
        b.f(this.f9623e);
        b.d();
    }

    public void f() {
        com.jingdong.app.mall.home.r.c.d b = com.jingdong.app.mall.home.r.c.d.b("Home_NearbyLoadingBlock");
        b.f(this.f9623e);
        b.d();
    }

    public void i() {
        if (this.f9626h.get()) {
            this.f9626h.set(false);
            j();
        }
    }

    public void j() {
        if (JDHomeFragment.O0() && com.jingdong.app.mall.home.i.f() <= 1 && com.jingdong.app.mall.home.i.g() <= 0) {
            if (com.jingdong.app.mall.home.i.i()) {
                this.f9626h.set(true);
                return;
            }
            this.f9626h.set(false);
            if (!XView2Manager.getInstance().getXViewCanPopStatus(this.f9622c)) {
                com.jingdong.app.mall.home.o.a.f.r0(this.a, "\u672a\u51c6\u5907\u597d\u8d44\u6e90\uff1a " + this.b + this.f9622c);
                int i2 = this.b;
                if (i2 > 5) {
                    return;
                }
                this.b = i2 + 1;
                return;
            }
            com.jingdong.app.mall.home.floor.view.b.f.d.b("home_Auto_Jump", Integer.MAX_VALUE, true);
            com.jingdong.app.mall.home.o.a.f.r0(this.a, "\u8c03\u7528 startXView2\uff1a " + this.f9622c);
            XView2LayerObservableManager.getManager().registerXView2Observer(this.f9627i, this.d);
            this.f9625g.w("showLayer", this.f9622c, null);
            return;
        }
        com.jingdong.app.mall.home.o.a.f.r0(this.a, "\u9996\u9875\u4e0d\u53ef\u89c1" + this.f9622c + " InitCount : " + com.jingdong.app.mall.home.i.f() + " PauseCount : " + com.jingdong.app.mall.home.i.f());
    }
}
