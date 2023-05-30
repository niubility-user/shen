package kotlin.collections;

import ..;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.BuilderInference;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000f\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a'\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0001H\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a-\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u00002\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0001\"\u00028\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001a\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u0000H\u0087\b\u00a2\u0006\u0004\b\t\u0010\u0007\u001a\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u0000H\u0087\b\u00a2\u0006\u0004\b\f\u0010\u0007\u001a&\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00028\u00000\rj\b\u0012\u0004\u0012\u00028\u0000`\u000e\"\u0004\b\u0000\u0010\u0000H\u0087\b\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001a-\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u00002\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0001\"\u00028\u0000\u00a2\u0006\u0004\b\f\u0010\n\u001a7\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00028\u00000\rj\b\u0012\u0004\u0012\u00028\u0000`\u000e\"\u0004\b\u0000\u0010\u00002\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0001\"\u00028\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0011\u001a'\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\b\b\u0000\u0010\u0000*\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a5\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\b\b\u0000\u0010\u0000*\u00020\u00122\u0016\u0010\b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00018\u00000\u0001\"\u0004\u0018\u00018\u0000\u00a2\u0006\u0004\b\u0014\u0010\n\u001aG\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0017\u001a\u00020\u00162!\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\u0016\u00a2\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00028\u00000\u0018H\u0087\b\u00a2\u0006\u0004\b\u001d\u0010\u001e\u001aG\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0017\u001a\u00020\u00162!\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\u0016\u00a2\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00028\u00000\u0018H\u0087\b\u00a2\u0006\u0004\b\u001f\u0010\u001e\u001aJ\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010 2\u001f\b\u0001\u0010#\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0004\u0012\u00020!0\u0018\u00a2\u0006\u0002\b\"H\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00a2\u0006\u0004\b$\u0010%\u001aR\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010 2\u0006\u0010&\u001a\u00020\u00162\u001f\b\u0001\u0010#\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0004\u0012\u00020!0\u0018\u00a2\u0006\u0002\b\"H\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u00a2\u0006\u0004\b$\u0010\u001e\u001a \u0010(\u001a\u00020'\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0087\b\u00a2\u0006\u0004\b(\u0010)\u001a3\u0010*\u001a\u00020'\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u00a2\u0006\u0004\b*\u0010)\u001a(\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0002H\u0087\b\u00a2\u0006\u0004\b+\u0010,\u001a(\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005H\u0087\b\u00a2\u0006\u0004\b+\u0010-\u001a:\u00102\u001a\u00028\u0001\"\u0010\b\u0000\u0010.*\u0006\u0012\u0002\b\u00030\u0002*\u00028\u0001\"\u0004\b\u0001\u0010/*\u00028\u00002\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u000100H\u0087\b\u00a2\u0006\u0004\b2\u00103\u001a3\u00105\u001a\u00020'\"\t\b\u0000\u0010\u0000\u00a2\u0006\u0002\b4*\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0087\b\u00a2\u0006\u0004\b5\u00106\u001a%\u00107\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0000\u00a2\u0006\u0004\b7\u0010-\u001aG\u0010;\u001a\u00020\u0016\"\u000e\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u000008*\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00052\b\u0010\u0013\u001a\u0004\u0018\u00018\u00002\b\b\u0002\u00109\u001a\u00020\u00162\b\b\u0002\u0010:\u001a\u00020\u0016\u00a2\u0006\u0004\b;\u0010<\u001aU\u0010;\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0013\u001a\u00028\u00002\u001a\u0010?\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00000=j\n\u0012\u0006\b\u0000\u0012\u00028\u0000`>2\b\b\u0002\u00109\u001a\u00020\u00162\b\b\u0002\u0010:\u001a\u00020\u0016\u00a2\u0006\u0004\b;\u0010@\u001af\u0010D\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u0000\"\u000e\b\u0001\u0010A*\b\u0012\u0004\u0012\u00028\u000108*\b\u0012\u0004\u0012\u00028\u00000\u00052\b\u0010B\u001a\u0004\u0018\u00018\u00012\b\b\u0002\u00109\u001a\u00020\u00162\b\b\u0002\u0010:\u001a\u00020\u00162\u0016\b\u0004\u0010C\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0018H\u0086\b\u00a2\u0006\u0004\bD\u0010E\u001aE\u0010;\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00052\b\b\u0002\u00109\u001a\u00020\u00162\b\b\u0002\u0010:\u001a\u00020\u00162\u0012\u0010F\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00160\u0018\u00a2\u0006\u0004\b;\u0010G\u001a'\u0010J\u001a\u00020!2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u00109\u001a\u00020\u00162\u0006\u0010:\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\bH\u0010I\u001a\u000f\u0010K\u001a\u00020!H\u0001\u00a2\u0006\u0004\bK\u0010L\u001a\u000f\u0010M\u001a\u00020!H\u0001\u00a2\u0006\u0004\bM\u0010L\"#\u0010P\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00058F@\u0006\u00a2\u0006\u0006\u001a\u0004\bN\u0010O\"\u001b\u0010T\u001a\u00020Q*\u0006\u0012\u0002\b\u00030\u00028F@\u0006\u00a2\u0006\u0006\u001a\u0004\bR\u0010S\u00a8\u0006U"}, d2 = {"T", "", "", "asCollection", "([Ljava/lang/Object;)Ljava/util/Collection;", "", "emptyList", "()Ljava/util/List;", "elements", "listOf", "([Ljava/lang/Object;)Ljava/util/List;", "", "mutableListOf", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "arrayListOf", "()Ljava/util/ArrayList;", "([Ljava/lang/Object;)Ljava/util/ArrayList;", "", "element", "listOfNotNull", "(Ljava/lang/Object;)Ljava/util/List;", "", ApkDownloadTable.FIELD_SIZE, "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "index", XView2Constants.XVIEW2_ACTION_INIT, "List", "(ILkotlin/jvm/functions/Function1;)Ljava/util/List;", "MutableList", "E", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "buildList", "(Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "capacity", "", "isNotEmpty", "(Ljava/util/Collection;)Z", "isNullOrEmpty", "orEmpty", "(Ljava/util/Collection;)Ljava/util/Collection;", "(Ljava/util/List;)Ljava/util/List;", "C", "R", "Lkotlin/Function0;", "defaultValue", "ifEmpty", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Lkotlin/internal/OnlyInputTypes;", "containsAll", "(Ljava/util/Collection;Ljava/util/Collection;)Z", "optimizeReadOnlyList", "", "fromIndex", "toIndex", "binarySearch", "(Ljava/util/List;Ljava/lang/Comparable;II)I", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "(Ljava/util/List;Ljava/lang/Object;Ljava/util/Comparator;II)I", "K", "key", "selector", "binarySearchBy", "(Ljava/util/List;Ljava/lang/Comparable;IILkotlin/jvm/functions/Function1;)I", "comparison", "(Ljava/util/List;IILkotlin/jvm/functions/Function1;)I", "rangeCheck$CollectionsKt__CollectionsKt", "(III)V", "rangeCheck", "throwIndexOverflow", "()V", "throwCountOverflow", "getLastIndex", "(Ljava/util/List;)I", "lastIndex", "Lkotlin/ranges/IntRange;", "getIndices", "(Ljava/util/Collection;)Lkotlin/ranges/IntRange;", "indices", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/collections/CollectionsKt")
/* loaded from: classes.dex */
public class CollectionsKt__CollectionsKt extends CollectionsKt__CollectionsJVMKt {
    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> List<T> List(int i2, Function1<? super Integer, ? extends T> function1) {
        ArrayList arrayList = new ArrayList(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            arrayList.add(function1.invoke(Integer.valueOf(i3)));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> List<T> MutableList(int i2, Function1<? super Integer, ? extends T> function1) {
        ArrayList arrayList = new ArrayList(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            arrayList.add(function1.invoke(Integer.valueOf(i3)));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> ArrayList<T> arrayListOf() {
        return new ArrayList<>();
    }

    @NotNull
    public static final <T> Collection<T> asCollection(@NotNull T[] tArr) {
        return new ArrayAsCollection(tArr, false);
    }

    public static final <T extends Comparable<? super T>> int binarySearch(@NotNull List<? extends T> list, @Nullable T t, int i2, int i3) {
        int compareValues;
        rangeCheck$CollectionsKt__CollectionsKt(list.size(), i2, i3);
        int i4 = i3 - 1;
        while (i2 <= i4) {
            int i5 = (i2 + i4) >>> 1;
            compareValues = ComparisonsKt__ComparisonsKt.compareValues(list.get(i5), t);
            if (compareValues < 0) {
                i2 = i5 + 1;
            } else if (compareValues <= 0) {
                return i5;
            } else {
                i4 = i5 - 1;
            }
        }
        return -(i2 + 1);
    }

    public static /* synthetic */ int binarySearch$default(List list, Comparable comparable, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = list.size();
        }
        return binarySearch(list, comparable, i2, i3);
    }

    public static final <T, K extends Comparable<? super K>> int binarySearchBy(@NotNull List<? extends T> list, @Nullable K k2, int i2, int i3, @NotNull Function1<? super T, ? extends K> function1) {
        return binarySearch(list, i2, i3, new CollectionsKt__CollectionsKt$binarySearchBy$1(function1, k2));
    }

    public static /* synthetic */ int binarySearchBy$default(List list, Comparable comparable, int i2, int i3, Function1 function1, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = list.size();
        }
        return binarySearch(list, i2, i3, new CollectionsKt__CollectionsKt$binarySearchBy$1(function1, comparable));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <E> List<E> buildList(@BuilderInference Function1<? super List<E>, Unit> function1) {
        ArrayList arrayList = new ArrayList();
        function1.invoke(arrayList);
        return arrayList;
    }

    @InlineOnly
    private static final <T> boolean containsAll(@NotNull Collection<? extends T> collection, Collection<? extends T> collection2) {
        return collection.containsAll(collection2);
    }

    @NotNull
    public static <T> List<T> emptyList() {
        return EmptyList.INSTANCE;
    }

    @NotNull
    public static  getIndices(@NotNull Collection<?> collection) {
        return new (0, collection.size() - 1);
    }

    public static <T> int getLastIndex(@NotNull List<? extends T> list) {
        return list.size() - 1;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final Object ifEmpty(Collection collection, Function0 function0) {
        return collection.isEmpty() ? function0.invoke() : collection;
    }

    @InlineOnly
    private static final <T> boolean isNotEmpty(@NotNull Collection<? extends T> collection) {
        return !collection.isEmpty();
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <T> boolean isNullOrEmpty(@Nullable Collection<? extends T> collection) {
        return collection == null || collection.isEmpty();
    }

    @NotNull
    public static <T> List<T> listOf(@NotNull T... tArr) {
        List<T> emptyList;
        List<T> asList;
        if (tArr.length > 0) {
            asList = ArraysKt___ArraysJvmKt.asList(tArr);
            return asList;
        }
        emptyList = emptyList();
        return emptyList;
    }

    @NotNull
    public static final <T> List<T> listOfNotNull(@Nullable T t) {
        List<T> emptyList;
        List<T> listOf;
        if (t != null) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(t);
            return listOf;
        }
        emptyList = emptyList();
        return emptyList;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> List<T> mutableListOf() {
        return new ArrayList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static <T> List<T> optimizeReadOnlyList(@NotNull List<? extends T> list) {
        List<T> emptyList;
        List<T> listOf;
        int size = list.size();
        if (size == 0) {
            emptyList = emptyList();
            return emptyList;
        } else if (size != 1) {
            return list;
        } else {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(list.get(0));
            return listOf;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <T> Collection<T> orEmpty(@Nullable Collection<? extends T> collection) {
        List emptyList;
        if (collection != 0) {
            return collection;
        }
        emptyList = emptyList();
        return emptyList;
    }

    private static final void rangeCheck$CollectionsKt__CollectionsKt(int i2, int i3, int i4) {
        if (i3 > i4) {
            throw new IllegalArgumentException("fromIndex (" + i3 + ") is greater than toIndex (" + i4 + ").");
        } else if (i3 < 0) {
            throw new IndexOutOfBoundsException("fromIndex (" + i3 + ") is less than zero.");
        } else if (i4 <= i2) {
        } else {
            throw new IndexOutOfBoundsException("toIndex (" + i4 + ") is greater than size (" + i2 + ").");
        }
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static void throwCountOverflow() {
        throw new ArithmeticException("Count overflow has happened.");
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static void throwIndexOverflow() {
        throw new ArithmeticException("Index overflow has happened.");
    }

    @NotNull
    public static <T> ArrayList<T> arrayListOf(@NotNull T... tArr) {
        return tArr.length == 0 ? new ArrayList<>() : new ArrayList<>(new ArrayAsCollection(tArr, true));
    }

    public static /* synthetic */ int binarySearch$default(List list, Object obj, Comparator comparator, int i2, int i3, int i4, Object obj2) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = list.size();
        }
        return binarySearch(list, obj, comparator, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <E> List<E> buildList(int i2, @BuilderInference Function1<? super List<E>, Unit> function1) {
        ArrayList arrayList = new ArrayList(i2);
        function1.invoke(arrayList);
        return arrayList;
    }

    @InlineOnly
    private static final <T> List<T> listOf() {
        List<T> emptyList;
        emptyList = emptyList();
        return emptyList;
    }

    @NotNull
    public static final <T> List<T> listOfNotNull(@NotNull T... tArr) {
        return ArraysKt___ArraysKt.filterNotNull(tArr);
    }

    @NotNull
    public static final <T> List<T> mutableListOf(@NotNull T... tArr) {
        return tArr.length == 0 ? new ArrayList() : new ArrayList(new ArrayAsCollection(tArr, true));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <T> List<T> orEmpty(@Nullable List<? extends T> list) {
        List<T> emptyList;
        if (list != 0) {
            return list;
        }
        emptyList = emptyList();
        return emptyList;
    }

    public static /* synthetic */ int binarySearch$default(List list, int i2, int i3, Function1 function1, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = 0;
        }
        if ((i4 & 2) != 0) {
            i3 = list.size();
        }
        return binarySearch(list, i2, i3, function1);
    }

    public static final <T> int binarySearch(@NotNull List<? extends T> list, T t, @NotNull Comparator<? super T> comparator, int i2, int i3) {
        rangeCheck$CollectionsKt__CollectionsKt(list.size(), i2, i3);
        int i4 = i3 - 1;
        while (i2 <= i4) {
            int i5 = (i2 + i4) >>> 1;
            int compare = comparator.compare((T) list.get(i5), t);
            if (compare < 0) {
                i2 = i5 + 1;
            } else if (compare <= 0) {
                return i5;
            } else {
                i4 = i5 - 1;
            }
        }
        return -(i2 + 1);
    }

    public static final <T> int binarySearch(@NotNull List<? extends T> list, int i2, int i3, @NotNull Function1<? super T, Integer> function1) {
        rangeCheck$CollectionsKt__CollectionsKt(list.size(), i2, i3);
        int i4 = i3 - 1;
        while (i2 <= i4) {
            int i5 = (i2 + i4) >>> 1;
            int intValue = function1.invoke((T) list.get(i5)).intValue();
            if (intValue < 0) {
                i2 = i5 + 1;
            } else if (intValue <= 0) {
                return i5;
            } else {
                i4 = i5 - 1;
            }
        }
        return -(i2 + 1);
    }
}
