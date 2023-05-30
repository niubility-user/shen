package c.t.m.g;

import android.app.PendingIntent;
import android.location.Location;
import com.tencent.map.geolocation.TencentGeofence;
import java.util.Locale;

/* loaded from: classes.dex */
public class g6 {
    public final TencentGeofence a;
    public final Location b;

    /* renamed from: c  reason: collision with root package name */
    public final long f456c;
    public final PendingIntent d;

    /* renamed from: e  reason: collision with root package name */
    public int f457e = 0;

    /* renamed from: f  reason: collision with root package name */
    public double f458f = Double.MAX_VALUE;

    /* renamed from: g  reason: collision with root package name */
    public Object f459g;

    public g6(TencentGeofence tencentGeofence, long j2, String str, PendingIntent pendingIntent) {
        this.a = tencentGeofence;
        this.f456c = j2;
        this.d = pendingIntent;
        Location location = new Location("");
        this.b = location;
        location.setLatitude(tencentGeofence.getLatitude());
        location.setLongitude(tencentGeofence.getLongitude());
        location.setTime(0L);
        location.setSpeed(-0.001f);
    }

    public double a() {
        if (Double.compare(this.f458f, Double.MAX_VALUE) == 0) {
            return Double.MAX_VALUE;
        }
        double radius = this.a.getRadius();
        double d = this.f458f;
        Double.isNaN(radius);
        return Math.abs(radius - d);
    }

    public final double b(double d, double d2, long j2, long j3) {
        if (j2 == 0) {
            return -0.0010000000474974513d;
        }
        if (d2 >= d) {
            return 0.0d;
        }
        long abs = Math.abs(j3 - j2) / 1000;
        double abs2 = Math.abs(d - d2);
        if (abs == 0) {
            abs++;
        }
        double d3 = abs;
        Double.isNaN(d3);
        return abs2 / d3;
    }

    public int c(Location location) {
        if (location == this.f459g) {
            return 0;
        }
        this.f459g = location;
        double d = this.f458f;
        double b = u0.b(location.getLatitude(), location.getLongitude(), this.b.getLatitude(), this.b.getLongitude());
        long time = this.b.getTime();
        long time2 = location.getTime();
        this.b.setTime(time2);
        this.b.setSpeed((float) b(d, b, time, time2));
        this.f458f = b;
        int i2 = this.f457e;
        if (b <= ((double) this.a.getRadius())) {
            this.f457e = 1;
            if (i2 != 1) {
                return 1;
            }
        } else {
            this.f457e = 2;
            if (i2 == 1) {
                return 2;
            }
        }
        return 0;
    }

    public float d() {
        float speed = this.b.getSpeed();
        if (speed <= -0.001f) {
            return -0.001f;
        }
        if (speed > 25.0f) {
            return 25.0f;
        }
        if (speed < 1.0f) {
            return 1.0f;
        }
        return speed;
    }

    public boolean e() {
        return this.f457e != 1 && this.b.getSpeed() >= 0.0f;
    }

    public String toString() {
        int i2 = this.f457e;
        return String.format(Locale.US, "%s dist=%5gm speed=%.2fm/s state=%s", this.a.toString(), Double.valueOf(this.f458f), Float.valueOf(d()), i2 != 1 ? i2 != 2 ? "?" : "OUT" : "IN");
    }
}
