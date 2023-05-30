package com.jingdong.jdsdk.utils;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public final class JsonEncryptUtil {
    public static final String ENC_KEY = "enc";
    public static final String ENC_PROFIX = "jdenc_";
    public static final String ENC_REDUNDANCY_PARAM_KEY = "phcre";
    public static final String ENC_REDUNDANCY_PARAM_VALUE = "v";
    public static final int ERRCODE_SVR_ENC_FAIL = 731;
    public static final String KEY_PHCENGINE_CONFIG = "phcEngineConfig";
    public static final String TAG = JsonEncryptUtil.class.toString();
    public static String sRedundencyStr = null;

    /* loaded from: classes14.dex */
    class a extends com.jingdong.sdk.phcenginesdk.d {
        a() {
        }

        @Override // com.jingdong.sdk.phcenginesdk.d, com.jd.phc.e.a
        public void a(String str, String str2) {
            super.a(str, str2);
            JDHttpTookit.getEngine().getPhcEncryptionPlugin().reportInitError(str, str2);
        }
    }

    public static JDJSONObject cloneJSONObject(JDJSONObject jDJSONObject) {
        try {
            return JDJSON.parseObject(jDJSONObject.toJSONString());
        } catch (Throwable th) {
            if (OKLog.D) {
                th.printStackTrace();
                return null;
            }
            return null;
        }
    }

    public static Map<String, String> decrypt(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return com.jingdong.sdk.phcenginesdk.b.a(JDHttpTookit.getEngine().getApplicationContext(), str);
            }
            throw new IllegalArgumentException("cipher content is empty.");
        } catch (Throwable th) {
            if (OKLog.D) {
                th.printStackTrace();
            }
            JDHttpTookit.getEngine().getPhcEncryptionPlugin().reportDecryptError(th);
            return new HashMap();
        }
    }

    public static Map<String, String> decryptV2(String str, Map<String, String> map, HttpSetting httpSetting) {
        try {
            Map<String, String> a2 = com.jingdong.sdk.phcenginesdk.b.a(JDHttpTookit.getEngine().getApplicationContext(), str);
            if (a2 != null && !a2.isEmpty()) {
                if (map != null && !map.isEmpty()) {
                    boolean z = true;
                    Iterator<Map.Entry<String, String>> it = a2.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Map.Entry<String, String> next = it.next();
                        String value = next.getValue();
                        String str2 = map.get(next.getKey());
                        if (!TextUtils.isEmpty(str2) && !Pattern.compile(str2).matcher(value).matches()) {
                            z = false;
                            break;
                        }
                    }
                    if (!z) {
                        throw new IllegalStateException("verify result by pattern failed.");
                    }
                }
                return a2;
            }
            throw new IllegalStateException("decrypt empty result.");
        } catch (Throwable th) {
            if (OKLog.D) {
                th.printStackTrace();
            }
            JDHttpTookit.getEngine().getPhcEncryptionPlugin().reportDecryptError(th);
            resendClearRequest(httpSetting);
            return new HashMap();
        }
    }

    public static JDJSONObject encryptJson(JDJSONObject jDJSONObject) {
        HashMap hashMap = new HashMap();
        Iterator<String> it = jDJSONObject.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            Object obj = jDJSONObject.get(next);
            encryptObject(obj);
            if (next.startsWith(ENC_PROFIX)) {
                hashMap.put(next.replace(ENC_PROFIX, ""), obj != null ? obj.toString() : "");
                it.remove();
                jDJSONObject.remove(next);
            }
        }
        if (!hashMap.isEmpty()) {
            jDJSONObject.put(ENC_KEY, com.jingdong.sdk.phcenginesdk.b.b(JDHttpTookit.getEngine().getApplicationContext(), hashMap));
        }
        return jDJSONObject;
    }

    public static void encryptJsonArray(JDJSONArray jDJSONArray) {
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            try {
                encryptObject(jDJSONArray.get(i2));
            } catch (Throwable th) {
                if (OKLog.E) {
                    OKLog.e(TAG, "encrypt json array error!", th);
                    return;
                }
                return;
            }
        }
    }

    public static void encryptObject(Object obj) {
        if (obj == JSONObject.NULL) {
            return;
        }
        if (obj instanceof JDJSONObject) {
            encryptJson((JDJSONObject) obj);
        } else if (obj instanceof JDJSONArray) {
            encryptJsonArray((JDJSONArray) obj);
        }
    }

    public static String getEncryptBodyStr(HttpSetting httpSetting, JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            JDJSONObject cloneJSONObject = cloneJSONObject(jDJSONObject);
            if (cloneJSONObject.toString().contains(ENC_PROFIX)) {
                boolean z = false;
                if (shouldEncryptBody() && !httpSetting.isEncryptionDowngrade()) {
                    try {
                        encryptJson(cloneJSONObject);
                        cloneJSONObject.put("deis", (Object) "ey");
                        return cloneJSONObject.toString();
                    } catch (Throwable th) {
                        if (OKLog.D) {
                            th.printStackTrace();
                        }
                        JDHttpTookit.getEngine().getPhcEncryptionPlugin().reportEncryptError(httpSetting.getUrl(), th);
                        z = true;
                    }
                }
                if (z) {
                    JDHttpTookit.getEngine().getPhcEncryptionPlugin().resendEncryptError(httpSetting.getUrl());
                }
                cloneJSONObject.put("deis", (Object) "dy");
                return cloneJSONObject.toString().replaceAll(ENC_PROFIX, "");
            }
            if (shouldEncryptBody()) {
                cloneJSONObject.put("deis", (Object) (httpSetting.isEncryptionDowngrade() ? "dy" : "ey"));
                if (!httpSetting.isEncryptionDowngrade() && !TextUtils.isEmpty(getEncryptRedundencyValue(httpSetting))) {
                    cloneJSONObject.put(ENC_KEY, (Object) getEncryptRedundencyValue(httpSetting));
                }
            }
            return cloneJSONObject.toString();
        } else if (shouldEncryptBody()) {
            JDJSONObject jDJSONObject2 = new JDJSONObject();
            jDJSONObject2.put("deis", (Object) (httpSetting.isEncryptionDowngrade() ? "dy" : "ey"));
            if (!httpSetting.isEncryptionDowngrade() && !TextUtils.isEmpty(getEncryptRedundencyValue(httpSetting))) {
                jDJSONObject2.put(ENC_KEY, (Object) getEncryptRedundencyValue(httpSetting));
            }
            return jDJSONObject2.toString();
        } else {
            return "{}";
        }
    }

    public static synchronized String getEncryptRedundencyValue(HttpSetting httpSetting) {
        String str;
        synchronized (JsonEncryptUtil.class) {
            if (TextUtils.isEmpty(sRedundencyStr)) {
                HashMap hashMap = new HashMap();
                hashMap.put(ENC_REDUNDANCY_PARAM_KEY, "v");
                sRedundencyStr = com.jingdong.sdk.phcenginesdk.b.b(JDHttpTookit.getEngine().getApplicationContext(), hashMap);
            }
            str = sRedundencyStr;
        }
        return str;
    }

    public static void initPhcEngineSDK(Context context) {
        if (shouldEncryptBody()) {
            try {
                com.jingdong.sdk.phcenginesdk.b.c(context, new a());
            } catch (Throwable th) {
                if (OKLog.D) {
                    th.printStackTrace();
                }
            }
        }
    }

    static void resendClearRequest(HttpSetting httpSetting) {
        if (httpSetting == null) {
            return;
        }
        try {
            httpSetting.isEncryptionDowngrade = true;
            HttpGroup httpGroupaAsynPool = HttpGroupUtils.getHttpGroupaAsynPool();
            httpSetting.resetHttpSetting();
            httpGroupaAsynPool.add(httpSetting);
        } catch (Throwable unused) {
        }
    }

    public static boolean shouldEncryptBody() {
        return "1".equals(ConfigUtil.getStringFromPreference(KEY_PHCENGINE_CONFIG));
    }
}
