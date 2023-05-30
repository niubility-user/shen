package c.t.m.g;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public class g1 {

    /* renamed from: e  reason: collision with root package name */
    public static g1 f431e = new g1();

    /* renamed from: f  reason: collision with root package name */
    public static final Comparator<double[]> f432f = new a();
    public final List<double[]> a = new ArrayList(32);
    public final List<double[]> b = new ArrayList(32);

    /* renamed from: c  reason: collision with root package name */
    public int f433c = 0;
    public int d = 4096;

    /* loaded from: classes.dex */
    public static class a implements Comparator<double[]> {
        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final int compare(double[] dArr, double[] dArr2) {
            return dArr.length - dArr2.length;
        }
    }

    public static g1 a() {
        return f431e;
    }

    public synchronized void b(double[] dArr) {
        if (dArr != null) {
            if (dArr.length <= this.d) {
                Arrays.fill(dArr, 0.0d);
                this.a.add(dArr);
                int binarySearch = Collections.binarySearch(this.b, dArr, f432f);
                if (binarySearch < 0) {
                    binarySearch = (-binarySearch) - 1;
                }
                this.b.add(binarySearch, dArr);
                this.f433c += dArr.length;
                d();
            }
        }
    }

    public synchronized double[] c(int i2) {
        for (int i3 = 0; i3 < this.b.size(); i3++) {
            double[] dArr = this.b.get(i3);
            if (dArr.length == i2) {
                this.f433c -= dArr.length;
                this.b.remove(i3);
                this.a.remove(dArr);
                return dArr;
            }
        }
        return new double[i2];
    }

    public final synchronized void d() {
        while (this.f433c > this.d) {
            double[] remove = this.a.remove(0);
            this.b.remove(remove);
            this.f433c -= remove.length;
        }
    }
}
