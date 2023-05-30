package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;

/* loaded from: classes11.dex */
public final class OperatorSkipUntil<T, U> implements Observable.Operator<T, T> {
    final Observable<U> other;

    public OperatorSkipUntil(Observable<U> observable) {
        this.other = observable;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        new SerializedSubscriber(subscriber);
        new AtomicBoolean();
        Subscriber<U> subscriber2 = new Subscriber<U>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000c: CONSTRUCTOR (r2v0 'subscriber2' rx.Subscriber<U>) = 
              (r4v0 'this' rx.internal.operators.OperatorSkipUntil<T, U> A[IMMUTABLE_TYPE, THIS])
              (r1 I:java.util.concurrent.atomic.AtomicBoolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r0 I:rx.observers.SerializedSubscriber A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[DECLARE_VAR, MD:(rx.internal.operators.OperatorSkipUntil, java.util.concurrent.atomic.AtomicBoolean, rx.observers.SerializedSubscriber):void (m)] (LINE:4) call: rx.internal.operators.OperatorSkipUntil.1.<init>(rx.internal.operators.OperatorSkipUntil, java.util.concurrent.atomic.AtomicBoolean, rx.observers.SerializedSubscriber):void type: CONSTRUCTOR in method: rx.internal.operators.OperatorSkipUntil.call(rx.Subscriber<? super T>):rx.Subscriber<? super T>, file: classes11.dex
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
            r0.<init>(r5)
            java.util.concurrent.atomic.AtomicBoolean r1 = new java.util.concurrent.atomic.AtomicBoolean
            r1.<init>()
            rx.internal.operators.OperatorSkipUntil$1 r2 = new rx.internal.operators.OperatorSkipUntil$1
            r2.<init>()
            r5.add(r2)
            rx.Observable<U> r3 = r4.other
            r3.unsafeSubscribe(r2)
            rx.internal.operators.OperatorSkipUntil$2 r2 = new rx.internal.operators.OperatorSkipUntil$2
            r2.<init>(r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorSkipUntil.call(rx.Subscriber):rx.Subscriber");
    }
}
