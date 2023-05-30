package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractSet;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class MapIteratorCache<K, V> {
    private final Map<K, V> backingMap;
    @NullableDecl
    private transient Map.Entry<K, V> entrySetCache;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MapIteratorCache(Map<K, V> map) {
        this.backingMap = (Map) Preconditions.checkNotNull(map);
    }

    public void clear() {
        clearCache();
        this.backingMap.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void clearCache() {
        this.entrySetCache = null;
    }

    public final boolean containsKey(@NullableDecl Object obj) {
        return getIfCached(obj) != null || this.backingMap.containsKey(obj);
    }

    public V get(@NullableDecl Object obj) {
        V ifCached = getIfCached(obj);
        return ifCached != null ? ifCached : getWithoutCaching(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public V getIfCached(@NullableDecl Object obj) {
        Map.Entry<K, V> entry = this.entrySetCache;
        if (entry == null || entry.getKey() != obj) {
            return null;
        }
        return entry.getValue();
    }

    public final V getWithoutCaching(@NullableDecl Object obj) {
        return this.backingMap.get(obj);
    }

    @CanIgnoreReturnValue
    public V put(@NullableDecl K k2, @NullableDecl V v) {
        clearCache();
        return this.backingMap.put(k2, v);
    }

    @CanIgnoreReturnValue
    public V remove(@NullableDecl Object obj) {
        clearCache();
        return this.backingMap.remove(obj);
    }

    public final Set<K> unmodifiableKeySet() {
        return new AbstractSet<K>() { // from class: com.google.common.graph.MapIteratorCache.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                return MapIteratorCache.this.containsKey(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return MapIteratorCache.this.backingMap.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<K> iterator() {
                MapIteratorCache.this.backingMap.entrySet().iterator();
                return new UnmodifiableIterator<K>
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0013: RETURN 
                      (wrap: com.google.common.collect.UnmodifiableIterator<K> : 0x0010: CONSTRUCTOR 
                      (r2v0 'this' com.google.common.graph.MapIteratorCache$1 A[IMMUTABLE_TYPE, THIS])
                      (r0 I:java.util.Iterator A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[MD:(com.google.common.graph.MapIteratorCache$1, java.util.Iterator):void (m), WRAPPED] (LINE:3) call: com.google.common.graph.MapIteratorCache.1.1.<init>(com.google.common.graph.MapIteratorCache$1, java.util.Iterator):void type: CONSTRUCTOR)
                     (LINE:3) in method: com.google.common.graph.MapIteratorCache.1.iterator():com.google.common.collect.UnmodifiableIterator<K>, file: classes12.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: java.lang.NullPointerException
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                    	... 15 more
                    */
                /*
                    this = this;
                    com.google.common.graph.MapIteratorCache r0 = com.google.common.graph.MapIteratorCache.this
                    java.util.Map r0 = com.google.common.graph.MapIteratorCache.access$000(r0)
                    java.util.Set r0 = r0.entrySet()
                    java.util.Iterator r0 = r0.iterator()
                    com.google.common.graph.MapIteratorCache$1$1 r1 = new com.google.common.graph.MapIteratorCache$1$1
                    r1.<init>()
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.MapIteratorCache.AnonymousClass1.iterator():com.google.common.collect.UnmodifiableIterator");
            }
        };
    }
}
