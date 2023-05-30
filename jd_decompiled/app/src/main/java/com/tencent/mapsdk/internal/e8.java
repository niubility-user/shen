package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.internal.z7;

/* loaded from: classes9.dex */
public class e8 extends z7 {

    /* renamed from: i  reason: collision with root package name */
    private GeoPoint f16456i = null;

    /* renamed from: j  reason: collision with root package name */
    private GeoPoint f16457j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f16458k;

    public e8(GeoPoint geoPoint) {
        this.f16457j = null;
        this.f16458k = false;
        if (geoPoint != null) {
            this.f16457j = new GeoPoint(geoPoint.getLatitudeE6(), geoPoint.getLongitudeE6());
            this.f16458k = true;
        }
    }

    @Override // com.tencent.mapsdk.internal.z7
    public void a(float f2, Interpolator interpolator) {
        GeoPoint geoPoint = this.f16457j;
        if (geoPoint == null || this.f16456i == null) {
            return;
        }
        int latitudeE6 = geoPoint.getLatitudeE6() - this.f16456i.getLatitudeE6();
        int longitudeE6 = this.f16457j.getLongitudeE6() - this.f16456i.getLongitudeE6();
        float interpolation = interpolator.getInterpolation(f2);
        int latitudeE62 = this.f16456i.getLatitudeE6() + ((int) (latitudeE6 * interpolation));
        int longitudeE62 = this.f16456i.getLongitudeE6() + ((int) (longitudeE6 * interpolation));
        if (1.0f - f2 < 1.0E-4d) {
            latitudeE62 = this.f16457j.getLatitudeE6();
            longitudeE62 = this.f16457j.getLongitudeE6();
        }
        z7.b bVar = this.f17557h;
        if (bVar != null) {
            bVar.a(latitudeE62, longitudeE62);
        }
    }

    @Override // com.tencent.mapsdk.internal.z7
    public boolean a(GeoPoint geoPoint, GeoPoint geoPoint2) {
        if (super.a((GeoPoint) null, (GeoPoint) null)) {
            if (geoPoint != null) {
                this.f16456i = new GeoPoint(geoPoint.getLatitudeE6(), geoPoint.getLongitudeE6());
            }
            if (this.f16458k || geoPoint2 == null) {
                return true;
            }
            this.f16457j = new GeoPoint(geoPoint2.getLatitudeE6(), geoPoint2.getLongitudeE6());
            return true;
        }
        return false;
    }
}
