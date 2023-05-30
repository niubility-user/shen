package kotlinx.coroutines.test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a2\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0001\u001a\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u0002\u00a2\u0006\u0002\b\u0004H\u0007\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lkotlinx/coroutines/test/TestCoroutineContext;", "testContext", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "testBody", "withTestContext", "(Lkotlinx/coroutines/test/TestCoroutineContext;Lkotlin/jvm/functions/Function1;)V", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TestCoroutineContextKt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "This API has been deprecated to integrate with Structured Concurrency.", replaceWith = @ReplaceWith(expression = "testContext.runBlockingTest(testBody)", imports = {"kotlin.coroutines.test"}))
    public static final void withTestContext(@NotNull TestCoroutineContext testCoroutineContext, @NotNull Function1<? super TestCoroutineContext, Unit> function1) {
        function1.invoke(testCoroutineContext);
        List<Throwable> exceptions = testCoroutineContext.getExceptions();
        boolean z = true;
        if (!(exceptions instanceof Collection) || !exceptions.isEmpty()) {
            Iterator<T> it = exceptions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (!(((Throwable) it.next()) instanceof CancellationException)) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            return;
        }
        throw new AssertionError("Coroutine encountered unhandled exceptions:\n" + testCoroutineContext.getExceptions());
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [kotlin.jvm.internal.DefaultConstructorMarker, java.lang.String] */
    public static /* synthetic */ void withTestContext$default(TestCoroutineContext testCoroutineContext, Function1 function1, int i2, Object obj) {
        int i3 = 1;
        if ((i2 & 1) != 0) {
            ?? r2 = 0;
            testCoroutineContext = new TestCoroutineContext(r2, i3, r2);
        }
        withTestContext(testCoroutineContext, function1);
    }
}
