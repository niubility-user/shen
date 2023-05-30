package com.jingdong.common.imagegifexpand;

import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/jingdong/common/imagegifexpand/FramePreparingExecutor;", "", "Ljava/util/concurrent/ExecutorService;", IMantoServerRequester.GET, "()Ljava/util/concurrent/ExecutorService;", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public interface FramePreparingExecutor {
    @NotNull
    ExecutorService get();
}
