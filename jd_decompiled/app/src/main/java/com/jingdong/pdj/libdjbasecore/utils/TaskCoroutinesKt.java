package com.jingdong.pdj.libdjbasecore.utils;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u001a=\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\u0001\u001a\u00020\u00002\u001e\b\b\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0002H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0007\u0010\b\u001aG\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\t2\u001e\b\b\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0002H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\f\u001aS\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e\"\u0004\b\u0000\u0010\r2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\t2\u001e\b\b\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0002H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001aM\u0010\u0013\u001a\u00020\u0012\"\u0004\b\u0000\u0010\r2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\u00112\u001e\b\b\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0002H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0014\u001a9\u0010\u0015\u001a\u00020\u0012\"\u0004\b\u0000\u0010\r2\u001e\b\b\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0002H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016\"(\u0010\u0017\u001a\u00020\t8\u0000@\u0000X\u0081\u000e\u00a2\u0006\u0018\n\u0004\b\u0017\u0010\u0018\u0012\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001f"}, d2 = {"", "delayTime", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "job", "taskBlockOnMainThread", "(JLkotlin/jvm/functions/Function1;)V", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "threadPool", "taskBlockOnWorkThread", "(JLkotlinx/coroutines/ExecutorCoroutineDispatcher;Lkotlin/jvm/functions/Function1;)V", "T", "Lkotlinx/coroutines/Deferred;", "taskAsync", "(JLkotlinx/coroutines/ExecutorCoroutineDispatcher;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/Deferred;", "Lkotlin/coroutines/CoroutineContext;", "Lkotlinx/coroutines/Job;", "taskLaunch", "(JLkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/Job;", "taskRunOnUiThread", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/Job;", "ThreadPool", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "getThreadPool", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "setThreadPool", "(Lkotlinx/coroutines/ExecutorCoroutineDispatcher;)V", "ThreadPool$annotations", "()V", "libDJBaseCore_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class TaskCoroutinesKt {
    @NotNull
    private static ExecutorCoroutineDispatcher ThreadPool = ThreadPoolDispatcherKt.newFixedThreadPoolContext(Runtime.getRuntime().availableProcessors() * 2, "ThreadPool");

    @PublishedApi
    public static /* synthetic */ void ThreadPool$annotations() {
    }

    @NotNull
    public static final ExecutorCoroutineDispatcher getThreadPool() {
        return ThreadPool;
    }

    public static final void setThreadPool(@NotNull ExecutorCoroutineDispatcher executorCoroutineDispatcher) {
        ThreadPool = executorCoroutineDispatcher;
    }

    @NotNull
    public static final <T> Deferred<T> taskAsync(long j2, @NotNull ExecutorCoroutineDispatcher executorCoroutineDispatcher, @NotNull Function1<? super Continuation<? super T>, ? extends Object> function1) {
        Deferred<T> async$default;
        async$default = BuildersKt__Builders_commonKt.async$default(GlobalScope.INSTANCE, executorCoroutineDispatcher, null, new TaskCoroutinesKt$taskAsync$1(j2, function1, null), 2, null);
        return async$default;
    }

    public static /* synthetic */ Deferred taskAsync$default(long j2, ExecutorCoroutineDispatcher executorCoroutineDispatcher, Function1 function1, int i2, Object obj) {
        Deferred async$default;
        if ((i2 & 1) != 0) {
            j2 = 0;
        }
        if ((i2 & 2) != 0) {
            executorCoroutineDispatcher = getThreadPool();
        }
        async$default = BuildersKt__Builders_commonKt.async$default(GlobalScope.INSTANCE, executorCoroutineDispatcher, null, new TaskCoroutinesKt$taskAsync$1(j2, function1, null), 2, null);
        return async$default;
    }

    public static final void taskBlockOnMainThread(long j2, @NotNull Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        BuildersKt__BuildersKt.runBlocking$default(null, new TaskCoroutinesKt$taskBlockOnMainThread$1(j2, function1, null), 1, null);
    }

    public static /* synthetic */ void taskBlockOnMainThread$default(long j2, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = 0;
        }
        BuildersKt__BuildersKt.runBlocking$default(null, new TaskCoroutinesKt$taskBlockOnMainThread$1(j2, function1, null), 1, null);
    }

    public static final void taskBlockOnWorkThread(long j2, @NotNull ExecutorCoroutineDispatcher executorCoroutineDispatcher, @NotNull Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        BuildersKt.runBlocking(executorCoroutineDispatcher, new TaskCoroutinesKt$taskBlockOnWorkThread$1(j2, function1, null));
    }

    public static /* synthetic */ void taskBlockOnWorkThread$default(long j2, ExecutorCoroutineDispatcher executorCoroutineDispatcher, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = 0;
        }
        if ((i2 & 2) != 0) {
            executorCoroutineDispatcher = getThreadPool();
        }
        BuildersKt.runBlocking(executorCoroutineDispatcher, new TaskCoroutinesKt$taskBlockOnWorkThread$1(j2, function1, null));
    }

    @NotNull
    public static final <T> Job taskLaunch(long j2, @NotNull CoroutineContext coroutineContext, @NotNull Function1<? super Continuation<? super T>, ? extends Object> function1) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, coroutineContext, null, new TaskCoroutinesKt$taskLaunch$1(function1, null), 2, null);
        return launch$default;
    }

    public static /* synthetic */ Job taskLaunch$default(long j2, CoroutineContext coroutineContext, Function1 function1, int i2, Object obj) {
        Job launch$default;
        int i3 = i2 & 1;
        if ((i2 & 2) != 0) {
            coroutineContext = getThreadPool();
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, coroutineContext, null, new TaskCoroutinesKt$taskLaunch$1(function1, null), 2, null);
        return launch$default;
    }

    @NotNull
    public static final <T> Job taskRunOnUiThread(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new TaskCoroutinesKt$taskRunOnUiThread$1(function1, null), 2, null);
        return launch$default;
    }
}
