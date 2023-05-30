package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.internal.operators.OperatorDebounceWithTime;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

/* loaded from: classes11.dex */
public final class OperatorDebounceWithSelector<T, U> implements Observable.Operator<T, T> {
    final Func1<? super T, ? extends Observable<U>> selector;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorDebounceWithSelector$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    public class AnonymousClass1 extends Subscriber<T> {
        final Subscriber<?> self;
        final OperatorDebounceWithTime.DebounceState<T> state;
        final /* synthetic */ SerializedSubscriber val$s;
        final /* synthetic */ SerialSubscription val$ssub;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Subscriber subscriber, SerializedSubscriber serializedSubscriber, SerialSubscription serialSubscription) {
            super(subscriber);
            this.val$s = serializedSubscriber;
            this.val$ssub = serialSubscription;
            this.state = new OperatorDebounceWithTime.DebounceState<>();
            this.self = this;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.state.emitAndComplete(this.val$s, this);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.val$s.onError(th);
            unsubscribe();
            this.state.clear();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                Observable<U> call = OperatorDebounceWithSelector.this.selector.call(t);
                this.state.next(t);
                Subscriber<U> subscriber = new Subscriber<U>
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0012: CONSTRUCTOR (r1v1 'subscriber' rx.Subscriber<U>) = 
                      (r2v0 'this' rx.internal.operators.OperatorDebounceWithSelector$1 A[IMMUTABLE_TYPE, THIS])
                      (r3 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[DECLARE_VAR, MD:(rx.internal.operators.OperatorDebounceWithSelector$1, int):void (m)] (LINE:3) call: rx.internal.operators.OperatorDebounceWithSelector.1.1.<init>(rx.internal.operators.OperatorDebounceWithSelector$1, int):void type: CONSTRUCTOR in method: rx.internal.operators.OperatorDebounceWithSelector.1.onNext(T):void, file: classes11.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                    	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
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
                    	... 21 more
                    */
                /*
                    this = this;
                    rx.internal.operators.OperatorDebounceWithSelector r0 = rx.internal.operators.OperatorDebounceWithSelector.this     // Catch: java.lang.Throwable -> L1e
                    rx.functions.Func1<? super T, ? extends rx.Observable<U>> r0 = r0.selector     // Catch: java.lang.Throwable -> L1e
                    java.lang.Object r0 = r0.call(r3)     // Catch: java.lang.Throwable -> L1e
                    rx.Observable r0 = (rx.Observable) r0     // Catch: java.lang.Throwable -> L1e
                    rx.internal.operators.OperatorDebounceWithTime$DebounceState<T> r1 = r2.state
                    int r3 = r1.next(r3)
                    rx.internal.operators.OperatorDebounceWithSelector$1$1 r1 = new rx.internal.operators.OperatorDebounceWithSelector$1$1
                    r1.<init>()
                    rx.subscriptions.SerialSubscription r3 = r2.val$ssub
                    r3.set(r1)
                    r0.unsafeSubscribe(r1)
                    return
                L1e:
                    r3 = move-exception
                    rx.exceptions.Exceptions.throwOrReport(r3, r2)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorDebounceWithSelector.AnonymousClass1.onNext(java.lang.Object):void");
            }

            @Override // rx.Subscriber
            public void onStart() {
                request(Long.MAX_VALUE);
            }
        }

        public OperatorDebounceWithSelector(Func1<? super T, ? extends Observable<U>> func1) {
            this.selector = func1;
        }

        @Override // rx.functions.Func1
        public /* bridge */ /* synthetic */ Object call(Object obj) {
            return call((Subscriber) ((Subscriber) obj));
        }

        public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
            SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
            SerialSubscription serialSubscription = new SerialSubscription();
            subscriber.add(serialSubscription);
            return new AnonymousClass1(subscriber, serializedSubscriber, serialSubscription);
        }
    }
