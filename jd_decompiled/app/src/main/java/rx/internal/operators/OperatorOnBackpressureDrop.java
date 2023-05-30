package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action1;

/* loaded from: classes11.dex */
public class OperatorOnBackpressureDrop<T> implements Observable.Operator<T, T> {
    final Action1<? super T> onDrop;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class Holder {
        static final OperatorOnBackpressureDrop<Object> INSTANCE = new OperatorOnBackpressureDrop<>();

        private Holder() {
        }
    }

    OperatorOnBackpressureDrop() {
        this(null);
    }

    public static <T> OperatorOnBackpressureDrop<T> instance() {
        return (OperatorOnBackpressureDrop<T>) Holder.INSTANCE;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorOnBackpressureDrop(Action1<? super T> action1) {
        this.onDrop = action1;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        new AtomicLong();
        subscriber.setProducer(new Producer
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000a: INVOKE 
              (r3v0 'subscriber' rx.Subscriber<? super T>)
              (wrap: rx.Producer : 0x0007: CONSTRUCTOR 
              (r2v0 'this' rx.internal.operators.OperatorOnBackpressureDrop<T> A[IMMUTABLE_TYPE, THIS])
              (r0 I:java.util.concurrent.atomic.AtomicLong A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(rx.internal.operators.OperatorOnBackpressureDrop, java.util.concurrent.atomic.AtomicLong):void (m), WRAPPED] (LINE:3) call: rx.internal.operators.OperatorOnBackpressureDrop.1.<init>(rx.internal.operators.OperatorOnBackpressureDrop, java.util.concurrent.atomic.AtomicLong):void type: CONSTRUCTOR)
             type: VIRTUAL call: rx.Subscriber.setProducer(rx.Producer):void A[MD:(rx.Producer):void (m)] (LINE:3) in method: rx.internal.operators.OperatorOnBackpressureDrop.call(rx.Subscriber<? super T>):rx.Subscriber<? super T>, file: classes11.dex
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
            java.util.concurrent.atomic.AtomicLong r0 = new java.util.concurrent.atomic.AtomicLong
            r0.<init>()
            rx.internal.operators.OperatorOnBackpressureDrop$1 r1 = new rx.internal.operators.OperatorOnBackpressureDrop$1
            r1.<init>()
            r3.setProducer(r1)
            rx.internal.operators.OperatorOnBackpressureDrop$2 r1 = new rx.internal.operators.OperatorOnBackpressureDrop$2
            r1.<init>(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorOnBackpressureDrop.call(rx.Subscriber):rx.Subscriber");
    }
}
