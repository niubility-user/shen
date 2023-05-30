package kotlin.collections;

import com.jd.dynamic.DYConstants;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0010 \n\u0002\b\u0004\u001a<\u0010\u0006\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u001a\u0010\u0004\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0002j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0003H\u0087\b\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a:\u0010\u0006\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\bH\u0087\b\u00a2\u0006\u0004\b\u0006\u0010\u000b\u001a'\u0010\u0006\u001a\u00020\u0005\"\u000e\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\f*\b\u0012\u0004\u0012\u00028\u00000\u0001\u00a2\u0006\u0004\b\u0006\u0010\r\u001a9\u0010\u000e\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u001a\u0010\u0004\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000\u0002j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`\u0003\u00a2\u0006\u0004\b\u000e\u0010\u0007\u001a(\u0010\u0010\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u000f\u001a\u00028\u0000H\u0087\b\u00a2\u0006\u0004\b\u0010\u0010\u0011\u001a \u0010\u0012\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0087\b\u00a2\u0006\u0004\b\u0012\u0010\r\u001a(\u0010\u0012\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0014\u001a\u00020\u0013H\u0087\b\u00a2\u0006\u0004\b\u0012\u0010\u0015\u001a%\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0019\u001a-\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u0010\u0014\u001a\u00020\u0013H\u0007\u00a2\u0006\u0004\b\u0018\u0010\u001a\u00a8\u0006\u001b"}, d2 = {"T", "", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "", "sort", "(Ljava/util/List;Ljava/util/Comparator;)V", "Lkotlin/Function2;", "", "comparison", "(Ljava/util/List;Lkotlin/jvm/functions/Function2;)V", "", "(Ljava/util/List;)V", "sortWith", "value", DYConstants.DY_FILL, "(Ljava/util/List;Ljava/lang/Object;)V", "shuffle", "Ljava/util/Random;", "random", "(Ljava/util/List;Ljava/util/Random;)V", "", "", "shuffled", "(Ljava/lang/Iterable;)Ljava/util/List;", "(Ljava/lang/Iterable;Ljava/util/Random;)Ljava/util/List;", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/collections/CollectionsKt")
/* loaded from: classes.dex */
public class CollectionsKt__MutableCollectionsJVMKt extends CollectionsKt__IteratorsKt {
    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final <T> void fill(@NotNull List<T> list, T t) {
        Collections.fill(list, t);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final <T> void shuffle(@NotNull List<T> list) {
        Collections.shuffle(list);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> List<T> shuffled(@NotNull Iterable<? extends T> iterable) {
        List<T> mutableList = CollectionsKt___CollectionsKt.toMutableList(iterable);
        Collections.shuffle(mutableList);
        return mutableList;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.jvm.internal.DefaultConstructorMarker, java.lang.String] */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use sortWith(comparator) instead.", replaceWith = @ReplaceWith(expression = "this.sortWith(comparator)", imports = {}))
    @InlineOnly
    private static final <T> void sort(@NotNull List<T> list, Comparator<? super T> comparator) {
        ?? r2 = 0;
        throw new NotImplementedError(r2, 1, r2);
    }

    public static <T> void sortWith(@NotNull List<T> list, @NotNull Comparator<? super T> comparator) {
        if (list.size() > 1) {
            Collections.sort(list, comparator);
        }
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final <T> void shuffle(@NotNull List<T> list, Random random) {
        Collections.shuffle(list, random);
    }

    @SinceKotlin(version = "1.2")
    @NotNull
    public static final <T> List<T> shuffled(@NotNull Iterable<? extends T> iterable, @NotNull Random random) {
        List<T> mutableList = CollectionsKt___CollectionsKt.toMutableList(iterable);
        Collections.shuffle(mutableList, random);
        return mutableList;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.jvm.internal.DefaultConstructorMarker, java.lang.String] */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use sortWith(Comparator(comparison)) instead.", replaceWith = @ReplaceWith(expression = "this.sortWith(Comparator(comparison))", imports = {}))
    @InlineOnly
    private static final <T> void sort(@NotNull List<T> list, Function2<? super T, ? super T, Integer> function2) {
        ?? r2 = 0;
        throw new NotImplementedError(r2, 1, r2);
    }

    public static <T extends Comparable<? super T>> void sort(@NotNull List<T> list) {
        if (list.size() > 1) {
            Collections.sort(list);
        }
    }
}
