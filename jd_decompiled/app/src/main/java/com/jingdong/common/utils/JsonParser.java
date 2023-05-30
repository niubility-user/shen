package com.jingdong.common.utils;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JsonParser {
    private static final String TAG = "JsonParser";

    public static JDJSONObject parseParamsJDJsonFromString(String str) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (str == null) {
            return jDJSONObject;
        }
        if (!str.startsWith("{")) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return jDJSONObject;
                }
                for (String str2 : str.trim().split(DYConstants.DY_REGEX_COMMA)) {
                    String[] split = str2.trim().split(ContainerUtils.KEY_VALUE_DELIMITER);
                    if (split.length >= 2) {
                        try {
                            jDJSONObject.put(split[0], (Object) split[1]);
                        } catch (Exception e2) {
                            if (OKLog.E) {
                                OKLog.e(TAG, e2);
                            }
                        }
                    }
                }
                return jDJSONObject;
            } catch (Exception e3) {
                if (OKLog.E) {
                    OKLog.e(TAG, e3);
                    return jDJSONObject;
                }
                return jDJSONObject;
            }
        }
        try {
            return JDJSON.parseObject(str);
        } catch (Exception e4) {
            if (OKLog.E) {
                OKLog.e(TAG, e4);
                return jDJSONObject;
            }
            return jDJSONObject;
        }
    }

    public static JDJSONObject parseParamsJDJsonUnsafe(String str) {
        if (str == null) {
            return new JDJSONObject();
        }
        if (!str.startsWith("{")) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            if (TextUtils.isEmpty(str)) {
                return jDJSONObject;
            }
            String[] split = str.trim().split(DYConstants.DY_REGEX_COMMA);
            if (split.length > 0) {
                int length = split.length;
                for (int i2 = 0; i2 < length; i2++) {
                    String str2 = split[i2];
                    String[] split2 = str2 != null ? str2.trim().split(ContainerUtils.KEY_VALUE_DELIMITER) : null;
                    if (split2 != null && split2.length >= 2) {
                        jDJSONObject.put(split2[0], (Object) split2[1]);
                    }
                }
                return jDJSONObject;
            }
            return jDJSONObject;
        }
        return JDJSON.parseObject(str);
    }

    public static JSONObjectProxy parseParamsJsonFromString(String str) {
        JSONObjectProxy jSONObjectProxy = new JSONObjectProxy();
        if (str == null) {
            return jSONObjectProxy;
        }
        if (!str.startsWith("{")) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return jSONObjectProxy;
                }
                for (String str2 : str.trim().split(DYConstants.DY_REGEX_COMMA)) {
                    String[] split = str2.trim().split(ContainerUtils.KEY_VALUE_DELIMITER);
                    if (split.length >= 2) {
                        try {
                            jSONObjectProxy.put(split[0], split[1]);
                        } catch (JSONException e2) {
                            if (OKLog.E) {
                                OKLog.e(TAG, e2);
                            }
                        }
                    }
                }
                return jSONObjectProxy;
            } catch (Exception e3) {
                if (OKLog.E) {
                    OKLog.e(TAG, e3);
                    return jSONObjectProxy;
                }
                return jSONObjectProxy;
            }
        }
        try {
            return new JSONObjectProxy(new JSONObject(str));
        } catch (JSONException e4) {
            if (OKLog.E) {
                OKLog.e(TAG, e4);
                return jSONObjectProxy;
            }
            return jSONObjectProxy;
        }
    }

    public static JSONObjectProxy parseParamsJsonUnsafe(String str) throws Exception {
        if (str == null) {
            return new JSONObjectProxy();
        }
        if (!str.startsWith("{")) {
            JSONObjectProxy jSONObjectProxy = new JSONObjectProxy();
            if (TextUtils.isEmpty(str)) {
                return jSONObjectProxy;
            }
            String[] split = str.trim().split(DYConstants.DY_REGEX_COMMA);
            if (split.length > 0) {
                int length = split.length;
                for (int i2 = 0; i2 < length; i2++) {
                    String str2 = split[i2];
                    String[] split2 = str2 != null ? str2.trim().split(ContainerUtils.KEY_VALUE_DELIMITER) : null;
                    if (split2 != null && split2.length >= 2) {
                        jSONObjectProxy.put(split2[0], split2[1]);
                    }
                }
                return jSONObjectProxy;
            }
            return jSONObjectProxy;
        }
        return new JSONObjectProxy(new JSONObject(str));
    }

    public static String parseStringFromJsonObject(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "";
        }
        String jSONObject2 = jSONObject.toString();
        return TextUtils.isEmpty(jSONObject2) ? "" : jSONObject2.replaceAll("\"", "\\\"");
    }
}
