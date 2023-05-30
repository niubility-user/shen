package c.t.m.g;

import com.jd.dynamic.DYConstants;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationUtils;
import java.util.LinkedList;
import java.util.ListIterator;

/* loaded from: classes.dex */
public class r5 {
    public int a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public LinkedList<a> f655c;
    public x1 d;

    /* loaded from: classes.dex */
    public static class a {
        public double a;
        public double b;

        /* renamed from: c  reason: collision with root package name */
        public long f656c;
        public int d;

        public static a a(TencentLocation tencentLocation) {
            a aVar = new a();
            aVar.a = tencentLocation.getLatitude();
            aVar.b = tencentLocation.getLongitude();
            aVar.f656c = tencentLocation.getTime();
            tencentLocation.getSpeed();
            boolean isFromGps = TencentLocationUtils.isFromGps(tencentLocation);
            int i2 = 2;
            float accuracy = tencentLocation.getAccuracy();
            if (isFromGps) {
                if (accuracy < 100.0f) {
                    i2 = 3;
                }
            } else if (accuracy >= 500.0f) {
                i2 = 1;
            }
            aVar.d = i2;
            return aVar;
        }

        public boolean b(a aVar) {
            double b = u0.b(this.a, this.b, aVar.a, aVar.b);
            double abs = Math.abs(this.f656c - aVar.f656c) + 1;
            Double.isNaN(abs);
            return b / (abs / 1000.0d) <= 100.0d;
        }

        public String toString() {
            return "[" + this.a + DYConstants.DY_REGEX_COMMA + this.b + "]";
        }
    }

    public r5(int i2, int i3) {
        if (i2 < i3) {
            throw new IllegalArgumentException("maxSize should >= coreSize");
        }
        if (i3 < 3) {
            throw new IllegalArgumentException("coreSize should >= 3");
        }
        this.f655c = new LinkedList<>();
        this.a = i2;
        this.b = i3;
        this.d = new x1();
    }

    public synchronized void a(q5 q5Var) {
        if (!q5Var.getProvider().equalsIgnoreCase("gps") || y2.f().i("gps_kalman")) {
            if (this.f655c.size() == 0) {
                return;
            }
            this.d.b(q5Var.getLatitude(), q5Var.getLongitude(), q5Var.getAccuracy(), q5Var.getTime());
            q5Var.n(this.d.a(), this.d.c());
        }
    }

    public synchronized void b(TencentLocation tencentLocation) {
        this.f655c.add(a.a(tencentLocation));
        if (this.f655c.size() > this.a) {
            this.f655c.removeFirst();
        }
    }

    public final boolean c() {
        return this.f655c.size() >= this.b;
    }

    public final synchronized boolean d(a aVar, y4 y4Var, boolean z) {
        if (y4Var != null) {
            LinkedList<a> linkedList = this.f655c;
            if (linkedList != null && linkedList.size() != 0) {
                int i2 = aVar.d;
                if (i2 == 3) {
                    return true;
                }
                if (i2 != 1 || k1.e(y4Var) || k1.f(y4Var) || z) {
                    if (aVar.f656c - this.f655c.getLast().f656c > 120000) {
                        this.f655c.clear();
                        return true;
                    }
                    if (c()) {
                        LinkedList<a> linkedList2 = this.f655c;
                        ListIterator<a> listIterator = linkedList2.listIterator(linkedList2.size());
                        int i3 = 0;
                        int i4 = 0;
                        while (listIterator.hasPrevious()) {
                            if (!listIterator.previous().b(aVar)) {
                                i3++;
                            }
                            i4++;
                            if (i4 > this.b) {
                                break;
                            }
                        }
                        if (i3 > 1) {
                            return false;
                        }
                    }
                    return true;
                }
                return true;
            }
        }
        return true;
    }

    public boolean e(TencentLocation tencentLocation, y4 y4Var, boolean z) {
        return d(a.a(tencentLocation), y4Var, z);
    }

    public synchronized void f() {
        this.f655c.clear();
        this.d.d();
    }
}
