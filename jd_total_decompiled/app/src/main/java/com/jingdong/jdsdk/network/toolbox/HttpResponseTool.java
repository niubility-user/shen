package com.jingdong.jdsdk.network.toolbox;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.network.SignatureAlertController;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class HttpResponseTool {
    public static final int JSON_CODE_ANTI_BRUSH = 602;
    public static final int JSON_CODE_ENCRYPT_FAIL = 731;
    public static final int JSON_CODE_GATEWAY_QUEUE = 601;
    public static final int JSON_CODE_GATEWAY_STALE = 603;
    public static final int JSON_CODE_HARD_VERIFY = 605;
    public static final int JSON_CODE_LOGIN_STATUS = 3;
    public static final int JSON_CODE_OK = 0;
    public static final int JSON_CODE_PARAM_ERROR = 2;
    public static final int JSON_CODE_PAY_VERIFY = 40;
    public static final int JSON_CODE_SAFETY_FAILED = 604;
    public static final int JSON_CODE_SERVER_ERROR = -1;
    public static final int JSON_CODE_SIGNATURE = 600;
    public static final int JSON_CODE_UNKNOWN = 1;
    public static final String TAG = "HttpResponseTool";

    public static <T> int checkSucceed(T t, HttpSetting httpSetting, HttpResponse httpResponse) throws HttpError {
        Integer valueOf;
        String string;
        if (t == null) {
            if (OKLog.D) {
                OKLog.d(TAG, "checkSucceed() ---> \u63a5\u6536\u5230\u65e0\u8fd4\u56de\u5185\u5bb9\u7684\u8bf7\u6c42\uff0c\u6309\u7167\u6210\u529f\u5bf9\u5f85");
            }
            return 0;
        }
        String functionId = httpSetting.getFunctionId();
        Map<String, String> header = httpResponse.getHeader();
        if (OKLog.D) {
            OKLog.d(TAG, functionId + " - checkSucceed jsonObject:" + t);
        }
        String str = null;
        try {
            if (t instanceof JSONObject) {
                valueOf = Integer.valueOf(((JSONObject) t).optString("code", "0"));
            } else {
                valueOf = Integer.valueOf(((JDJSONObject) t).optString("code", "0"));
            }
            try {
                if (t instanceof JSONObject) {
                    string = ((JSONObject) t).getString("msg");
                } else {
                    string = ((JDJSONObject) t).getString("msg");
                }
                str = string;
            } catch (Exception unused) {
            }
            if (valueOf != null && valueOf.intValue() != 0) {
                if (RuntimeConfigHelper.enableJsonCodeEventListener()) {
                    try {
                        JDHttpTookit.getEngine().getBusinessJsonCodeEventListener().onJsonCodeReceive(valueOf.toString(), httpSetting, httpResponse);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                if (valueOf.intValue() != 601 && valueOf.intValue() != 731 && valueOf.intValue() != 604 && valueOf.intValue() != 605) {
                    if (header != null && header.containsKey("X-API-Sign-Message") && TextUtils.equals(header.get("X-API-Sign-Message"), "stale")) {
                        return 603;
                    }
                    HttpError httpError = new HttpError();
                    httpError.setErrorCode(3);
                    if (valueOf.intValue() == 3) {
                        JDHttpTookit.getEngine().getLoginUserControllerImpl().reportCode3(functionId);
                        httpError.setErrorCode(33);
                    }
                    if (valueOf.intValue() == -1) {
                        httpError.setErrorCode(4);
                    }
                    if (valueOf.intValue() == 1 || valueOf.intValue() == 2 || valueOf.intValue() == 600) {
                        httpError.setErrorCode(5);
                    }
                    if (valueOf.intValue() == 602) {
                        httpError.setErrorCode(55);
                    }
                    if (valueOf.intValue() != 40 && JDHttpTookit.getEngine().isEnableBusinessLayerCheck()) {
                        httpError.setJsonCode(valueOf.intValue());
                        httpError.setMessage(str);
                        httpError.setNoRetry(true);
                        throw httpError;
                    }
                } else {
                    return valueOf.intValue();
                }
            }
            if (valueOf != null) {
                return valueOf.intValue();
            }
            return 0;
        } catch (NumberFormatException e2) {
            throw new HttpError(e2);
        }
    }

    public static JSONObjectProxy handlerEncrypt(JSONObjectProxy jSONObjectProxy) throws UnsupportedEncodingException, JSONException {
        return SignatureAlertController.handlerEncrypt(jSONObjectProxy);
    }
}
