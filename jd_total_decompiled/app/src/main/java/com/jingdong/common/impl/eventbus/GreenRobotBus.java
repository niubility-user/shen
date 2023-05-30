package com.jingdong.common.impl.eventbus;

import com.jingdong.common.impl.MyJdEventUtil;
import com.jingdong.common.protocol.eventbus.IEventBus;

/* loaded from: classes5.dex */
public class GreenRobotBus implements IEventBus {
    @Override // com.jingdong.common.protocol.eventbus.IEventBus
    public boolean isRegistered(Object obj) {
        return MyJdEventUtil.getEventBus().isRegistered(obj);
    }

    @Override // com.jingdong.common.protocol.eventbus.IEventBus
    public void postEvent(Object obj) {
        MyJdEventUtil.getEventBus().post(obj);
    }

    @Override // com.jingdong.common.protocol.eventbus.IEventBus
    public void register(Object obj) {
        MyJdEventUtil.getEventBus().register(obj);
    }

    @Override // com.jingdong.common.protocol.eventbus.IEventBus
    public void unregister(Object obj) {
        MyJdEventUtil.getEventBus().unregister(obj);
    }
}
