package kotlin.collections;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"kotlin/collections/MapsKt__MapWithDefaultKt", "kotlin/collections/MapsKt__MapsJVMKt", "kotlin/collections/MapsKt__MapsKt", "kotlin/collections/MapsKt___MapsKt"}, d2 = {}, k = 4, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class MapsKt extends MapsKt___MapsKt {
    private MapsKt() {
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <K, V> Map<K, V> emptyMap() {
        return MapsKt__MapsKt.emptyMap();
    }

    @PublishedApi
    public static /* bridge */ /* synthetic */ int mapCapacity(int i2) {
        return MapsKt__MapsJVMKt.mapCapacity(i2);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <K, V> Map<K, V> mutableMapOf(@NotNull Pair<? extends K, ? extends V>... pairArr) {
        return MapsKt__MapsKt.mutableMapOf(pairArr);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Map<? extends K, ? extends V> map2) {
        return MapsKt__MapsKt.plus(map, map2);
    }
}
