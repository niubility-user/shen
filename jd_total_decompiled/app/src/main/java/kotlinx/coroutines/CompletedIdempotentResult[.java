package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00018\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00018\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006\u00a8\u0006\n"}, d2 = {"Lkotlinx/coroutines/CompletedIdempotentResult;", "", "", "toString", "()Ljava/lang/String;", "result", "Ljava/lang/Object;", "idempotentResume", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* renamed from: kotlinx.coroutines.CompletedIdempotentResult  reason: from toString */
/* loaded from: classes11.dex */
final class CompletedIdempotentResult[ {
    @JvmField
    @Nullable
    public final Object idempotentResume;
    @JvmField
    @Nullable

    /* renamed from: result  reason: from kotlin metadata and from toString */
    public final Object CompletedIdempotentResult[;

    public CompletedIdempotentResult[(@Nullable Object obj, @Nullable Object obj2) {
        this.idempotentResume = obj;
        this.CompletedIdempotentResult[ = obj2;
    }

    @NotNull
    public String toString() {
        return "CompletedIdempotentResult[" + this.CompletedIdempotentResult[ + ']';
    }
}
