package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u0000*\n\b\u0000\u0010\u0002 \u0001*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/JobCancellingNode;", "Lkotlinx/coroutines/Job;", "J", "Lkotlinx/coroutines/JobNode;", "job", "<init>", "(Lkotlinx/coroutines/Job;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public abstract class JobCancellingNode<J extends Job> extends JobNode<J> {
    public JobCancellingNode(@NotNull J j2) {
        super(j2);
    }
}
