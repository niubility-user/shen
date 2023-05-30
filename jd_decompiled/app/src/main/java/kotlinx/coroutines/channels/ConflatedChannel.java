package kotlinx.coroutines.channels;

import com.jingdong.jdsdk.constant.CartConstant;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mp4parser.aspectj.lang.JoinPoint;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\b\u0010\u0018\u0000 **\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002:\u0001*B\u0007\u00a2\u0006\u0004\b(\u0010)J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00028\u0000H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0006J#\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00028\u00002\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0014\u00a2\u0006\u0004\b\t\u0010\nJ\u0011\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u0014\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\r\u001a\u0004\u0018\u00010\u00042\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0014\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0014\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0016\u001a\u00020\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u00020\u000f8D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001e\u001a\u00060\u001cj\u0002`\u001d8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u000f8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b \u0010\u0019R\u0016\u0010!\u001a\u00020\u000f8D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b!\u0010\u0019R\u0016\u0010\"\u001a\u00020\u000f8D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\u0019R\u0016\u0010&\u001a\u00020#8T@\u0014X\u0094\u0004\u00a2\u0006\u0006\u001a\u0004\b$\u0010%R\u0016\u0010'\u001a\u00020\u000f8D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b'\u0010\u0019\u00a8\u0006+"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedChannel;", "E", "Lkotlinx/coroutines/channels/AbstractChannel;", "element", "", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "pollInternal", "()Ljava/lang/Object;", "pollSelectInternal", "(Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "", "wasClosed", "", "onCancelIdempotent", "(Z)V", "Lkotlinx/coroutines/channels/Receive;", "receive", "enqueueReceiveInternal", "(Lkotlinx/coroutines/channels/Receive;)Z", "isBufferFull", "()Z", "value", "Ljava/lang/Object;", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", JoinPoint.SYNCHRONIZATION_LOCK, "Ljava/util/concurrent/locks/ReentrantLock;", CartConstant.KEY_GLOBAL_IS_EMPTY, "isBufferAlwaysFull", "isBufferAlwaysEmpty", "", "getBufferDebugString", "()Ljava/lang/String;", "bufferDebugString", "isBufferEmpty", "<init>", "()V", "Companion", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public class ConflatedChannel<E> extends AbstractChannel<E> {
    private static final Companion Companion = new Companion(null);
    private static final Symbol EMPTY = new Symbol("EMPTY");
    private final ReentrantLock lock = new ReentrantLock();
    private Object value = EMPTY;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedChannel$Companion;", "", "Lkotlinx/coroutines/internal/Symbol;", "EMPTY", "Lkotlinx/coroutines/internal/Symbol;", "<init>", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public boolean enqueueReceiveInternal(@NotNull Receive<? super E> receive) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.enqueueReceiveInternal(receive);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    protected String getBufferDebugString() {
        return "(value=" + this.value + ')';
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean isBufferAlwaysEmpty() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean isBufferAlwaysFull() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferEmpty() {
        return this.value == EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return isEmptyImpl();
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r1 == null) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        if ((r1 instanceof kotlinx.coroutines.channels.Closed@) == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x001f, code lost:
        if (r1 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0021, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0027, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0028, code lost:
        if (r1 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002a, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x002d, code lost:
        r2 = r1.tryResumeReceive(r5, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0032, code lost:
        if (r2 == null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0038, code lost:
        if (kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED() == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x003c, code lost:
        if (r2 != kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x003e, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0040, code lost:
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0041, code lost:
        if (r2 == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0049, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x004a, code lost:
        r2 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x004f, code lost:
        if (r1 != null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0051, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0054, code lost:
        r1.completeResumeReceive(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0057, code lost:
        if (r1 != null) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0059, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0060, code lost:
        return r1.getOfferResult();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0061, code lost:
        r4.value = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0068, code lost:
        return kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.value == kotlinx.coroutines.channels.ConflatedChannel.EMPTY) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0015, code lost:
        r1 = takeFirstReceiveOrPeekClosed();
     */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object offerInternal(E r5) {
        /*
            r4 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r4.lock
            r0.lock()
            kotlinx.coroutines.channels.Closed r1 = r4.getClosedForSend()     // Catch: java.lang.Throwable -> L69
            if (r1 == 0) goto Lf
            r0.unlock()
            return r1
        Lf:
            java.lang.Object r1 = r4.value     // Catch: java.lang.Throwable -> L69
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.ConflatedChannel.EMPTY     // Catch: java.lang.Throwable -> L69
            if (r1 != r2) goto L61
        L15:
            kotlinx.coroutines.channels.ReceiveOrClosed r1 = r4.takeFirstReceiveOrPeekClosed()     // Catch: java.lang.Throwable -> L69
            if (r1 == 0) goto L61
            boolean r2 = r1 instanceof kotlinx.coroutines.channels.Closed@     // Catch: java.lang.Throwable -> L69
            if (r2 == 0) goto L28
            if (r1 != 0) goto L24
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Throwable -> L69
        L24:
            r0.unlock()
            return r1
        L28:
            if (r1 != 0) goto L2d
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Throwable -> L69
        L2d:
            r2 = 0
            kotlinx.coroutines.internal.Symbol r2 = r1.tryResumeReceive(r5, r2)     // Catch: java.lang.Throwable -> L69
            if (r2 == 0) goto L15
            boolean r3 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()     // Catch: java.lang.Throwable -> L69
            if (r3 == 0) goto L4a
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN     // Catch: java.lang.Throwable -> L69
            if (r2 != r3) goto L40
            r2 = 1
            goto L41
        L40:
            r2 = 0
        L41:
            if (r2 == 0) goto L44
            goto L4a
        L44:
            java.lang.AssertionError r5 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L69
            r5.<init>()     // Catch: java.lang.Throwable -> L69
            throw r5     // Catch: java.lang.Throwable -> L69
        L4a:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L69
            r0.unlock()
            if (r1 != 0) goto L54
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L54:
            r1.completeResumeReceive(r5)
            if (r1 != 0) goto L5c
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L5c:
            java.lang.Object r5 = r1.getOfferResult()
            return r5
        L61:
            r4.value = r5     // Catch: java.lang.Throwable -> L69
            java.lang.Object r5 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS     // Catch: java.lang.Throwable -> L69
            r0.unlock()
            return r5
        L69:
            r5 = move-exception
            r0.unlock()
            goto L6f
        L6e:
            throw r5
        L6f:
            goto L6e
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ConflatedChannel.offerInternal(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerSelectInternal(E element, @NotNull SelectInstance<?> select) {
        Object performAtomicTrySelect;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Closed@<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            if (this.value == EMPTY) {
                do {
                    AbstractSendChannel.TryOfferDesc<E> describeTryOffer = describeTryOffer(element);
                    performAtomicTrySelect = select.performAtomicTrySelect(describeTryOffer);
                    if (performAtomicTrySelect == null) {
                        ReceiveOrClosed<? super E> result = describeTryOffer.getResult();
                        Unit unit = Unit.INSTANCE;
                        if (result == null) {
                            Intrinsics.throwNpe();
                        }
                        result.completeResumeReceive(element);
                        if (result == null) {
                            Intrinsics.throwNpe();
                        }
                        return result.getOfferResult();
                    } else if (performAtomicTrySelect == AbstractChannelKt.OFFER_FAILED) {
                    }
                } while (performAtomicTrySelect == AtomicKt.RETRY_ATOMIC);
                if (performAtomicTrySelect != SelectKt.getALREADY_SELECTED() && !(performAtomicTrySelect instanceof Closed@)) {
                    throw new IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + performAtomicTrySelect).toString());
                }
                return performAtomicTrySelect;
            }
            if (!select.trySelect()) {
                return SelectKt.getALREADY_SELECTED();
            }
            this.value = element;
            return AbstractChannelKt.OFFER_SUCCESS;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public void onCancelIdempotent(boolean wasClosed) {
        if (wasClosed) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                this.value = EMPTY;
                Unit unit = Unit.INSTANCE;
            } finally {
                reentrantLock.unlock();
            }
        }
        super.onCancelIdempotent(wasClosed);
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    @Nullable
    protected Object pollInternal() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Object obj = this.value;
            Symbol symbol = EMPTY;
            if (obj != symbol) {
                this.value = symbol;
                Unit unit = Unit.INSTANCE;
                return obj;
            }
            Object closedForSend = getClosedForSend();
            if (closedForSend == null) {
                closedForSend = AbstractChannelKt.POLL_FAILED;
            }
            return closedForSend;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    @Nullable
    protected Object pollSelectInternal(@NotNull SelectInstance<?> select) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Object obj = this.value;
            Symbol symbol = EMPTY;
            if (obj == symbol) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            } else if (!select.trySelect()) {
                return SelectKt.getALREADY_SELECTED();
            } else {
                Object obj2 = this.value;
                this.value = symbol;
                Unit unit = Unit.INSTANCE;
                return obj2;
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}
