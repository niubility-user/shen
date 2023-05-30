package com.jd.libs.hybrid.requestpreload.policy;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.dynamic.base.DynamicPrepareFetcher;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.libs.hybrid.base.util.CommonUtils;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.requestpreload.RequestPreloadSDK;
import com.jd.libs.hybrid.requestpreload.entity.ModuleEntity;
import com.jd.libs.hybrid.requestpreload.entity.RequestEntity;
import com.jd.libs.hybrid.requestpreload.entity.RequestItem;
import com.jd.libs.hybrid.requestpreload.policy.PolicyManager;
import com.jd.libs.hybrid.requestpreload.utils.CommonUtil;
import com.jingdong.app.mall.e;
import com.jingdong.jdsdk.a.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 -2\u00020\u0001:\u0001-B\u0007\u00a2\u0006\u0004\b,\u0010\bJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0006J\u001f\u0010\u0017\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0013J%\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0018j\b\u0012\u0004\u0012\u00020\u0011`\u00192\u0006\u0010\u000f\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010 \u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0002\u00a2\u0006\u0004\b \u0010!J\u0019\u0010#\u001a\u0004\u0018\u00010\u00022\b\u0010\"\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b#\u0010\u001fJ#\u0010$\u001a\u0004\u0018\u00010\u00022\b\u0010\u000f\u001a\u0004\u0018\u00010\u00022\b\u0010\"\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b$\u0010!J\r\u0010%\u001a\u00020\u0004\u00a2\u0006\u0004\b%\u0010\bR.\u0010(\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00110&0&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010'R.\u0010)\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020&0&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010'R\"\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0012\u0010'R\"\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010'\u00a8\u0006."}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/policy/PolicyManager;", "", "", "cmsData", "", e.a, "(Ljava/lang/String;)V", "c", "()V", "d", "()Ljava/lang/String;", "Lcom/jd/libs/hybrid/requestpreload/entity/ModuleEntity;", "module", "b", "(Lcom/jd/libs/hybrid/requestpreload/entity/ModuleEntity;)V", "appId", "requestId", "Lcom/jd/libs/hybrid/requestpreload/entity/RequestItem;", a.a, "(Ljava/lang/String;Ljava/lang/String;)Lcom/jd/libs/hybrid/requestpreload/entity/RequestItem;", IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "appIdOrUrl", "requestIdOrUrl", "getRequestItem", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getRequestItems", "(Ljava/lang/String;)Ljava/util/ArrayList;", "getModule", "(Ljava/lang/String;)Lcom/jd/libs/hybrid/requestpreload/entity/ModuleEntity;", "getAppId", "(Ljava/lang/String;)Ljava/lang/String;", "getRequestId", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "url", "getAppIdByUrl", "getRequestIdByUrl", "clear", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/util/concurrent/ConcurrentHashMap;", "requestMap", "requestIdUrlMap", "moduleMap", "appIdUrlMap", "<init>", "Companion", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class PolicyManager {

    /* renamed from: a  reason: from kotlin metadata */
    private ConcurrentHashMap<String, ModuleEntity> moduleMap = new ConcurrentHashMap<>();

    /* renamed from: b  reason: from kotlin metadata */
    private ConcurrentHashMap<String, String> appIdUrlMap = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private ConcurrentHashMap<String, ConcurrentHashMap<String, RequestItem>> requestMap = new ConcurrentHashMap<>();

    /* renamed from: d  reason: from kotlin metadata */
    private ConcurrentHashMap<String, ConcurrentHashMap<String, String>> requestIdUrlMap = new ConcurrentHashMap<>();

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    private static final String f6173e = f6173e;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    private static final String f6173e = f6173e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    private static final String f6174f = f6174f;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    private static final String f6174f = f6174f;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\t\u0010\nR\u001c\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086D\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u00020\u00028\u0006@\u0006X\u0086D\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/policy/PolicyManager$Companion;", "", "", "SP_NAME", "Ljava/lang/String;", "getSP_NAME", "()Ljava/lang/String;", "SP_KEY_CMS_POLICY", "getSP_KEY_CMS_POLICY", "<init>", "()V", "request-preload_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes16.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final String getSP_KEY_CMS_POLICY() {
            return PolicyManager.f6174f;
        }

        @NotNull
        public final String getSP_NAME() {
            return PolicyManager.f6173e;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public PolicyManager() {
        c();
    }

    private final RequestItem a(String appId, String requestId) {
        ConcurrentHashMap<String, RequestItem> concurrentHashMap = this.requestMap.get(appId);
        if (concurrentHashMap == null) {
            Intrinsics.throwNpe();
        }
        if (concurrentHashMap.get(requestId) != null) {
            ConcurrentHashMap<String, RequestItem> concurrentHashMap2 = this.requestMap.get(appId);
            if (concurrentHashMap2 == null) {
                Intrinsics.throwNpe();
            }
            RequestItem requestItem = concurrentHashMap2.get(requestId);
            if (requestItem == null) {
                Intrinsics.throwNpe();
            }
            return requestItem;
        }
        RequestItem requestItem2 = new RequestItem();
        ConcurrentHashMap<String, RequestItem> concurrentHashMap3 = this.requestMap.get(appId);
        if (concurrentHashMap3 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(concurrentHashMap3, "requestMap[appId]!!");
        concurrentHashMap3.put(requestId, requestItem2);
        return requestItem2;
    }

    private final void b(ModuleEntity module) {
        String appId = module.getAppId();
        this.requestMap.put(appId, new ConcurrentHashMap<>());
        this.requestIdUrlMap.put(appId, new ConcurrentHashMap<>());
        for (RequestEntity requestEntity : module.getRequests()) {
            RequestItem a = a(appId, requestEntity.getRequestId());
            a.setRequestId(requestEntity.getRequestId());
            a.setMethod(requestEntity.getMethod());
            a.setParamsMap(requestEntity.getParamTemplate());
            a.setUrl(requestEntity.getRequestUrl());
            ArrayList arrayList = new ArrayList();
            Iterator<Map.Entry<String, String>> it = a.getParamsMap().entrySet().iterator();
            while (it.hasNext()) {
                arrayList.addAll(CommonUtil.INSTANCE.findDependencyIds(it.next().getValue()));
            }
            if (arrayList.size() > 0) {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    RequestItem a2 = a(appId, (String) it2.next());
                    a.getDependOn().add(a2);
                    a2.getDependBy().add(a);
                }
            }
            ConcurrentHashMap<String, String> concurrentHashMap = this.requestIdUrlMap.get(appId);
            if (concurrentHashMap == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(concurrentHashMap, "requestIdUrlMap[appId]!!");
            concurrentHashMap.put(requestEntity.getRequestUrl(), requestEntity.getRequestId());
        }
    }

    private final void c() {
        DatabaseExecutors.getInstance().runOnIoThread(new Runnable() { // from class: com.jd.libs.hybrid.requestpreload.policy.PolicyManager$parseLocal$1
            @Override // java.lang.Runnable
            public final void run() {
                String d;
                PolicyManager policyManager = PolicyManager.this;
                d = policyManager.d();
                policyManager.parse(d);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String d() {
        Context context = RequestPreloadSDK.INSTANCE.getInstance().getContext();
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(f6173e, 0);
            Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.getSharedPrefere\u2026    Context.MODE_PRIVATE)");
            String string = sharedPreferences.getString(f6174f, "");
            if (string == null) {
                Intrinsics.throwNpe();
            }
            return string;
        }
        return "";
    }

    private final void e(final String cmsData) {
        DatabaseExecutors.getInstance().runOnIoThread(new Runnable() { // from class: com.jd.libs.hybrid.requestpreload.policy.PolicyManager$saveToLocal$1
            @Override // java.lang.Runnable
            public final void run() {
                Context context = RequestPreloadSDK.INSTANCE.getInstance().getContext();
                if (context != null) {
                    PolicyManager.Companion companion = PolicyManager.INSTANCE;
                    SharedPreferences sharedPreferences = context.getSharedPreferences(companion.getSP_NAME(), 0);
                    Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.getSharedPrefere\u2026    Context.MODE_PRIVATE)");
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    Intrinsics.checkExpressionValueIsNotNull(edit, "sharedPreferences.edit()");
                    edit.putString(companion.getSP_KEY_CMS_POLICY(), cmsData);
                    edit.commit();
                }
            }
        });
    }

    public final void clear() {
        this.moduleMap.clear();
        this.appIdUrlMap.clear();
        this.requestIdUrlMap.clear();
        this.requestMap.clear();
    }

    @Nullable
    public final String getAppId(@NotNull String appIdOrUrl) {
        CharSequence trim;
        trim = StringsKt__StringsKt.trim((CharSequence) appIdOrUrl);
        String obj = trim.toString();
        return CommonUtil.INSTANCE.isUrl(obj) ? getAppIdByUrl(CommonUtils.getCleanUrl(obj)) : obj;
    }

    @Nullable
    public final String getAppIdByUrl(@Nullable String url) {
        if (url == null) {
            return null;
        }
        return this.appIdUrlMap.get(url);
    }

    @Nullable
    public final ModuleEntity getModule(@NotNull String appIdOrUrl) {
        CharSequence trim;
        trim = StringsKt__StringsKt.trim((CharSequence) appIdOrUrl);
        String appId = getAppId(trim.toString());
        System.out.println("taoyr niubi: appId" + appId + ", moduleMap: " + this.moduleMap);
        if (appId == null) {
            return null;
        }
        return this.moduleMap.get(appId);
    }

    @Nullable
    public final String getRequestId(@NotNull String appIdOrUrl, @NotNull String requestIdOrUrl) {
        CharSequence trim;
        trim = StringsKt__StringsKt.trim((CharSequence) requestIdOrUrl);
        return CommonUtil.INSTANCE.isUrl(requestIdOrUrl) ? getRequestIdByUrl(getAppId(appIdOrUrl), requestIdOrUrl) : trim.toString();
    }

    @Nullable
    public final String getRequestIdByUrl(@Nullable String appId, @Nullable String url) {
        ConcurrentHashMap<String, String> concurrentHashMap;
        if (appId == null || url == null || (concurrentHashMap = this.requestIdUrlMap.get(appId)) == null) {
            return null;
        }
        return concurrentHashMap.get(url);
    }

    @Nullable
    public final RequestItem getRequestItem(@NotNull String appIdOrUrl, @NotNull String requestIdOrUrl) {
        String requestId = getRequestId(appIdOrUrl, requestIdOrUrl);
        String appId = getAppId(appIdOrUrl);
        if (appId == null || this.requestMap.get(appId) == null || requestId == null) {
            return null;
        }
        ConcurrentHashMap<String, RequestItem> concurrentHashMap = this.requestMap.get(appId);
        if (concurrentHashMap == null) {
            Intrinsics.throwNpe();
        }
        return concurrentHashMap.get(requestId);
    }

    @NotNull
    public final ArrayList<RequestItem> getRequestItems(@NotNull String appId) {
        if (this.requestMap.get(appId) != null) {
            ConcurrentHashMap<String, RequestItem> concurrentHashMap = this.requestMap.get(appId);
            if (concurrentHashMap == null) {
                Intrinsics.throwNpe();
            }
            return new ArrayList<>(concurrentHashMap.values());
        }
        return new ArrayList<>();
    }

    public final void parse(@Nullable String cmsData) {
        clear();
        if (TextUtils.isEmpty(cmsData)) {
            e("");
            return;
        }
        try {
            JSONArray optJSONArray = new JSONObject(cmsData).optJSONArray(DynamicPrepareFetcher.KEY_PREPARE_MODULES);
            int length = optJSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                ModuleEntity moduleEntity = new ModuleEntity();
                JSONObject jSONObject = optJSONArray.getJSONObject(i2);
                Intrinsics.checkExpressionValueIsNotNull(jSONObject, "moduleArr.getJSONObject(i)");
                ModuleEntity fromJson = moduleEntity.fromJson(jSONObject);
                this.appIdUrlMap.put(fromJson.getH5Url(), fromJson.getAppId());
                this.moduleMap.put(fromJson.getAppId(), fromJson);
                b(fromJson);
            }
            if (cmsData == null) {
                Intrinsics.throwNpe();
            }
            e(cmsData);
        } catch (Throwable unused) {
        }
    }
}
