package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

/* loaded from: classes11.dex */
public final class OperatorTakeUntilPredicate<T> implements Observable.Operator<T, T> {
    final Func1<? super T, Boolean> stopPredicate;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public final class ParentSubscriber extends Subscriber<T> {
        private final Subscriber<? super T> child;
        private boolean done = false;

        ParentSubscriber(Subscriber<? super T> subscriber) {
            this.child = subscriber;
        }

        void downstreamRequest(long j2) {
            request(j2);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.child.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                return;
            }
            this.child.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.child.onNext(t);
            try {
                if (OperatorTakeUntilPredicate.this.stopPredicate.call(t).booleanValue()) {
                    this.done = true;
                    this.child.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable th) {
                this.done = true;
                Exceptions.throwOrReport(th, this.child, t);
                unsubscribe();
            }
        }
    }

    public OperatorTakeUntilPredicate(Func1<? super T, Boolean> func1) {
        this.stopPredicate = func1;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final ParentSubscriber parentSubscriber = new ParentSubscriber(subscriber);
        subscriber.add(parentSubscriber);
        subscriber.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTakeUntilPredicate.1
            @Override // rx.Producer
            public void request(long j2) {
                parentSubscriber.downstreamRequest(j2);
            }
        });
        return parentSubscriber;
    }
}
