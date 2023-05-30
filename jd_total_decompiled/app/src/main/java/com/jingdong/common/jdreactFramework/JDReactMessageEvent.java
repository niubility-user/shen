package com.jingdong.common.jdreactFramework;

import com.facebook.react.bridge.ReadableMap;
import com.jingdong.cleanmvp.common.BaseEvent;

/* loaded from: classes5.dex */
public class JDReactMessageEvent extends BaseEvent {
    private ReadableMap eventParams;

    public JDReactMessageEvent(String str) {
        super(str);
    }

    public ReadableMap getEventParams() {
        return this.eventParams;
    }

    public void setEventParams(ReadableMap readableMap) {
        this.eventParams = readableMap;
    }

    public JDReactMessageEvent(String str, ReadableMap readableMap) {
        super(str);
        this.eventParams = readableMap;
    }
}
