package kotlin.collections;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"kotlin/collections/SetsKt__SetsJVMKt", "kotlin/collections/SetsKt__SetsKt", "kotlin/collections/SetsKt___SetsKt"}, d2 = {}, k = 4, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class SetsKt extends SetsKt___SetsKt {
    private SetsKt() {
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> Set<T> emptySet() {
        return SetsKt__SetsKt.emptySet();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> Set<T> optimizeReadOnlySet(@NotNull Set<? extends T> set) {
        return SetsKt__SetsKt.optimizeReadOnlySet(set);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ <T> Set<T> setOf(T t) {
        return SetsKt__SetsJVMKt.setOf(t);
    }
}
