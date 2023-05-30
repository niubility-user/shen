package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.c8;
import com.xiaomi.push.f7;
import com.xiaomi.push.g7;
import com.xiaomi.push.m7;
import com.xiaomi.push.m8;
import com.xiaomi.push.r9;
import com.xiaomi.push.u9;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes11.dex */
public class e1 {
    private static AtomicLong a = new AtomicLong(0);
    private static SimpleDateFormat b;

    /* renamed from: c */
    private static String f19080c;

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        b = simpleDateFormat;
        f19080c = simpleDateFormat.format(Long.valueOf(System.currentTimeMillis()));
    }

    private static c8 a(String str, String str2, f7 f7Var) {
        return new c8("-1", false).d(str).b(str2).a(u9.h(m8.f(f7Var))).c(m7.UploadTinyData.f179a);
    }

    public static synchronized String b() {
        String str;
        synchronized (e1.class) {
            String format = b.format(Long.valueOf(System.currentTimeMillis()));
            if (!TextUtils.equals(f19080c, format)) {
                a.set(0L);
                f19080c = format;
            }
            str = format + "-" + a.incrementAndGet();
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:127:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<c8> c(List<g7> list, String str, String str2, int i2) {
        int i3;
        String str3;
        if (list == null) {
            str3 = "requests can not be null in TinyDataHelper.transToThriftObj().";
        } else if (list.size() != 0) {
            ArrayList<c8> arrayList = new ArrayList<>();
            f7 f7Var = new f7();
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                g7 g7Var = list.get(i5);
                if (g7Var != null) {
                    if (g7Var.m61a() == null || !g7Var.m61a().containsKey("item_size")) {
                        i3 = 0;
                    } else {
                        String str4 = g7Var.m61a().get("item_size");
                        if (!TextUtils.isEmpty(str4)) {
                            try {
                                i3 = Integer.parseInt(str4);
                            } catch (Exception unused) {
                            }
                            if (g7Var.m61a().size() != 1) {
                                g7Var.a((Map<String, String>) null);
                            } else {
                                g7Var.m61a().remove("item_size");
                            }
                        }
                        i3 = 0;
                        if (g7Var.m61a().size() != 1) {
                        }
                    }
                    if (i3 <= 0) {
                        i3 = m8.f(g7Var).length;
                    }
                    if (i3 > i2) {
                        g.j.a.a.a.c.D("TinyData is too big, ignore upload request item:" + g7Var.d());
                    } else {
                        if (i4 + i3 > i2) {
                            arrayList.add(a(str, str2, f7Var));
                            f7Var = new f7();
                            i4 = 0;
                        }
                        f7Var.a(g7Var);
                        i4 += i3;
                    }
                }
            }
            if (f7Var.a() != 0) {
                arrayList.add(a(str, str2, f7Var));
            }
            return arrayList;
        } else {
            str3 = "requests.length is 0 in TinyDataHelper.transToThriftObj().";
        }
        g.j.a.a.a.c.D(str3);
        return null;
    }

    public static void d(Context context, String str, String str2, long j2, String str3) {
        g7 g7Var = new g7();
        g7Var.d(str);
        g7Var.c(str2);
        g7Var.a(j2);
        g7Var.b(str3);
        g7Var.a("push_sdk_channel");
        g7Var.g(context.getPackageName());
        g7Var.e(context.getPackageName());
        g7Var.a(true);
        g7Var.b(System.currentTimeMillis());
        g7Var.f(b());
        f1.a(context, g7Var);
    }

    public static boolean e(g7 g7Var, boolean z) {
        String str;
        if (g7Var == null) {
            str = "item is null, verfiy ClientUploadDataItem failed.";
        } else if (!z && TextUtils.isEmpty(g7Var.f142a)) {
            str = "item.channel is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (TextUtils.isEmpty(g7Var.d)) {
            str = "item.category is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (TextUtils.isEmpty(g7Var.f18666c)) {
            str = "item.name is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (!com.xiaomi.push.p0.i(g7Var.d)) {
            str = "item.category can only contain ascii char, verfiy ClientUploadDataItem failed.";
        } else if (com.xiaomi.push.p0.i(g7Var.f18666c)) {
            String str2 = g7Var.f146b;
            if (str2 == null || str2.length() <= 10240) {
                return false;
            }
            str = "item.data is too large(" + g7Var.f146b.length() + "), max size for data is 10240 , verfiy ClientUploadDataItem failed.";
        } else {
            str = "item.name can only contain ascii char, verfiy ClientUploadDataItem failed.";
        }
        g.j.a.a.a.c.o(str);
        return true;
    }

    public static boolean f(String str) {
        return !r9.i() || "com.miui.hybrid".equals(str);
    }
}
