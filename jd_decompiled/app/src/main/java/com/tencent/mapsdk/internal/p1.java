package com.tencent.mapsdk.internal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.tencent.mapsdk.core.MapDelegate;
import com.tencent.mapsdk.internal.ba;
import com.tencent.mapsdk.internal.m1;
import com.tencent.mapsdk.internal.o1;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapContext;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import com.tencent.tencentmap.mapsdk.maps.model.MapViewType;

/* loaded from: classes9.dex */
public abstract class p1<C extends o1, M extends m1> implements MapDelegate<C, M, v1> {

    /* renamed from: g */
    private C f16967g;

    /* renamed from: h */
    private M f16968h;

    /* renamed from: i */
    private v1 f16969i;

    /* renamed from: j */
    private ViewGroup f16970j;

    /* renamed from: k */
    public final Context f16971k;

    /* renamed from: l */
    public TencentMapOptions f16972l;

    /* renamed from: m */
    private ba.e f16973m;

    public p1(Context context, TencentMapOptions tencentMapOptions, ViewGroup viewGroup) {
        this.f16971k = context;
        this.f16970j = viewGroup;
        this.f16972l = tencentMapOptions;
        if (this.f16973m == null) {
            this.f16973m = ba.b();
        }
        ba.b(this.f16973m);
        ma.a(new oa(context, tencentMapOptions));
        qa.h(pa.W);
        qa.h(pa.y);
        if (tencentMapOptions != null) {
            qa.b(pa.W, "options", (Object) qa.a(context, tencentMapOptions.toString()));
        }
    }

    public ViewGroup F() {
        return this.f16970j;
    }

    public void L() {
    }

    public void P() {
    }

    public void Q() {
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    /* renamed from: R */
    public M getMap() {
        return this.f16968h;
    }

    @Override // com.tencent.mapsdk.core.MapDelegate
    /* renamed from: S */
    public C getMapContext() {
        return this.f16967g;
    }

    public MapViewType T() {
        return this.f16967g.t();
    }

    public final M a(C c2) {
        return b(c2);
    }

    @Override // com.tencent.mapsdk.core.MapDelegate
    /* renamed from: a */
    public final v1 createMapView(C c2, ViewGroup viewGroup) {
        return b((p1<C, M>) c2, viewGroup);
    }

    public void a(float f2) {
        v1 v1Var = this.f16969i;
        if (v1Var != null) {
            v1Var.a(f2);
        }
    }

    public abstract M b(C c2);

    public abstract C b(Context context, TencentMapOptions tencentMapOptions);

    public abstract v1 b(C c2, ViewGroup viewGroup);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.tencent.mapsdk.core.MapDelegate
    public /* synthetic */ TencentMap createMap(TencentMapContext tencentMapContext) {
        return a((p1<C, M>) ((o1) tencentMapContext));
    }

    @Override // com.tencent.mapsdk.core.MapDelegate
    public v1 getMapRenderView() {
        return this.f16969i;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public boolean isOpaque() {
        v1 v1Var = this.f16969i;
        if (v1Var != null) {
            return v1Var.z();
        }
        return true;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public boolean isTouchable() {
        M m2 = this.f16968h;
        return m2 != null && m2.g();
    }

    public void k(boolean z) {
        C c2 = this.f16967g;
        if (c2 != null) {
            c2.b(z);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onCreated() {
        qa.h(pa.K);
        this.f16967g = b(this.f16971k, this.f16972l);
        z();
        qa.i(pa.K);
        qa.h(pa.L);
        this.f16969i = createMapView(this.f16967g, this.f16970j);
        Q();
        View view = this.f16969i.getView();
        view.setEnabled(true);
        view.setClickable(true);
        qa.i(pa.L);
        qa.h(pa.J);
        this.f16968h = a((p1<C, M>) this.f16967g);
        P();
        this.f16968h.a(this.f16967g.a(this.f16971k));
        L();
        qa.i(pa.J);
        qa.i(pa.y);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onDestroy() {
        ViewGroup viewGroup = this.f16970j;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        M m2 = this.f16968h;
        if (m2 != null) {
            m2.b();
        }
        C c2 = this.f16967g;
        if (c2 != null) {
            c2.E();
        }
        ba.a(this.f16973m);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onPause() {
        M m2 = this.f16968h;
        if (m2 != null) {
            m2.c();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onRestart() {
        M m2 = this.f16968h;
        if (m2 != null) {
            m2.a();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onResume() {
        M m2 = this.f16968h;
        if (m2 != null) {
            m2.e();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        v1 v1Var = this.f16969i;
        if (v1Var != null) {
            v1Var.onSizeChanged(i2, i3, i4, i5);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onStart() {
        M m2 = this.f16968h;
        if (m2 != null) {
            m2.d();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onStop() {
        M m2 = this.f16968h;
        if (m2 != null) {
            m2.f();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onSurfaceChanged(Object obj, int i2, int i3) {
        v1 v1Var = this.f16969i;
        if (v1Var != null) {
            v1Var.onSurfaceChanged(obj, i2, i3);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onUpdateOptions(TencentMapOptions tencentMapOptions) {
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void setOnTop(boolean z) {
        M m2 = this.f16968h;
        if (m2 != null) {
            m2.a(z);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void setOpaque(boolean z) {
        v1 v1Var = this.f16969i;
        if (v1Var != null) {
            v1Var.setMapOpaque(z);
        }
    }

    public void z() {
        C c2 = this.f16967g;
        if (c2 != null) {
            c2.D();
        }
    }
}
