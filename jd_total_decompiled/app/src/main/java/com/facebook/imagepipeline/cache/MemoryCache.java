package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Predicate;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface MemoryCache<K, V> {

    /* loaded from: classes.dex */
    public interface CacheTrimStrategy {
        double getTrimRatio(MemoryTrimType memoryTrimType);
    }

    @Nullable
    CloseableReference<V> cache(K k2, CloseableReference<V> closeableReference);

    boolean contains(Predicate<K> predicate);

    boolean contains(K k2);

    @Nullable
    CloseableReference<V> get(K k2);

    int getCount();

    int getSizeInBytes();

    int removeAll(Predicate<K> predicate);
}
