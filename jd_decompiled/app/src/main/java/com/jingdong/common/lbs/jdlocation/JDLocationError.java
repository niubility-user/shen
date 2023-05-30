package com.jingdong.common.lbs.jdlocation;

import org.json.JSONObject;

/* loaded from: classes.dex */
public class JDLocationError {
    public static final int CODE_EXCEPTION = 300;
    public static final int CODE_LOOPER_NULL = 201;
    public static final int CODE_NET_DATA_ERROR = 400;
    public static final int CODE_NOT_FOREGROUND = 104;
    public static final int CODE_NOT_HAS_PRIVACY = 103;
    public static final int CODE_NOT_LOCATION = 105;
    public static final int CODE_NO_PERMISSION = 200;
    public static final int CODE_NO_SCENE_ALLOWED = 210;
    public static final int CODE_OVERSEAS_ERROR = 101;
    public static final int CODE_PROVIDER_NULL = 203;
    public static final int CODE_REGION_ERROR = 100;
    public static final int CODE_SYS_GPS_ERROR = 102;
    public static final int CODE_TIME_OUT = 202;
    public static final String MSG_EXCEPTION = "\u7cfb\u7edfException";
    public static final String MSG_LOOPER_NULL = "\u8c03\u7528\u7ebf\u7a0b\u5f02\u5e38";
    public static final String MSG_NET_DATA_ERROR = "\u540e\u53f0\u8fd4\u56de\u6570\u636e\u5f02\u5e38";
    public static final String MSG_NOT_FOREGROUND = "\u5e94\u7528\u4e0d\u5728\u524d\u53f0";
    public static final String MSG_NOT_HAS_PRIVACY = "\u7528\u6237\u672a\u540c\u610f\u91c7\u96c6\u4fe1\u606f";
    public static final String MSG_NOT_LOCATION = "\u9ed1\u540d\u5355\u7981\u7528\u5b9a\u4f4d\u529f\u80fd";
    public static final String MSG_NO_PERMISSION = "\u7f3a\u5c11\u5b9a\u4f4d\u6743\u9650";
    public static final String MSG_NO_SCENE_ALLOWED = "\u7f3a\u5c11\u573a\u666f\u8bb8\u53ef";
    public static final String MSG_OVERSEAS_ERROR = "\u533a\u57df\u5730\u5740\u83b7\u53d6\u5931\u8d25";
    public static final String MSG_PROVIDER_NULL = "\u8bf7\u786e\u8ba4GPS/\u4f4d\u7f6e\u670d\u52a1\u5f00\u5173\u662f\u5426\u6253\u5f00(\u5b9a\u4f4dProvider\u4e3a\u7a7a)";
    public static final String MSG_REGION_ERROR = "\u533a\u57df\u6570\u636e\u5f02\u5e38";
    public static final String MSG_SYS_GPS_ERROR = "\u7cfb\u7edf\u7ecf\u7eac\u5ea6\u83b7\u53d6\u5931\u8d25";
    public static final String MSG_TIME_OUT = "\u5b9a\u4f4d\u670d\u52a1\u8d85\u65f6";
    private int code;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public String getJsonForWeb() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", this.code);
            jSONObject.put("message", this.msg);
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(int i2) {
        this.code = i2;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
