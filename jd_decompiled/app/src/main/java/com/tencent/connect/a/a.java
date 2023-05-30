package com.tencent.connect.a;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.b.e;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.i;
import java.lang.reflect.Method;

/* loaded from: classes9.dex */
public class a {
    private static Class<?> a;
    private static Class<?> b;

    /* renamed from: c */
    private static Method f16093c;
    private static Method d;

    /* renamed from: e */
    private static Method f16094e;

    /* renamed from: f */
    private static Method f16095f;

    /* renamed from: g */
    private static boolean f16096g;

    public static boolean a(Context context, QQToken qQToken) {
        return i.a(context, qQToken.getAppId()).b("Common_ta_enable");
    }

    public static void b(Context context, QQToken qQToken) {
        try {
            if (a(context, qQToken)) {
                f16095f.invoke(a, Boolean.TRUE);
            } else {
                f16095f.invoke(a, Boolean.FALSE);
            }
        } catch (Exception e2) {
            SLog.e("OpenConfig", "checkStatStatus exception: " + e2.toString());
        }
    }

    public static void c(Context context, QQToken qQToken) {
        String str = "Aqc" + qQToken.getAppId();
        try {
            a = Class.forName("com.tencent.stat.StatConfig");
            Class<?> cls = Class.forName("com.tencent.stat.StatService");
            b = cls;
            f16093c = cls.getMethod("reportQQ", Context.class, String.class);
            d = b.getMethod("trackCustomEvent", Context.class, String.class, String[].class);
            Class<?> cls2 = b;
            Class<?> cls3 = Integer.TYPE;
            f16094e = cls2.getMethod("commitEvents", Context.class, cls3);
            Class<?> cls4 = a;
            Class<?> cls5 = Boolean.TYPE;
            f16095f = cls4.getMethod("setEnableStatService", cls5);
            b(context, qQToken);
            a.getMethod("setAutoExceptionCaught", cls5).invoke(a, Boolean.FALSE);
            a.getMethod("setEnableSmartReporting", cls5).invoke(a, Boolean.TRUE);
            a.getMethod("setSendPeriodMinutes", cls3).invoke(a, Integer.valueOf((int) R2.attr.motionEffect_translationX));
            Class<?> cls6 = Class.forName("com.tencent.stat.StatReportStrategy");
            a.getMethod("setStatSendStrategy", cls6).invoke(a, cls6.getField("PERIOD").get(null));
            b.getMethod("startStatService", Context.class, String.class, String.class).invoke(b, context, str, Class.forName("com.tencent.stat.common.StatConstants").getField("VERSION").get(null));
            f16096g = true;
        } catch (Exception e2) {
            SLog.e("OpenConfig", "start4QQConnect exception: " + e2.toString());
        }
    }

    public static void d(Context context, QQToken qQToken) {
        if (!TextUtils.isEmpty(qQToken.getOpenId())) {
            e.a().a(qQToken.getOpenId(), qQToken.getAppId(), "2", "1", "11", "0", "0", "0");
        }
        if (f16096g) {
            b(context, qQToken);
            if (qQToken.getOpenId() != null) {
                try {
                    f16093c.invoke(b, context, qQToken.getOpenId());
                } catch (Exception e2) {
                    SLog.e("OpenConfig", "reportQQ exception: " + e2.toString());
                }
            }
        }
    }

    public static void a(Context context, QQToken qQToken, String str, String... strArr) {
        if (f16096g) {
            b(context, qQToken);
            try {
                d.invoke(b, context, str, strArr);
            } catch (Exception e2) {
                SLog.e("OpenConfig", "trackCustomEvent exception: " + e2.toString());
            }
        }
    }
}
