package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.AresCommonUtils;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.utils.StringUtil;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeCookieListener implements NativeCookieListener, JDFlutterCall {
    private static final String ALLOWED_DOMAIN = "daojia.jd.com";
    private static final String ALLOWED_TEST_DOMAIN = "testpdjm.jd.com";
    public static final String COOKIECHANNEL = "com.jd.jdflutter/cookie";
    private static final String TAG = "JDReactNativeCookieListener";

    private boolean clearSingleCookie(Context context, String str, String str2, String str3, String str4, boolean z) {
        return setCookie(context, str, str2, "", str3, str4, getGMTString(System.currentTimeMillis() + 500), z);
    }

    private String combineCookies(String str, String str2) {
        StringBuilder sb = new StringBuilder("");
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        if (TextUtils.isEmpty(str2)) {
            return sb.toString();
        }
        for (String str3 : str2.split(";")) {
            if (str3 != null) {
                String trim = str3.trim();
                if (!sb.toString().contains(trim)) {
                    sb.append("; ");
                    sb.append(trim);
                }
            }
        }
        return sb.toString();
    }

    private String getCookie(String str, String str2, boolean z) {
        if (!StringUtil.isEmpty(str) && !StringUtil.isEmpty(str2)) {
            String cookies = getCookies(str, z);
            if (StringUtil.isEmpty(cookies)) {
                return "";
            }
            for (String str3 : cookies.split(";")) {
                if (str3 != null) {
                    String trim = str3.trim();
                    if (trim.startsWith(str2) && trim.length() > str2.length() + 1) {
                        return trim.substring(str2.length() + 1);
                    }
                }
            }
        }
        return "";
    }

    private String getCookies(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (z) {
            return CookieManager.getInstance().getCookie(str);
        }
        return android.webkit.CookieManager.getInstance().getCookie(str);
    }

    private String getGMTString(long j2) {
        return new Date(j2).toGMTString();
    }

    private boolean isDomainAllowed(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.equals(ALLOWED_DOMAIN) || str.equals(ALLOWED_TEST_DOMAIN)) {
            return true;
        }
        try {
            String host = Uri.parse(str).getHost();
            if (!ALLOWED_DOMAIN.equals(host)) {
                if (!ALLOWED_TEST_DOMAIN.equals(host)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            return false;
        }
    }

    private void performClearCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2, boolean z) {
        if (hashMap == null) {
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
            return;
        }
        String str = hashMap.containsKey("Url") ? (String) hashMap.get("Url") : null;
        if (!isDomainAllowed(str)) {
            AresCommonUtils.invokeCallback(jDCallback2, "url is not allowed");
            return;
        }
        String str2 = hashMap.containsKey("Name") ? (String) hashMap.get("Name") : null;
        if (TextUtils.isEmpty(str2)) {
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
            return;
        }
        String str3 = hashMap.containsKey("Path") ? (String) hashMap.get("Path") : null;
        String str4 = hashMap.containsKey("Domain") ? (String) hashMap.get("Domain") : null;
        if (!TextUtils.isEmpty(str4) && !isDomainAllowed(str4)) {
            AresCommonUtils.invokeCallback(jDCallback2, "domain is not allowed");
        } else if (clearSingleCookie(AbstractJDReactInitialHelper.getCurrentMyActivity(), str, str2, str4, str3, z)) {
            AresCommonUtils.invokeCallback(jDCallback, new Object[0]);
        } else {
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
        }
    }

    private void performGetCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2, boolean z) {
        if (hashMap == null) {
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
            return;
        }
        String str = hashMap.containsKey("Url") ? (String) hashMap.get("Url") : null;
        if (TextUtils.isEmpty(str)) {
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
            return;
        }
        String str2 = hashMap.containsKey("Name") ? (String) hashMap.get("Name") : null;
        if (TextUtils.isEmpty(str2)) {
            AresCommonUtils.invokeCallback(jDCallback, getCookies(str, z));
        } else {
            AresCommonUtils.invokeCallback(jDCallback, getCookie(str, str2, z));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void performSetCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2, boolean z) {
        long j2;
        String str;
        if (hashMap == null) {
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
            return;
        }
        String str2 = hashMap.containsKey("Url") ? (String) hashMap.get("Url") : null;
        if (!isDomainAllowed(str2)) {
            AresCommonUtils.invokeCallback(jDCallback2, "url is not allowed");
            return;
        }
        String str3 = hashMap.containsKey("Name") ? (String) hashMap.get("Name") : null;
        if (!TextUtils.isEmpty(str3) && hashMap.containsKey("Value")) {
            String str4 = (String) hashMap.get("Value");
            String str5 = hashMap.containsKey("Path") ? (String) hashMap.get("Path") : null;
            String str6 = hashMap.containsKey("Domain") ? (String) hashMap.get("Domain") : null;
            if (!TextUtils.isEmpty(str6) && !isDomainAllowed(str6)) {
                AresCommonUtils.invokeCallback(jDCallback2, "domain is not allowed");
                return;
            }
            try {
                str = hashMap.containsKey(HttpHeaders.EXPIRES) ? (String) hashMap.get(HttpHeaders.EXPIRES) : null;
            } catch (Exception unused) {
            }
            if (str != null) {
                j2 = Long.parseLong(str);
                if (!setCookie(AbstractJDReactInitialHelper.getCurrentMyActivity(), str2, str3, str4, str6, str5, j2 <= 0 ? getGMTString(j2) : null, z)) {
                    AresCommonUtils.invokeCallback(jDCallback, new Object[0]);
                    return;
                } else {
                    AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
                    return;
                }
            }
            j2 = 0;
            if (!setCookie(AbstractJDReactInitialHelper.getCurrentMyActivity(), str2, str3, str4, str6, str5, j2 <= 0 ? getGMTString(j2) : null, z)) {
            }
        } else {
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
        }
    }

    private boolean setCookie(Context context, String str, String str2, boolean z) {
        JLog.d(TAG, "setCookie, url:" + str + ", cookie:" + str2);
        try {
            if (z) {
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.setAcceptCookie(true);
                cookieManager.setCookie(str, str2);
                if (Build.VERSION.SDK_INT < 21) {
                    CookieSyncManager.createInstance(context);
                    CookieSyncManager.getInstance().sync();
                } else {
                    cookieManager.flush();
                }
            } else {
                android.webkit.CookieManager cookieManager2 = android.webkit.CookieManager.getInstance();
                cookieManager2.setAcceptCookie(true);
                cookieManager2.setCookie(str, str2);
                if (Build.VERSION.SDK_INT < 21) {
                    android.webkit.CookieSyncManager.createInstance(context);
                    android.webkit.CookieSyncManager.getInstance().sync();
                } else {
                    cookieManager2.flush();
                }
            }
            return true;
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            return false;
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCookieListener
    public void clearCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        performClearCookie(hashMap, jDCallback, jDCallback2, true);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCookieListener
    public void clearWebkitCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        performClearCookie(hashMap, jDCallback, jDCallback2, false);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCookieListener
    public void getUnionCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (hashMap == null) {
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
            return;
        }
        String str = hashMap.containsKey("Url") ? (String) hashMap.get("Url") : null;
        if (TextUtils.isEmpty(str)) {
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
            return;
        }
        String str2 = hashMap.containsKey("Name") ? (String) hashMap.get("Name") : null;
        if (TextUtils.isEmpty(str2)) {
            AresCommonUtils.invokeCallback(jDCallback, combineCookies(getCookies(str, true), getCookies(str, false)));
            return;
        }
        String cookie = getCookie(str, str2, true);
        if (TextUtils.isEmpty(cookie)) {
            AresCommonUtils.invokeCallback(jDCallback, getCookie(str, str2, false));
        } else {
            AresCommonUtils.invokeCallback(jDCallback, cookie);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCookieListener
    public void getWebkitCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        performGetCookie(hashMap, jDCallback, jDCallback2, false);
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("getCookie")) {
            getCookie(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.1
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.2
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("setCookie")) {
            setCookie(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.3
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.4
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("clearCookie")) {
            clearCookie(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.5
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.6
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getWebkitCookie")) {
            getWebkitCookie(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.7
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.8
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getUnionCookie")) {
            getUnionCookie(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.9
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.10
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("setWebkitCookie")) {
            setWebkitCookie(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.11
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.12
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("clearWebkitCookie")) {
            clearWebkitCookie(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.13
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.14
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCookieListener
    public void setWebkitCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        performSetCookie(hashMap, jDCallback, jDCallback2, false);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCookieListener
    public void getCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        performGetCookie(hashMap, jDCallback, jDCallback2, true);
    }

    private boolean setCookie(Context context, String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2)) {
            return false;
        }
        StringBuilder sb = new StringBuilder(str2);
        sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
        sb.append(str3);
        if (!TextUtils.isEmpty(str4)) {
            sb.append(";");
            sb.append("Domain=");
            sb.append(str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            sb.append(";");
            sb.append("Path=");
            sb.append(str5);
        }
        if (!TextUtils.isEmpty(str6)) {
            sb.append(";");
            sb.append("Expires=");
            sb.append(str6);
        }
        return setCookie(context, str, sb.toString(), z);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCookieListener
    public void setCookie(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        performSetCookie(hashMap, jDCallback, jDCallback2, true);
    }
}
