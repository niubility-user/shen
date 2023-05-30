package kotlin.sequences;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.util.Iterator;
import kotlin.BuilderInference;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aO\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00002/\b\u0001\u0010\u0007\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001\u00a2\u0006\u0002\b\u0006H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001aP\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00002/\b\t\u0010\u000b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001\u00a2\u0006\u0002\b\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\n\u001aO\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r\"\u0004\b\u0000\u0010\u00002/\b\u0001\u0010\u0007\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001\u00a2\u0006\u0002\b\u0006H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001aP\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\r\"\u0004\b\u0000\u0010\u00002/\b\t\u0010\u000b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001\u00a2\u0006\u0002\b\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u000f\"\u001a\u0010\u0013\u001a\u00060\u0011j\u0002`\u00128\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014\"\u001a\u0010\u0015\u001a\u00060\u0011j\u0002`\u00128\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0014\"\u001a\u0010\u0016\u001a\u00060\u0011j\u0002`\u00128\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0014\"\u001a\u0010\u0017\u001a\u00060\u0011j\u0002`\u00128\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0014\"\u001a\u0010\u0018\u001a\u00060\u0011j\u0002`\u00128\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0014\"\u001a\u0010\u0019\u001a\u00060\u0011j\u0002`\u00128\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0014*V\b\u0007\u0010%\u001a\u0004\b\u0000\u0010\u0000\"\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0002B6\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u001c\b\u001d\u0012\u0018\b\u000bB\u0014\b\u001e\u0012\u0006\b\u001f\u0012\u0002\b\f\u0012\b\b \u0012\u0004\b\b(!\u0012\n\b\"\u0012\u0006\b\n0#8$*\f\b\u0002\u0010&\"\u00020\u00112\u00020\u0011\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006'"}, d2 = {"T", "Lkotlin/Function2;", "Lkotlin/sequences/SequenceScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", JDReactConstant.BLOCK, "Lkotlin/sequences/Sequence;", "sequence", "(Lkotlin/jvm/functions/Function2;)Lkotlin/sequences/Sequence;", "builderAction", "buildSequence", "", "iterator", "(Lkotlin/jvm/functions/Function2;)Ljava/util/Iterator;", "buildIterator", "", "Lkotlin/sequences/State;", "State_ManyReady", "I", "State_Done", "State_ManyNotReady", "State_Ready", "State_Failed", "State_NotReady", "Lkotlin/Deprecated;", "message", "Use SequenceScope class instead.", "replaceWith", "Lkotlin/ReplaceWith;", "imports", "expression", "SequenceScope<T>", "level", "Lkotlin/DeprecationLevel;", "ERROR", "SequenceBuilder", "State", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/sequences/SequencesKt")
/* loaded from: classes.dex */
public class SequencesKt__SequenceBuilderKt {
    private static final int State_Done = 4;
    private static final int State_Failed = 5;
    private static final int State_ManyNotReady = 1;
    private static final int State_ManyReady = 2;
    private static final int State_NotReady = 0;
    private static final int State_Ready = 3;

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use SequenceScope class instead.", replaceWith = @ReplaceWith(expression = "SequenceScope<T>", imports = {}))
    public static /* synthetic */ void SequenceBuilder$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use 'iterator { }' function instead.", replaceWith = @ReplaceWith(expression = "iterator(builderAction)", imports = {}))
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> Iterator<T> buildIterator(@BuilderInference Function2<? super SequenceScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Iterator<T> it;
        it = iterator(function2);
        return it;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use 'sequence { }' function instead.", replaceWith = @ReplaceWith(expression = "sequence(builderAction)", imports = {}))
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> Sequence<T> buildSequence(@BuilderInference final Function2<? super SequenceScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return new Sequence<T>() { // from class: kotlin.sequences.SequencesKt__SequenceBuilderKt$buildSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<T> iterator() {
                Iterator<T> it;
                it = SequencesKt__SequenceBuilderKt.iterator(Function2.this);
                return it;
            }
        };
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static <T> Iterator<T> iterator(@BuilderInference @NotNull Function2<? super SequenceScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Continuation<Unit> createCoroutineUnintercepted;
        SequenceBuilderIterator sequenceBuilderIterator = new SequenceBuilderIterator();
        createCoroutineUnintercepted = IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(function2, sequenceBuilderIterator, sequenceBuilderIterator);
        sequenceBuilderIterator.setNextStep(createCoroutineUnintercepted);
        return sequenceBuilderIterator;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static <T> Sequence<T> sequence(@BuilderInference @NotNull final Function2<? super SequenceScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return new Sequence<T>() { // from class: kotlin.sequences.SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<T> iterator() {
                Iterator<T> it;
                it = SequencesKt__SequenceBuilderKt.iterator(Function2.this);
                return it;
            }
        };
    }
}
