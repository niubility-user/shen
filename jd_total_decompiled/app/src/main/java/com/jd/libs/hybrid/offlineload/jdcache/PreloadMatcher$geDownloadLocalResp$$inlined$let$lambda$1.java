package com.jd.libs.hybrid.offlineload.jdcache;

import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.offlineload.jdcache.PreloadMatcher;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.channels.Channel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/jd/jdcache/entity/JDCacheLocalResp;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/jd/libs/hybrid/offlineload/jdcache/PreloadMatcher$geDownloadLocalResp$1$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class PreloadMatcher$geDownloadLocalResp$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super JDCacheLocalResp>, Object> {
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ PreloadMatcher this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/jd/jdcache/entity/JDCacheLocalResp;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/jd/libs/hybrid/offlineload/jdcache/PreloadMatcher$geDownloadLocalResp$1$1$2", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* renamed from: com.jd.libs.hybrid.offlineload.jdcache.PreloadMatcher$geDownloadLocalResp$$inlined$let$lambda$1$1  reason: invalid class name */
    /* loaded from: classes16.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super JDCacheLocalResp>, Object> {
        Object L$0;
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
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super JDCacheLocalResp> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended;
            Channel waitingChannel;
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                waitingChannel = PreloadMatcher$geDownloadLocalResp$$inlined$let$lambda$1.this.this$0.getWaitingChannel();
                if (waitingChannel == null) {
                    return null;
                }
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = waitingChannel.receive(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            return (JDCacheLocalResp) obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PreloadMatcher$geDownloadLocalResp$$inlined$let$lambda$1(Continuation continuation, PreloadMatcher preloadMatcher) {
        super(2, continuation);
        this.this$0 = preloadMatcher;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        PreloadMatcher$geDownloadLocalResp$$inlined$let$lambda$1 preloadMatcher$geDownloadLocalResp$$inlined$let$lambda$1 = new PreloadMatcher$geDownloadLocalResp$$inlined$let$lambda$1(continuation, this.this$0);
        preloadMatcher$geDownloadLocalResp$$inlined$let$lambda$1.p$ = (CoroutineScope) obj;
        return preloadMatcher$geDownloadLocalResp$$inlined$let$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super JDCacheLocalResp> continuation) {
        return ((PreloadMatcher$geDownloadLocalResp$$inlined$let$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        PreloadMatcher.Companion unused;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                if (jDCacheLog.getCanLog()) {
                    jDCacheLog.d(this.this$0.getName(), "Waiting for receiving pre-download html file.");
                }
                long j2 = HybridSettings.MAX_HTML_PRE_FETCH_TIME;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(null);
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = TimeoutKt.withTimeout(j2, anonymousClass1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            return (JDCacheLocalResp) obj;
        } catch (TimeoutCancellationException unused2) {
            JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
            if (jDCacheLog2.getCanLog()) {
                jDCacheLog2.d(this.this$0.getName(), "Timeout in receiving pre-download html file.");
            }
            PreloadMatcher preloadMatcher = this.this$0;
            unused = PreloadMatcher.INSTANCE;
            preloadMatcher.state = -2;
            return null;
        } catch (Exception e2) {
            JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
            if (jDCacheLog3.getCanLog()) {
                jDCacheLog3.e(this.this$0.getName(), "Error in receiving pre-download html file, e = " + e2);
                return null;
            }
            return null;
        }
    }
}
