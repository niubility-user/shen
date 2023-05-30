package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;

/* loaded from: classes11.dex */
public final class OnSubscribeRange implements Observable.OnSubscribe<Integer> {
    private final int endIndex;
    private final int startIndex;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class RangeProducer extends AtomicLong implements Producer {
        private static final long serialVersionUID = 4114392207069098388L;
        private final Subscriber<? super Integer> childSubscriber;
        private long currentIndex;
        private final int endOfRange;

        RangeProducer(Subscriber<? super Integer> subscriber, int i2, int i3) {
            this.childSubscriber = subscriber;
            this.currentIndex = i2;
            this.endOfRange = i3;
        }

        void fastpath() {
            long j2 = this.endOfRange + 1;
            Subscriber<? super Integer> subscriber = this.childSubscriber;
            for (long j3 = this.currentIndex; j3 != j2; j3++) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(Integer.valueOf((int) j3));
            }
            if (subscriber.isUnsubscribed()) {
                return;
            }
            subscriber.onCompleted();
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (get() == Long.MAX_VALUE) {
                return;
            }
            if (j2 == Long.MAX_VALUE && compareAndSet(0L, Long.MAX_VALUE)) {
                fastpath();
            } else if (j2 <= 0 || BackpressureUtils.getAndAddRequest(this, j2) != 0) {
            } else {
                slowpath(j2);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x003e, code lost:
            r12.currentIndex = r4;
            r13 = addAndGet(-r9);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        void slowpath(long j2) {
            long j3 = this.endOfRange + 1;
            long j4 = this.currentIndex;
            Subscriber<? super Integer> subscriber = this.childSubscriber;
            do {
                long j5 = 0;
                while (true) {
                    if (j5 != j2 && j4 != j3) {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        subscriber.onNext(Integer.valueOf((int) j4));
                        j4++;
                        j5++;
                    } else if (subscriber.isUnsubscribed()) {
                        return;
                    } else {
                        if (j4 == j3) {
                            subscriber.onCompleted();
                            return;
                        }
                        j2 = get();
                        if (j2 == j5) {
                            break;
                        }
                    }
                }
            } while (j2 != 0);
        }
    }

    public OnSubscribeRange(int i2, int i3) {
        this.startIndex = i2;
        this.endIndex = i3;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super Integer> subscriber) {
        subscriber.setProducer(new RangeProducer(subscriber, this.startIndex, this.endIndex));
    }
}
