package com.tencent.mapsdk.internal;

import android.graphics.Point;
import android.location.Location;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class ea {
    private static final int a = 20037508;
    private static final int b = 30240971;

    /* renamed from: c  reason: collision with root package name */
    public static final int f16459c = 256;
    public static final int d = 20;

    /* renamed from: e  reason: collision with root package name */
    public static final double f16460e = 4.272282972352698E7d;

    /* renamed from: f  reason: collision with root package name */
    private static final double f16461f = 111319.49077777778d;

    /* renamed from: g  reason: collision with root package name */
    private static final double f16462g = 0.017453292519943295d;

    /* renamed from: h  reason: collision with root package name */
    private static final double f16463h = 0.008726646259971648d;

    /* renamed from: i  reason: collision with root package name */
    private static final double f16464i = 114.59155902616465d;

    private static double a(double d2) {
        return (d2 * 3.141592653589793d) / 180.0d;
    }

    public static double a(double d2, double d3, double d4, double d5) {
        double a2 = a(d4 - d2);
        double a3 = a(d5 - d3);
        double d6 = a2 / 2.0d;
        double d7 = a3 / 2.0d;
        double sin = (Math.sin(d6) * Math.sin(d6)) + (Math.sin(d7) * Math.sin(d7) * Math.cos(a(d2)) * Math.cos(a(d4)));
        return Math.atan2(Math.sqrt(sin), Math.sqrt(1.0d - sin)) * 2.0d * 6371.0d * 1000.0d;
    }

    public static float a(GeoPoint geoPoint, GeoPoint geoPoint2) {
        if (geoPoint == null || geoPoint2 == null) {
            throw new IllegalArgumentException("point is null");
        }
        double latitudeE6 = geoPoint.getLatitudeE6();
        Double.isNaN(latitudeE6);
        double d2 = latitudeE6 / 1000000.0d;
        double longitudeE6 = geoPoint.getLongitudeE6();
        Double.isNaN(longitudeE6);
        double d3 = longitudeE6 / 1000000.0d;
        double latitudeE62 = geoPoint2.getLatitudeE6();
        Double.isNaN(latitudeE62);
        double longitudeE62 = geoPoint2.getLongitudeE6();
        Double.isNaN(longitudeE62);
        return (float) a(d2, d3, latitudeE62 / 1000000.0d, longitudeE62 / 1000000.0d);
    }

    public static int a(int i2) {
        return i2 - a;
    }

    public static Point a(GeoPoint geoPoint) {
        if (geoPoint != null) {
            double latitudeE6 = geoPoint.getLatitudeE6();
            Double.isNaN(latitudeE6);
            double longitudeE6 = geoPoint.getLongitudeE6();
            Double.isNaN(longitudeE6);
            Point point2 = new Point();
            point2.x = (int) ((((longitudeE6 / 1000000.0d) + 180.0d) / 360.0d) * 2.68435456E8d);
            point2.y = (int) (((180.0d - (Math.log(Math.tan(((latitudeE6 / 1000000.0d) + 90.0d) * f16463h)) / 0.01745329238474369d)) / 360.0d) * 2.68435456E8d);
            return point2;
        }
        throw new IllegalArgumentException("point is null");
    }

    public static GeoPoint a(int i2, int i3) {
        return new GeoPoint((int) (d(i3) * 1000000.0d), (int) (c(i2) * 1000000.0d));
    }

    public static List<LatLng> a(List<GeoPoint> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<GeoPoint> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(d(it.next()));
        }
        return arrayList;
    }

    public static void a(double d2, double d3, double d4, double d5, float[] fArr) {
        double d6;
        double d7;
        double d8 = d2 * f16462g;
        double d9 = d4 * f16462g;
        double d10 = (f16462g * d5) - (d3 * f16462g);
        double atan = Math.atan(Math.tan(d8) * 0.996647189328169d);
        double atan2 = Math.atan(Math.tan(d9) * 0.996647189328169d);
        double cos = Math.cos(atan);
        double cos2 = Math.cos(atan2);
        double sin = Math.sin(atan);
        double sin2 = Math.sin(atan2);
        double d11 = cos * cos2;
        double d12 = sin * sin2;
        double d13 = d10;
        double d14 = 0.0d;
        double d15 = 0.0d;
        double d16 = 0.0d;
        double d17 = 0.0d;
        double d18 = 0.0d;
        int i2 = 0;
        while (true) {
            if (i2 >= 20) {
                d6 = sin;
                d7 = sin2;
                break;
            }
            d17 = Math.cos(d13);
            d18 = Math.sin(d13);
            double d19 = cos2 * d18;
            double d20 = (cos * sin2) - ((sin * cos2) * d17);
            d6 = sin;
            double sqrt = Math.sqrt((d19 * d19) + (d20 * d20));
            d7 = sin2;
            double d21 = d12 + (d11 * d17);
            d15 = Math.atan2(sqrt, d21);
            double d22 = sqrt == 0.0d ? 0.0d : (d11 * d18) / sqrt;
            double d23 = 1.0d - (d22 * d22);
            double d24 = d23 == 0.0d ? 0.0d : d21 - ((d12 * 2.0d) / d23);
            double d25 = 0.006739496756586903d * d23;
            double d26 = ((d25 / 16384.0d) * (((((320.0d - (175.0d * d25)) * d25) - 768.0d) * d25) + 4096.0d)) + 1.0d;
            double d27 = (d25 / 1024.0d) * ((d25 * (((74.0d - (47.0d * d25)) * d25) - 128.0d)) + 256.0d);
            double d28 = 2.0955066698943685E-4d * d23 * (((4.0d - (d23 * 3.0d)) * 0.0033528106718309896d) + 4.0d);
            double d29 = d24 * d24;
            d16 = d27 * sqrt * (d24 + ((d27 / 4.0d) * ((((d29 * 2.0d) - 1.0d) * d21) - ((((d27 / 6.0d) * d24) * (((sqrt * 4.0d) * sqrt) - 3.0d)) * ((d29 * 4.0d) - 3.0d)))));
            double d30 = d10 + ((1.0d - d28) * 0.0033528106718309896d * d22 * (d15 + (sqrt * d28 * (d24 + (d28 * d21 * (((2.0d * d24) * d24) - 1.0d))))));
            if (Math.abs((d30 - d13) / d30) < 1.0E-12d) {
                d14 = d26;
                break;
            }
            i2++;
            sin = d6;
            sin2 = d7;
            d13 = d30;
            d14 = d26;
        }
        fArr[0] = (float) (6356752.3142d * d14 * (d15 - d16));
        if (fArr.length > 1) {
            double d31 = d7 * cos;
            double d32 = d6;
            double atan22 = (float) Math.atan2(cos2 * d18, d31 - ((d32 * cos2) * d17));
            Double.isNaN(atan22);
            fArr[1] = (float) (atan22 * 57.29577951308232d);
            if (fArr.length > 2) {
                double atan23 = (float) Math.atan2(cos * d18, ((-d32) * cos2) + (d31 * d17));
                Double.isNaN(atan23);
                fArr[2] = (float) (atan23 * 57.29577951308232d);
            }
        }
    }

    public static float b(GeoPoint geoPoint, GeoPoint geoPoint2) {
        if (geoPoint == null || geoPoint2 == null) {
            return 0.0f;
        }
        float[] fArr = new float[10];
        double latitudeE6 = geoPoint.getLatitudeE6();
        Double.isNaN(latitudeE6);
        double longitudeE6 = geoPoint.getLongitudeE6();
        Double.isNaN(longitudeE6);
        double latitudeE62 = geoPoint2.getLatitudeE6();
        Double.isNaN(latitudeE62);
        double longitudeE62 = geoPoint2.getLongitudeE6();
        Double.isNaN(longitudeE62);
        b(latitudeE6 / 1000000.0d, longitudeE6 / 1000000.0d, latitudeE62 / 1000000.0d, longitudeE62 / 1000000.0d, fArr);
        return fArr[1];
    }

    public static int b(double d2) {
        return (int) (((Math.log(Math.tan((d2 + 90.0d) * f16463h)) / f16462g) * f16461f) + 3.0240971E7d);
    }

    public static int b(int i2) {
        return i2 - b;
    }

    public static Point b(GeoPoint geoPoint) {
        if (geoPoint != null) {
            double longitudeE6 = geoPoint.getLongitudeE6();
            Double.isNaN(longitudeE6);
            int c2 = c(longitudeE6 / 1000000.0d);
            double latitudeE6 = geoPoint.getLatitudeE6();
            Double.isNaN(latitudeE6);
            return new Point(c2, b(latitudeE6 / 1000000.0d));
        }
        throw new IllegalArgumentException("point is null");
    }

    public static GeoPoint b(int i2, int i3) {
        double d2 = i2;
        Double.isNaN(d2);
        double d3 = i3;
        Double.isNaN(d3);
        return new GeoPoint((int) (Math.toDegrees((Math.atan(Math.exp(3.141592653589793d - (d3 / 4.272282972352698E7d))) - 0.7853981633974483d) * 2.0d) * 1000000.0d), (int) (Math.toDegrees((d2 / 4.272282972352698E7d) - 3.141592653589793d) * 1000000.0d));
    }

    public static void b(double d2, double d3, double d4, double d5, float[] fArr) {
        if (fArr == null || fArr.length < 1) {
            throw new IllegalArgumentException("results is null or has length < 1");
        }
        Location.distanceBetween(d2, d3, d4, d5, fArr);
        fArr[0] = (float) a(d2, d3, d4, d5);
    }

    public static double c(int i2) {
        double d2 = i2 - a;
        Double.isNaN(d2);
        return d2 / f16461f;
    }

    public static int c(double d2) {
        return (int) ((d2 * f16461f) + 2.0037508E7d);
    }

    public static Point c(int i2, int i3) {
        double d2 = i2;
        Double.isNaN(d2);
        int a2 = a(c(d2 / 1000000.0d));
        double d3 = i3;
        Double.isNaN(d3);
        return new Point(a2, b(b(d3 / 1000000.0d)));
    }

    public static Point c(GeoPoint geoPoint) {
        if (geoPoint != null) {
            double longitudeE6 = geoPoint.getLongitudeE6();
            Double.isNaN(longitudeE6);
            int a2 = a(c(longitudeE6 / 1000000.0d));
            double latitudeE6 = geoPoint.getLatitudeE6();
            Double.isNaN(latitudeE6);
            return new Point(a2, b(b(latitudeE6 / 1000000.0d)));
        }
        throw new IllegalArgumentException("point is null");
    }

    public static double d(int i2) {
        double d2 = i2 - b;
        Double.isNaN(d2);
        return (Math.atan(Math.exp((d2 / f16461f) * f16462g)) * f16464i) - 90.0d;
    }

    public static GeoPoint d(int i2, int i3) {
        return new GeoPoint((int) (d(f(i3)) * 1000000.0d), (int) (c(e(i2)) * 1000000.0d));
    }

    public static LatLng d(GeoPoint geoPoint) {
        if (geoPoint == null) {
            return null;
        }
        double latitudeE6 = geoPoint.getLatitudeE6();
        Double.isNaN(latitudeE6);
        double longitudeE6 = geoPoint.getLongitudeE6();
        Double.isNaN(longitudeE6);
        return new LatLng(latitudeE6 / 1000000.0d, longitudeE6 / 1000000.0d);
    }

    public static int e(int i2) {
        return i2 + a;
    }

    public static int f(int i2) {
        return i2 + b;
    }
}
