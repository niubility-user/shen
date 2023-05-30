package com.jingdong.sdk.talos;

import android.content.Context;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.jingdong.sdk.talos.inner.e;
import com.jingdong.sdk.talos.inner.f;
import com.jingdong.sdk.talos.inner.l;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a {
    private static com.jingdong.sdk.talos.inner.b a;
    private static Context b;

    /* renamed from: c */
    private static b f15459c;

    public static void a(String str, String str2) {
        n(3, str, str2, null);
    }

    public static void b(String str, String str2, Throwable th) {
        n(3, str, str2, th);
    }

    public static void c(String str, Throwable th) {
        n(3, str, null, th);
    }

    public static void d(String str, String str2) {
        n(6, str, str2, null);
    }

    public static void e(String str, String str2, Throwable th) {
        n(6, str, str2, th);
    }

    public static void f(String str, Throwable th) {
        n(6, str, null, th);
    }

    public static Context g() {
        return b;
    }

    public static b h() {
        if (f15459c == null) {
            f15459c = b.d();
        }
        return f15459c;
    }

    public static void i(String str, String str2) {
        n(4, str, str2, null);
    }

    public static void j(String str, String str2, Throwable th) {
        n(4, str, str2, th);
    }

    public static void k(String str, Throwable th) {
        n(4, str, null, th);
    }

    public static void l(b bVar) {
        if (bVar == null || bVar.b() == null) {
            return;
        }
        f15459c = bVar;
        if (b == null) {
            b = bVar.b();
        }
        if (a == null) {
            try {
                a = com.jingdong.sdk.talos.inner.b.b(bVar);
            } catch (Throwable th) {
                e.d.d("LogX", th.getMessage());
            }
        }
    }

    public static void m(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            a(str, "Empty/Null json content");
            return;
        }
        try {
            String trim = str2.trim();
            if (trim.startsWith("{")) {
                a(str, new JSONObject(trim).toString(2));
            } else if (trim.startsWith("[")) {
                a(str, new JSONArray(trim).toString(2));
            }
        } catch (JSONException unused) {
            d(str, "Invalid Json");
        }
    }

    public static void n(int i2, String str, String str2, Throwable th) {
        com.jingdong.sdk.talos.inner.b bVar = a;
        if (bVar == null) {
            return;
        }
        String stackTraceString = Log.getStackTraceString(th);
        if (bVar.a.y()) {
            if (TextUtils.isEmpty(str2)) {
                Log.println(i2, str, stackTraceString);
            } else {
                Log.println(i2, str, str2 + '\n' + stackTraceString);
            }
        }
        if (!(TextUtils.isEmpty(str2) && th == null) && bVar.a.w() && i2 >= bVar.a.k()) {
            e eVar = new e();
            eVar.a = e.a.a;
            l lVar = new l();
            String name = Thread.currentThread().getName();
            long id = Thread.currentThread().getId();
            int myPid = Process.myPid();
            boolean z = Looper.getMainLooper() == Looper.myLooper();
            JSONObject jSONObject = new JSONObject();
            try {
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put("t", str);
                }
                jSONObject.put("m", str2);
                if (!TextUtils.isEmpty(stackTraceString)) {
                    jSONObject.put(com.jingdong.app.mall.e.a, stackTraceString);
                }
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            lVar.a = jSONObject.toString();
            lVar.f15534e = System.currentTimeMillis();
            lVar.f15535f = i2;
            lVar.b = z;
            lVar.f15533c = id;
            lVar.d = name;
            lVar.f15536g = myPid;
            eVar.b = lVar;
            if (bVar.b.size() < bVar.a.o()) {
                bVar.b.add(eVar);
                f fVar = bVar.f15494c;
                if (fVar != null) {
                    fVar.a();
                }
            }
        }
    }

    public static void o(String str, String str2) {
        n(2, str, str2, null);
    }

    public static void p(String str, String str2, Throwable th) {
        n(2, str, str2, th);
    }

    public static void q(String str, Throwable th) {
        n(2, str, null, th);
    }

    public static void r(String str, String str2) {
        n(5, str, str2, null);
    }

    public static void s(String str, String str2, Throwable th) {
        n(5, str, str2, th);
    }

    public static void t(String str, Throwable th) {
        n(5, str, null, th);
    }
}
