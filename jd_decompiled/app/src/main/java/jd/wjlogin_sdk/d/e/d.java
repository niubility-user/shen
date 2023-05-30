package jd.wjlogin_sdk.d.e;

import android.text.TextUtils;
import com.jingdong.jdsdk.config.Configuration;
import jd.wjlogin_sdk.common.WJLoginExtendProxy;
import jd.wjlogin_sdk.common.e;
import jd.wjlogin_sdk.model.FLDeviceInfo;
import jd.wjlogin_sdk.tlvtype.tlv_0x4;
import jd.wjlogin_sdk.util.g;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.r;
import jd.wjlogin_sdk.util.z;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d {
    public static jd.wjlogin_sdk.d.c a(short s, short s2, e eVar, int i2) {
        jd.wjlogin_sdk.d.c cVar = new jd.wjlogin_sdk.d.c();
        cVar.c((short) 1);
        cVar.a(0L);
        cVar.b(1);
        cVar.c(i2);
        cVar.a(0);
        cVar.b(s);
        cVar.e(s2);
        if (eVar != null) {
            cVar.a(eVar.h());
        }
        cVar.f(jd.wjlogin_sdk.util.e.f19934g);
        cVar.a((byte) 0);
        p.b("cmd=" + ((int) s) + "  subCmd=" + ((int) s2));
        return cVar;
    }

    public static void b(c cVar, String str) {
        if (str != null && str.length() != 0) {
            cVar.a((short) 24);
            cVar.b(str);
            return;
        }
        cVar.a((short) 24, 0);
    }

    public static void c(c cVar, String str) {
        if (str != null && str.length() != 0) {
            cVar.a((short) 93);
            cVar.b(str);
            return;
        }
        cVar.a((short) 93, 0);
    }

    public static void d(c cVar, String str) {
        if (str == null) {
            str = "";
        }
        cVar.a(z.Q, cVar.e(str) + 8);
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c(str);
    }

    public static void e(c cVar, String str) {
        if (TextUtils.isEmpty(str)) {
            cVar.a(z.R, 0);
            return;
        }
        cVar.a(z.R);
        cVar.b(str);
    }

    public static void f(c cVar, String str) throws Exception {
        byte[] b = jd.wjlogin_sdk.util.b.b(str);
        cVar.a((short) 10);
        cVar.a(b);
    }

    public static void a(c cVar, tlv_0x4 tlv_0x4Var) {
        if (tlv_0x4Var == null) {
            cVar.a((short) 4, 0);
            return;
        }
        cVar.a((short) 4, 52);
        cVar.a(tlv_0x4Var.getStrHexVer());
        cVar.a(tlv_0x4Var.getstrHexGuid());
    }

    public static void a(c cVar, e eVar, String str, WJLoginExtendProxy wJLoginExtendProxy) {
        if (eVar == null) {
            cVar.a((short) 8, 0);
            return;
        }
        int f2 = cVar.f(eVar.d()) + 30 + cVar.f(eVar.a());
        String k2 = jd.wjlogin_sdk.common.h.a.k();
        String b = jd.wjlogin_sdk.common.h.a.b();
        if (TextUtils.isEmpty(k2)) {
            k2 = "";
        }
        if (TextUtils.isEmpty(b)) {
            b = "";
        }
        int f3 = f2 + cVar.f(b);
        String c2 = eVar.c();
        int f4 = f3 + cVar.f(c2) + cVar.f(str) + cVar.f(jd.wjlogin_sdk.common.h.a.g()) + cVar.f(jd.wjlogin_sdk.common.h.a.h()) + cVar.f(k2) + cVar.f(jd.wjlogin_sdk.util.e.a);
        String b2 = eVar.b();
        cVar.a((short) 8, f4 + cVar.f(b2) + cVar.f(eVar.m()));
        cVar.b((short) 3);
        cVar.b(eVar.h());
        cVar.c(eVar.d());
        cVar.c(jd.wjlogin_sdk.common.h.a.g());
        cVar.c(c2);
        cVar.c(jd.wjlogin_sdk.common.h.a.h());
        cVar.c(eVar.a());
        cVar.c(str);
        cVar.c(b);
        cVar.c(k2);
        cVar.a(eVar.i());
        cVar.c(jd.wjlogin_sdk.util.e.a);
        cVar.c(b2);
        cVar.c(eVar.m());
    }

    public static void a(c cVar, String str) {
        if (str != null && str.length() != 0) {
            cVar.a((short) 16);
            cVar.b(str);
            return;
        }
        cVar.a((short) 16, 0);
    }

    public static void a(c cVar, e eVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Configuration.UNION_ID, eVar.p());
            jSONObject.put(Configuration.SUB_UNION_ID, eVar.o());
        } catch (Exception unused) {
        }
        cVar.a((short) 72, cVar.f(jd.wjlogin_sdk.common.h.a.f()) + 8 + cVar.f(jd.wjlogin_sdk.common.h.a.d()) + cVar.f(jd.wjlogin_sdk.common.h.a.e()) + cVar.f(jSONObject.toString()));
        cVar.c(jd.wjlogin_sdk.common.h.a.f());
        cVar.c(jd.wjlogin_sdk.common.h.a.d());
        cVar.c(jd.wjlogin_sdk.common.h.a.e());
        cVar.c(jSONObject.toString());
    }

    public static void a(c cVar, FLDeviceInfo fLDeviceInfo) {
        cVar.a((short) 90, cVar.f("") + 52 + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f("") + cVar.f(""));
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.a(0);
        cVar.a(0);
        cVar.c("");
    }

    public static void a(c cVar) {
        tlv_0x4 e2 = g.e();
        if (e2 == null) {
            cVar.a((short) 4, 0);
        } else {
            cVar.a((short) 4, 52);
            cVar.a(e2.getStrHexVer());
            cVar.a(e2.getstrHexGuid());
        }
        e d = g.d();
        if (d == null) {
            cVar.a((short) 8, 0);
        } else {
            int f2 = cVar.f(d.d()) + 30 + cVar.f(d.a());
            String k2 = jd.wjlogin_sdk.common.h.a.k();
            String b = jd.wjlogin_sdk.common.h.a.b();
            if (TextUtils.isEmpty(k2)) {
                k2 = "";
            }
            if (TextUtils.isEmpty(b)) {
                b = "";
            }
            String b2 = r.b(jd.wjlogin_sdk.common.b.a());
            int f3 = f2 + cVar.f(b);
            String c2 = d.c();
            int f4 = f3 + cVar.f(c2) + cVar.f(b2) + cVar.f(jd.wjlogin_sdk.common.h.a.g()) + cVar.f(jd.wjlogin_sdk.common.h.a.h()) + cVar.f(k2) + cVar.f(jd.wjlogin_sdk.util.e.a);
            String b3 = d.b();
            cVar.a((short) 8, f4 + cVar.f(b3) + cVar.f(d.m()));
            cVar.b((short) 3);
            cVar.b(d.h());
            cVar.c(d.d());
            cVar.c(jd.wjlogin_sdk.common.h.a.g());
            cVar.c(c2);
            cVar.c(jd.wjlogin_sdk.common.h.a.h());
            cVar.c(d.a());
            cVar.c(b2);
            cVar.c(b);
            cVar.c(k2);
            cVar.a(d.i());
            cVar.c(jd.wjlogin_sdk.util.e.a);
            cVar.c(b3);
            cVar.c(d.m());
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Configuration.UNION_ID, d.p());
            jSONObject.put(Configuration.SUB_UNION_ID, d.o());
        } catch (Exception unused) {
        }
        cVar.a((short) 72, cVar.f(jd.wjlogin_sdk.common.h.a.f()) + 8 + cVar.f(jd.wjlogin_sdk.common.h.a.d()) + cVar.f(jd.wjlogin_sdk.common.h.a.e()) + cVar.f(jSONObject.toString()));
        cVar.c(jd.wjlogin_sdk.common.h.a.f());
        cVar.c(jd.wjlogin_sdk.common.h.a.d());
        cVar.c(jd.wjlogin_sdk.common.h.a.e());
        cVar.c(jSONObject.toString());
        String c3 = jd.wjlogin_sdk.common.h.a.c();
        cVar.a(z.Q, cVar.e(c3) + 8);
        cVar.c("");
        cVar.c("");
        cVar.c("");
        cVar.c(c3);
    }
}
