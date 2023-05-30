package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
@GwtIncompatible
/* loaded from: classes12.dex */
public final class SimpleTimeLimiter implements TimeLimiter {
    private final ExecutorService executor;

    private SimpleTimeLimiter(ExecutorService executorService) {
        this.executor = (ExecutorService) Preconditions.checkNotNull(executorService);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T callWithTimeout(Callable<T> callable, long j2, TimeUnit timeUnit, boolean z) throws Exception {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j2);
        Future<T> submit = this.executor.submit(callable);
        try {
            if (z) {
                try {
                    return submit.get(j2, timeUnit);
                } catch (InterruptedException e2) {
                    submit.cancel(true);
                    throw e2;
                }
            }
            return (T) Uninterruptibles.getUninterruptibly(submit, j2, timeUnit);
        } catch (ExecutionException e3) {
            throw throwCause(e3, true);
        } catch (TimeoutException e4) {
            submit.cancel(true);
            throw new UncheckedTimeoutException(e4);
        }
    }

    private static void checkPositiveTimeout(long j2) {
        Preconditions.checkArgument(j2 > 0, "timeout must be positive: %s", j2);
    }

    public static SimpleTimeLimiter create(ExecutorService executorService) {
        return new SimpleTimeLimiter(executorService);
    }

    private static boolean declaresInterruptedEx(Method method) {
        for (Class<?> cls : method.getExceptionTypes()) {
            if (cls == InterruptedException.class) {
                return true;
            }
        }
        return false;
    }

