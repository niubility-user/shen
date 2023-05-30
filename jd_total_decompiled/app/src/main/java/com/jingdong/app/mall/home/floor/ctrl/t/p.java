package com.jingdong.app.mall.home.floor.ctrl.t;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.ctrl.s;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import com.jingdong.app.mall.home.pullrefresh.BaseLoadingView;
import com.jingdong.app.mall.home.pulltorefresh.JDHomeBaseLoadingView;
import com.jingdong.app.mall.home.pulltorefresh.JDHomeLoadingView;
import com.jingdong.app.mall.home.widget.HomePullRefreshRecyclerView;
import com.jingdong.app.mall.home.xview.HomeXview;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.jdtoast.ToastUtils;

/* loaded from: classes4.dex */
public class p extends f implements com.jingdong.app.mall.home.xview.b {

    /* renamed from: g  reason: collision with root package name */
    private s.a f9608g = new s.a();

    /* renamed from: h  reason: collision with root package name */
    private boolean f9609h;

    /* renamed from: i  reason: collision with root package name */
    private BaseActivity f9610i;

    /* renamed from: j  reason: collision with root package name */
    private HomeWebFloorViewEntity f9611j;

    /* renamed from: k  reason: collision with root package name */
    protected HomeXview f9612k;

    /* renamed from: l  reason: collision with root package name */
    private HomePullRefreshRecyclerView f9613l;

    /* renamed from: m  reason: collision with root package name */
    private JDHomeLoadingView f9614m;

    /* renamed from: n  reason: collision with root package name */
    private com.jingdong.app.mall.home.xview.a f9615n;
    private JumpEntity o;
    private boolean p;
    private boolean q;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ ViewGroup f9616g;

