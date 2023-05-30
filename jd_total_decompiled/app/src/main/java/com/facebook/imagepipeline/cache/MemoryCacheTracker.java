package com.facebook.imagepipeline.cache;

/* loaded from: classes.dex */
public interface MemoryCacheTracker<K> {
    void onCacheHit(K k2);

    void onCacheMiss(K k2);

    void onCachePut(K k2);
}
