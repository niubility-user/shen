package rx.internal.operators;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.functions.Action0;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;

/* loaded from: classes11.dex */
public final class OperatorSwitch<T> implements Observable.Operator<T, Observable<? extends T>> {
    final boolean delayError;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class Holder {
        static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch<>(false);

        private Holder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class HolderDelayError {
        static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch<>(true);

        private HolderDelayError() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class InnerSubscriber<T> extends Subscriber<T> {
        private final long id;
        private final SwitchSubscriber<T> parent;

        InnerSubscriber(long j2, SwitchSubscriber<T> switchSubscriber) {
            this.id = j2;
            this.parent = switchSubscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.parent.complete(this.id);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.parent.error(th, this.id);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.parent.emit(t, this);
        }

        @Override // rx.Subscriber
        public void setProducer(Producer producer) {
            this.parent.innerProducer(producer, this.id);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class SwitchSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final Throwable TERMINAL_ERROR = new Throwable("Terminal error");
        final Subscriber<? super T> child;
        final boolean delayError;
        boolean emitting;
        Throwable error;
        boolean innerActive;
        volatile boolean mainDone;
        boolean missed;
        Producer producer;
        long requested;
        final SerialSubscription ssub = new SerialSubscription();
        final AtomicLong index = new AtomicLong();
        final SpscLinkedArrayQueue<Object> queue = new SpscLinkedArrayQueue<>(RxRingBuffer.SIZE);
        final NotificationLite<T> nl = NotificationLite.instance();

        SwitchSubscriber(Subscriber<? super T> subscriber, boolean z) {
            this.child = subscriber;
            this.delayError = z;
        }

        protected boolean checkTerminated(boolean z, boolean z2, Throwable th, SpscLinkedArrayQueue<Object> spscLinkedArrayQueue, Subscriber<? super T> subscriber, boolean z3) {
            if (this.delayError) {
                if (z && !z2 && z3) {
                    if (th != null) {
                        subscriber.onError(th);
                    } else {
                        subscriber.onCompleted();
                    }
                    return true;
                }
                return false;
            } else if (th != null) {
                spscLinkedArrayQueue.clear();
                subscriber.onError(th);
                return true;
            } else if (z && !z2 && z3) {
                subscriber.onCompleted();
                return true;
            } else {
                return false;
            }
        }

        void childRequested(long j2) {
            Producer producer;
            synchronized (this) {
                producer = this.producer;
                this.requested = BackpressureUtils.addCap(this.requested, j2);
            }
            if (producer != null) {
                producer.request(j2);
            }
            drain();
        }

        void clearProducer() {
            synchronized (this) {
                this.producer = null;
            }
        }

        void complete(long j2) {
            synchronized (this) {
                if (this.index.get() != j2) {
                    return;
                }
                this.innerActive = false;
                this.producer = null;
                drain();
            }
        }

        void drain() {
            Throwable th;
            Throwable th2;
            boolean z = this.mainDone;
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                boolean z2 = this.innerActive;
                long j2 = this.requested;
                Throwable th3 = this.error;
                if (th3 != null && th3 != (th2 = TERMINAL_ERROR) && !this.delayError) {
                    this.error = th2;
                }
                SpscLinkedArrayQueue<Object> spscLinkedArrayQueue = this.queue;
                AtomicLong atomicLong = this.index;
                Subscriber<? super T> subscriber = this.child;
                boolean z3 = z2;
                long j3 = j2;
                Throwable th4 = th3;
                while (true) {
                    long j4 = 0;
                    while (j4 != j3) {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        boolean isEmpty = spscLinkedArrayQueue.isEmpty();
                        if (checkTerminated(z, z3, th4, spscLinkedArrayQueue, subscriber, isEmpty)) {
                            return;
                        }
                        if (isEmpty) {
                            break;
                        }
                        Object obj = (T) this.nl.getValue(spscLinkedArrayQueue.poll());
                        if (atomicLong.get() == ((InnerSubscriber) spscLinkedArrayQueue.poll()).id) {
                            subscriber.onNext(obj);
                            j4++;
                        }
                    }
                    if (j4 == j3) {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        if (checkTerminated(this.mainDone, z3, th4, spscLinkedArrayQueue, subscriber, spscLinkedArrayQueue.isEmpty())) {
                            return;
                        }
                    }
                    synchronized (this) {
                        long j5 = this.requested;
                        if (j5 != Long.MAX_VALUE) {
                            j5 -= j4;
                            this.requested = j5;
                        }
                        j3 = j5;
                        if (!this.missed) {
                            this.emitting = false;
                            return;
                        }
                        this.missed = false;
                        z = this.mainDone;
                        z3 = this.innerActive;
                        th4 = this.error;
                        if (th4 != null && th4 != (th = TERMINAL_ERROR) && !this.delayError) {
                            this.error = th;
                        }
                    }
                }
            }
        }

        void emit(T t, InnerSubscriber<T> innerSubscriber) {
            synchronized (this) {
                if (this.index.get() != ((InnerSubscriber) innerSubscriber).id) {
                    return;
                }
                this.queue.offer(innerSubscriber, this.nl.next(t));
                drain();
            }
        }

        void error(Throwable th, long j2) {
            boolean z;
            synchronized (this) {
                if (this.index.get() == j2) {
                    z = updateError(th);
                    this.innerActive = false;
                    this.producer = null;
                } else {
                    z = true;
                }
            }
            if (z) {
                drain();
            } else {
                pluginError(th);
            }
        }

        void init() {
            this.child.add(this.ssub);
            this.child.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorSwitch.SwitchSubscriber.1
                @Override // rx.functions.Action0
                public void call() {
                    SwitchSubscriber.this.clearProducer();
                }
            }));
            this.child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorSwitch.SwitchSubscriber.2
                @Override // rx.Producer
                public void request(long j2) {
                    if (j2 > 0) {
                        SwitchSubscriber.this.childRequested(j2);
                    } else if (j2 >= 0) {
                    } else {
                        throw new IllegalArgumentException("n >= 0 expected but it was " + j2);
                    }
                }
            });
        }

        void innerProducer(Producer producer, long j2) {
            synchronized (this) {
                if (this.index.get() != j2) {
                    return;
                }
                long j3 = this.requested;
                this.producer = producer;
                producer.request(j3);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.mainDone = true;
            drain();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            boolean updateError;
            synchronized (this) {
                updateError = updateError(th);
            }
            if (updateError) {
                this.mainDone = true;
                drain();
                return;
            }
            pluginError(th);
        }

        @Override // rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Observable) ((Observable) obj));
        }

        void pluginError(Throwable th) {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
        }

        boolean updateError(Throwable th) {
            Throwable th2 = this.error;
            if (th2 == TERMINAL_ERROR) {
                return false;
            }
            if (th2 == null) {
                this.error = th;
            } else if (th2 instanceof CompositeException) {
                ArrayList arrayList = new ArrayList(((CompositeException) th2).getExceptions());
                arrayList.add(th);
                this.error = new CompositeException(arrayList);
            } else {
                this.error = new CompositeException(th2, th);
            }
            return true;
        }

        public void onNext(Observable<? extends T> observable) {
            InnerSubscriber innerSubscriber;
            long incrementAndGet = this.index.incrementAndGet();
            Subscription subscription = this.ssub.get();
            if (subscription != null) {
                subscription.unsubscribe();
            }
            synchronized (this) {
                innerSubscriber = new InnerSubscriber(incrementAndGet, this);
                this.innerActive = true;
                this.producer = null;
            }
            this.ssub.set(innerSubscriber);
            observable.unsafeSubscribe(innerSubscriber);
        }
    }

    OperatorSwitch(boolean z) {
        this.delayError = z;
    }

    public static <T> OperatorSwitch<T> instance(boolean z) {
        if (z) {
            return (OperatorSwitch<T>) HolderDelayError.INSTANCE;
        }
        return (OperatorSwitch<T>) Holder.INSTANCE;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super Observable<? extends T>> call(Subscriber<? super T> subscriber) {
        SwitchSubscriber switchSubscriber = new SwitchSubscriber(subscriber, this.delayError);
        subscriber.add(switchSubscriber);
        switchSubscriber.init();
        return switchSubscriber;
    }
}
