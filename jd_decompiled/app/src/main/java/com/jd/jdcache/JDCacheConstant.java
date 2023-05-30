package com.jd.jdcache;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c0\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0007\u001a\u00020\u00028@@\u0000X\u0080\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001d\u0010\f\u001a\u00020\b8@@\u0000X\u0080\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u000e\u001a\u00020\r8\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0014\u001a\u00020\u00108@@\u0000X\u0080\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0015\u001a\u00020\r8\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u000f\u00a8\u0006\u0018"}, d2 = {"Lcom/jd/jdcache/JDCacheConstant;", "", "Lkotlinx/coroutines/CoroutineDispatcher;", "ioDispatcher$delegate", "Lkotlin/Lazy;", "getIoDispatcher$JDCache_release", "()Lkotlinx/coroutines/CoroutineDispatcher;", "ioDispatcher", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "mainDispatcher$delegate", "getMainDispatcher$JDCache_release", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "mainDispatcher", "", "NET_READ_BUFFER_SIZE", "I", "Lkotlinx/coroutines/CoroutineScope;", "applicationScope$delegate", "getApplicationScope$JDCache_release", "()Lkotlinx/coroutines/CoroutineScope;", "applicationScope", "LOCAL_READ_BUFFER_SIZE", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class JDCacheConstant {
    public static final JDCacheConstant INSTANCE = new JDCacheConstant();
    public static final int LOCAL_READ_BUFFER_SIZE = 2048;
    public static final int NET_READ_BUFFER_SIZE = 10240;
    @NotNull

    /* renamed from: applicationScope$delegate  reason: from kotlin metadata */
    private static final Lazy applicationScope;
    @NotNull

    /* renamed from: ioDispatcher$delegate  reason: from kotlin metadata */
    private static final Lazy ioDispatcher;
    @NotNull

    /* renamed from: mainDispatcher$delegate  reason: from kotlin metadata */
    private static final Lazy mainDispatcher;

    static {
        Lazy lazy;
        Lazy lazy2;
        Lazy lazy3;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineScope>() { // from class: com.jd.jdcache.JDCacheConstant$applicationScope$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final CoroutineScope invoke() {
                return CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null));
            }
        });
        applicationScope = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineDispatcher>() { // from class: com.jd.jdcache.JDCacheConstant$ioDispatcher$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final CoroutineDispatcher invoke() {
                return Dispatchers.getIO();
            }
        });
        ioDispatcher = lazy2;
        lazy3 = LazyKt__LazyJVMKt.lazy(new Function0<MainCoroutineDispatcher>() { // from class: com.jd.jdcache.JDCacheConstant$mainDispatcher$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final MainCoroutineDispatcher invoke() {
                return Dispatchers.getMain().getImmediate();
            }
        });
        mainDispatcher = lazy3;
    }

    private JDCacheConstant() {
    }

    @NotNull
    public final CoroutineScope getApplicationScope$JDCache_release() {
        return (CoroutineScope) applicationScope.getValue();
    }

    @NotNull
    public final CoroutineDispatcher getIoDispatcher$JDCache_release() {
        return (CoroutineDispatcher) ioDispatcher.getValue();
    }

    @NotNull
    public final MainCoroutineDispatcher getMainDispatcher$JDCache_release() {
        return (MainCoroutineDispatcher) mainDispatcher.getValue();
    }
}
