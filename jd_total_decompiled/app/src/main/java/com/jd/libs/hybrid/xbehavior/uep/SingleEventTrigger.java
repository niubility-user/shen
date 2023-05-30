package com.jd.libs.hybrid.xbehavior.uep;

import com.jd.libs.hybrid.xbehavior.base.BaseEvent;
import com.jd.libs.hybrid.xbehavior.base.IEventHandler;
import com.jd.libs.hybrid.xbehavior.base.ITrigger;
import java.util.HashMap;

/* loaded from: classes16.dex */
public class SingleEventTrigger implements ITrigger {
    private HashMap<Class<? extends BaseEvent>, IEventHandler> a = new HashMap<>();

    @Override // com.jd.libs.hybrid.xbehavior.base.ITrigger
    public void onEvent(BaseEvent baseEvent) {
        IEventHandler iEventHandler = baseEvent == null ? null : this.a.get(baseEvent.getClass());
        if (iEventHandler != null) {
            iEventHandler.handleEvent(baseEvent);
        }
    }

    public <E extends BaseEvent> void registerHandler(Class<E> cls, IEventHandler<E> iEventHandler) {
        if (cls == null || iEventHandler == null) {
            return;
        }
        this.a.put(cls, iEventHandler);
    }
}
