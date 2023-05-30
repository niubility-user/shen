package com.tencent.mapsdk.internal;

import com.tencent.map.lib.models.GeoPoint;
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions;

/* loaded from: classes9.dex */
public class gg {
    private int a;
    private qi b;

    public gg(qi qiVar, GeoPoint[] geoPointArr, PolylineOptions.Text text) {
        this.b = qiVar;
        if (qiVar == null) {
            return;
        }
        this.a = qiVar.a(geoPointArr, text);
    }

    public void a() {
        int i2;
        qi qiVar = this.b;
        if (qiVar == null || (i2 = this.a) <= 0) {
            return;
        }
        qiVar.e(i2);
    }

    public void a(PolylineOptions.Text text) {
        int i2;
        qi qiVar = this.b;
        if (qiVar == null || (i2 = this.a) <= 0) {
            return;
        }
        qiVar.a(i2, text);
    }
}
