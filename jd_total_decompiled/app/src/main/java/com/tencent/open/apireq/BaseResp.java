package com.tencent.open.apireq;

/* loaded from: classes9.dex */
public class BaseResp {
    public static final int CODE_ERROR_PARAMS = -2000;
    public static final int CODE_NOT_LOGIN = -2001;
    public static final int CODE_PERMISSION_NOT_GRANTED = -1003;
    public static final int CODE_QQ_LOW_VERSION = -1001;
    public static final int CODE_QQ_NOT_INSTALLED = -1000;
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_UNSUPPORTED_BRANCH = -1002;
    private int a = 0;
    private String b = "";

    protected String a(int i2) {
        return "Api call failed.";
    }

    public int getCode() {
        return this.a;
    }

    public String getErrorMsg() {
        return this.b;
    }

    public boolean isSuccess() {
        return this.a == 0;
    }

    public void setCode(int i2) {
        String str;
        this.a = i2;
        if (i2 == -2001) {
            str = "Not login.";
        } else if (i2 == -2000) {
            str = "The given params check failed.";
        } else if (i2 != 0) {
            switch (i2) {
                case -1002:
                    str = "The QQ branch (e.g. TIM) is not supported";
                    break;
                case -1001:
                    str = "QQ version is too low.";
                    break;
                case -1000:
                    str = "QQ is not installed.";
                    break;
                default:
                    str = a(i2);
                    break;
            }
        } else {
            str = "";
        }
        setErrorMsg(str);
    }

    public void setErrorMsg(String str) {
        this.b = str;
    }

    public String toString() {
        return "BaseResp{mCode=" + this.a + ", mErrorMsg='" + this.b + "'}";
    }
}
