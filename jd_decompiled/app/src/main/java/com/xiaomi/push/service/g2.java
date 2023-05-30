package com.xiaomi.push.service;

import com.xiaomi.push.a8;
import com.xiaomi.push.c8;
import com.xiaomi.push.r9;

/* loaded from: classes11.dex */
public class g2 {
    private static a a;

    /* loaded from: classes11.dex */
    public interface a {
        boolean a(c8 c8Var);
    }

    /* loaded from: classes11.dex */
    public interface b {
    }

    public static void a(b bVar) {
    }

    public static boolean b(c8 c8Var) {
        String str;
        if (a == null || c8Var == null) {
            str = "rc params is null, not cpra";
        } else if (a8.j(r9.b())) {
            return a.a(c8Var);
        } else {
            str = "rc app not permission to cpra";
        }
        g.j.a.a.a.c.o(str);
        return false;
    }
}
