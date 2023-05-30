package rx.internal.producers;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;

/* loaded from: classes11.dex */
public final class SingleProducer<T> extends AtomicBoolean implements Producer {
    private static final long serialVersionUID = -3353584923995471404L;
    final Subscriber<? super T> child;
    final T value;

    public SingleProducer(Subscriber<? super T> subscriber, T t) {
        this.child = subscriber;
        this.value = t;
    }

    @Override // rx.Producer
    public void request(long j2) {
        if (j2 >= 0) {
            if (j2 != 0 && compareAndSet(false, true)) {
                Subscriber<? super T> subscriber = this.child;
                Object obj = (T) this.value;
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                try {
                    subscriber.onNext(obj);
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    subscriber.onCompleted();
                    return;
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, subscriber, obj);
                    return;
                }
            }
            return;
        }
        throw new IllegalArgumentException("n >= 0 required");
    }
}
