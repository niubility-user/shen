package kotlinx.coroutines.internal;

import com.jingdong.jdsdk.auraSetting.AuraConstants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u001ao\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000*\u00028\u00002\u0006\u0010\u0003\u001a\u00020\u000228\u0010\b\u001a4\u0012\u0013\u0012\u00110\u0002\u00a2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0003\u0012\u0015\u0012\u0013\u0018\u00018\u0000\u00a2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00028\u00000\u0004H\u0082\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000b\u001a#\u0010\u000e\u001a\u00028\u0000\"\u000e\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\f*\u00028\u0000H\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000f\"\u001c\u0010\u0011\u001a\u00020\u00108\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u0012\u0004\b\u0013\u0010\u0014\"\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lkotlinx/coroutines/internal/Segment;", "S", "", "id", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "prev", "createNewSegment", "Lkotlinx/coroutines/internal/SegmentOrClosed;", "findSegmentInternal", "(Lkotlinx/coroutines/internal/Segment;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", AuraConstants.MESSAGE_COUPON_TYPE_NEW, "close", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "Lkotlinx/coroutines/internal/Symbol;", "CLOSED", "Lkotlinx/coroutines/internal/Symbol;", "CLOSED$annotations", "()V", "", "POINTERS_SHIFT", "I", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class ConcurrentLinkedListKt {
    private static final Symbol CLOSED = new Symbol("CLOSED");
    private static final int POINTERS_SHIFT = 16;

    private static /* synthetic */ void CLOSED$annotations() {
    }

    public static final /* synthetic */ Symbol access$getCLOSED$p() {
        return CLOSED;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [kotlinx.coroutines.internal.ConcurrentLinkedListNode] */
    @NotNull
    public static final <N extends ConcurrentLinkedListNode<N>> N close(@NotNull N n2) {
        while (true) {
            Object obj = n2.get_next();
            if (obj == CLOSED) {
                return n2;
            }
            ?? r0 = (ConcurrentLinkedListNode) obj;
            if (r0 != 0) {
                n2 = r0;
            } else if (n2.markAsClosed()) {
                return n2;
            }
        }
    }

    public static final <S extends Segment<S>> Object findSegmentInternal(@NotNull S s, long j2, Function2<? super Long, ? super S, ? extends S> function2) {
        while (true) {
            if (s.getId() < j2 || s.getRemoved()) {
                Object obj = s.get_next();
                if (obj == CLOSED) {
                    return SegmentOrClosed.m1257constructorimpl(CLOSED);
                }
                S s2 = (S) ((ConcurrentLinkedListNode) obj);
                if (s2 == null) {
                    s2 = function2.invoke(Long.valueOf(s.getId() + 1), s);
                    if (s.trySetNext(s2)) {
                        if (s.getRemoved()) {
                            s.remove();
                        }
                    }
                }
                s = s2;
            } else {
                return SegmentOrClosed.m1257constructorimpl(s);
            }
        }
    }
}
