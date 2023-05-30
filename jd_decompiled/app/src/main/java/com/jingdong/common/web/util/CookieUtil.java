package com.jingdong.common.web.util;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jd.libs.xconsole.XLog;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.CookieManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/* loaded from: classes12.dex */
public class CookieUtil {
    private static final String TAG = "CookieUtil";

    public static void clearCertainX5Cookies(String str, String[] strArr) {
        List asList;
        if (TextUtils.isEmpty(str)) {
            if (OKLog.D || WebLogHelper.showXLog) {
                XLog.d(TAG, "[clearCertainX5Cookies] \u5165\u53c2\u975e\u6cd5\uff0curl\u4e3a\u7a7a\uff0c\u4e0d\u6e05\u9664cookies");
                return;
            }
            return;
        }
        CookieManager cookieManager = CookieManager.getInstance();
        if (!cookieManager.acceptCookie()) {
            if (OKLog.D || WebLogHelper.showXLog) {
                XLog.d(TAG, "[clearCertainX5Cookies] \u5f53\u524d\u4e0d\u652f\u6301\u8bbe\u7f6ecookie\uff0c\u65e0\u9700\u6e05\u9664cookies");
            }
        } else if (!cookieManager.hasCookies()) {
            if (OKLog.D || WebLogHelper.showXLog) {
                XLog.d(TAG, "[clearCertainX5Cookies] \u5f53\u524d\u8fd8\u672a\u8bbe\u7f6ecookie\uff0c\u65e0\u9700\u6e05\u9664cookies");
            }
        } else {
            if (strArr != null) {
                try {
                    if (strArr.length > 0) {
                        asList = Arrays.asList(strArr);
                        doClearX5Cookies(str, asList);
                    }
                } catch (Throwable th) {
                    if (OKLog.E) {
                        OKLog.e(TAG, th);
                        XLog.e(TAG, "[clearCertainX5Cookies] \u5931\u8d25\uff0c\u5185\u90e8\u9519\u8bef");
                        return;
                    }
                    return;
                }
            }
            asList = null;
            doClearX5Cookies(str, asList);
        }
    }

    public static void clearX5CookiesExceptThan(String str, String[] strArr) {
        if (TextUtils.isEmpty(str)) {
            if (OKLog.D || WebLogHelper.showXLog) {
                XLog.d(TAG, "[clearX5CookiesExceptThan] \u5165\u53c2\u975e\u6cd5\uff0curl\u4e3a\u7a7a\uff0c\u4e0d\u6e05\u9664cookies");
                return;
            }
            return;
        }
        CookieManager cookieManager = CookieManager.getInstance();
        if (!cookieManager.acceptCookie()) {
            if (OKLog.D || WebLogHelper.showXLog) {
                XLog.d(TAG, "[clearX5CookiesExceptThan] \u5f53\u524d\u4e0d\u652f\u6301\u8bbe\u7f6ecookie\uff0c\u65e0\u9700\u6e05\u9664cookies");
            }
        } else if (!cookieManager.hasCookies()) {
            if (OKLog.D || WebLogHelper.showXLog) {
                XLog.d(TAG, "[clearX5CookiesExceptThan] \u5f53\u524d\u8fd8\u672a\u8bbe\u7f6ecookie\uff0c\u65e0\u9700\u6e05\u9664cookies");
            }
        } else {
            try {
                String cookie = cookieManager.getCookie(str);
                if (TextUtils.isEmpty(cookie)) {
                    return;
                }
                List asList = (strArr == null || strArr.length <= 0) ? null : Arrays.asList(strArr);
                LinkedList linkedList = new LinkedList();
                for (String str2 : cookie.split(";")) {
                    if (str2 != null) {
                        String[] split = str2.trim().split(ContainerUtils.KEY_VALUE_DELIMITER);
                        String trim = split[0] != null ? split[0].trim() : null;
                        if (!TextUtils.isEmpty(trim) && (asList == null || !asList.contains(trim))) {
                            linkedList.add(trim);
                        }
                    }
                }
                doClearX5Cookies(str, linkedList);
            } catch (Throwable th) {
                if (OKLog.E) {
                    OKLog.e(TAG, th);
                    XLog.e(TAG, "[clearX5CookiesExceptThan] \u5931\u8d25\uff0c\u5185\u90e8\u9519\u8bef");
                }
            }
        }
    }

    public static synchronized void compareCookieChanged(String str, String str2) {
        synchronized (CookieUtil.class) {
            if (passCondition("wvCompareCookie")) {
                String cookie = CookieManager.getInstance().getCookie("jd.com");
                if (!TextUtils.isEmpty(cookie) && !TextUtils.isEmpty(str)) {
                    ArrayList<String> extraKeys = getExtraKeys(getKeysList(str), getKeysList(cookie));
                    if (extraKeys.size() > 0) {
                        StringBuilder sb = new StringBuilder();
                        String[] split = cookie.split(";");
                        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("wvJDCookieKeyList", "");
                        Iterator<String> it = extraKeys.iterator();
                        while (it.hasNext()) {
                            String next = it.next();
                            if (!switchStringValue.contains(next)) {
                                for (String str3 : split) {
                                    if (str3.startsWith(next)) {
                                        sb.append(str3);
                                        sb.append(";");
                                    }
                                }
                            }
                        }
                        if (sb.length() > 0) {
                            ExceptionReporter.reportWebPageError("WebViewAddedCookie", String.valueOf(sb), str2, WebWhiteScreenHolder.ERR_CODE, String.valueOf(sb.length()));
                        }
                    }
                }
            }
        }
    }

