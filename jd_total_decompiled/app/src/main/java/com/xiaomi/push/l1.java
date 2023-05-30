package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.bing.utils.JDBingConst;

/* loaded from: classes11.dex */
public class l1 {
    private static Boolean a;

    static void a(String str) {
        b("Push-ConnectionQualityStatsHelper", str);
    }

    public static void b(String str, String str2) {
    }

    public static boolean c(Context context) {
        Boolean bool;
        if (a == null) {
            if (!a8.j(context)) {
                a = Boolean.FALSE;
            }
            String d = com.xiaomi.push.service.t2.d(context);
            if (TextUtils.isEmpty(d) || d.length() < 3) {
                bool = Boolean.FALSE;
            } else {
                String substring = d.substring(d.length() - 3);
                a("shouldSampling uuid suffix = " + substring);
                bool = Boolean.valueOf(TextUtils.equals(substring, JDBingConst.PARAM_ERROR_CODE));
            }
            a = bool;
            a("shouldSampling = " + a);
        }
        return a.booleanValue();
    }
}
