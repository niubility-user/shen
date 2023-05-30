package com.jd.jdcache.util;

import androidx.annotation.Keep;
import com.jd.jdcache.JDCacheConstant;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.tencent.connect.common.Constants;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J'\u0010\u0006\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0007\u00a2\u0006\u0004\b\u0006\u0010\u0007J'\u0010\n\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\t\u001a\u00020\bH\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ@\u0010\u0011\u001a\u00020\u0010*\u0004\u0018\u00010\u00012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\u001c\u0010\u000f\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000e\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012JI\u0010\u0013\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0002*\u0004\u0018\u00010\u00012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\u001c\u0010\u000f\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/jd/jdcache/util/CoroutineHelper;", "", "T", "Lkotlin/coroutines/Continuation;", "data", "", "onSuccess", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)V", "", "throwable", "onFail", "(Lkotlin/coroutines/Continuation;Ljava/lang/Throwable;)V", "Lkotlinx/coroutines/CoroutineScope;", Constants.PARAM_SCOPE, "Lkotlin/Function1;", JDReactConstant.BLOCK, "Lkotlinx/coroutines/Job;", "launchCoroutine", "(Ljava/lang/Object;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/Job;", "runOnIo", "(Ljava/lang/Object;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class CoroutineHelper {
    public static final CoroutineHelper INSTANCE = new CoroutineHelper();

    private CoroutineHelper() {
    }

    public static /* synthetic */ Job launchCoroutine$default(CoroutineHelper coroutineHelper, Object obj, CoroutineScope coroutineScope, Function1 function1, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            coroutineScope = JDCacheConstant.INSTANCE.getApplicationScope$JDCache_release();
        }
        return coroutineHelper.launchCoroutine(obj, coroutineScope, function1);
    }

    @JvmStatic
    public static final <T> void onFail(@NotNull Continuation<? super T> continuation, @NotNull Throwable th) {
        Result.Companion companion = Result.INSTANCE;
        continuation.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(th)));
    }

    @JvmStatic
    public static final <T> void onSuccess(@NotNull Continuation<? super T> continuation, T t) {
        Result.Companion companion = Result.INSTANCE;
        continuation.resumeWith(Result.m200constructorimpl(t));
    }

    public static /* synthetic */ Object runOnIo$default(CoroutineHelper coroutineHelper, Object obj, CoroutineScope coroutineScope, Function1 function1, Continuation continuation, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            coroutineScope = null;
        }
        return coroutineHelper.runOnIo(obj, coroutineScope, function1, continuation);
    }

    @NotNull
    public final Job launchCoroutine(@Nullable Object obj, @Nullable CoroutineScope coroutineScope, @NotNull Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        Job launch$default;
        if (coroutineScope == null) {
            coroutineScope = JDCacheConstant.INSTANCE.getApplicationScope$JDCache_release();
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new CoroutineHelper$launchCoroutine$1(function1, null), 3, null);
        return launch$default;
    }

    @Nullable
    public final <T> Object runOnIo(@Nullable Object obj, @Nullable CoroutineScope coroutineScope, @NotNull Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
        CoroutineContext ioDispatcher$JDCache_release;
        if (coroutineScope == null || (ioDispatcher$JDCache_release = coroutineScope.getCoroutineContext().plus(JDCacheConstant.INSTANCE.getIoDispatcher$JDCache_release())) == null) {
            ioDispatcher$JDCache_release = JDCacheConstant.INSTANCE.getIoDispatcher$JDCache_release();
        }
        return BuildersKt.withContext(ioDispatcher$JDCache_release, new CoroutineHelper$runOnIo$2(function1, null), continuation);
    }
}
