package com.jd.libs.hybrid.requestpreload.entity;

import androidx.annotation.Keep;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J(\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\f\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\rH\u00d6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u001b\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0014\u001a\u0004\b\u0015\u0010\u0007R\u001b\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0016\u001a\u0004\b\u0017\u0010\u0004\u00a8\u0006\u001a"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/entity/CacheItem;", "", "", "component1", "()Ljava/lang/String;", "Lorg/json/JSONObject;", "component2", "()Lorg/json/JSONObject;", "rawData", "jsonData", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;Lorg/json/JSONObject;)Lcom/jd/libs/hybrid/requestpreload/entity/CacheItem;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lorg/json/JSONObject;", "getJsonData", "Ljava/lang/String;", "getRawData", "<init>", "(Ljava/lang/String;Lorg/json/JSONObject;)V", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final /* data */ class CacheItem {
    @Nullable
    private final JSONObject jsonData;
    @Nullable
    private final String rawData;

    public CacheItem(@Nullable String str, @Nullable JSONObject jSONObject) {
        this.rawData = str;
        this.jsonData = jSONObject;
    }

    public static /* synthetic */ CacheItem copy$default(CacheItem cacheItem, String str, JSONObject jSONObject, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = cacheItem.rawData;
        }
        if ((i2 & 2) != 0) {
            jSONObject = cacheItem.jsonData;
        }
        return cacheItem.copy(str, jSONObject);
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final String getRawData() {
        return this.rawData;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final JSONObject getJsonData() {
        return this.jsonData;
    }

    @NotNull
    public final CacheItem copy(@Nullable String rawData, @Nullable JSONObject jsonData) {
        return new CacheItem(rawData, jsonData);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof CacheItem) {
                CacheItem cacheItem = (CacheItem) other;
                return Intrinsics.areEqual(this.rawData, cacheItem.rawData) && Intrinsics.areEqual(this.jsonData, cacheItem.jsonData);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final JSONObject getJsonData() {
        return this.jsonData;
    }

    @Nullable
    public final String getRawData() {
        return this.rawData;
    }

    public int hashCode() {
        String str = this.rawData;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        JSONObject jSONObject = this.jsonData;
        return hashCode + (jSONObject != null ? jSONObject.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "CacheItem(rawData=" + this.rawData + ", jsonData=" + this.jsonData + ")";
    }
}
