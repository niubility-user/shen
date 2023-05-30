package com.jingdong.jdreact.plugin.jdmodal;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/* loaded from: classes13.dex */
class JDRequestCloseEvent extends Event<JDRequestCloseEvent> {
    public static final String EVENT_NAME = "topRequestClose";

    /* JADX INFO: Access modifiers changed from: protected */
    public JDRequestCloseEvent(int i2) {
        super(i2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), null);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topRequestClose";
    }
}
