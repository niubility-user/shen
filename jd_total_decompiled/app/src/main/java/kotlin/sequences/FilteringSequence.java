package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B3\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\b\u0002\u0010\f\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005R\"\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u00068\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u00020\u00078\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\r\u00a8\u0006\u0010"}, d2 = {"Lkotlin/sequences/FilteringSequence;", "T", "Lkotlin/sequences/Sequence;", "", "iterator", "()Ljava/util/Iterator;", "Lkotlin/Function1;", "", "predicate", "Lkotlin/jvm/functions/Function1;", "sequence", "Lkotlin/sequences/Sequence;", "sendWhen", "Z", "<init>", "(Lkotlin/sequences/Sequence;ZLkotlin/jvm/functions/Function1;)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class FilteringSequence<T> implements Sequence<T> {
    private final Function1<T, Boolean> predicate;
    private final boolean sendWhen;
    private final Sequence<T> sequence;

    /* JADX WARN: Multi-variable type inference failed */
    public FilteringSequence(@NotNull Sequence<? extends T> sequence, boolean z, @NotNull Function1<? super T, Boolean> function1) {
        this.sequence = sequence;
        this.sendWhen = z;
        this.predicate = function1;
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<T> iterator() {
        return new FilteringSequence$iterator$1(this);
    }

    public /* synthetic */ FilteringSequence(Sequence sequence, boolean z, Function1 function1, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(sequence, (i2 & 2) != 0 ? true : z, function1);
    }
}
