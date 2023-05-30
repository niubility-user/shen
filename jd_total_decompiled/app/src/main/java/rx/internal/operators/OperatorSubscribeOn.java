package rx.internal.operators;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;

/* loaded from: classes11.dex */
public final class OperatorSubscribeOn<T> implements Observable.OnSubscribe<T> {
    final Scheduler scheduler;
    final Observable<T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorSubscribeOn$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    public class AnonymousClass1 implements Action0 {
        final /* synthetic */ Scheduler.Worker val$inner;
        final /* synthetic */ Subscriber val$subscriber;

        AnonymousClass1(Subscriber subscriber, Scheduler.Worker worker) {
            this.val$subscriber = subscriber;
            this.val$inner = worker;
        }

        @Override // rx.functions.Action0
        public void call() {
            Thread.currentThread();
            OperatorSubscribeOn.this.source.unsafeSubscribe(new Subscriber<T>
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: INVOKE 
                  (wrap: rx.Observable<T> : 0x000d: IGET 
                  (wrap: rx.internal.operators.OperatorSubscribeOn : 0x000b: IGET (r3v0 'this' rx.internal.operators.OperatorSubscribeOn$1 A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:3) rx.internal.operators.OperatorSubscribeOn.1.this$0 rx.internal.operators.OperatorSubscribeOn)
                 A[WRAPPED] (LINE:3) rx.internal.operators.OperatorSubscribeOn.source rx.Observable)
                  (wrap: rx.Subscriber<T> : 0x0008: CONSTRUCTOR 
                  (r3v0 'this' rx.internal.operators.OperatorSubscribeOn$1 A[IMMUTABLE_TYPE, THIS])
                  (wrap: rx.Subscriber : 0x0006: IGET (r3v0 'this' rx.internal.operators.OperatorSubscribeOn$1 A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] rx.internal.operators.OperatorSubscribeOn.1.val$subscriber rx.Subscriber)
                  (r0 I:java.lang.Thread A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(rx.internal.operators.OperatorSubscribeOn$1, rx.Subscriber, java.lang.Thread):void (m), WRAPPED] (LINE:2) call: rx.internal.operators.OperatorSubscribeOn.1.1.<init>(rx.internal.operators.OperatorSubscribeOn$1, rx.Subscriber, java.lang.Thread):void type: CONSTRUCTOR)
                 type: VIRTUAL call: rx.Observable.unsafeSubscribe(rx.Subscriber):rx.Subscription A[MD:(rx.Subscriber<? super T>):rx.Subscription (m)] (LINE:3) in method: rx.internal.operators.OperatorSubscribeOn.1.call():void, file: classes11.dex
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
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                rx.internal.operators.OperatorSubscribeOn$1$1 r1 = new rx.internal.operators.OperatorSubscribeOn$1$1
                rx.Subscriber r2 = r3.val$subscriber
                r1.<init>(r2)
                rx.internal.operators.OperatorSubscribeOn r0 = rx.internal.operators.OperatorSubscribeOn.this
                rx.Observable<T> r0 = r0.source
                r0.unsafeSubscribe(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorSubscribeOn.AnonymousClass1.call():void");
        }
    }

    public OperatorSubscribeOn(Observable<T> observable, Scheduler scheduler) {
        this.scheduler = scheduler;
        this.source = observable;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        subscriber.add(createWorker);
        createWorker.schedule(new AnonymousClass1(subscriber, createWorker));
    }
}
