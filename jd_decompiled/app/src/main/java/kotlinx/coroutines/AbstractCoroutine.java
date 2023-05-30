package kotlinx.coroutines;

import com.facebook.react.modules.appstate.AppStateModule;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.tencent.open.SocialConstants;
import jpbury.t;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@InternalCoroutinesApi
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u00028\u00000\u00042\u00020\u0005B\u0019\u0012\u0006\u00104\u001a\u000200\u0012\b\b\u0002\u0010;\u001a\u00020\u0012\u00a2\u0006\u0004\b<\u0010=J\u000f\u0010\t\u001a\u00020\u0006H\u0000\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\u0006H\u0014\u00a2\u0006\u0004\b\n\u0010\bJ\u000f\u0010\f\u001a\u00020\u0006H\u0000\u00a2\u0006\u0004\b\u000b\u0010\bJ\u0017\u0010\u000e\u001a\u00020\u00062\u0006\u0010\r\u001a\u00028\u0000H\u0014\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0014\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0014\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0019\u0010\u001b\u001a\u00020\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0004\u00a2\u0006\u0004\b\u001b\u0010\u000fJ\u001e\u0010\u001e\u001a\u00020\u00062\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001c\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u000fJ\u0019\u0010\u001f\u001a\u00020\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0014\u00a2\u0006\u0004\b\u001f\u0010\u000fJ\u0017\u0010#\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u0010H\u0000\u00a2\u0006\u0004\b!\u0010\"J\u000f\u0010%\u001a\u00020\u0016H\u0010\u00a2\u0006\u0004\b$\u0010\u0018J6\u0010'\u001a\u00020\u00062\u0006\u0010'\u001a\u00020&2\u001c\u0010)\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00190(\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010*JO\u0010'\u001a\u00020\u0006\"\u0004\b\u0001\u0010+2\u0006\u0010'\u001a\u00020&2\u0006\u0010,\u001a\u00028\u00012'\u0010)\u001a#\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00190-\u00a2\u0006\u0002\b.\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010/R\u0016\u00103\u001a\u0002008V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b1\u00102R\u0016\u00104\u001a\u0002008\u0004@\u0005X\u0085\u0004\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0016\u00106\u001a\u00020\u00128V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b6\u00107R\u001f\u00108\u001a\u0002008\u0006@\u0006\u00a2\u0006\u0012\n\u0004\b8\u00105\u0012\u0004\b:\u0010\b\u001a\u0004\b9\u00102\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006>"}, d2 = {"Lkotlinx/coroutines/AbstractCoroutine;", "T", "Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/Job;", "Lkotlin/coroutines/Continuation;", "Lkotlinx/coroutines/CoroutineScope;", "", "initParentJob$kotlinx_coroutines_core", "()V", "initParentJob", "onStart", "onStartInternal$kotlinx_coroutines_core", "onStartInternal", "value", "onCompleted", "(Ljava/lang/Object;)V", "", "cause", "", "handled", "onCancelled", "(Ljava/lang/Throwable;Z)V", "", "cancellationExceptionMessage", "()Ljava/lang/String;", "", XView2Constants.STATE, "onCompletionInternal", "Lkotlin/Result;", "result", "resumeWith", "afterResume", t.f20145j, "handleOnCompletionException$kotlinx_coroutines_core", "(Ljava/lang/Throwable;)V", "handleOnCompletionException", "nameString$kotlinx_coroutines_core", "nameString", "Lkotlinx/coroutines/CoroutineStart;", "start", "Lkotlin/Function1;", JDReactConstant.BLOCK, "(Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function1;)V", "R", SocialConstants.PARAM_RECEIVER, "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineStart;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "isActive", "()Z", AnnoConst.Constructor_Context, "getContext", "context$annotations", AppStateModule.APP_STATE_ACTIVE, "<init>", "(Lkotlin/coroutines/CoroutineContext;Z)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public abstract class AbstractCoroutine<T> extends JobSupport implements Job, Continuation<T>, CoroutineScope {
    @NotNull
    private final CoroutineContext context;
    @JvmField
    @NotNull
    protected final CoroutineContext parentContext;

    public /* synthetic */ AbstractCoroutine(CoroutineContext coroutineContext, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(coroutineContext, (i2 & 2) != 0 ? true : z);
    }

    public static /* synthetic */ void context$annotations() {
    }

    protected void afterResume(@Nullable Object r1) {
        afterCompletion(r1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.JobSupport
    @NotNull
    public String cancellationExceptionMessage() {
        return DebugStringsKt.getClassSimpleName(this) + " was cancelled";
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public final CoroutineContext getContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void handleOnCompletionException$kotlinx_coroutines_core(@NotNull Throwable r2) {
        CoroutineExceptionHandlerKt.handleCoroutineException(this.context, r2);
    }

    public final void initParentJob$kotlinx_coroutines_core() {
        initParentJobInternal$kotlinx_coroutines_core((Job) this.parentContext.get(Job.INSTANCE));
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public boolean isActive() {
        return super.isActive();
    }

    @Override // kotlinx.coroutines.JobSupport
    @NotNull
    public String nameString$kotlinx_coroutines_core() {
        String coroutineName = CoroutineContextKt.getCoroutineName(this.context);
        if (coroutineName != null) {
            return Typography.quote + coroutineName + "\":" + super.nameString$kotlinx_coroutines_core();
        }
        return super.nameString$kotlinx_coroutines_core();
    }

    protected void onCancelled(@NotNull Throwable cause, boolean handled) {
    }

    protected void onCompleted(T value) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.JobSupport
    protected final void onCompletionInternal(@Nullable Object r2) {
        if (r2 instanceof CompletedExceptionally) {
            CompletedExceptionally completedExceptionally = (CompletedExceptionally) r2;
            onCancelled(completedExceptionally.cause, completedExceptionally.getHandled());
            return;
        }
        onCompleted(r2);
    }

    protected void onStart() {
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void onStartInternal$kotlinx_coroutines_core() {
        onStart();
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(@NotNull Object result) {
        Object makeCompletingOnce$kotlinx_coroutines_core = makeCompletingOnce$kotlinx_coroutines_core(CompletedExceptionallyKt.toState(result));
        if (makeCompletingOnce$kotlinx_coroutines_core == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return;
        }
        afterResume(makeCompletingOnce$kotlinx_coroutines_core);
    }

    public final void start(@NotNull CoroutineStart start, @NotNull Function1<? super Continuation<? super T>, ? extends Object> r2) {
        initParentJob$kotlinx_coroutines_core();
        start.invoke(r2, this);
    }

    public AbstractCoroutine(@NotNull CoroutineContext coroutineContext, boolean z) {
        super(z);
        this.parentContext = coroutineContext;
        this.context = coroutineContext.plus(this);
    }

    public final <R> void start(@NotNull CoroutineStart start, R r2, @NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> r3) {
        initParentJob$kotlinx_coroutines_core();
        start.invoke(r3, r2, this);
    }
}
