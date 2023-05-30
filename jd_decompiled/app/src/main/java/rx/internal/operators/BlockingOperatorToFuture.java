package rx.internal.operators;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;

/* loaded from: classes11.dex */
public final class BlockingOperatorToFuture {
    private BlockingOperatorToFuture() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Future<T> toFuture(Observable<? extends T> observable) {
        new CountDownLatch(1);
        new AtomicReference();
        new AtomicReference();
        observable.single().subscribe((Subscriber<? super Object>) ((Subscriber<T>) new Subscriber<T>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0019: INVOKE 
              (wrap: rx.Observable<? extends T> : 0x0010: INVOKE (r4v0 'observable' rx.Observable<? extends T>) type: VIRTUAL call: rx.Observable.single():rx.Observable A[MD:():rx.Observable<T> (m), WRAPPED] (LINE:4))
              (wrap: rx.Subscriber<? super ? extends T> : ?: CAST (rx.Subscriber<? super ? extends T>) (wrap: rx.Subscriber : ?: CHECK_CAST (rx.Subscriber<T>) (wrap: rx.Subscriber : 0x0016: CONSTRUCTOR 
              (r0 I:java.util.concurrent.CountDownLatch A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r2 I:java.util.concurrent.atomic.AtomicReference A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r1 I:java.util.concurrent.atomic.AtomicReference A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(java.util.concurrent.CountDownLatch, java.util.concurrent.atomic.AtomicReference, java.util.concurrent.atomic.AtomicReference):void (m), WRAPPED] call: rx.internal.operators.BlockingOperatorToFuture.1.<init>(java.util.concurrent.CountDownLatch, java.util.concurrent.atomic.AtomicReference, java.util.concurrent.atomic.AtomicReference):void type: CONSTRUCTOR)))
             type: VIRTUAL call: rx.Observable.subscribe(rx.Subscriber):rx.Subscription A[MD:(rx.Subscriber<? super T>):rx.Subscription (m)] (LINE:4) in method: rx.internal.operators.BlockingOperatorToFuture.toFuture(rx.Observable<? extends T>):java.util.concurrent.Future<T>, file: classes11.dex
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
            java.util.concurrent.CountDownLatch r0 = new java.util.concurrent.CountDownLatch
            r1 = 1
            r0.<init>(r1)
            java.util.concurrent.atomic.AtomicReference r1 = new java.util.concurrent.atomic.AtomicReference
            r1.<init>()
            java.util.concurrent.atomic.AtomicReference r2 = new java.util.concurrent.atomic.AtomicReference
            r2.<init>()
            rx.Observable r4 = r4.single()
            rx.internal.operators.BlockingOperatorToFuture$1 r3 = new rx.internal.operators.BlockingOperatorToFuture$1
            r3.<init>()
            rx.Subscription r4 = r4.subscribe(r3)
            rx.internal.operators.BlockingOperatorToFuture$2 r3 = new rx.internal.operators.BlockingOperatorToFuture$2
            r3.<init>()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.BlockingOperatorToFuture.toFuture(rx.Observable):java.util.concurrent.Future");
    }
}
