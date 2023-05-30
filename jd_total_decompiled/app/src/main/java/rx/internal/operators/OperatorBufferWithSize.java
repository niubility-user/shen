package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.MissingBackpressureException;

/* loaded from: classes11.dex */
public final class OperatorBufferWithSize<T> implements Observable.Operator<List<T>, T> {
    final int count;
    final int skip;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class BufferExact<T> extends Subscriber<T> {
        final Subscriber<? super List<T>> actual;
        List<T> buffer;
        final int count;

        public BufferExact(Subscriber<? super List<T>> subscriber, int i2) {
            this.actual = subscriber;
            this.count = i2;
            request(0L);
        }

        Producer createProducer() {
            return new Producer() { // from class: rx.internal.operators.OperatorBufferWithSize.BufferExact.1
                @Override // rx.Producer
                public void request(long j2) {
                    if (j2 < 0) {
                        throw new IllegalArgumentException("n >= required but it was " + j2);
                    } else if (j2 != 0) {
                        BufferExact.this.request(BackpressureUtils.multiplyCap(j2, BufferExact.this.count));
                    }
                }
            };
        }

        @Override // rx.Observer
        public void onCompleted() {
            List<T> list = this.buffer;
            if (list != null) {
                this.actual.onNext(list);
            }
            this.actual.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.buffer = null;
            this.actual.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            List list = this.buffer;
            if (list == null) {
                list = new ArrayList(this.count);
                this.buffer = list;
            }
            list.add(t);
            if (list.size() == this.count) {
                this.buffer = null;
                this.actual.onNext(list);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class BufferOverlap<T> extends Subscriber<T> {
        final Subscriber<? super List<T>> actual;
        final int count;
        long index;
        long produced;
        final ArrayDeque<List<T>> queue = new ArrayDeque<>();
        final AtomicLong requested = new AtomicLong();
        final int skip;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes11.dex */
        public final class BufferOverlapProducer extends AtomicBoolean implements Producer {
            private static final long serialVersionUID = -4015894850868853147L;

            BufferOverlapProducer() {
            }

            @Override // rx.Producer
            public void request(long j2) {
                BufferOverlap bufferOverlap = BufferOverlap.this;
                if (!BackpressureUtils.postCompleteRequest(bufferOverlap.requested, j2, bufferOverlap.queue, bufferOverlap.actual) || j2 == 0) {
                    return;
                }
                if (get() || !compareAndSet(false, true)) {
                    bufferOverlap.request(BackpressureUtils.multiplyCap(bufferOverlap.skip, j2));
                } else {
                    bufferOverlap.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(bufferOverlap.skip, j2 - 1), bufferOverlap.count));
                }
            }
        }

        public BufferOverlap(Subscriber<? super List<T>> subscriber, int i2, int i3) {
            this.actual = subscriber;
            this.count = i2;
            this.skip = i3;
            request(0L);
        }

        Producer createProducer() {
            return new BufferOverlapProducer();
        }

        @Override // rx.Observer
        public void onCompleted() {
            long j2 = this.produced;
            if (j2 != 0) {
                if (j2 > this.requested.get()) {
                    this.actual.onError(new MissingBackpressureException("More produced than requested? " + j2));
                    return;
                }
                this.requested.addAndGet(-j2);
            }
            BackpressureUtils.postCompleteDone(this.requested, this.queue, this.actual);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.queue.clear();
            this.actual.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            long j2 = this.index;
            if (j2 == 0) {
                this.queue.offer(new ArrayList(this.count));
            }
            long j3 = j2 + 1;
            if (j3 == this.skip) {
                this.index = 0L;
            } else {
                this.index = j3;
            }
            Iterator<List<T>> it = this.queue.iterator();
            while (it.hasNext()) {
                it.next().add(t);
            }
            List<T> peek = this.queue.peek();
            if (peek == null || peek.size() != this.count) {
                return;
            }
            this.queue.poll();
            this.produced++;
            this.actual.onNext(peek);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class BufferSkip<T> extends Subscriber<T> {
        final Subscriber<? super List<T>> actual;
        List<T> buffer;
        final int count;
        long index;
        final int skip;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes11.dex */
        public final class BufferSkipProducer extends AtomicBoolean implements Producer {
            private static final long serialVersionUID = 3428177408082367154L;

            BufferSkipProducer() {
            }

            @Override // rx.Producer
            public void request(long j2) {
                if (j2 < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + j2);
                } else if (j2 != 0) {
                    BufferSkip bufferSkip = BufferSkip.this;
                    if (get() || !compareAndSet(false, true)) {
                        bufferSkip.request(BackpressureUtils.multiplyCap(j2, bufferSkip.skip));
                    } else {
                        bufferSkip.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(j2, bufferSkip.count), BackpressureUtils.multiplyCap(bufferSkip.skip - bufferSkip.count, j2 - 1)));
                    }
                }
            }
        }

        public BufferSkip(Subscriber<? super List<T>> subscriber, int i2, int i3) {
            this.actual = subscriber;
            this.count = i2;
            this.skip = i3;
            request(0L);
        }

        Producer createProducer() {
            return new BufferSkipProducer();
        }

        @Override // rx.Observer
        public void onCompleted() {
            List<T> list = this.buffer;
            if (list != null) {
                this.buffer = null;
                this.actual.onNext(list);
            }
            this.actual.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.buffer = null;
            this.actual.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            long j2 = this.index;
            List list = this.buffer;
            if (j2 == 0) {
                list = new ArrayList(this.count);
                this.buffer = list;
            }
            long j3 = j2 + 1;
            if (j3 == this.skip) {
                this.index = 0L;
            } else {
                this.index = j3;
            }
            if (list != null) {
                list.add(t);
                if (list.size() == this.count) {
                    this.buffer = null;
                    this.actual.onNext(list);
                }
            }
        }
    }

    public OperatorBufferWithSize(int i2, int i3) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("count must be greater than 0");
        }
        if (i3 > 0) {
            this.count = i2;
            this.skip = i3;
            return;
        }
        throw new IllegalArgumentException("skip must be greater than 0");
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super List<T>> subscriber) {
        int i2 = this.skip;
        int i3 = this.count;
        if (i2 == i3) {
            BufferExact bufferExact = new BufferExact(subscriber, i3);
            subscriber.add(bufferExact);
            subscriber.setProducer(bufferExact.createProducer());
            return bufferExact;
        } else if (i2 > i3) {
            BufferSkip bufferSkip = new BufferSkip(subscriber, i3, i2);
            subscriber.add(bufferSkip);
            subscriber.setProducer(bufferSkip.createProducer());
            return bufferSkip;
        } else {
            BufferOverlap bufferOverlap = new BufferOverlap(subscriber, i3, i2);
            subscriber.add(bufferOverlap);
            subscriber.setProducer(bufferOverlap.createProducer());
            return bufferOverlap;
        }
    }
}
