package com.jingdong.jdreact.plugin.map;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;

/* loaded from: classes13.dex */
public class RegionChangeEvent extends Event<RegionChangeEvent> {
    private final LatLngBounds bounds;
    private final LatLng center;
    private final boolean continuous;

    public RegionChangeEvent(int i2, LatLngBounds latLngBounds, LatLng latLng, boolean z) {
        super(i2);
        this.bounds = latLngBounds;
        this.center = latLng;
        this.continuous = z;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putBoolean("continuous", this.continuous);
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putDouble(PdLVBody.LATITUDE, this.center.getLatitude());
        writableNativeMap2.putDouble(PdLVBody.LONGITUDE, this.center.getLongitude());
        writableNativeMap2.putDouble("latitudeDelta", this.bounds.northeast.getLatitude() - this.bounds.southwest.getLatitude());
        writableNativeMap2.putDouble("longitudeDelta", this.bounds.northeast.getLongitude() - this.bounds.southwest.getLongitude());
        writableNativeMap.putMap("region", writableNativeMap2);
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), writableNativeMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topChange";
    }
}
