package com.tencent.mapsdk.vector;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;
import androidx.annotation.Keep;
import com.tencent.mapsdk.core.MapDelegate;
import com.tencent.mapsdk.internal.pa;
import com.tencent.mapsdk.internal.qa;
import com.tencent.mapsdk.internal.qc;
import com.tencent.mapsdk.internal.v1;
import com.tencent.mapsdk.internal.xi;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;

@Keep
/* loaded from: classes9.dex */
public class VectorMapDelegateProxy implements MapDelegate<qc, VectorMap, v1> {
    private xi mMapDelegate;

    @Keep
    public VectorMapDelegateProxy(Context context, TencentMapOptions tencentMapOptions, ViewGroup viewGroup) {
        this.mMapDelegate = new xi(context, tencentMapOptions, viewGroup);
    }

    @Override // com.tencent.mapsdk.core.MapDelegate
    public VectorMap createMap(qc qcVar) {
        return this.mMapDelegate.a((xi) qcVar);
    }

    @Override // com.tencent.mapsdk.core.MapDelegate
    public v1 createMapView(qc qcVar, ViewGroup viewGroup) {
        return this.mMapDelegate.createMapView((xi) qcVar, viewGroup);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public TencentMap getMap() {
        return this.mMapDelegate.getMap();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.tencent.mapsdk.core.MapDelegate
    public qc getMapContext() {
        return this.mMapDelegate.getMapContext();
    }

    @Override // com.tencent.mapsdk.core.MapDelegate
    public v1 getMapRenderView() {
        return this.mMapDelegate.getMapRenderView();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public boolean isOpaque() {
        return this.mMapDelegate.isOpaque();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public boolean isTouchable() {
        return this.mMapDelegate.isTouchable();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onCreated() {
        qa.h(pa.z);
        this.mMapDelegate.onCreated();
        qa.i(pa.z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onDestroy() {
        qa.h(pa.F);
        this.mMapDelegate.onDestroy();
        qa.i(pa.F);
        qa.i(pa.W);
        qa.e();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onPause() {
        qa.h(pa.C);
        this.mMapDelegate.onPause();
        qa.i(pa.C);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onRestart() {
        qa.h(pa.D);
        this.mMapDelegate.onRestart();
        qa.i(pa.D);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onResume() {
        qa.h(pa.B);
        this.mMapDelegate.onResume();
        qa.i(pa.B);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        qa.h(pa.H);
        this.mMapDelegate.onSizeChanged(i2, i3, i4, i5);
        qa.i(pa.H);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onStart() {
        qa.h(pa.A);
        this.mMapDelegate.onStart();
        qa.i(pa.A);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onStop() {
        qa.h(pa.E);
        this.mMapDelegate.onStop();
        qa.i(pa.E);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onSurfaceChanged(Object obj, int i2, int i3) {
        qa.h(pa.I);
        qa.b(pa.I, "width", Integer.valueOf(i2));
        qa.b(pa.I, "height", Integer.valueOf(i3));
        this.mMapDelegate.onSurfaceChanged(obj, i2, i3);
        qa.i(pa.I);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mMapDelegate.onTouchEvent(motionEvent);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onUpdateOptions(TencentMapOptions tencentMapOptions) {
        qa.h(pa.G);
        this.mMapDelegate.onUpdateOptions(tencentMapOptions);
        qa.i(pa.G);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void setOnTop(boolean z) {
        this.mMapDelegate.setOnTop(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void setOpaque(boolean z) {
        this.mMapDelegate.setOpaque(z);
    }
}
