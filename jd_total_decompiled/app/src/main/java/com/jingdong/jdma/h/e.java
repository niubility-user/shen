package com.jingdong.jdma.h;

import android.text.TextUtils;
import android.webkit.URLUtil;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdma.bean.e.f;
import com.jingdong.jdma.common.utils.Constant;
import com.jingdong.jdma.common.utils.LogUtil;
import com.jingdong.jdma.common.utils.n;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class e {

    /* renamed from: c  reason: collision with root package name */
    private int f12767c = 30;
    private String d = Constant.STATISTIC_GET_STRATEGY_DEFAULT_DOMAIN;
    private volatile f a = new f();
    private c b = new c();

    /* renamed from: e  reason: collision with root package name */
    private com.jingdong.jdma.bean.e.d f12768e = new com.jingdong.jdma.bean.e.d();

    /* renamed from: f  reason: collision with root package name */
    private com.jingdong.jdma.bean.e.a f12769f = new com.jingdong.jdma.bean.e.a();

    /* renamed from: g  reason: collision with root package name */
    private com.jingdong.jdma.bean.e.e f12770g = new com.jingdong.jdma.bean.e.e();

    /* renamed from: h  reason: collision with root package name */
    private com.jingdong.jdma.bean.e.b f12771h = new com.jingdong.jdma.bean.e.b();

    public e() {
        a();
    }

    private void e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("condition") && this.b.a(jSONObject.optString("condition"))) {
                this.f12769f.a(jSONObject.optString("discardResponseCodes"));
                this.f12769f.b(jSONObject.optInt("count"));
                this.f12769f.c(jSONObject.optInt("retryInterval"));
                this.f12769f.a(true);
            } else {
                this.f12769f.a(false);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void f(String str) {
        this.f12771h.a(this.a.b());
        this.f12771h.b(this.a.c());
        if (TextUtils.isEmpty(str)) {
            this.f12771h.c(this.a.e());
            this.f12771h.d(this.a.f());
            this.f12771h.e(this.a.g());
            this.f12771h.f(this.a.h());
            this.f12771h.g(this.a.i());
            this.f12771h.h(this.a.j());
            this.f12771h.i(this.a.k());
            this.f12771h.j(this.a.l());
            this.f12771h.k(this.a.z());
            this.f12771h.l(this.a.A());
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("condition") && this.b.a(jSONObject.optString("condition"))) {
                JSONObject jSONObject2 = new JSONObject(jSONObject.optString("action"));
                if (jSONObject2.has("2g")) {
                    String optString = jSONObject2.optString("2g");
                    if (optString.contains(DYConstants.DY_REGEX_COMMA)) {
                        this.f12771h.c(Integer.parseInt(optString.substring(0, optString.indexOf(DYConstants.DY_REGEX_COMMA))));
                        this.f12771h.d(Integer.parseInt(optString.substring(optString.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
                if (jSONObject2.has("3g")) {
                    String optString2 = jSONObject2.optString("3g");
                    if (optString2.contains(DYConstants.DY_REGEX_COMMA)) {
                        this.f12771h.e(Integer.parseInt(optString2.substring(0, optString2.indexOf(DYConstants.DY_REGEX_COMMA))));
                        this.f12771h.f(Integer.parseInt(optString2.substring(optString2.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
                if (jSONObject2.has("4g")) {
                    String optString3 = jSONObject2.optString("4g");
                    if (optString3.contains(DYConstants.DY_REGEX_COMMA)) {
                        this.f12771h.g(Integer.parseInt(optString3.substring(0, optString3.indexOf(DYConstants.DY_REGEX_COMMA))));
                        this.f12771h.h(Integer.parseInt(optString3.substring(optString3.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
                if (jSONObject2.has("5g")) {
                    String optString4 = jSONObject2.optString("5g");
                    if (optString4.contains(DYConstants.DY_REGEX_COMMA)) {
                        this.f12771h.i(Integer.parseInt(optString4.substring(0, optString4.indexOf(DYConstants.DY_REGEX_COMMA))));
                        this.f12771h.j(Integer.parseInt(optString4.substring(optString4.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
                if (jSONObject2.has("wifi")) {
                    String optString5 = jSONObject2.optString("wifi");
                    if (optString5.contains(DYConstants.DY_REGEX_COMMA)) {
                        this.f12771h.k(Integer.parseInt(optString5.substring(0, optString5.indexOf(DYConstants.DY_REGEX_COMMA))));
                        this.f12771h.l(Integer.parseInt(optString5.substring(optString5.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    private void g(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                this.f12768e = new com.jingdong.jdma.bean.e.d();
                return;
            }
            com.jingdong.jdma.bean.e.c cVar = new com.jingdong.jdma.bean.e.c();
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("action");
            String optString2 = jSONObject.optString("condition");
            cVar.a(this.a.b());
            cVar.b(this.a.c());
            if (!TextUtils.isEmpty(optString)) {
                JSONObject jSONObject2 = new JSONObject(optString);
                if (jSONObject2.has("2g")) {
                    String optString3 = jSONObject2.optString("2g");
                    if (optString3.contains(DYConstants.DY_REGEX_COMMA)) {
                        cVar.c(Integer.parseInt(optString3.substring(0, optString3.indexOf(DYConstants.DY_REGEX_COMMA))));
                        cVar.d(Integer.parseInt(optString3.substring(optString3.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
                if (jSONObject2.has("3g")) {
                    String optString4 = jSONObject2.optString("3g");
                    if (optString4.contains(DYConstants.DY_REGEX_COMMA)) {
                        cVar.e(Integer.parseInt(optString4.substring(0, optString4.indexOf(DYConstants.DY_REGEX_COMMA))));
                        cVar.f(Integer.parseInt(optString4.substring(optString4.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
                if (jSONObject2.has("4g")) {
                    String optString5 = jSONObject2.optString("4g");
                    if (optString5.contains(DYConstants.DY_REGEX_COMMA)) {
                        cVar.g(Integer.parseInt(optString5.substring(0, optString5.indexOf(DYConstants.DY_REGEX_COMMA))));
                        cVar.h(Integer.parseInt(optString5.substring(optString5.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
                if (jSONObject2.has("5g")) {
                    String optString6 = jSONObject2.optString("5g");
                    if (optString6.contains(DYConstants.DY_REGEX_COMMA)) {
                        cVar.i(Integer.parseInt(optString6.substring(0, optString6.indexOf(DYConstants.DY_REGEX_COMMA))));
                        cVar.j(Integer.parseInt(optString6.substring(optString6.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
                if (jSONObject2.has("wifi")) {
                    String optString7 = jSONObject2.optString("wifi");
                    if (optString7.contains(DYConstants.DY_REGEX_COMMA)) {
                        cVar.k(Integer.parseInt(optString7.substring(0, optString7.indexOf(DYConstants.DY_REGEX_COMMA))));
                        cVar.l(Integer.parseInt(optString7.substring(optString7.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
            }
            this.f12768e = new com.jingdong.jdma.bean.e.d(optString2, cVar);
        } catch (Exception unused) {
        }
    }

    private void i(String str) {
        try {
            this.b.d(str);
            if (!TextUtils.isEmpty(this.b.a())) {
                JSONObject jSONObject = new JSONObject(this.b.a());
                if (jSONObject.has("2g")) {
                    String optString = jSONObject.optString("2g");
                    if (optString.contains(DYConstants.DY_REGEX_COMMA)) {
                        this.a.d(Integer.parseInt(optString.substring(0, optString.indexOf(DYConstants.DY_REGEX_COMMA))));
                        this.a.e(Integer.parseInt(optString.substring(optString.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
                if (jSONObject.has("3g")) {
                    String optString2 = jSONObject.optString("3g");
                    if (optString2.contains(DYConstants.DY_REGEX_COMMA)) {
                        this.a.f(Integer.parseInt(optString2.substring(0, optString2.indexOf(DYConstants.DY_REGEX_COMMA))));
                        this.a.g(Integer.parseInt(optString2.substring(optString2.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
                if (jSONObject.has("4g")) {
                    String optString3 = jSONObject.optString("4g");
                    if (optString3.contains(DYConstants.DY_REGEX_COMMA)) {
                        this.a.h(Integer.parseInt(optString3.substring(0, optString3.indexOf(DYConstants.DY_REGEX_COMMA))));
                        this.a.i(Integer.parseInt(optString3.substring(optString3.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
                if (jSONObject.has("5g")) {
                    String optString4 = jSONObject.optString("5g");
                    if (optString4.contains(DYConstants.DY_REGEX_COMMA)) {
                        this.a.j(Integer.parseInt(optString4.substring(0, optString4.indexOf(DYConstants.DY_REGEX_COMMA))));
                        this.a.k(Integer.parseInt(optString4.substring(optString4.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
                if (jSONObject.has("wifi")) {
                    String optString5 = jSONObject.optString("wifi");
                    if (optString5.contains(DYConstants.DY_REGEX_COMMA)) {
                        this.a.u(Integer.parseInt(optString5.substring(0, optString5.indexOf(DYConstants.DY_REGEX_COMMA))));
                        this.a.v(Integer.parseInt(optString5.substring(optString5.indexOf(DYConstants.DY_REGEX_COMMA) + 1)));
                    }
                }
            }
            a();
        } catch (Exception unused) {
        }
    }

    private void j(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("condition") && this.b.a(jSONObject.optString("condition"))) {
                String optString = jSONObject.optString("value");
                if (TextUtils.isEmpty(optString)) {
                    return;
                }
                if (optString.contains(DYConstants.DY_REGEX_COMMA)) {
                    String[] split = optString.split(DYConstants.DY_REGEX_COMMA);
                    for (int i2 = 0; i2 < split.length; i2++) {
                        int parseInt = !TextUtils.isEmpty(split[i2]) ? Integer.parseInt(split[i2]) : 0;
                        if (i2 == 0) {
                            this.f12770g.a(parseInt);
                        } else if (i2 == 1) {
                            this.f12770g.b(parseInt);
                        } else if (i2 == 2) {
                            this.f12770g.c(parseInt);
                        } else if (i2 == 3) {
                            this.f12770g.e(parseInt);
                        } else if (i2 == 4) {
                            this.f12770g.d(parseInt);
                        }
                    }
                    return;
                }
                int parseInt2 = Integer.parseInt(optString);
                this.f12770g.a(parseInt2);
                this.f12770g.b(parseInt2);
                this.f12770g.c(parseInt2);
                this.f12770g.e(parseInt2);
                this.f12770g.d(parseInt2);
            }
        } catch (Exception unused) {
        }
    }

    public int a(String str) {
        if (!TextUtils.isEmpty(str) && !str.equalsIgnoreCase("unknown")) {
            if (str.equals("2g")) {
                return this.a.e();
            }
            if (str.equals("3g")) {
                return this.a.g();
            }
            if (str.equals("4g")) {
                return this.a.i();
            }
            if (str.equals("5g")) {
                return this.a.k();
            }
            if (str.equals("wifi")) {
                return this.a.z();
            }
            return this.a.b();
        }
        return this.a.b();
    }

    public int b(String str) {
        int b = this.f12770g.b();
        if (TextUtils.isEmpty(str)) {
            return b;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case R2.attr.re_pstsTabPaddingLeftRight /* 1653 */:
                if (str.equals("2g")) {
                    c2 = 0;
                    break;
                }
                break;
            case R2.attr.right2_text /* 1684 */:
                if (str.equals("3g")) {
                    c2 = 1;
                    break;
                }
                break;
            case R2.attr.roundwidth /* 1715 */:
                if (str.equals("4g")) {
                    c2 = 2;
                    break;
                }
                break;
            case R2.attr.select_indicator_height /* 1746 */:
                if (str.equals("5g")) {
                    c2 = 3;
                    break;
                }
                break;
            case 3649301:
                if (str.equals("wifi")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return this.f12770g.a();
            case 1:
                return this.f12770g.b();
            case 2:
                return this.f12770g.c();
            case 3:
                return this.f12770g.d();
            case 4:
                return this.f12770g.e();
            default:
                return b;
        }
    }

    public int c(String str) {
        if (!TextUtils.isEmpty(str) && !str.equalsIgnoreCase("unknown")) {
            if (str.equals("2g")) {
                return this.a.f();
            }
            if (str.equals("3g")) {
                return this.a.h();
            }
            if (str.equals("4g")) {
                return this.a.j();
            }
            if (str.equals("5g")) {
                return this.a.l();
            }
            if (str.equals("wifi")) {
                return this.a.A();
            }
            return this.a.c();
        }
        return this.a.c();
    }

    public String d() {
        String o = h().o();
        if (n.a().b()) {
            return o.replace("https://", "http://");
        }
        return o.replace("http://", "https://");
    }

    public f h() {
        return this.a;
    }

    public void k(String str) {
        String str2;
        String str3;
        String str4;
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (LogUtil.isDebug()) {
                str2 = "h5";
                str3 = "ret";
                StringBuilder sb = new StringBuilder();
                str4 = "wifiSz";
                sb.append("\u5f53\u524dApp\u7b56\u7565\u5185\u5bb9\uff1a");
                sb.append(str);
                LogUtil.d("StrategyParse", sb.toString());
            } else {
                str2 = "h5";
                str3 = "ret";
                str4 = "wifiSz";
            }
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("policyType")) {
                this.a.c(jSONObject.optString("policyType", "0"));
            }
            if (jSONObject.has("quickEnable")) {
                this.a.d(jSONObject.optString("quickEnable", "1"));
            }
            if (jSONObject.has("g2Int")) {
                this.a.d(jSONObject.optInt("g2Int", 15));
            }
            if (jSONObject.has("g2Sz")) {
                this.a.e(jSONObject.optInt("g2Sz", 10));
            }
            if (jSONObject.has("g3Int")) {
                this.a.f(jSONObject.optInt("g3Int", 15));
            }
            if (jSONObject.has("g3Sz")) {
                this.a.g(jSONObject.optInt("g3Sz", 10));
            }
            if (jSONObject.has("g4Int")) {
                this.a.h(jSONObject.optInt("g4Int", 15));
            }
            if (jSONObject.has("g4Sz")) {
                this.a.i(jSONObject.optInt("g4Sz", 10));
            }
            if (jSONObject.has("g5Int")) {
                this.a.j(jSONObject.optInt("g5Int", 15));
            }
            if (jSONObject.has("g5Sz")) {
                this.a.k(jSONObject.optInt("g5Sz", 10));
            }
            if (jSONObject.has("wifiInt")) {
                this.a.u(jSONObject.optInt("wifiInt", 15));
            }
            String str5 = str4;
            if (jSONObject.has(str5)) {
                this.a.v(jSONObject.optInt(str5, 10));
            }
            String str6 = str3;
            if (jSONObject.has(str6)) {
                this.a.s(jSONObject.optInt(str6, 1));
            }
            if (jSONObject.has("cyc")) {
                this.a.a(jSONObject.optInt("cyc", 10));
            }
            if (jSONObject.has("v")) {
                this.a.f(jSONObject.optString("v", "0.0.0"));
            }
            if (jSONObject.has("spd")) {
                this.a.t(jSONObject.optInt("spd"));
            }
            if (jSONObject.has("loopbtw")) {
                this.a.n(jSONObject.optInt("loopbtw", 2));
            }
            if (jSONObject.has("domain")) {
                this.a.a(jSONObject.optString("domain", Constant.STATISTIC_REPORT_DATA_DEFAULT_DOMAIN));
            }
            if (jSONObject.has("rules")) {
                this.a.e(jSONObject.optString("rules"));
            } else {
                this.a.e("");
            }
            if (jSONObject.has("defaultSz")) {
                this.a.c(jSONObject.optInt("defaultSz", 0));
            }
            if (jSONObject.has("defaultInt")) {
                this.a.b(jSONObject.optInt("defaultInt", 0));
            }
            h(jSONObject.optString("monitorSdk"));
            i(this.a.w());
            g(jSONObject.optString("logRules"));
            e(jSONObject.optString("failureRetry"));
            j(jSONObject.optString("sendLimit"));
            if (com.jingdong.jdma.f.c.d) {
                a(this.a.y(), TextUtils.isEmpty(this.b.b()) ? "default" : this.b.b());
            }
            if (jSONObject.has("httpdns")) {
                com.jingdong.jdma.d.a.d().a(jSONObject.optBoolean("httpdns"));
            }
            String str7 = str2;
            f(jSONObject.optString(str7));
            if (jSONObject.has("h5Url")) {
                n(jSONObject.optString("h5Url"));
            }
            if (jSONObject.has("randomSz") && (optJSONObject2 = jSONObject.optJSONObject("randomSz")) != null) {
                this.a.p(optJSONObject2.optInt("native", 25));
                this.a.m(optJSONObject2.optInt(str7, 1));
                com.jingdong.jdma.common.utils.c.x = this.a.q();
                com.jingdong.jdma.common.utils.c.y = this.a.n();
            }
            if (jSONObject.has("randomInt") && (optJSONObject = jSONObject.optJSONObject("randomInt")) != null) {
                this.a.o(optJSONObject.optInt("native", 60));
                this.a.l(optJSONObject.optInt(str7, 1));
                com.jingdong.jdma.common.utils.c.z = this.a.p();
                com.jingdong.jdma.common.utils.c.A = this.a.m();
            }
            if (jSONObject.has("randomCyc")) {
                this.a.q(jSONObject.optInt("randomCyc", 300));
                com.jingdong.jdma.common.utils.c.B = this.a.t();
            }
            if (jSONObject.has("randomIntAfterOneRange")) {
                this.a.r(jSONObject.optInt("randomIntAfterOneRange", 4));
                com.jingdong.jdma.common.utils.c.C = this.a.u();
            }
        } catch (Throwable unused) {
        }
    }

    public void l(String str) {
        this.b.f(str);
    }

    public void m(String str) {
        this.b.g(str);
    }

    public void n(String str) {
        this.a.b(str);
    }

    public synchronized void o(String str) {
        this.b.i(str);
    }

    public void p(String str) {
        this.a.a(str);
    }

    public void q(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.d = str;
    }

    public void r(String str) {
        this.b.j(str);
    }

    private void h(String str) {
        if (TextUtils.isEmpty(str)) {
            com.jingdong.jdma.f.c.d = false;
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("condition");
            String optString2 = jSONObject.optString("monitorUploadUrl");
            if (!TextUtils.isEmpty(optString2) && URLUtil.isValidUrl(optString2) && !TextUtils.isEmpty(optString) && this.b.a(optString)) {
                com.jingdong.jdma.f.c.d = true;
                com.jingdong.jdma.f.c.a().c(optString2);
                if (LogUtil.isDebug()) {
                    LogUtil.d("--------canMonitor=" + com.jingdong.jdma.f.c.d + ",----monitorUploadUrl=" + optString2);
                }
            } else {
                com.jingdong.jdma.f.c.d = false;
                com.jingdong.jdma.f.c.a().c("");
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public boolean d(String str) {
        com.jingdong.jdma.bean.e.d dVar = this.f12768e;
        if (dVar == null || TextUtils.isEmpty(dVar.a()) || this.f12768e.b() == null) {
            return false;
        }
        this.b.h(str);
        return this.b.a(this.f12768e.a());
    }

    public com.jingdong.jdma.bean.e.a b() {
        return this.f12769f;
    }

    public com.jingdong.jdma.bean.e.d e() {
        return this.f12768e;
    }

    private void a() {
        if (this.a.e() > this.f12767c) {
            this.f12767c = this.a.e();
        }
        if (this.a.g() > this.f12767c) {
            this.f12767c = this.a.g();
        }
        if (this.a.i() > this.f12767c) {
            this.f12767c = this.a.i();
        }
        if (this.a.k() > this.f12767c) {
            this.f12767c = this.a.k();
        }
        if (this.a.z() > this.f12767c) {
            this.f12767c = this.a.z();
        }
        if (this.a.b() > this.f12767c) {
            this.f12767c = this.a.b();
        }
    }

    public com.jingdong.jdma.bean.e.b c() {
        return this.f12771h;
    }

    public void j() {
        this.a.C();
        this.f12768e.c();
        this.f12770g.g();
        this.f12771h.l();
    }

    private void a(String str, String str2) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("policyVersion", str);
        hashMap.put("policyExpression", str2);
        hashMap.put("commonNum", com.jingdong.jdma.common.utils.c.f12674c + "");
        hashMap.put("quickNum", com.jingdong.jdma.common.utils.c.d + "");
        hashMap.put("dauNum", com.jingdong.jdma.common.utils.c.f12675e + "");
        com.jingdong.jdma.f.c.a().b("policyUpdate", hashMap);
    }

    public String i() {
        return (n.a().b() ? "http://" : "https://").concat(this.d).concat("/m/log/v2");
    }

    public String g() {
        return (n.a().b() ? "http://" : "https://").concat(h().d()).concat("/log/sdk");
    }

    public int f() {
        return this.f12767c;
    }
}
