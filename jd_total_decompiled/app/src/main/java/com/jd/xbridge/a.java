package com.jd.xbridge;

import com.jd.xbridge.base.IBridgeCallback;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public final class a {
    @Nullable
    private String a;
    @Nullable
    private IBridgeCallback b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    private final String f7267c;
    @Nullable
    private final Object d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    private final String f7268e;

    public a() {
        this(null, null, null, 7, null);
    }

    public a(@Nullable String str, @Nullable Object obj, @Nullable String str2) {
        this.f7267c = str;
        this.d = obj;
        this.f7268e = str2;
    }

    @Nullable
    public final String a() {
        return this.a;
    }

    @Nullable
    public final IBridgeCallback b() {
        return this.b;
    }

    @Nullable
    public final String c() {
        return this.f7268e;
    }

    @Nullable
    public final Object d() {
        return this.d;
    }

    @Nullable
    public final String e() {
        return this.f7267c;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof a) {
                a aVar = (a) obj;
                return Intrinsics.areEqual(this.f7267c, aVar.f7267c) && Intrinsics.areEqual(this.d, aVar.d) && Intrinsics.areEqual(this.f7268e, aVar.f7268e);
            }
            return false;
        }
        return true;
    }

    public final void f(@Nullable String str) {
        this.a = str;
    }

    public final void g(@Nullable IBridgeCallback iBridgeCallback) {
        this.b = iBridgeCallback;
    }

    public int hashCode() {
        String str = this.f7267c;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Object obj = this.d;
        int hashCode2 = (hashCode + (obj != null ? obj.hashCode() : 0)) * 31;
        String str2 = this.f7268e;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("plugin", this.f7267c);
            jSONObject.put("action", this.a);
            jSONObject.put("params", this.d);
            jSONObject.put("callbackId", this.f7268e);
        } catch (JSONException e2) {
            if (XBridgeManager.INSTANCE.getWebDebug()) {
                e2.getMessage();
            }
        }
        String quote = JSONObject.quote(jSONObject.toString());
        Intrinsics.checkExpressionValueIsNotNull(quote, "JSONObject.quote(jsonObj.toString())");
        return quote;
    }

    public /* synthetic */ a(String str, Object obj, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : obj, (i2 & 4) != 0 ? null : str2);
    }
}
