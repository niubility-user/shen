package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.g7;
import com.xiaomi.push.m8;
import com.xiaomi.push.n9;
import com.xiaomi.push.u9;
import com.xiaomi.push.y5;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes11.dex */
public class f1 {
    public static final Object a = new Object();

    public static void a(Context context, g7 g7Var) {
        if (e1.f(g7Var.e())) {
            com.xiaomi.push.i.b(context).g(new g1(context, g7Var));
        }
    }

    public static byte[] b(Context context) {
        String d = n9.b(context).d("mipush", "td_key", "");
        if (TextUtils.isEmpty(d)) {
            d = com.xiaomi.push.p0.a(20);
            n9.b(context).e("mipush", "td_key", d);
        }
        return c(d);
    }

    private static byte[] c(String str) {
        byte[] copyOf = Arrays.copyOf(com.xiaomi.push.m0.b(str), 16);
        copyOf[0] = 68;
        copyOf[15] = 84;
        return copyOf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.Closeable] */
    public static void e(Context context, g7 g7Var) {
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2;
        String str;
        String str2;
        BufferedOutputStream b = b(context);
        try {
            try {
                byte[] c2 = y5.c(b, m8.f(g7Var));
                if (c2 != null && c2.length >= 1) {
                    if (c2.length > 10240) {
                        str2 = "TinyData write to cache file failed case too much data content item:" + g7Var.d() + "  ts:" + System.currentTimeMillis();
                        g.j.a.a.a.c.o(str2);
                        u9.b(null);
                        u9.b(null);
                    }
                    BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(new FileOutputStream(new File(context.getFilesDir(), "tiny_data.data"), true));
                    try {
                        bufferedOutputStream3.write(com.xiaomi.push.c.b(c2.length));
                        bufferedOutputStream3.write(c2);
                        bufferedOutputStream3.flush();
                        u9.b(null);
                        u9.b(bufferedOutputStream3);
                        return;
                    } catch (IOException e2) {
                        bufferedOutputStream2 = bufferedOutputStream3;
                        e = e2;
                        str = "TinyData write to cache file failed cause io exception item:" + g7Var.d();
                        b = bufferedOutputStream2;
                        g.j.a.a.a.c.q(str, e);
                        u9.b(null);
                        u9.b(b);
                        return;
                    } catch (Exception e3) {
                        bufferedOutputStream = bufferedOutputStream3;
                        e = e3;
                        str = "TinyData write to cache file  failed item:" + g7Var.d();
                        b = bufferedOutputStream;
                        g.j.a.a.a.c.q(str, e);
                        u9.b(null);
                        u9.b(b);
                        return;
                    } catch (Throwable th) {
                        b = bufferedOutputStream3;
                        th = th;
                        u9.b(null);
                        u9.b(b);
                        throw th;
                    }
                }
                str2 = "TinyData write to cache file failed case encryption fail item:" + g7Var.d() + "  ts:" + System.currentTimeMillis();
                g.j.a.a.a.c.o(str2);
                u9.b(null);
                u9.b(null);
            } catch (IOException e4) {
                e = e4;
                bufferedOutputStream2 = null;
            } catch (Exception e5) {
                e = e5;
                bufferedOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                b = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
