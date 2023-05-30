package com.tencent.mapsdk.internal;

import android.graphics.PointF;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Pair;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.map.tools.IndexCallback;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Coordinate;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes9.dex */
public class wa {
    public static double a(double d) {
        return (d * 3.141592653589793d) / 180.0d;
    }

    public static double a(double d, double d2, double d3, double d4, double d5, double d6) {
        return ((((d2 - d4) * d5) + ((d3 - d) * d6)) + (d * d4)) - (d3 * d2);
    }

    public static double a(double d, int i2) {
        try {
            return new BigDecimal(d).setScale(i2, 4).doubleValue();
        } catch (Exception unused) {
            return d;
        }
    }

    public static double a(Coordinate coordinate, Coordinate coordinate2) {
        double x = coordinate.x() - coordinate2.x();
        double y = coordinate.y() - coordinate2.y();
        return Math.sqrt((x * x) + (y * y));
    }

    public static double a(Coordinate coordinate, Coordinate coordinate2, double d, boolean z, Coordinate coordinate3) {
        double a = (a(coordinate, coordinate2) * 0.5d) / Math.sin(a(0.5d * d));
        double x = (coordinate.x() + coordinate2.x()) / 2.0d;
        double y = (coordinate.y() + coordinate2.y()) / 2.0d;
        double sqrt = Math.sqrt((Math.pow(a, 2.0d) / (Math.pow(coordinate.x() - coordinate2.x(), 2.0d) + Math.pow(coordinate.y() - coordinate2.y(), 2.0d))) - 0.25d);
        double y2 = (coordinate.y() - coordinate2.y()) * sqrt;
        double x2 = (coordinate2.x() - coordinate.x()) * sqrt;
        if (d - 90.0d <= 1.0E-6d) {
            y2 = -y2;
            x2 = -x2;
        }
        if (Double.isNaN(y2)) {
            y2 = 0.0d;
        }
        if (Double.isNaN(x2)) {
            x2 = 0.0d;
        }
        double d2 = z ? x + y2 : x - y2;
        double d3 = z ? y + x2 : y - x2;
        coordinate3.setX(d2);
        coordinate3.setY(d3);
        return a;
    }

    public static double a(Coordinate coordinate, Coordinate coordinate2, Coordinate coordinate3) {
        return Math.atan((a(coordinate, coordinate2) / 2.0d) / c(coordinate3, coordinate, coordinate2)) * 2.0d;
    }

    public static double a(Coordinate coordinate, Coordinate coordinate2, Coordinate coordinate3, Coordinate coordinate4) {
        double x = (coordinate2.x() - coordinate.x()) * 2.0d;
        double y = (coordinate2.y() - coordinate.y()) * 2.0d;
        double x2 = (((coordinate2.x() * coordinate2.x()) + (coordinate2.y() * coordinate2.y())) - (coordinate.x() * coordinate.x())) - (coordinate.y() * coordinate.y());
        double x3 = (coordinate3.x() - coordinate2.x()) * 2.0d;
        double y2 = (coordinate3.y() - coordinate2.y()) * 2.0d;
        double x4 = (((coordinate3.x() * coordinate3.x()) + (coordinate3.y() * coordinate3.y())) - (coordinate2.x() * coordinate2.x())) - (coordinate2.y() * coordinate2.y());
        double d = (y2 * x) - (y * x3);
        double d2 = ((x2 * y2) - (x4 * y)) / d;
        double d3 = ((x * x4) - (x3 * x2)) / d;
        coordinate4.setX(d2);
        coordinate4.setY(d3);
        return Math.sqrt(Math.pow(coordinate.x() - d2, 2.0d) + Math.pow(coordinate.y() - d3, 2.0d));
    }

    public static float a(float f2, int i2) {
        try {
            return new BigDecimal(f2).setScale(i2, 4).floatValue();
        } catch (Exception unused) {
            return f2;
        }
    }

    public static int a(int i2, int i3) {
        int i4 = i3 - i2;
        if (i4 > 0) {
            return new SecureRandom().nextInt(i4) + i2;
        }
        if (i4 == 0) {
            return i2;
        }
        return 0;
    }

