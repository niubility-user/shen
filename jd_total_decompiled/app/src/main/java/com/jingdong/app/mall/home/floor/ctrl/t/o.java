package com.jingdong.app.mall.home.floor.ctrl.t;

import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorEntity;
import com.jingdong.app.mall.home.xview.HomeXview;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.log.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class o extends f {

    /* renamed from: g  reason: collision with root package name */
    private com.jingdong.app.mall.home.floor.ctrl.g f9604g;

    /* renamed from: h  reason: collision with root package name */
    private HomeXview f9605h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f9606i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (o.this.f9605h != null) {
                o.this.f9605h.destroyXView();
                o.this.f9605h = null;
            }
        }
    }

    public o() {
        new AtomicBoolean(false);
        this.f9606i = true;
    }

    private void q() {
        com.jingdong.app.mall.home.o.a.f.E0(new a());
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void a(HomeWebFloorEntity homeWebFloorEntity, BaseActivity baseActivity) {
        super.a(homeWebFloorEntity, baseActivity);
        this.d = 100;
        com.jingdong.app.mall.home.floor.ctrl.g gVar = new com.jingdong.app.mall.home.floor.ctrl.g("icon");
        this.f9604g = gVar;
        HomeWebFloorEntity homeWebFloorEntity2 = this.f9551c;
        gVar.b(homeWebFloorEntity2.sourceValue, homeWebFloorEntity2.showTimes, homeWebFloorEntity2.showTimesDaily);
        com.jingdong.app.mall.home.o.a.f.G0(this);
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void destroy() {
        super.destroy();
        q();
        com.jingdong.app.mall.home.o.a.f.H0(this);
        this.f9604g = null;
        this.f9553f = 0;
        i.p().z(7);
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.j
    public int e() {
        return 7;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onError(int i2) {
        super.onError(i2);
        if (Log.D) {
            Log.d("PartXviewCtrl", "part XView onError, code=" + i2);
        }
    }

    public void onEventMainThread(MallFloorEvent mallFloorEvent) {
        HomeXview homeXview;
        String type = mallFloorEvent.getType();
        type.hashCode();
        if (type.equals("home_resume")) {
            HomeXview homeXview2 = this.f9605h;
            if (homeXview2 != null) {
                homeXview2.onResume();
            }
        } else if (type.equals("home_stop") && (homeXview = this.f9605h) != null) {
            homeXview.onStop();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onXViewDisplayed() {
        super.onXViewDisplayed();
        if (Log.D) {
            Log.d("PartXviewCtrl", "part XView onDisplayed...");
        }
        if (this.f9606i) {
            this.f9606i = false;
            this.f9604g.e();
            try {
                JDMtaUtils.sendCommonData(JdSdk.getInstance().getApplicationContext(), "Home_LocalXVIEW", this.f9551c.sourceValue, "", this, "", "", "", RecommendMtaUtils.Home_PageId);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onXVivewClosed() {
        super.onXVivewClosed();
        if (Log.D) {
            Log.d("PartXviewCtrl", "part XView onClosed...");
        }
    }
}
