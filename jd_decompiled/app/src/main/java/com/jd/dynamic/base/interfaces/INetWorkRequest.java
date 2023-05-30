package com.jd.dynamic.base.interfaces;

import android.app.Activity;
import com.jd.dynamic.entity.RequestEntity;
import java.io.File;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public interface INetWorkRequest {
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";

    /* loaded from: classes13.dex */
    public interface DownloadCallBack {
        void onError(ErrorResponse errorResponse);

        void onPause();

        void onProgress(int i2, int i3);

        void onStart();

        void onSuccess(File file);
    }

    /* loaded from: classes13.dex */
    public static class ErrorResponse {
        public int errorCode;
        public String errorMsg;

        public ErrorResponse() {
        }

        public ErrorResponse(int i2, String str) {
            this.errorCode = i2;
            this.errorMsg = str;
        }

        public String toString() {
            return "ErrorResponse{errorCode=" + this.errorCode + ", errorMsg='" + this.errorMsg + "'}";
        }
    }

    /* loaded from: classes13.dex */
    public interface ResponseCallBack {
        void onError(ErrorResponse errorResponse);

        void onStart();

        void onSuccess(JSONObject jSONObject);
    }

    void downloadFile(String str, String str2, String str3, DownloadCallBack downloadCallBack);

    String getNetworkType();

    void requestWithFunctionId(Activity activity, RequestEntity requestEntity, ResponseCallBack responseCallBack);

    void requestWithHost(String str, String str2, String str3, ResponseCallBack responseCallBack);

    void sendRequest(Activity activity, RequestEntity requestEntity, ResponseCallBack responseCallBack);
}
