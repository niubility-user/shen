package com.google.common.graph;

import com.google.common.graph.GraphConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public final class ConfigurableMutableGraph<N> extends ForwardingGraph<N> implements MutableGraph<N> {
    private final MutableValueGraph<N, GraphConstants.Presence> backingValueGraph;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigurableMutableGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder) {
        this.backingValueGraph = new ConfigurableMutableValueGraph(abstractGraphBuilder);
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean addNode(N n2) {
        return this.backingValueGraph.addNode(n2);
    }

    @Override // com.google.common.graph.ForwardingGraph
    protected BaseGraph<N> delegate() {
        return this.backingValueGraph;
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean putEdge(N n2, N n3) {
        return this.backingValueGraph.putEdgeValue(n2, n3, GraphConstants.Presence.EDGE_EXISTS) == null;
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean removeEdge(N n2, N n3) {
        return this.backingValueGraph.removeEdge(n2, n3) != null;
    }

    @Override // com.google.common.graph.MutableGraph
    public boolean removeNode(N n2) {
        return this.backingValueGraph.removeNode(n2);
    }
}
