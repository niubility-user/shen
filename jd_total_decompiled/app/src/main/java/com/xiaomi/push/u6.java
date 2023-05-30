package com.xiaomi.push;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes11.dex */
public class u6 {
    private static l a = new l(true);
    private static volatile int b = -1;

    /* renamed from: c  reason: collision with root package name */
    private static long f19258c = System.currentTimeMillis();
    private static final Object d = new Object();

    /* renamed from: e  reason: collision with root package name */
    private static List<a> f19259e = Collections.synchronizedList(new ArrayList());

    /* renamed from: f  reason: collision with root package name */
    private static String f19260f = "";

    /* renamed from: g  reason: collision with root package name */
    private static com.xiaomi.push.providers.a f19261g = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class a {
        public String a;
        public long b;

        /* renamed from: c  reason: collision with root package name */
        public int f19262c;
        public int d;

        /* renamed from: e  reason: collision with root package name */
        public String f19263e;

        /* renamed from: f  reason: collision with root package name */
        public long f19264f;

        public a(String str, long j2, int i2, int i3, String str2, long j3) {
            this.a = "";
            this.b = 0L;
            this.f19262c = -1;
            this.d = -1;
            this.f19263e = "";
            this.f19264f = 0L;
            this.a = str;
            this.b = j2;
            this.f19262c = i2;
            this.d = i3;
            this.f19263e = str2;
            this.f19264f = j3;
        }

        public boolean a(a aVar) {
            return TextUtils.equals(aVar.a, this.a) && TextUtils.equals(aVar.f19263e, this.f19263e) && aVar.f19262c == this.f19262c && aVar.d == this.d && Math.abs(aVar.b - this.b) <= Final.FIVE_SECOND;
        }
    }

    public static int a(Context context) {
        if (b == -1) {
            b = n(context);
        }
        return b;
    }

    public static int b(String str) {
        try {
            return str.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes().length;
        }
    }

    private static long c(int i2, long j2, boolean z, long j3, boolean z2) {
        if (z && z2) {
            long j4 = f19258c;
            f19258c = j3;
            if (j3 - j4 > Final.HALF_MINUTE && j2 > 1024) {
                return j2 * 2;
            }
        }
        return (j2 * (i2 == 0 ? 13 : 11)) / 10;
    }

    private static com.xiaomi.push.providers.a d(Context context) {
        com.xiaomi.push.providers.a aVar = f19261g;
        if (aVar != null) {
            return aVar;
        }
        com.xiaomi.push.providers.a aVar2 = new com.xiaomi.push.providers.a(context);
        f19261g = aVar2;
        return aVar2;
    }

    private static synchronized String f(Context context) {
        synchronized (u6.class) {
            if (TextUtils.isEmpty(f19260f)) {
                return "";
            }
            return f19260f;
        }
    }

    public static void h(Context context) {
        b = n(context);
    }

    private static void i(Context context, String str, long j2, boolean z, long j3) {
        int a2;
        boolean isEmpty;
        if (context == null || TextUtils.isEmpty(str) || !"com.xiaomi.xmsf".equals(context.getPackageName()) || "com.xiaomi.xmsf".equals(str) || -1 == (a2 = a(context))) {
            return;
        }
        synchronized (d) {
            isEmpty = f19259e.isEmpty();
            l(new a(str, j3, a2, z ? 1 : 0, a2 == 0 ? f(context) : "", j2));
        }
        if (isEmpty) {
            a.f(new v6(context), Final.FIVE_SECOND);
        }
    }

    public static void j(Context context, String str, long j2, boolean z, boolean z2, long j3) {
        i(context, str, c(a(context), j2, z, j3, z2), z, j3);
    }

    private static void l(a aVar) {
        for (a aVar2 : f19259e) {
            if (aVar2.a(aVar)) {
                aVar2.f19264f += aVar.f19264f;
                return;
            }
        }
        f19259e.add(aVar);
    }

    public static synchronized void m(String str) {
        synchronized (u6.class) {
            if (!a8.t() && !TextUtils.isEmpty(str)) {
                f19260f = str;
            }
        }
    }

    private static int n(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return -1;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return -1;
            }
            return activeNetworkInfo.getType();
        } catch (Exception unused) {
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void o(Context context, List<a> list) {
        try {
            synchronized (com.xiaomi.push.providers.a.f18951h) {
                SQLiteDatabase writableDatabase = d(context).getWritableDatabase();
                writableDatabase.beginTransaction();
                for (a aVar : list) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("package_name", aVar.a);
                    contentValues.put("message_ts", Long.valueOf(aVar.b));
                    contentValues.put("network_type", Integer.valueOf(aVar.f19262c));
                    contentValues.put("bytes", Long.valueOf(aVar.f19264f));
                    contentValues.put("rcv", Integer.valueOf(aVar.d));
                    contentValues.put("imsi", aVar.f19263e);
                    writableDatabase.insert("traffic", null, contentValues);
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
            }
        } catch (Throwable th) {
            g.j.a.a.a.c.s(th);
        }
    }
}
