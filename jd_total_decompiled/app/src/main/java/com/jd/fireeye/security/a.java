package com.jd.fireeye.security;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.fireeye.b.f;
import com.jd.fireeye.b.o;
import com.jd.fireeye.security.fireeye.IMtaUtils;
import java.net.URLEncoder;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class a {
    public static Context a = null;
    private static String b = "";

    /* renamed from: c  reason: collision with root package name */
    private static String f2628c = "";
    private static String d = "";

    /* renamed from: e  reason: collision with root package name */
    private static String f2629e = "";

    /* renamed from: f  reason: collision with root package name */
    private static String f2630f = "";

    /* renamed from: g  reason: collision with root package name */
    private static boolean f2631g = false;

    /* renamed from: h  reason: collision with root package name */
    private static String f2632h = "";

    /* renamed from: i  reason: collision with root package name */
    private static String f2633i = "";

    /* renamed from: j  reason: collision with root package name */
    private static String f2634j = "";

    /* renamed from: k  reason: collision with root package name */
    private static String f2635k = "";

    /* renamed from: l  reason: collision with root package name */
    private static String f2636l = "";

    /* renamed from: m  reason: collision with root package name */
    private static boolean f2637m = false;

    /* renamed from: n  reason: collision with root package name */
    private static boolean f2638n = false;
    private static boolean o = true;
    private static IMtaUtils p = null;
    private static JSONObject q = null;
    private static String r = "1000";
    private static boolean s;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.fireeye.security.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public class C0082a implements IMtaUtils {
        C0082a() {
        }

        @Override // com.jd.fireeye.security.fireeye.IMtaUtils
        public void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
        }
    }

    public static void a(Context context) {
        if (a == null) {
            if (context instanceof Application) {
                Application application = (Application) context;
                a = context;
            } else if (context != null) {
                Context applicationContext = context.getApplicationContext();
                a = applicationContext;
                if (applicationContext instanceof Application) {
                    Application application2 = (Application) applicationContext;
                }
            }
        }
        if (com.jd.fireeye.network.b.b != null || context == null) {
            return;
        }
        com.jd.fireeye.network.b.b = context.getApplicationContext();
    }

    public static void b(boolean z) {
        f2631g = z;
    }

    public static void c(String str) {
        if (f.a) {
            f.a("try update extUniqueId = " + str);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        b = str;
    }

    public static String d() {
        String str = b;
        return str == null ? "" : str;
    }

    public static JSONObject e() {
        return q;
    }

    public static long f() {
        try {
            return Long.parseLong(r);
        } catch (Exception unused) {
            return 1000L;
        }
    }

    public static JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PairKey.APP_KEY, a());
            jSONObject.put("unionID", m());
            jSONObject.put("installID", c());
            jSONObject.put("devInfo", d());
            if (!TextUtils.isEmpty(o.a("touchstone_expids", ""))) {
                jSONObject.put("touchstone_expids", o.a("touchstone_expids", ""));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static String h() {
        return f2632h;
    }

    public static String i() {
        String str = f2630f;
        return str == null ? "" : str;
    }

    public static String j() {
        return f2634j;
    }

    public static String k() {
        if (TextUtils.isEmpty(f2633i)) {
            f.a("FireEyeSDK Error", "publicKey is NULL!");
        }
        return f2633i;
    }

    public static String l() {
        String str = d;
        return str == null ? "" : str;
    }

    public static String m() {
        String str = f2628c;
        return str == null ? "" : str;
    }

    public static IMtaUtils n() {
        IMtaUtils iMtaUtils = p;
        return iMtaUtils == null ? new C0082a() : iMtaUtils;
    }

    public static boolean o() {
        return f2637m;
    }

    public static boolean p() {
        return f2638n;
    }

    public static boolean q() {
        return f2631g;
    }

    public static boolean r() {
        return o;
    }

    public static boolean s() {
        return s;
    }

    public static void t() {
        try {
            f2635k = URLEncoder.encode(com.jd.fireeye.b.b.b(f2634j, k()), "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
            f2635k = "";
        }
    }

    public static String b() {
        if (TextUtils.isEmpty(f2635k)) {
            f.a("FireEyeSDK Error", "encryptPrivateKey is NULL!");
        }
        return f2635k;
    }

    public static void b(String str) {
        f2632h = str;
    }

    public static String c() {
        String str = f2629e;
        return str == null ? "" : str;
    }

    public static void c(boolean z) {
        s = z;
    }

    public static void a(FireEyeBaseData fireEyeBaseData, long j2) {
        if (fireEyeBaseData != null) {
            try {
                if (fireEyeBaseData.getiMtaUtils() != null) {
                    p = fireEyeBaseData.getiMtaUtils();
                } else {
                    FireEyeInit.isSuccess = false;
                    JSONObject a2 = a(fireEyeBaseData);
                    a2.put("reason", "\u57cb\u70b9\u56de\u8c03 \u4e3a\u7a7a");
                    a2.put("duration", String.valueOf(System.currentTimeMillis() - j2));
                    n().sendClickDataWithExt(a, "FireEye_SDK_Init_Failed", a2.toString(), "", "", "", "", "", "", null);
                }
                if (!TextUtils.isEmpty(fireEyeBaseData.getAppKey())) {
                    f2636l = fireEyeBaseData.getAppKey();
                } else {
                    FireEyeInit.isSuccess = false;
                    JSONObject a3 = a(fireEyeBaseData);
                    a3.put("reason", "appKey \u4e3a\u7a7a");
                    a3.put("duration", String.valueOf(System.currentTimeMillis() - j2));
                    n().sendClickDataWithExt(a, "FireEye_SDK_Init_Failed", a3.toString(), "", "", "", "", "", "", null);
                }
                if (!TextUtils.isEmpty(fireEyeBaseData.getUnionId())) {
                    f2628c = fireEyeBaseData.getUnionId();
                } else {
                    FireEyeInit.isSuccess = false;
                    JSONObject a4 = a(fireEyeBaseData);
                    a4.put("reason", "unionId \u4e3a\u7a7a");
                    a4.put("duration", String.valueOf(System.currentTimeMillis() - j2));
                    n().sendClickDataWithExt(a, "FireEye_SDK_Init_Failed", a4.toString(), "", "", "", "", "", "", null);
                }
                if (!TextUtils.isEmpty(fireEyeBaseData.getInstalltionid())) {
                    f2629e = fireEyeBaseData.getInstalltionid();
                } else {
                    FireEyeInit.isSuccess = false;
                    JSONObject a5 = a(fireEyeBaseData);
                    a5.put("reason", "installtionid \u4e3a\u7a7a");
                    a5.put("duration", String.valueOf(System.currentTimeMillis() - j2));
                    n().sendClickDataWithExt(a, "FireEye_SDK_Init_Failed", a5.toString(), "", "", "", "", "", "", null);
                }
                if (!TextUtils.isEmpty(fireEyeBaseData.getDeviceCode())) {
                    b = fireEyeBaseData.getDeviceCode();
                } else {
                    FireEyeInit.isSuccess = false;
                    JSONObject a6 = a(fireEyeBaseData);
                    a6.put("reason", "deviceCode \u4e3a\u7a7a");
                    a6.put("duration", String.valueOf(System.currentTimeMillis() - j2));
                    n().sendClickDataWithExt(a, "FireEye_SDK_Init_Failed", a6.toString(), "", "", "", "", "", "", null);
                }
                if (!TextUtils.isEmpty(fireEyeBaseData.getSubunionId())) {
                    d = fireEyeBaseData.getSubunionId();
                } else {
                    FireEyeInit.isSuccess = false;
                    JSONObject a7 = a(fireEyeBaseData);
                    a7.put("reason", "subunionId \u4e3a\u7a7a");
                    a7.put("duration", String.valueOf(System.currentTimeMillis() - j2));
                    n().sendClickDataWithExt(a, "FireEye_SDK_Init_Failed", a7.toString(), "", "", "", "", "", "", null);
                }
                if (!TextUtils.isEmpty(fireEyeBaseData.getPartner())) {
                    f2630f = fireEyeBaseData.getPartner();
                } else {
                    FireEyeInit.isSuccess = false;
                    JSONObject a8 = a(fireEyeBaseData);
                    a8.put("reason", "partner \u4e3a\u7a7a");
                    a8.put("duration", String.valueOf(System.currentTimeMillis() - j2));
                    n().sendClickDataWithExt(a, "FireEye_SDK_Init_Failed", a8.toString(), "", "", "", "", "", "", null);
                }
                if (!TextUtils.isEmpty(fireEyeBaseData.getPublicKey())) {
                    f2633i = fireEyeBaseData.getPublicKey();
                } else {
                    FireEyeInit.isSuccess = false;
                    JSONObject a9 = a(fireEyeBaseData);
                    a9.put("reason", "publicKey \u4e3a\u7a7a");
                    a9.put("duration", String.valueOf(System.currentTimeMillis() - j2));
                    n().sendClickDataWithExt(a, "FireEye_SDK_Init_Failed", a9.toString(), "", "", "", "", "", "", null);
                }
                f2632h = fireEyeBaseData.getOaId();
                f2637m = fireEyeBaseData.isAppSwitch();
                f2638n = fireEyeBaseData.isClipSwitch();
                o = fireEyeBaseData.isMtaSwitch();
                if (!TextUtils.isEmpty(fireEyeBaseData.getH5Switch())) {
                    r = fireEyeBaseData.getH5Switch();
                }
                f2634j = com.jd.fireeye.b.b.a();
                t();
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static String a() {
        if (TextUtils.isEmpty(f2636l)) {
            f.a("FireEyeSDK Error", "appKey  is NULL!");
        }
        return f2636l;
    }

    public static void a(boolean z) {
        f2638n = z;
    }

    public static JSONObject a(FireEyeBaseData fireEyeBaseData) {
        JSONObject jSONObject = new JSONObject();
        if (fireEyeBaseData == null) {
            return jSONObject;
        }
        try {
            if (!TextUtils.isEmpty(fireEyeBaseData.getAppKey())) {
                jSONObject.put(PairKey.APP_KEY, fireEyeBaseData.getAppKey());
            }
            if (!TextUtils.isEmpty(fireEyeBaseData.getUnionId())) {
                jSONObject.put("unionID", fireEyeBaseData.getUnionId());
            }
            if (!TextUtils.isEmpty(fireEyeBaseData.getInstalltionid())) {
                jSONObject.put("installID", fireEyeBaseData.getInstalltionid());
            }
            if (!TextUtils.isEmpty(fireEyeBaseData.getDeviceCode())) {
                jSONObject.put("devInfo", fireEyeBaseData.getDeviceCode());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static void a(JSONObject jSONObject) {
        q = jSONObject;
    }

    public static void a(String str) {
        r = str;
    }
}
