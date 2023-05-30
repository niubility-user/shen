package kotlin.collections;

import com.jd.dynamic.base.interfaces.IExceptionHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.TuplesKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u00002\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a!\u0010\b\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0001\u00a2\u0006\u0004\b\b\u0010\t\u001a'\u0010\u000b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\n\u001a\u00020\u0007H\u0001\u00a2\u0006\u0004\b\u000b\u0010\f\u001a\u001f\u0010\u0011\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\rH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001a3\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\r\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0014\u001a%\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\r\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a)\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017\"\u0004\b\u0000\u0010\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019\u001aG\u0010\u001c\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00170\u001b\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u001a*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001b0\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001d\u00a8\u0006\u001e"}, d2 = {"T", "Lkotlin/Function0;", "", "iterator", "", "Iterable", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Iterable;", "", "collectionSizeOrNull", "(Ljava/lang/Iterable;)Ljava/lang/Integer;", "default", "collectionSizeOrDefault", "(Ljava/lang/Iterable;I)I", "", "", "safeToConvertToSet$CollectionsKt__IterablesKt", "(Ljava/util/Collection;)Z", "safeToConvertToSet", "source", "convertToSetForSetOperationWith", "(Ljava/lang/Iterable;Ljava/lang/Iterable;)Ljava/util/Collection;", "convertToSetForSetOperation", "(Ljava/lang/Iterable;)Ljava/util/Collection;", "", "flatten", "(Ljava/lang/Iterable;)Ljava/util/List;", "R", "Lkotlin/Pair;", IExceptionHandler.DynamicExceptionData.TYPE_UNZIP, "(Ljava/lang/Iterable;)Lkotlin/Pair;", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/collections/CollectionsKt")
/* loaded from: classes.dex */
public class CollectionsKt__IterablesKt extends CollectionsKt__CollectionsKt {
    @InlineOnly
    private static final <T> Iterable<T> Iterable(Function0<? extends Iterator<? extends T>> function0) {
        return new CollectionsKt__IterablesKt$Iterable$1(function0);
    }

    @PublishedApi
    public static <T> int collectionSizeOrDefault(@NotNull Iterable<? extends T> iterable, int i2) {
        return iterable instanceof Collection ? ((Collection) iterable).size() : i2;
    }

    @PublishedApi
    @Nullable
    public static final <T> Integer collectionSizeOrNull(@NotNull Iterable<? extends T> iterable) {
        if (iterable instanceof Collection) {
            return Integer.valueOf(((Collection) iterable).size());
        }
        return null;
    }

    @NotNull
    public static <T> Collection<T> convertToSetForSetOperation(@NotNull Iterable<? extends T> iterable) {
        if (iterable instanceof Set) {
            return (Collection) iterable;
        }
        if (iterable instanceof Collection) {
            Collection<T> collection = (Collection) iterable;
            return safeToConvertToSet$CollectionsKt__IterablesKt(collection) ? CollectionsKt___CollectionsKt.toHashSet(iterable) : collection;
        }
        return CollectionsKt___CollectionsKt.toHashSet(iterable);
    }

    @NotNull
    public static final <T> Collection<T> convertToSetForSetOperationWith(@NotNull Iterable<? extends T> iterable, @NotNull Iterable<? extends T> iterable2) {
        if (iterable instanceof Set) {
            return (Collection) iterable;
        }
        if (iterable instanceof Collection) {
            if (!(iterable2 instanceof Collection) || ((Collection) iterable2).size() >= 2) {
                Collection<T> collection = (Collection) iterable;
                return safeToConvertToSet$CollectionsKt__IterablesKt(collection) ? CollectionsKt___CollectionsKt.toHashSet(iterable) : collection;
            }
            return (Collection) iterable;
        }
        return CollectionsKt___CollectionsKt.toHashSet(iterable);
    }

    @NotNull
    public static final <T> List<T> flatten(@NotNull Iterable<? extends Iterable<? extends T>> iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator<? extends Iterable<? extends T>> it = iterable.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, it.next());
        }
        return arrayList;
    }

    private static final <T> boolean safeToConvertToSet$CollectionsKt__IterablesKt(@NotNull Collection<? extends T> collection) {
        return collection.size() > 2 && (collection instanceof ArrayList);
    }

    @NotNull
    public static final <T, R> Pair<List<T>, List<R>> unzip(@NotNull Iterable<? extends Pair<? extends T, ? extends R>> iterable) {
        int collectionSizeOrDefault;
        collectionSizeOrDefault = collectionSizeOrDefault(iterable, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        for (Pair<? extends T, ? extends R> pair : iterable) {
            arrayList.add(pair.getFirst());
            arrayList2.add(pair.getSecond());
        }
        return TuplesKt.to(arrayList, arrayList2);
    }
}
