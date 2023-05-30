package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.tencent.mapsdk.internal.z7;

/* loaded from: classes9.dex */
public class c8 extends z7 {

    /* renamed from: i  reason: collision with root package name */
    private float f16360i;

    /* renamed from: j  reason: collision with root package name */
    private float f16361j;

    /* renamed from: k  reason: collision with root package name */
    private float f16362k;

    /* renamed from: l  reason: collision with root package name */
    private float f16363l;

    /* renamed from: m  reason: collision with root package name */
    private float f16364m;

    public c8(float f2, float f3, float f4, float f5, float f6) {
        this.f16360i = 0.0f;
        this.f16361j = 0.0f;
        this.f16362k = 0.0f;
        this.f16363l = 0.0f;
        this.f16364m = 0.0f;
        this.f16360i = f2;
        this.f16361j = f3;
        this.f16362k = f4;
        this.f16363l = f5;
        this.f16364m = f6;
    }

    @Override // com.tencent.mapsdk.internal.z7
    public void a(float f2, Interpolator interpolator) {
        float interpolation = this.f16360i + ((this.f16361j - this.f16360i) * interpolator.getInterpolation(f2));
        z7.b bVar = this.f17557h;
        if (bVar != null) {
            bVar.a(interpolation, this.f16362k, this.f16363l, this.f16364m);
        }
    }
}
