package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.Unit;
import kotlinx.coroutines.CancelHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0096\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\tR\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/sync/CancelSemaphoreAcquisitionHandler;", "Lkotlinx/coroutines/CancelHandler;", "", "cause", "", "invoke", "(Ljava/lang/Throwable;)V", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "segment", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "", "index", "I", "<init>", "(Lkotlinx/coroutines/sync/SemaphoreSegment;I)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* renamed from: kotlinx.coroutines.sync.CancelSemaphoreAcquisitionHandler  reason: from toString */
/* loaded from: classes11.dex */
final class CancelSemaphoreAcquisitionHandler[ extends CancelHandler {

    /* renamed from: index  reason: from kotlin metadata and from toString */
    private final int ;

    /* renamed from: segment  reason: from kotlin metadata and from toString */
    private final SemaphoreSegment[id= CancelSemaphoreAcquisitionHandler[;

    public CancelSemaphoreAcquisitionHandler[(@NotNull SemaphoreSegment[id= r1, int i2) {
        this.CancelSemaphoreAcquisitionHandler[ = r1;
        this. = i2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @NotNull
    public String toString() {
        return "CancelSemaphoreAcquisitionHandler[" + this.CancelSemaphoreAcquisitionHandler[ + ", " + this. + ']';
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable cause) {
        this.CancelSemaphoreAcquisitionHandler[.cancel(this.);
    }
}
