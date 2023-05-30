package com.jingdong.jdsdk.network.toolbox;

import com.jd.framework.network.error.JDError;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes.dex */
public class HttpError extends Exception {
    public static final int ANTI_BRUSH = 55;
    public static final int COLOR_KNOWN_CODE = 5;
    public static final int DATE_CODE = 4;
    public static final int EXCEPTION = 0;
    public static final String EXCEPTION_MESSAGE_ATTESTATION_RSA = "attestation RSA";
    public static final String EXCEPTION_MESSAGE_ATTESTATION_WIFI = "attestation WIFI";
    public static final String EXCEPTION_MESSAGE_NO_CACHE = "no cache";
    public static final String EXCEPTION_MESSAGE_NO_READY = "no ready";
    public static final int HTTP_STATUS_ERROR = 4;
    public static final int JSON_CODE = 3;
    public static final int JSON_PARSE_ERROR = 11;
    public static final int NO_CACHE_ERROR = 10;
    public static final int NO_LOGIN_CODE = 33;
    public static final int RESPONSE_CODE = 2;
    public static final int TIME_OUT = 1;
    public static final String VERIFY_STATUS_OVERCOUNT = "[481]";
    public static final String VERIFY_STATUS_OVERTIME = "[480]";
    public static final String VERIFY_STATUS_SUCCESS = "[0]";
    public static final String VERIFY_STATUS_UNKNOW = "[482]";
    private static final long serialVersionUID = 7786747359680769965L;
    private int errorCode;
    private Throwable exception;
    private HttpResponse httpResponse;
    private boolean isFinalDowngradeError;
    private int jsonCode;
    private String message;
    private boolean noRetry;
    private int responseCode;
    private int times;
    private String validDataErrorCode;

    public HttpError() {
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorCodeStr() {
        int i2 = this.errorCode;
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? "UNKNOWN" : "JSON_CODE" : "RESPONSE_CODE" : "TIME_OUT" : "EXCEPTION";
    }

    public Throwable getException() {
        return this.exception;
    }

    public HttpResponse getHttpResponse() {
        return this.httpResponse;
    }

    public int getJsonCode() {
        return this.jsonCode;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public int getTimes() {
        return this.times;
    }

    public String getValidDataErrorCode() {
        String str = this.validDataErrorCode;
        return str == null ? "" : str;
    }

    public boolean isFinalDowngradeError() {
        return this.isFinalDowngradeError;
    }

    public boolean isNoRetry() {
        return this.noRetry;
    }

    public void setErrorCode(int i2) {
        this.errorCode = i2;
    }

    public void setException(Throwable th) {
        this.exception = th;
    }

    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public void setJsonCode(int i2) {
        this.jsonCode = i2;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setNoRetry(boolean z) {
        this.noRetry = z;
    }

    public void setResponseCode(int i2) {
        this.responseCode = i2;
    }

    public void setTimes(int i2) {
        this.times = i2;
    }

    public void setValidDataErrorCode(String str) {
        this.validDataErrorCode = str;
    }

    @Override // java.lang.Throwable
    public String toString() {
        if (getException() != null && OKLog.D) {
            OKLog.d("HttpGroup", "HttpError Exception -->> ", getException());
        }
        return "HttpError [errorCode=" + getErrorCodeStr() + ", exception=" + this.exception + ", jsonCode=" + this.jsonCode + ", message=" + this.message + ", responseCode=" + this.responseCode + ", time=" + this.times + "]";
    }

    public HttpError(Throwable th) {
        this.errorCode = 0;
        this.exception = th;
    }

    public HttpError(JDError jDError) {
        this.exception = jDError;
        this.isFinalDowngradeError = jDError.isDowngradeError();
        this.responseCode = jDError.getStatusCode();
    }
}
