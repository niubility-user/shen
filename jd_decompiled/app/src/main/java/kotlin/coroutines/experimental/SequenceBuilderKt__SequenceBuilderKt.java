package kotlin.coroutines.experimental;

import java.util.Iterator;
import kotlin.BuilderInference;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\t\u001aO\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00002/\b\u0001\u0010\u0007\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001\u00a2\u0006\u0002\b\u0006H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001aO\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u00002/\b\u0001\u0010\u0007\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001\u00a2\u0006\u0002\b\u0006H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\r\"\u001a\u0010\u0010\u001a\u00060\u000ej\u0002`\u000f8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011\"\u001a\u0010\u0012\u001a\u00060\u000ej\u0002`\u000f8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0011\"\u001a\u0010\u0013\u001a\u00060\u000ej\u0002`\u000f8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0011\"\u001a\u0010\u0014\u001a\u00060\u000ej\u0002`\u000f8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0011\"\u001a\u0010\u0015\u001a\u00060\u000ej\u0002`\u000f8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0011\"\u001a\u0010\u0016\u001a\u00060\u000ej\u0002`\u000f8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0011*\f\b\u0002\u0010\u0017\"\u00020\u000e2\u00020\u000e\u0082\u0002\u0004\n\u0002\b\t\u00a8\u0006\u0018"}, d2 = {"T", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/SequenceBuilder;", "Lkotlin/coroutines/experimental/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "Lkotlin/sequences/Sequence;", "buildSequence", "(Lkotlin/jvm/functions/Function2;)Lkotlin/sequences/Sequence;", "", "buildIterator", "(Lkotlin/jvm/functions/Function2;)Ljava/util/Iterator;", "", "Lkotlin/coroutines/experimental/State;", "State_ManyNotReady", "I", "State_Ready", "State_ManyReady", "State_Done", "State_NotReady", "State_Failed", "State", "kotlin-stdlib-coroutines"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/coroutines/experimental/SequenceBuilderKt")
/* loaded from: classes11.dex */
class SequenceBuilderKt__SequenceBuilderKt {
    private static final int State_Done = 4;
    private static final int State_Failed = 5;
    private static final int State_ManyNotReady = 1;
    private static final int State_ManyReady = 2;
    private static final int State_NotReady = 0;
    private static final int State_Ready = 3;

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T> Iterator<T> buildIterator(@BuilderInference @NotNull Function2<? super SequenceBuilder<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Continuation<Unit> createCoroutineUnchecked;
        SequenceBuilderIterator sequenceBuilderIterator = new SequenceBuilderIterator();
        createCoroutineUnchecked = IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnchecked(function2, sequenceBuilderIterator, sequenceBuilderIterator);
        sequenceBuilderIterator.setNextStep(createCoroutineUnchecked);
        return sequenceBuilderIterator;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T> Sequence<T> buildSequence(@BuilderInference @NotNull final Function2<? super SequenceBuilder<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return new Sequence<T>() { // from class: kotlin.coroutines.experimental.SequenceBuilderKt__SequenceBuilderKt$buildSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<T> iterator() {
                return SequenceBuilderKt__SequenceBuilderKt.buildIterator(Function2.this);
            }
        };
    }
}
