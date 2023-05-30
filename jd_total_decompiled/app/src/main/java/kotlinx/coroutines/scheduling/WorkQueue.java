package kotlinx.coroutines.scheduling;

import com.jingdong.common.apkcenter.ApkDownloadTable;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlinx.coroutines.DebugKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b(\u0010)J\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0010\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0013\u001a\u00020\u0012*\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0011J!\u0010\u0017\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001aJ\u0015\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\f\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0016\u0010\"\u001a\u00020\u001f8@@\u0000X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b \u0010!R\u0016\u0010$\u001a\u00020\u001f8@@\u0000X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b#\u0010!R\u001e\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020%8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010'\u00a8\u0006*"}, d2 = {"Lkotlinx/coroutines/scheduling/WorkQueue;", "", "Lkotlinx/coroutines/scheduling/Task;", "task", "addLast", "(Lkotlinx/coroutines/scheduling/Task;)Lkotlinx/coroutines/scheduling/Task;", "victim", "", "blockingOnly", "", "tryStealLastScheduled", "(Lkotlinx/coroutines/scheduling/WorkQueue;Z)J", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "queue", "pollTo", "(Lkotlinx/coroutines/scheduling/GlobalQueue;)Z", "pollBuffer", "()Lkotlinx/coroutines/scheduling/Task;", "", "decrementIfBlocking", "(Lkotlinx/coroutines/scheduling/Task;)V", "poll", "fair", "add", "(Lkotlinx/coroutines/scheduling/Task;Z)Lkotlinx/coroutines/scheduling/Task;", "tryStealFrom", "(Lkotlinx/coroutines/scheduling/WorkQueue;)J", "tryStealBlockingFrom", "globalQueue", "offloadAllWorkTo", "(Lkotlinx/coroutines/scheduling/GlobalQueue;)V", "", "getBufferSize$kotlinx_coroutines_core", "()I", "bufferSize", "getSize$kotlinx_coroutines_core", ApkDownloadTable.FIELD_SIZE, "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "buffer", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "<init>", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class WorkQueue {
    private static final AtomicReferenceFieldUpdater lastScheduledTask$FU = AtomicReferenceFieldUpdater.newUpdater(WorkQueue.class, Object.class, "lastScheduledTask");
    private static final AtomicIntegerFieldUpdater producerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "producerIndex");
    private static final AtomicIntegerFieldUpdater consumerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "consumerIndex");
    private static final AtomicIntegerFieldUpdater blockingTasksInBuffer$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "blockingTasksInBuffer");
    private final AtomicReferenceArray<Task> buffer = new AtomicReferenceArray<>(128);
    private volatile Object lastScheduledTask = null;
    private volatile int producerIndex = 0;
    private volatile int consumerIndex = 0;
    private volatile int blockingTasksInBuffer = 0;

    public static /* synthetic */ Task add$default(WorkQueue workQueue, Task task, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return workQueue.add(task, z);
    }

    private final Task addLast(Task task) {
        if (task.taskContext.getTaskMode() == 1) {
            blockingTasksInBuffer$FU.incrementAndGet(this);
        }
        if (getBufferSize$kotlinx_coroutines_core() == 127) {
            return task;
        }
        int i2 = this.producerIndex & 127;
        while (this.buffer.get(i2) != null) {
            Thread.yield();
        }
        this.buffer.lazySet(i2, task);
        producerIndex$FU.incrementAndGet(this);
        return null;
    }

    private final void decrementIfBlocking(@Nullable Task task) {
        if (task != null) {
            if (task.taskContext.getTaskMode() == 1) {
                int decrementAndGet = blockingTasksInBuffer$FU.decrementAndGet(this);
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(decrementAndGet >= 0)) {
                        throw new AssertionError();
                    }
                }
            }
        }
    }

    private final Task pollBuffer() {
        Task andSet;
        while (true) {
            int i2 = this.consumerIndex;
            if (i2 - this.producerIndex == 0) {
                return null;
            }
            int i3 = i2 & 127;
            if (consumerIndex$FU.compareAndSet(this, i2, i2 + 1) && (andSet = this.buffer.getAndSet(i3, null)) != null) {
                decrementIfBlocking(andSet);
                return andSet;
            }
        }
    }

    private final boolean pollTo(GlobalQueue queue) {
        Task pollBuffer = pollBuffer();
        if (pollBuffer != null) {
            queue.addLast(pollBuffer);
            return true;
        }
        return false;
    }

    private final long tryStealLastScheduled(WorkQueue victim, boolean blockingOnly) {
        Task task;
        do {
            task = (Task) victim.lastScheduledTask;
            if (task == null) {
                return -2L;
            }
            if (blockingOnly) {
                if (!(task.taskContext.getTaskMode() == 1)) {
                    return -2L;
                }
            }
            long nanoTime = TasksKt.schedulerTimeSource.nanoTime() - task.submissionTime;
            long j2 = TasksKt.WORK_STEALING_TIME_RESOLUTION_NS;
            if (nanoTime < j2) {
                return j2 - nanoTime;
            }
        } while (!lastScheduledTask$FU.compareAndSet(victim, task, null));
        add$default(this, task, false, 2, null);
        return -1L;
    }

    @Nullable
    public final Task add(@NotNull Task task, boolean fair) {
        if (fair) {
            return addLast(task);
        }
        Task task2 = (Task) lastScheduledTask$FU.getAndSet(this, task);
        if (task2 != null) {
            return addLast(task2);
        }
        return null;
    }

    public final int getBufferSize$kotlinx_coroutines_core() {
        return this.producerIndex - this.consumerIndex;
    }

    public final int getSize$kotlinx_coroutines_core() {
        return this.lastScheduledTask != null ? getBufferSize$kotlinx_coroutines_core() + 1 : getBufferSize$kotlinx_coroutines_core();
    }

    public final void offloadAllWorkTo(@NotNull GlobalQueue globalQueue) {
        Task task = (Task) lastScheduledTask$FU.getAndSet(this, null);
        if (task != null) {
            globalQueue.addLast(task);
        }
        do {
        } while (pollTo(globalQueue));
    }

    @Nullable
    public final Task poll() {
        Task task = (Task) lastScheduledTask$FU.getAndSet(this, null);
        return task != null ? task : pollBuffer();
    }

    public final long tryStealBlockingFrom(@NotNull WorkQueue victim) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getBufferSize$kotlinx_coroutines_core() == 0)) {
                throw new AssertionError();
            }
        }
        int i2 = victim.producerIndex;
        AtomicReferenceArray<Task> atomicReferenceArray = victim.buffer;
        for (int i3 = victim.consumerIndex; i3 != i2; i3++) {
            int i4 = i3 & 127;
            if (victim.blockingTasksInBuffer == 0) {
                break;
            }
            Task task = atomicReferenceArray.get(i4);
            if (task != null) {
                if ((task.taskContext.getTaskMode() == 1) && atomicReferenceArray.compareAndSet(i4, task, null)) {
                    blockingTasksInBuffer$FU.decrementAndGet(victim);
                    add$default(this, task, false, 2, null);
                    return -1L;
                }
            }
        }
        return tryStealLastScheduled(victim, true);
    }

    public final long tryStealFrom(@NotNull WorkQueue victim) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getBufferSize$kotlinx_coroutines_core() == 0)) {
                throw new AssertionError();
            }
        }
        Task pollBuffer = victim.pollBuffer();
        if (pollBuffer != null) {
            Task add$default = add$default(this, pollBuffer, false, 2, null);
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (add$default == null) {
                    return -1L;
                }
                throw new AssertionError();
            }
            return -1L;
        }
        return tryStealLastScheduled(victim, false);
    }
}
