package com.jingdong.app.mall.home.shakeandshow;

import android.content.Context;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.unification.uniconfig.UnIconConfigController;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class f {
    private static float a = 0.0f;
    private static float b = 0.0f;

    /* renamed from: c  reason: collision with root package name */
    private static int f10854c = 0;
    private static String d = "";

    public static void a(Context context) {
        try {
            if (a > 0.0f && b > 0.0f) {
                PerformanceReporter.getStategyEntitiy(context, "10", "4");
                if (PerformanceReporter.getIsNeedReport(context, "10", "4")) {
                    HashMap hashMap = new HashMap(8);
                    hashMap.put("max", String.valueOf(a));
                    hashMap.put(UnIconConfigController.A_B_SWITCH_A, String.valueOf(b));
                    hashMap.put("shake", String.valueOf(f10854c));
                    hashMap.put("msg", d);
                    hashMap.put("typeId", "10");
                    hashMap.put("chId", "4");
                    hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
                    PerformanceReporter.reportData(hashMap);
                    b();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void b() {
        d(0.0f);
        c(0.0f);
        f(0);
        e("");
    }

    public static void c(float f2) {
        b = f2;
    }

    public static void d(float f2) {
        a = Math.max(a, f2);
    }

    public static void e(String str) {
        d = str;
    }

    public static void f(int i2) {
        f10854c = i2;
    }
}
