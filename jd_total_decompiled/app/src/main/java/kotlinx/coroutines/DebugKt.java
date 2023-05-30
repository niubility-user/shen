package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.internal.SystemPropsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0000\u00a2\u0006\u0004\b\u0001\u0010\u0002\u001a\u001e\u0010\u0006\u001a\u00020\u00002\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0081\b\u00a2\u0006\u0004\b\u0006\u0010\u0007\"\u0016\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\t\u0010\n\"\u001c\u0010\u000b\u001a\u00020\u00048\u0000@\u0000X\u0080\u0004\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u001c\u0010\u0010\u001a\u00020\u000f8\u0000@\u0000X\u0080\u0004\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0016\u0010\u0014\u001a\u00020\b8\u0000@\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\n\"\u0016\u0010\u0015\u001a\u00020\b8\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\n\"\u001c\u0010\u0016\u001a\u00020\u00048\u0000@\u0000X\u0080\u0004\u00a2\u0006\f\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0017\u0010\u000e\"\u001c\u0010\u0018\u001a\u00020\u00048\u0000@\u0000X\u0080\u0004\u00a2\u0006\f\n\u0004\b\u0018\u0010\f\u001a\u0004\b\u0019\u0010\u000e\"\u0016\u0010\u001a\u001a\u00020\b8\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\n\"\u0016\u0010\u001b\u001a\u00020\b8\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\n\u00a8\u0006\u001c"}, d2 = {"", "resetCoroutineId", "()V", "Lkotlin/Function0;", "", "value", "assert", "(Lkotlin/jvm/functions/Function0;)V", "", "DEBUG_PROPERTY_NAME", "Ljava/lang/String;", "DEBUG", "Z", "getDEBUG", "()Z", "Ljava/util/concurrent/atomic/AtomicLong;", "COROUTINE_ID", "Ljava/util/concurrent/atomic/AtomicLong;", "getCOROUTINE_ID", "()Ljava/util/concurrent/atomic/AtomicLong;", "STACKTRACE_RECOVERY_PROPERTY_NAME", "DEBUG_PROPERTY_VALUE_OFF", "ASSERTIONS_ENABLED", "getASSERTIONS_ENABLED", "RECOVER_STACK_TRACES", "getRECOVER_STACK_TRACES", "DEBUG_PROPERTY_VALUE_ON", "DEBUG_PROPERTY_VALUE_AUTO", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class DebugKt {
    private static final boolean ASSERTIONS_ENABLED = false;
    @NotNull
    private static final AtomicLong COROUTINE_ID;
    private static final boolean DEBUG;
    @NotNull
    public static final String DEBUG_PROPERTY_NAME = "kotlinx.coroutines.debug";
    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_AUTO = "auto";
    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_OFF = "off";
    @NotNull
    public static final String DEBUG_PROPERTY_VALUE_ON = "on";
    private static final boolean RECOVER_STACK_TRACES;
    @NotNull
    public static final String STACKTRACE_RECOVERY_PROPERTY_NAME = "kotlinx.coroutines.stacktrace.recovery";

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0038, code lost:
        if (r0.equals("on") != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0041, code lost:
        if (r0.equals("") != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0043, code lost:
        r0 = true;
     */
    static {
        String systemProp = SystemPropsKt.systemProp(DEBUG_PROPERTY_NAME);
        if (systemProp != null) {
            int hashCode = systemProp.hashCode();
            if (hashCode != 0) {
                if (hashCode != 3551) {
                    if (hashCode == 109935) {
                    }
                }
                throw new IllegalStateException(("System property 'kotlinx.coroutines.debug' has unrecognized value '" + systemProp + '\'').toString());
            }
            DEBUG = r0;
            RECOVER_STACK_TRACES = !r0 && SystemPropsKt.systemProp(STACKTRACE_RECOVERY_PROPERTY_NAME, true);
            COROUTINE_ID = new AtomicLong(0L);
        }
        boolean z = false;
        DEBUG = z;
        RECOVER_STACK_TRACES = !z && SystemPropsKt.systemProp(STACKTRACE_RECOVERY_PROPERTY_NAME, true);
        COROUTINE_ID = new AtomicLong(0L);
    }

    @InlineOnly
    /* renamed from: assert  reason: not valid java name */
    private static final void m1216assert(Function0<Boolean> function0) {
        if (getASSERTIONS_ENABLED() && !function0.invoke().booleanValue()) {
            throw new AssertionError();
        }
    }

    public static final boolean getASSERTIONS_ENABLED() {
        return ASSERTIONS_ENABLED;
    }

    @NotNull
    public static final AtomicLong getCOROUTINE_ID() {
        return COROUTINE_ID;
    }

    public static final boolean getDEBUG() {
        return DEBUG;
    }

    public static final boolean getRECOVER_STACK_TRACES() {
        return RECOVER_STACK_TRACES;
    }

    public static final void resetCoroutineId() {
        COROUTINE_ID.set(0L);
    }
}
