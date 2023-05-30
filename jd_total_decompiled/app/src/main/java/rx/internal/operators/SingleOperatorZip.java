package rx.internal.operators;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.functions.FuncN;
import rx.subscriptions.CompositeSubscription;

/* loaded from: classes11.dex */
public class SingleOperatorZip {
    public static <T, R> Single<R> zip(final Single<? extends T>[] singleArr, final FuncN<? extends R> funcN) {
        return Single.create(new Single.OnSubscribe<R>() { // from class: rx.internal.operators.SingleOperatorZip.1
            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(final SingleSubscriber<? super R> singleSubscriber) {
                if (singleArr.length == 0) {
                    singleSubscriber.onError(new NoSuchElementException("Can't zip 0 Singles."));
                    return;
                }
                new AtomicInteger(singleArr.length);
                final AtomicBoolean atomicBoolean = new AtomicBoolean();
                final Object[] objArr = new Object[singleArr.length];
                CompositeSubscription compositeSubscription = new CompositeSubscription();
                singleSubscriber.add(compositeSubscription);
                for (int i2 = 0; i2 < singleArr.length && !compositeSubscription.isUnsubscribed() && !atomicBoolean.get(); i2++) {
                    final int i3 = i2;
                    Subscription subscription = new SingleSubscriber<T>
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0047: CONSTRUCTOR (r12v0 'subscription' rx.Subscription) = 
                          (r13v0 'this' rx.internal.operators.SingleOperatorZip$1<R> A[IMMUTABLE_TYPE, THIS])
                          (r9v0 'objArr' java.lang.Object[] A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r5v0 'i3' int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r0 I:java.util.concurrent.atomic.AtomicInteger A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r14v0 'singleSubscriber' rx.SingleSubscriber<? super R> A[DONT_INLINE])
                          (r1v2 'atomicBoolean' java.util.concurrent.atomic.AtomicBoolean A[DONT_INLINE])
                         A[DECLARE_VAR, MD:(rx.internal.operators.SingleOperatorZip$1, java.lang.Object[], int, java.util.concurrent.atomic.AtomicInteger, rx.SingleSubscriber, java.util.concurrent.atomic.AtomicBoolean):void (m)] (LINE:11) call: rx.internal.operators.SingleOperatorZip.1.1.<init>(rx.internal.operators.SingleOperatorZip$1, java.lang.Object[], int, java.util.concurrent.atomic.AtomicInteger, rx.SingleSubscriber, java.util.concurrent.atomic.AtomicBoolean):void type: CONSTRUCTOR in method: rx.internal.operators.SingleOperatorZip.1.call(rx.SingleSubscriber<? super R>):void, file: classes11.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:195)
                        	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:171)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                        	... 25 more
                        */
                    /*
                        this = this;
                        rx.Single[] r0 = r1
                        int r0 = r0.length
                        if (r0 != 0) goto L10
                        java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
                        java.lang.String r1 = "Can't zip 0 Singles."
                        r0.<init>(r1)
                        r14.onError(r0)
                        return
                    L10:
                        java.util.concurrent.atomic.AtomicInteger r0 = new java.util.concurrent.atomic.AtomicInteger
                        rx.Single[] r1 = r1
                        int r1 = r1.length
                        r0.<init>(r1)
                        java.util.concurrent.atomic.AtomicBoolean r1 = new java.util.concurrent.atomic.AtomicBoolean
                        r1.<init>()
                        rx.Single[] r2 = r1
                        int r2 = r2.length
                        java.lang.Object[] r9 = new java.lang.Object[r2]
                        rx.subscriptions.CompositeSubscription r10 = new rx.subscriptions.CompositeSubscription
                        r10.<init>()
                        r14.add(r10)
                        r2 = 0
                        r11 = 0
                    L2c:
                        rx.Single[] r2 = r1
                        int r2 = r2.length
                        if (r11 >= r2) goto L64
                        boolean r2 = r10.isUnsubscribed()
                        if (r2 != 0) goto L64
                        boolean r2 = r1.get()
                        if (r2 == 0) goto L3e
                        goto L64
                    L3e:
                        rx.internal.operators.SingleOperatorZip$1$1 r12 = new rx.internal.operators.SingleOperatorZip$1$1
                        r2 = r12
                        r3 = r13
                        r4 = r9
                        r5 = r11
                        r6 = r0
                        r7 = r14
                        r8 = r1
                        r2.<init>()
                        r10.add(r12)
                        boolean r2 = r10.isUnsubscribed()
                        if (r2 != 0) goto L64
                        boolean r2 = r1.get()
                        if (r2 == 0) goto L5a
                        goto L64
                    L5a:
                        rx.Single[] r2 = r1
                        r2 = r2[r11]
                        r2.subscribe(r12)
                        int r11 = r11 + 1
                        goto L2c
                    L64:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.SingleOperatorZip.AnonymousClass1.call(rx.SingleSubscriber):void");
                }
            });
        }
    }
