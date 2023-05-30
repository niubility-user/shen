package com.tencent.mapsdk.internal;

import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.IndoorBuilding;

/* loaded from: classes9.dex */
public class ui implements TencentMap.OnIndoorStateChangeListener {

    /* renamed from: g  reason: collision with root package name */
    private xi f17353g;

    public ui(xi xiVar) {
        this.f17353g = xiVar;
    }

    private boolean j() {
        return this.f17353g == null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnIndoorStateChangeListener
    public boolean onIndoorBuildingDeactivated() {
        xi xiVar = this.f17353g;
        if (xiVar == null) {
            return false;
        }
        xiVar.onIndoorBuildingDeactivated();
        TencentMap.OnIndoorStateChangeListener onIndoorStateChangeListener = this.f17353g.Z;
        if (onIndoorStateChangeListener != null) {
            onIndoorStateChangeListener.onIndoorBuildingDeactivated();
            return true;
        }
        return true;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnIndoorStateChangeListener
    public boolean onIndoorBuildingFocused() {
        xi xiVar = this.f17353g;
        if (xiVar == null) {
            return false;
        }
        xiVar.onIndoorBuildingFocused();
        TencentMap.OnIndoorStateChangeListener onIndoorStateChangeListener = this.f17353g.Z;
        if (onIndoorStateChangeListener != null) {
            onIndoorStateChangeListener.onIndoorBuildingFocused();
            return true;
        }
        return true;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnIndoorStateChangeListener
    public boolean onIndoorLevelActivated(IndoorBuilding indoorBuilding) {
        xi xiVar = this.f17353g;
        if (xiVar == null) {
            return false;
        }
        xiVar.onIndoorLevelActivated(indoorBuilding);
        TencentMap.OnIndoorStateChangeListener onIndoorStateChangeListener = this.f17353g.Z;
        if (onIndoorStateChangeListener != null) {
            onIndoorStateChangeListener.onIndoorLevelActivated(indoorBuilding);
            return true;
        }
        return true;
    }
}
