package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.util.AbstractSet;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public abstract class AbstractBaseGraph<N> implements BaseGraph<N> {
    @Override // com.google.common.graph.BaseGraph
    public int degree(N n2) {
        if (isDirected()) {
            return IntMath.saturatedAdd(predecessors((AbstractBaseGraph<N>) n2).size(), successors((AbstractBaseGraph<N>) n2).size());
        }
        Set<N> adjacentNodes = adjacentNodes(n2);
        return IntMath.saturatedAdd(adjacentNodes.size(), (allowsSelfLoops() && adjacentNodes.contains(n2)) ? 1 : 0);
    }

    protected long edgeCount() {
        long j2 = 0;
        while (nodes().iterator().hasNext()) {
            j2 += degree(r0.next());
        }
        Preconditions.checkState((1 & j2) == 0);
        return j2 >>> 1;
    }

    @Override // com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> edges() {
        return new AbstractSet<EndpointPair<N>>() { // from class: com.google.common.graph.AbstractBaseGraph.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                if (obj instanceof EndpointPair) {
                    EndpointPair endpointPair = (EndpointPair) obj;
                    return AbstractBaseGraph.this.isDirected() == endpointPair.isOrdered() && AbstractBaseGraph.this.nodes().contains(endpointPair.nodeU()) && AbstractBaseGraph.this.successors((AbstractBaseGraph) endpointPair.nodeU()).contains(endpointPair.nodeV());
                }
                return false;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return Ints.saturatedCast(AbstractBaseGraph.this.edgeCount());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<EndpointPair<N>> iterator() {
                return EndpointPairIterator.of(AbstractBaseGraph.this);
            }
        };
    }

    @Override // com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(N n2, N n3) {
        Preconditions.checkNotNull(n2);
        Preconditions.checkNotNull(n3);
        return nodes().contains(n2) && successors((AbstractBaseGraph<N>) n2).contains(n3);
    }

    @Override // com.google.common.graph.BaseGraph
    public int inDegree(N n2) {
        return isDirected() ? predecessors((AbstractBaseGraph<N>) n2).size() : degree(n2);
    }

    @Override // com.google.common.graph.BaseGraph
    public int outDegree(N n2) {
        return isDirected() ? successors((AbstractBaseGraph<N>) n2).size() : degree(n2);
    }
}
