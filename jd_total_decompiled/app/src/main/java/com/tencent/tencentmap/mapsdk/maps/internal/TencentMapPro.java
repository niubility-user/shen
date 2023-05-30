package com.tencent.tencentmap.mapsdk.maps.internal;

import androidx.annotation.Keep;
import com.tencent.map.lib.mapstructure.MapRouteSectionWithName;
import com.tencent.mapsdk.internal.f0;
import com.tencent.mapsdk.internal.h1;
import com.tencent.mapsdk.internal.ij;
import com.tencent.mapsdk.internal.qc;
import com.tencent.mapsdk.internal.t4;
import com.tencent.mapsdk.internal.v1;
import com.tencent.mapsdk.internal.xi;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdate;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.IOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.IntersectionOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.IntersectionOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.MapRouteSection;
import java.util.ArrayList;
import java.util.List;

@Keep
/* loaded from: classes9.dex */
public final class TencentMapPro {
    private xi mVectorMapDelegate;
    private boolean mapDestroyed = false;
    private h1 mapManager;
    private f0 viewControl;

    public TencentMapPro(h1 h1Var, f0 f0Var) {
        this.mapManager = h1Var;
        this.viewControl = f0Var;
        this.mVectorMapDelegate = h1Var.l();
    }

    @Keep
    public IntersectionOverlay addIntersectionEnlargeOverlay(IntersectionOverlayOptions intersectionOverlayOptions) {
        xi xiVar;
        qc A;
        if (this.mapDestroyed || (xiVar = this.mVectorMapDelegate) == null || (A = xiVar.A()) == null) {
            return null;
        }
        return A.a(intersectionOverlayOptions);
    }

    @Keep
    public void addRouteNameSegments(List<MapRouteSectionWithName> list, List<LatLng> list2) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (MapRouteSectionWithName mapRouteSectionWithName : list) {
                if (mapRouteSectionWithName != null) {
                    MapRouteSection mapRouteSection = new MapRouteSection();
                    mapRouteSection.color = mapRouteSectionWithName.color;
                    mapRouteSection.endNum = mapRouteSectionWithName.endNum;
                    mapRouteSection.roadName = mapRouteSectionWithName.roadName;
                    mapRouteSection.startNum = mapRouteSectionWithName.startNum;
                    arrayList.add(mapRouteSection);
                }
            }
            addSegmentsWithRouteName(arrayList, list2);
        }
    }

    @Keep
    public void addSegmentsWithRouteName(List<MapRouteSection> list, List<LatLng> list2) {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return;
        }
        h1Var.a(list, list2);
    }

    @Keep
    public final void animateToNaviPosition(LatLng latLng, float f2, float f3) {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return;
        }
        h1Var.a(latLng, f2, f3, true);
    }

    @Keep
    public final void animateToNaviPosition(LatLng latLng, float f2, float f3, float f4) {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return;
        }
        h1Var.animateToNaviPosition(latLng, f2, f3, f4, true);
    }

    @Keep
    public final void animateToNaviPosition(LatLng latLng, float f2, float f3, float f4, boolean z) {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return;
        }
        h1Var.animateToNaviPosition(latLng, f2, f3, f4, z);
    }

    @Keep
    public final void animateToNaviPosition2(LatLng latLng, float f2, float f3, float f4, boolean z) {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return;
        }
        h1Var.animateToNaviPosition2(latLng, f2, f3, f4, z);
    }

    @Keep
    public float calNaviLevel(LatLngBounds latLngBounds, float f2, int i2, boolean z) {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return 0.0f;
        }
        return h1Var.calNaviLevel(latLngBounds, f2, i2, z);
    }

    @Keep
    public float calNaviLevel2(LatLng latLng, LatLng latLng2, float f2, float f3, int i2, boolean z) {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return 0.0f;
        }
        return h1Var.calNaviLevel2(latLng, latLng2, f2, f3, i2, z);
    }

    @Keep
    public float calNaviLevel3(LatLng latLng, LatLng latLng2, float f2, int i2, int i3, int i4, int i5, boolean z) {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return 0.0f;
        }
        return h1Var.calNaviLevel3(latLng, latLng2, f2, i2, i3, i4, i5, z);
    }

    @Keep
    public CameraPosition calculateZoomToSpanLevelAsync(List<IOverlay> list, List<LatLng> list2, int i2, int i3, int i4, int i5, TencentMap.AsyncOperateCallback<CameraPosition> asyncOperateCallback) {
        if (this.mapDestroyed || this.mapManager == null) {
            if (asyncOperateCallback != null) {
                asyncOperateCallback.onOperateFinished(null);
            }
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (IOverlay iOverlay : list) {
                if (iOverlay instanceof t4) {
                    arrayList.add((t4) iOverlay);
                }
            }
        }
        return this.mapManager.calculateZoomToSpanLevelAsync(arrayList, list2, i2, i3, i4, i5, asyncOperateCallback);
    }

    @Keep
    public void clearRouteNameSegments() {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return;
        }
        h1Var.clearRouteNameSegments();
    }

    @Keep
    public boolean isNaviStateEnabled() {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return false;
        }
        return h1Var.b();
    }

    @Keep
    public final void moveToNavPosition(CameraUpdate cameraUpdate, LatLng latLng) {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return;
        }
        h1Var.moveCamera(cameraUpdate);
        if (latLng != null) {
            setNavCenter((int) (latLng.latitude * 1000000.0d), (int) (latLng.longitude * 1000000.0d));
        }
    }

    @Keep
    public void onDestroy() {
        this.mapDestroyed = true;
    }

    @Keep
    public void setNavCenter(int i2, int i3) {
        f0 f0Var = this.viewControl;
        if (f0Var == null) {
            return;
        }
        v1 b = f0Var.b();
        if (b instanceof ij) {
            ((ij) b).getVectorMapDelegate().c(i2, i3);
        }
    }

    @Keep
    public void setNaviFixingProportion(float f2, float f3) {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return;
        }
        h1Var.setNaviFixingProportion(f2, f3);
    }

    @Keep
    public void setNaviFixingProportion2D(float f2, float f3) {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return;
        }
        h1Var.setNaviFixingProportion2D(f2, f3);
    }

    @Keep
    public void setNaviStateEnabled(boolean z) {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return;
        }
        h1Var.a(z);
    }

    @Keep
    public void setOnCameraChangeListener(TencentMap.OnCameraChangeListener onCameraChangeListener) {
        h1 h1Var;
        if (this.mapDestroyed || (h1Var = this.mapManager) == null) {
            return;
        }
        h1Var.a(onCameraChangeListener);
    }

    public void setOptionalResourcePath(String str) {
        xi xiVar;
        qc A;
        if (this.mapDestroyed || (xiVar = this.mVectorMapDelegate) == null || (A = xiVar.A()) == null) {
            return;
        }
        A.h(str);
    }
}
