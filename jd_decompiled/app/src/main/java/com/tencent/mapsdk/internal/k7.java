package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.tencent.tencentmap.mapsdk.maps.model.IEmergeAnimation;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public class k7 extends i7 implements IEmergeAnimation {

    /* renamed from: c  reason: collision with root package name */
    private LatLng f16762c;

    public k7(LatLng latLng) {
        this.f16762c = null;
        if (this.a == null) {
            this.a = new b8(latLng);
        }
        this.f16762c = latLng;
    }

    public LatLng a() {
        return this.f16762c;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Animation
    public void setDuration(long j2) {
        z7 z7Var = this.a;
        if (z7Var == null) {
            return;
        }
        z7Var.a(j2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Animation
    public void setInterpolator(Interpolator interpolator) {
        z7 z7Var = this.a;
        if (z7Var == null || interpolator == null) {
            return;
        }
        z7Var.a(interpolator);
    }
}
