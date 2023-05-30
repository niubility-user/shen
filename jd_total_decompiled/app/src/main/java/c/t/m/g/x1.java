package c.t.m.g;

/* loaded from: classes.dex */
public class x1 {
    public float b;

    /* renamed from: g  reason: collision with root package name */
    public double f742g;

    /* renamed from: j  reason: collision with root package name */
    public double f745j;
    public double d = -1.0d;

    /* renamed from: e  reason: collision with root package name */
    public double f740e = -1.0d;

    /* renamed from: f  reason: collision with root package name */
    public double f741f = -1.0d;
    public float a = -1.0f;

    /* renamed from: c  reason: collision with root package name */
    public long f739c = -1;

    /* renamed from: h  reason: collision with root package name */
    public double f743h = 0.0d;

    /* renamed from: i  reason: collision with root package name */
    public double f744i = 0.0d;

    public double a() {
        return this.d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0208, code lost:
        if ((r13 - r28.d) <= 0.0d) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0217, code lost:
        if ((r13 - r28.d) < 0.0d) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0219, code lost:
        r9 = r28.d;
        r11 = r7 / 1000;
        java.lang.Double.isNaN(r11);
        r28.d = r9 + (r5 * r11);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void b(double d, double d2, double d3, long j2) {
        long j3;
        double d4;
        double d5;
        double d6;
        double d7;
        double d8 = d3 < 1.0d ? 1.0d : d3;
        StringBuilder sb = new StringBuilder("lat_me:");
        sb.append(d);
        sb.append(",lng_me:");
        sb.append(d2);
        sb.append(",accuracy:");
        sb.append(d8);
        sb.append(",time:");
        sb.append(j2);
        sb.append(",lat:");
        sb.append(this.d);
        sb.append(",lng:");
        sb.append(this.f740e);
        if (j2 - this.f739c >= 20000) {
            StringBuilder sb2 = new StringBuilder("Time:");
            sb2.append(j2);
            sb2.append(",last_time:");
            sb2.append(this.f739c);
            d();
        }
        this.a = (float) (Math.abs(d - this.d) * 1000000.0d);
        this.b = (float) (Math.abs(d2 - this.f740e) * 1000000.0d);
        StringBuilder sb3 = new StringBuilder("Q:");
        sb3.append(this.a);
        sb3.append(",QLng:");
        sb3.append(this.b);
        double d9 = this.f741f;
        if (d9 < 0.0d) {
            this.f739c = j2;
            this.d = d;
            this.f740e = d2;
            this.f741f = d8 * d8;
            return;
        }
        long j4 = j2 - this.f739c;
        if (j4 < 1000) {
            j4 = 1000;
        }
        if (j4 > 0) {
            double d10 = j4;
            Double.isNaN(d10);
            this.f741f = d9 + d10;
            double d11 = this.f742g;
            Double.isNaN(d10);
            this.f742g = d11 + d10;
        }
        double d12 = this.f741f;
        double d13 = d8 * d8;
        double d14 = d8;
        double d15 = this.a * 5.0f;
        Double.isNaN(d15);
        double d16 = d12 / ((d12 + d13) + d15);
        double d17 = this.f742g;
        double d18 = d17 + d13;
        double d19 = this.b * 5.0f;
        Double.isNaN(d19);
        double d20 = d17 / (d18 + d19);
        StringBuilder sb4 = new StringBuilder("K:");
        sb4.append(d16);
        sb4.append(",KLng:");
        sb4.append(d20);
        if (d16 < 0.4d || d20 < 0.4d) {
            j3 = j2;
            d4 = d20;
            d5 = d16;
            double d21 = this.f743h;
            if (d21 > 0.0d) {
                d6 = d;
            } else {
                d6 = d;
            }
            if (d21 < 0.0d) {
            }
            double d22 = this.f744i;
            if ((d22 > 0.0d && d2 - this.f740e > 0.0d) || (d22 < 0.0d && d2 - this.f740e < 0.0d)) {
                double d23 = this.f740e;
                double d24 = j4 / 1000;
                Double.isNaN(d24);
                this.f740e = d23 + (d22 * d24);
            }
            double d25 = this.f741f;
            double d26 = j4;
            Double.isNaN(d26);
            this.f741f = d25 - d26;
            double d27 = this.f742g;
            Double.isNaN(d26);
            this.f742g = d27 - d26;
        } else {
            double d28 = this.d;
            double d29 = this.f743h;
            if ((d29 <= 0.0d || d - d28 <= 0.0d) && (d29 >= 0.0d || d - d28 >= 0.0d)) {
                d4 = d20;
            } else {
                d4 = d20;
                double d30 = j4 / 1000;
                Double.isNaN(d30);
                this.d = (d29 * d30) + d28;
            }
            double d31 = this.d;
            this.d = d31 + ((d - d31) * d16);
            StringBuilder sb5 = new StringBuilder("lat:");
            sb5.append(this.d);
            sb5.append(",tmp:");
            sb5.append(d28);
            sb5.append(",timeInc:");
            sb5.append(j4);
            double d32 = this.d - d28;
            double d33 = j4 / 1000;
            Double.isNaN(d33);
            this.f743h = d32 / d33;
            double d34 = this.f740e;
            d5 = d16;
            double d35 = this.f744i;
            if ((d35 > 0.0d && d2 - d34 > 0.0d) || (d35 < 0.0d && d2 - d34 < 0.0d)) {
                Double.isNaN(d33);
                this.f740e = (d35 * d33) + d34;
            }
            double d36 = this.f740e;
            this.f740e = d36 + ((d2 - d36) * d4);
            StringBuilder sb6 = new StringBuilder("lng:");
            sb6.append(this.f740e);
            sb6.append(",tmp:");
            sb6.append(d34);
            sb6.append(",timeInc:");
            sb6.append(j4);
            Double.isNaN(d33);
            this.f744i = (this.f740e - d34) / d33;
            this.f741f = (1.0d - d5) * this.f741f;
            this.f742g = (1.0d - d4) * this.f742g;
            j3 = j2;
            this.f739c = j3;
            StringBuilder sb7 = new StringBuilder("last_metres_per_second:");
            sb7.append(this.f743h);
            sb7.append(",last_metres_per_second_lng:");
            sb7.append(this.f744i);
            d6 = d;
        }
        StringBuilder sb8 = new StringBuilder("variance:");
        sb8.append(this.f741f);
        sb8.append(",vaLng:");
        sb8.append(this.f742g);
        if (d14 != 30.0d || d5 < 0.5d || d4 < 0.5d) {
            d7 = d13;
        } else {
            this.d = d6;
            this.f740e = d2;
            this.f743h = 0.0d;
            this.f744i = 0.0d;
            this.f739c = j3;
            d7 = d13;
            this.f741f = d7;
        }
        if (this.f745j > 100.0d && d14 <= 30.0d) {
            this.d = d6;
            this.f740e = d2;
            this.f743h = 0.0d;
            this.f744i = 0.0d;
            this.f739c = j3;
            this.f741f = d7;
        }
        this.f745j = d14;
    }

    public double c() {
        return this.f740e;
    }

    public void d() {
        this.f741f = -1.0d;
        this.a = -1.0f;
        this.f739c = -1L;
        this.f743h = 0.0d;
        this.f744i = 0.0d;
    }
}
