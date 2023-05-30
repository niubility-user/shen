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
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void slowpath(long r13) {
            /*
                r12 = this;
                int r0 = r12.endOfRange
                long r0 = (long) r0
                r2 = 1
                long r0 = r0 + r2
                long r4 = r12.currentIndex
                rx.Subscriber<? super java.lang.Integer> r6 = r12.childSubscriber
                r7 = 0
            Lc:
                r9 = r7
            Ld:
                int r11 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
                if (r11 == 0) goto L27
                int r11 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
                if (r11 == 0) goto L27
                boolean r11 = r6.isUnsubscribed()
                if (r11 == 0) goto L1c
                return
            L1c:
                int r11 = (int) r4
                java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
                r6.onNext(r11)
                long r4 = r4 + r2
                long r9 = r9 + r2
                goto Ld
            L27:
                boolean r13 = r6.isUnsubscribed()
                if (r13 == 0) goto L2e
                return
            L2e:
                int r13 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
                if (r13 != 0) goto L36
                r6.onCompleted()
                return
            L36:
                long r13 = r12.get()
                int r11 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
                if (r11 != 0) goto Ld
                r12.currentIndex = r4
                long r13 = -r9
                long r13 = r12.addAndGet(r13)
                int r9 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1))
                if (r9 != 0) goto Lc
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeRange.RangeProducer.slowpath(long):void");
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
