package com.xiaomi.push.service;

import android.util.Pair;
import com.xiaomi.push.b8;
import com.xiaomi.push.i7;
import com.xiaomi.push.j7;
import com.xiaomi.push.l7;
import com.xiaomi.push.n7;
import com.xiaomi.push.z7;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class d0 {
    public static int a(b0 b0Var, i7 i7Var) {
        return b0Var.b(i7Var, e0.a[i7Var.ordinal()] != 1 ? 0 : 1);
    }

    private static List<Pair<Integer, Object>> b(List<n7> list, boolean z) {
        if (com.xiaomi.push.d.a(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (n7 n7Var : list) {
            int a = n7Var.a();
            j7 a2 = j7.a(n7Var.b());
            if (a2 != null) {
                if (z && n7Var.f183a) {
                    arrayList.add(new Pair(Integer.valueOf(a), null));
                } else {
                    int i2 = e0.b[a2.ordinal()];
                    arrayList.add(i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? null : new Pair(Integer.valueOf(a), Boolean.valueOf(n7Var.g())) : new Pair(Integer.valueOf(a), n7Var.m106a()) : new Pair(Integer.valueOf(a), Long.valueOf(n7Var.m105a())) : new Pair(Integer.valueOf(a), Integer.valueOf(n7Var.c())));
                }
            }
        }
        return arrayList;
    }

    public static void c(b0 b0Var, z7 z7Var) {
        g.j.a.a.a.c.A("OnlineConfigHelper", "-->updateCustomConfigs(): onlineConfig=", b0Var, ", configMessage=", z7Var);
        b0Var.k(b(z7Var.a(), true));
        b0Var.n();
    }

    public static void d(b0 b0Var, b8 b8Var) {
        g.j.a.a.a.c.A("OnlineConfigHelper", "-->updateNormalConfigs(): onlineConfig=", b0Var, ", configMessage=", b8Var);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (l7 l7Var : b8Var.a()) {
            arrayList.add(new Pair<>(l7Var.m95a(), Integer.valueOf(l7Var.a())));
            List<Pair<Integer, Object>> b = b(l7Var.f175a, false);
            if (!com.xiaomi.push.d.a(b)) {
                arrayList2.addAll(b);
            }
        }
        b0Var.l(arrayList, arrayList2);
        b0Var.n();
    }
}
