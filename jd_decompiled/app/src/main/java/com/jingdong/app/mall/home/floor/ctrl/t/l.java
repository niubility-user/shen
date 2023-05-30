package com.jingdong.app.mall.home.floor.ctrl.t;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.ctrl.t.k;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import com.jingdong.app.mall.home.widget.HomeRecycleView;
import com.jingdong.app.mall.home.widget.HomeRecyclerAdapter;
import com.jingdong.app.mall.home.xview.HomeXview;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView2.XView2Manager;
import com.jingdong.common.XView2.common.IXView2LayerObserver;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;
import com.jingdong.common.XView2.entity.LocationEntity;
import com.jingdong.common.unification.navigationbar.NavigationTabLocationEntry;
import com.jingdong.common.unification.navigationbar.newbar.NavigationButton;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class l extends n {
    private static final String D = "l";
    private boolean A;
    private String B;
    private IXView2LayerObserver C;
    private int s;
    private int t = 0;
    private int u = 0;
    private int v = 0;
    private String w = "";
    private ValueAnimator x;
    private ValueAnimator y;
    private int z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ ViewGroup f9583g;

        a(ViewGroup viewGroup) {
            this.f9583g = viewGroup;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            l.this.U(this.f9583g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends BaseFloatPriority {
        b(l lVar, String str, int i2) {
            super(str, i2);
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        public boolean e() {
            return true;
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        protected void g(int i2) {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        public void h() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends IXView2LayerObserver {
        private boolean a = false;

        c() {
        }

        private LocationEntity a() {
            NavigationTabLocationEntry iconLocation;
            LocationEntity locationEntity = new LocationEntity();
            if (com.jingdong.app.mall.home.o.a.k.w()) {
                locationEntity.rectF = new RectF(com.jingdong.app.mall.home.floor.common.d.f9279g / 5.0f, com.jingdong.app.mall.home.floor.common.d.f9278f - com.jingdong.app.mall.home.floor.common.d.d(100), com.jingdong.app.mall.home.floor.common.d.f9279g / 2.5f, com.jingdong.app.mall.home.floor.common.d.f9278f);
                return locationEntity;
            }
            l.this.f9597n = com.jingdong.app.mall.home.o.a.f.E("new");
            NavigationButton navigationButton = l.this.f9597n;
            if (navigationButton == null || (iconLocation = navigationButton.getIconLocation()) == null) {
                return locationEntity;
            }
            l.this.y();
            locationEntity.rectF = new RectF(iconLocation.topX, iconLocation.topY, r3 + iconLocation.width, r5 + iconLocation.height);
            return locationEntity;
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void clickClose() {
            if (this.a) {
                return;
            }
            com.jingdong.app.mall.home.o.a.f.r0(l.D, " clickClose");
            this.a = true;
            l.this.A();
            HomeWebFloorViewEntity homeWebFloorViewEntity = l.this.o;
            HomeXview.m(homeWebFloorViewEntity.sourceValue, homeWebFloorViewEntity);
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public LocationEntity getLocationsByType(int i2) {
            k.f T;
            LocationEntity locationEntity = new LocationEntity();
            if (2 == i2 && (T = l.this.T()) != null && l.this.a0()) {
                l.this.X();
                locationEntity.rectF = T.a();
            }
            if (locationEntity.rectF == null) {
                locationEntity.LocationStatus = "1";
            }
            String str = l.D;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append(" linkedData: ");
            RectF rectF = locationEntity.rectF;
            sb.append(rectF == null ? " null" : rectF.toString());
            objArr[0] = sb.toString();
            com.jingdong.app.mall.home.o.a.f.r0(str, objArr);
            return locationEntity;
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public LocationEntity getPopLocation(int i2, int i3) {
            if (l.this.f9596m != 4) {
                return null;
            }
            LocationEntity a = a();
            RectF rectF = a.rectF;
            if (rectF == null) {
                a.LocationStatus = "1";
            } else {
                float f2 = rectF.top;
                rectF.set(((int) ((rectF.left + rectF.right) - r7)) >> 1, (int) ((f2 - r8) + ((rectF.bottom - f2) / 4.0f)), i2, i3);
            }
            return a;
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void jumpClose() {
            com.jingdong.app.mall.home.o.a.f.r0(l.D, " jumpClose");
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerAnimateEnd() {
            com.jingdong.app.mall.home.o.a.f.r0(l.D, " layerAnimateEnd");
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerRelease() {
            com.jingdong.app.mall.home.o.a.f.r0(l.D, " layerRelease");
            if (!l.this.A) {
                l.this.x();
            }
            l.this.W();
            l.this.onXVivewClosed();
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerShowError() {
            com.jingdong.app.mall.home.o.a.f.r0(l.D, " layerShowError");
            l.this.onXVivewClosed();
            l.this.d0("3");
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerShowSuccess() {
            com.jingdong.app.mall.home.o.a.f.r0(l.D, " layerShowSuccess");
            l.this.onXViewDisplayed();
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void onClick(String str, int i2, String str2) {
            super.onClick(str, i2, str2);
            boolean z = true;
            if (i2 != 1) {
                com.jingdong.app.mall.home.o.a.f.r0(l.D, " onClick clickCode= " + i2);
                com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(l.this.o.srvJson);
                boolean isEmpty = TextUtils.isEmpty(com.jingdong.app.mall.home.r.c.b.c(str2).optString("url")) ^ true;
                if (i2 != 3 && i2 != 2) {
                    z = false;
                }
                if (isEmpty) {
                    l.this.z();
                }
                c2.put("jumptype", isEmpty ? "0" : "1");
                c2.put("clickloc", z ? "0" : "1");
                com.jingdong.app.mall.home.r.c.a.s("Home_AutoXVIEWClick", l.this.o.sourceValue, c2.toString());
                return;
            }
            clickClose();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.o.a.b {
        d() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            l.this.V();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ HomeRecycleView f9586g;

        e(HomeRecycleView homeRecycleView) {
            this.f9586g = homeRecycleView;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (intValue - l.this.u > 0) {
                this.f9586g.scrollBy(0, intValue - l.this.u);
                l.this.u = intValue;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class f implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ HomeRecycleView f9588g;

        f(HomeRecycleView homeRecycleView) {
            this.f9588g = homeRecycleView;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (intValue - l.this.v > 0) {
                this.f9588g.scrollBy(0, -(intValue - l.this.v));
                l.this.v = intValue;
            }
        }
    }

    private int S(int i2, com.jingdong.app.mall.home.r.e.d dVar, com.jingdong.app.mall.home.r.e.f fVar, int i3) {
        if (TextUtils.isEmpty(dVar.a)) {
            return 0;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public k.f T() {
        try {
            return e0();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U(ViewGroup viewGroup) {
        if (!a0()) {
            onXVivewClosed();
            return;
        }
        this.B = this.o.xViewId;
        boolean xViewCanPopStatusByLayerId = XView2Manager.getInstance().getXViewCanPopStatusByLayerId(this.B);
        com.jingdong.app.mall.home.o.a.f.r0(D, "checkLayerShow prepared:" + xViewCanPopStatusByLayerId);
        if (!xViewCanPopStatusByLayerId) {
            if (this.z == 0) {
                d0("1");
            }
            int i2 = this.z;
            if (i2 < 5) {
                this.z = i2 + 1;
                com.jingdong.app.mall.home.o.a.f.F0(new a(viewGroup), 200L);
                return;
            }
            onXVivewClosed();
        } else if (com.jingdong.app.mall.home.o.a.h.d()) {
            d0("1");
            onXVivewClosed();
        } else {
            onXViewReady();
            b bVar = new b(this, "\u542f\u52a8\u89c6\u9891XView", 15);
            this.f9591h = bVar;
            if (!bVar.a()) {
                destroy();
                return;
            }
            this.C = new c();
            com.jingdong.app.mall.home.o.a.h.k();
            XView2LayerObservableManager.getManager().registerXView2Observer(this.C, this.B);
            f0(viewGroup.getContext());
            c0();
            d0("2");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V() {
        if (this.A) {
            return;
        }
        onXVivewClosed();
        com.jingdong.app.mall.home.o.a.f.L0("closeLayer", this.B);
        this.A = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W() {
        HomeRecycleView f2;
        ValueAnimator valueAnimator = this.y;
        if ((valueAnimator == null || !valueAnimator.isRunning()) && this.u > 0 && (f2 = com.jingdong.app.mall.home.a.f()) != null) {
            ValueAnimator valueAnimator2 = this.x;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
            }
            ValueAnimator ofInt = ValueAnimator.ofInt(0, this.u);
            this.y = ofInt;
            ofInt.setDuration(500L);
            this.y.setInterpolator(new LinearInterpolator());
            this.y.addUpdateListener(new f(f2));
            this.y.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X() {
        HomeRecycleView f2;
        if (this.t > 0 && (f2 = com.jingdong.app.mall.home.a.f()) != null) {
            ValueAnimator ofInt = ValueAnimator.ofInt(0, this.t);
            this.x = ofInt;
            ofInt.setDuration(500L);
            this.x.setInterpolator(new LinearInterpolator());
            this.x.addUpdateListener(new e(f2));
            this.x.start();
        }
    }

    private int Y(com.jingdong.app.mall.home.r.e.d dVar) {
        int floorHeight = dVar.getFloorHeight();
        return (!TextUtils.equals(dVar.b, "09B") || dVar.n()) ? floorHeight : floorHeight - com.jingdong.app.mall.home.floor.common.d.d(24);
    }

    private int Z(int i2, int i3) {
        int v = com.jingdong.app.mall.home.floor.common.i.m.v(this.b);
        this.s = v;
        double d2 = v;
        Double.isNaN(d2);
        double d3 = i2 + i3;
        if (d2 * 0.7d < d3) {
            double d4 = v;
            Double.isNaN(d4);
            Double.isNaN(d3);
            this.t = (int) (d3 - (d4 * 0.7d));
            double d5 = v;
            Double.isNaN(d5);
            double d6 = i3;
            Double.isNaN(d6);
            return (int) ((d5 * 0.7d) - d6);
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a0() {
        if (!JDHomeFragment.O0() || this.A || v()) {
            return false;
        }
        if (this.f9596m == 3) {
            com.jingdong.app.mall.home.r.e.h lastModel = t.FLOOR_CATEGORY.getLastModel();
            return lastModel != null && lastModel.Y == s.f9357c;
        }
        return true;
    }

    private void b0(IXView2LayerObserver iXView2LayerObserver) {
        this.A = true;
        XView2LayerObservableManager.getManager().unregisterXView2Observer(iXView2LayerObserver);
    }

    private void c0() {
        com.jingdong.app.mall.home.o.a.f.F0(new d(), 60000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d0(String str) {
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(this.o.srvJson);
        c2.a("response", str);
        com.jingdong.app.mall.home.r.c.a.y("Home_AutoXVIEW_Load", this.o.sourceValue, c2.toString());
    }

    private k.f e0() {
        List<com.jingdong.app.mall.home.r.e.d> list;
        int floorHeight;
        int S;
        List<com.jingdong.app.mall.home.r.e.d> list2;
        int i2;
        int i3;
        int i4;
        l lVar = this;
        HomeRecycleView f2 = com.jingdong.app.mall.home.a.f();
        k.f fVar = null;
        if (f2 == null || !(f2.getAdapter() instanceof HomeRecyclerAdapter)) {
            return null;
        }
        List<com.jingdong.app.mall.home.r.e.d> dataList = ((HomeRecyclerAdapter) f2.getAdapter()).getDataList();
        if (dataList == null || dataList.size() < 1) {
            return null;
        }
        if (DPIUtil.getDensity() == 0.0f) {
            return null;
        }
        k.f fVar2 = null;
        int i5 = 0;
        int i6 = 0;
        while (i5 < dataList.size()) {
            com.jingdong.app.mall.home.r.e.d dVar = dataList.get(i5);
            if (dVar != null) {
                if (dVar.mParentModel != null && !dVar.q()) {
                    int i7 = dVar.mParentModel.d;
                    com.jingdong.app.mall.home.r.e.d r = dVar.r();
                    boolean o = r.o();
                    int d2 = i7 + (o ? com.jingdong.app.mall.home.floor.common.d.d(12) : 0);
                    int i8 = 750 - (d2 << 1);
                    if (!r.g(lVar.w)) {
                        i6 += r.getFloorHeight();
                    } else {
                        int size = r.f10682c.size();
                        int i9 = d2;
                        int i10 = 0;
                        while (i10 < size) {
                            com.jingdong.app.mall.home.r.e.f fVar3 = r.f10682c.get(i10);
                            if (fVar3 == null || (S = lVar.S(i10, r, fVar3, i8)) < 0) {
                                return fVar;
                            }
                            if (!TextUtils.equals(lVar.w, fVar3.s())) {
                                list2 = dataList;
                                i2 = i10;
                                i3 = size;
                                i4 = i8;
                                i9 += S;
                            } else if (i9 + S > 750) {
                                return fVar;
                            } else {
                                int Y = lVar.Y(r) - (o ? com.jingdong.app.mall.home.floor.common.d.d(6) : 0);
                                int Z = lVar.Z(i6, Y);
                                int d3 = com.jingdong.app.mall.home.floor.common.d.d(i9);
                                int d4 = com.jingdong.app.mall.home.floor.common.d.d(S);
                                double d5 = lVar.t;
                                list2 = dataList;
                                double d6 = lVar.s;
                                Double.isNaN(d6);
                                int i11 = d5 > d6 * 0.3d ? 600 : 300;
                                i2 = i10;
                                i3 = size;
                                i4 = i8;
                                fVar2 = new k.f(d3, Z, d4, Y, i6, i11);
                            }
                            i10 = i2 + 1;
                            lVar = this;
                            size = i3;
                            i8 = i4;
                            dataList = list2;
                            fVar = null;
                        }
                        list = dataList;
                        if (r.q == t.FLOOR_RECOMMEND) {
                            break;
                        }
                        floorHeight = r.getFloorHeight();
                    }
                } else {
                    list = dataList;
                    floorHeight = dVar.getFloorHeight();
                }
                i6 += floorHeight;
                i5++;
                fVar = null;
                lVar = this;
                dataList = list;
            }
            list = dataList;
            i5++;
            fVar = null;
            lVar = this;
            dataList = list;
        }
        return fVar2;
    }

    private void f0(Context context) {
        try {
            com.jingdong.app.mall.home.o.a.f.r0(D, " startLayer");
            Bundle L0 = com.jingdong.app.mall.home.o.a.f.L0("showLayer", this.B);
            this.f9591h.l();
            if (com.jingdong.app.mall.home.o.a.k.w()) {
                com.jingdong.app.mall.home.o.a.f.J0(context, L0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n, com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void a(HomeWebFloorEntity homeWebFloorEntity, BaseActivity baseActivity) {
        super.a(homeWebFloorEntity, baseActivity);
        this.d = 20;
        this.w = String.valueOf(homeWebFloorEntity.bindModule);
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n, com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public boolean c() {
        if (this.A || !isShowing()) {
            return false;
        }
        V();
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n, com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void destroy() {
        super.destroy();
        try {
            if (this.C != null) {
                com.jingdong.app.mall.home.o.a.f.r0(D, " destroy");
                b0(this.C);
                this.C = null;
            }
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.o(e2.getMessage());
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void h() {
        super.h();
        V();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n, com.jingdong.app.mall.home.floor.ctrl.t.f
    public void n(ViewGroup viewGroup) {
        this.f9595l = com.jingdong.app.mall.home.floor.common.d.f9279g;
        if (!v() && Build.VERSION.SDK_INT >= 23) {
            HomeWebFloorViewEntity launchEntity = this.f9551c.getLaunchEntity();
            if (a0() && launchEntity != null) {
                com.jingdong.app.mall.home.o.a.f.G0(this);
                this.o = launchEntity;
                U(viewGroup);
                return;
            }
            onXVivewClosed();
            return;
        }
        onXVivewClosed();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n
    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent instanceof MallFloorEvent) {
            String type = baseEvent.getType();
            type.hashCode();
            char c2 = '\uffff';
            switch (type.hashCode()) {
                case 818672077:
                    if (type.equals("home_on_scroll")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 1069086460:
                    if (type.equals("home_pull_down")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 1236015766:
                    if (type.equals("home_pause")) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    if (this.f9596m == 3) {
                        V();
                        return;
                    }
                    return;
                case 1:
                case 2:
                    V();
                    return;
                default:
                    return;
            }
        }
    }
}
