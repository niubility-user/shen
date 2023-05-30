package kotlin.collections;

import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001aM\u0010\t\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0001*\u00028\u0000*\u0015\u0012\u0006\b\u0000\u0012\u00020\u0003\u0012\t\u0012\u00078\u0000\u00a2\u0006\u0002\b\u00040\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0087\n\u00a2\u0006\u0004\b\t\u0010\n\u001aO\u0010\t\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0001*\u00028\u0000*\u0017\u0012\u0006\b\u0000\u0012\u00020\u0003\u0012\u000b\b\u0001\u0012\u00078\u0000\u00a2\u0006\u0002\b\u00040\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0087\n\u00a2\u0006\u0004\b\f\u0010\n\u001a@\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u0012\u0012\u0006\b\u0000\u0012\u00020\u0003\u0012\u0006\b\u0000\u0012\u00028\u00000\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0087\b\u00a2\u0006\u0004\b\r\u0010\n\u001aH\u0010\u0010\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0000*\u0012\u0012\u0006\b\u0000\u0012\u00020\u0003\u0012\u0006\b\u0000\u0012\u00028\u00000\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u000e\u001a\u00028\u0000H\u0087\n\u00a2\u0006\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"V", "V1", "", "", "Lkotlin/internal/Exact;", "", "thisRef", "Lkotlin/reflect/KProperty;", "property", "getValue", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "", "getVar", "getVarContravariant", "value", "", "setValue", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
@JvmName(name = "MapAccessorsKt")
/* loaded from: classes.dex */
public final class MapAccessorsKt {
    @InlineOnly
    private static final <V, V1 extends V> V1 getValue(@NotNull Map<? super String, ? extends V> map, Object obj, KProperty<?> kProperty) {
        return (V1) MapsKt__MapWithDefaultKt.getOrImplicitDefaultNullable(map, kProperty.getName());
    }

    @InlineOnly
    @JvmName(name = "getVar")
    private static final <V, V1 extends V> V1 getVar(@NotNull Map<? super String, ? extends V> map, Object obj, KProperty<?> kProperty) {
        return (V1) MapsKt__MapWithDefaultKt.getOrImplicitDefaultNullable(map, kProperty.getName());
    }

    @InlineOnly
    @JvmName(name = "getVarContravariant")
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use getValue() with two type parameters instead")
    @LowPriorityInOverloadResolution
    private static final <V> V getVarContravariant(@NotNull Map<? super String, ? super V> map, Object obj, KProperty<?> kProperty) {
        return (V) MapsKt__MapWithDefaultKt.getOrImplicitDefaultNullable(map, kProperty.getName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <V> void setValue(@NotNull Map<? super String, ? super V> map, Object obj, KProperty<?> kProperty, V v) {
        map.put(kProperty.getName(), v);
    }
}