    public static PointF a(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4) {
        float f2 = pointF2.x;
        float f3 = pointF.x;
        if (f2 != f3) {
            float f4 = pointF4.x;
            float f5 = pointF3.x;
            if (f4 != f5) {
                float f6 = pointF2.y;
                float f7 = pointF.y;
                float f8 = f2 - f3;
                float f9 = (f6 - f7) / f8;
                float f10 = pointF4.y;
                float f11 = pointF3.y;
                float f12 = f4 - f5;
                float f13 = (f10 - f11) / f12;
                if (f9 == f13) {
                    return null;
                }
                float f14 = ((f11 * f4) - (f10 * f5)) / f12;
                float f15 = (f14 - (((f7 * f2) - (f6 * f3)) / f8)) / (f9 - f13);
                return new PointF(f15, (f13 * f15) + f14);
            }
        }
        return null;
    }

    public static Rect a(GeoPoint geoPoint, Rect rect) {
        int max = Math.max(Math.abs(rect.left - geoPoint.getLongitudeE6()), Math.abs(rect.right - geoPoint.getLongitudeE6()));
        int max2 = Math.max(Math.abs(rect.top - geoPoint.getLatitudeE6()), Math.abs(rect.bottom - geoPoint.getLatitudeE6()));
        return new Rect(geoPoint.getLongitudeE6() - max2, geoPoint.getLatitudeE6() - max, geoPoint.getLongitudeE6() + max2, geoPoint.getLatitudeE6() + max);
    }

    public static Rect a(GeoPoint geoPoint, GeoPoint geoPoint2) {
        if (geoPoint == null || geoPoint2 == null || geoPoint.equals(geoPoint2)) {
            return null;
        }
        int abs = Math.abs(geoPoint.getLongitudeE6() - geoPoint2.getLongitudeE6());
        int abs2 = Math.abs(geoPoint.getLatitudeE6() - geoPoint2.getLatitudeE6());
        return new Rect(geoPoint.getLongitudeE6() - abs, geoPoint.getLatitudeE6() - abs2, geoPoint.getLongitudeE6() + abs, geoPoint.getLatitudeE6() + abs2);
    }

    public static GeoPoint a(GeoPoint geoPoint, GeoPoint geoPoint2, GeoPoint geoPoint3) {
        int latitudeE6 = geoPoint2.getLatitudeE6() - geoPoint.getLatitudeE6();
        int longitudeE6 = geoPoint2.getLongitudeE6() - geoPoint.getLongitudeE6();
        if (latitudeE6 == 0 && longitudeE6 == 0) {
            return null;
        }
        double latitudeE62 = ((geoPoint3.getLatitudeE6() - geoPoint.getLatitudeE6()) * latitudeE6) + ((geoPoint3.getLongitudeE6() - geoPoint.getLongitudeE6()) * longitudeE6);
        double d = (latitudeE6 * latitudeE6) + (longitudeE6 * longitudeE6);
        Double.isNaN(latitudeE62);
        Double.isNaN(d);
        double d2 = latitudeE62 / d;
        if (d2 < 0.0d) {
            return new GeoPoint(geoPoint);
        }
        if (d2 > 1.0d) {
            return new GeoPoint(geoPoint2);
        }
        int latitudeE63 = geoPoint.getLatitudeE6();
        double d3 = latitudeE6;
        Double.isNaN(d3);
        int i2 = latitudeE63 + ((int) (d3 * d2));
        int longitudeE62 = geoPoint.getLongitudeE6();
        double d4 = longitudeE6;
        Double.isNaN(d4);
        return new GeoPoint(i2, longitudeE62 + ((int) (d4 * d2)));
    }

