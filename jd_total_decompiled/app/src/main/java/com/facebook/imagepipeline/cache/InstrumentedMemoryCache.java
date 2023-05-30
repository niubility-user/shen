package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Predicate;
import com.facebook.common.references.CloseableReference;

/* loaded from: classes.dex */
public class InstrumentedMemoryCache<K, V> implements MemoryCache<K, V> {
    private final MemoryCache<K, V> mDelegate;
    private final MemoryCacheTracker mTracker;

    public InstrumentedMemoryCache(MemoryCache<K, V> memoryCache, MemoryCacheTracker memoryCacheTracker) {
        this.mDelegate = memoryCache;
        this.mTracker = memoryCacheTracker;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public CloseableReference<V> cache(K k2, CloseableReference<V> closeableReference) {
        this.mTracker.onCachePut(k2);
        return this.mDelegate.cache(k2, closeableReference);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public boolean contains(Predicate<K> predicate) {
        return this.mDelegate.contains((Predicate) predicate);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public boolean contains(K k2) {
        return this.mDelegate.contains((MemoryCache<K, V>) k2);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public CloseableReference<V> get(K k2) {
        CloseableReference<V> closeableReference = this.mDelegate.get(k2);
        MemoryCacheTracker memoryCacheTracker = this.mTracker;
        if (closeableReference == null) {
            memoryCacheTracker.onCacheMiss(k2);
        } else {
            memoryCacheTracker.onCacheHit(k2);
        }
        return closeableReference;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public int getCount() {
        return this.mDelegate.getCount();
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public int getSizeInBytes() {
        return this.mDelegate.getSizeInBytes();
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public int removeAll(Predicate<K> predicate) {
        return this.mDelegate.removeAll(predicate);
    }
}
