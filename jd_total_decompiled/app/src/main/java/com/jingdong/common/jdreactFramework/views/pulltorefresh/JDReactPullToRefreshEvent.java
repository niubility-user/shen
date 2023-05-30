package com.jingdong.common.jdreactFramework.views.pulltorefresh;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/* loaded from: classes5.dex */
public class JDReactPullToRefreshEvent extends Event<JDReactPullToRefreshEvent> {
    /* JADX INFO: Access modifiers changed from: protected */
    public JDReactPullToRefreshEvent(int i2) {
        super(i2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), null);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topRefresh";
    }
}
