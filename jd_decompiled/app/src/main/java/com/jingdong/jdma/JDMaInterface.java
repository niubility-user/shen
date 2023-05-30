package com.jingdong.jdma;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import com.jingdong.jdma.common.utils.LogUtil;
import com.jingdong.jdma.common.utils.c;
import com.jingdong.jdma.common.utils.d;
import com.jingdong.jdma.iml.b;
import com.jingdong.jdma.minterface.AppMode;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.jdma.minterface.ClickInterfaceParam;
import com.jingdong.jdma.minterface.CustomInterfaceParam;
import com.jingdong.jdma.minterface.ExposureInterfaceParam;
import com.jingdong.jdma.minterface.MaInitCommonInfo;
import com.jingdong.jdma.minterface.OrderInterfaceParam;
import com.jingdong.jdma.minterface.PropertyInterfaceParam;
import com.jingdong.jdma.minterface.PvInterfaceParam;
import java.util.Map;

/* loaded from: classes12.dex */
public class JDMaInterface {
    private static final String TAG = "JDMA_JDMaInterface";

    public static void acceptPrivacyProtocol(boolean z) {
        LogUtil.i(TAG, "acceptPrivacyProtocol()");
        c.p = z;
    }

    public static synchronized void clearMtaSource() {
        synchronized (JDMaInterface.class) {
            LogUtil.i(TAG, "clearMtaSource()");
            b.f();
        }
    }

    public static void clipboard(boolean z) {
        LogUtil.i(TAG, "clipboard()");
        c.u = z;
    }

    public static void destroy() {
        LogUtil.i(TAG, "destroy()");
        b.g();
    }

    public static int getH5Size() {
        return b.h();
    }

    public static int getH5Time() {
        return b.i();
    }

    public static String getJda() {
        LogUtil.i(TAG, "getJda()");
        return b.k();
    }

    public static String getJdv() {
        LogUtil.i(TAG, "getJdv()");
        return b.l();
    }

    public static long getOpen_count() {
        LogUtil.i(TAG, "getPvSid()");
        return b.n();
    }

    public static long getSeq() {
        LogUtil.i(TAG, "getPvSeq()");
        return b.m();
    }

    public static String getSessionInfo(Context context) {
        LogUtil.i(TAG, "getSessionInfo()");
        return b.a(context);
    }

    public static String getSourceType() {
        LogUtil.i(TAG, "getSourceType()");
        return b.o();
    }

    public static String getSourceValue() {
        LogUtil.i(TAG, "getSourceValue()");
        return b.p();
    }

    public static String getUnpl() {
        LogUtil.i(TAG, "getUnpl()");
        return b.q();
    }

    public static void init(Context context, MaInitCommonInfo maInitCommonInfo) {
        LogUtil.i(TAG, "init()");
        b.b(context, maInitCommonInfo);
    }

    @Deprecated
    public static void onPause() {
        LogUtil.i(TAG, "onPause()");
    }

    @Deprecated
    public static void onResume(Context context) {
        LogUtil.i(TAG, "onResume()");
    }

    public static void openWebView(WebView webView) {
        LogUtil.i(TAG, "openWebView()");
        b.a(webView);
    }

    public static void openX5WebView(Object obj) {
        LogUtil.i(TAG, "openX5WebView()");
        b.a(obj);
    }

    public static void parseTextOnMobileCheckMode(String str) {
        LogUtil.i(TAG, "parseTextOnMobileCheckMode()");
        d.c().a(str);
    }

    public static long queryCount(Context context, MaInitCommonInfo maInitCommonInfo, String str) {
        LogUtil.i(TAG, "queryCount()");
        return b.a(context, maInitCommonInfo, str);
    }

    public static void recordLogFromH5(String str) {
        LogUtil.i(TAG, "recordLogFromH5()");
        b.b(str);
    }

    public static void removeUserProperty(@NonNull String str) {
        b.c(str);
    }

    public static boolean sendClickData(Context context, MaInitCommonInfo maInitCommonInfo, ClickInterfaceParam clickInterfaceParam) {
        LogUtil.i(TAG, "sendClickData()");
        return b.a(context, clickInterfaceParam);
    }

    public static boolean sendCustomData(Context context, MaInitCommonInfo maInitCommonInfo, CustomInterfaceParam customInterfaceParam) {
        LogUtil.i(TAG, "sendCustomData()");
        return b.a(context, customInterfaceParam);
    }

