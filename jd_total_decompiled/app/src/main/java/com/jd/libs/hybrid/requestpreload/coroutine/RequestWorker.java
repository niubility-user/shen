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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
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
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object a(@NotNull RequestItem requestItem, @NotNull Continuation<? super Unit> continuation) {
        RequestWorker$cancelDependentJob$1 requestWorker$cancelDependentJob$1;
        Object coroutine_suspended;
        int i2;
        RequestWorker requestWorker;
        RequestItem requestItem2;
        Iterator it;
        Object obj;
        if (continuation instanceof RequestWorker$cancelDependentJob$1) {
            requestWorker$cancelDependentJob$1 = (RequestWorker$cancelDependentJob$1) continuation;
            int i3 = requestWorker$cancelDependentJob$1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                requestWorker$cancelDependentJob$1.label = i3 - Integer.MIN_VALUE;
                Object obj2 = requestWorker$cancelDependentJob$1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = requestWorker$cancelDependentJob$1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj2);
                    List<RequestItem> dependBy = requestItem.getDependBy();
                    Iterator it2 = dependBy.iterator();
                    requestWorker = this;
                    requestItem2 = requestItem;
                    it = it2;
                    obj = dependBy;
                    while (it.hasNext()) {
                    }
                    return Unit.INSTANCE;
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    Deferred deferred = (Deferred) requestWorker$cancelDependentJob$1.L$6;
                    RequestItem requestItem3 = (RequestItem) requestWorker$cancelDependentJob$1.L$5;
                    Object obj3 = requestWorker$cancelDependentJob$1.L$4;
                    it = (Iterator) requestWorker$cancelDependentJob$1.L$3;
                    obj = (Iterable) requestWorker$cancelDependentJob$1.L$2;
                    RequestItem requestItem4 = (RequestItem) requestWorker$cancelDependentJob$1.L$1;
                    requestWorker = (RequestWorker) requestWorker$cancelDependentJob$1.L$0;
                    ResultKt.throwOnFailure(obj2);
                    requestItem2 = requestItem4;
                    while (it.hasNext()) {
                        Object next = it.next();
                        RequestItem requestItem5 = (RequestItem) next;
                        Deferred<Unit> c2 = requestWorker.c(requestItem5.getRequestId());
                        CommonUtil.INSTANCE.logX("[RequestWorker] \u53d6\u6d88\u4f9d\u8d56" + requestItem2.getUrl() + "\u7684\u5de5\u4f5c\uff1a" + requestItem5.getUrl());
                        requestWorker.d(requestItem5.getRequestId(), -1, null);
                        requestWorker.e(requestItem5.getRequestId(), -4);
                        if (c2 != null) {
                            requestWorker$cancelDependentJob$1.L$0 = requestWorker;
                            requestWorker$cancelDependentJob$1.L$1 = requestItem2;
                            requestWorker$cancelDependentJob$1.L$2 = obj;
                            requestWorker$cancelDependentJob$1.L$3 = it;
                            requestWorker$cancelDependentJob$1.L$4 = next;
                            requestWorker$cancelDependentJob$1.L$5 = requestItem5;
                            requestWorker$cancelDependentJob$1.L$6 = c2;
                            requestWorker$cancelDependentJob$1.label = 1;
                            if (JobKt.cancelAndJoin(c2, requestWorker$cancelDependentJob$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            requestItem4 = requestItem2;
                            requestItem2 = requestItem4;
                            while (it.hasNext()) {
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
            }
        }
        requestWorker$cancelDependentJob$1 = new RequestWorker$cancelDependentJob$1(this, continuation);
        Object obj22 = requestWorker$cancelDependentJob$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = requestWorker$cancelDependentJob$1.label;
        if (i2 != 0) {
        }
    }

    @NotNull
    public final Job run(@Nullable String appId, @Nullable String timestamp, @Nullable HashMap<String, String> urlQuery) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new RequestWorker$run$1(this, appId, timestamp, urlQuery, null), 3, null);
        return launch$default;
    }
}
