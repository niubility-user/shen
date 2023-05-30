package com.jd.libs.hybrid.offlineload.utils;

import android.text.TextUtils;
import androidx.annotation.Keep;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.base.util.ExceptionUtils;
import java.util.HashMap;
import java.util.Map;
import jpbury.t;

@Keep
/* loaded from: classes16.dex */
public class OfflineExceptionUtils {
    public static final String ERR_MSG_CHECK = "\u6821\u9a8c\u5931\u8d25";
    public static final String ERR_MSG_CODE = "\u4ee3\u7801\u5f02\u5e38";
    public static final String ERR_MSG_MERGE = "\u5dee\u5206\u5931\u8d25";
    public static final String ERR_MSG_NET = "\u4e0b\u8f7d\u5931\u8d25";
    public static final String ERR_MSG_UNZIP = "\u89e3\u538b\u5931\u8d25";

    public static void reportConfigError(int i2, String str, Exception exc) {
        reportConfigError(i2, str, (String) null, ExceptionUtils.getStackStringFromException(exc));
    }

    public static void reportDatabaseError(String str, Throwable th) {
        reportDatabaseError(str, ExceptionUtils.getStackStringFromException(th));
    }

    public static void reportDownloadCodeError(String str, String str2, String str3, Exception exc) {
        reportDownloadError(ERR_MSG_CODE, str, str2, str3, exc);
    }

    public static void reportDownloadError(String str, String str2, String str3, String str4, Exception exc) {
        HashMap hashMap;
        if (exc != null) {
            hashMap = new HashMap(1);
            hashMap.put(t.f20145j, ExceptionUtils.getStackStringFromException(exc));
        } else {
            hashMap = null;
        }
        reportDownloadError(str, str2, str3, str4, hashMap);
    }

    public static void reportError(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("function", str);
        hashMap.put("errMsg", str2);
        hashMap.put(t.f20145j, str4);
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put("reserved2", str3);
        }
        ExceptionUtils.reportError(hashMap);
    }

    public static void reportGentokenError(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("function", "hybrid_gentoken");
        hashMap.put("errMsg", str);
        hashMap.put(t.f20145j, str3);
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("url", str2);
        }
        ExceptionUtils.reportError(hashMap);
    }

    public static void reportMatchError(String str, String str2, String str3, String str4, Exception exc) {
        reportMatchError(str, str2, str3, str4, ExceptionUtils.getStackStringFromException(exc));
    }

    public static void reportConfigError(int i2, String str, String str2, String str3) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("code", String.valueOf(i2));
        hashMap.put(t.f20145j, str3);
        reportConfigError(str, str2, hashMap);
    }

    public static void reportDatabaseError(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("function", "hybrid_database");
        hashMap.put("errMsg", str);
        hashMap.put(t.f20145j, str2);
        ExceptionUtils.reportError(hashMap);
    }

    public static void reportMatchError(String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap();
        hashMap.put("function", "hybrid_match");
        hashMap.put("errMsg", str);
        hashMap.put(t.f20145j, str5);
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put("reserved1", str3);
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("reserved2", str2);
        }
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put("url", str4);
        }
        ExceptionUtils.reportError(hashMap);
    }

    public static void reportDownloadError(int i2, String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("code", String.valueOf(i2));
        hashMap.put(t.f20145j, str5);
        reportDownloadError(str, str2, str3, str4, hashMap);
    }

    public static void reportConfigError(String str, String str2, Map<String, Object> map) {
        reportConfigError(str, (String) null, str2, (map == null || map.isEmpty()) ? null : new JDJSONObject(map).toJSONString());
    }

    public static void reportDownloadError(String str, String str2, String str3, String str4, Map<String, Object> map) {
        reportDownloadError(str, str2, str3, str4, (map == null || map.isEmpty()) ? null : new JDJSONObject(map).toJSONString());
    }

    public static void reportConfigError(String str, String str2, String str3, Exception exc) {
        reportConfigError(str, str2, str3, ExceptionUtils.getStackStringFromException(exc));
    }

    public static void reportConfigError(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("function", "hybrid_config");
        hashMap.put("errMsg", str);
        hashMap.put(t.f20145j, str4);
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put("reserved1", str3);
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("reserved2", str2);
        }
        ExceptionUtils.reportError(hashMap);
    }

    public static void reportDownloadError(String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap();
        hashMap.put("function", "hybrid_download");
        hashMap.put("errMsg", str);
        hashMap.put(t.f20145j, str5);
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put("reserved1", str3);
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("reserved2", str2);
        }
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put("url", str4);
        }
        ExceptionUtils.reportError(hashMap);
    }
}
