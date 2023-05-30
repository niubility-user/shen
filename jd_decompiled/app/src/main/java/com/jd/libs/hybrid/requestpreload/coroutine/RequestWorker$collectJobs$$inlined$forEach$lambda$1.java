package com.jd.libs.hybrid.requestpreload.coroutine;

import com.jd.libs.hybrid.requestpreload.entity.RequestItem;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
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
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r21) {
            /*
                Method dump skipped, instructions count: 1356
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.requestpreload.coroutine.RequestWorker$collectJobs$$inlined$forEach$lambda$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
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
