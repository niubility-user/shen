package com.tencent.mapsdk.internal;

import com.tencent.map.lib.models.CommandFunctionModelClass;
import com.tencent.map.lib.models.CommonParamsModelClass;
import com.tencent.map.lib.models.ReturnInfoModelClass;
import com.tencent.map.sdk.utilities.visualization.glmodel.GLModelOverlay;
import com.tencent.map.sdk.utilities.visualization.glmodel.GLModelOverlayProvider;
import com.tencent.mapsdk.internal.tc;
import com.tencent.tencentmap.mapsdk.maps.model.GeneralTranslateAnimator;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import java.util.List;

/* loaded from: classes9.dex */
public class ed extends sc<dd> implements GLModelOverlay {

    /* renamed from: k */
    private GeneralTranslateAnimator f16471k;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ tc.a a;

        public a(tc.a aVar) {
            ed.this = r1;
            this.a = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((dd) ed.this.f17245j).getOutterVectorOverlayClickListener() != null) {
                ((dd) ed.this.f17245j).getOutterVectorOverlayClickListener().onClicked(this.a.getPosition(), this.a.getIdentifier(), this.a.getName());
            }
        }
    }

    public ed(cd cdVar, dd ddVar) {
        super(cdVar, ddVar);
    }

    @Override // com.tencent.mapsdk.internal.sc
    public void a(long j2) {
        this.f17243h = j2;
    }

    public void a(tc.a aVar) {
        ba.b(new a(aVar));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public void enableClick(boolean z) {
        if (((dd) this.f17245j).a() != null) {
            ((dd) this.f17245j).a().enableClick(z);
        }
        ((dd) this.f17245j).enableClick(z);
        a((ed) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public ReturnInfoModelClass.ReturnStatus executeCommandFunction(CommandFunctionModelClass.BaseCommandFunction baseCommandFunction) {
        List<LatLng> list;
        if (baseCommandFunction == null) {
            return new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.jsonparse);
        }
        String name = baseCommandFunction.getClass().getName();
        if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$SetZoomLevelRangeCommand")) {
            CommonParamsModelClass.ZoomLevelRangeParams zoomLevelRangeParams = ((CommandFunctionModelClass.SetZoomLevelRangeCommand) baseCommandFunction).params;
            if (zoomLevelRangeParams == null) {
                return new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.jsonparse);
            }
            ((dd) this.f17245j).setZoomLevelRange(zoomLevelRangeParams.minLevel, zoomLevelRangeParams.maxLevel);
            a((ed) this.f17245j);
            return new ReturnInfoModelClass.ReturnStatus();
        } else if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$StartTranslateAnimationCommand")) {
            CommandFunctionModelClass.StartTranslateAnimationCommand startTranslateAnimationCommand = (CommandFunctionModelClass.StartTranslateAnimationCommand) baseCommandFunction;
            CommonParamsModelClass.StartTranslateAnimationParams startTranslateAnimationParams = startTranslateAnimationCommand.params;
            if (startTranslateAnimationParams != null && (list = startTranslateAnimationParams.positions) != null) {
                this.f16471k = new GeneralTranslateAnimator.Builder(this, startTranslateAnimationParams.duration * 1000.0f, (LatLng[]) list.toArray(new LatLng[0])).rotateEnabled(startTranslateAnimationCommand.params.needRotate).modelType(GeneralTranslateAnimator.ModelType.MODEL_OVERLAY).initRotate(startTranslateAnimationCommand.params.initRotation).build();
                if (((dd) this.f17245j).a().getTransAnimatorEndListener() != null) {
                    this.f16471k.addAnimatorEndListener(((dd) this.f17245j).a().getTransAnimatorEndListener());
                }
                this.f16471k.startAnimation();
                return new ReturnInfoModelClass.ReturnStatus();
            }
            return new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.jsonparse);
        } else if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$SetRotationCommand")) {
            CommonParamsModelClass.RotationParams rotationParams = ((CommandFunctionModelClass.SetRotationCommand) baseCommandFunction).params;
            if (rotationParams == null) {
                return new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.jsonparse);
            }
            float f2 = rotationParams.rotationX;
            float f3 = rotationParams.rotationY;
            float f4 = rotationParams.rotationZ;
            if (((dd) this.f17245j).a() != null) {
                ((dd) this.f17245j).a().rotationX(f2).rotationY(f3).rotationZ(f4);
            }
            ((dd) this.f17245j).setRotationX(f2);
            ((dd) this.f17245j).setRotationY(f3);
            ((dd) this.f17245j).setRotationZ(f4);
            a((ed) this.f17245j);
            return new ReturnInfoModelClass.ReturnStatus();
        } else if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$SetExposureCommand")) {
            CommandFunctionModelClass.SetExposureCommand setExposureCommand = (CommandFunctionModelClass.SetExposureCommand) baseCommandFunction;
            if (setExposureCommand.params == null) {
                return new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.jsonparse);
            }
            if (((dd) this.f17245j).a() != null) {
                ((dd) this.f17245j).a().setExposure(setExposureCommand.params.exposure);
            }
            ((dd) this.f17245j).setExposure(setExposureCommand.params.exposure);
            a((ed) this.f17245j);
            return new ReturnInfoModelClass.ReturnStatus();
        } else if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$SetVisibleCommand")) {
            CommonParamsModelClass.VisibleParams visibleParams = ((CommandFunctionModelClass.SetVisibleCommand) baseCommandFunction).params;
            if (visibleParams == null) {
                return new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.jsonparse);
            }
            setVisibility(visibleParams.isVisible);
            return new ReturnInfoModelClass.ReturnStatus();
        } else if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$GetCurrentMaterialVariantCommand")) {
            return new ReturnInfoModelClass.BaseIntReturnInfo(getCurrentMaterialVariant());
        } else {
            if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$ErrorCommandFunction")) {
                return new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.unsupported);
            }
            if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$SetMaterialVariantCommand")) {
                CommonParamsModelClass.MaterialVariantIndexParams materialVariantIndexParams = ((CommandFunctionModelClass.SetMaterialVariantCommand) baseCommandFunction).params;
                if (materialVariantIndexParams == null) {
                    return new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.jsonparse);
                }
                setMaterialVariant(materialVariantIndexParams.variantIndex);
                return new ReturnInfoModelClass.ReturnStatus();
            } else if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$GetVisibleCommand")) {
                return new ReturnInfoModelClass.BaseBooleanReturnInfo(((dd) this.f17245j).isVisible());
            } else {
                if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$GetMaterialVariantsCommand")) {
                    return new ReturnInfoModelClass.MaterialVariantsReturnInfo(getMaterialVariants());
                }
                if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$SetPixelBoundCommand")) {
                    CommandFunctionModelClass.SetPixelBoundCommand setPixelBoundCommand = (CommandFunctionModelClass.SetPixelBoundCommand) baseCommandFunction;
                    if (setPixelBoundCommand.params == null) {
                        return new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.jsonparse);
                    }
                    if (((dd) this.f17245j).a() != null) {
                        ((dd) this.f17245j).a().coordType(GLModelOverlayProvider.CoordType.PixelType);
                        GLModelOverlayProvider a2 = ((dd) this.f17245j).a();
                        CommonParamsModelClass.PixelBoundParams pixelBoundParams = setPixelBoundCommand.params;
                        a2.pixelBounds(pixelBoundParams.width, pixelBoundParams.height);
                    }
                    GLModelOverlayProvider.CoordType coordType = GLModelOverlayProvider.CoordType.PixelType;
                    ((dd) this.f17245j).setCoordType(0);
                    CommonParamsModelClass.PixelBoundParams pixelBoundParams2 = setPixelBoundCommand.params;
                    ((dd) this.f17245j).setPixelBound(pixelBoundParams2.width, pixelBoundParams2.height);
                    a((ed) this.f17245j);
                    return new ReturnInfoModelClass.ReturnStatus();
                } else if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$GetPositionCommand")) {
                    return new ReturnInfoModelClass.PositionReturnInfo(((dd) this.f17245j).getPosition());
                } else {
                    if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$GetClickEnabledCommand")) {
                        return new ReturnInfoModelClass.BaseBooleanReturnInfo(isClickEnabled());
                    }
                    if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$GetScaleCommand")) {
                        return new ReturnInfoModelClass.BaseFloatReturnInfo((float) ((dd) this.f17245j).getScale());
                    }
                    if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$ResetColorCommand")) {
                        resetMonoColor();
                        return new ReturnInfoModelClass.ReturnStatus();
                    } else if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$GetSkeletonAnimationInfoCommand")) {
                        return new ReturnInfoModelClass.SkeletonAnimationReturnInfo(getSkeletonAnimationProperties());
                    } else {
                        if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$SetPositionCommand")) {
                            CommandFunctionModelClass.SetPositionCommand setPositionCommand = (CommandFunctionModelClass.SetPositionCommand) baseCommandFunction;
                            if (setPositionCommand.params == null) {
                                return new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.jsonparse);
                            }
                            CommonParamsModelClass.PositionParams positionParams = setPositionCommand.params;
                            LatLng latLng = new LatLng(positionParams.lat, positionParams.lng, positionParams.altitude);
                            if (((dd) this.f17245j).a() != null) {
                                ((dd) this.f17245j).a().position(latLng);
                            }
                            ((dd) this.f17245j).setModelPosition(latLng);
                            a((ed) this.f17245j);
                            return new ReturnInfoModelClass.ReturnStatus();
                        } else if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$EnableClickCommand")) {
                            CommonParamsModelClass.EnableClickParams enableClickParams = ((CommandFunctionModelClass.EnableClickCommand) baseCommandFunction).params;
                            if (enableClickParams == null) {
                                return new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.jsonparse);
                            }
                            enableClick(enableClickParams.enabled);
                            return new ReturnInfoModelClass.ReturnStatus();
                        } else if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$SetScaleCommand")) {
                            CommonParamsModelClass.ScaleParams scaleParams = ((CommandFunctionModelClass.SetScaleCommand) baseCommandFunction).params;
                            if (scaleParams == null) {
                                return new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.jsonparse);
                            }
                            float f5 = scaleParams.scale;
                            if (((dd) this.f17245j).a() != null) {
                                ((dd) this.f17245j).a().coordType(GLModelOverlayProvider.CoordType.GeoGraphicType);
                                ((dd) this.f17245j).a().scale(f5);
                            }
                            GLModelOverlayProvider.CoordType coordType2 = GLModelOverlayProvider.CoordType.GeoGraphicType;
                            ((dd) this.f17245j).setCoordType(1);
                            ((dd) this.f17245j).setScale(f5);
                            a((ed) this.f17245j);
                            return new ReturnInfoModelClass.ReturnStatus();
                        } else if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$GetTypeCommand")) {
                            return new ReturnInfoModelClass.BaseStringReturnInfo(getType());
                        } else {
                            if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$PlaySkeletonAnimationCommand")) {
                                CommonParamsModelClass.PlaySkeletonAnimationParams playSkeletonAnimationParams = ((CommandFunctionModelClass.PlaySkeletonAnimationCommand) baseCommandFunction).params;
                                if (playSkeletonAnimationParams == null) {
                                    return new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.jsonparse);
                                }
                                playSkeletonAnimation(playSkeletonAnimationParams.index, playSkeletonAnimationParams.speed, playSkeletonAnimationParams.repeat);
                                return new ReturnInfoModelClass.ReturnStatus();
                            } else if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$SetMonoColorCommand")) {
                                CommonParamsModelClass.MonoColorParams monoColorParams = ((CommandFunctionModelClass.SetMonoColorCommand) baseCommandFunction).params;
                                if (monoColorParams == null) {
                                    return new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.jsonparse);
                                }
                                setMonoColor(monoColorParams);
                                return new ReturnInfoModelClass.ReturnStatus();
                            } else if (name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$GetRotationCommand")) {
                                return new ReturnInfoModelClass.RotationReturnInfo(((dd) this.f17245j).getRotationX(), ((dd) this.f17245j).getRotationY(), ((dd) this.f17245j).getRotationZ());
                            } else {
                                if (!name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$StopSkeletonAnimationCommand")) {
                                    return !name.equals("com.tencent.map.lib.models.CommandFunctionModelClass$GetExposureCommand") ? new ReturnInfoModelClass.ErrorReturnInfo(ReturnInfoModelClass.ErrorReturnInfo.ErrorMsg.unsupported) : new ReturnInfoModelClass.BaseFloatReturnInfo(((dd) this.f17245j).getExposure());
                                }
                                stopSkeletonAnimation();
                                return new ReturnInfoModelClass.ReturnStatus();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // com.tencent.map.sdk.utilities.visualization.glmodel.GLModelOverlay
    public int getCurrentMaterialVariant() {
        Object obj = this.f17244i;
        if (obj instanceof cd) {
            return ((cd) obj).b(this.f17243h);
        }
        return 0;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.glmodel.GLModelOverlay
    public List<CommonParamsModelClass.MaterialVariantInfo> getMaterialVariants() {
        Object obj = this.f17244i;
        if (obj instanceof cd) {
            return ((cd) obj).c(this.f17243h);
        }
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IAnimatorModel
    public float getRotation() {
        return ((dd) this.f17245j).getRotationY();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.glmodel.GLModelOverlay
    public List<CommonParamsModelClass.AnimationInfo> getSkeletonAnimationProperties() {
        Object obj = this.f17244i;
        if (obj instanceof cd) {
            return ((cd) obj).a(this.f17243h);
        }
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public String getType() {
        return z3.GLModel.a();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public boolean isClickEnabled() {
        return ((dd) this.f17245j).isClickEnabled();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.glmodel.GLModelOverlay
    public void playSkeletonAnimation(int i2, float f2, boolean z) {
        ((cd) this.f17244i).a(this.f17243h, i2, f2, z);
    }

    @Override // com.tencent.map.sdk.utilities.visualization.glmodel.GLModelOverlay
    public void resetMonoColor() {
        Object obj = this.f17244i;
        if (obj instanceof cd) {
            ((cd) obj).d(this.f17243h);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public void setLevel(int i2) {
        if (((dd) this.f17245j).a() != null) {
            ((dd) this.f17245j).a().displayLevel(i2);
        }
        ((dd) this.f17245j).setLevel(i2);
        a((ed) this.f17245j);
    }

    @Override // com.tencent.map.sdk.utilities.visualization.glmodel.GLModelOverlay
    public void setMaterialVariant(int i2) {
        Object obj = this.f17244i;
        if (obj instanceof cd) {
            ((cd) obj).a(this.f17243h, i2);
        }
    }

    @Override // com.tencent.map.sdk.utilities.visualization.glmodel.GLModelOverlay
    public void setMonoColor(CommonParamsModelClass.MonoColorParams monoColorParams) {
        Object obj = this.f17244i;
        if ((obj instanceof cd) && monoColorParams != null) {
            ((cd) obj).a(this.f17243h, monoColorParams.r, monoColorParams.f16188g, monoColorParams.b);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public void setOpacity(float f2) {
        if (((dd) this.f17245j).a() != null) {
            ((dd) this.f17245j).a().opacity(f2);
        }
        ((dd) this.f17245j).setOpacity(f2);
        a((ed) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IAnimatorModel
    public void setPosition(LatLng latLng) {
        if (((dd) this.f17245j).a() != null) {
            ((dd) this.f17245j).a().position(latLng);
        }
        ((dd) this.f17245j).setModelPosition(latLng);
        a((ed) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IAnimatorModel
    public void setRotation(float f2) {
        if (((dd) this.f17245j).a() != null) {
            ((dd) this.f17245j).a().rotationY(f2);
        }
        ((dd) this.f17245j).setRotationY(f2);
        a((ed) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public void setVisibility(boolean z) {
        if (((dd) this.f17245j).a() != null) {
            ((dd) this.f17245j).a().visibility(z);
        }
        ((dd) this.f17245j).setVisible(z);
        a((ed) this.f17245j);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public void setZIndex(int i2) {
        if (((dd) this.f17245j).a() != null) {
            ((dd) this.f17245j).a().zIndex(i2);
        }
        ((dd) this.f17245j).setzIndex(i2);
        a((ed) this.f17245j);
    }

    @Override // com.tencent.map.sdk.utilities.visualization.glmodel.GLModelOverlay
    public void stopSkeletonAnimation() {
        ((cd) this.f17244i).e(this.f17243h);
    }

    public void y() {
        GeneralTranslateAnimator generalTranslateAnimator = this.f16471k;
        if (generalTranslateAnimator != null) {
            generalTranslateAnimator.cancelAnimation();
            this.f16471k.removeAnimatorEndListener(((dd) this.f17245j).a().getTransAnimatorEndListener());
        }
    }
}
