package com.jingdong.common.imagegifexpand;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u00078\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\f\u001a\u00020\u00078\u0006@\u0006\u00a2\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\r\u0010\u000bR$\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0017"}, d2 = {"Lcom/jingdong/common/imagegifexpand/AnimateConfig;", "", "", "bitmapConfig565Support", "Z", "getBitmapConfig565Support", "()Z", "", "cachingStrategyType", "I", "getCachingStrategyType", "()I", "numberOfFramesToPrepare", "getNumberOfFramesToPrepare", "Lcom/jingdong/common/imagegifexpand/FramePreparingExecutor;", "framePreparingExecutor", "Lcom/jingdong/common/imagegifexpand/FramePreparingExecutor;", "getFramePreparingExecutor", "()Lcom/jingdong/common/imagegifexpand/FramePreparingExecutor;", "setFramePreparingExecutor", "(Lcom/jingdong/common/imagegifexpand/FramePreparingExecutor;)V", "<init>", "(IIZ)V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class AnimateConfig {
    private final boolean bitmapConfig565Support;
    private final int cachingStrategyType;
    @Nullable
    private FramePreparingExecutor framePreparingExecutor;
    private final int numberOfFramesToPrepare;

    public AnimateConfig(int i2, int i3, boolean z) {
        this.numberOfFramesToPrepare = i2;
        this.cachingStrategyType = i3;
        this.bitmapConfig565Support = z;
    }

    public final boolean getBitmapConfig565Support() {
        return this.bitmapConfig565Support;
    }

    public final int getCachingStrategyType() {
        return this.cachingStrategyType;
    }

    @Nullable
    public final FramePreparingExecutor getFramePreparingExecutor() {
        return this.framePreparingExecutor;
    }

    public final int getNumberOfFramesToPrepare() {
        return this.numberOfFramesToPrepare;
    }

    public final void setFramePreparingExecutor(@Nullable FramePreparingExecutor framePreparingExecutor) {
        this.framePreparingExecutor = framePreparingExecutor;
    }
}
