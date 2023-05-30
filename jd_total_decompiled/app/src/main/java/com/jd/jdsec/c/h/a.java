package com.jd.jdsec.c.h;

import com.jd.jdsec.a.l.b;
import com.jd.jdsec.c.c;
import com.jd.jdsec.c.h.c.k;
import com.jingdong.union.common.config.UnionConstants;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class a {
    private static String a = "";
    private static String b = "";

    /* renamed from: c  reason: collision with root package name */
    private static ExecutorService f2750c = Executors.newCachedThreadPool();

    /* renamed from: com.jd.jdsec.c.h.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    class RunnableC0090a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f2751g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f2752h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ String f2753i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ String f2754j;

        RunnableC0090a(String str, String str2, String str3, String str4) {
            this.f2751g = str;
            this.f2752h = str2;
            this.f2753i = str3;
            this.f2754j = str4;
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject jSONObject = new JSONObject();
            try {
                String str = this.f2751g;
                if (str != null) {
                    a.g(str);
                }
                String str2 = this.f2752h;
                if (str2 != null) {
                    a.f(str2);
                }
                jSONObject.put("eventid", this.f2752h);
                jSONObject.put(UnionConstants.BUNDLE_REFER, this.f2751g);
                jSONObject.put("recent_pro", this.f2753i);
                jSONObject.put("refers", this.f2754j);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (!a.a()) {
                b.e("JDSec.Security.JMA", "report has close!");
            } else if (c.c().f(this.f2752h)) {
            } else {
                b.e("JDSec.Security.JMA", String.format("eventid = %s, refer = %s, recent_pro = %s, refers = %s \u4f34\u968f\u7740\u6b64\u4e8b\u4ef6\uff0cantiSDK\u5373\u5c06\u542f\u52a8", this.f2752h, this.f2751g, this.f2753i, this.f2754j));
                k.a(jSONObject);
                k.b(jSONObject);
            }
        }
    }

    static /* synthetic */ boolean a() {
        return b();
    }

    private static boolean b() {
        return c.c().e();
    }

    public static String c() {
        return b;
    }

    public static String d() {
        return a;
    }

    public static void e(String str, String str2, String str3, String str4) {
        try {
            ExecutorService executorService = f2750c;
            if (executorService != null) {
                executorService.execute(new RunnableC0090a(str2, str, str3, str4));
            }
        } catch (Exception e2) {
            b.e("JDSec.Security.JMA", "\u5916\u5c42\u5f02\u5e38" + e2.getMessage());
        }
    }

    public static void f(String str) {
        b = str;
    }

    public static void g(String str) {
        a = str;
    }
}
