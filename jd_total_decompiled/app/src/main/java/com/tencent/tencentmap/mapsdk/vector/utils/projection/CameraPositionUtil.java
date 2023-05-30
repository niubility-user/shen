package com.tencent.tencentmap.mapsdk.vector.utils.projection;

import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.vector.utils.a.f;
import com.tencent.tencentmap.mapsdk.vector.utils.a.g;

/* loaded from: classes9.dex */
public class CameraPositionUtil {
    public static CameraPosition a(LatLng[] latLngArr, double d, double d2) {
        int i2;
        int length = latLngArr.length;
        double d3 = Double.MIN_VALUE;
        double d4 = Double.MAX_VALUE;
        double d5 = Double.MAX_VALUE;
        int i3 = 0;
        double d6 = Double.MIN_VALUE;
        while (i3 < length) {
            LatLng latLng = latLngArr[i3];
            double d7 = latLng.latitude;
            double d8 = d3;
            if (d7 < d4) {
                d4 = d7;
            }
            double d9 = latLng.longitude;
            if (d9 < d5) {
                d5 = d9;
            }
            if (d7 <= d8) {
                d7 = d8;
            }
            if (d9 > d6) {
                d6 = d9;
            }
            i3++;
            d3 = d7;
        }
        g gVar = new g();
        f a = gVar.a(new LatLng(d4, d5));
        f a2 = gVar.a(new LatLng(d3, d6));
        double d10 = 2.0d;
        f fVar = new f((a.a + a2.a) / 2.0d, (a.b + a2.b) / 2.0d);
        int i4 = 20;
        while (true) {
            if (i4 < 0) {
                i2 = 0;
                break;
            }
            double pow = 156543.0339d / Math.pow(d10, i4);
            double d11 = ((-a2.b) + a.b) / pow;
            if ((a2.a - a.a) / pow <= d && d11 <= d2) {
                i2 = i4;
                break;
            }
            i4--;
            d10 = 2.0d;
        }
        LatLng a3 = gVar.a(fVar);
        CameraPosition.Builder builder = new CameraPosition.Builder();
        builder.target(a3).zoom(i2);
        return builder.build();
    }

    public static CameraPosition getCameraPosition(LatLng[] latLngArr, double d, double d2) {
        return a(latLngArr, d, d2);
    }
}
