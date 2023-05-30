package com.jingdong.sdk.jdhttpdns.core;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.jdhttpdns.config.Constant;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import com.jingdong.sdk.jdhttpdns.utils.DNSLog;
import com.jingdong.sdk.jdhttpdns.utils.NetUtils;
import com.jingdong.sdk.jdhttpdns.utils.PackageInfoUtils;
import com.jingdong.sdk.jdhttpdns.utils.SharedPreferencesUtil;
import com.tencent.connect.common.Constants;
import java.util.HashMap;
import java.util.UUID;

/* loaded from: classes7.dex */
public class ParamHelper {
    private static final String UNKNOWN = "unknown";
    private static String devicesUUID;

    public static String concat(String... strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : (String[]) strArr.clone()) {
            stringBuffer.append(str);
            stringBuffer.append(DYConstants.DY_REGEX_COMMA);
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }

    private static String getAndroidId(Context context) {
        return BaseInfo.getAndroidId();
    }

    public static String getDnsQueryStr(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        HashMap hashMap = new HashMap();
        hashMap.put("appid", JDHttpDnsToolkit.getInstance().getAccountId());
        hashMap.put(Constants.PARAM_PLATFORM, "android");
        hashMap.put("network", NetUtils.getNetworkType());
        hashMap.put("v", String.format("%s_%s", spilitSubString(PackageInfoUtils.getVersionName(), 12), String.valueOf(PackageInfoUtils.getVersionCode())));
        hashMap.put("token", getUUID());
        hashMap.put("t", String.valueOf(System.currentTimeMillis()));
        if (!TextUtils.equals(str, HttpDnsConfig.PREDOWNLOAD_PARAMS)) {
            hashMap.put("dn", str);
        }
        hashMap.put("sign", SignatureHelper.signature(hashMap, JDHttpDnsToolkit.getInstance().getSecretKey()));
        for (String str2 : hashMap.keySet()) {
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty((CharSequence) hashMap.get(str2))) {
                stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append(str2);
                stringBuffer.append(ContainerUtils.KEY_VALUE_DELIMITER);
                stringBuffer.append((String) hashMap.get(str2));
            }
        }
        String stringBuffer2 = stringBuffer.toString();
        return stringBuffer2.startsWith(ContainerUtils.FIELD_DELIMITER) ? stringBuffer2.replaceFirst(ContainerUtils.FIELD_DELIMITER, "") : stringBuffer2;
    }

    private static String getInstallationId() {
        try {
            return UUID.randomUUID().toString().replaceAll("-", "");
        } catch (Exception unused) {
            return "";
        }
    }

    private static String getUUID() {
        boolean isSafeMode = JDHttpDnsToolkit.getInstance().isSafeMode();
        String uUIDFromCache = getUUIDFromCache(isSafeMode);
        devicesUUID = uUIDFromCache;
        if (!TextUtils.isEmpty(uUIDFromCache) && !TextUtils.equals(devicesUUID, "unknown")) {
            return devicesUUID;
        }
        String installationId = isSafeMode ? getInstallationId() : getAndroidId(HttpDnsImpl.applicationContext);
        devicesUUID = installationId;
        if (isSafeMode) {
            SharedPreferencesUtil.putString(Constant.KEY_SAFE_UUID, installationId);
        } else {
            SharedPreferencesUtil.putString(Constant.KEY_UUID, installationId);
        }
        return devicesUUID;
    }

    private static String getUUIDFromCache(boolean z) {
        if (!TextUtils.isEmpty(devicesUUID)) {
            return devicesUUID;
        }
        if (z) {
            devicesUUID = SharedPreferencesUtil.getString(Constant.KEY_SAFE_UUID, "");
        } else {
            devicesUUID = SharedPreferencesUtil.getString(Constant.KEY_UUID, "");
        }
        return devicesUUID;
    }

    private static String spilitSubString(String str, int i2) {
        if (str != null) {
            try {
                return str.length() > i2 ? str.substring(0, i2) : str;
            } catch (Exception e2) {
                DNSLog.e(e2.getMessage());
                return str;
            }
        }
        return str;
    }
}
