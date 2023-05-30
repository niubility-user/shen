package com.jd.libs.hybrid.xbehavior;

import androidx.annotation.Keep;
import com.jd.libs.hybrid.xbehavior.base.BaseEvent;
import com.jd.libs.hybrid.xbehavior.events.PageLifeCycleEvent;
import com.jd.libs.hybrid.xbehavior.events.RequestEvent;
import com.jd.libs.hybrid.xbehavior.handlers.PageLifeCycleEventHandler;
import com.jd.libs.hybrid.xbehavior.handlers.RequestEventHandler;
import com.jd.libs.hybrid.xbehavior.uep.SingleEventTrigger;
import java.util.LinkedList;

@Keep
/* loaded from: classes16.dex */
public class XBehaviorManager {
    private static XBehaviorManager sInstance;
    private LinkedList<BaseEvent> mEventStream = new LinkedList<>();
    private SingleEventTrigger mSingleEventTrigger;

    private XBehaviorManager() {
        SingleEventTrigger singleEventTrigger = new SingleEventTrigger();
        this.mSingleEventTrigger = singleEventTrigger;
        singleEventTrigger.registerHandler(RequestEvent.class, new RequestEventHandler());
        this.mSingleEventTrigger.registerHandler(PageLifeCycleEvent.class, new PageLifeCycleEventHandler());
    }

    public static XBehaviorManager getInstance() {
        if (sInstance == null) {
            sInstance = new XBehaviorManager();
        }
        return sInstance;
    }

    public void dispatchEvent(BaseEvent baseEvent) {
        this.mEventStream.offer(baseEvent);
        this.mSingleEventTrigger.onEvent(baseEvent);
    }
}
