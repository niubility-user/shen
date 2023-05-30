package com.jingdong.cleanmvp.presenter;

import com.jingdong.cleanmvp.common.BaseEvent;
import de.greenrobot.event.EventBus;

/* loaded from: classes4.dex */
public abstract class BaseInteractor {
    public abstract void cancleIO();

    public abstract void clearState(int i2);

    public void postEvent(BaseEvent baseEvent) {
        EventBus.getDefault().post(baseEvent);
    }
}
