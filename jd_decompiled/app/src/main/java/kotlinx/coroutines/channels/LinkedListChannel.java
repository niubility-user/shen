package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00028\u0000H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0006J#\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00028\u00002\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0014\u00a2\u0006\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u000b8D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\u00020\u000b8D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u000f\u001a\u00020\u000b8D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0016\u0010\u0010\u001a\u00020\u000b8D@\u0004X\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\r\u00a8\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/channels/LinkedListChannel;", "E", "Lkotlinx/coroutines/channels/AbstractChannel;", "element", "", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "", "isBufferFull", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferAlwaysEmpty", "<init>", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public class LinkedListChannel<E> extends AbstractChannel<E> {
    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean isBufferAlwaysEmpty() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean isBufferAlwaysFull() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferEmpty() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerInternal(E element) {
        ReceiveOrClosed<?> sendBuffered;
        do {
            Object offerInternal = super.offerInternal(element);
            Object obj = AbstractChannelKt.OFFER_SUCCESS;
            if (offerInternal == obj) {
                return obj;
            }
            if (offerInternal == AbstractChannelKt.OFFER_FAILED) {
                sendBuffered = sendBuffered(element);
                if (sendBuffered == null) {
                    return obj;
                }
            } else if (offerInternal instanceof Closed@) {
                return offerInternal;
            } else {
                throw new IllegalStateException(("Invalid offerInternal result " + offerInternal).toString());
            }
        } while (!(sendBuffered instanceof Closed@));
        return sendBuffered;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerSelectInternal(E element, @NotNull SelectInstance<?> select) {
        Object performAtomicTrySelect;
        while (true) {
            if (getHasReceiveOrClosed()) {
                performAtomicTrySelect = super.offerSelectInternal(element, select);
            } else {
                performAtomicTrySelect = select.performAtomicTrySelect(describeSendBuffered(element));
                if (performAtomicTrySelect == null) {
                    performAtomicTrySelect = AbstractChannelKt.OFFER_SUCCESS;
                }
            }
            if (performAtomicTrySelect == SelectKt.getALREADY_SELECTED()) {
                return SelectKt.getALREADY_SELECTED();
            }
            Object obj = AbstractChannelKt.OFFER_SUCCESS;
            if (performAtomicTrySelect == obj) {
                return obj;
            }
            if (performAtomicTrySelect != AbstractChannelKt.OFFER_FAILED && performAtomicTrySelect != AtomicKt.RETRY_ATOMIC) {
                if (performAtomicTrySelect instanceof Closed@) {
                    return performAtomicTrySelect;
                }
                throw new IllegalStateException(("Invalid result " + performAtomicTrySelect).toString());
            }
        }
    }
}
