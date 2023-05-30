package com.jd.aips.verify.api;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.aips.common.utils.FsGsonUtil;
import com.jd.aips.common.utils.SecurityChannelUtils;

/* loaded from: classes12.dex */
public class VerifyApi {
    public static final String BASE_URL_PRODUCT = "https://identify.jd.com";
    public static final String BASE_URL_TEST = "https://10.222.31.116:8005";
    public static final String BASE_URL_UAT = "https://identify-pre.jd.com";
    public static final String GATEWAY_URL_PRODUCT = "https://facegw-rec.jd.com";
    public static final String GATEWAY_URL_TEST = "https://facegw-pre.jd.com";
    public static final String GATEWAY_URL_UAT = "https://facegw-pre.jd.com";

    private static String buildPostContent(@NonNull Context context, @NonNull VerifyRequest verifyRequest) throws ApiException {
        VerifyRequestWrapper verifyRequestWrapper = new VerifyRequestWrapper(verifyRequest);
        byte[] encodeDataToServer = SecurityChannelUtils.encodeDataToServer(context, FsGsonUtil.toJson(verifyRequest));
        if (encodeDataToServer != null) {
            verifyRequestWrapper.data = new String(encodeDataToServer);
            return FsGsonUtil.toJson(verifyRequestWrapper);
        }
        throw new ApiException(10002, "\u7cfb\u7edf\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
    }

    private static String decryptResponseData(@NonNull Context context, String str) throws ApiException {
        Result result = (Result) FsGsonUtil.fromJson(str, Result.class);
        if (result != null) {
            String str2 = result.msg;
            if (!TextUtils.isEmpty(result.promptMsg)) {
                str2 = result.promptMsg;
            }
            if (!TextUtils.isEmpty(result.data)) {
                byte[] decodeDataFromServer = SecurityChannelUtils.decodeDataFromServer(context, result.data);
                if (decodeDataFromServer != null) {
                    String str3 = new String(decodeDataFromServer);
                    String str4 = "decrypt data\uff1a" + str3;
                    return str3;
                }
                throw new ApiException(10002, "\u7cfb\u7edf\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
            } else if (result.code == 0) {
                throw new ApiException(10002, "\u7cfb\u7edf\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
            } else {
                throw new ApiException(result.code, str2);
            }
        }
        throw new ApiException(10002, "\u7cfb\u7edf\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
    }

    public static <T extends DataWrapper> T toRequest(@NonNull Context context, @NonNull String str, @NonNull VerifyRequest verifyRequest, @NonNull Class<T> cls) throws ApiException {
        T t = (T) FsGsonUtil.fromJson(decryptResponseData(context, ApiHelper.toPost(String.format("%s%s", ApiHelper.getApiBaseUrl(), str), buildPostContent(context, verifyRequest))), cls);
        if (t != null) {
            return t;
        }
        throw new ApiException(10002, "\u7cfb\u7edf\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
    }
}
