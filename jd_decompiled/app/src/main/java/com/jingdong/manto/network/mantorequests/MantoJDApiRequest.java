package com.jingdong.manto.network.mantorequests;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.manto.sdk.api.IGlobalParam;
import com.jingdong.manto.utils.MantoCryptoUtils;
import com.jingdong.manto.utils.MantoLog;
import com.tencent.mapsdk.internal.l4;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public abstract class MantoJDApiRequest implements MantoBaseRequest {
    protected static final String BETA_HOST = "https://beta-api.m.jd.com";
    private static final String HOST = "https://api.m.jd.com";

    /* loaded from: classes16.dex */
    class a implements Comparator<String> {
        a(MantoJDApiRequest mantoJDApiRequest) {
        }

        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(String str, String str2) {
            return str.compareTo(str2);
        }
    }

    public static JSONObject generateRequestParams(boolean z) {
        Map encryptUUID;
        JSONObject jSONObject = new JSONObject();
        IGlobalParam iGlobalParam = (IGlobalParam) com.jingdong.a.n(IGlobalParam.class);
        String uuid = iGlobalParam != null ? iGlobalParam.getUUID(com.jingdong.manto.b.f()) : "";
        String b = TextUtils.isEmpty(com.jingdong.manto.b.g().b("loginType")) ? "4" : com.jingdong.manto.b.g().b("loginType");
        String b2 = TextUtils.isEmpty(com.jingdong.manto.b.g().b("client")) ? "android" : com.jingdong.manto.b.g().b("client");
        String b3 = TextUtils.isEmpty(com.jingdong.manto.b.g().b("sign_app_id")) ? "jda-api" : com.jingdong.manto.b.g().b("sign_app_id");
        try {
            jSONObject.put(Configuration.PARTNER, com.jingdong.manto.b.g().b(Configuration.PARTNER));
            jSONObject.put(HybridSDK.APP_VERSION, com.jingdong.manto.b.g().b("versionName"));
            jSONObject.put(HybridSDK.APP_VERSION_CODE, com.jingdong.manto.b.g().b("versionCode"));
            jSONObject.put("uuid", uuid);
            jSONObject.put("loginType", b);
            jSONObject.put("networkType", com.jingdong.manto.utils.q.g());
            jSONObject.put("client", b2);
            jSONObject.put(HybridSDK.D_BRAND, com.jingdong.manto.utils.q.b());
            jSONObject.put(HybridSDK.D_MODEL, com.jingdong.manto.utils.q.d());
            jSONObject.put(HybridSDK.OS_VERSION, com.jingdong.manto.utils.q.f());
            jSONObject.put("screen", com.jingdong.manto.utils.q.c());
            jSONObject.put(l4.f16791e, String.valueOf(com.jingdong.manto.utils.q.a()));
            jSONObject.put("appid", b3);
            jSONObject.put("t", String.valueOf(System.currentTimeMillis()));
            if (iGlobalParam != null && (encryptUUID = iGlobalParam.getEncryptUUID(com.jingdong.a.g())) != null) {
                for (String str : encryptUUID.keySet()) {
                    jSONObject.put(str, (String) encryptUUID.get(str));
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public String getBetaHost() {
        return useJDNet() ? BETA_HOST : com.jingdong.manto.b.g().b("beta_host");
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public String getHost() {
        return useJDNet() ? com.jingdong.a.a ? getBetaHost() : HOST : com.jingdong.manto.b.g().b("host");
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public JSONObject getPostBody() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("host_id", com.jingdong.manto.b.e());
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public final JSONObject getRequestParams() {
        return generateRequestParams(useJDNet());
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public final String getSign(JSONObject jSONObject, String str) {
        try {
            String functionId = getFunctionId();
            TreeMap treeMap = new TreeMap(new a(this));
            if (jSONObject != null) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!TextUtils.isEmpty(jSONObject.optString(next))) {
                        treeMap.put(next, jSONObject.optString(next));
                    }
                }
            }
            if (!TextUtils.isEmpty(str)) {
                treeMap.put("body", str);
            }
            if (!TextUtils.isEmpty(functionId) && useJDNet()) {
                treeMap.put("functionId", functionId);
            }
            StringBuilder sb = new StringBuilder();
            Iterator it = treeMap.entrySet().iterator();
            while (it.hasNext()) {
                sb.append((String) ((Map.Entry) it.next()).getValue());
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
            if (sb.toString().endsWith(ContainerUtils.FIELD_DELIMITER)) {
                sb.setLength(sb.length() - 1);
            }
            String b = com.jingdong.manto.b.g().b("sign_secret");
            if (TextUtils.isEmpty(b)) {
                b = MantoCryptoUtils.a(com.jingdong.manto.b.l() + "7D6D16CC3D2BE89108F9DCFC9A855253", "616E746F");
            }
            MantoLog.d("sign  ", "secretKey --->  " + b);
            MantoLog.d("sign  ", "data --->  " + ((Object) sb));
            String a2 = MantoCryptoUtils.a(sb.toString().getBytes("UTF-8"), b.getBytes("UTF-8"));
            MantoLog.d("sign  ", "sign --->  " + a2);
            return !TextUtils.isEmpty(a2) ? a2.toLowerCase() : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    @Override // com.jingdong.manto.network.mantorequests.MantoBaseRequest
    public final boolean useJDNet() {
        return com.jingdong.a.f7578c;
    }
}
