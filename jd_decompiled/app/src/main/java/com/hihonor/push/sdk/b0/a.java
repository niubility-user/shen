package com.hihonor.push.sdk.b0;

import android.util.SparseArray;
import com.hihonor.push.sdk.common.data.ApiException;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes12.dex */
public enum a {
    SUCCESS(0, "success"),
    ERROR_NOT_SUPPORT_PUSH(8001000, "device is not support push."),
    ERROR_MAIN_THREAD(8001001, "operation in MAIN thread prohibited."),
    ERROR_NO_TOKEN(8001004, "token missing."),
    ERROR_NO_APPID(8001002, "app id missing."),
    ERROR_NOT_INITIALIZED(8001005, "SDK not initialized"),
    ERROR_CERT_FINGERPRINT_EMPTY(8001003, "certificate fingerprint empty."),
    ERROR_BIND_SERVICE(8002001, "bind service failed."),
    ERROR_SERVICE_DISCONNECTED(8002002, "service disconnected."),
    ERROR_SERVICE_TIME_OUT(8002003, "service connect time out."),
    ERROR_SERVICE_ARGUMENTS_INVALID(8002004, "service arguments invalid."),
    ERROR_SERVICE_NULL_BINDING(8002005, "service being bound has return null."),
    ERROR_SERVICE_INVALID(8002006, "service invalid."),
    ERROR_SERVICE_DISABLED(8002007, "service disabled."),
    ERROR_SERVICE_MISSING(8002008, "service missing."),
    ERROR_PUSH_SERVER(8003001, "push server error."),
    ERROR_UNKNOWN(8003002, "unknown error."),
    ERROR_INTERNAL_ERROR(8003003, "internal error."),
    ERROR_ARGUMENTS_INVALID(8003004, "arguments invalid."),
    ERROR_OPERATION_FREQUENTLY(8003005, "operation too frequently."),
    ERROR_NETWORK_NONE(8003006, "no network."),
    ERROR_STATEMENT_AGREEMENT(8003007, "not statement agreement."),
    ERROR_SERVICE_REQUEST_TIME_OUT(8003008, "service request time out."),
    ERROR_HTTP_OPERATION_FREQUENTLY(R2.drawable.main_bg, "http operation too frequently.");
    
    public static final SparseArray<a> ENUM_MAPPER = new SparseArray<>();
    public String message;
    public int statusCode;

    static {
        a[] values = values();
        for (int i2 = 0; i2 < 24; i2++) {
            a aVar = values[i2];
            ENUM_MAPPER.put(aVar.statusCode, aVar);
        }
    }

    a(int i2, String str) {
        this.statusCode = i2;
        this.message = str;
    }

    public static a fromCode(int i2) {
        return ENUM_MAPPER.get(i2, ERROR_UNKNOWN);
    }

    public int getErrorCode() {
        return this.statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public ApiException toApiException() {
        return new ApiException(getErrorCode(), getMessage());
    }
}
