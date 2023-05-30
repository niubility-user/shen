package com.jingdong.jdreact.plugin.jdmodal;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/* loaded from: classes13.dex */
class JDShowEvent2 extends Event<JDShowEvent> {
    public static final String EVENT_NAME = "topShow2";
    private int mId;

    /* JADX INFO: Access modifiers changed from: protected */
    public JDShowEvent2(int i2) {
        super(i2);
        this.mId = 0;
        this.mId = i2;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("modalID", this.mId);
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), writableNativeMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }
}
