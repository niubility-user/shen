package com.jd.stat.security.jma.b;

import android.text.TextUtils;
import com.jd.stat.bot.BotDetector;
import com.jd.stat.common.t;
import com.jd.stat.network.NetworkException;
import com.jd.stat.security.jma.JMA;
import com.jdjr.mobilecert.MobileCertConstants;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class c implements f {
    private static boolean a;
    private a b;

    /* loaded from: classes18.dex */
    public interface a {
        void a(JSONObject jSONObject);
    }

    public c(a aVar) {
        this.b = null;
        this.b = aVar;
    }

    private static String c() {
        return "UTF-8";
    }

    @Override // com.jd.stat.security.jma.b.f
    public void c(JSONObject jSONObject) {
        if (com.jd.stat.security.d.a().o() && !a) {
            a = true;
            com.jd.stat.network.d dVar = new com.jd.stat.network.d(j.f()) { // from class: com.jd.stat.security.jma.b.c.1
                {
                    c.this = this;
                }

                @Override // com.jd.stat.network.d
                protected String e() {
                    try {
                        return "content=" + URLEncoder.encode(c.this.a().toString(), "UTF-8");
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return null;
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                        return null;
                    }
                }

                @Override // com.jd.stat.network.d
                public Map<String, String> f() {
                    return super.f();
                }
            };
            dVar.a(MobileCertConstants.WSKEY, com.jd.stat.security.c.n());
            dVar.a("whwswswws", com.jd.stat.security.e.a(com.jd.stat.security.c.a).a());
            dVar.a("pin_hash", b());
            dVar.a(new com.jd.stat.network.f() { // from class: com.jd.stat.security.jma.b.c.2
                {
                    c.this = this;
                }

                @Override // com.jd.stat.network.f
                public void a(com.jd.stat.network.e eVar) {
                    try {
                        String str = new String(eVar.a());
                        com.jd.stat.common.b.b.a("bot", "responseStr=" + str);
                        JSONObject jSONObject2 = new JSONObject(str);
                        int optInt = jSONObject2.optInt("code", -1);
                        if (optInt == 0) {
                            if (com.jd.stat.common.b.b.a) {
                                com.jd.stat.common.b.b.a("JDMob.Security.ClogTokenSender", String.format("token json: \n%s", com.jd.stat.common.b.c.a(jSONObject2.toString())));
                            }
                            if (c.this.b != null) {
                                c.this.b.a(jSONObject2);
                            }
                        } else {
                            c.this.a("\u56de\u5305\u89e3\u6790\u5f02\u5e38", "code = " + optInt, new Exception());
                        }
                    } catch (Exception e2) {
                        c.this.a("\u56de\u5305\u89e3\u6790\u5f02\u5e38", e2);
                    }
                    boolean unused = c.a = false;
                }

                @Override // com.jd.stat.network.f
                public void a(NetworkException networkException) {
                    if (networkException.getErrorCode() != -1004) {
                        c.this.a("\u7f51\u7edc\u8bf7\u6c42\u5f02\u5e38", networkException);
                    }
                    boolean unused = c.a = false;
                }
            });
            dVar.b(true);
            dVar.a((Object) ("ClogTokenSender." + System.currentTimeMillis()));
            dVar.h();
        }
    }

    private String b() {
        if (TextUtils.isEmpty(com.jd.stat.security.c.f())) {
            return "";
        }
        try {
            return Integer.toHexString(a(com.jd.stat.security.c.f().getBytes(c()))).toUpperCase();
        } catch (Exception unused) {
            return "";
        }
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Constants.PARAM_PLATFORM, "2");
        jSONObject.put("hs", b());
        jSONObject.put("version", String.valueOf(0));
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("appname", com.jd.stat.security.c.e() ? "50158" : "50998");
        jSONObject2.put("whwswswws", com.jd.stat.security.e.a(com.jd.stat.security.c.a).a());
        jSONObject2.put("jdkey", t.b());
        jSONObject2.put("body", jSONObject);
        return jSONObject2;
    }

    public static int a(byte[] bArr) {
        long j2 = 0;
        for (byte b : bArr) {
            j2 = (j2 * 31) + (b & 255);
        }
        return (int) (j2 & 2147483647L);
    }

    public void a(String str, Exception exc) {
        a(str, (exc.getMessage() == null || exc.getMessage().length() <= 50) ? exc.getMessage() : exc.getMessage().substring(0, 50), exc);
    }

    private String a(Map<String, String> map) {
        try {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public void a(String str, String str2, Exception exc) {
        if (str2 == null) {
            str2 = "";
        }
        try {
            com.jd.stat.common.b.b.a("bot", "realMsg=" + str + "|errorMsg=" + str2);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("eventid", "RJError");
            jSONObject.put("uid", com.jd.stat.security.c.f());
            jSONObject.put("appid", com.jd.stat.security.c.g());
            jSONObject.put("sceneid", BotDetector.getSceneId());
            jSONObject.put("data", a(BotDetector.getBusinessData()));
            jSONObject.put("type", 2);
            jSONObject.put("client_time", String.valueOf(System.currentTimeMillis()));
            jSONObject.put("f_name", "getToken");
            jSONObject.put("call_stack_source", com.jd.stat.common.b.g.a(exc));
            jSONObject.put("error_msg", str2);
            jSONObject.put("cf_v", BotDetector.getCFVersion());
            jSONObject.put("cookin_pin", a(com.jd.stat.security.c.f()));
            jSONObject.put("time_correction", String.valueOf(BotDetector.getRealTimestamp()));
            jSONObject.put("is_trust", "2");
            jSONObject.put("index", "");
            jSONObject.put("session_c", "");
            jSONObject.put("real_msg", "");
            jSONObject.put("clientsdkversion", String.valueOf(0));
            JMA.report(com.jd.stat.security.c.a, jSONObject);
        } catch (Exception e2) {
            com.jd.stat.common.b.b.a("bot", "TokenSender reportEvent error:" + e2.getMessage());
        }
    }

    private static String a(String str) {
        String d = com.jd.stat.common.b.g.d(str);
        return TextUtils.isEmpty(d) ? "" : d;
    }
}
