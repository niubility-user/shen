package kotlin.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.random.Random;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u001d\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\f\u001a/\u0010\u0005\u001a\u00020\u0004\"\t\b\u0000\u0010\u0001\u00a2\u0006\u0002\b\u0000*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00022\u0006\u0010\u0003\u001a\u00028\u0000H\u0087\b\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a5\u0010\t\u001a\u00020\u0004\"\t\b\u0000\u0010\u0001\u00a2\u0006\u0002\b\u0000*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0087\b\u00a2\u0006\u0004\b\t\u0010\n\u001a5\u0010\u000b\u001a\u00020\u0004\"\t\b\u0000\u0010\u0001\u00a2\u0006\u0002\b\u0000*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0087\b\u00a2\u0006\u0004\b\u000b\u0010\n\u001a*\u0010\r\u001a\u00020\f\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\u0006\u0010\u0003\u001a\u00028\u0000H\u0087\n\u00a2\u0006\u0004\b\r\u0010\u000e\u001a0\u0010\r\u001a\u00020\f\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0087\n\u00a2\u0006\u0004\b\r\u0010\u0010\u001a0\u0010\r\u001a\u00020\f\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0087\n\u00a2\u0006\u0004\b\r\u0010\u0012\u001a0\u0010\r\u001a\u00020\f\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0087\n\u00a2\u0006\u0004\b\r\u0010\u0014\u001a*\u0010\u0015\u001a\u00020\f\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\u0006\u0010\u0003\u001a\u00028\u0000H\u0087\n\u00a2\u0006\u0004\b\u0015\u0010\u000e\u001a0\u0010\u0015\u001a\u00020\f\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0087\n\u00a2\u0006\u0004\b\u0015\u0010\u0010\u001a0\u0010\u0015\u001a\u00020\f\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0087\n\u00a2\u0006\u0004\b\u0015\u0010\u0012\u001a0\u0010\u0015\u001a\u00020\f\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0087\n\u00a2\u0006\u0004\b\u0015\u0010\u0014\u001a-\u0010\u0016\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f\u00a2\u0006\u0004\b\u0016\u0010\u0017\u001a-\u0010\u0016\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013\u00a2\u0006\u0004\b\u0016\u0010\u0018\u001a/\u0010\u0016\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0011\u00a2\u0006\u0004\b\u0016\u0010\u0019\u001a-\u0010\t\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f\u00a2\u0006\u0004\b\t\u0010\u0017\u001a-\u0010\t\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013\u00a2\u0006\u0004\b\t\u0010\u0018\u001a/\u0010\t\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0011\u00a2\u0006\u0004\b\t\u0010\u0019\u001a-\u0010\u000b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f\u00a2\u0006\u0004\b\u000b\u0010\u0017\u001a/\u0010\u000b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0011\u00a2\u0006\u0004\b\u000b\u0010\u0019\u001a-\u0010\u000b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013\u00a2\u0006\u0004\b\u000b\u0010\u0018\u001a\u0017\u0010\u001c\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0002H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001b\u001a1\u0010\t\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u001d2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u001e\u00a2\u0006\u0004\b\t\u0010 \u001a1\u0010\u000b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u001d2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u001e\u00a2\u0006\u0004\b\u000b\u0010 \u001a;\u0010$\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u001d2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u001e2\u0006\u0010!\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\"\u0010#\u001a-\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000'\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u000f2\u0006\u0010&\u001a\u00020%H\u0007\u00a2\u0006\u0004\b(\u0010)\u001a(\u0010\u0005\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000*2\u0006\u0010,\u001a\u00020+H\u0087\b\u00a2\u0006\u0004\b\u0005\u0010-\u001a\u001f\u0010.\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000*H\u0007\u00a2\u0006\u0004\b.\u0010/\u001a!\u00100\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000*H\u0007\u00a2\u0006\u0004\b0\u0010/\u001a\u001f\u00101\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000*H\u0007\u00a2\u0006\u0004\b1\u0010/\u001a!\u00102\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000*H\u0007\u00a2\u0006\u0004\b2\u0010/\u001a1\u0010\t\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000*2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u001e\u00a2\u0006\u0004\b\t\u00103\u001a1\u0010\u000b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000*2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u001e\u00a2\u0006\u0004\b\u000b\u00103\u001a;\u0010$\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000*2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u001e2\u0006\u0010!\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\"\u00104\u001a'\u00105\u001a\u00020\f\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000*2\u0006\u0010&\u001a\u00020%H\u0007\u00a2\u0006\u0004\b5\u00106\u00a8\u00067"}, d2 = {"Lkotlin/internal/OnlyInputTypes;", "T", "", "element", "", "remove", "(Ljava/util/Collection;Ljava/lang/Object;)Z", "", "elements", "removeAll", "(Ljava/util/Collection;Ljava/util/Collection;)Z", "retainAll", "", "plusAssign", "(Ljava/util/Collection;Ljava/lang/Object;)V", "", "(Ljava/util/Collection;Ljava/lang/Iterable;)V", "", "(Ljava/util/Collection;[Ljava/lang/Object;)V", "Lkotlin/sequences/Sequence;", "(Ljava/util/Collection;Lkotlin/sequences/Sequence;)V", "minusAssign", "addAll", "(Ljava/util/Collection;Ljava/lang/Iterable;)Z", "(Ljava/util/Collection;Lkotlin/sequences/Sequence;)Z", "(Ljava/util/Collection;[Ljava/lang/Object;)Z", "retainNothing$CollectionsKt__MutableCollectionsKt", "(Ljava/util/Collection;)Z", "retainNothing", "", "Lkotlin/Function1;", "predicate", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function1;)Z", "predicateResultToRemove", "filterInPlace$CollectionsKt__MutableCollectionsKt", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function1;Z)Z", "filterInPlace", "Lkotlin/random/Random;", "random", "", "shuffled", "(Ljava/lang/Iterable;Lkotlin/random/Random;)Ljava/util/List;", "", "", "index", "(Ljava/util/List;I)Ljava/lang/Object;", "removeFirst", "(Ljava/util/List;)Ljava/lang/Object;", "removeFirstOrNull", "removeLast", "removeLastOrNull", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)Z", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Z)Z", "shuffle", "(Ljava/util/List;Lkotlin/random/Random;)V", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/collections/CollectionsKt")
/* loaded from: classes.dex */
public class CollectionsKt__MutableCollectionsKt extends CollectionsKt__MutableCollectionsJVMKt {
    public static <T> boolean addAll(@NotNull Collection<? super T> collection, @NotNull Iterable<? extends T> iterable) {
        if (iterable instanceof Collection) {
            return collection.addAll((Collection) iterable);
        }
        boolean z = false;
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (collection.add((T) it.next())) {
                z = true;
            }
        }
        return z;
    }

    private static final <T> boolean filterInPlace$CollectionsKt__MutableCollectionsKt(@NotNull Iterable<? extends T> iterable, Function1<? super T, Boolean> function1, boolean z) {
        Iterator<? extends T> it = iterable.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (function1.invoke((T) it.next()).booleanValue() == z) {
                it.remove();
                z2 = true;
            }
        }
        return z2;
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> collection, T t) {
        collection.remove(t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> collection, T t) {
        collection.add(t);
    }

    @InlineOnly
    private static final <T> boolean remove(@NotNull Collection<? extends T> collection, T t) {
        if (collection != null) {
            return TypeIntrinsics.asMutableCollection(collection).remove(t);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }

    @InlineOnly
    private static final <T> boolean removeAll(@NotNull Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection != null) {
            return TypeIntrinsics.asMutableCollection(collection).removeAll(collection2);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    public static final <T> T removeFirst(@NotNull List<T> list) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.remove(0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Nullable
    public static final <T> T removeFirstOrNull(@NotNull List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.remove(0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    public static final <T> T removeLast(@NotNull List<T> list) {
        int lastIndex;
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
        return list.remove(lastIndex);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Nullable
    public static final <T> T removeLastOrNull(@NotNull List<T> list) {
        int lastIndex;
        if (list.isEmpty()) {
            return null;
        }
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
        return list.remove(lastIndex);
    }

    @InlineOnly
    private static final <T> boolean retainAll(@NotNull Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection != null) {
            return TypeIntrinsics.asMutableCollection(collection).retainAll(collection2);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }

    private static final boolean retainNothing$CollectionsKt__MutableCollectionsKt(@NotNull Collection<?> collection) {
        boolean z = !collection.isEmpty();
        collection.clear();
        return z;
    }

    @SinceKotlin(version = "1.3")
    public static final <T> void shuffle(@NotNull List<T> list, @NotNull Random random) {
        int lastIndex;
        for (lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list); lastIndex >= 1; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            T t = list.get(lastIndex);
            list.set(lastIndex, list.get(nextInt));
            list.set(nextInt, t);
        }
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <T> List<T> shuffled(@NotNull Iterable<? extends T> iterable, @NotNull Random random) {
        List<T> mutableList = CollectionsKt___CollectionsKt.toMutableList(iterable);
        shuffle(mutableList, random);
        return mutableList;
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> collection, Iterable<? extends T> iterable) {
        removeAll(collection, iterable);
    }

    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> collection, Iterable<? extends T> iterable) {
        addAll(collection, iterable);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use removeAt(index) instead.", replaceWith = @ReplaceWith(expression = "removeAt(index)", imports = {}))
    @InlineOnly
    private static final <T> T remove(@NotNull List<T> list, int i2) {
        return list.remove(i2);
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> collection, @NotNull Iterable<? extends T> iterable) {
        return TypeIntrinsics.asMutableCollection(collection).removeAll(CollectionsKt__IterablesKt.convertToSetForSetOperationWith(iterable, collection));
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> collection, @NotNull Iterable<? extends T> iterable) {
        return TypeIntrinsics.asMutableCollection(collection).retainAll(CollectionsKt__IterablesKt.convertToSetForSetOperationWith(iterable, collection));
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> collection, T[] tArr) {
        removeAll(collection, tArr);
    }

    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> collection, T[] tArr) {
        addAll(collection, tArr);
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> collection, @NotNull Sequence<? extends T> sequence) {
        HashSet hashSet;
        hashSet = SequencesKt___SequencesKt.toHashSet(sequence);
        return (hashSet.isEmpty() ^ true) && collection.removeAll(hashSet);
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> collection, @NotNull T[] tArr) {
        HashSet hashSet;
        if ((!(tArr.length == 0)) != false) {
            hashSet = ArraysKt___ArraysKt.toHashSet(tArr);
            return collection.retainAll(hashSet);
        }
        return retainNothing$CollectionsKt__MutableCollectionsKt(collection);
    }

    public static <T> boolean addAll(@NotNull Collection<? super T> collection, @NotNull Sequence<? extends T> sequence) {
        Iterator<? extends T> it = sequence.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (collection.add((T) it.next())) {
                z = true;
            }
        }
        return z;
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> collection, Sequence<? extends T> sequence) {
        removeAll(collection, sequence);
    }

    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> collection, Sequence<? extends T> sequence) {
        addAll(collection, sequence);
    }

    private static final <T> boolean filterInPlace$CollectionsKt__MutableCollectionsKt(@NotNull List<T> list, Function1<? super T, Boolean> function1, boolean z) {
        int lastIndex;
        int i2;
        int lastIndex2;
        if (!(list instanceof RandomAccess)) {
            if (list != null) {
                return filterInPlace$CollectionsKt__MutableCollectionsKt(TypeIntrinsics.asMutableIterable(list), function1, z);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableIterable<T>");
        }
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
        if (lastIndex >= 0) {
            int i3 = 0;
            i2 = 0;
            while (true) {
                T t = list.get(i3);
                if (function1.invoke(t).booleanValue() != z) {
                    if (i2 != i3) {
                        list.set(i2, t);
                    }
                    i2++;
                }
                if (i3 == lastIndex) {
                    break;
                }
                i3++;
            }
        } else {
            i2 = 0;
        }
        if (i2 >= list.size()) {
            return false;
        }
        lastIndex2 = CollectionsKt__CollectionsKt.getLastIndex(list);
        if (lastIndex2 < i2) {
            return true;
        }
        while (true) {
            list.remove(lastIndex2);
            if (lastIndex2 == i2) {
                return true;
            }
            lastIndex2--;
        }
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> collection, @NotNull T[] tArr) {
        HashSet hashSet;
        if ((!(tArr.length == 0)) == true) {
            hashSet = ArraysKt___ArraysKt.toHashSet(tArr);
            return collection.removeAll(hashSet);
        }
        return false;
    }

    public static final <T> boolean addAll(@NotNull Collection<? super T> collection, @NotNull T[] tArr) {
        List asList;
        asList = ArraysKt___ArraysJvmKt.asList(tArr);
        return collection.addAll(asList);
    }

    public static final <T> boolean removeAll(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> function1) {
        return filterInPlace$CollectionsKt__MutableCollectionsKt((Iterable) iterable, (Function1) function1, true);
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> collection, @NotNull Sequence<? extends T> sequence) {
        HashSet hashSet;
        hashSet = SequencesKt___SequencesKt.toHashSet(sequence);
        if ((!hashSet.isEmpty()) != false) {
            return collection.retainAll(hashSet);
        }
        return retainNothing$CollectionsKt__MutableCollectionsKt(collection);
    }

    public static final <T> boolean removeAll(@NotNull List<T> list, @NotNull Function1<? super T, Boolean> function1) {
        return filterInPlace$CollectionsKt__MutableCollectionsKt((List) list, (Function1) function1, true);
    }

    public static <T> boolean retainAll(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Boolean> function1) {
        return filterInPlace$CollectionsKt__MutableCollectionsKt((Iterable) iterable, (Function1) function1, false);
    }

    public static final <T> boolean retainAll(@NotNull List<T> list, @NotNull Function1<? super T, Boolean> function1) {
        return filterInPlace$CollectionsKt__MutableCollectionsKt((List) list, (Function1) function1, false);
    }
}
