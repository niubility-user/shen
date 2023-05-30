package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.Notification;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.producers.ProducerArbiter;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.SerialSubscription;

/* loaded from: classes11.dex */
public final class OnSubscribeRedo<T> implements Observable.OnSubscribe<T> {
    static final Func1<Observable<? extends Notification<?>>, Observable<?>> REDO_INFINITE = new Func1<Observable<? extends Notification<?>>, Observable<?>>() { // from class: rx.internal.operators.OnSubscribeRedo.1
        @Override // rx.functions.Func1
        public Observable<?> call(Observable<? extends Notification<?>> observable) {
            return observable.map(new Func1<Notification<?>, Notification<?>>() { // from class: rx.internal.operators.OnSubscribeRedo.1.1
                @Override // rx.functions.Func1
                public Notification<?> call(Notification<?> notification) {
                    return Notification.createOnNext(null);
                }
            });
        }
    };
    private final Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> controlHandlerFunction;
    private final Scheduler scheduler;
    final Observable<T> source;
    final boolean stopOnComplete;
    final boolean stopOnError;

    /* loaded from: classes11.dex */
    public static final class RedoFinite implements Func1<Observable<? extends Notification<?>>, Observable<?>> {
        final long count;

        public RedoFinite(long j2) {
            this.count = j2;
        }

        @Override // rx.functions.Func1
        public Observable<?> call(Observable<? extends Notification<?>> observable) {
            return observable.map(new Func1<Notification<?>, Notification<?>>() { // from class: rx.internal.operators.OnSubscribeRedo.RedoFinite.1
                int num = 0;

                @Override // rx.functions.Func1
                public Notification<?> call(Notification<?> notification) {
                    long j2 = RedoFinite.this.count;
                    if (j2 == 0) {
                        return notification;
                    }
                    int i2 = this.num + 1;
                    this.num = i2;
                    return ((long) i2) <= j2 ? Notification.createOnNext(Integer.valueOf(i2)) : notification;
                }
            }).dematerialize();
        }
    }

    /* loaded from: classes11.dex */
    public static final class RetryWithPredicate implements Func1<Observable<? extends Notification<?>>, Observable<? extends Notification<?>>> {
        final Func2<Integer, Throwable, Boolean> predicate;

        public RetryWithPredicate(Func2<Integer, Throwable, Boolean> func2) {
            this.predicate = func2;
        }

        @Override // rx.functions.Func1
        public Observable<? extends Notification<?>> call(Observable<? extends Notification<?>> observable) {
            return observable.scan(Notification.createOnNext(0), new Func2<Notification<Integer>, Notification<?>, Notification<Integer>>() { // from class: rx.internal.operators.OnSubscribeRedo.RetryWithPredicate.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // rx.functions.Func2
                public Notification<Integer> call(Notification<Integer> notification, Notification<?> notification2) {
                    int intValue = notification.getValue().intValue();
                    return RetryWithPredicate.this.predicate.call(Integer.valueOf(intValue), notification2.getThrowable()).booleanValue() ? Notification.createOnNext(Integer.valueOf(intValue + 1)) : notification2;
                }
            });
        }
    }

    private OnSubscribeRedo(Observable<T> observable, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1, boolean z, boolean z2, Scheduler scheduler) {
        this.source = observable;
        this.controlHandlerFunction = func1;
        this.stopOnComplete = z;
        this.stopOnError = z2;
        this.scheduler = scheduler;
    }

    public static <T> Observable<T> redo(Observable<T> observable, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1, Scheduler scheduler) {
        return Observable.create(new OnSubscribeRedo(observable, func1, false, false, scheduler));
    }

    public static <T> Observable<T> repeat(Observable<T> observable) {
        return repeat(observable, Schedulers.trampoline());
    }

