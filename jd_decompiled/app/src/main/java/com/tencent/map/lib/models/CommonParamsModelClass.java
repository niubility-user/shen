package com.tencent.map.lib.models;

import com.facebook.react.uimanager.ViewProps;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.XView2.common.XView2Constants;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngListDeserializer;
import java.util.List;

/* loaded from: classes9.dex */
public class CommonParamsModelClass {

    /* loaded from: classes9.dex */
    public static class AnimationInfo extends JsonComposer {
        @Json(name = "duration")
        public float duration;
        @Json(name = "index")
        public int index;
        @Json(name = "name")
        public String name;

        public AnimationInfo(int i2, String str, float f2) {
            this.index = i2;
            this.name = str;
            this.duration = f2;
        }
    }

    /* loaded from: classes9.dex */
    public static class EnableClickParams extends JsonComposer {
        @Json(name = ViewProps.ENABLED)
        public boolean enabled;
    }

    /* loaded from: classes9.dex */
    public static class ExposureParams extends JsonComposer {
        @Json(name = "exposure")
        public float exposure;
    }

    /* loaded from: classes9.dex */
    public static class MaterialVariantIndexParams extends JsonComposer {
        @Json(name = "index")
        public int variantIndex;
    }

    /* loaded from: classes9.dex */
    public static class MaterialVariantInfo extends JsonComposer {
        @Json(name = "name")
        public String variantName;
    }

    /* loaded from: classes9.dex */
    public static class MaterialVariantsInfoParams extends JsonComposer {
        @Json(name = "materialVariantsInfo")
        public List<MaterialVariantInfo> materialVariantInfoList;
    }

    /* loaded from: classes9.dex */
    public static class MonoColorParams extends JsonComposer {
        @Json(name = "b")
        public float b;
        @Json(name = "g")

        /* renamed from: g  reason: collision with root package name */
        public float f16188g;
        @Json(name = "r")
        public float r;
    }

    /* loaded from: classes9.dex */
    public static class PixelBoundParams extends JsonComposer {
        @Json(name = "height")
        public int height;
        @Json(name = "width")
        public int width;
    }

    /* loaded from: classes9.dex */
    public static class PlaySkeletonAnimationParams extends JsonComposer {
        @Json(name = "index")
        public int index;
        @Json(name = "repeat")
        public boolean repeat;
        @Json(name = "speed")
        public float speed;
    }

    /* loaded from: classes9.dex */
    public static class PositionParams extends JsonComposer {
        @Json(name = "altitude")
        public double altitude;
        @Json(name = "lat")
        public double lat;
        @Json(name = HybridSDK.LNG)
        public double lng;
    }

    /* loaded from: classes9.dex */
    public static class RotationParams extends JsonComposer {
        @Json(name = "rotationX")
        public float rotationX;
        @Json(name = "rotationY")
        public float rotationY;
        @Json(name = "rotationZ")
        public float rotationZ;
    }

    /* loaded from: classes9.dex */
    public static class ScaleParams extends JsonComposer {
        @Json(name = "scale")
        public float scale;
    }

    /* loaded from: classes9.dex */
    public static class SkeletonAnimationParams extends JsonComposer {
        @Json(name = "animationInfo")
        public List<AnimationInfo> animationInfoList;
    }

    /* loaded from: classes9.dex */
    public static class StartTranslateAnimationParams extends JsonComposer {
        @Json(name = "duration")
        public float duration;
        @Json(name = "initRotation")
        public float initRotation;
        @Json(name = "needRotate")
        public boolean needRotate;
        @Json(deserializer = LatLngListDeserializer.class, name = "positions")
        public List<LatLng> positions;
    }

    /* loaded from: classes9.dex */
    public static class VisibleParams extends JsonComposer {
        @Json(name = XView2Constants.ISVISIBLE)
        public boolean isVisible;
    }

    /* loaded from: classes9.dex */
    public static class ZoomLevelRangeParams extends JsonComposer {
        @Json(name = "maxLevel")
        public int maxLevel;
        @Json(name = "minLevel")
        public int minLevel;
    }
}
