package com.jingdong.app.mall.bundle.jdrhsdk.d;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleInfoHelper;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleJumpHelper;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleLoginHelper;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleManager;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleMtaHelper;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleVerifyHelper;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.HashMap;
import jd.wjlogin_sdk.common.WJLoginAntiRpIdProxy;
import jd.wjlogin_sdk.common.WJLoginHelper;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.util.f;

/* loaded from: classes2.dex */
public class a {
    private static JDRiskHandleInfoHelper a = null;
    private static JDRiskHandleMtaHelper b = null;

    /* renamed from: c */
    private static JDRiskHandleJumpHelper f8170c = null;
    private static JDRiskHandleVerifyHelper d = null;

    /* renamed from: e */
    private static JDRiskHandleLoginHelper f8171e = null;

    /* renamed from: f */
    public static String f8172f = "CJujLf0DFX1wFyni4g8d_8hFOSujE09vxNeCkQwi4z_CiaHA_unUMkzkXam4TDaDEGd4dGhqh2mUJ_JrLFXol3HnkmUi3zTFvTqoZqg1ECCrdoLMQ9x7HrgTz9uXXeGo";

    public static String A() {
        return "android_2.0.0";
    }

    public static String B() {
        try {
            JDRiskHandleVerifyHelper jDRiskHandleVerifyHelper = d;
            return jDRiskHandleVerifyHelper != null ? b(jDRiskHandleVerifyHelper.getUemp()) : "0";
        } catch (Exception unused) {
            return "0";
        }
    }

