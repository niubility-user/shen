package c.t.m.g;

import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.tencent.map.geolocation.util.SoUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class v {

    /* renamed from: e  reason: collision with root package name */
    public static long f710e;
    public final b1 a;
    public final f b;

    /* renamed from: c  reason: collision with root package name */
    public final n f711c;
    public final List<w6> d;

    public v(b1 b1Var, f fVar, n nVar, List<w6> list) {
        this.a = b1Var;
        this.b = fVar;
        this.f711c = nVar;
        this.d = list;
    }

    @Nullable
    public b1 a() {
        return this.a;
    }

    public final b1 b(b1 b1Var) {
        ArrayList arrayList = new ArrayList(b1Var.a());
        Collections.sort(arrayList, k1.f509g);
        return new b1(arrayList, b1Var.d(), b1Var.e());
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0039 A[Catch: all -> 0x021f, TRY_ENTER, TryCatch #3 {all -> 0x021f, blocks: (B:5:0x000a, B:13:0x001c, B:16:0x002c, B:21:0x0039, B:22:0x003b, B:26:0x0057, B:29:0x005e, B:17:0x0031, B:8:0x0010, B:12:0x001a), top: B:74:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005e A[Catch: all -> 0x021f, TRY_LEAVE, TryCatch #3 {all -> 0x021f, blocks: (B:5:0x000a, B:13:0x001c, B:16:0x002c, B:21:0x0039, B:22:0x003b, B:26:0x0057, B:29:0x005e, B:17:0x0031, B:8:0x0010, B:12:0x001a), top: B:74:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String c(int r18, int r19, java.lang.String r20, c.t.m.g.y4 r21, boolean r22, boolean r23, boolean r24) {
        /*
            Method dump skipped, instructions count: 545
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.v.c(int, int, java.lang.String, c.t.m.g.y4, boolean, boolean, boolean):java.lang.String");
    }

    public String d(int i2, String str, y4 y4Var, boolean z, boolean z2, boolean z3) {
        return c(i2, 0, str, y4Var, z, z2, z3);
    }

    @RequiresApi(api = 17)
    public final b1 e(b1 b1Var) {
        if (b1Var == null) {
            return null;
        }
        boolean z = Build.VERSION.SDK_INT >= 17;
        List<ScanResult> a = b1Var.a();
        if (a == null || a.size() == 0) {
            return null;
        }
        int[] iArr = new int[a.size()];
        int[] iArr2 = new int[a.size()];
        for (int i2 = 0; i2 < a.size(); i2++) {
            iArr[i2] = a.get(i2).level;
            iArr2[i2] = z ? (int) ((SystemClock.elapsedRealtime() / 1000) - ((a.get(i2).timestamp / 1000) / 1000)) : 0;
        }
        int[] fun_s = SoUtils.fun_s(iArr, iArr2, a.size(), z);
        ArrayList arrayList = new ArrayList();
        for (int i3 : fun_s) {
            arrayList.add(a.get(i3));
        }
        return new b1(arrayList, b1Var.d(), b1Var.e());
    }

    public boolean f() {
        return this.f711c != null;
    }
}
