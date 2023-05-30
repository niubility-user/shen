package com.tencent.mapsdk.internal;

import com.tencent.map.lib.models.GeoPoint;

/* loaded from: classes9.dex */
public class x7 implements x8<GeoPoint> {
    @Override // com.tencent.mapsdk.internal.x8
    public GeoPoint a(float f2, GeoPoint geoPoint, GeoPoint geoPoint2) {
        return new GeoPoint(geoPoint.getLatitudeE6() + ((int) ((geoPoint2.getLatitudeE6() - geoPoint.getLatitudeE6()) * f2)), geoPoint.getLongitudeE6() + ((int) (f2 * (geoPoint2.getLongitudeE6() - geoPoint.getLongitudeE6()))));
    }
}
