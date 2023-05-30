package com.jd.fireeye.security;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.jd.fireeye.b.d;
import com.jd.fireeye.b.f;
import com.jd.fireeye.b.g;
import com.jd.fireeye.b.q;
import com.jd.fireeye.network.NetworkException;
import com.jd.fireeye.network.d;
import com.jd.fireeye.network.e;
import com.jd.lib.productdetail.core.entitys.NoStockRecommendHead;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.recommend.RecommendMtaUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class TrackOrder {
    private static final String BETA_FIRST_ORDER_URL = "https://beta-fireye.m.jd.com/tracking/app/getTracking";
    private static final String FIRST_ORDER_URL = "https://fireye.m.jd.com/tracking/app/getTracking";
    private static final String TAG = "JDMob.Security.TrackOrder";
    private static final String TRACK_KEY = "track_id";
    private static final String TRACK_SIGNATURE = "track_signature";
    private static final String TRACK_SOURCE_NAME = "track_source_name";
    private static SharedPreferences shareStore;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class a extends d {

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ JSONObject f2627k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, JSONObject jSONObject) {
            super(str);
            this.f2627k = jSONObject;
        }

        @Override // com.jd.fireeye.network.d
        protected String a() {
            try {
                JSONObject buildFirstOrderRequest = TrackOrder.buildFirstOrderRequest(this.f2627k);
                if (f.a) {
                    f.b(TrackOrder.TAG, String.format("buildFirstOrderRequest json: \n%s", g.a(buildFirstOrderRequest.toString())));
                }
                return URLEncoder.encode(buildFirstOrderRequest.toString(), "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                return null;
            } catch (JSONException e3) {
                e3.printStackTrace();
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class b extends com.jd.fireeye.network.f {
        final /* synthetic */ JSONObject a;

        b(JSONObject jSONObject) {
            this.a = jSONObject;
        }

        @Override // com.jd.fireeye.network.f
        public void a(NetworkException networkException) {
        }

        @Override // com.jd.fireeye.network.f
        public void a(e eVar) {
            String str;
            JSONObject a = eVar.a();
            if (f.a) {
                f.b(TrackOrder.TAG, String.format("requestAndSaveTracking response json: \n%s", g.a(a.toString())));
            }
            try {
                if (a != null) {
                    String optString = a.optString("code");
                    if (TextUtils.equals("0", optString)) {
                        str = a.optString(RecommendMtaUtils.TRACKING);
                        if (f.a) {
                            f.b(TrackOrder.TAG, "tracking " + str + ",code " + optString);
                        }
                        this.a.put(RecommendMtaUtils.TRACKING, str);
                        TrackOrder.putTrackOrderParams(com.jd.fireeye.security.a.a, this.a);
                        return;
                    }
                }
                this.a.put(RecommendMtaUtils.TRACKING, str);
                TrackOrder.putTrackOrderParams(com.jd.fireeye.security.a.a, this.a);
                return;
            } catch (JSONException e2) {
                e2.printStackTrace();
                return;
            }
            str = "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject buildFirstOrderRequest(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        if (jSONObject != null) {
            String optString = jSONObject.optString("firstorderUnion");
            String optString2 = jSONObject.optString("firstorderChannel");
            String optString3 = jSONObject.optString("packageName");
            String optString4 = jSONObject.optString("callerPackage");
            String a2 = com.jd.fireeye.b.a.a(com.jd.fireeye.security.a.a, optString3);
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
            String d = com.jd.fireeye.security.a.d();
            d.a encryptDeviceCode = getEncryptDeviceCode(d);
            if (encryptDeviceCode != null) {
                d = encryptDeviceCode.a + "-" + encryptDeviceCode.b;
                jSONObject2.put("eufv", "1");
            }
            jSONObject2.put(NoStockRecommendHead.DEVICE, d);
            jSONObject2.put("package", optString3);
            jSONObject2.put("callerPackage", optString4);
            jSONObject2.put("extendedData1", optString5);
            jSONObject2.put("extendedData2", optString6);
            jSONObject2.put("extendedData3", optString7);
            jSONObject2.put("extendedData4", optString8);
            StringBuilder sb = new StringBuilder();
            sb.append(q.a("1b41a7042ce724d9ecaa5a15fe9fab7a" + optString + optString2 + com.jd.fireeye.security.a.d() + optString3 + optString4 + a2));
            StringBuilder sb2 = new StringBuilder();
            sb2.append(optString5);
            sb2.append(optString6);
            sb2.append(optString7);
            sb2.append(optString8);
            sb.append(q.a(sb2.toString()));
            jSONObject2.put("sign", q.a(sb.toString()));
        }
        return jSONObject2;
    }

    private static d.a getEncryptDeviceCode(String str) {
        try {
            if (TextUtils.isEmpty(str) || !str.contains("-")) {
                return null;
            }
            return com.jd.fireeye.b.d.a(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void getFirstOrderTracking(Context context, String str, JSONObject jSONObject) {
        if (TextUtils.equals(str, "0")) {
            requestAndSaveTracking(jSONObject);
        } else if (TextUtils.equals(str, "1")) {
            putTrackOrderParams(context, jSONObject);
        }
    }

    private static String getFirstOrderURL() {
        return com.jd.fireeye.security.a.q() ? BETA_FIRST_ORDER_URL : FIRST_ORDER_URL;
    }

    public static JSONObject getTrackOrderParams(Context context) {
        String str;
        String str2;
        initialShareStoreFile(context);
        JSONObject jSONObject = new JSONObject();
        try {
            f.b(TAG, "come to getTrackOrderParams");
            SharedPreferences sharedPreferences = shareStore;
            if (sharedPreferences != null) {
                str = sharedPreferences.getString(TRACK_KEY, "");
                str2 = shareStore.getString(TRACK_SIGNATURE, "");
                if (f.a) {
                    f.b(TAG, "trackKey = " + str + ",signature = " + str2);
                }
            } else {
                str = "";
                str2 = str;
            }
            if (str == null) {
                str = "";
            }
            jSONObject.put("fe_order_track", str);
            jSONObject.put("advertise_app", str2 != null ? str2 : "");
            String d = com.jd.fireeye.security.a.d();
            if (f.a) {
                f.b(TAG, "devicecode = " + d);
            }
            d.a encryptDeviceCode = getEncryptDeviceCode(d);
            if (encryptDeviceCode != null) {
                d = encryptDeviceCode.a + "-" + encryptDeviceCode.b;
                jSONObject.put("eufv", "1");
            }
            jSONObject.put("uuid", d);
            jSONObject.put("union_id", com.jd.fireeye.security.a.m());
            jSONObject.put("channel_id", com.jd.fireeye.security.a.l());
            jSONObject.put("appkey", "1b41a7042ce724d9ecaa5a15fe9fab7a");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (f.a) {
            f.b(TAG, String.format("getTrackOrderParams json: \n%s", g.a(jSONObject.toString())));
        }
        return jSONObject;
    }

    private static void initialShareStoreFile(Context context) {
        if (shareStore != null) {
            return;
        }
        if (context == null) {
            f.a(TAG, "create file but context is null!!!");
        } else {
            shareStore = context.getSharedPreferences("jd_order_identify_encrypt_client", 0);
        }
    }

    public static void putTrackOrderParams(Context context, JSONObject jSONObject) {
        f.b(TAG, "come to putTrackOrderParams");
        initialShareStoreFile(context);
        if (shareStore == null) {
            return;
        }
        if (jSONObject != null) {
            String str = "";
            String optString = jSONObject.optString(RecommendMtaUtils.TRACKING);
            String optString2 = jSONObject.optString("packageName");
            try {
                SharedPreferences.Editor edit = shareStore.edit();
                if (!TextUtils.isEmpty(optString)) {
                    edit.putString(TRACK_KEY, optString);
                }
                if (!TextUtils.isEmpty(optString2)) {
                    String str2 = new String(Base64.encode(optString2.getBytes(), 2));
                    try {
                        edit.putString(TRACK_SOURCE_NAME, str2).putString(TRACK_SIGNATURE, com.jd.fireeye.b.a.a(context, optString2));
                        str = str2;
                    } catch (Throwable unused) {
                        str = str2;
                    }
                }
                edit.commit();
            } catch (Throwable unused2) {
            }
            if (f.a) {
                f.b(TAG, "put trackid = " + optString + ",packName = " + optString2 + ",encryptSrc = " + str);
                return;
            }
            return;
        }
        f.a(TAG, "TrackParams is Empty!!!");
    }

    private static void requestAndSaveTracking(JSONObject jSONObject) {
        if (jSONObject != null) {
            if (f.a) {
                f.b(TAG, String.format("requestAndSaveTracking param json: \n%s", g.a(jSONObject.toString())));
            }
            if (TextUtils.isEmpty(jSONObject.optString("firstorderUnion"))) {
                return;
            }
            a aVar = new a(getFirstOrderURL(), jSONObject);
            aVar.a((com.jd.fireeye.network.f) new b(jSONObject));
            aVar.a(60000);
            aVar.a("TrackOrder." + System.currentTimeMillis());
            aVar.l();
        }
    }
}
