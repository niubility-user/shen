package com.jingdong.app.mall.home;

import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.ctrl.l;
import com.jingdong.app.mall.home.floor.ctrl.m;
import com.jingdong.app.mall.home.floor.ctrl.o;
import com.jingdong.app.mall.home.floor.ctrl.r;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle;
import com.jingdong.app.mall.home.widget.HomePullRefreshRecyclerView;
import com.jingdong.app.mall.home.widget.HomeRecycleView;
import com.jingdong.app.mall.home.widget.HomeRecyclerAdapter;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: h */
    public static boolean f8559h = false;

    /* renamed from: i */
    public static int f8560i = 0;

    /* renamed from: j */
    public static int f8561j = 0;

    /* renamed from: k */
    public static int f8562k = 0;

    /* renamed from: l */
    public static int f8563l = 0;

    /* renamed from: m */
    public static int f8564m = 0;

    /* renamed from: n */
    public static boolean f8565n = false;
    private static boolean o = true;
    public static boolean p;
    public static String q;
    public static com.jingdong.app.mall.home.floor.ctrl.a r;
    public static final com.jingdong.app.mall.home.floor.ctrl.b s = new com.jingdong.app.mall.home.floor.ctrl.b();
    public static final com.jingdong.app.mall.home.floor.ctrl.c t = new com.jingdong.app.mall.home.floor.ctrl.c();
    public static com.jingdong.app.mall.home.r.a.c u = new com.jingdong.app.mall.home.r.a.c();
    static boolean v = false;
    public static int w = -1;
    public static int x = IconFloorEntity.BGCOLOR_DEFAULT;
    private static final Map<String, List<String>> y = new HashMap();
    private static final List<String> z = new ArrayList();
    r a = new r();
    private com.jingdong.app.mall.home.r.e.d b = null;

    /* renamed from: c */
    private com.jingdong.app.mall.home.r.e.h f8566c = null;
    private com.jingdong.app.mall.home.r.e.h d = null;

    /* renamed from: e */
    private com.jingdong.app.mall.home.r.e.h f8567e = null;

    /* renamed from: f */
    private com.jingdong.app.mall.home.r.e.h f8568f = null;

    /* renamed from: g */
    AtomicBoolean f8569g = new AtomicBoolean(false);

    /* renamed from: com.jingdong.app.mall.home.a$a */
    /* loaded from: classes4.dex */
    public class C0272a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ boolean f8570g;

        C0272a(a aVar, boolean z) {
            this.f8570g = z;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            a.u.o();
            com.jingdong.app.mall.home.floor.view.b.f.e.j().q(this.f8570g);
            com.jingdong.app.mall.home.p.b.c.a.r().w(this.f8570g);
        }
    }

    public static void D() {
        HomeSettingBridge.setShowTopLbsSetting(true);
    }

    public static void a(String str) {
        if (LoginUserBase.hasLogin()) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String userPin = LoginUserBase.getUserPin();
            Map<String, List<String>> map = y;
            List<String> list = map.get(userPin);
            if (list == null) {
                list = new ArrayList<>();
                map.put(userPin, list);
            }
            list.add(str);
            return;
        }
        z.add(str);
    }

    public static HomeRecycleView f() {
        HomePullRefreshRecyclerView h2 = h();
        if (h2 == null) {
            return null;
        }
        RecyclerView q2 = h2.q();
        if (q2 instanceof HomeRecycleView) {
            return (HomeRecycleView) q2;
        }
        return null;
    }

    public static HomePullRefreshRecyclerView h() {
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 == null) {
            return null;
        }
        return z0.p;
    }

    public static int j() {
        com.jingdong.app.mall.home.floor.ctrl.a aVar = r;
        if (aVar == null) {
            return 0;
        }
        return aVar.c();
    }

    public static String k() {
        return m() ? "10.0.0" : "9.0.0";
    }

    public static boolean m() {
        return o;
    }

    public static boolean n(String str) {
        if (!LoginUserBase.hasLogin()) {
            return z.contains(str);
        }
        List<String> list = y.get(LoginUserBase.getUserPin());
        return list != null && list.contains(str);
    }

    public static boolean o() {
        return HomeSettingBridge.isTopLbsOpen();
    }

    public static void s(boolean z2) {
        if (z2) {
            return;
        }
        z.clear();
    }

    private void x(HomeRecyclerAdapter homeRecyclerAdapter, HomeRecycleView homeRecycleView, int i2) {
        int itemCount;
        if (homeRecycleView == null || homeRecyclerAdapter == null || (itemCount = homeRecyclerAdapter.getItemCount()) <= 3) {
            return;
        }
        MallFloorEvent.g();
        homeRecycleView.v(true, itemCount - 3, i2);
    }

    public void A(com.jingdong.app.mall.home.r.e.h hVar) {
        this.f8568f = hVar;
    }

    public void B(com.jingdong.app.mall.home.r.e.d dVar) {
        this.b = dVar;
    }

    public void C(com.jingdong.app.mall.home.r.e.h hVar) {
        this.f8566c = hVar;
    }

    public void b(IHomeTitle iHomeTitle) {
        if (iHomeTitle == null) {
            return;
        }
        iHomeTitle.refreshPlanB(this.b);
        iHomeTitle.refreshSearchBoxStyle(this.f8566c);
        iHomeTitle.showPromotionIcon(this.d);
        iHomeTitle.showSearchBarLeftIcon(this.f8567e);
    }

    public void c() {
        com.jingdong.app.mall.home.shakeandshow.d.a();
    }

    public void d(View view, RelativeLayout relativeLayout, HomeRecycleView homeRecycleView, int i2) {
        r = new com.jingdong.app.mall.home.floor.ctrl.a(view, relativeLayout, homeRecycleView, i2);
    }

    public void e(com.jingdong.app.mall.home.r.e.h hVar, RelativeLayout relativeLayout) {
        com.jingdong.app.mall.home.shakeandshow.d g2;
        if (hVar == null || !JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext()) || (g2 = com.jingdong.app.mall.home.shakeandshow.d.g(hVar, relativeLayout)) == null || i.i()) {
            return;
        }
        g2.l();
    }

    public com.jingdong.app.mall.home.r.e.h g() {
        return this.f8568f;
    }

    public int i() {
        com.jingdong.app.mall.home.floor.ctrl.a aVar = r;
        if (aVar == null) {
            return 0;
        }
        return aVar.b();
    }

    public void l(int i2) {
        com.jingdong.app.mall.home.floor.view.b.f.e.j().m(i2);
        if (Log.D) {
            Log.i("AllHomeFloorCtrl-Splash", "homeListViewOnScrollStateChanged-splash:" + i2);
        }
        com.jingdong.app.mall.home.r.a.c cVar = u;
        if (cVar == null) {
            return;
        }
        if (i2 != 0) {
            cVar.G(1);
        } else {
            cVar.z();
        }
    }

    public void p(boolean z2) {
        com.jingdong.app.mall.home.shakeandshow.d f2 = com.jingdong.app.mall.home.shakeandshow.d.f();
        if (f2 != null) {
            if (z2) {
                f2.j();
            } else {
                f2.i();
            }
        }
    }

    public void q(boolean z2) {
        v = true;
        u.D();
        u.C(f8560i, f8562k);
        u.m();
        u.J();
        u.i();
        com.jingdong.app.mall.home.o.a.f.F0(new C0272a(this, z2), 200L);
        o.g().e();
        l.g().e();
        m.e().d();
    }

    public void r() {
        com.jingdong.app.mall.home.floor.ctrl.t.h.d().g();
        com.jingdong.app.mall.home.floor.view.b.f.e.j().k();
        com.jingdong.app.mall.home.p.b.c.a.r().t();
        s.e(true);
    }

    public void t() {
        com.jingdong.app.mall.home.floor.view.b.f.e.j().l();
        com.jingdong.app.mall.home.p.b.c.a.r().u();
        s.f();
        com.jingdong.app.mall.home.r.a.c cVar = u;
        if (cVar != null) {
            cVar.z();
        }
    }

    public void u() {
        com.jingdong.app.mall.home.floor.ctrl.a aVar = r;
        if (aVar != null) {
            aVar.a();
        }
        r = null;
    }

    public void v() {
        v = false;
        this.f8567e = null;
        this.d = null;
        this.f8566c = null;
        this.b = null;
        this.f8568f = null;
        this.f8569g.set(true);
        this.f8568f = null;
        com.jingdong.app.mall.home.shakeandshow.d f2 = com.jingdong.app.mall.home.shakeandshow.d.f();
        if (f2 != null) {
            f2.o();
        }
    }

    public void w(HomeRecyclerAdapter homeRecyclerAdapter, HomeRecycleView homeRecycleView) {
        x(homeRecyclerAdapter, homeRecycleView, f8560i + i());
    }

    public void y(com.jingdong.app.mall.home.r.e.h hVar) {
        this.f8567e = hVar;
    }

    public void z(com.jingdong.app.mall.home.r.e.h hVar) {
        this.d = hVar;
    }
}
