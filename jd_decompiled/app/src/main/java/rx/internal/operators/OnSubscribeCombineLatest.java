package rx.internal.operators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.functions.FuncN;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.plugins.RxJavaPlugins;

/* loaded from: classes11.dex */
public final class OnSubscribeCombineLatest<T, R> implements Observable.OnSubscribe<R> {
    final int bufferSize;
    final FuncN<? extends R> combiner;
    final boolean delayError;
    final Observable<? extends T>[] sources;
    final Iterable<? extends Observable<? extends T>> sourcesIterable;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class CombinerSubscriber<T, R> extends Subscriber<T> {
        boolean done;
        final int index;
        final NotificationLite<T> nl = NotificationLite.instance();
        final LatestCoordinator<T, R> parent;

        public CombinerSubscriber(LatestCoordinator<T, R> latestCoordinator, int i2) {
            this.parent = latestCoordinator;
            this.index = i2;
            request(latestCoordinator.bufferSize);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.parent.combine(null, this.index);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
                return;
            }
            this.parent.onError(th);
            this.done = true;
            this.parent.combine(null, this.index);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            this.parent.combine(this.nl.next(t), this.index);
        }

        public void requestMore(long j2) {
            request(j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class LatestCoordinator<T, R> extends AtomicInteger implements Producer, Subscription {
        static final Object MISSING = new Object();
        private static final long serialVersionUID = 8567835998786448817L;
        int active;
        final Subscriber<? super R> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final FuncN<? extends R> combiner;
        int complete;
        final int count;
        final boolean delayError;
        volatile boolean done;
        final AtomicReference<Throwable> error;
        final Object[] latest;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested;
        final CombinerSubscriber<T, R>[] subscribers;

        public LatestCoordinator(Subscriber<? super R> subscriber, FuncN<? extends R> funcN, int i2, int i3, boolean z) {
            this.actual = subscriber;
            this.combiner = funcN;
            this.count = i2;
            this.bufferSize = i3;
            this.delayError = z;
            Object[] objArr = new Object[i2];
            this.latest = objArr;
            Arrays.fill(objArr, MISSING);
            this.subscribers = new CombinerSubscriber[i2];
            this.queue = new SpscLinkedArrayQueue<>(i3);
            this.requested = new AtomicLong();
            this.error = new AtomicReference<>();
        }

        void cancel(Queue<?> queue) {
            queue.clear();
            for (CombinerSubscriber<T, R> combinerSubscriber : this.subscribers) {
                combinerSubscriber.unsubscribe();
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<?> subscriber, Queue<?> queue, boolean z3) {
            if (this.cancelled) {
                cancel(queue);
                return true;
            } else if (z) {
                if (z3) {
                    if (z2) {
                        Throwable th = this.error.get();
                        if (th != null) {
                            subscriber.onError(th);
                        } else {
                            subscriber.onCompleted();
                        }
                        return true;
                    }
                    return false;
                }
                Throwable th2 = this.error.get();
                if (th2 != null) {
                    cancel(queue);
                    subscriber.onError(th2);
                    return true;
                } else if (z2) {
                    subscriber.onCompleted();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        void combine(Object obj, int i2) {
            boolean z;
            CombinerSubscriber<T, R> combinerSubscriber = this.subscribers[i2];
            synchronized (this) {
                Object[] objArr = this.latest;
                int length = objArr.length;
                Object obj2 = objArr[i2];
                int i3 = this.active;
                Object obj3 = MISSING;
                if (obj2 == obj3) {
                    i3++;
                    this.active = i3;
                }
                int i4 = this.complete;
                if (obj == null) {
                    i4++;
                    this.complete = i4;
                } else {
                    objArr[i2] = combinerSubscriber.nl.getValue(obj);
                }
                boolean z2 = false;
                z = i3 == length;
                if (i4 == length || (obj == null && obj2 == obj3)) {
                    z2 = true;
                }
                if (z2) {
                    this.done = true;
                } else if (obj != null && z) {
                    this.queue.offer(combinerSubscriber, this.latest.clone());
                } else if (obj == null && this.error.get() != null && (obj2 == obj3 || !this.delayError)) {
                    this.done = true;
                }
            }
            if (!z && obj != null) {
                combinerSubscriber.requestMore(1L);
            } else {
                drain();
            }
        }

        void drain() {
            long j2;
            if (getAndIncrement() != 0) {
                return;
            }
            SpscLinkedArrayQueue<Object> spscLinkedArrayQueue = this.queue;
            Subscriber<? super R> subscriber = this.actual;
            boolean z = this.delayError;
            AtomicLong atomicLong = this.requested;
            int i2 = 1;
            while (!checkTerminated(this.done, spscLinkedArrayQueue.isEmpty(), subscriber, spscLinkedArrayQueue, z)) {
                long j3 = atomicLong.get();
                boolean z2 = j3 == Long.MAX_VALUE;
                long j4 = j3;
                long j5 = 0;
                while (true) {
                    if (j4 == 0) {
                        j2 = j5;
                        break;
                    }
                    boolean z3 = this.done;
                    CombinerSubscriber combinerSubscriber = (CombinerSubscriber) spscLinkedArrayQueue.peek();
                    boolean z4 = combinerSubscriber == null;
                    long j6 = j5;
                    if (checkTerminated(z3, z4, subscriber, spscLinkedArrayQueue, z)) {
                        return;
                    }
                    if (z4) {
                        j2 = j6;
                        break;
                    }
                    spscLinkedArrayQueue.poll();
                    Object[] objArr = (Object[]) spscLinkedArrayQueue.poll();
                    if (objArr == null) {
                        this.cancelled = true;
                        cancel(spscLinkedArrayQueue);
                        subscriber.onError(new IllegalStateException("Broken queue?! Sender received but not the array."));
                        return;
                    }
                    try {
                        subscriber.onNext((R) this.combiner.call(objArr));
                        combinerSubscriber.requestMore(1L);
                        j4--;
                        j5 = j6 - 1;
                    } catch (Throwable th) {
                        this.cancelled = true;
                        cancel(spscLinkedArrayQueue);
                        subscriber.onError(th);
                        return;
                    }
                }
                if (j2 != 0 && !z2) {
                    atomicLong.addAndGet(j2);
                }
                i2 = addAndGet(-i2);
                if (i2 == 0) {
                    return;
                }
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.cancelled;
        }

        void onError(Throwable th) {
            Throwable th2;
            Throwable th3;
            AtomicReference<Throwable> atomicReference = this.error;
            do {
                th2 = atomicReference.get();
                if (th2 == null) {
                    th3 = th;
                } else if (th2 instanceof CompositeException) {
                    ArrayList arrayList = new ArrayList(((CompositeException) th2).getExceptions());
                    arrayList.add(th);
                    th3 = new CompositeException(arrayList);
                } else {
                    th3 = new CompositeException(Arrays.asList(th2, th));
                }
            } while (!atomicReference.compareAndSet(th2, th3));
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (j2 < 0) {
                throw new IllegalArgumentException("n >= required but it was " + j2);
            } else if (j2 != 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j2);
                drain();
            }
        }

        public void subscribe(Observable<? extends T>[] observableArr) {
            CombinerSubscriber<T, R>[] combinerSubscriberArr = this.subscribers;
            int length = combinerSubscriberArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                combinerSubscriberArr[i2] = new CombinerSubscriber<>(this, i2);
            }
            lazySet(0);
            this.actual.add(this);
            this.actual.setProducer(this);
            for (int i3 = 0; i3 < length && !this.cancelled; i3++) {
                observableArr[i3].subscribe((Subscriber<? super Object>) combinerSubscriberArr[i3]);
            }
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            if (getAndIncrement() == 0) {
                cancel(this.queue);
            }
        }
    }

    public OnSubscribeCombineLatest(Iterable<? extends Observable<? extends T>> iterable, FuncN<? extends R> funcN) {
        this(null, iterable, funcN, RxRingBuffer.SIZE, false);
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeCombineLatest(Observable<? extends T>[] observableArr, Iterable<? extends Observable<? extends T>> iterable, FuncN<? extends R> funcN, int i2, boolean z) {
        this.sources = observableArr;
        this.sourcesIterable = iterable;
        this.combiner = funcN;
        this.bufferSize = i2;
        this.delayError = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void call(rx.Subscriber<? super R> r9) {
        /*
            r8 = this;
            rx.Observable<? extends T>[] r0 = r8.sources
            if (r0 != 0) goto L45
            java.lang.Iterable<? extends rx.Observable<? extends T>> r0 = r8.sourcesIterable
            boolean r1 = r0 instanceof java.util.List
            if (r1 == 0) goto L1a
            java.util.List r0 = (java.util.List) r0
            int r1 = r0.size()
            rx.Observable[] r1 = new rx.Observable[r1]
            java.lang.Object[] r0 = r0.toArray(r1)
            rx.Observable[] r0 = (rx.Observable[]) r0
            int r1 = r0.length
            goto L46
        L1a:
            r1 = 8
            rx.Observable[] r1 = new rx.Observable[r1]
            java.util.Iterator r0 = r0.iterator()
            r2 = 0
            r3 = 0
        L24:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L42
            java.lang.Object r4 = r0.next()
            rx.Observable r4 = (rx.Observable) r4
            int r5 = r1.length
            if (r3 != r5) goto L3c
            int r5 = r3 >> 2
            int r5 = r5 + r3
            rx.Observable[] r5 = new rx.Observable[r5]
            java.lang.System.arraycopy(r1, r2, r5, r2, r3)
            r1 = r5
        L3c:
            int r5 = r3 + 1
            r1[r3] = r4
            r3 = r5
            goto L24
        L42:
            r0 = r1
            r4 = r3
            goto L47
        L45:
            int r1 = r0.length
        L46:
            r4 = r1
        L47:
            if (r4 != 0) goto L4d
            r9.onCompleted()
            return
        L4d:
            rx.internal.operators.OnSubscribeCombineLatest$LatestCoordinator r7 = new rx.internal.operators.OnSubscribeCombineLatest$LatestCoordinator
            rx.functions.FuncN<? extends R> r3 = r8.combiner
            int r5 = r8.bufferSize
            boolean r6 = r8.delayError
            r1 = r7
            r2 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            r7.subscribe(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeCombineLatest.call(rx.Subscriber):void");
    }
}
