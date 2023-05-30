package kotlinx.coroutines;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u001a\u0019\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0001\u001a\u0004\u0018\u00010\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\u001b\u0010\u0006\u001a\u00020\u00002\n\b\u0002\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0005\u001a \u0010\u000b\u001a\u00020\n2\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0087\b\u00a2\u0006\u0004\b\u000b\u0010\f\u001a\u001b\u0010\u000e\u001a\u00020\n*\u00020\u00002\u0006\u0010\r\u001a\u00020\nH\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a\u0017\u0010\u0010\u001a\u00020\b*\u00020\u0000H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0011\u001a#\u0010\u0015\u001a\u00020\b*\u00020\u00002\u0010\b\u0002\u0010\u0014\u001a\n\u0018\u00010\u0012j\u0004\u0018\u0001`\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a\u0013\u0010\u0015\u001a\u00020\b*\u00020\u0000H\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0017\u001a\u001f\u0010\u0015\u001a\u00020\b*\u00020\u00002\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0018H\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0019\u001a#\u0010\u001b\u001a\u00020\b*\u00020\u001a2\u0010\b\u0002\u0010\u0014\u001a\n\u0018\u00010\u0012j\u0004\u0018\u0001`\u0013\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a\u0013\u0010\u001b\u001a\u00020\b*\u00020\u001aH\u0007\u00a2\u0006\u0004\b\u001b\u0010\u001d\u001a\u0011\u0010\u001e\u001a\u00020\b*\u00020\u0000\u00a2\u0006\u0004\b\u001e\u0010\u0017\u001a\u0011\u0010\u001e\u001a\u00020\b*\u00020\u001a\u00a2\u0006\u0004\b\u001e\u0010\u001d\u001a%\u0010\u001b\u001a\u00020\b*\u00020\u00002\u0006\u0010 \u001a\u00020\u001f2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0018\u00a2\u0006\u0004\b\u001b\u0010!\u001a\u001f\u0010\u001b\u001a\u00020\"*\u00020\u001a2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0018H\u0007\u00a2\u0006\u0004\b\u001b\u0010#\u001a#\u0010\u0015\u001a\u00020\b*\u00020\u001a2\u0010\b\u0002\u0010\u0014\u001a\n\u0018\u00010\u0012j\u0004\u0018\u0001`\u0013\u00a2\u0006\u0004\b\u0015\u0010\u001c\u001a\u0013\u0010\u0015\u001a\u00020\b*\u00020\u001aH\u0007\u00a2\u0006\u0004\b\u0015\u0010\u001d\u001a\u001f\u0010\u0015\u001a\u00020\b*\u00020\u001a2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0018H\u0007\u00a2\u0006\u0004\b\u0015\u0010$\u001a\u001d\u0010(\u001a\u00020\u0018*\u0004\u0018\u00010\u00182\u0006\u0010%\u001a\u00020\u0000H\u0002\u00a2\u0006\u0004\b&\u0010'\"\u0017\u0010)\u001a\u00020\"*\u00020\u001a8F@\u0006\u00a2\u0006\u0006\u001a\u0004\b)\u0010*\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006+"}, d2 = {"Lkotlinx/coroutines/Job;", "parent", "Lkotlinx/coroutines/CompletableJob;", "Job", "(Lkotlinx/coroutines/Job;)Lkotlinx/coroutines/CompletableJob;", "(Lkotlinx/coroutines/Job;)Lkotlinx/coroutines/Job;", "Job0", "Lkotlin/Function0;", "", JDReactConstant.BLOCK, "Lkotlinx/coroutines/DisposableHandle;", "DisposableHandle", "(Lkotlin/jvm/functions/Function0;)Lkotlinx/coroutines/DisposableHandle;", "handle", "disposeOnCompletion", "(Lkotlinx/coroutines/Job;Lkotlinx/coroutines/DisposableHandle;)Lkotlinx/coroutines/DisposableHandle;", "cancelAndJoin", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cause", "cancelChildren", "(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;)V", "(Lkotlinx/coroutines/Job;)V", "", "(Lkotlinx/coroutines/Job;Ljava/lang/Throwable;)V", "Lkotlin/coroutines/CoroutineContext;", "cancel", "(Lkotlin/coroutines/CoroutineContext;Ljava/util/concurrent/CancellationException;)V", "(Lkotlin/coroutines/CoroutineContext;)V", "ensureActive", "", "message", "(Lkotlinx/coroutines/Job;Ljava/lang/String;Ljava/lang/Throwable;)V", "", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Throwable;)Z", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Throwable;)V", "job", "orCancellation$JobKt__JobKt", "(Ljava/lang/Throwable;Lkotlinx/coroutines/Job;)Ljava/lang/Throwable;", "orCancellation", "isActive", "(Lkotlin/coroutines/CoroutineContext;)Z", "kotlinx-coroutines-core"}, k = 5, mv = {1, 4, 0}, xs = "kotlinx/coroutines/JobKt")
/* loaded from: classes.dex */
public final /* synthetic */ class JobKt__JobKt {
    @InternalCoroutinesApi
    @NotNull
    public static final DisposableHandle DisposableHandle(@NotNull final Function0<Unit> function0) {
        return new DisposableHandle() { // from class: kotlinx.coroutines.JobKt__JobKt$DisposableHandle$1
            @Override // kotlinx.coroutines.DisposableHandle
            public void dispose() {
                Function0.this.invoke();
            }
        };
    }

    @NotNull
    public static final CompletableJob Job(@Nullable Job job) {
        return new JobImpl(job);
    }

    public static /* synthetic */ CompletableJob Job$default(Job job, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            job = null;
        }
        return JobKt.Job(job);
    }

    public static final void cancel(@NotNull CoroutineContext coroutineContext, @Nullable CancellationException cancellationException) {
        Job job = (Job) coroutineContext.get(Job.INSTANCE);
        if (job != null) {
            job.cancel(cancellationException);
        }
    }

    public static /* synthetic */ void cancel$default(CoroutineContext coroutineContext, CancellationException cancellationException, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            cancellationException = null;
        }
        JobKt.cancel(coroutineContext, cancellationException);
    }

    @Nullable
    public static final Object cancelAndJoin(@NotNull Job job, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        Object join = job.join(continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return join == coroutine_suspended ? join : Unit.INSTANCE;
    }

    public static final void cancelChildren(@NotNull Job job, @Nullable CancellationException cancellationException) {
        Iterator<Job> it = job.getChildren().iterator();
        while (it.hasNext()) {
            it.next().cancel(cancellationException);
        }
    }

    public static /* synthetic */ void cancelChildren$default(Job job, CancellationException cancellationException, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            cancellationException = null;
        }
        JobKt.cancelChildren(job, cancellationException);
    }

    @NotNull
    public static final DisposableHandle disposeOnCompletion(@NotNull Job job, @NotNull DisposableHandle disposableHandle) {
        return job.invokeOnCompletion(new DisposeOnCompletion[(job, disposableHandle));
    }

    public static final void ensureActive(@NotNull Job job) {
        if (!job.isActive()) {
            throw job.getCancellationException();
        }
    }

    public static final boolean isActive(@NotNull CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.get(Job.INSTANCE);
        return job != null && job.isActive();
    }

    private static final Throwable orCancellation$JobKt__JobKt(@Nullable Throwable th, Job job) {
        return th != null ? th : new ; job=("Job was cancelled", null, job);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    @JvmName(name = "Job")
    @NotNull
    /* renamed from: Job */
    public static final /* synthetic */ Job m1221Job(@Nullable Job job) {
        return JobKt.Job(job);
    }

    /* renamed from: Job$default */
    public static /* synthetic */ Job m1222Job$default(Job job, int i2, Object obj) {
        Job m1221Job;
        if ((i2 & 1) != 0) {
            job = null;
        }
        m1221Job = m1221Job(job);
        return m1221Job;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final /* synthetic */ void cancel(@NotNull CoroutineContext coroutineContext) {
        JobKt.cancel(coroutineContext, (CancellationException) null);
    }

    public static /* synthetic */ void cancel$default(Job job, String str, Throwable th, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            th = null;
        }
        JobKt.cancel(job, str, th);
    }

    public static /* synthetic */ void cancelChildren$default(Job job, Throwable th, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            th = null;
        }
        cancelChildren(job, th);
    }

    public static final void ensureActive(@NotNull CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.get(Job.INSTANCE);
        if (job != null) {
            JobKt.ensureActive(job);
        }
    }

    public static final void cancel(@NotNull Job job, @NotNull String str, @Nullable Throwable th) {
        job.cancel(ExceptionsKt.CancellationException(str, th));
    }

    public static /* synthetic */ boolean cancel$default(CoroutineContext coroutineContext, Throwable th, int i2, Object obj) {
        boolean cancel;
        if ((i2 & 1) != 0) {
            th = null;
        }
        cancel = cancel(coroutineContext, th);
        return cancel;
    }

    public static /* synthetic */ void cancelChildren$default(CoroutineContext coroutineContext, CancellationException cancellationException, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            cancellationException = null;
        }
        JobKt.cancelChildren(coroutineContext, cancellationException);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final /* synthetic */ boolean cancel(@NotNull CoroutineContext coroutineContext, @Nullable Throwable th) {
        CoroutineContext.Element element = coroutineContext.get(Job.INSTANCE);
        if (!(element instanceof JobSupport)) {
            element = null;
        }
        JobSupport jobSupport = (JobSupport) element;
        if (jobSupport != null) {
            jobSupport.cancelInternal(orCancellation$JobKt__JobKt(th, jobSupport));
            return true;
        }
        return false;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final /* synthetic */ void cancelChildren(@NotNull Job job) {
        JobKt.cancelChildren(job, (CancellationException) null);
    }

    public static /* synthetic */ void cancelChildren$default(CoroutineContext coroutineContext, Throwable th, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            th = null;
        }
        cancelChildren(coroutineContext, th);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final /* synthetic */ void cancelChildren(@NotNull Job job, @Nullable Throwable th) {
        for (Job job2 : job.getChildren()) {
            if (!(job2 instanceof JobSupport)) {
                job2 = null;
            }
            JobSupport jobSupport = (JobSupport) job2;
            if (jobSupport != null) {
                jobSupport.cancelInternal(orCancellation$JobKt__JobKt(th, job));
            }
        }
    }

    public static final void cancelChildren(@NotNull CoroutineContext coroutineContext, @Nullable CancellationException cancellationException) {
        Sequence<Job> children;
        Job job = (Job) coroutineContext.get(Job.INSTANCE);
        if (job == null || (children = job.getChildren()) == null) {
            return;
        }
        Iterator<Job> it = children.iterator();
        while (it.hasNext()) {
            it.next().cancel(cancellationException);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final /* synthetic */ void cancelChildren(@NotNull CoroutineContext coroutineContext) {
        JobKt.cancelChildren(coroutineContext, (CancellationException) null);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public static final /* synthetic */ void cancelChildren(@NotNull CoroutineContext coroutineContext, @Nullable Throwable th) {
        Job job = (Job) coroutineContext.get(Job.INSTANCE);
        if (job != null) {
            for (Job job2 : job.getChildren()) {
                if (!(job2 instanceof JobSupport)) {
                    job2 = null;
                }
                JobSupport jobSupport = (JobSupport) job2;
                if (jobSupport != null) {
                    jobSupport.cancelInternal(orCancellation$JobKt__JobKt(th, job));
                }
            }
        }
    }
}
