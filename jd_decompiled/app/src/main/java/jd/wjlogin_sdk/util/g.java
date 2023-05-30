package jd.wjlogin_sdk.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import jd.wjlogin_sdk.model.ClientInfo;
import jd.wjlogin_sdk.tlvtype.tlv_0x4;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class g {
    private static final String a = "WJLogin.DataUtil";
    public static final int b = 0;

    /* renamed from: c */
    public static final int f19965c = 1;
    private static tlv_0x4 d = null;

    /* renamed from: e */
    private static String f19966e = null;

    /* renamed from: f */
    private static String f19967f = "com.jingdong.th.app";

    /* renamed from: g */
    private static String f19968g = "com.jingdong.app.mall";

    /* renamed from: h */
    private static jd.wjlogin_sdk.common.e f19969h = null;

    /* renamed from: i */
    private static String f19970i = "";

    /* renamed from: j */
    private static String f19971j = "";

    /* renamed from: k */
    private static String f19972k = "";

    public static String a(String str, int i2) {
        if (str != null) {
            try {
                if (str.length() > i2) {
                    str = str.substring(0, i2);
                }
            } catch (Exception unused) {
            }
        }
        return str == null ? "" : str;
    }

    public static String b(Context context) {
        try {
            return context.getPackageName();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static int c(Context context) {
        try {
            PackageInfo e2 = e(context);
            if (e2 != null) {
                return e2.versionCode;
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String d(Context context) {
        try {
            PackageInfo e2 = e(context);
            if (e2 == null) {
                return "";
            }
            String str = e2.versionName;
            return !TextUtils.isEmpty(str) ? str : "";
        } catch (Exception e3) {
            e3.printStackTrace();
            return "";
        }
    }

    private static PackageInfo e(Context context) {
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    public static String f() {
        try {
            if (TextUtils.isEmpty(f19971j)) {
                f19971j = BaseInfo.getDeviceModel();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        p.b(a, "model = " + f19971j);
        return f19971j;
    }

    public static tlv_0x4 e() {
        p.b("getGuid =" + d.toString());
        return d;
    }

    public static String a(Context context) {
        try {
            if (TextUtils.isEmpty(f19970i)) {
                f19970i = BaseInfo.getAndroidIdWithAOPBySecure(context.getContentResolver(), "android_id");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        p.b(a, "androidID = " + f19970i);
        return f19970i;
    }

    public static int b() {
        String packageName = jd.wjlogin_sdk.common.b.a().getPackageName();
        return (!f19968g.equals(packageName) && f19967f.equals(packageName)) ? 1 : 0;
    }

    public static String c() {
        try {
            if (TextUtils.isEmpty(f19972k)) {
                f19972k = BaseInfo.getDeviceBrand();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        p.b(a, "brand = " + f19972k);
        return f19972k;
    }

    public static jd.wjlogin_sdk.common.e d() {
        return f19969h;
    }

    public static void a() {
        tlv_0x4 tlv_0x4Var = d;
        if (tlv_0x4Var == null || TextUtils.isEmpty(tlv_0x4Var.getStrHexVer()) || TextUtils.isEmpty(d.getstrHexGuid())) {
            tlv_0x4 tlv_0x4Var2 = (tlv_0x4) o.a(e.d);
            if (tlv_0x4Var2 != null && !TextUtils.isEmpty(tlv_0x4Var2.getStrHexVer()) && !TextUtils.isEmpty(tlv_0x4Var2.getstrHexGuid())) {
                d = tlv_0x4Var2;
                return;
            }
            tlv_0x4 tlv_0x4Var3 = new tlv_0x4();
            try {
                tlv_0x4Var3.setwGuidVer((short) 1);
                tlv_0x4Var3.setcTerminalType((short) 1);
                tlv_0x4Var3.setcOSVer(b0.c(jd.wjlogin_sdk.common.h.a.g()));
                String jVar = new j(jd.wjlogin_sdk.common.b.a()).toString();
                tlv_0x4Var3.setstrHexGuid(jVar);
                tlv_0x4Var3.setwNextFieldLen((short) jVar.length());
                c cVar = new c();
                cVar.a(tlv_0x4Var3.getwGuidVer());
                cVar.a(tlv_0x4Var3.getcTerminalType());
                cVar.a(tlv_0x4Var3.getcOSVer());
                cVar.a(tlv_0x4Var3.getwNextFieldLen());
                tlv_0x4Var3.setStrHexVer(ByteUtil.parseByte2HexStr(cVar.c()));
                o.a(e.d, tlv_0x4Var3);
                d = tlv_0x4Var3;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(ClientInfo clientInfo) {
        f19969h = new jd.wjlogin_sdk.common.e(clientInfo);
    }

    private static void a(String str, JSONObject jSONObject) {
        try {
            if (p.b) {
                p.b(str, "info: " + l.a(jSONObject));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
