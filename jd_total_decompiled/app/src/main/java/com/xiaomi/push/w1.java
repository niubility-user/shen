package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes11.dex */
public class w1 {
    public static int a(Context context, int i2) {
        int a = u6.a(context);
        if (-1 == a) {
            return -1;
        }
        return (i2 * (a == 0 ? 13 : 11)) / 10;
    }

    public static int b(c7 c7Var) {
        return c4.a(c7Var.a());
    }

    public static int c(n8 n8Var, c7 c7Var) {
        int a;
        switch (x1.a[c7Var.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return c4.a(c7Var.a());
            case 11:
                a = c4.a(c7Var.a());
                if (n8Var != null) {
                    try {
                        if (n8Var instanceof t7) {
                            String str = ((t7) n8Var).d;
                            if (!TextUtils.isEmpty(str) && c4.b(c4.i(str)) != -1) {
                                a = c4.b(c4.i(str));
                                break;
                            }
                        } else if (n8Var instanceof c8) {
                            String str2 = ((c8) n8Var).d;
                            if (!TextUtils.isEmpty(str2)) {
                                if (c4.b(c4.i(str2)) != -1) {
                                    a = c4.b(c4.i(str2));
                                }
                                if (m7.UploadTinyData.equals(c4.i(str2))) {
                                    return -1;
                                }
                            }
                        }
                    } catch (Exception unused) {
                        g.j.a.a.a.c.D("PERF_ERROR : parse Notification type error");
                        return a;
                    }
                }
                break;
            case 12:
                a = c4.a(c7Var.a());
                if (n8Var != null) {
                    try {
                        if (n8Var instanceof x7) {
                            String b = ((x7) n8Var).b();
                            if (!TextUtils.isEmpty(b) && l4.a(b) != -1) {
                                a = l4.a(b);
                                break;
                            }
                        } else if (n8Var instanceof w7) {
                            String a2 = ((w7) n8Var).a();
                            if (!TextUtils.isEmpty(a2) && l4.a(a2) != -1) {
                                return l4.a(a2);
                            }
                        }
                    } catch (Exception unused2) {
                        g.j.a.a.a.c.D("PERF_ERROR : parse Command type error");
                        break;
                    }
                }
                break;
            default:
                return -1;
        }
        return a;
    }

    public static void d(String str, Context context, int i2, int i3) {
        if (i2 <= 0 || i3 <= 0) {
            return;
        }
        int a = a(context, i3);
        if (i2 != c4.b(m7.UploadTinyData)) {
            d4.a(context.getApplicationContext()).c(str, i2, 1L, a);
        }
    }

    public static void e(String str, Context context, y7 y7Var, int i2) {
        c7 a;
        if (context == null || y7Var == null || (a = y7Var.a()) == null) {
            return;
        }
        int b = b(a);
        if (i2 <= 0) {
            byte[] f2 = m8.f(y7Var);
            i2 = f2 != null ? f2.length : 0;
        }
        d(str, context, b, i2);
    }

    public static void f(String str, Context context, n8 n8Var, c7 c7Var, int i2) {
        d(str, context, c(n8Var, c7Var), i2);
    }

    public static void g(String str, Context context, byte[] bArr) {
        if (context == null || bArr == null || bArr.length <= 0) {
            return;
        }
        y7 y7Var = new y7();
        try {
            m8.e(y7Var, bArr);
            e(str, context, y7Var, bArr.length);
        } catch (s8 unused) {
            g.j.a.a.a.c.o("fail to convert bytes to container");
        }
    }
}
