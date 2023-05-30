package retrofit2.adapter.rxjava;

import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import rx.Completable;
import rx.Scheduler;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

/* loaded from: classes11.dex */
final class CompletableHelper {

    /* loaded from: classes11.dex */
    static class CompletableCallAdapter implements CallAdapter<Completable> {
        private final Scheduler scheduler;

        CompletableCallAdapter(Scheduler scheduler) {
            this.scheduler = scheduler;
        }

        @Override // retrofit2.CallAdapter
        public Type responseType() {
            return Void.class;
        }

        @Override // retrofit2.CallAdapter
        public Completable adapt(Call call) {
            Completable create = Completable.create(new CompletableCallOnSubscribe(call));
            Scheduler scheduler = this.scheduler;
            return scheduler != null ? create.subscribeOn(scheduler) : create;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class CompletableCallOnSubscribe implements Completable.CompletableOnSubscribe {
        private final Call originalCall;

        CompletableCallOnSubscribe(Call call) {
            this.originalCall = call;
        }

        @Override // rx.functions.Action1
        public void call(Completable.CompletableSubscriber completableSubscriber) {
            final Call clone = this.originalCall.clone();
            Subscription create = Subscriptions.create(new Action0() { // from class: retrofit2.adapter.rxjava.CompletableHelper.CompletableCallOnSubscribe.1
                @Override // rx.functions.Action0
                public void call() {
                    clone.cancel();
                }
            });
            completableSubscriber.onSubscribe(create);
            try {
                Response execute = clone.execute();
                if (!create.isUnsubscribed()) {
                    if (execute.isSuccessful()) {
                        completableSubscriber.onCompleted();
                    } else {
                        completableSubscriber.onError(new HttpException(execute));
                    }
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                if (create.isUnsubscribed()) {
                    return;
                }
                completableSubscriber.onError(th);
            }
        }
    }

    CompletableHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CallAdapter<Completable> createCallAdapter(Scheduler scheduler) {
        return new CompletableCallAdapter(scheduler);
    }
}
