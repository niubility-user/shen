package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;
import java.util.LinkedHashMap;

/* loaded from: classes12.dex */
public class z0 {
    private String a;
    public l1 b;

    public z0(String str) {
        this.a = str;
        this.b = new l1(str);
        s.c().a(this.a, this.b);
    }

    private s0 b(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        return null;
                    }
                    return this.b.a();
                }
                return this.b.d();
            }
            return this.b.b();
        }
        return this.b.c();
    }

    private boolean c(int i2) {
        String str;
        if (i2 != 2) {
            s0 b = b(i2);
            if (b != null && !TextUtils.isEmpty(b.h())) {
                return true;
            }
            str = "verifyURL(): URL check failed. type: " + i2;
        } else if ("_default_config_tag".equals(this.a)) {
            return true;
        } else {
            str = "verifyURL(): type: preins. Only default config can report Pre-install data.";
        }
        v.e("hmsSdk", str);
        return false;
    }

    public void a(int i2) {
        v.d("hmsSdk", "onReport. TAG: " + this.a + ", TYPE: " + i2);
        g0.a().a(this.a, i2);
    }

    public void a(int i2, String str, LinkedHashMap<String, String> linkedHashMap) {
        v.d("hmsSdk", "onEvent. TAG: " + this.a + ", TYPE: " + i2 + ", eventId : " + str);
        if (e1.a(str) || !c(i2)) {
            v.e("hmsSdk", "onEvent() parameters check fail. Nothing will be recorded.TAG: " + this.a + ", TYPE: " + i2);
            return;
        }
        if (!e1.a(linkedHashMap)) {
            v.e("hmsSdk", "onEvent() parameter mapValue will be cleared.TAG: " + this.a + ", TYPE: " + i2);
            linkedHashMap = null;
        }
        g0.a().a(this.a, i2, str, linkedHashMap);
    }

    public void a(Context context, String str, String str2) {
        v.d("hmsSdk", "onEvent(context). TAG: " + this.a + ", eventId : " + str);
        if (context == null) {
            v.e("hmsSdk", "context is null in onevent ");
        } else if (e1.a(str) || !c(0)) {
            v.e("hmsSdk", "onEvent() parameters check fail. Nothing will be recorded.TAG: " + this.a);
        } else {
            if (!e1.a("value", str2, 65536)) {
                v.e("hmsSdk", "onEvent() parameter VALUE is overlong, content will be cleared.TAG: " + this.a);
                str2 = "";
            }
            g0.a().a(this.a, context, str, str2);
        }
    }

    public void a(s0 s0Var) {
        v.c("hmsSdk", "HiAnalyticsInstanceImpl.setMaintConf() is executed.TAG : " + this.a);
        if (s0Var != null) {
            this.b.a(s0Var);
            return;
        }
        v.e("hmsSdk", "HiAnalyticsInstanceImpl.setMaintConf(): config for maint is null!");
        this.b.a((s0) null);
    }

    public void b(int i2, String str, LinkedHashMap<String, String> linkedHashMap) {
        v.d("hmsSdk", "onStreamEvent. TAG: " + this.a + ", TYPE: " + i2 + ", eventId : " + str);
        if (e1.a(str) || !c(i2)) {
            v.e("hmsSdk", "onStreamEvent() parameters check fail. Nothing will be recorded.TAG: " + this.a + ", TYPE: " + i2);
            return;
        }
        if (!e1.a(linkedHashMap)) {
            v.e("hmsSdk", "onStreamEvent() parameter mapValue will be cleared.TAG: " + this.a + ", TYPE: " + i2);
            linkedHashMap = null;
        }
        g0.a().b(this.a, i2, str, linkedHashMap);
    }

    public void b(s0 s0Var) {
        v.c("hmsSdk", "HiAnalyticsInstanceImpl.setOperConf() is executed.TAG: " + this.a);
        if (s0Var != null) {
            this.b.b(s0Var);
            return;
        }
        this.b.b(null);
        v.e("hmsSdk", "HiAnalyticsInstanceImpl.setOperConf(): config for oper is null!");
    }
}
