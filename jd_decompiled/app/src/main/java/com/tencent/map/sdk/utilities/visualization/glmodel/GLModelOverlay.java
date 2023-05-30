package com.tencent.map.sdk.utilities.visualization.glmodel;

import com.tencent.map.lib.models.CommonParamsModelClass;
import com.tencent.tencentmap.mapsdk.maps.model.IAnimatorModel;
import com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay;
import java.util.List;

/* loaded from: classes9.dex */
public interface GLModelOverlay extends IAnimatorModel, VectorOverlay {
    int getCurrentMaterialVariant();

    List<CommonParamsModelClass.MaterialVariantInfo> getMaterialVariants();

    List<CommonParamsModelClass.AnimationInfo> getSkeletonAnimationProperties();

    void playSkeletonAnimation(int i2, float f2, boolean z);

    void resetMonoColor();

    void setMaterialVariant(int i2);

    void setMonoColor(CommonParamsModelClass.MonoColorParams monoColorParams);

    void stopSkeletonAnimation();
}
