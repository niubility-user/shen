package com.jd.stat.security.jma.b;

import com.jd.stat.bot.BlogUtil;
import com.jd.stat.common.t;
import com.jd.stat.network.NetworkException;
import com.jd.stat.security.jma.JMA;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class k implements f {
    @Override // com.jd.stat.security.jma.b.f
    public void c(JSONObject jSONObject) {
        if (com.jd.stat.security.d.a().o()) {
            com.jd.stat.network.d dVar = new com.jd.stat.network.d(j.e()) { // from class: com.jd.stat.security.jma.b.k.1
                {
                    k.this = this;
                }

                @Override // com.jd.stat.network.d
                protected String e() {
                    try {
                        return "content=" + URLEncoder.encode(k.this.a().getString("content"), "UTF-8");
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return null;
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                        return null;
                    }
                }
            };
            dVar.a(new com.jd.stat.network.f() { // from class: com.jd.stat.security.jma.b.k.2
                {
                    k.this = this;
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
                                com.jd.stat.common.b.b.a("JDMob.Security.TokenSender", String.format("token json: \n%s", com.jd.stat.common.b.c.a(jSONObject2.toString())));
                            }
                            BlogUtil.updateJoyytoken(jSONObject2);
                            return;
                        }
                        k.this.a("\u56de\u5305\u89e3\u6790\u5f02\u5e38", "code = " + optInt);
                    } catch (Exception e2) {
                        k.this.a("\u56de\u5305\u89e3\u6790\u5f02\u5e38", e2);
                    }
                }

                @Override // com.jd.stat.network.f
                public void a(NetworkException networkException) {
                    k.this.a("\u7f51\u7edc\u8bf7\u6c42\u5f02\u5e38", networkException);
                }
            });
            dVar.a((Object) ("TokenSender." + System.currentTimeMillis()));
            dVar.h();
        }
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Constants.PARAM_PLATFORM, "2");
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("appname", BlogUtil.debugGettoken ? "59880" : "50999");
        jSONObject2.put("whwswswws", com.jd.stat.security.e.a(com.jd.stat.security.c.a).a());
        jSONObject2.put("jdkey", t.b());
        jSONObject2.put("body", jSONObject);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("content", jSONObject2);
        return jSONObject3;
    }

    public void a(String str, Exception exc) {
        int length = exc.getMessage().length();
        String message = exc.getMessage();
        if (length > 50) {
            message = message.substring(0, 50);
        }
        a(str, message);
    }

    public void a(String str, String str2) {
        try {
            com.jd.stat.common.b.b.a("bot", "realMsg=" + str + "|errorMsg=" + str2);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("eventid", "gettoken");
            jSONObject.put("uid", "");
            jSONObject.put("appid", "");
            jSONObject.put("sceneid", "");
            jSONObject.put("data", "");
            jSONObject.put("type", "");
            jSONObject.put("client_time", "" + System.currentTimeMillis());
            jSONObject.put("client_version", "" + BaseInfo.getAppVersionName());
            jSONObject.put("f_name", "blog");
            jSONObject.put("call_stack_source", "");
            jSONObject.put("cf_v", BlogUtil.getCF_V());
            jSONObject.put("cookie_pin", "" + BlogUtil.md5pin);
            jSONObject.put("time_correction", String.valueOf(BlogUtil.getQ()));
            jSONObject.put("is_trust", "");
            jSONObject.put("dr", "");
            jSONObject.put("index", "");
            jSONObject.put("session_c", "");
            jSONObject.put("real_msg", "" + str);
            jSONObject.put("error_msg", "" + str2);
            JMA.report(com.jd.stat.security.c.a, jSONObject);
        } catch (Exception e2) {
            com.jd.stat.common.b.b.a("bot", "TokenSender reportEvent error:" + e2.getMessage());
        }
    }
}
