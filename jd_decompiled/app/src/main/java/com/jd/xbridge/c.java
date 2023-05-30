package com.jd.xbridge;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public final class c {
    @NotNull
    private final String a;
    @Nullable
    private final String b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    private final Object f7269c;
    @Nullable
    private final String d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f7270e;

    public c(@NotNull String str, @Nullable String str2, @Nullable Object obj, @Nullable String str3, boolean z) {
        this.a = str;
        this.b = str2;
        this.f7269c = obj;
        this.d = str3;
        this.f7270e = z;
    }

    @Nullable
    public final String a() {
        return this.b;
    }

    public final boolean b() {
        return this.f7270e;
    }

    @Nullable
    public final Object c() {
        return this.f7269c;
    }

    @Nullable
    public final String d() {
        return this.d;
    }

    @NotNull
    public final String e() {
        return this.a;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof c) {
                c cVar = (c) obj;
                return Intrinsics.areEqual(this.a, cVar.a) && Intrinsics.areEqual(this.b, cVar.b) && Intrinsics.areEqual(this.f7269c, cVar.f7269c) && Intrinsics.areEqual(this.d, cVar.d) && this.f7270e == cVar.f7270e;
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Object obj = this.f7269c;
        int hashCode3 = (hashCode2 + (obj != null ? obj.hashCode() : 0)) * 31;
        String str3 = this.d;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        boolean z = this.f7270e;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return hashCode4 + i2;
    }

    @NotNull
    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", this.a);
            jSONObject.put("callbackId", this.b);
            jSONObject.put("data", this.f7269c);
            jSONObject.put("msg", this.d);
            jSONObject.put("complete", this.f7270e);
        } catch (JSONException e2) {
            if (XBridgeManager.INSTANCE.getWebDebug()) {
                e2.getMessage();
            }
        }
        e eVar = e.b;
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "jsonObj.toString()");
        return eVar.d(jSONObject2);
    }

    public /* synthetic */ c(String str, String str2, Object obj, String str3, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : obj, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? true : z);
    }
}
