package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00060\u0002j\u0002`\u00032\u00020\u0004B'\u0012\n\u0010\u0006\u001a\u00060\u0002j\u0002`\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0015\u00a2\u0006\u0004\b!\u0010\"J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0000H\u0096\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\u00020\t8\u0016@\u0016X\u0096\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0006\u001a\u00060\u0002j\u0002`\u00038\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\u00158\u0000@\u0001X\u0081\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0017R(\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001a8\u0016@\u0016X\u0096\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 \u00a8\u0006#"}, d2 = {"Lkotlinx/coroutines/test/TimedRunnableObsolete;", "", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "", "run", "()V", "other", "", "compareTo", "(Lkotlinx/coroutines/test/TimedRunnableObsolete;)I", "", "toString", "()Ljava/lang/String;", "index", "I", "getIndex", "()I", "setIndex", "(I)V", "", "count", "J", "Ljava/lang/Runnable;", "time", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "heap", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "<init>", "(Ljava/lang/Runnable;JJ)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* renamed from: kotlinx.coroutines.test.TimedRunnableObsolete  reason: from toString */
/* loaded from: classes11.dex */
public final class TimedRunnable implements Comparable<TimedRunnable>, Runnable, ThreadSafeHeapNode {
    private final long count;
    @Nullable
    private ThreadSafeHeap<?> heap;
    private int index;
    private final Runnable run;
    @JvmField
    public final long time;

    public TimedRunnable(@NotNull Runnable runnable, long j2, long j3) {
        this.run = runnable;
        this.count = j2;
        this.time = j3;
    }

    @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
    @Nullable
    public ThreadSafeHeap<?> getHeap() {
        return this.heap;
    }

    @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
    public int getIndex() {
        return this.index;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.run.run();
    }

    @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
    public void setHeap(@Nullable ThreadSafeHeap<?> threadSafeHeap) {
        this.heap = threadSafeHeap;
    }

    @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
    public void setIndex(int i2) {
        this.index = i2;
    }

    @NotNull
    public String toString() {
        return "TimedRunnable(time=" + this.time + ", run=" + this.run + ')';
    }

    public /* synthetic */ TimedRunnable(Runnable runnable, long j2, long j3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(runnable, (i2 & 2) != 0 ? 0L : j2, (i2 & 4) != 0 ? 0L : j3);
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull TimedRunnable other) {
        long j2 = this.time;
        long j3 = other.time;
        if (j2 == j3) {
            j2 = this.count;
            j3 = other.count;
        }
        return (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
    }
}
