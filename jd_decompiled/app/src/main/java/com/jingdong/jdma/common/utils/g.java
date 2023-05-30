package com.jingdong.jdma.common.utils;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class g {
    public String B;
    public String C;
    public String D;
    private Context E;
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f12697c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f12698e;

    /* renamed from: f  reason: collision with root package name */
    public String f12699f;

    /* renamed from: g  reason: collision with root package name */
    public String f12700g;

    /* renamed from: h  reason: collision with root package name */
    public String f12701h;

    /* renamed from: i  reason: collision with root package name */
    public String f12702i;

    /* renamed from: j  reason: collision with root package name */
    public String f12703j;

    /* renamed from: k  reason: collision with root package name */
    public String f12704k;

    /* renamed from: l  reason: collision with root package name */
    public String f12705l;

    /* renamed from: m  reason: collision with root package name */
    public String f12706m;

    /* renamed from: n  reason: collision with root package name */
    public String f12707n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;
    public String t;
    public String u;
    public String v;
    public String w;
    public String x;
    public String y;
    private String z = "0|";
    public volatile String A = "0|";

    public g(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.E = applicationContext;
        if (applicationContext == null) {
            this.E = context;
        }
        a();
        c();
    }

    private void a() {
        String a;
        Context context = this.E;
        if (context == null || l.a(context).a("advert_version")) {
            return;
        }
        if (j.b(this.E)) {
            a = f.b(this.E, "advert.info");
        } else {
            a = f.a(this.E, "advertinfo");
        }
        if (TextUtils.isEmpty(a)) {
            return;
        }
        Map<String, String> a2 = f.a(a);
        l a3 = l.a(this.E);
        a3.b("advert_version", "1.0");
        a3.b("jda", a2.get("jda"));
        a3.b("jda_ts", a2.get("jda_ts"));
        a3.b("jdv", a2.get("jdv"));
        a3.b("usc", a2.get("usc"));
        a3.b("ucp", a2.get("ucp"));
        a3.b("umd", a2.get("umd"));
        a3.b("utr", a2.get("utr"));
        a3.b("adk", a2.get("adk"));
        a3.b("ad_sc_value", a2.get("ad_sc_value"));
        a3.b("ext_unpl", a2.get("ext_unpl"));
        a3.b("inner_unpl", a2.get("inner_unpl"));
        a3.b("mba_muid", a2.get("mba_muid"));
        a3.b("mba_sid", a2.get("mba_sid"));
        a3.b("m_source", a2.get("m_source"));
    }

    private void c() {
        Context context = this.E;
        if (context == null) {
            return;
        }
        l a = l.a(context);
        this.f12699f = a.a("jda", "");
        this.f12700g = a.a("jda_ts", "");
        this.A = a.a("jdv", "");
        this.f12704k = a.a("usc", "");
        this.f12705l = a.a("ucp", "");
        this.f12706m = a.a("umd", "");
        this.f12707n = a.a("utr", "");
        this.o = a.a("adk", "");
        this.p = a.a("ad_sc_value", "");
        this.w = a.a("ext_unpl", "");
        this.x = a.a("inner_unpl", "");
        this.a = a.a("mba_muid", "");
        this.b = a.a("mba_sid", "");
        this.f12697c = a.a("m_source", "");
        b();
    }

    public void b(String str, String str2) {
        this.d = str;
        this.f12698e = str2;
    }

    public void d(String str) {
        String stringBuffer;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
        int indexOf = str.indexOf("|");
        StringBuffer stringBuffer2 = new StringBuffer();
        if (-1 != indexOf) {
            String[] split = str.split(DYConstants.DY_REGEX_VERTICAL_LINE, -1);
            String str2 = (split.length <= 0 || !"-1".equals(split[0])) ? "0" : "-1";
            if (split.length > 6) {
                stringBuffer2.append(str2);
                stringBuffer2.append("|");
                stringBuffer2.append(split[1]);
                stringBuffer2.append("|");
                stringBuffer2.append(split[2]);
                stringBuffer2.append("|");
                stringBuffer2.append(split[3]);
                stringBuffer2.append("|");
                stringBuffer2.append(split[4]);
                stringBuffer2.append("|");
                stringBuffer2.append(split[5]);
                stringBuffer2.append("|");
                stringBuffer2.append(valueOf);
                stringBuffer = stringBuffer2.toString();
            } else {
                stringBuffer2.append(str2);
                stringBuffer2.append(str.substring(indexOf));
                stringBuffer2.append("|");
                stringBuffer2.append(valueOf);
                stringBuffer = stringBuffer2.toString();
            }
        } else {
            stringBuffer2.append(str);
            stringBuffer2.append("|");
            stringBuffer2.append(valueOf);
            stringBuffer = stringBuffer2.toString();
        }
        this.A = stringBuffer;
        a("jdv", this.A);
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (LogUtil.isDebug()) {
                LogUtil.d("setExternalMPageParam", "----jsonStr=" + str);
            }
            this.r = "0";
            JSONObject jSONObject = new JSONObject(str);
            this.f12699f = jSONObject.optString("jda");
            this.f12701h = jSONObject.optString("psn");
            this.f12702i = jSONObject.optString("psq");
            this.B = jSONObject.optString("std");
            this.f12703j = jSONObject.optString("ref");
            this.C = jSONObject.optString("par");
            this.D = jSONObject.optString("event_id");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void f(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (LogUtil.isDebug()) {
                LogUtil.d("setInternalMPageParam", "----jsonStr=" + str);
            }
            this.r = "1";
            JSONObject jSONObject = new JSONObject(str);
            this.f12699f = jSONObject.optString("jda");
            this.f12701h = jSONObject.optString("psn");
            this.f12702i = jSONObject.optString("psq");
            this.B = jSONObject.optString("std");
            this.f12703j = jSONObject.optString("ref");
            this.C = jSONObject.optString("par");
            this.D = jSONObject.optString("event_id");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void g(String str) {
        if (str == null) {
            return;
        }
        this.r = "1";
        a(str);
    }

    public void h(String str) {
        if (str == null) {
            return;
        }
        this.r = "0";
        a(str);
    }

    public void b(String str) {
        this.w = str;
        a("ext_unpl", str);
    }

    public synchronized void b() {
        if (!TextUtils.isEmpty(this.w) && !TextUtils.isEmpty(this.x)) {
            this.y = this.w + "|" + this.x;
        } else if (!TextUtils.isEmpty(this.w)) {
            this.y = this.w;
        } else {
            if (!TextUtils.isEmpty(this.x)) {
                this.y = "|" + this.x;
            }
        }
    }

    public void c(String str) {
        this.x = str;
        a("inner_unpl", str);
    }

    private void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f12701h = jSONObject.optString("psn");
            this.f12702i = jSONObject.optString("psq");
            if (jSONObject.has("adk") && jSONObject.has("ads")) {
                this.o = jSONObject.optString("adk");
                this.p = jSONObject.optString("ads");
                a("adk", this.o);
                a("ad_sc_value", this.p);
            }
            if (jSONObject.has("usc") && jSONObject.has("ucp") && jSONObject.has("umd") && jSONObject.has("utr")) {
                this.f12704k = jSONObject.optString("usc");
                this.f12705l = jSONObject.optString("ucp");
                this.f12706m = jSONObject.optString("umd");
                this.f12707n = jSONObject.optString("utr");
                a("usc", this.f12704k);
                a("ucp", this.f12705l);
                a("umd", this.f12706m);
                a("utr", this.f12707n);
            }
            String optString = jSONObject.optString("mba_muid");
            if (!TextUtils.isEmpty(optString)) {
                this.a = optString;
                a("mba_muid", optString);
            }
            String optString2 = jSONObject.optString("mba_sid");
            if (!TextUtils.isEmpty(optString2)) {
                this.b = optString2;
                a("mba_sid", optString2);
            }
            String optString3 = jSONObject.optString("m_source");
            if (!TextUtils.isEmpty(optString3)) {
                this.f12697c = optString3;
                a("m_source", optString3);
            }
            if (jSONObject.has("jda")) {
                this.f12699f = jSONObject.optString("jda");
                this.f12700g = String.valueOf(System.currentTimeMillis() / 1000);
                a("jda", this.f12699f);
                a("jda_ts", this.f12700g);
            }
            if (jSONObject.has("jdv")) {
                d(jSONObject.optString("jdv"));
            }
            this.f12703j = jSONObject.optString("ref", "");
            if (jSONObject.has("wjfrom")) {
                this.s = jSONObject.optString("wjfrom");
            }
            if (jSONObject.has("wjwxpubid")) {
                this.t = jSONObject.optString("wjwxpubid");
            }
            if (jSONObject.has("wjunionid")) {
                this.u = jSONObject.optString("wjunionid");
            }
            if (jSONObject.has("wjopenid")) {
                this.v = jSONObject.optString("wjopenid");
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void a(String[] strArr) {
        for (String str : strArr) {
            Context context = this.E;
            if (context != null) {
                l.a(context).b(str);
            }
        }
    }

    private void a(String str, String str2) {
        if (this.E == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        l.a(this.E).b(str, str2);
    }
}
