package com.jd.libs.hybrid.requestpreload.coroutine;

import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.libs.hybrid.requestpreload.RequestPreloadSDK;
import com.jd.libs.hybrid.requestpreload.entity.CacheItem;
import com.jd.libs.hybrid.requestpreload.entity.RequestItem;
import com.jd.libs.hybrid.requestpreload.network.RequestCacheManager;
import com.jd.libs.hybrid.requestpreload.utils.CommonUtil;
import com.jd.libs.xdog.b;
import com.jingdong.app.mall.e;
import com.jingdong.jdsdk.a.a;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B\u0007\u00a2\u0006\u0004\b(\u0010\u000fJ)\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u001f\u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u001f\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00122\u0006\u0010\u0011\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0015H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018JI\u0010\u001f\u001a\u00020\u001e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00022\b\u0010\u001a\u001a\u0004\u0018\u00010\u00022&\u0010\u001d\u001a\"\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u001bj\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u0001`\u001c\u00a2\u0006\u0004\b\u001f\u0010 R2\u0010\u001d\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u001bj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002`\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010!R&\u0010%\u001a\u0012\u0012\u0004\u0012\u00020\u00150\"j\b\u0012\u0004\u0012\u00020\u0015`#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010$R\u0016\u0010\u001a\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\f\u0010&R\u0016\u0010\u0019\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010&RB\u0010'\u001a.\u0012\u0004\u0012\u00020\u0002\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u00120\u001bj\u0016\u0012\u0004\u0012\u00020\u0002\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0012`\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010!\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006*"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/coroutine/RequestWorker;", "", "", "requestId", "", "code", "Lcom/jd/libs/hybrid/requestpreload/entity/CacheItem;", "data", "", "d", "(Ljava/lang/String;ILcom/jd/libs/hybrid/requestpreload/entity/CacheItem;)V", "status", e.a, "(Ljava/lang/String;I)V", "b", "()V", "f", CartConstant.KEY_VENDOR_ITEM, "Lkotlinx/coroutines/Deferred;", "c", "(Ljava/lang/String;)Lkotlinx/coroutines/Deferred;", "Lcom/jd/libs/hybrid/requestpreload/entity/RequestItem;", "requestItem", a.a, "(Lcom/jd/libs/hybrid/requestpreload/entity/RequestItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "appId", VerifyTracker.KEY_TIMESTAMP, "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "urlQuery", "Lkotlinx/coroutines/Job;", "run", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/HashMap;)Lkotlinx/coroutines/Job;", "Ljava/util/HashMap;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "Ljava/util/ArrayList;", "requestItems", "Ljava/lang/String;", "jobMap", "<init>", "Companion", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class RequestWorker {
    public static final long JOB_TIMEOUT = 12000;
    @NotNull
    public static final String TAG = "[RequestWorker]";

    /* renamed from: a  reason: from kotlin metadata */
    private volatile HashMap<String, Deferred<Unit>> jobMap = new HashMap<>();

    /* renamed from: b  reason: from kotlin metadata */
    private String appId = "";

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private ArrayList<RequestItem> requestItems = new ArrayList<>();

    /* renamed from: d  reason: from kotlin metadata */
    private HashMap<String, String> urlQuery = new HashMap<>();

    /* renamed from: e  reason: collision with root package name and from kotlin metadata */
    private String timestamp = "";

    /* JADX INFO: Access modifiers changed from: private */
    public final void b() {
        Deferred<Unit> async$default;
        for (RequestItem requestItem : this.requestItems) {
            async$default = BuildersKt__Builders_commonKt.async$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, CoroutineStart.LAZY, new RequestWorker$collectJobs$$inlined$forEach$lambda$1(requestItem, null, this), 1, null);
            this.jobMap.put(requestItem.getRequestId(), async$default);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Deferred<Unit> c(String item) {
        return this.jobMap.get(item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void d(String requestId, int code, CacheItem data) {
        RequestPreloadSDK.Companion companion = RequestPreloadSDK.INSTANCE;
        companion.getInstance().getPerfMonitor().addRecord(this.timestamp, "prlend", String.valueOf(System.currentTimeMillis()));
        b.k(HttpDnsConfig.PREDOWNLOAD_PARAMS, "prlend", String.valueOf(System.currentTimeMillis()));
        RequestCacheManager.Callback callback = companion.getInstance().getRequestCacheManager().getCallback(this.appId, requestId, this.timestamp);
        if (callback != null) {
            callback.onResult(code, data);
            companion.getInstance().getRequestCacheManager().destroy(this.timestamp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void e(String requestId, int status) {
        RequestPreloadSDK.INSTANCE.getInstance().getRequestCacheManager().setStatus(this.appId, requestId, this.timestamp, status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void f() {
        for (Map.Entry<String, Deferred<Unit>> entry : this.jobMap.entrySet()) {
            if (entry.getValue().isCancelled()) {
                CommonUtil.INSTANCE.logX("[RequestWorker] " + entry.getKey() + "\u5de5\u4f5c\u5df2\u53d6\u6d88");
            } else {
                entry.getValue().start();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0062  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00c1 -> B:23:0x00c2). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* synthetic */ java.lang.Object a(@org.jetbrains.annotations.NotNull com.jd.libs.hybrid.requestpreload.entity.RequestItem r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r12 = this;
            boolean r0 = r14 instanceof com.jd.libs.hybrid.requestpreload.coroutine.RequestWorker$cancelDependentJob$1
            if (r0 == 0) goto L13
            r0 = r14
            com.jd.libs.hybrid.requestpreload.coroutine.RequestWorker$cancelDependentJob$1 r0 = (com.jd.libs.hybrid.requestpreload.coroutine.RequestWorker$cancelDependentJob$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.jd.libs.hybrid.requestpreload.coroutine.RequestWorker$cancelDependentJob$1 r0 = new com.jd.libs.hybrid.requestpreload.coroutine.RequestWorker$cancelDependentJob$1
            r0.<init>(r12, r14)
        L18:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L4c
            if (r2 != r3) goto L44
            java.lang.Object r13 = r0.L$6
            kotlinx.coroutines.Deferred r13 = (kotlinx.coroutines.Deferred) r13
            java.lang.Object r13 = r0.L$5
            com.jd.libs.hybrid.requestpreload.entity.RequestItem r13 = (com.jd.libs.hybrid.requestpreload.entity.RequestItem) r13
            java.lang.Object r13 = r0.L$4
            java.lang.Object r13 = r0.L$3
            java.util.Iterator r13 = (java.util.Iterator) r13
            java.lang.Object r2 = r0.L$2
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.lang.Object r4 = r0.L$1
            com.jd.libs.hybrid.requestpreload.entity.RequestItem r4 = (com.jd.libs.hybrid.requestpreload.entity.RequestItem) r4
            java.lang.Object r5 = r0.L$0
            com.jd.libs.hybrid.requestpreload.coroutine.RequestWorker r5 = (com.jd.libs.hybrid.requestpreload.coroutine.RequestWorker) r5
            kotlin.ResultKt.throwOnFailure(r14)
            goto Lc2
        L44:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L4c:
            kotlin.ResultKt.throwOnFailure(r14)
            java.util.List r14 = r13.getDependBy()
            java.util.Iterator r2 = r14.iterator()
            r5 = r12
            r11 = r14
            r14 = r13
            r13 = r2
            r2 = r11
        L5c:
            boolean r4 = r13.hasNext()
            if (r4 == 0) goto Lc4
            java.lang.Object r4 = r13.next()
            r6 = r4
            com.jd.libs.hybrid.requestpreload.entity.RequestItem r6 = (com.jd.libs.hybrid.requestpreload.entity.RequestItem) r6
            java.lang.String r7 = r6.getRequestId()
            kotlinx.coroutines.Deferred r7 = r5.c(r7)
            com.jd.libs.hybrid.requestpreload.utils.CommonUtil r8 = com.jd.libs.hybrid.requestpreload.utils.CommonUtil.INSTANCE
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "[RequestWorker] \u53d6\u6d88\u4f9d\u8d56"
            r9.append(r10)
            java.lang.String r10 = r14.getUrl()
            r9.append(r10)
            java.lang.String r10 = "\u7684\u5de5\u4f5c\uff1a"
            r9.append(r10)
            java.lang.String r10 = r6.getUrl()
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.logX(r9)
            java.lang.String r8 = r6.getRequestId()
            r9 = -1
            r10 = 0
            r5.d(r8, r9, r10)
            java.lang.String r8 = r6.getRequestId()
            r9 = -4
            r5.e(r8, r9)
            if (r7 == 0) goto L5c
            r0.L$0 = r5
            r0.L$1 = r14
            r0.L$2 = r2
            r0.L$3 = r13
            r0.L$4 = r4
            r0.L$5 = r6
            r0.L$6 = r7
            r0.label = r3
            java.lang.Object r4 = kotlinx.coroutines.JobKt.cancelAndJoin(r7, r0)
            if (r4 != r1) goto Lc1
            return r1
        Lc1:
            r4 = r14
        Lc2:
            r14 = r4
            goto L5c
        Lc4:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.requestpreload.coroutine.RequestWorker.a(com.jd.libs.hybrid.requestpreload.entity.RequestItem, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final Job run(@Nullable String appId, @Nullable String timestamp, @Nullable HashMap<String, String> urlQuery) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new RequestWorker$run$1(this, appId, timestamp, urlQuery, null), 3, null);
        return launch$default;
    }
}
