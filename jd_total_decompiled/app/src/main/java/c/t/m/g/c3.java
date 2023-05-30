package c.t.m.g;

import android.location.Location;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import java.util.LinkedList;

/* loaded from: classes.dex */
public class c3 {

    /* renamed from: e  reason: collision with root package name */
    public static c3 f327e = new c3();
    public LinkedList<n> a;
    public LinkedList<a> b;

    /* renamed from: c  reason: collision with root package name */
    public int f328c;
    public double[] d;

    /* loaded from: classes.dex */
    public class a {
        public long a;
    }

    /* loaded from: classes.dex */
    public enum b {
        UNKNOW,
        MOVE,
        STATIC
    }

    public c3() {
        b bVar = b.UNKNOW;
        this.a = new LinkedList<>();
        this.b = new LinkedList<>();
        this.f328c = -1;
        this.d = new double[]{0.0d, 0.0d};
    }

    public static c3 e() {
        return f327e;
    }

    public final boolean a(Location location, Location location2) {
        if (location.getLatitude() == 0.0d || location.getLongitude() == 0.0d) {
            return false;
        }
        return Math.abs(location.getLatitude() - location2.getLatitude()) >= 1.0E-7d || Math.abs(location.getLongitude() - location2.getLongitude()) >= 1.0E-7d;
    }

    public final boolean b(n nVar) {
        while (this.a.size() > 9) {
            this.a.remove(0);
        }
        while (this.a.size() > 0) {
            n first = this.a.getFirst();
            long j2 = nVar.b - first.b;
            double b2 = u0.b(first.a.getLatitude(), first.a.getLongitude(), nVar.a.getLatitude(), nVar.a.getLongitude());
            if (j2 <= 180000 || b2 <= 500.0d) {
                break;
            }
            this.a.remove(0);
        }
        int size = this.a.size();
        if (size >= 5) {
            int i2 = 0;
            for (int i3 = size - 2; i3 >= 0; i3--) {
                if (!a(nVar.a, this.a.get(i3).a)) {
                    i2++;
                }
            }
            return i2 < 4 || nVar.a.getAccuracy() >= 20.0f;
        }
        return true;
    }

    public double[] c() {
        double[] dArr = this.d;
        double d = 0.0d;
        dArr[0] = 0.0d;
        dArr[1] = 0.0d;
        if (this.a.size() < 2) {
            return this.d;
        }
        int size = this.a.size();
        double d2 = 0.0d;
        long j2 = 0;
        for (int i2 = 1; i2 < size; i2++) {
            n nVar = this.a.get(i2);
            n nVar2 = this.a.get(i2 - 1);
            d2 += u0.b(nVar2.a.getLatitude(), nVar2.a.getLongitude(), nVar.a.getLatitude(), nVar.a.getLongitude());
            j2 += nVar.b - nVar2.b;
            double speed = nVar2.a.getSpeed();
            Double.isNaN(speed);
            d += speed;
        }
        double speed2 = this.a.getLast().a.getSpeed();
        Double.isNaN(speed2);
        double d3 = d + speed2;
        if (j2 > 0) {
            double[] dArr2 = this.d;
            double d4 = size;
            Double.isNaN(d4);
            dArr2[0] = d3 / d4;
            double d5 = j2;
            Double.isNaN(d5);
            dArr2[1] = (d2 / d5) * 1000.0d;
        }
        return this.d;
    }

    public synchronized int d(n nVar) {
        int i2;
        if (nVar != null) {
            this.a.add(new n(nVar));
            if ((!b(r0)) == false) {
                i2 = f() ? -1 : -2;
            }
            return i2;
        }
        return this.a.size();
    }

    public final boolean f() {
        if (this.b.isEmpty()) {
            return true;
        }
        double[] c2 = c();
        return System.currentTimeMillis() - this.b.getLast().a >= Final.FIVE_SECOND || this.f328c != 1 || (c2[0] <= 5.0d && c2[1] <= 5.0d);
    }
}
