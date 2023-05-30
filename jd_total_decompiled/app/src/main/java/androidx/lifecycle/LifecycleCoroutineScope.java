package androidx.lifecycle;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\t\b\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012J9\u0010\t\u001a\u00020\b2'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0002\u00a2\u0006\u0002\b\u0006\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\nJ9\u0010\u000b\u001a\u00020\b2'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0002\u00a2\u0006\u0002\b\u0006\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\nJ9\u0010\f\u001a\u00020\b2'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0002\u00a2\u0006\u0002\b\u0006\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\nR\u0016\u0010\u0010\u001a\u00020\r8 @ X\u00a0\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Landroidx/lifecycle/LifecycleCoroutineScope;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", JDReactConstant.BLOCK, "Lkotlinx/coroutines/Job;", "launchWhenCreated", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "launchWhenStarted", "launchWhenResumed", "Landroidx/lifecycle/Lifecycle;", "getLifecycle$lifecycle_runtime_ktx_release", "()Landroidx/lifecycle/Lifecycle;", "lifecycle", "<init>", "()V", "lifecycle-runtime-ktx_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public abstract class LifecycleCoroutineScope implements CoroutineScope {
    @NotNull
    /* renamed from: getLifecycle$lifecycle_runtime_ktx_release */
    public abstract Lifecycle getLifecycle();

    @NotNull
    public final Job launchWhenCreated(@NotNull Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> r7) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(this, null, null, new LifecycleCoroutineScope$launchWhenCreated$1(this, r7, null), 3, null);
        return launch$default;
    }

    @NotNull
    public final Job launchWhenResumed(@NotNull Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> r7) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(this, null, null, new LifecycleCoroutineScope$launchWhenResumed$1(this, r7, null), 3, null);
        return launch$default;
    }

    @NotNull
    public final Job launchWhenStarted(@NotNull Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> r7) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(this, null, null, new LifecycleCoroutineScope$launchWhenStarted$1(this, r7, null), 3, null);
        return launch$default;
    }
}
