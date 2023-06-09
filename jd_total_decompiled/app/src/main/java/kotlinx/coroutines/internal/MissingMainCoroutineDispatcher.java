package kotlinx.coroutines.internal;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\b\u0010!\u001a\u0004\u0018\u00010 \u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u001d\u00a2\u0006\u0004\b(\u0010)J\u000f\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u000b2\n\u0010\u0013\u001a\u00060\u0011j\u0002`\u0012H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J#\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00062\n\u0010\u0013\u001a\u00060\u0011j\u0002`\u0012H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J%\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\u0019H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fR\u0018\u0010!\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0018\u0010#\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010'\u001a\u00020\u00018V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b%\u0010&\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006*"}, d2 = {"Lkotlinx/coroutines/internal/MissingMainCoroutineDispatcher;", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "", "missing", "()Ljava/lang/Void;", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "", "isDispatchNeeded", "(Lkotlin/coroutines/CoroutineContext;)Z", "", "time", "", "delay", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "timeMillis", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", JDReactConstant.BLOCK, "Lkotlinx/coroutines/DisposableHandle;", "invokeOnTimeout", "(JLjava/lang/Runnable;)Lkotlinx/coroutines/DisposableHandle;", "dispatch", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)Ljava/lang/Void;", "Lkotlinx/coroutines/CancellableContinuation;", "continuation", "scheduleResumeAfterDelay", "(JLkotlinx/coroutines/CancellableContinuation;)Ljava/lang/Void;", "", "toString", "()Ljava/lang/String;", "", "cause", "Ljava/lang/Throwable;", "errorHint", "Ljava/lang/String;", "getImmediate", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "immediate", "<init>", "(Ljava/lang/Throwable;Ljava/lang/String;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class MissingMainCoroutineDispatcher extends MainCoroutineDispatcher implements Delay {
    private final Throwable cause;
    private final String errorHint;

    public /* synthetic */ MissingMainCoroutineDispatcher(Throwable th, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(th, (i2 & 2) != 0 ? null : str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0023, code lost:
        if (r1 != null) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Void missing() {
        String str;
        if (this.cause != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Module with the Main dispatcher had failed to initialize");
            String str2 = this.errorHint;
            if (str2 != null) {
                str = ". " + str2;
            }
            str = "";
            sb.append((Object) str);
            throw new IllegalStateException(sb.toString(), this.cause);
        }
        MainDispatchersKt.throwMissingMainDispatcherException();
        throw null;
    }

    @Override // kotlinx.coroutines.Delay
    @Nullable
    public Object delay(long j2, @NotNull Continuation<? super Unit> continuation) {
        missing();
        throw null;
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher
    @NotNull
    public MainCoroutineDispatcher getImmediate() {
        return this;
    }

    @Override // kotlinx.coroutines.Delay
    @NotNull
    public DisposableHandle invokeOnTimeout(long timeMillis, @NotNull Runnable block) {
        missing();
        throw null;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public boolean isDispatchNeeded(@NotNull CoroutineContext context) {
        missing();
        throw null;
    }

    @Override // kotlinx.coroutines.Delay
    /* renamed from: scheduleResumeAfterDelay  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ void mo1255scheduleResumeAfterDelay(long j2, CancellableContinuation cancellableContinuation) {
        scheduleResumeAfterDelay(j2, (CancellableContinuation<? super Unit>) cancellableContinuation);
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher, kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Dispatchers.Main[missing");
        if (this.cause != null) {
            str = ", cause=" + this.cause;
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(']');
        return sb.toString();
    }

    public MissingMainCoroutineDispatcher(@Nullable Throwable th, @Nullable String str) {
        this.cause = th;
        this.errorHint = str;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    /* renamed from: dispatch  reason: merged with bridge method [inline-methods] */
    public Void mo1254dispatch(@NotNull CoroutineContext context, @NotNull Runnable block) {
        missing();
        throw null;
    }

    @NotNull
    public Void scheduleResumeAfterDelay(long timeMillis, @NotNull CancellableContinuation<? super Unit> continuation) {
        missing();
        throw null;
    }
}
