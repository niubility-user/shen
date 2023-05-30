package kotlin.collections;

import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.PublishedApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"kotlin/collections/CollectionsKt__CollectionsJVMKt", "kotlin/collections/CollectionsKt__CollectionsKt", "kotlin/collections/CollectionsKt__IterablesKt", "kotlin/collections/CollectionsKt__IteratorsJVMKt", "kotlin/collections/CollectionsKt__IteratorsKt", "kotlin/collections/CollectionsKt__MutableCollectionsJVMKt", "kotlin/collections/CollectionsKt__MutableCollectionsKt", "kotlin/collections/CollectionsKt__ReversedViewsKt", "kotlin/collections/CollectionsKt___CollectionsJvmKt", "kotlin/collections/CollectionsKt___CollectionsKt"}, d2 = {}, k = 4, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class CollectionsKt extends CollectionsKt___CollectionsKt {
    private CollectionsKt() {
    }

    @PublishedApi
    public static /* bridge */ /* synthetic */ <T> int collectionSizeOrDefault(@NotNull Iterable<? extends T> iterable, int i2) {
        return CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, i2);
    }

    public static /* bridge */ /* synthetic */ <T> T first(@NotNull Iterable<? extends T> iterable) {
        return (T) CollectionsKt___CollectionsKt.first(iterable);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T> T firstOrNull(@NotNull List<? extends T> list) {
        return (T) CollectionsKt___CollectionsKt.firstOrNull((List) list);
    }

    public static /* bridge */ /* synthetic */ <T> T last(@NotNull List<? extends T> list) {
        return (T) CollectionsKt___CollectionsKt.last((List) list);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ <T extends Comparable<? super T>> T min(@NotNull Iterable<? extends T> iterable) {
        return (T) CollectionsKt___CollectionsKt.min(iterable);
    }

    public static /* bridge */ /* synthetic */ <T> T single(@NotNull Iterable<? extends T> iterable) {
        return (T) CollectionsKt___CollectionsKt.single(iterable);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> List<T> sortedWith(@NotNull Iterable<? extends T> iterable, @NotNull Comparator<? super T> comparator) {
        return CollectionsKt___CollectionsKt.sortedWith(iterable, comparator);
    }
}
