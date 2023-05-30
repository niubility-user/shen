package jd.wjlogin_sdk.common;

import android.text.TextUtils;
import com.jingdong.jdsdk.config.Configuration;
import jd.wjlogin_sdk.model.ClientInfo;
import jd.wjlogin_sdk.util.p;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class e {
    private int a;
    private short b;

    /* renamed from: c */
    private String f19755c;
    private String d;

    /* renamed from: e */
    private String f19756e;

    /* renamed from: f */
    private String f19757f;

    /* renamed from: g */
    private String f19758g;

    /* renamed from: h */
    private String f19759h;

    /* renamed from: i */
    private String f19760i;

    /* renamed from: j */
    private String f19761j;

    /* renamed from: k */
    private String f19762k;

    /* renamed from: l */
    private String f19763l;

    /* renamed from: m */
    private String f19764m;

    /* renamed from: n */
    private String f19765n;
    private long o;
    private long p;

    public e(ClientInfo clientInfo) {
        this.a = 1;
        this.f19755c = "";
        this.d = "";
        this.f19756e = "";
        this.f19757f = "";
        this.f19758g = "";
        this.f19759h = "";
        this.f19760i = "";
        this.f19761j = "";
        this.f19762k = "";
        this.f19763l = "";
        this.f19764m = "";
        this.f19765n = "";
        this.o = 0L;
        this.p = 0L;
        try {
            this.f19756e = "android";
            this.f19759h = clientInfo.getAppName() == null ? "" : clientInfo.getAppName();
            this.b = clientInfo.getDwAppID();
            this.a = 1;
            this.f19760i = clientInfo.getDeviceBrand() == null ? "" : clientInfo.getDeviceBrand();
            this.f19761j = clientInfo.getDeviceModel() == null ? "" : clientInfo.getDeviceModel();
            this.f19762k = clientInfo.getDeviceName() == null ? "" : clientInfo.getDeviceName();
            this.f19757f = clientInfo.getOsVer() == null ? "" : clientInfo.getOsVer();
            this.f19758g = clientInfo.getScreen() == null ? "" : clientInfo.getScreen();
            String unionId = clientInfo.getUnionId();
            String subunionId = clientInfo.getSubunionId();
            String partner = clientInfo.getPartner();
            if (!TextUtils.isEmpty(unionId)) {
                this.f19764m = unionId;
            }
            if (!TextUtils.isEmpty(subunionId)) {
                this.f19765n = subunionId;
            }
            if (!TextUtils.isEmpty(partner)) {
                this.f19763l = partner;
            }
            this.f19755c = jd.wjlogin_sdk.util.g.d(b.a());
            this.d = "" + jd.wjlogin_sdk.util.g.c(b.a());
            this.o = clientInfo.getFristInstallTime();
            this.p = clientInfo.getLastUpdateTime();
        } catch (Exception e2) {
            p.a("SDKBaseInfo exception: ", e2);
        }
    }

    public void a(int i2) {
        this.a = i2;
    }

    public void b(String str) {
        this.d = str;
    }

    public void c(String str) {
        this.f19755c = str;
    }

    public void d(String str) {
        this.f19756e = str;
    }

    public void e(String str) {
        this.f19760i = str;
    }

    public void f(String str) {
        this.f19761j = str;
    }

    public void g(String str) {
        this.f19762k = str;
    }

    public void h(String str) {
        this.f19757f = str;
    }

    public void i(String str) {
        this.f19763l = str;
    }

    public void j(String str) {
        this.f19758g = str;
    }

    public void k(String str) {
        this.f19765n = str;
    }

    public void l(String str) {
        this.f19764m = str;
    }

    public String m() {
        return this.f19763l;
    }

    public String n() {
        return this.f19758g;
    }

    public String o() {
        return this.f19765n;
    }

    public String p() {
        return this.f19764m;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appName", this.f19759h);
            jSONObject.put("dwAppID", (int) this.b);
            jSONObject.put(Configuration.UNION_ID, this.f19764m);
            jSONObject.put(Configuration.SUB_UNION_ID, this.f19765n);
            jSONObject.put("dwGetSig", this.a);
            jSONObject.put("clientType", this.f19756e);
            jSONObject.put("appVersionName", this.f19755c);
            jSONObject.put("screen", this.f19758g);
            jSONObject.put("osVer", this.f19757f);
            jSONObject.put("DeviceBrand", this.f19760i);
            jSONObject.put("DeviceModel", this.f19761j);
            jSONObject.put("DeviceName", this.f19762k);
            jSONObject.put("appVerionCode", this.d);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public void a(short s) {
        this.b = s;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.f19755c;
    }

    public String d() {
        return this.f19756e;
    }

    public String e() {
        return this.f19760i;
    }

    public String f() {
        return this.f19761j;
    }

    public String g() {
        return this.f19762k;
    }

    public short h() {
        if (p.b) {
            p.b("WJLogin.SDKBaseInfo", "dwAppID=" + ((int) this.b));
        }
        return this.b;
    }

    public int i() {
        return this.a;
    }

    public long j() {
        return this.o;
    }

    public long k() {
        return this.p;
    }

    public String l() {
        return this.f19757f;
    }

    public void a(String str) {
        this.f19759h = str;
    }

    public void b(long j2) {
        this.p = j2;
    }

    public String a() {
        return this.f19759h;
    }

    public void a(long j2) {
        this.o = j2;
    }
}
