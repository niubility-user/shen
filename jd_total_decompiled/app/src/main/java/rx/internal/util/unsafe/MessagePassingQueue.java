package rx.internal.util.unsafe;

/* loaded from: classes11.dex */
interface MessagePassingQueue<M> {
    boolean isEmpty();

    boolean offer(M m2);

    M peek();

    M poll();

    int size();
}
