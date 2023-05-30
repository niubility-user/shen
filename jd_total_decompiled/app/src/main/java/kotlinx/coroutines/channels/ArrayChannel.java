package kotlinx.coroutines.channels;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.AbstractChannel;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mp4parser.aspectj.lang.JoinPoint;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u000f\u0012\u0006\u0010'\u001a\u00020\u0003\u00a2\u0006\u0004\b8\u0010\u0007J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00028\u0000H\u0014\u00a2\u0006\u0004\b\n\u0010\u000bJ#\u0010\u000e\u001a\u00020\t2\u0006\u0010\b\u001a\u00028\u00002\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\fH\u0014\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0012\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\u0010H\u0014\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u0004\u0018\u00010\tH\u0014\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0016\u001a\u0004\u0018\u00010\t2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\fH\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u001b\u001a\u00020\u001a2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0014\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001aH\u0014\u00a2\u0006\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u001a8D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b \u0010!R\u001e\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0019\u0010'\u001a\u00020\u00038\u0006@\u0006\u00a2\u0006\f\n\u0004\b'\u0010&\u001a\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\u001a8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b*\u0010!R\u0016\u0010.\u001a\u00020+8T@\u0014X\u0094\u0004\u00a2\u0006\u0006\u001a\u0004\b,\u0010-R\u0016\u0010/\u001a\u00020\u001a8D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b/\u0010!R\u0016\u00100\u001a\u00020\u001a8D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b0\u0010!R\u0016\u00101\u001a\u00020\u001a8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b1\u0010!R\u001a\u00104\u001a\u000602j\u0002`38\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0016\u00106\u001a\u00020\u001a8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b6\u0010!R\u0016\u00107\u001a\u00020\u001a8D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b7\u0010!\u00a8\u00069"}, d2 = {"Lkotlinx/coroutines/channels/ArrayChannel;", "E", "Lkotlinx/coroutines/channels/AbstractChannel;", "", "currentSize", "", "ensureCapacity", "(I)V", "element", "", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/Send;", "send", "enqueueSend", "(Lkotlinx/coroutines/channels/Send;)Ljava/lang/Object;", "pollInternal", "()Ljava/lang/Object;", "pollSelectInternal", "(Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/Receive;", "receive", "", "enqueueReceiveInternal", "(Lkotlinx/coroutines/channels/Receive;)Z", "wasClosed", "onCancelIdempotent", "(Z)V", "isBufferFull", "()Z", "", "buffer", "[Ljava/lang/Object;", DataCompassUtils.MODULE_TYPE_HEAD, "I", "capacity", "getCapacity", "()I", CartConstant.KEY_GLOBAL_IS_EMPTY, "", "getBufferDebugString", "()Ljava/lang/String;", "bufferDebugString", "isBufferAlwaysFull", "isBufferEmpty", "isClosedForReceive", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", JoinPoint.SYNCHRONIZATION_LOCK, "Ljava/util/concurrent/locks/ReentrantLock;", JDReactConstant.TPYE_FLAG, "isBufferAlwaysEmpty", "<init>", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public class ArrayChannel<E> extends AbstractChannel<E> {
    private Object[] buffer;
    private final int capacity;
    private int head;
    private final ReentrantLock lock;
    private volatile int size;

    public ArrayChannel(int i2) {
        this.capacity = i2;
        if (i2 >= 1) {
            this.lock = new ReentrantLock();
            this.buffer = new Object[Math.min(i2, 8)];
            this.size = 0;
            return;
        }
        throw new IllegalArgumentException(("ArrayChannel capacity must be at least 1, but " + i2 + " was specified").toString());
    }

    private final void ensureCapacity(int currentSize) {
        Object[] objArr = this.buffer;
        if (currentSize >= objArr.length) {
            Object[] objArr2 = new Object[Math.min(objArr.length * 2, this.capacity)];
            for (int i2 = 0; i2 < currentSize; i2++) {
                Object[] objArr3 = this.buffer;
                objArr2[i2] = objArr3[(this.head + i2) % objArr3.length];
            }
            this.buffer = objArr2;
            this.head = 0;
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @Nullable
    public Object enqueueSend(@NotNull Send send) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.enqueueSend(send);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    protected String getBufferDebugString() {
        return "(buffer:capacity=" + this.capacity + ",size=" + this.size + ')';
    }

    public final int getCapacity() {
        return this.capacity;
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
        return this.size == 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        return this.size == this.capacity;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.isClosedForReceive();
        } finally {
            reentrantLock.unlock();
        }
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

    @Override // kotlinx.coroutines.channels.AbstractSendChannel, kotlinx.coroutines.channels.SendChannel
    public boolean isFull() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return isFullImpl();
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r1 == 0) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001b, code lost:
        r2 = takeFirstReceiveOrPeekClosed();
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001f, code lost:
        if (r2 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0023, code lost:
        if ((r2 instanceof kotlinx.coroutines.channels.Closed@) == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
        r5.size = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0027, code lost:
        if (r2 != null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0029, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x002f, code lost:
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0030, code lost:
        if (r2 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0032, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0035, code lost:
        r3 = r2.tryResumeReceive(r6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003a, code lost:
        if (r3 == null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0040, code lost:
        if (kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0044, code lost:
        if (r3 != kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0046, code lost:
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0048, code lost:
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0049, code lost:
        if (r3 == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0051, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0052, code lost:
        r5.size = r1;
        r1 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0059, code lost:
        if (r2 != null) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x005b, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x005e, code lost:
        r2.completeResumeReceive(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0061, code lost:
        if (r2 != null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0063, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x006a, code lost:
        return r2.getOfferResult();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x006b, code lost:
        ensureCapacity(r1);
        r2 = r5.buffer;
        r2[(r5.head + r1) % r2.length] = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x007c, code lost:
        return kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS;
     */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object offerInternal(E element) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i2 = this.size;
            Closed@<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            if (i2 < this.capacity) {
                this.size = i2 + 1;
            } else {
                return AbstractChannelKt.OFFER_FAILED;
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerSelectInternal(E element, @NotNull SelectInstance<?> select) {
        Object performAtomicTrySelect;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i2 = this.size;
            Closed@<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            if (i2 < this.capacity) {
                this.size = i2 + 1;
                if (i2 == 0) {
                    do {
                        AbstractSendChannel.TryOfferDesc<E> describeTryOffer = describeTryOffer(element);
                        performAtomicTrySelect = select.performAtomicTrySelect(describeTryOffer);
                        if (performAtomicTrySelect == null) {
                            this.size = i2;
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
                    this.size = i2;
                    return performAtomicTrySelect;
                }
                if (!select.trySelect()) {
                    this.size = i2;
                    return SelectKt.getALREADY_SELECTED();
                }
                ensureCapacity(i2);
                Object[] objArr = this.buffer;
                objArr[(this.head + i2) % objArr.length] = element;
                return AbstractChannelKt.OFFER_SUCCESS;
            }
            return AbstractChannelKt.OFFER_FAILED;
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
                int i2 = this.size;
                for (int i3 = 0; i3 < i2; i3++) {
                    this.buffer[this.head] = 0;
                    this.head = (this.head + 1) % this.buffer.length;
                }
                this.size = 0;
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
            int i2 = this.size;
            if (i2 == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object[] objArr = this.buffer;
            int i3 = this.head;
            Object obj = objArr[i3];
            Send send = null;
            objArr[i3] = null;
            this.size = i2 - 1;
            Object obj2 = AbstractChannelKt.POLL_FAILED;
            if (i2 == this.capacity) {
                Send send2 = null;
                while (true) {
                    Send takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                    if (takeFirstSendOrPeekClosed == null) {
                        send = send2;
                        break;
                    }
                    if (takeFirstSendOrPeekClosed == null) {
                        Intrinsics.throwNpe();
                    }
                    Symbol tryResumeSend = takeFirstSendOrPeekClosed.tryResumeSend(null);
                    if (tryResumeSend != null) {
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if (!(tryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN)) {
                                throw new AssertionError();
                            }
                        }
                        if (takeFirstSendOrPeekClosed == null) {
                            Intrinsics.throwNpe();
                        }
                        obj2 = takeFirstSendOrPeekClosed.getPollResult();
                        send = takeFirstSendOrPeekClosed;
                        r6 = true;
                    } else {
                        send2 = takeFirstSendOrPeekClosed;
                    }
                }
            }
            if (obj2 != AbstractChannelKt.POLL_FAILED && !(obj2 instanceof Closed@)) {
                this.size = i2;
                Object[] objArr2 = this.buffer;
                objArr2[(this.head + i2) % objArr2.length] = obj2;
            }
            this.head = (this.head + 1) % this.buffer.length;
            Unit unit = Unit.INSTANCE;
            if (r6) {
                if (send == null) {
                    Intrinsics.throwNpe();
                }
                send.completeResumeSend();
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00a1 A[Catch: all -> 0x00ca, TRY_LEAVE, TryCatch #0 {all -> 0x00ca, blocks: (B:3:0x0005, B:5:0x0009, B:8:0x0010, B:11:0x0016, B:13:0x002a, B:15:0x0034, B:17:0x003d, B:18:0x0040, B:37:0x0087, B:39:0x008b, B:41:0x008f, B:47:0x00b1, B:42:0x009b, B:44:0x00a1, B:20:0x0046, B:23:0x004b, B:26:0x0050, B:28:0x0056, B:31:0x0062, B:33:0x0066, B:34:0x006b, B:35:0x0085), top: B:58:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c1  */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected Object pollSelectInternal(@NotNull SelectInstance<?> select) {
        boolean z;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i2 = this.size;
            if (i2 == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object[] objArr = this.buffer;
            int i3 = this.head;
            Object obj = objArr[i3];
            Send send = null;
            objArr[i3] = null;
            this.size = i2 - 1;
            Object obj2 = AbstractChannelKt.POLL_FAILED;
            if (i2 == this.capacity) {
                while (true) {
                    AbstractChannel.TryPollDesc<E> describeTryPoll = describeTryPoll();
                    Object performAtomicTrySelect = select.performAtomicTrySelect(describeTryPoll);
                    if (performAtomicTrySelect == null) {
                        send = describeTryPoll.getResult();
                        if (send == null) {
                            Intrinsics.throwNpe();
                        }
                        obj2 = send.getPollResult();
                    } else if (performAtomicTrySelect == AbstractChannelKt.POLL_FAILED) {
                        break;
                    } else if (performAtomicTrySelect != AtomicKt.RETRY_ATOMIC) {
                        if (performAtomicTrySelect == SelectKt.getALREADY_SELECTED()) {
                            this.size = i2;
                            this.buffer[this.head] = obj;
                            return performAtomicTrySelect;
                        } else if (performAtomicTrySelect instanceof Closed@) {
                            send = (Send) performAtomicTrySelect;
                            obj2 = performAtomicTrySelect;
                        } else {
                            throw new IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + performAtomicTrySelect).toString());
                        }
                    }
                }
                z = true;
                if (obj2 == AbstractChannelKt.POLL_FAILED && !(obj2 instanceof Closed@)) {
                    this.size = i2;
                    Object[] objArr2 = this.buffer;
                    objArr2[(this.head + i2) % objArr2.length] = obj2;
                } else if (!select.trySelect()) {
                    this.size = i2;
                    this.buffer[this.head] = obj;
                    return SelectKt.getALREADY_SELECTED();
                }
                this.head = (this.head + 1) % this.buffer.length;
                Unit unit = Unit.INSTANCE;
                if (z) {
                    if (send == null) {
                        Intrinsics.throwNpe();
                    }
                    send.completeResumeSend();
                }
                return obj;
            }
            z = false;
            if (obj2 == AbstractChannelKt.POLL_FAILED) {
            }
            if (!select.trySelect()) {
            }
            this.head = (this.head + 1) % this.buffer.length;
            Unit unit2 = Unit.INSTANCE;
            if (z) {
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }
}
