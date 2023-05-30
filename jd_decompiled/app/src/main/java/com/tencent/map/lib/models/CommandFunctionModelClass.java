package com.tencent.map.lib.models;

import com.tencent.map.lib.models.CommonParamsModelClass;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;

/* loaded from: classes9.dex */
public class CommandFunctionModelClass {

    /* loaded from: classes9.dex */
    public static class BaseCommandFunction extends JsonComposer {
        @Json(name = "function")
        public String commandFunction;
    }

    /* loaded from: classes9.dex */
    public static class EnableClickCommand extends BaseCommandFunction {
        @Json(name = "params")
        public CommonParamsModelClass.EnableClickParams params;
    }

    /* loaded from: classes9.dex */
    public static class ErrorCommandFunction extends BaseCommandFunction {
    }

    /* loaded from: classes9.dex */
    public static class GetClickEnabledCommand extends BaseCommandFunction {
    }

    /* loaded from: classes9.dex */
    public static class GetCurrentMaterialVariantCommand extends BaseCommandFunction {
    }

    /* loaded from: classes9.dex */
    public static class GetExposureCommand extends BaseCommandFunction {
    }

    /* loaded from: classes9.dex */
    public static class GetMaterialVariantsCommand extends BaseCommandFunction {
    }

    /* loaded from: classes9.dex */
    public static class GetPositionCommand extends BaseCommandFunction {
    }

    /* loaded from: classes9.dex */
    public static class GetRotationCommand extends BaseCommandFunction {
    }

    /* loaded from: classes9.dex */
    public static class GetScaleCommand extends BaseCommandFunction {
    }

    /* loaded from: classes9.dex */
    public static class GetSkeletonAnimationInfoCommand extends BaseCommandFunction {
    }

    /* loaded from: classes9.dex */
    public static class GetTypeCommand extends BaseCommandFunction {
    }

    /* loaded from: classes9.dex */
    public static class GetVisibleCommand extends BaseCommandFunction {
    }

    /* loaded from: classes9.dex */
    public static class PlaySkeletonAnimationCommand extends BaseCommandFunction {
        @Json(name = "params")
        public CommonParamsModelClass.PlaySkeletonAnimationParams params;
    }

    /* loaded from: classes9.dex */
    public static class ResetColorCommand extends BaseCommandFunction {
    }

    /* loaded from: classes9.dex */
    public static class SetExposureCommand extends BaseCommandFunction {
        @Json(name = "params")
        public CommonParamsModelClass.ExposureParams params;
    }

    /* loaded from: classes9.dex */
    public static class SetMaterialVariantCommand extends BaseCommandFunction {
        @Json(name = "params")
        public CommonParamsModelClass.MaterialVariantIndexParams params;
    }

    /* loaded from: classes9.dex */
    public static class SetMonoColorCommand extends BaseCommandFunction {
        @Json(name = "params")
        public CommonParamsModelClass.MonoColorParams params;
    }

    /* loaded from: classes9.dex */
    public static class SetPixelBoundCommand extends BaseCommandFunction {
        @Json(name = "params")
        public CommonParamsModelClass.PixelBoundParams params;
    }

    /* loaded from: classes9.dex */
    public static class SetPositionCommand extends BaseCommandFunction {
        @Json(name = "params")
        public CommonParamsModelClass.PositionParams params;
    }

    /* loaded from: classes9.dex */
    public static class SetRotationCommand extends BaseCommandFunction {
        @Json(name = "params")
        public CommonParamsModelClass.RotationParams params;
    }

    /* loaded from: classes9.dex */
    public static class SetScaleCommand extends BaseCommandFunction {
        @Json(name = "params")
        public CommonParamsModelClass.ScaleParams params;
    }

    /* loaded from: classes9.dex */
    public static class SetVisibleCommand extends BaseCommandFunction {
        @Json(name = "params")
        public CommonParamsModelClass.VisibleParams params;
    }

    /* loaded from: classes9.dex */
    public static class SetZoomLevelRangeCommand extends BaseCommandFunction {
        @Json(name = "params")
        public CommonParamsModelClass.ZoomLevelRangeParams params;
    }

    /* loaded from: classes9.dex */
    public static class StartTranslateAnimationCommand extends BaseCommandFunction {
        @Json(name = "params")
        public CommonParamsModelClass.StartTranslateAnimationParams params;
    }

    /* loaded from: classes9.dex */
    public static class StopSkeletonAnimationCommand extends BaseCommandFunction {
    }
}
