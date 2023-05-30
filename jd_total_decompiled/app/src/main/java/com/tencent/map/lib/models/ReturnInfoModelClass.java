package com.tencent.map.lib.models;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.tencent.map.lib.models.CommonParamsModelClass;
import com.tencent.map.sdk.comps.vis.VisualLayer;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class ReturnInfoModelClass {

    /* loaded from: classes9.dex */
    public static class BaseBooleanReturnInfo extends ReturnStatus {
        public boolean value;

        public BaseBooleanReturnInfo(boolean z) {
            this.status = "success";
            this.value = z;
        }
    }

    /* loaded from: classes9.dex */
    public static class BaseFloatReturnInfo extends ReturnStatus {
        public float value;

        public BaseFloatReturnInfo(float f2) {
            this.status = "success";
            this.value = f2;
        }
    }

    /* loaded from: classes9.dex */
    public static class BaseIntReturnInfo extends ReturnStatus {
        public int value;

        public BaseIntReturnInfo(int i2) {
            this.status = "success";
            this.value = i2;
        }
    }

    /* loaded from: classes9.dex */
    public static class BaseStringReturnInfo extends ReturnStatus {
        public String value;

        public BaseStringReturnInfo(String str) {
            this.status = "success";
            this.value = str;
        }
    }

    /* loaded from: classes9.dex */
    public static class ErrorReturnInfo extends ReturnStatus {
        public int errorCode;
        public String errorMsg;

        /* loaded from: classes9.dex */
        public enum ErrorMsg {
            success(0, "none"),
            jsonparse(1, "json parse error"),
            unsupported(2, "unsupported function"),
            internal(3, VisualLayer.OnLayerStatusChangedListener.LayerStatusMsg.MSG_ERR_INTERNAL_ERROR);
            
            private int errorCode;
            private String errorMsg;

            ErrorMsg(int i2, String str) {
                this.errorCode = i2;
                this.errorMsg = str;
            }

            public int getErrorCode() {
                return this.errorCode;
            }

            public String getErrorMsg() {
                return this.errorMsg;
            }
        }

        public ErrorReturnInfo(ErrorMsg errorMsg) {
            this.status = JDReactConstant.FAILED;
            this.errorCode = errorMsg.getErrorCode();
            this.errorMsg = errorMsg.getErrorMsg();
        }
    }

    /* loaded from: classes9.dex */
    public static class MaterialVariantsReturnInfo extends ReturnStatus {
        public CommonParamsModelClass.MaterialVariantsInfoParams value;

        public MaterialVariantsReturnInfo(List<CommonParamsModelClass.MaterialVariantInfo> list) {
            CommonParamsModelClass.MaterialVariantsInfoParams materialVariantsInfoParams = new CommonParamsModelClass.MaterialVariantsInfoParams();
            this.value = materialVariantsInfoParams;
            materialVariantsInfoParams.materialVariantInfoList = list;
        }
    }

    /* loaded from: classes9.dex */
    public static class PositionReturnInfo extends ReturnStatus {
        public CommonParamsModelClass.PositionParams value;

        public PositionReturnInfo(LatLng latLng) {
            CommonParamsModelClass.PositionParams positionParams = new CommonParamsModelClass.PositionParams();
            this.value = positionParams;
            positionParams.lat = latLng.latitude;
            positionParams.lng = latLng.longitude;
            positionParams.altitude = latLng.altitude;
        }
    }

    /* loaded from: classes9.dex */
    public static class ReturnStatus extends JsonComposer {
        public String status = "success";
    }

    /* loaded from: classes9.dex */
    public static class RotationReturnInfo extends ReturnStatus {
        public CommonParamsModelClass.RotationParams value;

        public RotationReturnInfo(float f2, float f3, float f4) {
            CommonParamsModelClass.RotationParams rotationParams = new CommonParamsModelClass.RotationParams();
            this.value = rotationParams;
            rotationParams.rotationX = f2;
            rotationParams.rotationY = f3;
            rotationParams.rotationZ = f4;
        }
    }

    /* loaded from: classes9.dex */
    public static class SkeletonAnimationReturnInfo extends ReturnStatus {
        public CommonParamsModelClass.SkeletonAnimationParams value;

        public SkeletonAnimationReturnInfo(List<CommonParamsModelClass.AnimationInfo> list) {
            CommonParamsModelClass.SkeletonAnimationParams skeletonAnimationParams = new CommonParamsModelClass.SkeletonAnimationParams();
            this.value = skeletonAnimationParams;
            skeletonAnimationParams.animationInfoList = list == null ? new ArrayList<>() : list;
        }
    }
}
