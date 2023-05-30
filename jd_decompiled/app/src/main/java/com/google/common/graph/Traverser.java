package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

@Beta
/* loaded from: classes12.dex */
public abstract class Traverser<N> {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static final class GraphTraverser<N> extends Traverser<N> {
        private final SuccessorsFunction<N> graph;

        /* loaded from: classes12.dex */
        private final class BreadthFirstIterator extends UnmodifiableIterator<N> {
            private final Queue<N> queue;
            private final Set<N> visited;

            BreadthFirstIterator(N n2) {
                ArrayDeque arrayDeque = new ArrayDeque();
                this.queue = arrayDeque;
                HashSet hashSet = new HashSet();
                this.visited = hashSet;
                arrayDeque.add(n2);
                hashSet.add(n2);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return !this.queue.isEmpty();
            }

            @Override // java.util.Iterator
            public N next() {
                N remove = this.queue.remove();
                for (N n2 : GraphTraverser.this.graph.successors(remove)) {
                    if (this.visited.add(n2)) {
                        this.queue.add(n2);
                    }
                }
                return remove;
            }
        }

        /* loaded from: classes12.dex */
        private final class DepthFirstIterator extends AbstractIterator<N> {
            private final Order order;
            private final Deque<GraphTraverser<N>.DepthFirstIterator.NodeAndSuccessors> stack;
            private final Set<N> visited;

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX WARN: Field signature parse error: node
            jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: TN at position 1 ('N'), unexpected: T
            	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
            	at jadx.core.dex.visitors.SignatureProcessor.parseFieldSignature(SignatureProcessor.java:128)
            	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:36)
             */
            /* loaded from: classes12.dex */
            public final class NodeAndSuccessors {
                final Object node;
                final Iterator<? extends N> successorIterator;

                NodeAndSuccessors(N n2, Iterable<? extends N> iterable) {
                    this.node = n2;
                    this.successorIterator = iterable.iterator();
                }
            }

            DepthFirstIterator(N n2, Order order) {
                ArrayDeque arrayDeque = new ArrayDeque();
                this.stack = arrayDeque;
                this.visited = new HashSet();
                arrayDeque.push(withSuccessors(n2));
                this.order = order;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.AbstractIterator
            protected N computeNext() {
                while (!this.stack.isEmpty()) {
                    GraphTraverser<N>.DepthFirstIterator.NodeAndSuccessors first = this.stack.getFirst();
                    boolean add = this.visited.add(first.node);
                    boolean z = true;
                    boolean z2 = !first.successorIterator.hasNext();
                    if ((!add || this.order != Order.PREORDER) && (!z2 || this.order != Order.POSTORDER)) {
                        z = false;
                    }
                    if (z2) {
                        this.stack.pop();
                    } else {
                        N next = first.successorIterator.next();
                        if (!this.visited.contains(next)) {
                            this.stack.push(withSuccessors(next));
                        }
                    }
                    if (z) {
                        return (N) first.node;
                    }
                }
                return (N) endOfData();
            }

            GraphTraverser<N>.DepthFirstIterator.NodeAndSuccessors withSuccessors(N n2) {
                return new NodeAndSuccessors(n2, GraphTraverser.this.graph.successors(n2));
            }
        }

        GraphTraverser(SuccessorsFunction<N> successorsFunction) {
            super();
            this.graph = (SuccessorsFunction) Preconditions.checkNotNull(successorsFunction);
        }

