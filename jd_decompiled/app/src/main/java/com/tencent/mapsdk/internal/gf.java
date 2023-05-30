package com.tencent.mapsdk.internal;

import android.graphics.Rect;
import com.tencent.map.lib.models.AccessibleTouchItem;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.MapPoi;

/* loaded from: classes9.dex */
public class gf extends AccessibleTouchItem {
    private MapPoi a;
    private xi b;

    public gf(xi xiVar, MapPoi mapPoi) {
        this.a = mapPoi;
        this.b = xiVar;
    }

    @Override // com.tencent.map.lib.models.AccessibleTouchItem
    public Rect getBounds() {
        o5 a = this.b.getMap().getProjection().a(GeoPoint.from(new LatLng(this.a.getLatitude(), this.a.getLongitude())));
        double d = a.a;
        double w = b7.w() * 20.0f;
        Double.isNaN(w);
        double d2 = a.b;
        double w2 = b7.w() * 20.0f;
        Double.isNaN(w2);
        double d3 = a.a;
        double w3 = b7.w() * 20.0f;
        Double.isNaN(w3);
        int i2 = (int) (d3 + w3);
        double d4 = a.b;
        double w4 = b7.w() * 20.0f;
        Double.isNaN(w4);
        return new Rect((int) (d - w), (int) (d2 - w2), i2, (int) (d4 + w4));
    }

    @Override // com.tencent.map.lib.models.AccessibleTouchItem
    public String getContentDescription() {
        return this.a.getName();
    }

    @Override // com.tencent.map.lib.models.AccessibleTouchItem
    public void onClick() {
        TencentMap.OnMapPoiClickListener onMapPoiClickListener;
        xi xiVar = this.b;
        if (xiVar == null || (onMapPoiClickListener = xiVar.c0) == null) {
            return;
        }
        MapPoi mapPoi = new MapPoi();
        mapPoi.position = new LatLng(this.a.getLatitude(), this.a.getLongitude());
        mapPoi.name = this.a.getName();
        onMapPoiClickListener.onClicked(mapPoi);
    }
}
