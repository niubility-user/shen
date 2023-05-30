package com.jingdong.pdj.libdjbasecore.utils;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
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
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        FlowCollector flowCollector;
        DjRVTimer$createCountDownJob$2 djRVTimer$createCountDownJob$2;
        Job job;
        int i2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            flowCollector = this.p$;
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
            flowCollector = (FlowCollector) this.L$0;
            djRVTimer$createCountDownJob$2 = this;
            djRVTimer$createCountDownJob$2.L$0 = flowCollector;
            djRVTimer$createCountDownJob$2.label = 2;
            if (DelayKt.delay(1000L, djRVTimer$createCountDownJob$2) == coroutine_suspended) {
                return coroutine_suspended;
            }
            job = djRVTimer$createCountDownJob$2.this$0.timerJob;
            if (job == null && job.isActive()) {
                DjRVTimer djRVTimer = djRVTimer$createCountDownJob$2.this$0;
                i2 = djRVTimer.count;
                djRVTimer.count = i2 + 1;
                Integer boxInt = Boxing.boxInt(i2);
                djRVTimer$createCountDownJob$2.L$0 = flowCollector;
                djRVTimer$createCountDownJob$2.label = 1;
                if (flowCollector.emit(boxInt, djRVTimer$createCountDownJob$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                djRVTimer$createCountDownJob$2.L$0 = flowCollector;
                djRVTimer$createCountDownJob$2.label = 2;
                if (DelayKt.delay(1000L, djRVTimer$createCountDownJob$2) == coroutine_suspended) {
                }
                job = djRVTimer$createCountDownJob$2.this$0.timerJob;
                if (job == null) {
                }
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        } else if (i3 != 2) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
            flowCollector = (FlowCollector) this.L$0;
        }
        djRVTimer$createCountDownJob$2 = this;
        job = djRVTimer$createCountDownJob$2.this$0.timerJob;
        if (job == null) {
        }
        return Unit.INSTANCE;
    }
}
