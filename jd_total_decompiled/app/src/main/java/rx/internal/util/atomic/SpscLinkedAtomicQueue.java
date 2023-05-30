package rx.internal.util.atomic;

/* loaded from: classes11.dex */
public final class SpscLinkedAtomicQueue<E> extends BaseLinkedAtomicQueue<E> {
    public SpscLinkedAtomicQueue() {
        LinkedQueueNode<E> linkedQueueNode = new LinkedQueueNode<>();
        spProducerNode(linkedQueueNode);
        spConsumerNode(linkedQueueNode);
        linkedQueueNode.soNext(null);
    }

    @Override // java.util.Queue
    public boolean offer(E e2) {
        if (e2 != null) {
            LinkedQueueNode<E> linkedQueueNode = new LinkedQueueNode<>(e2);
            lpProducerNode().soNext(linkedQueueNode);
            spProducerNode(linkedQueueNode);
            return true;
        }
        throw new NullPointerException("null elements not allowed");
    }

    @Override // java.util.Queue
    public E peek() {
        LinkedQueueNode<E> lvNext = lpConsumerNode().lvNext();
        if (lvNext != null) {
            return lvNext.lpValue();
        }
        return null;
    }

    @Override // java.util.Queue
    public E poll() {
        LinkedQueueNode<E> lvNext = lpConsumerNode().lvNext();
        if (lvNext != null) {
            E andNullValue = lvNext.getAndNullValue();
            spConsumerNode(lvNext);
            return andNullValue;
        }
        return null;
    }
}