    public static String C() {
        try {
            JDRiskHandleInfoHelper jDRiskHandleInfoHelper = a;
            return jDRiskHandleInfoHelper != null ? b(jDRiskHandleInfoHelper.getUnionwsws()) : "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String D() {
        try {
            JDRiskHandleInfoHelper jDRiskHandleInfoHelper = a;
            return jDRiskHandleInfoHelper != null ? b(jDRiskHandleInfoHelper.getUuid()) : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private static WJLoginHelper E() {
        try {
            JDRiskHandleInfoHelper jDRiskHandleInfoHelper = a;
            if (jDRiskHandleInfoHelper != null) {
                return jDRiskHandleInfoHelper.getWJLoginHelper();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean F() {
        try {
            if (E() != null) {
                return E().hasLogin();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean G() {
        try {
            JDRiskHandleInfoHelper jDRiskHandleInfoHelper = a;
            if (jDRiskHandleInfoHelper != null) {
                return jDRiskHandleInfoHelper.isAppForeground();
            }
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean H() {
        try {
            if (E() != null) {
                return E().isEnterLogined();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean I() {
        Context a2 = c.a();
        if (a2 != null) {
            return f.f19954c.equals(a2.getPackageName());
        }
        return false;
    }

    public static String a() {
        try {
            if (E() != null) {
                String antiCrawlerToken = E().getAntiCrawlerToken();
                d.a("RiskHandle", "getAntiCrawlerToken=" + antiCrawlerToken);
                return antiCrawlerToken;
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public static String b(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static void c(Context context) {
        try {
            JDRiskHandleLoginHelper jDRiskHandleLoginHelper = f8171e;
            if (jDRiskHandleLoginHelper != null) {
                jDRiskHandleLoginHelper.exitLogin(context);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void d(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        try {
            JDRiskHandleMtaHelper jDRiskHandleMtaHelper = b;
            if (jDRiskHandleMtaHelper != null) {
                jDRiskHandleMtaHelper.sendPVExtend(context, obj, str, str2, str3, str4, str5, str6, hashMap, hashMap2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void e(Context context, String str) {
        try {
            JDRiskHandleLoginHelper jDRiskHandleLoginHelper = f8171e;
            if (jDRiskHandleLoginHelper == null || context == null) {
                return;
            }
            jDRiskHandleLoginHelper.jumpToLogin(context, str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void f(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
        try {
            JDRiskHandleMtaHelper jDRiskHandleMtaHelper = b;
            if (jDRiskHandleMtaHelper != null) {
                jDRiskHandleMtaHelper.sendClickDataWithExt(context, str, str2, str3, str4, str5, str6, str7, str8, hashMap);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void g(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap) {
        try {
            JDRiskHandleMtaHelper jDRiskHandleMtaHelper = b;
            if (jDRiskHandleMtaHelper != null) {
                jDRiskHandleMtaHelper.sendExposureDataWithExt(context, str, str2, str3, str4, str5, str6, hashMap);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void h(JDRiskHandleInfoHelper jDRiskHandleInfoHelper) {
        a = jDRiskHandleInfoHelper;
    }

    public static void i(JDRiskHandleJumpHelper jDRiskHandleJumpHelper) {
        f8170c = jDRiskHandleJumpHelper;
    }

    public static void j(JDRiskHandleLoginHelper jDRiskHandleLoginHelper) {
        f8171e = jDRiskHandleLoginHelper;
    }

    public static void k(JDRiskHandleMtaHelper jDRiskHandleMtaHelper) {
        b = jDRiskHandleMtaHelper;
    }

    public static void l(JDRiskHandleVerifyHelper jDRiskHandleVerifyHelper) {
        d = jDRiskHandleVerifyHelper;
    }

    public static void m(HttpSetting httpSetting) {
        if (httpSetting != null) {
            try {
                if (I()) {
                    return;
                }
                httpSetting.setAppId("RiskHandleSDK");
                httpSetting.setSecretKey("1bf945d8f010405db1903d66be7406c0");
            } catch (Exception unused) {
            }
        }
    }

    public static void n(WJLoginAntiRpIdProxy wJLoginAntiRpIdProxy) {
        try {
            if (E() == null || wJLoginAntiRpIdProxy == null) {
                return;
            }
            E().setAntiRpIdProxy(wJLoginAntiRpIdProxy);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void o(OnCommonCallback onCommonCallback) {
        try {
            if (E() == null || onCommonCallback == null) {
                return;
            }
            E().CheckA2(onCommonCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static String p() {
        try {
            if (E() != null) {
                String antiRpId = E().getAntiRpId();
                d.a("RiskHandle", "loginRpId=" + antiRpId);
                return antiRpId;
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public static void q(Context context) {
        if (context == null) {
            return;
        }
        try {
            if (f8170c == null || !I()) {
                JDRiskHandleManager.getInstance().loadUrl(context, u());
            } else {
                f8170c.jumpToFeedBack(context, u());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static String r() {
        return "";
    }

    private static String s() {
        return BaseInfo.getAppVersionName();
    }

    public static String t() {
        try {
            JDRiskHandleInfoHelper jDRiskHandleInfoHelper = a;
            return jDRiskHandleInfoHelper != null ? b(jDRiskHandleInfoHelper.getEid()) : "";
        } catch (Exception unused) {
            return "";
        }
    }

    private static String u() {
        try {
            return String.format("https://uc.jd.com/?dsCode=%s&loginType=%s&client=%s&clientVersion=%s&build=%s&osVersion=%s&networkType=%s&d_brand=%s&d_model=%s", "55F0326F04942317B7CA83BCE949A77AB588511B", x(), "android", s(), r(), y(), BaseInfo.getNetworkType(), BaseInfo.getDeviceBrand(), BaseInfo.getDeviceModel());
        } catch (Exception unused) {
            return "";
        }
    }

    public static double v() {
        try {
            JDRiskHandleVerifyHelper jDRiskHandleVerifyHelper = d;
            if (jDRiskHandleVerifyHelper != null) {
                return jDRiskHandleVerifyHelper.getLat();
            }
            return 0.0d;
        } catch (Exception unused) {
            return 0.0d;
        }
    }

    public static double w() {
        try {
            JDRiskHandleVerifyHelper jDRiskHandleVerifyHelper = d;
            if (jDRiskHandleVerifyHelper != null) {
                return jDRiskHandleVerifyHelper.getLng();
            }
            return 0.0d;
        } catch (Exception unused) {
            return 0.0d;
        }
    }

    private static String x() {
        return "10";
    }

    private static String y() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    public static String z() {
        try {
            return E() != null ? b(E().getPin()) : "";
        } catch (Exception unused) {
            return "";
        }
    }
}
