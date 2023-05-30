package kotlinx.coroutines.internal;

import com.jingdong.app.mall.e;
import java.util.ArrayDeque;
import java.util.Iterator;
import jpbury.t;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\u001a!\u0010\u0003\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00028\u0000H\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\u001d\u0010\u0005\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00028\u0000H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0004\u001a.\u0010\u0003\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00028\u00002\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0080\b\u00a2\u0006\u0004\b\u0003\u0010\b\u001a-\u0010\u000b\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00028\u00002\n\u0010\u0007\u001a\u00060\tj\u0002`\nH\u0002\u00a2\u0006\u0004\b\u000b\u0010\f\u001a;\u0010\u0013\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00028\u00002\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u00060\u0010j\u0002`\u00110\u000fH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014\u001a3\u0010\u0017\u001a\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0010j\u0002`\u00110\u00160\u0015\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00028\u0000H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a3\u0010\u001b\u001a\u00020\u001a2\u0010\u0010\u0019\u001a\f\u0012\b\u0012\u00060\u0010j\u0002`\u00110\u00162\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u00060\u0010j\u0002`\u00110\u000fH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a\u001b\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u0000H\u0080H\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u001f\u001a\"\u0010 \u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00028\u0000H\u0080\b\u00a2\u0006\u0004\b \u0010\u0004\u001a!\u0010!\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00028\u0000H\u0000\u00a2\u0006\u0004\b!\u0010\u0004\u001a%\u0010\"\u001a\f\u0012\b\u0012\u00060\u0010j\u0002`\u00110\u000f2\n\u0010\u0007\u001a\u00060\tj\u0002`\nH\u0002\u00a2\u0006\u0004\b\"\u0010#\u001a\u001b\u0010&\u001a\u00060\u0010j\u0002`\u00112\u0006\u0010%\u001a\u00020$H\u0007\u00a2\u0006\u0004\b&\u0010'\u001a\u0017\u0010)\u001a\u00020(*\u00060\u0010j\u0002`\u0011H\u0000\u00a2\u0006\u0004\b)\u0010*\u001a%\u0010-\u001a\u00020,*\f\u0012\b\u0012\u00060\u0010j\u0002`\u00110\u00162\u0006\u0010+\u001a\u00020$H\u0002\u00a2\u0006\u0004\b-\u0010.\u001a#\u00100\u001a\u00020(*\u00060\u0010j\u0002`\u00112\n\u0010/\u001a\u00060\u0010j\u0002`\u0011H\u0002\u00a2\u0006\u0004\b0\u00101\u001a\u001b\u00102\u001a\u00020\u001a*\u00020\u00002\u0006\u0010\r\u001a\u00020\u0000H\u0000\u00a2\u0006\u0004\b2\u00103\"\u001e\u00105\u001a\n 4*\u0004\u0018\u00010$0$8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106\"\u0016\u00107\u001a\u00020$8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u00106\"\u0016\u00108\u001a\u00020$8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u00106\"\u001e\u00109\u001a\n 4*\u0004\u0018\u00010$0$8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u00106*\f\b\u0000\u0010:\"\u00020\t2\u00020\t*\f\b\u0000\u0010;\"\u00020\u00102\u00020\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006<"}, d2 = {"", "E", t.f20145j, "recoverStackTrace", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "sanitizeStackTrace", "Lkotlin/coroutines/Continuation;", "continuation", "(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Throwable;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "recoverFromStackFrame", "(Ljava/lang/Throwable;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Ljava/lang/Throwable;", "cause", "result", "Ljava/util/ArrayDeque;", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "resultStackTrace", "createFinalException", "(Ljava/lang/Throwable;Ljava/lang/Throwable;Ljava/util/ArrayDeque;)Ljava/lang/Throwable;", "Lkotlin/Pair;", "", "causeAndStacktrace", "(Ljava/lang/Throwable;)Lkotlin/Pair;", "recoveredStacktrace", "", "mergeRecoveredTraces", "([Ljava/lang/StackTraceElement;Ljava/util/ArrayDeque;)V", "", "recoverAndThrow", "(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unwrap", "unwrapImpl", "createStackTrace", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Ljava/util/ArrayDeque;", "", "message", "artificialFrame", "(Ljava/lang/String;)Ljava/lang/StackTraceElement;", "", "isArtificial", "(Ljava/lang/StackTraceElement;)Z", "methodName", "", "frameIndex", "([Ljava/lang/StackTraceElement;Ljava/lang/String;)I", e.a, "elementWiseEquals", "(Ljava/lang/StackTraceElement;Ljava/lang/StackTraceElement;)Z", "initCause", "(Ljava/lang/Throwable;Ljava/lang/Throwable;)V", "kotlin.jvm.PlatformType", "baseContinuationImplClassName", "Ljava/lang/String;", "stackTraceRecoveryClass", "baseContinuationImplClass", "stackTraceRecoveryClassName", "CoroutineStackFrame", "StackTraceElement", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class StackTraceRecoveryKt {
    private static final String baseContinuationImplClass = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
    private static final String baseContinuationImplClassName;
    private static final String stackTraceRecoveryClass = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
    private static final String stackTraceRecoveryClassName;

    static {
        Object m200constructorimpl;
        Object m200constructorimpl2;
        try {
            Result.Companion companion = Result.INSTANCE;
            m200constructorimpl = Result.m200constructorimpl(Class.forName(baseContinuationImplClass).getCanonicalName());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m200constructorimpl = Result.m200constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m203exceptionOrNullimpl(m200constructorimpl) != null) {
            m200constructorimpl = baseContinuationImplClass;
        }
        baseContinuationImplClassName = (String) m200constructorimpl;
        try {
            Result.Companion companion3 = Result.INSTANCE;
            m200constructorimpl2 = Result.m200constructorimpl(Class.forName(stackTraceRecoveryClass).getCanonicalName());
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            m200constructorimpl2 = Result.m200constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m203exceptionOrNullimpl(m200constructorimpl2) != null) {
            m200constructorimpl2 = stackTraceRecoveryClass;
        }
        stackTraceRecoveryClassName = (String) m200constructorimpl2;
    }

    public static /* synthetic */ void CoroutineStackFrame$annotations() {
    }

    public static /* synthetic */ void StackTraceElement$annotations() {
    }

    public static final /* synthetic */ Throwable access$recoverFromStackFrame(Throwable th, CoroutineStackFrame coroutineStackFrame) {
        return recoverFromStackFrame(th, coroutineStackFrame);
    }

    @InternalCoroutinesApi
    @NotNull
    public static final StackTraceElement artificialFrame(@NotNull String str) {
        return new StackTraceElement("\b\b\b(" + str, "\b", "\b", -1);
    }

    private static final <E extends Throwable> Pair<E, StackTraceElement[]> causeAndStacktrace(@NotNull E e2) {
        boolean z;
        Throwable cause = e2.getCause();
        if (cause != null && Intrinsics.areEqual(cause.getClass(), e2.getClass())) {
            StackTraceElement[] stackTrace = e2.getStackTrace();
            int length = stackTrace.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z = false;
                    break;
                } else if (isArtificial(stackTrace[i2])) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                return TuplesKt.to(cause, stackTrace);
            }
            return TuplesKt.to(e2, new StackTraceElement[0]);
        }
        return TuplesKt.to(e2, new StackTraceElement[0]);
    }

    private static final <E extends Throwable> E createFinalException(E e2, E e3, ArrayDeque<StackTraceElement> arrayDeque) {
        arrayDeque.addFirst(artificialFrame("Coroutine boundary"));
        StackTraceElement[] stackTrace = e2.getStackTrace();
        int frameIndex = frameIndex(stackTrace, baseContinuationImplClassName);
        int i2 = 0;
        if (frameIndex == -1) {
            Object[] array = arrayDeque.toArray(new StackTraceElement[0]);
            if (array != null) {
                e3.setStackTrace((StackTraceElement[]) array);
                return e3;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[arrayDeque.size() + frameIndex];
        for (int i3 = 0; i3 < frameIndex; i3++) {
            stackTraceElementArr[i3] = stackTrace[i3];
        }
        Iterator<T> it = arrayDeque.iterator();
        while (it.hasNext()) {
            stackTraceElementArr[frameIndex + i2] = (StackTraceElement) it.next();
            i2++;
        }
        e3.setStackTrace(stackTraceElementArr);
        return e3;
    }

    private static final ArrayDeque<StackTraceElement> createStackTrace(CoroutineStackFrame coroutineStackFrame) {
        ArrayDeque<StackTraceElement> arrayDeque = new ArrayDeque<>();
        StackTraceElement stackTraceElement = coroutineStackFrame.getStackTraceElement();
        if (stackTraceElement != null) {
            arrayDeque.add(stackTraceElement);
        }
        while (true) {
            if (!(coroutineStackFrame instanceof CoroutineStackFrame)) {
                coroutineStackFrame = null;
            }
            if (coroutineStackFrame == null || (coroutineStackFrame = coroutineStackFrame.getCallerFrame()) == null) {
                break;
            }
            StackTraceElement stackTraceElement2 = coroutineStackFrame.getStackTraceElement();
            if (stackTraceElement2 != null) {
                arrayDeque.add(stackTraceElement2);
            }
        }
        return arrayDeque;
    }

    private static final boolean elementWiseEquals(@NotNull StackTraceElement stackTraceElement, StackTraceElement stackTraceElement2) {
        return stackTraceElement.getLineNumber() == stackTraceElement2.getLineNumber() && Intrinsics.areEqual(stackTraceElement.getMethodName(), stackTraceElement2.getMethodName()) && Intrinsics.areEqual(stackTraceElement.getFileName(), stackTraceElement2.getFileName()) && Intrinsics.areEqual(stackTraceElement.getClassName(), stackTraceElement2.getClassName());
    }

    private static final int frameIndex(@NotNull StackTraceElement[] stackTraceElementArr, String str) {
        int length = stackTraceElementArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (Intrinsics.areEqual(str, stackTraceElementArr[i2].getClassName())) {
                return i2;
            }
        }
        return -1;
    }

    public static final void initCause(@NotNull Throwable th, @NotNull Throwable th2) {
        th.initCause(th2);
    }

    public static final boolean isArtificial(@NotNull StackTraceElement stackTraceElement) {
        boolean startsWith$default;
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(stackTraceElement.getClassName(), "\b\b\b", false, 2, null);
        return startsWith$default;
    }

    private static final void mergeRecoveredTraces(StackTraceElement[] stackTraceElementArr, ArrayDeque<StackTraceElement> arrayDeque) {
        int length = stackTraceElementArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            } else if (isArtificial(stackTraceElementArr[i2])) {
                break;
            } else {
                i2++;
            }
        }
        int i3 = i2 + 1;
        int length2 = stackTraceElementArr.length - 1;
        if (length2 < i3) {
            return;
        }
        while (true) {
            if (elementWiseEquals(stackTraceElementArr[length2], arrayDeque.getLast())) {
                arrayDeque.removeLast();
            }
            arrayDeque.addFirst(stackTraceElementArr[length2]);
            if (length2 == i3) {
                return;
            }
            length2--;
        }
    }

    @Nullable
    public static final Object recoverAndThrow(@NotNull Throwable th, @NotNull Continuation<?> continuation) {
        if (DebugKt.getRECOVER_STACK_TRACES()) {
            if (continuation instanceof CoroutineStackFrame) {
                throw recoverFromStackFrame(th, (CoroutineStackFrame) continuation);
            }
            throw th;
        }
        throw th;
    }

    @Nullable
    private static final Object recoverAndThrow$$forInline(@NotNull Throwable th, @NotNull Continuation continuation) {
        if (DebugKt.getRECOVER_STACK_TRACES()) {
            InlineMarker.mark(0);
            if (continuation instanceof CoroutineStackFrame) {
                throw recoverFromStackFrame(th, (CoroutineStackFrame) continuation);
            }
            throw th;
        }
        throw th;
    }

    public static final <E extends Throwable> E recoverFromStackFrame(E e2, CoroutineStackFrame coroutineStackFrame) {
        Pair causeAndStacktrace = causeAndStacktrace(e2);
        Throwable th = (Throwable) causeAndStacktrace.component1();
        StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) causeAndStacktrace.component2();
        Throwable tryCopyException = ExceptionsConstuctorKt.tryCopyException(th);
        if (tryCopyException == null || (!Intrinsics.areEqual(tryCopyException.getMessage(), th.getMessage())) == true) {
            return e2;
        }
        ArrayDeque<StackTraceElement> createStackTrace = createStackTrace(coroutineStackFrame);
        if (createStackTrace.isEmpty()) {
            return e2;
        }
        if (th != e2) {
            mergeRecoveredTraces(stackTraceElementArr, createStackTrace);
        }
        return (E) createFinalException(th, tryCopyException, createStackTrace);
    }

    @NotNull
    public static final <E extends Throwable> E recoverStackTrace(@NotNull E e2) {
        Throwable tryCopyException;
        return (DebugKt.getRECOVER_STACK_TRACES() && (tryCopyException = ExceptionsConstuctorKt.tryCopyException(e2)) != null) ? (E) sanitizeStackTrace(tryCopyException) : e2;
    }

    private static final <E extends Throwable> E sanitizeStackTrace(@NotNull E e2) {
        StackTraceElement stackTraceElement;
        StackTraceElement[] stackTrace = e2.getStackTrace();
        int length = stackTrace.length;
        int frameIndex = frameIndex(stackTrace, stackTraceRecoveryClassName);
        int i2 = frameIndex + 1;
        int frameIndex2 = frameIndex(stackTrace, baseContinuationImplClassName);
        int i3 = (length - frameIndex) - (frameIndex2 == -1 ? 0 : length - frameIndex2);
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            if (i4 == 0) {
                stackTraceElement = artificialFrame("Coroutine boundary");
            } else {
                stackTraceElement = stackTrace[(i2 + i4) - 1];
            }
            stackTraceElementArr[i4] = stackTraceElement;
        }
        e2.setStackTrace(stackTraceElementArr);
        return e2;
    }

    @NotNull
    public static final <E extends Throwable> E unwrap(@NotNull E e2) {
        return !DebugKt.getRECOVER_STACK_TRACES() ? e2 : (E) unwrapImpl(e2);
    }

    @NotNull
    public static final <E extends Throwable> E unwrapImpl(@NotNull E e2) {
        E e3 = (E) e2.getCause();
        if (e3 != null) {
            boolean z = true;
            if ((!Intrinsics.areEqual(e3.getClass(), e2.getClass())) == false) {
                StackTraceElement[] stackTrace = e2.getStackTrace();
                int length = stackTrace.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        z = false;
                        break;
                    } else if (isArtificial(stackTrace[i2])) {
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z) {
                    return e3;
                }
            }
        }
        return e2;
    }

    @NotNull
    public static final <E extends Throwable> E recoverStackTrace(@NotNull E e2, @NotNull Continuation<?> continuation) {
        return (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) ? (E) recoverFromStackFrame(e2, (CoroutineStackFrame) continuation) : e2;
    }
}
