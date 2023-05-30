package retrofit2.adapter.rxjava;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

/* loaded from: classes11.dex */
public final class RxJavaCallAdapterFactory extends CallAdapter.Factory {
    private final Scheduler scheduler;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class CallOnSubscribe<T> implements Observable.OnSubscribe<Response<T>> {
        private final Call<T> originalCall;

        CallOnSubscribe(Call<T> call) {
            this.originalCall = call;
        }

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public void call(Subscriber<? super Response<T>> subscriber) {
            RequestArbiter requestArbiter = new RequestArbiter(this.originalCall.clone(), subscriber);
            subscriber.add(requestArbiter);
            subscriber.setProducer(requestArbiter);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class RequestArbiter<T> extends AtomicBoolean implements Subscription, Producer {
        private final Call<T> call;
        private final Subscriber<? super Response<T>> subscriber;

        RequestArbiter(Call<T> call, Subscriber<? super Response<T>> subscriber) {
            this.call = call;
            this.subscriber = subscriber;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.call.isCanceled();
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (j2 >= 0) {
                if (j2 != 0 && compareAndSet(false, true)) {
                    try {
                        Response<T> execute = this.call.execute();
                        if (!this.subscriber.isUnsubscribed()) {
                            this.subscriber.onNext(execute);
                        }
                        if (this.subscriber.isUnsubscribed()) {
                            return;
                        }
                        this.subscriber.onCompleted();
                        return;
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        if (this.subscriber.isUnsubscribed()) {
                            return;
                        }
                        this.subscriber.onError(th);
                        return;
                    }
                }
                return;
            }
            throw new IllegalArgumentException("n < 0: " + j2);
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.call.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ResponseCallAdapter implements CallAdapter<Observable<?>> {
        private final Type responseType;
        private final Scheduler scheduler;

        ResponseCallAdapter(Type type, Scheduler scheduler) {
            this.responseType = type;
            this.scheduler = scheduler;
        }

        @Override // retrofit2.CallAdapter
        public Type responseType() {
            return this.responseType;
        }

        @Override // retrofit2.CallAdapter
        public <R> Observable<?> adapt(Call<R> call) {
            Observable<?> create = Observable.create(new CallOnSubscribe(call));
            Scheduler scheduler = this.scheduler;
            return scheduler != null ? create.subscribeOn(scheduler) : create;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ResultCallAdapter implements CallAdapter<Observable<?>> {
        private final Type responseType;
        private final Scheduler scheduler;

        ResultCallAdapter(Type type, Scheduler scheduler) {
            this.responseType = type;
            this.scheduler = scheduler;
        }

        @Override // retrofit2.CallAdapter
        public Type responseType() {
            return this.responseType;
        }

        @Override // retrofit2.CallAdapter
        public <R> Observable<?> adapt(Call<R> call) {
            Observable observable = (Observable<R>) Observable.create(new CallOnSubscribe(call)).map(new Func1<Response<R>, Result<R>>() { // from class: retrofit2.adapter.rxjava.RxJavaCallAdapterFactory.ResultCallAdapter.2
                @Override // rx.functions.Func1
                public /* bridge */ /* synthetic */ Object call(Object obj) {
                    return call((Response) ((Response) obj));
                }

                public Result<R> call(Response<R> response) {
                    return Result.response(response);
                }
            }).onErrorReturn(new Func1<Throwable, Result<R>>() { // from class: retrofit2.adapter.rxjava.RxJavaCallAdapterFactory.ResultCallAdapter.1
                @Override // rx.functions.Func1
                public Result<R> call(Throwable th) {
                    return Result.error(th);
                }
            });
            Scheduler scheduler = this.scheduler;
            return scheduler != null ? observable.subscribeOn(scheduler) : observable;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class SimpleCallAdapter implements CallAdapter<Observable<?>> {
        private final Type responseType;
        private final Scheduler scheduler;

        SimpleCallAdapter(Type type, Scheduler scheduler) {
            this.responseType = type;
            this.scheduler = scheduler;
        }

        @Override // retrofit2.CallAdapter
        public Type responseType() {
            return this.responseType;
        }

        @Override // retrofit2.CallAdapter
        public <R> Observable<?> adapt(Call<R> call) {
            Observable<?> lift = Observable.create(new CallOnSubscribe(call)).lift(OperatorMapResponseToBodyOrError.instance());
            Scheduler scheduler = this.scheduler;
            return scheduler != null ? lift.subscribeOn(scheduler) : lift;
        }
    }

    private RxJavaCallAdapterFactory(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public static RxJavaCallAdapterFactory create() {
        return new RxJavaCallAdapterFactory(null);
    }

    public static RxJavaCallAdapterFactory createWithScheduler(Scheduler scheduler) {
        if (scheduler != null) {
            return new RxJavaCallAdapterFactory(scheduler);
        }
        throw new NullPointerException("scheduler == null");
    }

    private CallAdapter<Observable<?>> getCallAdapter(Type type, Scheduler scheduler) {
        Type parameterUpperBound = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) type);
        Class<?> rawType = CallAdapter.Factory.getRawType(parameterUpperBound);
        if (rawType == Response.class) {
            if (parameterUpperBound instanceof ParameterizedType) {
                return new ResponseCallAdapter(CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) parameterUpperBound), scheduler);
            }
            throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
        } else if (rawType == Result.class) {
            if (parameterUpperBound instanceof ParameterizedType) {
                return new ResultCallAdapter(CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) parameterUpperBound), scheduler);
            }
            throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
        } else {
            return new SimpleCallAdapter(parameterUpperBound, scheduler);
        }
    }

    @Override // retrofit2.CallAdapter.Factory
    public CallAdapter<?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        Class<?> rawType = CallAdapter.Factory.getRawType(type);
        String canonicalName = rawType.getCanonicalName();
        boolean equals = "rx.Single".equals(canonicalName);
        boolean equals2 = "rx.Completable".equals(canonicalName);
        if (rawType == Observable.class || equals || equals2) {
            if (equals2 || (type instanceof ParameterizedType)) {
                if (equals2) {
                    return CompletableHelper.createCallAdapter(this.scheduler);
                }
                CallAdapter<Observable<?>> callAdapter = getCallAdapter(type, this.scheduler);
                return equals ? SingleHelper.makeSingle(callAdapter) : callAdapter;
            }
            String str = equals ? "Single" : "Observable";
            throw new IllegalStateException(str + " return type must be parameterized as " + str + "<Foo> or " + str + "<? extends Foo>");
        }
        return null;
    }
}
