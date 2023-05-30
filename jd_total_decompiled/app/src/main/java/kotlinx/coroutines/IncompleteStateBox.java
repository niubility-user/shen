package kotlinx.coroutines;

import com.jingdong.common.XView2.common.XView2Constants;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/IncompleteStateBox;", "", "Lkotlinx/coroutines/Incomplete;", XView2Constants.STATE, "Lkotlinx/coroutines/Incomplete;", "<init>", "(Lkotlinx/coroutines/Incomplete;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
final class IncompleteStateBox {
    @JvmField
    @NotNull
    public final Incomplete state;

    public IncompleteStateBox(@NotNull Incomplete incomplete) {
        this.state = incomplete;
    }
}
