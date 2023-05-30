package kotlin;

import java.io.PrintStream;
import java.io.PrintWriter;
import jpbury.t;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u001c\u0010\u0002\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u0002\u0010\u0006\u001a\u001c\u0010\u0002\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0087\b\u00a2\u0006\u0004\b\u0002\u0010\t\u001a\u0019\u0010\u000b\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000\u00a2\u0006\u0004\b\u000b\u0010\f\"#\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\r*\u00020\u00008F@\u0006\u00a2\u0006\f\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0013"}, d2 = {"", "", "printStackTrace", "(Ljava/lang/Throwable;)V", "Ljava/io/PrintWriter;", "writer", "(Ljava/lang/Throwable;Ljava/io/PrintWriter;)V", "Ljava/io/PrintStream;", "stream", "(Ljava/lang/Throwable;Ljava/io/PrintStream;)V", t.f20145j, "addSuppressed", "(Ljava/lang/Throwable;Ljava/lang/Throwable;)V", "", "Ljava/lang/StackTraceElement;", "getStackTrace", "(Ljava/lang/Throwable;)[Ljava/lang/StackTraceElement;", "stackTrace$annotations", "stackTrace", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/ExceptionsKt")
/* loaded from: classes11.dex */
public class ExceptionsKt__ExceptionsKt {
    public static void addSuppressed(@NotNull Throwable th, @NotNull Throwable th2) {
        PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed(th, th2);
    }

    @NotNull
    public static final StackTraceElement[] getStackTrace(@NotNull Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace == null) {
            Intrinsics.throwNpe();
        }
        return stackTrace;
    }

    @InlineOnly
    private static final void printStackTrace(@NotNull Throwable th) {
        if (th == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
        }
        th.printStackTrace();
    }

    public static /* synthetic */ void stackTrace$annotations(Throwable th) {
    }

    @InlineOnly
    private static final void printStackTrace(@NotNull Throwable th, PrintWriter printWriter) {
        if (th == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
        }
        th.printStackTrace(printWriter);
    }

    @InlineOnly
    private static final void printStackTrace(@NotNull Throwable th, PrintStream printStream) {
        if (th == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
        }
        th.printStackTrace(printStream);
    }
}
