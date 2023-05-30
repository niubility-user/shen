package com.tencent.map.lib.models;

import com.jd.dynamic.DYConstants;
import com.jingdong.common.XView2.common.XView2Constants;
import com.tencent.map.sdk.comps.vis.VisualLayer;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public class EventParamsModelClass {

    /* loaded from: classes9.dex */
    public static class ClickEventParams extends EventParams {
        @Json(name = "clickedPosition")
        public float[] clickedPosition;
        @Json(name = DYConstants.DY_IDENTIFIER)
        public String identifier;
        @Json(name = "name")
        public String name;

        public ClickEventParams(String str, LatLng latLng, String str2, String str3) {
            this.eventType = VisualLayer.OnLayerStatusChangedListener.EventType.ON_CLICK;
            this.layerId = str;
            this.clickedPosition = r3;
            float[] fArr = {(float) latLng.getLatitude()};
            this.clickedPosition[1] = (float) latLng.getLongitude();
            this.identifier = str2;
            this.name = str3;
        }
    }

    /* loaded from: classes9.dex */
    public static class EventParams extends JsonComposer {
        @Json(name = "eventType")
        public String eventType;
        @Json(name = XView2Constants.LAYER_ID)
        public String layerId;
    }

    /* loaded from: classes9.dex */
    public static class LoadFinishEventParams extends EventParams {
        public int errorCode;
        public String errorMsg;

        /* loaded from: classes9.dex */
        public enum LoadFinishInfo {
            ok(0, ""),
            errNetwork(1, VisualLayer.OnLayerStatusChangedListener.LayerStatusMsg.MSG_ERR_NETWORK),
            errAuth(2, VisualLayer.OnLayerStatusChangedListener.LayerStatusMsg.MSG_ERR_AUTH),
            errDataDecode(3, VisualLayer.OnLayerStatusChangedListener.LayerStatusMsg.MSG_ERR_DATA_DECODE),
            errDataAvailable(4, VisualLayer.OnLayerStatusChangedListener.LayerStatusMsg.MSG_ERR_DATA_AVAILABLE),
            errInternal(20, VisualLayer.OnLayerStatusChangedListener.LayerStatusMsg.MSG_ERR_INTERNAL_ERROR);
            
            private int errorCode;
            private String errorMsg;

            LoadFinishInfo(int i2, String str) {
                this.errorCode = i2;
                this.errorMsg = str;
            }

            public static LoadFinishInfo getById(int i2) {
                LoadFinishInfo[] values = values();
                for (int i3 = 0; i3 < 6; i3++) {
                    LoadFinishInfo loadFinishInfo = values[i3];
                    if (loadFinishInfo.checkStatus(i2)) {
                        return loadFinishInfo;
                    }
                }
                return ok;
            }

            public boolean checkStatus(int i2) {
                return this.errorCode == i2;
            }

            public int getErrorCode() {
                return this.errorCode;
            }

            public String getErrorMsg() {
                return this.errorMsg;
            }
        }

        public LoadFinishEventParams(String str, LoadFinishInfo loadFinishInfo) {
            this.eventType = VisualLayer.OnLayerStatusChangedListener.EventType.ON_LAYER_LOAD_FINISH;
            this.layerId = str;
            this.errorCode = loadFinishInfo.errorCode;
            this.errorMsg = loadFinishInfo.errorMsg;
        }
    }

    /* loaded from: classes9.dex */
    public static class TranslateAnimationCompleteEventParams extends EventParams {
        public TranslateAnimationCompleteEventParams(String str) {
            this.eventType = VisualLayer.OnLayerStatusChangedListener.EventType.ON_TRANSLATEANIMATION_COMPLETE;
            this.layerId = str;
        }
    }
}
