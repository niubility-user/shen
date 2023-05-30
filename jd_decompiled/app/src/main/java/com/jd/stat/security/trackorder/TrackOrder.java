package com.jd.stat.security.trackorder;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.jd.lib.productdetail.core.entitys.NoStockRecommendHead;
import com.jd.stat.common.b.b;
import com.jd.stat.common.b.c;
import com.jd.stat.common.b.g;
import com.jd.stat.common.t;
import com.jd.stat.network.NetworkException;
import com.jd.stat.network.d;
import com.jd.stat.network.e;
import com.jd.stat.network.f;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.recommend.RecommendMtaUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class TrackOrder {
    private static SharedPreferences a;

    private static void b(final JSONObject jSONObject) {
        if (jSONObject != null) {
            if (b.a) {
                b.b("JDMob.Security.TrackOrder", String.format("requestAndSaveTracking param json: \n%s", c.a(jSONObject.toString())));
            }
            if (TextUtils.isEmpty(jSONObject.optString("firstorderUnion"))) {
                return;
            }
            d dVar = new d(a()) { // from class: com.jd.stat.security.trackorder.TrackOrder.1
                @Override // com.jd.stat.network.d
                protected String e() {
                    try {
                        JSONObject c2 = TrackOrder.c(jSONObject);
                        if (b.a) {
                            b.b("JDMob.Security.TrackOrder", String.format("buildFirstOrderRequest json: \n%s", c.a(c2.toString())));
                        }
                        return URLEncoder.encode(c2.toString(), "UTF-8");
                    } catch (UnsupportedEncodingException e2) {
                        e2.printStackTrace();
                        return null;
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                        return null;
                    }
                }
            };
            dVar.a(new f() { // from class: com.jd.stat.security.trackorder.TrackOrder.2
                @Override // com.jd.stat.network.f
                public void a(NetworkException networkException) {
                }

                @Override // com.jd.stat.network.f
                public void a(e eVar) {
                    String str;
                    JSONObject d = eVar.d();
                    if (b.a) {
                        b.b("JDMob.Security.TrackOrder", String.format("requestAndSaveTracking response json: \n%s", c.a(d.toString())));
                    }
                    try {
                        if (d != null) {
                            String optString = d.optString("code");
                            if (TextUtils.equals("0", optString)) {
                                str = d.optString(RecommendMtaUtils.TRACKING);
                                if (b.a) {
                                    b.b("JDMob.Security.TrackOrder", "tracking " + str + ",code " + optString);
                                }
                                jSONObject.put(RecommendMtaUtils.TRACKING, str);
                                TrackOrder.putTrackOrderParams(com.jd.stat.security.c.a, jSONObject);
                                return;
                            }
                        }
                        jSONObject.put(RecommendMtaUtils.TRACKING, str);
                        TrackOrder.putTrackOrderParams(com.jd.stat.security.c.a, jSONObject);
                        return;
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        return;
                    }
                    str = "";
                }
            });
            dVar.a(60000);
            dVar.a((Object) ("TrackOrder." + System.currentTimeMillis()));
            dVar.h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject c(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        if (jSONObject != null) {
            String optString = jSONObject.optString("firstorderUnion");
            String optString2 = jSONObject.optString("firstorderChannel");
            String optString3 = jSONObject.optString("packageName");
            String optString4 = jSONObject.optString("callerPackage");
            String a2 = com.jd.stat.common.c.a(com.jd.stat.security.c.a, optString3);
            String optString5 = jSONObject.optString("firstorderExtended1");
            if (optString5.length() > 127) {
                optString5 = optString5.substring(0, 126);
            }
            String optString6 = jSONObject.optString("firstorderExtended2");
            if (optString6.length() > 127) {
                optString6 = optString6.substring(0, 126);
            }
            String optString7 = jSONObject.optString("firstorderExtended3");
            if (optString7.length() > 511) {
                optString7 = optString7.substring(0, 510);
            }
            String optString8 = jSONObject.optString("firstorderExtended4");
            if (optString8.length() > 511) {
                optString8 = optString8.substring(0, 510);
            }
            jSONObject2.put("appkey", "1b41a7042ce724d9ecaa5a15fe9fab7a");
            jSONObject2.put(JumpUtil.VALUE_DES_CPS_UNION, optString);
            jSONObject2.put("channel", optString2);
            jSONObject2.put("appSign", a2);
            jSONObject2.put(NoStockRecommendHead.DEVICE, com.jd.stat.security.c.a());
            jSONObject2.put("package", optString3);
            jSONObject2.put("callerPackage", optString4);
            jSONObject2.put("extendedData1", optString5);
            jSONObject2.put("extendedData2", optString6);
            jSONObject2.put("extendedData3", optString7);
            jSONObject2.put("extendedData4", optString8);
            StringBuilder sb = new StringBuilder();
            sb.append(g.d("1b41a7042ce724d9ecaa5a15fe9fab7a" + optString + optString2 + com.jd.stat.security.c.a() + optString3 + optString4 + a2));
            StringBuilder sb2 = new StringBuilder();
            sb2.append(optString5);
            sb2.append(optString6);
            sb2.append(optString7);
            sb2.append(optString8);
            sb.append(g.d(sb2.toString()));
            jSONObject2.put("sign", g.d(sb.toString()));
        }
        return jSONObject2;
    }

    public static void getFirstOrderTracking(Context context, String str, JSONObject jSONObject) {
        if (TextUtils.equals(str, "0")) {
            b(jSONObject);
        } else if (TextUtils.equals(str, "1")) {
            putTrackOrderParams(context, jSONObject);
        }
    }

    public static JSONObject getTrackOrderParams(Context context) {
        String str;
        String str2;
        a(context);
        JSONObject jSONObject = new JSONObject();
        try {
            b.b("JDMob.Security.TrackOrder", "come to getTrackOrderParams");
            SharedPreferences sharedPreferences = a;
            String str3 = "";
            if (sharedPreferences != null) {
                str = sharedPreferences.getString("track_id", "");
                str2 = a.getString("track_signature", "");
                if (b.a) {
                    b.b("JDMob.Security.TrackOrder", "trackKey = " + str + ",signature = " + str2);
                }
            } else {
                str = "";
                str2 = str;
            }
            if (str == null) {
                str = "";
            }
            jSONObject.put("fe_order_track", str);
            if (str2 != null) {
                str3 = str2;
            }
            jSONObject.put("advertise_app", str3);
            String a2 = com.jd.stat.security.c.a();
            if (b.a) {
                b.b("JDMob.Security.TrackOrder", "devicecode = " + a2);
            }
            if (!g.c(a2)) {
                a2 = t.c(context);
                if (b.a) {
                    b.b("JDMob.Security.TrackOrder", "get uuid in sdk,devicecode = " + a2);
                }
            }
            jSONObject.put("uuid", a2);
            jSONObject.put("union_id", com.jd.stat.security.c.b());
            jSONObject.put("channel_id", com.jd.stat.security.c.c());
            jSONObject.put("appkey", "1b41a7042ce724d9ecaa5a15fe9fab7a");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (b.a) {
            b.b("JDMob.Security.TrackOrder", String.format("getTrackOrderParams json: \n%s", c.a(jSONObject.toString())));
        }
        return jSONObject;
    }

    public static void putTrackOrderParams(Context context, JSONObject jSONObject) {
        b.b("JDMob.Security.TrackOrder", "come to putTrackOrderParams");
        a(context);
        if (a == null) {
            return;
        }
        if (jSONObject != null) {
            String str = "";
            String optString = jSONObject.optString(RecommendMtaUtils.TRACKING);
            String optString2 = jSONObject.optString("packageName");
            try {
                SharedPreferences.Editor edit = a.edit();
                if (!TextUtils.isEmpty(optString)) {
                    edit.putString("track_id", optString);
                }
                if (!TextUtils.isEmpty(optString2)) {
                    String str2 = new String(Base64.encode(optString2.getBytes(), 2));
                    try {
                        edit.putString("track_source_name", str2).putString("track_signature", com.jd.stat.common.c.a(context, optString2));
                        str = str2;
                    } catch (Throwable unused) {
                        str = str2;
                    }
                }
                edit.commit();
            } catch (Throwable unused2) {
            }
            if (b.a) {
                b.b("JDMob.Security.TrackOrder", "put trackid = " + optString + ",packName = " + optString2 + ",encryptSrc = " + str);
                return;
            }
            return;
        }
        b.a("JDMob.Security.TrackOrder", "TrackParams is Empty!!!");
    }

    private static String a() {
        return com.jd.stat.security.c.e() ? "https://beta-fireye.m.jd.com/tracking/app/getTracking" : "https://fireye.m.jd.com/tracking/app/getTracking";
    }

    private static void a(Context context) {
        if (a != null) {
            return;
        }
        if (context == null) {
            b.a("JDMob.Security.TrackOrder", "create file but context is null!!!");
        } else {
            a = context.getSharedPreferences("jd_order_identify_encrypt_client", 0);
        }
    }
}
