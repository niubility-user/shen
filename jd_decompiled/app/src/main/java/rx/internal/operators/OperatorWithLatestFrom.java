package rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;
import rx.observers.SerializedSubscriber;

/* loaded from: classes11.dex */
public final class OperatorWithLatestFrom<T, U, R> implements Observable.Operator<R, T> {
    static final Object EMPTY = new Object();
    final Observable<? extends U> other;
    final Func2<? super T, ? super U, ? extends R> resultSelector;

    public OperatorWithLatestFrom(Observable<? extends U> observable, Func2<? super T, ? super U, ? extends R> func2) {
        this.other = observable;
        this.resultSelector = func2;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber, false);
        subscriber.add(serializedSubscriber);
        new AtomicReference(EMPTY);
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: ?: CHECK_CAST (r7v0 'subscriber2' rx.Subscriber) = (rx.Subscriber<T>) (wrap: rx.Subscriber : 0x0018: CONSTRUCTOR 
              (r8v0 'this' rx.internal.operators.OperatorWithLatestFrom<T, U, R> A[IMMUTABLE_TYPE, THIS])
              (r6v0 'serializedSubscriber' rx.observers.SerializedSubscriber)
              true
              (r9 I:java.util.concurrent.atomic.AtomicReference A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r6v0 'serializedSubscriber' rx.observers.SerializedSubscriber A[DONT_INLINE])
             A[MD:(rx.internal.operators.OperatorWithLatestFrom, rx.Subscriber, boolean, java.util.concurrent.atomic.AtomicReference, rx.observers.SerializedSubscriber):void (m), WRAPPED] (LINE:5) call: rx.internal.operators.OperatorWithLatestFrom.1.<init>(rx.internal.operators.OperatorWithLatestFrom, rx.Subscriber, boolean, java.util.concurrent.atomic.AtomicReference, rx.observers.SerializedSubscriber):void type: CONSTRUCTOR) in method: rx.internal.operators.OperatorWithLatestFrom.call(rx.Subscriber<? super R>):rx.Subscriber<? super T>, file: classes11.dex
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
            */
        /*
            this = this;
            rx.observers.SerializedSubscriber r6 = new rx.observers.SerializedSubscriber
            r0 = 0
            r6.<init>(r9, r0)
            r9.add(r6)
            java.util.concurrent.atomic.AtomicReference r9 = new java.util.concurrent.atomic.AtomicReference
            java.lang.Object r0 = rx.internal.operators.OperatorWithLatestFrom.EMPTY
            r9.<init>(r0)
            rx.internal.operators.OperatorWithLatestFrom$1 r7 = new rx.internal.operators.OperatorWithLatestFrom$1
            r3 = 1
            r0 = r7
            r1 = r8
            r2 = r6
            r4 = r9
            r5 = r6
            r0.<init>(r2, r3)
            rx.internal.operators.OperatorWithLatestFrom$2 r0 = new rx.internal.operators.OperatorWithLatestFrom$2
            r0.<init>()
            r6.add(r7)
            r6.add(r0)
            rx.Observable<? extends U> r9 = r8.other
            r9.unsafeSubscribe(r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorWithLatestFrom.call(rx.Subscriber):rx.Subscriber");
    }
}
