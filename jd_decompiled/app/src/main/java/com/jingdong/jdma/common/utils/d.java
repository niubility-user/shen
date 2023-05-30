package com.jingdong.jdma.common.utils;

import android.text.TextUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class d {

    /* renamed from: f  reason: collision with root package name */
    private static d f12685f;
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f12686c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f12687e;

    private d() {
    }

    public static d c() {
        if (f12685f == null) {
            synchronized (d.class) {
                if (f12685f == null) {
                    f12685f = new d();
                }
            }
        }
        return f12685f;
    }

    private String d() {
        return (n.a().b() ? "http://" : "https://").concat(this.f12686c).concat("/debuglog/sdk");
    }

    public void a(String str) {
        try {
            this.f12687e = false;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (LogUtil.isDebug()) {
                LogUtil.d("JDMALogLookup", "parseTextOnMobileCheckMode\uff0ctext=" + str);
            }
            String str2 = new String(com.jingdong.jdma.a.a.a.a(str));
            if (str2.startsWith("jdma://param=")) {
                try {
                    JSONObject jSONObject = new JSONObject(str2.substring(13));
                    this.b = jSONObject.optString("siteId");
                    this.f12686c = jSONObject.optString("domain");
                    this.a = jSONObject.optString("debugId");
                    int optInt = jSONObject.optInt(XView2Constants.STATE);
                    if (TextUtils.isEmpty(this.b)) {
                        if (LogUtil.isDebug()) {
                            LogUtil.d(d.class.getSimpleName(), "---siteId\u4e0d\u80fd\u4e3a\u7a7a----");
                        }
                    } else if (TextUtils.isEmpty(this.f12686c)) {
                        if (LogUtil.isDebug()) {
                            LogUtil.d(d.class.getSimpleName(), "----domain\u4e0d\u80fd\u4e3a\u7a7a----");
                        }
                    } else if (TextUtils.isEmpty(this.a)) {
                        if (LogUtil.isDebug()) {
                            LogUtil.d(d.class.getSimpleName(), "----debugId\u4e0d\u80fd\u4e3a\u7a7a----");
                        }
                    } else if (optInt == 0) {
                        if (LogUtil.isDebug()) {
                            LogUtil.d(d.class.getSimpleName(), "----state\u4e0d\u4e3a0----");
                        }
                    } else {
                        if (LogUtil.isDebug()) {
                            LogUtil.d("----\u5f00\u542f\u57cb\u70b9\u65e5\u5fd7\u67e5\u770b\u6a21\u5f0f----");
                        }
                        com.jingdong.jdma.bean.a.b().a(this.b);
                        this.f12687e = true;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public String b() {
        return this.f12686c;
    }

    public String e() {
        return this.b;
    }

    public boolean f() {
        return this.f12687e;
    }

    public void b(String str) {
        this.d = str;
    }

    public void a(HashMap<String, String> hashMap) {
        if (TextUtils.isEmpty(this.a)) {
            return;
        }
        hashMap.put("debugId", this.a);
        hashMap.put("originStd", this.d);
        try {
            com.jingdong.jdma.f.g.a(d(), hashMap, null, true);
        } catch (Throwable unused) {
        }
    }

    public String a() {
        return this.a;
    }
}
