package com.jd.jdsec.c;

import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class c {
    private final C0089c a;

    /* loaded from: classes13.dex */
    private static class b {
        private static final c a = new c();
    }

    /* renamed from: com.jd.jdsec.c.c$c  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    static class C0089c {
        int a = R2.attr.motionEffect_translationX;
        int b = 1;

        /* renamed from: c  reason: collision with root package name */
        int f2736c = 1;
        int d = 1;

        /* renamed from: e  reason: collision with root package name */
        Set<String> f2737e = new HashSet();

        C0089c() {
        }

        private Set<String> a(String str) {
            return b(str, DYConstants.DY_REGEX_COMMA);
        }

        private Set<String> b(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                return new HashSet();
            }
            String[] split = str.split(str2);
            HashSet hashSet = new HashSet();
            for (String str3 : split) {
                if (!TextUtils.isEmpty(str3)) {
                    hashSet.add(str3);
                }
            }
            return hashSet;
        }

        void c(JSONObject jSONObject) {
            if (jSONObject != null) {
                com.jd.jdsec.a.l.b.a("[JMA CCF] \u5f00\u59cb\u89e3\u6790");
                this.a = jSONObject.optInt("fixedinfo", R2.attr.motionEffect_translationX);
                this.b = jSONObject.optInt("openall", 0);
                this.d = jSONObject.optInt("cprs", 1);
                this.f2736c = jSONObject.optInt("readPhone", 1);
                this.f2737e = a(jSONObject.optString("ev"));
                com.jd.jdsec.a.l.b.a("[JMA CCF] \u89e3\u6790\u5b8c\u6210 privacy \u914d\u7f6e:\nphoneStatusReadCmd:\t" + this.f2736c + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
    }

    public static c c() {
        return b.a;
    }

    public boolean a() {
        return this.a.d == 1;
    }

    public int b() {
        return this.a.a;
    }

    public boolean d() {
        return this.a.f2736c == 1;
    }

    public boolean e() {
        return this.a.b == 1;
    }

    public boolean f(String str) {
        Set<String> set = this.a.f2737e;
        return set != null && set.contains(str);
    }

    public void g(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (jSONObject.length() != 0) {
                    com.jd.jdsec.a.l.b.e("JDSec.Security.SDKRemoteConfig", String.format("setConfig invoked %s", jSONObject));
                    this.a.c(jSONObject);
                }
            } catch (Exception e2) {
                com.jd.jdsec.a.l.b.c("JDSec.Security.SDKRemoteConfig", "setConfig", e2);
                return;
            }
        }
        com.jd.jdsec.a.l.b.e("JDSec.Security.SDKRemoteConfig", "setConfig invoked with empty obj");
    }

    private c() {
        this.a = new C0089c();
        com.jd.jdsec.a.l.b.e("JDSec.Security.SDKRemoteConfig", "SDKRemoteConfig init");
    }
}
