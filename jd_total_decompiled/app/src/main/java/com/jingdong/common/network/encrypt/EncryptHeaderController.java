package com.jingdong.common.network.encrypt;

import android.text.TextUtils;
import com.jd.stat.security.jma.JMA;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleManager;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.ActivityManagerUtil;
import com.jingdong.common.login.DeviceFingerUtil;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import jd.wjlogin_sdk.common.WJLoginHelper;
import jd.wjlogin_sdk.util.f;

/* loaded from: classes5.dex */
public class EncryptHeaderController {
    public static final String TAG = "EncryptHeaderController";
    private static String sCachedJEHValue;
    private static Object sJEHValueLock = new Object();

    public static Map<String, String> getEncryptHeaderField(boolean z) {
        HashMap hashMap = new HashMap();
        if (z) {
            String safeCookie = getSafeCookie();
            if (!TextUtils.isEmpty(safeCookie)) {
                hashMap.put("Cookie", safeCookie);
            }
            String jECValue = getJECValue();
            if (!TextUtils.isEmpty(jECValue)) {
                hashMap.put("J-E-C", jECValue);
            }
        }
        if (TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDRequestIdentifier", "switch", "netUASwitch"), "1")) {
            String safeUserAgent = StatisticsReportUtil.UserAgentResolver.getSafeUserAgent();
            if (!TextUtils.isEmpty(safeUserAgent)) {
                hashMap.put("User-Agent", safeUserAgent);
            }
            String jEHValue = getJEHValue();
            if (!TextUtils.isEmpty(jEHValue)) {
                hashMap.put("J-E-H", jEHValue);
            }
        }
        try {
            hashMap.put("X-Referer-Package", f.f19954c);
            if (ActivityManagerUtil.getScreenManager().currentActivity() != null) {
                hashMap.put("X-Referer-Page", ActivityManagerUtil.getScreenManager().currentActivity().getClass().getName());
            }
            if (!TextUtils.isEmpty(JDRiskHandleManager.getInstance().getRiskHandleVersion())) {
                hashMap.put("X-Rp-Client", JDRiskHandleManager.getInstance().getRiskHandleVersion());
            }
        } catch (Throwable unused) {
        }
        return hashMap;
    }

    public static String getJECValue() {
        try {
            HashMap hashMap = new HashMap();
            String pin = UserUtil.getWJLoginHelper() != null ? UserUtil.getWJLoginHelper().getPin() : null;
            if (!TextUtils.isEmpty(pin)) {
                try {
                    hashMap.put("pin", URLEncoder.encode(pin, "UTF-8"));
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
            if (hashMap.isEmpty()) {
                return null;
            }
            return EncryptTool.encryptAndEncode(hashMap);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getJEHValue() {
        String str;
        synchronized (sJEHValueLock) {
            if (sCachedJEHValue == null) {
                try {
                    HashMap hashMap = new HashMap();
                    String userAgent = StatisticsReportUtil.UserAgentResolver.getUserAgent();
                    if (!TextUtils.isEmpty(userAgent)) {
                        hashMap.put("User-Agent", userAgent);
                    }
                    if (!hashMap.isEmpty()) {
                        sCachedJEHValue = EncryptTool.encryptAndEncode(hashMap);
                    }
                } catch (Throwable unused) {
                }
            }
            str = sCachedJEHValue;
        }
        return str;
    }

    public static String getSafeCookie() {
        String str = "";
        try {
            WJLoginHelper wJLoginHelper = UserUtil.getWJLoginHelper();
            String a2 = wJLoginHelper != null ? wJLoginHelper.getA2() : "";
            String unionFingerPrint = DeviceFingerUtil.getUnionFingerPrint();
            String softFingerprint = JMA.getSoftFingerprint(JdSdk.getInstance().getApplication());
            String riskHandleToken = JDRiskHandleManager.getInstance().getRiskHandleToken();
            if (!TextUtils.isEmpty(a2)) {
                str = "wskey=" + a2 + ";";
            }
            if (!TextUtils.isEmpty(softFingerprint)) {
                str = str + "whwswswws=" + softFingerprint + ";";
            }
            if (!TextUtils.isEmpty(unionFingerPrint)) {
                str = str + "unionwsws=" + unionFingerPrint + ";";
            }
            if (!TextUtils.isEmpty(riskHandleToken)) {
                str = str + "x-rp-evtoken=" + riskHandleToken + ";";
            }
            String pin = UserUtil.getWJLoginHelper() != null ? UserUtil.getWJLoginHelper().getPin() : null;
            if (!TextUtils.isEmpty(pin)) {
                str = str + "pin_hash=" + pin.hashCode() + ";";
            }
            if (OKLog.I) {
                OKLog.i(TAG, "getSafeCookie==== " + str);
            }
        } catch (Throwable unused) {
        }
        return str;
    }
}