    private static Set<Method> findInterruptibleMethods(Class<?> cls) {
        HashSet newHashSet = Sets.newHashSet();
        for (Method method : cls.getMethods()) {
            if (declaresInterruptedEx(method)) {
                newHashSet.add(method);
            }
        }
        return newHashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Exception throwCause(Exception exc, boolean z) throws Exception {
        Throwable cause = exc.getCause();
        if (cause != null) {
            if (z) {
                cause.setStackTrace((StackTraceElement[]) ObjectArrays.concat(cause.getStackTrace(), exc.getStackTrace(), StackTraceElement.class));
            }
            if (!(cause instanceof Exception)) {
                if (cause instanceof Error) {
                    throw ((Error) cause);
                }
                throw exc;
            }
            throw ((Exception) cause);
        }
        throw exc;
    }

    private void wrapAndThrowExecutionExceptionOrError(Throwable th) throws ExecutionException {
        if (!(th instanceof Error)) {
            if (th instanceof RuntimeException) {
                throw new UncheckedExecutionException(th);
            }
            throw new ExecutionException(th);
        }
        throw new ExecutionError((Error) th);
    }

    private void wrapAndThrowRuntimeExecutionExceptionOrError(Throwable th) {
        if (th instanceof Error) {
            throw new ExecutionError((Error) th);
        }
        throw new UncheckedExecutionException(th);
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    @CanIgnoreReturnValue
    public <T> T callUninterruptiblyWithTimeout(Callable<T> callable, long j2, TimeUnit timeUnit) throws TimeoutException, ExecutionException {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j2);
        Future<T> submit = this.executor.submit(callable);
        try {
            return (T) Uninterruptibles.getUninterruptibly(submit, j2, timeUnit);
        } catch (ExecutionException e2) {
            wrapAndThrowExecutionExceptionOrError(e2.getCause());
            throw new AssertionError();
        } catch (TimeoutException e3) {
            submit.cancel(true);
            throw e3;
        }
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public <T> T newProxy(final T t, Class<T> cls, final long j2, final TimeUnit timeUnit) {
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j2);
        Preconditions.checkArgument(cls.isInterface(), "interfaceType must be an interface type");
        findInterruptibleMethods(cls);
        return (T) newProxy(cls, new InvocationHandler
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0027: RETURN 
              (wrap: T : ?: CHECK_CAST (T) (wrap: java.lang.Object : 0x0023: INVOKE 
              (r11v0 'cls' java.lang.Class<T>)
              (wrap: java.lang.reflect.InvocationHandler : 0x0020: CONSTRUCTOR 
              (r9v0 'this' com.google.common.util.concurrent.SimpleTimeLimiter A[IMMUTABLE_TYPE, THIS])
              (r10v0 't' T A[DONT_INLINE])
              (r12v0 'j2' long A[DONT_INLINE])
              (r14v0 'timeUnit' java.util.concurrent.TimeUnit A[DONT_INLINE])
              (r8 I:java.util.Set A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.google.common.util.concurrent.SimpleTimeLimiter, java.lang.Object, long, java.util.concurrent.TimeUnit, java.util.Set):void (m), WRAPPED] (LINE:7) call: com.google.common.util.concurrent.SimpleTimeLimiter.1.<init>(com.google.common.util.concurrent.SimpleTimeLimiter, java.lang.Object, long, java.util.concurrent.TimeUnit, java.util.Set):void type: CONSTRUCTOR)
             type: STATIC call: com.google.common.util.concurrent.SimpleTimeLimiter.newProxy(java.lang.Class, java.lang.reflect.InvocationHandler):java.lang.Object A[MD:<T>:(java.lang.Class<T>, java.lang.reflect.InvocationHandler):T (m), WRAPPED] (LINE:8)))
             (LINE:8) in method: com.google.common.util.concurrent.SimpleTimeLimiter.newProxy(T, java.lang.Class<T>, long, java.util.concurrent.TimeUnit):T, file: classes12.dex
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
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:344)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            com.google.common.base.Preconditions.checkNotNull(r10)
            com.google.common.base.Preconditions.checkNotNull(r11)
            com.google.common.base.Preconditions.checkNotNull(r14)
            checkPositiveTimeout(r12)
            boolean r0 = r11.isInterface()
            java.lang.String r1 = "interfaceType must be an interface type"
            com.google.common.base.Preconditions.checkArgument(r0, r1)
            java.util.Set r8 = findInterruptibleMethods(r11)
            com.google.common.util.concurrent.SimpleTimeLimiter$1 r0 = new com.google.common.util.concurrent.SimpleTimeLimiter$1
            r2 = r0
            r3 = r9
            r4 = r10
            r5 = r12
            r7 = r14
            r2.<init>()
            java.lang.Object r10 = newProxy(r11, r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SimpleTimeLimiter.newProxy(java.lang.Object, java.lang.Class, long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public void runUninterruptiblyWithTimeout(Runnable runnable, long j2, TimeUnit timeUnit) throws TimeoutException {
        Preconditions.checkNotNull(runnable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j2);
        Future<?> submit = this.executor.submit(runnable);
        try {
            Uninterruptibles.getUninterruptibly(submit, j2, timeUnit);
        } catch (ExecutionException e2) {
            wrapAndThrowRuntimeExecutionExceptionOrError(e2.getCause());
            throw new AssertionError();
        } catch (TimeoutException e3) {
            submit.cancel(true);
            throw e3;
        }
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public void runWithTimeout(Runnable runnable, long j2, TimeUnit timeUnit) throws TimeoutException, InterruptedException {
        Preconditions.checkNotNull(runnable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j2);
        Future<?> submit = this.executor.submit(runnable);
        try {
            submit.get(j2, timeUnit);
        } catch (InterruptedException e2) {
            e = e2;
            submit.cancel(true);
            throw e;
        } catch (ExecutionException e3) {
            wrapAndThrowRuntimeExecutionExceptionOrError(e3.getCause());
            throw new AssertionError();
        } catch (TimeoutException e4) {
            e = e4;
            submit.cancel(true);
            throw e;
        }
    }

    private static <T> T newProxy(Class<T> cls, InvocationHandler invocationHandler) {
        return cls.cast(Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler));
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    @CanIgnoreReturnValue
    public <T> T callWithTimeout(Callable<T> callable, long j2, TimeUnit timeUnit) throws TimeoutException, InterruptedException, ExecutionException {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(timeUnit);
        checkPositiveTimeout(j2);
        Future<T> submit = this.executor.submit(callable);
        try {
            return submit.get(j2, timeUnit);
        } catch (InterruptedException e2) {
            e = e2;
            submit.cancel(true);
            throw e;
        } catch (ExecutionException e3) {
            wrapAndThrowExecutionExceptionOrError(e3.getCause());
            throw new AssertionError();
        } catch (TimeoutException e4) {
            e = e4;
            submit.cancel(true);
            throw e;
        }
    }
}
