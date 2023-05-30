package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.xiaomi.push.a6;
import com.xiaomi.push.s9;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes11.dex */
public class w2 {
    private static final Map<String, byte[]> a = new HashMap();
    private static ArrayList<Pair<String, byte[]>> b = new ArrayList<>();

    public static void a(Context context, int i2, String str) {
        Map<String, byte[]> map = a;
        synchronized (map) {
            for (String str2 : map.keySet()) {
                g.j.a.a.a.c.o("notify registration error. " + str2);
                b(context, str2, a.get(str2), i2, str);
            }
            a.clear();
        }
    }

    public static void b(Context context, String str, byte[] bArr, int i2, String str2) {
        Intent intent = new Intent("com.xiaomi.mipush.ERROR");
        intent.setPackage(str);
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mipush_error_code", i2);
        intent.putExtra("mipush_error_msg", str2);
        context.sendBroadcast(intent, k.g(str));
    }

    public static void c(XMPushService xMPushService) {
        ArrayList<Pair<String, byte[]>> arrayList;
        try {
            synchronized (b) {
                arrayList = b;
                b = new ArrayList<>();
            }
            boolean c2 = s9.c();
            Iterator<Pair<String, byte[]>> it = arrayList.iterator();
            while (it.hasNext()) {
                Pair<String, byte[]> next = it.next();
                k.l(xMPushService, (String) next.first, (byte[]) next.second);
                if (!c2) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        } catch (a6 e2) {
            g.j.a.a.a.c.D("meet error when process pending message. " + e2);
            xMPushService.a(10, e2);
        }
    }

    public static void d(XMPushService xMPushService, boolean z) {
        try {
            Map<String, byte[]> map = a;
            synchronized (map) {
                for (String str : map.keySet()) {
                    g.j.a.a.a.c.o("processing pending registration request. " + str);
                    k.l(xMPushService, str, a.get(str));
                    if (z && !s9.c()) {
                        try {
                            Thread.sleep(200L);
                        } catch (Exception unused) {
                        }
                    }
                }
                a.clear();
            }
        } catch (a6 e2) {
            g.j.a.a.a.c.D("fail to deal with pending register request. " + e2);
            xMPushService.a(10, e2);
        }
    }

    public static void e(String str, byte[] bArr) {
        Map<String, byte[]> map = a;
        synchronized (map) {
            g.j.a.a.a.c.o("pending registration request. " + str);
            map.put(str, bArr);
        }
    }

    public static void f(String str, byte[] bArr) {
        synchronized (b) {
            b.add(new Pair<>(str, bArr));
            if (b.size() > 50) {
                b.remove(0);
            }
        }
    }
}
