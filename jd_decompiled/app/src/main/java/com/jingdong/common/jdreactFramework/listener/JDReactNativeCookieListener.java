package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void performSetCookie(java.util.HashMap r15, com.jingdong.common.jdreactFramework.JDCallback r16, com.jingdong.common.jdreactFramework.JDCallback r17, boolean r18) {
        /*
            r14 = this;
            r9 = r14
            r0 = r15
            r10 = r17
            java.lang.String r1 = "Expires"
            r11 = 0
            if (r0 != 0) goto Lf
            java.lang.Object[] r0 = new java.lang.Object[r11]
            com.jingdong.common.jdreactFramework.utils.AresCommonUtils.invokeCallback(r10, r0)
            return
        Lf:
            java.lang.String r2 = "Url"
            boolean r3 = r15.containsKey(r2)
            r4 = 0
            if (r3 == 0) goto L1f
            java.lang.Object r2 = r15.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            goto L20
        L1f:
            r2 = r4
        L20:
            boolean r3 = r14.isDomainAllowed(r2)
            r5 = 1
            if (r3 != 0) goto L31
            java.lang.Object[] r0 = new java.lang.Object[r5]
            java.lang.String r1 = "url is not allowed"
            r0[r11] = r1
            com.jingdong.common.jdreactFramework.utils.AresCommonUtils.invokeCallback(r10, r0)
            return
        L31:
            java.lang.String r3 = "Name"
            boolean r6 = r15.containsKey(r3)
            if (r6 == 0) goto L40
            java.lang.Object r3 = r15.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            goto L41
        L40:
            r3 = r4
        L41:
            boolean r6 = android.text.TextUtils.isEmpty(r3)
            if (r6 != 0) goto Lcf
            java.lang.String r6 = "Value"
            boolean r7 = r15.containsKey(r6)
            if (r7 != 0) goto L51
            goto Lcf
        L51:
            java.lang.Object r6 = r15.get(r6)
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r7 = "Path"
            boolean r8 = r15.containsKey(r7)
            if (r8 == 0) goto L66
            java.lang.Object r7 = r15.get(r7)
            java.lang.String r7 = (java.lang.String) r7
            goto L67
        L66:
            r7 = r4
        L67:
            java.lang.String r8 = "Domain"
            boolean r12 = r15.containsKey(r8)
            if (r12 == 0) goto L76
            java.lang.Object r8 = r15.get(r8)
            java.lang.String r8 = (java.lang.String) r8
            goto L77
        L76:
            r8 = r4
        L77:
            boolean r12 = android.text.TextUtils.isEmpty(r8)
            if (r12 != 0) goto L8d
            boolean r12 = r14.isDomainAllowed(r8)
            if (r12 != 0) goto L8d
            java.lang.Object[] r0 = new java.lang.Object[r5]
            java.lang.String r1 = "domain is not allowed"
            r0[r11] = r1
            com.jingdong.common.jdreactFramework.utils.AresCommonUtils.invokeCallback(r10, r0)
            return
        L8d:
            r12 = 0
            boolean r5 = r15.containsKey(r1)     // Catch: java.lang.Exception -> La4
            if (r5 == 0) goto L9c
            java.lang.Object r0 = r15.get(r1)     // Catch: java.lang.Exception -> La4
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> La4
            goto L9d
        L9c:
            r0 = r4
        L9d:
            if (r0 == 0) goto La4
            long r0 = java.lang.Long.parseLong(r0)     // Catch: java.lang.Exception -> La4
            goto La5
        La4:
            r0 = r12
        La5:
            int r5 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
            if (r5 <= 0) goto Laf
            java.lang.String r0 = r14.getGMTString(r0)
            r12 = r0
            goto Lb0
        Laf:
            r12 = r4
        Lb0:
            android.app.Activity r1 = com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper.getCurrentMyActivity()
            r0 = r14
            r4 = r6
            r5 = r8
            r6 = r7
            r7 = r12
            r8 = r18
            boolean r0 = r0.setCookie(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r0 == 0) goto Lc9
            java.lang.Object[] r0 = new java.lang.Object[r11]
            r1 = r16
            com.jingdong.common.jdreactFramework.utils.AresCommonUtils.invokeCallback(r1, r0)
            goto Lce
        Lc9:
            java.lang.Object[] r0 = new java.lang.Object[r11]
            com.jingdong.common.jdreactFramework.utils.AresCommonUtils.invokeCallback(r10, r0)
        Lce:
            return
        Lcf:
            java.lang.Object[] r0 = new java.lang.Object[r11]
            com.jingdong.common.jdreactFramework.utils.AresCommonUtils.invokeCallback(r10, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.listener.JDReactNativeCookieListener.performSetCookie(java.util.HashMap, com.jingdong.common.jdreactFramework.JDCallback, com.jingdong.common.jdreactFramework.JDCallback, boolean):void");
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
