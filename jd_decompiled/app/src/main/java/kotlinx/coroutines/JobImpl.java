package kotlinx.coroutines;

import jpbury.t;
import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0004\u001a\u00020\u0003H\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0005J\u0017\u0010\t\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\u00020\u00038\u0010@\u0010X\u0090\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u000b\u001a\u0004\b\f\u0010\u0005R\u0016\u0010\u000e\u001a\u00020\u00038P@\u0010X\u0090\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u0005\u00a8\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/JobImpl;", "Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/CompletableJob;", "", "handlesException", "()Z", "complete", "", t.f20145j, "completeExceptionally", "(Ljava/lang/Throwable;)Z", "Z", "getHandlesException$kotlinx_coroutines_core", "getOnCancelComplete$kotlinx_coroutines_core", "onCancelComplete", "Lkotlinx/coroutines/Job;", "parent", "<init>", "(Lkotlinx/coroutines/Job;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public class JobImpl extends JobSupport implements CompletableJob {
    private final boolean handlesException;

    public JobImpl(@Nullable Job job) {
        super(true);
        initParentJobInternal$kotlinx_coroutines_core(job);
        this.handlesException = handlesException();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r0.getHandlesException() == false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001b, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x001d, code lost:
        r0 = r0.getParentHandle$kotlinx_coroutines_core();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0023, code lost:
        if ((r0 instanceof kotlinx.coroutines.ChildHandle[) != false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0026, code lost:
        r0 = (kotlinx.coroutines.ChildHandle[) r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0028, code lost:
        if (r0 == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002a, code lost:
        r0 = (kotlinx.coroutines.JobSupport) r0.job;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x002e, code lost:
        if (r0 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r0 != null) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean handlesException() {
        /*
            r4 = this;
            kotlinx.coroutines.ChildHandle r0 = r4.getParentHandle$kotlinx_coroutines_core()
            boolean r1 = r0 instanceof kotlinx.coroutines.ChildHandle[
            r2 = 0
            if (r1 != 0) goto La
            r0 = r2
        La:
            kotlinx.coroutines.ChildHandleNode r0 = (kotlinx.coroutines.ChildHandle[) r0
            r1 = 0
            if (r0 == 0) goto L31
            J extends kotlinx.coroutines.Job r0 = r0.job
            kotlinx.coroutines.JobSupport r0 = (kotlinx.coroutines.JobSupport) r0
            if (r0 == 0) goto L31
        L15:
            boolean r3 = r0.getHandlesException()
            if (r3 == 0) goto L1d
            r0 = 1
            return r0
        L1d:
            kotlinx.coroutines.ChildHandle r0 = r0.getParentHandle$kotlinx_coroutines_core()
            boolean r3 = r0 instanceof kotlinx.coroutines.ChildHandle[
            if (r3 != 0) goto L26
            r0 = r2
        L26:
            kotlinx.coroutines.ChildHandleNode r0 = (kotlinx.coroutines.ChildHandle[) r0
            if (r0 == 0) goto L31
            J extends kotlinx.coroutines.Job r0 = r0.job
            kotlinx.coroutines.JobSupport r0 = (kotlinx.coroutines.JobSupport) r0
            if (r0 == 0) goto L31
            goto L15
        L31:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobImpl.handlesException():boolean");
    }

    @Override // kotlinx.coroutines.CompletableJob
    public boolean complete() {
        return makeCompleting$kotlinx_coroutines_core(Unit.INSTANCE);
    }

    @Override // kotlinx.coroutines.CompletableJob
    public boolean completeExceptionally(@NotNull Throwable exception) {
        return makeCompleting$kotlinx_coroutines_core(new CompletedExceptionally(exception, false, 2, null));
    }

    @Override // kotlinx.coroutines.JobSupport
    /* renamed from: getHandlesException$kotlinx_coroutines_core  reason: from getter */
    public boolean getHandlesException() {
        return this.handlesException;
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return true;
    }
}
