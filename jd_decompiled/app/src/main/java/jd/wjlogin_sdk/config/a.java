package jd.wjlogin_sdk.config;

import android.text.TextUtils;
import android.util.Pair;
import com.jd.dynamic.DYConstants;
import java.net.URLEncoder;
import jd.wjlogin_sdk.common.c;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.net.b;
import jd.wjlogin_sdk.net.d;
import jd.wjlogin_sdk.util.b0;
import jd.wjlogin_sdk.util.e;
import jd.wjlogin_sdk.util.g;
import jd.wjlogin_sdk.util.l;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.v;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a {

    /* renamed from: c  reason: collision with root package name */
    private static final String f19804c = "WJLogin.LoginConfig";
    public static final String d = "wjlogin_ccf_config";

    /* renamed from: e  reason: collision with root package name */
    private static final String f19805e = "configVer";

    /* renamed from: f  reason: collision with root package name */
    private static final String f19806f = "syncIntvl";

    /* renamed from: g  reason: collision with root package name */
    private static final String f19807g = "httpSwitch";

    /* renamed from: h  reason: collision with root package name */
    private static final String f19808h = "stov2";

    /* renamed from: i  reason: collision with root package name */
    private static final String f19809i = "uri";

    /* renamed from: j  reason: collision with root package name */
    private static final String f19810j = "useNewEncryptSwitch";

    /* renamed from: k  reason: collision with root package name */
    private static final String f19811k = "useHttpDNSSwitch";

    /* renamed from: l  reason: collision with root package name */
    private static final String f19812l = "deleteParamsSwitch";

    /* renamed from: m  reason: collision with root package name */
    private static final String f19813m = "guardSignSwitch";
    private long a;
    private JSONObject b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: jd.wjlogin_sdk.config.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0847a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ OnCommonCallback f19814c;

        RunnableC0847a(String str, String str2, OnCommonCallback onCommonCallback) {
            this.a = str;
            this.b = str2;
            this.f19814c = onCommonCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                JSONObject a = a.this.a(this.a, this.b);
                String a2 = jd.wjlogin_sdk.util.b.a(a.toString(), 2);
                StringBuilder sb = new StringBuilder();
                if (b0.b().length() > 0) {
                    sb.append(b0.b());
                    sb.append(a2);
                } else {
                    sb.append("hykebyIld");
                    sb.append(a2);
                }
                if (p.b) {
                    p.b(a.f19804c, "ccf login request = " + l.a(a));
                    p.b(a.f19804c, "ccf login request base64 = " + sb.toString());
                }
                Pair<Integer, byte[]> b = new b.C0849b().a(jd.wjlogin_sdk.util.e0.b.a()).a(URLEncoder.encode(sb.toString(), "UTF-8").getBytes()).a().b();
                if (b != null && ((Integer) b.first).intValue() == 200) {
                    a.this.a(new String((byte[]) b.second));
                }
                if (a.this.l()) {
                    c.f();
                }
                OnCommonCallback onCommonCallback = this.f19814c;
                if (onCommonCallback != null) {
                    onCommonCallback.onSuccess();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {
        static final a a = new a(null);

        b() {
        }
    }

    /* synthetic */ a(RunnableC0847a runnableC0847a) {
        this();
    }

    public static a c() {
        return b.a;
    }

    private int d() {
        JSONObject jSONObject = this.b;
        int optInt = jSONObject != null ? jSONObject.optInt(f19806f, 0) : 0;
        if (p.b) {
            p.b(f19804c, "syncDt = " + optInt);
        }
        return optInt;
    }

    private void f() {
        String e2 = v.e(d);
        try {
            if (TextUtils.isEmpty(e2)) {
                return;
            }
            this.b = new JSONObject(e2);
            if (p.b) {
                p.b(f19804c, "initial local config = " + l.a(this.b));
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void b(String str, String str2) {
        a(str, str2, (OnCommonCallback) null);
    }

    public String[] e() {
        JSONObject jSONObject = this.b;
        String optString = jSONObject != null ? jSONObject.optString(f19809i, "") : null;
        if (p.b) {
            p.b(f19804c, "config authrity = " + optString);
        }
        if (TextUtils.isEmpty(optString)) {
            return null;
        }
        return optString.split(DYConstants.DY_REGEX_COMMA);
    }

    public boolean g() {
        JSONObject jSONObject = this.b;
        boolean z = jSONObject != null && jSONObject.optInt(f19807g, 0) == 1;
        if (p.b) {
            p.b(f19804c, "openHttp = " + z);
        }
        return z;
    }

    public boolean h() {
        JSONObject jSONObject = this.b;
        boolean z = jSONObject != null && jSONObject.optInt(f19812l, 0) == 1;
        if (p.b) {
            p.b(f19804c, "deleteParamsSwitch = " + z);
        }
        return z;
    }

    public boolean i() {
        JSONObject jSONObject = this.b;
        boolean z = true;
        if (jSONObject != null && jSONObject.optInt(f19813m, 1) != 1) {
            z = false;
        }
        if (p.b) {
            p.b(f19804c, "guardSignSwitch = " + z);
        }
        return z;
    }

    public boolean j() {
        JSONObject jSONObject = this.b;
        boolean z = true;
        if (jSONObject != null && jSONObject.optInt(f19808h, 1) != 1) {
            z = false;
        }
        if (p.b) {
            p.b(f19804c, "openFileStore = " + z);
        }
        return z;
    }

    public boolean k() {
        JSONObject jSONObject = this.b;
        boolean z = jSONObject != null && jSONObject.optInt(f19811k, 0) == 1;
        if (p.b) {
            p.b(f19804c, "useHttpDNSSwitch = " + z);
        }
        return z;
    }

    public boolean l() {
        JSONObject jSONObject = this.b;
        boolean z = true;
        if (jSONObject != null && jSONObject.optInt(f19810j, 1) != 1) {
            z = false;
        }
        if (p.b) {
            p.b(f19804c, "isUseNewEncrypt = " + z);
        }
        return z;
    }

    private a() {
        this.a = 0L;
        f();
    }

    private String b() {
        JSONObject jSONObject = this.b;
        String optString = jSONObject != null ? jSONObject.optString(f19805e, "") : "";
        if (p.b) {
            p.b(f19804c, "configVer = " + optString);
        }
        return optString;
    }

    public JSONObject a() {
        return this.b;
    }

    public void a(String str, String str2, OnCommonCallback onCommonCallback) {
        if (System.currentTimeMillis() - this.a < d() * 1000) {
            if (p.b) {
                p.b(f19804c, "during cached time");
            }
            if (this.b == null || onCommonCallback == null) {
                return;
            }
            onCommonCallback.onSuccess();
            return;
        }
        this.a = System.currentTimeMillis();
        try {
            d.a().a(new RunnableC0847a(str, str2, onCommonCallback));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject a(String str, String str2) {
        String str3 = "";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("udid", "");
            String d2 = TextUtils.isEmpty(str) ? "" : jd.wjlogin_sdk.util.b.d(str);
            if (p.b) {
                p.b(f19804c, "pin = " + str + " pin2 = " + d2);
            }
            if (g.d() != null && jd.wjlogin_sdk.common.h.a.g() != null) {
                str3 = jd.wjlogin_sdk.common.h.a.g();
            }
            jSONObject.put("pin", d2);
            jSONObject.put("client", "android");
            jSONObject.put("boundId", g.b(jd.wjlogin_sdk.common.b.a()));
            jSONObject.put("appVer", g.d(jd.wjlogin_sdk.common.b.a()));
            jSONObject.put("sdkVer", e.a);
            jSONObject.put("osVer", str3);
            jSONObject.put(f19805e, b());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        try {
            if (p.b) {
                p.b(f19804c, "use remote config = " + l.a(str));
            }
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() == 0) {
                if (p.b) {
                    p.b(f19804c, "remote is empty");
                    return;
                }
                return;
            }
            this.b = jSONObject;
            v.a(d, jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
