package com.xiaomi.push.service;

import android.app.NotificationChannel;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.hms.framework.common.ContainerUtils;
import com.xiaomi.push.a8;
import com.xiaomi.push.r9;
import com.xiaomi.push.s9;
import com.xiaomi.push.service.z;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class c2 {
    private static final int[] a = {1, 2, 4, 8, 16};
    private static final SparseArray<z.a<String, String, String>> b = new d2(5);

    /* renamed from: c  reason: collision with root package name */
    private static final SparseArray<Integer> f19073c = new e2(5);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(String str, String str2) {
        int i2 = m(str, str2, 8) ? 8 : 0;
        if (m(str, str2, 16)) {
            i2 |= 16;
        }
        if (m(str, str2, 1)) {
            i2 |= 1;
        }
        if (m(str, str2, 2)) {
            i2 |= 2;
        }
        return m(str, str2, 4) ? i2 | 4 : i2;
    }

    public static int b(String str, String str2, int i2) {
        return z.c(r9.b(), str, str2, b.get(i2));
    }

    private static SharedPreferences c(Context context) {
        return context.getSharedPreferences("ch_permission_cache_file", 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(Context context, String str) {
        List<NotificationChannel> l2;
        if (!a8.j(context) || TextUtils.isEmpty(str) || (l2 = y.e(context, str).l()) == null) {
            return;
        }
        synchronized (c2.class) {
            SharedPreferences c2 = c(context);
            ArrayList arrayList = new ArrayList();
            Iterator<NotificationChannel> it = l2.iterator();
            while (it.hasNext()) {
                String str2 = (String) com.xiaomi.push.k0.d(it.next(), "mId");
                if (!TextUtils.isEmpty(str2) && c2.contains(str2)) {
                    arrayList.add(str2);
                }
            }
            if (arrayList.size() > 0) {
                i(c2, arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(Context context, String str, String str2, int i2, String str3, boolean z, int i3) {
        if (!a8.j(context) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            if (a8.j(context)) {
                g.j.a.a.a.c.o("ChannelPC: can`t setup permission with permissionCode:" + String.valueOf(str3) + " channelId:" + String.valueOf(str2) + " targetPkg:" + str);
                return;
            }
            return;
        }
        int a2 = s9.a(str3, 0);
        boolean l2 = l(i2, a2);
        if (z) {
            j(str, str2, a2, i3);
            if (l2) {
                synchronized (c2.class) {
                    f(c(context), a2, str2);
                }
                return;
            }
            return;
        }
        synchronized (c2.class) {
            SharedPreferences c2 = c(context);
            if (l2 || c2.contains(str2)) {
                g(c2, a2, str, str2, i3);
                if (l2) {
                    f(c2, a2, str2);
                } else {
                    h(c2, str2);
                }
            }
        }
    }

    private static void f(SharedPreferences sharedPreferences, int i2, String str) {
        sharedPreferences.edit().putInt(str, i2).commit();
    }

    private static void g(SharedPreferences sharedPreferences, int i2, String str, String str2, int i3) {
        if (sharedPreferences.getInt(str2, 0) != i2) {
            j(str, str2, i2, i3);
        }
    }

    private static void h(SharedPreferences sharedPreferences, String str) {
        i(sharedPreferences, new f2(str));
    }

    private static void i(SharedPreferences sharedPreferences, List<String> list) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            edit.remove(it.next());
        }
        edit.commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void j(String str, String str2, int i2, int i3) {
        for (int i4 : a) {
            if ((f19073c.get(i4).intValue() & i3) == 0) {
                k(str, str2, i4, (i2 & i4) > 0);
            } else {
                g.j.a.a.a.c.o("ChannelPermissions.grantPermission:" + str + ":" + str2 + ": <" + i4 + "> :stoped by userLock");
            }
        }
    }

    private static void k(String str, String str2, int i2, boolean z) {
        g.j.a.a.a.c.o("ChannelPermissions.grantPermission:" + str + ":" + str2 + ": <" + i2 + ContainerUtils.KEY_VALUE_DELIMITER + z + "> :" + z.q(r9.b(), str, str2, b.get(i2), z));
    }

    private static boolean l(int i2, int i3) {
        return i2 >= 4 || (i3 & 2) > 0 || (i3 & 1) > 0 || (i3 & 8) > 0 || (i3 & 16) > 0;
    }

    private static boolean m(String str, String str2, int i2) {
        boolean z = z.c(r9.b(), str, str2, b.get(i2)) == 1;
        g.j.a.a.a.c.o("ChannelPermissions.checkPermission:" + str + ":" + str2 + ": <" + i2 + ContainerUtils.KEY_VALUE_DELIMITER + z + ">");
        return z;
    }
}
