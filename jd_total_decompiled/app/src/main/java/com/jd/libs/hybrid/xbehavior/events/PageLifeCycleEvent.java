package com.jd.libs.hybrid.xbehavior.events;

import com.jd.libs.hybrid.xbehavior.base.BaseEvent;

/* loaded from: classes16.dex */
public class PageLifeCycleEvent extends BaseEvent {
    public static final String STATE_ACTIVE = "ContainerActive";
    public static final String STATE_CREATE = "ContainerCreate";
    public static final String STATE_DESTROY = "ContainerDestroy";
    public static final String STATE_HIDE = "ContainerHide";
    public static final String STATE_INACTIVE = "ContainerInActive";
    public static final String STATE_SHOW = "ContainerShow";
    public String activityName;
    public String source;
    public String state;

    public PageLifeCycleEvent(String str, String str2, String str3) {
        this.activityName = str;
        this.state = str2;
        this.source = str3;
    }

    @Override // com.jd.libs.hybrid.xbehavior.base.BaseEvent
    public String getName() {
        return "PageLifeCycleEvent";
    }

    @Override // com.jd.libs.hybrid.xbehavior.base.BaseEvent
    public String toJson() {
        return null;
    }
}
