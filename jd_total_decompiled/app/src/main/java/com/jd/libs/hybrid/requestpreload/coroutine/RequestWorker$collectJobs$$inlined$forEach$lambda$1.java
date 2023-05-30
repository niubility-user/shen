package com.jd.libs.hybrid.requestpreload.coroutine;

import android.os.SystemClock;
import com.jd.libs.hybrid.requestpreload.RequestPreloadSDK;
import com.jd.libs.hybrid.requestpreload.dsl.DynamicParamInjector;
import com.jd.libs.hybrid.requestpreload.entity.CacheItem;
import com.jd.libs.hybrid.requestpreload.entity.RequestItem;
import com.jd.libs.hybrid.requestpreload.network.RequestCacheManager;
import com.jd.libs.hybrid.requestpreload.network.RequestHelper;
import com.jd.libs.hybrid.requestpreload.utils.CommonUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\u008a@\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/jd/libs/hybrid/requestpreload/coroutine/RequestWorker$collectJobs$1$job$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class RequestWorker$collectJobs$$inlined$forEach$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ RequestItem $it;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ RequestWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\u008a@\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/jd/libs/hybrid/requestpreload/coroutine/RequestWorker$collectJobs$1$job$1$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* renamed from: com.jd.libs.hybrid.requestpreload.coroutine.RequestWorker$collectJobs$$inlined$forEach$lambda$1$1  reason: invalid class name */
    /* loaded from: classes16.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        private CoroutineScope p$;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x012f A[Catch: Exception -> 0x0222, all -> 0x0495, TryCatch #2 {, blocks: (B:7:0x001d, B:10:0x0032, B:79:0x049d, B:81:0x04a1, B:83:0x0503, B:82:0x04df, B:13:0x0045, B:62:0x039b, B:64:0x03c9, B:66:0x03d5, B:67:0x0438, B:19:0x0071, B:47:0x021f, B:24:0x008f, B:40:0x0187, B:32:0x0129, B:34:0x012f, B:36:0x0144, B:44:0x01be, B:51:0x0225, B:53:0x02a8, B:54:0x02b5, B:56:0x02e8, B:59:0x033b, B:30:0x009f), top: B:96:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:51:0x0225 A[Catch: Exception -> 0x0222, all -> 0x0495, TryCatch #2 {, blocks: (B:7:0x001d, B:10:0x0032, B:79:0x049d, B:81:0x04a1, B:83:0x0503, B:82:0x04df, B:13:0x0045, B:62:0x039b, B:64:0x03c9, B:66:0x03d5, B:67:0x0438, B:19:0x0071, B:47:0x021f, B:24:0x008f, B:40:0x0187, B:32:0x0129, B:34:0x012f, B:36:0x0144, B:44:0x01be, B:51:0x0225, B:53:0x02a8, B:54:0x02b5, B:56:0x02e8, B:59:0x033b, B:30:0x009f), top: B:96:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:66:0x03d5 A[Catch: Exception -> 0x048e, all -> 0x0495, TryCatch #1 {Exception -> 0x048e, blocks: (B:64:0x03c9, B:66:0x03d5, B:67:0x0438), top: B:94:0x03c9 }] */
        /* JADX WARN: Removed duplicated region for block: B:67:0x0438 A[Catch: Exception -> 0x048e, all -> 0x0495, TRY_LEAVE, TryCatch #1 {Exception -> 0x048e, blocks: (B:64:0x03c9, B:66:0x03d5, B:67:0x0438), top: B:94:0x03c9 }] */
        /* JADX WARN: Removed duplicated region for block: B:81:0x04a1 A[Catch: all -> 0x0495, TryCatch #2 {, blocks: (B:7:0x001d, B:10:0x0032, B:79:0x049d, B:81:0x04a1, B:83:0x0503, B:82:0x04df, B:13:0x0045, B:62:0x039b, B:64:0x03c9, B:66:0x03d5, B:67:0x0438, B:19:0x0071, B:47:0x021f, B:24:0x008f, B:40:0x0187, B:32:0x0129, B:34:0x012f, B:36:0x0144, B:44:0x01be, B:51:0x0225, B:53:0x02a8, B:54:0x02b5, B:56:0x02e8, B:59:0x033b, B:30:0x009f), top: B:96:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:82:0x04df A[Catch: all -> 0x0495, TryCatch #2 {, blocks: (B:7:0x001d, B:10:0x0032, B:79:0x049d, B:81:0x04a1, B:83:0x0503, B:82:0x04df, B:13:0x0045, B:62:0x039b, B:64:0x03c9, B:66:0x03d5, B:67:0x0438, B:19:0x0071, B:47:0x021f, B:24:0x008f, B:40:0x0187, B:32:0x0129, B:34:0x012f, B:36:0x0144, B:44:0x01be, B:51:0x0225, B:53:0x02a8, B:54:0x02b5, B:56:0x02e8, B:59:0x033b, B:30:0x009f), top: B:96:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:85:0x0533 A[RETURN] */
        /* JADX WARN: Type inference failed for: r3v0, types: [int] */
        /* JADX WARN: Type inference failed for: r3v1 */
        /* JADX WARN: Type inference failed for: r3v18 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0184 -> B:99:0x0187). Please submit an issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended;
            AnonymousClass1 anonymousClass1;
            CoroutineScope coroutineScope;
            Exception exc;
            CoroutineScope coroutineScope2;
            RequestWorker requestWorker;
            RequestItem requestItem;
            CoroutineScope coroutineScope3;
            String str;
            String str2;
            Object obj2;
            Iterator it;
            long j2;
            DynamicParamInjector dynamicParamInjector;
            String str3;
            String str4;
            HashMap hashMap;
            LinkedHashMap<String, String> linkedHashMap;
            long currentTimeMillis;
            Object doRequestAsync;
            Deferred c2;
            AnonymousClass1 anonymousClass12;
            CoroutineScope coroutineScope4;
            RequestItem requestItem2;
            CacheItem cacheItem;
            CommonUtil commonUtil;
            String str5;
            String str6;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r3 = this.label;
            try {
            } catch (Exception e2) {
                e = e2;
            }
            switch (r3) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    coroutineScope3 = this.p$;
                    try {
                        CommonUtil commonUtil2 = CommonUtil.INSTANCE;
                        StringBuilder sb = new StringBuilder();
                        sb.append("[RequestWorker] \u63a5\u53e3\u9884\u52a0\u8f7d[");
                        str = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.this$0.appId;
                        sb.append(str);
                        sb.append('_');
                        sb.append(RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getRequestId());
                        sb.append('_');
                        str2 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.this$0.timestamp;
                        sb.append(str2);
                        sb.append("]\u5de5\u4f5c\u542f\u52a8, url=");
                        sb.append(RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getUrl());
                        commonUtil2.logX(sb.toString());
                        commonUtil2.logX("\u5de5\u4f5c\u5f00\u59cb\u65f6\u95f4\uff1a" + new Date());
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$1 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                        requestWorker$collectJobs$$inlined$forEach$lambda$1.this$0.e(requestWorker$collectJobs$$inlined$forEach$lambda$1.$it.getRequestId(), 0);
                        List<RequestItem> dependOn = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getDependOn();
                        anonymousClass1 = this;
                        obj2 = dependOn;
                        it = dependOn.iterator();
                        j2 = elapsedRealtime;
                        try {
                        } catch (Exception e3) {
                            e = e3;
                            coroutineScope = coroutineScope3;
                            exc = e;
                            coroutineScope2 = coroutineScope;
                            if (exc instanceof TimeoutCancellationException) {
                            }
                            RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$12 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                            requestWorker$collectJobs$$inlined$forEach$lambda$12.this$0.e(requestWorker$collectJobs$$inlined$forEach$lambda$12.$it.getRequestId(), -4);
                            RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$13 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                            requestWorker$collectJobs$$inlined$forEach$lambda$13.this$0.d(requestWorker$collectJobs$$inlined$forEach$lambda$13.$it.getRequestId(), -1, null);
                            RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$14 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                            requestWorker = requestWorker$collectJobs$$inlined$forEach$lambda$14.this$0;
                            requestItem = requestWorker$collectJobs$$inlined$forEach$lambda$14.$it;
                            anonymousClass1.L$0 = coroutineScope2;
                            anonymousClass1.L$1 = exc;
                            anonymousClass1.label = 6;
                            if (requestWorker.a(requestItem, anonymousClass1) == coroutine_suspended) {
                            }
                            return Unit.INSTANCE;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        anonymousClass1 = this;
                        coroutineScope = coroutineScope3;
                        exc = e;
                        coroutineScope2 = coroutineScope;
                        if (exc instanceof TimeoutCancellationException) {
                        }
                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$122 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                        requestWorker$collectJobs$$inlined$forEach$lambda$122.this$0.e(requestWorker$collectJobs$$inlined$forEach$lambda$122.$it.getRequestId(), -4);
                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$132 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                        requestWorker$collectJobs$$inlined$forEach$lambda$132.this$0.d(requestWorker$collectJobs$$inlined$forEach$lambda$132.$it.getRequestId(), -1, null);
                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$142 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                        requestWorker = requestWorker$collectJobs$$inlined$forEach$lambda$142.this$0;
                        requestItem = requestWorker$collectJobs$$inlined$forEach$lambda$142.$it;
                        anonymousClass1.L$0 = coroutineScope2;
                        anonymousClass1.L$1 = exc;
                        anonymousClass1.label = 6;
                        if (requestWorker.a(requestItem, anonymousClass1) == coroutine_suspended) {
                        }
                        return Unit.INSTANCE;
                    }
                    if (it.hasNext()) {
                        Object next = it.next();
                        RequestItem requestItem3 = (RequestItem) next;
                        c2 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.this$0.c(requestItem3.getRequestId());
                        if (c2 != null) {
                            CommonUtil.INSTANCE.logX("[RequestWorker] " + RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getUrl() + "\u5de5\u4f5c\u5f00\u59cb\u7b49\u5f85" + requestItem3.getUrl());
                            anonymousClass1.L$0 = coroutineScope3;
                            anonymousClass1.J$0 = j2;
                            anonymousClass1.L$1 = obj2;
                            anonymousClass1.L$2 = it;
                            anonymousClass1.L$3 = next;
                            anonymousClass1.L$4 = requestItem3;
                            anonymousClass1.L$5 = c2;
                            anonymousClass1.label = 1;
                            if (c2.await(anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            anonymousClass12 = anonymousClass1;
                            coroutineScope4 = coroutineScope3;
                            requestItem2 = requestItem3;
                            try {
                                CommonUtil.INSTANCE.logX("[RequestWorker] " + RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getUrl() + "\u5de5\u4f5c\u7b49\u5f85" + requestItem2.getUrl() + "\u5b8c\u6bd5");
                                coroutineScope3 = coroutineScope4;
                                anonymousClass1 = anonymousClass12;
                            } catch (Exception e5) {
                                exc = e5;
                                coroutineScope2 = coroutineScope4;
                                anonymousClass1 = anonymousClass12;
                                if (exc instanceof TimeoutCancellationException) {
                                }
                                RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$1222 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                requestWorker$collectJobs$$inlined$forEach$lambda$1222.this$0.e(requestWorker$collectJobs$$inlined$forEach$lambda$1222.$it.getRequestId(), -4);
                                RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$1322 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                requestWorker$collectJobs$$inlined$forEach$lambda$1322.this$0.d(requestWorker$collectJobs$$inlined$forEach$lambda$1322.$it.getRequestId(), -1, null);
                                RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$1422 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                requestWorker = requestWorker$collectJobs$$inlined$forEach$lambda$1422.this$0;
                                requestItem = requestWorker$collectJobs$$inlined$forEach$lambda$1422.$it;
                                anonymousClass1.L$0 = coroutineScope2;
                                anonymousClass1.L$1 = exc;
                                anonymousClass1.label = 6;
                                if (requestWorker.a(requestItem, anonymousClass1) == coroutine_suspended) {
                                }
                                return Unit.INSTANCE;
                            }
                            if (it.hasNext()) {
                                CommonUtil commonUtil3 = CommonUtil.INSTANCE;
                                commonUtil3.logX("[RequestWorker] \u4f9d\u8d56\u63a5\u53e3\u6570: " + RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getDependOn().size() + ", \u7b49\u5f85\u8017\u65f6\uff1a" + (SystemClock.elapsedRealtime() - j2));
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("[RequestWorker] ");
                                sb2.append(RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getUrl());
                                sb2.append("\u5de5\u4f5c\u8fdb\u884c\u4e2d\uff1a\u5f00\u59cb\u53c2\u6570\u89e3\u6790");
                                commonUtil3.logX(sb2.toString());
                                long elapsedRealtime2 = SystemClock.elapsedRealtime();
                                str3 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.this$0.appId;
                                str4 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.this$0.timestamp;
                                hashMap = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.this$0.urlQuery;
                                dynamicParamInjector = new DynamicParamInjector(str3, str4, hashMap);
                                linkedHashMap = (LinkedHashMap) dynamicParamInjector.injectParams(RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getParamsMap());
                                if (dynamicParamInjector.getNeedCustom()) {
                                    dynamicParamInjector.setParamsDe(commonUtil3.getJsonParams(linkedHashMap));
                                    linkedHashMap = (LinkedHashMap) dynamicParamInjector.injectParams(linkedHashMap);
                                }
                                commonUtil3.logX("[RequestWorker] \u89e3\u6790\u53c2\u6570\u8017\u65f6: " + (SystemClock.elapsedRealtime() - elapsedRealtime2));
                                commonUtil3.logX("[RequestWorker] \u6700\u7ec8\u53c2\u6570\u4e3a: " + linkedHashMap);
                                if (!dynamicParamInjector.getIsSuccess()) {
                                    commonUtil3.logX("[RequestWorker] " + RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getUrl() + "\u53c2\u6570\u89e3\u6790\u5f02\u5e38");
                                    RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$15 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                    requestWorker$collectJobs$$inlined$forEach$lambda$15.this$0.e(requestWorker$collectJobs$$inlined$forEach$lambda$15.$it.getRequestId(), -3);
                                    RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$16 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                    requestWorker$collectJobs$$inlined$forEach$lambda$16.this$0.d(requestWorker$collectJobs$$inlined$forEach$lambda$16.$it.getRequestId(), -3, null);
                                    RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$17 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                    RequestWorker requestWorker2 = requestWorker$collectJobs$$inlined$forEach$lambda$17.this$0;
                                    RequestItem requestItem4 = requestWorker$collectJobs$$inlined$forEach$lambda$17.$it;
                                    anonymousClass1.L$0 = coroutineScope3;
                                    anonymousClass1.J$0 = elapsedRealtime2;
                                    anonymousClass1.L$1 = dynamicParamInjector;
                                    anonymousClass1.L$2 = linkedHashMap;
                                    anonymousClass1.label = 3;
                                    if (requestWorker2.a(requestItem4, anonymousClass1) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    return Unit.INSTANCE;
                                }
                                commonUtil3.logX("[RequestWorker] " + RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getUrl() + "\u5de5\u4f5c\u8fdb\u884c\u4e2d\uff1a\u5f00\u59cb\u6570\u636e\u8bf7\u6c42");
                                currentTimeMillis = System.currentTimeMillis();
                                commonUtil3.logX("\u63a5\u53e3\u8bf7\u6c42\u5f00\u59cb\u65f6\u95f4\uff1a" + new Date());
                                RequestHelper requestHelper = RequestHelper.INSTANCE;
                                String url = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getUrl();
                                String method = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getMethod();
                                anonymousClass1.L$0 = coroutineScope3;
                                anonymousClass1.J$0 = currentTimeMillis;
                                anonymousClass1.L$1 = dynamicParamInjector;
                                anonymousClass1.L$2 = linkedHashMap;
                                anonymousClass1.label = 4;
                                doRequestAsync = requestHelper.doRequestAsync(url, method, linkedHashMap, anonymousClass1);
                                if (doRequestAsync == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                try {
                                    cacheItem = (CacheItem) doRequestAsync;
                                    commonUtil = CommonUtil.INSTANCE;
                                    commonUtil.logX("\u63a5\u53e3\u8bf7\u6c42\u7ed3\u675f\u65f6\u95f4\uff1a" + new Date());
                                    StringBuilder sb3 = new StringBuilder();
                                    sb3.append("[RequestWorker] \u6570\u636e\u8bf7\u6c42\u8017\u65f6: ");
                                } catch (Exception e6) {
                                    e = e6;
                                    coroutineScope = coroutineScope3;
                                    exc = e;
                                    coroutineScope2 = coroutineScope;
                                    if (exc instanceof TimeoutCancellationException) {
                                    }
                                    RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$12222 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                    requestWorker$collectJobs$$inlined$forEach$lambda$12222.this$0.e(requestWorker$collectJobs$$inlined$forEach$lambda$12222.$it.getRequestId(), -4);
                                    RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$13222 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                    requestWorker$collectJobs$$inlined$forEach$lambda$13222.this$0.d(requestWorker$collectJobs$$inlined$forEach$lambda$13222.$it.getRequestId(), -1, null);
                                    RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$14222 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                    requestWorker = requestWorker$collectJobs$$inlined$forEach$lambda$14222.this$0;
                                    requestItem = requestWorker$collectJobs$$inlined$forEach$lambda$14222.$it;
                                    anonymousClass1.L$0 = coroutineScope2;
                                    anonymousClass1.L$1 = exc;
                                    anonymousClass1.label = 6;
                                    if (requestWorker.a(requestItem, anonymousClass1) == coroutine_suspended) {
                                    }
                                    return Unit.INSTANCE;
                                }
                                try {
                                    sb3.append(System.currentTimeMillis() - currentTimeMillis);
                                    commonUtil.logX(sb3.toString());
                                    if (cacheItem == null) {
                                        RequestCacheManager requestCacheManager = RequestPreloadSDK.INSTANCE.getInstance().getRequestCacheManager();
                                        str5 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.this$0.appId;
                                        String requestId = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getRequestId();
                                        str6 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.this$0.timestamp;
                                        requestCacheManager.putResult(str5, requestId, str6, cacheItem);
                                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$18 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                        requestWorker$collectJobs$$inlined$forEach$lambda$18.this$0.e(requestWorker$collectJobs$$inlined$forEach$lambda$18.$it.getRequestId(), 1);
                                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$19 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                        requestWorker$collectJobs$$inlined$forEach$lambda$19.this$0.d(requestWorker$collectJobs$$inlined$forEach$lambda$19.$it.getRequestId(), 200, cacheItem);
                                        commonUtil.logX("[RequestWorker] " + RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getUrl() + "\u5de5\u4f5c\u7ed3\u675f\uff1a\u5de5\u4f5c\u6570\u636e\u8bf7\u6c42\u6210\u529f");
                                    } else {
                                        commonUtil.logX("[RequestWorker] " + RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getUrl() + "\u5de5\u4f5c\u7ed3\u675f\uff1a\u5de5\u4f5c\u6570\u636e\u8bf7\u6c42\u5931\u8d25");
                                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$110 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                        requestWorker$collectJobs$$inlined$forEach$lambda$110.this$0.e(requestWorker$collectJobs$$inlined$forEach$lambda$110.$it.getRequestId(), 2);
                                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$111 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                        requestWorker$collectJobs$$inlined$forEach$lambda$111.this$0.d(requestWorker$collectJobs$$inlined$forEach$lambda$111.$it.getRequestId(), -1, null);
                                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$112 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                        RequestWorker requestWorker3 = requestWorker$collectJobs$$inlined$forEach$lambda$112.this$0;
                                        RequestItem requestItem5 = requestWorker$collectJobs$$inlined$forEach$lambda$112.$it;
                                        anonymousClass1.L$0 = coroutineScope3;
                                        anonymousClass1.J$0 = currentTimeMillis;
                                        anonymousClass1.L$1 = dynamicParamInjector;
                                        anonymousClass1.L$2 = linkedHashMap;
                                        anonymousClass1.L$3 = cacheItem;
                                        anonymousClass1.label = 5;
                                        if (requestWorker3.a(requestItem5, anonymousClass1) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                } catch (Exception e7) {
                                    exc = e7;
                                    coroutine_suspended = coroutine_suspended;
                                    coroutineScope2 = coroutineScope3;
                                    if (exc instanceof TimeoutCancellationException) {
                                    }
                                    RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$122222 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                    requestWorker$collectJobs$$inlined$forEach$lambda$122222.this$0.e(requestWorker$collectJobs$$inlined$forEach$lambda$122222.$it.getRequestId(), -4);
                                    RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$132222 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                    requestWorker$collectJobs$$inlined$forEach$lambda$132222.this$0.d(requestWorker$collectJobs$$inlined$forEach$lambda$132222.$it.getRequestId(), -1, null);
                                    RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$142222 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                                    requestWorker = requestWorker$collectJobs$$inlined$forEach$lambda$142222.this$0;
                                    requestItem = requestWorker$collectJobs$$inlined$forEach$lambda$142222.$it;
                                    anonymousClass1.L$0 = coroutineScope2;
                                    anonymousClass1.L$1 = exc;
                                    anonymousClass1.label = 6;
                                    if (requestWorker.a(requestItem, anonymousClass1) == coroutine_suspended) {
                                    }
                                    return Unit.INSTANCE;
                                }
                                return Unit.INSTANCE;
                            }
                        } else {
                            CommonUtil.INSTANCE.logX("[RequestWorker] " + RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getUrl() + "\u5de5\u4f5c\u505c\u6b62\uff1a\u56e0\u6ca1\u6709\u627e\u5230\u6240\u4f9d\u8d56\u7684\u5de5\u4f5c" + requestItem3.getUrl());
                            RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$113 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                            requestWorker$collectJobs$$inlined$forEach$lambda$113.this$0.e(requestWorker$collectJobs$$inlined$forEach$lambda$113.$it.getRequestId(), -4);
                            RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$114 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                            requestWorker$collectJobs$$inlined$forEach$lambda$114.this$0.d(requestWorker$collectJobs$$inlined$forEach$lambda$114.$it.getRequestId(), -1, null);
                            RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$115 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                            RequestWorker requestWorker4 = requestWorker$collectJobs$$inlined$forEach$lambda$115.this$0;
                            RequestItem requestItem6 = requestWorker$collectJobs$$inlined$forEach$lambda$115.$it;
                            anonymousClass1.L$0 = coroutineScope3;
                            anonymousClass1.J$0 = j2;
                            anonymousClass1.L$1 = obj2;
                            anonymousClass1.L$2 = next;
                            anonymousClass1.L$3 = requestItem3;
                            anonymousClass1.L$4 = c2;
                            anonymousClass1.label = 2;
                            if (requestWorker4.a(requestItem6, anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        }
                    }
                    break;
                case 1:
                    Deferred deferred = (Deferred) this.L$5;
                    requestItem2 = (RequestItem) this.L$4;
                    it = (Iterator) this.L$2;
                    obj2 = (Iterable) this.L$1;
                    j2 = this.J$0;
                    coroutineScope4 = (CoroutineScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        anonymousClass12 = this;
                        CommonUtil.INSTANCE.logX("[RequestWorker] " + RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getUrl() + "\u5de5\u4f5c\u7b49\u5f85" + requestItem2.getUrl() + "\u5b8c\u6bd5");
                        coroutineScope3 = coroutineScope4;
                        anonymousClass1 = anonymousClass12;
                        if (it.hasNext()) {
                        }
                    } catch (Exception e8) {
                        e = e8;
                        r3 = coroutineScope4;
                        anonymousClass1 = this;
                        coroutineScope = r3;
                        exc = e;
                        coroutineScope2 = coroutineScope;
                        if (exc instanceof TimeoutCancellationException) {
                        }
                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$1222222 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                        requestWorker$collectJobs$$inlined$forEach$lambda$1222222.this$0.e(requestWorker$collectJobs$$inlined$forEach$lambda$1222222.$it.getRequestId(), -4);
                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$1322222 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                        requestWorker$collectJobs$$inlined$forEach$lambda$1322222.this$0.d(requestWorker$collectJobs$$inlined$forEach$lambda$1322222.$it.getRequestId(), -1, null);
                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$1422222 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                        requestWorker = requestWorker$collectJobs$$inlined$forEach$lambda$1422222.this$0;
                        requestItem = requestWorker$collectJobs$$inlined$forEach$lambda$1422222.$it;
                        anonymousClass1.L$0 = coroutineScope2;
                        anonymousClass1.L$1 = exc;
                        anonymousClass1.label = 6;
                        if (requestWorker.a(requestItem, anonymousClass1) == coroutine_suspended) {
                        }
                        return Unit.INSTANCE;
                    }
                    break;
                case 2:
                    Deferred deferred2 = (Deferred) this.L$4;
                    RequestItem requestItem7 = (RequestItem) this.L$3;
                    Iterable iterable = (Iterable) this.L$1;
                    CoroutineScope coroutineScope5 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                case 3:
                    LinkedHashMap linkedHashMap2 = (LinkedHashMap) this.L$2;
                    DynamicParamInjector dynamicParamInjector2 = (DynamicParamInjector) this.L$1;
                    CoroutineScope coroutineScope6 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                case 4:
                    LinkedHashMap<String, String> linkedHashMap3 = (LinkedHashMap) this.L$2;
                    dynamicParamInjector = (DynamicParamInjector) this.L$1;
                    currentTimeMillis = this.J$0;
                    CoroutineScope coroutineScope7 = (CoroutineScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        doRequestAsync = obj;
                        anonymousClass1 = this;
                        linkedHashMap = linkedHashMap3;
                        coroutineScope3 = coroutineScope7;
                        cacheItem = (CacheItem) doRequestAsync;
                        commonUtil = CommonUtil.INSTANCE;
                        commonUtil.logX("\u63a5\u53e3\u8bf7\u6c42\u7ed3\u675f\u65f6\u95f4\uff1a" + new Date());
                        StringBuilder sb32 = new StringBuilder();
                        sb32.append("[RequestWorker] \u6570\u636e\u8bf7\u6c42\u8017\u65f6: ");
                        sb32.append(System.currentTimeMillis() - currentTimeMillis);
                        commonUtil.logX(sb32.toString());
                        if (cacheItem == null) {
                        }
                    } catch (Exception e9) {
                        e = e9;
                        anonymousClass1 = this;
                        coroutineScope = coroutineScope7;
                        exc = e;
                        coroutineScope2 = coroutineScope;
                        if (exc instanceof TimeoutCancellationException) {
                            CommonUtil commonUtil4 = CommonUtil.INSTANCE;
                            commonUtil4.logX("[RequestWorker] " + RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getUrl() + "\u5de5\u4f5c\u5df2\u56e0\u8d85\u65f6\u53d6\u6d88\uff1a" + exc);
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append("\u8d85\u65f6\u65f6\u95f4\uff1a");
                            sb4.append(new Date());
                            commonUtil4.logX(sb4.toString());
                        } else {
                            CommonUtil.INSTANCE.logX("[RequestWorker] " + RequestWorker$collectJobs$$inlined$forEach$lambda$1.this.$it.getUrl() + "\u5de5\u4f5c\u5df2\u53d6\u6d88\uff1a" + exc);
                        }
                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$12222222 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                        requestWorker$collectJobs$$inlined$forEach$lambda$12222222.this$0.e(requestWorker$collectJobs$$inlined$forEach$lambda$12222222.$it.getRequestId(), -4);
                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$13222222 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                        requestWorker$collectJobs$$inlined$forEach$lambda$13222222.this$0.d(requestWorker$collectJobs$$inlined$forEach$lambda$13222222.$it.getRequestId(), -1, null);
                        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$14222222 = RequestWorker$collectJobs$$inlined$forEach$lambda$1.this;
                        requestWorker = requestWorker$collectJobs$$inlined$forEach$lambda$14222222.this$0;
                        requestItem = requestWorker$collectJobs$$inlined$forEach$lambda$14222222.$it;
                        anonymousClass1.L$0 = coroutineScope2;
                        anonymousClass1.L$1 = exc;
                        anonymousClass1.label = 6;
                        if (requestWorker.a(requestItem, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                case 5:
                    CacheItem cacheItem2 = (CacheItem) this.L$3;
                    LinkedHashMap linkedHashMap4 = (LinkedHashMap) this.L$2;
                    DynamicParamInjector dynamicParamInjector3 = (DynamicParamInjector) this.L$1;
                    CoroutineScope coroutineScope8 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                case 6:
                    Exception exc2 = (Exception) this.L$1;
                    CoroutineScope coroutineScope9 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RequestWorker$collectJobs$$inlined$forEach$lambda$1(RequestItem requestItem, Continuation continuation, RequestWorker requestWorker) {
        super(2, continuation);
        this.$it = requestItem;
        this.this$0 = requestWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        RequestWorker$collectJobs$$inlined$forEach$lambda$1 requestWorker$collectJobs$$inlined$forEach$lambda$1 = new RequestWorker$collectJobs$$inlined$forEach$lambda$1(this.$it, continuation, this.this$0);
        requestWorker$collectJobs$$inlined$forEach$lambda$1.p$ = (CoroutineScope) obj;
        return requestWorker$collectJobs$$inlined$forEach$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RequestWorker$collectJobs$$inlined$forEach$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (TimeoutKt.withTimeout(RequestWorker.JOB_TIMEOUT, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
