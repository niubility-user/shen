package rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;

/* loaded from: classes11.dex */
public final class OperatorSampleWithObservable<T, U> implements Observable.Operator<T, T> {
    static final Object EMPTY_TOKEN = new Object();
    final Observable<U> sampler;

    public OperatorSampleWithObservable(Observable<U> observable) {
        this.sampler = observable;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        new SerializedSubscriber(subscriber);
        new AtomicReference(EMPTY_TOKEN);
        final AtomicReference atomicReference = new AtomicReference();
        final Subscriber<U> subscriber2 = new Subscriber<U>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0013: CONSTRUCTOR (r3v0 'subscriber2' rx.Subscriber<U>) = 
              (r5v0 'this' rx.internal.operators.OperatorSampleWithObservable<T, U> A[IMMUTABLE_TYPE, THIS])
              (r1 I:java.util.concurrent.atomic.AtomicReference A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r0 I:rx.observers.SerializedSubscriber A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r2v1 'atomicReference' java.util.concurrent.atomic.AtomicReference A[DONT_INLINE])
             A[DECLARE_VAR, MD:(rx.internal.operators.OperatorSampleWithObservable, java.util.concurrent.atomic.AtomicReference, rx.observers.SerializedSubscriber, java.util.concurrent.atomic.AtomicReference):void (m)] (LINE:5) call: rx.internal.operators.OperatorSampleWithObservable.1.<init>(rx.internal.operators.OperatorSampleWithObservable, java.util.concurrent.atomic.AtomicReference, rx.observers.SerializedSubscriber, java.util.concurrent.atomic.AtomicReference):void type: CONSTRUCTOR in method: rx.internal.operators.OperatorSampleWithObservable.call(rx.Subscriber<? super T>):rx.Subscriber<? super T>, file: classes11.dex
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
            rx.observers.SerializedSubscriber r0 = new rx.observers.SerializedSubscriber
            r0.<init>(r6)
            java.util.concurrent.atomic.AtomicReference r1 = new java.util.concurrent.atomic.AtomicReference
            java.lang.Object r2 = rx.internal.operators.OperatorSampleWithObservable.EMPTY_TOKEN
            r1.<init>(r2)
            java.util.concurrent.atomic.AtomicReference r2 = new java.util.concurrent.atomic.AtomicReference
            r2.<init>()
            rx.internal.operators.OperatorSampleWithObservable$1 r3 = new rx.internal.operators.OperatorSampleWithObservable$1
            r3.<init>()
            rx.internal.operators.OperatorSampleWithObservable$2 r4 = new rx.internal.operators.OperatorSampleWithObservable$2
            r4.<init>()
            r2.lazySet(r4)
            r6.add(r4)
            r6.add(r3)
            rx.Observable<U> r6 = r5.sampler
            r6.unsafeSubscribe(r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorSampleWithObservable.call(rx.Subscriber):rx.Subscriber");
    }
}
