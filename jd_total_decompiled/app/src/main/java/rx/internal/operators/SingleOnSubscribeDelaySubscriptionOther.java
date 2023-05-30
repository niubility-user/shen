package rx.internal.operators;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.subscriptions.SerialSubscription;

/* loaded from: classes11.dex */
public final class SingleOnSubscribeDelaySubscriptionOther<T> implements Single.OnSubscribe<T> {
    final Single<? extends T> main;
    final Observable<?> other;

    public SingleOnSubscribeDelaySubscriptionOther(Single<? extends T> single, Observable<?> observable) {
        this.main = single;
        this.other = observable;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(final SingleSubscriber<? super T> singleSubscriber) {
        new SingleSubscriber<T>() { // from class: rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther.1
            @Override // rx.SingleSubscriber
            public void onError(Throwable th) {
                singleSubscriber.onError(th);
            }

            @Override // rx.SingleSubscriber
            public void onSuccess(T t) {
                singleSubscriber.onSuccess(t);
            }
        };
        final SerialSubscription serialSubscription = new SerialSubscription();
        singleSubscriber.add(serialSubscription);
        Subscriber<? super Object> subscriber = new Subscriber<Object>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: CONSTRUCTOR (r3v1 'subscriber' rx.Subscriber<? super ?>) = 
              (r2v0 'this' rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther<T> A[IMMUTABLE_TYPE, THIS])
              (r0 I:rx.SingleSubscriber A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r1v0 'serialSubscription' rx.subscriptions.SerialSubscription A[DONT_INLINE])
             A[DECLARE_VAR, MD:(rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther, rx.SingleSubscriber, rx.subscriptions.SerialSubscription):void (m)] (LINE:5) call: rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther.2.<init>(rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther, rx.SingleSubscriber, rx.subscriptions.SerialSubscription):void type: CONSTRUCTOR in method: rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther.call(rx.SingleSubscriber<? super T>):void, file: classes11.dex
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
            rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther$1 r0 = new rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther$1
            r0.<init>()
            rx.subscriptions.SerialSubscription r1 = new rx.subscriptions.SerialSubscription
            r1.<init>()
            r3.add(r1)
            rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther$2 r3 = new rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther$2
            r3.<init>()
            r1.set(r3)
            rx.Observable<?> r0 = r2.other
            r0.subscribe(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther.call(rx.SingleSubscriber):void");
    }
}
