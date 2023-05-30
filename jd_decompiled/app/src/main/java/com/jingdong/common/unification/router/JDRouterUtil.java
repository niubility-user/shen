package com.jingdong.common.unification.router;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.unification.router.config.JDRouterConfig;
import java.net.URLDecoder;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDRouterUtil {
    private static final String TAG = "JDRouterUtil";

    public static void callBackComplete(CallBackListener callBackListener) {
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public static void callBackError(CallBackListener callBackListener, int i2) {
        if (callBackListener != null) {
            callBackListener.onError(i2);
        }
    }

    public static boolean isRouterJump() {
        return JDRouterConfig.isRouterOpen();
    }

    public static JSONObject parseParam(String str) {
        JSONObject jSONObject = new JSONObject();
        if (str.contains("?")) {
            String substring = str.substring(str.indexOf("?") + 1);
            if (!TextUtils.isEmpty(substring)) {
                for (String str2 : substring.split(ContainerUtils.FIELD_DELIMITER)) {
                    if (str2.contains(ContainerUtils.KEY_VALUE_DELIMITER)) {
                        try {
                            String[] split = str2.split(ContainerUtils.KEY_VALUE_DELIMITER);
                            if (split.length >= 2) {
                                jSONObject.put(split[0], URLDecoder.decode(split[1], "utf-8"));
                            } else if (split.length == 1) {
                                jSONObject.put(split[0], "");
                            }
                        } catch (Exception e2) {
                            if (JDRouterConfig.isDebug()) {
                                JDRouterConfig.e(TAG, e2);
                            }
                        }
                    }
                }
            }
            if (JDRouterConfig.isDebug()) {
                JDRouterConfig.d(TAG, "paramJsonObject:" + jSONObject.toString());
            }
            return jSONObject;
        }
        return jSONObject;
    }

    public static JDJSONObject parseParamToJDJson(String str) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (str.contains("?")) {
            String substring = str.substring(str.indexOf("?") + 1);
            if (!TextUtils.isEmpty(substring)) {
                for (String str2 : substring.split(ContainerUtils.FIELD_DELIMITER)) {
                    if (str2.contains(ContainerUtils.KEY_VALUE_DELIMITER)) {
                        try {
                            String[] split = str2.split(ContainerUtils.KEY_VALUE_DELIMITER);
                            if (split.length >= 2) {
                                jDJSONObject.put(split[0], (Object) URLDecoder.decode(split[1], "utf-8"));
                            } else if (split.length == 1) {
                                jDJSONObject.put(split[0], (Object) "");
                            }
                        } catch (Exception e2) {
                            if (JDRouterConfig.isDebug()) {
                                JDRouterConfig.e(TAG, e2);
                            }
                        }
                    }
                }
            }
            if (JDRouterConfig.isDebug()) {
                JDRouterConfig.d(TAG, "paramJsonObject:" + jDJSONObject.toString());
            }
            return jDJSONObject;
        }
        return jDJSONObject;
    }
}
