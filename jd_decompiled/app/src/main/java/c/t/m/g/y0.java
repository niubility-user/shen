package c.t.m.g;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public class y0 {

    /* renamed from: e  reason: collision with root package name */
    public static y0 f769e = new y0();

    /* renamed from: f  reason: collision with root package name */
    public static final Comparator<byte[]> f770f = new a();
    public final List<byte[]> a = new ArrayList(32);
    public final List<byte[]> b = new ArrayList(32);

    /* renamed from: c  reason: collision with root package name */
    public int f771c = 0;
    public int d = 10240;

    /* loaded from: classes.dex */
    public static class a implements Comparator<byte[]> {
        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final int compare(byte[] bArr, byte[] bArr2) {
            return bArr.length - bArr2.length;
        }
    }

    public static y0 a() {
        return f769e;
    }

    public synchronized void b(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.d) {
                Arrays.fill(bArr, (byte) 0);
                this.a.add(bArr);
                int binarySearch = Collections.binarySearch(this.b, bArr, f770f);
                if (binarySearch < 0) {
                    binarySearch = (-binarySearch) - 1;
                }
                this.b.add(binarySearch, bArr);
                this.f771c += bArr.length;
                d();
            }
        }
    }

    public synchronized byte[] c(int i2) {
        for (int i3 = 0; i3 < this.b.size(); i3++) {
            byte[] bArr = this.b.get(i3);
            if (bArr.length == i2) {
                this.f771c -= bArr.length;
                this.b.remove(i3);
                this.a.remove(bArr);
                return bArr;
            }
        }
        return new byte[i2];
    }

    public final synchronized void d() {
        while (this.f771c > this.d) {
            byte[] remove = this.a.remove(0);
            this.b.remove(remove);
            this.f771c -= remove.length;
        }
    }
}
