package com.tencent.mapsdk.internal;

import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.internal.o7;

/* loaded from: classes9.dex */
public class f8 extends o7 {

    /* renamed from: j  reason: collision with root package name */
    private GeoPoint f16510j;

    /* renamed from: k  reason: collision with root package name */
    private GeoPoint f16511k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f16512l = false;

    public f8(GeoPoint geoPoint, GeoPoint geoPoint2) {
        this.f16510j = null;
        this.f16511k = null;
        if (geoPoint != null) {
            this.f16510j = new GeoPoint(geoPoint.getLatitudeE6(), geoPoint.getLongitudeE6());
        }
        if (geoPoint2 != null) {
            this.f16511k = new GeoPoint(geoPoint2.getLatitudeE6(), geoPoint2.getLongitudeE6());
        }
    }

    @Override // com.tencent.mapsdk.internal.o7
    public void b(float f2) {
        GeoPoint geoPoint = this.f16511k;
        if (geoPoint == null || this.f16510j == null) {
            return;
        }
        int latitudeE6 = geoPoint.getLatitudeE6() - this.f16510j.getLatitudeE6();
        int longitudeE6 = this.f16511k.getLongitudeE6() - this.f16510j.getLongitudeE6();
        int latitudeE62 = this.f16510j.getLatitudeE6() + ((int) (latitudeE6 * f2));
        int longitudeE62 = this.f16510j.getLongitudeE6() + ((int) (longitudeE6 * f2));
        o7.b bVar = this.f16916i;
        if (bVar != null) {
            bVar.a(new GeoPoint(latitudeE62, longitudeE62));
        }
    }
}
