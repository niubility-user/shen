package c.t.m.g;

import android.net.wifi.ScanResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class b1 extends e0 {
    public static final b1 d = new b1(Collections.emptyList(), 0, 0);
    public final List<ScanResult> a;
    public final long b;

    /* renamed from: c  reason: collision with root package name */
    public final int f315c;

    public b1(List<ScanResult> list, long j2, int i2) {
        this.b = j2;
        this.f315c = i2;
        this.a = new ArrayList(list);
    }

    public List<ScanResult> a() {
        return Collections.unmodifiableList(this.a);
    }

    public boolean b(long j2, long j3) {
        return j2 - this.b < j3;
    }

    public boolean c(b1 b1Var) {
        if (b1Var == null) {
            return false;
        }
        List<ScanResult> list = b1Var.a;
        List<ScanResult> list2 = this.a;
        if (list == null || list2 == null || list.size() == 0 || list2.size() == 0) {
            return false;
        }
        return !z5.a(list, list2);
    }

    public long d() {
        return this.b;
    }

    public int e() {
        return this.f315c;
    }
}
