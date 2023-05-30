package com.jingdong.common.imagegifexpand;

import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \n2\u00020\u0001:\u0002\n\u000bB\t\b\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004R\u001e\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00020\u00028\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\f"}, d2 = {"Lcom/jingdong/common/imagegifexpand/DefaultAnimateExecutor;", "Lcom/jingdong/common/imagegifexpand/FramePreparingExecutor;", "Ljava/util/concurrent/ExecutorService;", IMantoServerRequester.GET, "()Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "executor", "Ljava/util/concurrent/ExecutorService;", "<init>", "()V", "Companion", "InstanceHelper", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class DefaultAnimateExecutor implements FramePreparingExecutor {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ExecutorService executor;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0007"}, d2 = {"Lcom/jingdong/common/imagegifexpand/DefaultAnimateExecutor$Companion;", "", "Lcom/jingdong/common/imagegifexpand/DefaultAnimateExecutor;", "getInstance", "()Lcom/jingdong/common/imagegifexpand/DefaultAnimateExecutor;", "<init>", "()V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final DefaultAnimateExecutor getInstance() {
            return InstanceHelper.INSTANCE.getSSingle();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/jingdong/common/imagegifexpand/DefaultAnimateExecutor$InstanceHelper;", "", "Lcom/jingdong/common/imagegifexpand/DefaultAnimateExecutor;", "sSingle", "Lcom/jingdong/common/imagegifexpand/DefaultAnimateExecutor;", "getSSingle", "()Lcom/jingdong/common/imagegifexpand/DefaultAnimateExecutor;", "<init>", "()V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes5.dex */
    public static final class InstanceHelper {
        public static final InstanceHelper INSTANCE = new InstanceHelper();
        @NotNull
        private static final DefaultAnimateExecutor sSingle = new DefaultAnimateExecutor(null);

        private InstanceHelper() {
        }

        @NotNull
        public final DefaultAnimateExecutor getSSingle() {
            return sSingle;
        }
    }

    private DefaultAnimateExecutor() {
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override // com.jingdong.common.imagegifexpand.FramePreparingExecutor
    @NotNull
    public ExecutorService get() {
        ExecutorService executor = this.executor;
        Intrinsics.checkExpressionValueIsNotNull(executor, "executor");
        return executor;
    }

    public /* synthetic */ DefaultAnimateExecutor(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
