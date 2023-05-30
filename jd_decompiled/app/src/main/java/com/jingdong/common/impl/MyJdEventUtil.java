package com.jingdong.common.impl;

import de.greenrobot.event.EventBus;

/* loaded from: classes5.dex */
public class MyJdEventUtil {
    private static volatile EventBus mEventBus;

    public static EventBus getEventBus() {
        if (mEventBus == null) {
            synchronized (EventBus.class) {
                if (mEventBus == null) {
                    mEventBus = new EventBus();
                }
            }
        }
        return mEventBus;
    }
}
