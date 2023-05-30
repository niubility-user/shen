package performance.jd.jdreportperformance.e;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.mapsdk.internal.l4;
import java.io.File;
import org.json.JSONObject;
import performance.jd.jdreportperformance.b.b.d;
import performance.jd.jdreportperformance.minterface.InitInformation;

/* loaded from: classes.dex */
public class a {
    private static int r;
    private static volatile a s;
    private String b;

    /* renamed from: c */
    private String f20449c;
    private String d;

    /* renamed from: e */
    private String f20450e;

    /* renamed from: f */
    private String f20451f;

    /* renamed from: g */
    private String f20452g;

    /* renamed from: h */
    private String f20453h;

    /* renamed from: i */
    private String f20454i;

    /* renamed from: j */
    private String f20455j;

    /* renamed from: k */
    private String f20456k;

    /* renamed from: l */
    private String f20457l;

    /* renamed from: m */
    private String f20458m;

    /* renamed from: n */
    private String f20459n;
    private String q;
    private String a = "";
    private String o = "";
    private String p = "";

    /* renamed from: performance.jd.jdreportperformance.e.a$a */
    /* loaded from: classes.dex */
    public static class RunnableC0858a implements Runnable {
        final /* synthetic */ Context a;

        RunnableC0858a(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Context context = this.a;
            if (context == null) {
                return;
            }
            try {
                String str = context.getApplicationInfo().nativeLibraryDir;
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                File file = new File(str);
                if (file.exists()) {
                    String name = file.getName();
                    performance.jd.jdreportperformance.b.b.b.a("CommonInfo", "abi type : " + name);
                    File[] listFiles = file.listFiles();
                    if (!TextUtils.equals(name, "arm64") || listFiles == null || listFiles.length <= 0) {
                        return;
                    }
                    int unused = a.r = 1;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private a() {
        this.b = "";
        this.f20449c = "";
        this.d = "";
        this.f20450e = "";
        this.f20451f = "";
        this.f20452g = "";
        this.f20453h = "";
        this.f20454i = "";
        this.f20455j = "";
        this.f20456k = "";
        this.f20457l = "";
        this.f20458m = "";
        this.f20459n = "";
        this.q = "";
        this.b = "";
        this.f20449c = "";
        this.d = "";
        this.f20450e = a(BaseInfo.getDeviceModel(), 12);
        this.f20451f = d();
        this.f20452g = Build.VERSION.RELEASE;
        this.f20453h = "android";
        this.f20454i = "";
        this.f20455j = "";
        this.f20456k = "";
        this.f20457l = "4";
        this.f20458m = performance.jd.jdreportperformance.b.b.a.b();
        this.f20459n = performance.jd.jdreportperformance.b.b.c.a(this.f20458m + "5YT%aC89$22OI@pQ");
        this.q = "";
        b();
    }

    private static void b() {
        performance.jd.jdreportperformance.d.c.b().a(new RunnableC0858a(performance.jd.jdreportperformance.a.b().a()));
    }

    public static a c() {
        if (s == null) {
            synchronized (a.class) {
                if (s == null) {
                    s = new a();
                }
            }
        }
        return s;
    }

    private static String d() {
        return TextUtils.equals("harmony", Class.forName("com.huawei.system.BuildEx").getMethod("getOsBrand", new Class[0]).invoke(null, new Object[0]).toString()) ? "harmony" : "android";
    }

    public void a(InitInformation initInformation) {
        if (initInformation != null) {
            this.b = initInformation.env;
            this.f20449c = initInformation.pin;
            this.d = initInformation.guid;
            this.f20454i = initInformation.appVersion;
            this.f20455j = initInformation.harmonyVersion;
            this.f20456k = initInformation.build;
            this.a = initInformation.appId;
            this.p = initInformation.deviceManufacture;
            this.o = initInformation.screenInfo;
            performance.jd.jdreportperformance.b.b.b.a(initInformation.logLevel);
            this.q = initInformation.userModel + "";
        }
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appId", this.a);
            jSONObject.put("env", this.b);
            jSONObject.put("accountId", this.f20449c);
            jSONObject.put("machineCode", this.d);
            jSONObject.put("machineType", this.f20450e);
            jSONObject.put("os", this.f20451f);
            jSONObject.put(HybridSDK.OS_VERSION, this.f20452g);
            jSONObject.put("app", this.f20453h);
            jSONObject.put("appVersion", this.f20454i);
            jSONObject.put("harmonyVersion", this.f20455j);
            jSONObject.put(HybridSDK.APP_VERSION_CODE, this.f20456k);
            Context a = performance.jd.jdreportperformance.a.b().a();
            if (a != null) {
                jSONObject.put("net", d.c(a));
            } else {
                performance.jd.jdreportperformance.b.b.b.a("CommonInfo", "context is null");
            }
            jSONObject.put(l4.f16791e, this.f20457l);
            jSONObject.put("curTime", this.f20458m);
            jSONObject.put("token", this.f20459n);
            jSONObject.put("screen", this.o);
            jSONObject.put(HybridSDK.D_BRAND, this.p);
            jSONObject.put("abiType", "" + r);
            jSONObject.put("curStrategyId", c.a().f20463f);
            jSONObject.put("userModel", this.q);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private static String a(String str, int i2) {
        if (str != null) {
            try {
                return str.length() > i2 ? str.substring(0, i2) : str;
            } catch (Exception e2) {
                e2.printStackTrace();
                return str;
            }
        }
        return str;
    }
}
