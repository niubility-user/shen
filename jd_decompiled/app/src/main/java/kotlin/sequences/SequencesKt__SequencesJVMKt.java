package kotlin.sequences;

import java.util.Enumeration;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IteratorsJVMKt;
import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0087\b\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"T", "Ljava/util/Enumeration;", "Lkotlin/sequences/Sequence;", "asSequence", "(Ljava/util/Enumeration;)Lkotlin/sequences/Sequence;", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/sequences/SequencesKt")
/* loaded from: classes11.dex */
class SequencesKt__SequencesJVMKt extends SequencesKt__SequenceBuilderKt {
    @InlineOnly
    private static final <T> Sequence<T> asSequence(@NotNull Enumeration<T> enumeration) {
        Iterator it;
        Sequence<T> asSequence;
        it = CollectionsKt__IteratorsJVMKt.iterator(enumeration);
        asSequence = SequencesKt__SequencesKt.asSequence(it);
        return asSequence;
    }
}
