package com.jd.stat.security;

import android.content.Context;
import com.jd.stat.common.MonitorService;
import com.jd.stat.common.callback.JmaCallback;
import com.jd.stat.common.f;
import com.jd.stat.common.n;
import com.jd.stat.common.p;
import com.jd.stat.security.jma.JMA;
import com.jd.stat.security.jma.b.h;
import org.json.JSONException;
import org.json.JSONObject;
import org.mp4parser.aspectj.lang.JoinPoint;

/* loaded from: classes18.dex */
public class SecurityInit {
    private static boolean a;

    public static void b(int i2, String str) {
        try {
            com.jd.stat.common.b.b.a("reportInitializationEvent", "statusCode=" + i2);
            JMA.report(c.a, c(i2, str));
        } catch (Exception e2) {
            com.jd.stat.common.b.b.a("bot", "TokenSender reportEvent error:" + e2.getMessage());
        }
    }

    private static JSONObject c(int i2, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("extkey", c.a());
        jSONObject.put("eventid", JoinPoint.INITIALIZATION);
        jSONObject.put("stack", str);
        jSONObject.put("packageName", c.a.getPackageName());
        jSONObject.put("appid", c.g());
        jSONObject.put("agreePP", c.k() ? 1 : 0);
        jSONObject.put("statuscode", i2);
        return jSONObject;
    }

    public static synchronized void init(Context context, String str, TrackBaseData trackBaseData, final JmaCallback jmaCallback) {
        synchronized (SecurityInit.class) {
            if (!a) {
                a = true;
                com.jd.stat.common.b.b.b("JDMob.Security.SecurityInit", "SecurityInit.init called");
                c.b(str);
                c.a(context);
                h.a();
                com.jd.stat.network.b.a(trackBaseData.isEnableLog());
                com.jd.stat.common.b.b.a(trackBaseData.isEnableLog());
                c.a(trackBaseData.isDebug());
                c.a(trackBaseData);
                com.jd.stat.common.d.a(context).a(c.b);
                MonitorService.f().a(context);
                d.a().a(trackBaseData != null ? trackBaseData.useRemoteConfig() : false);
                p.a();
                final String a2 = a();
                if (str != null && str.length() != 0) {
                    a.a(context, new com.jd.stat.common.callback.a<Integer, Integer>() { // from class: com.jd.stat.security.SecurityInit.1
                        @Override // com.jd.stat.common.callback.a
                        public void a(Integer num) {
                            SecurityInit.b(num.intValue(), a2);
                            JmaCallback jmaCallback2 = jmaCallback;
                            if (jmaCallback2 != null) {
                                jmaCallback2.onCheckAppIdResult("100");
                            }
                        }

                        @Override // com.jd.stat.common.callback.a
                        public void b(Integer num) {
                            if (num.intValue() < 0) {
                                num = 102;
                            }
                            SecurityInit.b(num.intValue(), a2);
                            JmaCallback jmaCallback2 = jmaCallback;
                            if (jmaCallback2 != null) {
                                jmaCallback2.onCheckAppIdResult("101");
                            }
                        }
                    });
                    n.j(context);
                    f.a(context);
                }
                b(101, a2);
                if (jmaCallback != null) {
                    jmaCallback.onCheckAppIdResult("101");
                }
                n.j(context);
                f.a(context);
            }
        }
    }

    private static String a() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        StringBuilder sb = new StringBuilder();
        if (stackTrace.length > 1) {
            for (int i2 = 1; i2 < stackTrace.length; i2++) {
                StackTraceElement stackTraceElement = stackTrace[i2];
                sb.append(stackTraceElement.getClassName() + "|" + stackTraceElement.getMethodName());
                if (i2 == Math.min(stackTrace.length - 1, 3)) {
                    break;
                }
                sb.append("|");
            }
        }
        return sb.toString();
    }
}
