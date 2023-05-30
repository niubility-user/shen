package com.jingdong.app.mall.home.floor.ctrl.t;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.UiThread;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import com.jingdong.app.mall.home.xview.HomeXview;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.kepler.KeplerJumpUtils;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.unification.navigationbar.newbar.NavigationButton;
import com.jingdong.corelib.utils.Log;
import de.greenrobot.event.EventBus;

/* loaded from: classes4.dex */
public class n extends f {
    protected static Handler p = new Handler(Looper.getMainLooper());
    private static boolean q;
    private static boolean r;

    /* renamed from: g */
    protected HomeXview f9590g;

    /* renamed from: h */
    protected BaseFloatPriority f9591h;

    /* renamed from: i */
    private String f9592i;

    /* renamed from: j */
    private boolean f9593j;

    /* renamed from: k */
    private boolean f9594k;

    /* renamed from: l */
    protected int f9595l;

    /* renamed from: m */
    protected int f9596m;

    /* renamed from: n */
    protected NavigationButton f9597n;
    protected HomeWebFloorViewEntity o;

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ View f9598g;

        a(View view) {
            n.this = r1;
            this.f9598g = view;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            View view = this.f9598g;
            com.jingdong.app.mall.home.o.a.f.n(view);
            n.this.n((ViewGroup) view);
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ ViewGroup f9600g;

        b(ViewGroup viewGroup) {
            n.this = r1;
            this.f9600g = viewGroup;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void onError(Throwable th) {
            super.onError(th);
            n.this.destroy();
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            n.this.u(this.f9600g);
        }
    }

    /* loaded from: classes4.dex */
    public class c extends BaseFloatPriority {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str, int i2) {
            super(str, i2);
            n.this = r1;
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        protected void g(int i2) {
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        public void h() {
            n nVar = n.this;
            if (nVar.f9590g != null && !nVar.w(!nVar.f9594k)) {
                n.this.f9590g.displayXView();
            } else {
                n.this.destroy();
            }
        }
    }

    /* loaded from: classes4.dex */
    public class d extends HomeXview {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Context context) {
            super(context);
            n.this = r1;
        }

        @Override // com.jingdong.app.mall.home.xview.HomeXview, com.jingdong.common.XView.XView, com.jingdong.common.XView.IXView
        public boolean displayXView() {
            if (n.this.f9593j) {
                return super.displayXView();
            }
            if (n.this.v()) {
                m.c(n.this.o, "2");
                n.this.destroy();
                return false;
            }
            BaseFloatPriority baseFloatPriority = n.this.f9591h;
            if (baseFloatPriority == null || baseFloatPriority.a()) {
                if (!n.this.f9593j && com.jingdong.app.mall.home.o.a.h.d()) {
                    m.d(n.this.o, "2", com.jingdong.app.mall.home.o.a.h.a());
                    n.this.destroy();
                    return false;
                }
                BaseFloatPriority baseFloatPriority2 = n.this.f9591h;
                if (baseFloatPriority2 != null) {
                    baseFloatPriority2.k();
                }
                n.this.f9593j = true;
                com.jingdong.app.mall.home.o.a.h.j();
                return super.displayXView();
            }
            m.c(n.this.o, "2");
            n.this.f9591h.j();
            return false;
        }

        @Override // com.jingdong.app.mall.home.xview.HomeXview
        public void i() {
            super.i();
            n.this.A();
        }

        @Override // com.jingdong.app.mall.home.xview.HomeXview
        public void l() {
            super.l();
            n.this.z();
        }
    }

    private void B() {
        if (this.o != null) {
            new com.jingdong.app.mall.home.q.a("\u542f\u52a8XView\u66dd\u5149", true, this.o.expoLog).b();
        }
    }

    private void C() {
        com.jingdong.app.mall.home.o.a.f.H0(this);
        this.f9590g = null;
        this.f9552e = null;
        i.p().z(3);
    }

    public static void D(boolean z) {
        r = z;
    }

    private String r() {
        HomeWebFloorViewEntity homeWebFloorViewEntity;
        if (this.f9551c == null || (homeWebFloorViewEntity = this.o) == null) {
            return null;
        }
        return homeWebFloorViewEntity.getUrl();
    }

    public static boolean s() {
        return r;
    }

    public static boolean t() {
        return q;
    }

    public void A() {
        if (this.o != null) {
            new com.jingdong.app.mall.home.q.a("\u542f\u52a8XView\u5173\u95ed", this.o.closeLog).b();
        }
    }

    protected void E() {
        HomeWebFloorViewEntity homeWebFloorViewEntity;
        if (this.f9551c == null || this.f9590g == null || (homeWebFloorViewEntity = this.o) == null) {
            return;
        }
        this.f9590g.setBackgroundColor(com.jingdong.app.mall.home.floor.view.b.h.a.b(com.jingdong.app.mall.home.floor.common.i.m.j(homeWebFloorViewEntity.baseColor, 0), (this.o.transparency * 255) / 100));
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void a(HomeWebFloorEntity homeWebFloorEntity, BaseActivity baseActivity) {
        super.a(homeWebFloorEntity, baseActivity);
        this.f9596m = homeWebFloorEntity.xViewType;
        if (homeWebFloorEntity.isPassthrough()) {
            this.d = 100;
        } else {
            this.d = 20;
        }
        View childAt = ((ViewGroup) baseActivity.findViewById(16908290)).getChildAt(0);
        HomeWebFloorViewEntity launchEntity = this.f9551c.getLaunchEntity();
        this.o = launchEntity;
        if ((childAt instanceof ViewGroup) && launchEntity != null) {
            launchEntity.initLaunchType(JDHomeFragment.O0());
            com.jingdong.app.mall.home.floor.ctrl.guide.a.j().o();
            q = this.o.isLoginXView();
            if (this.o.conflictWithStay) {
                KeplerJumpUtils.setHasShownRetainView(true);
            }
            int f2 = com.jingdong.app.mall.home.o.a.l.f();
            p.postDelayed(new a(childAt), f2 > 0 ? f2 : 100L);
            return;
        }
        destroy();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public boolean c() {
        XViewEntity xViewEntity;
        if (isShowing() && (xViewEntity = this.f9552e) != null && xViewEntity.isIntercepted) {
            A();
            destroy();
            return true;
        }
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void destroy() {
        super.destroy();
        D(false);
        EventBus.getDefault().post(new com.jingdong.app.mall.home.floor.common.e("homePageXViewLaunchClose"));
        HomeXview homeXview = this.f9590g;
        if (homeXview != null) {
            homeXview.destroyXView();
        }
        q = false;
        BaseFloatPriority baseFloatPriority = this.f9591h;
        if (baseFloatPriority != null) {
            baseFloatPriority.b(true);
        }
        C();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.j
    public int e() {
        return 3;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f
    public boolean i() {
        return super.i();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f
    protected XView l() {
        return this.f9590g;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f
    public void n(ViewGroup viewGroup) {
        super.n(viewGroup);
        if (!i()) {
            destroy();
            return;
        }
        String r2 = r();
        if (TextUtils.isEmpty(r2)) {
            destroy();
            return;
        }
        if (Log.D) {
            Log.d("LaunchXviewCtrl", "launch XView startXView, url=" + r2);
        }
        this.f9592i = LoginUserBase.getUserPin();
        XViewEntity xViewEntity = new XViewEntity();
        this.f9552e = xViewEntity;
        xViewEntity.url = r2;
        xViewEntity.isIntercepted = !this.f9551c.isPassthrough();
        this.f9552e.needAutoDisplay = true;
        com.jingdong.app.mall.home.o.a.f.E0(new b(viewGroup));
        com.jingdong.app.mall.home.o.a.f.G0(this);
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onCloseButtonClicked() {
        super.onCloseButtonClicked();
        HomeXview.m(this.f9551c.sourceValue, this.o);
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onError(int i2) {
        super.onError(i2);
        destroy();
        m.c(this.o, "3");
        if (Log.D) {
            Log.d("LaunchXviewCtrl", "launch XView onError");
        }
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        HomeXview homeXview;
        HomeXview homeXview2;
        XViewEntity xViewEntity;
        HomeWebFloorViewEntity homeWebFloorViewEntity;
        String type = baseEvent.getType();
        type.hashCode();
        char c2 = '\uffff';
        switch (type.hashCode()) {
            case -1158331917:
                if (type.equals("homePageXViewDisplay")) {
                    c2 = 0;
                    break;
                }
                break;
            case -277321843:
                if (type.equals("home_resume")) {
                    c2 = 1;
                    break;
                }
                break;
            case 815832937:
                if (type.equals("homePageXViewClose")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1085080119:
                if (type.equals("adActivityOnClick")) {
                    c2 = 3;
                    break;
                }
                break;
            case 2118188898:
                if (type.equals("home_stop")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                XViewEntity xViewEntity2 = this.f9552e;
                if (xViewEntity2 == null || xViewEntity2.isIntercepted || (homeXview = this.f9590g) == null) {
                    return;
                }
                homeXview.onStop();
                return;
            case 1:
                HomeXview homeXview3 = this.f9590g;
                if (homeXview3 != null) {
                    homeXview3.onResume();
                    return;
                }
                return;
            case 2:
                XViewEntity xViewEntity3 = this.f9552e;
                if (xViewEntity3 == null || xViewEntity3.isIntercepted || (homeXview2 = this.f9590g) == null) {
                    return;
                }
                homeXview2.onResume();
                return;
            case 3:
                if (this.f9553f == 4 && (xViewEntity = this.f9552e) != null && xViewEntity.isIntercepted) {
                    destroy();
                    return;
                }
                return;
            case 4:
                if (this.f9590g != null) {
                    m.c(this.o, "1");
                    if (!this.f9593j && (homeWebFloorViewEntity = this.o) != null && homeWebFloorViewEntity.isLeaveClose()) {
                        destroy();
                        return;
                    }
                    HomeWebFloorViewEntity homeWebFloorViewEntity2 = this.o;
                    if (homeWebFloorViewEntity2 != null) {
                        homeWebFloorViewEntity2.initLaunchType(false);
                    }
                    this.f9590g.onStop();
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onXViewDisplayed() {
        super.onXViewDisplayed();
        if (w(!this.f9594k)) {
            m.c(this.o, "2");
            destroy();
            return;
        }
        if (this.f9591h != null && t()) {
            this.f9591h.l();
        }
        if (this.f9594k) {
            return;
        }
        this.f9594k = true;
        com.jingdong.app.mall.home.p.b.d.c.g().n();
        com.jingdong.app.mall.home.o.a.f.m(this.a);
        com.jingdong.app.mall.home.floor.ctrl.k.b(this.o);
        B();
        com.jingdong.app.mall.home.floor.ctrl.t.c.y();
        String str = this.f9551c.sourceValue;
        HomeWebFloorViewEntity homeWebFloorViewEntity = this.o;
        HomeXview.n(str, homeWebFloorViewEntity == null ? "" : homeWebFloorViewEntity.srvJson, homeWebFloorViewEntity == null ? "-100" : homeWebFloorViewEntity.getLaunchType());
        if (Log.D) {
            Log.d("LaunchXviewCtrl", "launch XView onDisplayed...");
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onXVivewClosed() {
        super.onXVivewClosed();
        destroy();
        com.jingdong.app.mall.home.p.b.d.c.g().q();
        if (Log.D) {
            Log.d("LaunchXviewCtrl", "launch XView onClosed");
        }
    }

    @UiThread
    public void u(ViewGroup viewGroup) {
        this.f9595l = com.jingdong.app.mall.home.floor.common.d.f9279g;
        if (v()) {
            m.c(this.o, "2");
            destroy();
            return;
        }
        HomeWebFloorEntity homeWebFloorEntity = this.f9551c;
        if (homeWebFloorEntity != null && !homeWebFloorEntity.isPassthrough()) {
            this.f9591h = new c("\u542f\u52a8XView", 15);
        } else {
            this.f9591h = null;
        }
        if (this.f9590g == null) {
            this.f9590g = new d(viewGroup.getContext());
        }
        this.f9590g.configXView(viewGroup, this.f9552e, this);
        this.f9590g.s(i.p().b.get());
        m.a(this.o);
        this.f9590g.r(this.o);
        this.f9590g.startXView();
        E();
    }

    public boolean v() {
        return w(true);
    }

    protected boolean w(boolean z) {
        HomeWebFloorViewEntity homeWebFloorViewEntity;
        if (!((this.f9594k || (homeWebFloorViewEntity = this.o) == null || !homeWebFloorViewEntity.isLeaveClose()) ? false : true)) {
            return this.f9595l != com.jingdong.app.mall.home.floor.common.d.f9279g || e.g() || com.jingdong.app.mall.home.floor.ctrl.t.c.isShowing() || (z && t() && !TextUtils.equals(this.f9592i, LoginUserBase.getUserPin()));
        }
        m.c(this.o, "1");
        return true;
    }

    public void x() {
        if (this.f9597n == null || !JDHomeFragment.Q0()) {
            return;
        }
        this.f9597n.showNavigationButtonEffect(1);
    }

    public void y() {
        if (this.f9597n == null || !JDHomeFragment.Q0()) {
            return;
        }
        this.f9597n.showNavigationButtonEffect(0);
    }

    public void z() {
        if (this.o != null) {
            new com.jingdong.app.mall.home.q.a("\u542f\u52a8XView\u70b9\u51fb", this.o.clkLog).b();
        }
    }
}
