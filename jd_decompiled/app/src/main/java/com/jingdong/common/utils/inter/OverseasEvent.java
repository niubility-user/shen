package com.jingdong.common.utils.inter;

import com.jingdong.cleanmvp.common.BaseEvent;
import de.greenrobot.event.EventBus;

/* loaded from: classes6.dex */
public class OverseasEvent extends BaseEvent {
    public static final String OVERSEAS_CHANGE = "OVERSEAS_CHANGE";

    public OverseasEvent() {
        super(OVERSEAS_CHANGE, OVERSEAS_CHANGE);
    }

    public static void postOverseasChanged() {
        EventBus.getDefault().post(new OverseasEvent());
    }
}