    private static void doClearX5Cookies(String str, List<String> list) {
        if (list == null) {
            if (OKLog.D || WebLogHelper.showXLog) {
                XLog.d(TAG, "[doClearX5Cookies] \u9700\u6e05\u9664\u7684keys\u4e3a\u7a7a\uff0c\u4e0d\u6e05\u9664cookies");
                return;
            }
            return;
        }
        CookieManager cookieManager = CookieManager.getInstance();
        Uri parse = Uri.parse(str);
        String host = parse.getHost();
        if (!TextUtils.isEmpty(host) && !TextUtils.isEmpty(parse.getScheme())) {
            String cookie = cookieManager.getCookie(str);
            if (TextUtils.isEmpty(cookie)) {
                return;
            }
            String[] split = cookie.split(";");
            if (split.length <= 0) {
                return;
            }
            if (OKLog.D || WebLogHelper.showXLog) {
                XLog.d(TAG, "[doClearX5Cookies] \u9700\u6e05\u7406cookies: url = " + str + ", \u6e05\u9664\u7684keys = " + list);
                StringBuilder sb = new StringBuilder();
                sb.append("[doClearX5Cookies] \u6e05\u7406\u524dcookie\u503c = ");
                sb.append(cookie);
                XLog.d(TAG, sb.toString());
            }
            int length = split.length;
            int i2 = 0;
            boolean z = false;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                String str2 = split[i2];
                if (str2 != null) {
                    String[] split2 = str2.trim().split(ContainerUtils.KEY_VALUE_DELIMITER);
                    String trim = split2[0] != null ? split2[0].trim() : null;
                    if (!TextUtils.isEmpty(trim) && list.contains(trim)) {
                        String str3 = trim + "=; EXPIRES=Thu, 01-Jan-1970 00:00:00 GMT; PATH=/; DOMAIN=" + host;
                        if (OKLog.D) {
                            OKLog.d(TAG, "[doClearX5Cookies] \u6e05\u9664cookie: " + str3);
                        }
                        cookieManager.setCookie(str, str3);
                        z = true;
                    }
                }
                i2++;
            }
            if (z) {
                cookieManager.removeSessionCookies(null);
                cookieManager.flush();
            }
            if (OKLog.D || WebLogHelper.showXLog) {
                XLog.d(TAG, "[doClearX5Cookies] \u6e05\u7406cookies\u5b8c\u6210");
                XLog.d(TAG, "[doClearX5Cookies] \u6e05\u7406\u540ecookie\u503c = " + cookieManager.getCookie(str));
            }
        } else if (OKLog.D || WebLogHelper.showXLog) {
            XLog.d(TAG, "[doClearX5Cookies] \u5165\u53c2\u975e\u6cd5\uff0curl\u7684host\u6216scheme\u4e3a\u7a7a\uff0c\u4e0d\u6e05\u9664cookies");
        }
    }

    private static ArrayList<String> getExtraKeys(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        String arrayList3 = arrayList.toString();
        ArrayList<String> arrayList4 = new ArrayList<>();
        Iterator<String> it = arrayList2.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!TextUtils.isEmpty(arrayList3)) {
                if (!arrayList3.contains(next)) {
                    arrayList4.add(next);
                }
            } else {
                arrayList4.add(next);
            }
        }
        return arrayList4;
    }

    private static ArrayList<String> getKeysList(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(";")) {
                int indexOf = str2.trim().indexOf(ContainerUtils.KEY_VALUE_DELIMITER);
                if (indexOf > 0) {
                    arrayList.add(str2.substring(0, indexOf + 1));
                }
            }
        }
        return arrayList;
    }

    public static void monitorCookie(JDWebView jDWebView) {
        if (!passCondition("wvMonitorCookie") || jDWebView == null || jDWebView.getWebView() == null) {
            return;
        }
        jDWebView.getWebView().loadUrl("javascript:;(function() {\n    var cookieOriginSetter = document.__lookupSetter__(\"cookie\"),\n        cookieOriginGetter = document.__lookupGetter__(\"cookie\");\n    if (cookieOriginSetter && cookieOriginGetter) {\n        Object.defineProperty(document, \"cookie\", {\n            set: function(cookstr) {\n                if (typeof cookstr !== \"string\") {\n                    return\n                }\n                window.JDAppUnite && window.JDAppUnite.notifyCookie(cookstr);\n                cookieOriginSetter.apply(document, [cookstr])\n            },\n            get: function() {\n                return cookieOriginGetter.apply(document, [])\n            },\n            configurable: false\n        })\n    } \n})();\n");
    }

    public static boolean passCondition(String str) {
        if (SwitchQueryFetcher.getSwitchBooleanValue(str, false)) {
            String[] split = SwitchQueryFetcher.getSwitchStringValue("wvCookieRandom", "").split(DYConstants.DY_REGEX_COMMA);
            if (split.length > 1) {
                return new Random().nextInt(Integer.parseInt(split[0])) < Integer.parseInt(split[1]);
            }
            return false;
        }
        return false;
    }

    public static void setCookie(JDWebView jDWebView, String str, String str2) {
        if (OKLog.D) {
            OKLog.d(TAG, "setCookie  key=" + str + "  value=" + str2);
        }
        String str3 = "(function setCookie(name,value,time,domain,path) {\nvar exp = new Date();\nexp.setTime(exp.getTime() + time);\ndocument.cookie = name + \"=\"+ encodeURIComponent(value) + \";expires=\" + exp.toGMTString() + \";domain=\" + domain + \";path=\" + path;\n})(\"" + str + "\",\"" + str2 + "\", 20*365*24*3600*1000 , \"jd.com\" , \"/\");";
        if (jDWebView == null || jDWebView.getWebView() == null) {
            return;
        }
        jDWebView.getWebView().loadUrl("javascript:" + str3);
    }
}
