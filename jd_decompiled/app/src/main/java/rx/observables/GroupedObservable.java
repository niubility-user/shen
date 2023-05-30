package rx.observables;

import rx.Observable;
import rx.Subscriber;

/* loaded from: classes11.dex */
public class GroupedObservable<K, T> extends Observable<T> {
    private final K key;

    /* JADX INFO: Access modifiers changed from: protected */
    public GroupedObservable(K k2, Observable.OnSubscribe<T> onSubscribe) {
        super(onSubscribe);
        this.key = k2;
    }

    public static <K, T> GroupedObservable<K, T> create(K k2, Observable.OnSubscribe<T> onSubscribe) {
        return new GroupedObservable<>(k2, onSubscribe);
    }

    public static <K, T> GroupedObservable<K, T> from(K k2, final Observable<T> observable) {
        return new GroupedObservable<>(k2, new Observable.OnSubscribe<T>() { // from class: rx.observables.GroupedObservable.1
            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((Subscriber) ((Subscriber) obj));
            }

            public void call(Subscriber<? super T> subscriber) {
                Observable.this.unsafeSubscribe(subscriber);
            }
        });
    }

    public K getKey() {
        return this.key;
    }
}
