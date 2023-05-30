package kotlinx.coroutines.internal;

import androidx.core.internal.view.SupportMenu;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlinx.coroutines.internal.Segment;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\b \u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0012\u0004\u0012\u00028\u00000\u0002B!\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\b\u0010\u0017\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0018\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u0006\u001a\u00020\u0003H\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\b\u001a\u00020\u0003H\u0000\u00a2\u0006\u0004\b\u0007\u0010\u0005J\r\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bR\u0016\u0010\r\u001a\u00020\u00038V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\u0005R\u0019\u0010\u000f\u001a\u00020\u000e8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0016\u001a\u00020\u00138&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\u001b"}, d2 = {"Lkotlinx/coroutines/internal/Segment;", "S", "Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "", "tryIncPointers$kotlinx_coroutines_core", "()Z", "tryIncPointers", "decPointers$kotlinx_coroutines_core", "decPointers", "", "onSlotCleaned", "()V", "getRemoved", "removed", "", "id", "J", "getId", "()J", "", "getMaxSlots", "()I", "maxSlots", "prev", "pointers", "<init>", "(JLkotlinx/coroutines/internal/Segment;I)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public abstract class Segment<S extends Segment<S>> extends ConcurrentLinkedListNode<S> {
    private static final AtomicIntegerFieldUpdater cleanedAndPointers$FU = AtomicIntegerFieldUpdater.newUpdater(Segment.class, "cleanedAndPointers");
    private volatile int cleanedAndPointers;
    private final long id;

    public Segment(long j2, @Nullable S s, int i2) {
        super(s);
        this.id = j2;
        this.cleanedAndPointers = i2 << 16;
    }

    public final boolean decPointers$kotlinx_coroutines_core() {
        return cleanedAndPointers$FU.addAndGet(this, SupportMenu.CATEGORY_MASK) == getMaxSlots() && !isTail();
    }

    public final long getId() {
        return this.id;
    }

    public abstract int getMaxSlots();

    @Override // kotlinx.coroutines.internal.ConcurrentLinkedListNode
    public boolean getRemoved() {
        return this.cleanedAndPointers == getMaxSlots() && !isTail();
    }

    public final void onSlotCleaned() {
        if (cleanedAndPointers$FU.incrementAndGet(this) != getMaxSlots() || isTail()) {
            return;
        }
        remove();
    }

    public final boolean tryIncPointers$kotlinx_coroutines_core() {
        int i2;
        do {
            i2 = this.cleanedAndPointers;
            if (!(i2 != getMaxSlots() || isTail())) {
                return false;
            }
        } while (!cleanedAndPointers$FU.compareAndSet(this, i2, 65536 + i2));
        return true;
    }
}
