package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.tencent.mapsdk.internal.z7;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public class b8 extends z7 {

    /* renamed from: i  reason: collision with root package name */
    private LatLng f16303i;

    public b8(LatLng latLng) {
        this.f16303i = null;
        this.f16303i = latLng;
    }

    @Override // com.tencent.mapsdk.internal.z7
    public void a(float f2, Interpolator interpolator) {
        float interpolation = interpolator.getInterpolation(f2);
        z7.b bVar = this.f17557h;
        if (bVar != null) {
            bVar.a(interpolation);
        }
    }

    public LatLng i() {
        return this.f16303i;
    }
}
