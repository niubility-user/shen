package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.SerialSubscription;

/* loaded from: classes11.dex */
public final class CompletableOnSubscribeConcat implements Completable.CompletableOnSubscribe {
    final int prefetch;
    final Observable<Completable> sources;

    /* loaded from: classes11.dex */
    public static final class CompletableConcatSubscriber extends Subscriber<Completable> {
        final Completable.CompletableSubscriber actual;
        volatile boolean done;
        final ConcatInnerSubscriber inner;
        final AtomicBoolean once;
        final int prefetch;
        final SpscArrayQueue<Completable> queue;
        final SerialSubscription sr;
        final AtomicInteger wip;

        /* loaded from: classes11.dex */
        public final class ConcatInnerSubscriber implements Completable.CompletableSubscriber {
            ConcatInnerSubscriber() {
                CompletableConcatSubscriber.this = r1;
            }

            @Override // rx.Completable.CompletableSubscriber
            public void onCompleted() {
                CompletableConcatSubscriber.this.innerComplete();
            }

            @Override // rx.Completable.CompletableSubscriber
            public void onError(Throwable th) {
                CompletableConcatSubscriber.this.innerError(th);
            }

            @Override // rx.Completable.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                CompletableConcatSubscriber.this.sr.set(subscription);
            }
        }

        public CompletableConcatSubscriber(Completable.CompletableSubscriber completableSubscriber, int i2) {
            this.actual = completableSubscriber;
            this.prefetch = i2;
            this.queue = new SpscArrayQueue<>(i2);
            SerialSubscription serialSubscription = new SerialSubscription();
            this.sr = serialSubscription;
            this.inner = new ConcatInnerSubscriber();
            this.wip = new AtomicInteger();
            this.once = new AtomicBoolean();
            add(serialSubscription);
            request(i2);
        }

        void innerComplete() {
            if (this.wip.decrementAndGet() != 0) {
                next();
            }
            if (this.done) {
                return;
            }
            request(1L);
        }

        void innerError(Throwable th) {
            unsubscribe();
            onError(th);
        }

        void next() {
            boolean z = this.done;
            Completable poll = this.queue.poll();
            if (poll != null) {
                poll.unsafeSubscribe(this.inner);
            } else if (z) {
                if (this.once.compareAndSet(false, true)) {
                    this.actual.onCompleted();
                }
            } else {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(new IllegalStateException("Queue is empty?!"));
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                next();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                this.actual.onError(th);
            } else {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
            }
        }

        @Override // rx.Observer
        public void onNext(Completable completable) {
            if (!this.queue.offer(completable)) {
                onError(new MissingBackpressureException());
            } else if (this.wip.getAndIncrement() == 0) {
                next();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CompletableOnSubscribeConcat(Observable<? extends Completable> observable, int i2) {
        this.sources = observable;
        this.prefetch = i2;
    }

    @Override // rx.functions.Action1
    public void call(Completable.CompletableSubscriber completableSubscriber) {
        CompletableConcatSubscriber completableConcatSubscriber = new CompletableConcatSubscriber(completableSubscriber, this.prefetch);
        completableSubscriber.onSubscribe(completableConcatSubscriber);
        this.sources.subscribe((Subscriber<? super Completable>) completableConcatSubscriber);
    }
}
