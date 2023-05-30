package kotlinx.coroutines.sync;

import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B!\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u001c\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0086\b\u00a2\u0006\u0004\b\u0005\u0010\u0006J\"\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0086\b\u00a2\u0006\u0004\b\t\u0010\nJ,\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0086\b\u00a2\u0006\u0004\b\r\u0010\u000eJ$\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0086\b\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0018\u001a\u00020\u00028V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u001f"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreSegment;", "Lkotlinx/coroutines/internal/Segment;", "", "index", "", IMantoServerRequester.GET, "(I)Ljava/lang/Object;", "value", "", "set", "(ILjava/lang/Object;)V", "expected", "", "cas", "(ILjava/lang/Object;Ljava/lang/Object;)Z", "getAndSet", "(ILjava/lang/Object;)Ljava/lang/Object;", "cancel", "(I)V", "", "toString", "()Ljava/lang/String;", "getMaxSlots", "()I", "maxSlots", "", "id", "prev", "pointers", "<init>", "(JLkotlinx/coroutines/sync/SemaphoreSegment;I)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* renamed from: kotlinx.coroutines.sync.SemaphoreSegment  reason: from toString */
/* loaded from: classes11.dex */
public final class SemaphoreSegment[id= extends Segment<SemaphoreSegment[id=> {
    @NotNull
    AtomicReferenceArray acquirers;

    public SemaphoreSegment[id=(long j2, @Nullable SemaphoreSegment[id= r3, int i2) {
        super(j2, r3, i2);
        int i3;
        i3 = SemaphoreKt.SEGMENT_SIZE;
        this.acquirers = new AtomicReferenceArray(i3);
    }

    public final void cancel(int index) {
        Symbol symbol;
        symbol = SemaphoreKt.CANCELLED;
        this.acquirers.set(index, symbol);
        onSlotCleaned();
    }

    public final boolean cas(int index, @Nullable Object expected, @Nullable Object value) {
        return this.acquirers.compareAndSet(index, expected, value);
    }

    @Nullable
    public final Object get(int index) {
        return this.acquirers.get(index);
    }

    @Nullable
    public final Object getAndSet(int index, @Nullable Object value) {
        return this.acquirers.getAndSet(index, value);
    }

    @Override // kotlinx.coroutines.internal.Segment
    public int getMaxSlots() {
        int i2;
        i2 = SemaphoreKt.SEGMENT_SIZE;
        return i2;
    }

    public final void set(int index, @Nullable Object value) {
        this.acquirers.set(index, value);
    }

    @NotNull
    public String toString() {
        return "SemaphoreSegment[id=" + getId() + ", hashCode=" + hashCode() + ']';
    }
}
