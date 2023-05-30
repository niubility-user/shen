package com.jingdong.jdreact.plugin.jdmodal;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/* loaded from: classes13.dex */
class JDShowEvent extends Event<JDShowEvent> {
    public static final String EVENT_NAME = "topShow";

    /* JADX INFO: Access modifiers changed from: protected */
    public JDShowEvent(int i2) {
        super(i2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), null);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topShow";
    }
}
