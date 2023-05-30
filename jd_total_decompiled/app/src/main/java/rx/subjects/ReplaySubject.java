package rx.subjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.annotations.Beta;
import rx.exceptions.Exceptions;
import rx.internal.operators.BackpressureUtils;
import rx.internal.util.RxJavaPluginUtils;

/* loaded from: classes11.dex */
public final class ReplaySubject<T> extends Subject<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    final ReplayState<T> state;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public interface ReplayBuffer<T> {
        void complete();

        boolean drain(ReplayProducer<T> replayProducer);

        Throwable error();

        void error(Throwable th);

        boolean isComplete();

        boolean isEmpty();

        T last();

        void next(T t);

        int size();

        T[] toArray(T[] tArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ReplayProducer<T> extends AtomicInteger implements Producer, Subscription {
        private static final long serialVersionUID = -5006209596735204567L;
        final Subscriber<? super T> actual;
        boolean caughtUp;
        int index;
        Object node;
        final AtomicLong requested = new AtomicLong();
        final ReplayState<T> state;
        int tailIndex;

        public ReplayProducer(Subscriber<? super T> subscriber, ReplayState<T> replayState) {
            this.actual = subscriber;
            this.state = replayState;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.actual.isUnsubscribed();
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (j2 > 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j2);
                this.state.buffer.drain(this);
            } else if (j2 >= 0) {
            } else {
                throw new IllegalArgumentException("n >= required but it was " + j2);
            }
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.state.remove(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ReplayState<T> extends AtomicReference<ReplayProducer<T>[]> implements Observable.OnSubscribe<T>, Observer<T> {
        static final ReplayProducer[] EMPTY = new ReplayProducer[0];
        static final ReplayProducer[] TERMINATED = new ReplayProducer[0];
        private static final long serialVersionUID = 5952362471246910544L;
        final ReplayBuffer<T> buffer;

        public ReplayState(ReplayBuffer<T> replayBuffer) {
            this.buffer = replayBuffer;
            lazySet(EMPTY);
        }

        boolean add(ReplayProducer<T> replayProducer) {
            ReplayProducer<T>[] replayProducerArr;
            ReplayProducer[] replayProducerArr2;
            do {
                replayProducerArr = get();
                if (replayProducerArr == TERMINATED) {
                    return false;
                }
                int length = replayProducerArr.length;
                replayProducerArr2 = new ReplayProducer[length + 1];
                System.arraycopy(replayProducerArr, 0, replayProducerArr2, 0, length);
                replayProducerArr2[length] = replayProducer;
            } while (!compareAndSet(replayProducerArr, replayProducerArr2));
            return true;
        }

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        boolean isTerminated() {
            return get() == TERMINATED;
        }

        @Override // rx.Observer
        public void onCompleted() {
            ReplayBuffer<T> replayBuffer = this.buffer;
            replayBuffer.complete();
            for (ReplayProducer<T> replayProducer : getAndSet(TERMINATED)) {
                if (replayProducer.caughtUp) {
                    replayProducer.actual.onCompleted();
                } else if (replayBuffer.drain(replayProducer)) {
                    replayProducer.caughtUp = true;
                    replayProducer.node = null;
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            ReplayBuffer<T> replayBuffer = this.buffer;
            replayBuffer.error(th);
            ArrayList arrayList = null;
            for (ReplayProducer<T> replayProducer : getAndSet(TERMINATED)) {
                try {
                    if (replayProducer.caughtUp) {
                        replayProducer.actual.onError(th);
                    } else if (replayBuffer.drain(replayProducer)) {
                        replayProducer.caughtUp = true;
                        replayProducer.node = null;
                    }
                } catch (Throwable th2) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th2);
                }
            }
            Exceptions.throwIfAny(arrayList);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            ReplayBuffer<T> replayBuffer = this.buffer;
            replayBuffer.next(t);
            for (ReplayProducer<T> replayProducer : get()) {
                if (replayProducer.caughtUp) {
                    replayProducer.actual.onNext(t);
                } else if (replayBuffer.drain(replayProducer)) {
                    replayProducer.caughtUp = true;
                    replayProducer.node = null;
                }
            }
        }

        void remove(ReplayProducer<T> replayProducer) {
            ReplayProducer<T>[] replayProducerArr;
            ReplayProducer[] replayProducerArr2;
            do {
                replayProducerArr = get();
                if (replayProducerArr == TERMINATED || replayProducerArr == EMPTY) {
                    return;
                }
                int length = replayProducerArr.length;
                int i2 = -1;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    } else if (replayProducerArr[i3] == replayProducer) {
                        i2 = i3;
                        break;
                    } else {
                        i3++;
                    }
                }
                if (i2 < 0) {
                    return;
                }
                if (length == 1) {
                    replayProducerArr2 = EMPTY;
                } else {
                    ReplayProducer[] replayProducerArr3 = new ReplayProducer[length - 1];
                    System.arraycopy(replayProducerArr, 0, replayProducerArr3, 0, i2);
                    System.arraycopy(replayProducerArr, i2 + 1, replayProducerArr3, i2, (length - i2) - 1);
                    replayProducerArr2 = replayProducerArr3;
                }
            } while (!compareAndSet(replayProducerArr, replayProducerArr2));
        }

        public void call(Subscriber<? super T> subscriber) {
            ReplayProducer<T> replayProducer = new ReplayProducer<>(subscriber, this);
            subscriber.add(replayProducer);
            subscriber.setProducer(replayProducer);
            if (add(replayProducer) && replayProducer.isUnsubscribed()) {
                remove(replayProducer);
            } else {
                this.buffer.drain(replayProducer);
            }
        }
    }

    ReplaySubject(ReplayState<T> replayState) {
        super(replayState);
        this.state = replayState;
    }

    public static <T> ReplaySubject<T> create() {
        return create(16);
    }

    static <T> ReplaySubject<T> createUnbounded() {
        return new ReplaySubject<>(new ReplayState(new ReplaySizeBoundBuffer(Integer.MAX_VALUE)));
    }

    public static <T> ReplaySubject<T> createWithSize(int i2) {
        return new ReplaySubject<>(new ReplayState(new ReplaySizeBoundBuffer(i2)));
    }

    public static <T> ReplaySubject<T> createWithTime(long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return createWithTimeAndSize(j2, timeUnit, Integer.MAX_VALUE, scheduler);
    }

    public static <T> ReplaySubject<T> createWithTimeAndSize(long j2, TimeUnit timeUnit, int i2, Scheduler scheduler) {
        return new ReplaySubject<>(new ReplayState(new ReplaySizeAndTimeBoundBuffer(i2, timeUnit.toMillis(j2), scheduler)));
    }

    @Beta
    public Throwable getThrowable() {
        if (this.state.isTerminated()) {
            return this.state.buffer.error();
        }
        return null;
    }

    @Beta
    public T getValue() {
        return this.state.buffer.last();
    }

    @Beta
    public T[] getValues(T[] tArr) {
        return this.state.buffer.toArray(tArr);
    }

    @Beta
    public boolean hasAnyValue() {
        return !this.state.buffer.isEmpty();
    }

    @Beta
    public boolean hasCompleted() {
        return this.state.isTerminated() && this.state.buffer.error() == null;
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.get().length != 0;
    }

    @Beta
    public boolean hasThrowable() {
        return this.state.isTerminated() && this.state.buffer.error() != null;
    }

    @Beta
    public boolean hasValue() {
        return hasAnyValue();
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.state.onCompleted();
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.state.onError(th);
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.state.onNext(t);
    }

    @Beta
    public int size() {
        return this.state.buffer.size();
    }

    int subscriberCount() {
        return this.state.get().length;
    }

    /* loaded from: classes11.dex */
    static final class ReplaySizeBoundBuffer<T> implements ReplayBuffer<T> {
        volatile boolean done;
        Throwable error;
        volatile Node<T> head;
        final int limit;
        int size;
        Node<T> tail;

        /* loaded from: classes11.dex */
        static final class Node<T> extends AtomicReference<Node<T>> {
            private static final long serialVersionUID = 3713592843205853725L;
            final T value;

            public Node(T t) {
                this.value = t;
            }
        }

        public ReplaySizeBoundBuffer(int i2) {
            this.limit = i2;
            Node<T> node = new Node<>(null);
            this.tail = node;
            this.head = node;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void complete() {
            this.done = true;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean drain(ReplayProducer<T> replayProducer) {
            long j2;
            if (replayProducer.getAndIncrement() != 0) {
                return false;
            }
            Subscriber<? super T> subscriber = replayProducer.actual;
            int i2 = 1;
            do {
                j2 = replayProducer.requested.get();
                Node<T> node = (Node) replayProducer.node;
                if (node == null) {
                    node = this.head;
                }
                long j3 = 0;
                while (j3 != j2) {
                    if (subscriber.isUnsubscribed()) {
                        replayProducer.node = null;
                        return false;
                    }
                    boolean z = this.done;
                    Node<T> node2 = node.get();
                    boolean z2 = node2 == null;
                    if (z && z2) {
                        replayProducer.node = null;
                        Throwable th = this.error;
                        if (th != null) {
                            subscriber.onError(th);
                        } else {
                            subscriber.onCompleted();
                        }
                        return false;
                    } else if (z2) {
                        break;
                    } else {
                        subscriber.onNext((T) node2.value);
                        j3++;
                        node = node2;
                    }
                }
                if (j3 == j2) {
                    if (subscriber.isUnsubscribed()) {
                        replayProducer.node = null;
                        return false;
                    }
                    boolean z3 = this.done;
                    boolean z4 = node.get() == null;
                    if (z3 && z4) {
                        replayProducer.node = null;
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            subscriber.onError(th2);
                        } else {
                            subscriber.onCompleted();
                        }
                        return false;
                    }
                }
                if (j3 != 0 && j2 != Long.MAX_VALUE) {
                    BackpressureUtils.produced(replayProducer.requested, j3);
                }
                replayProducer.node = node;
                i2 = replayProducer.addAndGet(-i2);
            } while (i2 != 0);
            return j2 == Long.MAX_VALUE;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void error(Throwable th) {
            this.error = th;
            this.done = true;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isComplete() {
            return this.done;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isEmpty() {
            return this.head.get() == null;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T last() {
            Node<T> node = this.head;
            while (true) {
                Node<T> node2 = node.get();
                if (node2 == null) {
                    return node.value;
                }
                node = node2;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void next(T t) {
            Node<T> node = new Node<>(t);
            this.tail.set(node);
            this.tail = node;
            int i2 = this.size;
            if (i2 == this.limit) {
                this.head = this.head.get();
            } else {
                this.size = i2 + 1;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public int size() {
            Node<T> node = this.head.get();
            int i2 = 0;
            while (node != null && i2 != Integer.MAX_VALUE) {
                node = node.get();
                i2++;
            }
            return i2;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList();
            for (Node<T> node = this.head.get(); node != null; node = node.get()) {
                arrayList.add(node.value);
            }
            return (T[]) arrayList.toArray(tArr);
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public Throwable error() {
            return this.error;
        }
    }

    public static <T> ReplaySubject<T> create(int i2) {
        if (i2 > 0) {
            return new ReplaySubject<>(new ReplayState(new ReplayUnboundedBuffer(i2)));
        }
        throw new IllegalArgumentException("capacity > 0 required but it was " + i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Beta
    public Object[] getValues() {
        Object[] objArr = EMPTY_ARRAY;
        Object[] values = getValues(objArr);
        return values == objArr ? new Object[0] : values;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ReplaySizeAndTimeBoundBuffer<T> implements ReplayBuffer<T> {
        volatile boolean done;
        Throwable error;
        volatile TimedNode<T> head;
        final int limit;
        final long maxAgeMillis;
        final Scheduler scheduler;
        int size;
        TimedNode<T> tail;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes11.dex */
        public static final class TimedNode<T> extends AtomicReference<TimedNode<T>> {
            private static final long serialVersionUID = 3713592843205853725L;
            final long timestamp;
            final T value;

            public TimedNode(T t, long j2) {
                this.value = t;
                this.timestamp = j2;
            }
        }

        public ReplaySizeAndTimeBoundBuffer(int i2, long j2, Scheduler scheduler) {
            this.limit = i2;
            TimedNode<T> timedNode = new TimedNode<>(null, 0L);
            this.tail = timedNode;
            this.head = timedNode;
            this.maxAgeMillis = j2;
            this.scheduler = scheduler;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void complete() {
            evictFinal();
            this.done = true;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean drain(ReplayProducer<T> replayProducer) {
            long j2;
            if (replayProducer.getAndIncrement() != 0) {
                return false;
            }
            Subscriber<? super T> subscriber = replayProducer.actual;
            int i2 = 1;
            do {
                j2 = replayProducer.requested.get();
                TimedNode<T> timedNode = (TimedNode) replayProducer.node;
                if (timedNode == null) {
                    timedNode = latestHead();
                }
                long j3 = 0;
                while (j3 != j2) {
                    if (subscriber.isUnsubscribed()) {
                        replayProducer.node = null;
                        return false;
                    }
                    boolean z = this.done;
                    TimedNode<T> timedNode2 = timedNode.get();
                    boolean z2 = timedNode2 == null;
                    if (z && z2) {
                        replayProducer.node = null;
                        Throwable th = this.error;
                        if (th != null) {
                            subscriber.onError(th);
                        } else {
                            subscriber.onCompleted();
                        }
                        return false;
                    } else if (z2) {
                        break;
                    } else {
                        subscriber.onNext((T) timedNode2.value);
                        j3++;
                        timedNode = timedNode2;
                    }
                }
                if (j3 == j2) {
                    if (subscriber.isUnsubscribed()) {
                        replayProducer.node = null;
                        return false;
                    }
                    boolean z3 = this.done;
                    boolean z4 = timedNode.get() == null;
                    if (z3 && z4) {
                        replayProducer.node = null;
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            subscriber.onError(th2);
                        } else {
                            subscriber.onCompleted();
                        }
                        return false;
                    }
                }
                if (j3 != 0 && j2 != Long.MAX_VALUE) {
                    BackpressureUtils.produced(replayProducer.requested, j3);
                }
                replayProducer.node = timedNode;
                i2 = replayProducer.addAndGet(-i2);
            } while (i2 != 0);
            return j2 == Long.MAX_VALUE;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void error(Throwable th) {
            evictFinal();
            this.error = th;
            this.done = true;
        }

        void evictFinal() {
            long now = this.scheduler.now() - this.maxAgeMillis;
            TimedNode<T> timedNode = this.head;
            TimedNode<T> timedNode2 = timedNode;
            while (true) {
                TimedNode<T> timedNode3 = timedNode2.get();
                if (timedNode3 == null || timedNode3.timestamp > now) {
                    break;
                }
                timedNode2 = timedNode3;
            }
            if (timedNode != timedNode2) {
                this.head = timedNode2;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isComplete() {
            return this.done;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isEmpty() {
            return latestHead().get() == null;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T last() {
            TimedNode<T> latestHead = latestHead();
            while (true) {
                TimedNode<T> timedNode = latestHead.get();
                if (timedNode == null) {
                    return latestHead.value;
                }
                latestHead = timedNode;
            }
        }

        TimedNode<T> latestHead() {
            long now = this.scheduler.now() - this.maxAgeMillis;
            TimedNode<T> timedNode = this.head;
            while (true) {
                TimedNode<T> timedNode2 = timedNode.get();
                if (timedNode2 == null || timedNode2.timestamp > now) {
                    break;
                }
                timedNode = timedNode2;
            }
            return timedNode;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void next(T t) {
            TimedNode<T> timedNode;
            long now = this.scheduler.now();
            TimedNode<T> timedNode2 = new TimedNode<>(t, now);
            this.tail.set(timedNode2);
            this.tail = timedNode2;
            long j2 = now - this.maxAgeMillis;
            int i2 = this.size;
            TimedNode<T> timedNode3 = this.head;
            if (i2 == this.limit) {
                timedNode = timedNode3.get();
            } else {
                i2++;
                timedNode = timedNode3;
            }
            while (true) {
                TimedNode<T> timedNode4 = timedNode.get();
                if (timedNode4 == null || timedNode4.timestamp > j2) {
                    break;
                }
                i2--;
                timedNode = timedNode4;
            }
            this.size = i2;
            if (timedNode != timedNode3) {
                this.head = timedNode;
            }
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public int size() {
            TimedNode<T> timedNode = latestHead().get();
            int i2 = 0;
            while (timedNode != null && i2 != Integer.MAX_VALUE) {
                timedNode = timedNode.get();
                i2++;
            }
            return i2;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList();
            for (TimedNode<T> timedNode = latestHead().get(); timedNode != null; timedNode = timedNode.get()) {
                arrayList.add(timedNode.value);
            }
            return (T[]) arrayList.toArray(tArr);
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public Throwable error() {
            return this.error;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ReplayUnboundedBuffer<T> implements ReplayBuffer<T> {
        final int capacity;
        volatile boolean done;
        Throwable error;
        final Object[] head;
        volatile int size;
        Object[] tail;
        int tailIndex;

        public ReplayUnboundedBuffer(int i2) {
            this.capacity = i2;
            Object[] objArr = new Object[i2 + 1];
            this.head = objArr;
            this.tail = objArr;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void complete() {
            this.done = true;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean drain(ReplayProducer<T> replayProducer) {
            long j2;
            if (replayProducer.getAndIncrement() != 0) {
                return false;
            }
            Subscriber<? super T> subscriber = replayProducer.actual;
            int i2 = this.capacity;
            int i3 = 1;
            do {
                j2 = replayProducer.requested.get();
                Object[] objArr = (Object[]) replayProducer.node;
                if (objArr == null) {
                    objArr = this.head;
                }
                int i4 = replayProducer.tailIndex;
                int i5 = replayProducer.index;
                long j3 = 0;
                while (j3 != j2) {
                    if (subscriber.isUnsubscribed()) {
                        replayProducer.node = null;
                        return false;
                    }
                    boolean z = this.done;
                    boolean z2 = i5 == this.size;
                    if (z && z2) {
                        replayProducer.node = null;
                        Throwable th = this.error;
                        if (th != null) {
                            subscriber.onError(th);
                        } else {
                            subscriber.onCompleted();
                        }
                        return false;
                    } else if (z2) {
                        break;
                    } else {
                        if (i4 == i2) {
                            objArr = (Object[]) objArr[i4];
                            i4 = 0;
                        }
                        subscriber.onNext(objArr[i4]);
                        j3++;
                        i4++;
                        i5++;
                    }
                }
                if (j3 == j2) {
                    if (subscriber.isUnsubscribed()) {
                        replayProducer.node = null;
                        return false;
                    }
                    boolean z3 = this.done;
                    boolean z4 = i5 == this.size;
                    if (z3 && z4) {
                        replayProducer.node = null;
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            subscriber.onError(th2);
                        } else {
                            subscriber.onCompleted();
                        }
                        return false;
                    }
                }
                if (j3 != 0 && j2 != Long.MAX_VALUE) {
                    BackpressureUtils.produced(replayProducer.requested, j3);
                }
                replayProducer.index = i5;
                replayProducer.tailIndex = i4;
                replayProducer.node = objArr;
                i3 = replayProducer.addAndGet(-i3);
            } while (i3 != 0);
            return j2 == Long.MAX_VALUE;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void error(Throwable th) {
            if (this.done) {
                RxJavaPluginUtils.handleException(th);
                return;
            }
            this.error = th;
            this.done = true;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isComplete() {
            return this.done;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isEmpty() {
            return this.size == 0;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T last() {
            int i2 = this.size;
            if (i2 == 0) {
                return null;
            }
            Object[] objArr = this.head;
            int i3 = this.capacity;
            while (i2 >= i3) {
                objArr = objArr[i3];
                i2 -= i3;
            }
            return (T) objArr[i2 - 1];
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public void next(T t) {
            if (this.done) {
                return;
            }
            int i2 = this.tailIndex;
            Object[] objArr = this.tail;
            if (i2 == objArr.length - 1) {
                Object[] objArr2 = new Object[objArr.length];
                objArr2[0] = t;
                this.tailIndex = 1;
                objArr[i2] = objArr2;
                this.tail = objArr2;
            } else {
                objArr[i2] = t;
                this.tailIndex = i2 + 1;
            }
            this.size++;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public int size() {
            return this.size;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public T[] toArray(T[] tArr) {
            int i2 = this.size;
            if (tArr.length < i2) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i2));
            }
            Object[] objArr = this.head;
            int i3 = this.capacity;
            int i4 = 0;
            while (true) {
                int i5 = i4 + i3;
                if (i5 >= i2) {
                    break;
                }
                System.arraycopy(objArr, 0, tArr, i4, i3);
                objArr = objArr[i3];
                i4 = i5;
            }
            System.arraycopy(objArr, 0, tArr, i4, i2 - i4);
            if (tArr.length > i2) {
                tArr[i2] = null;
            }
            return tArr;
        }

        @Override // rx.subjects.ReplaySubject.ReplayBuffer
        public Throwable error() {
            return this.error;
        }
    }
}
