package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\u001a9\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001aB\u0010\u000b\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00072\u0006\u0010\b\u001a\u00028\u00002\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\tH\u0086\b\u00a2\u0006\u0004\b\u000b\u0010\f\u001aA\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e\"\u000e\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\r\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001aG\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00042\u000e\u0010\u0012\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0011\u00a2\u0006\u0004\b\u000f\u0010\u0013\u001a[\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e\"\u000e\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\r\"\u0004\b\u0001\u0010\u00012*\u0010\u0015\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00020\u0014\"\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017\u001a \u0010\u001a\u001a\u00020\u0019*\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00180\u0004H\u0087\b\u00a2\u0006\u0004\b\u001a\u0010\u001b\u001a8\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0081\b\u00a2\u0006\u0004\b\u001c\u0010\u001d\u001a9\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0000\u00a2\u0006\u0004\b\u001e\u0010\u001d\u001a\u0017\u0010!\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0001\u00a2\u0006\u0004\b!\u0010\"\u001a\u0018\u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020\u001fH\u0081\b\u00a2\u0006\u0004\b%\u0010&\"\u0016\u0010'\u001a\u00020\u001f8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010(\u00a8\u0006)"}, d2 = {"K", "V", "Lkotlin/Pair;", "pair", "", "mapOf", "(Lkotlin/Pair;)Ljava/util/Map;", "Ljava/util/concurrent/ConcurrentMap;", "key", "Lkotlin/Function0;", "defaultValue", "getOrPut", "(Ljava/util/concurrent/ConcurrentMap;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "", "Ljava/util/SortedMap;", "toSortedMap", "(Ljava/util/Map;)Ljava/util/SortedMap;", "Ljava/util/Comparator;", "comparator", "(Ljava/util/Map;Ljava/util/Comparator;)Ljava/util/SortedMap;", "", "pairs", "sortedMapOf", "([Lkotlin/Pair;)Ljava/util/SortedMap;", "", "Ljava/util/Properties;", "toProperties", "(Ljava/util/Map;)Ljava/util/Properties;", "toSingletonMapOrSelf", "(Ljava/util/Map;)Ljava/util/Map;", "toSingletonMap", "", "expectedSize", "mapCapacity", "(I)I", "capacity", "", "checkBuilderCapacity", "(I)V", "INT_MAX_POWER_OF_TWO", "I", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/collections/MapsKt")
/* loaded from: classes.dex */
public class MapsKt__MapsJVMKt extends MapsKt__MapWithDefaultKt {
    private static final int INT_MAX_POWER_OF_TWO = 1073741824;

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    @PublishedApi
    private static final void checkBuilderCapacity(int i2) {
    }

    public static final <K, V> V getOrPut(@NotNull ConcurrentMap<K, V> concurrentMap, K k2, @NotNull Function0<? extends V> function0) {
        V v = concurrentMap.get(k2);
        if (v != null) {
            return v;
        }
        V invoke = function0.invoke();
        V putIfAbsent = concurrentMap.putIfAbsent(k2, invoke);
        return putIfAbsent != null ? putIfAbsent : invoke;
    }

    @PublishedApi
    public static int mapCapacity(int i2) {
        if (i2 < 0) {
            return i2;
        }
        if (i2 < 3) {
            return i2 + 1;
        }
        if (i2 < 1073741824) {
            return (int) ((i2 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    @NotNull
    public static final <K, V> Map<K, V> mapOf(@NotNull Pair<? extends K, ? extends V> pair) {
        Map<K, V> singletonMap = Collections.singletonMap(pair.getFirst(), pair.getSecond());
        Intrinsics.checkExpressionValueIsNotNull(singletonMap, "java.util.Collections.si\u2026(pair.first, pair.second)");
        return singletonMap;
    }

    @NotNull
    public static final <K extends Comparable<? super K>, V> SortedMap<K, V> sortedMapOf(@NotNull Pair<? extends K, ? extends V>... pairArr) {
        TreeMap treeMap = new TreeMap();
        MapsKt__MapsKt.putAll(treeMap, pairArr);
        return treeMap;
    }

    @InlineOnly
    private static final Properties toProperties(@NotNull Map<String, String> map) {
        Properties properties = new Properties();
        properties.putAll(map);
        return properties;
    }

    @NotNull
    public static final <K, V> Map<K, V> toSingletonMap(@NotNull Map<? extends K, ? extends V> map) {
        Map.Entry<? extends K, ? extends V> next = map.entrySet().iterator().next();
        Map<K, V> singletonMap = Collections.singletonMap(next.getKey(), next.getValue());
        Intrinsics.checkExpressionValueIsNotNull(singletonMap, "java.util.Collections.singletonMap(key, value)");
        Intrinsics.checkExpressionValueIsNotNull(singletonMap, "with(entries.iterator().\u2026ingletonMap(key, value) }");
        return singletonMap;
    }

    @InlineOnly
    private static final <K, V> Map<K, V> toSingletonMapOrSelf(@NotNull Map<K, ? extends V> map) {
        return toSingletonMap(map);
    }

    @NotNull
    public static final <K extends Comparable<? super K>, V> SortedMap<K, V> toSortedMap(@NotNull Map<? extends K, ? extends V> map) {
        return new TreeMap(map);
    }

    @NotNull
    public static final <K, V> SortedMap<K, V> toSortedMap(@NotNull Map<? extends K, ? extends V> map, @NotNull Comparator<? super K> comparator) {
        TreeMap treeMap = new TreeMap(comparator);
        treeMap.putAll(map);
        return treeMap;
    }
}
