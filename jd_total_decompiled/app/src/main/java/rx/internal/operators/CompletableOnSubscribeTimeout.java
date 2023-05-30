package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Completable;
import rx.Scheduler;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;

/* loaded from: classes11.dex */
public final class CompletableOnSubscribeTimeout implements Completable.CompletableOnSubscribe {
    final Completable other;
    final Scheduler scheduler;
    final Completable source;
    final long timeout;
    final TimeUnit unit;

    public CompletableOnSubscribeTimeout(Completable completable, long j2, TimeUnit timeUnit, Scheduler scheduler, Completable completable2) {
        this.source = completable;
        this.timeout = j2;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.other = completable2;
    }

    @Override // rx.functions.Action1
    public void call(final Completable.CompletableSubscriber completableSubscriber) {
        final CompositeSubscription compositeSubscription = new CompositeSubscription();
        completableSubscriber.onSubscribe(compositeSubscription);
        new AtomicBoolean();
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        compositeSubscription.add(createWorker);
        createWorker.schedule(new Action0
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001f: INVOKE 
              (r2v1 'createWorker' rx.Scheduler$Worker)
              (wrap: rx.functions.Action0 : 0x0018: CONSTRUCTOR 
              (r7v0 'this' rx.internal.operators.CompletableOnSubscribeTimeout A[IMMUTABLE_TYPE, THIS])
              (r1 I:java.util.concurrent.atomic.AtomicBoolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r0v0 'compositeSubscription' rx.subscriptions.CompositeSubscription A[DONT_INLINE])
              (r8v0 'completableSubscriber' rx.Completable$CompletableSubscriber A[DONT_INLINE])
             A[MD:(rx.internal.operators.CompletableOnSubscribeTimeout, java.util.concurrent.atomic.AtomicBoolean, rx.subscriptions.CompositeSubscription, rx.Completable$CompletableSubscriber):void (m), WRAPPED] (LINE:7) call: rx.internal.operators.CompletableOnSubscribeTimeout.1.<init>(rx.internal.operators.CompletableOnSubscribeTimeout, java.util.concurrent.atomic.AtomicBoolean, rx.subscriptions.CompositeSubscription, rx.Completable$CompletableSubscriber):void type: CONSTRUCTOR)
              (wrap: long : 0x001b: IGET (r7v0 'this' rx.internal.operators.CompletableOnSubscribeTimeout A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] rx.internal.operators.CompletableOnSubscribeTimeout.timeout long)
              (wrap: java.util.concurrent.TimeUnit : 0x001d: IGET (r7v0 'this' rx.internal.operators.CompletableOnSubscribeTimeout A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] rx.internal.operators.CompletableOnSubscribeTimeout.unit java.util.concurrent.TimeUnit)
             type: VIRTUAL call: rx.Scheduler.Worker.schedule(rx.functions.Action0, long, java.util.concurrent.TimeUnit):rx.Subscription A[MD:(rx.functions.Action0, long, java.util.concurrent.TimeUnit):rx.Subscription (m)] (LINE:7) in method: rx.internal.operators.CompletableOnSubscribeTimeout.call(rx.Completable$CompletableSubscriber):void, file: classes11.dex
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
            rx.subscriptions.CompositeSubscription r0 = new rx.subscriptions.CompositeSubscription
            r0.<init>()
            r8.onSubscribe(r0)
            java.util.concurrent.atomic.AtomicBoolean r1 = new java.util.concurrent.atomic.AtomicBoolean
            r1.<init>()
            rx.Scheduler r2 = r7.scheduler
            rx.Scheduler$Worker r2 = r2.createWorker()
            r0.add(r2)
            rx.internal.operators.CompletableOnSubscribeTimeout$1 r3 = new rx.internal.operators.CompletableOnSubscribeTimeout$1
            r3.<init>()
            long r4 = r7.timeout
            java.util.concurrent.TimeUnit r6 = r7.unit
            r2.schedule(r3, r4, r6)
            rx.Completable r2 = r7.source
            rx.internal.operators.CompletableOnSubscribeTimeout$2 r3 = new rx.internal.operators.CompletableOnSubscribeTimeout$2
            r3.<init>()
            r2.unsafeSubscribe(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.CompletableOnSubscribeTimeout.call(rx.Completable$CompletableSubscriber):void");
    }
}
