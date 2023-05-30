package com.jd.libs.hybrid.requestpreload.entity;

import com.jd.libs.hybrid.requestpreload.utils.CommonUtil;
import com.jingdong.app.mall.e;
import com.jingdong.jdsdk.a.a;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b.\u0010/J\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001H\u0096\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bR\"\u0010\u0013\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0017\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u000e\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\"\u0010\u001b\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u000e\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R>\u0010$\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u001cj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f`\u001d8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00000%8\u0006@\u0006\u00a2\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u001f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00000%8\u0006@\u0006\u00a2\u0006\f\n\u0004\b+\u0010'\u001a\u0004\b,\u0010)\u00a8\u00060"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/entity/RequestItem;", "", "Lorg/json/JSONObject;", "getJsonParams", "()Lorg/json/JSONObject;", "o", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "b", "Ljava/lang/String;", "getUrl", "()Ljava/lang/String;", "setUrl", "(Ljava/lang/String;)V", "url", a.a, "getRequestId", "setRequestId", "requestId", "d", "getMethod", "setMethod", "method", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "c", "Ljava/util/LinkedHashMap;", "getParamsMap", "()Ljava/util/LinkedHashMap;", "setParamsMap", "(Ljava/util/LinkedHashMap;)V", "paramsMap", "", e.a, "Ljava/util/List;", "getDependOn", "()Ljava/util/List;", "dependOn", "f", "getDependBy", "dependBy", "<init>", "()V", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class RequestItem {
    @NotNull

    /* renamed from: a  reason: from kotlin metadata */
    private String requestId = "";
    @NotNull

    /* renamed from: b  reason: from kotlin metadata */
    private String url = "";
    @NotNull

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private LinkedHashMap<String, String> paramsMap = new LinkedHashMap<>();
    @NotNull

    /* renamed from: d  reason: from kotlin metadata */
    private String method = IMantoServerRequester.POST;
    @NotNull

    /* renamed from: e  reason: collision with root package name and from kotlin metadata */
    private final List<RequestItem> dependOn = new ArrayList();
    @NotNull

    /* renamed from: f  reason: collision with root package name and from kotlin metadata */
    private final List<RequestItem> dependBy = new ArrayList();

    public boolean equals(@Nullable Object o) {
        boolean equals;
        boolean equals2;
        if (this == o) {
            return true;
        }
        if (o == null || (!Intrinsics.areEqual(RequestItem.class, o.getClass())) == true) {
            return false;
        }
        RequestItem requestItem = (RequestItem) o;
        equals = StringsKt__StringsJVMKt.equals(this.url, requestItem.url, true);
        if (equals && this.paramsMap.size() == requestItem.paramsMap.size()) {
            for (Map.Entry<String, String> entry : this.paramsMap.entrySet()) {
                equals2 = StringsKt__StringsJVMKt.equals(entry.getValue(), requestItem.paramsMap.get(entry.getKey()), true);
                if (!equals2) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @NotNull
    public final List<RequestItem> getDependBy() {
        return this.dependBy;
    }

    @NotNull
    public final List<RequestItem> getDependOn() {
        return this.dependOn;
    }

    @NotNull
    public final JSONObject getJsonParams() {
        return CommonUtil.INSTANCE.getJsonParams(this.paramsMap);
    }

    @NotNull
    public final String getMethod() {
        return this.method;
    }

    @NotNull
    public final LinkedHashMap<String, String> getParamsMap() {
        return this.paramsMap;
    }

    @NotNull
    public final String getRequestId() {
        return this.requestId;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        return this.url.hashCode() + this.paramsMap.hashCode();
    }

    public final void setMethod(@NotNull String str) {
        this.method = str;
    }

    public final void setParamsMap(@NotNull LinkedHashMap<String, String> linkedHashMap) {
        this.paramsMap = linkedHashMap;
    }

    public final void setRequestId(@NotNull String str) {
        this.requestId = str;
    }

    public final void setUrl(@NotNull String str) {
        this.url = str;
    }
}
