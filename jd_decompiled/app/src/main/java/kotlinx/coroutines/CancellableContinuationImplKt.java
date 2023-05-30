package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0016\u0010\u0001\u001a\u00020\u00008\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0001\u0010\u0002\"\u0016\u0010\u0003\u001a\u00020\u00008\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0002\"\u0016\u0010\u0004\u001a\u00020\u00008\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0002\"\u001c\u0010\u0006\u001a\u00020\u00058\u0000@\u0001X\u0081\u0004\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0007\u0012\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"", "UNDECIDED", "I", DebugCoroutineInfoImplKt.SUSPENDED, "RESUMED", "Lkotlinx/coroutines/internal/Symbol;", "RESUME_TOKEN", "Lkotlinx/coroutines/internal/Symbol;", "RESUME_TOKEN$annotations", "()V", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class CancellableContinuationImplKt {
    private static final int RESUMED = 2;
    @JvmField
    @NotNull
    public static final Symbol RESUME_TOKEN = new Symbol("RESUME_TOKEN");
    private static final int SUSPENDED = 1;
    private static final int UNDECIDED = 0;

    public static /* synthetic */ void RESUME_TOKEN$annotations() {
    }
}
