package jd.wjlogin_sdk.d;

import android.text.TextUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.dependency.IStatInfoConfig;
import jd.wjlogin_sdk.common.e;
import jd.wjlogin_sdk.model.FBTokenInfo;
import jd.wjlogin_sdk.model.FLDeviceInfo;
import jd.wjlogin_sdk.model.GlobalRegisterInfo;
import jd.wjlogin_sdk.model.GoogleTokenInfo;
import jd.wjlogin_sdk.model.HuaweiTokenInfo;
import jd.wjlogin_sdk.model.JDUnionTokenInfo;
import jd.wjlogin_sdk.model.LineTokenInfo;
import jd.wjlogin_sdk.model.OneKeyTokenInfo;
import jd.wjlogin_sdk.model.QQTokenInfo;
import jd.wjlogin_sdk.model.TwitterTokenInfo;
import jd.wjlogin_sdk.model.UnionLoginTokenInfo;
import jd.wjlogin_sdk.tlvtype.tlv_0x4;
import jd.wjlogin_sdk.util.g;
import jd.wjlogin_sdk.util.l;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.r;
import jd.wjlogin_sdk.util.z;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d {
    private static final String a = "WJLogin.MessageProcess";

    public static void A(b bVar, String str) {
        if (TextUtils.isEmpty(str)) {
            bVar.a(z.l0, (short) 0);
            return;
        }
        bVar.a(z.l0);
        bVar.c(str);
    }

    public static void B(b bVar, String str) {
        if (TextUtils.isEmpty(str)) {
            bVar.a(z.n0, (short) 0);
            return;
        }
        bVar.a(z.n0);
        bVar.c(str);
    }

    public static c a(short s, short s2, e eVar, int i2) {
        c cVar = new c();
        cVar.d((short) 0);
        cVar.b(1L);
        cVar.b(1);
        cVar.c(i2);
        cVar.a(0);
        cVar.b(s);
        cVar.e(s2);
        if (eVar != null) {
            cVar.a(eVar.h());
        }
        cVar.f((short) 273);
        cVar.a((byte) 0);
        p.b("cmd=" + ((int) s) + "  subCmd=" + ((int) s2));
        return cVar;
    }

    public static void b(b bVar, String str, String str2) {
        bVar.a((short) 2, (short) (((short) (bVar.f(str) + 4)) + bVar.f(str2)));
        bVar.c(str);
        bVar.c(str2);
    }

    public static void c(b bVar, String str) {
        bVar.a((short) 23);
        bVar.b(str);
    }

    public static void d(b bVar, String str) {
        if (str != null && str.length() != 0) {
            bVar.a((short) 24);
            bVar.c(str);
            return;
        }
        bVar.a((short) 24, (short) 0);
    }

    public static void e(b bVar, String str) {
        bVar.a((short) 25);
        bVar.c(str);
    }

    public static void f(b bVar, String str) {
        bVar.a((short) 26);
        bVar.c(str);
    }

    public static void g(b bVar, String str) {
        bVar.a((short) 28);
        bVar.c(str);
    }

    public static void h(b bVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        bVar.a((short) 30);
        bVar.c(str);
    }

    public static void i(b bVar, String str) {
        bVar.a((short) 36, bVar.f(str));
        bVar.a(str);
    }

    public static void j(b bVar, String str) {
        if (str != null) {
            bVar.a((short) 42);
            bVar.c(str);
        }
    }

    public static void k(b bVar, String str) {
        bVar.a((short) 49);
        bVar.c(str);
    }

    public static void l(b bVar, String str) {
        if (str != null) {
            bVar.a((short) 55);
            bVar.c(str);
        }
    }

    public static void m(b bVar, String str) {
        if (str != null) {
            bVar.a((short) 58);
            bVar.c(str);
        }
    }

    public static void n(b bVar, String str) {
        if (str != null) {
            bVar.a((short) 59);
            bVar.c(str);
        }
    }

    public static void o(b bVar, String str) {
        if (str != null) {
            bVar.a((short) 66);
            bVar.c(str);
        }
    }

    public static void p(b bVar, String str) {
        if (str != null) {
            bVar.a((short) 77);
            bVar.c(str);
        }
    }

    public static void q(b bVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        bVar.a((short) 87);
        bVar.c(str);
    }

    public static void r(b bVar, String str) {
        if (str != null) {
            bVar.a((short) 91);
            bVar.c(str);
        }
    }

    public static void s(b bVar, String str) {
        if (str != null && str.length() != 0) {
            bVar.a((short) 93);
            bVar.c(str);
            return;
        }
        bVar.a((short) 93, (short) 0);
    }

    public static void t(b bVar, String str) {
        String c2 = jd.wjlogin_sdk.common.h.a.c();
        if (c2 == null) {
            c2 = "";
        }
        bVar.a(z.Q, (short) (bVar.f(c2) + 8));
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c(c2);
    }

    public static void u(b bVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        bVar.a(z.S);
        bVar.c(str);
    }

    public static void v(b bVar, String str) {
        if (str != null) {
            bVar.a(z.V);
            bVar.c(str);
        }
    }

    public static void w(b bVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        bVar.a(z.Z);
        bVar.c(str);
    }

    public static void x(b bVar, String str) {
        if (str != null) {
            bVar.a(z.h0);
            bVar.c(str);
        }
    }

    public static void y(b bVar, String str) throws Exception {
        byte[] b = jd.wjlogin_sdk.util.b.b(str);
        bVar.a((short) 10);
        bVar.a(b);
    }

    public static void z(b bVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        bVar.a(z.i0);
        bVar.c(str);
    }

    public static void c(b bVar, short s) {
        bVar.a(z.o0, (short) 2);
        bVar.b(s);
    }

    public static void c(b bVar, int i2) {
        bVar.a(z.o0, (short) 4);
        bVar.a(i2);
    }

    public static void d(b bVar, int i2) {
        bVar.a(z.m0, (short) 4);
        bVar.a(i2);
    }

    public static void b(b bVar, String str) {
        if (TextUtils.isEmpty(str)) {
            bVar.a((short) 17, (short) 0);
            return;
        }
        bVar.a((short) 17);
        bVar.c(str);
    }

    public static void b(b bVar, short s) {
        bVar.a(z.W, (short) 2);
        bVar.b(s);
    }

    public static void b(b bVar, int i2) {
        bVar.a(z.a0, (short) 4);
        bVar.a(i2);
    }

    public static void a(b bVar, byte b, int i2) {
        bVar.a((short) 34, (short) 5);
        bVar.a(b);
        bVar.a(i2);
    }

    public static void b(b bVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        bVar.a((short) 76);
        bVar.c(jSONObject.toString());
    }

    public static void a(b bVar, tlv_0x4 tlv_0x4Var) {
        if (tlv_0x4Var == null) {
            bVar.a((short) 4, (short) 0);
            return;
        }
        bVar.a((short) 4, (short) 52);
        bVar.a(tlv_0x4Var.getStrHexVer());
        bVar.a(tlv_0x4Var.getstrHexGuid());
    }

    public static void a(b bVar, e eVar, String str) {
        if (eVar == null) {
            bVar.a((short) 8, (short) 0);
            return;
        }
        short f2 = (short) (((short) (bVar.f(eVar.d()) + 30)) + bVar.f(eVar.a()));
        String k2 = jd.wjlogin_sdk.common.h.a.k();
        String b = jd.wjlogin_sdk.common.h.a.b();
        if (TextUtils.isEmpty(k2)) {
            k2 = "";
        }
        if (TextUtils.isEmpty(b)) {
            b = "";
        }
        String c2 = eVar.c();
        String b2 = eVar.b();
        bVar.a((short) 8, (short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (f2 + bVar.f(b))) + bVar.f(c2))) + bVar.f(str))) + bVar.f(jd.wjlogin_sdk.common.h.a.g()))) + bVar.f(jd.wjlogin_sdk.common.h.a.h()))) + bVar.f(k2))) + bVar.f(jd.wjlogin_sdk.util.e.a))) + bVar.f(b2))) + bVar.f(eVar.m())));
        bVar.b((short) 3);
        bVar.b(eVar.h());
        bVar.c(eVar.d());
        bVar.c(jd.wjlogin_sdk.common.h.a.g());
        bVar.c(c2);
        bVar.c(jd.wjlogin_sdk.common.h.a.h());
        bVar.c(eVar.a());
        bVar.c(str);
        bVar.c(b);
        bVar.c(k2);
        bVar.a(eVar.i());
        bVar.c(jd.wjlogin_sdk.util.e.a);
        bVar.c(b2);
        bVar.c(eVar.m());
    }

    public static void a(b bVar, String str) {
        if (str != null && str.length() != 0) {
            bVar.a((short) 16);
            bVar.c(str);
            return;
        }
        bVar.a((short) 16, (short) 0);
    }

    public static void a(b bVar, int i2, byte b) {
        bVar.a((short) 19, (short) 5);
        bVar.a(i2);
        bVar.a(b);
    }

    public static void a(b bVar, byte b) {
        bVar.a((short) 20, (short) 1);
        bVar.a(b);
    }

    public static void a(b bVar, String str, String str2) {
        bVar.a((short) 18, (short) (((short) (bVar.f(str) + 4)) + bVar.f(str2)));
        bVar.c(str);
        bVar.c(str2);
    }

    public static void a(b bVar, UnionLoginTokenInfo unionLoginTokenInfo) {
        bVar.a((short) 28);
        bVar.c(unionLoginTokenInfo.getCode());
    }

    public static void a(b bVar, JDUnionTokenInfo jDUnionTokenInfo) {
        bVar.a((short) 28);
        bVar.c(jDUnionTokenInfo.getCode());
    }

    public static void a(b bVar, QQTokenInfo qQTokenInfo) {
        bVar.a((short) 32, (short) (((short) (bVar.f(qQTokenInfo.getAccessToken()) + 4)) + bVar.f(qQTokenInfo.getOpenid())));
        bVar.c(qQTokenInfo.getAccessToken());
        bVar.c(qQTokenInfo.getOpenid());
    }

    public static void a(b bVar, e eVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(eVar.p())) {
                jSONObject.put(Configuration.UNION_ID, eVar.p());
            }
            if (!TextUtils.isEmpty(eVar.o())) {
                jSONObject.put(Configuration.SUB_UNION_ID, eVar.o());
            }
        } catch (Exception unused) {
        }
        bVar.a((short) 72, (short) (((short) (((short) (((short) (bVar.f(jd.wjlogin_sdk.common.h.a.f()) + 8)) + bVar.f(jd.wjlogin_sdk.common.h.a.d()))) + bVar.f(jd.wjlogin_sdk.common.h.a.e()))) + bVar.f(jSONObject.toString())));
        bVar.c(jd.wjlogin_sdk.common.h.a.f());
        bVar.c(jd.wjlogin_sdk.common.h.a.d());
        bVar.c(jd.wjlogin_sdk.common.h.a.e());
        bVar.c(jSONObject.toString());
    }

    public static void a(b bVar, int i2) {
        bVar.a((short) 85, (short) 4);
        bVar.a(i2);
    }

    public static void a(b bVar, FLDeviceInfo fLDeviceInfo) {
        bVar.a((short) 90, (short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (bVar.f("") + 52)) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f(""))) + bVar.f("")));
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.a(0);
        bVar.a(0);
        bVar.c("");
    }

    public static void a(b bVar, FBTokenInfo fBTokenInfo) {
        if (fBTokenInfo != null) {
            bVar.a(z.T, (short) (((short) (((short) (((short) (((short) (bVar.f(fBTokenInfo.getAppid()) + 18)) + bVar.f(fBTokenInfo.getDeclinedPermissions()))) + bVar.f(fBTokenInfo.getPermissions()))) + bVar.f(fBTokenInfo.getToken()))) + bVar.f(fBTokenInfo.getUserid())));
            bVar.c(fBTokenInfo.getAppid());
            bVar.c(fBTokenInfo.getDeclinedPermissions());
            bVar.a(fBTokenInfo.getExpirationDate());
            bVar.c(fBTokenInfo.getPermissions());
            bVar.a(fBTokenInfo.getRefreshDate());
            bVar.c(fBTokenInfo.getToken());
            bVar.c(fBTokenInfo.getUserid());
        }
    }

    public static void a(b bVar, short s) {
        bVar.a(z.U, (short) 2);
        bVar.b(s);
    }

    public static void a(b bVar) {
        tlv_0x4 e2 = g.e();
        if (e2 != null && !TextUtils.isEmpty(e2.getStrHexVer()) && !TextUtils.isEmpty(e2.getstrHexGuid())) {
            bVar.a((short) 4, (short) 52);
            bVar.a(e2.getStrHexVer());
            bVar.a(e2.getstrHexGuid());
        } else {
            if (e2 != null) {
                p.b(a, "putCommonData TLVType.tlv0x4 0 =" + e2.toString());
            }
            bVar.a((short) 4, (short) 0);
        }
        e d = g.d();
        if (d == null) {
            bVar.a((short) 8, (short) 0);
        } else {
            String b = r.b(jd.wjlogin_sdk.common.b.a());
            short f2 = (short) (((short) (bVar.f(d.d()) + 30)) + bVar.f(d.a()));
            String k2 = jd.wjlogin_sdk.common.h.a.k();
            String b2 = jd.wjlogin_sdk.common.h.a.b();
            p.b(a, IStatInfoConfig.REPORT_PARAM_LBS_AREA + b2);
            p.b(a, "uuid=" + k2);
            if (TextUtils.isEmpty(k2)) {
                k2 = "";
            }
            if (TextUtils.isEmpty(b2)) {
                b2 = "";
            }
            short f3 = (short) (f2 + bVar.f(b2));
            String c2 = d.c();
            String b3 = d.b();
            bVar.a((short) 8, (short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (f3 + bVar.f(c2))) + bVar.f(b))) + bVar.f(jd.wjlogin_sdk.common.h.a.g()))) + bVar.f(jd.wjlogin_sdk.common.h.a.h()))) + bVar.f(k2))) + bVar.f(jd.wjlogin_sdk.util.e.a))) + bVar.f(b3))) + bVar.f(d.m())));
            bVar.b((short) 3);
            bVar.b(d.h());
            bVar.c(d.d());
            bVar.c(jd.wjlogin_sdk.common.h.a.g());
            bVar.c(c2);
            bVar.c(jd.wjlogin_sdk.common.h.a.h());
            bVar.c(d.a());
            bVar.c(b);
            bVar.c(b2);
            bVar.c(k2);
            bVar.a(d.i());
            bVar.c(jd.wjlogin_sdk.util.e.a);
            bVar.c(b3);
            bVar.c(d.m());
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(d.p())) {
                jSONObject.put(Configuration.UNION_ID, d.p());
            }
            if (!TextUtils.isEmpty(d.o())) {
                jSONObject.put(Configuration.SUB_UNION_ID, d.o());
            }
        } catch (Exception unused) {
        }
        bVar.a((short) 72, (short) (((short) (((short) (((short) (bVar.f(jd.wjlogin_sdk.common.h.a.f()) + 8)) + bVar.f(jd.wjlogin_sdk.common.h.a.d()))) + bVar.f(jd.wjlogin_sdk.common.h.a.e()))) + bVar.f(jSONObject.toString())));
        bVar.c(jd.wjlogin_sdk.common.h.a.f());
        bVar.c(jd.wjlogin_sdk.common.h.a.d());
        bVar.c(jd.wjlogin_sdk.common.h.a.e());
        bVar.c(jSONObject.toString());
        String c3 = jd.wjlogin_sdk.common.h.a.c();
        bVar.a(z.Q, (short) (bVar.f(c3) + 8));
        bVar.c("");
        bVar.c("");
        bVar.c("");
        bVar.c(c3);
    }

    public static void a(b bVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        bVar.a((short) 53);
        bVar.c(jSONObject.toString());
    }

    public static void a(b bVar, GoogleTokenInfo googleTokenInfo) {
        if (googleTokenInfo != null) {
            bVar.a((short) 153, (short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (bVar.f(googleTokenInfo.getAppid()) + 16)) + bVar.f(googleTokenInfo.getIdToken()))) + bVar.f(googleTokenInfo.getAccessToken()))) + bVar.f(googleTokenInfo.getUserId()))) + bVar.f(googleTokenInfo.getFullName()))) + bVar.f(googleTokenInfo.getGivenName()))) + bVar.f(googleTokenInfo.getFamilyName()))) + bVar.f(googleTokenInfo.getEmail())));
            bVar.c(googleTokenInfo.getAppid());
            bVar.c(googleTokenInfo.getIdToken());
            bVar.c(googleTokenInfo.getAccessToken());
            bVar.c(googleTokenInfo.getUserId());
            bVar.c(googleTokenInfo.getFullName());
            bVar.c(googleTokenInfo.getGivenName());
            bVar.c(googleTokenInfo.getFamilyName());
            bVar.c(googleTokenInfo.getEmail());
        }
    }

    public static void a(b bVar, LineTokenInfo lineTokenInfo) {
        if (lineTokenInfo != null) {
            bVar.a(z.d0, (short) (((short) (((short) (((short) (((short) (((short) (bVar.f(lineTokenInfo.getAppid()) + 12)) + bVar.f(lineTokenInfo.getAccessToken()))) + bVar.f(lineTokenInfo.getDisplayName()))) + bVar.f(lineTokenInfo.getUserId()))) + bVar.f(lineTokenInfo.getStatusMessage()))) + bVar.f(lineTokenInfo.getPictureURL())));
            bVar.c(lineTokenInfo.getAppid());
            bVar.c(lineTokenInfo.getAccessToken());
            bVar.c(lineTokenInfo.getDisplayName());
            bVar.c(lineTokenInfo.getUserId());
            bVar.c(lineTokenInfo.getStatusMessage());
            bVar.c(lineTokenInfo.getPictureURL());
        }
    }

    public static void a(b bVar, TwitterTokenInfo twitterTokenInfo) {
        if (twitterTokenInfo != null) {
            bVar.a(z.e0, (short) (((short) (((short) (((short) (((short) (bVar.f(twitterTokenInfo.getAppid()) + 10)) + bVar.f(twitterTokenInfo.getAccessToken()))) + bVar.f(twitterTokenInfo.getAccessTokenSecret()))) + bVar.f(twitterTokenInfo.getUserName()))) + bVar.f(twitterTokenInfo.getUserID())));
            bVar.c(twitterTokenInfo.getAppid());
            bVar.c(twitterTokenInfo.getAccessToken());
            bVar.c(twitterTokenInfo.getAccessTokenSecret());
            bVar.c(twitterTokenInfo.getUserName());
            bVar.c(twitterTokenInfo.getUserID());
        }
    }

    public static void a(b bVar, GlobalRegisterInfo globalRegisterInfo) {
        if (globalRegisterInfo != null) {
            bVar.a(z.f0, (short) (((short) (((short) (((short) (((short) (bVar.f(globalRegisterInfo.getFirstName()) + 14)) + bVar.f(globalRegisterInfo.getLastName()))) + bVar.f(globalRegisterInfo.getEmail()))) + bVar.f(globalRegisterInfo.getMobile()))) + bVar.f(globalRegisterInfo.getReserve())));
            bVar.a(globalRegisterInfo.getRegType());
            bVar.c(globalRegisterInfo.getFirstName());
            bVar.c(globalRegisterInfo.getLastName());
            bVar.c(globalRegisterInfo.getMobile());
            bVar.c(globalRegisterInfo.getEmail());
            bVar.c(globalRegisterInfo.getReserve());
        }
    }

    public static void a(b bVar, HuaweiTokenInfo huaweiTokenInfo) {
        String accessToken = huaweiTokenInfo.getAccessToken();
        if (accessToken == null) {
            accessToken = "";
        }
        bVar.a((short) 29, (short) (((short) (((short) (((short) (bVar.f(accessToken) + 13)) + bVar.f(huaweiTokenInfo.getRefreshToken()))) + bVar.f(huaweiTokenInfo.getOpenid()))) + bVar.f(huaweiTokenInfo.getScope())));
        bVar.c(accessToken);
        bVar.a(huaweiTokenInfo.getExpireTime());
        bVar.c(huaweiTokenInfo.getRefreshToken());
        bVar.c(huaweiTokenInfo.getOpenid());
        bVar.c(huaweiTokenInfo.getScope());
        bVar.a((byte) 3);
    }

    public static void a(b bVar, OneKeyTokenInfo oneKeyTokenInfo) {
        if (oneKeyTokenInfo != null) {
            bVar.a((short) 60, (short) (((short) (bVar.f(oneKeyTokenInfo.getAccessToken()) + 6)) + bVar.f(oneKeyTokenInfo.getReserve())));
            bVar.b(oneKeyTokenInfo.getOperateType());
            bVar.c(oneKeyTokenInfo.getAccessToken());
            bVar.c(oneKeyTokenInfo.getReserve());
            return;
        }
        bVar.a((short) 60, (short) 0);
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
