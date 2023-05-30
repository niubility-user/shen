package com.jd.xbridge;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public final class d {
    @NotNull
    public static final c a(@NotNull JSONObject jSONObject) {
        String optString = jSONObject.optString("status", "0");
        Intrinsics.checkExpressionValueIsNotNull(optString, "optString(\"status\", \"0\")");
        String optString2 = jSONObject.optString("callbackId", "");
        if (optString2.length() == 0) {
            optString2 = null;
        }
        Object opt = jSONObject.opt("data");
        String optString3 = jSONObject.optString("msg", "");
        return new c(optString, optString2, opt, optString3.length() == 0 ? null : optString3, jSONObject.optBoolean("complete", true));
    }
}
