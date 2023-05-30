package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\u001a\u0017\u0010\u0001\u001a\u0004\u0018\u00010\u0000*\u0004\u0018\u00010\u0000H\u0000\u00a2\u0006\u0004\b\u0001\u0010\u0002\u001a\u0017\u0010\u0003\u001a\u0004\u0018\u00010\u0000*\u0004\u0018\u00010\u0000H\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0002\"\u001c\u0010\u0005\u001a\u00020\u00048\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0006\u0012\u0004\b\u0007\u0010\b\"\u001c\u0010\n\u001a\u00020\t8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u0012\u0004\b\f\u0010\b\"\u001c\u0010\r\u001a\u00020\u00048\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\r\u0010\u0006\u0012\u0004\b\u000e\u0010\b\"\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011\"\u001c\u0010\u0012\u001a\u00020\t8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u0012\u0010\u000b\u0012\u0004\b\u0013\u0010\b\"\u0016\u0010\u0014\u001a\u00020\u000f8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0011\"\u001c\u0010\u0015\u001a\u00020\t8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u0015\u0010\u000b\u0012\u0004\b\u0016\u0010\b\"\u001c\u0010\u0017\u001a\u00020\t8\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u0017\u0010\u000b\u0012\u0004\b\u0018\u0010\b\"\u001c\u0010\u0019\u001a\u00020\t8\u0000@\u0001X\u0081\u0004\u00a2\u0006\f\n\u0004\b\u0019\u0010\u000b\u0012\u0004\b\u001a\u0010\b\"\u0016\u0010\u001b\u001a\u00020\u000f8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0011\u00a8\u0006\u001c"}, d2 = {"", "boxIncomplete", "(Ljava/lang/Object;)Ljava/lang/Object;", "unboxState", "Lkotlinx/coroutines/Empty;", "EMPTY_NEW", "Lkotlinx/coroutines/Empty;", "EMPTY_NEW$annotations", "()V", "Lkotlinx/coroutines/internal/Symbol;", "TOO_LATE_TO_CANCEL", "Lkotlinx/coroutines/internal/Symbol;", "TOO_LATE_TO_CANCEL$annotations", "EMPTY_ACTIVE", "EMPTY_ACTIVE$annotations", "", "RETRY", "I", "COMPLETING_RETRY", "COMPLETING_RETRY$annotations", "TRUE", "SEALED", "SEALED$annotations", "COMPLETING_ALREADY", "COMPLETING_ALREADY$annotations", "COMPLETING_WAITING_CHILDREN", "COMPLETING_WAITING_CHILDREN$annotations", "FALSE", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class JobSupportKt {
    private static final int FALSE = 0;
    private static final int RETRY = -1;
    private static final int TRUE = 1;
    private static final Symbol COMPLETING_ALREADY = new Symbol("COMPLETING_ALREADY");
    @JvmField
    @NotNull
    public static final Symbol COMPLETING_WAITING_CHILDREN = new Symbol("COMPLETING_WAITING_CHILDREN");
    private static final Symbol COMPLETING_RETRY = new Symbol("COMPLETING_RETRY");
    private static final Symbol TOO_LATE_TO_CANCEL = new Symbol("TOO_LATE_TO_CANCEL");
    private static final Symbol SEALED = new Symbol("SEALED");
    private static final Empty EMPTY_NEW = new Empty(false);
    private static final Empty EMPTY_ACTIVE = new Empty(true);

    private static /* synthetic */ void COMPLETING_ALREADY$annotations() {
    }

    private static /* synthetic */ void COMPLETING_RETRY$annotations() {
    }

    public static /* synthetic */ void COMPLETING_WAITING_CHILDREN$annotations() {
    }

    private static /* synthetic */ void EMPTY_ACTIVE$annotations() {
    }

    private static /* synthetic */ void EMPTY_NEW$annotations() {
    }

    private static /* synthetic */ void SEALED$annotations() {
    }

    private static /* synthetic */ void TOO_LATE_TO_CANCEL$annotations() {
    }

    @Nullable
    public static final Object boxIncomplete(@Nullable Object obj) {
        return obj instanceof Incomplete ? new IncompleteStateBox((Incomplete) obj) : obj;
    }

    @Nullable
    public static final Object unboxState(@Nullable Object obj) {
        Incomplete incomplete;
        IncompleteStateBox incompleteStateBox = (IncompleteStateBox) (!(obj instanceof IncompleteStateBox) ? null : obj);
        return (incompleteStateBox == null || (incomplete = incompleteStateBox.state) == null) ? obj : incomplete;
    }
}
