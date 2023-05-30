package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.y7;
import java.util.Map;

/* loaded from: classes11.dex */
public class j {
    private static a a;
    private static b b;

    /* loaded from: classes11.dex */
    public interface a {
        Map<String, String> a(Context context, y7 y7Var);

        /* renamed from: a  reason: collision with other method in class */
        void m161a(Context context, y7 y7Var);

        boolean b(Context context, y7 y7Var, boolean z);
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a(y7 y7Var);

        void a(String str);

        /* renamed from: a  reason: collision with other method in class */
        boolean m162a(y7 y7Var);
    }

    public static Map<String, String> a(Context context, y7 y7Var) {
        a aVar = a;
        if (aVar == null || y7Var == null) {
            g.j.a.a.a.c.o("pepa listener or container is null");
            return null;
        }
        return aVar.a(context, y7Var);
    }

    public static void b(Context context, y7 y7Var) {
        a aVar = a;
        if (aVar == null || y7Var == null) {
            g.j.a.a.a.c.o("handle msg wrong");
        } else {
            aVar.m161a(context, y7Var);
        }
    }

    public static void c(y7 y7Var) {
        b bVar = b;
        if (bVar == null || y7Var == null) {
            g.j.a.a.a.c.o("pepa clearMessage is null");
        } else {
            bVar.a(y7Var);
        }
    }

    public static void d(String str) {
        b bVar = b;
        if (bVar == null || str == null) {
            g.j.a.a.a.c.o("pepa clearMessage is null");
        } else {
            bVar.a(str);
        }
    }

    public static boolean e(Context context, y7 y7Var, boolean z) {
        a aVar = a;
        if (aVar == null || y7Var == null) {
            g.j.a.a.a.c.o("pepa judement listener or container is null");
            return false;
        }
        return aVar.b(context, y7Var, z);
    }

    public static boolean f(y7 y7Var) {
        b bVar = b;
        if (bVar == null || y7Var == null) {
            g.j.a.a.a.c.o("pepa handleReceiveMessage is null");
            return false;
        }
        return bVar.m162a(y7Var);
    }
}
