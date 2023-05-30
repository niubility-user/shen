package com.tencent.mapsdk.internal;

import com.tencent.map.lib.models.GeoPoint;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class sa {
    private static double a(int i2, int i3, int i4, float f2) {
        double d = f2;
        Double.isNaN(d);
        double d2 = 1.0d - d;
        double d3 = i2;
        Double.isNaN(d3);
        double d4 = d2 * d2 * d3;
        double d5 = 2.0f * f2;
        Double.isNaN(d5);
        double d6 = i3;
        Double.isNaN(d6);
        double d7 = d5 * d2 * d6;
        double d8 = f2 * f2 * i4;
        Double.isNaN(d8);
        return d4 + d7 + d8;
    }

    private static double a(o5 o5Var, o5 o5Var2) {
        double d = o5Var2.a - o5Var.a;
        double d2 = o5Var2.b - o5Var.b;
        return Math.sqrt((d * d) + (d2 * d2));
    }

    private static float a(int i2, int i3, int i4, int i5, float f2) {
        float f3 = (i3 - i2) * 3.0f;
        float f4 = ((i4 - i3) * 3.0f) - f3;
        float f5 = f2 * f2;
        return ((((i5 - i2) - f3) - f4) * f5 * f2) + (f4 * f5) + (f3 * f2) + i2;
    }

    public static int a(List<GeoPoint> list, int[] iArr, s4 s4Var) {
        int size;
        int i2 = 0;
        if (list != null && (size = list.size()) >= 2) {
            int i3 = 0;
            while (i2 < size - 1) {
                int i4 = i2 + 1;
                int a = (int) a(s4Var.a(list.get(i2)), s4Var.a(list.get(i4)));
                int max = a / Math.max(4, (a / 500) * 4);
                iArr[i2] = max;
                i3 += max;
                i2 = i4;
            }
            return i3;
        }
        return 0;
    }

    private static GeoPoint a(List<GeoPoint> list, float f2) {
        int size;
        if (list == null || list.isEmpty() || !((size = list.size()) == 3 || size == 4)) {
            return null;
        }
        if (size == 3) {
            GeoPoint geoPoint = list.get(0);
            GeoPoint geoPoint2 = list.get(1);
            GeoPoint geoPoint3 = list.get(2);
            if (geoPoint == null || geoPoint2 == null || geoPoint3 == null) {
                return null;
            }
            return new GeoPoint((int) a(geoPoint.getLatitudeE6(), geoPoint2.getLatitudeE6(), geoPoint3.getLatitudeE6(), f2), (int) a(geoPoint.getLongitudeE6(), geoPoint2.getLongitudeE6(), geoPoint3.getLongitudeE6(), f2));
        }
        GeoPoint geoPoint4 = list.get(0);
        GeoPoint geoPoint5 = list.get(1);
        GeoPoint geoPoint6 = list.get(2);
        GeoPoint geoPoint7 = list.get(3);
        if (geoPoint4 != null && geoPoint5 != null && geoPoint6 != null && geoPoint7 != null) {
            return new GeoPoint((int) a(geoPoint4.getLatitudeE6(), geoPoint5.getLatitudeE6(), geoPoint6.getLatitudeE6(), geoPoint7.getLatitudeE6(), f2), (int) a(geoPoint4.getLongitudeE6(), geoPoint5.getLongitudeE6(), geoPoint6.getLongitudeE6(), geoPoint7.getLongitudeE6(), f2));
        }
        return null;
    }

    public static List<GeoPoint> a(List<GeoPoint> list, int i2) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(i2);
        GeoPoint geoPoint = list.get(0);
        GeoPoint geoPoint2 = list.get(size - 1);
        int longitudeE6 = (geoPoint.getLongitudeE6() + geoPoint2.getLongitudeE6()) / 2;
        int latitudeE6 = (geoPoint.getLatitudeE6() + geoPoint2.getLatitudeE6()) / 2;
        for (int i3 = 0; i3 < size; i3++) {
            GeoPoint geoPoint3 = list.get(i3);
            geoPoint3.setLongitudeE6(geoPoint3.getLongitudeE6() - longitudeE6);
            geoPoint3.setLatitudeE6(geoPoint3.getLatitudeE6() - latitudeE6);
        }
        float f2 = 1.0f / (i2 + 1);
        float f3 = f2;
        for (int i4 = 0; i4 < i2; i4++) {
            GeoPoint a = a(list, f3);
            if (a != null) {
                a.setLongitudeE6(a.getLongitudeE6() + longitudeE6);
                a.setLatitudeE6(a.getLatitudeE6() + latitudeE6);
                arrayList.add(a);
                f3 += f2;
            }
        }
        return arrayList;
    }
}
