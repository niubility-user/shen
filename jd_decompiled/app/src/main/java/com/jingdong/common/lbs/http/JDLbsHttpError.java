package com.jingdong.common.lbs.http;

import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDLbsHttpError {
    public static final int CODE_DEFAULTCACHE_ERROR = 302;
    public static final int CODE_DEFAULT_ADDRESS = 500;
    public static final int CODE_EXCEPTION = 300;
    public static final int CODE_GLOBAL_LATLNG_EMPTY = 502;
    public static final int CODE_LOCATION_LATLNG_EMPTY = 501;
    public static final int CODE_NOT_SAME_CITY = 1001;
    public static final int CODE_SUBCODE_ERROR = 301;
    public static final int CODE_TIME_OUT = 503;
    public static final String MSG_DEFAULTCACHE_ERROR = "\u83b7\u53d6\u5168\u6e20\u9053\u5c55\u793a\u5730\u5740\u5931\u8d25\uff0c\u8fd4\u56de\u515c\u5e95\u5730\u5740";
    public static final String MSG_DEFAULT_ADDRESS = "\u540e\u53f0\u63a5\u53e3\u8981\u6c42\u8fd4\u56de\u5168\u7ad9\u5730\u5740";
    public static final String MSG_EXCEPTION = "\u7cfb\u7edfException\uff0c\u8fd4\u56de\u5168\u7ad9\u5730\u5740\u515c\u5e95";
    public static final String MSG_GLOBAL_LATLNG_EMPTY = "\u5168\u7ad9\u5730\u5740\u7ecf\u7eac\u5ea6\u4e3a\u7a7a\uff0c\u8fd4\u56de\u5317\u4eac\u5730\u5740\u515c\u5e95";
    public static final String MSG_LOCATION_LATLNG_EMPTY = "\u5b9a\u4f4d\u5730\u5740\u7ecf\u7eac\u5ea6\u4e3a\u7a7a\uff0c\u8fd4\u56de\u5168\u7ad9\u5730\u5740\u515c\u5e95";
    public static final String MSG_NOT_SAME_CITY = "\u4e0d\u5728\u540c\u57ce\u8303\u56f4";
    public static final String MSG_SUBCODE_ERROR = "\u540e\u53f0\u4e1a\u52a1\u4ee3\u7801\u62a5\u9519\uff0c\u8fd4\u56de\u5168\u7ad9\u5730\u5740\u515c\u5e95";
    public static final String MSG_TIME_OUT = "\u5b9a\u4f4d\u8d85\u65f6\uff0c\u8fd4\u56de\u5168\u7ad9\u5730\u5740\u515c\u5e95";
    private int code;
    private String message;

    public int getCode() {
        return this.code;
    }

    public String getJsonStr() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", this.code);
            jSONObject.put("message", this.message);
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String getMessage() {
        String str = this.message;
        return str == null ? "" : str;
    }

    public void setCode(int i2) {
        this.code = i2;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
