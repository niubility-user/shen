package com.jingdong.common.imagegifexpand;

import com.facebook.imagepipeline.animated.factory.AnimatedFactory;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/jingdong/common/imagegifexpand/DefaultAnimateFactoryCreator;", "Lcom/jingdong/common/imagegifexpand/AnimateFactoryCreator;", "Lcom/jingdong/common/imagegifexpand/AnimateConfig;", "animateConfig", "Lcom/facebook/imagepipeline/animated/factory/AnimatedFactory;", "createAnimateFactory", "(Lcom/jingdong/common/imagegifexpand/AnimateConfig;)Lcom/facebook/imagepipeline/animated/factory/AnimatedFactory;", "<init>", "()V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class DefaultAnimateFactoryCreator implements AnimateFactoryCreator {
    @Override // com.jingdong.common.imagegifexpand.AnimateFactoryCreator
    @NotNull
    public AnimatedFactory createAnimateFactory(@NotNull AnimateConfig animateConfig) {
        ImagePipelineFactory imagePipelineFactory = ImagePipelineFactory.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(imagePipelineFactory, "imagePipelineFactory");
        return new DefaultAnimateFactoryImpl(imagePipelineFactory.getPlatformBitmapFactory(), imagePipelineFactory.getBitmapCountingMemoryCache(), animateConfig);
    }
}
