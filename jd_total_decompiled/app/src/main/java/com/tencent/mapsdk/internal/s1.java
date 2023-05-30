package com.tencent.mapsdk.internal;

import android.content.Context;
import android.os.Bundle;
import com.tencent.map.sdk.utilities.heatmap.HeatMapTileProvider;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import com.tencent.tencentmap.mapsdk.maps.model.IAlphaAnimation;
import com.tencent.tencentmap.mapsdk.maps.model.IAnimationSet;
import com.tencent.tencentmap.mapsdk.maps.model.IEmergeAnimation;
import com.tencent.tencentmap.mapsdk.maps.model.IRotateAnimation;
import com.tencent.tencentmap.mapsdk.maps.model.IScaleAnimation;
import com.tencent.tencentmap.mapsdk.maps.model.ITranslateAnimation;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public abstract class s1 extends o1 {
    public s1(Context context, TencentMapOptions tencentMapOptions, p1 p1Var) {
        super(context, tencentMapOptions, p1Var);
    }

    @Override // com.tencent.mapsdk.internal.o1
    public void a(Bundle bundle) {
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapComponent
    public IAlphaAnimation createAlphaAnimation(float f2, float f3) {
        return new h7(f2, f3);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapComponent
    public IAnimationSet createAnimationSet(boolean z) {
        return new j7(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapComponent
    public IEmergeAnimation createEmergeAnimation(LatLng latLng) {
        return new k7(latLng);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapComponent
    public HeatMapTileProvider createHeatMapTileProvider(HeatMapTileProvider.Builder builder) {
        return new w1(builder);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapComponent
    public IRotateAnimation createRotateAnimation(float f2, float f3, float f4, float f5, float f6) {
        return new l7(f2, f3, f4, f5, f6);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapComponent
    public IScaleAnimation createScaleAnimation(float f2, float f3, float f4, float f5) {
        return new m7(f2, f3, f4, f5);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapComponent
    public ITranslateAnimation createTranslateAnimation(LatLng latLng) {
        return new n7(latLng);
    }

    @Override // com.tencent.mapsdk.internal.o1
    public String y() {
        return "4.5.10.6";
    }
}
