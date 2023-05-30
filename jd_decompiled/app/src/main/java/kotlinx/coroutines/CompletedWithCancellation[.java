package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B4\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001\u0012!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00018\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R1\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u00078\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000e\u00a8\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/CompletedWithCancellation;", "", "", "toString", "()Ljava/lang/String;", "result", "Ljava/lang/Object;", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "onCancellation", "Lkotlin/jvm/functions/Function1;", "<init>", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* renamed from: kotlinx.coroutines.CompletedWithCancellation  reason: from toString */
/* loaded from: classes11.dex */
final class CompletedWithCancellation[ {
    @JvmField
    @NotNull
    public final Function1<Throwable, Unit> onCancellation;
    @JvmField
    @Nullable

    /* renamed from: result  reason: from kotlin metadata and from toString */
    public final Object CompletedWithCancellation[;

    /* JADX WARN: Multi-variable type inference failed */
    public CompletedWithCancellation[(@Nullable Object obj, @NotNull Function1<? super Throwable, Unit> function1) {
        this.CompletedWithCancellation[ = obj;
        this.onCancellation = function1;
    }

    @NotNull
    public String toString() {
        return "CompletedWithCancellation[" + this.CompletedWithCancellation[ + ']';
    }
}
