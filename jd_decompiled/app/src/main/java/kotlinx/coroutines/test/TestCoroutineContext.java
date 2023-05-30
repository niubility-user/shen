package kotlinx.coroutines.test;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.test.TestCoroutineContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated(level = DeprecationLevel.WARNING, message = "This API has been deprecated to integrate with Structured Concurrency.", replaceWith = @ReplaceWith(expression = "TestCoroutineScope", imports = {"kotlin.coroutines.test"}))
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001:\u0001NB\u0013\u0012\n\b\u0002\u0010I\u001a\u0004\u0018\u00010*\u00a2\u0006\u0004\bL\u0010MJ\u001b\u0010\u0006\u001a\u00020\u00052\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J#\u0010\u000b\u001a\u00020\n2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J7\u0010\u0017\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u0013\u001a\u00028\u00002\u0018\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00028\u00000\u0014H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J*\u0010\u001c\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0019*\u00020\u00152\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0096\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001e\u001a\u00020\u00012\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010\"\u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020 \u00a2\u0006\u0004\b\"\u0010#J\u001f\u0010$\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020 \u00a2\u0006\u0004\b$\u0010%J\u001f\u0010&\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020 \u00a2\u0006\u0004\b&\u0010'J\r\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0010\u0010(J\r\u0010)\u001a\u00020\u0005\u00a2\u0006\u0004\b)\u0010(J+\u00100\u001a\u00020\u00052\b\b\u0002\u0010+\u001a\u00020*2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020.0,\u00a2\u0006\u0004\b0\u00101J+\u00102\u001a\u00020\u00052\b\b\u0002\u0010+\u001a\u00020*2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020.0,\u00a2\u0006\u0004\b2\u00101J+\u00103\u001a\u00020\u00052\b\b\u0002\u0010+\u001a\u00020*2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020.0,\u00a2\u0006\u0004\b3\u00101J1\u00105\u001a\u00020\u00052\b\b\u0002\u0010+\u001a\u00020*2\u0018\u0010/\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020-04\u0012\u0004\u0012\u00020.0,\u00a2\u0006\u0004\b5\u00101J\u000f\u00106\u001a\u00020*H\u0016\u00a2\u0006\u0004\b6\u00107R\u001a\u00109\u001a\u000608R\u00020\u00008\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0016\u0010<\u001a\u00020;8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0019\u0010@\u001a\b\u0012\u0004\u0012\u00020-048F@\u0006\u00a2\u0006\u0006\u001a\u0004\b>\u0010?R\u001c\u0010B\u001a\b\u0012\u0004\u0012\u00020-0A8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u001c\u0010E\u001a\b\u0012\u0004\u0012\u00020\n0D8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010G\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bG\u0010HR\u0018\u0010I\u001a\u0004\u0018\u00010*8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010H\u00a8\u0006O"}, d2 = {"Lkotlinx/coroutines/test/TestCoroutineContext;", "Lkotlin/coroutines/CoroutineContext;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", JDReactConstant.BLOCK, "", "enqueue", "(Ljava/lang/Runnable;)V", "", "delayTime", "Lkotlinx/coroutines/test/TimedRunnableObsolete;", "postDelayed", "(Ljava/lang/Runnable;J)Lkotlinx/coroutines/test/TimedRunnableObsolete;", "processNextEvent", "()J", "targetTime", "triggerActions", "(J)V", "R", "initial", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "operation", "fold", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "E", "Lkotlin/coroutines/CoroutineContext$Key;", "key", IMantoServerRequester.GET, "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext;", "Ljava/util/concurrent/TimeUnit;", "unit", "now", "(Ljava/util/concurrent/TimeUnit;)J", "advanceTimeBy", "(JLjava/util/concurrent/TimeUnit;)J", "advanceTimeTo", "(JLjava/util/concurrent/TimeUnit;)V", "()V", "cancelAllActions", "", "message", "Lkotlin/Function1;", "", "", "predicate", "assertUnhandledException", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "assertAllUnhandledExceptions", "assertAnyUnhandledException", "", "assertExceptions", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/test/TestCoroutineContext$Dispatcher;", "ctxDispatcher", "Lkotlinx/coroutines/test/TestCoroutineContext$Dispatcher;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "ctxHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "getExceptions", "()Ljava/util/List;", "exceptions", "", "uncaughtExceptions", "Ljava/util/List;", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "queue", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "counter", "J", "name", "Ljava/lang/String;", "time", "<init>", "(Ljava/lang/String;)V", "Dispatcher", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TestCoroutineContext implements CoroutineContext {
    private long counter;
    private final Dispatcher ctxDispatcher;
    private final CoroutineExceptionHandler ctxHandler;
    private final String name;
    private final ThreadSafeHeap<TimedRunnable> queue;
    private long time;
    private final List<Throwable> uncaughtExceptions;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\u00a2\u0006\u0004\b\u001c\u0010\u001dJ#\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00032\n\u0010\u0007\u001a\u00060\u0005j\u0002`\u0006H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ%\u0010\u0012\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0010H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J#\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u000e2\n\u0010\u0007\u001a\u00060\u0005j\u0002`\u0006H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u000eH\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/test/TestCoroutineContext$Dispatcher;", "Lkotlinx/coroutines/EventLoop;", "Lkotlinx/coroutines/Delay;", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", JDReactConstant.BLOCK, "", "dispatch", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)V", "", "shouldBeProcessedFromContext", "()Z", "", "timeMillis", "Lkotlinx/coroutines/CancellableContinuation;", "continuation", "scheduleResumeAfterDelay", "(JLkotlinx/coroutines/CancellableContinuation;)V", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnTimeout", "(JLjava/lang/Runnable;)Lkotlinx/coroutines/DisposableHandle;", "processNextEvent", "()J", "", "toString", "()Ljava/lang/String;", "<init>", "(Lkotlinx/coroutines/test/TestCoroutineContext;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    private final class Dispatcher extends EventLoop implements Delay {
        public Dispatcher() {
            EventLoop.incrementUseCount$default(this, false, 1, null);
        }

        @Override // kotlinx.coroutines.Delay
        @Nullable
        public Object delay(long j2, @NotNull Continuation<? super Unit> continuation) {
            return Delay.DefaultImpls.delay(this, j2, continuation);
        }

        @Override // kotlinx.coroutines.CoroutineDispatcher
        /* renamed from: dispatch */
        public void mo1254dispatch(@NotNull CoroutineContext context, @NotNull Runnable block) {
            TestCoroutineContext.this.enqueue(block);
        }

        @Override // kotlinx.coroutines.Delay
        @NotNull
        public DisposableHandle invokeOnTimeout(long timeMillis, @NotNull Runnable block) {
            final TimedRunnable postDelayed = TestCoroutineContext.this.postDelayed(block, timeMillis);
            return new DisposableHandle() { // from class: kotlinx.coroutines.test.TestCoroutineContext$Dispatcher$invokeOnTimeout$1
                @Override // kotlinx.coroutines.DisposableHandle
                public void dispose() {
                    ThreadSafeHeap threadSafeHeap;
                    threadSafeHeap = TestCoroutineContext.this.queue;
                    threadSafeHeap.remove(postDelayed);
                }
            };
        }

        @Override // kotlinx.coroutines.EventLoop
        public long processNextEvent() {
            return TestCoroutineContext.this.processNextEvent();
        }

        @Override // kotlinx.coroutines.Delay
        /* renamed from: scheduleResumeAfterDelay */
        public void mo1255scheduleResumeAfterDelay(long timeMillis, @NotNull final CancellableContinuation<? super Unit> continuation) {
            TestCoroutineContext.this.postDelayed(new Runnable() { // from class: kotlinx.coroutines.test.TestCoroutineContext$Dispatcher$scheduleResumeAfterDelay$$inlined$Runnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    continuation.resumeUndispatched(TestCoroutineContext.Dispatcher.this, Unit.INSTANCE);
                }
            }, timeMillis);
        }

        @Override // kotlinx.coroutines.EventLoop
        public boolean shouldBeProcessedFromContext() {
            return true;
        }

        @Override // kotlinx.coroutines.CoroutineDispatcher
        @NotNull
        public String toString() {
            return "Dispatcher(" + TestCoroutineContext.this + ')';
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.jvm.internal.DefaultConstructorMarker, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public TestCoroutineContext() {
        /*
            r2 = this;
            r0 = 0
            r1 = 1
            r2.<init>(r0, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.test.TestCoroutineContext.<init>():void");
    }

    public TestCoroutineContext(@Nullable String str) {
        this.name = str;
        this.uncaughtExceptions = new ArrayList();
        this.ctxDispatcher = new Dispatcher();
        this.ctxHandler = new TestCoroutineContext$$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE, this);
        this.queue = new ThreadSafeHeap<>();
    }

    public static /* synthetic */ long advanceTimeBy$default(TestCoroutineContext testCoroutineContext, long j2, TimeUnit timeUnit, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        return testCoroutineContext.advanceTimeBy(j2, timeUnit);
    }

    public static /* synthetic */ void advanceTimeTo$default(TestCoroutineContext testCoroutineContext, long j2, TimeUnit timeUnit, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        testCoroutineContext.advanceTimeTo(j2, timeUnit);
    }

    public static /* synthetic */ void assertAllUnhandledExceptions$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertAllUnhandledExceptions(str, function1);
    }

    public static /* synthetic */ void assertAnyUnhandledException$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertAnyUnhandledException(str, function1);
    }

    public static /* synthetic */ void assertExceptions$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertExceptions(str, function1);
    }

    public static /* synthetic */ void assertUnhandledException$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertUnhandledException(str, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enqueue(Runnable block) {
        ThreadSafeHeap<TimedRunnable> threadSafeHeap = this.queue;
        long j2 = this.counter;
        this.counter = 1 + j2;
        threadSafeHeap.addLast(new TimedRunnable(block, j2, 0L, 4, null));
    }

    public static /* synthetic */ long now$default(TestCoroutineContext testCoroutineContext, TimeUnit timeUnit, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        return testCoroutineContext.now(timeUnit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TimedRunnable postDelayed(Runnable block, long delayTime) {
        long j2 = this.counter;
        this.counter = 1 + j2;
        TimedRunnable timedRunnable = new TimedRunnable(block, j2, this.time + TimeUnit.MILLISECONDS.toNanos(delayTime));
        this.queue.addLast(timedRunnable);
        return timedRunnable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long processNextEvent() {
        TimedRunnable peek = this.queue.peek();
        if (peek != null) {
            triggerActions(peek.time);
        }
        return this.queue.isEmpty() ? Long.MAX_VALUE : 0L;
    }

    public final long advanceTimeBy(long delayTime, @NotNull TimeUnit unit) {
        long j2 = this.time;
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        advanceTimeTo(unit.toNanos(delayTime) + j2, timeUnit);
        return unit.convert(this.time - j2, timeUnit);
    }

    public final void advanceTimeTo(long targetTime, @NotNull TimeUnit unit) {
        long nanos = unit.toNanos(targetTime);
        triggerActions(nanos);
        if (nanos > this.time) {
            this.time = nanos;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void assertAllUnhandledExceptions(@NotNull String message, @NotNull Function1<? super Throwable, Boolean> predicate) {
        List<Throwable> list = this.uncaughtExceptions;
        boolean z = true;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (!predicate.invoke(it.next()).booleanValue()) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            this.uncaughtExceptions.clear();
            return;
        }
        throw new AssertionError(message);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void assertAnyUnhandledException(@NotNull String message, @NotNull Function1<? super Throwable, Boolean> predicate) {
        List<Throwable> list = this.uncaughtExceptions;
        boolean z = false;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (predicate.invoke(it.next()).booleanValue()) {
                    z = true;
                    break;
                }
            }
        }
        if (z) {
            this.uncaughtExceptions.clear();
            return;
        }
        throw new AssertionError(message);
    }

    public final void assertExceptions(@NotNull String message, @NotNull Function1<? super List<? extends Throwable>, Boolean> predicate) {
        if (predicate.invoke(this.uncaughtExceptions).booleanValue()) {
            this.uncaughtExceptions.clear();
            return;
        }
        throw new AssertionError(message);
    }

    public final void assertUnhandledException(@NotNull String message, @NotNull Function1<? super Throwable, Boolean> predicate) {
        if (this.uncaughtExceptions.size() == 1 && predicate.invoke(this.uncaughtExceptions.get(0)).booleanValue()) {
            this.uncaughtExceptions.clear();
            return;
        }
        throw new AssertionError(message);
    }

    public final void cancelAllActions() {
        if (this.queue.isEmpty()) {
            return;
        }
        this.queue.clear();
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R fold(R initial, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> operation) {
        return operation.invoke((R) operation.invoke(initial, this.ctxDispatcher), this.ctxHandler);
    }

    @Override // kotlin.coroutines.CoroutineContext
    @Nullable
    public <E extends CoroutineContext.Element> E get(@NotNull CoroutineContext.Key<E> key) {
        if (key == ContinuationInterceptor.INSTANCE) {
            Dispatcher dispatcher = this.ctxDispatcher;
            if (dispatcher != null) {
                return dispatcher;
            }
            throw new TypeCastException("null cannot be cast to non-null type E");
        } else if (key == CoroutineExceptionHandler.INSTANCE) {
            CoroutineExceptionHandler coroutineExceptionHandler = this.ctxHandler;
            if (coroutineExceptionHandler != null) {
                return coroutineExceptionHandler;
            }
            throw new TypeCastException("null cannot be cast to non-null type E");
        } else {
            return null;
        }
    }

    @NotNull
    public final List<Throwable> getExceptions() {
        return this.uncaughtExceptions;
    }

    @Override // kotlin.coroutines.CoroutineContext
    @NotNull
    public CoroutineContext minusKey(@NotNull CoroutineContext.Key<?> key) {
        return key == ContinuationInterceptor.INSTANCE ? this.ctxHandler : key == CoroutineExceptionHandler.INSTANCE ? this.ctxDispatcher : this;
    }

    public final long now(@NotNull TimeUnit unit) {
        return unit.convert(this.time, TimeUnit.NANOSECONDS);
    }

    @Override // kotlin.coroutines.CoroutineContext
    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext coroutineContext) {
        return CoroutineContext.DefaultImpls.plus(this, coroutineContext);
    }

    @NotNull
    public String toString() {
        String str = this.name;
        if (str != null) {
            return str;
        }
        return "TestCoroutineContext@" + DebugStringsKt.getHexAddress(this);
    }

    public final void triggerActions() {
        triggerActions(this.time);
    }

    private final void triggerActions(long targetTime) {
        TimedRunnable timedRunnable;
        while (true) {
            ThreadSafeHeap<TimedRunnable> threadSafeHeap = this.queue;
            synchronized (threadSafeHeap) {
                TimedRunnable firstImpl = threadSafeHeap.firstImpl();
                if (firstImpl != null) {
                    timedRunnable = (firstImpl.time > targetTime ? 1 : (firstImpl.time == targetTime ? 0 : -1)) <= 0 ? threadSafeHeap.removeAtImpl(0) : null;
                }
            }
            TimedRunnable timedRunnable2 = timedRunnable;
            if (timedRunnable2 == null) {
                return;
            }
            long j2 = timedRunnable2.time;
            if (j2 != 0) {
                this.time = j2;
            }
            timedRunnable2.run();
        }
    }

    public /* synthetic */ TestCoroutineContext(String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str);
    }
}
