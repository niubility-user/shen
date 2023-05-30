package c.t.m.g;

/* loaded from: classes.dex */
public class y {
    public final int a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final int f759c;
    public final int d;

    /* renamed from: e  reason: collision with root package name */
    public final long f760e;

    /* renamed from: f  reason: collision with root package name */
    public final int f761f;

    /* loaded from: classes.dex */
    public enum a {
        NONE,
        GSM,
        CDMA,
        WCDMA,
        LTE,
        NR,
        TEMP6,
        TEMP7,
        NOSIM
    }

    public y(int i2, int i3, int i4, long j2, int i5, int i6) {
        this.a = i2;
        this.b = i3;
        this.f759c = i4;
        this.f760e = j2;
        this.d = i5;
        this.f761f = i6;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && y.class == obj.getClass()) {
            y yVar = (y) obj;
            if (this.a == yVar.a && this.b == yVar.b && this.f759c == yVar.f759c && this.f760e == yVar.f760e) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "CellCoreInfo{MCC=" + this.a + ", MNC=" + this.b + ", LAC=" + this.f759c + ", RSSI=" + this.d + ", CID=" + this.f760e + ", PhoneType=" + this.f761f + '}';
    }
}