    public static LatLng a(LatLng latLng, LatLng latLng2, int i2) {
        double d = latLng2.longitude;
        double d2 = latLng.longitude;
        int i3 = (d > d2 ? 1 : (d == d2 ? 0 : -1));
        double d3 = i2;
        Double.isNaN(d3);
        double d4 = i3 > 0 ? d2 - d3 : d2 + d3;
        double d5 = latLng2.latitude;
        double d6 = latLng.latitude;
        int i4 = (d5 > d6 ? 1 : (d5 == d6 ? 0 : -1));
        double d7 = i2;
        Double.isNaN(d7);
        return new LatLng(i4 > 0 ? d6 - d7 : d6 + d7, d4);
    }

    public static String a() {
        byte[] bArr = new byte[20];
        new SecureRandom().nextBytes(bArr);
        return new String(bArr);
    }

    public static String a(File file) {
        int i2;
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_1);
                    byte[] bArr = new byte[10485760];
                    while (true) {
                        int read = fileInputStream2.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        messageDigest.update(bArr, 0, read);
                    }
                    String bigInteger = new BigInteger(1, messageDigest.digest()).toString(16);
                    int length = 40 - bigInteger.length();
                    if (length > 0) {
                        for (i2 = 0; i2 < length; i2++) {
                            bigInteger = "0" + bigInteger;
                        }
                    }
                    ga.a((Closeable) fileInputStream2);
                    return bigInteger;
                } catch (IOException e2) {
                    e = e2;
                    fileInputStream = fileInputStream2;
                    e.printStackTrace();
                    ga.a((Closeable) fileInputStream);
                    return "";
                } catch (NoSuchAlgorithmException e3) {
                    e = e3;
                    fileInputStream = fileInputStream2;
                    e.printStackTrace();
                    ga.a((Closeable) fileInputStream);
                    return "";
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    ga.a((Closeable) fileInputStream);
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
            } catch (NoSuchAlgorithmException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? str : va.a(str.getBytes());
    }

    public static <T extends Coordinate> void a(T t, double d, T t2, T t3, boolean z, IndexCallback<Pair<Double, Double>> indexCallback) {
        if (indexCallback == null) {
            return;
        }
        double b = b(t, t2);
        boolean z2 = t.x() > t2.x() ? !z : z;
        int i2 = 0;
        double d2 = 0.0d;
        double d3 = 0.0d;
        while (i2 < 360) {
            double d4 = i2;
            Double.isNaN(d4);
            double tan = Math.tan(a(z ? b - d4 : d4 + b));
            if (d3 != d2 && d3 * tan < d2 && Math.abs(tan) > 1.0d) {
                z2 = !z2;
            }
            double sqrt = d / Math.sqrt((tan * tan) + 1.0d);
            double d5 = tan * sqrt;
            ma.a(la.f16819f, i2 + ":curDeltaK[" + tan + "]deltaKChanged[" + z2 + "]clockwise[" + z + "]deltaX[" + sqrt + "]deltaY[" + d5 + "]");
            if (z2) {
                sqrt = -sqrt;
                d5 = -d5;
            }
            double x = t.x();
            double d6 = z ? x - sqrt : x + sqrt;
            double y = t.y();
            double d7 = z ? y - d5 : y + d5;
            double a = a(t2.x(), t2.y(), t3.x(), t3.y(), d6, d7);
            if (z) {
                if (a <= 0.0d) {
                    i2++;
                    d3 = tan;
                    d2 = 0.0d;
                }
                indexCallback.callback(i2, new Pair<>(Double.valueOf(d6), Double.valueOf(d7)));
                i2++;
                d3 = tan;
                d2 = 0.0d;
            } else {
                if (a >= 0.0d) {
                    i2++;
                    d3 = tan;
                    d2 = 0.0d;
                }
                indexCallback.callback(i2, new Pair<>(Double.valueOf(d6), Double.valueOf(d7)));
                i2++;
                d3 = tan;
                d2 = 0.0d;
            }
        }
    }

    public static boolean a(LatLng latLng, double d, LatLng latLng2, LatLng latLng3) {
        if (c((Coordinate) latLng, (Coordinate) latLng2, (Coordinate) latLng3) - d > 1.0E-6d) {
            return false;
        }
        return c(b(latLng, latLng2, latLng3), latLng2, latLng3);
    }

    public static double[] a(LatLng latLng, LatLng latLng2) {
        double d;
        double d2 = Double.NaN;
        if (Math.abs(latLng.longitude - latLng2.longitude) < 1.0E-6d) {
            d = Double.NaN;
        } else if (Math.abs(latLng.latitude - latLng2.latitude) < 1.0E-6d) {
            d2 = 0.0d;
            d = latLng.latitude;
        } else {
            double d3 = latLng2.latitude;
            double d4 = latLng.latitude;
            double d5 = latLng2.longitude;
            double d6 = latLng.longitude;
            double d7 = (d3 - d4) / (d5 - d6);
            d = ((d5 * d4) - (d6 * d3)) / (d5 - d4);
            d2 = d7;
        }
        return new double[]{d2, d};
    }

    public static double[] a(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        double d;
        double[] a = a(latLng2, latLng3);
        double d2 = Double.NaN;
        if (Double.isNaN(a[0])) {
            d = latLng.latitude;
            d2 = 0.0d;
        } else if (a[0] == 0.0d) {
            d = Double.NaN;
        } else {
            d2 = (-1.0d) / a[0];
            d = latLng.latitude + ((1.0d / a[0]) * latLng.longitude);
        }
        return new double[]{d2, d};
    }

    public static double b(double d) {
        double d2 = d % 360.0d;
        return d2 > 180.0d ? d2 - 360.0d : d2 < -180.0d ? d2 + 360.0d : d2;
    }

    public static double b(Coordinate coordinate, Coordinate coordinate2) {
        return c(Math.atan(c(coordinate, coordinate2)));
    }

    public static double b(Coordinate coordinate, Coordinate coordinate2, Coordinate coordinate3) {
        return a(coordinate, coordinate2, coordinate3) * a(coordinate, coordinate3);
    }

    public static LatLng b(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        double d;
        double d2;
        double[] a = a(latLng2, latLng3);
        if (Double.isNaN(a[0])) {
            d2 = latLng2.longitude;
            d = latLng.latitude;
        } else if (a[0] == 0.0d) {
            double d3 = latLng.longitude;
            double d4 = latLng2.latitude;
            d2 = d3;
            d = d4;
        } else {
            double d5 = a[0];
            double d6 = d5 * d5;
            double d7 = latLng2.longitude;
            double d8 = latLng.latitude;
            double d9 = latLng2.latitude;
            double d10 = (((d6 * d7) + ((d8 - d9) * d5)) + latLng.longitude) / (d6 + 1.0d);
            d = (d5 * (d10 - d7)) + d9;
            d2 = d10;
        }
        return new LatLng(d, d2);
    }

    public static double c(double d) {
        return (d * 180.0d) / 3.141592653589793d;
    }

    public static double c(Coordinate coordinate, Coordinate coordinate2) {
        return (coordinate.y() - coordinate2.y()) / (coordinate.x() - coordinate2.x());
    }

    public static double c(Coordinate coordinate, Coordinate coordinate2, Coordinate coordinate3) {
        double a = a(coordinate, coordinate2);
        double a2 = a(coordinate, coordinate3);
        double a3 = a(coordinate2, coordinate3);
        double d = a + a2;
        if (d == a3) {
            return 0.0d;
        }
        if (a3 <= 1.0E-6d) {
            return a;
        }
        double d2 = a2 * a2;
        double d3 = a * a;
        double d4 = a3 * a3;
        if (d2 >= d3 + d4) {
            return a;
        }
        if (d3 >= d2 + d4) {
            return a2;
        }
        double d5 = (d + a3) / 2.0d;
        return (Math.sqrt((((d5 - a) * d5) * (d5 - a2)) * (d5 - a3)) * 2.0d) / a3;
    }

    public static boolean c(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        return (a((Coordinate) latLng2, (Coordinate) latLng3) - a((Coordinate) latLng, (Coordinate) latLng2)) - a((Coordinate) latLng, (Coordinate) latLng3) < 1.0E-6d;
    }
}
