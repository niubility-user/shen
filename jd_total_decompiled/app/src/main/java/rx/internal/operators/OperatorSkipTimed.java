package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;

/* loaded from: classes11.dex */
public final class OperatorSkipTimed<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;

    public OperatorSkipTimed(long j2, TimeUnit timeUnit, Scheduler scheduler) {
        this.time = j2;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        subscriber.add(createWorker);
        new AtomicBoolean();
        createWorker.schedule(new Action0
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0017: INVOKE 
              (r0v1 'createWorker' rx.Scheduler$Worker)
              (wrap: rx.functions.Action0 : 0x0010: CONSTRUCTOR 
              (r6v0 'this' rx.internal.operators.OperatorSkipTimed<T> A[IMMUTABLE_TYPE, THIS])
              (r1 I:java.util.concurrent.atomic.AtomicBoolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(rx.internal.operators.OperatorSkipTimed, java.util.concurrent.atomic.AtomicBoolean):void (m), WRAPPED] (LINE:5) call: rx.internal.operators.OperatorSkipTimed.1.<init>(rx.internal.operators.OperatorSkipTimed, java.util.concurrent.atomic.AtomicBoolean):void type: CONSTRUCTOR)
              (wrap: long : 0x0013: IGET (r6v0 'this' rx.internal.operators.OperatorSkipTimed<T> A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] rx.internal.operators.OperatorSkipTimed.time long)
              (wrap: java.util.concurrent.TimeUnit : 0x0015: IGET (r6v0 'this' rx.internal.operators.OperatorSkipTimed<T> A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] rx.internal.operators.OperatorSkipTimed.unit java.util.concurrent.TimeUnit)
             type: VIRTUAL call: rx.Scheduler.Worker.schedule(rx.functions.Action0, long, java.util.concurrent.TimeUnit):rx.Subscription A[MD:(rx.functions.Action0, long, java.util.concurrent.TimeUnit):rx.Subscription (m)] (LINE:5) in method: rx.internal.operators.OperatorSkipTimed.call(rx.Subscriber<? super T>):rx.Subscriber<? super T>, file: classes11.dex
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
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            rx.Scheduler r0 = r6.scheduler
            rx.Scheduler$Worker r0 = r0.createWorker()
            r7.add(r0)
            java.util.concurrent.atomic.AtomicBoolean r1 = new java.util.concurrent.atomic.AtomicBoolean
            r1.<init>()
            rx.internal.operators.OperatorSkipTimed$1 r2 = new rx.internal.operators.OperatorSkipTimed$1
            r2.<init>()
            long r3 = r6.time
            java.util.concurrent.TimeUnit r5 = r6.unit
            r0.schedule(r2, r3, r5)
            rx.internal.operators.OperatorSkipTimed$2 r0 = new rx.internal.operators.OperatorSkipTimed$2
            r0.<init>(r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorSkipTimed.call(rx.Subscriber):rx.Subscriber");
    }
}
