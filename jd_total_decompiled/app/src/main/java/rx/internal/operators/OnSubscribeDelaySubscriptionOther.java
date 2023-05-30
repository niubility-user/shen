package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.observers.Subscribers;
import rx.subscriptions.SerialSubscription;

/* loaded from: classes11.dex */
public final class OnSubscribeDelaySubscriptionOther<T, U> implements Observable.OnSubscribe<T> {
    final Observable<? extends T> main;
    final Observable<U> other;

    public OnSubscribeDelaySubscriptionOther(Observable<? extends T> observable, Observable<U> observable2) {
        this.main = observable;
        this.other = observable2;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        final SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        Subscribers.wrap(subscriber);
        Subscriber<U> subscriber2 = new Subscriber<U>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000e: CONSTRUCTOR (r1v0 'subscriber2' rx.Subscriber<U>) = 
              (r2v0 'this' rx.internal.operators.OnSubscribeDelaySubscriptionOther<T, U> A[IMMUTABLE_TYPE, THIS])
              (r3 I:rx.Subscriber A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r0v0 'serialSubscription' rx.subscriptions.SerialSubscription A[DONT_INLINE])
             A[DECLARE_VAR, MD:(rx.internal.operators.OnSubscribeDelaySubscriptionOther, rx.Subscriber, rx.subscriptions.SerialSubscription):void (m)] (LINE:5) call: rx.internal.operators.OnSubscribeDelaySubscriptionOther.1.<init>(rx.internal.operators.OnSubscribeDelaySubscriptionOther, rx.Subscriber, rx.subscriptions.SerialSubscription):void type: CONSTRUCTOR in method: rx.internal.operators.OnSubscribeDelaySubscriptionOther.call(rx.Subscriber<? super T>):void, file: classes11.dex
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
            rx.subscriptions.SerialSubscription r0 = new rx.subscriptions.SerialSubscription
            r0.<init>()
            r3.add(r0)
            rx.Subscriber r3 = rx.observers.Subscribers.wrap(r3)
            rx.internal.operators.OnSubscribeDelaySubscriptionOther$1 r1 = new rx.internal.operators.OnSubscribeDelaySubscriptionOther$1
            r1.<init>()
            r0.set(r1)
            rx.Observable<U> r3 = r2.other
            r3.unsafeSubscribe(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeDelaySubscriptionOther.call(rx.Subscriber):void");
    }
}
