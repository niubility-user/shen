package kotlinx.coroutines.debug;

import android.annotation.SuppressLint;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import kotlin.Metadata;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.misc.Signal;
import sun.misc.SignalHandler;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\b\u00c1\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\t\b\u0002\u00a2\u0006\u0004\b\u0012\u0010\nJ!\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/debug/AgentPremain;", "", "", "args", "Ljava/lang/instrument/Instrumentation;", "instrumentation", "", "premain", "(Ljava/lang/String;Ljava/lang/instrument/Instrumentation;)V", "installSignalHandler", "()V", "", "enableCreationStackTraces", "Z", "isInstalledStatically", "()Z", "setInstalledStatically", "(Z)V", "<init>", "DebugProbesTransformer", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
@SuppressLint({NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL})
/* loaded from: classes11.dex */
public final class AgentPremain {
    public static final AgentPremain INSTANCE = new AgentPremain();
    private static final boolean enableCreationStackTraces;
    private static boolean isInstalledStatically;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\b\u00c0\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJA\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016\u00a2\u0006\u0004\b\f\u0010\r\u00a8\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/debug/AgentPremain$DebugProbesTransformer;", "Ljava/lang/instrument/ClassFileTransformer;", "Ljava/lang/ClassLoader;", "loader", "", "className", "Ljava/lang/Class;", "classBeingRedefined", "Ljava/security/ProtectionDomain;", "protectionDomain", "", "classfileBuffer", "transform", "(Ljava/lang/ClassLoader;Ljava/lang/String;Ljava/lang/Class;Ljava/security/ProtectionDomain;[B)[B", "<init>", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class DebugProbesTransformer implements ClassFileTransformer {
        public static final DebugProbesTransformer INSTANCE = new DebugProbesTransformer();

        private DebugProbesTransformer() {
        }

        @Nullable
        public byte[] transform(@NotNull ClassLoader loader, @NotNull String className, @Nullable Class<?> classBeingRedefined, @NotNull ProtectionDomain protectionDomain, @Nullable byte[] classfileBuffer) {
            if ((!Intrinsics.areEqual(className, "kotlin/coroutines/jvm/internal/DebugProbesKt")) == true) {
                return null;
            }
            AgentPremain.INSTANCE.setInstalledStatically(true);
            return ByteStreamsKt.readBytes(loader.getResourceAsStream("DebugProbesKt.bin"));
        }
    }

    static {
        String property = System.getProperty("kotlinx.coroutines.debug.enable.creation.stack.trace");
        enableCreationStackTraces = property != null ? Boolean.parseBoolean(property) : DebugProbesImpl.INSTANCE.getEnableCreationStackTraces();
    }

    private AgentPremain() {
    }

    private final void installSignalHandler() {
        try {
            Signal.handle(new Signal("TRAP"), new SignalHandler() { // from class: kotlinx.coroutines.debug.AgentPremain$installSignalHandler$1
                public final void handle(Signal signal) {
                    DebugProbesImpl debugProbesImpl = DebugProbesImpl.INSTANCE;
                    if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                        debugProbesImpl.dumpCoroutines(System.out);
                    } else {
                        System.out.println((Object) "Cannot perform coroutines dump, debug probes are disabled");
                    }
                }
            });
        } catch (Throwable unused) {
        }
    }

    @JvmStatic
    public static final void premain(@Nullable String args, @NotNull Instrumentation instrumentation) {
        isInstalledStatically = true;
        instrumentation.addTransformer(DebugProbesTransformer.INSTANCE);
        DebugProbesImpl debugProbesImpl = DebugProbesImpl.INSTANCE;
        debugProbesImpl.setEnableCreationStackTraces(enableCreationStackTraces);
        debugProbesImpl.install();
        INSTANCE.installSignalHandler();
    }

    public final boolean isInstalledStatically() {
        return isInstalledStatically;
    }

    public final void setInstalledStatically(boolean z) {
        isInstalledStatically = z;
    }
}
