package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.tencent.connect.common.Constants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class e {
    private static e b;

    /* renamed from: c  reason: collision with root package name */
    private static Map<String, Long> f1360c = new HashMap();
    private Context a;

    public static e a() {
        return b();
    }

    private static synchronized e b() {
        e eVar;
        synchronized (e.class) {
            if (b == null) {
                b = new e();
            }
            eVar = b;
        }
        return eVar;
    }

    private void b(Context context) {
        String str;
        String d = o.d(context);
        q0.a(d);
        if (q1.b().a()) {
            String a = d.a(context, "global_v2", Constants.PARAM_APP_VER, "");
            d.b(context, "global_v2", Constants.PARAM_APP_VER, d);
            q0.b(a);
            if (!TextUtils.isEmpty(a)) {
                if (a.equals(d)) {
                    return;
                }
                v.c("hmsSdk", "the appVers are different!");
                a().a("", "alltype", a);
                return;
            }
            str = "app ver is first save!";
        } else {
            str = "userManager.isUserUnlocked() == false";
        }
        v.c("hmsSdk", str);
    }

    public void a(Context context) {
        this.a = context;
        b(context);
        s.c().b().h(o.a());
    }

    public void a(String str, int i2) {
        if (this.a == null) {
            v.e("hmsSdk", "onReport() null context or SDK was not init.");
            return;
        }
        v.c("hmsSdk", "onReport: Before calling runtaskhandler()");
        a(str, n1.a(i2), q0.g());
    }

    public void a(String str, int i2, String str2, JSONObject jSONObject) {
        long currentTimeMillis = System.currentTimeMillis();
        if (2 == i2) {
            currentTimeMillis = n1.a("yyyy-MM-dd", currentTimeMillis);
        }
        b0.c().a(new a0(str2, jSONObject, str, n1.a(i2), currentTimeMillis));
    }

    public void a(String str, int i2, String str2, JSONObject jSONObject, long j2) {
        new i1(str, n1.a(i2), str2, jSONObject.toString(), j2).a();
    }

    public void a(String str, String str2) {
        if (!a1.a(str, str2)) {
            v.c("hmsSdk", "auto report is closed tag:" + str);
            return;
        }
        long j2 = a1.j(str, str2);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - j2 <= Final.HALF_MINUTE) {
            v.f("hmsSdk", "autoReport timeout. interval < 30s ");
            return;
        }
        v.a("hmsSdk", "begin to call onReport!");
        a1.a(str, str2, currentTimeMillis);
        a(str, str2, q0.g());
    }

    public void a(String str, String str2, String str3) {
        Context context = this.a;
        if (context == null) {
            v.e("hmsSdk", "onReport() null context or SDK was not init.");
            return;
        }
        String a = r0.a(context);
        if (a1.e(str, str2) && !"WIFI".equals(a)) {
            v.c("hmsSdk", "strNetworkType is :" + a);
        } else if (TextUtils.isEmpty(a) || "2G".equals(a)) {
            v.e("hmsSdk", "The network is bad.");
        } else {
            b0.c().a(new v0(str, str2, str3));
        }
    }
}
