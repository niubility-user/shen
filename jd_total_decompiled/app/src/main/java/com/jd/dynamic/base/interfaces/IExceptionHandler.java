package com.jd.dynamic.base.interfaces;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public interface IExceptionHandler {

    /* loaded from: classes13.dex */
    public static class DynamicExceptionData {
        public static final String TYPE_ACTION = "action";
        public static final String TYPE_BIND = "bind";
        public static final String TYPE_BIND_ASYNC = "bindAsync";
        public static final String TYPE_DOWNLOAD = "download";
        public static final String TYPE_EXP = "expV2";
        public static final String TYPE_FILE = "file";
        public static final String TYPE_JS = "js";
        public static final String TYPE_JS_ADDRESS = "jsAddress";
        public static final String TYPE_LOCAL_BACKUP = "backUp";
        public static final String TYPE_LOCAL_BACKUP_ERROR = "backUpErr";
        public static final String TYPE_MISS_MD5 = "missmd5";
        public static final String TYPE_MTA = "mta";
        public static final String TYPE_PARSE = "parse";
        public static final String TYPE_QUERY_TEMPLATES = "queryTemplates";
        public static final String TYPE_RENDER = "renderEx";
        public static final String TYPE_REQUEST_CALLBACK = "callBack";
        public static final String TYPE_STORAGE = "storage";
        public static final String TYPE_UNZIP = "unzip";
        public String bizField;
        public String errorMsg;
        public Exception exception;
        public int httpRespCode;
        public String systemCode;
        public String type;

        public DynamicExceptionData() {
        }

        public DynamicExceptionData(String str, String str2, String str3, String str4, int i2, Exception exc, JSONObject jSONObject) {
            this(str, str2, str3, str4, exc, jSONObject);
            this.httpRespCode = i2;
        }

        public DynamicExceptionData(String str, String str2, String str3, String str4, Exception exc, JSONObject jSONObject) {
            this.type = str;
            this.errorMsg = str2;
            this.bizField = str3;
            if (jSONObject != null) {
                String optString = jSONObject.optString("pckVersion");
                if (!TextUtils.isEmpty(optString)) {
                    this.bizField = str3 + "-pkv=" + optString;
                }
            }
            this.systemCode = str4;
            this.exception = exc;
        }
    }

    void handException(DynamicExceptionData dynamicExceptionData);
}
