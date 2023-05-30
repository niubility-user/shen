package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import com.tencent.map.tools.json.JsonUtils;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class k2 extends e2 {

    /* renamed from: i  reason: collision with root package name */
    public static final String f16753i = "name";

    /* renamed from: j  reason: collision with root package name */
    public static final String f16754j = "host";

    /* renamed from: k  reason: collision with root package name */
    public static final String f16755k = "host_test";

    /* renamed from: l  reason: collision with root package name */
    public static final String f16756l = "https";

    /* renamed from: m  reason: collision with root package name */
    public static final String f16757m = "status";

    public void a(JSONObject jSONObject) {
        a().a((m2) JsonUtils.parseToModel(jSONObject, m2.class, new Object[0]));
    }

    public void b(JSONObject jSONObject) {
        i3 i3Var;
        if (jSONObject == null) {
            return;
        }
        String optString = jSONObject.optString("name", null);
        if (TextUtils.isEmpty(optString) || (i3Var = (i3) c(optString)) == null) {
            return;
        }
        int optInt = jSONObject.optInt("status", 1);
        String optString2 = jSONObject.optString("host", null);
        String optString3 = jSONObject.optString(f16755k, null);
        boolean optBoolean = jSONObject.optBoolean("https", true);
        i3Var.setAllow(optInt != 0);
        i3Var.setUseHttps(optBoolean);
        if (!TextUtils.isEmpty(optString2)) {
            i3Var.b(optString2);
        }
        if (!TextUtils.isEmpty(optString3)) {
            i3Var.c(optString3);
        }
        i3Var.setUseTest(optInt == 2);
    }
}
