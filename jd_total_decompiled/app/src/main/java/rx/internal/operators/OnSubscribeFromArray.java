package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;

/* loaded from: classes11.dex */
public final class OnSubscribeFromArray<T> implements Observable.OnSubscribe<T> {
    final T[] array;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class FromArrayProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = 3534218984725836979L;
        final T[] array;
        final Subscriber<? super T> child;
        int index;

        public FromArrayProducer(Subscriber<? super T> subscriber, T[] tArr) {
            this.child = subscriber;
            this.array = tArr;
        }

        void fastPath() {
            Subscriber<? super T> subscriber = this.child;
            for (T t : this.array) {
                Object obj = (Object) t;
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(obj);
            }
            if (subscriber.isUnsubscribed()) {
                return;
            }
            subscriber.onCompleted();
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (j2 < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j2);
            } else if (j2 == Long.MAX_VALUE) {
                if (BackpressureUtils.getAndAddRequest(this, j2) == 0) {
                    fastPath();
                }
            } else if (j2 == 0 || BackpressureUtils.getAndAddRequest(this, j2) != 0) {
            } else {
                slowPath(j2);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0038, code lost:
            r10.index = r3;
            r11 = addAndGet(r6);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        void slowPath(long j2) {
            Subscriber<? super T> subscriber = this.child;
            T[] tArr = this.array;
            int length = tArr.length;
            int i2 = this.index;
            do {
                long j3 = 0;
                while (true) {
                    if (j2 != 0 && i2 != length) {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        subscriber.onNext((Object) tArr[i2]);
                        i2++;
                        if (i2 == length) {
                            if (subscriber.isUnsubscribed()) {
                                return;
                            }
                            subscriber.onCompleted();
                            return;
                        }
                        j2--;
                        j3--;
                    } else {
                        j2 = get() + j3;
                        if (j2 == 0) {
                            break;
                        }
                    }
                }
            } while (j2 != 0);
        }
    }

    public OnSubscribeFromArray(T[] tArr) {
        this.array = tArr;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        subscriber.setProducer(new FromArrayProducer(subscriber, this.array));
    }
}