    public static <T> Observable<T> retry(Observable<T> observable) {
        return retry(observable, REDO_INFINITE);
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public static <T> Observable<T> repeat(Observable<T> observable, Scheduler scheduler) {
        return repeat(observable, REDO_INFINITE, scheduler);
    }

    public static <T> Observable<T> retry(Observable<T> observable, long j2) {
        if (j2 >= 0) {
            return j2 == 0 ? observable : retry(observable, new RedoFinite(j2));
        }
        throw new IllegalArgumentException("count >= 0 expected");
    }

    public void call(final Subscriber<? super T> subscriber) {
        new AtomicBoolean(true);
        new AtomicLong();
        final Scheduler.Worker createWorker = this.scheduler.createWorker();
        subscriber.add(createWorker);
        final SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        final BehaviorSubject create = BehaviorSubject.create();
        create.subscribe((Subscriber) Subscribers.empty());
        new ProducerArbiter();
        new Action0
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0034: CONSTRUCTOR 
              (r14v0 'this' rx.internal.operators.OnSubscribeRedo<T> A[IMMUTABLE_TYPE, THIS])
              (r15v0 'subscriber' rx.Subscriber<? super T> A[DONT_INLINE])
              (r0v2 'create' rx.subjects.BehaviorSubject A[DONT_INLINE])
              (r11 I:rx.internal.producers.ProducerArbiter A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r9 I:java.util.concurrent.atomic.AtomicLong A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r7v0 'serialSubscription' rx.subscriptions.SerialSubscription A[DONT_INLINE])
             A[MD:(rx.internal.operators.OnSubscribeRedo, rx.Subscriber, rx.subjects.BehaviorSubject, rx.internal.producers.ProducerArbiter, java.util.concurrent.atomic.AtomicLong, rx.subscriptions.SerialSubscription):void (m)] (LINE:12) call: rx.internal.operators.OnSubscribeRedo.2.<init>(rx.internal.operators.OnSubscribeRedo, rx.Subscriber, rx.subjects.BehaviorSubject, rx.internal.producers.ProducerArbiter, java.util.concurrent.atomic.AtomicLong, rx.subscriptions.SerialSubscription):void type: CONSTRUCTOR in method: rx.internal.operators.OnSubscribeRedo.call(rx.Subscriber<? super T>):void, file: classes11.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            java.util.concurrent.atomic.AtomicBoolean r8 = new java.util.concurrent.atomic.AtomicBoolean
            r0 = 1
            r8.<init>(r0)
            java.util.concurrent.atomic.AtomicLong r9 = new java.util.concurrent.atomic.AtomicLong
            r9.<init>()
            rx.Scheduler r0 = r14.scheduler
            rx.Scheduler$Worker r10 = r0.createWorker()
            r15.add(r10)
            rx.subscriptions.SerialSubscription r7 = new rx.subscriptions.SerialSubscription
            r7.<init>()
            r15.add(r7)
            rx.subjects.BehaviorSubject r0 = rx.subjects.BehaviorSubject.create()
            rx.Subscriber r1 = rx.observers.Subscribers.empty()
            r0.subscribe(r1)
            rx.internal.producers.ProducerArbiter r11 = new rx.internal.producers.ProducerArbiter
            r11.<init>()
            rx.internal.operators.OnSubscribeRedo$2 r12 = new rx.internal.operators.OnSubscribeRedo$2
            r1 = r12
            r2 = r14
            r3 = r15
            r4 = r0
            r5 = r11
            r6 = r9
            r1.<init>()
            rx.functions.Func1<? super rx.Observable<? extends rx.Notification<?>>, ? extends rx.Observable<?>> r1 = r14.controlHandlerFunction
            rx.internal.operators.OnSubscribeRedo$3 r2 = new rx.internal.operators.OnSubscribeRedo$3
            r2.<init>()
            rx.Observable r0 = r0.lift(r2)
            java.lang.Object r0 = r1.call(r0)
            r2 = r0
            rx.Observable r2 = (rx.Observable) r2
            rx.internal.operators.OnSubscribeRedo$4 r13 = new rx.internal.operators.OnSubscribeRedo$4
            r0 = r13
            r1 = r14
            r4 = r9
            r5 = r10
            r6 = r12
            r7 = r8
            r0.<init>()
            r10.schedule(r13)
            rx.internal.operators.OnSubscribeRedo$5 r7 = new rx.internal.operators.OnSubscribeRedo$5
            r0 = r7
            r2 = r9
            r3 = r11
            r4 = r8
            r0.<init>()
            r15.setProducer(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeRedo.call(rx.Subscriber):void");
    }

    public static <T> Observable<T> repeat(Observable<T> observable, long j2) {
        return repeat(observable, j2, Schedulers.trampoline());
    }

    public static <T> Observable<T> repeat(Observable<T> observable, long j2, Scheduler scheduler) {
        if (j2 == 0) {
            return Observable.empty();
        }
        if (j2 >= 0) {
            return repeat(observable, new RedoFinite(j2 - 1), scheduler);
        }
        throw new IllegalArgumentException("count >= 0 expected");
    }

    public static <T> Observable<T> retry(Observable<T> observable, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1) {
        return Observable.create(new OnSubscribeRedo(observable, func1, true, false, Schedulers.trampoline()));
    }

    public static <T> Observable<T> retry(Observable<T> observable, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1, Scheduler scheduler) {
        return Observable.create(new OnSubscribeRedo(observable, func1, true, false, scheduler));
    }

    public static <T> Observable<T> repeat(Observable<T> observable, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1) {
        return Observable.create(new OnSubscribeRedo(observable, func1, false, true, Schedulers.trampoline()));
    }

    public static <T> Observable<T> repeat(Observable<T> observable, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1, Scheduler scheduler) {
        return Observable.create(new OnSubscribeRedo(observable, func1, false, true, scheduler));
    }
}
