package com.jd.libs.hybrid.requestpreload;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.VersionUtils;
import com.jd.libs.hybrid.requestpreload.coroutine.RequestWorker;
import com.jd.libs.hybrid.requestpreload.entity.CacheItem;
import com.jd.libs.hybrid.requestpreload.entity.ModuleEntity;
import com.jd.libs.hybrid.requestpreload.network.RequestCacheManager;
import com.jd.libs.hybrid.requestpreload.perf.PerfMonitor;
import com.jd.libs.hybrid.requestpreload.policy.PolicyManager;
import com.jd.libs.hybrid.requestpreload.utils.CommonUtil;
import com.jd.libs.xdog.b;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.a.a;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 72\u00020\u0001:\u000278B\t\b\u0002\u00a2\u0006\u0004\b6\u0010\u0016J\u0017\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\t\u0010\nJ!\u0010\r\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\r\u0010\u000eJ-\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0014\u0010\nJ\r\u0010\u0015\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0015\u0010\u0016R\"\u0010\u0018\u001a\u00020\u00178\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0018\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\u001e\u001a\u00020\u001d8\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010%\u001a\u00020$8\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\"\u0010,\u001a\u00020+8\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\b\u0003\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u0010\u0006\u00a8\u00069"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/RequestPreloadSDK;", "", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", XView2Constants.XVIEW2_ACTION_INIT, "(Landroid/content/Context;)V", "", "cmsData", IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "(Ljava/lang/String;)V", "appIdOrUrl", "timeStamp", HttpDnsConfig.PREDOWNLOAD_PARAMS, "(Ljava/lang/String;Ljava/lang/String;)V", "requestIdOrUrl", "Lcom/jd/libs/hybrid/requestpreload/entity/CacheItem;", "getPreloadData", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/jd/libs/hybrid/requestpreload/entity/CacheItem;", VerifyTracker.KEY_TIMESTAMP, "destroy", "destroyAll", "()V", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isInit", "Ljava/util/concurrent/atomic/AtomicBoolean;", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "setInit", "(Ljava/util/concurrent/atomic/AtomicBoolean;)V", "Lcom/jd/libs/hybrid/requestpreload/network/RequestCacheManager;", "requestCacheManager", "Lcom/jd/libs/hybrid/requestpreload/network/RequestCacheManager;", "getRequestCacheManager", "()Lcom/jd/libs/hybrid/requestpreload/network/RequestCacheManager;", "setRequestCacheManager", "(Lcom/jd/libs/hybrid/requestpreload/network/RequestCacheManager;)V", "Lcom/jd/libs/hybrid/requestpreload/perf/PerfMonitor;", SwitchQueryFetcher.PERFORMANCE_MONITOR, "Lcom/jd/libs/hybrid/requestpreload/perf/PerfMonitor;", "getPerfMonitor", "()Lcom/jd/libs/hybrid/requestpreload/perf/PerfMonitor;", "setPerfMonitor", "(Lcom/jd/libs/hybrid/requestpreload/perf/PerfMonitor;)V", "Lcom/jd/libs/hybrid/requestpreload/policy/PolicyManager;", "policyManager", "Lcom/jd/libs/hybrid/requestpreload/policy/PolicyManager;", "getPolicyManager", "()Lcom/jd/libs/hybrid/requestpreload/policy/PolicyManager;", "setPolicyManager", "(Lcom/jd/libs/hybrid/requestpreload/policy/PolicyManager;)V", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "<init>", "Companion", "SingletonHolder", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class RequestPreloadSDK {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    @NotNull
    public static final String TAG = "RequestPreloadSDK";
    @NotNull
    public volatile Context context;
    @NotNull
    private AtomicBoolean isInit;
    @NotNull
    public PerfMonitor perfMonitor;
    @NotNull
    public PolicyManager policyManager;
    @NotNull
    public RequestCacheManager requestCacheManager;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\n"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/RequestPreloadSDK$Companion;", "", "Lcom/jd/libs/hybrid/requestpreload/RequestPreloadSDK;", "getInstance", "()Lcom/jd/libs/hybrid/requestpreload/RequestPreloadSDK;", "", "TAG", "Ljava/lang/String;", "<init>", "()V", "request-preload_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes16.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final RequestPreloadSDK getInstance() {
            return SingletonHolder.INSTANCE.getHolder();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c2\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\b\u0010\tR\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\n"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/RequestPreloadSDK$SingletonHolder;", "", "Lcom/jd/libs/hybrid/requestpreload/RequestPreloadSDK;", a.a, "Lcom/jd/libs/hybrid/requestpreload/RequestPreloadSDK;", "getHolder", "()Lcom/jd/libs/hybrid/requestpreload/RequestPreloadSDK;", "holder", "<init>", "()V", "request-preload_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes16.dex */
    public static final class SingletonHolder {
        public static final SingletonHolder INSTANCE = new SingletonHolder();
        @NotNull

        /* renamed from: a */
        private static final RequestPreloadSDK holder = new RequestPreloadSDK(null);

        private SingletonHolder() {
        }

        @NotNull
        public final RequestPreloadSDK getHolder() {
            return holder;
        }
    }

    private RequestPreloadSDK() {
        this.isInit = new AtomicBoolean(false);
    }

    public final void destroy(@NotNull String r3) {
        if (!this.isInit.get()) {
            CommonUtil.INSTANCE.logX("SDK\u6ca1\u6709\u6210\u529f\u521d\u59cb\u5316");
            return;
        }
        RequestCacheManager requestCacheManager = this.requestCacheManager;
        if (requestCacheManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestCacheManager");
        }
        requestCacheManager.destroy(r3);
        PerfMonitor perfMonitor = this.perfMonitor;
        if (perfMonitor == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SwitchQueryFetcher.PERFORMANCE_MONITOR);
        }
        perfMonitor.destroy(r3);
    }

    public final void destroyAll() {
        if (!this.isInit.get()) {
            CommonUtil.INSTANCE.logX("SDK\u6ca1\u6709\u6210\u529f\u521d\u59cb\u5316");
            return;
        }
        PolicyManager policyManager = this.policyManager;
        if (policyManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("policyManager");
        }
        policyManager.clear();
        RequestCacheManager requestCacheManager = this.requestCacheManager;
        if (requestCacheManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestCacheManager");
        }
        requestCacheManager.destroyAll();
        PerfMonitor perfMonitor = this.perfMonitor;
        if (perfMonitor == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SwitchQueryFetcher.PERFORMANCE_MONITOR);
        }
        perfMonitor.destroyAll();
    }

    @NotNull
    public final Context getContext() {
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException(AnnoConst.Constructor_Context);
        }
        return context;
    }

    @NotNull
    public final PerfMonitor getPerfMonitor() {
        PerfMonitor perfMonitor = this.perfMonitor;
        if (perfMonitor == null) {
            Intrinsics.throwUninitializedPropertyAccessException(SwitchQueryFetcher.PERFORMANCE_MONITOR);
        }
        return perfMonitor;
    }

    @NotNull
    public final PolicyManager getPolicyManager() {
        PolicyManager policyManager = this.policyManager;
        if (policyManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("policyManager");
        }
        return policyManager;
    }

    @Nullable
    public final CacheItem getPreloadData(@Nullable String appIdOrUrl, @Nullable String requestIdOrUrl, @Nullable String timeStamp) {
        if (!this.isInit.get()) {
            CommonUtil.INSTANCE.logX("SDK\u6ca1\u6709\u6210\u529f\u521d\u59cb\u5316");
            return null;
        }
        if (appIdOrUrl != null && requestIdOrUrl != null) {
            PolicyManager policyManager = this.policyManager;
            if (policyManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("policyManager");
            }
            String appId = policyManager.getAppId(appIdOrUrl);
            PolicyManager policyManager2 = this.policyManager;
            if (policyManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("policyManager");
            }
            String requestId = policyManager2.getRequestId(appIdOrUrl, requestIdOrUrl);
            if (appId != null && requestId != null) {
                RequestCacheManager requestCacheManager = this.requestCacheManager;
                if (requestCacheManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("requestCacheManager");
                }
                return requestCacheManager.getResult(appId, requestId, timeStamp);
            }
        }
        return null;
    }

    @NotNull
    public final RequestCacheManager getRequestCacheManager() {
        RequestCacheManager requestCacheManager = this.requestCacheManager;
        if (requestCacheManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestCacheManager");
        }
        return requestCacheManager;
    }

    public final void init(@Nullable Context context) {
        if (context != null && !this.isInit.get()) {
            this.context = context;
            this.policyManager = new PolicyManager();
            this.requestCacheManager = new RequestCacheManager();
            this.perfMonitor = new PerfMonitor();
            this.isInit.set(true);
            return;
        }
        CommonUtil.INSTANCE.logX("SDK\u521d\u59cb\u5316\u5931\u8d25\uff0c\u56e0\u4e3acontext\u4e3a\u7a7a");
    }

    @NotNull
    /* renamed from: isInit  reason: from getter */
    public final AtomicBoolean getIsInit() {
        return this.isInit;
    }

    public final void parse(@Nullable String str) {
        if (!this.isInit.get()) {
            CommonUtil.INSTANCE.logX("SDK\u6ca1\u6709\u6210\u529f\u521d\u59cb\u5316");
            return;
        }
        PolicyManager policyManager = this.policyManager;
        if (policyManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("policyManager");
        }
        policyManager.parse(str);
    }

    public final void preload(@Nullable String str, @Nullable String str2) {
        if (!this.isInit.get()) {
            CommonUtil.INSTANCE.logX("SDK\u6ca1\u6709\u6210\u529f\u521d\u59cb\u5316");
            return;
        }
        CommonUtil commonUtil = CommonUtil.INSTANCE;
        commonUtil.logX("\u6b63\u5728\u67e5\u627e\u662f\u5426\u5b58\u5728\u63a5\u53e3\u9884\u52a0\u8f7d\u914d\u7f6e\uff0cURL\uff1a" + str);
        if (str != null && !HybridSettings.isPreloadDisable()) {
            PolicyManager policyManager = this.policyManager;
            if (policyManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("policyManager");
            }
            ModuleEntity module = policyManager.getModule(str);
            if (!TextUtils.isEmpty(str2)) {
                PerfMonitor perfMonitor = this.perfMonitor;
                if (perfMonitor == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(SwitchQueryFetcher.PERFORMANCE_MONITOR);
                }
                if (str2 == null) {
                    Intrinsics.throwNpe();
                }
                perfMonitor.addRecord(str2, "prlstart", String.valueOf(System.currentTimeMillis()));
                b.k(HttpDnsConfig.PREDOWNLOAD_PARAMS, "prlstart", String.valueOf(System.currentTimeMillis()));
            }
            if (module == null) {
                commonUtil.logX("\u672a\u627e\u5230\u63a5\u53e3\u9884\u52a0\u8f7d\u914d\u7f6e\uff0cURL\uff1a" + str);
                return;
            }
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException(AnnoConst.Constructor_Context);
            }
            if (context == null) {
                commonUtil.logX("\u5931\u8d25\uff0c\u56e0\u4e3acontext = null");
                return;
            }
            commonUtil.logX("\u627e\u5230\u9884\u52a0\u8f7d\u914d\u7f6e\uff0cURL\uff1a" + module.getH5Url() + ", appId: " + module.getAppId());
            Context context2 = this.context;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(AnnoConst.Constructor_Context);
            }
            if (VersionUtils.isAppVersionBetween(context2, module.getAppMin(), module.getAppMax())) {
                HashMap<String, String> hashMap = new HashMap<>();
                if (commonUtil.isUrl(str)) {
                    hashMap = commonUtil.getQueryMap(str);
                }
                new RequestWorker().run(module.getAppId(), str2, hashMap);
                return;
            }
            Context context3 = this.context;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(AnnoConst.Constructor_Context);
            }
            PackageManager packageManager = context3.getPackageManager();
            Context context4 = this.context;
            if (context4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(AnnoConst.Constructor_Context);
            }
            commonUtil.logX("\u56e0\u672a\u5728\u7248\u672c\u533a\u95f4\u5185, \u5f53\u524d\u7248\u672c=" + packageManager.getPackageInfo(context4.getPackageName(), 0).versionName + ", app_min=" + module.getAppMin() + ", app_max=" + module.getAppMax());
            return;
        }
        commonUtil.logX("\u63a5\u53e3\u9884\u52a0\u8f7d\u53d1\u751f\u964d\u7ea7\u6216\u8005url\u4e3a\u7a7a\uff0cURL\uff1a" + str);
    }

    public final void setContext(@NotNull Context context) {
        this.context = context;
    }

    public final void setInit(@NotNull AtomicBoolean atomicBoolean) {
        this.isInit = atomicBoolean;
    }

    public final void setPerfMonitor(@NotNull PerfMonitor perfMonitor) {
        this.perfMonitor = perfMonitor;
    }

    public final void setPolicyManager(@NotNull PolicyManager policyManager) {
        this.policyManager = policyManager;
    }

    public final void setRequestCacheManager(@NotNull RequestCacheManager requestCacheManager) {
        this.requestCacheManager = requestCacheManager;
    }

    public /* synthetic */ RequestPreloadSDK(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
