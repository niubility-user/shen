package rx.internal.operators;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscExactAtomicArrayQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.Pow2;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.CompositeSubscription;

/* loaded from: classes11.dex */
public final class OperatorMerge<T> implements Observable.Operator<T, Observable<? extends T>> {
    final boolean delayErrors;
    final int maxConcurrent;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class HolderDelayErrors {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge<>(true, Integer.MAX_VALUE);

        private HolderDelayErrors() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class HolderNoDelay {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge<>(false, Integer.MAX_VALUE);

        private HolderNoDelay() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class InnerSubscriber<T> extends Subscriber<T> {
        static final int limit = RxRingBuffer.SIZE / 4;
        volatile boolean done;
        final long id;
        int outstanding;
        final MergeSubscriber<T> parent;
        volatile RxRingBuffer queue;

        public InnerSubscriber(MergeSubscriber<T> mergeSubscriber, long j2) {
            this.parent = mergeSubscriber;
            this.id = j2;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.done = true;
            this.parent.emit();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.done = true;
            this.parent.getOrCreateErrorQueue().offer(th);
            this.parent.emit();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.parent.tryEmit(this, t);
        }

        @Override // rx.Subscriber
        public void onStart() {
            int i2 = RxRingBuffer.SIZE;
            this.outstanding = i2;
            request(i2);
        }

        public void requestMore(long j2) {
            int i2 = this.outstanding - ((int) j2);
            if (i2 > limit) {
                this.outstanding = i2;
                return;
            }
            int i3 = RxRingBuffer.SIZE;
            this.outstanding = i3;
            int i4 = i3 - i2;
            if (i4 > 0) {
                request(i4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class MergeProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -1214379189873595503L;
        final MergeSubscriber<T> subscriber;

        public MergeProducer(MergeSubscriber<T> mergeSubscriber) {
            this.subscriber = mergeSubscriber;
        }

        public long produced(int i2) {
            return addAndGet(-i2);
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (j2 <= 0) {
                if (j2 < 0) {
                    throw new IllegalArgumentException("n >= 0 required");
                }
            } else if (get() == Long.MAX_VALUE) {
            } else {
                BackpressureUtils.getAndAddRequest(this, j2);
                this.subscriber.emit();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class MergeSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final InnerSubscriber<?>[] EMPTY = new InnerSubscriber[0];
        final Subscriber<? super T> child;
        final boolean delayErrors;
        volatile boolean done;
        boolean emitting;
        volatile ConcurrentLinkedQueue<Throwable> errors;
        long lastId;
        int lastIndex;
        final int maxConcurrent;
        boolean missed;
        MergeProducer<T> producer;
        volatile Queue<Object> queue;
        int scalarEmissionCount;
        final int scalarEmissionLimit;
        volatile CompositeSubscription subscriptions;
        long uniqueId;
        final NotificationLite<T> nl = NotificationLite.instance();
        final Object innerGuard = new Object();
        volatile InnerSubscriber<?>[] innerSubscribers = EMPTY;

        public MergeSubscriber(Subscriber<? super T> subscriber, boolean z, int i2) {
            this.child = subscriber;
            this.delayErrors = z;
            this.maxConcurrent = i2;
            if (i2 == Integer.MAX_VALUE) {
                this.scalarEmissionLimit = Integer.MAX_VALUE;
                request(Long.MAX_VALUE);
                return;
            }
            this.scalarEmissionLimit = Math.max(1, i2 >> 1);
            request(i2);
        }

        private void reportError() {
            ArrayList arrayList = new ArrayList(this.errors);
            if (arrayList.size() == 1) {
                this.child.onError((Throwable) arrayList.get(0));
            } else {
                this.child.onError(new CompositeException(arrayList));
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void addInner(InnerSubscriber<T> innerSubscriber) {
            getOrCreateComposite().add(innerSubscriber);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] innerSubscriberArr = this.innerSubscribers;
                int length = innerSubscriberArr.length;
                InnerSubscriber<?>[] innerSubscriberArr2 = new InnerSubscriber[length + 1];
                System.arraycopy(innerSubscriberArr, 0, innerSubscriberArr2, 0, length);
                innerSubscriberArr2[length] = innerSubscriber;
                this.innerSubscribers = innerSubscriberArr2;
            }
        }

        boolean checkTerminate() {
            if (this.child.isUnsubscribed()) {
                return true;
            }
            ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.errors;
            if (this.delayErrors || concurrentLinkedQueue == null || concurrentLinkedQueue.isEmpty()) {
                return false;
            }
            try {
                reportError();
                return true;
            } finally {
                unsubscribe();
            }
        }

        void emit() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                emitLoop();
            }
        }

        void emitEmpty() {
            int i2 = this.scalarEmissionCount + 1;
            if (i2 == this.scalarEmissionLimit) {
                this.scalarEmissionCount = 0;
                requestMore(i2);
                return;
            }
            this.scalarEmissionCount = i2;
        }

        void emitLoop() {
            boolean z;
            long j2;
            int i2;
            boolean z2;
            try {
                Subscriber<? super T> subscriber = this.child;
                while (!checkTerminate()) {
                    Queue<Object> queue = this.queue;
                    long j3 = this.producer.get();
                    boolean z3 = j3 == Long.MAX_VALUE;
                    if (queue != null) {
                        int i3 = 0;
                        while (true) {
                            j2 = j3;
                            int i4 = 0;
                            i2 = i3;
                            Object obj = null;
                            while (true) {
                                if (j2 <= 0) {
                                    break;
                                }
                                Object poll = queue.poll();
                                if (checkTerminate()) {
                                    return;
                                }
                                if (poll == null) {
                                    obj = poll;
                                    break;
                                }
                                subscriber.onNext((T) this.nl.getValue(poll));
                                i2++;
                                i4++;
                                j2--;
                                obj = poll;
                            }
                            if (i4 > 0) {
                                j2 = z3 ? Long.MAX_VALUE : this.producer.produced(i4);
                            }
                            if (j2 == 0 || obj == null) {
                                break;
                            }
                            i3 = i2;
                            j3 = j2;
                        }
                    } else {
                        j2 = j3;
                        i2 = 0;
                    }
                    boolean z4 = this.done;
                    Queue<Object> queue2 = this.queue;
                    InnerSubscriber<?>[] innerSubscriberArr = this.innerSubscribers;
                    int length = innerSubscriberArr.length;
                    if (z4 && ((queue2 == null || queue2.isEmpty()) && length == 0)) {
                        ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.errors;
                        if (concurrentLinkedQueue != null && !concurrentLinkedQueue.isEmpty()) {
                            reportError();
                            return;
                        }
                        subscriber.onCompleted();
                        return;
                    }
                    if (length > 0) {
                        long j4 = this.lastId;
                        int i5 = this.lastIndex;
                        if (length <= i5 || innerSubscriberArr[i5].id != j4) {
                            if (length <= i5) {
                                i5 = 0;
                            }
                            for (int i6 = 0; i6 < length && innerSubscriberArr[i5].id != j4; i6++) {
                                i5++;
                                if (i5 == length) {
                                    i5 = 0;
                                }
                            }
                            this.lastIndex = i5;
                            this.lastId = innerSubscriberArr[i5].id;
                        }
                        z2 = false;
                        for (int i7 = 0; i7 < length; i7++) {
                            if (checkTerminate()) {
                                return;
                            }
                            InnerSubscriber<?> innerSubscriber = innerSubscriberArr[i5];
                            Object obj2 = null;
                            do {
                                int i8 = 0;
                                while (j2 > 0) {
                                    if (checkTerminate()) {
                                        return;
                                    }
                                    RxRingBuffer rxRingBuffer = innerSubscriber.queue;
                                    if (rxRingBuffer == null || (obj2 = rxRingBuffer.poll()) == null) {
                                        break;
                                    }
                                    try {
                                        subscriber.onNext((T) this.nl.getValue(obj2));
                                        j2--;
                                        i8++;
                                    }
                                }
                                if (i8 > 0) {
                                    j2 = !z3 ? this.producer.produced(i8) : Long.MAX_VALUE;
                                    innerSubscriber.requestMore(i8);
                                }
                                if (j2 == 0) {
                                    break;
                                }
                            } while (obj2 != null);
                            boolean z5 = innerSubscriber.done;
                            RxRingBuffer rxRingBuffer2 = innerSubscriber.queue;
                            if (z5 && (rxRingBuffer2 == null || rxRingBuffer2.isEmpty())) {
                                removeInner(innerSubscriber);
                                if (checkTerminate()) {
                                    return;
                                }
                                i2++;
                                z2 = true;
                            }
                            if (j2 == 0) {
                                break;
                            }
                            i5++;
                            if (i5 == length) {
                                i5 = 0;
                            }
                        }
                        this.lastIndex = i5;
                        this.lastId = innerSubscriberArr[i5].id;
                    } else {
                        z2 = false;
                    }
                    if (i2 > 0) {
                        request(i2);
                    }
                    if (!z2) {
                        synchronized (this) {
                            try {
                                if (!this.missed) {
                                    try {
                                        this.emitting = false;
                                        return;
                                    } catch (Throwable th) {
                                        th = th;
                                        z = true;
                                        while (true) {
                                            try {
                                                break;
                                            } catch (Throwable th2) {
                                                th = th2;
                                            }
                                        }
                                        throw th;
                                    }
                                }
                                this.missed = false;
                            } catch (Throwable th3) {
                                th = th3;
                                z = false;
                            }
                        }
                        try {
                            break;
                            throw th;
                        } catch (Throwable th4) {
                            th = th4;
                            if (!z) {
                                synchronized (this) {
                                    this.emitting = false;
                                }
                            }
                            throw th;
                        }
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                z = false;
            }
        }

        protected void emitScalar(InnerSubscriber<T> innerSubscriber, T t, long j2) {
            try {
                this.child.onNext(t);
                if (j2 != Long.MAX_VALUE) {
                    this.producer.produced(1);
                }
                innerSubscriber.requestMore(1L);
                synchronized (this) {
                    try {
                        if (!this.missed) {
                            this.emitting = false;
                            return;
                        }
                        this.missed = false;
                        emitLoop();
                    }
                }
            }
        }

        CompositeSubscription getOrCreateComposite() {
            CompositeSubscription compositeSubscription;
            CompositeSubscription compositeSubscription2 = this.subscriptions;
            if (compositeSubscription2 == null) {
                boolean z = false;
                synchronized (this) {
                    compositeSubscription = this.subscriptions;
                    if (compositeSubscription == null) {
                        CompositeSubscription compositeSubscription3 = new CompositeSubscription();
                        this.subscriptions = compositeSubscription3;
                        compositeSubscription = compositeSubscription3;
                        z = true;
                    }
                }
                if (z) {
                    add(compositeSubscription);
                }
                return compositeSubscription;
            }
            return compositeSubscription2;
        }

        Queue<Throwable> getOrCreateErrorQueue() {
            ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.errors;
            if (concurrentLinkedQueue == null) {
                synchronized (this) {
                    concurrentLinkedQueue = this.errors;
                    if (concurrentLinkedQueue == null) {
                        concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
                        this.errors = concurrentLinkedQueue;
                    }
                }
            }
            return concurrentLinkedQueue;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.done = true;
            emit();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            getOrCreateErrorQueue().offer(th);
            this.done = true;
            emit();
        }

        @Override // rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Observable) ((Observable) obj));
        }

        protected void queueScalar(InnerSubscriber<T> innerSubscriber, T t) {
            RxRingBuffer rxRingBuffer = innerSubscriber.queue;
            if (rxRingBuffer == null) {
                rxRingBuffer = RxRingBuffer.getSpscInstance();
                innerSubscriber.add(rxRingBuffer);
                innerSubscriber.queue = rxRingBuffer;
            }
            try {
                rxRingBuffer.onNext(this.nl.next(t));
                emit();
            } catch (IllegalStateException e2) {
                if (innerSubscriber.isUnsubscribed()) {
                    return;
                }
                innerSubscriber.unsubscribe();
                innerSubscriber.onError(e2);
            } catch (MissingBackpressureException e3) {
                innerSubscriber.unsubscribe();
                innerSubscriber.onError(e3);
            }
        }

        void removeInner(InnerSubscriber<T> innerSubscriber) {
            RxRingBuffer rxRingBuffer = innerSubscriber.queue;
            if (rxRingBuffer != null) {
                rxRingBuffer.release();
            }
            this.subscriptions.remove(innerSubscriber);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] innerSubscriberArr = this.innerSubscribers;
                int length = innerSubscriberArr.length;
                int i2 = -1;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    } else if (innerSubscriber.equals(innerSubscriberArr[i3])) {
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
                    this.innerSubscribers = EMPTY;
                    return;
                }
                InnerSubscriber<?>[] innerSubscriberArr2 = new InnerSubscriber[length - 1];
                System.arraycopy(innerSubscriberArr, 0, innerSubscriberArr2, 0, i2);
                System.arraycopy(innerSubscriberArr, i2 + 1, innerSubscriberArr2, i2, (length - i2) - 1);
                this.innerSubscribers = innerSubscriberArr2;
            }
        }

        public void requestMore(long j2) {
            request(j2);
        }

        void tryEmit(InnerSubscriber<T> innerSubscriber, T t) {
            long j2 = this.producer.get();
            boolean z = false;
            if (j2 != 0) {
                synchronized (this) {
                    j2 = this.producer.get();
                    if (!this.emitting && j2 != 0) {
                        this.emitting = true;
                        z = true;
                    }
                }
            }
            if (z) {
                emitScalar(innerSubscriber, t, j2);
            } else {
                queueScalar(innerSubscriber, t);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void onNext(Observable<? extends T> observable) {
            if (observable == null) {
                return;
            }
            if (observable == Observable.empty()) {
                emitEmpty();
            } else if (observable instanceof ScalarSynchronousObservable) {
                tryEmit(((ScalarSynchronousObservable) observable).get());
            } else {
                long j2 = this.uniqueId;
                this.uniqueId = 1 + j2;
                InnerSubscriber innerSubscriber = new InnerSubscriber(this, j2);
                addInner(innerSubscriber);
                observable.unsafeSubscribe(innerSubscriber);
                emit();
            }
        }

        void tryEmit(T t) {
            long j2 = this.producer.get();
            boolean z = false;
            if (j2 != 0) {
                synchronized (this) {
                    j2 = this.producer.get();
                    if (!this.emitting && j2 != 0) {
                        this.emitting = true;
                        z = true;
                    }
                }
            }
            if (z) {
                emitScalar(t, j2);
            } else {
                queueScalar(t);
            }
        }

        protected void queueScalar(T t) {
            Queue<Object> spscExactAtomicArrayQueue;
            Queue<Object> queue = this.queue;
            if (queue == null) {
                int i2 = this.maxConcurrent;
                if (i2 == Integer.MAX_VALUE) {
                    queue = new SpscUnboundedAtomicArrayQueue<>(RxRingBuffer.SIZE);
                } else {
                    if (Pow2.isPowerOfTwo(i2)) {
                        if (UnsafeAccess.isUnsafeAvailable()) {
                            spscExactAtomicArrayQueue = new SpscArrayQueue<>(i2);
                        } else {
                            spscExactAtomicArrayQueue = new SpscAtomicArrayQueue<>(i2);
                        }
                    } else {
                        spscExactAtomicArrayQueue = new SpscExactAtomicArrayQueue<>(i2);
                    }
                    queue = spscExactAtomicArrayQueue;
                }
                this.queue = queue;
            }
            if (!queue.offer(this.nl.next(t))) {
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(new MissingBackpressureException(), t));
                return;
            }
            emit();
        }

        protected void emitScalar(T t, long j2) {
            try {
                this.child.onNext(t);
                if (j2 != Long.MAX_VALUE) {
                    this.producer.produced(1);
                }
                int i2 = this.scalarEmissionCount + 1;
                if (i2 == this.scalarEmissionLimit) {
                    this.scalarEmissionCount = 0;
                    requestMore(i2);
                } else {
                    this.scalarEmissionCount = i2;
                }
                synchronized (this) {
                    try {
                        if (!this.missed) {
                            this.emitting = false;
                            return;
                        }
                        this.missed = false;
                        emitLoop();
                    }
                }
            }
        }
    }

    OperatorMerge(boolean z, int i2) {
        this.delayErrors = z;
        this.maxConcurrent = i2;
    }

    public static <T> OperatorMerge<T> instance(boolean z) {
        if (z) {
            return (OperatorMerge<T>) HolderDelayErrors.INSTANCE;
        }
        return (OperatorMerge<T>) HolderNoDelay.INSTANCE;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<Observable<? extends T>> call(Subscriber<? super T> subscriber) {
        MergeSubscriber mergeSubscriber = new MergeSubscriber(subscriber, this.delayErrors, this.maxConcurrent);
        MergeProducer<T> mergeProducer = new MergeProducer<>(mergeSubscriber);
        mergeSubscriber.producer = mergeProducer;
        subscriber.add(mergeSubscriber);
        subscriber.setProducer(mergeProducer);
        return mergeSubscriber;
    }

    public static <T> OperatorMerge<T> instance(boolean z, int i2) {
        if (i2 > 0) {
            if (i2 == Integer.MAX_VALUE) {
                return instance(z);
            }
            return new OperatorMerge<>(z, i2);
        }
        throw new IllegalArgumentException("maxConcurrent > 0 required but it was " + i2);
    }
}
