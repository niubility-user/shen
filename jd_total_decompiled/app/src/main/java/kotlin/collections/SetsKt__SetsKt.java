package kotlin.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.BuilderInference;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\u001a\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a-\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u00002\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0004\"\u00028\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000H\u0087\b\u00a2\u0006\u0004\b\u0006\u0010\u0003\u001a\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0000H\u0087\b\u00a2\u0006\u0004\b\t\u0010\u0003\u001a-\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00002\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0004\"\u00028\u0000\u00a2\u0006\u0004\b\t\u0010\u0007\u001a&\u0010\f\u001a\u0012\u0012\u0004\u0012\u00028\u00000\nj\b\u0012\u0004\u0012\u00028\u0000`\u000b\"\u0004\b\u0000\u0010\u0000H\u0087\b\u00a2\u0006\u0004\b\f\u0010\r\u001a7\u0010\f\u001a\u0012\u0012\u0004\u0012\u00028\u00000\nj\b\u0012\u0004\u0012\u00028\u0000`\u000b\"\u0004\b\u0000\u0010\u00002\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0004\"\u00028\u0000\u00a2\u0006\u0004\b\f\u0010\u000e\u001a&\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u000fj\b\u0012\u0004\u0012\u00028\u0000`\u0010\"\u0004\b\u0000\u0010\u0000H\u0087\b\u00a2\u0006\u0004\b\u0011\u0010\u0012\u001a7\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u000fj\b\u0012\u0004\u0012\u00028\u0000`\u0010\"\u0004\b\u0000\u0010\u00002\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0004\"\u00028\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0013\u001aJ\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u00142\u001f\b\u0001\u0010\u0018\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0004\u0012\u00020\u00160\u0015\u00a2\u0006\u0002\b\u0017H\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001aR\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u00142\u0006\u0010\u001c\u001a\u00020\u001b2\u001f\b\u0001\u0010\u0018\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0004\u0012\u00020\u00160\u0015\u00a2\u0006\u0002\b\u0017H\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u00a2\u0006\u0004\b\u0019\u0010\u001d\u001a(\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0001H\u0087\b\u00a2\u0006\u0004\b\u001e\u0010\u001f\u001a%\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0000\u00a2\u0006\u0004\b \u0010\u001f\u00a8\u0006!"}, d2 = {"T", "", "emptySet", "()Ljava/util/Set;", "", "elements", "setOf", "([Ljava/lang/Object;)Ljava/util/Set;", "", "mutableSetOf", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "hashSetOf", "()Ljava/util/HashSet;", "([Ljava/lang/Object;)Ljava/util/HashSet;", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "linkedSetOf", "()Ljava/util/LinkedHashSet;", "([Ljava/lang/Object;)Ljava/util/LinkedHashSet;", "E", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "buildSet", "(Lkotlin/jvm/functions/Function1;)Ljava/util/Set;", "", "capacity", "(ILkotlin/jvm/functions/Function1;)Ljava/util/Set;", "orEmpty", "(Ljava/util/Set;)Ljava/util/Set;", "optimizeReadOnlySet", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/collections/SetsKt")
/* loaded from: classes11.dex */
public class SetsKt__SetsKt extends SetsKt__SetsJVMKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <E> Set<E> buildSet(@BuilderInference Function1<? super Set<E>, Unit> function1) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        function1.invoke(linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static <T> Set<T> emptySet() {
        return EmptySet.INSTANCE;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> HashSet<T> hashSetOf() {
        return new HashSet<>();
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> LinkedHashSet<T> linkedSetOf() {
        return new LinkedHashSet<>();
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> Set<T> mutableSetOf() {
        return new LinkedHashSet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static <T> Set<T> optimizeReadOnlySet(@NotNull Set<? extends T> set) {
        Set<T> emptySet;
        Set<T> of;
        int size = set.size();
        if (size == 0) {
            emptySet = emptySet();
            return emptySet;
        } else if (size != 1) {
            return set;
        } else {
            of = SetsKt__SetsJVMKt.setOf(set.iterator().next());
            return of;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <T> Set<T> orEmpty(@Nullable Set<? extends T> set) {
        Set<T> emptySet;
        if (set != 0) {
            return set;
        }
        emptySet = emptySet();
        return emptySet;
    }

    @NotNull
    public static final <T> Set<T> setOf(@NotNull T... tArr) {
        Set<T> emptySet;
        if (tArr.length > 0) {
            return ArraysKt___ArraysKt.toSet(tArr);
        }
        emptySet = emptySet();
        return emptySet;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <E> Set<E> buildSet(int i2, @BuilderInference Function1<? super Set<E>, Unit> function1) {
        int mapCapacity;
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(i2);
        LinkedHashSet linkedHashSet = new LinkedHashSet(mapCapacity);
        function1.invoke(linkedHashSet);
        return linkedHashSet;
    }

    @NotNull
    public static final <T> HashSet<T> hashSetOf(@NotNull T... tArr) {
        int mapCapacity;
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(tArr.length);
        return (HashSet) ArraysKt___ArraysKt.toCollection(tArr, new HashSet(mapCapacity));
    }

    @NotNull
    public static final <T> LinkedHashSet<T> linkedSetOf(@NotNull T... tArr) {
        int mapCapacity;
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(tArr.length);
        return (LinkedHashSet) ArraysKt___ArraysKt.toCollection(tArr, new LinkedHashSet(mapCapacity));
    }

    @NotNull
    public static final <T> Set<T> mutableSetOf(@NotNull T... tArr) {
        int mapCapacity;
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(tArr.length);
        return (Set) ArraysKt___ArraysKt.toCollection(tArr, new LinkedHashSet(mapCapacity));
    }

    @InlineOnly
    private static final <T> Set<T> setOf() {
        Set<T> emptySet;
        emptySet = emptySet();
        return emptySet;
    }
}
