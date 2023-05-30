package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.ViewGroup;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.core.MapDelegate;
import com.tencent.mapsdk.internal.fj;
import com.tencent.mapsdk.internal.h1;
import com.tencent.mapsdk.vector.VectorMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.OverSeaTileProvider;
import com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListener;

/* loaded from: classes9.dex */
public interface a1 extends MapDelegate<qc, VectorMap, v1>, fj.n, le, u4, TencentMap.OnIndoorStateChangeListener {
    public static final int a = 0;
    public static final int b = 1;

    qc A();

    @Deprecated
    boolean B();

    int C();

    boolean D();

    void E();

    ViewGroup F();

    boolean G();

    int H();

    String I();

    CameraPosition J();

    void K();

    boolean M();

    void N();

    boolean O();

    ye a(String str);

    void a();

    void a(int i2, int i3);

    void a(Context context, TencentMapOptions tencentMapOptions);

    void a(Handler handler, Bitmap.Config config, int i2);

    void a(b5 b5Var);

    void a(c1 c1Var);

    void a(ce ceVar);

    void a(dg dgVar);

    void a(h1.g gVar);

    void a(h5 h5Var);

    void a(ph phVar);

    void a(v4 v4Var);

    void a(xd xdVar, hb hbVar);

    void a(TencentMap.OnCameraChangeListener onCameraChangeListener);

    void a(TencentMap.OnDismissCallback onDismissCallback);

    void a(TencentMap.OnIndoorStateChangeListener onIndoorStateChangeListener);

    void a(TencentMap.OnMapPoiClickListener onMapPoiClickListener);

    void a(OverSeaTileProvider overSeaTileProvider);

    void a(TencentMapGestureListener tencentMapGestureListener);

    void a(String str, boolean z);

    void a(String str, boolean z, boolean z2);

    void a(boolean z);

    boolean a(float f2, float f3);

    void b(float f2);

    void b(int i2, int i3);

    void b(b5 b5Var);

    void b(v4 v4Var);

    void b(TencentMapGestureListener tencentMapGestureListener);

    void b(boolean z);

    boolean b();

    boolean b(float f2, float f3);

    boolean b(int i2);

    boolean b(String str);

    GeoPoint c();

    String c(float f2, float f3);

    void c(float f2);

    void c(int i2, int i3);

    void c(boolean z);

    uf d();

    void d(boolean z);

    boolean d(float f2, float f3);

    void e();

    void e(boolean z);

    boolean e(float f2, float f3);

    void f(boolean z);

    boolean f();

    boolean f(float f2, float f3);

    void g();

    void g(boolean z);

    Context getContext();

    @Override // com.tencent.mapsdk.internal.le
    int getEGLContextHash();

    void h();

    void h(boolean z);

    b0 i();

    void i(boolean z);

    boolean isHandDrawMapEnable();

    void j(boolean z);

    dg k();

    TencentMapOptions l();

    boolean m();

    int n();

    xd o();

    void p();

    int q();

    boolean r();

    boolean s();

    void setCompassExtraPadding(int i2);

    void setCompassExtraPadding(int i2, int i3);

    void setFlingGestureEnabled(boolean z);

    void setMapCenterAndScale(float f2, float f3, float f4);

    void setOnTapMapViewInfoWindowHidden(boolean z);

    void t();

    uh u();

    boolean v();

    pc w();

    void y();
}
