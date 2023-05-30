package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultScheduler;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0014\u0010\bR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\r\u001a\u00020\t8F@\u0007X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\f\u0010\b\u001a\u0004\b\n\u0010\u000bR\"\u0010\u000e\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0004\u0012\u0004\b\u0010\u0010\b\u001a\u0004\b\u000f\u0010\u0006R\"\u0010\u0011\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0004\u0012\u0004\b\u0013\u0010\b\u001a\u0004\b\u0012\u0010\u0006\u00a8\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/Dispatchers;", "", "Lkotlinx/coroutines/CoroutineDispatcher;", "Default", "Lkotlinx/coroutines/CoroutineDispatcher;", "getDefault", "()Lkotlinx/coroutines/CoroutineDispatcher;", "Default$annotations", "()V", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "getMain", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "Main$annotations", "Main", "Unconfined", "getUnconfined", "Unconfined$annotations", "IO", "getIO", "IO$annotations", "<init>", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class Dispatchers {
    public static final Dispatchers INSTANCE = new Dispatchers();
    @NotNull
    private static final CoroutineDispatcher Default = CoroutineContextKt.createDefaultDispatcher();
    @NotNull
    private static final CoroutineDispatcher Unconfined = Unconfined.INSTANCE;
    @NotNull
    private static final CoroutineDispatcher IO = DefaultScheduler.INSTANCE.getIO();

    private Dispatchers() {
    }

    @JvmStatic
    public static /* synthetic */ void Default$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void IO$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void Main$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void Unconfined$annotations() {
    }

    @NotNull
    public static final CoroutineDispatcher getDefault() {
        return Default;
    }

    @NotNull
    public static final CoroutineDispatcher getIO() {
        return IO;
    }

    @NotNull
    public static final MainCoroutineDispatcher getMain() {
        return MainDispatcherLoader.dispatcher;
    }

    @NotNull
    public static final CoroutineDispatcher getUnconfined() {
        return Unconfined;
    }
}
