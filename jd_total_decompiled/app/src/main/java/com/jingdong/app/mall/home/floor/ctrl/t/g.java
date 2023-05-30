package com.jingdong.app.mall.home.floor.ctrl.t;

import android.app.Activity;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import com.jingdong.app.mall.home.xview.HomeXview;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class g extends f {

    /* renamed from: g  reason: collision with root package name */
    private int f9554g;

    /* renamed from: h  reason: collision with root package name */
    private HomeWebFloorViewEntity f9555h;

    /* renamed from: i  reason: collision with root package name */
    private HomeXview f9556i;

    /* renamed from: j  reason: collision with root package name */
    private String f9557j;

    /* renamed from: k  reason: collision with root package name */
    private int f9558k;

    /* renamed from: l  reason: collision with root package name */
    private long f9559l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f9560m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ ViewGroup f9561g;

        a(ViewGroup viewGroup) {
            this.f9561g = viewGroup;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (g.this.f9556i == null) {
                g.this.f9556i = new HomeXview(this.f9561g.getContext());
            }
            g.this.f9556i.r(g.this.f9555h);
            HomeXview homeXview = g.this.f9556i;
            ViewGroup viewGroup = this.f9561g;
            g gVar = g.this;
            homeXview.configXView(viewGroup, gVar.f9552e, gVar);
            g.this.f9556i.startXView();
            com.jingdong.app.mall.home.o.a.f.G0(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {
        b() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (g.this.f9556i != null) {
                g.this.f9556i.destroyXView();
                g.this.f9556i = null;
            }
        }
    }

    private void t(boolean z) {
        if (i.p().n(10) == null) {
            return;
        }
        if (z) {
            this.f9554g++;
        }
        ViewGroup u = u();
        if (u != null) {
            n(u);
        }
    }

    private ViewGroup u() {
        Activity u0 = JDHomeFragment.u0();
        if (u0 == null) {
            return null;
        }
        View childAt = ((ViewGroup) u0.findViewById(16908290)).getChildAt(0);
        if (childAt instanceof ViewGroup) {
            return (ViewGroup) childAt;
        }
        return null;
    }

    private void w(String str, HomeWebFloorViewEntity homeWebFloorViewEntity, boolean z) {
        if (homeWebFloorViewEntity == null || !homeWebFloorViewEntity.checkUnLogin || TextUtils.isEmpty(LoginUserBase.getUserPin())) {
            if (this.f9552e == null) {
                super.m(str, homeWebFloorViewEntity);
                this.d = 50;
                this.f9557j = str;
                XViewEntity xViewEntity = new XViewEntity();
                this.f9552e = xViewEntity;
                xViewEntity.url = this.f9557j;
                xViewEntity.isIntercepted = true;
                xViewEntity.needAutoClose = false;
                xViewEntity.needAutoDisplay = false;
                t(z);
            } else if (!TextUtils.equals(str, this.f9557j)) {
                this.f9557j = str;
                this.f9552e.url = str;
                if (d()) {
                    r();
                } else {
                    t(z);
                }
            } else {
                t(z);
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void a(HomeWebFloorEntity homeWebFloorEntity, BaseActivity baseActivity) {
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void b() {
        super.b();
        if (this.f9556i == null || !i()) {
            return;
        }
        this.f9556i.displayXView();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public boolean c() {
        if (isShowing()) {
            HomeXview homeXview = this.f9556i;
            if (homeXview != null) {
                homeXview.closeXView();
                return true;
            }
            return true;
        }
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void destroy() {
        if (k() == 4) {
            this.f9560m = true;
            return;
        }
        super.destroy();
        com.jingdong.app.mall.home.o.a.f.H0(this);
        i.p().z(10);
        com.jingdong.app.mall.home.o.a.f.E0(new b());
        if (Log.D) {
            Log.d("CountdownXviewCtrl", "countDown XView destroy...");
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.j
    public int e() {
        return 10;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f
    public boolean i() {
        j n2 = i.p().n(3);
        if (n2 == null || n2.getPriority() > 50) {
            return true;
        }
        n2.destroy();
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f
    protected XView l() {
        return this.f9556i;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f
    public void m(String str, HomeWebFloorViewEntity homeWebFloorViewEntity) {
        this.f9554g = 0;
        this.f9555h = homeWebFloorViewEntity;
        w(str, homeWebFloorViewEntity, false);
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f
    public void n(ViewGroup viewGroup) {
        super.n(viewGroup);
        com.jingdong.app.mall.home.o.a.f.E0(new a(viewGroup));
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        HomeXview homeXview;
        String type = baseEvent.getType();
        type.hashCode();
        if (type.equals("adActivityOnClick") && this.f9553f == 4 && (homeXview = this.f9556i) != null) {
            homeXview.closeXView();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onStart() {
        super.onStart();
        this.f9559l = SystemClock.elapsedRealtime();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onXViewDisplayed() {
        super.onXViewDisplayed();
        com.jingdong.app.mall.home.o.a.f.m(this.a);
        if (this.f9555h != null) {
            com.jingdong.app.mall.home.r.c.d b2 = com.jingdong.app.mall.home.r.c.d.b("Home_UnregisteredGuideXviewExpo");
            b2.f(this.f9555h.srvJson);
            b2.d();
        }
        this.f9554g = 0;
        if (Log.D) {
            Log.d("CountdownXviewCtrl", "countDown XView onDisplay...");
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onXViewReady() {
        super.onXViewReady();
        this.f9559l = 0L;
        this.f9558k++;
        if (Log.D) {
            Log.d("CountdownXviewCtrl", "countDown XView onReady...");
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onXVivewClosed() {
        super.onXVivewClosed();
        if (this.f9560m) {
            this.f9560m = false;
            destroy();
        } else if (this.f9554g < 10) {
            w(this.f9557j, this.f9555h, true);
            if (Log.D) {
                Log.d("CountdownXviewCtrl", "countDown XView onClose...");
            }
        }
    }

    public void r() {
        HomeXview homeXview = this.f9556i;
        if (homeXview != null) {
            homeXview.closeXView();
        }
    }

    public long s() {
        if (this.f9558k == 0 || this.f9559l == 0) {
            return 0L;
        }
        return (SystemClock.elapsedRealtime() - this.f9559l) / 1000;
    }

    public String v() {
        return this.f9557j;
    }
}
