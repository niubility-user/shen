package com.jingdong.common.protocol.eventbus;

/* loaded from: classes5.dex */
public interface IEventBus {
    boolean isRegistered(Object obj);

    void postEvent(Object obj);

    void register(Object obj);

    void unregister(Object obj);
}
