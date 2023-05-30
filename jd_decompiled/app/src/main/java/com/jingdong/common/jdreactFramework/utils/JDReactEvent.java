package com.jingdong.common.jdreactFramework.utils;

import com.jingdong.cleanmvp.common.BaseEvent;

/* loaded from: classes5.dex */
public class JDReactEvent extends BaseEvent {
    public static final String TYPE_JDREACT_EVENT = "TYPE_JDREACT_EVENT";
    public static final String TYPE_JDREACT_EVENT_NEW = "TYPE_JDREACT_EVENT_NEW";

    public JDReactEvent() {
        super(TYPE_JDREACT_EVENT);
    }

    public JDReactEvent(String str) {
        super(str);
    }
}
