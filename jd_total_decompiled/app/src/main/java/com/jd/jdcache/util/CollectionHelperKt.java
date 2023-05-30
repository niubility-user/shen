package com.jd.jdcache.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\\\u0010\u0007\u001a\u0004\u0018\u00018\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001\"\u0006\b\u0001\u0010\u0001\u0018\u0001\"\u0016\b\u0002\u0010\u0003\u0018\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002*\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00042\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0001\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005H\u0086\b\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"K", "V", "", "M", "", "Lkotlin/Function1;", "getKey", "keyNonNullMap", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "JDCache_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class CollectionHelperKt {
    @Nullable
    public static final /* synthetic */ <K, V, M extends Map<K, V>> M keyNonNullMap(@Nullable Collection<? extends V> collection, @NotNull Function1<? super V, ? extends K> function1) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(4, "M");
        M map = (M) Map.class.newInstance();
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            Object obj = (Object) it.next();
            K invoke = function1.invoke(obj);
            if (invoke != null) {
                Intrinsics.checkExpressionValueIsNotNull(map, "map");
                map.put(invoke, obj);
            }
        }
        return map;
    }
}
