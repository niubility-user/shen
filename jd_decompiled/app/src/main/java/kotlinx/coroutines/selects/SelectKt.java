package kotlinx.coroutines.selects;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.time.ExperimentalTime;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aK\u0010\u000b\u001a\u00020\b\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u001c\u0010\u0007\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001aG\u0010\u000e\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u001f\b\u0004\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0001\u0012\u0004\u0012\u00020\b0\u0004\u00a2\u0006\u0002\b\fH\u0086H\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\b\u000e\u0010\u000f\"\"\u0010\u0010\u001a\u00020\u00068\u0000@\u0001X\u0081\u0004\u00a2\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013\"\u001c\u0010\u0016\u001a\u00020\u00068\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0011\u0012\u0004\b\u0017\u0010\u0015\"\u001c\u0010\u0018\u001a\u00020\u00068\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0011\u0012\u0004\b\u0019\u0010\u0015\"\u001c\u0010\u001b\u001a\u00020\u001a8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u001b\u0010\u001c\u0012\u0004\b\u001d\u0010\u0015\"\"\u0010\u001e\u001a\u00020\u00068\u0000@\u0001X\u0081\u0004\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u0011\u0012\u0004\b \u0010\u0015\u001a\u0004\b\u001f\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"R", "Lkotlinx/coroutines/selects/SelectBuilder;", "Lkotlin/time/Duration;", "timeout", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", JDReactConstant.BLOCK, "", "onTimeout-0lHKgQg", "(Lkotlinx/coroutines/selects/SelectBuilder;DLkotlin/jvm/functions/Function1;)V", "onTimeout", "Lkotlin/ExtensionFunctionType;", "builder", "select", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "NOT_SELECTED", "Ljava/lang/Object;", "getNOT_SELECTED", "()Ljava/lang/Object;", "NOT_SELECTED$annotations", "()V", "UNDECIDED", "UNDECIDED$annotations", "RESUMED", "RESUMED$annotations", "Lkotlinx/coroutines/selects/SeqNumber;", "selectOpSequenceNumber", "Lkotlinx/coroutines/selects/SeqNumber;", "selectOpSequenceNumber$annotations", "ALREADY_SELECTED", "getALREADY_SELECTED", "ALREADY_SELECTED$annotations", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class SelectKt {
    @NotNull
    private static final Object NOT_SELECTED = new Symbol("NOT_SELECTED");
    @NotNull
    private static final Object ALREADY_SELECTED = new Symbol("ALREADY_SELECTED");
    private static final Object UNDECIDED = new Symbol("UNDECIDED");
    private static final Object RESUMED = new Symbol("RESUMED");
    private static final SeqNumber selectOpSequenceNumber = new SeqNumber();

    public static /* synthetic */ void ALREADY_SELECTED$annotations() {
    }

    public static /* synthetic */ void NOT_SELECTED$annotations() {
    }

    private static /* synthetic */ void RESUMED$annotations() {
    }

    private static /* synthetic */ void UNDECIDED$annotations() {
    }

    public static final /* synthetic */ Object access$getRESUMED$p() {
        return RESUMED;
    }

    public static final /* synthetic */ SeqNumber access$getSelectOpSequenceNumber$p() {
        return selectOpSequenceNumber;
    }

    public static final /* synthetic */ Object access$getUNDECIDED$p() {
        return UNDECIDED;
    }

    @NotNull
    public static final Object getALREADY_SELECTED() {
        return ALREADY_SELECTED;
    }

    @NotNull
    public static final Object getNOT_SELECTED() {
        return NOT_SELECTED;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ExperimentalTime
    @ExperimentalCoroutinesApi
    /* renamed from: onTimeout-0lHKgQg */
    public static final <R> void m1266onTimeout0lHKgQg(@NotNull SelectBuilder<? super R> selectBuilder, double d, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        selectBuilder.onTimeout(DelayKt.m1218toDelayMillisLRDsOJo(d), function1);
    }

    @Nullable
    public static final <R> Object select(@NotNull Function1<? super SelectBuilder<? super R>, Unit> function1, @NotNull Continuation<? super R> continuation) {
        Object coroutine_suspended;
        SelectInstance selectInstance = new SelectInstance(continuation);
        try {
            function1.invoke(selectInstance);
        } catch (Throwable th) {
            selectInstance.handleBuilderException(th);
        }
        Object result = selectInstance.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Nullable
    private static final Object select$$forInline(@NotNull Function1 function1, @NotNull Continuation continuation) {
        Object coroutine_suspended;
        InlineMarker.mark(0);
        SelectInstance selectInstance = new SelectInstance(continuation);
        try {
            function1.invoke(selectInstance);
        } catch (Throwable th) {
            selectInstance.handleBuilderException(th);
        }
        Object result = selectInstance.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return result;
    }

    private static /* synthetic */ void selectOpSequenceNumber$annotations() {
    }
}
