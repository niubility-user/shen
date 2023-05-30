package com.jd.libs.hybrid.xbehavior.handlers;

import com.jd.libs.hybrid.xbehavior.base.IEventHandler;
import com.jd.libs.hybrid.xbehavior.events.PageLifeCycleEvent;
import com.jd.libs.hybrid.xbehavior.lifecycle.LifeCycleDispatcher;

/* loaded from: classes16.dex */
public class PageLifeCycleEventHandler implements IEventHandler<PageLifeCycleEvent> {
    @Override // com.jd.libs.hybrid.xbehavior.base.IEventHandler
    public void handleEvent(PageLifeCycleEvent pageLifeCycleEvent) {
        if (PageLifeCycleEvent.STATE_CREATE.equals(pageLifeCycleEvent.state)) {
            LifeCycleDispatcher.dispatchOnCreate(pageLifeCycleEvent.activityName, pageLifeCycleEvent.source);
        }
    }
}
