package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes12.dex */
public final class DirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    private static final Object PRED = new Object();
    private final Map<N, Object> adjacentNodeValues;
    private int predecessorCount;
    private int successorCount;

    /* loaded from: classes12.dex */
    public static final class PredAndSucc {
        private final Object successorValue;

        PredAndSucc(Object obj) {
            this.successorValue = obj;
        }
    }

    private DirectedGraphConnections(Map<N, Object> map, int i2, int i3) {
        this.adjacentNodeValues = (Map) Preconditions.checkNotNull(map);
        this.predecessorCount = Graphs.checkNonNegative(i2);
        this.successorCount = Graphs.checkNonNegative(i3);
        Preconditions.checkState(i2 <= map.size() && i3 <= map.size());
    }

    public static boolean isPredecessor(@NullableDecl Object obj) {
        return obj == PRED || (obj instanceof PredAndSucc);
    }

    public static boolean isSuccessor(@NullableDecl Object obj) {
        return (obj == PRED || obj == null) ? false : true;
    }

    public static <N, V> DirectedGraphConnections<N, V> of() {
        return new DirectedGraphConnections<>(new HashMap(4, 1.0f), 0, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <N, V> DirectedGraphConnections<N, V> ofImmutable(Set<N> set, Map<N, V> map) {
        HashMap hashMap = new HashMap();
        hashMap.putAll(map);
        for (N n2 : set) {
            Object put = hashMap.put(n2, PRED);
            if (put != null) {
                hashMap.put(n2, new PredAndSucc(put));
            }
        }
        return new DirectedGraphConnections<>(ImmutableMap.copyOf((Map) hashMap), set.size(), map.size());
    }

    @Override // com.google.common.graph.GraphConnections
    public void addPredecessor(N n2, V v) {
        Map<N, Object> map = this.adjacentNodeValues;
        Object obj = PRED;
        Object put = map.put(n2, obj);
        if (put == null) {
            int i2 = this.predecessorCount + 1;
            this.predecessorCount = i2;
            Graphs.checkPositive(i2);
        } else if (put instanceof PredAndSucc) {
            this.adjacentNodeValues.put(n2, put);
        } else if (put != obj) {
            this.adjacentNodeValues.put(n2, new PredAndSucc(put));
            int i3 = this.predecessorCount + 1;
            this.predecessorCount = i3;
            Graphs.checkPositive(i3);
        }
    }

    @Override // com.google.common.graph.GraphConnections
    public V addSuccessor(N n2, V v) {
        V v2 = (V) this.adjacentNodeValues.put(n2, v);
        if (v2 == null) {
            int i2 = this.successorCount + 1;
            this.successorCount = i2;
            Graphs.checkPositive(i2);
            return null;
        } else if (v2 instanceof PredAndSucc) {
            this.adjacentNodeValues.put(n2, new PredAndSucc(v));
            return (V) ((PredAndSucc) v2).successorValue;
        } else if (v2 == PRED) {
            this.adjacentNodeValues.put(n2, new PredAndSucc(v));
            int i3 = this.successorCount + 1;
            this.successorCount = i3;
            Graphs.checkPositive(i3);
            return null;
        } else {
            return v2;
        }
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(this.adjacentNodeValues.keySet());
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> predecessors() {
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.1
            {
                DirectedGraphConnections.this = this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.predecessorCount;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                return new AbstractIterator<N>
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0013: RETURN 
                      (wrap: com.google.common.collect.AbstractIterator<N> : 0x0010: CONSTRUCTOR 
                      (r2v0 'this' com.google.common.graph.DirectedGraphConnections$1 A[IMMUTABLE_TYPE, THIS])
                      (r0 I:java.util.Iterator A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[MD:(com.google.common.graph.DirectedGraphConnections$1, java.util.Iterator):void (m), WRAPPED] (LINE:3) call: com.google.common.graph.DirectedGraphConnections.1.1.<init>(com.google.common.graph.DirectedGraphConnections$1, java.util.Iterator):void type: CONSTRUCTOR)
                     (LINE:3) in method: com.google.common.graph.DirectedGraphConnections.1.iterator():com.google.common.collect.UnmodifiableIterator<N>, file: classes12.dex
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
                    */
                /*
                    this = this;
                    com.google.common.graph.DirectedGraphConnections r0 = com.google.common.graph.DirectedGraphConnections.this
                    java.util.Map r0 = com.google.common.graph.DirectedGraphConnections.access$000(r0)
                    java.util.Set r0 = r0.entrySet()
                    java.util.Iterator r0 = r0.iterator()
                    com.google.common.graph.DirectedGraphConnections$1$1 r1 = new com.google.common.graph.DirectedGraphConnections$1$1
                    r1.<init>()
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.DirectedGraphConnections.AnonymousClass1.iterator():com.google.common.collect.UnmodifiableIterator");
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public void removePredecessor(N n2) {
        Object obj = this.adjacentNodeValues.get(n2);
        if (obj == PRED) {
            this.adjacentNodeValues.remove(n2);
            int i2 = this.predecessorCount - 1;
            this.predecessorCount = i2;
            Graphs.checkNonNegative(i2);
        } else if (obj instanceof PredAndSucc) {
            this.adjacentNodeValues.put(n2, ((PredAndSucc) obj).successorValue);
            int i3 = this.predecessorCount - 1;
            this.predecessorCount = i3;
            Graphs.checkNonNegative(i3);
        }
    }

    @Override // com.google.common.graph.GraphConnections
    public V removeSuccessor(Object obj) {
        Object obj2;
        V v = (V) this.adjacentNodeValues.get(obj);
        if (v == null || v == (obj2 = PRED)) {
            return null;
        }
        if (v instanceof PredAndSucc) {
            this.adjacentNodeValues.put(obj, obj2);
            int i2 = this.successorCount - 1;
            this.successorCount = i2;
            Graphs.checkNonNegative(i2);
            return (V) ((PredAndSucc) v).successorValue;
        }
        this.adjacentNodeValues.remove(obj);
        int i3 = this.successorCount - 1;
        this.successorCount = i3;
        Graphs.checkNonNegative(i3);
        return v;
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> successors() {
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.2
            {
                DirectedGraphConnections.this = this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.successorCount;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                return new AbstractIterator<N>
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0013: RETURN 
                      (wrap: com.google.common.collect.AbstractIterator<N> : 0x0010: CONSTRUCTOR 
                      (r2v0 'this' com.google.common.graph.DirectedGraphConnections$2 A[IMMUTABLE_TYPE, THIS])
                      (r0 I:java.util.Iterator A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[MD:(com.google.common.graph.DirectedGraphConnections$2, java.util.Iterator):void (m), WRAPPED] (LINE:3) call: com.google.common.graph.DirectedGraphConnections.2.1.<init>(com.google.common.graph.DirectedGraphConnections$2, java.util.Iterator):void type: CONSTRUCTOR)
                     (LINE:3) in method: com.google.common.graph.DirectedGraphConnections.2.iterator():com.google.common.collect.UnmodifiableIterator<N>, file: classes12.dex
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
                    */
                /*
                    this = this;
                    com.google.common.graph.DirectedGraphConnections r0 = com.google.common.graph.DirectedGraphConnections.this
                    java.util.Map r0 = com.google.common.graph.DirectedGraphConnections.access$000(r0)
                    java.util.Set r0 = r0.entrySet()
                    java.util.Iterator r0 = r0.iterator()
                    com.google.common.graph.DirectedGraphConnections$2$1 r1 = new com.google.common.graph.DirectedGraphConnections$2$1
                    r1.<init>()
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.DirectedGraphConnections.AnonymousClass2.iterator():com.google.common.collect.UnmodifiableIterator");
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public V value(N n2) {
        V v = (V) this.adjacentNodeValues.get(n2);
        if (v == PRED) {
            return null;
        }
        return v instanceof PredAndSucc ? (V) ((PredAndSucc) v).successorValue : v;
    }
}
