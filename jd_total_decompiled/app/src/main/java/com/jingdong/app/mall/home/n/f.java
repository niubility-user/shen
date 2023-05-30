package com.jingdong.app.mall.home.n;

import android.text.TextUtils;
import android.util.SparseArray;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class f {
    private static Map<String, a> a = new HashMap(64);
    private static SparseArray<a> b = new SparseArray<>(64);

    /* renamed from: c */
    private static Map<String, b> f10350c = new HashMap(32);
    private static SparseArray<b> d = new SparseArray<>(32);

    static {
        for (a aVar : a.values()) {
            aVar.parseFloorType(a, b);
        }
        for (b bVar : b.values()) {
            bVar.parseFloorType(f10350c, d);
        }
    }

    public static a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return a.C_EMPTY;
        }
        a aVar = a.get(str);
        return aVar == null ? a.C_EMPTY : aVar;
    }

    public static synchronized b b(int i2) {
        b bVar;
        synchronized (f.class) {
            bVar = d.get(i2);
        }
        return bVar;
    }

    public static synchronized a c(int i2) {
        a aVar;
        synchronized (f.class) {
            aVar = b.get(i2);
        }
        return aVar;
    }

    public static void d() {
    }
}
