package kotlinx.coroutines;

import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u008a@\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lkotlin/sequences/SequenceScope;", "Lkotlinx/coroutines/ChildJob;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.JobSupport$children$1", f = "JobSupport.kt", i = {0, 0, 1, 1, 1, 1, 1, 1}, l = {R2.attr.fontVariationSettings, R2.attr.foot_view_height}, m = "invokeSuspend", n = {"$this$sequence", XView2Constants.STATE, "$this$sequence", XView2Constants.STATE, ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID, "this_$iv", "cur$iv", AdvanceSetting.NETWORK_TYPE}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* loaded from: classes11.dex */
final class JobSupport$children$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super ChildJob>, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    private SequenceScope p$;
    final /* synthetic */ JobSupport this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JobSupport$children$1(JobSupport jobSupport, Continuation continuation) {
        super(2, continuation);
        this.this$0 = jobSupport;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        JobSupport$children$1 jobSupport$children$1 = new JobSupport$children$1(this.this$0, continuation);
        jobSupport$children$1.p$ = (SequenceScope) obj;
        return jobSupport$children$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super ChildJob> sequenceScope, Continuation<? super Unit> continuation) {
        return ((JobSupport$children$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x007d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x007f -> B:29:0x009b). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0098 -> B:29:0x009b). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        LockFreeLinkedListNode list;
        SequenceScope sequenceScope;
        Object obj2;
        LockFreeLinkedListNode lockFreeLinkedListNode;
        JobSupport$children$1 jobSupport$children$1;
        LockFreeLinkedListNode lockFreeLinkedListNode2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            SequenceScope sequenceScope2 = this.p$;
            Object state$kotlinx_coroutines_core = this.this$0.getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof ChildHandle[) {
                ChildJob childJob = ((ChildHandle[) state$kotlinx_coroutines_core).ChildHandle[;
                this.L$0 = sequenceScope2;
                this.L$1 = state$kotlinx_coroutines_core;
                this.label = 1;
                if (sequenceScope2.yield(childJob, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if ((state$kotlinx_coroutines_core instanceof Incomplete) && (list = ((Incomplete) state$kotlinx_coroutines_core).getList()) != null) {
                Object next = list.getNext();
                if (next != null) {
                    sequenceScope = sequenceScope2;
                    obj2 = state$kotlinx_coroutines_core;
                    lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
                    jobSupport$children$1 = this;
                    lockFreeLinkedListNode2 = list;
                    if ((!Intrinsics.areEqual(lockFreeLinkedListNode, list)) != false) {
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
                }
            }
        } else if (i2 == 1) {
            SequenceScope sequenceScope3 = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i2 != 2) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ChildHandle[ r1 = (ChildHandle[) this.L$5;
            lockFreeLinkedListNode = (LockFreeLinkedListNode) this.L$4;
            list = (LockFreeLinkedListHead) this.L$3;
            lockFreeLinkedListNode2 = (NodeList) this.L$2;
            obj2 = this.L$1;
            sequenceScope = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            jobSupport$children$1 = this;
            lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
            if ((!Intrinsics.areEqual(lockFreeLinkedListNode, list)) != false) {
                if (lockFreeLinkedListNode instanceof ChildHandle[) {
                    ChildHandle[ r8 = (ChildHandle[) lockFreeLinkedListNode;
                    ChildJob childJob2 = r8.ChildHandle[;
                    jobSupport$children$1.L$0 = sequenceScope;
                    jobSupport$children$1.L$1 = obj2;
                    jobSupport$children$1.L$2 = lockFreeLinkedListNode2;
                    jobSupport$children$1.L$3 = list;
                    jobSupport$children$1.L$4 = lockFreeLinkedListNode;
                    jobSupport$children$1.L$5 = r8;
                    jobSupport$children$1.label = 2;
                    if (sequenceScope.yield(childJob2, jobSupport$children$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
                if ((!Intrinsics.areEqual(lockFreeLinkedListNode, list)) != false) {
                }
            }
        }
        return Unit.INSTANCE;
    }
}
