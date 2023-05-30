package com.tencent.map.geolocation;

import android.os.SystemClock;
import android.text.TextUtils;
import java.util.Locale;

/* loaded from: classes9.dex */
public class TencentGeofence {
    public final int a;
    public final double b;

    /* renamed from: c  reason: collision with root package name */
    public final double f16166c;
    public final float d;

    /* renamed from: e  reason: collision with root package name */
    public final long f16167e;

    /* renamed from: f  reason: collision with root package name */
    public final String f16168f;

    /* renamed from: g  reason: collision with root package name */
    public final long f16169g;

    /* loaded from: classes9.dex */
    public static class Builder {
        public double a;
        public double b;

        /* renamed from: c  reason: collision with root package name */
        public float f16170c;
        public long d;

        /* renamed from: e  reason: collision with root package name */
        public String f16171e;

        public static void a(double d, double d2) {
            if (d > 90.0d || d < -90.0d) {
                throw new IllegalArgumentException("invalid latitude: ".concat(String.valueOf(d)));
            }
            if (d2 > 180.0d || d2 < -180.0d) {
                throw new IllegalArgumentException("invalid longitude: ".concat(String.valueOf(d2)));
            }
        }

        public static void a(float f2) {
            if (f2 <= 0.0f) {
                throw new IllegalArgumentException("invalid radius: ".concat(String.valueOf(f2)));
            }
        }

        public static void a(long j2) {
            if (j2 < 0) {
                throw new IllegalArgumentException("invalid duration: ".concat(String.valueOf(j2)));
            }
        }

        public TencentGeofence build() {
            return new TencentGeofence(0, this.a, this.b, this.f16170c, this.d, this.f16171e);
        }

        public Builder setCircularRegion(double d, double d2, float f2) {
            a(f2);
            a(d, d2);
            this.a = d;
            this.b = d2;
            this.f16170c = f2;
            return this;
        }

        public Builder setExpirationDuration(long j2) {
            a(j2);
            this.d = j2;
            return this;
        }

        public Builder setTag(String str) {
            if (TextUtils.isEmpty(str)) {
                throw null;
            }
            this.f16171e = str;
            return this;
        }
    }

    public TencentGeofence(int i2, double d, double d2, float f2, long j2, String str) {
        this.a = i2;
        this.b = d;
        this.f16166c = d2;
        this.d = f2;
        this.f16169g = j2;
        this.f16167e = SystemClock.elapsedRealtime() + j2;
        this.f16168f = str;
    }

    public static void a(int i2) {
        if (i2 != 0) {
            throw new IllegalArgumentException("invalid type: ".concat(String.valueOf(i2)));
        }
    }

    public static String b(int i2) {
        if (i2 != 0) {
            a(i2);
            return null;
        }
        return "CIRCLE";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && TencentGeofence.class == obj.getClass()) {
            TencentGeofence tencentGeofence = (TencentGeofence) obj;
            if (Double.doubleToLongBits(this.b) == Double.doubleToLongBits(tencentGeofence.b) && Double.doubleToLongBits(this.f16166c) == Double.doubleToLongBits(tencentGeofence.f16166c)) {
                String str = this.f16168f;
                String str2 = tencentGeofence.f16168f;
                if (str == null) {
                    if (str2 != null) {
                        return false;
                    }
                } else if (!str.equals(str2)) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public long getDuration() {
        return this.f16169g;
    }

    public long getExpireAt() {
        return this.f16167e;
    }

    public double getLatitude() {
        return this.b;
    }

    public double getLongitude() {
        return this.f16166c;
    }

    public float getRadius() {
        return this.d;
    }

    public String getTag() {
        return this.f16168f;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.b);
        long doubleToLongBits2 = Double.doubleToLongBits(this.f16166c);
        int i2 = (((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31;
        String str = this.f16168f;
        return i2 + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        Locale locale = Locale.US;
        double elapsedRealtime = this.f16167e - SystemClock.elapsedRealtime();
        Double.isNaN(elapsedRealtime);
        return String.format(locale, "TencentGeofence[tag=%s, type=%s, loc=(%.6f, %.6f), radius=%.2fm life=%.2fs]", this.f16168f, b(this.a), Double.valueOf(this.b), Double.valueOf(this.f16166c), Float.valueOf(this.d), Double.valueOf(elapsedRealtime / 1000.0d));
    }
}
