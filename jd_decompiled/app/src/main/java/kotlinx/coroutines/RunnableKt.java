package kotlinx.coroutines;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a$\u0010\u0005\u001a\u00060\u0003j\u0002`\u00042\u000e\b\u0004\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0086\b\u00a2\u0006\u0004\b\u0005\u0010\u0006*\n\u0010\u0005\"\u00020\u00032\u00020\u0003\u00a8\u0006\u0007"}, d2 = {"Lkotlin/Function0;", "", JDReactConstant.BLOCK, "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "Runnable", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Runnable;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class RunnableKt {
    @NotNull
    public static final Runnable Runnable(@NotNull final Function0<Unit> function0) {
        return new Runnable() { // from class: kotlinx.coroutines.RunnableKt$Runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.invoke();
            }
        };
    }
}
