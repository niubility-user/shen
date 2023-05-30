package com.jingdong.app.mall.bundle.jdrhsdk.api;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class JDRiskHandleError {
    public static final int CODE_605_AND_CHECK_A2 = -3005;
    public static final int CODE_APP_BACKGROUND = -1002;
    public static final int CODE_CHECK_ERROR_FIND_ZERO = -2002;
    public static final int CODE_CHECK_HANDLE_TYPE_ERROR = -2001;
    public static final int CODE_CHECK_HTTP_RESPONSE_ERROR = -2003;
    public static final int CODE_ENTER_LOGIN = -3003;
    public static final int CODE_JAVA_EXCEPTION = -1001;
    public static final int CODE_LOGIN_TIMEOUT = -3004;
    public static final int CODE_SDK_NOT_INIT = -2004;
    public static final int CODE_USER_CANCEL = -3001;
    public static final int CODE_USER_RETRY_FAILED = -3002;
    public static final String MSG_605_AND_CHECK_A2 = "\u7f51\u5173605\u4e0e\u767b\u5f55\u6001checkA2\u51b2\u7a81";
    public static final String MSG_APP_BACKGROUND = "\u5e94\u7528\u5904\u4e8e\u540e\u53f0";
    public static final String MSG_CHECK_ERROR_FIND_ZERO = "\u5931\u8d25\u6570\u636e\u4e2d\u5b58\u5728code=0,code\u4fee\u6b63\u4e3a\u8d1f\u6570";
    public static final String MSG_CHECK_HANDLE_TYPE_ERROR = "\u5904\u7f6e\u7c7b\u578b\u4e0d\u652f\u6301";
    public static final String MSG_CHECK_HTTP_RESPONSE_ERROR = "\u63a5\u53e3\u8fd4\u56de\u6570\u636e\u683c\u5f0f\u5f02\u5e38";
    public static final String MSG_ENTER_LOGIN = "\u5df2\u5728\u767b\u5f55\u6d41\u7a0b\u4e2d";
    public static final String MSG_JAVA_EXCEPTION = "\u672c\u5730\u7cfb\u7edf\u4ee3\u7801\u5f02\u5e38";
    public static final String MSG_LOGIN_TIMEOUT = "\u767b\u5f55\u5904\u7f6e\u6d41\u7a0b\u8d85\u65f6";
    public static final String MSG_SDK_NOT_INIT = "SDK\u672a\u521d\u59cb\u5316";
    public static final String MSG_USER_CANCEL = "\u7528\u6237\u53d6\u6d88\u9a8c\u8bc1";
    public static final String MSG_USER_RETRY_FAILED = "\u7528\u6237\u91cd\u8bd5\u65e0\u6548";
    private int code = -1;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public String getJsonStr() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", getCode());
            jSONObject.put("msg", getMsg());
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public String getMsg() {
        return TextUtils.isEmpty(this.msg) ? "" : this.msg;
    }

    public void setCode(int i2) {
        this.code = i2;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
