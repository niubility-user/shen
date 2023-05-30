package com.tencent.mapsdk.internal;

import android.content.Context;
import com.tencent.mapsdk.internal.ca;
import java.io.File;

/* loaded from: classes9.dex */
public class ma {
    private static na a;

    public static String a() {
        na naVar = a;
        if (naVar != null) {
            return naVar.c();
        }
        return null;
    }

    public static void a(Context context, ca.a aVar) {
        na naVar = a;
        if (naVar != null) {
            naVar.a(context, aVar);
        }
    }

    public static void a(na naVar) {
        na naVar2 = a;
        if (naVar2 != naVar) {
            if (naVar2 != null) {
                naVar2.a();
            }
            a = naVar;
        }
    }

    public static void a(File file, String str, String str2) {
        na naVar = a;
        if (naVar != null) {
            naVar.a(file, str, str2);
        }
    }

    public static void a(String str) {
        na naVar = a;
        if (naVar != null) {
            naVar.f(str);
        } else {
            f(str);
        }
    }

    public static void a(String str, String str2) {
        na naVar = a;
        if (naVar != null) {
            naVar.f(str, str2);
        } else {
            e(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        na naVar = a;
        if (naVar != null) {
            naVar.b(str, str2, th);
        } else {
            d(str, str2, th);
        }
    }

    public static void a(String str, Throwable th) {
        na naVar = a;
        if (naVar != null) {
            naVar.b(str, th);
        } else {
            d(str, th);
        }
    }

    public static void a(boolean z, int i2, String... strArr) {
        na naVar = a;
        if (naVar != null) {
            naVar.a(z, i2, strArr);
        }
    }

    public static void a(boolean z, String... strArr) {
        na naVar = a;
        if (naVar != null) {
            naVar.a(z, strArr);
        }
    }

    public static boolean a(int i2, String str) {
        na naVar = a;
        if (naVar != null) {
            return naVar.a(i2, str);
        }
        return false;
    }

    public static void b(String str) {
        na naVar = a;
        if (naVar != null) {
            naVar.b(str);
        } else {
            f(str);
        }
    }

    public static void b(String str, String str2) {
        na naVar = a;
        if (naVar != null) {
            naVar.b(str, str2);
        } else {
            e(str, str2);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        na naVar = a;
        if (naVar != null) {
            naVar.d(str, str2, th);
        } else {
            d(str, str2, th);
        }
    }

    public static void b(String str, Throwable th) {
        na naVar = a;
        if (naVar != null) {
            naVar.e(str, th);
        } else {
            d(str, th);
        }
    }

    public static boolean b() {
        na naVar = a;
        if (naVar != null) {
            return naVar.b();
        }
        return false;
    }

    public static void c(String str) {
        na naVar = a;
        if (naVar != null) {
            naVar.a(str);
        } else {
            f(str);
        }
    }

    public static void c(String str, String str2) {
        na naVar = a;
        if (naVar != null) {
            naVar.a(str, str2);
        } else {
            e(str, str2);
        }
    }

    public static void c(String str, String str2, Throwable th) {
        na naVar = a;
        if (naVar != null) {
            naVar.c(str, str2, th);
        } else {
            d(str, str2, th);
        }
    }

    public static void c(String str, Throwable th) {
        na naVar = a;
        if (naVar != null) {
            naVar.a(str, th);
        } else {
            d(str, th);
        }
    }

    public static void d(String str, String str2) {
        na naVar = a;
        if (naVar != null) {
            naVar.d(str, str2);
        }
    }

    private static void d(String str, String str2, Throwable th) {
        f("[" + str + "]:" + str2 + th);
    }

    private static void d(String str, Throwable th) {
        f(str + th);
    }

    public static boolean d(String str) {
        na naVar = a;
        if (naVar != null) {
            return naVar.d(str);
        }
        return false;
    }

    public static void e(String str) {
        na naVar = a;
        if (naVar != null) {
            naVar.e(str);
        } else {
            f(str);
        }
    }

    private static void e(String str, String str2) {
        f("[" + str + "]:" + str2);
    }

    public static void e(String str, String str2, Throwable th) {
        na naVar = a;
        if (naVar != null) {
            naVar.e(str, str2, th);
        } else {
            d(str, str2, th);
        }
    }

    public static void e(String str, Throwable th) {
        na naVar = a;
        if (naVar != null) {
            naVar.d(str, th);
        } else {
            d(str, th);
        }
    }

    private static void f(String str) {
        System.out.println(str);
    }

    public static void f(String str, String str2) {
        na naVar = a;
        if (naVar != null) {
            naVar.c(str, str2);
        } else {
            e(str, str2);
        }
    }

    public static void f(String str, String str2, Throwable th) {
        na naVar = a;
        if (naVar != null) {
            naVar.a(str, str2, th);
        } else {
            d(str, str2, th);
        }
    }

    public static void f(String str, Throwable th) {
        na naVar = a;
        if (naVar != null) {
            naVar.c(str, th);
        } else {
            d(str, th);
        }
    }

    public static void g(String str) {
        na naVar = a;
        if (naVar != null) {
            naVar.c(str);
        } else {
            f(str);
        }
    }

    public static void g(String str, String str2) {
        na naVar = a;
        if (naVar != null) {
            naVar.e(str, str2);
        } else {
            e(str, str2);
        }
    }

    public static void h(String str) {
        na naVar = a;
        if (naVar != null) {
            naVar.g(str);
        } else {
            f(str);
        }
    }
}