        private void checkThatNodeIsInGraph(N n2) {
            this.graph.successors(n2);
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> breadthFirst(final N n2) {
            Preconditions.checkNotNull(n2);
            checkThatNodeIsInGraph(n2);
            return new Iterable<N>() { // from class: com.google.common.graph.Traverser.GraphTraverser.1
                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new BreadthFirstIterator(n2);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPostOrder(final N n2) {
            Preconditions.checkNotNull(n2);
            checkThatNodeIsInGraph(n2);
            return new Iterable<N>() { // from class: com.google.common.graph.Traverser.GraphTraverser.3
                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstIterator(n2, Order.POSTORDER);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPreOrder(final N n2) {
            Preconditions.checkNotNull(n2);
            checkThatNodeIsInGraph(n2);
            return new Iterable<N>() { // from class: com.google.common.graph.Traverser.GraphTraverser.2
                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstIterator(n2, Order.PREORDER);
                }
            };
        }
    }

    /* loaded from: classes12.dex */
    private enum Order {
        PREORDER,
        POSTORDER
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static final class TreeTraverser<N> extends Traverser<N> {
        private final SuccessorsFunction<N> tree;

        /* loaded from: classes12.dex */
        private final class BreadthFirstIterator extends UnmodifiableIterator<N> {
            private final Queue<N> queue;

            BreadthFirstIterator(N n2) {
                ArrayDeque arrayDeque = new ArrayDeque();
                this.queue = arrayDeque;
                arrayDeque.add(n2);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return !this.queue.isEmpty();
            }

            @Override // java.util.Iterator
            public N next() {
                N remove = this.queue.remove();
                Iterables.addAll(this.queue, TreeTraverser.this.tree.successors(remove));
                return remove;
            }
        }

        /* loaded from: classes12.dex */
        private final class DepthFirstPostOrderIterator extends AbstractIterator<N> {
            private final ArrayDeque<TreeTraverser<N>.DepthFirstPostOrderIterator.NodeAndChildren> stack;

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX WARN: Field signature parse error: node
            jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: TN at position 1 ('N'), unexpected: T
            	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
            	at jadx.core.dex.visitors.SignatureProcessor.parseFieldSignature(SignatureProcessor.java:128)
            	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:36)
             */
            /* loaded from: classes12.dex */
            public final class NodeAndChildren {
                final Iterator<? extends N> childIterator;
                final Object node;

                NodeAndChildren(N n2, Iterable<? extends N> iterable) {
                    this.node = n2;
                    this.childIterator = iterable.iterator();
                }
            }

            DepthFirstPostOrderIterator(N n2) {
                ArrayDeque<TreeTraverser<N>.DepthFirstPostOrderIterator.NodeAndChildren> arrayDeque = new ArrayDeque<>();
                this.stack = arrayDeque;
                arrayDeque.addLast(withChildren(n2));
            }

            @Override // com.google.common.collect.AbstractIterator
            protected N computeNext() {
                while (!this.stack.isEmpty()) {
                    TreeTraverser<N>.DepthFirstPostOrderIterator.NodeAndChildren last = this.stack.getLast();
                    if (last.childIterator.hasNext()) {
                        this.stack.addLast(withChildren(last.childIterator.next()));
                    } else {
                        this.stack.removeLast();
                        return (N) last.node;
                    }
                }
                return (N) endOfData();
            }

            TreeTraverser<N>.DepthFirstPostOrderIterator.NodeAndChildren withChildren(N n2) {
                return new NodeAndChildren(n2, TreeTraverser.this.tree.successors(n2));
            }
        }

        /* loaded from: classes12.dex */
        private final class DepthFirstPreOrderIterator extends UnmodifiableIterator<N> {
            private final Deque<Iterator<? extends N>> stack;

            DepthFirstPreOrderIterator(N n2) {
                ArrayDeque arrayDeque = new ArrayDeque();
                this.stack = arrayDeque;
                arrayDeque.addLast(Iterators.singletonIterator(Preconditions.checkNotNull(n2)));
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return !this.stack.isEmpty();
            }

            @Override // java.util.Iterator
            public N next() {
                Iterator<? extends N> last = this.stack.getLast();
                N n2 = (N) Preconditions.checkNotNull(last.next());
                if (!last.hasNext()) {
                    this.stack.removeLast();
                }
                Iterator<? extends N> it = TreeTraverser.this.tree.successors(n2).iterator();
                if (it.hasNext()) {
                    this.stack.addLast(it);
                }
                return n2;
            }
        }

        TreeTraverser(SuccessorsFunction<N> successorsFunction) {
            super();
            this.tree = (SuccessorsFunction) Preconditions.checkNotNull(successorsFunction);
        }

        private void checkThatNodeIsInTree(N n2) {
            this.tree.successors(n2);
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> breadthFirst(final N n2) {
            Preconditions.checkNotNull(n2);
            checkThatNodeIsInTree(n2);
            return new Iterable<N>() { // from class: com.google.common.graph.Traverser.TreeTraverser.1
                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new BreadthFirstIterator(n2);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPostOrder(final N n2) {
            Preconditions.checkNotNull(n2);
            checkThatNodeIsInTree(n2);
            return new Iterable<N>() { // from class: com.google.common.graph.Traverser.TreeTraverser.3
                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstPostOrderIterator(n2);
                }
            };
        }

        @Override // com.google.common.graph.Traverser
        public Iterable<N> depthFirstPreOrder(final N n2) {
            Preconditions.checkNotNull(n2);
            checkThatNodeIsInTree(n2);
            return new Iterable<N>() { // from class: com.google.common.graph.Traverser.TreeTraverser.2
                @Override // java.lang.Iterable
                public Iterator<N> iterator() {
                    return new DepthFirstPreOrderIterator(n2);
                }
            };
        }
    }

    public static <N> Traverser<N> forGraph(SuccessorsFunction<N> successorsFunction) {
        Preconditions.checkNotNull(successorsFunction);
        return new GraphTraverser(successorsFunction);
    }

    public static <N> Traverser<N> forTree(SuccessorsFunction<N> successorsFunction) {
        Preconditions.checkNotNull(successorsFunction);
        if (successorsFunction instanceof BaseGraph) {
            Preconditions.checkArgument(((BaseGraph) successorsFunction).isDirected(), "Undirected graphs can never be trees.");
        }
        if (successorsFunction instanceof Network) {
            Preconditions.checkArgument(((Network) successorsFunction).isDirected(), "Undirected networks can never be trees.");
        }
        return new TreeTraverser(successorsFunction);
    }

    public abstract Iterable<N> breadthFirst(N n2);

    public abstract Iterable<N> depthFirstPostOrder(N n2);

    public abstract Iterable<N> depthFirstPreOrder(N n2);

    private Traverser() {
    }
}
