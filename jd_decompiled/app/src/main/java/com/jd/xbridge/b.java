package com.jd.xbridge;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public final class b {
    @NotNull
    public static final a a(@NotNull JSONObject jSONObject) {
        String optString = jSONObject.optString("plugin", "");
        if (optString.length() == 0) {
            optString = null;
        }
        Object opt = jSONObject.opt("params");
        String optString2 = jSONObject.optString("callbackId", "");
        if (optString2.length() == 0) {
            optString2 = null;
        }
        a aVar = new a(optString, opt, optString2);
        String optString3 = jSONObject.optString("action", "");
        aVar.f(optString3.length() == 0 ? null : optString3);
        return aVar;
    }
}
