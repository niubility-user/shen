package com.jd.aips.verify.api;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class Result implements Serializable {
    public static final int CODE_DATA_ERROR = 10002;
    public static final int CODE_DATA_FAKE = 1159;
    public static final int CODE_ENV_ERROR_108 = 108;
    public static final int CODE_ENV_ERROR_1154 = 1154;
    public static final int CODE_ENV_ERROR_1187 = 1187;
    public static final int CODE_ENV_WARN = 1183;
    public static final int CODE_FAILED = -1;
    public static final int CODE_INVALID_CRC = 1145;
    public static final int CODE_NETWORK_ERROR = 10003;
    public static final int CODE_NOT_PIC = 1103;
    public static final int CODE_OVER_RETRY_TIMES = 1166;
    public static final int CODE_SAME_IMAGE_CRC_MD5 = 1144;
    public static final int CODE_SAME_IMAGE_MD5 = 1143;
    public static final int CODE_SEVER_ERROR_1 = 1161;
    public static final int CODE_SEVER_ERROR_1186 = 1186;
    public static final int CODE_SEVER_ERROR_2 = 50;
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_TOKEN_TIMEOUT = 1160;
    public static final String KEY_CODE = "code";
    public static final String KEY_DATA = "data";
    public static final String KEY_MSG = "msg";
    static final long serialVersionUID = -7450955979769607554L;
    public String data;
    public int code = 0;
    public String msg = "";
    public String verifyId = "";
    public String promptMsg = "";

    public int getCode() {
        return this.code;
    }

    public String getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(int i2) {
        this.code = i2;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String toString() {
        return "Result{code=" + this.code + ", msg='" + this.msg + "', data=" + this.data + '}';
    }
}
