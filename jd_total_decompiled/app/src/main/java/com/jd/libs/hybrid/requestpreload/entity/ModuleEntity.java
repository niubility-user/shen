package com.jd.libs.hybrid.requestpreload.entity;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.base.util.CommonUtils;
import com.jd.libs.hybrid.requestpreload.utils.CommonUtil;
import com.jingdong.app.mall.e;
import com.jingdong.jdsdk.a.a;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\u00a2\u0006\u0004\b\"\u0010#J\u0017\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005R\"\u0010\r\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR)\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u00108\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\"\u0010\u0019\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\b\u001a\u0004\b\u0017\u0010\n\"\u0004\b\u0018\u0010\fR\"\u0010\u001d\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\b\u001a\u0004\b\u001b\u0010\n\"\u0004\b\u001c\u0010\fR\"\u0010!\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\b\u001a\u0004\b\u001f\u0010\n\"\u0004\b \u0010\f\u00a8\u0006$"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/entity/ModuleEntity;", "Lcom/jd/libs/hybrid/requestpreload/entity/IEntity;", "Lorg/json/JSONObject;", "json", "fromJson", "(Lorg/json/JSONObject;)Lcom/jd/libs/hybrid/requestpreload/entity/ModuleEntity;", "", "b", "Ljava/lang/String;", "getH5Url", "()Ljava/lang/String;", "setH5Url", "(Ljava/lang/String;)V", "h5Url", "Ljava/util/ArrayList;", "Lcom/jd/libs/hybrid/requestpreload/entity/RequestEntity;", "Lkotlin/collections/ArrayList;", e.a, "Ljava/util/ArrayList;", "getRequests", "()Ljava/util/ArrayList;", "requests", "c", "getAppMax", "setAppMax", "appMax", a.a, "getAppId", "setAppId", "appId", "d", "getAppMin", "setAppMin", "appMin", "<init>", "()V", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class ModuleEntity implements IEntity<ModuleEntity> {
    @NotNull

    /* renamed from: a  reason: from kotlin metadata */
    private String appId = "";
    @NotNull

    /* renamed from: b  reason: from kotlin metadata */
    private String h5Url = "";
    @NotNull

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private String appMax = "";
    @NotNull

    /* renamed from: d  reason: from kotlin metadata */
    private String appMin = "";
    @NotNull

    /* renamed from: e  reason: collision with root package name and from kotlin metadata */
    private final ArrayList<RequestEntity> requests = new ArrayList<>();

    @NotNull
    public final String getAppId() {
        return this.appId;
    }

    @NotNull
    public final String getAppMax() {
        return this.appMax;
    }

    @NotNull
    public final String getAppMin() {
        return this.appMin;
    }

    @NotNull
    public final String getH5Url() {
        return this.h5Url;
    }

    @NotNull
    public final ArrayList<RequestEntity> getRequests() {
        return this.requests;
    }

    public final void setAppId(@NotNull String str) {
        this.appId = str;
    }

    public final void setAppMax(@NotNull String str) {
        this.appMax = str;
    }

    public final void setAppMin(@NotNull String str) {
        this.appMin = str;
    }

    public final void setH5Url(@NotNull String str) {
        this.h5Url = str;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.libs.hybrid.requestpreload.entity.IEntity
    @NotNull
    public ModuleEntity fromJson(@NotNull JSONObject json) {
        CharSequence trim;
        CharSequence trim2;
        CharSequence trim3;
        CharSequence trim4;
        String optString = json.optString("appid");
        Intrinsics.checkExpressionValueIsNotNull(optString, "json.optString(\"appid\")");
        if (optString != null) {
            trim = StringsKt__StringsKt.trim((CharSequence) optString);
            this.appId = trim.toString();
            String optString2 = json.optString("app_max");
            Intrinsics.checkExpressionValueIsNotNull(optString2, "json.optString(\"app_max\")");
            if (optString2 != null) {
                trim2 = StringsKt__StringsKt.trim((CharSequence) optString2);
                this.appMax = trim2.toString();
                String optString3 = json.optString("app_min");
                Intrinsics.checkExpressionValueIsNotNull(optString3, "json.optString(\"app_min\")");
                if (optString3 != null) {
                    trim3 = StringsKt__StringsKt.trim((CharSequence) optString3);
                    this.appMin = trim3.toString();
                    String optString4 = json.optString("h5_url");
                    Intrinsics.checkExpressionValueIsNotNull(optString4, "json.optString(\"h5_url\")");
                    if (optString4 != null) {
                        trim4 = StringsKt__StringsKt.trim((CharSequence) optString4);
                        String obj = trim4.toString();
                        if (!TextUtils.isEmpty(obj) && CommonUtil.INSTANCE.isUrl(obj)) {
                            String cleanUrl = CommonUtils.getCleanUrl(obj);
                            Intrinsics.checkExpressionValueIsNotNull(cleanUrl, "CommonUtils.getCleanUrl(url)");
                            this.h5Url = cleanUrl;
                        }
                        JSONArray optJSONArray = json.optJSONArray("requests");
                        if (optJSONArray != null) {
                            int length = optJSONArray.length();
                            for (int i2 = 0; i2 < length; i2++) {
                                if (optJSONArray.get(i2) instanceof JSONObject) {
                                    RequestEntity requestEntity = new RequestEntity();
                                    Object obj2 = optJSONArray.get(i2);
                                    if (obj2 != null) {
                                        RequestEntity fromJson = requestEntity.fromJson((JSONObject) obj2);
                                        if (!TextUtils.isEmpty(fromJson.getRequestUrl()) && (!Intrinsics.areEqual(fromJson.getRequestUrl(), DYConstants.DY_NULL_STR)) != false) {
                                            this.requests.add(fromJson);
                                        }
                                    } else {
                                        throw new TypeCastException("null cannot be cast to non-null type org.json.JSONObject");
                                    }
                                }
                            }
                        }
                        return this;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
}
