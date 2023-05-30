package com.jd.security.jdguard.d.d.d;

import android.content.Context;
import android.os.Debug;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.jd.dynamic.DYConstants;
import com.jd.security.jdguard.d.c.d;
import com.jd.security.jdguard.d.c.f;
import com.jd.security.jdguard.f.e;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.connect.common.Constants;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes17.dex */
public class c extends com.jd.security.jdguard.d.d.a {

    /* renamed from: e */
    private static c f6933e;

    private void A(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        String str = "JEN";
        try {
            if (cVar.o("shua")) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("1", b.p(context) ? 1 : 0);
                String h2 = b.h(context);
                if (TextUtils.isEmpty(h2)) {
                    h2 = "JEN";
                }
                jSONObject2.put("2", h2);
                String n2 = cVar.n("shua", "check");
                StringBuilder sb = new StringBuilder();
                if (!TextUtils.isEmpty(n2)) {
                    for (String str2 : n2.split(DYConstants.DY_REGEX_COMMA)) {
                        boolean isPkgInstalled = BaseInfo.isPkgInstalled(context, str2);
                        Object[] objArr = new Object[2];
                        objArr[0] = str2;
                        objArr[1] = Integer.valueOf(isPkgInstalled ? 1 : 0);
                        sb.append(String.format("%s(%s)", objArr));
                    }
                }
                String sb2 = sb.toString();
                if (!TextUtils.isEmpty(sb2)) {
                    str = sb2;
                }
                jSONObject2.put("3", str);
                jSONObject.put("15", jSONObject2);
                return;
            }
            jSONObject.put("15", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void B(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            if (cVar.o("sk")) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("1", dVar.c());
                jSONObject2.put("2", b.k(context));
                Pair<Boolean, String> q = b.q(context, context.getApplicationInfo().dataDir.toString());
                jSONObject2.put("3", ((Boolean) q.first).booleanValue() ? 1 : 0);
                jSONObject2.put("4", q.second);
                jSONObject2.put("5", b.m(context));
                jSONObject.put("1", jSONObject2);
                return;
            }
            jSONObject.put("1", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private String C(JSONObject jSONObject) throws Throwable {
        return (jSONObject == null || TextUtils.isEmpty(jSONObject.toString())) ? "" : Base64.encodeToString(com.jd.security.jdguard.b.b(com.jd.security.jdguard.f.a.c(jSONObject.toString().getBytes())), 2);
    }

    private void D(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            if (cVar.o("uni")) {
                JSONObject jSONObject2 = new JSONObject();
                String a = dVar.a();
                if (TextUtils.isEmpty(a)) {
                    a = "JEN";
                }
                jSONObject2.put("1", a);
                jSONObject.put("16", jSONObject2);
                return;
            }
            jSONObject.put("16", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void E(final int i2, final String str) {
        com.jd.security.jdguard.d.d.b bVar = this.a;
        f fVar = bVar.a;
        final com.jd.security.jdguard.d.c.c cVar = (com.jd.security.jdguard.d.c.c) fVar;
        if (fVar == null || bVar.d == null || !fVar.enable() || System.currentTimeMillis() - cVar.v() <= cVar.u() * 60 * 1000) {
            return;
        }
        this.a.d.schedule(new Runnable() { // from class: com.jd.security.jdguard.d.d.d.a
            {
                c.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                c.this.v(str, i2, cVar);
            }
        }, cVar.t(), TimeUnit.SECONDS);
    }

    private void F(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            if (cVar.o("usb")) {
                JSONObject jSONObject2 = new JSONObject();
                int u = b.u(context);
                int i2 = 1;
                if (u != 2) {
                    i2 = u == 1 ? 2 : u == 4 ? 3 : 0;
                }
                jSONObject2.put("1", i2);
                jSONObject2.put("2", b.j(context));
                jSONObject.put("5", jSONObject2);
                return;
            }
            jSONObject.put("5", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void G(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            if (cVar.o("vpn")) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("1", b.c());
                jSONObject.put("6", jSONObject2);
                return;
            }
            jSONObject.put("6", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void H(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            if (cVar.o("xp")) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("1", b.e(context) ? 1 : 0);
                if (cVar.p("xp", "cd")) {
                    String e2 = dVar.e("0");
                    jSONObject2.put("2", TextUtils.isEmpty(e2) ? "JEN" : e2);
                }
                jSONObject.put("9", jSONObject2);
                return;
            }
            jSONObject.put("9", "JNOP");
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
    }

    private void m(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            if (cVar.o("api")) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("1", b.i(context));
                jSONObject.put("8", jSONObject2);
                return;
            }
            jSONObject.put("8", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void n(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("1", "3.1.1");
            jSONObject2.put("2", "Android");
            jSONObject.put("0", jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void o(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            if (cVar.o("db")) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("1", Integer.parseInt(dVar.i()));
                jSONObject2.put("2", Debug.isDebuggerConnected() ? 1 : 0);
                jSONObject.put("11", jSONObject2);
                return;
            }
            jSONObject.put("11", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void p(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            if (cVar.o("dm")) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("1", b.v(context) ? 1 : 0);
                jSONObject.put("4", jSONObject2);
                return;
            }
            jSONObject.put("4", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void q(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            if (cVar.o("eml")) {
                JSONObject jSONObject2 = new JSONObject();
                String g2 = dVar.g();
                if (TextUtils.isEmpty(g2)) {
                    g2 = "JEN";
                }
                jSONObject2.put("1", g2);
                jSONObject.put("13", jSONObject2);
                return;
            }
            jSONObject.put("13", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void r(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            if (cVar.o(IMediaPlayer.OnNativeInvokeListener.ARG_FD)) {
                JSONObject jSONObject2 = new JSONObject();
                String b = dVar.b();
                if (TextUtils.isEmpty(b)) {
                    b = "JEN";
                }
                jSONObject2.put("1", b);
                jSONObject.put("12", jSONObject2);
                return;
            }
            jSONObject.put("12", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static c s() {
        if (f6933e == null) {
            synchronized (c.class) {
                if (f6933e == null) {
                    f6933e = new c();
                }
            }
        }
        return f6933e;
    }

    private void t(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            if (cVar.o("ip")) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("1", new JSONArray(b.l()));
                jSONObject.put("3", jSONObject2);
                return;
            }
            jSONObject.put("3", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: u */
    public /* synthetic */ void v(String str, int i2, com.jd.security.jdguard.d.c.c cVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("packageName", BaseInfo.getAppPackageName());
            jSONObject.putOpt("eid", this.a.f6925f);
            jSONObject.putOpt("version", BaseInfo.getAppVersionName());
            jSONObject.putOpt(Constants.PARAM_PLATFORM, "0");
            jSONObject.putOpt("pin", com.jd.security.jdguard.d.d.e.c.b());
            jSONObject.putOpt("ts", "" + System.currentTimeMillis());
            jSONObject.putOpt("data", str);
            jSONObject.putOpt("type", "" + i2);
            jSONObject.putOpt("sid", "jdg");
            jSONObject.putOpt("business", "jdgde");
            e.b("https://waapdg.jd.com/api/v1/jdguard/gather", C(jSONObject));
            cVar.s(System.currentTimeMillis());
        } catch (Throwable unused) {
        }
    }

    private void w(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            if (cVar.o("lc")) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("1", b.r(context) ? 1 : 0);
                jSONObject.put("2", jSONObject2);
                return;
            }
            jSONObject.put("2", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void x(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            if (cVar.o("pxy")) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("1", b.x());
                jSONObject.put("7", jSONObject2);
                return;
            }
            jSONObject.put("7", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void y(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        String str = "JEN";
        try {
            if (cVar.o("qk")) {
                JSONObject jSONObject2 = new JSONObject();
                String o = b.o();
                if (TextUtils.isEmpty(o)) {
                    o = "JEN";
                }
                jSONObject2.put("1", o);
                if (cVar.p("qk", "arp")) {
                    String d = dVar.d(3);
                    if (TextUtils.isEmpty(d)) {
                        d = b.a();
                    }
                    if (TextUtils.isEmpty(d)) {
                        d = "JEN";
                    }
                    jSONObject2.put("2", d);
                }
                if (cVar.p("qk", "adb")) {
                    String d2 = dVar.d(1);
                    if (TextUtils.isEmpty(d2)) {
                        d2 = "JEN";
                    }
                    jSONObject2.put("3", d2);
                }
                if (cVar.p("qk", "cmdl")) {
                    String d3 = dVar.d(2);
                    if (!TextUtils.isEmpty(d3)) {
                        str = d3;
                    }
                    jSONObject2.put("4", str);
                }
                jSONObject.put("14", jSONObject2);
                return;
            }
            jSONObject.put("14", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void z(Context context, com.jd.security.jdguard.d.c.c cVar, d dVar, JSONObject jSONObject) {
        try {
            if (cVar.o("rt")) {
                JSONObject jSONObject2 = new JSONObject();
                String f2 = dVar.f();
                if (TextUtils.isEmpty(f2)) {
                    f2 = "JEN";
                }
                jSONObject2.put("1", f2);
                String h2 = dVar.h();
                if (h2.isEmpty()) {
                    jSONObject2.put("2", "JEN");
                } else {
                    String[] split = h2.split("\u00a7\u00a7");
                    if (split != null && split.length != 0) {
                        jSONObject2.put("2", h2);
                    }
                    jSONObject2.put("2", "JEN");
                }
                jSONObject.put("10", jSONObject2);
                return;
            }
            jSONObject.put("10", "JNOP");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.security.jdguard.d.d.a
    public String d() {
        return null;
    }

    @Override // com.jd.security.jdguard.d.d.a
    protected void f(Context context, f fVar, d dVar, Object obj) {
        com.jd.security.jdguard.d.c.c cVar = (com.jd.security.jdguard.d.c.c) fVar;
        JSONObject jSONObject = (JSONObject) obj;
        n(context, cVar, dVar, jSONObject);
        y(context, cVar, dVar, jSONObject);
        B(context, cVar, dVar, jSONObject);
        A(context, cVar, dVar, jSONObject);
        w(context, cVar, dVar, jSONObject);
        H(context, cVar, dVar, jSONObject);
        m(context, cVar, dVar, jSONObject);
        t(context, cVar, dVar, jSONObject);
        F(context, cVar, dVar, jSONObject);
        p(context, cVar, dVar, jSONObject);
        G(context, cVar, dVar, jSONObject);
        x(context, cVar, dVar, jSONObject);
        q(context, cVar, dVar, jSONObject);
        r(context, cVar, dVar, jSONObject);
        z(context, cVar, dVar, jSONObject);
        o(context, cVar, dVar, jSONObject);
        D(context, cVar, dVar, jSONObject);
    }

    @Override // com.jd.security.jdguard.d.d.a
    public void h(com.jd.security.jdguard.d.d.c cVar, int i2, String str) {
        super.h(cVar, i2, str);
        try {
            E(i2, str);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jd.security.jdguard.d.d.a
    protected String j(Object obj) {
        if (obj != null) {
            return ((JSONObject) obj).toString();
        }
        return null;
    }

    @Override // com.jd.security.jdguard.d.d.a
    protected Object k() {
        return new JSONObject();
    }
}
