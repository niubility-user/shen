package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
/* loaded from: classes12.dex */
public final class Graphs {

    /* loaded from: classes12.dex */
    public enum NodeVisitState {
        PENDING,
        COMPLETE
    }

    /* loaded from: classes12.dex */
    private static class TransposedGraph<N> extends ForwardingGraph<N> {
        private final Graph<N> graph;

        TransposedGraph(Graph<N> graph) {
            this.graph = graph;
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public boolean hasEdgeConnecting(N n2, N n3) {
            return delegate().hasEdgeConnecting(n3, n2);
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int inDegree(N n2) {
            return delegate().outDegree(n2);
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int outDegree(N n2) {
            return delegate().inDegree(n2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.PredecessorsFunction
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((TransposedGraph<N>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.SuccessorsFunction
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((TransposedGraph<N>) obj);
        }

        @Override // com.google.common.graph.ForwardingGraph
        public Graph<N> delegate() {
            return this.graph;
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N n2) {
            return delegate().successors((Graph<N>) n2);
        }

        @Override // com.google.common.graph.ForwardingGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N n2) {
            return delegate().predecessors((Graph<N>) n2);
        }
    }

    /* loaded from: classes12.dex */
    private static class TransposedNetwork<N, E> extends ForwardingNetwork<N, E> {
        private final Network<N, E> network;

        TransposedNetwork(Network<N, E> network) {
            this.network = network;
        }

        @Override // com.google.common.graph.ForwardingNetwork
        protected Network<N, E> delegate() {
            return this.network;
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public E edgeConnectingOrNull(N n2, N n3) {
            return delegate().edgeConnectingOrNull(n3, n2);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public Set<E> edgesConnecting(N n2, N n3) {
            return delegate().edgesConnecting(n3, n2);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public boolean hasEdgeConnecting(N n2, N n3) {
            return delegate().hasEdgeConnecting(n3, n2);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public int inDegree(N n2) {
            return delegate().outDegree(n2);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public Set<E> inEdges(N n2) {
            return delegate().outEdges(n2);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public EndpointPair<N> incidentNodes(E e2) {
            EndpointPair<N> incidentNodes = delegate().incidentNodes(e2);
            return EndpointPair.of((Network<?, ?>) this.network, (Object) incidentNodes.nodeV(), (Object) incidentNodes.nodeU());
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.AbstractNetwork, com.google.common.graph.Network
        public int outDegree(N n2) {
            return delegate().inDegree(n2);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network
        public Set<E> outEdges(N n2) {
            return delegate().inEdges(n2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.PredecessorsFunction
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((TransposedNetwork<N, E>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.SuccessorsFunction
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((TransposedNetwork<N, E>) obj);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N n2) {
            return delegate().successors((Network<N, E>) n2);
        }

        @Override // com.google.common.graph.ForwardingNetwork, com.google.common.graph.Network, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N n2) {
            return delegate().predecessors((Network<N, E>) n2);
        }
    }

    /* loaded from: classes12.dex */
    private static class TransposedValueGraph<N, V> extends ForwardingValueGraph<N, V> {
        private final ValueGraph<N, V> graph;

        TransposedValueGraph(ValueGraph<N, V> valueGraph) {
            this.graph = valueGraph;
        }

        @Override // com.google.common.graph.ForwardingValueGraph
        protected ValueGraph<N, V> delegate() {
            return this.graph;
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.ValueGraph
        @NullableDecl
        public V edgeValueOrDefault(N n2, N n3, @NullableDecl V v) {
            return delegate().edgeValueOrDefault(n3, n2, v);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public boolean hasEdgeConnecting(N n2, N n3) {
            return delegate().hasEdgeConnecting(n3, n2);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int inDegree(N n2) {
            return delegate().outDegree(n2);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
        public int outDegree(N n2) {
            return delegate().inDegree(n2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.PredecessorsFunction
        public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
            return predecessors((TransposedValueGraph<N, V>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.SuccessorsFunction
        public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
            return successors((TransposedValueGraph<N, V>) obj);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction
        public Set<N> predecessors(N n2) {
            return delegate().successors((ValueGraph<N, V>) n2);
        }

        @Override // com.google.common.graph.ForwardingValueGraph, com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction
        public Set<N> successors(N n2) {
            return delegate().predecessors((ValueGraph<N, V>) n2);
        }
    }

    private Graphs() {
    }

    private static boolean canTraverseWithoutReusingEdge(Graph<?> graph, Object obj, @NullableDecl Object obj2) {
        return graph.isDirected() || !Objects.equal(obj2, obj);
    }

    @CanIgnoreReturnValue
    public static int checkNonNegative(int i2) {
        Preconditions.checkArgument(i2 >= 0, "Not true that %s is non-negative.", i2);
        return i2;
    }

    @CanIgnoreReturnValue
    public static int checkPositive(int i2) {
        Preconditions.checkArgument(i2 > 0, "Not true that %s is positive.", i2);
        return i2;
    }

    public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
        MutableGraph<N> mutableGraph = (MutableGraph<N>) GraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();
        Iterator<N> it = graph.nodes().iterator();
        while (it.hasNext()) {
            mutableGraph.addNode(it.next());
        }
        for (EndpointPair<N> endpointPair : graph.edges()) {
            mutableGraph.putEdge(endpointPair.nodeU(), endpointPair.nodeV());
        }
        return mutableGraph;
    }

    @Deprecated
    public static boolean equivalent(@NullableDecl Graph<?> graph, @NullableDecl Graph<?> graph2) {
        return Objects.equal(graph, graph2);
    }

    public static <N> boolean hasCycle(Graph<N> graph) {
        int size = graph.edges().size();
        if (size == 0) {
            return false;
        }
        if (graph.isDirected() || size < graph.nodes().size()) {
            HashMap newHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(graph.nodes().size());
            Iterator<N> it = graph.nodes().iterator();
            while (it.hasNext()) {
                if (subgraphHasCycle(graph, newHashMapWithExpectedSize, it.next(), null)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static <N> MutableGraph<N> inducedSubgraph(Graph<N> graph, Iterable<? extends N> iterable) {
        MutableGraph<N> mutableGraph;
        if (iterable instanceof Collection) {
            mutableGraph = GraphBuilder.from(graph).expectedNodeCount(((Collection) iterable).size()).build();
        } else {
            mutableGraph = GraphBuilder.from(graph).build();
        }
        Iterator<? extends N> it = iterable.iterator();
        while (it.hasNext()) {
            mutableGraph.addNode(it.next());
        }
        for (N n2 : mutableGraph.nodes()) {
            for (N n3 : graph.successors((Graph<N>) n2)) {
                if (mutableGraph.nodes().contains(n3)) {
                    mutableGraph.putEdge(n2, n3);
                }
            }
        }
        return mutableGraph;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <N> Set<N> reachableNodes(Graph<N> graph, N n2) {
        Preconditions.checkArgument(graph.nodes().contains(n2), "Node %s is not an element of this graph.", n2);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayDeque arrayDeque = new ArrayDeque();
        linkedHashSet.add(n2);
        arrayDeque.add(n2);
        while (!arrayDeque.isEmpty()) {
            for (Object obj : graph.successors((Graph<N>) arrayDeque.remove())) {
                if (linkedHashSet.add(obj)) {
                    arrayDeque.add(obj);
                }
            }
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    private static <N> boolean subgraphHasCycle(Graph<N> graph, Map<Object, NodeVisitState> map, N n2, @NullableDecl N n3) {
        NodeVisitState nodeVisitState = map.get(n2);
        if (nodeVisitState == NodeVisitState.COMPLETE) {
            return false;
        }
        NodeVisitState nodeVisitState2 = NodeVisitState.PENDING;
        if (nodeVisitState == nodeVisitState2) {
            return true;
        }
        map.put(n2, nodeVisitState2);
        for (N n4 : graph.successors((Graph<N>) n2)) {
            if (canTraverseWithoutReusingEdge(graph, n4, n3) && subgraphHasCycle(graph, map, n4, n2)) {
                return true;
            }
        }
        map.put(n2, NodeVisitState.COMPLETE);
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.common.graph.MutableGraph, com.google.common.graph.Graph<N>] */
    public static <N> Graph<N> transitiveClosure(Graph<N> graph) {
        ?? build = GraphBuilder.from(graph).allowsSelfLoops(true).build();
        if (graph.isDirected()) {
            for (N n2 : graph.nodes()) {
                Iterator it = reachableNodes(graph, n2).iterator();
                while (it.hasNext()) {
                    build.putEdge(n2, it.next());
                }
            }
        } else {
            HashSet hashSet = new HashSet();
            for (N n3 : graph.nodes()) {
                if (!hashSet.contains(n3)) {
                    Set reachableNodes = reachableNodes(graph, n3);
                    hashSet.addAll(reachableNodes);
                    int i2 = 1;
                    for (Object obj : reachableNodes) {
                        int i3 = i2 + 1;
                        Iterator it2 = Iterables.limit(reachableNodes, i2).iterator();
                        while (it2.hasNext()) {
                            build.putEdge(obj, it2.next());
                        }
                        i2 = i3;
                    }
                }
            }
        }
        return build;
    }

    public static <N> Graph<N> transpose(Graph<N> graph) {
        if (graph.isDirected()) {
            if (graph instanceof TransposedGraph) {
                return ((TransposedGraph) graph).graph;
            }
            return new TransposedGraph(graph);
        }
        return graph;
    }

    @CanIgnoreReturnValue
    public static long checkNonNegative(long j2) {
        Preconditions.checkArgument(j2 >= 0, "Not true that %s is non-negative.", j2);
        return j2;
    }

    @CanIgnoreReturnValue
    public static long checkPositive(long j2) {
        Preconditions.checkArgument(j2 > 0, "Not true that %s is positive.", j2);
        return j2;
    }

    @Deprecated
    public static boolean equivalent(@NullableDecl ValueGraph<?, ?> valueGraph, @NullableDecl ValueGraph<?, ?> valueGraph2) {
        return Objects.equal(valueGraph, valueGraph2);
    }

    @Deprecated
    public static boolean equivalent(@NullableDecl Network<?, ?> network, @NullableDecl Network<?, ?> network2) {
        return Objects.equal(network, network2);
    }

    public static <N, V> ValueGraph<N, V> transpose(ValueGraph<N, V> valueGraph) {
        if (valueGraph.isDirected()) {
            if (valueGraph instanceof TransposedValueGraph) {
                return ((TransposedValueGraph) valueGraph).graph;
            }
            return new TransposedValueGraph(valueGraph);
        }
        return valueGraph;
    }

    public static <N, V> MutableValueGraph<N, V> copyOf(ValueGraph<N, V> valueGraph) {
        MutableValueGraph<N, V> mutableValueGraph = (MutableValueGraph<N, V>) ValueGraphBuilder.from(valueGraph).expectedNodeCount(valueGraph.nodes().size()).build();
        Iterator<N> it = valueGraph.nodes().iterator();
        while (it.hasNext()) {
            mutableValueGraph.addNode(it.next());
        }
        for (EndpointPair<N> endpointPair : valueGraph.edges()) {
            mutableValueGraph.putEdgeValue(endpointPair.nodeU(), endpointPair.nodeV(), valueGraph.edgeValueOrDefault(endpointPair.nodeU(), endpointPair.nodeV(), null));
        }
        return mutableValueGraph;
    }

    public static boolean hasCycle(Network<?, ?> network) {
        if (network.isDirected() || !network.allowsParallelEdges() || network.edges().size() <= network.asGraph().edges().size()) {
            return hasCycle(network.asGraph());
        }
        return true;
    }

    public static <N, E> Network<N, E> transpose(Network<N, E> network) {
        if (network.isDirected()) {
            if (network instanceof TransposedNetwork) {
                return ((TransposedNetwork) network).network;
            }
            return new TransposedNetwork(network);
        }
        return network;
    }

    public static <N, V> MutableValueGraph<N, V> inducedSubgraph(ValueGraph<N, V> valueGraph, Iterable<? extends N> iterable) {
        MutableValueGraph<N, V> mutableValueGraph;
        if (iterable instanceof Collection) {
            mutableValueGraph = ValueGraphBuilder.from(valueGraph).expectedNodeCount(((Collection) iterable).size()).build();
        } else {
            mutableValueGraph = ValueGraphBuilder.from(valueGraph).build();
        }
        Iterator<? extends N> it = iterable.iterator();
        while (it.hasNext()) {
            mutableValueGraph.addNode(it.next());
        }
        for (N n2 : mutableValueGraph.nodes()) {
            for (N n3 : valueGraph.successors((ValueGraph<N, V>) n2)) {
                if (mutableValueGraph.nodes().contains(n3)) {
                    mutableValueGraph.putEdgeValue(n2, n3, valueGraph.edgeValueOrDefault(n2, n3, null));
                }
            }
        }
        return mutableValueGraph;
    }

    public static <N, E> MutableNetwork<N, E> copyOf(Network<N, E> network) {
        MutableNetwork<N, E> mutableNetwork = (MutableNetwork<N, E>) NetworkBuilder.from(network).expectedNodeCount(network.nodes().size()).expectedEdgeCount(network.edges().size()).build();
        Iterator<N> it = network.nodes().iterator();
        while (it.hasNext()) {
            mutableNetwork.addNode(it.next());
        }
        for (E e2 : network.edges()) {
            EndpointPair<N> incidentNodes = network.incidentNodes(e2);
            mutableNetwork.addEdge(incidentNodes.nodeU(), incidentNodes.nodeV(), e2);
        }
        return mutableNetwork;
    }

    public static <N, E> MutableNetwork<N, E> inducedSubgraph(Network<N, E> network, Iterable<? extends N> iterable) {
        MutableNetwork<N, E> mutableNetwork;
        if (iterable instanceof Collection) {
            mutableNetwork = NetworkBuilder.from(network).expectedNodeCount(((Collection) iterable).size()).build();
        } else {
            mutableNetwork = NetworkBuilder.from(network).build();
        }
        Iterator<? extends N> it = iterable.iterator();
        while (it.hasNext()) {
            mutableNetwork.addNode(it.next());
        }
        for (N n2 : mutableNetwork.nodes()) {
            for (E e2 : network.outEdges(n2)) {
                N adjacentNode = network.incidentNodes(e2).adjacentNode(n2);
                if (mutableNetwork.nodes().contains(adjacentNode)) {
                    mutableNetwork.addEdge(n2, adjacentNode, e2);
                }
            }
        }
        return mutableNetwork;
    }
}
