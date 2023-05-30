package com.tencent.mapsdk.navi;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;
import androidx.annotation.Keep;
import com.tencent.mapsdk.core.MapDelegate;
import com.tencent.mapsdk.internal.ei;
import com.tencent.mapsdk.internal.fi;
import com.tencent.mapsdk.internal.qc;
import com.tencent.mapsdk.internal.v1;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;

@Keep
/* loaded from: classes9.dex */
public class VectorMapNaviDelegateProxy implements MapDelegate<qc, ei, v1> {
    private fi mMapDelegate;

    @Keep
    public VectorMapNaviDelegateProxy(Context context, TencentMapOptions tencentMapOptions, ViewGroup viewGroup) {
        this.mMapDelegate = new fi(context, tencentMapOptions, viewGroup);
    }

    @Override // com.tencent.mapsdk.core.MapDelegate
    public ei createMap(qc qcVar) {
        return (ei) this.mMapDelegate.a((fi) qcVar);
    }

    @Override // com.tencent.mapsdk.core.MapDelegate
    public v1 createMapView(qc qcVar, ViewGroup viewGroup) {
        return this.mMapDelegate.createMapView((fi) qcVar, viewGroup);
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
        this.mMapDelegate.onCreated();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onDestroy() {
        this.mMapDelegate.onDestroy();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onPause() {
        this.mMapDelegate.onPause();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onRestart() {
        this.mMapDelegate.onRestart();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onResume() {
        this.mMapDelegate.onResume();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        this.mMapDelegate.onSizeChanged(i2, i3, i4, i5);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onStart() {
        this.mMapDelegate.onStart();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onStop() {
        this.mMapDelegate.onStop();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onSurfaceChanged(Object obj, int i2, int i3) {
        this.mMapDelegate.onSurfaceChanged(obj, i2, i3);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mMapDelegate.onTouchEvent(motionEvent);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onUpdateOptions(TencentMapOptions tencentMapOptions) {
        this.mMapDelegate.onUpdateOptions(tencentMapOptions);
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
