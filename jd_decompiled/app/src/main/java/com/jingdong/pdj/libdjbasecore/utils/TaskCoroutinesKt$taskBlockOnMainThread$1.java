package com.jingdong.pdj.libdjbasecore.utils;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u008a@\u00a2\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jingdong.pdj.libdjbasecore.utils.TaskCoroutinesKt$taskBlockOnMainThread$1", f = "TaskCoroutines.kt", i = {0, 1}, l = {32, 33}, m = "invokeSuspend", n = {"$this$runBlocking", "$this$runBlocking"}, s = {"L$0", "L$0"})
/* loaded from: classes7.dex */
public final class TaskCoroutinesKt$taskBlockOnMainThread$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $delayTime;
    final /* synthetic */ Function1 $job;
    Object L$0;
    int label;
    private CoroutineScope p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TaskCoroutinesKt$taskBlockOnMainThread$1(long j2, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$delayTime = j2;
        this.$job = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        TaskCoroutinesKt$taskBlockOnMainThread$1 taskCoroutinesKt$taskBlockOnMainThread$1 = new TaskCoroutinesKt$taskBlockOnMainThread$1(this.$delayTime, this.$job, continuation);
        taskCoroutinesKt$taskBlockOnMainThread$1.p$ = (CoroutineScope) obj;
        return taskCoroutinesKt$taskBlockOnMainThread$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TaskCoroutinesKt$taskBlockOnMainThread$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        CoroutineScope coroutineScope;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.p$;
            long j2 = this.$delayTime;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(j2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        Function1 function1 = this.$job;
        this.L$0 = coroutineScope;
        this.label = 2;
        if (function1.invoke(this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
