package com.jingdong.pdj.libdjbasecore.utils;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u008a@\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lkotlinx/coroutines/flow/FlowCollector;", "", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jingdong.pdj.libdjbasecore.utils.DjRVTimer$createCountDownJob$2", f = "DjRVTimer.kt", i = {0, 1}, l = {79, 80}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
/* loaded from: classes7.dex */
public final class DjRVTimer$createCountDownJob$2 extends SuspendLambda implements Function2<FlowCollector<? super Integer>, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    private FlowCollector p$;
    final /* synthetic */ DjRVTimer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DjRVTimer$createCountDownJob$2(DjRVTimer djRVTimer, Continuation continuation) {
        super(2, continuation);
        this.this$0 = djRVTimer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        DjRVTimer$createCountDownJob$2 djRVTimer$createCountDownJob$2 = new DjRVTimer$createCountDownJob$2(this.this$0, continuation);
        djRVTimer$createCountDownJob$2.p$ = (FlowCollector) obj;
        return djRVTimer$createCountDownJob$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(FlowCollector<? super Integer> flowCollector, Continuation<? super Unit> continuation) {
        return ((DjRVTimer$createCountDownJob$2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0063 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0061 -> B:12:0x002f). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L29
            if (r1 == r3) goto L1f
            if (r1 != r2) goto L17
            java.lang.Object r1 = r7.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r1
            goto L2e
        L17:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L1f:
            java.lang.Object r1 = r7.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r1
            r1 = r7
            goto L57
        L29:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.flow.FlowCollector r8 = r7.p$
        L2e:
            r1 = r7
        L2f:
            com.jingdong.pdj.libdjbasecore.utils.DjRVTimer r4 = r1.this$0
            kotlinx.coroutines.Job r4 = com.jingdong.pdj.libdjbasecore.utils.DjRVTimer.access$getTimerJob$p(r4)
            if (r4 == 0) goto L64
            boolean r4 = r4.isActive()
            if (r4 != r3) goto L64
            com.jingdong.pdj.libdjbasecore.utils.DjRVTimer r4 = r1.this$0
            int r5 = com.jingdong.pdj.libdjbasecore.utils.DjRVTimer.access$getCount$p(r4)
            int r6 = r5 + 1
            com.jingdong.pdj.libdjbasecore.utils.DjRVTimer.access$setCount$p(r4, r6)
            java.lang.Integer r4 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            r1.L$0 = r8
            r1.label = r3
            java.lang.Object r4 = r8.emit(r4, r1)
            if (r4 != r0) goto L57
            return r0
        L57:
            r4 = 1000(0x3e8, double:4.94E-321)
            r1.L$0 = r8
            r1.label = r2
            java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r4, r1)
            if (r4 != r0) goto L2f
            return r0
        L64:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.pdj.libdjbasecore.utils.DjRVTimer$createCountDownJob$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
