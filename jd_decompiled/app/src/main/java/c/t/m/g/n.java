package c.t.m.g;

import android.location.Location;

/* loaded from: classes.dex */
public class n extends e0 {
    public final Location a;
    public final long b;

    /* renamed from: c  reason: collision with root package name */
    public final int f549c;
    public final int d;

    /* renamed from: e  reason: collision with root package name */
    public final int f550e;

    /* renamed from: f  reason: collision with root package name */
    public final a f551f;

    /* loaded from: classes.dex */
    public enum a {
        NONE,
        GPS,
        PDR,
        VDR
    }

    public n(Location location, long j2, int i2, int i3, int i4, a aVar) {
        this.a = location;
        this.b = j2;
        this.f549c = i2;
        this.d = i3;
        this.f550e = i4;
        this.f551f = aVar;
    }

    public n(n nVar) {
        this.a = nVar.a == null ? null : new Location(nVar.a);
        this.b = nVar.b;
        this.f549c = nVar.f549c;
        this.d = nVar.d;
        this.f550e = nVar.f550e;
        this.f551f = nVar.f551f;
    }

    public String toString() {
        return "TxGpsInfo [location=" + this.a + ", gpsTime=" + this.b + ", visbleSatelliteNum=" + this.f549c + ", usedSatelliteNum=" + this.d + ", gpsStatus=" + this.f550e + "]";
    }
}
