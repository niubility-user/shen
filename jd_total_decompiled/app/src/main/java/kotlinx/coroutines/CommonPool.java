package kotlinx.coroutines;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\f\b\u00c0\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b6\u0010\u001dJ&\u0010\u0005\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0082\b\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\n\u0010\tJ\u000f\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ#\u0010\u0014\u001a\u00020\u00112\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0010\u001a\u00020\u0007H\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013J#\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00152\n\u0010\u0004\u001a\u00060\u0017j\u0002`\u0018H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001e\u001a\u00020\u0019H\u0000\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010#\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u001fH\u0000\u00a2\u0006\u0004\b!\u0010\"J\u000f\u0010%\u001a\u00020\u0019H\u0000\u00a2\u0006\u0004\b$\u0010\u001dJ\u000f\u0010'\u001a\u00020&H\u0016\u00a2\u0006\u0004\b'\u0010(J\u000f\u0010)\u001a\u00020\u0019H\u0016\u00a2\u0006\u0004\b)\u0010\u001dR\u0016\u0010\u001e\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010*R\u0016\u0010.\u001a\u00020+8B@\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b,\u0010-R\u0018\u0010/\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0016\u0010\u0010\u001a\u00020\u000b8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b1\u0010\rR\u0016\u00102\u001a\u00020&8\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0016\u00104\u001a\u00020+8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00105\u00a8\u00067"}, d2 = {"Lkotlinx/coroutines/CommonPool;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "T", "Lkotlin/Function0;", JDReactConstant.BLOCK, "Try", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Ljava/util/concurrent/ExecutorService;", "createPool", "()Ljava/util/concurrent/ExecutorService;", "createPlainPool", "Ljava/util/concurrent/Executor;", "getOrCreatePoolSync", "()Ljava/util/concurrent/Executor;", "Ljava/lang/Class;", "fjpClass", "executor", "", "isGoodCommonPool$kotlinx_coroutines_core", "(Ljava/lang/Class;Ljava/util/concurrent/ExecutorService;)Z", "isGoodCommonPool", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "", "dispatch", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)V", "usePrivatePool$kotlinx_coroutines_core", "()V", "usePrivatePool", "", "timeout", "shutdown$kotlinx_coroutines_core", "(J)V", "shutdown", "restore$kotlinx_coroutines_core", "restore", "", "toString", "()Ljava/lang/String;", "close", "Z", "", "getParallelism", "()I", "parallelism", "pool", "Ljava/util/concurrent/Executor;", "getExecutor", "DEFAULT_PARALLELISM_PROPERTY_NAME", "Ljava/lang/String;", "requestedParallelism", "I", "<init>", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class CommonPool extends ExecutorCoroutineDispatcher {
    @NotNull
    public static final String DEFAULT_PARALLELISM_PROPERTY_NAME = "kotlinx.coroutines.default.parallelism";
    public static final CommonPool INSTANCE = new CommonPool();
    private static volatile Executor pool;
    private static final int requestedParallelism;
    private static boolean usePrivatePool;

    static {
        String str;
        int i2;
        Integer intOrNull;
        try {
            str = System.getProperty(DEFAULT_PARALLELISM_PROPERTY_NAME);
        } catch (Throwable unused) {
            str = null;
        }
        if (str != null) {
            intOrNull = StringsKt__StringNumberConversionsKt.toIntOrNull(str);
            if (intOrNull != null && intOrNull.intValue() >= 1) {
                i2 = intOrNull.intValue();
            } else {
                throw new IllegalStateException(("Expected positive number in kotlinx.coroutines.default.parallelism, but has " + str).toString());
            }
        } else {
            i2 = -1;
        }
        requestedParallelism = i2;
    }

    private CommonPool() {
    }

    private final <T> T Try(Function0<? extends T> r1) {
        try {
            return r1.invoke();
        } catch (Throwable unused) {
            return null;
        }
    }

    private final ExecutorService createPlainPool() {
        new AtomicInteger();
        return Executors.newFixedThreadPool(getParallelism(), new ThreadFactory
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0012: RETURN 
              (wrap: java.util.concurrent.ExecutorService : 0x000e: INVOKE 
              (wrap: int : 0x0005: INVOKE (r3v0 'this' kotlinx.coroutines.CommonPool A[IMMUTABLE_TYPE, THIS]) type: DIRECT call: kotlinx.coroutines.CommonPool.getParallelism():int A[MD:():int (m), WRAPPED] (LINE:2))
              (wrap: java.util.concurrent.ThreadFactory : 0x000b: CONSTRUCTOR (r0 I:java.util.concurrent.atomic.AtomicInteger A[DONT_GENERATE, DONT_INLINE, REMOVE]) A[MD:(java.util.concurrent.atomic.AtomicInteger):void (m), WRAPPED] call: kotlinx.coroutines.CommonPool$createPlainPool$1.<init>(java.util.concurrent.atomic.AtomicInteger):void type: CONSTRUCTOR)
             type: STATIC call: java.util.concurrent.Executors.newFixedThreadPool(int, java.util.concurrent.ThreadFactory):java.util.concurrent.ExecutorService A[MD:(int, java.util.concurrent.ThreadFactory):java.util.concurrent.ExecutorService (c), WRAPPED] (LINE:2))
             (LINE:2) in method: kotlinx.coroutines.CommonPool.createPlainPool():java.util.concurrent.ExecutorService, file: classes11.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            java.util.concurrent.atomic.AtomicInteger r0 = new java.util.concurrent.atomic.AtomicInteger
            r0.<init>()
            int r1 = r3.getParallelism()
            kotlinx.coroutines.CommonPool$createPlainPool$1 r2 = new kotlinx.coroutines.CommonPool$createPlainPool$1
            r2.<init>()
            java.util.concurrent.ExecutorService r0 = java.util.concurrent.Executors.newFixedThreadPool(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CommonPool.createPlainPool():java.util.concurrent.ExecutorService");
    }

    private final ExecutorService createPool() {
        Class<?> cls;
        ExecutorService executorService;
        if (System.getSecurityManager() != null) {
            return createPlainPool();
        }
        ExecutorService executorService2 = null;
        try {
            cls = Class.forName("java.util.concurrent.ForkJoinPool");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls != null) {
            if (!usePrivatePool && requestedParallelism < 0) {
                try {
                    Method method = cls.getMethod("commonPool", new Class[0]);
                    Object invoke = method != null ? method.invoke(null, new Object[0]) : null;
                    if (!(invoke instanceof ExecutorService)) {
                        invoke = null;
                    }
                    executorService = (ExecutorService) invoke;
                } catch (Throwable unused2) {
                    executorService = null;
                }
                if (executorService != null) {
                    if (!INSTANCE.isGoodCommonPool$kotlinx_coroutines_core(cls, executorService)) {
                        executorService = null;
                    }
                    if (executorService != null) {
                        return executorService;
                    }
                }
            }
            try {
                Object newInstance = cls.getConstructor(Integer.TYPE).newInstance(Integer.valueOf(INSTANCE.getParallelism()));
                if (!(newInstance instanceof ExecutorService)) {
                    newInstance = null;
                }
                executorService2 = (ExecutorService) newInstance;
            } catch (Throwable unused3) {
            }
            return executorService2 != null ? executorService2 : createPlainPool();
        }
        return createPlainPool();
    }

    private final synchronized Executor getOrCreatePoolSync() {
        Executor executor;
        executor = pool;
        if (executor == null) {
            executor = createPool();
            pool = executor;
        }
        return executor;
    }

    private final int getParallelism() {
        int coerceAtLeast;
        Integer valueOf = Integer.valueOf(requestedParallelism);
        if (!(valueOf.intValue() > 0)) {
            valueOf = null;
        }
        if (valueOf != null) {
            return valueOf.intValue();
        }
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(Runtime.getRuntime().availableProcessors() - 1, 1);
        return coerceAtLeast;
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Close cannot be invoked on CommonPool".toString());
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* renamed from: dispatch */
    public void mo1254dispatch(@NotNull CoroutineContext r2, @NotNull Runnable r3) {
        Runnable runnable;
        try {
            Executor executor = pool;
            if (executor == null) {
                executor = getOrCreatePoolSync();
            }
            TimeSource timeSource = TimeSourceKt.getTimeSource();
            if (timeSource == null || (runnable = timeSource.wrapTask(r3)) == null) {
                runnable = r3;
            }
            executor.execute(runnable);
        } catch (RejectedExecutionException unused) {
            TimeSource timeSource2 = TimeSourceKt.getTimeSource();
            if (timeSource2 != null) {
                timeSource2.unTrackTask();
            }
            DefaultExecutor.INSTANCE.enqueue(r3);
        }
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher
    @NotNull
    public Executor getExecutor() {
        Executor executor = pool;
        return executor != null ? executor : getOrCreatePoolSync();
    }

    public final boolean isGoodCommonPool$kotlinx_coroutines_core(@NotNull Class<?> fjpClass, @NotNull ExecutorService executor) {
        executor.submit(new Runnable() { // from class: kotlinx.coroutines.CommonPool$isGoodCommonPool$1
            @Override // java.lang.Runnable
            public final void run() {
            }
        });
        Integer num = null;
        try {
            Object invoke = fjpClass.getMethod("getPoolSize", new Class[0]).invoke(executor, new Object[0]);
            if (!(invoke instanceof Integer)) {
                invoke = null;
            }
            num = (Integer) invoke;
        } catch (Throwable unused) {
        }
        return num != null && num.intValue() >= 1;
    }

    public final synchronized void restore$kotlinx_coroutines_core() {
        shutdown$kotlinx_coroutines_core(0L);
        usePrivatePool = false;
        pool = null;
    }

    public final synchronized void shutdown$kotlinx_coroutines_core(long timeout) {
        Executor executor = pool;
        if (!(executor instanceof ExecutorService)) {
            executor = null;
        }
        ExecutorService executorService = (ExecutorService) executor;
        if (executorService != null) {
            executorService.shutdown();
            if (timeout > 0) {
                executorService.awaitTermination(timeout, TimeUnit.MILLISECONDS);
            }
            Iterator<T> it = executorService.shutdownNow().iterator();
            while (it.hasNext()) {
                DefaultExecutor.INSTANCE.enqueue((Runnable) it.next());
            }
        }
        pool = new Executor() { // from class: kotlinx.coroutines.CommonPool$shutdown$2
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                throw new RejectedExecutionException("CommonPool was shutdown");
            }
        };
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    public String toString() {
        return "CommonPool";
    }

    public final synchronized void usePrivatePool$kotlinx_coroutines_core() {
        shutdown$kotlinx_coroutines_core(0L);
        usePrivatePool = true;
        pool = null;
    }
}
