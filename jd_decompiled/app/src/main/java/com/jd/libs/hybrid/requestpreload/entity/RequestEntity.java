package com.jd.libs.hybrid.requestpreload.entity;

import android.text.TextUtils;
import com.jingdong.jdsdk.a.a;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005R\"\u0010\r\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\u0011\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\b\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR5\u0010\u0018\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0012j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006`\u00138\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\"\u0010\u001c\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\b\u001a\u0004\b\u001a\u0010\n\"\u0004\b\u001b\u0010\f\u00a8\u0006\u001f"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/entity/RequestEntity;", "Lcom/jd/libs/hybrid/requestpreload/entity/IEntity;", "Lorg/json/JSONObject;", "json", "fromJson", "(Lorg/json/JSONObject;)Lcom/jd/libs/hybrid/requestpreload/entity/RequestEntity;", "", "c", "Ljava/lang/String;", "getMethod", "()Ljava/lang/String;", "setMethod", "(Ljava/lang/String;)V", "method", "b", "getRequestUrl", "setRequestUrl", "requestUrl", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "d", "Ljava/util/LinkedHashMap;", "getParamTemplate", "()Ljava/util/LinkedHashMap;", "paramTemplate", a.a, "getRequestId", "setRequestId", "requestId", "<init>", "()V", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class RequestEntity implements IEntity<RequestEntity> {
    @NotNull

    /* renamed from: a  reason: from kotlin metadata */
    private String requestId = "";
    @NotNull

    /* renamed from: b  reason: from kotlin metadata */
    private String requestUrl = "";
    @NotNull

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private String method = IMantoServerRequester.POST;
    @NotNull

    /* renamed from: d  reason: from kotlin metadata */
    private final LinkedHashMap<String, String> paramTemplate = new LinkedHashMap<>();

    @NotNull
    public final String getMethod() {
        return this.method;
    }

    @NotNull
    public final LinkedHashMap<String, String> getParamTemplate() {
        return this.paramTemplate;
    }

    @NotNull
    public final String getRequestId() {
        return this.requestId;
    }

    @NotNull
    public final String getRequestUrl() {
        return this.requestUrl;
    }

    public final void setMethod(@NotNull String str) {
        this.method = str;
    }

    public final void setRequestId(@NotNull String str) {
        this.requestId = str;
    }

    public final void setRequestUrl(@NotNull String str) {
        this.requestUrl = str;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.libs.hybrid.requestpreload.entity.IEntity
    @NotNull
    public RequestEntity fromJson(@NotNull JSONObject json) {
        CharSequence trim;
        CharSequence trim2;
        String optString = json.optString("request_id");
        Intrinsics.checkExpressionValueIsNotNull(optString, "json.optString(\"request_id\")");
        if (optString != null) {
            trim = StringsKt__StringsKt.trim((CharSequence) optString);
            this.requestId = trim.toString();
            String optString2 = json.optString("request_url");
            Intrinsics.checkExpressionValueIsNotNull(optString2, "json.optString(\"request_url\")");
            if (optString2 != null) {
                trim2 = StringsKt__StringsKt.trim((CharSequence) optString2);
                this.requestUrl = trim2.toString();
                this.method = json.optInt("method") == 1 ? IMantoServerRequester.GET : IMantoServerRequester.POST;
                try {
                    String optString3 = json.optString("param_template");
                    if (!TextUtils.isEmpty(optString3)) {
                        JSONObject jSONObject = new JSONObject(optString3);
                        Iterator<String> keys = jSONObject.keys();
                        Intrinsics.checkExpressionValueIsNotNull(keys, "paramJsonObj.keys()");
                        while (keys.hasNext()) {
                            String it = keys.next();
                            LinkedHashMap<String, String> linkedHashMap = this.paramTemplate;
                            Intrinsics.checkExpressionValueIsNotNull(it, "it");
                            linkedHashMap.put(it, jSONObject.get(it).toString());
                        }
                    }
                } catch (JSONException unused) {
                }
                return this;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
}