        a(ViewGroup viewGroup) {
            this.f9616g = viewGroup;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            p pVar = p.this;
            if (pVar.f9552e == null || pVar.f9551c.isPullJump() || TextUtils.isEmpty(p.this.f9552e.url)) {
                return;
            }
            p pVar2 = p.this;
            if (pVar2.f9612k == null) {
                pVar2.f9612k = new HomeXview(this.f9616g.getContext());
            }
            p pVar3 = p.this;
            pVar3.f9612k.configXView(this.f9616g, pVar3.f9552e, pVar3);
            p.this.f9612k.startXView();
            p.this.f9612k.setBackgroundColor(-1);
            p pVar4 = p.this;
            pVar4.f9612k.o(pVar4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {
        b() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            p.this.f9612k.displayXView();
            p.this.f9613l.T();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f9619g;

        c(String str) {
            this.f9619g = str;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            ToastUtils.showToast(p.this.f9610i, this.f9619g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.o.a.b {
        d() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            HomeXview homeXview = p.this.f9612k;
            if (homeXview != null) {
                homeXview.destroyXView();
                p.this.f9612k = null;
            }
        }
    }

    private void A() {
        com.jingdong.app.mall.home.xview.a aVar = this.f9615n;
        if (aVar != null && aVar.j()) {
            this.f9615n.g();
        }
        HomeXview homeXview = this.f9612k;
        if (homeXview != null) {
            homeXview.onStop();
            JDHomeLoadingView jDHomeLoadingView = this.f9614m;
            if (jDHomeLoadingView != null) {
                jDHomeLoadingView.S(null, null);
            }
        }
    }

    private void B() {
        JDHomeLoadingView jDHomeLoadingView;
        if (this.f9613l == null || (jDHomeLoadingView = this.f9614m) == null) {
            return;
        }
        C(jDHomeLoadingView);
        this.f9553f = 0;
    }

    private void C(JDHomeBaseLoadingView jDHomeBaseLoadingView) {
        if (jDHomeBaseLoadingView != null) {
            jDHomeBaseLoadingView.S(null, null);
        }
    }

    private void D() {
        com.jingdong.app.mall.home.o.a.f.E0(new c(JdSdk.getInstance().getApplication().getString(R.string.pull_to_show_home_webview_too_frequently)));
        HomePullRefreshRecyclerView homePullRefreshRecyclerView = this.f9613l;
        if (homePullRefreshRecyclerView != null) {
            homePullRefreshRecyclerView.T();
        }
    }

    private boolean q(HomeWebFloorEntity homeWebFloorEntity) {
        if (homeWebFloorEntity == null || this.f9551c == null || !d()) {
            return false;
        }
        HomeWebFloorViewEntity firstEntity = this.f9551c.getFirstEntity();
        HomeWebFloorViewEntity firstEntity2 = homeWebFloorEntity.getFirstEntity();
        return firstEntity != null && firstEntity2 != null && TextUtils.equals(this.f9551c.sourceValue, homeWebFloorEntity.sourceValue) && this.f9551c.moduleFunction == homeWebFloorEntity.moduleFunction && TextUtils.equals(firstEntity.img, firstEntity2.img) && TextUtils.equals(firstEntity.getJump().des, firstEntity2.getJump().des) && TextUtils.equals(v(firstEntity), v(firstEntity2)) && this.p == homeWebFloorEntity.isPullJump();
    }

    private void s() {
        HomeXview homeXview = this.f9612k;
        if (homeXview == null) {
            return;
        }
        if (this.f9551c.animationTime < 200) {
            homeXview.closeXView();
            return;
        }
        com.jingdong.app.mall.home.xview.a aVar = this.f9615n;
        if (aVar == null || !aVar.j()) {
            if (this.f9615n == null) {
                this.f9615n = new com.jingdong.app.mall.home.xview.a();
            }
            this.f9615n.i(this.f9613l, this.f9612k, this.f9551c.animationTime);
            this.f9615n.l();
        }
    }

    private JDHomeLoadingView t(HomePullRefreshRecyclerView homePullRefreshRecyclerView) {
        if (homePullRefreshRecyclerView == null) {
            return null;
        }
        BaseLoadingView m2 = homePullRefreshRecyclerView.m();
        if (m2 instanceof JDHomeLoadingView) {
            return (JDHomeLoadingView) m2;
        }
        return null;
    }

    private static com.jingdong.app.mall.home.pulltorefresh.a u(HomePullRefreshRecyclerView homePullRefreshRecyclerView) {
        if (homePullRefreshRecyclerView == null) {
            return null;
        }
        com.jingdong.app.mall.home.pullrefresh.a p = homePullRefreshRecyclerView.p();
        if (p instanceof com.jingdong.app.mall.home.pulltorefresh.a) {
            return (com.jingdong.app.mall.home.pulltorefresh.a) p;
        }
        return null;
    }

    private String v(HomeWebFloorViewEntity homeWebFloorViewEntity) {
        if (homeWebFloorViewEntity.getJump() == null || homeWebFloorViewEntity.getJump().params == null) {
            return null;
        }
        try {
            return JDJSON.parseObject(homeWebFloorViewEntity.getJump().params).getString("url");
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
                return null;
            }
            return null;
        }
    }

    private void w() {
        this.f9609h = false;
        if (!i()) {
            if (Log.D) {
                Log.d("PullXviewCtrl", "pull iew cannot showXV...");
            }
            destroy();
        } else if (i.p().n(1) == null || this.f9611j == null) {
        } else {
            if (Log.D) {
                Log.d("PullXviewCtrl", "pull XView set bg=" + this.f9611j.img);
            }
            this.o = this.f9611j.getJump();
            if (this.f9613l == null) {
                this.f9613l = com.jingdong.app.mall.home.a.h();
            }
            if (this.f9614m == null) {
                this.f9614m = t(this.f9613l);
            }
            JDHomeLoadingView jDHomeLoadingView = this.f9614m;
            if (jDHomeLoadingView != null) {
                jDHomeLoadingView.R(this.f9611j.wordsColor);
                this.f9614m.S(this.f9611j.img, this.o);
            }
            x(this.f9551c, this.f9613l);
            String v = v(this.f9611j);
            if (!TextUtils.isEmpty(v) && !this.p) {
                XViewEntity xViewEntity = new XViewEntity();
                this.f9552e = xViewEntity;
                xViewEntity.url = v;
                xViewEntity.isIntercepted = !this.f9551c.isPassthrough();
                this.f9552e.needAutoDisplay = false;
                View childAt = ((ViewGroup) this.f9610i.findViewById(16908290)).getChildAt(0);
                if (childAt instanceof ViewGroup) {
                    n((ViewGroup) childAt);
                    return;
                }
                return;
            }
            this.f9612k = null;
            this.f9553f = 0;
        }
    }

    private void x(HomeWebFloorEntity homeWebFloorEntity, HomePullRefreshRecyclerView homePullRefreshRecyclerView) {
        com.jingdong.app.mall.home.pulltorefresh.a u = u(homePullRefreshRecyclerView);
        if (u != null) {
            u.q(homeWebFloorEntity.refreshHeight, homeWebFloorEntity.resultHeight);
        }
    }

    private void z() {
        JDHomeLoadingView jDHomeLoadingView;
        HomeXview homeXview = this.f9612k;
        if (homeXview != null) {
            homeXview.onResume();
            HomeWebFloorViewEntity homeWebFloorViewEntity = this.f9611j;
            if (homeWebFloorViewEntity == null || (jDHomeLoadingView = this.f9614m) == null) {
                return;
            }
            jDHomeLoadingView.R(homeWebFloorViewEntity.wordsColor);
            this.f9614m.S(this.f9611j.img, y() ? null : this.f9611j.getJump());
            if (Log.D) {
                Log.d("PullXviewCtrl", "pull xview onResume set bg=" + this.f9611j.img);
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void a(HomeWebFloorEntity homeWebFloorEntity, BaseActivity baseActivity) {
        x(homeWebFloorEntity, this.f9613l);
        if (q(homeWebFloorEntity)) {
            this.f9551c = homeWebFloorEntity;
            if (this.q) {
                f();
                return;
            }
            return;
        }
        super.a(homeWebFloorEntity, baseActivity);
        this.p = this.f9551c.isPullJump();
        this.d = 50;
        this.f9610i = baseActivity;
        this.f9611j = this.f9551c.getFirstEntity();
        HomePullRefreshRecyclerView h2 = com.jingdong.app.mall.home.a.h();
        this.f9613l = h2;
        JDHomeLoadingView t = t(h2);
        this.f9614m = t;
        if (t == null || this.f9613l == null) {
            return;
        }
        if (y()) {
            this.f9614m.R(this.f9611j.wordsColor);
            this.f9614m.S(this.f9611j.img, null);
            return;
        }
        s.f(this.f9551c);
        w();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void b() {
        if (com.jingdong.app.mall.home.floor.ctrl.t.c.isShowing()) {
            return;
        }
        if (this.p && this.o != null) {
            com.jingdong.app.mall.home.r.c.a.s("Home_XVIEW", this.f9551c.sourceValue, com.jingdong.app.mall.home.r.c.b.c(this.f9611j.srvJson).put("opentype", "0").toString());
            com.jingdong.app.mall.home.floor.common.i.l.e(this.f9610i, this.o);
            if (this.f9613l == null) {
                this.f9613l = com.jingdong.app.mall.home.a.h();
            }
            HomePullRefreshRecyclerView homePullRefreshRecyclerView = this.f9613l;
            if (homePullRefreshRecyclerView != null) {
                homePullRefreshRecyclerView.g(true);
            }
        } else if (this.f9551c == null || this.f9612k == null) {
        } else {
            if (Log.D) {
                Log.d("PullXviewCtrl", "pull XView showXView success...");
            }
            if (this.f9609h) {
                com.jingdong.app.mall.home.o.a.f.E0(new b());
            } else {
                D();
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public boolean c() {
        if (isShowing()) {
            s();
            return true;
        }
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void destroy() {
        super.destroy();
        B();
        i.p().z(1);
        com.jingdong.app.mall.home.o.a.f.E0(new d());
        com.jingdong.app.mall.home.o.a.f.H0(this);
        if (Log.D) {
            Log.d("PullXviewCtrl", "pull XView destroy...");
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.j
    public int e() {
        return 1;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void f() {
        super.f();
        this.q = true;
        com.jingdong.app.mall.home.floor.ctrl.guide.a.j().m(this.f9551c, this.f9611j, this.f9613l, this.f9614m);
    }

    @Override // com.jingdong.app.mall.home.xview.b
    public void g() {
        s();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f
    public boolean i() {
        return s.a(this.f9551c.getWebViewList().size(), this.f9551c.showTimes, this.f9608g) && s.d(this.f9551c) > 0;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f
    protected XView l() {
        return this.f9612k;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f
    public void n(ViewGroup viewGroup) {
        super.n(viewGroup);
        com.jingdong.app.mall.home.o.a.f.E0(new a(viewGroup));
        com.jingdong.app.mall.home.o.a.f.G0(this);
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onCloseButtonClicked() {
        super.onCloseButtonClicked();
        HomeWebFloorEntity homeWebFloorEntity = this.f9551c;
        HomeXview.m(homeWebFloorEntity.sourceValue, homeWebFloorEntity.getLaunchEntity());
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onError(int i2) {
        super.onError(i2);
        this.f9609h = false;
        s.h();
        if (Log.D) {
            Log.d("PullXviewCtrl", "pull XView error...");
        }
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        HomeXview homeXview;
        String type = baseEvent.getType();
        type.hashCode();
        char c2 = '\uffff';
        switch (type.hashCode()) {
            case -277321843:
                if (type.equals("home_resume")) {
                    c2 = 0;
                    break;
                }
                break;
            case 1085080119:
                if (type.equals("adActivityOnClick")) {
                    c2 = 1;
                    break;
                }
                break;
            case 2118188898:
                if (type.equals("home_stop")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                z();
                return;
            case 1:
                if (this.f9553f != 4 || (homeXview = this.f9612k) == null) {
                    return;
                }
                homeXview.closeXView();
                return;
            case 2:
                A();
                return;
            default:
                return;
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onXViewDisplayed() {
        super.onXViewDisplayed();
        com.jingdong.app.mall.home.o.a.f.m(this.a);
        s.a aVar = this.f9608g;
        s.c(aVar.a, aVar.b, aVar.f9512c);
        com.jingdong.app.mall.home.r.c.a.s("Home_XVIEW", this.f9551c.sourceValue, com.jingdong.app.mall.home.r.c.b.c(this.f9611j.srvJson).put("opentype", "0").toString());
        if (Log.D) {
            Log.d("PullXviewCtrl", "pull XView onDisplayed...");
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onXViewReady() {
        this.f9553f = this.f9553f != 4 ? 3 : 4;
        HomeXview homeXview = this.f9612k;
        if (homeXview != null) {
            homeXview.k();
        }
        this.f9609h = true;
        if (Log.D) {
            Log.d("PullXviewCtrl", "pull XView onReady...");
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onXVivewClosed() {
        int i2 = this.f9553f;
        if (i2 == 0 || i2 == 1) {
            return;
        }
        super.onXVivewClosed();
        w();
        if (Log.D) {
            Log.d("PullXviewCtrl", "pull XView onClosed...");
        }
    }

    public boolean r() {
        if (com.jingdong.app.mall.home.floor.ctrl.t.c.isShowing()) {
            return false;
        }
        if (this.f9609h || this.p) {
            return true;
        }
        D();
        return false;
    }

    public boolean y() {
        return this.f9551c.moduleFunction == 0;
    }
}
