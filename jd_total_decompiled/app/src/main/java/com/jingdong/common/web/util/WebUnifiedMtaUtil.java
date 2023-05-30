package com.jingdong.common.web.util;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.utils.JDNetworkConstant;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebView;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import jpbury.t;

/* loaded from: classes12.dex */
public class WebUnifiedMtaUtil {
    public static final String MWEBVIEW_H5TONATIVE = "MWebview_H5toNative";
    public static final String MWEBVIEW_PULLREFRESH = "MWebview_PullRefresh";
    public static final String MWEBVIEW_XVIEWEXPO = "MWebview_XViewExpo";
    private static boolean hasInit;

    public static void albumReport(IWebUiBinder iWebUiBinder, String str) {
        WebUtils.reportCommonErr(iWebUiBinder, "Album_Used", str, "");
    }

    public static void functionReport(IWebUiBinder iWebUiBinder, String str) {
        WebUtils.reportCommonErr(iWebUiBinder, "Function_Unused", str, "");
    }

    public static void permissionReport(IWebUiBinder iWebUiBinder, String str) {
        WebUtils.reportCommonErr(iWebUiBinder, "Permission_Used", str, "");
    }

    public static void queryAppReport(IWebUiBinder iWebUiBinder, String str) {
        WebUtils.reportCommonErr(iWebUiBinder, "Query_Third_App", str, "");
    }

    public static void sendAlbumMta(IWebUiBinder iWebUiBinder, String str, String str2) {
        sendAlbumMta(iWebUiBinder, str, str2, null);
    }

    public static void sendAlbumMtaWithoutUrl(String str, String str2, String str3) {
        try {
            if (!TextUtils.isEmpty(str3) && !new File(str3).exists()) {
                str2 = str2 + " file \u4e0d\u5b58\u5728";
            }
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", "925");
            hashMap.put("function", str);
            hashMap.put("errMsg", str2);
            Object[] objArr = new Object[1];
            double currentTimeMillis = System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            objArr[0] = Double.valueOf(currentTimeMillis / 1000.0d);
            hashMap.put("occurTime", String.format("%.6f", objArr));
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable unused) {
        }
    }

    public static void sendCoreMta(JDWebView jDWebView, Context context) {
        if (jDWebView == null || context == null || hasInit) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(jDWebView.isSystemCoreNotX5() ? "0" : "1");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(WebView.getTbsSDKVersion(context));
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(QbSdk.getTbsVersion(context));
        sendExposureMta(context, "JDWebView", "", "", "MWebview_InitializedFirstEntry", sb.toString());
        hasInit = true;
    }

    public static void sendExposureMta(Context context, Object obj, String str, String str2, String str3, String str4) {
        sendExposureMtaEX(context, obj, str, str2, str3, str4, "", "", "");
    }

    public static void sendExposureMtaEX(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        JDMtaUtils.sendExposureData(context, obj, str, str2, str3, str4, str5, str6, str7);
    }

    public static void sendUnknownSchemeMta(String str, List<String> list, String str2, String str3) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", "814");
            hashMap.put("function", "schemeException");
            hashMap.put("errMsg", str2);
            hashMap.put("url", str);
            Object[] objArr = new Object[1];
            double currentTimeMillis = System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            objArr[0] = Double.valueOf(currentTimeMillis / 1000.0d);
            hashMap.put("occurTime", String.format("%.6f", objArr));
            hashMap.put(t.f20145j, str3);
            if (list != null) {
                hashMap.put("postData", WebUtils.stringifyUrlHistory(list));
            }
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable unused) {
        }
    }

    public static void sendUrlInBlackListMta(String str, List<String> list, String str2, String str3) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", JDNetworkConstant.START_UP_TIME_ERRCODE);
            hashMap.put(PerformanceManager.ERR_TYPE, "3");
            hashMap.put("function", "blacklist_host");
            hashMap.put("errMsg", str2);
            hashMap.put(t.f20145j, str3);
            Object[] objArr = new Object[1];
            double currentTimeMillis = System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            objArr[0] = Double.valueOf(currentTimeMillis / 1000.0d);
            hashMap.put("occurTime", String.format("%.6f", objArr));
            hashMap.put("url", TextUtils.isEmpty(str) ? "" : str);
            if (!TextUtils.isEmpty(str) && str.startsWith("http")) {
                try {
                    hashMap.put("reserved1", ExceptionReporter.getIpByHost(new URL(str).getHost()));
                } catch (Exception unused) {
                }
            }
            if (list != null) {
                hashMap.put("postData", WebUtils.stringifyUrlHistory(list));
            }
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable unused2) {
        }
    }

    public static void albumReport(IWebUiBinder iWebUiBinder, String str, String str2) {
        WebUtils.reportCommonErr(iWebUiBinder, "Album_Used", str, str2);
    }

    public static void functionReport(JDWebView jDWebView, String str) {
        if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.WEB_FUNCTION_REPORT, false)) {
            WebUtils.reportCommonErr(jDWebView, "Function_Unused", str, "");
        }
    }

    public static void permissionReport(IWebUiBinder iWebUiBinder, String str, String str2) {
        WebUtils.reportCommonErr(iWebUiBinder, "Permission_Used", str, str2);
    }

    public static void sendAlbumMta(IWebUiBinder iWebUiBinder, String str, String str2, Throwable th) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", "925");
            hashMap.put("function", str);
            hashMap.put("errMsg", str2);
            hashMap.put("url", (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null) ? "" : WebUtils.decodeUrl(iWebUiBinder.getJdWebView().getFinalUrl()));
            Object[] objArr = new Object[1];
            double currentTimeMillis = System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            objArr[0] = Double.valueOf(currentTimeMillis / 1000.0d);
            hashMap.put("occurTime", String.format("%.6f", objArr));
            if (th != null) {
                hashMap.put(t.f20145j, ExceptionReporter.getStackStringFromException(th));
            }
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable unused) {
        }
    }
}