    public static void sendData(Context context, MaInitCommonInfo maInitCommonInfo, BaseEvent baseEvent) {
        LogUtil.i(TAG, "sendData()");
        b.a(context, baseEvent);
    }

    public static boolean sendExposureData(Context context, MaInitCommonInfo maInitCommonInfo, ExposureInterfaceParam exposureInterfaceParam) {
        LogUtil.i(TAG, "sendExposureData()");
        return b.a(context, exposureInterfaceParam);
    }

    public static boolean sendOrderData(Context context, MaInitCommonInfo maInitCommonInfo, OrderInterfaceParam orderInterfaceParam) {
        LogUtil.i(TAG, "sendOrderData()");
        return b.a(context, orderInterfaceParam);
    }

    public static boolean sendPropertyData(Context context, MaInitCommonInfo maInitCommonInfo, PropertyInterfaceParam propertyInterfaceParam) {
        LogUtil.i(TAG, "sendPropertyData()");
        return b.a(context, propertyInterfaceParam);
    }

    public static boolean sendPvData(Context context, MaInitCommonInfo maInitCommonInfo, PvInterfaceParam pvInterfaceParam) {
        LogUtil.i(TAG, "sendPvData()");
        return b.a(context, pvInterfaceParam);
    }

    public static void setAppMode(AppMode appMode) {
        LogUtil.i(TAG, "setAppMode()");
        b.a(appMode);
    }

    public static void setAreaCode(String str) {
        LogUtil.i(TAG, "setAreaCode()");
        c.D = str;
    }

    public static void setExternalMPageParam(String str) {
        LogUtil.i(TAG, "setExternalMPageParam()");
        b.d(str);
    }

    public static void setImeiTag(String str) {
        LogUtil.i(TAG, "setImeiTag()");
        c.f12684n = str;
    }

    public static void setJdv(String str) {
        LogUtil.i(TAG, "setJdv()");
        b.f(str);
    }

    public static void setMaAbTest(String str) {
        LogUtil.i(TAG, "setMaAbTest()");
        c.t = str;
    }

    public static void setMaBGroup(String str) {
        LogUtil.i(TAG, "setMaBGroup()");
        c.s = str;
    }

    public static void setMaIsSparse(String str) {
        LogUtil.i(TAG, "setMaIsSparse()");
        c.r = str;
    }

    public static void setModeTag(String str) {
        LogUtil.i(TAG, "setModeTag()");
        c.q = str;
    }

    public static synchronized void setMtaContent4Inside(String str) {
        synchronized (JDMaInterface.class) {
            if (LogUtil.isDebug()) {
                LogUtil.w(TAG, "setMtaContent4Inside(), jsonStr: " + str);
            }
            b.g(str);
        }
    }

    public static synchronized void setMtaContent4OpenApp(Context context, String str) {
        synchronized (JDMaInterface.class) {
            if (LogUtil.isDebug()) {
                String str2 = "setMtaContent4OpenApp(), jsonStr: " + str;
            }
            b.a(context, str);
        }
    }

    public static void setOAID(String str) {
        LogUtil.i(TAG, "setOAID()");
        c.f12683m = str;
    }

    public static void setReportDomain(String str) {
        LogUtil.i(TAG, "setReportDomain()");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.jingdong.jdma.h.d.e().i(str);
    }

    public static void setSessionInfo(Context context, String str) {
        if (LogUtil.isDebug()) {
            LogUtil.i(TAG, "setSessionInfo(), content: " + str);
        }
        b.b(context, str);
    }

    public static void setShowLog(boolean z) {
        LogUtil.i(TAG, "setShowLog()");
        b.a(z);
    }

    public static void setSourceData(String str, String str2, Context context) {
        LogUtil.i(TAG, "setSourceData()");
        b.a(str, str2, context);
    }

    public static void setUserProperty(@NonNull String str, String str2) {
        b.a(str, str2);
    }

    public static void updateUnpl(String str, String str2, String str3) {
        LogUtil.i(TAG, "updateUnpl()");
        b.a(str, str2, str3);
    }

    public static void removeUserProperty(String[] strArr) {
        b.a(strArr);
    }

    public static void setUserProperty(Map<String, String> map) {
        b.a(map);
    }
}
