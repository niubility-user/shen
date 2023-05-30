package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;

/* loaded from: classes11.dex */
public final class OperatorTake<T> implements Observable.Operator<T, T> {
    final int limit;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorTake$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    public class AnonymousClass1 extends Subscriber<T> {
        boolean completed;
        int count;
        final /* synthetic */ Subscriber val$child;

        AnonymousClass1(Subscriber subscriber) {
            this.val$child = subscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.completed) {
                return;
            }
            this.completed = true;
            this.val$child.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.completed) {
                return;
            }
            this.completed = true;
            try {
                this.val$child.onError(th);
            } finally {
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (isUnsubscribed()) {
                return;
            }
            int i2 = this.count;
            int i3 = i2 + 1;
            this.count = i3;
            int i4 = OperatorTake.this.limit;
            if (i2 < i4) {
                boolean z = i3 == i4;
                this.val$child.onNext(t);
                if (!z || this.completed) {
                    return;
                }
                this.completed = true;
                try {
                    this.val$child.onCompleted();
                } finally {
                    unsubscribe();
                }
            }
        }

        @Override // rx.Subscriber
        public void setProducer(final Producer producer) {
            this.val$child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTake.1.1
                final AtomicLong requested = new AtomicLong(0);

                @Override // rx.Producer
                public void request(long j2) {
                    long j3;
                    long min;
                    if (j2 <= 0 || AnonymousClass1.this.completed) {
                        return;
                    }
                    do {
                        j3 = this.requested.get();
                        min = Math.min(j2, OperatorTake.this.limit - j3);
                        if (min == 0) {
                            return;
                        }
                    } while (!this.requested.compareAndSet(j3, j3 + min));
                    producer.request(min);
                }
            });
        }
    }

    public OperatorTake(int i2) {
        if (i2 >= 0) {
            this.limit = i2;
            return;
        }
        throw new IllegalArgumentException("limit >= 0 required but it was " + i2);
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(subscriber);
        if (this.limit == 0) {
            subscriber.onCompleted();
            anonymousClass1.unsubscribe();
        }
        subscriber.add(anonymousClass1);
        return anonymousClass1;
    }
}
