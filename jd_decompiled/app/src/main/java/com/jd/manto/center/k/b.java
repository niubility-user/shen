package com.jd.manto.center.k;

import java.util.List;

/* loaded from: classes17.dex */
public class b {
    public static boolean a(Object... objArr) {
        for (Object obj : objArr) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(List list) {
        return list == null || list.size() == 0;
    }

    public static boolean c(List list) {
        return list != null && list.size() > 0;
    }
}
